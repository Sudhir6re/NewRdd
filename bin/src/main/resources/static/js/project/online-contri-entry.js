var contextPath = "";
	$(document).ready(function() {
	contextPath = $("#appRootPath").val();
	if ($('#cmbSchemeName').length) {
		$('#cmbSchemeName').select2();
	}

	if ($('#billGroupId').length) {
		$('#billGroupId').select2();
	}

	if ($('#ddoCode').length) {
		$('#ddoCode').select2();
	}
});





jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
});

/*$("#delayedMonthAndYearCombos").hide();
 $("#DAArrearsDatesDivDisplay").hide();
 $("#PayArrearsDatesDivDisplay").hide();*/

$('#trnDCPSTable').on('input change', 'input, select', function() {
	$(this).siblings('.error').remove();
});

$('#save')
		.click(
				function(e) {
					e.preventDefault(); // Prevent the default form submission
					var roleId=$("#levelRoleVal").val();
				
					if(roleId=="2"){
						$("#onlineEntryForm").attr("action",contextPath + "/ddo/saveOnlineContriEntry");
					}else{
						$("#onlineEntryForm").attr("action",contextPath + "/ddoast/saveOnlineContriEntry");
					}
					
					// Clear previous error messages
					$('.error').remove();

					// Flag to check if the form is valid
					let isValid = true;

					// Validate each row in the table
					$('#trnDCPSTable tr')
							.each(
									function() {
										const $row = $(this);

										// Validate required fields
										const employeeName = $row.find(
												'.employeeName').text().trim();
										const dcpsNO = $row.find('.dcpsNO')
												.val().trim();
										const startDate = $row.find(
												'.startDate').val().trim();
										const endDate = $row.find('.endDate')
												.val().trim();
										const payCommission = $row.find(
												'.payCommission').val();
										const typeOfPayment = $row.find(
												'.typeOfPayment').val();
										const basicPay = $row.find('.basicPay')
												.val().trim();
										const contribution = $row.find(
												'.contribution').val().trim();
										const emprContribution = $row.find(
												'.emprContribution').val()
												.trim();
										const da = $row.find('.da').val()
												.trim();

										// Check if employee name is not empty
										if (!employeeName) {
											isValid = false;
											$row
													.find('.employeeName')
													.after(
															'<span class="error text-danger">Employee name is required.</span>');
										}

										// Check if DCPS ID is not empty
										if (!dcpsNO) {
											isValid = false;
											$row
													.find('.dcpsNO')
													.after(
															'<span class="error text-danger">DCPS ID is required.</span>');
										}

										// Check if start date is selected
										if (!startDate) {
											isValid = false;
											$row
													.find('.startDate')
													.after(
															'<span class="error text-danger">Start date is required.</span>');
										}

										// Check if end date is selected
										if (!endDate) {
											isValid = false;
											$row
													.find('.endDate')
													.after(
															'<span class="error text-danger">End date is required.</span>');
										}

										// Check if pay commission is selected
										if (payCommission === "0") {
											isValid = false;
											$row
													.find('.payCommission')
													.after(
															'<span class="error text-danger">Pay Commission must be selected.</span>');
										}

										// Check if payment type is selected
										if (typeOfPayment === "0") {
											isValid = false;
											$row
													.find('.typeOfPayment')
													.after(
															'<span class="error text-danger">Payment type must be selected.</span>');
										}

										// Check if basic pay is a valid number and not empty
										if (!basicPay || isNaN(basicPay)) {
											isValid = false;
											$row
													.find('.basicPay')
													.after(
															'<span class="error text-danger">Valid basic pay is required.</span>');
										}

										// Check if contribution is a valid number and not empty
										if (!contribution
												|| isNaN(contribution)) {
											isValid = false;
											$row
													.find('.contribution')
													.after(
															'<span class="error text-danger">Valid contribution is required.</span>');
										}

										// Check if employer contribution is a valid number and not empty
										if (!emprContribution
												|| isNaN(emprContribution)) {
											isValid = false;
											$row
													.find('.emprContribution')
													.after(
															'<span class="error text-danger">Valid employer contribution is required.</span>');
										}

										// Check if DA is a valid number and not empty
										if (!da || isNaN(da)) {
											isValid = false;
											$row
													.find('.da')
													.after(
															'<span class="error text-danger">Valid DA is required.</span>');
										}
									});

					// If valid, submit the form
					if (isValid) {
						$("#onlineEntryForm").submit();
						// $('form').submit(); // Or use AJAX for submission
					}
				});

