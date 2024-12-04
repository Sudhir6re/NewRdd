
$("#btnAdvDtlsAddRow").click(function(){
	    var isRecovery=$('input[name="balOfAdv"]:checked').val();
		if(isRecovery=="Y"){
		var index=$('#tblTypeAdvDtls  >tbody >tr').length;
		var srNo=$('#tblTypeAdvDtls  >tbody >tr').length+1;
		
		var Recovery = '<select  name="lstPensionEmpRecovDtlsModel[' + index + '].recoveryFromPension"  class="form-control removeErrorFromDropdown recoveryFromPension"><option value="0">Please Select</option><option value="P">Pension</option><option value="G">Grauity</option><option value="permanent">Permanent</option><option value="WithheldRecovery">Recovery from WithHeld</option><option value="WthheldAmtFromWithheld">Hold Amount from Withheld</option></select>';
		var typeOfRec='<select id="lstPensionEmpRecovDtlsModel' + index + 'typeOfRec"   name="lstPensionEmpRecovDtlsModel[' + index + '].typeOfRec"    class="form-control removeErrorFromDropdown"><option value="0">--Select--</option><option value="Government Accommodation" title="Government Accommodation">Government Accommodation</option><option value="Balance of House Building Advance" title="Balance of House Building Advance">Balance of House Building Advance</option><option value="Interest on House Building Advance" title="Interest on House Building Advance">Interest on House Building Advance</option><option value="Balance of Conveyance Advance" title="Balance of Conveyance Advance">Balance of Conveyance Advance</option><option value="Interest on Conveyance Advance" title="Interest on Conveyance Advance">Interest on Conveyance Advance</option><option value="over payment of pay and allowances" title="Over payment of pay and allowances">Over payment of pay and allowances</option><option value="Income tax" title="Income tax">Income tax</option><option value="Dues referred to in Rule 134 of M.C.S" title="Dues referred to in Rule 134 of M.C.S">Dues referred to in Rule 134 of M.C.S</option><option value="Recovery of share of Management contribution to Provident Fund" title="Recovery of share of Management contribution to Provident Fund">Recovery of share of Management contribution to Provident Fund</option><option value="Other" title="Others">Others</option></select>';
		var principalAmount='<input type="text" class="form-control float principalAmount  removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'amount"  name="lstPensionEmpRecovDtlsModel[' + index + '].principalAmount"     />';
		var schemeCode='<input type="text" class="form-control removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'schemeCode"  name="lstPensionEmpRecovDtlsModel[' + index + '].schemeCode"     />';
		var recoveryFromDate='<input type="date" class="form-control recoveryFromDate  removeErrorFromDate" id="lstPensionEmpRecovDtlsModel' + index + 'recoveryFromDate"  name="lstPensionEmpRecovDtlsModel[' + index + '].recoveryFromDate"     />';
		var recoveryToDate='<input type="date" class="form-control recoveryToDate  removeErrorFromDate" id="lstPensionEmpRecovDtlsModel' + index + 'recoveryToDate"  name="lstPensionEmpRecovDtlsModel[' + index + '].recoveryToDate"     />';
		
		
		
		var amount='<input type="text" class="form-control  amount float removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'amount"  name="lstPensionEmpRecovDtlsModel[' + index + '].amount"     />';
		
		
		var noOfInstallment='<input type="text" class="form-control number noOfInstallment removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'noOfInstallment"  name="lstPensionEmpRecovDtlsModel[' + index + '].noOfInstallment"     />';
		var noOfInstallmentsPaid='<input type="text" class="form-control number noOfInstallmentsPaid removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'noOfInstallmentsPaid"  name="lstPensionEmpRecovDtlsModel[' + index + '].noOfInstallmentsPaid"     />';
		var totalAmtPaid='<input type="text" class="form-control number totalAmtPaid removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'totalAmtPaid"  name="lstPensionEmpRecovDtlsModel[' + index + '].totalAmtPaid"     />';
		var remark='<input type="text" class="form-control  remark removeErrorFromInput" id="lstPensionEmpRecovDtlsModel' + index + 'remark"  name="lstPensionEmpRecovDtlsModel[' + index + '].remark"     />';
		
		var isActive = '<select  name="lstPensionEmpRecovDtlsModel[' + index + '].isActive" id="lstPensionEmpRecovDtlsModel' + index + 'isActive"   class="form-control  isActive"><option value="1">Active</option><option value="0">InActive</option></select>';
		
		var deleteRow='<div  align="center" style="vertical-align: middle;"  data-className="tblTypeAdvDtls"  class="delete tblTypeAdvDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete tblTypeAdvDtls"   data-className="tblTypeAdvDtls"   data-totalRow="'+index+'"></span></a>';
		
		$("#tblTypeAdvDtls tbody").append("<tr>" + 
		        "<td>"+Recovery+"</td>" +
		        "<td>"+typeOfRec+"</td>" +
		        "<td>"+principalAmount+"</td>" +
		     //   "<td>"+schemeCode+"</td>" +
		        "<td>"+recoveryFromDate+"</td>" +
		        "<td>"+recoveryToDate+"</td>" +
		        "<td>"+amount+"</td>" +
		        "<td>"+noOfInstallment+"</td>" +
		        "<td>"+noOfInstallmentsPaid+"</td>" +
		        "<td>"+totalAmtPaid+"</td>" +
		        "<td>"+remark+"</td>" +
		        "<td>"+isActive+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");
		
		$(".tblTypeAdvDtls").attr("data-totalRow",index);
	}else{
		$("#tblTypeAdvDtls tbody").empty();
	}
});

$("#tblTypeAdvDtls").on("change", ".recoveryFromDate", function() {
	  var row = $(this).closest("tr");
	  if(row.find(".recoveryToDate").val()!=''){
		  calculateInstallment(row);
	/*	  var sanctionAmount =row.find(".principalAmount").val();
		  var fromDate=new Date(row.find(".recoveryFromDate ").val());
		  var toDate=new Date(row.find(".recoveryToDate ").val());
		
		  
		  var numberOfMonths = Math.ceil((toDate - fromDate) / (30 * 24 * 60 * 60 * 1000));
		    var installmentPerMonth = numberOfMonths > 0 ? sanctionAmount / numberOfMonths : 0;
		  
		  row.find(".amount").val(installmentPerMonth);*/
	  }
});

$("#tblTypeAdvDtls").on("change", ".recoveryToDate", function() {
	var row = $(this).closest("tr");
	calculateInstallment(row);
});


function calculateInstallment(row){
	//var row = $(this).closest("tr");
	var sanctionAmount =row.find(".principalAmount").val();
	var fromDate=new Date(row.find(".recoveryFromDate ").val());
	var toDate=new Date(row.find(".recoveryToDate ").val());
	
	
	var numberOfMonths = Math.ceil((toDate - fromDate) / (30 * 24 * 60 * 60 * 1000));
	var installmentPerMonth = numberOfMonths > 0 ? sanctionAmount / numberOfMonths : 0;
	
	row.find(".amount").val(installmentPerMonth.toFixed(2));
	row.find(".noOfInstallment ").val(numberOfMonths);
	row.find(".noOfInstallmentsPaid ").val("0");
	row.find(".totalAmtPaid  ").val("0");
} 


$(document).on('click', '.delete', function(){ 
	 //var rowlen=Number($(this).attr("data-totalRow"))+1;
	
	 var tableName="tblTypeAdvDtls";
	 var rowlen=$('#tblTypeAdvDtls >tbody >tr').length;
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
				   rowlen=$('#tblTypeAdvDtls >tbody >tr').length;
				  
			  } else {
			  }
			});
		
		$("."+tableName).attr("data-totalRow",Number(rowlen)-1);
	}
});


