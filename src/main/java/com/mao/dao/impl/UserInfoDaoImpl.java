package com.mao.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mao.dao.UserDao;
import com.mao.dao.UserInfoDao;
import com.mao.domain.User;
import com.mao.domain.UserInfo;
import com.mao.util.DBUtil;
@Component
public class UserInfoDaoImpl implements UserInfoDao {
	public DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Resource(name="dataSource")
	//@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(UserInfo userInfo) {
		if (dataSource == null) System.out.println("wow");
		//Connection conn = DBUtil.open();	
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into UserInfoTable (userId, registerDate, nickname) values(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userInfo.getUserId());
			pstmt.setString(2, userInfo.getRegisterDate());
			pstmt.setString(3, userInfo.getNickname());
			int addNum = pstmt.executeUpdate();
			System.out.println("增加"+addNum+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delete(UserInfo userInfo) {
//		Connection conn = DBUtil.open();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "delete from UserInfoTable where userId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userInfo.getUserId());
			int delNum = pstmt.executeUpdate();
			System.out.println("删除"+delNum+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//			DBUtil.close(conn);
		}
	}

	public void updateUserInfo(UserInfo userInfo) {
		//Connection conn = DBUtil.open();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update UserInfoTable set nickname = ?, gender = ?, age = ?, constellation = ?, school = ?, hobby = ?, province = ?, signature = ?  where userId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userInfo.getNickname());
			pstmt.setString(2, userInfo.getGender());
			pstmt.setString(3, userInfo.getAge());
			pstmt.setString(4, userInfo.getConstellation());
			pstmt.setString(5, userInfo.getSchool());
			pstmt.setString(6, userInfo.getHobby());
			pstmt.setString(7, userInfo.getProvince());
			pstmt.setString(8, userInfo.getSignature());
			pstmt.setInt(9, userInfo.getUserId());
			int updNum = pstmt.executeUpdate();
			System.out.println("更改"+updNum+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//DBUtil.close(conn);
		}
	}

	public List<User> query() {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
//		Connection conn = DBUtil.open();		
		try {
			conn = dataSource.getConnection();
			String sql = "select id, phoneNum, password from UserTable";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String phoneNum = rs.getString(2);
				String password = rs.getString(3);
				User user = new User();
				user.setId(id);
				user.setPhoneNum(phoneNum);
				user.setPassword(password);
				users.add(user);
			}
			System.out.print(users);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//DBUtil.close(conn);
		}
		return null;
	}
	
	public boolean findUserByPhoneNum(String phoneNum) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from UserTable where phoneNum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public UserInfo matchUserInfoByUserId(int userId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from UserInfoTable where userID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String registerDate = rs.getString("registerDate");
				String nickname = rs.getString("nickname");
				String gender = rs.getString("gender");
				String age = rs.getString("age");
				String constellation = rs.getString("constellation");
				String school = rs.getString("school");
				String hobby = rs.getString("hobby");
				String province = rs.getString("province");
				String signature = rs.getString("signature");
				UserInfo userInfo = new UserInfo();
				userInfo.setId(id);
				userInfo.setUserId(userId);
				userInfo.setRegisterDate(registerDate);
				userInfo.setNickname(nickname);
				userInfo.setGender(gender);
				userInfo.setAge(age);
				userInfo.setConstellation(constellation);
				userInfo.setSchool(school);
				userInfo.setHobby(hobby);
				userInfo.setProvince(province);
				userInfo.setSignature(signature);
				return userInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	
}
