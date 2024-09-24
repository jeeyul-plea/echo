package com.kr.plea.echomodule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Member {
	private String name;
	private int age;
	private transient String code; // 직렬화시 null 된다.
}
