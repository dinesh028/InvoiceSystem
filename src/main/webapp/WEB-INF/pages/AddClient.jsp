<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript">

	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<body>
<jsp:include page="imageheader.jsp"/>


	<center>
		<br> <br></br>



		<form method="post" action="/InvoiceSystem/user/client/add"
			autocomplete="off">


			Add Client

			<table>
			<tr>
					<td>Client-Id</td>
					<td><label name="client_id">${client.clientId}</label> 
						<input type="hidden" value="${client.clientId}" name="clientId" />
						<td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" />
					</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="emailId" /> <br>
					</td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="invoiceEmailId" /> <br>
					</td>
				</tr>
				
				<tr>
					<td>Company</td>
					<td><input type="text" name="companyName" />
					</td>
				</tr>
			</table>
			<input name="Submit" type="submit" value="Submit" /> <input
				name="Reset" type="reset" value="Reset" />
		</form>

	</center>
</body>
</html>

