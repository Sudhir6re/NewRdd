$(function() {
	// Initialize date picker
	$(".datePicker").datepicker({
		dateFormat : 'dd-mm-yy',
		onSelect : function(dateText, inst) {
			// When a date is selected from the datepicker, update the input
			// value
			$(".datePicker").val(dateText);
		}
	});

	// Add event listener to validate manual input
	$("datePicker").on("change", function() {
		var enteredDate = $(this).val();
		var regex = /^\d{2}-\d{2}-\d{4}$/; // Regex to match dd-mm-yyyy format
		if (!regex.test(enteredDate)) {
			alert("Please enter date in the format dd-mm-yyyy");
			$(this).val(""); // Clear the input field
		}
	});
});

function digitFormat(formfield) {
	var val;
	if (window.event.keyCode > 47 && window.event.keyCode < 58) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function dateFormat(field) {
	var value = new String(field.value);
	if (value.length == 2) {
		field.value = value + '-'
	}
	if (value.length == 5) {
		field.value = value + '-'
	}
}
