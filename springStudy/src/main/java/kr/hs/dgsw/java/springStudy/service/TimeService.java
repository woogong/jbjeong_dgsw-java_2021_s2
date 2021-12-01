package kr.hs.dgsw.java.springStudy.service;

public interface TimeService {
	public String getNow();
	
	/**
	 * 오늘을 기준으로 days 만큰 지났을 때의 날짜를 yyyy-MM-dd 형으로 제공한다.
	 * 
	 * @param days 날짜
	 * @return
	 */
	public String calculateDays(int days);
}
