package com.example.testSpringApp.api.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum UserRole implements NamedEnum {
    ADMIN(0, "ADMINSSS"),
    MERCHANT(1, "MERCHANTSSS");

    private final int index;
    private final String value;

    @Override
    public String toNamedString() {
        return String.format("%s_%s", index, value);
    }
}
