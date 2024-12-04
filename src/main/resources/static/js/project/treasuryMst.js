
jQuery(document).ready(function($) {
	//alert("qwe");
	var varMessage = $("#message").val();
	//alert("varMessage in if----"+varMessage);

	if (varMessage != "" && varMessage != undefined) {
		swal.fire("Record Updated Successfully..!!");
	}
});

$("form[name='TreasuryMst']").validate({
    // Specify validation rules
    rules: {
    	countryCode:
    		{
    		required:true,
    		min:1								
    		},
    		stateCode:
    		{
    			required:true,
    			min:1
    		},
    		districtCode:
    		{
    			required:true,
    			min:1
    		},
    		talukaCode:
    		{
    			required:true,
    			min:1
    		},
    		treasuryOfficeName: "required",
    		treasuryOfficeNameMr : "required",
    		treasuryOfficeShortName : "required",
    		treasuryOfficeCode : "required",
    		address: "required",
    		
    		
    		
  
    },
    // Specify validation error messages
    messages: {
    	countryCode: "Please Select the Country",
    	stateCode: "Please Select the State",
    	districtCode: "Please Select the District",
    	talukaCode: "Please Select the Taluka",
    	treasuryOfficeName: "Please Enter Treasury Office Name",
    	treasuryOfficeNameMr: "Please Enter Treasury Office Name in Marathi",
    	treasuryOfficeShortName: "Please Enter Treasury Office Short Name",
    	treasuryOfficeCode: "Please Enter Treasury Office Code",
    	address: "Please Enter Address",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
 
