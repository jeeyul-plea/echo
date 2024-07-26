package kr.plea.testApp;


import kr.plea.testApp.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository{
    private final MemberMapper memberMapper;
}
