package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum ReviewStatus implements NamedEnum {
    PENDING("PENDING"),
    REVIEWED("REVIEWED"),
    ;

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
