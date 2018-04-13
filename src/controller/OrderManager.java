package controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Map.Entry;

import model.Order;
import model.Product;
import model.Status;
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
	
	public Order createNewOrder( Map<Product, Integer> products) {
		Order order = new Order(calculateOrderPrice(products), LocalDateTime.now(), Status.REGISTRATED, products);
		return order;
	}
	
	public double calculateOrderPrice(Map<Product, Integer> products) {
		double money = 0;
		for(Entry<Product, Integer> e: products.entrySet()) {
			money += e.getKey().getPrice()*e.getValue();
		}
		return money;
	}

}
