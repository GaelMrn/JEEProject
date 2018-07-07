package fr.jeeproject.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.jeeproject.pojos.City;
import fr.jeeproject.pojos.Matches;

public class MatchesService {

	private Connection connection;
	
	public MatchesService(Connection connection) {

		this.connection = connection;
	}
	
	public List<Matches> getAll() throws SQLException {
		
		List<Matches> matches = new ArrayList<Matches>();
		
		Statement statement = connection.createStatement();
		
		String query = "select * from results_matches";
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()) {
			          
			Matches match = new Matches(
				rs.getString("RES_TEAM_LOGO"),
				rs.getString("RES_TEAM"),
				rs.getLong("RES_POINTS"),
				rs.getLong("RES_J"),
				rs.getLong("RES_G"),
				rs.getLong("RES_N"),
				rs.getLong("RES_P"),
				rs.getLong("RES_P_MIN"),
				rs.getLong("RES_C_MIN"),
				rs.getLong("RES_DIFF")
			);
							
			match.setRes_id(rs.getLong(1));						
			
			matches.add(match);
		}
		
		return matches;
	}
	
}