/*$("#btnApprove").click(
		function(e) {
			//	e.preventDefault();
			$("#onlineEntryForm").attr("action",
					contextPath + "/ddoast/viewForwrdedOnlineContriEntry");
			$("#onlineEntryForm").submit();
		});*/

$("#finYearId")
		.change(
				function(e) {
					swal("Please select correct Financial Pay Year. For January, February and March - Select 2017-2018 and for April onwards - Select 2018-2019.");
				});

$("#trnDCPSTable").on('blur', ".basicPay", function() {
	var row = $(this).closest("tr");
	var basic = parseFloat(row.find('.basicPay').val());
	var payCommission = row.find('.payCommission').val();
	var DP;

	if (payCommission === '700015' || payCommission === '700345') {
		DP = basic / 2;
	} else {
		DP = 0;
	}

	var DARate = parseFloat(row.find('.daRate').val());
	DARate=0.01 *DARate;
	var DA = ((basic + DP) * DARate).toFixed(2);
	var tempContribution = basic + Math.round(DA) + DP;
	var contribution = (tempContribution * 0.10).toFixed(2);
	var contributionNps = (tempContribution * 0.14).toFixed(2);

	row.find('.da').val(Math.round(DA));
	row.find('.contribution').val(Math.round(contribution));
	row.find('.emprContribution').val(Math.ceil(contributionNps));
});

$("#trnDCPSTable").on('blur', ".da", function() {
	var row = $(this).closest("tr");
	var basic = parseFloat(row.find('.basicPay').val());
	var payCommission = row.find('.payCommission').val();
	var DP;

	if (payCommission === '700015' || payCommission === '700345') {
		DP = basic / 2;
	} else {
		DP = 0;
	}

	//var DARate = parseFloat(row.find('.daRate').val()) || 0;
	var DA = parseFloat(row.find('.da').val()) || 0;
	var tempContribution = basic + Math.round(DA) + DP;
	var contribution = (tempContribution * 0.10).toFixed(2);
	var contributionNps = (tempContribution * 0.14).toFixed(2);

	row.find('.da').val(Math.round(DA));
	row.find('.contribution').val(Math.round(contribution));
	row.find('.emprContribution').val(Math.ceil(contributionNps));
});

$("#trnDCPSTable").on(
		'blur',
		".endDate, .startDate,.payCommission",
		function() {
			var row = $(this).closest("tr");
			var startDate = row.find('.startDate').val();
			var endDate = row.find('.endDate').val();
			var sevaarthId = row.find('.sevaarthId').val();
			var typeOfPayment = row.find('.typeOfPayment').val();
			var payCommission = row.find('.payCommission').val();

			var monthId = $('#monthId1').val();
			var finYearId = $('#finYearId').val();
			var noOfDays = (new Date(endDate) - new Date(startDate))
					/ (1000 * 60 * 60 * 24) + 1;

			if (endDate != '' && (startDate > endDate)) {
				swal('Select start date less than or equal to End Date');
				row.find('.startDate').val("");
				row.find('.endDate').val("");
			}else if(startDate!='' && endDate!=null && !checkSameMonth(row)){
				 row.find('.startDate').val("");
				 row.find('.endDate').val("");
			} else if(startDate!='' && endDate!=null && !checkFutureDate(row)){
				 row.find('.startDate').val("");
				 row.find('.endDate').val("");
			} else if(isValidDate(new Date(startDate)) && isValidDate(new Date(endDate)) ) {
			
				var params = {
					startDate : startDate,
					endDate : endDate,
					sevaarthId : sevaarthId,
					typeOfPayment : typeOfPayment,
					payCommission : payCommission,
					monthId : monthId,
					finYearId : finYearId
				};

				$
						.ajax({
							type : "POST",
							url : contextPath + '/ddoast/calculateDcpsArrear',
							data : JSON.stringify(params),
							contentType : 'application/json',
							error : function(xhr, status, error) {
								console.error('Error occurred:', error);
							},
							success : function(data) {
								console.log(data);
								if (data.length !== 0) {
									
									row.find('.basicPay').val(Math.round(data.basicPay));
									
									row.find('.da').val(Math.round(data.da));
									
									row.find('.daRate').val(Math.round(data.daRate));
									
									row.find('.contribution').val(Math.round(data.contribution));
									
									row.find('.emprContribution').val(Math.round(data.emprContribution));
								}
							}
						});
			}
		});

