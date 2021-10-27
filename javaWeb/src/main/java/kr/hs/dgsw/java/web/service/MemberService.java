package kr.hs.dgsw.java.web.service;

import java.util.List;

import kr.hs.dgsw.java.web.domain.Member;

public interface MemberService {

	public void registerMember(Member member);

	public boolean isEmailDuplicated(String email);
	
	public List<Member> getList();
	
}
