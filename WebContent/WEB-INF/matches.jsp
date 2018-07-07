<%@include file="template/header.jsp" %>

<div class="main-container">

	<div id="matches-container">
		
		<h2>Classement des matches</h2>
		
		${messageErrorDBConnect}
		
		<table>
			<tr>
				<th>Logo</th>
				<th>Equipe</th>
				<th>Pts.</th>
				<th>J.</th>
				<th>G.</th>
				<th>N.</th>
				<th>P.</th>
				<th>p.</th>
				<th>c.</th>
				<th>diff.</th>
			</tr>
			<c:forEach var="match"  items="${match}" >
				<tr>
					<td><img src="${match.getRes_team_logo()}"></td>
					<td>${match.getRes_team()}</td>
					<td>${match.getRes_points()}</td>
					<td>${match.getRes_j()}</td>
					<td>${match.getRes_g()}</td>
					<td>${match.getRes_n()}</td>
					<td>${match.getRes_p()}</td>
					<td>${match.getRes_p_min()}</td>
					<td>${match.getRes_c_min()}</td>
					<td>${match.getRes_diff()}</td>
				</tr>
	        </c:forEach>
			
		</table>
	</div>
</div>

<%@include file="template/footer.jsp" %>