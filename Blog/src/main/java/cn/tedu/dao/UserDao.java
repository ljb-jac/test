package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

public class UserDao {

	public void save(User user) {
		try (Connection conn = DBUtils.getConn()) {
			String sql="insert into user (userName,password,email) values(?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	public User login(String userName, String password) {
		try (Connection conn = DBUtils.getConn()) {
			String sql="select oId from user where userName=? and password=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int oId=rs.getInt(1);
				User user =new User(oId, userName, password, null);
				return user;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
