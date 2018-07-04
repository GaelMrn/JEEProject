<%@include file="../template/header.jsp" %>

<div class="main-container">
	<p>Admin profil</p>
	
	<div id="form-admin-profil-container">
	
		${messageErrorDBConnect}
		${errorMessage}
		
		<form method="post" action="admin-profil" id="form-admin-profil">
			        	 
            <label for="nom">Nom</label>
            <input type="text" name="lastname" required class="" value="${userLastname}"/>
                
            <label for="prenom">Prénom </label>
            <input type="text" name="firstname" required class="" value="${userFirstname}"/>
        	
            <label for="email">Email</label>
            <input type="text" name="email" required class="${errorAdminProfilEmail}" value="${userEmail}"/>
            
            <label for="password">Mot de passe </label>
            <input type="password" name="password" required class="${errorAdminProfilPassword}" value="${userPassword}"/>
	
			<select name="city" required class="form-control input-lg">
                <%-- <option disabled selected value="ville">${userCity}</option> --%>
                <option selected value="${userCity.getId()}">${userCity.getNom()}</option>
                <c:forEach var="city"  items="${city}" >
                    <option value="${city.getId()}">${city.getNom()}</option>
                </c:forEach>
            </select>
                
            <input type="submit" value="Valider" />
        </form>
    </div>
</div>

<%@include file="../template/footer.jsp" %>