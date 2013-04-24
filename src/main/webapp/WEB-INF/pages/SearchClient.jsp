<html>

<head>
<script src="/InvoiceSystem/js/jquery.js"></script>

<script type="text/javascript">
function searchClient()
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
	 var table = "<br><table width=300 >"
		+ "<tr><td>Client-Id</td>"
		+ "<td>" + client.clientId+ "</td></tr>"
		+ "<tr><td>Name</td>"
		+ "<td>" + client.name+ "</td></tr>"
		+ "<tr><td>Company</td>"
		+ "<td>" + client.companyName+ "</td></tr>"
		+ "<tr><td>Email-Id</td>"
		+ "<td>" + client.emailId+ "</td></tr>"
		+ "<tr><td>Invoice Email-Id</td>"
		+ "<td>" + client.invoiceEmailId+ "</td></tr>"
		+ "</tr>";
		
		table += "</table>";
		$("#dynamicTable").html(table);
			 
}

	/* 
function table(client)
{
	var table = "<table id = stockTable align = center width=90% ><thead> <tr align=center>"
		+ "<th>Client Id</th>"
		+ "<th>Name</th>"
		+ "<th>Company Name </th>"
		+ "</tr></thead<tbody>";
		table += "<br/><tr align = center height=40px>"
		+ "<td>" + client.email+ "</td>"
		+ "<td>" + client.name + " </td>"
		+ "<td>" + client.companyname + "</td>"
		+"</tr>";
		table += "</tbody></table>";
		$("#dynamicTable").html(table);
			
} */
</script>
</head>
<body>
<jsp:include page="imageheader.jsp"/>

	<center>
		<br> <br></br>



		Search Client

		<table>

			<tr>
				<td>Client Id</td>
				<td><input type="text" id="clientId" /> <br>
				</td>
			</tr>
		</table>
		<!-- <input name="Submit" type="submit" value="Submit" /> -->

		<input type="button" onclick="searchClient()" name="Search"
			value="Search" id="id"> <input type="reset" value="Reset"
			id="reset" />



		<div id="dynamicTable" align="center"></div>



		</div>
		</form>

	</center>
</body>
</html>

