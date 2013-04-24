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
<jsp:include page="imageheader.jsp"/>

	<center>
		<br>
		<br></br>

		<%-- <font color="red">
<c:choose>
<c:when test="${empty member1.tenant_id}"></c:when>
<c:otherwise>Registration Successful</c:otherwise></c:choose>
</font>	
 --%>

		<form method="post" action="/InvoiceSystem/registration/user"
			autocomplete="off">


			Add User to your Company

			<table>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" value="" /></td>
				</tr>
				<tr>
					<td>EmailId/UserId</td>
					<td><input type="text" name="email" /> <br></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			</table>
			<input name="Submit" type="submit" value="Submit" /> <input
				name="Reset" type="reset" value="Reset" />
		</form>

	</center>
</body>
</html>

