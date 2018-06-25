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
import com.mao.domain.User;
import com.mao.util.DBUtil;
@Component
public class UserDaoImpl implements UserDao {
	public DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Resource(name="dataSource")
	//@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) {
		if (dataSource == null) System.out.println("wow");
		//Connection conn = DBUtil.open();	
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into UserTable (phoneNum, password) values(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPhoneNum());
			pstmt.setString(2, user.getPassword());
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

	public void delete(User user) {
//		Connection conn = DBUtil.open();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "delete from UserTable where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
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

	public void update(User user) {
		//Connection conn = DBUtil.open();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update UserTable set password = ? where phoneNum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getPhoneNum());
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

	public User matchUserByPhoneNum(String phoneNum) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select id, password from UserTable where phoneNum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String password = rs.getString(2);
				User user = new User();
				user.setId(id);
				user.setPhoneNum(phoneNum);
				user.setPassword(password);
				return user;
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
