<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
	<nav>
		<div id="logo-container">
			<a href="home"><img src="images/logo.png" id="logo"></a>
		</div>
		<ul>
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
			
			<c:if test="${empty sessionScope.User}">
			<!--  If User is NOT connected -->
				<li>
					<a href="signup">Inscription</a>
				</li>
				<li>
					<a href="login">Connexion</a>
				</li>
			</c:if>
			
			<c:if test="${!empty sessionScope.User}">	
			<!--  If User is connected -->
				<li>
					<a href="logout">Déconnexion</a>
				</li>	
				<li id="admin-menu">
					<p>Administration</p>	
					<ul>
						<li>
							<a href="admin-profil">Profil</a>
						</li>
						<c:if test="${sessionScope.idRole != 3}">
						<li>
							<a href="admin-blog">Blog</a>
						</li>
						<c:if test="${sessionScope.idRole == 1}">
						<li>
							<a href="admin-competitions">Championnats</a>
						</li>
						<li>
							<a href="admin-matches">Matches</a>
						</li>
						<li>
							<a href="admin-teams">Equipes</a>
						</li>
						</c:if>
						</c:if>
					</ul>
				</li>
			</c:if>
		</ul>
	</nav>
</header>
