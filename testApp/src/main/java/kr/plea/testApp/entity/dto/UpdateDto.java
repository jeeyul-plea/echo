package kr.plea.testApp.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDto {
    Long id;
    String name;
    Integer age;
    String hobby;
}
