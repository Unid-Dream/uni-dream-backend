package unid.monoServerApp.feign.cod;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unid.monoServerApp.feign.cod.model.CodRequest;
import unid.monoServerApp.feign.cod.model.CodResponse;

import javax.validation.Valid;

@Component
@FeignClient(
        name = "CodApi",
        url = "https://${unid.monoserver-app.cod-api-host}",
        configuration = {CodApiConfig.class}
)
@Validated
interface CodApi {
    @PostMapping("v1/service")
    @Headers("Content-Type: application/json;charset=utf-8")
    <Response> CodResponse<Response> request(@Valid @RequestBody CodRequest payload);
}
