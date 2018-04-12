package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.DBManager;
import exceptions.InvalidArgumentsException;
import model.Ingredient;

public class IngredientsDao implements IIngredientDao{
	
	private static IngredientsDao instance;
	private Connection connection;
	
	
	private IngredientsDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static IngredientsDao getInstance() {
		if(instance == null) {
			instance = new IngredientsDao();
		}
		return instance;
	}

	@Override
	public List<Ingredient> getAllIngredients() throws SQLException, InvalidArgumentsException {
		String sqlSelectAllIngredients = "SELECT * FROM ingredients;";
		List<Ingredient> ingredients = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(sqlSelectAllIngredients);
		ResultSet set = ps.executeQuery();
		while (set.next()) {
			int id = set.getInt("ingredient_id");
			String name = set.getString("name");
			double price = set.getDouble("price");
			ingredients.add(new Ingredient(id,name,price));
		}
		return Collections.unmodifiableList(ingredients);
	}

	@Override
	public void deleteIngredient(Ingredient ingredient){
		String sqlDeleteIngredient = "DELETE FROM ingredients \nWHERE ingredient_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlDeleteIngredient);
			ps.setInt(1, ingredient.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateIngredient(Ingredient ingredient){
		String sqlUpdateIngredient  = "UPDATE ingredients \nSET name = ?, price = ?\nWHERE ingredient_id = ?;";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlUpdateIngredient);
			ps.setString(1, ingredient.getName());
			ps.setDouble(2, ingredient.getPrice());
			ps.setInt(3, ingredient.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addNewIngredient(Ingredient ingredient){
		String sqlInsertIngredient = "INSERT INTO ingredients (name, price) \nVALUES(?,?)";
		PreparedStatement ps;
		try{
			ps = connection.prepareStatement(sqlInsertIngredient);
			ps.setString(1, ingredient.getName());
			ps.setDouble(2, ingredient.getPrice());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			ingredient.setId(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
