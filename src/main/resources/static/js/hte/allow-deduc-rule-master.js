var contextPath ="";
jQuery(document).ready(function($) {
	 contextPath = $("#appRootPath").val();
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
	
	var table = $('#tblDataTable').DataTable();
	
	
    $('#departmentAllowdeducCode').on('change', function() {
    	var depName=$(this).attr("data2");
      //  table.column(1).search(depName).draw(); 
        if($('#departmentAllowdeducCode').val()=="4" || $('#departmentAllowdeducCode').val()=="11"  || $('#departmentAllowdeducCode').val()=="135" || $('#departmentAllowdeducCode').val()=="12"  || $('#departmentAllowdeducCode').val()=="18"  || $('#departmentAllowdeducCode').val()=="128"){
        	swal("This component is update only");
        	$("#btnSave").prop("disabled",true);
        }else{
        	 $("#btnSave").prop("disabled",false);
        }
        
    });
    
    $("#isType").change(function(){
  	  var isType=$("#isType").val();
  		$.ajax({
  			type : "GET",
  			url : contextPath+"/mdc/AllowanceDeductionWiseMaster/"
  						+ isType,
  			async : true,
  			contentType : 'application/json',
  			error : function(data) {
  				 console.log(data);
  			},
  			success : function(data) {
  				 console.log(data);
  				var len = data.length;
  				
  			var validCodes = [
      		    "106", "10", "87", "161", "39", 
      		    "101", "102", "61", "35", "160", 
      		    "143", "9", "127", "11", "128"
      		];
  				
  				if (len != 0) { 
  				$("#departmentAllowdeducCode").empty();
  				 var newOption = $('<option value="0">Please Select</option>');
              	 $('#departmentAllowdeducCode').append(newOption);
                		for (var i = 0; i < data.length; i++) {
                			var departmentCodeString = String(data[i].departmentAllowdeducCode);
                			 if (validCodes.indexOf(departmentCodeString) !== -1) {
                		        var newOption = $('<option value="'+data[i].departmentAllowdeducCode+'">'+data[i].departmentAllowdeducName+'</option>');
                		        $('#departmentAllowdeducCode').append(newOption);
                		    }
                		}
  			   }
  			}
  		});
       });
    
    

        	//editRuleMasterForm
    
   	
    $("#btnUpdate").click(function(event) {
        event.preventDefault();
        var isType = $('#isType').val();
        var departmentAllowdeducCode = $('#departmentAllowdeducCode').val();
        var payCommissionCode = $('#payCommissionCode').val();
        var amount = $('#amount').val().trim();
        var startDate = $('#startDate').val().trim();
        var endDate = $('#endDate').val().trim();
        var percentage = $('#percentage').val().trim();
        var minBasic = $('#minBasic').val().trim();
        var maxBasic = $('#maxBasic').val().trim();
        var cityClass = $('#cityClass').val();
        var gradePayLower = $('#gradePayLower').val().trim();
        var gradePayHigher = $('#gradePayHigher').val().trim();
        var premiumAmount = $('#premiumAmount').val().trim();
        var cityGroup = $('#cityGroup').val().trim();
        
        var errors = [];
        
        var payCommissionMandatoryCodes = ["101", "35", "143", "102", "106", "11", "128", "61", "160", "10", "161", "39"];
        if (payCommissionMandatoryCodes.includes(departmentAllowdeducCode) && payCommissionCode == "0") {
            errors.push('Pay Commission is required for the selected Type Of Component.');
        }

        var mandatoryPercentageAllowDeducCode = ["39", "61", "101", "102", "160", "143", "10", "161", "106", "9", "11"];
        if (mandatoryPercentageAllowDeducCode.includes(departmentAllowdeducCode) && !percentage) {
            errors.push('Percentage is required for the selected Type Of Component.');
        }

        var amountMandatoryCodes = ["35", "9", "127", "87", "106", "11", "128"];
        if (amountMandatoryCodes.includes(departmentAllowdeducCode) && !amount) {
            errors.push('Amount is required for the selected Type Of Component.');
        }

        var maxBasicMandatoryCodes = ["11", "35"];
        if (maxBasicMandatoryCodes.includes(departmentAllowdeducCode) && !maxBasic) {
            errors.push('Maximum Basic is required for the selected Type Of Component.');
        }

        var minBasicMandatoryCodes = ["9", "11", "35"];
        if (minBasicMandatoryCodes.includes(departmentAllowdeducCode) && !minBasic) {
            errors.push('Minimum Basic is required for the selected Type Of Component.');
        }

        var startDateMandatoryCodes = ["106", "11", "61", "160", "101", "9", "10", "143", "127", "102", "161"];
        if (startDateMandatoryCodes.includes(departmentAllowdeducCode) && !startDate) {
            errors.push('Start Date is required for the selected Type Of Component.');
        }

        var cityGroupMandatoryCodes = ["11", "35", "127", "87"];
        if (cityGroupMandatoryCodes.includes(departmentAllowdeducCode) && !cityGroup) {
            errors.push('City Group is required for the selected Type Of Component.');
        }

        if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
            errors.push('End Date should be after Start Date.');
        }

        if (departmentAllowdeducCode == "9" && !cityClass) {
            errors.push('City Class is required for the selected Type Of Component.');
        }

        if (departmentAllowdeducCode == "106" && (!gradePayLower || !gradePayHigher)) {
            errors.push('Grade Pay/Pay Scale is required for the selected Type Of Component.');
        }

        if (errors.length > 0) {
            swal({
                title: 'Validation Error',
                text: errors.join('\n'),  
                icon: 'error',
                button: 'Ok',
            });
        } else {
            swal({
                title: 'Success',
                icon: 'warning',
                text: 'Are are sure to submit!',
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete && parseInt(departmentAllowdeducCode) > 0) {
                    $("#editRuleMasterForm").submit(); 
                }else{
                	swal("select atleast one componet");
                }
            });	
        }
    });
    
        	
    $("#btnSave").click(function(event) {
        event.preventDefault();
        var isType = $('#isType').val();
        var departmentAllowdeducCode = $('#departmentAllowdeducCode').val();
        var payCommissionCode = $('#payCommissionCode').val();
        var amount = $('#amount').val().trim();
        var startDate = $('#startDate').val().trim();
        var endDate = $('#endDate').val().trim();
        var percentage = $('#percentage').val().trim();
        var minBasic = $('#minBasic').val().trim();
        var maxBasic = $('#maxBasic').val().trim();
        var cityClass = $('#cityClass').val();
        var gradePayLower = $('#gradePayLower').val().trim();
        var gradePayHigher = $('#gradePayHigher').val().trim();
        var premiumAmount = $('#premiumAmount').val().trim();
        var cityGroup = $('#cityGroup').val().trim();
        
        var errors = [];
        
        var payCommissionMandatoryCodes = ["101", "35", "143", "102", "106", "11", "128", "61", "160", "10", "161", "39"];
        if (payCommissionMandatoryCodes.includes(departmentAllowdeducCode) && payCommissionCode == "0") {
            errors.push('Pay Commission is required for the selected Type Of Component.');
        }

        var mandatoryPercentageAllowDeducCode = ["39", "61", "101", "102", "160", "143", "10", "161", "106", "9", "11"];
        if (mandatoryPercentageAllowDeducCode.includes(departmentAllowdeducCode) && !percentage) {
            errors.push('Percentage is required for the selected Type Of Component.');
        }

        var amountMandatoryCodes = ["35", "9", "127", "87", "106", "11", "128"];
        if (amountMandatoryCodes.includes(departmentAllowdeducCode) && !amount) {
            errors.push('Amount is required for the selected Type Of Component.');
        }

        var maxBasicMandatoryCodes = ["11", "35"];
        if (maxBasicMandatoryCodes.includes(departmentAllowdeducCode) && !maxBasic) {
            errors.push('Maximum Basic is required for the selected Type Of Component.');
        }

        var minBasicMandatoryCodes = ["9", "11", "35"];
        if (minBasicMandatoryCodes.includes(departmentAllowdeducCode) && !minBasic) {
            errors.push('Minimum Basic is required for the selected Type Of Component.');
        }

        var startDateMandatoryCodes = ["106", "11", "61", "160", "101", "9", "10", "143", "127", "102", "161"];
        if (startDateMandatoryCodes.includes(departmentAllowdeducCode) && !startDate) {
            errors.push('Start Date is required for the selected Type Of Component.');
        }

        var cityGroupMandatoryCodes = ["11", "35", "127", "87"];
        if (cityGroupMandatoryCodes.includes(departmentAllowdeducCode) && !cityGroup) {
            errors.push('City Group is required for the selected Type Of Component.');
        }

        if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
            errors.push('End Date should be after Start Date.');
        }

        if (departmentAllowdeducCode == "9" && !cityClass) {
            errors.push('City Class is required for the selected Type Of Component.');
        }

        if (departmentAllowdeducCode == "106" && (!gradePayLower || !gradePayHigher)) {
            errors.push('Grade Pay/Pay Scale is required for the selected Type Of Component.');
        }

        if (errors.length > 0) {
            swal({
                title: 'Validation Error',
                text: errors.join('\n'),  
                icon: 'error',
                button: 'Ok',
            });
        } else {
            swal({
                title: 'Success',
                icon: 'warning',
                text: 'Are are sure to submit!',
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete && parseInt(departmentAllowdeducCode) > 0) {
                    $("#ruleMasterForm").submit(); 
                }else{
                	swal("select atleast one componet");
                }
            });	
        }
    });


    
   
    
});

	
	
   
/*


old  mandatory code                                   new codes

"Family Planning Allowance"	10      "Family Planning Allowance"	106

"Dearness Allowance (D.A)"	8          "Dearness Allowance (D.A)"	10

"GIS"	37                             "GIS"	87

"7PC DA"	207                        "7PC DA"	161

"ATS-Incentive(50)"	4                      "ATS-Incentive(50)"	39

"Force-1 Incentive(100)"	11              "Force-1 Incentive(100)"	101

"Force-1 Incentive(25)"	12                  "Force-1 Incentive(25)"	102

"ATS-Incentive(30)"	18                       "ATS-Incentive(30)"	61

"CLA"	2                                       "CLA"  35	

"DA ON TA"	19                                 "DA ON TA"	 160 

"Central DA"	6                            "Central DA"	143

"HRA"	17                                     "HRA"	 9 

"Accidential Policy"	134                    "Accidential Policy"	127

"Empr Contr(NPS 14%)"	208                      "Empr Contr(NPS 14%)"   11 

"Revenue_Stamp"	135                                  "Revenue_Stamp"	 128

*/
  
  
