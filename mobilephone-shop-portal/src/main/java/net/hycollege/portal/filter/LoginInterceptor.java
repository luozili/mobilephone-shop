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
public class LoginInterceptor implements HandlerInterceptor {
	private static final int thirtyMinutes = 1800;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (c.getName() == "flag") {
				cookie = c;
				break;
			}
		}
		String value = cookie.getValue();
		if (redisTemplate.hasKey(value)) {
			cookie.setMaxAge(thirtyMinutes);
			redisTemplate.expire(value, thirtyMinutes, TimeUnit.SECONDS);
			return HandlerInterceptor.super.preHandle(request, response, handler);
		} else {
			response.sendRedirect("/login.html");
			return false;
		}

	}
}
