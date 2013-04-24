<html>

<head>

<script src="/InvoiceSystem/js/jquery.js"></script>

<script type="text/javascript">

function updateClient() {
	if(confirm("Are you sure to update client")){
	$.ajax({
		type : "POST",
		data : "clientId="+ $("#clientId").val()+"&name="+$("#name").val()+"&emailId="+$("#emailId").val()+"&invoiceEmailId="+$("#invoiceEmailId").val()+"&companyName="+$("#companyName").val(),
		url : "/InvoiceSystem/user/update/client/save",
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
function searchItem()
{
	
	$.ajax( {
		type : "GET",
		data: "clientId="+ $("#clientId").val(),
		url : "/InvoiceSystem/user/client/search/view",
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


function table(client)
{
	 $("t").hide();
	 $("#dynamicTable").show();
	var table = "<table>  "
		+ "<tr><td>Client-Id</td>"
		+ "<td><input type='text' id='clientId' readonly='true' value='" + client.clientId+ "'/></td></tr>"
		+ "<tr><td>Name</td>"
		+ "<td><input type='text' id='name' value='" + client.name+ "'/></td></tr>"
		+ "<tr><td>Email-Id</td>"
		+ "<td><input type='text' id='emailId'  value='" + client.emailId+ "'/></td></tr>"
		+ "<tr><td>Invoice Email-Id</td>"
		+ "<td><input type='text' id='invoiceEmailId'  value='" + client.invoiceEmailId+ "'/></td></tr>"
		+ "<tr><td>Company Name</td>"
		+ "<td><input type='text' id='companyName'  value='" + client.companyName+ "'/></td></tr>"
		+ "</tr>";
		
		table += "</table><input type=button value='Update' onclick='updateClient()'/>";
		$("#dynamicTable").html(table);
			
}	
		

</script>
</head>
<body>
<jsp:include page="imageheader.jsp"/>

	<center>
		<br> <br></br>



	
		Update Client<br>
		<t>
		<tr>
			<td>Email ID</td>
			<td><input type="text" id="clientId" /> <br></td>
		</tr>
		<input type="button" onclick="searchItem()" name="search"
			value="Search"> </t>


		<center>
			<div id="dynamicTable"></div>
		</center>






		</div>
		</form>

	</center>
</body>
</html>

