package com.mao.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mao.dao.UserDao;
import com.mao.dao.UserInfoDao;
import com.mao.domain.User;
import com.mao.domain.UserInfo;
import com.mao.util.DateFormat;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	public void add(User user) {
		userDao.add(user);
		int userId = userDao.matchUserByPhoneNum(user.getPhoneNum()).getId();
		UserInfo userInfo = new UserInfo();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String registerDate = new DateFormat().toString();
		userInfo.setUserId(userId);
		userInfo.setRegisterDate(registerDate);
		userInfo.setNickname("user" + userId);
		userInfoDao.add(userInfo);
	}
	
	public void delete(User user) {
		userDao.delete(user);
	}
	
	public void update(User user) {
		userDao.update(user);
	}
	
	public void query() {
		userDao.query();
	}

	public boolean findUser(String phoneNum) {
		return userDao.findUserByPhoneNum(phoneNum);
	}

	public User matchUser(String phoneNum) {
		return userDao.matchUserByPhoneNum(phoneNum);
	}

	public void updateInfo(UserInfo userInfo) {
		userInfoDao.updateUserInfo(userInfo);
	}

	public UserInfo matchUserInfo(int userId) {
		return userInfoDao.matchUserInfoByUserId(userId);
	}
}
