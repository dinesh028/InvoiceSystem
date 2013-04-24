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
<jsp:include page="imageheaderComp.jsp"/>

	<center>
		<br>
		<br></br>

		<%-- <font color="red">
<c:choose>
<c:when test="${empty member1.tenant_id}"></c:when>
<c:otherwise>Registration Successful</c:otherwise></c:choose>
</font>	
 --%>

		<form method="post" action="/InvoiceSystem/company/admin"
			autocomplete="off">


			Add Admin to your Company

			<table>
			<tr>
					<td>User-Id</td>
					<td><input type="text" name="userId" value="" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" value="" /></td>
				</tr>
				<tr>
					<td>Email-Id</td>
					<td><input type="text" name="emailId" /> <br></td>
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

