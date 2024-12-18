$("#radioBalOfAdvY").change(function() {
	var recovery=$("input[name='balOfAdv']:checked").val();
	if(recovery=='Y'){
		$("#txtTotalAdvanceBalAmt").prop("disabled",false);
	}else{
		$("#txtTotalAdvanceBalAmt").prop("disabled",true);
	}
		});


$("#radioBalOfAdvN").change(function() {
	var recovery=$("input[name='balOfAdv']:checked").val();
	if(recovery=='N'){
		$("#txtTotalAdvanceBalAmt").prop("disabled",true);
	}else{
		$("#txtTotalAdvanceBalAmt").prop("disabled",false);
	}
});


$("#txtEmolumentFromDate").change(function(){
	console.log($(this).val());
	var start = new Date($(this).val());
	for(var i=0;i<10;i++){
		if(i==0)
			start.setMonth(start.getMonth()+0);
		else
			start.setMonth(start.getMonth()+1);
		
		$("#txtPeriodFromDate"+i).val(dateToYMD(start));
	}
});



$("#bankId")
		.change(
				function() {
					var bankid = $("#bankId").val();
					//alert("DDO CODE is "+departmentId);
					//  alert("payScale CODE is "+payScale);

					if (bankid != '') {
						$
								.ajax({
									type : "GET",
									url : "../level1/fetchbankbranch/" + bankid,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											$('#cmbTargetBranchName').empty();
											$('#cmbTargetBranchName')
													.append(
															"<option value='0'>Please Select</option>");
											var temp = data;
											$
													.each(
															temp,
															function(index,
																	value) {
																console
																		.log(value[1]);
																$(
																		'#cmbTargetBranchName')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#cmbTargetBranchName').empty();
											$('#cmbTargetBranchName')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
					}

				});


//$("#txtDoWantCommute").prop("readOnly",true);
$("#cmbTargetBranchName").change(function(){
	var branchId=$(this).val();
		$.ajax({
			type : "GET",
			url : "../level1/getIfscCodeByBranchId/"
					+ branchId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
					$("#txtIFSCCode").val(data[0].ifscCode);
			   }
			}
		});
     });
$("#btnSavedraft").click(function(){
	
	//$('#caseForm').validate().settings.ignore = ".ignore";
	$('input[type=text]').addClass('ignore');
	$('select').addClass('ignore');
	
	if($("#empId").val()==''){
		$("#isActive").val("0");
	}else{
		  $("#caseForm").attr("action", "../updatePensDtls"); 
	}

});
$("#btnSaveForward").click(function(){
	if($("#empId").val()==''){
	$("#isActive").val("1");
}else{
	  $("#caseForm").attr("action", "../updatePensDtls"); 
}});
$("#btnFrwrd2AG").click(function(){
	if($("#empId").val()==''){
		$("#isActive").val("2");
	}else{
		$("#isActive").val("2");
		  $("#caseForm").attr("action", "../updatePensDtls"); 
	}});
$("#btnApproved").click(function(){
	if($("#empId").val()==''){
		$("#isActive").val("3");
	}else{
		$("#isActive").val("3");
		$("#caseForm").attr("action", "../updatePensDtls"); 
	}});






var relationList='<option value="0" >--Select--</option> <option value="Husband" > Husband</option> <option value="Wife" > Wife</option> <option value="Daughter" > Daughter</option> <option value="Son" > Son</option> <option value="Other" > Other</option> <option value="Sister" > Sister</option> <option value="Brother" > Brother</option> <option value="Father" > Father</option> <option value="Mother" > Mother</option> <option value="DG" > Defecto Guardian</option> <option value="CPDS" > Children of Predeceased Son</option> <option value="WDAU" > Widowed Daughters</option> <option value="WSIS" > Widow Sister</option> <option value="Spouse" > Spouse</option>';
$("#familyDtlsAddRow").click(function(){
	  /* var isFamilyDtls=$('input[name="retireReportEmpFam"]:checked').val();
		if(isFamilyDtls=="Y"){*/
		var index=$('#tblFamilyDtls  >tbody >tr').length;
		var srNo=$('#tblFamilyDtls  >tbody >tr').length+1;
		
		var nameOfFamMemb='<input type="text" class="form-control removeErrorFromInput nameOfFamMemb"    id="nameOfFamMemb'+index+'"     name="lstPensionEmpFamilyDtlsModel[' + index + '].nameOfFamMemb"       />';
		var relation='<select   id="relation'+index+'"     name="lstPensionEmpFamilyDtlsModel[' + index + '].relation"  class="form-control removeErrorFromDropdown relation"     >'+relationList+'</select>';
		var percentage='<input type="text" class="form-control float removeErrorFromInput percentage" id="percentage'+index+'"   name="lstPensionEmpFamilyDtlsModel[' + index + '].percentage"       />';
		var phyhandMentChal='<select id="phyhandMentChal' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].phyhandMentChal"       class="form-control  phyhandMentChal removeErrorFromDropdown"><option value="0">--Select--</option><option value="Y">Yes</option><option value="N" selected="selected">No</option></select>';
		var dob='<input type="date" class="form-control removeErrorFromDate dob" id="dob' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].dob"       />';
		var famPensioner='<input type="text" class="form-control famPensioner removeErrorFromInput" id="famPensioner' + index + '"   name="lstPensionEmpFamilyDtlsModel[' + index + '].famPensioner"  />';
		var minor='<input type="checkbox"  class="minor chk"  value="Y" id="minor' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].minor"    />';
		var married='<input type="checkbox" class="married chk" value="Y" id="married' + index + '"   name="lstPensionEmpFamilyDtlsModel[' + index + '].married"   />';
		var salary='<input type="text" class="form-control removeErrorFromInput salary"  id="salary' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].salary"      />';
		var guardianName='<input type="text" class="form-control removeErrorFromInput guardianName"  id="guardianName' + index + '"   name="lstPensionEmpFamilyDtlsModel[' + index + '].guardianName"      />';
		var dateOfDeath='<input type="date" class="form-control removeErrorFromDate dateOfDeath" id="dateOfDeath' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].dateOfDeath"  />';
		var deleteRow='<div  align="center" style="vertical-align: middle;"  data-className="tblFamilyDtls"  class="delete "  data-totalRow="'+index+'"><a><span class="glyphicon glyphicon-trash delete tblFamilyDtls"   data-className="tblFamilyDtls"   data-totalRow="'+index+'"></span></a>';
		
		$(".tblFamilyDtls").attr("data-totalRow",index);
		
		$("#tblFamilyDtls tbody").append("<tr>" + 
				"<td class='text-center'>"+famPensioner+"</td>" +
		        "<td>"+relation+"</td>" +
		        "<td>"+percentage+"</td>" +
		        "<td>"+phyhandMentChal+"</td>" +
		        "<td>"+dob+"</td>" +
		        "<td class='text-center'>"+nameOfFamMemb+"</td>" +
		        "<td class='text-center'>"+minor+"</td>" +
		        "<td class='text-center'>"+married+"</td>" +
		        "<td class='text-right'>"+salary+"</td>" +
		        "<td>"+guardianName+"</td>" +
		        "<td>"+dateOfDeath+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");
	/*}else{
		 $('#tblFamilyDtls tbody').empty();
	}*/
});









$("#NomineeDtlsAddRow").click(function(){
	var index=$('#tblNomineeDtls  >tbody >tr').length;
	var srNo=$('#tblNomineeDtls  >tbody >tr').length+1;
	
	var nameOfFamMemb='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpNomineeDtlsModel' + index + 'nameOfFamMemb"  name="lstPensionEmpNomineeDtlsModel[' + index + '].nameOfFamMemb"       />';
	var relation='<select id="lstPensionEmpFamilyDtlsModel' + index + 'relation"    name="lstPensionEmpFamilyDtlsModel[' + index + '].relation"  class="form-control removeErrorFromDropdown"    >'+relationList+'</select>';
	var percentage='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionEmpNomineeDtlsModel' + index + 'percentage"   name="lstPensionEmpNomineeDtlsModel[' + index + '].percentage"       />';
	var alternateNominee='<input type="text" class="form-control removeErrorFromInput"  id="lstPensionEmpNomineeDtlsModel' + index + 'alternateNominee"  name="lstPensionEmpNomineeDtlsModel[' + index + '].alternateNominee"     />';
	var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="tblNomineeDtls" class="delete tblNomineeDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete tblNomineeDtls"   data-className="tblNomineeDtls"  data-totalRow="'+index+'"></span></a>';
	
	$("#tblNomineeDtls tbody").append("<tr>" + 
	        "<td>"+nameOfFamMemb+"</td>" +
	        "<td>"+relation+"</td>" +
	        "<td>"+percentage+"</td>" +
	        "<td>"+alternateNominee+"</td>" +
	        "<td>"+deleteRow+"</td>" +
	        "</tr>");
	
	$(".tblNomineeDtls").attr("data-totalRow",index);
});





