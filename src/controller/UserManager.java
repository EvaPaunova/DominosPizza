package controller;

import java.sql.SQLException;

import model.User;
import model.dao.IUserDao;
import model.dao.UserDao;

public class UserManager {
	
	private static UserManager instance;
	private IUserDao userDao;
	
	private UserManager() {
		this.userDao = UserDao.getInstance();
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public boolean check(String username,String password) {
		return userDao.checkUserData(username, password);
	}
	
	public void register(User user) {
		try {
			if(!userDao.checkUserExist(user.getUsername())) {
				userDao.addNewUser(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
