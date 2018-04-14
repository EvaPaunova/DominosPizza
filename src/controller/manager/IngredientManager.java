package controller.manager;

import java.util.List;

import exceptions.InvalidArgumentsException;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Ingredient;
import model.dao.IIngredientDao;
import model.dao.IngredientsDao;
import model.dao.UserDao;

public class IngredientManager {
	
	private static IngredientManager instance;
	private IIngredientDao ingredientDao;
	
	private IngredientManager() {
		this.ingredientDao = IngredientsDao.getInstance();
	}

	public static IngredientManager getInstance() {
		if (instance == null) {
			instance = new IngredientManager();
		}
		return instance;
	}

	public void createNewIngredient(String name, int price){
		Ingredient ingredient = new Ingredient(name, price);
		try {
			ingredientDao.addNewIngredient(ingredient);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteIngredient(Ingredient ingredient) {
		try {
			ingredientDao.deleteIngredient(ingredient);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeIngredient(Ingredient ingredient) {
		try {
			ingredientDao.updateIngredient(ingredient);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getListOfAllIngredients() {
		try {
			List<Ingredient> ingredients = ingredientDao.getAllIngredients();
		} catch (SQLException | InvalidArgumentsException e) {
			e.printStackTrace();
		}
		//print list of ingredients
	}
	

}
