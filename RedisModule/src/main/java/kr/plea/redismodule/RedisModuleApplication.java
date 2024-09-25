package kr.plea.redismodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan()
public class RedisModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisModuleApplication.class, args);
	}

}
