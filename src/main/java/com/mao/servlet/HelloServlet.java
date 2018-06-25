package com.mao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.dao.impl.UserDaoImpl;
import com.mao.service.UserService;

public class HelloServlet extends HttpServlet {
//	private UserService userService;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletConfig sc = super.getServletConfig();
//		String school = sc.getInitParameter("school");
//		String address = sc.getInitParameter("address");
		
//		ServletContext sc = super.getServletContext();
//		String school = sc.getInitParameter("school");
//		String address = sc.getInitParameter("address");
		
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserService userService = applicationContext.getBean(UserService.class);
//		userService.query();
//		UserDaoImpl userDaoImpl = new UserDaoImpl();
//		if (userDaoImpl.dataSource == null) System.out.println("hhh");
//		userDaoImpl.lowQuery();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if ("sunyunxuan".equals(username)) {
			response.sendRedirect("/MyProject/index.html");
		} else {
			response.sendRedirect("/MyProject");
		}
		
		
		PrintWriter pw = response.getWriter();
		pw.println("用户名：" + username);
		pw.println("密码：" + password);
//		pw.println("学校：" + school + "<br/>");
//		pw.println("地址：" + address);
//		pw.println("<html>");
//		pw.println("<head><title>HelloServlet</title></head>");
//		pw.println("<body><h1>这是doGet()方法</h1></body>");
//		pw.println("<html>"); 
		
		pw.close(); 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html;charset=utf-8");
//		PrintWriter pw = resp.getWriter();
//		pw.println("<html>");
//		pw.println("<head><title>HelloServlet</title></head>");
//		pw.println("<body><h1>这是doPost()方法</h1></body>");
//		pw.println("<html>"); 
//		pw.close();
		doGet(req, resp);
	}
//	public HelloServlet() {
//		System.out.println("构造方法");
//	}
//	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		System.out.println("初始化方法");
//	}
//	
//	@Override
//	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
//		System.out.println("服务..");
//	}
	
//	@Override
//		public void destroy() {
//			System.out.println("销毁");
//		}
	
}
