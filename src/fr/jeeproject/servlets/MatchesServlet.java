package fr.jeeproject.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.jeeproject.pojos.City;
import fr.jeeproject.pojos.Matches;
import fr.jeeproject.services.CityService;
import fr.jeeproject.services.MatchesService;
import fr.jeeproject.utils.DataConnect;

/**
 * Servlet implementation class MatchesServlet
 */
@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		// Try to connect to the database
		try {
            Connection connection = DataConnect.getConnection();
            
            // Instance VilleService
            MatchesService matchesService =  new MatchesService(connection);
            
            // Retrieve and stock every city
            List<Matches> match = matchesService.getAll();
            
            request.setAttribute("match", match);
            
            
//		            this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    		
		} catch (SQLException | ClassNotFoundException e) {
			
			// Can't connect to database
            e.printStackTrace();

        	// Database connection failed : Set an error sentence
			String messageErrorDBConnect = "<span class='error'>La connection à la base de données a échoué.</span>";
			request.setAttribute("messageErrorDBConnect", messageErrorDBConnect);
			
        }
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/matches.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
