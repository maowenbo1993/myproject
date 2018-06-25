package com.mao.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mao.domain.User;
import com.mao.domain.UserInfo;
import com.mao.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		String phoneNum = request.getParameter("phoneNum");
		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (phoneNum == null || password == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (phoneNum == "" || password == "") {
			jsonObject.put("status", 10005);
			jsonObject.put("message", "NULL");
			pw.println(jsonObject.toString());
		}else if (!userService.findUser(phoneNum)) {
			jsonObject.put("status", 10003);
			jsonObject.put("message", "UserNotExist");
			pw.println(jsonObject.toString());
		} else {
			String userPassword = userService.matchUser(phoneNum).getPassword();
			if (!password.equals(userPassword)) {
				jsonObject.put("status", 10004);
				jsonObject.put("message", "WrongPassword");
				pw.println(jsonObject.toString());
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("phoneNum", phoneNum);
				
//				response.addHeader("Access-Control-Allow-Origin", "*");  
//		        response.addHeader("Access-Control-Allow-Credentials","true");
				
//				request.getRequestDispatcher("/images/index.html").forward(request, response);
//				request.getRequestDispatcher("mao.html").forward(request, response);
				response.sendRedirect("/myproject/images/index.html");
//				response.sendRedirect("/myproject/updatePassword.jsp");
				System.out.println(session.getId());
				jsonObject.put("status", 10000);
				jsonObject.put("message", "OK");
				pw.println(jsonObject.toString());
			}
		}
		pw.close(); 
	}
	
	@RequestMapping("/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		String phoneNum = request.getParameter("phoneNum");
		String password = request.getParameter("password");	
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (phoneNum == null || password == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (phoneNum == "" || password == "") {
			jsonObject.put("status", 10005);
			jsonObject.put("message", "NULL");
			pw.println(jsonObject.toString());
		} else if (userService.findUser(phoneNum)) {
			jsonObject.put("status", 10002);
			jsonObject.put("message", "UserExist");
			pw.println(jsonObject.toString());
		} else {
			User user = new User();
			user.setPhoneNum(phoneNum);
			user.setPassword(password);
			userService.add(user);			
			jsonObject.put("status", 10000);
			jsonObject.put("message", "OK");			
			pw.println(jsonObject.toString());
			response.sendRedirect("/myproject/index.jsp");
		}
		pw.close(); 
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	@RequestMapping("/update")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		String phoneNum = (String) session.getAttribute("phoneNum");
		String password = request.getParameter("password");	
		String newPassword = request.getParameter("newPassword");
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (password == "" || newPassword == "") {
			jsonObject.put("status", 10005);
			jsonObject.put("message", "NULL");
			pw.println(jsonObject.toString());
		} else {
			String userPassword = userService.matchUser(phoneNum).getPassword();
			if (!password.equals(userPassword)) {
				jsonObject.put("status", 10004);
				jsonObject.put("message", "WrongPassword");
				pw.println(jsonObject.toString());
			} else {
				User user = new User();
				user.setPhoneNum(phoneNum);
				user.setPassword(newPassword);
				userService.update(user);
				jsonObject.put("status", 10000);
				jsonObject.put("message", "OK");
				pw.println(jsonObject.toString());
			}
		}
		pw.close(); 
	}
	
	@RequestMapping("/getUser")
	public void getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(false);
		String phoneNum = (String) session.getAttribute("phoneNum");
		User user = userService.matchUser(phoneNum);
		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		pw.println(gson.toJson(user));
		pw.close();
	}
	
	@RequestMapping("/requery")
	public void requeryUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		pw.println("requery");
		pw.close(); 
	}

	
	
}
