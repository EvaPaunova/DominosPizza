package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Product {
	private int id;
	private String name;
	private double price;
	private int size;
	private HashSet<Ingredient> ingredients;
	
	public Product(String name, double price, Size size) {
		setName(name);
		setPrice(price);
		setSize(size);
	}
	
	public Product(String name, double price, int size) {
		setName(name);
		setPrice(price);
		this.size = size;
	}
	
	public Product(int id,String name, double price, Size size) {
		this(name,price,size);
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
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size.getId();
	}

	public Set<Ingredient> getIngredients() {
		return Collections.unmodifiableSet(ingredients);
	}

	public void setIngredients(HashSet<Ingredient> ingredients) {
		if(ingredients != null) {
			this.ingredients = ingredients;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}