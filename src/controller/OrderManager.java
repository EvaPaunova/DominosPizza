package controller;

import model.dao.IOrderDao;
import model.dao.OrderDao;
import model.dao.UserDao;

public class OrderManager {
	
	private static OrderManager instance;
	private IOrderDao orderDao;
	
	private OrderManager() {
		this.orderDao = OrderDao.getInstance();
	}

	public static OrderManager getInstance() {
		if (instance == null) {
			instance = new OrderManager();
		}
		return instance;
	}
	
	

}