$(document).on(
		'click',
		'.addRow',
		function() {

			var rowIndex = $('#trnDCPSTable>tr').length;

			var currentRow = $(this).closest('tr').clone();
			currentRow.find(".employeeName").attr("id",
					"employeeName" + rowIndex);

			currentRow.find('.dcpsNO').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].dcpsNO').attr(
					'id', 'dcpsNO' + rowIndex);

			currentRow.find('.startDate').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].startDate')
					.attr('id', 'startDate' + rowIndex).val('');

			currentRow.find('.endDate').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].endDate').attr(
					'id', 'endDate' + rowIndex).val('');

			currentRow.find('.payCommission').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].payCommission')
					.attr('id', 'payCommission' + rowIndex).val("0");

			currentRow.find('.dcpContributionId').attr(
					'name',
					'lstDcpContributionModel[' + rowIndex
							+ '].dcpContributionId').attr('id',
					'dcpContributionId' + rowIndex);
			// .val("0");

			currentRow.find('.typeOfPayment').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].typeOfPayment')
					.attr('id', 'typeOfPayment' + rowIndex).val("0");

			currentRow.find('.basicPay').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].basicPay').attr(
					'id', 'basicPay' + rowIndex);
			//.val("0");

			currentRow.find('.sevaarthId').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].sevaarthId')
					.attr('id', 'sevaarthId' + rowIndex);

			currentRow.find('.dcpEmpId').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].dcpEmpId').attr(
					'id', 'dcpEmpId' + rowIndex);

			currentRow.find('.daRate').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].daRate').attr(
					'id', 'daRate' + rowIndex);

			currentRow.find('.da').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].da').attr('id',
					'da' + rowIndex);

			currentRow.find('.contribution').attr('name',
					'lstDcpContributionModel[' + rowIndex + '].contribution')
					.attr('id', 'contribution' + rowIndex).val("0");

			currentRow.find('.emprContribution').attr(
					'name',
					'lstDcpContributionModel[' + rowIndex
							+ '].emprContribution').attr('id',
					'emprContribution' + rowIndex).val("0");

			currentRow.find('.hidBasic').attr('id', 'hidBasic' + rowIndex);

			currentRow.find('.regStatus').attr('text', 'Not Saved');

			$('#trnDCPSTable').append(currentRow);
		});
$(document).on('click', '.deleteRow', function() {
    var dcpsempId = $(this).attr("data-dcpContributionId");
    
    // SweetAlert confirmation
    
    swal({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            // Append the ID to the hidden input
            var currentIds = $("#deleteDcpContributionId").val();
            
            if(currentIds && currentIds!=null  && currentIds!=undefined){
            	 if (!currentIds.split(",").includes(dcpsempId)) {
                     $("#deleteDcpContributionId").val(currentIds + dcpsempId + ",");
                 }
            }
           

            // Remove the row from the table
            $(this).closest('tr').remove();

            // Optionally show a success message
            swal("Deleted!", "Your contribution has been deleted.", "success");
        }
    });
    
    
});

/*$(document).on('click', '.deleteRow', function() {
	var dcpsempId = $(this).attr("data-dcpContributionId");
	if (dcpsempId) {
		$("#deleteDcpContributionId").append(dcpsempId + ",");
	}

	$(this).closest('tr').remove();
});*/

