package com.practice.spring.echo.exam.mapper;

import com.practice.spring.echo.exam.entity.Member;
import com.practice.spring.echo.exam.entity.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    void saveMember(@Param("name") String name, @Param("age") Integer age, @Param("hobby") String hobby);
    Member findMember(@Param("id") Long id);
    void updateMember(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age, @Param("hobby") String hobby);
    void deleteMember(@Param("id") Long id);

    List<MemberDto.Base> findMembersPage(@Param("size") int size, @Param("offset") long offset);
    int countMembers();
    List<MemberDto.Base> findAll();
}
