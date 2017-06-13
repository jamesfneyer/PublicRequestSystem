<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendors Menu</title>
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
	<header>Welcome to the Vendors Menu</header>
	<br>
	<nav id="topNav">
    	<a href="administratorMenu.jsp">Home</a>
    	<a href="/PRSWeb/ProductsServlet">Products</a>
    	<a href="/PRSWeb/UsersServlet">Users</a>
    	<a href="/PRSWeb/VendorsServlet">Vendors</a>
  	</nav>
  	
  	<br>
  	
  	<table class="display">
	<tr><td>Code</td><td>Name</td><td>Address</td><td>City</td><td>State</td><td>ZipCode</td><td>Phone</td><td>Email</td><td>Preapproved</td></tr>
	<c:forEach items="${vendors}" var="vendor">
    <tr>
       <td>${vendor.code}</td>
       <td>${vendor.name}</td>
       <td>${vendor.address}</td>
       <td>${vendor.city}</td>
       <td>${vendor.state}</td>
       <td>${vendor.zipCode}</td>
       <td>${vendor.phone}</td>
       <td>${vendor.email}</td>
       <td>${vendor.preapproved}</td>
    </tr>
    </c:forEach>
    </table>
    <br>
    <p>Would you like to update a vendor's information or add a vendor?</p>
    <nav id="botNav">
    	<a href="addVendor.jsp">Add Vendor</a>
    	<a href="/PRSWeb/UpdatingVendorServlet">Update Vendor</a>
  	</nav>
</body>
</html>