function addMonthYearComboForDelayed() {
	var user = $("#levelRoleVal").val().trim();
	var use = $("#Use").val().trim();

	if (user == 3 && use == "ViewAll") {
		var typeOfPaymentMaster = $("#typeOfPayment").val().trim();

		// Handle delayed month and year combo visibility
		if (typeOfPaymentMaster == '700047') {
			$("#delayedMonthAndYearCombos").show();
		} else {
			$("#delayedMonthAndYearCombos").hide();
		}

		// Handle DA Arrears Dates visibility
		if (typeOfPaymentMaster == '700048') {
			$("#DAArrearsDatesDivDisplay").show();
		} else {
			$("#DAArrearsDatesDivDisplay").hide();
		}

		// Handle Pay Arrears Dates visibility
		if (typeOfPaymentMaster == '700049') {
			$("#PayArrearsDatesDivDisplay").css("display", "inline");
		} else {
			$("#PayArrearsDatesDivDisplay").hide();
		}
	}
}

function funDdo1() {

	var billGroupId = $('#billGroupId').val();

	if (billGroupId != "0") {
		removeErrorClass($("#billGroupId"));
	}

	if (parseInt(billGroupId) > 0) {
		
		var roleId=$("#levelRoleVal").val();
		var url=contextPath + "/ddoast/getSchemeCodeByBillGroupId/"+ billGroupId;
		if(roleId=="2"){
			url=contextPath + "/ddo/getSchemeCodeByBillGroupId/"+ billGroupId;
		}else{
			url=contextPath + "/ddoast/getSchemeCodeByBillGroupId/"+ billGroupId;
		}
		$.ajax({
			type : "GET",
			url : url,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);
				$("#loaderMainNew").hide();
			},
			beforeSend : function() {
				$("#loaderMainNew").show();
			},
			complete : function(data) {
				$("#loaderMainNew").hide();
			},
			success : function(data) {
				console.log(data);
				$("#txtSchemeName").val(data[0].schemeName);
				$("#schemeCode").val(data[0].schemeCode);
				//$("#txtSubSchemeName").val("-");
			}
		});
	}

}

var status = false;




function searchRejectedContri(e) {
	//	e.preventDefault();

	var flag = true;

	$('#action').val('SEARCH_EMP');

	var paymentType = $("#typeOfPayment").val();
	var yearId = $("#finYearId").val();
	var monthId = $("#monthId1").val();
	var delayedMonthId = $("#delayedMonthId").val();
	var delayedFinYearId = $("#delayedFinYearId").val();
	var txtSchemeName = $("#txtSchemeName").val();
	var billGroupId = $("#billGroupId").val();

	if (paymentType === "" || paymentType === '0') {
		swal('Please Select Payment Type');
		flag = false;
	}
	if (yearId === "0") {
		swal('Please select pay year');
		flag = false;
	}
	if (monthId === "0") {
		swal('Please select pay month');
		flag = false;
	}

	if (paymentType == "700047") {
		if (delayedMonthId === "0") {
			swal('Please select delayed pay month');
			flag = false;
		}

		if (delayedFinYearId === "0") {
			swal('Please select delayed pay year');
			flag = false;
		}
		
		var currentMonthAndYearDate = new Date();
		var yearCode = parseInt(yearId) + 1999;
		if (monthId == 1 || monthId == 2 || monthId == 3) {
			yearCode = Number(yearCode) + 1;
		}
		currentMonthAndYearDate.setFullYear(yearCode, monthId, 1);
		currentMonthAndYearDate.setMonth(currentMonthAndYearDate.getMonth() - 1);

		var delayedMonthAndYearDate = new Date();
		var DelayedYearCode = parseInt(delayedFinYearId) + 1999;
		if (delayedMonthId == 1 || delayedMonthId == 2 || delayedMonthId == 3) {
			DelayedYearCode = Number(DelayedYearCode) + 1;
		}
		delayedMonthAndYearDate.setFullYear(DelayedYearCode, delayedMonthId, 1);
		delayedMonthAndYearDate.setMonth(delayedMonthAndYearDate.getMonth() - 1);

		if (delayedMonthAndYearDate.getTime() >= currentMonthAndYearDate.getTime()) {
			swal('Delayed date must not be a future date. Please select past month and year.');
			
		$("#delayedMonthId").val("");
		$("#delayedFinYearId").val("");
			flag = false;
		}
	}

	if (txtSchemeName === '') {
		swal('Scheme name is not blank  ');
		flag = false;
	}

	if (billGroupId === '0') {
		swal('Please Select Bill Group');
		flag = false;
	}

	if (flag) {
		$(".trnDCPSTable").empty();
		$("#onlineEntryForm").submit();
		return true;
	}
}


