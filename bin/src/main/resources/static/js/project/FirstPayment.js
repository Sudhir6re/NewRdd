 jQuery(document).ready(function($) {
                  var varMessage = $("#message").val();

                  if (varMessage != "" && varMessage != undefined) {
                        swal(varMessage);
                  }
            });

var ppoNo='';




/*$("#btnDCRG").click(function(event){
      if(ppoNo==''){
      swal("Please Select Checkbox");
      }else{
              window.location.href="../pension/DCRGBillDetails/"+ppoNo;
      }
});*/


$("#scheduleTimeBtn").click(function(event){
	ppoNo=$("#ppoNo").val();
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//var action="../pension/saveScheduleTime/"+ppoNo;
		var action="../pension/saveScheduleTime";
		$("#scheduleForm").attr("action",action);
		///window.location.href="../pension/saveScheduleTime/"+ppoNo;
	}
});






/*$("#btnCVP").click(function(event){
      if(ppoNo==''){
      swal("Please Select Checkbox");
      }else{
              window.location.href="../pension/CVPBillDetails/"+ppoNo;
      }
});*/
$("#btnMovetoRecordRoom").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
            window.location.href="../pension/moveToLibrary/"+ppoNo;
      }
});
$("#btnCalOfDutyPrd").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
          //  window.location.href="../master/CalculationOfDutyPeriod/"+ppoNo;
           window.open('../master/CalculationOfDutyPeriod/'+ppoNo, '_blank');
      }
});


$("#BalanceDCRGReport").click(function(event){
    if(ppoNo==''){
          swal("Please Select Checkbox");
    }else{
         window.open('../master/balanceBCRG/'+ppoNo, '_blank');
    }
});





$("#btnCalOfComm").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
       //     window.location.href="../master/CalSheetPensionCommutation/"+ppoNo;
           window.open('../master/CalSheetPensionCommutation/'+ppoNo, '_blank');
      }
});
$("#btnCalOfdcrg").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
           //window.location.href="../pension/DetialsofPensionandDCRGcal/"+ppoNo;
           window.open('../pension/DetialsofPensionandDCRGcal/'+ppoNo, '_blank');
      }
});



$("#btnfinalPens").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
         //   window.location.href="../master/FinalPensionPaymentOrder/"+ppoNo;
          window.open('../master/FinalPensionPaymentOrder/'+ppoNo, '_blank');
      }
});


$("#btnProvisionalPens").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//   window.location.href="../master/FinalPensionPaymentOrder/"+ppoNo;
		window.open('../master/ProvisionalPensionPaymentOrder/'+ppoNo, '_blank');
	}
});



$("#btnPensPaymentOrder").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
         //   window.location.href="../master/PensionPaymentOrder/"+ppoNo;
           window.open('../master/PensionPaymentOrder/'+ppoNo, '_blank');
      }
});
$("#btnCalSheetCommut").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
          //  window.location.href="../master/CalSheetPensionCommutation/"+ppoNo;
             window.open('../master/CalSheetPensionCommutation/'+ppoNo, '_blank');
      }
});
$("#btnCommPayOrder").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
         //   window.location.href="../master/CommutationPaymentOrder/"+ppoNo;
            window.open('../master/CommutationPaymentOrder/'+ppoNo, '_blank');
      }
});
$("#btnFamPensOrder").click(function(event){
      if(ppoNo==''){
            swal("Please Select Checkbox");
      }else{
        //    window.location.href="../master/FamilyPaymentOrder/"+ppoNo;
            window.open('../master/FamilyPaymentOrder/'+ppoNo, '_blank');
      }
});
$("#btnCalSheetCommut").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//window.location.href="../master/CalSheetPensionCommutation/"+ppoNo;
		  window.open('../master/CalSheetPensionCommutation/'+ppoNo, '_blank');
	}
});
$("#btnCommPayOrder").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
	//	window.location.href="../master/CommutationPaymentOrder/"+ppoNo;
		 window.open('../master/CommutationPaymentOrder/'+ppoNo, '_blank');
	}
});
$("#btnFamPensOrder").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//window.location.href="../master/FamilyPaymentOrder/"+ppoNo;
		 window.open('../master/FamilyPaymentOrder/'+ppoNo, '_blank');
	}
});
$("#btnGratuityPayOrder").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//window.location.href="../master/GratuityPaymentOrder/"+ppoNo;
		var classOfPension=$('input[name="chkbxPesnionerNo"]:checked').attr("data");
		if(classOfPension=="84"){
			 window.open('../master/familyGratuityPaymentOrderReport/'+ppoNo, '_blank');
		}else{
			 window.open('../master/GratuityPaymentOrder/'+ppoNo, '_blank');
		}
	}
});
$("#btnClsrPensAcc").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//window.location.href="../master/CloserofPensionAcc/"+ppoNo;
		window.open('../master/CloserofPensionAcc/'+ppoNo, '_blank');
	}
});

