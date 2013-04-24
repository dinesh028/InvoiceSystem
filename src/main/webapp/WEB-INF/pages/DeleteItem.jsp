<html>

<head>

<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
function deleteClient()
{
	
	if(confirm("Are you sure to delete item, all invoices in draft status will be deleted")){
		$.ajax( {
			type : "POST",
			data: "itemId="+ $("#itemId").val(),
			url : "/InvoiceSystem/user/delete/Item/view",
			cache : false,
			success : function(msg) {
				alert(msg);
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

		Delete Item

		<table>

			<tr>
				<td>Item Id</td>
				<td><input type="text" id="itemId" /> <br>
				</td>
			</tr>
		</table>
		<!-- <input name="Submit" type="submit" value="Submit" /> -->

		<input type="button" onclick="deleteClient()" name="Delete"
			value="Delete" id="Delete"> <input type="reset" value="Reset"
			id="reset" />
		<div id="id1"></div>
		</form>

	</center>
</body>
</html>

