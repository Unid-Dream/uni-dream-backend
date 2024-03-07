package unid.monoServerApp.feign.cod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pwh.coreStarter.type.NamedEnum;
import pwh.springStarter.service.ValidationService;
import unid.monoServerApp.Properties;
import unid.monoServerApp.feign.cod.model.*;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Validated
public class CodApiService {
    private final CodApi codApi;
    private final ObjectMapper objectMapper;
    private final Properties properties;
    private final ValidationService validationService;
    private final String alogrithm = "AES/CBC/PKCS5Padding";
    private byte[] encryptPk;
    private byte[] encryptPubK;

    @PostConstruct
    void pc() {
        encryptPk = java.util.Base64.getDecoder().decode(
                properties.getCodPrivateKeyBase64().getBytes(StandardCharsets.UTF_8)
        );
        encryptPubK = java.util.Base64.getDecoder().decode(
                properties.getCodPublicKeyBase64().getBytes(StandardCharsets.UTF_8)
        );
    }

    @SneakyThrows
    private <Request, Response> CodResponse<Response> request(CodService service, Request request, Class<Response> responseType) {
        var payload = CodRequestPayload.<Request>builder()
                .requestUuid(UUID.randomUUID())
                .requestTimeEpoch(Long.toString(System.currentTimeMillis() / 1000L))
                .service(service)
                .merchantId(properties.getCodMerchantId())
                .requestObject(request)
                .build();
        validationService.validate(payload);
        var payloadJsonStr = objectMapper.writeValueAsString(payload);

        // Secure random iv
        var secureRandom = new SecureRandom();
        var iv = new byte[16]; //NEVER REUSE THIS IV WITH THE SAME KEY
        secureRandom.nextBytes(iv);

        // Algorithms
        var secretKey = new SecretKeySpec(properties.getCodSecretKey128().getBytes(StandardCharsets.UTF_8), "AES");
        var parameterSpecEncrypt = new IvParameterSpec(iv);
        var cipherEncrypt = Cipher.getInstance(alogrithm);
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpecEncrypt);

        // Cipher text
        var cipherText = cipherEncrypt.doFinal(payloadJsonStr.getBytes(StandardCharsets.UTF_8));

        // Concat iv and cipher text for signing
        @Cleanup var outputStream = new ByteArrayOutputStream();
        outputStream.write(iv);
        outputStream.write(cipherText);
        var signMessage = outputStream.toByteArray();

        // Sign key
        @Cleanup var isr = new InputStreamReader(new ByteArrayInputStream(encryptPk));
        @Cleanup var pemParser = new PEMParser(isr);
        var parserObj = pemParser.readObject();
        AsymmetricKeyParameter privateKey = null;
        if (parserObj instanceof PrivateKeyInfo) {
            privateKey = PrivateKeyFactory.createKey((PrivateKeyInfo) parserObj);
        } else {
            var keyPair = (PEMKeyPair) pemParser.readObject();
            var pki = keyPair.getPrivateKeyInfo();
            privateKey = PrivateKeyFactory.createKey(pki);
        }

        // Signing
        var signer = new RSADigestSigner(new SHA256Digest());
        signer.init(true, privateKey);
        signer.update(signMessage, 0, signMessage.length);
        var signature = signer.generateSignature();

        var encoder = java.util.Base64.getEncoder();

        // Combine result
        log.info("Message Unencrypted: {}", payloadJsonStr);
        log.info("Message Encrypted {}", cipherText);
        var finalRequest = CodRequest.builder()
                .merchantId(properties.getCodMerchantId())
                .message(encoder.encodeToString(cipherText))
                .nonce(encoder.encodeToString(iv))
                .tag(encoder.encodeToString(signature))
                .cipherSuite("aes-128-cbc-pkcs7-with-rsa-sha256")
                .build();
        log.info("Final Request {}", objectMapper.writeValueAsString(finalRequest));
        CodResponse<Response> result = null;
        try {
            result = codApi.request(finalRequest);
        } catch (FeignException.Unauthorized | FeignException.BadRequest | FeignException.Forbidden u) {
            // TODO: not ideal, temp solution. Should have let convert above error status into 200 so that feign parse the result as-is
            result = objectMapper.readValue(u.responseBody().get().array(), new TypeReference<>() {
            });
        }
        if (result.getResult() != null) {
            var decoder = java.util.Base64.getDecoder();
            var decodedMessageBytes = decoder.decode(result.getResult().getBase64EncodedEncryptedMessage());
            var decodedIv = decoder.decode(result.getResult().getNonce());
            var parameterSpecDecrypt = new IvParameterSpec(decodedIv);
            var cipherDecrypt = Cipher.getInstance(alogrithm);
            cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKey, parameterSpecDecrypt);
            result.setResultObj(objectMapper.readValue(cipherDecrypt.doFinal(decodedMessageBytes), responseType));
        }
        return result;
    }

    public CodResponse<CodCreateOrderResponse> createOrder(CodCreateOrderRequest.CodCreateOrderRequestBuilder<?, ?> request) {
        request.segmentId(properties.getCodSid());
        request.timeoutMinutes(properties.getCodPaymentTimeoutMinutes());
        return request(CodService.CREATE_ORDER, request.build(), CodCreateOrderResponse.class);
    }

    public CodResponse<CodOrderDetailResponse> getOrder(CodOrderDetailRequest.CodOrderDetailRequestBuilder<?, ?> request) {
        return request(CodService.ORDER_DETAILS, request.build(), CodOrderDetailResponse.class);
    }

    @SneakyThrows
    public CodNotificationType getType(CodNotification payload) {
        var jsonNode = objectMapper.readTree(payload.getData());
        return NamedEnum.get(CodNotificationType.class, jsonNode.path("type").asText());
    }

    @SneakyThrows
    public boolean verify(CodNotification payload) {
        var decoder = Base64.getDecoder();
        @Cleanup var isr = new InputStreamReader(new ByteArrayInputStream(encryptPubK));
        @Cleanup var pemParser = new PEMParser(isr);
        var parserObj = pemParser.readObject();
        AsymmetricKeyParameter publicKey = null;
        if (parserObj instanceof SubjectPublicKeyInfo) {
            publicKey = PublicKeyFactory.createKey((SubjectPublicKeyInfo) parserObj);
        } else {
            var keyPair = (PEMKeyPair) pemParser.readObject();
            var pki = keyPair.getPublicKeyInfo();
            publicKey = PublicKeyFactory.createKey(pki);
        }
        var verifier = new RSADigestSigner(new SHA256Digest());
        verifier.init(false, publicKey);
        var signMessageForVerifing = payload.getData().getBytes();
        var signatureBase64ForVerifing = decoder.decode(payload.getSignatureBase64());
        verifier.update(signMessageForVerifing, 0, signMessageForVerifing.length);
        return verifier.verifySignature(signatureBase64ForVerifing);
    }

    public boolean verify(CodNotificationPayment payload) {
        return
                properties.getCodMerchantId().equals(payload.getMerchantId())
                        && properties.getCodSid().equals(payload.getSegmentId());
//                        && properties.getCodPid().equals(payload.getPid());
    }

    public boolean verify(CodNotificationRefund payload) {
        return
                properties.getCodMerchantId().equals(payload.getMerchantId())
                        && properties.getCodSid().equals(payload.getSegmentId())
                        && properties.getCodPid().equals(payload.getPid());
    }
}
