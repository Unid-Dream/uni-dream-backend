package com.example.testCoreApp;

import lombok.extern.slf4j.Slf4j;
import pwh.coreJooqLibs.converter.HashingConverter;

@Slf4j
public class CoreTestApp {

    public static void main(String args[]) {
        var convertor = new HashingConverter();
        var input = "dllm";
        var hashed = convertor.to(input);
        log.info("Hashed: {}", hashed);
        log.info("Retrieved: {}", convertor.from(hashed));
        log.info("Equals: {}", convertor.compareIsTheSame(hashed, "dllm"));
    }
}
