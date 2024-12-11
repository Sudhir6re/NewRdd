$("#addDoc").click(function(){
		var rowCount = $('#documentTbl tr').length;
		var col1='<td>'+(Number(rowCount))+'</td>';
		var col2='<td align="left" style="vertical-align: middle;"><div class="custom-file"><input type="file" id="files" class="custom-file-input form-control input-sm" name="files" style="padding: 4px 11px !important;"></div></td>';
		var col3='<td align="center" class="delete" style="vertical-align: middle;"><a><span class="glyphicon glyphicon-trash delete"></span></a></td>';	
		//$("#documentTbl").append('<tr>'+col1+col2+col3+'</tr>');
		$('#documentTbl tr:last').after('<tr>'+col1+col2+col3+'</tr>');
	});
	
	
	
	
	$(document).on('click', '.delete', function(){ 
		 $(this).closest("tr").remove();
	});
	
	
	
	$("#empstaemptype").change(function(){
		var isExServc=$(this).val();
		if(isExServc=="2"){
			$("#txtJoiningdate").prop("disabled",false);
		}else{
			$("#txtJoiningdate").prop("disabled",true);
		}
	});



jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

function popupSancDetailsCA(){

	var reqAmount = document.getElementById("txtReqAmountCA").value;
	var actualCost = document.getElementById("txtActualCostCA").value;
	var maxSancAmount = 20000;
	if(reqAmount != "" && actualCost != "" ){
		if(reqAmount<maxSancAmount){
			if(Number(reqAmount)<Number(actualCost)){
				document.getElementById("txtSancAmountCA").value = reqAmount;
			}
			else{
				document.getElementById("txtSancAmountCA").value = actualCost;
			}
		}else{
			if(Number(actualCost)>maxSancAmount){
				document.getElementById("txtSancAmountCA").value = maxSancAmount;
			}
			else{
				document.getElementById("txtSancAmountCA").value = actualCost;
			}			
		}
	}
}
function checkEligibleAmountCA(){
	var reqAmount = document.getElementById("txtReqAmountCA").value;
	var actualCost = document.getElementById("txtActualCostCA").value;
	if(reqAmount != "" && actualCost != "" ){
		if(reqAmount<20000){
			if(Number(reqAmount)<Number(actualCost)){
				document.getElementById("txtEligibleAmtCA").value = reqAmount;
			}
			else{
				
				
				document.getElementById("txtEligibleAmtCA").value = actualCost;
			}
		}else{
			if(Number(reqAmount)<Number(actualCost)){
				document.getElementById("txtEligibleAmtCA").value = 20000;
			}
			else{
				document.getElementById("txtEligibleAmtCA").value = actualCost;
			}			
		}
	}
}
function validateNoOfInstallmentsCA(){
	
	//alert("validateNoOfInstallmentsCA");
	var a = document.getElementById("txtInstallmentAmountCA").value = "";
	
	//alert("txtInstallmentAmountCA"+a);
	var c= document.getElementById("basic").value;
	//alert("basic"+document.getElementById("basic").value);
	//alert("txtInstallmentAmountCA"+a);
	
	
	
  var b =	document.getElementById("txtOddInstallmentAmtCA").value = "";
  //alert("txtOddInstallmentAmtCA"+b);
  
  
  
  
  
	var sancInstallments = document.getElementById("txtSancInstallmentsCA").value;
	var DOS = document.getElementById("dateSuperAnnuation").value;
	
	
	var lArrDate = DOS.split("/");	
	var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	
	var currDate = new Date();
	var futureDate = new Date(currDate);
	
	futureDate.setMonth(futureDate.getMonth() + Number(sancInstallments));
	
	var maxInstallment=dateDiffInMonth(currDate, dateofSuperAnnu);
	if(sancInstallments!=""){
		if(Number(sancInstallments==0)){
			alert('Sanction Installments Should be greater than 0');
			document.getElementById("txtSancInstallmentsCA").value="";
			document.getElementById("txtSancInstallmentsCA").focus();
			return false;
		}
		if(Number(sancInstallments>40)){
			alert('Sanction Installments Should be less than 40');
			document.getElementById("txtSancInstallmentsCA").value="";
			document.getElementById("txtSancInstallmentsCA").focus();
			return false;
		}
		if(futureDate>dateofSuperAnnu){
			alert('Considering Retirement Date Maximum Number of Total Installments for Employee are '+maxInstallment);
			document.getElementById("txtSancInstallmentsCA").value="";
			document.getElementById("txtSancInstallmentsCA").focus();
			return false;
		}
		var sancAmount = document.getElementById("txtSancAmountCA").value;
		var noOfInstallment = document.getElementById("txtSancInstallmentsCA").value;
		
	
		
		
		if(sancAmount != "" && noOfInstallment != ""){
			
			var inst= Math.floor(sancAmount/noOfInstallment);
			var oddValue = sancAmount - (inst*(noOfInstallment-1));
			
			
			
			
			if(Number(document.getElementById("txtInstallmentAmountCA").value)>Number(document.getElementById("basic").value) &&   Number(document.getElementById("txtInstallmentAmountCA").value)>Number(document.getElementById("sevenpcbasic").value))
			{
				alert("Installment amount can not be greater than basic");
				document.getElementById("txtInstallmentAmountCA").value ="";
				document.getElementById("cmbOddInstallNoCA").value = -1;
				document.getElementById("cmbOddInstallNoCA").disabled = true;
			}
			
			
			
			if (inst==oddValue){
			//alert("inst",+inst);
				document.getElementById("txtInstallmentAmountCA").value = inst;
				//alert("Amount is"+document.getElementById("txtInstallmentAmountCA").value);
			
				//alert(Number(document.getElementById("txtInstallmentAmountCA").value));
				//alert(Number(document.getElementById("lDblBasicPay").value));
				
				if(Number(document.getElementById("txtInstallmentAmountCA").value)>Number(document.getElementById("basic").value) &&   Number(document.getElementById("txtInstallmentAmountCA").value)>Number(document.getElementById("sevenpcbasic").value))
			{
				alert("Installment amount can not be greater than basic");
				document.getElementById("txtInstallmentAmountCA").value ="";
				document.getElementById("cmbOddInstallNoCA").value = -1;
				document.getElementById("cmbOddInstallNoCA").disabled = true;
			}
				
				//alert("6576576");
				
				document.getElementById("cmbOddInstallNoCA").value = -1;
				document.getElementById("cmbOddInstallNoCA").disabled = true;				
			} 
			
			else{
				//alert("in else");
				
				document.getElementById("txtInstallmentAmountCA").value = inst;
				
				if(Number(document.getElementById("txtInstallmentAmountCA").value)>Number(document.getElementById("basic").value) &&   Number(document.getElementById("txtInstallmentAmountCA").value)>Number(document.getElementById("sevenpcbasic").value))
				{
					alert("Installment amount can not be greater than basic");
					document.getElementById("txtInstallmentAmountCA").value ="";
					document.getElementById("cmbOddInstallNoCA").value = -1;
					document.getElementById("cmbOddInstallNoCA").disabled = true;
					document.getElementById("txtOddInstallmentAmtCA").value = oddValue;
					document.getElementById("cmbOddInstallNoCA").disabled = false;
					document.getElementById("cmbOddInstallNoCA").value= 800057;
				}
			    //alert("32132"+document.getElementById("txtInstallmentAmountCA").value);
				document.getElementById("txtOddInstallmentAmtCA").value = oddValue;
				document.getElementById("cmbOddInstallNoCA").disabled = false;
				document.getElementById("cmbOddInstallNoCA").value= 800057;
			}
		}
	}
	
	
	return true;
}
function validateReqAmountCA(flag){
	
	var reqAmount;
	
	if(flag==1){
		reqAmount= document.getElementById("txtReqAmountCA");
		if(reqAmount.value>20000){
			alert('Computer Advance Amount should be less than or Equals to 20,000');
			reqAmount.value = "";
			return false;
		}
	}else{
		reqAmount= document.getElementById("txtSancAmountCA");
		var tempReqAmount = document.getElementById("hdnSancAmountCA").value;
		if(reqAmount.value>20000){
			alert('You cannot enter the sanction amount more than 20,000');
			reqAmount.value=tempReqAmount;
			reqAmount.focus();
			return false;
		}
		else if(reqAmount.value>tempReqAmount){
			alert('Sanction amount cannot be greater than Request amount');
			reqAmount.value=tempReqAmount;
			reqAmount.focus();
			return false;
		}
		
	}	
	return true;
}

