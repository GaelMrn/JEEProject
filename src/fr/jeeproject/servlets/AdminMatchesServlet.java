package fr.jeeproject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminMatchesServlet
 */
@WebServlet("/admin-matches")
public class AdminMatchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMatchesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		// Retrieve the session
        HttpSession session = request.getSession();

        Long idRole = (Long) session.getAttribute("idRole");
        
        if(idRole == null){
        	// If idRole isn't set, forbid access
        	response.sendRedirect("home");
        }else {
        	
        	if(idRole == 1) {
            	// If idRole is administrator, allow access
            	
            	this.getServletContext().getRequestDispatcher("/WEB-INF/administration/admin-matches.jsp").forward(request, response);        	
            }else {
            	// If idRole isn't set, forbid access
            	response.sendRedirect("home");
            }
        }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
