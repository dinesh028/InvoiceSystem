<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>
<body>
<jsp:include page="imageheader.jsp"/>



	<center>
		<br> <br></br>



		<form method="post" action="/InvoiceSystem/user/item/add"
			autocomplete="off" commandName="item">


			Add Item

			<table>
				<tr>
					<td>Item-Id</td>
					<td><label name="item_id">${item.itemId}</label> 
						<input type="hidden" value="${item.itemId}" name="itemId" />
						<td>
				</tr>
				<tr>
					<td>Item Name</td>
					<td><input type="text" name="name" />
					</td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="text" name="price" />
					</td>
				</tr>
			</table>
			<input name="Submit" type="submit" value="Submit" /> <input
				name="Reset" type="reset" value="Reset" />
		</form>

	</center>
</body>
</html>

