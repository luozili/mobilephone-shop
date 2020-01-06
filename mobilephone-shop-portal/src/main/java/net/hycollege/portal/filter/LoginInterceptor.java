package net.hycollege.portal.filter;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import net.hycollege.message.bean.Constants;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("flag")) {
				cookie = c;
				break;
			}
		}
		if (cookie == null) {
			return false;
		}
		String value = cookie.getValue();
		if (redisTemplate.hasKey(value)) {
			cookie.setMaxAge(Constants.thirtyMinutes);
			redisTemplate.expire(value, Constants.thirtyMinutes, TimeUnit.SECONDS);
			return HandlerInterceptor.super.preHandle(request, response, handler);
		} 
		request.getRequestDispatcher("/login").forward(request, response);
		return false;
	}
	
}
