package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.UserManager;
import exceptions.InvalidArgumentsException;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor. 
     */
    public RegisterServlet() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first name");
		String lastName = request.getParameter("last name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirm password");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phone number");

		User user = null;
		
		if (password.equals(confirmpassword)) {
			try {
				user = new User(firstName, lastName, username, email, password, address, phoneNumber);
				UserManager.getInstance().register(user);
			} catch (SQLException | InvalidArgumentsException e) {
				System.out.println(e.getMessage());
			}
			
			response.getWriter().println("Successfull registration!");
			
			return;
		} else {
			response.getWriter().println("Your password does not match!");
		}
	}
	

}