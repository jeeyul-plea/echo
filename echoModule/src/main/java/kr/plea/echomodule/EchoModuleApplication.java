package kr.plea.echomodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan()
@SpringBootApplication
public class EchoModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoModuleApplication.class, args);
    }

}