function SearchEmployee(e) {
	//	e.preventDefault();

	var flag = true;

	$('#action').val('SEARCH_EMP');

	var paymentType = $("#typeOfPayment").val();
	var yearId = $("#finYearId").val();
	var monthId = $("#monthId1").val();
	var delayedMonthId = $("#delayedMonthId").val();
	var delayedFinYearId = $("#delayedFinYearId").val();
	var txtSchemeName = $("#txtSchemeName").val();
	var billGroupId = $("#billGroupId").val();

	if (paymentType === "" || paymentType === '0') {
		swal('Please Select Payment Type');
		flag = false;
	}
	if (yearId === "0") {
		swal('Please select pay year');
		flag = false;
	}
	if (monthId === "0") {
		swal('Please select pay month');
		flag = false;
	}

	if (paymentType == "700047") {
		if (delayedMonthId === "0") {
			swal('Please select delayed pay month');
			flag = false;
		}

		if (delayedFinYearId === "0") {
			swal('Please select delayed pay year');
			flag = false;
		}
		
		var currentMonthAndYearDate = new Date();
		var yearCode = parseInt(yearId) + 1999;
		if (monthId == 1 || monthId == 2 || monthId == 3) {
			yearCode = Number(yearCode) + 1;
		}
		currentMonthAndYearDate.setFullYear(yearCode, monthId, 1);
		currentMonthAndYearDate.setMonth(currentMonthAndYearDate.getMonth() - 1);

		var delayedMonthAndYearDate = new Date();
		var DelayedYearCode = parseInt(delayedFinYearId) + 1999;
		if (delayedMonthId == 1 || delayedMonthId == 2 || delayedMonthId == 3) {
			DelayedYearCode = Number(DelayedYearCode) + 1;
		}
		delayedMonthAndYearDate.setFullYear(DelayedYearCode, delayedMonthId, 1);
		delayedMonthAndYearDate.setMonth(delayedMonthAndYearDate.getMonth() - 1);

		if (delayedMonthAndYearDate.getTime() >= currentMonthAndYearDate.getTime()) {
			swal('Delayed date must not be a future date. Please select past month and year.');
			
		$("#delayedMonthId").val("");
		$("#delayedFinYearId").val("");
			flag = false;
		}
	}

	if (txtSchemeName === '') {
		swal('Scheme name is not blank  ');
		flag = false;
	}

	if (billGroupId === '0') {
		swal('Please Select Bill Group');
		flag = false;
	}

	if (flag) {
		$(".trnDCPSTable").empty();
		$("#onlineEntryForm").submit();
		return true;
	}
}



var status = false;

function searchDetailsForApprove(e) {
	//	e.preventDefault();

	var flag = true;

	$('#action').val('SEARCH_EMP');

	var paymentType = $("#typeOfPayment").val();
	var yearId = $("#finYearId").val();
	var monthId = $("#monthId1").val();
	var delayedMonthId = $("#delayedMonthId").val();
	var delayedFinYearId = $("#delayedFinYearId").val();
	var txtSchemeName = $("#txtSchemeName").val();
	var billGroupId = $("#billGroupId").val();

	if (yearId === "0") {
		swal('Please select pay year');
		flag = false;
	}
	if (monthId === "0") {
		swal('Please select pay month');
		flag = false;
	}


	if (txtSchemeName === '') {
		swal('Scheme name is not blank  ');
		flag = false;
	}

	if (billGroupId === '0') {
		swal('Please Select Bill Group');
		flag = false;
	}

	if (flag) {
		$(".trnDCPSTable").empty();
		$("#onlineEntryForm").submit();
		return true;
	}
}

/*
 $(document).ready(function() {
 $("form[name='onlineEntryForm']").validate({
 ignore : "",
 rules : {
 cmbTreasuryCode : {
 required : true,
 },
 ddoCode : {
 required : true,
 },
 billGroupId : {
 required : true,
 },
 txtSchemeName : {
 required : true,
 },
 txtSubSchemeName : {
 required : true,
 },
 monthId : {
 required : true,
 },
 finYearId : {
 required : true,
 },

 },
 messages : {
 cmbTreasuryCode : {
 required : "Please select Treasury Name",
 },
 ddoCode : {
 required : "Please select DDO Name",
 },
 billGroupId : {
 required : "Please select Bill Group",
 },
 txtSchemeName : {
 required : "Please Insert Scheme",
 },
 txtSubSchemeName : {
 required : "Please Insert Sub Scheme",
 },
 monthId : {
 required : "Please select Pay Month",
 },
 finYearId : {
 required : "Please select Pay year",
 },

 },
 submitHandler : function(form) {
 form.submit();
 }
 });

 });*/





