package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Order {
	private int id;
	private int userId;
	
	private int status;
	private double price;
	private LocalDateTime date;
	private String address;
	private Map<Product, Integer> products = new HashMap<>();
	
	public Order(double price,LocalDateTime date, Status status) {
		setPrice(price);
		setDate(date);
		setStatus(status);
	}
	
	public Order(double price,LocalDateTime date, Status status, Map<Product, Integer> products) {
		setPrice(price);
		setDate(date);
		setStatus(status);
		this.products = products;
	}
	
	public Order(int id,double price,LocalDateTime date, Status status) {
		this(price,date,status);
		setId(id);
	}
	
	public Order(int id,double price,LocalDateTime date, int status) {
		this.id = id;
		this.date = date;
		this.status = status;
		setId(id);
	}
	
	public Order(int id,double price,LocalDateTime date, int status, Map<Product, Integer> products) {
		this.id = id;
		this.date = date;
		this.status = status;
		setId(id);
		this.setProducts(products);
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status.getId();
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public Map<Product, Integer> getProducts() {
		return Collections.unmodifiableMap(products);
	}
	
	public void setProducts(Map<Product, Integer> products) {
		if(products != null) {
			this.products = products;
		}
	}
	
}