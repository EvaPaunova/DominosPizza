package controller;

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


}