function checkSameMonth(row) {
	
//	var row = $(this).closest("tr");
	var startDate = row.find('.startDate').val();
	var endDate = row.find('.endDate').val();
	var sevaarthId = row.find('.sevaarthId').val();
	var typeOfPayment = row.find('.typeOfPayment').val();
	var payCommission = row.find('.payCommission').val();

	var monthId = $('#monthId1').val();
	var finYearId = $('#finYearId').val();
	var noOfDays = (new Date(endDate) - new Date(startDate))
			/ (1000 * 60 * 60 * 24) + 1;


    var fromDateObj = new Date(startDate);
    var toDateObj = new Date(endDate);

    if(isValidDate(fromDateObj) && isValidDate(toDateObj)){
    	if (isNaN(fromDateObj.getTime()) && isNaN(toDateObj.getTime())) {
            swal("Invalid date format.");
            return false;
        }

    	
    	if($('#typeOfPayment').val()=="700047"){  
    		if (fromDateObj.getFullYear() !== toDateObj.getFullYear()) {
                swal("Please Select same year in a row");
            	row.find('.startDate').val("");
            	row.find('.endDate').val("");
                return false;
            }
            
            if (fromDateObj.getMonth() !== toDateObj.getMonth()) {
                swal("Please Select same month in a row");
                return false;
            }
    	}
        
    }
    
    
    
    return true;
}





function checkFutureDate(row) {
	
	var startDate = row.find('.startDate').val();
	var endDate = row.find('.endDate').val();

	var monthId = $('#monthId1').val();  //1-jan 12 dec
	var finYearId = $('#finYearId').val();  //24 for 2025  23 for 2024


    var fromDateObj = new Date(startDate);
    var toDateObj = new Date(endDate);
  

    // Create date based on monthId and finYearId (not future)
    var futureYear = parseInt(finYearId) + 1999; // Adjust to map finYearId to actual year 
    var futureMonth = parseInt(monthId) - 1; // monthId is 1-based, so subtract 1 for Date object

    var monthYearDate = new Date(futureYear, futureMonth);
    monthYearDate.setHours(0, 0, 0, 0); // Set time to midnight for comparison

    if(isValidDate(fromDateObj) && isValidDate(toDateObj)){
    	  if (isNaN(fromDateObj.getTime()) && isNaN(toDateObj.getTime())) {
    	        swal("Invalid date format.");
    	        return false;
    	    }

    	    if (fromDateObj.getTime() > monthYearDate.getTime() || toDateObj.getTime() > monthYearDate.getTime()) {
    	        swal("Please do not select future dates.");
    	        row.find('.startDate').val("");
            	row.find('.endDate').val("");
    	        return false;
    	    }
    }
  
    
    return true;
}


function checkOverlapForRegular(counter) {
    var one_day = 1000 * 60 * 60 * 24;
    var startDateStr = $("#txtStartDate" + counter).val().trim();
    var endDateStr = $("#txtEndDate" + counter).val().trim();

    if (startDateStr !== "" && endDateStr !== "") {
        var startDate = new Date(startDateStr);
        var endDate = new Date(endDateStr);

        var totalRows = $('#trnDCPSTable>tr').length;
        
        for (var i = 1; i <= totalRows; i++) {
            if ($("#dcpsempId" + counter).val().trim() === $("#dcpsempId" + i).val().trim()) {
                var otherStartDateStr = $("#txtStartDate" + i).val().trim();
                var otherEndDateStr = $("#txtEndDate" + i).val().trim();

                if (otherStartDateStr !== "" && otherEndDateStr !== "") {
                    var otherStartDate = new Date(otherStartDateStr);
                    var otherEndDate = new Date(otherEndDateStr);

                    // Check for overlap
                    if (counter !== i && (
                        (startDate <= otherEndDate && startDate >= otherStartDate) || 
                        (endDate <= otherEndDate && endDate >= otherStartDate))) {
                        swal('The dates overlap for Regular Type in the same month.');
                        $("#txtStartDate" + counter).val("");
                        $("#txtEndDate" + counter).val("");
                        return false;
                    }
                }
            }
        }
    }
    return true;
}



