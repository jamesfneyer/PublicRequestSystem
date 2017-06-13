<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
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
<h1>Enter information for the Product you would like to add.</h1>
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
	<form method="post" action="addProduct">
		<input type="hidden" name="action" value="addedProduct">  
		    <table class ="display">
    <tr><td>Vendor Code</td><td>Product Name</td><td>PartNumber</td><td>Price</td><td>Unit</td><td>Photopath</td></tr>
    <tr><td><input type="text" name="vendorCode" placeholder="Vendor Code"
							maxlength=10 required/></td>
			<td><input type="text" name="name" placeholder="Product Name"
							maxlength=10 required/></td>
			<td><input type="text" name="partNumber" placeholder="PartNumber"
							maxlength=10 required/></td>
			<td><input type="text" name="price" placeholder="Price"
							maxlength=12 required/></td>			
			<td><input type="text" name="unit" placeholder="Unit"
							maxLength=10 required/></td>
			<td><input type="text" name="photoPath" placeholder="Photopath"
							maxlength=255 required/></td>
			</tr>
			<tr><td><button type="submit">Submit</button></td></tr>
    </table>
    </form>
</body>
</html>