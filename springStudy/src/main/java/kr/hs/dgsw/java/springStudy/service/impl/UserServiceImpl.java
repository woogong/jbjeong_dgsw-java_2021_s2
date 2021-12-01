package kr.hs.dgsw.java.springStudy.service.impl;

import org.springframework.stereotype.Service;

import kr.hs.dgsw.java.springStudy.domain.User;
import kr.hs.dgsw.java.springStudy.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Override
	public User getUser(String id) {
		User user = new User();
		user.setName("홍길동");
		user.setBirthday("1446-08-23");
		user.setPhoneNumber("010-1234-5678");
		user.setJob("도둑");
		user.setId(id);
		
		return user;
	}
	
}
