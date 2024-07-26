package kr.plea.testApp.entity.dto;

import kr.plea.testApp.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDto {

	String name;
	Integer age;
	String hobby;

	public BaseDto(Member member) {
		this.name = member.getName();
		this.age = member.getAge();
		this.hobby = member.getHobby();
	}
}
