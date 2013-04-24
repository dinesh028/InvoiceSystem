<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


<script language="javascript">
function GoTo()
{
	
window.document.location.href = "/InvoiceSystem";
}
/* function uploadFile()
{
	alert("here " +$("#file").val());
	/* $.ajax({
		type : "GET",
		url : "/InvoiceSystem/registration/searchall/client",
		cache : false,
		success : function(msg) {
			/* alert("here"); */
			//dropdownClient(msg);
		/* },
		error : function(e) {
			alert("1here");
			alert('Error: ' + e);
		}
	}); */
 
 
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="imageheader.jsp"/>
	<center>
		<br>
		<br>
		<br>

<form method="post" action="/InvoiceSystem/registration/client/xml/save"
			autocomplete="off" commandName="tenant" enctype="multipart/form-data">
			<table>
					<tr>
						<td>Upload XML</td>
						<td><input type="file" name="fileUpload" id="file"></td>
					</tr>

			</table>


			<input name="Submit" type="submit" value="Submit">
			 <input type="button" onclick="GoTo()" Value="Back" />
			
	</center>
</body>
</html>