function ConfirmDeletePensionRecoveryDtls(penEmpRecId,currRow){
	   var context=$("#context").val();
	   var isTrue=isAuthorizedForDelete($("#cmbClassOfPnsn").val());
	   if(isTrue==true){
	   swal({
		   title: "Are you sure?",
		   text: "To Delete this!",
		   icon: "warning",
		   buttons: true,
		   dangerMode: true,
	   }).then((willDelete) => {
		   if (willDelete) {
			   $.ajax({
				   type: "GET",
				   url: context+"/pension/deleteRecoveryDetail/"+penEmpRecId,
				   async: true,
				   error: function(data){
					   console.log(data);
					   swal("Something went wrong !", {
						   icon: "warning",
					   });
				   },
					beforeSend : function(){
						// Show image container
						$( "#loaderMainNew").show();
						},
					complete : function(data){
						$( "#loaderMainNew").hide();
					},	
				   success: function(data){
					   if(parseInt(data)>0){
						   swal("Deleted successfully !", {
							   icon: "success",
						   }); 
						   $(currRow).closest("tr").remove();
					   }
				   },
			   });
		   }
	   }) }else{
		   swal("Your not authorized for delete !", {
	    	      icon: "error",
	    	  }); 
		   
		   return false;
	   }
	   
}   


