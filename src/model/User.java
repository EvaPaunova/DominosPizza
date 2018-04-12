package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import exceptions.InvalidArgumentsException;

public class User {
	private static final int MIN_FIRSTNAME_LENGTH = 3;
	private static final int MIN_LASTNAME_LENGTH = 6;
	public static final int PASSWORD_MIN_LENGTH = 6;
	public static final int PASSWORD_MAX_LENGTH = 15;
	public static final int USERNAME_MIN_LENGTH = 6;
	public static final int USERNAME_MAX_LENGTH = 15;
	public static final int ADDRESS_MIN_LENGTH = 15;
	public static final int ADDRESS_MAX_LENGTH = 40;
	public static final int PHONE_MIN_LENGTH = 8;
	public static final String INVALID_NAME = "Invalid name";
	public static final String INVALID_EMAIL = "Invalid email";
	public static final String INVALID_PASSWORD = "Invalid password";
	public static final String INVALID_PHONE = "Invalid phone number";
	public static final String INVALID_ADDRESS = "Invalid address";
	public static final String INVALID_USERNAME = "Invalid username";
	
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String username;
	private String password;
	private String email;
	private boolean logged = false;
	private ArrayList<Order> orders = new ArrayList<>();
	private HashSet<Product> favorite = new HashSet<>();
	private HashMap<Product, Integer> cart = new HashMap<>();
	
	public User(String firstname,String lastname,String username,String password,String email, String address, String phoneNumber) throws InvalidArgumentsException {
		setFirstName(firstname);
		setLastName(lastname);
		setUsername(username);
		setEmail(email);
		setPassword(password);
		setAddress(address);
		setPhoneNumber(phoneNumber);
	}
	
	public User(int id,String firstname,String lastname,String username, String email, String password, String address, String phoneNumber) throws InvalidArgumentsException {
		this(firstname,lastname,username,password,email,address,phoneNumber);
		setId(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidArgumentsException {
		if (firstName.length() >= MIN_FIRSTNAME_LENGTH && firstName.matches("[a-zA-Z]+")) {
			this.firstName = firstName;
		} else {
			throw new InvalidArgumentsException(INVALID_NAME);
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidArgumentsException {
		if (lastName.length() >= MIN_LASTNAME_LENGTH && lastName.matches("[a-zA-Z]+")) {
			this.lastName = lastName;
		} else {
			throw new InvalidArgumentsException(INVALID_NAME);
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InvalidArgumentsException {
		if (address.length() >= ADDRESS_MIN_LENGTH && address.length() <= ADDRESS_MAX_LENGTH) {
			this.address = address;
		} else {
			throw new InvalidArgumentsException(INVALID_ADDRESS);
		}
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidArgumentsException {
		if ((phoneNumber.matches("[0-9]+")) && (!phoneNumber.isEmpty()) && (!phoneNumber.equals(""))
				&& phoneNumber.length() >= PHONE_MIN_LENGTH) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidArgumentsException(INVALID_PHONE);
}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws InvalidArgumentsException {
		if (username.length() >= USERNAME_MIN_LENGTH && username.length() <= USERNAME_MAX_LENGTH) {
			this.username = username;
	} else {
		throw new InvalidArgumentsException(INVALID_USERNAME);
	}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidArgumentsException {
		if (password.length() >= PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH) {
				this.password = password;
		} else {
			throw new InvalidArgumentsException(INVALID_PASSWORD);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidArgumentsException {
		if ((email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
			this.email = email;
		} else {
			throw new InvalidArgumentsException(INVALID_EMAIL);
		}
	}

	
	
}