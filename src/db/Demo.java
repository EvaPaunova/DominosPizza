package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import controller.manager.UserManager;
import exceptions.InvalidArgumentsException;
import model.Ingredient;
import model.Product;
import model.User;
import model.dao.ProductDao;
import model.dao.UserDao;

public class Demo {

	public static void main(String[] args) {

		User u = null;
		try {
			User user = new User("testfirstname", "testlastname", "username", "password", "email@email.bg", "adress 12557871", "87841218481");
			UserManager.getInstance().register(user);
		} catch (SQLException | InvalidArgumentsException e) {
			e.getMessage();
		}
	
		
	}

}
