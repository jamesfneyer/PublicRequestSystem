<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products Menu</title>
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
	<header>Welcome to the Products Menu</header>
	<br>
	<nav id="topNav">
    	<a href="administratorMenu.jsp">Home</a>
    	<a href="/PRSWeb/ProductsServlet">Products</a>
    	<a href="/PRSWeb/UsersServlet">Users</a>
    	<a href="/PRSWeb/VendorsServlet">Vendors</a>
  	</nav>
  	<br>
  	<table class ="display">
	<tr><td>Vendor Name</td><td>Vendor Code</td><td>Product Name</td><td>PartNumber</td><td>Price</td><td>Unit</td><td>Photopath</td></tr>
	<c:forEach items="${products}" var="products">
    <tr>
       <td>${products.vendorName}</td>
       <td>${products.vendorCode}</td>
       <td>${products.name}</td>
       <td>${products.partNumber}</td>
       <td>${products.formattedPrice}</td>
       <td>${products.unit}</td>
       <td>${products.photoPath}</td>       
    </tr>
    </c:forEach>
    </table>
    <br>
  	<p>Would you like to update a product's information or add a product?</p>
  	<nav id="topNav">
    	<a href="/PRSWeb/ProductsAddingServlet">Add Product</a>
    	<a href="/PRSWeb/ProductsUpdatingServlet">Update Product</a>
  	</nav>
</body>
</html>