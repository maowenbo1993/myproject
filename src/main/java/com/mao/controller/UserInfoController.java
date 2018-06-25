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
import com.mao.util.ToJson;

@Controller
public class UserInfoController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserInfo")
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		
//        HttpServletResponse httpResponse = (HttpServletResponse) response; 
//        httpResponse.addHeader("Access-Control-Allow-Origin", "*");  
//        httpResponse.addHeader("Access-Control-Allow-Credentials","true");
		
		HttpSession session = request.getSession();
		String phoneNum = (String) session.getAttribute("phoneNum");
		if (phoneNum == null) {
			System.out.println("eeeee");
			PrintWriter pw = response.getWriter();
			pw.println("No Session");
		}
		else {
			int userId = userService.matchUser(phoneNum).getId();
			UserInfo userInfo = userService.matchUserInfo(userId);
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			Gson gson = new Gson();
			
			jsonObject.put("status", 10000);
			jsonObject.put("message", gson.toJson(userInfo));
//			pw.println(jsonObject.toString());
			pw.println(gson.toJson(userInfo));
			pw.close();
		}
	}
	
	@RequestMapping("/updateInfo")
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		HttpSession session = request.getSession(false);
		String phoneNum = (String)session.getAttribute("phoneNum");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String constellation = request.getParameter("constellation");
		String school = request.getParameter("school");
		String hobby = request.getParameter("hobby");
		String province = request.getParameter("province");
		String signature = request.getParameter("signature");
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (nickname == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			int userId = userService.matchUser(phoneNum).getId();
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userId);
			userInfo.setNickname(nickname);
			userInfo.setGender(gender);
			userInfo.setAge(age);
			userInfo.setConstellation(constellation);
			userInfo.setSchool(school);
			userInfo.setHobby(hobby);
			userInfo.setProvince(province);
			userInfo.setSignature(signature);
			userService.updateInfo(userInfo);			
			jsonObject.put("status", 10000);
			jsonObject.put("message", "OK");			
			pw.println(jsonObject.toString());
		}
		pw.close(); 
	}

//	@RequestMapping("/updateInfo")
//	public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		
//		HttpSession session = request.getSession(false);
//		String phoneNum = (String) session.getAttribute("phoneNum");
//		int userId = userService.matchUser(phoneNum).getId();
//		UserInfo userInfo = userService.matchUserInfo(userId);
//		
//		String json = ToJson.toJson(request);
//		System.out.println(json);
//		System.out.println("hh");
//		UserInfo newUserInfo = new Gson().fromJson(json, UserInfo.class);
//		userInfo.setNickname(newUserInfo.getNickname());
//		userInfo.setGender(newUserInfo.getGender());
//		userInfo.setAge(newUserInfo.getAge());
//		userInfo.setConstellation(newUserInfo.getConstellation());
//		userInfo.setSchool(newUserInfo.getSchool());
//		userInfo.setHobby(newUserInfo.getHobby());
//		userInfo.setProvince(newUserInfo.getProvince());
//		userInfo.setSignature(newUserInfo.getSignature());
//		userService.updateInfo(userInfo);
//		
//		PrintWriter pw = response.getWriter();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("status", 10000);
//		jsonObject.put("message", "OK");			
//		pw.println(jsonObject.toString());		
//		pw.close(); 
//	}

}
