<html>
<%@ page import="com.invoice.model.*, java.util.List , java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	window.document.location.href = "/InvoiceSystem/user/invoice/update?id="+id;
}

/* 
function mailInvoice(id)
{
	
	alert("Your request processing please wait....");
	$.ajax( {
		type : "POST",
		data: "id="+ id,
		url : "/InvoiceSystem/registration/invoice/mail",
		cache : false,
		success : function(msg) {
			
			alert(msg);
			alert("Please wait while page is refreshing...")
				 
	    	      location.reload();
	 
		
			
		},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
	
	
}
 */

/* 
function scheduleInvoice(id)
{
	
	alert("Your request processing please wait....");
	$.ajax( {
		type : "POST",
		data: "id="+ id,
		url : "/InvoiceSystem/registration/rinvoice/update/status",
		cache : false,
		success : function(msg) {
			
			alert(msg);
			alert("Please wait while page is refreshing...")
				 
	    	      location.reload();
	 
		
			
		},
	 error: function(e){  
		 alert("Errror");
	      alert('Error: ' + e);  
	    }  
	});
	
	
}

 */



function deleteInvoice(id)
{
	/* alert("FDSF FDSF "+id); */
	
	
		$.ajax( {
			type : "POST",
			data: "id="+ id,
			url : "/InvoiceSystem/user/invoice/delete",
			cache : false,
			success : function(msg) {
				
				alert(msg);
				alert("Please wait while page is refreshing...")
					 
		    	      location.reload();
		 
			
				
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

		
	<!-- 	<table>
		<tr><td width="10%" >Invoice Id</td>
			<td width="10%">Name</td>
			<td width="10%">Email Id</td>
			<td width="10%"></td>
			<td width="10%"></td>
			<td width="10%"></td>
</tr>
</table> -->

    <%-- 
<%
List<Invoice> invoiceList = (List)session.getAttribute("invoiceList");
if(invoiceList==null){%>Sorry, No More Records FoundfFs<%
}
else{

Iterator<Invoice> it = invoiceList.iterator();
if(it.hasNext()==false){%>Sorry, No More Records Found<%}else{
		while (it.hasNext()) {

			Invoice c = (Invoice)it.next();
			
			%>
			<tr><td width="5%"><%=c.getInvoice_id() %></td>
			<td width="5%"><%=c.getClient().getName() %></td>
			<td width="5%"><%=c.getClient().getEmailid()%></td>
			<td width="5%"><input id=<%=c.getInvoice_id() %> type="button" value="Update" onclick="javascript:GoTo(this.id)"/></td>
			<td width="5%"> <input id=<%=c.getInvoice_id() %> type="button" value="Delete" onclick="deleteInvoice(this.id)"  /></td>
			
			<%if(c.getRecurring()==1) {%>
			<td width="5%"><input id=<%=c.getInvoice_id() %> type="button" value="Schedule" onclick="scheduleInvoice(this.id)"  /></td>
			<%}else{ %>
			<td width="5%"><input id=<%=c.getInvoice_id() %> type="button" value="Mail Invoice" onclick="mailInvoice(this.id)"  /></td>
			<%} %>
			
</tr>
		<%}} }%>  --%>
		
		
	
		 
			
  		<%-- 	<c:forEach var="inv" items="${sessionScope.invoiceList}" >
  <option><c:out value="${inv.invoice_id}"/></option>
</c:forEach> --%>

<c:choose>
<c:when test="${empty invoiceList}">
Sorry, No More Records Found 
</c:when>
<c:otherwise>
					 <table width='500' cellpadding="2">
					
			 <c:forEach items="${invoiceList}" var="invoice">                 
                <tr>
				<td width="5%">${invoice.invoiceId}</td>
			<td >${invoice.client.clientId}</td>
			<td >${invoice.client.name}</td>
			<td >${invoice.client.emailId}</td>
			<td ><input id=${invoice.invoiceId} type="button" value="Update" onclick="javascript:GoTo(this.id)"/></td>
			<td > <input id=${invoice.invoiceId} type="button" value="Delete" onclick="deleteInvoice(this.id)"  /></td>
			
                
                </c:forEach>


			
			   
  	</table>
</c:otherwise>
</c:choose>
	</center>
	
	
	<input type="hidden" id="id"/>
</body>
</html>

