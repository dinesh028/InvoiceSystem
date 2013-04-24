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
<jsp:include page="imageheader.jsp"></jsp:include>

	<center>
		<br>
		<br></br>

	
		<form method="post" action="/InvoiceSystem/user/save"
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