function isAuthorizedForDelete(cmbClassOfPnsn){
	var roleId=Number($("#roleId").val());
	switch(roleId){
	case 13:
		return true;
	    break;
	    
	case 29:
		return true;
		break;
		
	case 2:
		return false;
		break;
		
	case 12:
		return true;
		break;
		
	case 15:
		return true;
		break;
	case 30:
		if(cmbClassOfPnsn==81) {
			return true;
		}else {
			return false;
		}
		break;
		
	case 31:
		if(cmbClassOfPnsn==81) {
			return true;
		}else {
			return false;
		}
		break;
	case 32:
		if(cmbClassOfPnsn==81) {
			return true;
		}else {
			return false;
		}
		break;
		
	case 33:
		if(cmbClassOfPnsn==81) {
			return true;
		}else {
			return false;
		}
		break;
	    
	  default:
		  return true;
		  break;
	}
}


$("#update").click(function(e){
	
 	 var amount = $('#amount').val();
 	 var recoveryFromDate = $('#recoveryFromDate').val(); 
 	 var recoveryToDate = $('#recoveryToDate').val(); 
 	 var noOfInstallment = $('#noOfInstallment').val(); 
 	 var noOfInstallmentsPaid = $('#noOfInstallmentsPaid').val(); 
 	 var totalAmtPaid = $('#totalAmtPaid').val(); 
 	 var remark = $('#remark').val(); 
 	 var recovery = $('#recoveryFromPension').val();
 	 
 	removeErrorClass($('#amount'));
 	removeErrorClass($('#recoveryFromDate'));
 	removeErrorClass($('#recoveryToDate'));
 	removeErrorClass($('#noOfInstallment'));
 	removeErrorClass($('#noOfInstallmentsPaid'));
 	removeErrorClass($('#totalAmtPaid'));
 	removeErrorClass($('#remark'));
 	 //alert("month and year  ="+month+''+year);
 	 
 	 if(amount=="0"){
 		addErrorClass($('#amount'),"Please Enter Amount !!!");
 		e.preventDefault();
 	 }
 	 
 	 
 	 if(recoveryFromDate=="" && recovery !='permanent'){
 		addErrorClass($('#recoveryFromDate'),"Please Select Date !!!");
 		e.preventDefault();
 	 }
 	 
 	 if(recoveryToDate=="" && recovery !='permanent'){
 		 addErrorClass($('#recoveryToDate'),"Please Select Date !!!");
 		 e.preventDefault();
 	 }
 	 
 	 if(noOfInstallment=="0"){
 		 addErrorClass($('#noOfInstallment'),"Please Enter Installment !!!");
 		 e.preventDefault();
 	 }
 	 
 	 if(noOfInstallmentsPaid=="0"){
 		 addErrorClass($('#recoveryFromDate'),"Please Enter Paid Installment !!!");
 		 e.preventDefault();
 	 }
 	 
 	 if(totalAmtPaid=="0"){
 		 addErrorClass($('#totalAmtPaid'),"Please Enter Total Paid Amount !!!");
 		 e.preventDefault();
 	 }
 	 
 	 if(remark==""){
 		 addErrorClass($('#remark'),"Please Enter Remark !!!");
 		 e.preventDefault();
 	 }
 	 
 	 
 	 
});


