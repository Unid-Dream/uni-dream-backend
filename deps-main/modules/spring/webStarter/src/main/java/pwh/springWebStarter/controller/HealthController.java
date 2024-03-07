package pwh.springWebStarter.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * for any containerized env (or vice versa) health checking
 */

@ResponseBody
@RequestMapping(
        "#{@'pwh.springWebStarter.properties'.adminUrlPrefix}" +
                "#{@'pwh.springWebStarter.properties'.adminHealthCheckPath}"
)
@Hidden
public class HealthController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void healthCheck() {
    }
}
