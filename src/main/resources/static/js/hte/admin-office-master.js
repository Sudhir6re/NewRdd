var context ="";
jQuery(document).ready(function($) {
	context = $("#appRootPath").val();	
	$("#tblDataTable").dataTable();
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});




$("#officeName").blur(function(){
	var officeName = $("#officeName").val();
	if (officeName != '') {
		$.ajax({
			type : "GET",
			url : context+"/mdc/officeNameExists/"+officeName,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);
			},
			success : function(data) {
				console.log(data);
				var len = data.length;
				var checkFlag = data;
				var status=true;
				if (checkFlag == 0) {
					status = true;
					$("#btnSave").prop("disabled",false);
				} else if (checkFlag > 0) {
					swal(officeName + ' Already Present in the system, Please enter the Different officename !!!');
					document.getElementById("officeName").value = "";
					$("#btnSave").prop("disabled",true);
				}
			}
		});
	}
});


$("form[name='createOffice']").validate({
    rules: {
    	officeName: "required",
    	officeCode: "required",
    	dcpsOffName : {
    		  required: true,
              minLengthSelect: 2,
		},
    },
    messages: {
    	officeName: "Please Enter Admin Office Name",
    	officeCode: "Please Enter office Code",
    	dcpsOffName: {
            required: "Please Enter Dcps office Name",
            minLengthSelect: "Please Enter Dcps office Name",
        }
    },
    submitHandler: function(form) {
      form.submit();
      $("#loaderMainNew").show();
    }
  });


$.validator.addMethod("minLengthSelect", function(value, element, minLength) {
    return value.length >= minLength;
}, "Please select an option with at least {0} characters.");