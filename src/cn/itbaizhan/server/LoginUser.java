package cn.itbaizhan.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import cn.itbaizhan.common.FriendLabel;
import cn.itbaizhan.common.UserBean;

/* User login module, display currently logged in users in ServerFrame */
public class LoginUser extends TimerTask {
	private DefaultListModel listModel = null;
	private JList userList = null;
	private JLabel jCount = null;
	private Hashtable userTable = new Hashtable(); // Store basic information for each user
	private int count = 0; // Online user count
	private Connection con = null; // Database connection

	public LoginUser(DefaultListModel listModel, JList userList, JLabel jCount, Hashtable userTable, Connection con) {
		this.listModel = listModel;
		this.userList = userList;
		this.jCount = jCount;
		this.userTable = userTable;
		this.con = con;
	}

	// Timer task method
	@Override
	public void run() {
		count = 0;
		userTable.clear();
		listModel.clear();
		getUser(); // Query database for online users
		getUserInfo(); // Method to update the list
		userList.setCellRenderer(new FriendLabel()); // Use FriendLabel to display avatars
		jCount.setText(new Integer(count).toString());
	}

	public void getUser() {
		String sql = "select * from UserInformation where Status = 1";
		String userNum = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				++count; // Count increases by 1
				// Create object to store user information
				UserBean user = new UserBean();
				userNum = rs.getString("UserNum");
				// Store user information in the object
				user.setUserNum(userNum);
				user.setUserName(rs.getString("UserName"));
				user.setPassword(rs.getString("Password"));
				user.setSex(rs.getString("Sex"));
				user.setBirth(rs.getString("Birth"));
				user.setAddress(rs.getString("Address"));
				user.setSign(rs.getString("Sign"));
				user.setPortrait(rs.getString("Portrait"));
				user.setStatus(rs.getInt("Status"));
				user.setPort(rs.getInt("Port"));
				user.setIp(rs.getString("IP"));
				userTable.put(userNum, user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update user information to the list
	public void getUserInfo() {
		// Create an object that implements the Enumeration interface to traverse a series of elements, one by one using the nextElement method
		Enumeration it = userTable.elements();
		String userName = "";
		String userNum = "";
		String portrait = "";
		String userInfo = "";
		int status = 0;
		while (it.hasMoreElements()) {
			UserBean user = (UserBean) it.nextElement();
			userName = user.getUserName().trim();
			userNum = user.getUserNum().trim();
			portrait = user.getPortrait();
			status = user.getStatus();
			userInfo = status + userName + "<" + userNum + ">" + "*" + portrait + "^";
			listModel.addElement(userInfo);
		}
	}
}
