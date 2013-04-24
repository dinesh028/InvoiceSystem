<html>
<%@ page import="com.invoice.model.*, java.util.List , java.util.Iterator" %>

<head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<script src="/InvoiceSystem/js/jquery.js"></script>
<script type="text/javascript">

function GoTo(id)
{
window.document.location.href = "/InvoiceSystem/user/invoice/view?id="+id;
}




/* function searchInvoiceCriteria()
{
	$.ajax( {
		type : "POST",
		data: "searchInvoice=" + $("#searchInvoice").val() + "&searchClient="
		+ $("#searchClient").val()+ "&searchItem="
		+ $("#searchItem").val(),
		url : "/InvoiceSystem/registration/invoicehistory/search/criteria",
		cache : false,
		success : function(msg) {
			$.each(msg, function(i, item) {
				alert("here1"+item.histId);
				alert("tax "+item.invoice.tax);
				 });
			
		},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
			
			
	
} */


/* 
function searchInvoiceCriteriaById()
{
	$.ajax( {
		type : "POST",
		data: "searchInvoice=" + $("#searchInvoice").val() + "&searchClient="
		+ $("#searchClient").val()+ "&searchItem="
		+ $("#searchItem").val(),
		url : "/InvoiceSystem/registration/invoice/search/criteria",
		cache : false,
		success : function(msg) {
			alert("here"+msg.invoice_id);
			//alert("here"+msg.invoicehistory.quantity);
			//alert("here"+msg.invoicehistory.price);
			 $.each(msg.invoiceHistory, function(i, item) {
				alert("here1");
				alert(item.price);
			}); 
		},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
			
			
	
}




function searchInvoiceCriteriaByName()
{
	$.ajax( {
		type : "POST",
		data: "searchInvoice=" + $("#searchInvoice").val() + "&searchClient="
		+ $("#searchClient").val()+ "&searchItem="
		+ $("#searchItem").val(),
		url : "/InvoiceSystem/registration/invoice/name/search/criteria",
		cache : false,
		success : function(msg) {
			/* alert("here"+msg.invoice_id);
			alert("here"+msg.invoicehistory.quantity); */
		/*	 $.each(msg, function(i, item) {
				alert("here1"+item.invoice_id);
				$.each(item.invoiceHistory, function(i, it) {
				alert(it.quantity);
				});
			}); 
		/*},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
			
			
	
}
 */



function searchInvoiceCriteriaByAll()
{
	$.ajax( {
		type : "POST",
		data: "searchInvoice=" + $("#searchInvoice").val() + "&searchClient="
		+ $("#searchClient").val()+ "&searchItem="
		+ $("#searchItem").val(),
		url : "/InvoiceSystem/user/invoice/search/criteria1",
		cache : false,
		success : function(msg) {
			/* $.each(msg, function(i, item) {alert("tax "+item.invoice.tax); */
				if(msg==null)
					alert("empty");
				else
					display(msg);
				/* alert("here1"+item.histId);*/
			},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
			
			
	
}


function display(data)
{
	
	
    var table = '<table align="center" width="95%">'+
    '<thead>'+
    '<tr hheight="44">'+
    '<th>Invoice Id</th>'+
    '<th>Client Name</th>'+
    '</tr>'+
    '</thead>';
 
                        $.each(data, function(i,item) {
        
    table += "<br/><tr align = center  style=height:30px >"
    + "<td >" + item.invoice.invoiceId + " </td>"
    + "<td>" + item.invoice.cname + "</td>"
    + "<td>" +"<input type=button value=View id="+item.invoice.invoiceId+" onclick=GoTo(this.id)>"+ "</td>"
    + "</td>"
    +"</tr>";
    });
    table += "</tbody></table>";                                                                                    
    $("#dynamicTable").html(table);
	//$("br").remove();
	$("#dynamicTable br").remove();
	
}	




</script>










</head>
<body>
<jsp:include page="imageheader.jsp"/>

	<center>
	<br>
	<input type="text" id="searchInvoice" value="" placeholder="Search by Invoice Id"/>
	 <input type="text" id="searchClient" value="" placeholder="Search by Client Id"/>
	 <input type="text" id="searchItem" value="" placeholder="Search by Item Id"/>
<!-- 	<input type="button" value="pens" onclick="searchInvoiceCriteria()"/>
	<input type="button" value="invoiceid" onclick="searchInvoiceCriteriaById()"/>
	<input type="button" value="Name" onclick="searchInvoiceCriteriaByName()"/> -->
	<input type="button" value="Go" onclick="searchInvoiceCriteriaByAll()"/>
	<p id="dynamicTable"></p>
	</center>		
	
	

</body>
</html>

