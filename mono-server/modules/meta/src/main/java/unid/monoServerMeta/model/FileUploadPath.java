package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum FileUploadPath implements NamedEnum {
    WRITING_SKILL("writing-skills"),
    INTERVIEW_SKILL("interview-skills"),
    PROFILE_PICTURE("profiles"),
    SCHOOL_REPORT("school-reports"),

    ECA("eca"),
    ACADEMIC_SUBJECT_READING("academic-subject-reading"),
    ACADEMIC_MAJOR("academic-major"),
    COURSE("course"),
    WEBINAR("webinar"),


    ;

    private final String value;

    @Override
    public String toNamedString() {
        return this.value;
    }

    public static FileUploadPath getBy(String value){
        for(FileUploadPath path : FileUploadPath.values()){
            if(path.name().equals(value)){
                return path;
            }
        }
        return null;
    }
}
