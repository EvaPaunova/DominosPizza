package controller;

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

	

}
