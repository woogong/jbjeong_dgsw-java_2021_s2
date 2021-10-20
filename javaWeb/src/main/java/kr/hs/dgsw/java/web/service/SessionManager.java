package kr.hs.dgsw.java.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionManager {
	public boolean isAuthorized(HttpServletRequest request);
	
	public String getId(HttpServletRequest request);
	
	public void doLogin(HttpServletRequest request, 
			HttpServletResponse response, 
			String id);
	
	public void doLogout(HttpServletRequest request, 
			HttpServletResponse response);
}
