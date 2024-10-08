package kr.plea.testApp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan("kr.plea.testApp.mapper")
public class TestAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestAppApplication.class, args);
    }
}
