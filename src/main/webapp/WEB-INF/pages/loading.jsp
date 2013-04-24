<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
<script src="http://code.jquery.com/jquery-1.5.1.js"></script>
<script type="text/javascript" src="/InvoiceSystem/js/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript" src="/InvoiceSystem/js/jquery.mockjax.js"></script>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
.ui-datepicker {
	font-size: 9pt !important;
}
</style>
 <style type="text/css">
 
.modal {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      100%;
    background: rgba( 255, 255, 255, .8 ) 
                url('http://localhost:8081/InvoiceSystem/images/loading3.gif') 
                50% 50% 
                no-repeat;
}


body.loading {
    overflow: hidden;   
}

body.loading .modal {
    display: block;
}
  </style>
  


<script type="text/javascript">//<![CDATA[ 
$(window).load(function(){
$("body").on({
    // When ajaxStart is fired, add 'loading' to body class
    ajaxStart: function() { 
        $(this).addClass("loading"); 
    },
    // When ajaxStop is fired, rmeove 'loading' from body class
    ajaxStop: function() { 
        $(this).removeClass("loading"); 
    }    
});

// Initiates an AJAX request on click



// http://code.appendto.com/plugins/jquery-mockjax
$.mockjax({ url: '/mockjax', responseTime: 2000 });

});//]]>  

</script>

