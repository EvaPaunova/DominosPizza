package model.dao;

import java.sql.SQLException;
import java.util.List;

import exceptions.InvalidArgumentsException;
import model.User;

public interface IUserDao {
	
	public List<User> getAllUsers() throws SQLException, InvalidArgumentsException;
	
	public User getUserById(int id) throws SQLException, InvalidArgumentsException;
	
	public User getUserByUsername(String username) throws SQLException, InvalidArgumentsException;
	
	public void deleteUser(User user) throws SQLException;
	
	public void updateUser(User user) throws SQLException;
	
	public void addNewUser(User user) throws SQLException;
	
	public boolean checkUserExist(String username) throws SQLException;

}