$("#btnCloserOfFamilyPenAcc").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//window.location.href="../master/CloserofFamPensionAccNoReco/"+ppoNo;
		window.open('../master/CloserofFamPensionAccNoReco/'+ppoNo, '_blank');
	}
});
$("#btnCloserOfFamilyPenAcc1").click(function(event){
	if(ppoNo==''){
		swal("Please Select Checkbox");
	}else{
		//window.location.href="../master/CloserofFamPensionAccBrkn/"+ppoNo;
		window.open('../master/CloserofFamPensionAccBrkn/'+ppoNo, '_blank');
	}
});
    
         
function getChkValue(currentChk){
       ppoNo=currentChk.value;
       
 $("#ppoNo").val(ppoNo);
 
 var makeBtnDisabled=false;
 
 
 var context=$("#context").val();
 $("#pension").attr("href",context+"/pension/pensionBillDetail/"+ppoNo+"/currMonth");
 $("#btnDCRG").attr("href",context+"/pension/DCRGBillDetails/"+ppoNo);
 $("#btnCVP").attr("href",context+"/pension/CVPBillDetails/"+ppoNo);

 
var firstPayflag=$('input[name="chkbxPesnionerNo"]:checked').attr("data-firstpayflag");
var cvpflag=$('input[name="chkbxPesnionerNo"]:checked').attr("data-cvpflag");
var dcrgflag=$('input[name="chkbxPesnionerNo"]:checked').attr("data-dcrgflag");


 if(firstPayflag=="FP"){
	   $("#pension").removeAttr("href");  
	//   swal("First Pension Bill Already Generated !!!");
 }
 
 if(dcrgflag=="DCRG"){
	   $("#btnDCRG").removeAttr("href"); 
	  // swal("First DCRG Bill Already Generated !!!");
 }
 
 if(cvpflag=="CVP"){
	 $("#btnCVP").removeAttr("href");  
	// swal("First Commutaion Bill Already Generated !!!");
 }
      
 
 
       var classOfPension=$('input[name="chkbxPesnionerNo"]:checked').attr("data");
       if(classOfPension!='undefined' && classOfPension!=''){
       if(classOfPension=="84"){
    	   $("#btnFamPensOrder").prop("disabled",false);
    	   $("#btnCloserOfFamilyPenAcc").prop("disabled",false);
    	   $("#btnCloserOfFamilyPenAcc1").prop("disabled",false);
       }else{
    	   $("#btnFamPensOrder").prop("disabled",true);
    	   $("#btnCloserOfFamilyPenAcc").prop("disabled",true);
    	   $("#btnCloserOfFamilyPenAcc1").prop("disabled",true);
       }
       }
}


function getPpoNo(ppoNo){
	$("#ppoNo").val(ppoNo);
	ppoNo=ppoNo;
}



$('#checkboxid').click(function() {
      console.log($(this).val());
      if ($('#checkboxid').prop('checked')) {
            $(".checkboxid").prop('checked', true);
            $(".checkboxid").val(true);
      } else {
            $(".checkboxid").prop('checked', false);
            $(".checkboxid").val(false);
      }
});


$(document).on('click', "[class^=checkbox]", function() {
      if ($(this).prop('checked')) {
            $(this).prop('checked', true);
            $(this).val(true);
      } else {
            $(this).prop('checked', false);
            $(this).val(false);
      }
});







$("#pension").click(function(event){
    var ppoNo=$("#ppoNo").val();
    if(ppoNo!=''){
    	//check first payment already done
    } 
});
$("#btnDCRG").click(function(event){
	var ppoNo=$("#ppoNo").val();
	if(ppoNo!=''){
		//check first payment already done
	} 
});

$("#btnCVP").click(function(event){
	var ppoNo=$("#ppoNo").val();
	if(ppoNo!=''){
		//check first payment already done
	} 
});




/*function isBillAlreadyCreated(){
	$.ajax({
		  type : "POST",
		  url : context+"/admin/isAllowanceDeductionWiseMasterDataPresent/"+isType+'/'+departmentAllowdeducCode+'/'+payCommision+'/'+startDate+'/'+endDate,
		  async : true,
		  contentType : 'application/json',
		  error : function(data) {
			  console.log(data);
		  },
		  success : function(data) {
			  console.log(data);
			  var len = data.length;
		  }
	});
}*/













//end 



/*$("#pension").click(function(event){
        if(ppoNo==''){
                  swal("Please Select Checkbox");
                  }else{
                        $("#ppoNo").val(ppoNo);
                         $("#monthModel").modal("show");
                  }
        
      
});
*/
$("#okBtn").click(function(event){
      var ppoNo=$("#ppoNo").val();
      var month=$("input[type='radio'].month:checked").val();
        window.location.href="../pension/pensionBillDetail/"+ppoNo+"/"+month;
});




