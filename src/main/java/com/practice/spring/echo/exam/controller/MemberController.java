package com.practice.spring.echo.exam.controller;

import com.practice.spring.echo.exam.entity.Member;
import com.practice.spring.echo.exam.entity.dto.MemberDto;
import com.practice.spring.echo.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> addMember(@RequestBody MemberDto.Base memberDto) {
        memberService.saveMember(memberDto);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< ?> getMember(@RequestParam("memberId") long memberId) {
        Member findMember = memberService.findMember(memberId);
        MemberDto.Base memberDto = new MemberDto.Base(findMember);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getMembers() {
        List<MemberDto.Base> members = memberService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getMembersPage(@PageableDefault(size = 5) Pageable pageable) {
        Page<MemberDto.Base> members = memberService.findAllPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @PatchMapping
    public ResponseEntity<?> updateMember(@RequestBody MemberDto.Update memberDto) {
        memberService.updateMember(memberDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMember(@RequestParam("memberId") long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
