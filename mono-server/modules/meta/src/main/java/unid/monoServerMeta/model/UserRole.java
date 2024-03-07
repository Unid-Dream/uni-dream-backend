package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum UserRole implements NamedEnum {
    ROOT("ROOT"),
    ADMIN("ADMIN"),
    EDUCATOR("EDUCATOR"),
    STUDENT("STUDENT");
    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
