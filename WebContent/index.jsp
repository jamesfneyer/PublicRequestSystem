<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Request System</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
<div class="SubmitPage">
	<h1>Welcome to the Purchase Request System</h1>
	<form method="post" action="login">
	    <input type="hidden" name="action" value="loggedin">        
	
		<div class="SubmitContainer">
			<table class="submitinfo">
				<tbody>
					<tr>
						<td><input type="text" name="username" placeholder="username"
							maxlength=20 /><br /></td>
					</tr>
					<tr>
						<td><input type="password" name="password"
							placeholder="password" maxlength=15 /><br /></td>
					</tr>
					<td colspan="2">

						<div class="button">
							<button type="submit">Submit</button>
							</div>
					</td>
		</tbody>
		</table>
		</div>
	</form>
	</div>
</body>
</html>