<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

 <script type="text/javascript" src="/InvoiceSystem/js/jquery-1.8.0.min.js"></script>
<script language="javascript">
	
	function GoTo() {

		window.document.location.href = "/InvoiceSystem";
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Registration</title>
</head>
<body>
	<center>
		<br> <br> <br>





		<form method="post" id="regForm" action="/InvoiceSystem/registration/validity"
			autocomplete="off" commandName="tenant" enctype="multipart/form-data">

			<table>
				<tr>
					<td>Company Name</td>
					<td><input type="text" name="companyName" /> <br>
					</td>
				</tr>
				<tr>
					<td>UserId</td>
					<td><input type="text" name="tenantId" />
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td>Email Id</td>
					<td><input type="text" name="emailId" />
					</td>
				</tr>
				<tr>
					<td>Reminder Mail Service</td>
					<td><input type="checkbox" name="reminder" value="1">
					</td>
				</tr>
				<tr>
					<td>Thank You Mail Service</td>
					<td><input type="checkbox" name="thankyou" value="1">
					</td>
				</tr>
				<tr>
					<td>Date Format</td>
					<td><select name="dateFormat">
							<option value="dd-MM-y">DD-MM-YY</option>
							<option value="dd-MM-yy">DD-MM-YYYY</option>
							<option value="y-MM-dd">YY-MM-DD</option>
							<option value="yy-MM-dd">YYYY-MM-DD</option>
					</select></td>
				</tr>
				<tr>
					<td>Currency Format</td>
					<td><select name="currencyFormat">
							<option value="hi,IN">India</option>
							<option value="en,US">USA</option>
							<option value="pt,BR">Brazil</option>
							<option value="da,DK">Denmark</option>
							<option value="it,IT">Italy</option>
					</select></td>
				</tr>

				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contactNumber">
					</td>
				</tr>
				<tr>
					<td>Address</td>
					<td><textarea rows="4" cols="30" name="address"></textarea>
					</td>
				</tr>
				<tr>
					<td>City</td>
					<td><input type="text" name="city">
					</td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" name="state">
					</td>
				</tr>
				<tr>
					<td>Country</td>
					<td><input type="text" name="country">
					</td>
				</tr>
				
				
				<tr>
					<td>Upload Company Logo</td>
					<td><input type="file" name="fileUpload">
					</td>
				</tr>
			</table>


			<input name="Submit" type="submit" value="Submit" /> <input
				name="rest" type="reset" value="Reset" />
			<!-- <input type="button" onclick="javascript:GoTo()" Value="Back" /> -->
		</form>
	</center>
</body>
</html>

