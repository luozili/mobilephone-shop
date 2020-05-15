package net.hycollege.admin.console.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import net.hycollege.message.bean.Message;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = request.getSession().getAttribute("login");
		if (object == null) {
			System.out.println(request.getRequestURI());
			response.sendRedirect("/login.html");
			return false;
		}
		String loginFlag = object.toString();
		if (loginFlag.equals(Message.ok + ""))
			return true;
		return true;
	}
}
