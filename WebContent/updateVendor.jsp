<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Vendor</title>
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
<h1>Please enter New Vendor's information below:</h1>
<br>
	<form method="post" action="updateVendor">
		<input type="hidden" name="action" value="updatedVendor">  
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
		<tr><td><input type="text" name="code" placeholder="Code"
							maxlength=10 required/></td>
			<td><input type="text" name="name" placeholder="Name"
							maxlength=255 required/></td>
			<td><input type="text" name="address" placeholder="Address"
							maxlength=255 required/></td>
			<td><input type="text" name="city" placeholder="City"
							maxlength=255 required/></td>
			<td><select name="state" required>
				<option value="OH">Ohio</option>
				<option value="KY">Kentucky</option>				
				<option value="MI">Michigan</option>
			</select></td>
			<td><input type="text" name="zipCode" placeholder="ZipCode"
							maxLength=5 required/></td>
			<td><input type="tel" name="phone" placeholder="Phone"
							maxlength=12 required/></td>
			<td><input type="email" name="email" placeholder="Email"
							maxlength=255 required/></td>
			<td><select name="preapproved" required>
				<option value="false">Not Preapproved</option>
				<option value="true">Preapproved</option>				
			</select></td>
			
			</tr>
			<tr><td><button type="submit">Submit</button></td></tr>
		</table> 
		</form>
</body>
</html>