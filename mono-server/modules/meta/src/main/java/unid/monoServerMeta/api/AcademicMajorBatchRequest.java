package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import java.util.List;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class AcademicMajorBatchRequest {

    private I18n nameI18n;
    private I18n descI18n;
    private List<AcademicSubjectItem> subjects;


    @Data
    public static class AcademicSubjectItem{
        private I18n nameI18n;
        private I18n descI18n;
        private List<ReadingItem> books;
        private List<VideoItem> videos;
        private List<PodcastItem> podcasts;
        private List<I18n> answers;

    }


    @Data
    public static class ReadingItem{
        private I18n nameI18n;
        private I18n authorI18n;
        private String image;
    }

    @Data
    public static class VideoItem{
        private I18n nameI18n;
        private I18n authorI18n;
        private String url;
        private String type;
    }

    @Data
    public static class PodcastItem{
        private I18n nameI18n;
        private I18n authorI18n;
        private String url;
    }

}




