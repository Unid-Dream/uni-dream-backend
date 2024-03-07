package unid.monoServerApp.feign.cod.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum CodService implements NamedEnum {
    CREATE_ORDER("create_order"),
    ORDER_DETAILS("order_details");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
