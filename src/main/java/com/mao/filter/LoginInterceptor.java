package com.mao.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		StringBuffer url = request.getRequestURL();
		System.out.println(url);
		
		HttpSession session = request.getSession(false);
		String phoneNum = (String) session.getAttribute("phoneNum");
		if (phoneNum == null) {
			System.out.println("hhh");
			
			response.sendRedirect("/myproject/index.jsp");
//			request.getRequestDispatcher("index.jsp").forward(request, response);
			return false;
		}
		return true;
	}
}
