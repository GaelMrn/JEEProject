package fr.jeeproject.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.jeeproject.pojos.User;
import fr.jeeproject.services.UserService;
import fr.jeeproject.utils.DataConnect;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		// Retrieve and stock login input : email & password
		String postEmail = request.getParameter("email");
		String postPassword = request.getParameter("password");
        
        // Check if fields are filled
        if(!postEmail.isEmpty() && !postPassword.isEmpty()) {
        	
    		// Connect to database
	        try {
	        	Connection connection = DataConnect.getConnection();
	        	
	        	// Create an instance of JoueurService
	        	UserService userService = new UserService(connection);
	        	
        		// Create player
        		User user = userService.getUserFromEmailAndPassword(postEmail, postPassword);
	    			
        		// If player isn't null
    			if(user != null) {

    				// Open session
    		        HttpSession session = request.getSession();
    		        
    		        // Stock the player in the session
	                session.setAttribute( "User", user );
	                
	                session.setAttribute("idRole", user.getIdRole());
	                
	    			// Login succeeded : redirect user
	    			response.sendRedirect("home");
	    			
	    			User userSession = (User) session.getAttribute("User");

              	}else {					
					// Login failed : Set a class
					String errorInput = "errorInput";
					request.setAttribute("errorInput", errorInput);
					
					// Login failed : Set an error sentence
					String messageErrorLogin = "<span class='error'>Les informations ne correspondent pas.</span>";
					request.setAttribute("messageErrorLogin", messageErrorLogin);
					
					doGet(request, response);
				}	
    			
    			// Close the connection
    			connection.close();

	    	}
	        catch (SQLException | ClassNotFoundException e) {    
	        	e.printStackTrace(); 
	        	
	        	// Database Login failed : Set an error sentence
				String messageErrorDBConnect = "<span class='error'>La connection à la base de données a échoué.</span>";
				request.setAttribute("messageErrorDBConnect", messageErrorDBConnect);
				
	        	doGet(request, response);
	    	}
	        
        }else {
        	// Error, empty login field
        	String emptyLoginField = "<span class='error'>Erreur. Veuillez entrez vos informations.</span>";
            request.setAttribute("emptyLoginField", emptyLoginField);

        	doGet(request, response);
    	}
	}

}
