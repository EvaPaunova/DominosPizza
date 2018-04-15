package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean logged = false;
		HttpSession session = request.getSession();
		if (session.getAttribute("logged") != null) {
			logged = (boolean) request.getSession().getAttribute("logged");
		}
		if (logged) {
			request.getRequestDispatcher("logged.html").forward(request, response);
		} else {
			request.getRequestDispatcher("html.html").forward(request, response);
}
	}


}
