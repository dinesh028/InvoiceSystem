<html>

<head>
<script src="/InvoiceSystem/js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>


<script type="text/javascript">

function updateItem() {

	$.ajax({
		type : "GET",
		data : "id="+ $("#id").val()+"&name="+$("#name").val()+"&quantity="+$("#quantity").val()+"&price="+$("#price").val(),
		url : "/InvoiceSystem/registration/updating/item",
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
function searchItem()
{
	
	
	$.ajax( {
		type : "POST",
		data: "id="+ $("#id").val(),
		url : "/InvoiceSystem/registration/search/item",
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


function table(item)
{
	 $("t").hide();
	 $("#dynamicTable").show();
	var table = "<table>  "
		+ "<tr><td>Item Id</td>"
		+ "<td><input type='text' id='id' disabled='disabled' value='" + item.id+ "'/></td></tr>"
		+ "<tr><td>Item Name</td>"
		+ "<td><input type='text' id='name' value='" + item.name+ "'/></td></tr>"
		+ "<tr><td>Item Quantity</td>"
		+ "<td><input type='text' id='quantity'  value='" + item.quantity+ "'/></td></tr>"
		+ "<tr><td>Item Price</td>"
		+ "<td><input type='text' id='price'  value='" + item.price+ "'/></td></tr>"
		+ "</tr>";
		
		table += "</table><input type=button value='Update' onclick='updateItem()'/>";
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

		Update Item<br>
		<t>
		<tr>
			<td>Item Id</td>
			<td><input type="text" id="id" /> <br></td>
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

