package unid.monoServerApp.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PresignedUrlUploadRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pwh.springStarter.service.ValidationService;
import unid.monoServerApp.Properties;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerMeta.model.FileUploadPath;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * DEV solution, pending for an acl version
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class S3Service {
    private static final String s3AccessKey = "AKIA3FLD543H7ZXHRDJW";
    private static final String s3SecretKey= "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";
    private static final String BUCKET_NAME = "file-jjl-dev"; //Bucket 名称

    private final AmazonS3Client s3Client;
    private final ObjectMapper objectMapper;
    private final ValidationService validationService;
    private final Properties properties;

    public Url generateUpload() {
        var organicName = String.format("%s-%s", UUID.randomUUID(), UUID.randomUUID());
        var request = new PresignedUrlUploadRequest(
                s3Client.generatePresignedUrl(
                        properties.getS3Bucket(),
                        toTempFile(organicName),
                        DateUtils.addSeconds(
                                new Date(),
                                properties.getFileUploadTimeoutSeconds().intValue()
                        ),
                        HttpMethod.PUT
                )
        );
        return new Url(organicName, properties.getFileUploadTimeoutSeconds(), request);
    }

    public void tempToPermanent(
            String organicName
//            S3UserMeta userMeta,
//            S3FileType fileType
    ) {
        if (StringUtils.isBlank(organicName)) {
            return;
        }
//        validationService.validate(userMeta);
//        var meta = new ObjectMetadata();
//        meta.setUserMetadata(
//                objectMapper.convertValue(
//                        userMeta,
//                        new TypeReference<>() {}
//                )
//        );
//        meta.setContentType(fileType.name().toLowerCase());
        var tempName = toTempFile(organicName);
        if (s3Client.doesObjectExist(properties.getS3Bucket(), tempName)) {
            var request = new CopyObjectRequest()
                    .withSourceBucketName(properties.getS3Bucket())
                    .withSourceKey(tempName)
                    .withDestinationBucketName(properties.getS3Bucket())
                    .withDestinationKey(organicName);
//                    .withNewObjectMetadata(meta);
            s3Client.copyObject(request);
//            deleteObject(tempName);
        }
    }

    /**
     * 由于保存的文件名是 /xxx/xx/xxx.jpg 的方式，但是s3中删除文件名不允许以/开头
     * @param fileName
     */
//    @CacheEvict(value = CacheTags.FILE, key = "#fileName")
    public void deleteObject(FileUploadPath path,String fileName) {
        fileName = StrUtil.replace(fileName,"/"+path.getValue(),path.getValue());
        if (StringUtils.isNotBlank(fileName)) {
            AWSCredentials awsCredentials = new BasicAWSCredentials(s3AccessKey, s3SecretKey);
            AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
            AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withRegion(Regions.AP_SOUTHEAST_1)
                    .withCredentials(awsCredentialsProvider)
                    .build();
            try{
                amazonS3.deleteObject(BUCKET_NAME, fileName);
            }catch (Exception e){
                StaticLog.error(e);
            }
        }
    }

//    @CacheEvict(value = CacheTags.FILE, key = "#oldFile")
    public void oldToNew(@Nullable String oldFile, @Nullable String newFile) {
        if (!StringUtils.equals(oldFile, newFile)) {
//            deleteObject(oldFile);
//            tempToPermanent(newFile);
        }
    }

    private String toTempFile(String organicName) {
        return String.format("%s%s", properties.getS3FileTempPrefix(), organicName);
    }

//    @Cacheable(value = CacheTags.FILE, key = "#fileName")
    public String getDisplayUrl(String fileName) {
        var url = new GeneratePresignedUrlRequest(
                properties.getS3Bucket(), fileName
        );
        return s3Client.generatePresignedUrl(url).toString();
    }

    @Data
    @AllArgsConstructor
    public static class Url {
        @NotBlank
        private String fileName;
        @NotNull
        private Long timeoutSeconds;
        @NotNull
        private PresignedUrlUploadRequest request;
    }
}
