<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>
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
<br><br>
<h1>Please enter Users information to update below:</h1>
<br>
<br>
	<form method="post" action="updateUser">
		<input type="hidden" name="action" value="updatedUser">  
		<table class="display">      
		<tr><td>Last Name</td><td>First Name</td><td>Username</td><td>Password</td><td>Phone</td><td>Email</td><td>Role</td></tr>
		<tr><td><input type="text" name="lastName" placeholder="Last Name"
							maxlength=20 /></td>
			<td><input type="text" name="firstName" placeholder="First Name"
							maxlength=20 /></td>
			<td><input type="text" name="username" placeholder="Username"
							maxlength=20 required/></td>
			<td><input type="text" name="password" placeholder="Password"
							maxlength=15 /></td>
			<td><input type="tel" name="phone" placeholder="Phone"
							maxlength=12 /></td>
			<td><input type="email" name="email" placeholder="Email"
							maxlength=75 /></td>
			<td><select name="role" >
				<option value="employee">Employee</option>
				<option value="manager">Manager</option>				
				<option value="administrator">Administrator</option>
			</select></td>

			</tr>
			<tr><td><button type="submit">Submit</button></td></tr>
		</table> 
		</form>
</body>
</html>