$("#btnAdvDtlsAddRow").click(function(){
	    var isRecovery=$('input[name="balOfAdv"]:checked').val();
		if(isRecovery=="Y"){
		var index=$('#tblTypeAdvDtls  >tbody >tr').length;
		var srNo=$('#tblTypeAdvDtls  >tbody >tr').length+1;
		
		var Recovery = '<select  name="lstPensionEmpRecovDtlsModel[' + index + '].recoveryFromPension"  class="form-control removeErrorFromDropdown recoveryFromPension"><option value="0">Please Select</option><option value="P">Pension</option><option value="G">Grauity</option></select>';
		var typeOfRec='<select id="lstPensionEmpRecovDtlsModel' + index + 'typeOfRec"   name="lstPensionEmpRecovDtlsModel[' + index + '].typeOfRec"    class="form-control removeErrorFromDropdown"><option value="0">--Select--</option><option value="Government Accommodation" title="Government Accommodation">Government Accommodation</option><option value="Balance of House Building Advance" title="Balance of House Building Advance">Balance of House Building Advance</option><option value="Interest on House Building Advance" title="Interest on House Building Advance">Interest on House Building Advance</option><option value="Balance of Conveyance Advance" title="Balance of Conveyance Advance">Balance of Conveyance Advance</option><option value="Interest on Conveyance Advance" title="Interest on Conveyance Advance">Interest on Conveyance Advance</option><option value="over payment of pay and allowances" title="Over payment of pay and allowances">Over payment of pay and allowances</option><option value="Income tax" title="Income tax">Income tax</option><option value="Dues referred to in Rule 134 of M.C.S" title="Dues referred to in Rule 134 of M.C.S">Dues referred to in Rule 134 of M.C.S</option><option value="Recovery of share of Management contribution to Provident Fund" title="Recovery of share of Management contribution to Provident Fund">Recovery of share of Management contribution to Provident Fund</option><option value="Other" title="Others">Others</option></select>';
		var amount='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'amount"  name="lstPensionEmpRecovDtlsModel[' + index + '].amount"     />';
		var schemeCode='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'schemeCode"  name="lstPensionEmpRecovDtlsModel[' + index + '].schemeCode"     />';
		var recoveryFromDate='<input type="date" class="form-control removeErrorFromDate" id="lstPensionEmpRecovDtlsModel' + index + 'recoveryFromDate"  name="lstPensionEmpRecovDtlsModel[' + index + '].recoveryFromDate"     />';
		var recoveryToDate='<input type="date" class="form-control removeErrorFromDate" id="lstPensionEmpRecovDtlsModel' + index + 'recoveryToDate"  name="lstPensionEmpRecovDtlsModel[' + index + '].recoveryToDate"     />';
		
		var deleteRow='<div  align="center" style="vertical-align: middle;"  data-className="tblTypeAdvDtls"  class="delete tblTypeAdvDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete tblTypeAdvDtls"   data-className="tblTypeAdvDtls"   data-totalRow="'+index+'"></span></a>';
		
		$("#tblTypeAdvDtls tbody").append("<tr>" + 
		        "<td>"+Recovery+"</td>" +
		        "<td>"+typeOfRec+"</td>" +
		        "<td>"+amount+"</td>" +
		     //   "<td>"+schemeCode+"</td>" +
		        "<td>"+recoveryFromDate+"</td>" +
		        "<td>"+recoveryToDate+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");
		
		$(".tblTypeAdvDtls").attr("data-totalRow",index);
	}else{
		$("#tblTypeAdvDtls tbody").empty();
	}
});



$("#authoDtlsAddRow").click(function(){
	
	var index=$('#tblAuthoTypeAdvDtls  >tbody >tr').length;
	var srNo=$('#tblAuthoTypeAdvDtls  >tbody >tr').length+1;
	var orderType='<select id="lstPensionAuthDtlsModel' + index + 'orderType"  name="lstPensionAuthDtlsModel[' + index + '].orderType"  class="form-control removeErrorFromDropdown"><option value="0">--Select--</option><option value="Government Accommodation" title="Government Accommodation">Government Accommodation</option><option value="Balance of House Building Advance" title="Balance of House Building Advance">Balance of House Building Advance</option><option value="Interest on House Building Advance" title="Interest on House Building Advance">Interest on House Building Advance</option><option value="Balance of Conveyance Advance" title="Balance of Conveyance Advance">Balance of Conveyance Advance</option><option value="Interest on Conveyance Advance" title="Interest on Conveyance Advance">Interest on Conveyance Advance</option><option value="over payment of pay and allowances" title="Over payment of pay and allowances">Over payment of pay and allowances</option><option value="Income tax" title="Income tax">Income tax</option><option value="Dues referred to in Rule 134 of M.C.S" title="Dues referred to in Rule 134 of M.C.S">Dues referred to in Rule 134 of M.C.S</option><option value="Recovery of share of Management contribution to Provident Fund" title="Recovery of share of Management contribution to Provident Fund">Recovery of share of Management contribution to Provident Fund</option><option value="Other" title="Others">Others</option></select>';
	var orderNo='<input type="text" class="form-control removeErrorFromInput"  id="lstPensionAuthDtlsModel' + index + 'orderNo" name="lstPensionAuthDtlsModel[' + index + '].orderNo"     />';
	var orderDate='<input type="date" class="form-control removeErrorFromDate"  id="lstPensionAuthDtlsModel' + index + 'orderDate"  name="lstPensionAuthDtlsModel[' + index + '].orderDate"     />';
	var pensionerName='<input type="text" class="form-control  removeErrorFromInput" id="lstPensionAuthDtlsModel' + index + 'pensionerName"  name="lstPensionAuthDtlsModel[' + index + '].pensionerName"     />';
	
	var basicAmt='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionAuthDtlsModel' + index + 'basicAmt"   name="lstPensionAuthDtlsModel[' + index + '].basicAmt"     />';
	var enhFamPensAmt='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionAuthDtlsModel' + index + 'enhFamPensAmt"   name="lstPensionAuthDtlsModel[' + index + '].enhFamPensAmt"     />';
	var famPensAmt='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionAuthDtlsModel' + index + 'famPensAmt"  name="lstPensionAuthDtlsModel[' + index + '].famPensAmt"     />';
	
	var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="tblAuthoTypeAdvDtls"  class="delete tblAuthoTypeAdvDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete tblAuthoTypeAdvDtls"  data-className="tblAuthoTypeAdvDtls"   data-totalRow="'+index+'"></span></a>';
	
	$("#tblAuthoTypeAdvDtls tbody").append("<tr>" + 
	        "<td>"+orderType+"</td>" +
	        "<td>"+orderNo+"</td>" +
	        "<td>"+orderDate+"</td>" +
	        "<td>"+pensionerName+"</td>" +
	        "<td>"+basicAmt+"</td>" +
	        "<td>"+enhFamPensAmt+"</td>" +
	        "<td>"+famPensAmt+"</td>" +
	        "<td>"+deleteRow+"</td>" +
	        "</tr>");
	
	$(".tblAuthoTypeAdvDtls").attr("data-totalRow",index);

});









$("#addForeignServiceDtlRow").click(function(){
	var isForeignService=$('input[name="foreignSrvcOrDeput"]:checked').val();
	if(isForeignService=="Y"){
		var index=$('#tblForeignServDtls  >tbody >tr').length;
		var srNo=$('#tblForeignServDtls  >tbody >tr').length+1;
		var durationFromDate='<input type="date" class="form-control removeErrorFromDate"  id="lstPensionEmpForeignServiceDtlsModel' + index + 'durationFromDate" name="lstPensionEmpForeignServiceDtlsModel[' + index + '].durationFromDate"     />';
		var durationToDate='<input type="date" class="form-control removeErrorFromDate" id="lstPensionEmpForeignServiceDtlsModel' + index + 'durationToDate"   name="lstPensionEmpForeignServiceDtlsModel[' + index + '].durationToDate"     />';
		var totalAmount='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionEmpForeignServiceDtlsModel' + index + 'totalAmount"  name="lstPensionEmpForeignServiceDtlsModel[' + index + '].totalAmount"     />';
		var paidAmount='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionEmpForeignServiceDtlsModel' + index + 'paidAmount"  name="lstPensionEmpForeignServiceDtlsModel[' + index + '].paidAmount"     />';
		var challanOrDDNo='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpForeignServiceDtlsModel' + index + 'challanOrDDNo"  name="lstPensionEmpForeignServiceDtlsModel[' + index + '].challanOrDDNo"     />';
		var challanDate='<input type="date" class="form-control removeErrorFromDate"  id="lstPensionEmpForeignServiceDtlsModel' + index + 'challanDate"  name="lstPensionEmpForeignServiceDtlsModel[' + index + '].challanDate"     />';
		var deputedOffcName='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpForeignServiceDtlsModel' + index + 'deputedOffcName"  name="lstPensionEmpForeignServiceDtlsModel[' + index + '].deputedOffcName"    />';
		var deleteRow='<div  align="center" style="vertical-align: middle;" class="delete tblForeignServDtls"  data-className="tblForeignServDtls"    data-totalRow="'+index+'"  ><a><span class="glyphicon glyphicon-trash delete tblForeignServDtls"   data-className="tblForeignServDtls"  data-totalRow="'+index+'"></span></a>';
		
		$("#tblForeignServDtls tbody").append("<tr>" + 
		        "<td>"+srNo+"</td>" +
		        "<td>"+durationFromDate+"</td>" +
		        "<td>"+durationToDate+"</td>" +
		        "<td>"+totalAmount+"</td>" +
		        "<td>"+paidAmount+"</td>" +
		        "<td>"+challanOrDDNo+"</td>" +
		        "<td>"+challanDate+"</td>" +
		        "<td>"+deputedOffcName+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");

		$(".tblForeignServDtls").attr("data-totalRow",index);
	}else{
		  $('#tblForeignServDtls tbody').empty();
	}
 });



function provisionalPaidTableAddRow(){
	var isPrvsnlPnsnDtls=$('input[name="foreignSrvcOrDeput"]:checked').val();
	if(isPrvsnlPnsnDtls=="Y"){
	var index=$('#tblPrvsnlPnsnDtls  >tbody >tr').length;
	var srNo=$('#tblPrvsnlPnsnDtls  >tbody >tr').length+1;
	
	var sancAuthName='<select class="form-control removeErrorFromInput"  id="lstPensionEmpProvisionalDtlsModel' + index + 'sancAuthName"  name="lstPensionEmpProvisionalDtlsModel[' + index + '].sancAuthName"    ><option value="0">--Select--</option><option value="O">DAT</option><option value="M">AG Mumbai</option><option value="N">AG Nagpur</option></select>';
	var sancAuthNo='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionEmpProvisionalDtlsModel' + index + 'sancAuthNo"  name="lstPensionEmpProvisionalDtlsModel[' + index + '].sancAuthNo"     />';
	var sanctionDate='<input type="date" class="form-control removeErrorFromDate"  id="lstPensionEmpProvisionalDtlsModel' + index + 'sanctionDate" name="lstPensionEmpProvisionalDtlsModel[' + index + '].sanctionDate"     />';
	var billType='<select id="lstPensionEmpProvisionalDtlsModel' + index + 'billType"   name="lstPensionEmpProvisionalDtlsModel[' + index + '].billType"     class="form-control removeErrorFromDropdown" ><option value="0">--Select--</option><option value="P">Pension</option><option value="G">Gratuity</option></select>';
	var amount='<input type="text" class="form-control float removeErrorFromInput"  id="lstPensionEmpProvisionalDtlsModel' + index + 'amount" name="lstPensionEmpProvisionalDtlsModel[' + index + '].amount"     />';
	var voucherNo='<input type="text" class="form-control removeErrorFromInput"  id="lstPensionEmpProvisionalDtlsModel' + index + 'voucherNo" name="lstPensionEmpProvisionalDtlsModel[' + index + '].voucherNo"    />';
	var voucherDate='<input type="date" class="form-control removeErrorFromDate"  id="lstPensionEmpProvisionalDtlsModel' + index + 'voucherDate"   name="lstPensionEmpProvisionalDtlsModel[' + index + '].voucherDate"    />';
	var deleteRow='<div  align="center" style="vertical-align: middle;" class="delete tblPrvsnlPnsnDtls"  data-className="tblPrvsnlPnsnDtls"    data-totalRow="'+index+'" ><a><span class="glyphicon glyphicon-trash delete tblPrvsnlPnsnDtls" data-className="tblPrvsnlPnsnDtls"  data-totalRow="'+index+'"></span></a>';
	
	$("#tblPrvsnlPnsnDtls tbody").append("<tr>" + 
	        "<td>"+sancAuthName+"</td>" +
	        "<td>"+sancAuthNo+"</td>" +
	        "<td>"+sanctionDate+"</td>" +
	        "<td>"+billType+"</td>" +
	        "<td>"+amount+"</td>" +
	        "<td>"+voucherNo+"</td>" +
	        "<td>"+voucherDate+"</td>" +
	        "<td>"+deleteRow+"</td>" +
	        "</tr>");
	
	$(".tblPrvsnlPnsnDtls").attr("data-totalRow",index);
	}else{
		  $('#tblPrvsnlPnsnDtls tbody').empty();
	}
}



function eventDtlsTableAddRow(){
	
	var index=$('#tblEventDtls  >tbody >tr').length;
	var srNo=$('#tblEventDtls  >tbody >tr').length+1;
	
//	var dynamicId='lstPensionEmpEventDtlsModel[' + index + ']';
	
	var event='<input type="text" class="form-control removeErrorFromInput"  id="lstPensionEmpEventDtlsModel' + index + 'event"  name="lstPensionEmpEventDtlsModel[' + index + '].event"     />';
	var payscaleId='<input type="text" class="form-control removeErrorFromInput"   id="lstPensionEmpEventDtlsModel' + index + 'payscaleId"  name="lstPensionEmpEventDtlsModel[' + index + '].payscaleId"     />';
	var basic='<input type="text" class="form-control float removeErrorFromInput" id="lstPensionEmpEventDtlsModel' + index + 'basic"  name="lstPensionEmpEventDtlsModel[' + index + '].basic"     />';
	var dpOrGp='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpEventDtlsModel' + index + 'dpOrGp"   name="lstPensionEmpEventDtlsModel[' + index + '].dpOrGp"     />';
	var fromDate='<input type="date" class="form-control removeErrorFromDate" id="lstPensionEmpEventDtlsModel' + index + 'fromDate"  name="lstPensionEmpEventDtlsModel[' + index + '].fromDate"    />';
	var toDate='<input type="date" class="form-control removeErrorFromDate" id="lstPensionEmpEventDtlsModel' + index + 'toDate"  name="lstPensionEmpEventDtlsModel[' + index + '].toDate"    />';
	var deleteRow='<div  align="center" style="vertical-align: middle;" class="delete tblEventDtls"  data-totalRow="'+index+'"   data-className="tblEventDtls"  ><a><span class="glyphicon glyphicon-trash delete tblEventDtls"  data-totalRow="'+index+'"></span></a>';
	
	$("#tblEventDtls tbody").append("<tr>" + 
			"<td>"+event+"</td>" +
			"<td>"+payscaleId+"</td>" +
			"<td>"+basic+"</td>" +
			"<td>"+dpOrGp+"</td>" +
			"<td>"+fromDate+"</td>" +
			"<td>"+toDate+"</td>" +
			"<td>"+deleteRow+"</td>" +
	"</tr>");
	
	$(".tblEventDtls").attr("data-totalRow",index);
}



function nonQualifyingServiceAddRow(){
	
	var index=$('#tblNonQualifyingService  >tbody >tr').length;
	var srNo=$('#tblNonQualifyingService  >tbody >tr').length+1;
	
	
	var typeOfBreak='<select   class="form-control removeErrorFromDropdown"   id="lstPensionEmpNonQualSrvcModel' + index + 'typeOfBreak"  name="lstPensionEmpNonQualSrvcModel[' + index + '].typeOfBreak" ><option value="0">--Select--</option><option value="10053" title="Half of Daily Rated/Daily Wages service"> Half of Daily Rated/Daily Wages service</option><option value="10054" title="Interruption in service condoned under rule 48 of the M.C.S.(P)rules,1982"> Interruption in service condoned under rule 48 of the M.C.S.(P)rules,1982</option><option value="10055" title="Extraordinary leave specifically sanctioned not to qualify for pension"> Extraordinary leave specifically sanctioned not to qualify for pension</option><option value="10056" title="Extraordinary leave on private grounds availed after 01/02/2001"> Extraordinary leave on private grounds availed after 01/02/2001</option><option value="10057" title="Suspension period not treated as qualifying service"> Suspension period not treated as qualifying service</option><option value="10058" title="Any other service not treated as qualifying service"> Any other service not treated as qualifying service</option><option value="10036" title="Others"> Others</option></select>';
	var withEffDate='<input type="date" class="form-control withEffDate removeErrorFromDate"  id="txtDateOfBrkFrom'+index+'" name="lstPensionEmpNonQualSrvcModel[' + index + '].withEffDate" onchange="findDiffBetwnTwoDts();"      />';
	var toDate='<input type="date" class="form-control toDate removeErrorFromDate"  id="txtDateOfBrkTo'+index+'"  name="lstPensionEmpNonQualSrvcModel[' + index + '].toDate"  onchange="findDiffBetwnTwoDts();"     />';
	var remark='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpNonQualSrvcModel' + index + 'remark"  name="lstPensionEmpNonQualSrvcModel[' + index + '].remark"      />';
	var deleteRow='<div  align="center" style="vertical-align: middle;" class="delete tblNonQualifyingService"  data-className="tblNonQualifyingService"   data-totalRow="'+index+'"  ><a><span class="glyphicon glyphicon-trash delete tblNonQualifyingService"  data-className="tblNonQualifyingService"  data-totalRow="'+index+'" ></span></a>';
	
	var dayDiff='<input type="hidden" id="hidDays'+index+'" class="hidDays" name="hidDays'+index+'" >';
	
	$("#tblNonQualifyingService tbody").append("<tr>" + 
			"<td>"+typeOfBreak+"</td>" +
			"<td>"+withEffDate+"</td>" +
			"<td>"+toDate+"</td>" +
			"<td>"+remark+"</td>" +
			"<td>"+deleteRow+dayDiff+"</td>" +
	"</tr>");
	
	$(".tblNonQualifyingService").attr("data-totalRow",index);
}





function tblQualifyingServiceAddRow(){
	
	var index=$('#tblQualifyingService  >tbody >tr').length;
	var srNo=$('#tblQualifyingService  >tbody >tr').length+1;
	
	var grNoRule='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpAddQualModel' + index + 'grNoRule"     name="lstPensionEmpAddQualModel[' + index + '].grNoRule"        />';
	var grDate='<input type="date" class="form-control removeErrorFromDate" id="lstPensionEmpAddQualModel' + index + 'grDate"  name="lstPensionEmpAddQualModel[' + index + '].grDate"     />';
	var addQualYear='<input type="text" class="form-control number addQualYear removeErrorFromInput" onblur="sumOfAllAddService();"  name="lstPensionEmpAddQualModel[' + index + '].addQualYear"  id="lstPensionEmpAddQualModel' + index +'addQualYear"      />';
	var addQualMonth='<input type="text" class="form-control number addQualMonth removeErrorFromInput"  onblur="sumOfAllAddService();" name="lstPensionEmpAddQualModel[' + index + '].addQualMonth"   id="lstPensionEmpAddQualModel' + index + 'addQualMonth"        />';
	var addQualDay='<input type="text" class="form-control number addQualDay removeErrorFromInput" onblur="sumOfAllAddService();"  name="lstPensionEmpAddQualModel[' + index + '].addQualDay"   id="lstPensionEmpAddQualModel' + index + 'addQualDay"         />';
	var remark='<input type="text" class="form-control removeErrorFromInput"  id="lstPensionEmpAddQualModel' + index + 'remark"   name="lstPensionEmpAddQualModel[' + index + '].remark"     />';
	var deleteRow='<div  align="center" style="vertical-align: middle;" class="delete tblQualifyingService"  data-className="tblQualifyingService"  data-totalRow="'+index+'" ><a><span class="glyphicon glyphicon-trash delete tblQualifyingService" data-className="tblQualifyingService"  data-totalRow="'+index+'"></span></a>';
	
	$("#tblQualifyingService tbody").append("<tr>" + 
			"<td>"+grNoRule+"</td>" +
			"<td>"+grDate+"</td>" +
			"<td>"+addQualYear+"</td>" +
			"<td>"+addQualMonth+"</td>" +
			"<td>"+addQualDay+"</td>" +
			"<td>"+remark+"</td>" +
			"<td>"+deleteRow+"</td>" +
	"</tr>");
	
	$(".tblQualifyingService").attr("data-totalRow",index);
}






$(document).on('click', '.delete', function(){ 
	 //var rowlen=Number($(this).attr("data-totalRow"))+1;
	
	 var tableName=$(this).attr("data-className");
	 var rowlen=$('#'+tableName+ ' >tbody >tr').length;
	 var currRow=$(this).closest("tr")[0].rowIndex;  
	
	if(Number(currRow)<Number(rowlen)){
		swal({
			  title: "Warning!",
			  text: "Delete last row first!",
			  icon: "warning",
			});
	}else{
		swal({
			  title: "Are you sure?",
			  text: "to delete this row",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  $(this).closest("tr").remove();
			  } else {
			  }
			});
		
		$("."+tableName).attr("data-totalRow",Number(rowlen)-1);
	}
});



$("#tblAvgPayCalc").on("keyup", ".txtAvgPayBasic,.txtAvgPayDP,.txtNPA", function() {
	  var row = $(this).closest("tr");
	  var txtAvgPayBasic = parseFloat(row.find(".txtAvgPayBasic").val());
	  var txtAvgPayDP = parseFloat(row.find(".txtAvgPayDP").val());
	  var txtNPA = parseFloat(row.find(".txtNPA").val());
	  
	  
	   txtAvgPayBasic = isNaN(txtAvgPayBasic) ? 0 : Number(txtAvgPayBasic);
	   txtAvgPayDP =isNaN(txtAvgPayDP) ? 0 : Number(txtAvgPayDP);
	   txtNPA = isNaN(txtNPA) ? 0 : Number(txtNPA);
	  
	   var total = txtAvgPayBasic+txtAvgPayDP+txtNPA;
	   row.find(".txtTotal").val(isNaN(total) ? "" : total);
	   
	      var txtGrandTotal=0;
	      var txtAvgPay=0;
	      
	      
	      $('.txtTotal').each(function(x,y){
	    	  if(!isNaN($(this).val())){
	    		  
	    		  var txtTotal=isNaN($(this).val())?0:Number($(this).val());
	    		  
	    		  txtGrandTotal += Number(txtTotal);       
	    	  }
	      })
	   
	      
	      $("#txtGrandTotal").val(txtGrandTotal);
	      $("#txtAvgPay").val(Number(txtGrandTotal)/10);
	   
	   
});



/*$("#txtNonQualifyingServYear,#txtNonQualifyingServMonth,#txtNonQualifyingServDay").keyup(function(){
	calculateTotalQualServ();
});*/


/*$(document).on('keyup', '#lstPensionEmpAddQualModel0addQualYear', function(){ 
	var addQualServcYear=$("#lstPensionEmpAddQualModel0addQualYear").val();
	var addQualServcMonth=$("#lstPensionEmpAddQualModel0addQualMonth").val();
	var addQualServcDay=$("#lstPensionEmpAddQualModel0addQualDay").val();
	if(Number(addQualServcYear)>0){
		$("#txtTotQlyServYear").val(addQualServcYear);
		$("#txtQualiServYear").val(addQualServcYear);
	}
	
	calculateTotalQualServ();
});
$(document).on('keyup', '#lstPensionEmpAddQualModel0addQualMonth', function(){ 
	var addQualServcYear=$("#lstPensionEmpAddQualModel0addQualYear").val();
	var addQualServcMonth=$("#lstPensionEmpAddQualModel0addQualMonth").val();
	var addQualServcDay=$("#lstPensionEmpAddQualModel0addQualDay").val();
	if(Number(addQualServcYear)>0){
		$("#txtTotQlyServMonth").val(addQualServcMonth);
		$("#txtQualiServMonth").val(addQualServcMonth);
	}
	
	calculateTotalQualServ();
	
});
$(document).on('keyup', '#lstPensionEmpAddQualModel0addQualDay', function(){ 
	var addQualServcYear=$("#lstPensionEmpAddQualModel0addQualYear").val();
	var addQualServcMonth=$("#lstPensionEmpAddQualModel0addQualMonth").val();
	var addQualServcDay=$("#lstPensionEmpAddQualModel0addQualDay").val();
	
	if(Number(addQualServcYear)>0){
		$("#txtTotQlyServDay").val(addQualServcDay);
		$("#txtQualiServDay").val(addQualServcDay);
	}
	
	calculateTotalQualServ();
	
});*/








function calculateTotalQualServ(){
	
	
	if($("#logUser").text().includes("_AST")){
		
		//Additional Qualifying Service +
		var txtQualiServYear=$("#txtQualiServYear").val();
		var txtQualiServMonth=$("#txtQualiServMonth").val();
		var txtQualiServDay=$("#txtQualiServDay").val();
		var NoDays=0;
		
		//txtQlyService total days
		if(txtQualiServYear!="" && txtQualiServMonth!="" && txtQualiServDay!="" ){
			 NoDays=(Number(txtQualiServYear)*365)+(Number(txtQualiServMonth)*30)+(Number(txtQualiServDay));
			$("#txtQlyService").val(NoDays);
		}
		
		//Non-Qualifying Service -
		
		var txtNonQualifyingServYear=$("#txtNonQualifyingServYear").val();
		var txtNonQualifyingServMonth=$("#txtNonQualifyingServMonth").val();
		var txtNonQualifyingServDay=$("#txtNonQualifyingServDay").val();
		//txtNonQualifyingServ  total days 
		
		if(txtNonQualifyingServYear!="" && txtNonQualifyingServMonth!="" && txtNonQualifyingServDay!="" ){
			 NoDays=(Number(txtNonQualifyingServYear)*365)+(Number(txtNonQualifyingServMonth)*30)+(Number(txtNonQualifyingServDay));
			$("#txtNonQualifyingServ").val(NoDays);
		}
		
		//Actual Service + 
		var txtActualSerYear=$("#txtActualSerYear").val();
		var txtActualSerMonth=$("#txtActualSerMonth").val();
		var txtActualSerDay=$("#txtActualSerDay").val();
		//txtActualSer total days
		
		if(txtActualSerYear!="" && txtActualSerMonth!="" && txtActualSerDay!="" ){
			 NoDays=(Number(txtActualSerYear)*365)+(Number(txtActualSerMonth)*30)+(Number(txtActualSerDay));
			$("#txtActualSer").val(NoDays);
		}
		
		
		

		var sumAllYear=Number(txtQualiServYear)-Number(txtNonQualifyingServYear)+Number(txtActualSerYear);
		var sumAllMonth=Number(txtQualiServMonth)-Number(txtNonQualifyingServMonth)+Number(txtActualSerMonth);
		var sumAllDay=Number(txtQualiServDay)-Number(txtNonQualifyingServDay)+Number(txtActualSerDay);
		
		
		 NoDays=(Number(sumAllYear)*365)+(Number(sumAllMonth)*30)+(Number(sumAllDay));
		
		$("#txtQualifyingServ").val(NoDays);
		
		var year=NoDays/365;
		
		var remDaysFrmYear=NoDays%365;
		
		var month=remDaysFrmYear/30;
		
		var day=month%30;
		var consYear=0;
		if(year>33){
			consYear=33;
			$("#txtQualifyingServYear").val("33");
			$("#txtQualifyingServMonth").val("0");
			 $("#txtQualifyingServDay").val("0");
		}else{
			consYear=year;
			$("#txtQualifyingServYear").val(parseInt(year));
			$("#txtQualifyingServMonth").val(parseInt(month));
			 $("#txtQualifyingServDay").val(parseInt(day));
		}
		var totalSms = smsCalculation();
		
		$("#qualifyingLabel").val(totalSms+" (Six Monthly) "+(parseInt(consYear))+"*"+(2));
	}
	
	
	
}


/*function calculateTotalQualServ(){

	var txtNonQualifyingServ=$("#txtNonQualifyingServ").val();
	var txtQlyService=$("#txtQlyService").val();
	var txtActualSer=$("#txtActualSer").val();
	$("#txtQualifyingServ").val((Number(txtQlyService))+Number(txtActualSer)-Number(txtNonQualifyingServ));
	if(!isNaN($("#txtQualifyingServ").val())){
		findYearsMontsanDays($("#txtQualifyingServ").val(),$("#txtQualifyingServYear"),$("#txtQualifyingServMonth"), $("#txtQualifyingServDay"));
	}
	var noOfDays=$("#txtQlyService").val();
	if(!isNaN($("#txtQlyService").val())){
	findYearsMontsanDays(noOfDays,$("#txtQualiServYear"),$("#txtQualiServMonth"), $("#txtQualiServDay"));
	}
	if(!isNaN($("#txtNonQualifyingServ").val())){
		noOfDays=$("#txtNonQualifyingServ").val();
		findYearsMontsanDays(noOfDays,$("#txtNonQualifyingServYear"),$("#txtNonQualifyingServMonth"), $("#txtNonQualifyingServDay"));
	}
	if(!isNaN($("#txtActualSer").val())){
		noOfDays=$("#txtActualSer").val();
		findYearsMontsanDays(noOfDays,$("#txtActualSerYear"),$("#txtActualSerMonth"), $("#txtActualSerDay"));
	}
	
	
}*/













function findYearsMontsanDays(noOfDays,yearId,MonthId,DayId){
	 var year, months, week, days;
     year = noOfDays >= 365 ? Math.floor(noOfDays / 365) : 0;
     noOfDays = year ? noOfDays - (year*365) : noOfDays;
     months = noOfDays >= 30 ? Math.floor((noOfDays % 365) / 30) : 0;
     noOfDays = months ? noOfDays - (months*30) : noOfDays;
     week = noOfDays >= 7 ? Math.floor((noOfDays % 365) / 7) : 0;
     noOfDays = week ? noOfDays - (week*7) : noOfDays;
     days = noOfDays < 7 ? Math.floor((noOfDays % 365) % 7) : 0;
  
     DayId.val(days);
     MonthId.val(months);
     yearId.val(year);
}

$("#importFilePhoto").change(function(e){
	 readURL(this,1);
	 displayFileDetails(1,this);
});

$("#importFileSign").change(function(e){
	 readURL(this,2);
	 displayFileDetails(2,this);
});

function readURL(input,type) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
 
        if(type=="1"){
        	 reader.onload = function (e) {
                 $('#prewPhoto').attr('src', e.target.result);
             }
        }else if(type=="2"){
        	 reader.onload = function (e) {
                 $('#prewSign').attr('src', e.target.result);
             }
        }
        reader.readAsDataURL(input.files[0]);
    }
}


function displayFileDetails(type,input){
	var deleteRow='<div  align="center" style="vertical-align: middle;" class="delete tblQualifyingService"  ><a><span class="glyphicon glyphicon-trash delete tblQualifyingService" ></span></a>';
	if(type=="1"){
		var descPhoto=$("#descPhoto").val();
		if(descPhoto!=''){
			var filename=input.files[0].name;
			$("#myTablePhoto tbody").append("<tr>" + 
					"<td>"+descPhoto+"</td>" +
					"<td>"+filename+"</td>" +
					"<td>"+deleteRow+"</td>" +
			"</tr>");
		}else{
		  swal("Please enter photo Description !!!");	
		}
	}else{
		var descSign=$("#descSign").val();
		var filename=input.files[0].name;
		if(descSign!=''){
			$("#myTableSign tbody").append("<tr>" + 
					"<td>"+descSign+"</td>" +
					"<td>"+filename+"</td>" +
					"<td>"+deleteRow+"</td>" +
			"</tr>");
		}else{
			swal("Please enter sign Description !!!");	
		}
		
	}
}



$('input[type=radio][name=commuteOrNot]').change(function() {
    if (this.value == 'Y') {
    	 $("#txtDoWantCommute").show();
   	     $("#divForCom").show();
   	     $("#txtDoWantCommute").prop("readOnly",false);
    	if(Number($("#txtDoWantCommute").val())>40){
    		swal("Percentage of Commutation Should be less then 40 only");
    		 $("#txtDoWantCommute").prop("readOnly",false);
    	}else{
    		  $("#txtDoWantCommute").prop("readOnly",false);
    		  $("#txtDoWantCommute").val("40");
    	}
    }
    else if (this.value == 'N') {
    	 $("#txtDoWantCommute").val("0");
    	  $("#txtDoWantCommute").hide();
    	  $("#divForCom").hide();
    }
});





$("#txtDoWantCommute").keyup(function(){
	var commuteOrNot=$('input[type=radio][name=commuteOrNot]').val();
	if(commuteOrNot=='Y' && Number($("#txtDoWantCommute").val())>40){
		swal("Percentage of Commutation Should be less then 40 only");
		 $("#txtDoWantCommute").val("");
	}
});



$('input[type=radio][name=radioPrvsnlGrtyPaid]').change(function() {
	    if (this.value == 'Y') {
	    	provisionalGratuityPaid(false);
	    }else  if(this.value == 'N'){
	    	provisionalGratuityPaid(true);
	    	$("#txtPnsnGrtyAmount").val("");
	    	$("#txtOrderNo").val("");
	    	$("#txtOrderDate").val("");
	    	$("#txtvoucherNo").val("");
	    	$("#txtvoucherDate").val("");
	    }
    });


 provisionalGratuityPaid(true);

