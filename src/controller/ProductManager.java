package controller;

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

	
	

}