function addNewCheckListCA(){
	
	var rowCnt = document.getElementById("hidRowCountCA").value;
	var newRow =  document.getElementById("tblChecklistCA").insertRow(document.getElementById("tblChecklistCA").rows.length);	
	var Cell1 = newRow.insertCell(0);
	var Cell2 = newRow.insertCell(1);
	
	Cell1.innerHTML = 'Enter Document Name     <input type="text" name="txtChecklistNameCA" id="txtChecklistNameCA"/>';
	Cell2.innerHTML = '<input type="button" class="bigbutton" style="width: 100px" value="OK" id="btnOk" name="btnOk" onclick=\'displayChecklistCA(this,"tblChecklistCA",'+rowCnt+')\'"/> <img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblChecklistCA")\'/>';
	document.getElementById("btnAddNewCheckListCA").style.display='none';
	//document.getElementById("btnAddNewCheckListCA").disabled=true;
}

function displayChecklistCA(obj, tblId ,rowCnt){
	
	var label=document.getElementById("txtChecklistNameCA").value;
	if(label!=""){
		var colCnt = document.getElementById("hidCheckListsCA").value;
		var newRow =  document.getElementById("tblChecklistCA").insertRow(document.getElementById("tblChecklistCA").rows.length);	
		var rowID = showRowCell(obj);		
		var tbl = document.getElementById(tblId); 
		tbl.deleteRow(rowID);
		var Cell1 = newRow.insertCell(0);
		Cell1.innerHTML = '<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="'+label+'">'+label +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img name="Image" id="Image" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblChecklistCA")\'/>';
		document.getElementById("btnAddNewCheckListCA").style.display='';
	}else{
		alert('Please Enter Document Name');
	}			
}


$("#selectAllCheckListsComp").change(function(){
		if($(this).is(":checked")){
			$("input[name='cbDocCheckListCA']").prop('checked', true);
		}else{
			$("input[name='cbDocCheckListCA']").prop('checked', false);
		}
	});


function selectAllCheckListComp(){	
	if(document.getElementById("selectAllCheckListsComp").checked){
		var obj ;		
			obj = document.LNARequestProcessForm.cbDocCheckListCA;
			for(var i =0 ; i < obj.length;i++){
				obj[i].checked = true;
			}		
	}
	if(!document.getElementById("selectAllCheckListsComp").checked){		
		for( i =0 ; i < document.LNARequestProcessForm.cbDocCheckListCA.length;i++){
			document.LNARequestProcessForm.cbDocCheckListCA[i].checked = false;
		}		
	}
}


$("form[name='computerAdvanceForm']").validate({
    // Specify validation rules
    rules: {
    	chkValidate: "required",
  
    },
    // Specify validation error messages
    messages: {
    	chkValidate: "Please Select CheckBox",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });