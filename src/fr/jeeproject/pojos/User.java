package fr.jeeproject.pojos;

public class User {

	 	private Long id;
		private String email;
		private String nom;
		private String prenom;
		private String motDePasse;
		private Long idVille;
		private Long idRole;
		
		/**
		 * @param email
		 * @param nom
		 * @param prenom
		 * @param motDePasse
		 * @param idVille
		 * @param idRole
		 */
		public User(String email, String nom, String prenom, String motDePasse, Long idVille, Long idRole) {
			this.email = email;
			this.nom = nom;
			this.prenom = prenom;
			this.motDePasse = motDePasse;
			this.idVille = idVille;
			this.idRole = idRole;
		}

		/**
		 * @return le id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id le id à définir
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return le email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email le email à définir
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return le nom
		 */
		public String getNom() {
			return nom;
		}

		/**
		 * @param nom le nom à définir
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}

		/**
		 * @return le prenom
		 */
		public String getPrenom() {
			return prenom;
		}

		/**
		 * @param prenom le prenom à définir
		 */
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		/**
		 * @return le motDePasse
		 */
		public String getMotDePasse() {
			return motDePasse;
		}

		/**
		 * @param motDePasse le motDePasse à définir
		 */
		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}

		
		/**
		 * @return le idVille
		 */
		public Long getIdVille() {
			return idVille;
		}

		/**
		 * @param idVille le idVille à définir
		 */
		public void setIdVille(Long idVille) {
			this.idVille = idVille;
		}

		/**
		 * @return the idRole
		 */
		public Long getIdRole() {
			return idRole;
		}

		/**
		 * @param idRole the idRole to set
		 */
		public void setIdRole(Long idRole) {
			this.idRole = idRole;
		}

		@Override
		public String toString() {
			return "User [email = " + email + ", nom = " + nom + ", prenom = " + prenom + ", mot de passe = " + motDePasse + ", idVille = " + idVille + ", idRole = " + idRole + "]";
		}

}
