package com.mao.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.mao.domain.User;

public class UserInfoControllerTest {

	@Test
	public void test() {
		User user = new User();
		user.setId(1);
		user.setPassword("123");
//		user.setPhoneNum("110");
		Gson gson = new Gson();
		String json = gson.toJson(user);
		System.out.println(json);
		User user2 = gson.fromJson(json, User.class);
		System.out.println(user2);
	}

}
