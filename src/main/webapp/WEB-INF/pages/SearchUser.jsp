<html>

<head>
<script src="/InvoiceSystem/js/jquery.js"></script>

<script type="text/javascript">
function searchUser()
{
		
	
	$.ajax( {
		type : "GET",
		data: "userId="+ $("#userId").val(),
		url : "/InvoiceSystem/user/search/view",
		cache : false,
		success : function(data) {
			if(data.name==null)
				alert("No Record Found");
			else
				table(data);
			
			
		},
	 error: function(e){  
	      alert('Error: ' + e);  
	    }  
	});


	}
	
	
function table(user)
{
	var table = "<br><table width='150'>"
		+ "<tr><td>Name</td>"
		+ "<td>" + user.name+ "</td></tr>"
		+ "<tr><td>User-Id</td>"
		+ "<td>" + user.userId+ "</td></tr>"
		+ "<tr><td>Email-Id</td>"
		+ "<td>" + user.emailId+ "</td></tr>"
		+ "</tr>";
		
		table += "</table>";
		$("#dynamicTable").html(table);
			
}


</script>
</head>
<body>
<jsp:include page="imageheader.jsp"/>

	<center>
		<br> <br></br>


	Search User
<form action="">
		<table>

			<tr>
				<td>User Id</td>
				<td><input type="text" id="userId" /> <br>
				</td>
			</tr>
		</table>
		<!-- <input name="Submit" type="submit" value="Submit" /> -->

		<input type="button" onclick="searchUser()" name="Search"
			value="Search" id="id"> <input type="reset" value="Reset"
			id="reset" />



		<div id="dynamicTable" align="center"></div>



		</div>
		</form>

	</center>
</body>
</html>

