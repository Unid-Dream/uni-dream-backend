package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum SerialNumberType implements NamedEnum{
    AcademicMajor("EDU"),
    ;

    private final String value;
    @Override
    public String toNamedString() {
        return this.value;
    }
}
