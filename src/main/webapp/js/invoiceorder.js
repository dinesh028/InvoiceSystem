
	var g = 1;
	function GoTo() {

		window.document.location.href = "/InvoiceSystem/registration/invoice/main";
	}
	var nameG;
	var count = 0;

	$(function() {
		$("#datepicker").datepicker();
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
				dropdownClient(msg);
			},
			error : function(e) {
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
		table += "<option value='1'>-----Select------</option>";
		$.each(data, function(i, item) {
			table += "<option value='"+item+"'>" + item + "</option>";
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
		if (itemName == 1) {
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

		} else if (itemName != 1) {
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
			data : "total_amount=" + $("#iNet").val() + "&customer="
					+ $("#clientname").val() + "&due_date="
					+ $("#datepicker").val(),
			cache : false,
			success : function(msg) {
				alert(msg);
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

		$.each(data, function(i, item) {
			table += "<option value='"+item+"'>" + item + "</option>";
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
				var initial = parseInt($("#iTax").val()) + parseInt(100);
				$("#iNet").val(parseInt(initial) * $("#iAmount").val() / 100);
				if ($("#iNet").val() == 0)
					$("#iNet").val('');
				/* alert(parseInt($("#iTax").val())+parseInt(100)*$("#iAmount").val()); */
				/* alert((parseInt(initial)*$("#iAmount").val())/100); */
			}

		}

	}

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

												var table = '<select id=itemname_'
														+ g
														+ ' name = itemname_'
														+ g
														+ ' onchange=selectChange('
														+ g + ')>';
												table += "<option value='1'>-----Select------</option>";
												$
														.each(
																nameG,
																function(g,
																		item) {
																	table += "<option value='"+item+"'>"
																			+ item
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
