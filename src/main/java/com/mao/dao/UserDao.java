package com.mao.dao;

import java.util.List;

import com.mao.domain.User;

public interface UserDao {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public List<User> query();
	public boolean findUserByPhoneNum(String phoneNum);
	public User matchUserByPhoneNum(String phoneNum);
}
