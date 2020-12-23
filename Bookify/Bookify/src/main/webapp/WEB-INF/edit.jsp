<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<h1><c:out value="${trip.getDestination()}"/></h1>
	<p><c:out value="${error}" /></p>
	<form:form action="/trips/${trip.getId()}/edit" method="POST" modelAttribute="show">
		<div>
			<form:label path="destination">Destination</form:label>
			<form:errors path="destination"/>
			<form:input path="destination" placeholder="${trip.getDestination()}"/>
		</div>
		<div>
			<form:label path="departure">Departure</form:label>
			<form:errors path="departure"/>
			<form:input type="date" path="departure" placeholder="${trip.getDeparture()}"/>
		</div>
		<p>
			<input type="submit" value="Update"/>
		</p>
	</form:form>
	<form:form action="/delete/${trip.getId()}" method="POST">
		<p>
			<input type="submit" value="Delete"/>
		</p>
	</form:form>
</body>
</html>