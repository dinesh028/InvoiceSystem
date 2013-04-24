<html>

<head>

<script src="/InvoiceSystem/js/jquery.js"></script>

<script type="text/javascript">

function updateItem() {
	if(confirm("Are you sure to delete item")){
	$.ajax({
		type : "GET",
		data : "itemId="+ $("#itemId").val()+"&name="+$("#name").val()+"&price="+$("#price").val(),
		url : "/InvoiceSystem/user/update/item/save",
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
		type : "POST",
		data: "itemId="+ $("#itemId").val(),
		url : "/InvoiceSystem/user/item/search/view",
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
		+ "<td><input type='text' id='itemId' disabled='disabled' value='" + item.itemId+ "'/></td></tr>"
		+ "<tr><td>Item Name</td>"
		+ "<td><input type='text' id='name' value='" + item.name+ "'/></td></tr>"
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



		
		Update Item<br>
		<t>
		<tr>
			<td>Item Id</td>
			<td><input type="text" id="itemId" /> <br></td>
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

