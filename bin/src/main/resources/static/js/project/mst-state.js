var stateNameError;
var stateCodeError;
var countryNameError;
var warningError;
if($('#language').val()=="en"){
	stateNameError="Please enter state name !!!";
	stateCodeError="Please enter state code !!!";
	countryNameError="Please select country name !!!";
	warning="warning !";
}else{
	stateNameError="कृपया राज्य नाव प्रविष्ट करा !!!";
	stateNameError="कृपया राज्य कोड प्रविष्ट करा  !!!";
	countryNameError="कृपया देशाचा नाव निवडा !!!";
	warning="चेतावणी !";
}


$("form[name='mstState']").validate({
    // Specify validation rules
    rules: {
    	stateNameEn: "required",
    	stateNameMr: "required",
  
    },
    // Specify validation error messages
    messages: {
    	stateNameEn: "Please enter State Name in english",
    	stateNameMr: "Please enter State Name in Marathi",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });














