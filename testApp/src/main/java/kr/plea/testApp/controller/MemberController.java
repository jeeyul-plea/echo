package kr.plea.testApp.controller;

import kr.plea.testApp.entity.Member;
import kr.plea.testApp.entity.dto.BaseDto;
import kr.plea.testApp.entity.dto.UpdateDto;
import kr.plea.testApp.service.MemberService;
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
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<Void> addMember(@RequestBody BaseDto baseDto) {
		memberService.saveMember(baseDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<BaseDto> getMember(@PathVariable("memberId") Long memberId) {
		Member findMember = memberService.findMember(memberId);
		BaseDto baseDto = new BaseDto(findMember);
		return ResponseEntity.status(HttpStatus.OK).body(baseDto);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BaseDto>> getMembers() {
		List<BaseDto> members = memberService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(members);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<BaseDto>> getMembersPage(@PageableDefault(size = 5) Pageable pageable) {
		Page<BaseDto> members = memberService.findAllPage(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(members);
	}

	@PatchMapping
	public ResponseEntity<Void> updateMember(@RequestBody UpdateDto memberDto) {
		memberService.updateMember(memberDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteMember(@PathVariable("memberId") Long memberId) {
		memberService.deleteMember(memberId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