<script type="text/javascript">
	var g = 1;
	function GoTo() {

		window.document.location.href = "/InvoiceSystem/registration/profile/user";
	}
	var nameG;
	var count = 0;

	$(function() {
	//	alert("in date format"+$("#dateF").val());
	//	$('#datepicker').datepicker({ dateFormat: 'dd-MM-yy' }).val();
		$('#datepicker').datepicker({ dateFormat: $("#dateF").val().toLowerCase() }).val();
	});

	$(document).ready(function() {

		$('#remScnt').hide();
	});

	$(document).ready(function() {

		$.ajax({
			type : "GET",
			url : "/InvoiceSystem/registration/searchall/client",
			cache : false,
			success : function(msg) {
				/* alert("here"); */
				dropdownClient(msg);
			},
			error : function(e) {
				alert("1here");
				alert('Error: ' + e);
			}
		});
	});
	
	$(document).ready(function() {

		/* alert("entering"); */
		$.ajax({
			type : "GET",
			url : "/InvoiceSystem/registration/searchall/item",
			cache : false,
			success : function(msg) {
				/* alert("Printing Item Name"+msg); */
				nameG = msg;
				dropdownItem(msg);
			},
			error : function(e) {
				alert('Error: ' + e.responseText);
			}
		});
	});

	function dropdownItem(data) {
		rowCount = 0;
		var table = '<select id = itemname_0 onchange=selectChange(0)>';
		table += "<option value='0'>-----Select------</option>";
		$.each(data, function(i, item) {
			table += "<option value='"+item.name+"'>" + item.name + "</option>";
			rowCount++;
		});
		table += "</select>";

		$("#dynamicDropDown1").html(table);
	}
	function duplicateCheck(w) {
	var itemname = "itemname_" + "" + w;
		var searchitem;
		for ( var q = 0; q <= g; q++) {
			if (q == w) {
			} else {
				searchitem = "itemname_" + "" + q;
				if ($("#" + itemname).val() == $("#" + searchitem).val()) {
					/* alert($("#" + itemname).val() + $("#" + searchitem).val()); */
					alert("Item is already selected, Please choose different Item");

					return 1;
					break;
				}

			}
		}

	}

	function selectChange(i) {
		
		var itemname_ = 'itemname_';
		var search = itemname_ + "" + i;
		var searchPrice = "itemPrice_" + "" + i;
		var searchQuantity = "itemQuantity_" + "" + i;
		var searchAmount = "totalAmount_" + "" + i;

		/* alert(searchPrice);
		alert("Search is "+search);//itemname_0 */

		var itemName = $("#" + search).val();
		/* alert("item list is "+itemName+$("#itemname_1").val()); */
		if (itemName == 0) {
			if ($("#iAmount").val() == '') {
			} else {

				var oldAmount = $("#" + searchAmount).val();
				$("#iAmount").val($("#iAmount").val() - parseInt(oldAmount));
				if ($("#iAmount").val() == 0) {/* alert("he"); */
					$("#iAmount").val('');
					$("#iNet").val('');
				}
			}
			$('#' + searchQuantity).attr('readonly', 'readonly');
			$('#' + searchQuantity).val('');
			$("#" + searchPrice).val('');
			$("#" + searchAmount).val('');

		} else if (itemName != 0) {
			/* alert("here"); */
			$('#' + searchQuantity).removeAttr('readonly');
			var result = duplicateCheck(i);
			if (parseInt(result) != 1) {
				$.ajax({
					type : "GET",
					url : "/InvoiceSystem/registration/search/price",
					data : "itemname=" + $("#" + search).val(),
					cache : false,
					success : function(msg) {

						$("#" + searchPrice).val(msg);
						amountCalc(i);
						/* $("#"+searchAmount).val(($("#"+searchPrice).val(msg)*$("#"+searchQuantity).val())); */

						if ($("#iAmount").val() == '') {
							$("#iAmount").val('');
							$("#iNet").val('');
						}
					},
					error : function(e) {
						alert('Error: ' + e.responseText);
					}
				});
			} else {
				if (parseInt(result) != 1) {
					/* alert("in this"); */
					/* alert("hie"); */
					$('#' + searchQuantity).attr('readonly', 'readonly');
					$('#' + searchQuantity).val('');
					$("#" + searchPrice).val('');

					/* $("#"+searchAmount).val(''); */
					/* var isum=0;
					/* alert(""+$("#"+searchAmount).val()); */
					/*if(!$("#"+searchAmount).val()=='')
						 $("#iAmount").val($("#iAmount").val()-$("#"+searchAmount).val());
					$("#"+searchAmount).val('');  */
				} else {

					/* alert(i); */
					/* $("#itemname_0").val(''); */
					
					searchPrice = "itemPrice_" + "" + i;
					searchQuantity = "itemQuantity_" + "" + i;
					itemname = "itemname_" + "" + i;
					$('#' + itemname).val('');
					$('#' + searchQuantity).attr('readonly', 'readonly');
					$('#' + searchQuantity).val('');
					amountCalc(i);
					
					$("#" + searchPrice).val('');
					
					/* alert(i); */
					/*if ($('#' + searchQuantity).val() == '') {
						alert("in");
						$('#' + itemname).val('');
						/* $('#' + itemname).val(1); */
					/*} else {

					}*/
				}
			}
		}
	}

	function saveDetails() {
		/* alert("in her"); */
		var invoiceId;
		var due_date;
		var total_amount;
		var customer;
		total_amount = $("#iNet").val();
		customer = $("#clientname").val();
		/* alert("ss" + total_amount + customer); */
		due_date = $("#datepicker").val();
		/* alert(due_date); */

		$.ajax({
			type : "GET",
			url : "/InvoiceSystem/registration/invoice/save",
			data : "total_amount=" + $("#iAmount").val() + "&customer="
					+ $("#clientname").val() + "&due_date="
					+ $("#datepicker").val()+"&tax="
					+ $("#iTax").val()+ "&id=" + $("#iID").val()+"total_amount=" + $("#fItem").val() +  "&quantity=" + $("#fQuantity").val() + "&amount=" + $("#fAmount").val()+ "&id=" + $("#iID").val() + "&price=" + $("#fPrice").val(),
			cache : false,
			success : function(msg) {
				alert(msg);
				
			},
			error : function(e) {
				alert('Error: ' + e.responseText);
			}
		});
	}
	
	
		
	
	function mSave(status)
	{
		
		
		var itemname = "itemname_" + "" + 0;
		var searchQuantity = "itemQuantity_" + "" + 0;
		var searchAmount = "totalAmount_" + "" + 0;
		var fPrice='';
		var fItem='';var fQuantity='';var fAmount='';
		
		
		 for(var q=0; q<g; q++)
			{			
				searchAmount = "totalAmount_" + "" + q;searchQuantity = "itemQuantity_" + "" + q;itemname="itemname_" + "" +q;
				searchPrice="itemPrice_" + "" +q;
				/* alert($("#"+searchPrice).val()); */
				if($("#"+searchAmount).val()>=1)
					{
					
					fAmount = fAmount + $("#"+searchAmount).val() +",";
					fQuantity = fQuantity + $("#"+searchQuantity).val() +",";
					fItem = fItem + $("#"+itemname).val() +",";
					fPrice = fPrice + $("#"+searchPrice).val() +",";
					/* alert("Checking "+fPrice); */
					}
				else{}
					
			}
		
		searchAmount = "totalAmount_" + "" + 0;
		
		$(document).on("click", function(){
		    $.post("/mockjax");        
		});
		
		$("#fItem").val(fItem);$("#fQuantity").val(fQuantity);$("#fAmount").val(fAmount);$("#fPrice").val(fPrice);
		$("#status").val(status);
		$.ajax({
			type : "POST",
			url : "/InvoiceSystem/registration/invoice/save",
			data : "total_amount=" + $("#iAmount").val() + "&customer="
					+ $("#clientname").val() + "&due_date="
					+ $("#datepicker").val()+"&tax="
					+ $("#iTax").val()+ "&id=" + $("#iID").val()+ "&quantity=" + $("#fQuantity").val() +  "&price=" + $("#fPrice").val()
					+"&fAmount=" + $("#fAmount").val()+"&fItem=" + $("#fItem").val()+"&status=" + $("#status").val()+"&recurring=" + $("#recurring").val(),
					
					 
			cache : false,
			success : function(msg) {
			//	$.mockjax({ url: '/mockjax', responseTime: 2000 });
				//GoTo();
			/* 	if(msg==1)
				{
					alert("Invoice Mailed Successfully");
					GoTo();
				}
				else if(msg==3)
					{
						alert("Invoice is Scheduled Successfully");
						GoTo();
					}
				else
					alert("Invoice Saved Successfully"); */
				
			},
			error : function(e) {
				alert('Error: ' + e.responseText);
			}
		});
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function amountCalc(i) {
		var itemname = "itemname_" + "" + i;
		var aq;
		var searchPrice = "itemPrice_" + "" + i;
		var searchQuantity = "itemQuantity_" + "" + i;
		var searchAmount = "totalAmount_" + "" + i;
		var oldAmount = 0;
		var newAmount = 0;
		$.ajax( {
			type : "GET",
			url : "/InvoiceSystem/registration/search/quantity",
			data: "itemname="+ $("#"+itemname).val(),
			cache : false,
			success : function(msg) {
				/* var fsearch=parseInt(msg); */
			
				/* alert("kk");
				alert(parseInt($("#"+searchQuantity).val()));
				alert(msg);
				alert("Msg "+parseInt(msg));
				 alert("Vd "+$("#"+searchQuantity).val());*/ 
							if(parseInt($("#"+searchQuantity).val())>parseInt(msg))			
							{	alert("Maximum Quantity available is"+msg);
							
								$("#"+searchQuantity).val(msg);
								amountCalc(i);
								return;
								
							}
				
					},
					 error: function(e){  
					      alert('Error: ' + e.responseText);  
					    }  
					});
		
		if ($("#" + searchAmount).val() == '') {
			oldAmount = parseInt(0);/* $("#"+searchAmount).val(0); */
		} else {
			oldAmount = $("#" + searchAmount).val();
		}
		if ($("#iAmount").val() == '') {
			$("#iAmount").val(0);
		} else {
			/* oldAmount=$("#"+searchAmount).val(); */
		}
		newAmount = $("#" + searchPrice).val() * $("#" + searchQuantity).val();
		/* alert("oldAmount"+oldAmount+"NewAMount"+newAmount); */
		$("#" + searchAmount).val(newAmount);
		/* alert("V"+parseInt(newAmount-oldAmount)); */
		var iAmount = $("#iAmount").val();
		var iA = parseInt(iAmount) + parseInt(newAmount - oldAmount);
		/* alert("Va"+iA); */
		/*$("#iAmount").val($("#iAmount").val()+parseInt(newAmount)-parseInt(oldAmount)); */
		$("#iAmount").val(iA);
		calcTax();
		if ($("#" + searchQuantity).val() == '') {
			$("#" + searchAmount).val('');
			if ($("#iAmount").val() == 0) {
				$("#iAmount").val('');
				$("#iNet").val('');
			}
		}

	}

	function removeCall(k) {
		/* alert(k); */
		/* 	i--;  */
		/* alert(i); */

		var searchAmount = "totalAmount_" + "" + k;

		if (!$("#" + searchAmount).val() == '') {
			$("#iAmount")
					.val($("#iAmount").val() - $("#" + searchAmount).val());
			calcTax();
			if ($("#iAmount").val() == 0)
				$("#iAmount").val('');
		}
	}

	function dropdownClient(data) {
		
		rowCount = 0;
		var table = '<select id=clientname>';

		table += "<option value='1'>-----Select------</option>";
		/* <option value=''>-----Select-----</option> */

		$.each(data, function(i, client) {
			table += "<option value='"+client.emailid+"'>" + client.name+" ( "+client.emailid+" ) " + "</option>";
			rowCount++;
		});
		table += "</select>";

		$("#dynamicDropDown").html(table);
	}

	function calcTax() {
		if ($("#iTax").val() == '') {

		} else {
			if ($("#iAmount").val() != '') {
				/* alert("here"); */
				var initial = parseFloat($("#iTax").val()) + parseInt(100);
				$("#iNet").val(parseFloat(parseFloat(initial) * ($("#iAmount").val() / 100)).toFixed(2));
				if ($("#iNet").val() == 0)
					$("#iNet").val('');
				/* alert(parseInt($("#iTax").val())+parseInt(100)*$("#iAmount").val()); */
				/* alert((parseInt(initial)*$("#iAmount").val())/100); */
			}

		}

	}
	
	function iHistory()
	{
		
		
		alert("going"+saveDetails());
		var itemname = "itemname_" + "" + 0;
		var searchQuantity = "itemQuantity_" + "" + 0;
		var searchAmount = "totalAmount_" + "" + 0;
		
		var fPrice='';
		var fItem='';var fQuantity='';var fAmount='';
		
		/* for(var q=0; q<g; q++)
		{			
			itemname="itemname_" + "" +q;
			if($("#"+itemname).val()!=1)
				fItem = fItem + $("#"+itemname).val() +",";
			else
				alert("in");
		}	
		 for(var q=0; q<g; q++)
		{			
			searchQuantity = "itemQuantity_" + "" + q;
			if($("#"+searchQuantity).val()>=1)
				{alert("F");
				fQuantity = fQuantity + $("#"+searchQuantity).val() +",";
				}
			else
				alert("in");
		}  */
		 for(var q=0; q<g; q++)
			{			
				searchAmount = "totalAmount_" + "" + q;searchQuantity = "itemQuantity_" + "" + q;itemname="itemname_" + "" +q;
				searchPrice="itemPrice_" + "" +q;
				/* alert($("#"+searchPrice).val()); */
				if($("#"+searchAmount).val()>=1)
					{
					
					fAmount = fAmount + $("#"+searchAmount).val() +",";
					fQuantity = fQuantity + $("#"+searchQuantity).val() +",";
					fItem = fItem + $("#"+itemname).val() +",";
					fPrice = fPrice + $("#"+searchPrice).val() +",";
					/* alert("Checking "+fPrice); */
					}
				else{}
					
			}
		/* alert($("#"+searchPrice).val()); */
		searchAmount = "totalAmount_" + "" + 0;
		/* alert("Values are "+fItem); */
		/* alert("Values are "+fQuantity); */
		/* alert("Values are "+fAmount); */
		$("#fItem").val(fItem);$("#fQuantity").val(fQuantity);$("#fAmount").val(fAmount);$("#fPrice").val(fPrice);
		/* alert("Prce is "+$("#fPrice").val()); */
		$.blockUI();
		 $('#loadingmessage').show();
		
		$.ajax({
			type : "GET",
			url : "/InvoiceSystem/registration/invoicehistory/save",
			data : "total_amount=" + $("#fItem").val() +  "&quantity=" + $("#fQuantity").val() + "&amount=" + $("#fAmount").val()+ "&id=" + $("#iID").val() + "&price=" + $("#fPrice").val(),
			cache : false,
			success : function(msg) {
				
				
				$.ajax({
					type : "GET",
					url : "/InvoiceSystem/registration/invoice/status",
					data : "id=" + $("#iID").val(),
					cache : false,
					success : function(msg) {
						 $.unblockUI();
						 $('#loadingmessage').hide();
						alert("Invoice Mailed Successfully");
						
						
					},
					error : function(e) {
						alert("");
						GoTo();
					}
				});
					
				
				
				
			},
			error : function(e) {
				alert("Invoice didn't Mail Successfully, try after some time");
				GoTo();
			}
		});
			
		
	}
	
	/* function cHist(search)
	{
		 for(var q=0; q<g; q++)
			{			
				search = "totalAmount_" + "" + q;
				if($("#"+searchAmount).val()>=1)
					{alert("F");
					fAmount = fAmount + $("#"+searchAmount).val() +",";
					}
				else
					alert("in");
			}
		
	} */
	
	
	function changeDate(id)
	{
		$("#dateD").val($("#dueDate").val());
		
	}
	
	
	
	
	
	
	
</script>
<script type="text/javascript">
	$(window)
			.load(
					function() {
						$(function() {
							var scntDiv = $('#p_scents');
							var i = $('#p_scents p').size() + 1;

							$('#addScnt')
									.live(
											'click',
											function() {

												var table = '<select id=itemname_' + g + ' value= 1 name = itemname_' + g + ' onchange=selectChange('+ g + ')>';
												table += "<option value='0'>-----Select------</option>";
												$
														.each(
																nameG,
																function(g,
																		item) {
																	table += "<option value='"+item.name+"'>"
																			+ item.name
																			+ "</option>";

																});

												$(
														'<p id='+g+'>'
																+ table
																+ '<input type="text" readonly="true" onblur="amountCalc('
																+ g
																+ ')" id="itemQuantity_'
																+ g
																+ '"name="itemQuantity_'
																+ g
																+ '" size="20" placeholder="Quantity"> <input type="text" readonly="true" id="itemPrice_' + g +'" name="itemPrice_' + g +'" size="20" placeholder="Price per Item"> <input type="text" readonly="true" id="totalAmount_' +g +'" name="totalAmount_' + g +'" size="20" placeholder="Total Amount"> <input id="remScnt" type="button" name = "'+g+'" value="Remove"></p>')
														.appendTo(scntDiv);
												i++;
												g++;
												return false;
											});

							$('#remScnt').live('click', function() {
								if (i > 1) {
									removeCall(this.name);

									$(this).parents('p').remove();
									i--;
								}
								return false;
							});
						});

					});
</script>

</head>
<body>
<jsp:include page="imageheader.jsp"/>


	<div id='loadingmessage' style='display: none'>
		<img src='/InvoiceSystem/images/loading (1).gif' />
	</div>
	<c:if test="${invoice.recurring eq 1}">
	
<br>

			<center>This is a Recurring Invoice</center>
		</c:if>
	
	
	<table id="itemTable">
		<tr>

			<td>Invoice ID</td>
			<td><label name="invoiceid">${invoice.invoice_id}</label>
			</td>
			<input type="hidden" id="iID" value="${invoice.invoice_id}" name="ID" />
			<br>
		</tr>
		<tr>
			<td>Client to be billed</td>
			<td><div id="dynamicDropDown"></div>
			</td>
		</tr>

		<tr>
			
			<c:choose>
      		<c:when test="${invoice.recurring eq 1}">
      		<td>Due Day of Month</td>
      		<td>	<select id="datepicker" onchange="changeDate(this.id)">
					<option value="0">-----Select----- </option>
    				<c:forEach begin="01" end="31" var="val">
    				
    				
    				
    				
    						<option value="${val}"> ${val} </option>
					</c:forEach>
    			</select></td>
    		 </c:when>
		     <c:otherwise>
		     <td>Due Date</td>
      			<td><input type="text" id="datepicker" readonly="true"
						placeholder="Due Date" /></td>
      		</c:otherwise>
			</c:choose>
			
			
		</tr>
	</table>
	<div class="inputForm">
		<s:actionerror cssClass="errorMsg" />
		<s:form action="" method="post">
			<table>
				<tr>
					<td>

						<table>
							<tr>
								<td><input id="addScnt" type="button" value="Add Item">
								</td>
							</tr>

							<tr>
								<td><div id="p_scents">
										<table>
											<tr>
											<p>
												<td><div id="dynamicDropDown1"></div>
												</td>
												<td><input type="text" readonly=true
													id="itemQuantity_0" onblur="amountCalc(0)"
													name="itemQuantity" size="20" placeholder="Quantity" />
												</td>
												<td><input type="text" id="itemPrice_0"
													name="itemPrice_0" readonly=true size="20"
													placeholder="Price per Item">
												</td>
												<td><input type="text" id="totalAmount_0"
													name="totalAmount_0" readonly=true size="20"
													placeholder="Total Amount">
												</td>
												<td><input id="remScnt" type="button" value="Remove">
												</td>
												</p>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td>Taxable Amount</td>
											<td><input type="text" id="iAmount" name="iAmount"
												readonly="true" placeholder="Total Amount" size="20">
											</td>
										</tr>
										<tr>
											<td>Tax</td>
											<td><input type="text" id="iTax" name="iAmount"
												placeholder="Tax" value="2" size="20" onblur="calcTax()">
											</td>
										</tr>
										<tr>
											<td>Net Amount</td>
											<td><input type="text" id="iNet" name="iNet"
												readonly="true" placeholder="Total Amount" size="20">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
			<p>
				<!-- <table>

                           <tr>
                                  <td><textarea></textarea></td>
                           </tr>
                     </table> -->

			</p>
			<p></p>
			<p>
				<input id="" type="button" value="Cancel"
					onclick="javascript:GoTo()"> <input id="DRAFT" type="button"
					value="Save" onclick="mSave(this.id)"> 
					
					<c:choose>
      				<c:when test="${invoice.recurring eq 1}">
									<input id="SENT"
					type="button" onclick="mSave(this.id)" value="Save & Schedule">
							      		
      		   		 </c:when>
		     <c:otherwise>
					
					<input id="SENT"
					type="button" onclick="mSave(this.id)" value="Save & Send">
					</c:otherwise>
					</c:choose>


					
			</p>
		</s:form>
	</div>
	<input type="hidden" id="fItem" />
	<input type="hidden" id="fQuantity" />
	<input type="hidden" id="fAmount" />
	<input type="hidden" id="fPrice" />
	<input type="hidden" id="status" />
	<input type="hidden" id="dateD" />
	<input type="hidden" id="recurring"  value=${invoice.recurring} /> 
	<input type="text" id="dateF" value=${sessionScope.login.tenant.dateFormat}  />
</body>
<div class="modal"></div>
</html>