package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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
		String sqlInsertProduct = "INSERT INTO products (name, price,size) \nVALUES(?,?,?)";
		PreparedStatement ps;
		try{
			ps = connection.prepareStatement(sqlInsertProduct,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3,product.getSize());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			product.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		addIngredients(product);
		
	}
	
	public void addIngredients(Product product) {
		ArrayList<Ingredient> ingredients = new ArrayList<>(product.getIngredients());
		for(int i = 0; i < ingredients.size(); i++) {
			String sql = "INSERT INTO products_has_ingredients (product_id, ingredient_id) \\nVALUES(?,?)";
			try(PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, product.getId());
				ps.setInt(1, ingredients.get(i).getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void addFovoriteProduct(long user, long product) throws SQLException {
		String sql = "INSERT INTO user_has_favorite_products (user_id, product_id) \\nVALUES(?,?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setLong(1, user);
			ps.setLong(1, product);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

}
