package kr.hs.dgsw.java.web.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieSessionManager implements SessionManager {

	public static final String COOKIE_NAME = "choco";
	
	@Override
	public boolean isAuthorized(HttpServletRequest request) {
		return (getId(request) != null);
	}

	@Override
	public String getId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_NAME)) {
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}

	@Override
	public void doLogin(HttpServletRequest request, 
			HttpServletResponse response, 
			String id) {
		Cookie cookie = new Cookie(COOKIE_NAME, id);
		response.addCookie(cookie);
	}

	@Override
	public void doLogout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(COOKIE_NAME, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
	}

	

}
