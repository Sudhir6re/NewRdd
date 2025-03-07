var contextPath = "";
$(document).ready(function() {
	contextPath = $("#appRootPath").val();
});

function validateAccNo() {
	var accNo = document.getElementById("txtAccountNo").value;
	var regex = /^[0-9]*$/;
	if (regex.test(accNo)) {
	} else {
		//alert('Please enter only digit in Mobile No. field');
		document.getElementById("txtAccountNo").value = '';
		return false;
	}
}


function funDdo1() {

	var schemeId = $('#cmbSchemeName').val();

	if (schemeId != "0") {
		removeErrorClass($("#schemeName"));
	}

	if (schemeId != '') {
		$.ajax({
			type : "GET",
			url : contextPath + "/ddoast/getSchemeCodeAgainstName/" + schemeId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);
				/* alert("eror is" + data); */
			},

			success : function(data) {
				console.log(data);
				$('#txtSchemeCode').val(0.);
				$("#txtSchemeCode").val(data[0].schemeCode);
				$("#cmbSubschemeName").val(data[0].schemeName);
				$("#RadioPermenantTempBothP").val(data[0].schemeType);
				/*
				 * var temp=data $("#txtSchemeCode").val(''); $.each(temp,
				 * function(index, value) { $.each(value, function(index,
				 * value1) { //console.log(index + "-" + value1);
				 * if(index=="txtSchemeCode"){ console.log("txtSchemeCode"); }
				 * }); });
				 */
			}
		});
	}

}

$(".officeName").click(function() {

	$('#approveDDOOffice').trigger("reset");
	var ddoCode = $(this).attr("value");
	
	
	
	var status= $(this).attr("data");
	

	if (ddoCode != '') {

		$.ajax({
			type : "GET",
			url : contextPath + "/ddo/getAlreadySavedDataforDDO/" + ddoCode,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);

				$(".loaderMainNew").hide();
				// alert(data);
			},
			success : function(data) {

				var temp = data;
				$(".loaderMainNew").hide();
				$.each(temp, function(index, value) {
					$('#txtNameOfOffice').val(value[0]);
					$('#stateId').val(value[1]);
					$('#districtId').val(value[2]);
					$('#talukaId').val(value[3]);
					$('#txtTown').val(value[4]);
					$('#village').val(value[5]);
					$('#txtAddress1').val(value[6]);
					$('#txtPin').val(value[7]);
					$('#ddoOffClass').val(value[8]);
					$('#txtdiceCode').val(value[9]);
					$('#txtGrant').val(value[10]);
					$('#txtTelNo1').val(value[11]);
					$('#txtfax').val(value[12]);
					$('#txtEmail').val(value[14]);
					$('#hiddenddoCode').val(ddoCode);
					var tribalArea = value[13];
					if (tribalArea == 'Yes')
						$('#RadioButtonTriableAreaYes').prop("checked", true);
					else
						$('#RadioButtonTriableAreaNo').prop("checked", true);

					var hillyArea = value[15];
					if (tribalArea == 'Yes')
						$('#RadioButtonHillyAreaYes').prop("checked", true);
					else
						$('#RadioButtonHillyAreaNo').prop("checked", true);

					var naxaliteArea = value[16];
					if (tribalArea == 'Yes')
						$('#RadioButtonNaxaliteAreaYes').prop("checked", true);
					else
						$('#RadioButtonNaxaliteAreaNo').prop("checked", true);

					$('#txtDDOName').val(value[17]);
					$('#cmbDesignation').val(value[18]);
					$('#txtWEFDate').val(dateToYMD(value[19]));
					$('#txtTANNo').val(value[20]);
					$('#txtITWardCircle').val(value[21]);
					$('#cmbBankName').val(value[22]);
					$('#cmbBranchName').val(value[23]);
					$('#txtIFSCCode').val(value[24]);
					$('#txtAccountNo').val(value[25]);
					$('#txtRemarks').val(value[26]);
					$('#instituteType').val(value[27]);
					
					
					
					
					
					if(value[28]=='Y'){
						$("#bankPass").prop("checked",true);
					}else{
						$("#bankPass").prop("checked",false);
					}
					
					
					if(value[29]=='Y'){
						$('#bankCheque').prop("checked",true);
					}else{
						$('#bankCheque').prop("checked",false);
					}
					
					
					if(value[30]=='Y'){
						$('#deptLetter').prop("checked",true);
					}else{
						$('#deptLetter').prop("checked",false);
					}
					
					
					$('#txtTelNo2').val(value[31]);
					

				});
				$('select').each(function() {
					var preloadedValue = $(this).val();
					if (preloadedValue) {
						$(this).trigger('change');
					}
				});
				
				
				
				$('html, body').animate({scrollTop : $("#txtNameOfOffice").offset().top},2000);

			}
		});
		
		
		if(status=="Approved"){
			  $("#approve").prop("disabled",true);
			  $("#btnRjct").prop("disabled",true);
		}
		
		
	} else {
		swal("Please Select Atleast one Component");
	}
});

