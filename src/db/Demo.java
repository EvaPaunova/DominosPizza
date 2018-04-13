package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import controller.UserManager;
import exceptions.InvalidArgumentsException;
import model.Ingredient;
import model.Product;
import model.User;
import model.dao.ProductDao;
import model.dao.UserDao;

public class Demo {

	public static void main(String[] args) {

		User u = UserManager.getInstance().logIn("ivtashkova", "parola");
		System.out.println(u.getUsername());
		Product p = new Product("Pizza", 10.90, 1);
		Ingredient i = new Ingredient("domat", 2);
		HashSet<Ingredient> ingreds = new HashSet<>();
		p.setIngredients(ingreds);
		try {
			ProductDao.getInstance().addNewProduct(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
