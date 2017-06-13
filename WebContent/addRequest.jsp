<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>Add Request JSP</title>
</head>
<body>


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
    <form method="post" action="addRequest">
	<input type="hidden" name="action" value="addedRequest"> 
	<input type="hidden" name="user" value='${user.username}'>
    <table class="display">
		<tr>
				<td><input type="text" name="description"
					placeholder="Description" maxlength=100 required /></td>
				<td><input type="text" name="justification"
					placeholder="Justification" maxlength=255 required /></td>
				<td><input type="date" name="dateNeeded"
					placeholder="Date Needed" required /></td>
				<td><select name="deliveryMode" required>
						<option value="delivery">Delivery</option>
						<option value="pickUp">Pick Up</option>
				</select></td>
		</tr>
		<tr>
			<td><button type="submit">Submit</button></td>
		</tr>
	</table>
	</form>
</body>
</html>