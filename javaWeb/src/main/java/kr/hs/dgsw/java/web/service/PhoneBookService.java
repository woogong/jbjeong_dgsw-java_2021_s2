package kr.hs.dgsw.java.web.service;

import java.util.List;

import kr.hs.dgsw.java.web.domain.PhoneNumber;

public interface PhoneBookService {

	public PhoneNumber create(PhoneNumber phoneNumber);
	
	public PhoneNumber read(int idx);
	
	public void update(PhoneNumber phoneNumber);
	
	public void delete(int idx);
	
	public List<PhoneNumber> list();
	
}
