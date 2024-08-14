package kr.plea.echomodule;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.plea")
public class DataSource {
	String name;
	String height;
}
