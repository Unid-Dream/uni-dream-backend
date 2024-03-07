package unid.monoServerApp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Configuration("unid.monoServerApp.properties")
@ConfigurationProperties(prefix = "unid.monoserver-app")
@Data
@Validated
public class Properties {
    @NotBlank
    private String sqsMain;
    @NotBlank
    private String snsEmail;
    @NotBlank
    private String snsTagging;
    @NotBlank
    private String snsEducatorMeeting;
    @NotBlank
    private String snsPendingTransaction;
    @NotBlank
    private String snsPendingConfirmedMeeting;
    @NotBlank
    private String snsExpiredMeeting;
    @NotBlank
    private String emailPersonal;
    @NotNull
    private Long registerUserTimeoutMinutes;
    @NotNull
    private Long registerUserOtpResendSeconds;
    @NotNull
    private Integer registerUserOtpResendMaxRetires;
    @NotNull
    private Long jwtExpiryMinutes;
    @NotBlank
    private String jwtIssuer;
    @NotBlank
    private String s3Bucket;
    @NotBlank
    private String s3FilePath;
    @NotBlank
    private String s3FileTempPrefix;
    @NotNull
    private Long fileUploadTimeoutSeconds;
    @NotBlank
    private String codApiHost;
    @NotBlank
    private String codMerchantId;
    @NotBlank
    private String codPid;
    @NotBlank
    private String codSid;
    @NotBlank
    private String codPrivateKeyBase64;
    @NotBlank
    private String codPublicKeyBase64;
    @NotBlank
    private String codSecretKey128;
    @NotBlank
    private String codSecretKey256;
    @NotNull
    private Integer codPaymentTimeoutMinutes;
    @NotNull
    private Integer meetingAllowedToBookBeforeStartMinutes;
    @NotNull
    private Integer meetingAllowedToAcceptBeforeStartMinutes;
    @NotNull
    private Integer meetingAllowedToPayBeforeStartMinutes;
    @NotBlank
    private String msClientId;
    @NotBlank
    private String msTenantId;
    @NotBlank
    private String msSecretValue;
    @NotBlank
    private String msSecretId;
    @NotBlank
    private String msOrganizerId;
}
