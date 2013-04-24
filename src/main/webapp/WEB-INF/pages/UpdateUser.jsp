<html>

<head>
<script src="/InvoiceSystem/js/jquery.js"></script>
<script type="text/javascript">

function updateUser() {
	
	if(confirm("Are you sure to update user")){
	$.ajax({
		type : "POST",
		data : "userId="+ $("#userId").val()+"&name="+$("#name").val()+"&emailId="+$("#emailId").val(),
		url : "/InvoiceSystem/user/update/save",
		cache : false,
		success : function(data) {
			alert(data);
			$("#dynamicTable").hide();
			$("t").show();
			
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
	}

}
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
	 $("t").hide();
	 $("#dynamicTable").show();
	var table = "<table>  "
		+ "<tr><td>Name</td>"
		+ "<td><input type='text' id='name'value='" + user.name+ "'/></td></tr>"
		+ "<tr><td>User-Id</td>"
		+ "<td><input type='text' id='userId'  readonly='true'  value='" + user.userId+ "'/></td></tr>"
		+ "<tr><td>Email-Id</td>"
		+ "<td><input type='text' id='emailId' value='" + user.emailId+ "'/></td></tr>";
		
		table += "</table><input type=button value='Update' onclick='updateUser()'/>";
		$("#dynamicTable").html(table);
			
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

		Update User<br>
		<t>
		<tr>
			<td>User-ID</td>
			<td><input type="text" id="userId" /> <br></td>
		</tr>
		<input type="button" onclick="searchUser()" name="search"
			value="Search"> </t>


		<center>
			<div id="dynamicTable"></div>
		</center>






		</div>
		</form>

	</center>
</body>
</html>

