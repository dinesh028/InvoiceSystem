<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="/InvoiceSystem/js/jquery.js"></script>


<script type="text/javascript">
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
	var table = "<br><table  width='150'>"
		+ "<tr><td>Item Id</td>"
		+ "<td>" + item.itemId+ "</td></tr>"
		+ "<tr><td>Item Name</td>"
		+ "<td>" + item.name+ "</td></tr>"
		+ "<tr><td>Item Price</td>"
		+ "<td>" + item.price+ "</td></tr>"
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



		Search Item

		<table>

			<tr>
				<td>Item Id</td>
				<td><input type="text" id="itemId" /> <br>
				</td>
			</tr>
		</table>
		<!-- <input name="Submit" type="submit" value="Submit" /> -->

		<input type="button" onclick="searchItem()" name="Search"
			value="Search"> <input type="reset" value="Reset" id="reset" />



		<div id="dynamicTable"></div>



		</div>
		</form>

	</center>
</body>
</html>

