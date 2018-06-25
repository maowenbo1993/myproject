package com.mao.dao;

import java.util.List;

import com.mao.domain.User;
import com.mao.domain.UserInfo;

public interface UserInfoDao {
	public void add(UserInfo userInfo);
	public void delete(UserInfo userInfo);
	public void updateUserInfo(UserInfo userInfo);
	public UserInfo matchUserInfoByUserId(int userId);
//	public List<User> query();
//	public boolean findUserByPhoneNum(String phoneNum);
//	public User matchUserByPhoneNum(String phoneNum);
}
