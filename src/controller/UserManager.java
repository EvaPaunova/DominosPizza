package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.InvalidArgumentsException;
import model.Ingredient;
import model.Order;
import model.Product;
import model.Status;
import model.User;
import model.dao.IUserDao;
import model.dao.OrderDao;
import model.dao.ProductDao;
import model.dao.UserDao;

public class UserManager {
	
	private static UserManager instance;
	private IUserDao userDao;
	public static List<User> users;
	
	private UserManager() {
		this.userDao = UserDao.getInstance();
		users = new ArrayList<>();
		getAllUsers();
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
	
	public void register(User user) throws SQLException {
		
		if(!userDao.checkUserExist(user.getUsername())) {
			userDao.addNewUser(user);
		}

	}
	
	public User logIn(String username, String password) {
		User user = null;
		try {
			if(userDao.checkUserData(username, password)) {
				user = userDao.getUserByUsername(username);
				System.out.println("You are logged in!");
			}
			else {
				System.out.println("You are not a registrated user!");
			}
		} catch (SQLException | InvalidArgumentsException e)  {
			System.out.println(e.getMessage());
		}
			
		return user;
	}
	
	public void makeOrder(User user) {
		Order newOrder = OrderManager.getInstance().createNewOrder(user.getCart());
		newOrder.setUserId(user.getId());
		try {
			OrderDao.getInstance().addNewOrder(newOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}	
	}
	
	public void chageOrderStatus(Order order, Status status) {
		try {
			OrderDao.getInstance().updateOrderStatus(order, status);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addToCart(User user, Product product,int count) {
		user.addProductToShoppingCart(product, count);
		
	}
	
	public void removeFromCart(User user, Product product) {
		user.removeProductFromShoppingCart(product);
	}
	
	public void getListOfAllOrders(User user) {
		try {
			List<Order> orders = OrderDao.getInstance().getAllOrderByUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//print list of orders
	}
	
	public void addProductToFavorites(User user, Product product) {
		try {
			ProductDao.getInstance().addFovoriteProduct(user.getId(), product.getId());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getAllUsers() {
		users = UserDao.getInstance().getAllUsers();
	}

}
