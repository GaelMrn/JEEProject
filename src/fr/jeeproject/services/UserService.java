package fr.jeeproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.jeeproject.pojos.User;

public class UserService {

private Connection connection;
	
	public UserService(Connection connection) {

		this.connection = connection;
	}

	public User getUserFromId(Long id) throws SQLException {

		User user = null;
		
		String query = "select * from user where id = ?";
		
		PreparedStatement psmt = connection.prepareStatement(query);
		
		psmt.setLong(1, id);
		
		ResultSet rs = psmt.executeQuery();
		
		if (rs.next()) {
			
			user = new User(rs.getString("email"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("motDePasse"),
					rs.getLong("idVille"),
					rs.getLong("USE_ROL_ID"));
			
			user.setId(rs.getLong(1));
		}		

		rs.close();
		psmt.close();
		
		return user;

	}

	public User getUserFromEmailAndPassword(String email, String password) throws SQLException {

		User user = null;	
		
		String query = "select * from user where email = ? and motDePasse = ?";
		
		PreparedStatement psmt = connection.prepareStatement(query);
		
		psmt.setString(1, email);
		psmt.setString(2, password);
		
		ResultSet rs = psmt.executeQuery();
		
		if (rs.next()) {
			
			user = new User(rs.getString("email"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("motDePasse"),
					rs.getLong("idVille"),
					rs.getLong("USE_ROL_ID"));
			
			user.setId(rs.getLong(1));
		}		

		rs.close();
		psmt.close();
		
		return user;

	}
	
	public User createUser(User user) throws SQLException {

		String query = "insert into user (email, nom, prenom, motDePasse, idVille, USE_ROL_ID) "
			+ " values (?, ?, ?, ?, ?, ?)";

		PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		psmt.setString(1, user.getEmail());
		psmt.setString(2, user.getNom());
		psmt.setString(3, user.getPrenom());
		psmt.setString(4, user.getMotDePasse());
		psmt.setLong(5, user.getIdVille());
		psmt.setLong(6, user.getIdRole());
		
		psmt.executeUpdate();
		
		ResultSet rs = psmt.getGeneratedKeys();
		
		if (rs.first()) {
			user.setId(rs.getLong(1));			
		}
		
		rs.close();
		psmt.close();
		
		return user;
	}
	
	public User updateUser(User user) throws SQLException {

		String query = 
			"UPDATE user SET " +
			"email = ? , " + 
			"nom = ? , " + 
			"prenom = ? , " + 
			"motDePasse = ? , " + 
			"idVille = ? , " + 
			"USE_ROL_ID = ? " +
			"WHERE id = ?";

		PreparedStatement psmt = connection.prepareStatement(query);
		
		psmt.setString(1, user.getEmail());
		psmt.setString(2, user.getNom());
		psmt.setString(3, user.getPrenom());
		psmt.setString(4, user.getMotDePasse());
		psmt.setLong(5, user.getIdVille());
		psmt.setLong(6, user.getIdRole());
		psmt.setLong(7, user.getId());
		
		psmt.executeUpdate();

		psmt.close();
		
		return user;
	}
}
