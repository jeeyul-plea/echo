package kr.plea.testApp.mapper;

import kr.plea.testApp.entity.Member;
import kr.plea.testApp.entity.dto.BaseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    void saveMember(@Param("name") String name, @Param("age") Integer age, @Param("hobby") String hobby);
    Member findMember(@Param("id") Long id);
    void updateMember(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age, @Param("hobby") String hobby);
    void deleteMember(@Param("id") Long id);
    List<BaseDto> findMembersPage(@Param("size") int size, @Param("offset") long offset);
    int countMembers();
    List<BaseDto> findAll();
}
