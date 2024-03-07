package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.monoServerApp.service.S3Service;
import unid.monoServerMeta.api.FileResponse;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class
        }
)
public interface FileMapper {

    default FileResponse toResponse(S3Service.Url url) {
        return new FileResponse()
                .setUrl(url.getRequest().getPresignedUrl().toString())
                .setValidWithinSeconds(url.getTimeoutSeconds())
                .setFileName(url.getFileName());
    }
}
