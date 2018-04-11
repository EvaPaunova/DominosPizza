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

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			boolean check = UserManager.getInstance().check(username,password);
			if(check) {
				
				//..login
				//redirect
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}