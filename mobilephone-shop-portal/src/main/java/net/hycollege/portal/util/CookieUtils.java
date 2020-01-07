package net.hycollege.portal.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
	public static String getToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("flag")) {
				cookie = c;
				break;
			}
		}
		if (cookie == null) {
			return null;
		}
		return cookie.getValue();
	}
}