function provisionalGratuityPaid(trueOrFalse){
	$("#txtPnsnGrtyAmount").prop("readOnly",trueOrFalse);
	$("#txtOrderNo").prop("readOnly",trueOrFalse);
	$("#txtOrderDate").prop("readOnly",trueOrFalse);
	$("#txtvoucherNo").prop("readOnly",trueOrFalse);
	$("#txtvoucherDate").prop("readOnly",trueOrFalse);
}






$("#txtDateOfRetiremt").change(function(){
	calDayBetnSerJoinToServEnd();
});


$("#txtDateOfRetiremt").blur(function(){
	calDayBetnSerJoinToServEnd();
});




calDayBetnSerJoinToServEnd();
function calDayBetnSerJoinToServEnd(){
	  var fromdate = $("#txtDateOfStartingService").val();
      var todate = $("#txtDateOfRetiremt").val();
 
      if ((fromdate == "") || (todate == "")) {
        //swal("Please select Service joining and retirement  dates");
        return false
      }else{
    	  var dt1 = new Date(fromdate);
          var dt2 = new Date(todate);
     
          var time_difference = dt2.getTime() - dt1.getTime();
          var result = time_difference / (1000 * 60 * 60 * 24);
          
          if(Number(result)>0){
        	  $("#txtActualSer").val(parseInt(result));
        	  var noOfDays=$("#txtActualSer").val();
      		findYearsMontsanDays(noOfDays,$("#txtActualSerYear"),$("#txtActualSerMonth"), $("#txtActualSerDay"));
      		findYearsMontsanDays(noOfDays,$("#txtQualifyingServYear"),$("#txtQualifyingServMonth"), $("#txtQualifyingServDay"));
          }
      }
}


function findDiffBetwnTwoDts(){
	var totalYear=0;
	var totalMonth=0;
	var totalDay=0;
	var count=0
	   $("#tblNonQualifyingService > tbody  > tr").each(function(){
	      var row=$(this);
	  	  var withEffDate = new Date(row.find(".withEffDate").val());
	  	  var toDate = new Date(row.find(".toDate").val());
	  	  if(row.find(".withEffDate").val()!='' &&  row.find(".toDate").val()!=''){
	  		  if(toDate>withEffDate){
		  	  var yearsDifference=toDate.getFullYear()-withEffDate.getFullYear();
		      var monthsDifference=toDate.getMonth()-withEffDate.getMonth();
		      var daysDifference=toDate.getDate()-withEffDate.getDate();
		      
		      totalYear=parseInt(totalYear)+parseInt(yearsDifference);
		      totalMonth=parseInt(totalMonth)+parseInt(monthsDifference);
		      totalDay=parseInt(totalDay)+parseInt(daysDifference);
		      
		      // To calculate the time difference of two dates
		      var Difference_In_Time = toDate.getTime() - withEffDate.getTime();
		        
		      // To calculate the no. of days between two dates
		      var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
		      document.getElementById("hidDays"+count).value = Difference_In_Days
	  		  }else{
	 	  		  swal("To date should be bigger then with efective date !!!");
	  		  }
	  		count=parseInt(count)+1;
	  	  }
	  	  //}/*else{
	  		 /* swal("To date should be bigger then with efective date !!!");
	  	  }*/
	   });
	   $("#txtNonQualifyingServYear").val(totalYear);
	   $("#txtNonQualifyingServMonth").val(totalMonth);
	   $("#txtNonQualifyingServDay").val(totalDay);
	   if(totalYear!=0 || totalMonth!=0  || totalDay!=0){
		   calculateTotalQualServ();
	   }
}



function sumOfAllAddService(){
	var totalYear=0;
	var totalMonth=0;
	var totalDay=0;
	   $("#tblQualifyingService > tbody  > tr").each(function(){
	      var row=$(this);
	  	  var year = row.find(".addQualYear").val();
	  	  var month = row.find(".addQualMonth").val();
	  	  var day = row.find(".addQualDay").val();
	  	  if(year!='' &&  month!='' &&  day!=''){
	      totalYear=parseInt(totalYear)+parseInt(year);
	      totalMonth=parseInt(totalMonth)+parseInt(month);
	      totalDay=parseInt(totalDay)+parseInt(day);
	  	  }
	   });
	   $("#txtQualiServYear").val(totalYear);
	   $("#txtQualiServMonth").val(totalMonth);
	   $("#txtQualiServDay").val(totalDay);
	   if(totalYear!=0 || totalMonth!=0  || totalDay!=0){
		   calculateTotalQualServ();
	   }
}




function newPensionCalculation() {

	if($("#cmbClassOfPnsn").val()=='' || $("#cmbClassOfPnsn").val()==undefined || $("#cmbClassOfPnsn").val()=='0' ){
		swal("Please select Class of Pension !!!");
		return false;
	}
	if($("#payCommissionCode").val()=='' || $("#payCommissionCode").val()==undefined ||  $("#payCommissionCode").val()=='0'){
		swal("Please select PayCommision Code !!!");
		return false;
	}
	if($("#txtDateOfBirth").val()=='' || $("#txtDateOfBirth").val()==undefined){
		swal("Please select Date of Birth  !!!");
		return false;
	}
	if($("#txtDateOfRetiremt").val()=='' || $("#txtDateOfRetiremt").val()==undefined){
		swal("Please select Date of Retirement  !!!");
		return false;
	}
	if($("#txtDateOfStartingService").val()=='' || $("#txtDateOfStartingService").val()==undefined){
		swal("Please select Date Of Joining Government Service  !!!");
		return false;
	}
	
	
	var CalcGridLength = $("#tblAvgPayCalc>tbody>tr").length;;
	var DOR = $("#txtDateOfRetiremt").val();  //date--month--year
	
	var DPConsideringDate = "01/08/2004";
	
	var payComm = $("#payCommissionCode option:selected").text(); //$("#cmbClassOfPnsn").val();  //string 

	
	
	/*if (CalcGridLength >= 1) {
		for (var rowServCnt = 0; rowServCnt < Number(CalcGridLength); rowServCnt++) {
			try {
				if($("#txtPeriodFromDate"+rowServCnt).val()=='' || $("#txtPeriodFromDate"+rowServCnt).val()==undefined){
					swal("Please select from Date from last 10 basic pay !!!");
					return false;
				}else if($("#txtAvgPayBasic"+rowServCnt).val()=='' || $("#txtAvgPayBasic"+rowServCnt).val()==undefined){
					swal("Please select basic pay from last 10 basic pay !!!");
					return false;
				}
			} catch (ex) {

			}
		}
	}*/
	
	
	var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text(); //document.getElementById("cmbClassOfPnsn").value;
	var PayComsn = $("#payCommissionCode option:selected").text();  //document.getElementById("cmbPayComsn").value;
	var PnsnCatg = $("#cmbPnsnCatg option:selected").text();     //document.getElementById("cmbPnsnCatg").value;
	var DOB =  $("#txtDateOfBirth").val();
	var DOR = $("#txtDateOfRetiremt").val();
	var DOJ = $("#txtDateOfStartingService").val();
	var DOE = $("#txtDateOfExpiry").val();

	var FirstDate6thPC = $("#hdnFirstDate6thPC").val();
	var ActualDate6thPC = $("#hdnActualDate6thPC").val();
	var DCRGMinDate = $("#hdnDCRGMinDate").val();
	var DCRGMaxDate = $("#hdnDCRGMaxDate").val();
	var State = $("#cmbState").val();
	var commutPer = $("#txtDoWantCommute").val();
	if (PnsnCatg == 'High Court Judges' && ClassOfPnsn != 'Family Pension') {
		if (commutPer == "") {
			swal('Please enter commuted percentage')
			return false;
		}
	}
	var lengthServBrkDtls = $("#tblNonQualifyingService>tbody>tr").length;
 	for (var i = 0; i <= Number(lengthServBrkDtls); i++) {
		calTotalSrvcBrk(i);
	}

	// alert("after for loop");
	//calActualServiceDays();
	//setTotalDaysOfSrvcBrk('txtDateOfBrkFrom', 'txtDateOfBrkTo');
	// alert("after for loop values set");
	var DORArr = DOR.split("-");
	var DOBArr = DOB.split("-");

	
	
	var totalSms = smsCalculation();
	
	
	
	
	
	
	// alert("totalSms "+totalSms);
	var lastEmolument = "";
	var npaAllow = false;
	var lastNPA = 0;
	
	
	var avgEmolument = document.getElementById("txtAvgPay").value;

	// alert("avgEmolument "+avgEmolument);
	if (PnsnCatg == 'Medical Officer') {
		if (document.getElementById("chkBoxNPA").checked == true) {
			npaAllow = true;
		}
	}
	var PensionAmount = "";
	var serviceGratiity = "";
	var DCRGamount = "";
	var cvpAmount = "";
	var commutedPension = "";
	var payCom = "";
	var efpAmount = "";
	var rrAmount = "";

	var lastBasic = document.getElementById("txtAvgPayBasic9").value;
	// alert("lastBasic "+lastBasic); //6.0
	var lastGradePay = document.getElementById("txtAvgPayDP9").value;
	// alert("lastGradePay"+lastGradePay);//7.0
	// if(npaAllow){
	lastNPA = document.getElementById("txtNPA9").value;
	// alert("lastNPA"+lastNPA);
	// }
	
	
	
	// start  mjp for customised sevaarth
	
	lastEmolument = Number(lastBasic) + Number(lastGradePay)
	+ Number(lastNPA);
	
	var pensionWorkOut=(Number(avgEmolument)/2);//(A)
	var basicPensionOnLastPay= (Number(lastEmolument)/2);
	
	var basicPension=Math.max(pensionWorkOut,basicPensionOnLastPay);//  3
	var maxbasicPension =basicPension;
	
	var familyPensionAmt=(basicPensionOnLastPay*0.3);
	
	var enhanceFamilyPension=pensionWorkOut;  //1
	var familyPension=Number(familyPensionAmt)*2; //2
	
	basicPension=Math.min(enhanceFamilyPension,familyPension,basicPension);
	
	
	PensionAmount=basicPension;
	var gratuityAmt=((Number(lastEmolument)*totalSms)/4);
	
	$("#txtServGratyAmt").val(gratuityAmt);
	
	
	document.getElementById("txtTotPnsnAmt").value = Math
	.ceil(maxbasicPension); // Total Pension Editable
document.getElementById("txtTotlPnsnAmt").value = Math
	.ceil(maxbasicPension); // Total Pension ReadOnly
	

document.getElementById("txtTotDCRGAmt").value = Math
.ceil(gratuityAmt);


commutedPension = Number(maxbasicPension)
* Number(commutPer) / 100;

document.getElementById("txtComPnsnAmt").value = Math
.ceil(commutedPension);
document.getElementById("txtMonthAmt").value = Math
.ceil(commutedPension);
var age = ageFromDOBAndDOR(DOB, DOR);

$("#age").val(age);

if (Number(age) > 81) {
age = 81;
}
getCVPRateFromAge(age, payCom);
cvpRate = document.getElementById("hdnCvpRate").value;

document.getElementById("txtCVPAmt").value = Math.ceil(Number(commutedPension) * 12 * Number(cvpRate));

var efpAmount = Number(lastEmolument) / 2;

document.getElementById("txtFP1TotlAmt").value = Math
.ceil(efpAmount);



var rrAmount = Number(lastEmolument) * 0.3;


document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);  //family pension 
	

document.getElementById("txtRedsPnsnAmt").value = Number(document.getElementById("txtTotlPnsnAmt").value)
		- Number(document.getElementById("txtComPnsnAmt").value);
	
	//  end for mjp
	
}


