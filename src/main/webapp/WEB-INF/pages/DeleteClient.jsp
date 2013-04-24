<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<script type="text/javascript">
function deleteClient()
{
	if(confirm("Are you sure to delete client, all invoices in draft status will be deleted")){
		
		$.ajax( {
			type : "POST",
			data: "clientId="+ $("#clientId").val(),
			url : "/InvoiceSystem/user/delete/Client/view",
			cache : false,
			success : function(msg) {
				alert(msg);
				$("#clientId").val('');
			},
		 error: function(e){  
		      alert('Error: ' + e);  
		    }  
		});

	}
}
	</script>
</head>
<body>

<jsp:include page="imageheader.jsp"/>

	<center>
		<br> <br></br>



		<!-- <form method="post" action="/InvoiceSystem/registration/deletion/client"
			autocomplete="off">
 -->

		Delete Client

		<table>

			<tr>
				<td>Client Id</td>
				<td><input type="text" id="clientId" /> <br>
				</td>
			</tr>
		</table>
		<!-- <input name="Submit" type="submit" value="Submit" /> -->

		<input type="button" onclick="deleteClient()" name="Delete"
			value="Delete" id="Delete"> <input type="reset" value="Reset"
			id="reset" />
		</form>

	</center>
</body>
</html>

