package com.example.testSpringApp.feign.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;

@Component
@FeignClient(name = "TestApi",
        url = "https://jsonplaceholder.typicode.com",
        // allow multiple configs
        configuration = {TestApiConfig.class})
@Validated
public interface TestApi {

    // query as pojo for better code
    // others like request body or path variable could just use ordinary spring annotation
    @GetMapping("posts")
    List<TestApiResponse> getPosts(@Valid @SpringQueryMap TestApiQuery query);
}
