<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
	<nav>
		<ul>
			<li>
				<a href="home"><img src="images/logo.png" id="logo"></a>
			</li>
			<li>
				<a href="competitions">Championnats</a>
			</li>
			<li>
				<a href="matches">Matches</a>
			</li>
			<li>
				<a href="teams">Equipes</a>
			</li>
			<li>
				<a href="blog">Blog</a>
			</li>
			
			<!--  If User is NOT connected -->
			<c:if test="${empty sessionScope.User}">
				<li>
					<a href="signup">Inscription</a>
				</li>
				<li>
					<a href="login">Connexion</a>
				</li>
			</c:if>
			
			<!--  If User is connected -->
			<c:if test="${!empty sessionScope.User}">	
				<li>
					<a href="logout">Déconnexion</a>
				</li>
			</c:if>
		</ul>
	</nav>
</header>