$("#btnApprove")
.click(
		function(e) {
			e.preventDefault();
			$("#btnApprove").attr("disabled", true); 
			$("#loaderMainNew").show();
			var voucherNo=$("#txtVoucherNo").val();
			var voucherDate=$("#txtVoucherDate").val();
			
			var monthId = $('#monthId1').val();
			var finYearId = $('#finYearId').val();
			var delayedFinYearId = $('#delayedFinYearId').val();
			var delayedMonthId = $('#delayedMonthId').val();
			var billGroupId = $('#billGroupId').val();
			var typeOfPayment = $('#typeOfPayment').val();
			var ddoCode = $('#ddoCode').val();
			var cmbTreasuryCode = $('#cmbTreasuryCode').val();
			
			if (voucherNo == "" || voucherNo == "0") {
				e.preventDefault();
				swal("Please Enter Voucher Number");
			} else if (voucherDate == "" || voucherDate == "0") {
				e.preventDefault();
				swal("Please Enter Voucher Date");
			}
			else{
				$("#loaderMainNew").show();
				
				var params = {
					voucherNo : voucherNo,
					voucherDate : voucherDate,
					monthId : monthId,
					finYearId : finYearId,
					delayedFinYearId : delayedFinYearId,
					delayedMonthId : delayedMonthId,
					billGroupId : billGroupId,
					typeOfPayment : typeOfPayment,
					ddoCode : ddoCode,
					cmbTreasuryCode : cmbTreasuryCode
			 	};

				$.ajax({
							type : "POST",
							url : contextPath + '/ddo/addDcpsVoucherDtl',
							data : JSON.stringify(params),
							contentType : 'application/json',
							error : function(xhr, status, error) {
								console.error('Error occurred:', error);
								$("#loaderMainNew").hide();
							},
							success : function(data) {
								$("#loaderMainNew").hide();
								console.log(data);
								if (data.length !== 0) {
									 swal("Dcps detail Approved Successfully");
									 
									$("#txtVoucherNo").val("");
									$("#txtVoucherDate").val("");
										
									 
									 setTimeout(
												function() {
													location
															.reload(true);
												}, 1000);
								}
							}
						});
			
				
				}
			$("#loaderMainNew").hide();
		});




$("#btnReject").click(function(e) {
			e.preventDefault();
			$("#btnApprove").attr("disabled", true); 
			$("#loaderMainNew").show();
			
			var monthId = $('#monthId1').val();
			var finYearId = $('#finYearId').val();
			var billGroupId = $('#billGroupId').val();
			var ddoCode = $('#ddoCode').val();
			var cmbTreasuryCode = $('#cmbTreasuryCode').val();
           			
			if (finYearId == "" || finYearId == "0") {
				e.preventDefault();
				swal("Please Select Financial year");
			} else if (monthId == "" || monthId == "0") {
				e.preventDefault();
				swal("Please select Financial month");
			}
			else{
				$("#loaderMainNew").show();
				
				var params = {
					monthId : monthId,
					finYearId : finYearId,
					billGroupId : billGroupId,
					ddoCode : ddoCode,
					cmbTreasuryCode : cmbTreasuryCode
			 	};

				$.ajax({
							type : "POST",
							url : contextPath + '/ddo/rejectContribution',
							data : JSON.stringify(params),
							contentType : 'application/json',
							error : function(xhr, status, error) {
								console.error('Error occurred:', error);
								$("#loaderMainNew").hide();
							},
							success : function(data) {
								$("#loaderMainNew").hide();
								console.log(data);
								if (data.length !== 0) {
									 swal("Dcps Contribution rejected Successfully");
									 setTimeout(
												function() {
													location
															.reload(true);
												}, 1000);
								}
							}
						});
			
				
				}
			$("#loaderMainNew").hide();
		});