function newPensionCalculation1() {

	// alert("inside newPensionCalculation"); //dan

	if($("#cmbClassOfPnsn").val()=='' || $("#cmbClassOfPnsn").val()==undefined || $("#cmbClassOfPnsn").val()=='0' ){
		swal("Please select Class of Pension !!!");
		return false;
	}
	if($("#payCommissionCode").val()=='' || $("#payCommissionCode").val()==undefined ||  $("#payCommissionCode").val()=='0'){
		swal("Please select PayCommision Code !!!");
		return false;
	}
	if($("#txtDateOfBirth").val()=='' || $("#txtDateOfBirth").val()==undefined){
		swal("Please select Date of Birth  !!!");
		return false;
	}
	if($("#txtDateOfRetiremt").val()=='' || $("#txtDateOfRetiremt").val()==undefined){
		swal("Please select Date of Retirement  !!!");
		return false;
	}
	if($("#txtDateOfStartingService").val()=='' || $("#txtDateOfStartingService").val()==undefined){
		swal("Please select Date Of Joining Government Service  !!!");
		return false;
	}
	
	
	var CalcGridLength = $("#tblAvgPayCalc>tbody>tr").length;;
	var DOR = $("#txtDateOfRetiremt").val();  //date--month--year
	
	var DPConsideringDate = "01/08/2004";
	
	var payComm = $("#payCommissionCode option:selected").text(); //$("#cmbClassOfPnsn").val();  //string 

	lastEmolument = Number(lastBasic) + Number(lastGradePay) + Number(lastNPA);
	
	if (CalcGridLength >= 1) {
		for (var rowServCnt = 0; rowServCnt < Number(CalcGridLength); rowServCnt++) {
			try {
				if($("#txtPeriodFromDate"+rowServCnt).val()=='' || $("#txtPeriodFromDate"+rowServCnt).val()==undefined){
					swal("Please select from Date from last 10 basic pay !!!");
					return false;
				}else if($("#txtAvgPayBasic"+rowServCnt).val()=='' || $("#txtAvgPayBasic"+rowServCnt).val()==undefined){
					swal("Please select basic pay from last 10 basic pay !!!");
					return false;
				}
			} catch (ex) {

			}
		}
	}
	var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text(); //document.getElementById("cmbClassOfPnsn").value;
	var PayComsn = $("#payCommissionCode option:selected").text();  //document.getElementById("cmbPayComsn").value;
	var PnsnCatg = $("#cmbPnsnCatg option:selected").text();     //document.getElementById("cmbPnsnCatg").value;
	var DOB =  $("#txtDateOfBirth").val();
	var DOR = $("#txtDateOfRetiremt").val();
	var DOJ = $("#txtDateOfStartingService").val();
	var DOE = $("#txtDateOfExpiry").val();

	var FirstDate6thPC = $("#hdnFirstDate6thPC").val();
	var ActualDate6thPC = $("#hdnActualDate6thPC").val();
	var DCRGMinDate = $("#hdnDCRGMinDate").val();
	var DCRGMaxDate = $("#hdnDCRGMaxDate").val();
	var State = $("#cmbState").val();
	var commutPer = $("#txtDoWantCommute").val();
	if (PnsnCatg == 'High Court Judges' && ClassOfPnsn != 'Family Pension') {
		if (commutPer == "") {
			swal('Please enter commuted percentage')
			return false;
		}
	}
	var lengthServBrkDtls = $("#tblNonQualifyingService>tbody>tr").length;
 	for (var i = 0; i <= Number(lengthServBrkDtls); i++) {
		calTotalSrvcBrk(i);
	}

	// alert("after for loop");
	//calActualServiceDays();
	//setTotalDaysOfSrvcBrk('txtDateOfBrkFrom', 'txtDateOfBrkTo');
	// alert("after for loop values set");
	var DORArr = DOR.split("-");
	var DOBArr = DOB.split("-");

	var totalSms = smsCalculation();
	// alert("totalSms "+totalSms);
	var lastEmolument = "";
	var npaAllow = false;
	var lastNPA = 0;
	var avgEmolument = document.getElementById("txtAvgPay").value;

	// alert("avgEmolument "+avgEmolument);
	if (PnsnCatg == 'Medical Officer') {
		if (document.getElementById("chkBoxNPA").checked == true) {
			npaAllow = true;
		}
	}
	var PensionAmount = "";
	var serviceGratiity = "";
	var DCRGamount = "";
	var cvpAmount = "";
	var commutedPension = "";
	var payCom = "";
	var efpAmount = "";
	var rrAmount = "";

	var lastBasic = document.getElementById("txtAvgPayBasic9").value;
	// alert("lastBasic "+lastBasic); //6.0
	var lastGradePay = document.getElementById("txtAvgPayDP9").value;
	// alert("lastGradePay"+lastGradePay);//7.0
	// if(npaAllow){
	lastNPA = document.getElementById("txtNPA9").value;
	// alert("lastNPA"+lastNPA);
	// }

	if (PayComsn == 'Sixth Pay Commission') {
		// alert("inside PayComsn");//8.0
		if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')) {
			if (Number(lastBasic) > 67000)
				lastBasic = 67000
			if (Number(lastGradePay) > 12000)
				lastGradePay = 12000

				// alert("lastBasic 1="+lastBasic);//Dan 9.0
				// alert("lastNPA 2 ="+lastNPA);
				// alert("lastGradePay 3 ="+lastGradePay);
			lastEmolument = Number(lastBasic) + Number(lastGradePay)
					+ Number(lastNPA);
			// alert("lastEmolument 4= "+lastEmolument);

			// Pension Calculation

			if (Number(totalSms) > 20) {
				if (ClassOfPnsn == 'Family Pension') {
					// alert("inside ClassOfPnsn");
					// No pension calculate
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {

					if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
							&& compareDatesWithoutAlert(DOR, ActualDate6thPC,
									'<')) {

						PensionAmount = (Number(lastEmolument) / 2)
								* (Number(totalSms) / 66);

					} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC,
							'>')) {
						alert("lastEmolument: " + lastEmolument);
						// alert("avgEmolument: "+avgEmolument);
						PensionAmount = ((Number(lastEmolument) / 2) > (Number(avgEmolument) / 2)) ? (Number(lastEmolument) / 2)
								: (Number(avgEmolument) / 2);
						alert("PensionAmount: " + PensionAmount);
					}

				} else {
					// alert("lastEmolument: "+lastEmolument);
					// alert("avgEmolument: "+avgEmolument);
					PensionAmount = ((Number(lastEmolument) / 2) > (Number(avgEmolument) / 2)) ? (Number(lastEmolument) / 2)
							: (Number(avgEmolument) / 2);
					// alert("PensionAmount: "+PensionAmount);
				}
				if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS') {
					// alert("Not in IAS , IPS , IFS ");//dan 13.0

					if (Number(PensionAmount) > 39500)
						// alert("inside 39500");
						PensionAmount = 39500;

					if (Number(PensionAmount) < 2882
							&& Number(PensionAmount) > 0)
						// alert("inside 2882");
						PensionAmount = 2882;
				} else {
					if (Number(PensionAmount) > 45000)
						PensionAmount = 45000;

					if (Number(PensionAmount) < 3500
							&& Number(PensionAmount) > 0)
						PensionAmount = 3500;
				}

				document.getElementById("txtTotPnsnAmt").value = Math
						.ceil(PensionAmount); // Total Pension Editable
				document.getElementById("txtTotlPnsnAmt").value = Math
						.ceil(PensionAmount); // Total Pension ReadOnly

			}

			

			if (ClassOfPnsn == 'Family Pension') {

				// alert("family");
				//alert("lastEmolument: " + lastEmolument)
				//alert("totalSms: " + totalSms);
				if (Number(totalSms) < 2) {
					serviceGratiity = 2 * Number(lastEmolument);
					// alert("serviceGratiity 2"+serviceGratiity);

				} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
					// alert("serviceGratiity 6"+serviceGratiity);
					serviceGratiity = 6 * Number(lastEmolument);
					// alert("serviceGratiity 6"+serviceGratiity);

				} else if (Number(totalSms) >= 11 && Number(totalSms) < 22) {
					// alert("(Number(totalSms) >= 11 && Number(totalSms) <
					// 22)")
					serviceGratiity = 12 * Number(lastEmolument);

				} else if (Number(totalSms) >= 22 && Number(totalSms) < 40) {
					// alert("family : 22-40: ")
					// alert("(Number(totalSms) >= 22 && Number(totalSms) <
					// 40)")
					serviceGratiity = 20 * Number(lastEmolument);
					// alert("serviceGratiityfa,ily22-40: "+serviceGratiity)

				} else if (Number(totalSms) >= 40) {
					// alert("totalSms"+totalSms);
					if (Number(totalSms) >= 66) {
						totalSms = 66;
					} else {
						totalSms = totalSms;
					}
					serviceGratiity = (Number(lastEmolument) / 2)
							* Number(totalSms);
					// alert("serviceGratiity 40"+serviceGratiity); //dan 15.0

				}
				//TODO
				//alert("DOR:"+DOR)
				//alert("FirstDate6thPC:"+FirstDate6thPC);
				//alert("DOE:"+DOE);
				//alert("DCRGMinDate:"+DCRGMinDate);
				//alert("serviceGratiity:"+serviceGratiity);
				//alert("ActualDate6thPC:"+ActualDate6thPC);

			/*	if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 700000)
							serviceGratiity = 700000;
						// alert("serviceGratiity 700000"+serviceGratiity);

					}

				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 700000)
							alert("1")
							serviceGratiity = 700000;
					} else if (compareDatesWithoutAlert(DOE, DCRGMinDate, '>')) {
						if (Number(serviceGratiity) > 500000)
							alert("2")
							serviceGratiity = 500000;
					}
				}*/
				//TODO
				//alert("new dcrg")
					if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, DCRGMaxDate , '<')) {
						if (Number(serviceGratiity) > 500000){
							//alert("5")
							serviceGratiity = 500000;
						// alert("serviceGratiity 700000"+serviceGratiity);

					}
						}else if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
								&& compareDatesWithoutAlert(DOR, DCRGMinDate , '>')) {
							if (Number(serviceGratiity) > 700000){
								//alert("7")
								serviceGratiity = 700000;
							// alert("serviceGratiity 700000"+serviceGratiity);

						}
						}
						
					/*if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 700000)
							serviceGratiity = 700000;
						// alert("serviceGratiity 700000"+serviceGratiity);

					}

				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 700000)
							alert("1")
							serviceGratiity = 700000;
					} else if (compareDatesWithoutAlert(DOE, DCRGMinDate, '>')) {
						if (Number(serviceGratiity) > 500000)
							alert("2")
							serviceGratiity = 500000;
					}
				}*/
				
			} else if (ClassOfPnsn == 'Invalid Pension (Rule 68)' || ClassOfPnsn == 'Superannuation Pension (Rule 63)') {
				// alert(" : VO: " )
				//alert("ClassOfPnsn:"+ClassOfPnsn);
				//alert("totalSms: " + totalSms);
				//alert("lastBasic: " + lastBasic);
				// alert("totalSms 12: "+totalSms)
				//alert("lastEmolument: " + lastEmolument)
				if (Number(totalSms) < 2) {
					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 10 && Number(totalSms) < 20) {
					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 20 && Number(totalSms) < 40) {
					serviceGratiity = (Number(lastEmolument) / 4)
							* Number(totalSms);

				} else if (Number(totalSms) >= 40) {
					// alert("serviceGratiity= "+serviceGratiity);
					serviceGratiity = (Number(lastEmolument) / 4)
							* Number(totalSms);
				}
				
				//alert("new Super dcrg")
				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
					&& compareDatesWithoutAlert(DOR, DCRGMaxDate , '<')) {
					if (Number(serviceGratiity) > 500000){
						//alert("5")
						serviceGratiity = 500000;
					// alert("serviceGratiity 700000"+serviceGratiity);

				}
					}else if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
							&& compareDatesWithoutAlert(DOR, DCRGMinDate , '>')) {
						if (Number(serviceGratiity) > 700000){
							//alert("7")
							serviceGratiity = 700000;
						// alert("serviceGratiity 700000"+serviceGratiity);

					}
					}

			} else {
				//alert("other");
				//alert("totalSms: " + totalSms);
				//alert("lastBasic: " + lastBasic);
				// alert("totalSms 12: "+totalSms)
				//alert("lastEmolument: " + lastEmolument)
				if (Number(totalSms) < 2) {
					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 10 && Number(totalSms) < 22) {
					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 22 && Number(totalSms) < 40) {

					alert("Qualifing services Less than 20 years");
				} else if (Number(totalSms) >= 40) {
					// alert("serviceGratiity= "+serviceGratiity);
					serviceGratiity = (Number(lastEmolument) / 4)
							* Number(totalSms);
				}

				else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {

					serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;

					if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
							&& compareDatesWithoutAlert(DOR, ActualDate6thPC,
									'<')) {
						if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
								DOR, DCRGMaxDate, '=') && document
								.getElementById("radioBn").checked))) {
							// edited by aditya
							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;

						} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate,
								'>') || (compareDatesWithoutAlert(DOR,
								DCRGMaxDate, '=') && document
								.getElementById("radioAn").checked))) {

							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;
						}
					} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC,
							'>')) {
						if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
								DOR, DCRGMaxDate, '=') && document
								.getElementById("radioBn").checked))) {
							// edited by aditya
							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;

						} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate,
								'>') || (compareDatesWithoutAlert(DOR,
								DCRGMaxDate, '=') && document
								.getElementById("radioAn").checked))) {

							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;
						}
					}
				}/*
					 * else{
					 * 
					 * serviceGratiity = (Number(lastEmolument) *
					 * Number(totalSms)) / 4;
					 * 
					 * alert("serviceGratiity11111: "+serviceGratiity)
					 * alert("other"); if(Number(totalSms) < 2) {
					 * //alert("family : 1") //alert("(Number(totalSms) < 2)")
					 * serviceGratiity = 2 * Number(lastBasic);
					 *  } //for 1 to 5 --2) else if(Number(totalSms) >= 2 &&
					 * Number(totalSms) <10) { //alert("(Number(totalSms) >= 2 &&
					 * Number(totalSms) <10)") serviceGratiity = 6 *
					 * Number(lastBasic);
					 *  } //for 5 to 11 -- 3) else if(Number(totalSms) >= 11 &&
					 * Number(totalSms) < 22) { //alert("(Number(totalSms) >= 11 &&
					 * Number(totalSms) < 22)") serviceGratiity = 12 *
					 * Number(lastBasic);
					 *  } else if(Number(totalSms) >= 22 && Number(totalSms) <
					 * 40) { //alert("family : 22-40: ")
					 * //alert("(Number(totalSms) >= 22 && Number(totalSms) <
					 * 40)") serviceGratiity = 20 * Number(lastBasic);
					 * //alert("serviceGratiityfa,ily22-40: "+serviceGratiity)
					 * 
					 * }//for 20 -- 4)
					 * 
					 * else if(Number(totalSms) >= 40 )
					 *  { //alert("(Number(totalSms) >= 40 )") serviceGratiity =
					 * (Number(lastBasic)/2) * 33;
					 * 
					 * 
					 * //alert("serviceGratiity11111:"+serviceGratiity); }
					 */

				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						} else {
							// edited by aditya
							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;
						}
					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						}

						else {
							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;
						}
					}
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						} else {
							// edited by aditya
							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;
						}
					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						} else {
							if (Number(serviceGratiity) > 700000)
								serviceGratiity = 700000;
						}
					}
				}
			}

			document.getElementById("txtServGratyAmt").value = Math
					.ceil(serviceGratiity);

			// DCRG calculation

			var withheldAmt = document.getElementById("txtWitheldGratuity").value;
			// alert ("withheldAmt*****"+withheldAmt);

			DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
			// alert("DCRGamount111111: "+DCRGamount)

			document.getElementById("txtTotDCRGAmt").value = Math
					.ceil(DCRGamount);

			// CVP Calculation

			if (document.getElementById("radioDoWantCommuteYes").checked
					&& Number(totalSms) > 20) {
				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

					if (ClassOfPnsn == 'Family Pension') {

					} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
							|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount) / 3;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					} else {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount) / 3;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					}
					payCom = '5th Pay Commission';
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if (ClassOfPnsn == 'Family Pension') {

					} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
							|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					} else {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					}
					payCom = '6th Pay Commission';
				}
				document.getElementById("txtComPnsnAmt").value = Math
						.ceil(commutedPension);
				document.getElementById("txtMonthAmt").value = Math
						.ceil(commutedPension);
				
				var age = ageFromDOBAndDOR(DOB, DOR);

				if (Number(age) > 81) {
					age = 81;
				}
				getCVPRateFromAge(age, payCom);
				cvpRate = document.getElementById("hdnCvpRate").value;

				document.getElementById("txtCVPAmt").value = Math
						.ceil(Number(commutedPension) * 12 * Number(cvpRate));

			}
			if (ClassOfPnsn != 'Family Pension') {
				document.getElementById("txtRedsPnsnAmt").value = Number(document
						.getElementById("txtTotlPnsnAmt").value)
						- Number(document.getElementById("txtComPnsnAmt").value);
			}
			// EFP and RR amount claculation

			if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
					&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

				if (ClassOfPnsn == 'Family Pension') {
					efpAmount = Number(lastEmolument) / 2;
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {
					efpAmount = Number(lastEmolument) / 2;
				} else {
					// efpAmount = ((Number(lastEmolument)/2) <
					// Number(avgEmolument)) ?
					// (Number(lastEmolument)/2)*(Number(totalSms)/66) :
					// Number(avgEmolument) *(Number(totalSms)/66);
					efpAmount = Number(lastEmolument) / 2;
				}

			} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
				if (ClassOfPnsn == 'Family Pension') {
					efpAmount = Number(lastEmolument) / 2;
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {
					efpAmount = Number(lastEmolument) / 2;
				} else {
					// efpAmount = ((Number(lastEmolument)/2) <
					// Number(avgEmolument)) ?
					// (Number(lastEmolument)/2)*(Number(totalSms)/66) :
					// Number(avgEmolument) *(Number(totalSms)/66);
					// cahanged on 18-3-2016
					efpAmount = Number(lastEmolument) / 2;
				}
			}
			if (PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS') {
				if (Number(efpAmount) > 39500)
					efpAmount = 39500;

				if (Number(efpAmount) < 2882 && Number(efpAmount) > 0)
					efpAmount = 2882;
			} else {
				if (Number(efpAmount) > 45000)
					efpAmount = 45000;

				if (Number(efpAmount) < 3500 && Number(efpAmount) > 0)
					efpAmount = 3500;
			}
			document.getElementById("txtFP1TotlAmt").value = Math
					.ceil(efpAmount);

			rrAmount = Number(lastEmolument) * 0.3;
			if (PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS') {
				if (Number(rrAmount) > 39500)
					rrAmount = 39500;

				if (Number(rrAmount) < 2882 && Number(rrAmount) > 0)
					rrAmount = 2882;
			} else {
				if (Number(rrAmount) > 45000)
					rrAmount = 45000;

				if (Number(rrAmount) < 3500 && Number(rrAmount) > 0)
					rrAmount = 3500;
			}

			document.getElementById("txtFP2TotlAmt").value = Math
					.ceil(rrAmount);
		} else {
			resetAllAmnt();
		}
	}

	// added by shraddha for Padmanabhan comm on 29/12/2015

	else if (PayComsn == 'PadmanabhanComm') {

		if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')) {
			if (Number(lastBasic) > 76450)
				lastBasic = 76450
			if (Number(lastGradePay) > 12000)
				lastGradePay = 12000

			lastEmolument = Number(lastBasic) + Number(lastGradePay)
					+ Number(lastNPA);
			// Pension Calculation

			if (Number(totalSms) > 20) {
				if (ClassOfPnsn == 'Family Pension') {
					// No pension calculate
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {

					if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
							&& compareDatesWithoutAlert(DOR, ActualDate6thPC,
									'<')) {

						PensionAmount = (Number(lastEmolument) / 2)
								* (Number(totalSms) / 66);

					} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC,
							'>')) {
						PensionAmount = ((Number(lastEmolument) / 2) > (Number(avgEmolument) / 2)) ? (Number(lastEmolument) / 2)
								: (Number(avgEmolument) / 2);
					}

				} else {
					PensionAmount = ((Number(lastEmolument) / 2) > (Number(avgEmolument) / 2)) ? (Number(lastEmolument) / 2)
							: (Number(avgEmolument) / 2);
				}
				if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS') {
					if (Number(PensionAmount) > 39500)
						PensionAmount = 39500;

					if (Number(PensionAmount) < 2882
							&& Number(PensionAmount) > 0)
						PensionAmount = 2882;
				} else {
					if (Number(PensionAmount) > 45000)
						PensionAmount = 45000;

					if (Number(PensionAmount) < 3500
							&& Number(PensionAmount) > 0)
						PensionAmount = 3500;
				}

				document.getElementById("txtTotPnsnAmt").value = Math
						.ceil(PensionAmount); // Total Pension Editable
				document.getElementById("txtTotlPnsnAmt").value = Math
						.ceil(PensionAmount); // Total Pension ReadOnly

			}// Service Gratiity

			if (ClassOfPnsn == 'Family Pension') {

				if (Number(totalSms) < 2) {
					serviceGratiity = 2 * Number(lastEmolument);

				} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
					serviceGratiity = 6 * Number(lastEmolument);

				} else if (Number(totalSms) >= 11 && Number(totalSms) < 22) {
					// alert("(Number(totalSms) >= 11 && Number(totalSms) <
					// 22)")
					serviceGratiity = 12 * Number(lastBasic);

				} else if (Number(totalSms) >= 22 && Number(totalSms) < 40) {
					// alert("family : 22-40: ")
					// alert("(Number(totalSms) >= 22 && Number(totalSms) <
					// 40)")
					serviceGratiity = 20 * Number(lastBasic);
					// alert("serviceGratiityfa,ily22-40: "+serviceGratiity)

				} else if (Number(totalSms) >= 40) {
					// alert ("serviceGratiity2: "+serviceGratiity)
					serviceGratiity = (Number(lastEmolument) / 2)
							* Number(totalSms);

				}

				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;
					}

				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;
					} else if (compareDatesWithoutAlert(DOE, DCRGMinDate, '>')) {
						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;
					}
				}
			} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
					|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {

				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;

				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {
						// edited by aditya
						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;

					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;
					}
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {
						// edited by aditya
						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;

					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						if (Number(serviceGratiity) > 1000000)
							serviceGratiity = 1000000;
					}
				}
			} else {

				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;

				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						} else {
							// edited by aditya
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						}
					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						}

						else {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						}
					}
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						} else {
							// edited by aditya
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						}
					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						// edited by shraddha
						if (PnsnCatg == 'IAS' || PnsnCatg == 'IPS'
								|| PnsnCatg == 'IFS') {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						} else {
							if (Number(serviceGratiity) > 1000000)
								serviceGratiity = 1000000;
						}
					}
				}
			}

			document.getElementById("txtServGratyAmt").value = Math
					.ceil(serviceGratiity);

			// DCRG calculation

			withheldAmt = document.getElementById("txtWitheldGratuity").value;
			// alert ("withheldAmt*****"+withheldAmt);

			DCRGamount = Number(serviceGratiity) - Number(withheldAmt);

			document.getElementById("txtTotDCRGAmt").value = Math
					.ceil(DCRGamount);

			// CVP Calculation

			if (document.getElementById("radioDoWantCommuteYes").checked
					&& Number(totalSms) > 20) {
				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

					if (ClassOfPnsn == 'Family Pension') {

					} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
							|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount) / 3;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					} else {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount) / 3;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					}
					payCom = '5th Pay Commission';
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if (ClassOfPnsn == 'Family Pension') {

					} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
							|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					} else {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					}
					payCom = 'Padmanabhan Comm.';
				}
				document.getElementById("txtComPnsnAmt").value = Math
						.ceil(commutedPension);
				document.getElementById("txtMonthAmt").value = Math
						.ceil(commutedPension);
				var age = ageFromDOBAndDOR(DOB, DOR);

				if (Number(age) > 81) {
					age = 81;
				}
				getCVPRateFromAge(age, payCom);
				cvpRate = document.getElementById("hdnCvpRate").value;

				document.getElementById("txtCVPAmt").value = Math
						.ceil(Number(commutedPension) * 12 * Number(cvpRate));

			}
			if (ClassOfPnsn != 'Family Pension') {
				document.getElementById("txtRedsPnsnAmt").value = Number(document
						.getElementById("txtTotlPnsnAmt").value)
						- Number(document.getElementById("txtComPnsnAmt").value);
			}
			// EFP and RR amount claculation

			if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
					&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

				if (ClassOfPnsn == 'Family Pension') {
					efpAmount = Number(lastEmolument) / 2;
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
					efpAmount = Number(lastEmolument) / 2;
				} else {
					efpAmount = ((Number(lastEmolument) / 2) < Number(avgEmolument)) ? (Number(lastEmolument) / 2)
							* (Number(totalSms) / 66)
							: Number(avgEmolument) * (Number(totalSms) / 66);
				}
			} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
				if (ClassOfPnsn == 'Family Pension') {
					efpAmount = Number(lastEmolument) / 2;
				} else if (ClassOfPnsn == 'VOLUNTARY64'
						|| ClassOfPnsn == 'VOLUNTARY65') {
					efpAmount = Number(lastEmolument) / 2;
				} else {
					efpAmount = ((Number(lastEmolument) / 2) < Number(avgEmolument)) ? (Number(lastEmolument) / 2)
							* (Number(totalSms) / 66)
							: Number(avgEmolument) * (Number(totalSms) / 66);
				}
			}
			if (PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS') {
				if (Number(efpAmount) > 39500)
					efpAmount = 39500;

				if (Number(efpAmount) < 2882 && Number(efpAmount) > 0)
					efpAmount = 2882;
			} else {
				if (Number(efpAmount) > 45000)
					efpAmount = 45000;

				if (Number(efpAmount) < 3500 && Number(efpAmount) > 0)
					efpAmount = 3500;
			}
			document.getElementById("txtFP1TotlAmt").value = Math
					.ceil(efpAmount);

			rrAmount = Number(lastEmolument) * 0.3;
			if (PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS') {
				if (Number(rrAmount) > 39500)
					rrAmount = 39500;

				if (Number(rrAmount) < 2882 && Number(rrAmount) > 0)
					rrAmount = 2882;
			} else {
				if (Number(rrAmount) > 45000)
					rrAmount = 45000;

				if (Number(rrAmount) < 3500 && Number(rrAmount) > 0)
					rrAmount = 3500;
			}

			document.getElementById("txtFP2TotlAmt").value = Math
					.ceil(rrAmount);
		} else {
			resetAllAmnt();
		}
	}
	// --------------------------ends----------------------
	else if (PayComsn == 'Fifth Pay Commission') {

		lastEmolument = Number(lastBasic) + Number(lastGradePay)
				+ Number(lastNPA);

		if (Number(totalSms) > 20) {

			if (ClassOfPnsn == 'Family Pension') {

			} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
					|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
				PensionAmount = (Number(avgEmolument) / 2 * Number(totalSms) / 66);
			} else {
				PensionAmount = (Number(avgEmolument) / 2 * Number(totalSms) / 66);
			}
			document.getElementById("txtTotPnsnAmt").value = Math
					.ceil(PensionAmount); // Total Pension Editable
			document.getElementById("txtTotlPnsnAmt").value = Math
					.ceil(PensionAmount); // Total Pension ReadOnly

			if (document.getElementById("radioDoWantCommuteYes").checked) {

				document.getElementById("txtMonthAmt").value = Math
						.ceil(PensionAmount / 3); // Monthly Commuted Pension
													// amount
				document.getElementById("txtComPnsnAmt").value = Math
						.ceil(PensionAmount / 3); // Commuted Pension
													// amount(same as monthly
													// commuted amount)

				var age = ageFromDOBAndDOR(DOB, DOR);

				if (Number(age) > 85) {
					age = 85;
				}
				getCVPRateFromAge(age, 'Fifth Pay Commission');
				cvpRate = document.getElementById("hdnCvpRate").value;

				document.getElementById("txtCVPAmt").value = Math
						.ceil(Number(document.getElementById("txtComPnsnAmt").value)
								* 12 * Number(cvpRate));
			}

		}

		/*
		 * else if(Number(totalSms) <= 20){
		 * 
		 * serviceGratiity = (Number(lastEmolument)/4) *6 ; }
		 * document.getElementById("txtServGratyAmt").value =
		 * Math.ceil(serviceGratiity);
		 */

		if (Number(totalSms) < 2) {
			serviceGratiity = 2 * Number(lastEmolument);

		} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
			serviceGratiity = 6 * Number(lastEmolument);

		} else if (Number(totalSms) >= 10 && Number(totalSms) < 40) {
			serviceGratiity = 12 * Number(lastEmolument);

		} else if (Number(totalSms) >= 40) {
			// alert("serviceGratiity3:"+serviceGratiity);
			serviceGratiity = (Number(lastEmolument) / 2) * Number(totalSms);

		}

		if (Number(serviceGratiity) > 350000)
			serviceGratiity = 350000;

		document.getElementById("txtServGratyAmt").value = Math
				.ceil(serviceGratiity);

		if (ClassOfPnsn != 'Family Pension') {
			document.getElementById("txtRedsPnsnAmt").value = Number(document
					.getElementById("txtTotlPnsnAmt").value)
					- Number(document.getElementById("txtComPnsnAmt").value);
		}

		withheldAmt = document.getElementById("txtWitheldGratuity").value;
		// alert ("withheldAmt**************************"+withheldAmt);

		DCRGamount = Number(serviceGratiity) - Number(withheldAmt);

		document.getElementById("txtTotDCRGAmt").value = serviceGratiity;

		efpAmount = (Number(lastEmolument) / 2 < Number(PensionAmount)) ? Number(lastEmolument) / 2
				: PensionAmount;
		document.getElementById("txtFP1TotlAmt").value = Math.ceil(efpAmount);

		rrAmount = Number(lastEmolument) * 0.3;
		document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);
		// alert("rrAmount: "+rrAmount);
	}
	// TODO
	// Added by shraddha for 7th pay on 27/01/2017
	else if (PayComsn == 'Seventh Pay Commission') {
		// alert("7THPAYCOMSN1111111");
		// alert("ClassOfPnsn: "+ClassOfPnsn);
		if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')) {
			/*
			 * if(Number(lastBasic)>67000) lastBasic = 67000
			 * if(Number(lastGradePay)>12000) lastGradePay = 12000
			 */

			lastEmolument = Number(lastBasic) + Number(lastGradePay)
					+ Number(lastNPA);
			// alert("lastEmolument: "+lastEmolument);
			// alert("lastBasic: "+lastBasic);
			// alert("lastGradePay: "+lastGradePay);
			// alert("lastNPA: "+lastNPA);*/
			// Pension Calculation

			if (Number(totalSms) > 0) {
				if (ClassOfPnsn == 'Family Pension') {
					// No pension calculate
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)') {

					// alert("ClassOfPnsn: "+ClassOfPnsn);
					if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
							&& compareDatesWithoutAlert(DOR, ActualDate6thPC,
									'<')) {

						PensionAmount = (Number(lastEmolument) / 2)
								* (Number(totalSms) / 66);

					} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC,
							'>')) {
						PensionAmount = ((Number(lastEmolument) / 2) > (Number(avgEmolument) / 2)) ? (Number(lastEmolument) / 2)
								: (Number(avgEmolument) / 2);
					}

				} else {

					PensionAmount = ((Number(lastEmolument) / 2) > (Number(avgEmolument) / 2)) ? (Number(lastEmolument) / 2)
							: (Number(avgEmolument) / 2);
					// alert("PensionAmount: "+PensionAmount);
				}
				// TODO

				// alert("inside maharashhta");
				if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS' || PnsnCatg != 'IFS') {
					// alert("not in ias,ips");
					if (State == 27) {
						if (Number(PensionAmount) > 125000) {
							PensionAmount = 125000;
						}
						/*
						 * else if(Number(PensionAmount) < 125000 &&
						 * Number(PensionAmount) > 0){ alert("PensionAmount11:
						 * "+PensionAmount); PensionAmount = PensionAmount; }
						 */

						else if (Number(PensionAmount) < 7500
								&& Number(PensionAmount) > 0) {
							// alert("PensionAmount13: "+PensionAmount);
							PensionAmount = 7500;
						} else if (Number(PensionAmount) >= 7500
								&& Number(PensionAmount) > 0) {
							// alert("PensionAmount14: "+PensionAmount);
							PensionAmount = PensionAmount;
						}

					} else {
						// alert(" in ias,ips");
						if (Number(PensionAmount) > 125000) {
							PensionAmount = 125000;
						} else if (Number(PensionAmount) < 9000
								&& Number(PensionAmount) > 0) {
							// alert("other state");
							PensionAmount = 9000;
						} else if (Number(PensionAmount) >= 9000
								&& Number(PensionAmount) > 0) {
							// alert("PensionAmount3: "+PensionAmount);
							PensionAmount = PensionAmount;
						}

					}
					document.getElementById("txtTotPnsnAmt").value = Math
							.ceil(PensionAmount); // Total Pension Editable
					document.getElementById("txtTotlPnsnAmt").value = Math
							.ceil(PensionAmount); // Total Pension ReadOnly

				}
				// Service Gratiity

				// modify by sampada

				if (ClassOfPnsn == 'Family Pension') {
					//alert("lastBasic: " + lastBasic);
					//alert("lastEmolument: " + lastEmolument)
					//alert("totalSms: " + totalSms);
					// for 1 - 1)
					// alert("inside family")
					if (Number(totalSms) < 2) {
						// alert("family : 1")
						// alert("(Number(totalSms) < 2)")
						serviceGratiity = 2 * Number(lastEmolument);

					}
					// for 1 to 5 --2)
					else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
						// alert("(Number(totalSms) >= 2 && Number(totalSms)
						// <10)")
						serviceGratiity = 6 * Number(lastEmolument);

					}
					// for 5 to 11 -- 3)
					else if (Number(totalSms) >= 10 && Number(totalSms) < 22) {
						// alert("(Number(totalSms) >= 11 && Number(totalSms) <
						// 22)")
						serviceGratiity = 12 * Number(lastEmolument);

					} else if (Number(totalSms) >= 22 && Number(totalSms) < 40) {
						// alert("family : 22-40: ")
						// alert("(Number(totalSms) >= 22 && Number(totalSms) <
						// 40)")
						serviceGratiity = 20 * Number(lastEmolument);
						// alert("serviceGratiityfa,ily22-40: "+serviceGratiity)

					}// for 20 -- 4)

					else if (Number(totalSms) >= 40)
					{
						// alert("Number(totalSms) >= 40")
						if (Number(totalSms) >= 66) {
							totalSms = 66;
						} else {
							totalSms = totalSms;
						}
						// alert("totalSms: "+totalSms)
						/*serviceGratiity = (Number(lastEmolument) / 4)
								* totalSms;*/
						serviceGratiity = (Number(lastEmolument) / 2)
						* totalSms;
						// alert("serviceGratiity max 40= "+serviceGratiity);

						// alert("serviceGratiity:"+serviceGratiity);
					}
				} else if (ClassOfPnsn == 'INVALID'
						|| ClassOfPnsn == 'Superannuation Pension (Rule 63)') {
					// alert(" : VO: " )
					//alert("super22");
					//alert("totalSms: " + totalSms);
					//alert("lastBasic: " + lastBasic);
					// alert("totalSms 12: "+totalSms)
					//alert("lastEmolument: " + lastEmolument)
					if (Number(totalSms) < 2) {
						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 10 && Number(totalSms) < 20) {
						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 20 && Number(totalSms) < 40) {
						serviceGratiity = (Number(lastEmolument) / 4)
								* Number(totalSms);

					} else if (Number(totalSms) >= 40) {
						// alert("serviceGratiity= "+serviceGratiity);
						serviceGratiity = (Number(lastEmolument) / 4)
								* Number(totalSms);
					}

				} else {
					//alert("other");
					//alert("totalSms: " + totalSms);
					//alert("lastBasic: " + lastBasic);
					// alert("totalSms 12: "+totalSms)
					//alert("lastEmolument: " + lastEmolument)
					if (Number(totalSms) < 2) {
						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 2 && Number(totalSms) < 10) {
						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 10 && Number(totalSms) < 22) {
						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 22 && Number(totalSms) < 40) {

						alert("Qualifing services Less than 20 years");
					} else if (Number(totalSms) >= 40) {
						// alert("serviceGratiity= "+serviceGratiity);
						serviceGratiity = (Number(lastEmolument) / 4)
								* Number(totalSms);
					}
				}
				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}

				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {

					if (compareDatesWithoutAlert(DOE, DCRGMinDate, '<')) {
						// edited by aditya
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					} else if (compareDatesWithoutAlert(DOE, DCRGMinDate, '>')) {
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}
				}
			} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
					|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {

				serviceGratiity = (Number(lastEmolument) * Number(totalSms)) / 4;

				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {
						// edited by aditya
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;

					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioBn").checked))) {
						// edited by aditya
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;

					} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
							DOR, DCRGMaxDate, '=') && document
							.getElementById("radioAn").checked))) {

						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}
				}
			}

			
			if (State == 27) {

				// alert("serviceGratiitySampad:11111111111 "+serviceGratiity);
				// alert("inside 1")
				if (Number(serviceGratiity) > 1400000) {
					// alert("inside 2")
					serviceGratiity = 1400000;
				}
			} else {
				// alert("outside maharashtra");
				if (Number(serviceGratiity) > 2000000) {
					// alert("inside 3")
					serviceGratiity = 2000000;
				}

			}

			if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
					&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {
				if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
						DOR, DCRGMaxDate, '=') && document
						.getElementById("radioBn").checked))) {

					// edited by shraddha
					if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS'
							|| PnsnCatg != 'IFS') {
						// alert("inside ias")
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					} else {
						// edited by aditya
						if (Number(serviceGratiity) > 2000000)
							// alert("inside else")
							serviceGratiity = 2000000;
					}
				} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
						DOR, DCRGMaxDate, '=') && document
						.getElementById("radioAn").checked))) {

					// edited by shraddha
					if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS'
							|| PnsnCatg != 'IFS') {
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}

					else {
						if (Number(serviceGratiity) > 2000000)
							// alert("else else")
							serviceGratiity = 2000000;
					}
				}
			} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
				if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '<') || (compareDatesWithoutAlert(
						DOR, DCRGMaxDate, '=') && document
						.getElementById("radioBn").checked))) {

					// edited by shraddha
					if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS'
							|| PnsnCatg != 'IFS') {
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					} else {
						// edited by aditya
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}
				} else if ((compareDatesWithoutAlert(DOR, DCRGMaxDate, '>') || (compareDatesWithoutAlert(
						DOR, DCRGMaxDate, '=') && document
						.getElementById("radioAn").checked))) {

					// edited by shraddha
					if (PnsnCatg != 'IAS' || PnsnCatg != 'IPS'
							|| PnsnCatg != 'IFS') {
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					} else {
						if (Number(serviceGratiity) > 2000000)
							serviceGratiity = 2000000;
					}
				}
			}

			// alert("start")

			if (State == 27) {
				// alert("inside maha")
				if ((serviceGratiity) > 1400000) {
					// alert("serviceGratiity 1: "+serviceGratiity)
					// alert("inside maha")
					serviceGratiity = 1400000;
				}
			} else if (Number(serviceGratiity) > 2000000) {
				// alert("serviceGratiity)>2000000")
				// alert("other")

				// alert("serviceGratiityother 2: "+serviceGratiity)
				serviceGratiity = 2000000;

			}

			// alert("serviceGratiity_actual: "+serviceGratiity)
			document.getElementById("txtServGratyAmt").value = Math
					.ceil(serviceGratiity);
			// alert("serviceGratiity:1::::"+serviceGratiity)

			// DCRG calculation
			// alert("withheldAmt")
			var withheldAmt = document.getElementById("txtWitheldGratuity").value;
			// alert ("withheldAmt*****"+withheldAmt);
			// alert("serviceGratiity::::: "+serviceGratiity)
			DCRGamount = Number(serviceGratiity) - Number(withheldAmt);
			// alert("DCRGamount: "+DCRGamount);

			document.getElementById("txtTotDCRGAmt").value = Math
					.ceil(DCRGamount);
			// CVP Calculation

			if (document.getElementById("radioDoWantCommuteYes").checked
					&& Number(totalSms) > 20) {
				if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
						&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

					if (ClassOfPnsn == 'Family Pension') {

					} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
							|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount) / 3;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					} else {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount) / 3;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					}
					payCom = 'Fifth Pay Commission';
				} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
					if (ClassOfPnsn == 'Family Pension') {

					} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
							|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					} else {
						if (PnsnCatg != 'High Court') {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						} else {
							commutedPension = Number(PensionAmount)
									* Number(commutPer) / 100;
						}
					}
					payCom = '6th Pay Commission';
				}
				document.getElementById("txtComPnsnAmt").value = Math
						.ceil(commutedPension);
				document.getElementById("txtMonthAmt").value = Math
						.ceil(commutedPension);
				var age = ageFromDOBAndDOR(DOB, DOR);

				if (Number(age) > 81) {
					age = 81;
				}
				getCVPRateFromAge(age, payCom);
				cvpRate = document.getElementById("hdnCvpRate").value;

				document.getElementById("txtCVPAmt").value = Math
						.ceil(Number(commutedPension) * 12 * Number(cvpRate));

			}
			if (ClassOfPnsn != 'Family Pension') {
				document.getElementById("txtRedsPnsnAmt").value = Number(document
						.getElementById("txtTotlPnsnAmt").value)
						- Number(document.getElementById("txtComPnsnAmt").value);
			}
			// EFP and RR amount claculation

			if (compareDatesWithoutAlert(DOR, FirstDate6thPC, '>')
					&& compareDatesWithoutAlert(DOR, ActualDate6thPC, '<')) {

				if (ClassOfPnsn == 'Family Pension') {
					efpAmount = Number(lastEmolument) / 2;
				} if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
					|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
					efpAmount = Number(lastEmolument) / 2;
				} else {
					efpAmount = Number(lastEmolument) / 2;
					// efpAmount = ((Number(lastEmolument)/2) <
					// Number(avgEmolument)) ?
					// (Number(lastEmolument)/2)*(Number(totalSms)/66) :
					// Number(avgEmolument) *(Number(totalSms)/66);
				}

			} else if (compareDatesWithoutAlert(DOR, ActualDate6thPC, '>')) {
				if (ClassOfPnsn == 'Family Pension') {
					efpAmount = Number(lastEmolument) / 2;
				} else if (ClassOfPnsn == 'Voluntary Pension Rule 65 (30 Yrs Service)'
						|| ClassOfPnsn == 'Voluntary Pension Rule 65 (20 Yrs Service)') {
					efpAmount = Number(lastEmolument) / 2;
				} else {
					efpAmount = Number(lastEmolument) / 2;
					// efpAmount = ((Number(lastEmolument)/2) <
					// Number(avgEmolument)) ?
					// (Number(lastEmolument)/2)*(Number(totalSms)/66) :
					// Number(avgEmolument) *(Number(totalSms)/66);
				}
			}// TODO

			if (PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS') {
				/*
				 * if(Number(efpAmount) > 39500) efpAmount = 39500;
				 * 
				 * if(Number(efpAmount) < 2882 && Number(efpAmount) > 0)
				 * efpAmount = 2882;
				 */
				if (State == 27) {
					if (efpAmount < 7500 && Number(efpAmount) > 0) {
						// alert("efp:1: "+efpAmount);
						efpAmount = 7500;
					} else if (Number(efpAmount) >= 7500
							&& Number(efpAmount) > 0) {
						// alert("efp2: "+efpAmount);
						efpAmount = efpAmount;
					}
					/*
					 * if(efpAmount < 9000 && Number(efpAmount) > 0){
					 * alert("efp:2"); efpAmount=9000; }
					 */
				} else {
					/*
					 * if(Number(efpAmount) > 45000) efpAmount = 45000;
					 * 
					 * if(Number(efpAmount) < 3500 && Number(efpAmount) > 0)
					 * efpAmount = 3500;
					 */
					/*
					 * if(efpAmount > 75000 && Number(efpAmount) > 0){
					 * alert("efp:3"); efpAmount=75000; }
					 */
					if (efpAmount < 9000 && Number(efpAmount) > 0) {
						// alert("efp3: "+efpAmount);
						efpAmount = 9000;
					} else if (Number(efpAmount) >= 9000
							&& Number(efpAmount) > 0) {
						// alert("efp4: "+efpAmount);
						efpAmount = efpAmount;
					}
				}

				// Added by shraddha for EFP,FP limit

				document.getElementById("txtFP1TotlAmt").value = Math
						.ceil(efpAmount);

				rrAmount = Number(lastEmolument) * 0.3;

				if(ClassOfPnsn=="Superannuation Pension (Rule 63)"){
					rrAmount=rrAmount*2;
				}else if (PnsnCatg != 'IAS' || PnsnCatg != 'IFS' || PnsnCatg != 'IPS') {
					/*
					 * if(Number(rrAmount) > 39500) rrAmount = 39500;
					 * 
					 * if(Number(rrAmount) < 2882 && Number(rrAmount) > 0)
					 * 
					 * rrAmount = 2882;
					 */
					// TODO
					if (State == 27) {
						// alert("rrAmount1:"+rrAmount);
						if (rrAmount < 7500 && Number(rrAmount) > 0) {
							// alert("rrAmount12:"+rrAmount);
							rrAmount = 7500;
						} else if (rrAmount >= 7500
								&& Number(rrAmount) <= 120000) {
							// alert("rrAmount13:"+rrAmount);
						}
						rrAmount = rrAmount;
					}

					/*
					 * if(rrAmount < 9000 && Number(rrAmount) > 0){
					 * rrAmount=9000; }
					 */

					else {
						/*
						 * if(Number(rrAmount) > 45000) rrAmount = 45000;
						 * 
						 * if(Number(rrAmount) < 3500 && Number(rrAmount) > 0)
						 * rrAmount = 3500;
						 */
						/*
						 * if(rrAmount > 75000 && Number(rrAmount) > 0){
						 * rrAmount= 75000; }
						 */
						// alert("rrAmount3:"+rrAmount);
						if (rrAmount < 9000 && Number(rrAmount) > 0) {
							// alert("rrAmount4:"+rrAmount);
							rrAmount = 9000;
						} else if (rrAmount >= 9000
								&& Number(rrAmount) <= 125000) {
							// alert("rrAmount5:"+rrAmount);
							rrAmount = rrAmount;
						}
					}
				}
			}
		}
		document.getElementById("txtFP2TotlAmt").value = Math.ceil(rrAmount);  //family pension 
	} else {
		resetAllAmnt();
	}
}



