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
	<header>Welcome to the main menu for Employees</header>
	<h1>${user.lastName}, ${user.firstName}</h1>
	<br>
	<form method="post" action="addRequest">
	<input type="hidden" name="action" value="addedRequest"> 
	<input type="hidden" name="user" value='${user.username}'>
	<table class="display">
		<tr>
			<td>Description:</td>
			<td>Justification:</td>
			<td>Date Needed:</td>
			<td>Delivery Mode:</td>
			<td>Status</td>
			<td>Total:</td>
			<td>Submitted Date:</td>
		</tr>
		<c:forEach items="${requests}" var="request">
			<tr>
				<td>${request.description}</td>
				<td>${request.justification}</td>
				<td>${request.dateNeeded}</td>
				<td>${request.deliveryMode}</td>
				<td>${request.status}</td>
				<td>${request.total}</td>
				<td>${request.submittedDate}</td>
			</tr>
		</c:forEach>
		<tr>
				<td><input type="text" name="description"
					placeholder="Description" maxlength=100 required /></td>
				<td><input type="text" name="justification"
					placeholder="Justification" maxlength=255 required /></td>
				<td><input type="date" name="dateNeeded"
					placeholder="Date Needed" required /></td>
				<td><select name="deliveryMode" required>
						<option value="Delivery">Delivery</option>
						<option value="Pick Up">Pick Up</option>
				</select></td>
		</tr>
	</table>
	<br>
	<table class="display">
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
    	<tr>
    		<td><input type="text" name="vendorCode" placeholder="Vendor Code"
    					maxlength=10 required /></td>
    		<td><input type="text" name="partNumber" placeholder="Part Number"
    					maxlength=10 required /></td>
    		<td><input type="number" name="quantity" placeholder="Quantity"
    					maxlength=3 required /></td>
    	</tr>
    	<tr>
			<td><button type="submit">Submit</button></td>
		</tr>
    </table>
	</form>
</body>
</html>