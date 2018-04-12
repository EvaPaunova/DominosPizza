package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserManager;
import exceptions.InvalidArgumentsException;
import model.dao.UserDao;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if (UserDao.getInstance().checkUserData(username, password)) {
				request.getSession().setAttribute("logged", true);
				request.getSession().setAttribute("user", UserDao.getInstance().getUserByUsername(username));
				request.getRequestDispatcher("logged.html").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} catch (IOException e) {
			try {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
}
	}

}