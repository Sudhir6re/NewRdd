//$("#btnCalculateArrear").click(function(){
//	var formData = $("#pensionBillForm").serializeArray();
//	$.ajax({
//	    type: 'POST',
//	    url: "../pension/saveArreasDtls",
//	    data: JSON.stringify(formData),
//	    //data: formData,
//	    success: function(data) { alert('data: ' + data); },
//	    contentType: "application/json",
//	   // dataType: 'json'
//	});
//});



//Pension Basic Details calculation
//txtOldBasicEffFrom
$("#BasicPnsnDtl").on("change", ".txtOldBasicEffTo", function() {
	
	  var row =$(this).closest("tr");
	  var cmbRevisionType =row.find(".cmbRevisionType").val();
	  var txtOldBasic=row.find(".txtOldBasic").val();
	  var txtNewBasic=row.find(".txtNewBasic").val();
	  
	  var monthlyDiffBasic=Number(txtNewBasic)-Number(txtOldBasic);
	  
	  var txtOldBasicEffFrom=row.find(".txtOldBasicEffFrom").val();
	  var txtOldBasicEffTo=row.find(".txtOldBasicEffTo").val();
	  
	  
	  var month=dateDiffInMonth(new Date(txtOldBasicEffFrom),new Date(txtOldBasicEffTo));
	  
	  var basicArrears=(Number(monthlyDiffBasic)*Number(month));
	  
	  
	  var daPercentage=$("#daPercentage").val();
	  
	  var  daArrears=(basicArrears+((basicArrears*daPercentage)/100));
	  
	  
	//  payCommissionCode
	  
	//  var daPercentage=
	  
	  //daAmt=(basicPensionAmt+((basicPensionAmt*percentage)/100));
	  $("#txtTIArrearAmt").val(daArrears);
	 // row.find(".checkbox").val(chk);
});


//Punishment Cut(Deduct from Basic Pension) calculation
$("#tblPnshmntCut").on("change", ".txtPnshmntToDate", function() {
	var row = $(this).closest("tr");
	var txtPnshmntAmount=row.find(".txtPnshmntAmount ").val();
	var txtPnshmntFromDate=row.find(".txtPnshmntFromDate").val();
	var txtPnshmntToDate=row.find(".txtPnshmntToDate").val();
	
	 var month=dateDiffInMonth(new Date(txtPnshmntFromDate),new Date(txtPnshmntToDate));
	 
	 var diffAmnt=(Number(month)*Number(txtPnshmntAmount));
	
	  //row.find(".checkbox").val(chk);
});


//CVP Details calculation
$("#tblCVPDtls").on("change", ".txtOldCVPEffTo", function() {
	var row = $(this).closest("tr");
	
	 var txtOldCVP=row.find(".txtOldCVP").val();
	  var txtNewCVP=row.find(".txtNewCVP").val();
	  
	  var monthlyCvpDiff=Number(txtNewCVP)-Number(txtOldCVP);
	  
	  
	var txtOldCVPEffFrom=row.find(".txtOldCVPEffFrom").val();
	var txtOldCVPEffTo=row.find(".txtOldCVPEffTo").val();
	
	var month=dateDiffInMonth(new Date(txtOldCVPEffFrom),new Date(txtOldCVPEffTo));
	
	var diffCvp=(Number(month)*Number(monthlyCvpDiff));
	$("#txtCommArrearAmt").val(diffCvp);
	//row.find(".checkbox").val(chk);
});

//Re-Employed Detail calculation
$("#ReEmp").on("change", ".cmbDAInPnsnSal", function() {
	var row = $(this).closest("tr");
	
	var cmbDAInPnsnSal=row.find(".cmbDAInPnsnSal").val();
	
	var txtOldReEmpEffFrom=row.find(".txtOldReEmpEffFrom").val();
	var txtOldReEmpEffTo=row.find(".txtOldReEmpEffTo").val();
	
	var month=dateDiffInMonth(new Date(txtOldReEmpEffFrom),new Date(txtOldReEmpEffTo));
	
	//$("#txtCommArrearAmt").val(diffCvp);
	
	//row.find(".checkbox").val(chk);
});


//Re-Employed Detail calculation
$("#tblAllowanceDtls").on("change", ".txtAllowanceEffTo", function() {
	var row = $(this).closest("tr");
	
	var cmbAllowanceType=row.find(".cmbAllowanceType").val();
	
	var txtOldAllowanceAmt=row.find(".txtOldAllowanceAmt").val();
	var txtNewAllowanceAmt=row.find(".txtNewAllowanceAmt").val();
	
	var monthlyOtherAllowDiff=Number(txtNewAllowanceAmt)-Number(txtOldAllowanceAmt);
	
	
	var txtAllowanceEffFrom=row.find(".txtAllowanceEffFrom").val();
	var txtAllowanceEffTo=row.find(".txtAllowanceEffTo").val();
	
	var month=dateDiffInMonth(new Date(txtAllowanceEffFrom),new Date(txtAllowanceEffTo));
	
	
	var diffOtherAllow=(Number(month)*Number(monthlyOtherAllowDiff));
	
	
	$("#txtOthrDiffArrearAmt").val(diffOtherAllow);
	
	//row.find(".checkbox").val(chk);
});


$("#btnCalculateArrear").click(function(){
	$("#myModal").modal("hide");
});















