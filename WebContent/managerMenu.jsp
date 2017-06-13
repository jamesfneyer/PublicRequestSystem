<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Menu</title>
<link rel="stylesheet" href="style.css">
<style type="text/css">
table, tr, td{
	border: solid 1px;
}
table{
	float: center;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
<header>Welcome to the main menu for Managers</header>
<h1>${user.lastName}, ${user.firstName}</h1>
	<br>
	<form method="post" action="updateRequest">
	<input type="hidden" name="action" value="updatedRequest"> 
	<input type="hidden" name="user" value='${user.username}'>
	<table class="display">
		<tr>
			<td>Description:</td>
			<td>Justification:</td>
			<td>Date Needed:</td>
			<td>Delivery Mode:</td>
			<td>Total:</td>
			<td>Submitted Date:</td>
			<td>Status:</td>
		</tr>
		<c:forEach items="${requests}" var="request">
			<tr>
				<td>${request.description}</td>
				<td>${request.justification}</td>
				<td>${request.dateNeeded}</td>
				<td>${request.deliveryMode}</td>
				<td>${request.total}</td>
				<td>${request.submittedDate}</td>
				<td>
					<input type="hidden" name="id" value='${request.ID }'>
					<select name="status_${request.ID}" required>
						<option value="submitted">submitted</option>
						<option value="approved">approved</option>				
						<option value="rejected">rejected</option>
					</select>
				</td>
			</tr>
		</c:forEach>
		<tr><td><button type="submit">Submit</button></td></tr>
	</table>
	</form>
</body>
</html>