/*function calTotalSrvcBrk(count){
	return true;
}*/



function calTotalSrvcBrk(count)
{
	//alert("inside calTotalSrvcBrk");
	try{
	var brkFrom = document.getElementById("txtDateOfBrkFrom"+count).value;
	var brkTo = document.getElementById("txtDateOfBrkTo"+count).value;
		/*alert("Sampada brkTo:"+brkTo)
		alert("Sampada brkFrom:"+brkFrom)*/
	var dayDiff = 0;
	if(brkFrom == "" || brkTo == "")
	{
	return 0;
	}
	var brkFromDateArr = brkFrom.split("-");
	var brkFromDateDay = brkFromDateArr[2];
	var brkFromDateMonth = brkFromDateArr[1];
	
	
	var brkFromDateYear = brkFromDateArr[0];
	
	var brkToDateArr = brkTo.split("-");   // 01/01/2022
	var brkToDate = new Date(brkToDateArr[2] +"/"+brkToDateArr[1]+"/"+ brkToDateArr[0]);
	brkToDate.setDate(brkToDate.getDate()+1);
	
	
	
	var brkToDateDay = brkToDateArr[2];
	var brkToDateMonth = brkToDateArr[1];
	var brkToDateYear = brkToDateArr[0];
	//alert("Sampada brkFromDateMonth:"+brkFromDateMonth);
	//alert("Sampada brkToDateMonth:"+brkToDateMonth);
	if(Number(brkFromDateYear) > Number(brkToDateYear))
	{
		alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
		document.getElementById("txtDateOfBrkTo"+count).value ="";
		document.getElementById("txtDateOfBrkTo"+count).focus();
	}
	else
		{
		//alert("Logic started..");
		if(Number(brkFromDateYear) == Number(brkToDateYear)) 
			{
			//alert("In From and To year same..");
			if(Number(brkFromDateMonth) > Number(brkToDateMonth))
			{
			alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
			document.getElementById("txtDateOfBrkTo"+count).value ="";
			document.getElementById("txtDateOfBrkTo"+count).focus();
			}
			else if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
			{
				//alert("In From and To month same..");
			if(Number(brkFromDateDay) > Number(brkToDateDay))
			{
			//alert("brkFromDateDay1"+brkFromDateDay);
			alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
			document.getElementById("txtDateOfBrkTo"+count).value ="";
			document.getElementById("txtDateOfBrkTo"+count).focus();
			//alert(brkToDateDay+":brkToDateDay1");
			}
			else if(Number(brkFromDateDay) ==  Number(brkToDateDay))
			{
				//alert("inside bye");
				dayDiff = 1;
				monthDiff = 0;
				yearDiff = 0;
			//	alert("brkFromDateDay2 "+brkFromDateDay);
			//	alert(brkToDateDay+":brkToDateDay22");
			}
			/*else if(Number(brkFromDateDay) <  Number(brkToDateDay))
					//if( brkToDateMonth == 2 && brkFromDateMonth==2 && brkToDateDay == 28)
			{
				alert(brkToDateDay+":brkToDateDay17 1313");
				dayDiff = (Number(brkToDateDay) - Number(brkFromDateDay)+1); //((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
				//	alert('dayDiffdayDiff test 403:'+dayDiff);
				
			}*/else  // brkFromDateDay is less than brkToDateDay
			{
			//if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
			//alert("not in (brkFromDateDay) ==  Number(brkToDateDay)");
			dayDiff = Number(brkToDateDay) - Number(brkFromDateDay) + 1;
			//alert(brkToDateDay+":brkToDateDay2");
			//	alert("dayDiff1"+dayDiff);
			monthDiff = 0;
			yearDiff = 0;
			//	alert("brkFromDateDay3 "+brkFromDateDay);
			}
			
			}
			else // brkFromDateMonth is less than brkToDateMonth
			{
				//alert("In From is less than To month..");
			monthDiff = Number(brkToDateMonth) - Number(brkFromDateMonth);
			yearDiff = 0;
			if(Number(brkToDateDay) == Number(brkFromDateDay))
			{
				
				//alert("In From day  and to day same..");
			//alert("brkFromDateDay1=4874 "+brkFromDateDay);
			dayDiff = 30 * monthDiff;
			//	alert(brkToDateDay+":brkToDateDay3");
			}
			else if(brkToDateMonth ==2 && brkToDateDay ==28)
			{ 
				//alert("to month2 and date 28");
				//alert("In From day  and to day not same..");
			//	alert("else (brkToDateDay) == Number(brkFromDateDay)");
			//	alert("dayDiff2"+dayDiff);
			//alert("In From day  and to day not same..");
			dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay)+3);
				/*alert("Number(brkFromDateDay) "+Number(brkFromDateDay));
				alert("Number(brkToDateDay) "+Number(brkToDateDay));
				alert("monthDiff:::"+monthDiff);
				alert("dayDiff:::"+dayDiff);*/
			}
			else
			{ 
				//alert("In From day  and to day not same..");
			//	alert("else (brkToDateDay) == Number(brkFromDateDay)");
			//	alert("dayDiff2"+dayDiff);
			//alert("In From day  and to day not same..");
			dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay)+1);
				/*alert("Number(brkFromDateDay) "+Number(brkFromDateDay));
				alert("Number(brkToDateDay) "+Number(brkToDateDay));
				alert("monthDiff:::"+monthDiff);
				alert("dayDiff:::"+dayDiff);*/
			}
			}
			//alert("dayDiff:::"+dayDiff);
		}
		else // brkFromDateYear is less than brkToDateYear
		{
			//alert("In From less than to year..");
		yearDiff = Number(brkToDateYear) - Number(brkFromDateYear);
		if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
		{
		monthDiff = 12 * yearDiff;
		if(Number(brkFromDateDay) ==  Number(brkToDateDay))
		{
		//	alert("if (brkFromDateDay) ==  Number(brkToDateDay)")
		dayDiff =  (yearDiff * 360);
		//	alert("dayDiff3:"+dayDiff);
		//	alert(brkToDateDay+":brkToDateDay5");
		}
		else
		{
		if(Number(brkToDateDay) > Number(brkFromDateDay))
		{
		//	alert("brkFromDateDay6 "+brkFromDateDay);
		//	alert("if (Number(brkToDateDay) > Number(brkFromDateDay)");
		dayDiff = (Number(brkToDateDay)-Number(brkFromDateDay)) + (360 * (yearDiff));
		//	alert("dayDiff:4 "+dayDiff);
		//	alert(brkToDateDay+":brkToDateDay6");
		}
		else//brkToDateDay is less than brkFromDateDay
		{
		
		dayDiff = (360 * (yearDiff)) - (Number(brkFromDateDay)-Number(brkToDateDay));
		//	alert("brkFromDateDay7 "+brkFromDateDay);
		//	alert(brkToDateDay+":brkToDateDay7");
		}
		}
		}
		else 
		{ 
		//alert("else(Number(brkToDateDay) > Number(brkFromDateDay)");
		if(Number(brkToDateMonth) > Number(brkFromDateMonth))
		{
		monthDiff = (12 * yearDiff) +  (Number(brkToDateMonth) - Number(brkFromDateMonth));
		if(Number(brkFromDateDay) ==  Number(brkToDateDay))
		{
		//	alert(brkToDateDay+":brkToDateDay7");
		//	alert("brkFromDateDay8 "+brkFromDateDay);
		dayDiff =  (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)));
		//	alert("dayDiff:5"+dayDiff);
		}
		else
		{
		if(Number(brkToDateDay) > Number(brkFromDateDay))
		{
		//	alert("brkFromDateDay6 "+brkFromDateDay);
		//alert(brkToDateDay+":brkToDateDay8");
		dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
		//	alert("brkToDateDay:6"+brkToDateDay);
		}
		else//brkToDateDay is less than brkFromDateDay
		{
		dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		
		//	alert("dayDiff:7"+dayDiff);
		//	alert("brkFromDateDay7 "+brkFromDateDay);
		}
		}
		
		}
		else //brkToDateMonth is less than brkFromDateMonth
		{
		//monthDiff = (12 * yearDiff) -  (Number(brkFromDateMonth) - Number(brkToDateMonth));
		if(Number(brkFromDateDay) ==  Number(brkToDateDay))
		{
		//	alert(brkToDateDay+":brkToDateDay9");
		//	alert("brkFromDateDay8 "+brkFromDateDay);
			//alert("Doubt 2");
		dayDiff =  (yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)) - 1);
		//	alert("dayDiff:8"+dayDiff);
		//	alert("brkToDateMonth "+brkToDateMonth);
		}
		else
		{	
		if(Number(brkToDateDay) > Number(brkFromDateDay))
		{
		//	alert(brkToDateDay+":brkToDateDay10");
		//	alert("brkFromDateDay9 "+brkFromDateDay);
		if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 ))) && brkToDateDay == 29){
		
		//	alert(brkToDateDay+":brkToDateDay11");
		dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
		//	alert('dayDiffdayDiff test 9:'+dayDiff);
		//Commneted by Pravin
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		
		
		}else if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 )))){
		//	alert('brkToDateDay test 303(1):'+ (Number(brkToDateDay))); 
		dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth) + 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
		/*alert('dayDiffdayDiff test 303:'+dayDiff);
		alert('month diff test 303:'+(30 * (Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))- 29) );
		alert('days Diff test 303:'+ (Number(brkToDateDay) - Number(brkFromDateDay)));   //(Number(brkToDateDay) - Number(brkFromDateDay));
		alert('brkToDateDay test 303:'+ (Number(brkToDateDay)));  
		alert('brkFromDateDay test 303:'+ ( Number(brkFromDateDay)))*/;   //(Number(brkToDateDay) - Number(brkFromDateDay));
		//Commneted by Pravin
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		//alert("brkFromDateDay10 "+brkFromDateDay);
		
		}//TODO
		else if(brkFromDateMonth==2 && brkToDateMonth==2 && brkToDateDay == 28){
			//alert(brkToDateDay+":brkToDateDay12 1166");
				//alert("dhekane");
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+2) ;
			//Commneted by Pravin
			//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
			//alert('dayDiffdayDiff test 304:'+dayDiff);
			
			//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
			//	alert("dayDiff:10"+dayDiff);
			
			}
		
		//
		
		else if(brkToDateMonth==2 && brkToDateDay == 28){
		//alert(brkToDateDay+":brkToDateDay12 1166");
			//alert("sam");
		dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
		//Commneted by Pravin
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		//alert('dayDiffdayDiff test 304:'+dayDiff);
		
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		//	alert("dayDiff:10"+dayDiff);
		
		}else if(brkToDateMonth==2 && brkToDateDay != 28){
		//	alert(brkToDateDay+":brkToDateDay13");
			//alert("Doubtful slab");
		dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)-1) ; //TODO
		//Commneted by Pravin
		//	alert("hiii");
		//	alert("brkFromDateDay: "+brkFromDateDay);
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		//alert('dayDiffdayDiff test 305:'+dayDiff);
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		//	alert("dayDiff:11"+dayDiff);
		
		}
		else{
		dayDiff = ((yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkToDateDay) -  Number(brkFromDateDay) ) ;//commented by Kinjal//TODO
		//alert('dayDiffdayDiff test 306:'+dayDiff);	
		//	alert("dayDiff:12"+dayDiff);
		//	alert(brkToDateDay+":brkToDateDay14");
		}
		}
		else	//brkToDateDay is less than brkFromDateDay   
		{	//Adde by pravin 
		//  for brkToDateDay is less than brkFromDateDay ----- Start----
		
		if(Number(brkToDateMonth) == 2 && (Math.floor(Number(brkToDateYear%4 == 0 ))) && brkToDateDay == 29){
		//	alert(brkToDateDay+":brkToDateDay15");
		dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
		//	alert('dayDiffdayDiff test 401 :'+dayDiff);
		//	alert(brkToDateDay+":brkToDateDay16");
		}else if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 )))){
		
		dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkFromDateDay) - Number(brkToDateDay) - 2); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
		//	alert('dayDiffdayDiff test 402:'+dayDiff);
		//Commneted by Pravin
		//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
		
		
		}else if( brkToDateMonth == 2 && brkFromDateMonth==2 && brkToDateDay == 28){
		//alert(brkToDateDay+":brkToDateDay17 1212");
		dayDiff = ((yearDiff * 360) + (Number(brkToDateDay) - Number(brkFromDateDay)+1)) ; //(30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
		//	alert('dayDiffdayDiff test 403:'+dayDiff);
		
		}else if(brkToDateMonth==2 && brkFromDateMonth==2){
		//alert("Check 1");
		dayDiff = ((yearDiff * 360) +   (Number(brkToDateDay) - Number(brkFromDateDay)-1)) ;
		//	alert('dayDiffdayDiff test 404:'+dayDiff);
		//	alert(brkToDateDay+":brkToDateDay18");
		
		}else{
		
		dayDiff = (( yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay) + 1) ;//commented by Kinjal
		//dayDiff = (( yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;//commented by Kinjal
		/*	alert('dayDiffdayDiff test 405:'+dayDiff);
		alert('brkToDateDay test 405:'+brkToDateDay);
		alert('brkFromDateDay test 405:'+brkFromDateDay);
		alert(brkToDateDay+":brkToDateDay18");*/
		
		}
		}
		
		
		}
		}
		}
		}
		}
	//alert("Break  dayDiff:::"+dayDiff);
	document.getElementById("hidDays"+count).value = dayDiff
	//alert("Break  dayDiff:::"+document.getElementById("hidDays"+count).value);

	
	}catch(e){
	
	}
	
}
	



