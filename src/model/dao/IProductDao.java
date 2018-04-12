package model.dao;

import java.sql.SQLException;
import java.util.List;

import exceptions.InvalidArgumentsException;
import model.Product;

public interface IProductDao {
	
	public List<Product> getAllProducts() throws SQLException, InvalidArgumentsException;
	
	public void deleteProduct(Product product) throws SQLException;
	
	public void updateProduct(Product product) throws SQLException;
	
	public void addNewProduct(Product product) throws SQLException;
	
	public void addIngredients(Product product) throws SQLException;

}
