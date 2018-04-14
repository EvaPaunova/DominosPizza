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
		
		try {
			if (UserDao.getInstance().userExists(username, password)) {
				UserManager.getInstance().logIn(username, password);
				HttpSession session = request.getSession();
				session.setAttribute("logged", true);
				session.setAttribute("user", UserDao.getInstance().getUserByUsername(username));
				request.getRequestDispatcher("logged.html").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("login.html").forward(request, response);
				return;
			}
		} catch (SQLException |InvalidArgumentsException e) {
			try {
				request.getRequestDispatcher("errorpage.html").forward(request, response);
			} catch (IOException e1) {
				
			}
		} 
	}
}