$('#approve').click(function() {
    var ddoCode = $('#hiddenddoCode').val();
    var cityClass = $('#ddoOffClass').val();
    var flag = 1;
    
    if (ddoCode !== '') {
        // Show SweetAlert with OK and Cancel options
        swal({
            title: "Are you sure you want to approve?",
            icon: "warning",
            buttons: {
                cancel: {
                    text: "Cancel",
                    value: false,
                    visible: true,
                    className: "",
                    closeModal: true
                },
                confirm: {
                    text: "OK",
                    value: true,
                    visible: true,
                    className: "",
                    closeModal: true
                }
            }
        }).then((isConfirmed) => {
            if (isConfirmed) {
                // If OK is clicked, make the AJAX call
                $.ajax({
                    type: "GET",
                    url: contextPath + "/ddo/updateApproveRejectStatus/" + ddoCode + "/" + flag + "/" + cityClass,
                    async: true,
                    contentType: 'application/json',
                    error: function(data) {
                        console.log(data);
                    },
                    success: function(data) {
                        console.log(data);
                        swal("Approved Successfully!", "", "success").then(() => {
                            location.reload(); // Reload after showing success
                        });
                    }
                });
            } else {
                // If Cancel is clicked, show a cancel message
                swal("Action cancelled", "", "info");
            }
        });
    }
});


$('#btnRjct').click(function() {
			var ddoCode = $('#hiddenddoCode').val();
			var cityClass = $('#ddoOffClass').val();
			var txtReasonForRejection = $('#txtReasonForRejection').val();
			
			$("#reasonForRejection").show();
				
			if(txtReasonForRejection==''){
				swal("Please write remark for rejection ", "", "warning");
			}
			
			var flag = 2;
			
			cityClass=txtReasonForRejection;
			
			
			if (ddoCode !== '' && txtReasonForRejection!='') {
			       swal({
			           title: "Are you sure you want to Reject?",
			           icon: "warning",
			           buttons: {
			               cancel: {
			                   text: "Cancel",
			                   value: false,
			                   visible: true,
			                   className: "",
			                   closeModal: true
			               },
			               confirm: {
			                   text: "OK",
			                   value: true,
			                   visible: true,
			                   className: "",
			                   closeModal: true
			               }
			           }
			       }).then((isConfirmed) => {
			           if (isConfirmed) {
			               $.ajax({
			                   type: "GET",
			                   url: contextPath + "/ddo/updateApproveRejectStatus/" + ddoCode + "/" + flag + "/" + cityClass,
			                   async: true,
			                   contentType: 'application/json',
			                   error: function(data) {
			                       console.log(data);
			                   },
			                   success: function(data) {
			                       console.log(data);
			                       swal("Rejected Successfully!", "", "success").then(() => {
			                           location.reload();
			                       });
			                   }
			               });
			           } else {
			               swal("Action cancelled", "", "info");
			           }
			       });
			   }
		});
		
	
