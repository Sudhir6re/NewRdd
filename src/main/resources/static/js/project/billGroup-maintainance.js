var contextPath =
$(document).ready(function() {
	 contextPath = $("#appRootPath").val();
	  if ($('#cmbSchemeName').length) {
	        $('#cmbSchemeName').select2();
	    }
});










$("#cmbSchemeName").change(function(){
	$("#schemeName").val($("#cmbSchemeName").text());
});




function funDdo1() {

		var schemeId = $('#cmbSchemeName').val();
		
		if(schemeId != "0"){
			removeErrorClass($("#schemeName"));
		}

		if (schemeId != ''  && schemeId != '0') {
			$.ajax({
				type : "GET",
				url : contextPath+"/ddoast/getSchemeCodeAgainstName/" + schemeId,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					console.log(data);
				},
				beforeSend : function(){
					$( "#loaderMainNew").show();
					},
				complete : function(data){
					$( "#loaderMainNew").hide();
				},	
				success : function(data) {
					console.log(data);
					$('#txtSchemeCode').val(0.);
					$("#txtSchemeCode").val(data[0].schemeCode);
					$("#cmbSubschemeName").val(data[0].schemeName);
					$("#RadioPermenantTempBothP").val(data[0].schemeType);
				
				}
			});
		}

	}

$(".billGrpId")
.click(
		function() {
			
			$('#frmDdoGroupBill').trigger("reset");
			var billGrpId=$(this).text();
				
			 if ((billGrpId !='')) {
					
					$
							.ajax({
								type : "GET",
								url : contextPath+"/ddoast/getBillDtlsForAlreadySaved/"+ billGrpId,
								async : true,
								contentType : 'application/json',
								error : function(
										response) {
									console.log(response);
									$(".loaderMainNew").hide();
									// alert(data);
								},
								beforeSend : function(){
									$( "#loaderMainNew").show();
									},
								complete : function(data){
									$( "#loaderMainNew").hide();
								},	
								success : function(
										response) {
									if(response!=''){
									
									var temp=response;
									$(".loaderMainNew").hide();
									
									
									$.each(response.billdetails,function(index,value) {
												$('#txtSchemeCode').val(value[3]);
												$('#txtDescription').val(value[0]);
												 $('#cmbSchemeName').val(value[3]);
												 $('#txtBillGroupNo').val(value[5]); 
												 var typePost= value[2];
												 if(typePost=='P')
													 $('#RadioPermenantTempBothP').prop("checked",true);
												 else if(typePost=='T')
													 $('#RadioPermenantTempBothT').prop("checked",true);
												 else
													 $('#RadioPermenantTempBothB').prop("checked",true);
												
											});
									
									
									  $.each(response.grpdtls, function(index, value) {
												if(value == 'NA')
													 $('#GroupNA').prop("checked",true);
												if(value == 'A')
													 $('#GroupA').prop("checked",true);
												if(value == 'B')
													 $('#GroupB').prop("checked",true);
												if(value == 'BnGz')
													 $('#GroupBnGz').prop("checked",true);
												if(value == 'C')
													 $('#GroupC').prop("checked",true);
												if(value == 'D')
													 $('#GroupD').prop("checked",true);
						                 });
									
								}
								}
							});
					}

		});





$('#frmDdoGroupBill').validate({
    rules: {
        dcpsDdoSchemeCode: {
            required: true,
            min: 1 
        },
        txtSchemeCode: {
            required: true
        },
        dcpsDdoBillTypeOfPost: {
            required: true
        },
        description: {
        	required: true
        },
    },
    messages: {
        dcpsDdoSchemeCode: {
            required: "Please select a Scheme Name",
            min: "Please select a Scheme Name"
        },
        cmbSubschemeName: {
            required: "Please select a Subscheme Name"
        },
        dcpsDdoBillTypeOfPost: {
            required: "Please select a Type of Post"
        },
        description: {
        	required: "Please enter description"
        }
    },
    submitHandler: function(form) {
        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
        var isChecked = false;

        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                isChecked = true;
            }
        });
        if (!isChecked) {
            addErrorClass($("#errorClass"),"Please select at least one Group.");
            return false;
        }else{
        	removeErrorClass($("#errorClass"));
        	form.submit(); 
        	$( "#loaderMainNew").show();
        }
    }
  
});





