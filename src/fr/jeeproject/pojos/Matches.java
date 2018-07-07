package fr.jeeproject.pojos;

/**
 * @author gaele
 *
 */
public class Matches {

	private Long res_id;
	private String res_team_logo;
	private String res_team;
	private Long res_points;
	private Long res_j;
	private Long res_g;
	private Long res_n;
	private Long res_p;
	private Long res_p_min;
	private Long res_c_min;
	private Long res_diff;
		
	public Matches(String res_team_logo, String res_team, Long res_points, Long res_j, Long res_g, Long res_n, Long res_p,
			Long res_p_min, Long res_c_min, Long res_diff) {
		
		this.res_team_logo = res_team_logo;
		this.res_team = res_team;
		this.res_points = res_points;
		this.res_j = res_j;
		this.res_g = res_g;
		this.res_n = res_n;
		this.res_p = res_p;
		this.res_p_min = res_p_min;
		this.res_c_min = res_c_min;
		this.res_diff = res_diff;
	}

	/**
	 * @return the res_id
	 */
	public Long getRes_id() {
		return res_id;
	}

	/**
	 * @param res_id the res_id to set
	 */
	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}

	/**
	 * @return the res_team_logo
	 */
	public String getRes_team_logo() {
		return res_team_logo;
	}

	/**
	 * @param res_team_logo the res_team_logo to set
	 */
	public void setRes_team_logo(String res_team_logo) {
		this.res_team_logo = res_team_logo;
	}

	/**
	 * @return the res_team
	 */
	public String getRes_team() {
		return res_team;
	}

	/**
	 * @param res_team the res_team to set
	 */
	public void setRes_team(String res_team) {
		this.res_team = res_team;
	}

	/**
	 * @return the res_points
	 */
	public Long getRes_points() {
		return res_points;
	}

	/**
	 * @param res_points the res_points to set
	 */
	public void setRes_points(Long res_points) {
		this.res_points = res_points;
	}

	/**
	 * @return the res_j
	 */
	public Long getRes_j() {
		return res_j;
	}

	/**
	 * @param res_j the res_j to set
	 */
	public void setRes_j(Long res_j) {
		this.res_j = res_j;
	}

	/**
	 * @return the res_g
	 */
	public Long getRes_g() {
		return res_g;
	}

	/**
	 * @param res_g the res_g to set
	 */
	public void setRes_g(Long res_g) {
		this.res_g = res_g;
	}

	/**
	 * @return the res_n
	 */
	public Long getRes_n() {
		return res_n;
	}

	/**
	 * @param res_n the res_n to set
	 */
	public void setRes_n(Long res_n) {
		this.res_n = res_n;
	}

	/**
	 * @return the res_p
	 */
	public Long getRes_p() {
		return res_p;
	}

	/**
	 * @param res_p the res_p to set
	 */
	public void setRes_p(Long res_p) {
		this.res_p = res_p;
	}

	/**
	 * @return the res_p_min
	 */
	public Long getRes_p_min() {
		return res_p_min;
	}

	/**
	 * @param res_p_min the res_p_min to set
	 */
	public void setRes_p_min(Long res_p_min) {
		this.res_p_min = res_p_min;
	}

	/**
	 * @return the res_c_min
	 */
	public Long getRes_c_min() {
		return res_c_min;
	}

	/**
	 * @param res_c_min the res_c_min to set
	 */
	public void setRes_c_min(Long res_c_min) {
		this.res_c_min = res_c_min;
	}

	/**
	 * @return the res_diff
	 */
	public Long getRes_diff() {
		return res_diff;
	}

	/**
	 * @param res_diff the res_diff to set
	 */
	public void setRes_diff(Long res_diff) {
		this.res_diff = res_diff;
	}


	
}
