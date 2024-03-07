package unid.monoServerApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import unid.monoServerApp.database.init.DbTestInitService;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAsync
@Slf4j
public class SpringApp {
    @Autowired
    private DbTestInitService dbTestInitService;

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }

    @PostConstruct
    void pc() {
        // TODO: remove
//        dbTestInitService.injectInitialData();
    }
}