function smsCalculation() {

	var smsYear = document.getElementById("txtQualifyingServYear").value;
	var smsMonth = document.getElementById("txtQualifyingServMonth").value;
	var smsDays = document.getElementById("txtQualifyingServDay").value;
	var noOfSms;
	
	var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text();
	if (ClassOfPnsn == 'Family Pension' || ClassOfPnsn == 'Voluntary Pension'
			|| ClassOfPnsn == 'Voluntary Pension') {
		if (Number(smsYear) >= 20) {
			smsYear = Number(smsYear);
		}
		if (Number(smsYear) >= 33) {
			noOfSms = 66;
		}
	}

	if (Number(smsYear) > 0) {
		noOfSms = 2 * Number(smsYear);
		if (Number(noOfSms) > 66) {
			noOfSms = 66;
		} else {
			if (Number(noOfSms) == 66) {
				noOfSms = noOfSms;
			} else 
			{
				if (Number(smsMonth) >= 3 && Number(smsMonth) < 9) {
					noOfSms = noOfSms + 1;
				} else if (Number(smsMonth) >= 9) {
					noOfSms = noOfSms + 2;
				} else 
				{
					noOfSms = noOfSms;
				}
			}
		}
	} else if (Number(smsYear) < 1)

	{
		noOfSms = 2 * Number(smsYear);
		if (Number(noOfSms) > 66) {
			noOfSms = 66;
		} else {
			if (Number(noOfSms) == 66) {
				noOfSms = noOfSms;
			} else // no. of SMS are less than 66
			{
				if (Number(smsMonth) >= 3 && Number(smsMonth) < 9) {
					noOfSms = noOfSms + 1;
				} else if (Number(smsMonth) >= 9) {
					noOfSms = noOfSms + 2;
				} else // smsMonth is less than 3
				{
					noOfSms = noOfSms;
				}
			}
		}
	}
	return noOfSms;
}


function resetAllAmnt() {
	document.getElementById("txtMonthAmt").value = 0;
	document.getElementById("txtCVPAmt").value = 0;
	document.getElementById("txtTotPnsnAmt").value = 0;
	document.getElementById("txtRedsPnsnAmt").value = 0;
	document.getElementById("txtTotlPnsnAmt").value = 0;
	document.getElementById("txtComPnsnAmt").value = 0;
	document.getElementById("txtServGratyAmt").value = 0;
	document.getElementById("txtTotDCRGAmt").value = 0;
	document.getElementById("txtFP1TotlAmt").value = 0;
	document.getElementById("txtFP2TotlAmt").value = 0;
}



$("#btnResetBottom").click(function(){
	resetAllAmnt();
});




function compareDatesWithoutAlert(Date1,Date2,flag)
{
	var one_day = 1000*60*60*24; 
    
    var date1 = new Date(Date1);
    var date2 = new Date(Date2);
    
	var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
	
	if(flag == '=' &&  Number(Diff) == 0){		
		return false;		
	}

    else if( (flag == '<' &&  Number(Diff)<=0) || (flag == '>' && Number(Diff)>=0))
    {
         	return false;
    }
    else {    
    	return true;
    }
}

function ageFromDOBAndDOR(birthDate, retirementDate) {
	var dateofNextBirthDay=$("#dateofNextBirthDay").val();
	
	if(dateofNextBirthDay==''){
		var birthArrDate = birthDate.split("-");    
		var bday = birthArrDate[2];
		var bmo = birthArrDate[1];
		var byr = parseInt(birthArrDate[0]);
		var ClassOfPnsn =$("#cmbClassOfPnsn option:selected").text();
		var age;
		
		var retirementArrDate = retirementDate.split("-");
		var rday = retirementArrDate[2];
		var rmo = retirementArrDate[1];
		var ryr = parseInt(retirementArrDate[0]);
		
		if (ClassOfPnsn == "Superannuation Pension") {
			if ((bday == 01 && bmo == 01)) {
				age = byr - 1;
			} else {
				age = byr;
			}
		}
		else {
			if (bmo < rmo || (bmo == rmo && bday < rday)) {
				age = byr;
			} else {
				age = byr + 1;
			}
		}
		
		ryr = Number(ryr) + 1;
		if (isNaN(ryr - age) == true) {
			return 0;
		} else if ((ryr - age) > 150 || (ryr - age) <= -1) {
			return "N.A.";
		} else {
			return (Number(ryr - age));
		}
		
	}else{
		
		var birthArrDate = birthDate.split("-");    
		var bday = birthArrDate[2];
		var bmo = birthArrDate[1];
		var byr = parseInt(birthArrDate[0]);
		var ClassOfPnsn =$("#cmbClassOfPnsn option:selected").text();
		var age;
		
		var dateofNextBirthDay=$("#dateofNextBirthDay").val();
		
		retirementDate=dateofNextBirthDay;
		
		var retirementArrDate = retirementDate.split("-");
		var rday = retirementArrDate[2];
		var rmo = retirementArrDate[1];
		var ryr = parseInt(retirementArrDate[0]);
		
		if (ClassOfPnsn == "Superannuation Pension") {
			if ((bday == 01 && bmo == 01)) {
				age = byr - 1;
			} else {
				age = byr;
			}
		}
		else {
			if (bmo < rmo || (bmo == rmo && bday < rday)) {
				age = byr;
			} else {
				age = byr + 1;
			}
		}
		
		//ryr = Number(ryr) + 1;
		ryr = Number(ryr);
		if (isNaN(ryr - age) == true) {
			return 0;
		} else if ((ryr - age) > 150 || (ryr - age) <= -1) {
			return "N.A.";
		} else {
			return (Number(ryr - age));
		}
	}
}

function getCVPRateFromAge(Age, PayCommission) {
	
	var payCommsionCode=$("#payCommissionCode").val();
	var age=Age;
	
	$.ajax({
	      type: "POST",
	      url: "../pension/getCVPRateByPayCommsionCode/"+payCommsionCode+"/"+age,
	      async: false,
	      contentType:'application/json',
		 // dataType : 'json',
	      error: function(data){
	    	  console.log(data);
	      },
	     /* data:{
	    	  payCommisionCode:payCommsionCode
	      },*/
	      success: function(data){
	    	  console.log(data);
	    	    //ajax 
	    		document.getElementById("hdnCvpRate").value = data;
	    		document.getElementById("txtCvpRate").value = data;
	    	}
	 });
}


//  $("#configuration_sidebar_content").find(".active").attr('id')
 
$(".nav-item").click(function(){
	var tabId=$(this).attr("id");
	hideShowSubmitBtn(tabId);
});



function hideShowSubmitBtn(tabId){
	if(parseInt(tabId)<7){
		$("#next").removeClass("hide");
		$("#submit").addClass("hide");
		if(parseInt(tabId)<7 && parseInt(tabId)>1)
		     $("#previousBlock").removeClass("hide");
		else
			$("#previousBlock").addClass("hide");
	}else{
		$("#next").addClass("hide");
		$("#submit").removeClass("hide");
		$("#previousBlock").removeClass("hide");
	}
}

$("#Previous").click(function(){
	var tabId=$(".nav-item.active").attr("id");
	$(".nav-item.active").removeClass("active");
	
	var prevId=parseInt(tabId)-1;
	$("#"+prevId).addClass("active");
	document.getElementById("tab"+prevId).click();
	
	hideShowSubmitBtn(prevId);
});


$("#saveAndNext").click(function(){
	var tabId=$(".nav-item.active").attr("id");
	$(".nav-item.active").removeClass("active");
	var prevId=parseInt(tabId)+1;
	$("#"+prevId).addClass("active");
	document.getElementById("tab"+prevId).click();
	hideShowSubmitBtn(prevId);
});





$("#txtWitheldGratuity").blur(function(){      
			var txtWitheldGratuity=$("#txtWitheldGratuity").val();
			var txtServGratyAmt=$("#txtServGratyAmt").val();
			$("#txtTotDCRGAmt").val(parseInt(txtServGratyAmt)-parseInt(txtWitheldGratuity));
		});














$(document).ready(function(){
	
	if($("#logUser").text().includes("_AST")){
		var commuteOrNot = $('input[name="commuteOrNot"]:checked').val();
		if(commuteOrNot=='' || commuteOrNot==undefined){
			$("#radioDoWantCommuteYes").attr("checked","checked");
		}
	}
	
	
	setFP1AndFp2Date(1);
	
	calculateTotalQualServ();
	
	  var roleId=$("#roleId").val();
	  if(roleId!=='' && roleId!=undefined){
		  switch(roleId){
		  case "15":
			  $('form *').prop('disabled', false);
			  $(":input").not("[name=btnPrevious], [name=btnNext],[name=btnSavedraft],[name=btnFrwrd2AG],[name=btnSaveForward],[name=btnClose]").prop("readonly", false);

			  break;
		  case "2":
			  $(":input").not("[name=btnPrevious], [name=btnNext],[name=btnApprove],[name=btnReject]").prop("readonly", false);
			  $('select').prop('disabled', false);
			  break;
		  case "12":
			  $(":input").not("[name=btnPrevious], [name=btnNext],[name=btnApprove],[name=btnReject]").prop("readonly", true);
			  $('select').prop('disabled', true);
			  break;
		  case "13":
			  $(":input").not("[name=btnPrevious], [name=btnNext],[name=btnApprove],[name=btnReject]").prop("readonly", true);
			  $('select').prop('disabled', true);
			  break;
		  case "14":
			  $(":input").not("[name=btnPrevious], [name=btnNext],[name=btnApprove],[name=btnReject]").prop("readonly", true);
			  $('select').prop('disabled', true);
			  break;  
		  }  
	  }
	  
	  
	  var params = new window.URLSearchParams(window.location.search); 
		
		if(params.get('sevaarthId') !=null){
			var sevaarthId=params.get('sevaarthId');
			 var PensionCaseType=params.get('classOfPension');
			 var cmbClassOfPnsn=params.get('classOfPension');
			
			$("#txtSevaarthId").val(sevaarthId);
			$("#cmbClassOfPnsn").val(cmbClassOfPnsn);
			
			if(Number(cmbClassOfPnsn)==84){   //family pension common id is 84
				$("#txtDateOfExpiry").prop("disabled",false);
				$("input[name='radioEfp']").prop("disabled",false);
			}else if(Number(cmbClassOfPnsn)==79){
				$("#txtDateOfRetiremt").prop("disabled",false);
				$("#txtDateOfRetiremt").removeClass("ReadonltDD");
				//$("#txtDateOfExpiry").prop("disabled",false);
				//$("input[name='radioEfp']").prop("disabled",false);
			}else if(Number(cmbClassOfPnsn)==74){
				$("#txtDateOfRetiremt").prop("disabled",false);
				$("#txtDateOfRetiremt").removeClass("ReadonltDD");
			}
			else{
				$("#txtDateOfExpiry").prop("disabled",true);
				$("input[name='radioEfp']").prop("disabled",true);
			}
		}

		
		var dpval = $("#txtDP").val();
		if(dpval ==''){
			$("#txtDP").val(0);
		}
		
		
		var ddoCode=$("#logUser").text();
		if(ddoCode.includes("_INW")){
			$("#btnCalculateBottom").hide();
		}else if(ddoCode.includes("_AUDITOR")){
			$("#btnCalculateBottom").hide();
		}
});



$("#btnApprove").click(function(){
	  $("#caseForm").attr("action", "../approveCase"); 
});


$("#btnReject").click(function(){
	  $("#caseForm").attr("action", "../rejectCase"); 
	  
});



