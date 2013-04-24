
$(function(){

	// preview icon
	$("#config-icon select")
		.change(function(){
			var icon = "<span class='ui-icon "+$(this).val()+"'></span>";
			$(this).parents(".wrapper").find("ins").html(icon);
		})
		.trigger("change");


	// click to open dialog
	 $(document).ready(function() {
		//dialog options
		var dialogOptions = {
			"title" : "Latest Invoices",
			"width" : 400,
			"height" : 300,
			"modal" : false,
			"resizable" : false, 
			"position": [100, 200],
			"close" : function(){ $(this).remove(); }
		};
			
		// dialog-extend options
		var dialogExtendOptions = {
			"close" :  true,
			"maximize" : true,
			"minimize" : true,
			"dblclick" : false,
			"titlebar" : false, 
		};
		// open dialog
		$(divDue).dialog(dialogOptions).dialogExtend(dialogExtendOptions);
		//$(divDue).dialog(dialogOptions, 'position', 'left').dialogExtend(dialogExtendOptions).position;
		
		//$(divDue).dialog({width:500, height:232 ,position:[100, 200]});
	});


	// click to invoke method
	$("#config-method button").click(function(){
		var command = $(this).text();
		var dialog = $(".ui-dialog:last").find(".ui-dialog-content");
		if ( $(dialog).length ) {
			if ( command == 'state' ) {
				alert( $(dialog).dialogExtend(command) );
			} else {
				$(dialog).dialogExtend(command);
			}
		}
	});

});