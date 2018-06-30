<%@include file="template/header.jsp" %>

<div class="main-container">
	<p>Inscription</p>
	
	<div id="formSignupContainer">
	
		${messageErrorDBConnect}
		${errorMessage}
		${signupFieldsEmptyMessage}
	
		<form method="post" action="signup" id="formSignup">
			        	 
            <label for="nom">Nom</label>
            <input type="text" name="lastname" required class=""/>
                
            <label for="prenom">Pr�nom </label>
            <input type="text" name="firstname" required class=""/>
        	
            <label for="email">Email</label>
            <input type="text" name="email" required class="${errorSignupEmail}"/>
            
            <label for="password">Mot de passe </label>
            <input type="password" name="password" required class="${errorSignupPassword}"/>
	
			<select name="city" required class="form-control input-lg">
                <option disabled selected value="ville">Ville</option>
                <c:forEach var="city"  items="${city}" >
                    <option value="${city.getId()}">${city.getNom()}</option>
                </c:forEach>
            </select>
                
            <input type="submit" value="Valider" />
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>