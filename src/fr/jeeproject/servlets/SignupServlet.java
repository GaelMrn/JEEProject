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
import fr.jeeproject.pojos.User;
import fr.jeeproject.services.CityService;
import fr.jeeproject.services.UserService;
import fr.jeeproject.utils.DataConnect;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
            CityService cityService =  new CityService(connection);
            
            // Retrieve and stock every city
            List<City> city = cityService.getAll();
            
            request.setAttribute("city", city);
            
//            this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    		
		} catch (SQLException | ClassNotFoundException e) {
			
			// Can't connect to database
            e.printStackTrace();

        	// Database connection failed : Set an error sentence
			String messageErrorDBConnect = "<span class='error'>La connection à la base de données a échoué.</span>";
			request.setAttribute("messageErrorDBConnect", messageErrorDBConnect);
			
        }

//    	doGet(request, response);
        this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String postSignupLastname = request.getParameter("lastname");
		String postSignupFirstname = request.getParameter("firstname");
		String postSignupEmail = request.getParameter("email");
		String postSignupPassword = request.getParameter("password");
		String postSignupCity = request.getParameter("city");
				
		// Check if every fields are filled
		if(
			!postSignupLastname.isEmpty() &&
			!postSignupFirstname.isEmpty() &&
			!postSignupEmail.isEmpty() &&
			!postSignupPassword.isEmpty() &&
			!postSignupCity.isEmpty()				
		) {
			
			// If email match with the following pattern
			if( 
				postSignupEmail.matches( "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}" ) &&
				!postSignupEmail.matches( "[a-zA-Z0-9._-]+@yopmail\\.[a-z]{2,4}" )
			) {
				
				// If password don't measure 6 characters or more
				if( postSignupPassword.length() > 5 ) {
					
					// Try to connect to the database
					try {
		                Connection connection = DataConnect.getConnection();
		                
		                // Instance of JoueurService
		                UserService userService = new UserService(connection);

		                // Retrieve the value of the city. That's to say the city ID. Then cast city to Long type
		                Long cityValue = Long.valueOf(postSignupCity);
		                
		                // Create player
		                User createPlayer = new User(
		            		postSignupEmail,
		                    postSignupLastname,
		                    postSignupFirstname,
		            		postSignupPassword,
		            		cityValue,
		                    (long) 3
		                );

		                // Call createJoueur to insert into database the new player
		                userService.createUser(createPlayer);
	    	        
		                // Redirect to login page
		                response.sendRedirect("login");

		                // Close the connection
		    			connection.close();
		    			
		            } catch (SQLException | ClassNotFoundException e) {
		                e.printStackTrace();
		            }
					
				}else {

	                // Set a error message
	                String errorMessage = "<span class='error'>Le mot de passe doit faire au moins 6 caractères.</span>";
	                request.setAttribute("errorMessage", errorMessage);
	                
	                // Error password field
	                String errorSignupPassword = "errorSignupPassword";
	                request.setAttribute("errorSignupPassword", errorSignupPassword);

	    			doGet(request, response);
				}
				
			}else {
				
                // Set a error message
                String errorMessage = "<span class='error'>Le format d'e-mail est invalide.</span>";
                request.setAttribute("errorMessage", errorMessage);
                
                // Error email field
                String errorSignupEmail = "errorSignupEmail";
                request.setAttribute("errorSignupEmail", errorSignupEmail);

    			doGet(request, response);
			}
			
		}else {
			
			// Fields are empty : Message
			String signupFieldsEmptyMessage = "<span class='error'>Veuillez renseigner tous les champs.</span>";
			request.setAttribute("signupFieldsEmptyMessage", signupFieldsEmptyMessage);
			
			doGet(request, response);
		}
	}

}
