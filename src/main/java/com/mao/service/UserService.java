package com.mao.service;

import com.mao.domain.User;
import com.mao.domain.UserInfo;

public interface UserService {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public void query();
	public boolean findUser(String phoneNum);
	public User matchUser(String phoneNum);
	public void updateInfo(UserInfo userInfo);
	public UserInfo matchUserInfo(int userId);
}
 