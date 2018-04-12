package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import exceptions.InvalidArgumentsException;
import model.User;

public class UserDao implements IUserDao{
	
	private static UserDao instance;
	private Connection connection;
	
	
	private UserDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	@Override
	public List<User> getAllUsers() {
		String sqlSelectAllUsers = "SELECT * FROM users \n WHERE isAdmin = ?;";
		List<User> users = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlSelectAllUsers);
			ps.setBoolean(1, false);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				int user_id = set.getInt("user_id");
				String firstName = set.getString("first_name");
				String lastName = set.getString("last_name");
				String email = set.getString("email");
				String address = set.getString("address");
				String phoneNumber = set.getString("phone_number");
				String username = set.getString("username");
				String password = set.getString("password");
				users.add(new User(firstName, lastName, username, email, password, address, phoneNumber));
			}
		} catch (SQLException | InvalidArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(int id){
		User user = null;
		String sqlSelectUser = "SELECT user_id,first_name,last_name,address,email,phone_number,username,password \nFROM users \nWHERE user_id = ? ;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlSelectUser);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				String address = result.getString("address");
				String phoneNumber = result.getString("phone_number");
				String username = result.getString("username");
				String password = result.getString("password");
				
				user = new User(firstName, lastName, username, email, password, address, phoneNumber);
			}
		} catch (SQLException | InvalidArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public User getUserByUsername(String username){
		User user = null;
		String sqlSelectUser = "SELECT user_id,first_name,last_name,address,email,phone_number,username,password \nFROM users \nWHERE username = ? ;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlSelectUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				String address = result.getString("address");
				String phoneNumber = result.getString("phone_number");
				String password = result.getString("password");
				
				user = new User(firstName, lastName, username, email, password, address, phoneNumber);
			}
		} catch (SQLException | InvalidArgumentsException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	

	@Override
	public void deleteUser(User user){
		String sqlDeleteUser = "DELETE FROM users \nWHERE user_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlDeleteUser);
			ps.setInt(1, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User user) {
		String sqlUpdateUser  = "UPDATE users \nSET first_name = ?, last_name = ?, address = ?,email = ?,phone_number = ?,username = ?,password = ?\nWHERE user_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlUpdateUser);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhoneNumber());
			ps.setString(6, user.getUsername());
			ps.setString(7, user.getPassword());
			ps.setInt(8, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addNewUser(User user) {
		String sqlInsertUser = "INSERT INTO users (first_name, last_name, email, address, phone_number, username, password, isAdmin) \nVALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlInsertUser);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getPhoneNumber());
			ps.setString(6, user.getUsername());
			ps.setString(7, user.getPassword());
			ps.setBoolean(8, false);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean checkUserExist(String username){
		String sqlCheckUser = "SELECT * \nFROM users \nWHERE username = ? ;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlCheckUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			int counter = 0;
			while(result.next()) {
				counter++;
			}
			if(counter != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean checkUserData(String username, String password){
		String sqlCheckUser = "SELECT * \nFROM users \nWHERE username = ? ;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlCheckUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				String pass = result.getString("password");
				if(pass.equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
