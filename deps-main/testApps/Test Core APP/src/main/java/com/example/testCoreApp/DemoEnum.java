package com.example.testCoreApp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum DemoEnum implements NamedEnum {
    JOSHUA("joshua is on99"),
    CHECHEGOR("che che gor = joshua");

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
