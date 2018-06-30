package fr.jeeproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.jeeproject.pojos.City;

public class CityService {

private Connection connection;
	
	public CityService(Connection connection) {

		this.connection = connection;
	}
	
	public City getVilleFromId(Long id) throws SQLException {
		
		City ville = null;	
		
		String query = "select * from ville where id = ?";
		
		PreparedStatement psmt = connection.prepareStatement(query);
		
		psmt.setLong(1, id);
		
		ResultSet rs = psmt.executeQuery();
		
		if (rs.next()) {
			
			ville = new City(rs.getString("cp"),
					rs.getString("nom"));
			
			ville.setId(rs.getLong(1));
		}		

		rs.close();
		psmt.close();
		
		return ville;
		
	}
	
	public List<City> getAll() throws SQLException {
		
		List<City> villes = new ArrayList<City>();
		
		Statement statement = connection.createStatement();
		
		String query = "select * from ville";
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()) {
			
			City ville = new City(rs.getString("cp"),
					rs.getString("nom"));
							
			ville.setId(rs.getLong(1));						
			
			villes.add(ville);
		}
		
		return villes;
	}
}
