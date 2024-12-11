
$(document).ready(function(){ 
 $("#searchDiv").hide();
});


$("#sevaarth").blur(
		function() {
			var sevaarthId = $("#sevaarth").val();
			if (sevaarthId.length == 0) {
				document.getElementById("searchDiv").innerHTML = "";
				document.getElementById("searchDiv").style.border = "0px";
				return;
			}
			if (sevaarthId != '' && sevaarthId.length != 0) {
				$("#loaderMainNew").show();
				$.ajax({
					type : "POST",
					url : "../mdc/getEmpDobBySevaarthId/" + sevaarthId,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						document.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {

							$("#searchDiv").show();
							//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							//$("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv").append("<li class='empdata'   employeeId='"+data[i].employeeId+"'   empsevaathid='"+ data[i].sevaarthId + "'" +     
							" empName='" +data[i].employeeFullNameEn+"' designation='"+data[i].designation+"' dob='"+data[i].dateString+"'>"+data[i].employeeFullNameEn+"</li>");
							
							
							$("#searchDiv").css("border:1px solid #A5ACB2;");
							///a.sevaarth_id,b.designation_name,c.ddo_office,a.pfacno	///a.sevaarth_id,b.designation_name,c.ddo_office,a.pfacno
						}
						
						
						if(data.length==0){
							swal("No Data Found");
						}

					}
				});
			}
		});


$('body').on('click', '.empdata', function() {
    $("#sevaarthId").val($(this).attr("empsevaathid"));
    $("#designName").val($(this).attr("designation"));
    $("#empName").val($(this).attr("empName"));
    $("#employeeId").val($(this).attr("employeeId"));
    
    $("#dob").val($(this).attr("dob"));
    
    document.getElementById("searchDiv").innerHTML = "";
    $("#searchDiv").hide();
});




$("form[name='form1']").validate({
	// Specify validation rules
	rules : {
		loanAdvName : {
			"required" : true,
		},
		loanDate : {
			"required" : true,
		},
		loansancorderdate : {
			"required" : true,
		},
		loanprinamt : {
			"required" : true,
		},

	},
	// Specify validation error messages
	messages : {
		stateNameEn : "Please enter State Name in english",
		stateNameMr : "Please enter State Name in Marathi",
		loanAdvName : "Please select the Loan Name",
	},
	// Make sure the form is submitted to the destination defined
	// in the "action" attribute of the form when valid
	submitHandler : function(form) {
		var flag = true;
		if ($('#loanAdvName').val() == "0") {
			flag = false;
			addErrorClass($('#loanAdvName'), "Please select the Loan Name");
		} else {
			removeErrorClass($('#loanAdvName'));

		}

		if (flag == false) {

		} else {
			form.submit();
		}

	}
});

