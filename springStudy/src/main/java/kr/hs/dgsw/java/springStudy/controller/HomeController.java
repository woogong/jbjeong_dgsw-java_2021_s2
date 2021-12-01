package kr.hs.dgsw.java.springStudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hs.dgsw.java.springStudy.domain.User;
import kr.hs.dgsw.java.springStudy.service.TimeService;
import kr.hs.dgsw.java.springStudy.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "/hello.dgsw")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value = "/hi")
	public String hi() {
		return "hi";
	}
	
	@RequestMapping(value = "/time")
	public String time(Model model) {
		
		model.addAttribute("now", timeService.getNow());
		
		return "time";
	}
	
	@RequestMapping(value = "/user.abc")
	public String userDetail(Model model, 
			@RequestParam(value = "id") String id) {
		
		// 사용자 상세 정보를 읽어온다.
		User user = userService.getUser(id);
		
		model.addAttribute("user", user);
		
		return "user";
	}
	
	@RequestMapping(value = "/day.do")
	public String calcDays(Model model, 
			@RequestParam(value = "days") int days) {
		
		String todayStr = timeService.getNow();
		todayStr = "오늘은 " + todayStr + "입니다.";
		
		String thatDay = timeService.calculateDays(days);
		String thatDayStr = "오늘 + " + days + "는 " + thatDay + " 입니다.";
		
		model.addAttribute("today", todayStr);
		model.addAttribute("thatDay", thatDayStr);
		
		return "days";
	}


}


