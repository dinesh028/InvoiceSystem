<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	 window.history.forward(); 
	function noBack() {
		window.history.forward();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<center>
		<h2>Invoice Management System</h2>
		<font color="red"> <c:choose>
				<c:when test="${empty member.email}"></c:when>
				<c:otherwise>Registration Successful</c:otherwise>
			</c:choose> </font>

		<c:if test="${not empty error}">

			<font color="red">
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
		</c:if>


		<form action="j_spring_security_check" method="post">
			<table>
				<tr>
					<td>User Id</td>
					<td><input type="text" name="j_username" value="admin">
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="j_password" value="admin">
					</td>
				</tr>
			</table>
			<!-- <a href="registration/main">Register Your Company</a> <br>--> 
			<input 	type="submit" name="Login" value="Login"> <input
				name="reset" type="reset" />
		</form>
	</center>

</body>
</html>