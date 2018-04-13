package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DBManager;
import exceptions.InvalidArgumentsException;
import model.Order;
import model.Status;
import model.User;

public class OrderDao implements IOrderDao{
	
	private static OrderDao instance;
	private Connection connection;
	
	
	private OrderDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static OrderDao getInstance() {
		if(instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}

	@Override
	public List<Order> getAllOrderByUser(User user){
		String sqlSelectAllOrders = "SELECT * FROM orders \n WHERE user_id = ?;";
		List<Order> orders = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlSelectAllOrders);
			ps.setLong(1, user.getId());
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int id = set.getInt("order_id");
				Double price = set.getDouble("price");
				LocalDateTime date = LocalDateTime.now();
				int status = set.getInt("status_id");
				orders.add(new Order(id, price, date,status));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public void deleteOrder(Order order) throws SQLException {
		String sqlDeleteOrder = "DELETE FROM orders \nWHERE order_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlDeleteOrder);
			ps.setLong(1, order.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderStatus(Order order, Status status) throws SQLException {
		String sqlUpdateIngredient  = "UPDATE orders \nSET status_id = ?\nWHERE order_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlUpdateIngredient);
			ps.setInt(1, status.getId());
			ps.setLong(2, order.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addNewOrder(Order order) throws SQLException {
		String sqlInsertOrder = "INSERT INTO orders (price, date,address, user_id, status_id) \nVALUES(?,?,?,?,?)";
		PreparedStatement ps;
		try{
			ps = connection.prepareStatement(sqlInsertOrder,Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, order.getPrice());
			ps.setDate(2,null);
			ps.setString(3, order.getAddress());
			ps.setLong(4, order.getUserId());
			ps.setInt(5, 1);
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					order.setId(rs.getInt(1));
				}
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
				
	}
	

}
