package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum Gender implements NamedEnum {
    MALE("MALE"),

    FEMALE("FEMALE"),

    SECRET("SECRET");
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
