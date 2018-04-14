package controller.manager;

import java.sql.SQLException;
import java.util.List;

import exceptions.InvalidArgumentsException;
import model.Ingredient;
import model.Product;
import model.dao.IProductDao;
import model.dao.ProductDao;

public class ProductManager {
	
	private static ProductManager instance;
	private IProductDao productDao;
	
	private ProductManager() {
		this.productDao = ProductDao.getInstance();
	}

	public static ProductManager getInstance() {
		if (instance == null) {
			instance = new ProductManager();
		}
		return instance;
	}

	public void createNewProduct(String name, int price){
		Product product = new Product(name, price, 2);
		try {
			productDao.addNewProduct(product);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteProduct(Product product) {
		try {
			productDao.deleteProduct(product);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeProduct(Product product) {
		try {
			productDao.updateProduct(product);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getListOfAllProduct() {
		try {
			List<Product> ingredients = productDao.getAllProducts();
		} catch (SQLException | InvalidArgumentsException e) {
			e.printStackTrace();
		}
		//print list of products
	}
	
	public void addNewProduct(Product product) {
		try {
			productDao.addNewProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
