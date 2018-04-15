package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.manager.UserManager;
import exceptions.InvalidArgumentsException;
import model.User;
import model.dao.UserDao;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = null;
		try {
			user = UserManager.getInstance().logIn(username, password);
		} catch (SQLException | InvalidArgumentsException e) {
			e.getMessage();
		}
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("logged", true);
			session.setAttribute("user", user);
			response.sendRedirect("logged.html");
		} else {
			response.sendRedirect("errorpage.html");
		} 
	}
}