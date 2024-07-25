package com.practice.spring.echo.exam.entity.dto;

import com.practice.spring.echo.exam.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Base {
        String name;
        int age;

        public Base(Member member) {
            this.name = member.getName();
            this.age = member.getAge();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update{
        Long id;
        String name;
        int age;
    }
}
