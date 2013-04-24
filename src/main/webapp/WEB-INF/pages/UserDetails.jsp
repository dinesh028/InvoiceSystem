<html>

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $("i").toggle("hide");
  });
});
</script>

</head>
<body>
<jsp:include page="imageheader.jsp"/>

	<center>
		<br>
		<br></br>

	
		<form method="post" action="/InvoiceSystem/user/profile/save"
			autocomplete="off">


			Your profile

			<table>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name"
						value="${sessionScope['login'].name}" /></td>
				</tr>
				<tr>
					<td>User-Id</td>
					<td><input type="text" readonly="true" name="userId"
						value="${sessionScope['login'].userId}" /></td>
				</tr>
				<tr>
					<td>Email-Id</td>
					<td><input type="text" name="emailId"
						value="${sessionScope['login'].emailId}" /> <br>
					</td>
				</tr>
				<%-- <tr>
			<td>Password</td>
			<td>
			<input type="password" name="password" value="${sessionScope['member'].password}"/>
			</td>
		</tr> --%>
			</table>
			<input name="Update" type="submit" value="Update" />

		</form>


	</center>
</body>
</html>

