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
import javax.servlet.http.HttpSession;

import fr.jeeproject.pojos.City;
import fr.jeeproject.pojos.User;
import fr.jeeproject.services.CityService;
import fr.jeeproject.services.UserService;
import fr.jeeproject.utils.DataConnect;

/**
 * Servlet implementation class AdminProfilServlet
 */
@WebServlet("/admin-profil")
public class AdminProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfilServlet() {
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
		
			// Try to connect to the database
			try {
	            Connection connection = DataConnect.getConnection();
	            
	            // Instance VilleService
	            CityService cityService =  new CityService(connection);
	            
	            // Retrieve and stock every city
	            List<City> city = cityService.getAll();
	            
	            request.setAttribute("city", city);
	            		        
		        // Get City of the user
		        City userCity = cityService.getVilleFromId((Long) session.getAttribute("idCity"));
		        User user = (User) session.getAttribute("User");
	
		        // Store in session the lastname of the user
		        request.setAttribute("userLastname", user.getNom());
		        // Store in session the firstname of the user
		        request.setAttribute("userFirstname", user.getPrenom());
		        // Store in session the email of the user
		        request.setAttribute("userEmail", user.getEmail());
		        // Store in session the password of the user
		        request.setAttribute("userPassword", user.getMotDePasse());
		        // Store in session the name of the city
		        request.setAttribute("userCity", userCity);
		        
			} catch (SQLException | ClassNotFoundException e) {
				
				// Can't connect to database
	            e.printStackTrace();
	
	        	// Database connection failed : Set an error sentence
				String messageErrorDBConnect = "<span class='error'>La connection à la base de données a échoué.</span>";
				request.setAttribute("messageErrorDBConnect", messageErrorDBConnect);
				
	        }
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration/admin-profil.jsp").forward(request, response);

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String postAdminProfilLastname = request.getParameter("lastname");
		String postAdminProfilFirstname = request.getParameter("firstname");
		String postAdminProfilEmail = request.getParameter("email");
		String postAdminProfilPassword = request.getParameter("password");
		String postAdminProfilCity = request.getParameter("city");
				
		// Check if every fields are filled
		if(
			!postAdminProfilLastname.isEmpty() &&
			!postAdminProfilFirstname.isEmpty() &&
			!postAdminProfilEmail.isEmpty() &&
			!postAdminProfilPassword.isEmpty()		
		) {
			
			// If email match with the following pattern
			if( 
				postAdminProfilEmail.matches( "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{2,4}" ) &&
				!postAdminProfilEmail.matches( "[a-zA-Z0-9._-]+@yopmail\\.[a-z]{2,4}" )
			) {
				
				// If password don't measure 6 characters or more
				if( postAdminProfilPassword.length() > 5 ) {
					
					// Try to connect to the database
					try {
		                Connection connection = DataConnect.getConnection();
		                
		                // Instance of JoueurService
		                UserService userService = new UserService(connection);

		                // Retrieve the value of the city. That's to say the city ID. Then cast city to Long type
		                Long cityValue = Long.valueOf(postAdminProfilCity);
		                		                
		                // Open session
		    	        HttpSession session = request.getSession();
		    	        
		    	        User user = (User) session.getAttribute("User");
		    	        // Retrieve the role id of the user
		    	        Long userIdRole = user.getIdRole();
		    	        		    	        
		    	        user.setEmail(postAdminProfilEmail);
		    	        user.setNom(postAdminProfilLastname);
		    	        user.setPrenom(postAdminProfilFirstname);
		    	        user.setMotDePasse(postAdminProfilPassword);
		    	        user.setIdVille(cityValue);
		    	        user.setIdRole(userIdRole);
		                
		                // Call updateUser to insert into database the new player
		                userService.updateUser(user);
		                
	    		        // Stock the player in the session
		                session.setAttribute( "User", user );
		                
		                session.setAttribute( "idRole", user.getIdRole() );
		                
		                session.setAttribute( "idCity", user.getIdVille() );
	    	        
		                // Redirect to login page
		                response.sendRedirect("admin-profil");

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
	                String errorAdminProfilPassword = "error-admin-profil-password";
	                request.setAttribute("errorAdminProfilPassword", errorAdminProfilPassword);

	    			doGet(request, response);
				}
				
			}else {
				
                // Set a error message
                String errorMessage = "<span class='error'>Le format d'e-mail est invalide.</span>";
                request.setAttribute("errorMessage", errorMessage);
                
                // Error email field
                String errorAdminProfilEmail = "error-admin-profil-email";
                request.setAttribute("errorAdminProfilEmail", errorAdminProfilEmail);

    			doGet(request, response);
			}
			
		}else {
			
			// Fields are empty : Message
			String errorMessage = "<span class='error'>Veuillez renseigner tous les champs.</span>";
			request.setAttribute("errorMessage", errorMessage);
			
			doGet(request, response);
		}
	}

}
