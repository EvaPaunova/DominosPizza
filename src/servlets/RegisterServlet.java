package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserManager;
import exceptions.InvalidArgumentsException;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
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
		String phoneNumber = request.getParameter("phonenumber");

		User user = null;
		
		if (password.equals(confirmpassword)) {
			try {
				user = new User(firstName, lastName, username, email, password, address, phoneNumber);
			} catch (InvalidArgumentsException e) {
				e.printStackTrace();
			}
			try {
				UserManager.getInstance().register(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.getWriter().println("Successfull registration!");
			
			return;
		} else {
			response.getWriter().println("Your password does not match!");
		}
	}
	

}