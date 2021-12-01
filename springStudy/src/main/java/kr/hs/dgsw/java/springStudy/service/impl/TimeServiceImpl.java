package kr.hs.dgsw.java.springStudy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import kr.hs.dgsw.java.springStudy.service.TimeService;

@Service(value = "timeService")
public class TimeServiceImpl implements TimeService
{
	private static final SimpleDateFormat DATE_FORMAT = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat DATE_FORMAT_SIMPLE = 
			new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String getNow() {
		Date date = new Date();
		String str = DATE_FORMAT.format(date);
		
		return str;
	}
	
	@Override
	public String calculateDays(int days) {
		long now = System.currentTimeMillis();
		long that = now + ((long)days * (24L * 60L * 60L * 1000L));
		System.out.println(now + "  " + that);
		
		Date date = new Date();
		date.setTime(that);
		
		System.out.println(date.toString());
		
		return DATE_FORMAT_SIMPLE.format(date);
	}
}
