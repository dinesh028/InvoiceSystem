<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.5.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
.ui-datepicker {
	font-size: 9pt !important;
}
</style>
<script language="javascript">
function GoTo()
{
	window.document.location.href = "/InvoiceSystem";
}
$(function() {
	$('#startdatepicker').datepicker({ dateFormat: $("#dateF").val().toLowerCase() }).val();
});
$(function() {
	$('#enddatepicker').datepicker({ dateFormat: $("#dateF").val().toLowerCase() }).val();
});
function saveOption()
{
	
	if($("#thankyou").is(":checked"))
	{
		$("#thankyou").val(1);
	}

	if($("#reminder").is(":checked"))
	{
		$("#reminder").val(1);
	}
	
	 $.ajax( {
		type : "POST",
		data: "reminder="+ $("#reminder").val()+"&currencyFormat="+ $("#currencyFormat").val()+"&thankyou="+ $("#thankyou").val()+"&dateFormat="+ $("#dateFormat").val(),
		url : "/InvoiceSystem/user/settings/save",
		cache : false,
		success : function(data) {
			alert("Saved Successfully");
			
			
		},
	 error: function(e){  
	      alert('Error: ' + e);  
	    }  
	});
 

	}
	function searchExcel()
	{
		
		
		$.ajax( {
			type : "GET",
			data: "startDate="+$('#startdatepicker').val()+"&endDate="+ $("#enddatepicker").val(),
			url : "/InvoiceSystem/user/invoice/export/view",
			cache : false,
			success : function(data) {
				alert(data);
				
				
			},
		 error: function(e){  
		      alert('Error: ' + e);  
		    }  
		});
	 
		
		
		
	}
	function changeLogo()
	{
		
		alert($('#fileUpload').val());
		$.ajax( {
			type : "POST",
			data: "fileUpload="+$('#fileUpload').val(),
			url : "/InvoiceSystem/user/logo/save",
			cache : false,
			success : function(data) {
				alert(data);
				
				
			},
		 error: function(e){  
		      alert('Error: ' + e);  
		    }  
		});
	 
		
		
		
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="imageheader.jsp"></jsp:include>
	<center>
		<br>
		<br>
		<br>






<c:choose>
				<c:when test="${excel eq 1}">
				Download Invoice Summary in excel
			
				</td>
				<form method="get" action="/InvoiceSystem/user/invoice/export/view"
			autocomplete="off" >
      			<input type="text" id="startdatepicker" name="startDate" readonly="true"  placeholder="Start Date" />
      			<input type="text" id="enddatepicker" name="endDate" readonly="true"  placeholder="End Date" />
					<input type="submit" value="Go" />
					<input type="reset" value="Reset" />
					</form>
				</c:when>
				<c:when test="${excel eq 2}">
				<form method="post" action="/InvoiceSystem/user/logo/save"
			 enctype="multipart/form-data">
					<table>
					<tr>
						<td>Upload Company Logo</td>
						<td><input type="file" name="fileUpload">
						</td>
					</tr>
			</table>
			<input type="submit" value="Submit" />
					<input type="reset" value="Reset" />
			</form>
			
				</c:when>
				<c:otherwise>
					

		<table>
			<tr>
				<td>Reminder Mail Service</td> 
					<td><input type="checkbox" id="reminder" name="reminder" value="0"  ${tenantInfo.reminder == '1' ? 'checked' : ''}></td>
					</tr>
					<tr>
						<td>Thank You Mail Service</td>
						<td><input type="checkbox" id="thankyou" name="thankyou" value="0"  ${tenantInfo.thankyou == '1' ? 'checked' : ''}></td>
					</tr>
					<tr>
						<td>Date Format</td>
						<td>
						<select name="dateFormat" id="dateFormat">
 							 <option value="dd-MM-y" ${tenantInfo.dateFormat == 'dd-MM-y' ? 'selected' : ''}>DD-MM-YY</option>
  							 <option value="dd-MM-yy" ${tenantInfo.dateFormat == 'dd-MM-yy' ? 'selected' : ''}>DD-MM-YYYY</option>
  							<option value="y-MM-dd" ${tenantInfo.dateFormat == 'y-MM-dd' ? 'selected' : ''}>YY-MM-DD</option>
  							 <option value="yy-MM-dd" ${tenantInfo.dateFormat == 'yy-MM-dd' ? 'selected' : ''}>YYYY-MM-DD</option>
						</select> 
						</td>
					</tr>
					<tr>
						<td>Currency Format</td>
						<td>
						<select name="currencyFormat" id="currencyFormat">
 							 <option value="hi,IN" ${tenantInfo.currencyFormat == 'hi,IN' ? 'selected' : ''}>India</option>
  							<option value="en,US" ${tenantInfo.currencyFormat == 'en,US' ? 'selected' : ''}>USA</option>
  							 <option value="pt,BR" ${tenantInfo.currencyFormat == 'pt,BR' ? 'selected' : ''}>Brazil</option>
  							 <option value="da,DK" ${tenantInfo.currencyFormat == 'da,DK' ? 'selected' : ''}>Denmark</option>
  							 <option value="it,IT" ${tenantInfo.currencyFormat == 'it,IT' ? 'selected' : ''}>Italy</option>
  							
						</select> 
						</td>
					</tr>
					
					
			</table>
	<input name="Submit" type="submit" value="Submit" onclick="saveOption()" />
	 <input type="button" onclick="javascript:GoTo()" Value="Back" />
	 		</form>
	 		
	 		</c:otherwise>
			</c:choose>
	</center>
	<input type="hidden" id="dateF" value=${sessionScope.login.tenant.dateFormat}  />
</body>
</html>