$("form[name='caseForm']").validate({

	 ignore: "",
	
    rules: {
    	transcType : {
			required : true,
			min : 1
		},
		payCommissionCode : {
			required : true,
			min : 1
		},
		stateCtgry : {
			required : true,
			min : 1
		},
    	inwardId: "required",
    	classOfPension : {
			required : true,
			min : 1
		},
//    	sevaarthId: "required",
    	pnsnType : {
			required : true,
			min : 1
		},
    	currDDOCode : {
			required : true,
			//min : 1
		},
    	commuteOrNot : {
			required : function() {
				return ($('input[name="commuteOrNot"]:checked').val() == '' || $('input[name="commuteOrNot"]:checked').val()==undefined);
			},
			minlength : 1,
		},
		txtDoWantCommute : {
			required : function() {
				return ($('input[name="commuteOrNot"]:checked').val() == 'Y'  );
			},
		},
		currDDOoffcName: "required",
		currdesgName: "required",
		pensCaseDDOCode: "required",
		pensCaseDDOOfcName: "required",
		desgPensCaseDDO: "required",
		gratuityDDOOffcName: "required",
		
		
		gratuityDDOCode : {
			required : true,
			//min : 1
		},
		
		/* basic details start */
		empName: "required",
		empNameMr: "required",
		gender : {
			required : function() {
				return ($('input[name="commuteOrNot"]:checked').val() == '' || $('input[name="commuteOrNot"]:checked').val()==undefined);
			},
			minlength : 1,
		},
		//feet:"required",
		//inches:"required",
		dob:"required",
		dojGovSrvc:"required",
		dor:"required",
		pensCommDate:"required",
		/*radioEfp : {
			required : function() {
				return ($('input[name="commuteOrNot"]:checked').val() == '' || $('input[name="commuteOrNot"]:checked').val()==undefined);
			},
			minlength : 1,
		},*/
		empDesg:"required",
		uidNo1 : {
			required : function() {
				return ($('#txtEID').val() == '' && $('#txtUidNo1').val() == '' );
			},
		},
		uidNo2 : {
			required : function() {
				return ($('#txtEID').val() == '' && $('#txtUidNo2').val() == '' );
			},
		},
		uidNo3 : {
			required : function() {
				return ($('#txtEID').val() == '' && $('#txtUidNo3').val() == '' );
			},
		},
		txtEID : {
			required : function() {
				return ($('#txtEID').val() == '' && $('#txtUidNo1').val() == '' && $('#txtUidNo2').val() == '' && $('#txtUidNo3').val() == '' );
			},
		},
		panNo:{
	    	required: true,
	    	pattern: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,
	    	minlength: 10,
	    	maxlength: 10, 
	    },
	   // persIdentiMark:"required",
	    /* basic details end */
	    
	    /*Retiring Office Details*/
	    retireAdminDept : {
	    	required : true,
			min : 1
		},
		retireFieldDept : {
			required : true,
			min : 1
		},
		RetireAddrBldg:"required",
	//	retireLandlineNo:"required",
		
	    /*Retiring Office Details end*/
		
		/*Residence Address Start*/
		
		residenceAddrBldg:"required",
		residenceAddrStreet:"required",
	//	residenceLocality:"required",
		residenceState:{
	    	required : true,
			min : 1
		},
		residenceDistrict:{
	    	required : true,
			min : 1
		},
		residencePincode:"required",
		//residenceLandlineNo:"required",
		//residenceMobNo:"required",
		//residenceEmailId:"required",
		
		
		
		/*Residence Address End*/
		/*Residence Address After Retirement Start*/
		
		resiAddrBldgAftrRetire:"required",
		resiAddrStreetAfterRetire:"required",
	//	resiLocalityAfterRetire:"required",
		resiStateAfterRetire:{
	    	required : true,
			min : 1
		},
		resiDistrictAfterretire:{
	    	required : true,
			min : 1
		},
		//resiPincodeAfterRetire:"required",
	//	resiLandlineNoAfterRetire:"required",
		//resiMobNoAfterRetire:"required",
		//resiEmailIdAfterRetire:"required",
		
		
		/*Residence Address After Retirement End*/
		/*Treasury Office Start*/
		agOffcId:{
	    	required : true,
			min : 1
		},
		agOffcIdAfterFirstPay:{
	    	required : true,
			min : 1
		},
		trsyPensionId:{
	    	required : true,
			min : 1
		},
		trsyIdAfterFirstPay:{
	    	required : true,
			min : 1
		},
		
		/*Treasury Office End*/
		/*Bank Details Start*/
		
		bankId:{
	    	required : true,
			min : 1
		},
		branchId:{
	    	required : true,
			min : 1
		},
		ifscCode:"required",
		bankBranchAddr:"required",
		accNo:"required",
		
		/*Bank Details End*/
		/*Pay And Service Details Starts*/
		/*Pay Details Starts*/
		
		payscaleId:{
	    	required : true,
			min : 1
		},
		gradePay:"required",
		//npa:"required",
		basicPay:"required",
		dp:"required",
	//	amount:"required",
//		orderNo:"required",
		orderDate:"required",
		orderDate:"required",
		voucherDate:"required",
		
		/*Pay Details End*/
		
		/*Provisional Pension Details Start*/
		
		txtFromDatePrvsnlPnsn:"required",
		txtToDatePrvsnlPnsn:"required",
		txtSancAuthNo:"required",
		txtSancAuthName:"required",
		/*Provisional Pension Details End*/
		/*Pay And Service Details End*/
		/*CheckList Start*/
		certificate:{
	    	required : true,
			min : 1
		},
		issuingAuth:"required",
//		fromDate:"required",
//		toDate:"required",
		/*CheckList End*/
		/*Pension Calculation Starts*/
		/*Commuted Value of Pension Starts*/
		
		cvpRate:"required",
		cvpAppDate:"required",
	//	cpoNo:"required",
		//cpoDate:"required",
		commMonthlyPension:"required",
		commValueOfPension:"required",
		/*Commuted Value of Pension End*/
		/*Pension Starts*/
		
		ppoNo:"required",
		ppoDate:"required", 	
		totalPensionAmt:"required",
		reducedPensionAmt:"required",
		txtTotlPnsnAmt:"required",
		
		
		/*Pension Ends*/
		
		/*Death Gratuity/Retirement Gratuity/Service Gratuity Starts*/
		
		gpoNo:"required",
		gpoDate:"required",
		dcrgAppDate:"required",
		gratuityAmt:"required",
		///witheldGratuity:"required",
		netGratuity:"required",
		
		/*Death Gratuity/Retirement Gratuity/Service Gratuity End*/
		/*Family Pension Starts*/
		
		enhFamPensDate:"required",
		enhFamPensAmt:"required",
		famPensDate:"required",
		famPensAmt:"required",
		
		/*Family Pension End*/
		
		/*Pension Calculation End*/
      /*Authority Details Starts*/
		agMastSalut:"required",
		agMastCatgry:"required",
		agMastReligion:"required",
		agMastGrpClass:"required",
		
		/*Authority Details End*/
		
		
		
		
    },
    messages: {
    	transcType : {
			required : "Please select transaction type",
			min :"Please select transaction type",
		},
		payCommissionCode : {
			required : "Please select pay commision code",
			min : "Please select pay commision code",
		},
		stateCtgry : {
			required : "Please select state",
			min : "Please select state",
		},
    	inwardId: "Please enter Inward id",
    	classOfPension : {
			required : "Please select class of pension",
			min :"Please select class of pension",
		},
//    	sevaarthId: "required",
    	pnsnType : {
			required : "Please select pension type",
			min :  "Please select pension type",
		},
    	currDDOCode: "Please enter current ddo code of employee",
    	commuteOrNot : {
			required : "Please select commute yes or no",
			minlength :"Please select commute yes or no",
		},
		txtDoWantCommute : {
			required : "Please enter commute percentage",
		},
		currDDOoffcName: "Please enter current ddo office name",
		currdesgName: "Please enter current designation name",
		pensCaseDDOCode: "Please enter pension case ddo code",
		pensCaseDDOOfcName: "Please enter pension case ddo office name",
		desgPensCaseDDO: "Please enter Designation of Pension Case DDO",
		gratuityDDOCode: "Please enter Gratuity DDO Code",
		gratuityDDOOffcName: "Please enter Gratuity DDO Office Name",
		/* basic details start */
		empName: "Please enter Name in English",
		empNameMr: "Please enter Name in Marathi",
		gender:"Please select gender",
		//feet:"Please enter height in feet",
		//inches:"Please enter height in inches",
		dob:"Please select dob",
		dojGovSrvc:"Please select Date of service joining",
		dor:"Please select Date of retirement",
		pensCommDate:"Please select Date of Pension Commencement Date",
	//	radioEfp : "Please select EFP upto 10 years",
		empDesg : {
		    	required :"Please select Designation",
				min :"Please select Designation",
			},
		uidNo1 : {
		     required :"Please select Uid No 1",
		},
		uidNo2 : {
			 required :"Please select Uid No 1",
		},
		uidNo3 : {
			 required :"Please select Uid No 1",
		},
		txtEID : {
			 required :"Please select Uid No 1",
		},
		panNo:{
	    	required: "Please enter pan no",
	    	pattern: "Please input valid pan no",
	    	minlength: "Please input valid pan no length",
	    	maxlength:  "Please input valid pan no length",
	    },
	   // persIdentiMark:"Please write identification mark",
		/* basic details end */
	    
	    /*Retiring Office Details*/
	    retireAdminDept : {
	    	 required :"Please select Retire admin department",
			min : "Please select Retire admin department",
		},
		retireFieldDept : {
			required : "Please select Retire Field department",
			min : "Please select Retire Field department",
		},
		RetireAddrBldg:"Please enter Building name",
		//retireLandlineNo:"Please enter landline No",
		
		  /*Retiring Office end*/
		
		/*Residence Address start*/
		
		residenceAddrBldg:"Please enter Residence Building name",
		residenceAddrStreet:"Please enter Residence Street Address",
		//residenceLocality:"Please enter Residence Locality",
		residenceState:{
	    	 required :"Please select Residence State",
				min : "Please select Residence State",
			},
		residenceDistrict:{
	    	 required :"Please select Residence District",
				min : "Please select Residence District",
			},
		residencePincode:"Please enter Residence Pincode",
		residenceLandlineNo:"Please enter Residence Landline Number",
		//residenceMobNo:"Please enter Residence Mobile Number",
		//residenceEmailId:"Please enter Residence EmailId",
		
		/*Residence Address End*/
		/*Residence Address After Retirement Start*/
		
		resiAddrBldgAftrRetire:"Please enter Residence Building After Retirement name ",
		resiAddrStreetAfterRetire:"Please enter Residence Building After Retirement name ",
		resiLocalityAfterRetire:"Please enter Residence Building After Retirement name ",
		resiStateAfterRetire:{
			 required :"Please select Residence After Retirement State ",
				min : "Please select Residence After Retirement State ",
		},
		resiDistrictAfterretire:{
			 required :"Please select Residence District After Retirement",
				min : "Please select Residence District After Retirement",
		},
		//resiPincodeAfterRetire:"Please enter Residence After Retirement Pincode ",
		resiLandlineNoAfterRetire:"Please enter Residence After Retirement Landline Number",
		//resiMobNoAfterRetire:"Please enter Residence After Retirement Mobile Number",
		//resiEmailIdAfterRetire:"Please enter Residence After Retirement EmailId",
		
		/*Residence Address After Retirement End*/
		
		/*Treasury Office Start*/
		agOffcId:{
			 required :"Please select AG Office ",
				min : "Please select AG Office ",
		},
		agOffcIdAfterFirstPay:{
			 required :"Please select AG Office After First Payment ",
				min : "Please select AG Office After First Payment ",
		},
		trsyPensionId:{
			 required :"Please select Treasury For Pension ",
				min : "Please select Treasury For Pension ",
		},
		trsyIdAfterFirstPay:{
			 required :"Please select Treasury After First Payment ",
				min : "Please select Treasury After First Payment ",
		},
		
		/*Treasury Office End*/
/*Bank Details Start*/
		
		bankId:{
			required :"Please select Bank ",
			min : "Please select Bank ",
		},
		branchId:{
			required :"Please select Branch    ",
			min : "Please select Branch    ",
		},
		ifscCode:"Please enter IFSC Code",
		bankBranchAddr:"Please enter Bank Branch Address",
		accNo:"Please enter Account Number",
		
		/*Bank Details End*/
		
	/*Pay And Service Details Starts*/
		/*Pay Details Starts*/
		
		payscaleId:{
			required :"Please select Pay Scale/Pay Band  ",
			min : "Please select Pay Scale/Pay Band  ",
		},
		gradePay:"Please enter Grade Pay ",
	//	npa:"Please enter Non Practicing Allowance (NPA) ",
		basicPay:"Please enter Basic Pay ",
		dp:"Please enter DP ",
		amount:"Please enter Amount ",
		orderNo:"Please enter Order No. ",
		orderDate:"Please enter Order Date ",
		voucherNo:"Please enter Voucher No. ",
		voucherDate:"Please enter Voucher Date ",
		/*Pay Details End*/
		/*Provisional Pension Details Start*/
		
		txtFromDatePrvsnlPnsn:"Please enter From Date ",
		txtToDatePrvsnlPnsn:"Please enter To Date ",
		txtSancAuthNo:"Please enter Sanctioning Authority No. ",
		txtSancAuthName:"Please enter Sanctioning Authority Name ",
		/*Provisional Pension Details End*/
		/*Pay And Service Details End*/
		/*CheckList Start*/
		certificate:{
			required :"Please select Certificate   ",
			min : "Please select Certificate   ",
		},
		issuingAuth:"Please Enter Issuing Authority",
		fromDate:"Please Enter From Date",
		toDate:"Please Enter To Date ",
		/*CheckList End*/
		/*Pension Calculation Starts*/
		/*Commuted Value of Pension Starts*/
		
		cvpRate:"Please Enter CVP Rate ",
		cvpAppDate:"Please Enter CVP Application Date ",
		cpoNo:"Please Enter CPO No. ",
		cpoDate:"Please Enter CPO Date ",
		commMonthlyPension:"Please Enter Commuted Monthly Pension amount ",
		/*Commuted Value of Pension End*/
		
/*Pension Starts*/
		
		ppoNo:"Please Enter PPO No ",
		ppoDate:"Please Enter PPO Date ", 	
		totalPensionAmt:"Please Enter Total Pension Amount ",
		reducedPensionAmt:"Please Enter Reduced Pension Amount ",
		txtTotlPnsnAmt:"Please Enter 	Commuted Monthly Pension amount ",
		
		
		/*Pension Ends*/
		/*Death Gratuity/Retirement Gratuity/Service Gratuity Starts*/
		
		gpoNo:"Please Enter GPO No. ",
		gpoDate:"Please Enter GPO Date ",
		dcrgAppDate:"Please Enter DCRG Application Date ",
		gratuityAmt:"Please Enter Gratuity Amount ",
	//	witheldGratuity:"Please Enter Witheld Gratuity ",
		netGratuity:"Please Enter Net Gratuity ",
		/*Death Gratuity/Retirement Gratuity/Service Gratuity End*/
		
		/*Family Pension Starts*/
		
		enhFamPensDate:"Please Enter Enhanced Family Pension Date ",
		enhFamPensAmt:"Please Enter Enhanced Family Pension Amount ",
		famPensDate:"Please Enter Family Pension Date ",
		famPensAmt:"Please Enter Family Pension Amount ",
		/*Family Pension End*/
		
		/*Pension Calculation End*/
		
		/*Authority Details Starts*/
		agMastSalut:"Please Enter AG Master Salutation ",
		agMastCatgry:"Please Enter AG Master Category/Series ",
		agMastReligion:"Please Enter AG Master Religion ",
		agMastGrpClass:"Please Enter AG Master Group Class",
		
		/*Authority Details End*/
		
		
		
		
    },
    submitHandler: function(form,event ) {
   	 if($("#tblFamilyDtls>tbody>tr").length==0){
   		 swal("Please enter Nominee Details");
   	 }else if(checkPercentage()==true){
		 swal("Please enter valid percentage");
	 }else if(checkFamilyDetails()==false){
		 swal("Please fill all nominee details");
	 }else{
		 form.submit();
	 }
      
    }
  });


//dynamic table validation part start

//foreign service validation
function checkForeignServiceDep(){
	var YesOrNo=$("input[name='foreignSrvcOrDeput']").val();
	var isFilledAllData=true;
	if(YesOrNo=="Y"){
		var tblLength=$("#tblForeignServDtls>tbody>tr").length;
		for(var i=0;i<tblLength;i++){

			var durationFromDate=$("#lstPensionEmpForeignServiceDtlsModel"+i+"durationFromDate").val();
			var durationToDate=$("#lstPensionEmpForeignServiceDtlsModel"+i+"durationToDate").val();
			var totalAmount=$("#lstPensionEmpForeignServiceDtlsModel"+i+"totalAmount").val();
			var paidAmount=$("#lstPensionEmpForeignServiceDtlsModel"+i+"paidAmount").val();
			var challanOrDDNo=$("#lstPensionEmpForeignServiceDtlsModel"+i+"challanOrDDNo").val();
			var challanDate=$("#lstPensionEmpForeignServiceDtlsModel"+i+"challanDate").val();
			var deputedOffcName=$("#lstPensionEmpForeignServiceDtlsModel"+i+"deputedOffcName").val();

			if(durationFromDate==''){
				showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"durationFromDate"),"Please select From Date");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"durationFromDate"));
			}
			
			if(durationToDate==''){
				showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"durationToDate"),"Please select To Date");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"durationToDate"));
			}
			
			 if(totalAmount==''){
				showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"totalAmount"),"Please enter totat amount");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"totalAmount"));
			}
			
			 if(paidAmount==''){
				 showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"paidAmount"),"Please enter paid amount");
				 isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"paidAmount"));
			 }
			 
			 if(challanOrDDNo==''){
				showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"challanOrDDNo"),"Please enter challan number");
				isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"challanOrDDNo"));
			 }
			 
			 if(challanDate==''){
				showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"challanDate"),"Please select challan date");
				isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"challanDate"));
			 }
			 
			 if(deputedOffcName==''){
				showError($("#lstPensionEmpForeignServiceDtlsModel"+i+"deputedOffcName"),"Please enter deputed office name");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpForeignServiceDtlsModel"+i+"deputedOffcName"));
			 }
		}
	}
	return isFilledAllData;
}


//Provisional Pension Details validation 
function checkPrvsnlPensnDtls(){
	var YesOrNo=$("input[name='provPensionPay']").val();
	var isFilledAllData=true;
	if(YesOrNo=="Y" || YesOrNo!=0  ){
		var tblLength=$("#tblPrvsnlPnsnDtls>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var sancAuthName=$("#lstPensionEmpProvisionalDtlsModel"+i+"sancAuthName").val();
			var sancAuthNo=$("#lstPensionEmpProvisionalDtlsModel"+i+"sancAuthNo").val();
			var sanctionDate=$("#lstPensionEmpProvisionalDtlsModel"+i+"sanctionDate").val();
			var billType=$("#lstPensionEmpProvisionalDtlsModel"+i+"billType").val();
			var amount=$("#lstPensionEmpProvisionalDtlsModel"+i+"amount").val();
			var voucherNo=$("#lstPensionEmpProvisionalDtlsModel"+i+"voucherNo").val();
			var voucherDate=$("#lstPensionEmpProvisionalDtlsModel"+i+"voucherDate").val();
			
			
			if(sancAuthName=='' || sancAuthName=='0' ){
				showError($("#lstPensionEmpProvisionalDtlsModel"+i+"sancAuthName"),"Please enter sanction auth name");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"sancAuthName"));
			}
			
			if(sancAuthNo==''){
				showError($("#lstPensionEmpProvisionalDtlsModel"+i+"sancAuthNo"),"Please enter sanction auth no");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"sancAuthNo"));
			}
			
			if(sanctionDate==''){
				showError($("#lstPensionEmpProvisionalDtlsModel"+i+"sanctionDate"),"Please select Sanction Date");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"sanctionDate"));
			}
			
			 if(billType=='' || billType=='0'){
				showError($("#lstPensionEmpProvisionalDtlsModel"+i+"billType"),"Please select bill type");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"billType"));
			}
			
			 if(amount==''){
				 showError($("#lstPensionEmpProvisionalDtlsModel"+i+"amount"),"Please enter amount");
				 isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"amount"));
			 }
			 
			 if(voucherNo==''){
				showError($("#lstPensionEmpProvisionalDtlsModel"+i+"voucherNo"),"Please enter voucher No");
				isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"voucherNo"));
			 }
			 
			 if(voucherDate==''){
				showError($("#lstPensionEmpProvisionalDtlsModel"+i+"voucherDate"),"Please select voucher Date");
				isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpProvisionalDtlsModel"+i+"voucherDate"));
			 }
			 
			
		}
	}
	return isFilledAllData;
}

//event table validation 
function checkEventsDtls(){
	var isFilledAllData=true;
		var tblLength=$("#tblEventDtls>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var event=$("#lstPensionEmpEventDtlsModel"+i+"event").val();
			var payscaleId=$("#lstPensionEmpEventDtlsModel"+i+"payscaleId").val();
			var basic=$("#lstPensionEmpEventDtlsModel"+i+"basic").val();
			var dpOrGp=$("#lstPensionEmpEventDtlsModel"+i+"dpOrGp").val();
			var fromDate=$("#lstPensionEmpEventDtlsModel"+i+"fromDate").val();
			var toDate=$("#lstPensionEmpEventDtlsModel"+i+"toDate").val();
			
			if(event=='' ){
				showError($("#lstPensionEmpEventDtlsModel"+i+"event"),"Please enter event name");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpEventDtlsModel"+i+"event"));
			}
			
			if(payscaleId==''){
				showError($("#lstPensionEmpEventDtlsModel"+i+"payscaleId"),"Please enter Pay Scale/Pay Band");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpEventDtlsModel"+i+"payscaleId"));
			}
			
			if(basic==''){
				showError($("#lstPensionEmpEventDtlsModel"+i+"basic"),"Please enter basic");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpEventDtlsModel"+i+"basic"));
			}
			
			 if(dpOrGp=='' ){
				showError($("#lstPensionEmpEventDtlsModel"+i+"dpOrGp"),"Please enter Dp/GP");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpEventDtlsModel"+i+"dpOrGp"));
			}
			
			 if(fromDate==''){
				 showError($("#lstPensionEmpEventDtlsModel"+i+"fromDate"),"Please select from date");
				 isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpEventDtlsModel"+i+"fromDate"));
			 }
			 
			 if(toDate==''){
				showError($("#lstPensionEmpEventDtlsModel"+i+"toDate"),"Please select to Date");
				isFilledAllData=false;
			 }else{
					hideError($("#lstPensionEmpEventDtlsModel"+i+"toDate"));
			 }
		}
	return isFilledAllData;
}

