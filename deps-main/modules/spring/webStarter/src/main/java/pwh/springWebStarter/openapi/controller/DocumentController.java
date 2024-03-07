package pwh.springWebStarter.openapi.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pwh.springWebStarter.Properties;

@RequestMapping(
        "#{@'pwh.springWebStarter.properties'.adminUrlPrefix}" +
                "#{@'pwh.springWebStarter.properties'.adminDocPath}" +
                "#{@'pwh.springWebStarter.properties'.adminDocApiPath}"
)
@Hidden
@Slf4j
public class DocumentController {
    private final String ROOT_PATH;

    public DocumentController(
            Properties properties
    ) {
        ROOT_PATH = String.format("%s/openapi/redoc", properties.getStaticUrlPrefix());
    }

    @RequestMapping("/ui")
    public String uiPage() {
        log.info("Forwarding OpenAPI UI");
        return String.format("forward:%s/index.html", ROOT_PATH);
    }

    /**
     * 'resrc' = specifically designed prefix for getting relative resource for this controller
     */
    @RequestMapping("/resrc/{path}/{file}")
    public String uiResource(@PathVariable("path") String path, @PathVariable("file") String file) {
        log.info("Forwarding OpenAPI UI Resource");
        return String.format("forward:%s/%s/%s", ROOT_PATH, path, file);
    }
}

