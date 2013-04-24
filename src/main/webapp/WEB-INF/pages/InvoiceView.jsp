<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<script type="text/javascript">
	var g = 1;



	
	
	
</script>

</head>
<body>



	
	

	<jsp:include page="imageheader.jsp" />
	

	<div id='loadingmessage' style='display: none'>
		<img src='/InvoiceSystem/images/loading (1).gif' />
	</div>
	<c:if test="${invoice.recurring eq 1}">

		

		<center>
			<b><br>This was a Recurring Invoice<b></b>
		</center>
	</c:if>

<br>
	<table width="500" id="itemTable">
		<tr>

			<td>Invoice ID</td>
			<td><label name="invoiceid">${invoice.invoiceId}</label></td>
			
			
		</tr>
		<tr>

			<td>Invoice Status</td>
			<td><label name="status">${invoice.status}</label></td>
			
			
		</tr>
		<tr>
			<td>Client to be billed</td>
			<td><label>${invoice.client.name}(${invoice.client.emailId})</label></td>
		</tr>
	
		<tr>
			<td>Invoice Send Date</td>
			<td><label>${sessionScope.sendDate}</label></td>
		</tr>
		<tr>
			<td>Invoice Due Date</td>
			<td><label>${sessionScope.dueDate}</label></td>
		</tr>
		<%-- <tr><c:choose>
				<c:when test="${invoice.recurring eq 1}">
					<td>Invoice Sending Date</td>
					<td><input type="text" value="${sessionScope.sendDate}" readonly="true "/></td>
					
				</c:when>
				<c:otherwise>
					<td>Due Date</td>
					<td><input type="text" readonly="true"
						value="${sessionScope.date} "  />
					</td>
				</c:otherwise>
			</c:choose>








		</tr>
		<tr>
			<c:choose>
				<c:when test="${invoice.recurring eq 1}">
					<td>Due Day of Month</td>
					<td><input type="text" value="${sessionScope.date1}" readonly="true "/></td>
					
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose></tr>
 --%>







		
		
	</table>
	<div class="inputForm">
		<s:actionerror cssClass="errorMsg" />
		<s:form action="" method="post">
			<table>
				<tr>
					<td>

						<table>
							<tr>
								<td>Item Purchased
								</td>
							</tr>

							<tr>
								<td><div id="p_scents">
										<!-- <table  width="80%">
										<thead>
										<tr>
										<th >Item Name</th>
										<th >Quantity</th>
										<th >Price </th>
										<th >Amount</th>
								</tr>
									</thead>	
									</table> -->
										<table>
										
										
											<c:forEach items="${invoice.invoiceHistory}" var="inhist">
												<tr>
													<p>

													<input type="text" value="${inhist.item }" readonly="true" 
															size="20"/> 

														

														<input type="text" value="${inhist.quantity }" readonly="true" 
															size="20"/>

														<input type="text"
															 value=${inhist.price } readonly=true
															 > <input
															type="text" 
															value="${inhist.quantity * inhist.price}" readonly=true
															 >
													</p>
												</tr>
												
											</c:forEach>

										</table>
									</div></td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td>Taxable Amount</td>
											<td><input type="text" id="iAmount" name="iAmount"
												readonly="true" value=${invoice.amount
												} placeholder="Total Amount" size="20"></td>
										</tr>
										<tr>
											<td>Tax</td>
											<td><input type="text" id="iTax" name="iAmount"
												placeholder="Tax" value=${invoice.tax }  readonly=true size="20"
												onblur="calcTax()"></td>
										</tr>
										<tr>
											<td>Net Amount</td>
											<td><input type="text" id="iNet" name="iNet" value="${(invoice.amount * (invoice.tax + 100))/100}"
												readonly="true" placeholder="Total Amount" size="20">
											</td>
										</tr>
									</table></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
				</s:form>
	</div>
	
	
</body>

</html>