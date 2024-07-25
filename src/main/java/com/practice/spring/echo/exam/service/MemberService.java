package com.practice.spring.echo.exam.service;

import com.practice.spring.echo.exam.entity.Member;
import com.practice.spring.echo.exam.entity.dto.MemberDto;
import com.practice.spring.echo.exam.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper memberMapper;


    public void saveMember(MemberDto.Base dto) {
        memberMapper.saveMember(dto.getName(), dto.getAge(), dto.getHobby());
    }
    @Transactional(readOnly = true)
    public Member findMember(Long memberId){
        return memberMapper.findMember(memberId);
    }

    @Transactional(readOnly = true)
    public List<MemberDto.Base> findAll(){
        return memberMapper.findAll();
    }


    public void updateMember(MemberDto.Update dto) {
        memberMapper.updateMember(dto.getId(),dto.getName(),dto.getAge(), dto.getHobby());
    }

   public void deleteMember(Long memberId) {
        memberMapper.deleteMember(memberId);
   }

   public Page<MemberDto.Base> findAllPage(Pageable pageable) {
       int size = pageable.getPageSize();
       long offset = pageable.getOffset();
       List<MemberDto.Base> findMembers = memberMapper.findMembersPage(size, offset);
       int total = memberMapper.countMembers();
       return new PageImpl<>(findMembers, pageable, total);
   }


}
