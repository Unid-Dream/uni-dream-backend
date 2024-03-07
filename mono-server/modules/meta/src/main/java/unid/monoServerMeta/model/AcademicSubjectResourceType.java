package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum AcademicSubjectResourceType implements NamedEnum {
    READINGS("READINGS"),
    VIDEO("VIDEO"),
    PODCAST("PODCAST");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
