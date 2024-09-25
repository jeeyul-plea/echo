package kr.plea.testApp.service;

import kr.plea.testApp.entity.Member;
import kr.plea.testApp.entity.dto.BaseDto;
import kr.plea.testApp.entity.dto.UpdateDto;
import kr.plea.testApp.mapper.MemberMapper;
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

	public void saveMember(BaseDto dto) {
		memberMapper.saveMember(dto.getName(), dto.getAge(), dto.getHobby());
	}

	@Transactional(readOnly = true)
	public Member findMember(Long memberId) {
		return memberMapper.findMember(memberId);
	}

	@Transactional(readOnly = true)
	public List<BaseDto> findAll() {
		return memberMapper.findAll();
	}

	public void updateMember(UpdateDto dto) {
		memberMapper.updateMember(dto.getId(), dto.getName(), dto.getAge(), dto.getHobby());
	}

	public void deleteMember(Long memberId) {
		memberMapper.deleteMember(memberId);
	}

	public Page<BaseDto> findAllPage(Pageable pageable) {
		int size = pageable.getPageSize();
		long offset = pageable.getOffset();
		List<BaseDto> findMembers = memberMapper.findMembersPage(size, offset);
		int total = memberMapper.countMembers();
		return new PageImpl<>(findMembers, pageable, total);
	}

}
