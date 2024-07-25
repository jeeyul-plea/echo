package com.practice.spring.echo.exam.controller;

import com.practice.spring.echo.exam.entity.Member;
import com.practice.spring.echo.exam.entity.dto.MemberDto;
import com.practice.spring.echo.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody MemberDto.Base memberDto) {
        memberService.saveMember(memberDto);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< MemberDto.Base> getMember(@RequestParam("memberId") long memberId) {
        Member findMember = memberService.findMember(memberId);
        MemberDto.Base memberDto = new MemberDto.Base(findMember);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberDto.Base>> getMembers() {
        List<MemberDto.Base> members = memberService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @PatchMapping
    public ResponseEntity<Member> updateMember(@RequestBody MemberDto.Update memberDto) {
        memberService.updateMember(memberDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Member> deleteMember(@RequestParam("memberId") long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
