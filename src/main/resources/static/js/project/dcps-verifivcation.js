jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
});

/*$(document).ready(function() {
	$("form[name='dcpsVerification']").validate({
		ignore : "",
		rules : {
			cmbFinancialYear : {
				required : true,
			},
		},
		messages : {
			cmbFinancialYear : {
				required : "Please select Treasury Name",
		},
		submitHandler : function(form) {
			form.submit();
		}
	});

});*/