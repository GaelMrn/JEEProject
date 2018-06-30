<%@include file="template/header.jsp" %>

<div class="main-container">
	<p>Connexion</p>
	
	<div id="formLoginContainer">
		
		${messageErrorDBConnect}
		${messageErrorLogin}
		${emptyLoginField}
				
		<form method="post" action="login" id="formLogin">
			<label for="email" class="formLoginLabel">Email</label>
	        <input type="text" name="email" required class="formLoginInput ${errorInput}"/>
	        
	        <label for="password" class="formLoginLabel">Mot de passe </label>
            <input type="password" name="password" required class="formLoginInput ${errorInput}"/>
            
            <input type="submit" value="Connexion" id="formLoginSubmit" />
		</form>
	</div>
</div>

<%@include file="template/footer.jsp" %>