
jQuery(document).ready(function($) {
	//$('#tblConsolidatePayBill').hide();
	//$('#tblShowPayBill').hide();
	
	if($("#schemeCode").length  && $("#schemeCode").is(":visible")){
		$("#schemeCode").select2();
	}
	
});


var paybillGenerationTransactionIdArr = []; 
var grossAmtAr = []; 
var netAmtAr = []; 

//var schemeNameArr; 
//var schemeCodeArr;
var statusArr;
// var department_allowdeduc_id = []; 
var ddoCodeArr=[];
$("#selectAll").change(function() {
	var isChecked = $(this).prop("checked");
	console.log(isChecked);
	if (isChecked == true) {
		$(".checkbox").prop("checked", true);
	} else if (isChecked == false) {
		$(".checkbox").prop("checked", false);
	}
});





//for reject consolidate paybill
$('#btnRejectConsolidatePaybill')
.click(
		function() {
			var paybillGenerationTrnId = $(
					'#radioid').val();
			var userId = $(
			'#userId').val();
			
			var billStatus=$('input[name="paybillId"]:checked').attr('data');
			
			if(billStatus==4){
				swal("Paybill is already Rejected");
			}
			else if(billStatus==3){
				swal("Approved Paybill can not Reject");
			}
			else if (paybillGenerationTrnId != '') {
				$
						.ajax({
							type : "GET",
							url : "../moderator/rejectChangeStatement/"
									+ paybillGenerationTrnId+"/"+userId,
							async : true,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								console.log(data);
								// alert(data);

								if ($("#is_changed")
										.val() == 1) {

									swal(
											"Change Statement Rejected Successfully",
											{
												icon : "success",
											});
									setTimeout(
											function() {
												location
														.reload(true);
											}, 3000);

								} else {
									swal(
											"Change Statement Rejected Successfully",
											{
												icon : "success",
											});
									setTimeout(
											function() {
												location
														.reload(true);
											}, 3000);
								}

							}
						});
			}
			
			
			
		});



$("#btnSearch").click(function (e){
	
	var schemeBillGroupId= $("#schemeCode").val();
	var yearName = $("#paybillYear").val();
	var monthName = $("#paybillMonth").val();
	
	 if (yearName == "" || yearName == "0") {
		e.preventDefault();
		swal.fire("Please select paybill year");
	}
	else if (monthName == "" || monthName == "0") {
		e.preventDefault();
		swal.fire("Please select paybill month");
	} 
	 else if (schemeBillGroupId == "" || schemeBillGroupId == "0") {
		e.preventDefault();
		swal.fire("Please select Bill Group");
	} 
	
});






$('#btnDeleteBill')
.click(
		function() {
			
			var consPaybillGenerationTrnId = $(
								'#radioid').val();

			 //alert(consPaybillGenerationTrnId);
			if (consPaybillGenerationTrnId != '') {
				$
						.ajax({
							type : "GET",
							url : "../ddo/deleteConsolidateBill/"
									+ consPaybillGenerationTrnId, 
							async : true,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								console.log(data);
								// alert(data);

								if ($("#is_changed")
										.val() == 1) {

									swal(
											"Consolidate Paybill has been deleted successfully",
											{
												icon : "success",
											});
									setTimeout(
											function() {
												location
														.reload(true);
											}, 2000);

								} else {
									swal(
											"Consolidate Paybill has been deleted successfully",
											{
												icon : "success",
											});
									setTimeout(
											function() {
												location
														.reload(true);
											}, 2000);
								}

							}
						});
			}
		});

