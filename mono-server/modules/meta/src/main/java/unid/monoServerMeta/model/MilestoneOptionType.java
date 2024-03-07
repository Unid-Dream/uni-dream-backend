package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum MilestoneOptionType implements NamedEnum {
    PROVIDED_CHOICE("PROVIDED_CHOICE"),

    USER_INPUT("USER_INPUT");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
