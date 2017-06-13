<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Menu</title>
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
	<header>Welcome to the Users Menu</header>
	<br>
	<nav id="topNav">
    	<a href="administratorMenu.jsp">Home</a>
    	<a href="/PRSWeb/ProductsServlet">Products</a>
    	<a href="/PRSWeb/UsersServlet">Users</a>
    	<a href="/PRSWeb/VendorsServlet">Vendors</a>
  	</nav>
  	<br>
  	<table class="display">
	<tr><td>Last Name</td><td>First Name</td><td>Username</td><td>Password</td><td>Phone</td><td>Email</td><td>Role</td></tr>
	<c:forEach items="${users}" var="user">

	   <tr>
       <td>${user.lastName}</td>
       <td>${user.firstName}</td>
       <td>${user.username}</td>
       <td>${user.password}</td>
       <td>${user.phone}</td>
       <td>${user.email}</td>
       <td>${user.role}</td> 
    </tr>
</c:forEach>   
</table>
    <br>
    <p>Would you like to update a user's information or add a user?</p>
    <nav id="botNav">
    	<a href="addUser.jsp">Add User</a>
    	<a href="updateUser.jsp">Update User</a>
  	</nav>
  	<br>
</body>
</html>