//tblNonQualifyingService table validation 
function checkNonQualifyingService(){
	var isFilledAllData=true;
		var tblLength=$("#tblNonQualifyingService>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var typeOfBreak=$("#lstPensionEmpNonQualSrvcModel"+i+"typeOfBreak").val();
			var txtDateOfBrkFrom=$("#txtDateOfBrkFrom"+i).val();
			var txtDateOfBrkTo=$("#txtDateOfBrkTo"+i).val();
			var remark=$("#lstPensionEmpNonQualSrvcModel"+i+"remark").val();
			 
			if(typeOfBreak==''  || typeOfBreak=='0'  ){
				showError($("#lstPensionEmpNonQualSrvcModel"+i+"typeOfBreak"),"Please enter event name");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpNonQualSrvcModel"+i+"typeOfBreak"));
			}
			
			
			 if(txtDateOfBrkFrom==''){
				 showError($("#txtDateOfBrkFrom"+i),"Please select from date");
				 isFilledAllData=false;
			 }else{
					hideError($("#txtDateOfBrkFrom"+i));
			 }
			 
			 if(txtDateOfBrkTo==''){
				showError($("#txtDateOfBrkTo"+i),"Please select to Date");
				isFilledAllData=false;
			 }else{
					hideError($("#txtDateOfBrkTo"+i));
			 }
			 
			
			if(remark==''){
				showError($("#lstPensionEmpNonQualSrvcModel"+i+"remark"),"Please enter Remark");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpNonQualSrvcModel"+i+"remark"));
			}
			
		}
	return isFilledAllData;
}


//additional qualifying table validation 
function checkAddQualifyingService(){
	var isFilledAllData=true;
		var tblLength=$("#tblNonQualifyingService>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var grNoRule=$("#lstPensionEmpAddQualModel"+i+"grNoRule").val();
			var grDate=$("#lstPensionEmpAddQualModel"+i+"grDate").val();
			var addQualYear=$("#lstPensionEmpAddQualModel"+i+"addQualYear").val();
			var addQualMonth=$("#lstPensionEmpAddQualModel"+i+"addQualMonth").val();
			var addQualDay=$("#lstPensionEmpAddQualModel"+i+"addQualDay").val();
			var remark=$("#lstPensionEmpAddQualModel"+i+"remark").val();
		
			 
			if(grNoRule==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"grNoRule"),"Please enter gr rule number ");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"grNoRule"));
			}
			
			
			if(grDate==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"grDate"),"Please select gr date");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"grDate"));
			}
			
			if(addQualYear==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"addQualYear"),"Please enter additional Qualification Year");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"addQualYear"));
			}
			
			if(addQualMonth==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"addQualMonth"),"Please enter additional Qualification Month");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"addQualMonth"));
			}
			
			if(addQualDay==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"addQualDay"),"Please enter additional Qualification Day");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"addQualDay"));
			}
			
			if(remark==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"remark"),"Please enter remark");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"remark"));
			}
			
		}
	return isFilledAllData;
}

//Average Pay Calculator validation 
function checkAveragePay(){
	var isFilledAllData=true;
		var tblLength=$("#tblAvgPayCalc>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var grNoRule=$("#lstPensionEmpAddQualModel"+i+"grNoRule").val();
			var grDate=$("#lstPensionEmpAddQualModel"+i+"grDate").val();
			var addQualYear=$("#lstPensionEmpAddQualModel"+i+"addQualYear").val();
			var addQualMonth=$("#lstPensionEmpAddQualModel"+i+"addQualMonth").val();
			var addQualDay=$("#lstPensionEmpAddQualModel"+i+"addQualDay").val();
			var remark=$("#lstPensionEmpAddQualModel"+i+"remark").val();
		
			 
			if(grNoRule==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"grNoRule"),"Please enter gr rule number ");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"grNoRule"));
			}
			
			
			if(grDate==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"grDate"),"Please select gr date");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"grDate"));
			}
			
			if(addQualYear==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"addQualYear"),"Please enter additional Qualification Year");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"addQualYear"));
			}
			
			if(addQualMonth==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"addQualMonth"),"Please enter additional Qualification Month");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"addQualMonth"));
			}
			
			if(addQualDay==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"addQualDay"),"Please enter additional Qualification Day");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"addQualDay"));
			}
			
			if(remark==''   ){
				showError($("#lstPensionEmpAddQualModel"+i+"remark"),"Please enter remark");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpAddQualModel"+i+"remark"));
			}
			
		}
	return isFilledAllData;
}

//family details validation
//checkFamilyDetails();




$(document).on('blur','.percentage', function(event){
	var percentage=0;
    $(".percentage").each(function(){
    percentage += parseFloat(nanTozero($(this).val()));
    if(parseInt(percentage)>100){
		swal("Please enter valid percentage");
		showError($(this),"Please enter valid percentage");
	}else{
		hideError($(this));
	}
   });
});



function checkPercentage(){
	var flag=false;
	var percentage=0;
	$(".percentage").each(function(){
	       percentage += parseFloat(nanTozero($(this).val()));
	});
	if(parseInt(percentage)>100){
		flag=true;
		return flag;
	}else if(parseInt(percentage)==0){
		flag=true;
		return flag;
	}
}





//checkFamilyDetails();
function checkFamilyDetails(){  
	//var YesOrNo=$("input[name='retireReportEmpFam:checked']").val();
	var isFilledAllData=true;
	//if(YesOrNo=="Y"){
		var tblLength=$("#tblFamilyDtls>tbody>tr").length;
		
		
		
		
		
		for(var i=0;i<tblLength;i++){
			var famPensioner=$("#famPensioner"+i).val();
			var nameOfFamMemb=$("#nameOfFamMemb"+i).val();
			var relation=$("#relation"+i).val();
			var percentage=$("#percentage"+i).val();
			var phyhandMentChal=$("#phyhandMentChal"+i).val();
			var dob=$("#dob"+i).val();
			var salary=$("#salary"+i).val();
			var guardianName=$("#guardianName"+i).val();
			
			if(famPensioner=='' || famPensioner==undefined){
				showError($("#famPensioner"+i),"Please enter name of family pensioner");
				isFilledAllData=false;
			}else{
				hideError($("#famPensioner"+i));
			}
				
	
			
			if(nameOfFamMemb=='' || nameOfFamMemb==undefined ){
				showError($("#nameOfFamMemb"+i),"Please enter name of family member");
				isFilledAllData=false;
			}else{
				hideError($("#nameOfFamMemb"+i));
			}
			
			if(relation==''  || relation=='0'){
				showError($("#relation"+i),"Please select relation");
				isFilledAllData=false;
			}else{
				hideError($("#relation"+i));
			}
			
			 if(percentage=='' || percentage==undefined){
				showError($("#percentage"+i),"Please enter percentage");
				isFilledAllData=false;
			}else{
				hideError($("#percentage"+i));
			}
			
			 if(phyhandMentChal=='' ||  phyhandMentChal=='0'){
				 showError($("#phyhandMentChal"+i),"Please select Physically Handicapped/Mentally Challanged");
				 isFilledAllData=false;
			 }else{
					hideError($("#phyhandMentChal"+i));
			 }
			 
			 if(dob=='' || dob==undefined){
				showError($("#dob"+i),"Please select dob");
				isFilledAllData=false;
			 }else{
					hideError($("#dob"+i));
			 }
			 
			 if(salary=='' || salary==undefined){
				showError($("#salary"+i),"Please enter salary");
				isFilledAllData=false;
			 }else{
					hideError($("#salary"+i));
			 }
			 
			 
			 
			 if($("minor"+i).val()=='Y'){
				 if(guardianName==''){
						showError($("#guardianName"+i),"Please enter Guardian Name");
						isFilledAllData=false;
					}else{
						hideError($("#guardianName"+i));
					 }
			 }
			 
			 
			 
			  var dob = new Date($("#dob"+i).val());
			   var today = new Date();
			   var age = Math.floor((today-dob) / (365.25 * 24 * 60 * 60 * 1000));
			   if(parseInt(age)<18){
				   if(guardianName==''){
						showError($("#guardianName"+i),"Please enter Guardian Name");
						isFilledAllData=false;
					}else{
						hideError($("#guardianName"+i));
					 }
			   }
			   
			   
			   
		}
	//}
	return isFilledAllData;
}


//nominee table validation 
function checkNomineeDtls(){
	var isFilledAllData=true;
		var tblLength=$("#tblNomineeDtls>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var nameOfFamMemb=$("#lstPensionEmpNomineeDtlsModel"+i+"nameOfFamMemb").val();
			var relation=$("#lstPensionEmpFamilyDtlsModel"+i+"relation").val();
			var percentage=$("#lstPensionEmpNomineeDtlsModel"+i+"percentage").val();
			var alternateNominee=$("#lstPensionEmpNomineeDtlsModel"+i+"alternateNominee").val();
			 
			if(nameOfFamMemb==''  ){
				showError($("#lstPensionEmpNomineeDtlsModel"+i+"nameOfFamMemb"),"Please enter nominee name");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpNomineeDtlsModel"+i+"nameOfFamMemb"));
			}
			
			if(relation==''  || relation=='0'  ){
				showError($("#lstPensionEmpNomineeDtlsModel"+i+"relation"),"Please select realation");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpNomineeDtlsModel"+i+"relation"));
			}
			
		
			if(percentage==''  ){
				showError($("#lstPensionEmpNomineeDtlsModel"+i+"percentage"),"Please enter percentage");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpNomineeDtlsModel"+i+"percentage"));
			}
			
			/*if(alternateNominee==''  ){
				showError($("#lstPensionEmpNonQualSrvcModel"+i+"alternateNominee"),"Please enter alternate Nominee");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpNonQualSrvcModel"+i+"alternateNominee"));
			}*/
			
			
		}
	return isFilledAllData;
}


//recovery validation 
function checkRecoveryDtls(){
	var isFilledAllData=true;
		var tblLength=$("#tblTypeAdvDtls>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var typeOfRec=$("#lstPensionEmpRecovDtlsModel"+i+"typeOfRec").val();
			var amount=$("#lstPensionEmpRecovDtlsModel"+i+"amount").val();
			var schemeCode=$("#lstPensionEmpRecovDtlsModel"+i+"schemeCode").val();
			 
			if(typeOfRec==''  || typeOfRec=='0'  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"typeOfRec"),"Please select type Of Recovery");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"typeOfRec"));
			}
			
			
			if(amount==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"amount"),"Please enter amount");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"amount"));
			}
			
			
			
		
			if(schemeCode==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"schemeCode"),"Please enter schemeCode");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"schemeCode"));
			}
			
			
		}
	return isFilledAllData;
}


//authorization details validation 
function checkAuthDetails(){
	var isFilledAllData=true;
		var tblLength=$("#tblAuthoTypeAdvDtls>tbody>tr").length;
		for(var i=0;i<tblLength;i++){
			var orderType=$("#lstPensionAuthDtlsModel"+i+"orderType").val();
			var orderNo=$("#lstPensionAuthDtlsModel"+i+"orderNo").val();
			var orderDate=$("#lstPensionAuthDtlsModel"+i+"orderDate").val();
			var pensionerName=$("#lstPensionAuthDtlsModel"+i+"pensionerName").val();
			var basicAmt=$("#lstPensionAuthDtlsModel"+i+"basicAmt").val();
			var enhFamPensAmt=$("#lstPensionAuthDtlsModel"+i+"enhFamPensAmt").val();
			var famPensAmt=$("#lstPensionAuthDtlsModel"+i+"famPensAmt").val();
			 
			if(orderType==''  || orderType=='0'  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"orderType"),"Please select type of order");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"orderType"));
			}
			
			
			if(orderNo==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"orderNo"),"Please enter order no");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"orderNo"));
			}
			
			
			
		
			if(orderDate==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"orderDate"),"Please select order date");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"orderDate"));
			}
			
			if(pensionerName==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"pensionerName"),"Please enter pensioner name");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"pensionerName"));
			}
			
			if(basicAmt==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"basicAmt"),"Please enter basic amount");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"basicAmt"));
			}
			
			
			if(enhFamPensAmt==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"enhFamPensAmt"),"Please enter enhance family pension amount");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"enhFamPensAmt"));
			}
			
			if(famPensAmt==''  ){
				showError($("#lstPensionEmpRecovDtlsModel"+i+"famPensAmt"),"Please enter family pension amount");
				isFilledAllData=false;
			}else{
				hideError($("#lstPensionEmpRecovDtlsModel"+i+"famPensAmt"));
			}
			
			
		}
	return isFilledAllData;
}


$('input[type="checkbox"][name="radioIsAddrAfterRet"]').click(function() {
	  //  $('#permanent_address').val($('#current_address').val());
	//  $('#presentSamePerm').val($('#presentNotSamePerm').val());
	  
	  if($(this).prop("checked"))
		{
		  var residenceAddrBldg = $("#txtPrFlatDoorBlk").val();
		  var residenceAddrStreet = $("#txtPrRoadPostOff").val();
		  var residenceLocality = $("#txtPrAreaLocality").val();
		  var residencePincode = $("#txtPrPincode").val();
		  var residenceLandlineNo = $("#txtLandlineNo").val();
		  var residenceMobNo = $("#txtMobileNo").val();
		  var residenceEmailId = $("#txtEmailId").val();
		  
		   $("#resiAddrBldgAftrRetire").val(residenceAddrBldg);
		   $("#resiAddrStreetAfterRetire").val(residenceAddrStreet);
		   $("#resiLocalityAfterRetire").val(residenceLocality);
		   $("#txtARPincode").val(residencePincode);
		   $("#txtARLandlineNo").val(residenceLandlineNo);
		   $("#txtARMobileNo").val(residenceMobNo);
		   $("#txtAREmailId").val(residenceEmailId);
		   
		   $("#resiAddrBldgAftrRetire").prop("readOnly",true);
		   $("#resiAddrStreetAfterRetire").prop("readOnly",true);
		   $("#resiLocalityAfterRetire").prop("readOnly",true);
		   $("#txtARPincode").prop("readOnly",true);
		   $("#txtARLandlineNo").prop("readOnly",true);
		   $("#txtARMobileNo").prop("readOnly",true);
		   $("#txtAREmailId").prop("readOnly",true);
		}
	  else
		  {
		  $("#resiAddrBldgAftrRetire").prop("readOnly",false);
		   $("#resiAddrStreetAfterRetire").prop("readOnly",false);
		   $("#resiLocalityAfterRetire").prop("readOnly",false);
		   $("#txtARPincode").prop("readOnly",false);
		   $("#txtARLandlineNo").prop("readOnly",false);
		   $("#txtARMobileNo").prop("readOnly",false);
		   $("#txtAREmailId").prop("readOnly",false);
		   
		  $("#resiAddrBldgAftrRetire").val("");
		  $("#resiAddrStreetAfterRetire").val("");
		  $("#resiLocalityAfterRetire").val("");
		  $("#txtARPincode").val("");
		   $("#txtARLandlineNo").val("");
		   $("#txtARMobileNo").val("");
		   $("#txtAREmailId").val("");
		  
		  }
	  });




$(".toDate").keydown(disableKeyUp); // use keydown
function disableKeyUp(e) {
    if(e.keyCode === 13) {
        e.stopPropagation();
        e.preventDefault();
        return false;
    }
}



$("input[name='maritalStatus']").change(function (){
	if($("input[name='maritalStatus']:checked").val()=="Y"){
		$("#spouseName").prop("readpnly",false);
	}else{
		$("#spouseName").prop("readpnly",false);	
	}
});



$("#txtDateOfExpiry").change(function() {
	setFP1AndFp2Date(2);
	var date = new Date($("#txtDateOfExpiry").val());
	date.setDate(date.getDate());
	$("#txtDateOfCommencement").val(dateToYMD(date));
	$("#txtDateOfRetiremt").val(dateToYMD(date));
});



$("#txtDateOfRetiremt").change(function() {
	var date = new Date($("#txtDateOfRetiremt").val());
	date.setDate(date.getDate() + 1);
	$("#txtDateOfCommencement").val(dateToYMD(date));
	setFP1AndFp2Date(1);
});




function setFP1AndFp2Date(flag) {

	if (flag == 1)
		setFp1Date("txtDateOfBirth", "txtFP1Date", "dateOfBirth");

	else if (flag == 2)
		setFp1Date("txtDateOfExpiry", "txtFP1Date", "dateOfExpiry");

	setFp2Date();
}



function setFp1Date(sourceFieldId, targetFieldId, str) {
	if (str == "dateOfBirth") {
		var dor = new Date($("#txtDateOfRetiremt").val());
		dor.setFullYear(dor.getFullYear() + 7);
		var dorp7YearDate = dateToYMD(dor);


		var dob = new Date($("#txtDateOfBirth").val());
		dob.setFullYear(dob.getFullYear() + 65);
		var dobp7YearDate = dob;


		var date1 = new Date(dob).getTime();
		var date2 = new Date(dor).getTime();

		if (dobp7YearDate < dorp7YearDate) {
			var date = new Date(date1);
			date.setDate(date.getDate() - 1);
			$("#txtFP1Date").val(dateToYMD(date));

		} else if (date1 > date2) {
			var date = new Date(date1);
			date.setDate(date.getDate() - 1);
			$("#txtFP1Date").val(dateToYMD(date));

		} else {
			var date = new Date(date1);
			date.setDate(date.getDate() - 1);
			$("#txtFP1Date").val(dateToYMD(date));
		}
	}
	if (str == "dateOfExpiry") {

		if (document.getElementById('radioEfpYes').checked) {
			var doe = new Date($("#txtDateOfExpiry").val());
			doe.setDate(doe.getDate() + 1);
			doe.setFullYear(doe.getFullYear() + 10);
			$("#txtFP1Date").val(dateToYMD(doe));
		} else {
			var doe = new Date($("#txtDateOfExpiry").val());
			doe.setDate(doe.getDate() + 1);
			doe.setFullYear(doe.getFullYear() + 7);
			$("#txtFP1Date").val(dateToYMD(doe));
		}
	}
}

function setFp2Date() {
	var date = new Date($("#txtFP1Date").val());
	date.setDate(date.getDate() + 1);
	$("#txtFP2Date").val(dateToYMD(date));
}





var currentTab = 0;

$("#btnPrevious").hide();

$(function() {
	$("#tabs").tabs({
		select : function(e, i) {
			currentTab = i.index;
			if(currentTab=="0"){
				 $("#btnPrevious").hide();
				 $("#btnNext").show();
			}
			
		}
	});
});

$("#btnNext").click(function() {
	var tabs = $('#tabs').tabs();
	var c = $('#tabs').tabs("length");
	currentTab = currentTab == (c - 1) ? currentTab : (currentTab + 1);

	tabs.tabs('select', currentTab);

	$("#btnPrevious").show();
	if (currentTab == (c - 1)) {
		 $("#btnNext").hide(); 
	} else {
		$("#btnNext").show();
	}
});
$("#btnPrevious").click(function() {
	var tabs = $('#tabs').tabs();
	var c = $('#tabs').tabs("length");
	currentTab = currentTab == 0 ? currentTab : (currentTab - 1);
	tabs.tabs('select', currentTab);
	if (currentTab == 0) {
		$("#btnNext").show();
		 $("#btnPrevious").hide(); 
	}
	if (currentTab < (c - 1)) {
		$("#btnNext").show();
	}
});




jQuery.browser = {
	    msie: false,
	    version: 0
	};


   it("resolves to undefined when no listeners reply", () => { 
	 
	   const fakeChrome = { 
	     runtime: { 
	       // This error message is defined as CHROME_SEND_MESSAGE_CALLBACK_NO_RESPONSE_MESSAGE 
	       // in the polyfill sources and it is used to recognize when Chrome has detected that 
	       // none of the listeners replied. 
	       lastError: { 
	         message: "The message port closed before a response was received.", 
	       }, 
	       sendMessage: sinon.stub(), 
	     }, 
	   }; 
	  
	   fakeChrome.runtime.sendMessage.onFirstCall().callsArgWith(1, [undefined]); 
	  
	   return setupTestDOMWindow(fakeChrome).then(window => { 
	     const promise = window.browser.runtime.sendMessage("some_message"); 
	     ok(fakeChrome.runtime.sendMessage.calledOnce, "sendMessage has been called once"); 
	  
	     return promise.then(reply => { 
	       deepEqual(reply, undefined, "sendMessage promise should be resolved to undefined"); 
	     }); 
	   }); 
	 }); 


