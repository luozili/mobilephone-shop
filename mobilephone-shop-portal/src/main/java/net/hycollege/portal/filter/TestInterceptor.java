package net.hycollege.portal.filter;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TestInterceptor implements HandlerInterceptor {
	private static final int thirtyMinutes = 1800;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			response.getWriter().print("1 null");
			return false;
		}
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("1")) {
				cookie = c;
				break;
			}
		}
		if (cookie == null) {
			response.getWriter().print("1 null");
			return false;
		}
		
		String value = cookie.getValue();
		System.out.println(value);
		return true;
	}
	
}
