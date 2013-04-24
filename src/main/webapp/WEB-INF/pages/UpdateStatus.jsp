<html>
<%@ page import="com.invoice.model.*, java.util.List , java.util.Iterator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<script src="/InvoiceSystem/js/jquery.js"></script>
<script type="text/javascript">

function GoTo()
{
	window.document.location.href = "/InvoiceSystem/user/invoice/paid";
}


function changeStatus(id)
{
	
	alert("Your request processing please wait....");
	$.ajax( {
		type : "POST",
		data: "id="+ id,
		url : "/InvoiceSystem/user/invoice/update/paid",
		cache : false,
		success : function(msg) {
			
			alert(msg);
			GoTo();
				 
	    	      
	 
		
			
		},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
	
	
}

	</script>




</head>

<body>
<jsp:include page="imageheader.jsp"/>

	<center>
		<br>
		<br></br>


<!-- <table cellpadding="2"> -->
	
<%-- <%
List<Invoice> invoiceList = (List)session.getAttribute("invoiceList");
Iterator<Invoice> it = invoiceList.iterator();
if(!it.hasNext()){%>Sorry, No More Records Founds<%}else{
		while (it.hasNext()) {

			Invoice c = (Invoice)it.next();
			
			%>
			
			<tr><td width="5%"><%=c.getInvoiceId() %></td>
			<td width="5%"><%=c.getClient().getName() %></td>
			<td width="5%"><%=c.getClient().getEmailId()%></td>
			<td width="5%"><input id=<%=c.getInvoiceId() %> type="button" value="Paid" onclick="changeStatus(this.id)"/>
			</td></tr>
			<%}} %> --%>
 
 				
  
<c:choose>
<c:when test="${empty invoiceList}">
Sorry, No More Records Found 
</c:when>
<c:otherwise>
					 <table width='500' cellpadding="2">
					
			 <c:forEach items="${invoiceList}" var="invoice">                 
                <tr>
				<td width="25%">${invoice.invoiceId}</td>
			<td width="25%">${invoice.client.clientId}</td>
			<td width="25%">${invoice.client.name}</td>
			<td width="25%">${invoice.client.emailId}</td>
			<td width="25%"><input id=${invoice.invoiceId} type="button" value="Paid" onclick="changeStatus(this.id)"/></td>
			
			
                
                </c:forEach>


			
			   
  	</table>
</c:otherwise>
</c:choose>
	</center>
  	
	
	
	 	</center>
</body>
</html>

