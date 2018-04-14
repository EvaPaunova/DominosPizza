package controller.manager;

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
	
	public boolean register(User user) throws SQLException {
		
		if(!userDao.checkUserExist(user.getUsername())) {
			userDao.addNewUser(user);
			return true;
		}
		
		return false;
	}
	
	public User logIn(String username, String password) throws SQLException, InvalidArgumentsException {
		User user = null;
			if(userDao.checkUserData(username, password)) {
				user = userDao.getUserByUsername(username);
				System.out.println("You are logged in!");
			}
			else {
				System.out.println("You are not a registrated user!");
			}			
		return user;
	}
	
	public void makeOrder(User user) throws SQLException {
		Order newOrder = OrderManager.getInstance().createNewOrder(user.getCart());
		newOrder.setUserId(user.getId());
		OrderDao.getInstance().addNewOrder(newOrder);
	}
	
	public void chageOrderStatus(Order order, Status status) throws SQLException {
		OrderDao.getInstance().updateOrderStatus(order, status);
	}
	
	public void addToCart(User user, Product product,int count) {
		user.addProductToShoppingCart(product, count);
		
	}
	
	public void removeFromCart(User user, Product product) {
		user.removeProductFromShoppingCart(product);
	}
	
	public void getListOfAllOrders(User user) {
		List<Order> orders = OrderDao.getInstance().getAllOrderByUser(user);
		//print list of orders
	}
	
	public void addProductToFavorites(User user, Product product) {
		try {
			ProductDao.getInstance().addFovoriteProduct(user.getId(), product.getId());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
