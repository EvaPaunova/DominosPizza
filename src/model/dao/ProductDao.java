package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;
import exceptions.InvalidArgumentsException;
import model.Ingredient;
import model.Product;

public class ProductDao implements IProductDao{
	
	private static ProductDao instance;
	private Connection connection;
	
	
	private ProductDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	@Override
	public List<Product> getAllProducts() throws SQLException, InvalidArgumentsException {
		String sqlSelectAllProducts = "SELECT * FROM products;";
		List<Product> products = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(sqlSelectAllProducts);
		ResultSet set = ps.executeQuery();
		while (set.next()) {
			int id = set.getInt("product_id");
			String name = set.getString("name");
			double price = set.getDouble("price");
			products.add(new Product(name,price,id));
		}
		return products;
	}

	@Override
	public void deleteProduct(Product product) throws SQLException {
		String sqlDeleteProduct = "DELETE FROM products \nWHERE product_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlDeleteProduct);
			ps.setInt(1, product.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
		String sqlUpdateIngredient  = "UPDATE products \nSET name = ?, price = ?\nWHERE product_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlUpdateIngredient);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3, product.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addNewProduct(Product product) throws SQLException {
		String sqlInsertProduct = "INSERT INTO products (name, price) \nVALUES(?,?)";
		PreparedStatement ps;
		try{
			ps = connection.prepareStatement(sqlInsertProduct);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			product.setId(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
