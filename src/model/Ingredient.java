package model;

public class Ingredient {
	private int id;
	private String name;
	private double price;
	
	public Ingredient(String name, double price) {
		setName(name);
		setPrice(price);
	}
	
	public Ingredient(int id, String name, double price) {
		this(name,price);
		setId(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null && !(name.equals(""))) {
			this.name = name;
		}	
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price > 0) {
			this.price = price;
		}
		
	}

}
