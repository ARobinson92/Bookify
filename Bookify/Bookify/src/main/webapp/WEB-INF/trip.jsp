<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trip to ${trip.destination}</title>
<style>
	table, th, td{
		border: 2px solid black;
		border-collapse: collapse;
		font-size: 32px;
	}
</style>
</head>
<body>

	<c:if test="${userEmail} == ${trip.creator}" var="variable" scope="scope">
		<a href="/trips/${trip.id}/edit">Edit</a>
	</c:if>

	<h1>Destination: ${trip.destination}</h1>
	<br />
	<h2>This flight will be leaving on ${trip.departure}</h2>
	<br />
	<h2>Rating: ${trip.rating}</h2>
	<br />
	<h2>Users on this trip: </h2>
	<br />
	<table>
			<thead>
				<tr>
					<th class="thead">Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.email}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<h2>Would you like to join this trip?</h2>
	<form:form action="/trips/${trip.id}" method="post">
		<input type="submit" value="Join" />
	</form:form>

</body>
</html>