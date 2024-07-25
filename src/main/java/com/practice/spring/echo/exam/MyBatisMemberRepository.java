package com.practice.spring.echo.exam;

import com.practice.spring.echo.exam.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository{
    private final MemberMapper memberMapper;
}
