package model.dao;

import java.sql.SQLException;
import java.util.List;

import exceptions.InvalidArgumentsException;
import model.Order;
import model.Status;
import model.User;

public interface IOrderDao {

	public List<Order> getAllOrderByUser(User user) throws SQLException, InvalidArgumentsException;
	
	public void deleteOrder(Order order) throws SQLException;
	
	public void updateOrderStatus(Order order, Status status) throws SQLException;
	
	public void addNewOrder(Order order) throws SQLException;
	
}
