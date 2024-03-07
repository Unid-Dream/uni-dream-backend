package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum SchoolLevel implements NamedEnum {
    SECONDARY_SCHOOL("SECONDARY_SCHOOL"),
    UNIVERSITY("UNIVERSITY");
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
