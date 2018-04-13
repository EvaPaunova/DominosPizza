package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.UserManager;
import exceptions.InvalidArgumentsException;
import model.dao.UserDao;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if (UserDao.getInstance().checkUserData(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("logged", true);
				session.setAttribute("user", UserDao.getInstance().getUserByUsername(username));
				response.sendRedirect("html.html");
				return;
			} else {
				response.sendRedirect("loging.html");
				return;
			}
		} catch (IOException e) {
			try {
				response.sendRedirect("loging.html");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	

}