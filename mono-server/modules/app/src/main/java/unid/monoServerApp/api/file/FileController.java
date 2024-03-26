package unid.monoServerApp.api.file;//package unid.monoServerApp.api.country;

import cn.hutool.core.util.StrUtil;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.FileMapper;
import unid.monoServerApp.service.JwtTokenService;
import unid.monoServerApp.service.S3Service;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.FileResponse;
import unid.monoServerMeta.model.FileUploadPath;
import unid.monoServerMeta.model.JwtTokenClaims;
import unid.monoServerMeta.model.TransactionItem;

import javax.validation.Valid;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Files")
@Slf4j
public class FileController {
    private final SessionService sessionService;
    private final S3Service s3Service;
    private final FileMapper fileMapper;

    private final JwtTokenService jwtTokenService;

    @GetMapping("uploadDestination")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Get Upload Destination"
    )
    @Hidden
    public @Valid UnifiedResponse<FileResponse> get() {
        sessionService.allowAuthedOnly();
        return UnifiedResponse.of(
                fileMapper.toResponse(s3Service.generateUpload())
        );
    }

    @GetMapping("{fileName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get File URL"
    )
    @Hidden
    public @Valid RedirectView get(
            @PathVariable("fileName") String fileName
    ) {
        return new RedirectView(s3Service.getDisplayUrl(fileName));
    }

    private static final long DURATION_SECONDS = 1000 * 60 * 30; //临时URL 过期时间
    private static final String BUCKET_NAME = "file-jjl-dev"; //Bucket 名称

    private static final String s3AccessKey = "AKIA3FLD543H7ZXHRDJW";
    private static final String s3SecretKey= "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";

    @GetMapping(value = {"student/file/preSignedUrl","educator/file/preSignedUrl","admin/file/preSignedUrl"})
    @ResponseStatus(HttpStatus.OK)
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT,UserRoleEnum.EDUCATOR, UserRoleEnum.ADMIN}
    )
    @Operation(
            summary = "Get PreSignedUrl"
    )
    public @Valid UnifiedResponse<Map<String,Object>> getPreSignedUrl(
            @RequestParam String fileName,
            @RequestParam String enumName,
            @RequestParam(required = false) String contentType,
            @RequestHeader String unidtoken
    ) {

        JwtTokenClaims jwtTokenClaims = jwtTokenService.getClaimsObject(unidtoken).orElseThrow(()->Exceptions.unauthorized(" token is expired!"));
        FileUploadPath path = Optional.ofNullable(FileUploadPath.getBy(enumName))
                .orElseThrow(()-> Exceptions.notFound("File Upload Path"));
        String s3FilePath = path.getValue() + "/" + jwtTokenClaims.getUserId() +"/"+fileName;

        Map<String, Object> map = new HashMap<>();
        try {
            AWSCredentials awsCredentials = new BasicAWSCredentials(s3AccessKey, s3SecretKey);
            AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withRegion(Regions.AP_SOUTHEAST_1)
                    .withCredentials(awsCredentialsProvider)
                    .build();
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime() + DURATION_SECONDS;
            expiration.setTime(expTimeMillis);
            String name = s3FilePath.substring(0,s3FilePath.lastIndexOf("."));
            String fileType = s3FilePath.substring(s3FilePath.lastIndexOf("."));
            String prefixFileName = name+ "_"+String.valueOf(System.currentTimeMillis()).substring(6)+""+fileType;
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, prefixFileName)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);
            if(StrUtil.isNotEmpty(contentType)){
                generatePresignedUrlRequest.withContentType(contentType);
            }
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            if(url == null){
                throw Exceptions.unknownError("Can not generate PreSingedUrl");
            }
            // 文件访问地址
            StringBuilder urlImage = new StringBuilder();
            urlImage.append(url.getProtocol()).append("://").append(url.getHost()).
                    append(URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8));
            // 预签名put地址
            StringBuilder preUrl = new StringBuilder();
            preUrl.append(url.getProtocol()).append("://").append(url.getHost()).
                    append(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8));
            map.put("preUrl",preUrl);
            map.put("filePath","/"+s3FilePath);
         } catch (Exception e) {
            e.printStackTrace();
         }
        return UnifiedResponse.of(map);
    }


}
