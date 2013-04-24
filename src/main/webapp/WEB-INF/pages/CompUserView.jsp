<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body>
	<jsp:include page="imageheaderComp.jsp"></jsp:include>

	<center>
		<br> <br></br>

		<c:choose>
			<c:when test="${user.role eq 'ROLE_ADMIN'}">
				<form method="post" action="/InvoiceSystem/company/admin"
					autocomplete="off">

					<table>
						<tr>
							<td>Name</td>
							<td><input type="text" name="name" value="" /></td>
						</tr>
						<tr>
							<td>User-Id</td>
							<td><input type="text" name="userId" value="" /></td>

						</tr>
						Add Admin to your Company
						</c:when>
						<c:otherwise>
							<form method="post" action="/InvoiceSystem/company/user"
								autocomplete="off">
								Add User to your Company

								<table>
									<tr>
										<td>Name</td>
										<td><input type="text" name="name" value="" /></td>
									</tr>
									<tr>
										<td>User-Id</td>
										<td><input type="text" name="userId" value="" /></td>
									</tr>

									</c:otherwise>
									</c:choose>

									<tr>
										<td>Password</td>
										<td><input type="password" name="password" /></td>
									</tr>




									<tr>
										<td>Email-Id</td>
										<td><input type="text" name="emailId" /> <br></td>
									</tr>

								</table>
								<input name="Submit" type="submit" value="Submit" /> <input
									name="Reset" type="reset" value="Reset" />
							</form>

							</center>
</body>
</html>

