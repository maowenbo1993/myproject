package com.mao.dao.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.mao.domain.User;
import com.mao.domain.UserInfo;
import com.mao.service.UserService;

public class UserDaoImplTest {
//	User user;
//	
//	@Before
//	public void before() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		//UserService userService = applicationContext.getBean(UserService.class);
//		//user = applicationContext.getBean(User.class);
//	}
	

//	@Test
//	public void testAdd() {
//		new UserDaoImpl().add(user);
//	}
	@Test
	public void testQuery() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = applicationContext.getBean(UserService.class);
//		User user = applicationContext.getBean(User.class);
//		Gson gson = new Gson();
//		UserInfo userInfo = userService.matchUserInfo(12);
		UserInfo userInfo = new UserInfo();
		userInfo.setAge("25");
		userInfo.setGender("male");
		userInfo.setUserId(16);
		userService.updateInfo(userInfo);
	}
	


}
