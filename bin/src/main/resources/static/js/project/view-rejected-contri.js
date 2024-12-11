jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
});

$(document).ready(function() {
	$("form[name='viewRejectedContri']").validate({
		ignore : "",
		rules : {
			cmbTreasuryCode : {
				required : true,
			},
			cmbDDOCode : {
				required : true,
			},
			cmbBillGroup : {
				required : true,
			},
			txtSchemeName : {
				required : true,
			},
			txtSubSchemeName : {
				required : true,
			},
			cmbMonth : {
				required : true,
			},
			cmbYear : {
				required : true,
			},

		},
		messages : {
			cmbTreasuryCode : {
				required : "Please select Treasury Name",
			},
			cmbDDOCode : {
				required : "Please select DDO Name",
			},
			cmbBillGroup : {
				required : "Please select Bill Group",
			},
			txtSchemeName : {
				required : "Please Insert Scheme",
			},
			txtSubSchemeName : {
				required : "Please Insert Sub Scheme",
			},
			cmbMonth : {
				required : "Please select Pay Month",
			},
			cmbYear : {
				required : "Please select Pay year",
			},

		},
		submitHandler : function(form) {
			form.submit();
		}
	});

});