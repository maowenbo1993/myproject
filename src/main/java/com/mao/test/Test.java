package com.mao.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mao.dao.impl.UserDaoImpl;
import com.mao.domain.User;
import com.mao.service.UserService;
import com.mao.util.DBUtil;

public class Test {
	private static Logger logger = Logger.getLogger(Test.class);  
	public static void main(String[] args) {
		logger.info("test debeg");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		User user = applicationContext.getBean(User.class);
//		userService.query();
		//userService.add(user);
		System.out.println(userService.findUser("123"));
	}

}
