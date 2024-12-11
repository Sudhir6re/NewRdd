/*$("form[name='reviseCaseForm']").validate({
    // Specify validation rules
	ignore: "",
    rules: {
    	cmbCaseType:{
    		required: true,
    		min:1
    	},
    	cmbClassOfPnsn:{
    		required: true,
    		min:1
    	},
    	
    	
    	dateOfDeath : {
			required : function() {
				return ($('#cmbClassOfPnsn').val() == '84' );
			},
		},
		dateOfConfrm : {
			required : function() {
				return ($('#cmbClassOfPnsn').val() == '84' );
			},
		},
		
		cmbCaseType: "required",
		cmbClassOfPnsn: "required",
		dateOfDeath: "required",
		dateOfConfrm: "required",
		file:{
	          required: true,
	         // accept:"jpg,jpeg,pdf",
	          filesize:2,
	      },  
    },
    // Specify validation error messages
    messages: {
    	cmbCaseType: "Please Select Transaction type",
    	cmbClassOfPnsn: "Please Select Class of pension",
    	dateOfDeath: "Please select date of death",
    	dateOfConfrm: "Please select date of confirmation",
    	 file:{
             required: "Please Upload atlest One Document",
             //accept:"Only jpg,jpeg,pdf types of Documents are allowed ",
             filesize:"Only 2 MB Documents size allowed",
         }  
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
   	 if($("#tblFamilyDtls>tbody>tr").length==0){
   		 swal("Please enter Nominee Details");
   	 }else if(checkPercentage()==true){
    		 swal("Please enter valid percentage");
    	 }else if(checkFamilyDetails()==false ){
    		 swal("Please fill all nominee details");
    	 }else{
    		 form.submit();
    	 }
    }
  });*/


$("#tblFamilyDtls").on("change", ".famPensioner", function() {
	  var row = $(this).closest("tr");
	  var famPensioner =row.find(".famPensioner").val();
	  row.find(".nameOfFamMemb").val(famPensioner);
	});




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
	/*var famPensioner='<input type="text" class="form-control famPensioner removeErrorFromInput" id="famPensioner' + index + '"   name="lstPensionEmpFamilyDtlsModel[' + index + '].famPensioner"  />';*/
	var minor='<input type="checkbox"  class="minor chk"  value="Y" id="minor' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].minor"    />';
	var married='<input type="checkbox" class="married chk" value="Y" id="married' + index + '"   name="lstPensionEmpFamilyDtlsModel[' + index + '].married"   />';
	/*var salary='<input type="text" class="form-control removeErrorFromInput salary"  id="salary' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].salary" value="0"      />';*/
	/*var guardianName='<input type="text" class="form-control removeErrorFromInput guardianName"  id="guardianName' + index + '"   name="lstPensionEmpFamilyDtlsModel[' + index + '].guardianName"      />';*/
	var dateOfDeath='<input type="date" class="form-control removeErrorFromDate dateOfDeath" id="dateOfDeath' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].dateOfDeath"  />';
	
	

	 var bankOption = $('<option value="0">Please Select</option>');
	
	$("#bankId > option").each(function() {
		bankOption=bankOption+'<option data="'+index+'" value="'+this.value+'">'+this.text+'</option>';
	});
	
	
	var branchOption = $('<option value="0">Please Select</option>');
	
	$("#cmbTargetBranchName > option").each(function() {
		branchOption=branchOption+'<option data="'+index+'" value="'+this.value+'">'+this.text+'</option>';
	});
	
	
    var	ifscCode='<input type="text" readonly class="form-control ifscCode removeErrorFromInput"  id="lstPensionEmpFamilyDtlsModel' + index + 'ifscCode"  name="lstPensionEmpFamilyDtlsModel[' + index + '].ifscCode"     />';
	var accNo='<input type="text" class="form-control removeErrorFromInput"  id="lstPensionEmpFamilyDtlsModel' + index + 'accNo"  name="lstPensionEmpFamilyDtlsModel[' + index + '].accountNo"     />';
	
		
    var bankId='<select   id="lstPensionEmpFamilyDtlsModel' + index + 'bankId"   name="lstPensionEmpFamilyDtlsModel[' + index + '].bankId"  class="form-control bankId removeErrorFromDropdown" >'+bankOption+'</select>';
		
	var bankBranchId='<select  id="lstPensionEmpFamilyDtlsModel' + index + 'bankBranchId"      name="lstPensionEmpFamilyDtlsModel[' + index + '].branchId"  class="form-control bankBranchId removeErrorFromDropdown" >'+branchOption+'</select>';
		

	var nomineeAddress='<input type="text" class="form-control removeErrorFromInput nomineeAddress "  id="lstPensionEmpFamilyDtlsModel' + index + 'nomineeAddress"  name="lstPensionEmpFamilyDtlsModel[' + index + '].nomineeAddress"     />';
	
	var gpoNo='<input type="text" class="form-control removeErrorFromInput gpoNo"  id="gpoNo' + index + '"  name="lstPensionEmpFamilyDtlsModel[' + index + '].gpoNo"   />';
	
	
	var roleId=Number($("#roleId").val());
	
	
	var gpoLink="";
	
	if(gpoLink=='33'){
		gpoLink='<b><a class="genGpoNoForNominee"   th:data="gpoNo' + index + '" style="color: blue;"  >Don\'t have GPO NO&nbsp;&nbsp;</a></b>';
	}
	
	
	
	
	var deleteRow='<div  align="center" style="vertical-align: middle;"  data-className="tblFamilyDtls"  class="delete "  data-totalRow="'+index+'"><a><span class="glyphicon glyphicon-trash delete tblFamilyDtls"   data-className="tblFamilyDtls"   data-totalRow="'+index+'"></span></a>';
	
	$(".tblFamilyDtls").attr("data-totalRow",index);
	
	$("#tblFamilyDtls tbody").append("<tr>" + 
			"<td class='text-center'>"+nameOfFamMemb+"</td>" +
	        "<td>"+relation+"</td>" +
	        "<td>"+percentage+"</td>" +
	        "<td>"+phyhandMentChal+"</td>" +
	        "<td>"+dob+"</td>" +
	      /*  "<td class='text-center hide'>"+famPensioner+"</td>" +*/
	        "<td class='text-center'>"+minor+"</td>" +
	        "<td class='text-center'>"+married+"</td>" +
	     /*   "<td class='text-right hide'>"+salary+"</td>" +*/
	      /*  "<td>"+guardianName+"</td>" +*/
	        "<td>"+dateOfDeath+"</td>" +
	        "<td>"+bankId+"</td>" +
	        "<td>"+bankBranchId+"</td>" +
	        "<td>"+ifscCode+"</td>" +
	        "<td>"+accNo+"</td>" +
	        "<td>"+branchAddress+"</td>" +
	        "<td>"+nomineeAddress+"</td>" +
	        "<td>"+gpoNo+gpoLink+"</td>" +
	        "<td>"+deleteRow+"</td>" +
	        "</tr>");
/*}else{
	 $('#tblFamilyDtls tbody').empty();
}*/
});

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
			/*var famPensioner=$("#famPensioner"+i).val();*/
			var nameOfFamMemb=$("#nameOfFamMemb"+i).val();
			var relation=$("#relation"+i).val();
			var percentage=$("#percentage"+i).val();
			var phyhandMentChal=$("#phyhandMentChal"+i).val();
			var dob=$("#dob"+i).val();
			/*var salary=$("#salary"+i).val();*/
			/*var guardianName=$("#guardianName"+i).val();*/
			
		/*	if(famPensioner=='' || famPensioner==undefined){
				showError($("#famPensioner"+i),"Please enter name of family pensioner");
				isFilledAllData=false;
			}else{
				hideError($("#famPensioner"+i));
			}*/
				
	
			
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
			 
			/* if(salary=='' || salary==undefined){
				showError($("#salary"+i),"Please enter salary");
				isFilledAllData=false;
			 }else{
					hideError($("#salary"+i));
			 }*/
			 
			 
			 
			/* if($("minor"+i).val()=='Y'){
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
			   }*/
			   
			 
			 
		}
	//}
	return isFilledAllData;
}




$("#cmbClassOfPnsn").change(function() {
	var cmbClassOfPnsn=$("#cmbClassOfPnsn");
	if(cmbClassOfPnsn=='84'){
		$("#Certificates").css("style","display:block;");
	}else{
		$("#Certificates").css("style","display:none;");
	}
});




$("#txtDateOfExpiry").change(function() {
	setFP1AndFp2Date(1);
	var date = new Date($("#txtDateOfExpiry").val());
	date.setDate(date.getDate());
	//$("#txtDateOfCommencement").val(dateToYMD(date));
	 $("#Certificates").show();
	//$("#txtDateOfRetiremt").val(dateToYMD(date));
	
	if(date!=''){
		$("#Certificates").css("style","display:block;");
	}
});



function setFP1AndFp2Date(flag) {

	/*if (flag == 1)
		setFp1Date("txtDateOfBirth", "txtFP1Date", "dateOfBirth");

	else if (flag == 2)
		setFp1Date("txtDateOfExpiry", "txtFP1Date", "dateOfExpiry");

	setFp2Date();*/
}



function setFp1Date(sourceFieldId, targetFieldId, str) {
	if (str == "dateOfBirth") {
		var dor = new Date($("#txtDateOfRetiremt").val());
		dor.setFullYear(dor.getFullYear() + 7);
		var dorp7YearDate = new Date(dor);


		var dob = new Date($("#txtDateOfBirth").val());
		dob.setFullYear(dob.getFullYear() + 65);
		var dobp7YearDate = new Date(dob);


		var date1 = new Date(dob).getTime();
		var date2 = new Date(dor).getTime();

		if (dobp7YearDate < dorp7YearDate) {
			var date = new Date(date1);
			date.setDate(date.getDate() - 1);
			$("#txtFP1Date").val(dateToYMD(date));

		} else if (date1 > date2) {
			var date = new Date(date2);
			date.setDate(date.getDate() - 1);
			$("#txtFP1Date").val(dateToYMD(date));
		} else {
			var date = new Date(date1);
			date.setDate(date.getDate() - 1);
			$("#txtFP1Date").val(dateToYMD(date));
		}
	}
}

function setFp2Date() {
	/*var date = new Date($("#txtFP1Date").val());
	date.setDate(date.getDate() + 1);
	$("#txtFP2Date").val(dateToYMD(date));*/
}


/*function txtDateOfExpiry() {
	var date = new Date($("#txtDateOfExpiry").val());
	$("#txtDateOfExpiry").val(dateToYMD(date));
}*/

$("#txtDateOfExpiry").change(function() {
	var date = new Date($("#txtDateOfExpiry").val());
	date.setDate(date.getDate() + 1);
	$("#txtDateOfConfirmation").val(dateToYMD(date));
});


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
				  
				   rowlen=$('#'+tableName+ ' >tbody >tr').length;
				  if(tableName=="tblNonQualifyingService" && rowlen==0){
						 calTotalService();
					 }else if(tableName=="tblNonQualifyingService" && rowlen>=0){
						 findDiffBetwnTwoDts();
					 }
			  } else {
			  }
			});
		
		$("."+tableName).attr("data-totalRow",Number(rowlen)-1);
	}
});


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

function ConfirmDeleteNomineeDetail(penEmpFamDtlsId,currRow){
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
					   url: context+"/pension/deleteNomineeDetails/"+penEmpFamDtlsId,
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
		   })
	   }else{
		   swal("Your not authorized for delete !", {
	    	      icon: "error",
	    	  }); 
		   
		   return false;
	   }
}   

$("#tblFamilyDtls").on("change", ".bankId", function() {
	  var bankid = $(this).val(); 
	  var index =  $('option:selected', this).attr('data');
	  //alert(index);
	  
	  var row = $(this).closest("tr");
	  var bankBranchId =row.find(".bankBranchId");
	  
	  row.find(".ifscCode").val("");
	  getBranchByBankId(bankid,index,bankBranchId);
	});


function getBranchByBankId(bankid,index1,bankBranchId){
	

	
	
	
	var contextPath = $("#appRootPath").val();
	
	
	if(contextPath==''){
		if(window.location.host=='mjpsevaarth.in'){
			contextPath="https://mjpsevaarth.in";
		}else if(window.location.host=='mjpsevaarth.in'){
			contextPath="https://stag.mjpsevaarth.in";
		}
	}
	
	
	if (bankid != '') {
		$
				.ajax({
					type : "GET",
					url : contextPath+"/pension/fetchbankbranch/" + bankid,
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
							
							$(bankBranchId).empty();
							$(bankBranchId).append(
							"<option value='0'>Please Select</option>");
							
							/*$('#bankBranchId'+index1).empty();
							$('#bankBranchId'+index1)
									.append(
											"<option value='0'>Please Select</option>");*/
							var temp = data;
							$
									.each(
											temp,
											function(index,
													value) {
												console
														.log(value[1]);
											
														$(bankBranchId)
														.append(
																"<option data="+index1+"     value="
																		+ value[0]
																		+ ">"
																		+ value[1]
																		+ "</option>");
											});
						} else {
							$(bankBranchId).empty();
							$(bankBranchId)
									.append(
											"<option value='0'>Please Select</option>");
							swal("Record not found !!!");
						}
					}
				});
	}
}



$("#tblFamilyDtls").on("change", ".bankBranchId", function() {
	
	//var contextPath="";
	
	
	
	var contextPath = $("#appRootPath").val();
	
	
	if(contextPath==''){
		if(window.location.host=='mjpsevaarth.in'){
			contextPath="https://mjpsevaarth.in";
		}else if(window.location.host=='mjpsevaarth.in'){
			contextPath="https://stag.mjpsevaarth.in";
		}
	}
	  var row = $(this).closest("tr");
	  var chk =row.find(".allowCheckBox").prop("checked");
	  var branchId=$(this).val();
	  var index =  $('option:selected', this).attr('data');
		$.ajax({
			type : "GET",
			url : contextPath+"/pension/getIfscCodeByBranchId/"
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
					 row.find(".ifscCode").val(data[0].ifscCode);
					//$("#lstPensionEmpFamilyDtlsModel"+index+"ifscCode").val(data[0].ifscCode);
				//	$("#ifscCode"+index).val(data[0].ifscCode);
			   }
			}
		});
	});

$("#addDeathCertificate").click(function(){
	var index=$('#deathCertificateTbl  >tbody >tr').length;
	var srNo=$('#deathCertificateTbl  >tbody >tr').length+1;
	var fileDescription='<td><input type="text" class="form-control removeErrorFromInput deathCertificateDescription"    id="deathCertificateDesc'+index+'"     name="lstPensFamilyCertificatesModel[' + index + '].deathCertificateDescription"       /></td>';
	var file='<td align="left" style="vertical-align: middle;"><div class="custom-file"><input type="file"  accept="application/pdf"  id="deathCertificates" class="custom-file-input form-control input-sm" name="deathCertificates" style="padding: 4px 11px !important;"></div></td>';
	var deletebtn='<td align="center" class="delete" style="vertical-align: middle;"><a><span class="glyphicon glyphicon-trash delete"></span></a></td>';	
	$("#deathCertificateTbl>tbody").append('<tr>'+fileDescription+file+deletebtn+'</tr>');
});


function ConfirmDeleteDeathCertificate(pensFamilyCertificateId,currRow){
	   var isTrue=isAuthorizedForDelete($("#cmbClassOfPnsn").val());
	   if(isTrue==true){
		   var context=$("#context").val();
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
						      url: context+"/pension/deleteDeathCertificates/"+pensFamilyCertificateId,
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
			})
	   }else{
		   swal("Your not authorized for delete !", {
	    	      icon: "error",
	    	  }); 
		   
		   return false;
	   }
	}   



function rejectCase(){
		   $('#myModal').modal('show');
}
function rejectReviseCase(){
	   $('#myModal').modal('show');
}

function rejectReviseCase(){
	   
	   var context = $("#context").val();
	   
	   var ppoNo = $('#ppoNo').val();
	   var caseReviseRejectRemark=$("#caseRejectRemark").val();
	   
	   
	   if (caseRejectRemark == "" ) {
		   swal("Please Enter Remark");
	   } 
	   else
	   {
		   swal({
			   title: "Are you sure?",
			   text: "To Reject this!",
			   icon: "warning",
			   buttons: true,
			   dangerMode: true,
		   }).then((willDelete) => {
			   if (willDelete) {
				   $.ajax({
					   type: "GET",
					   url: context+"/pension/rejectReviseCaseForm/"+ppoNo+ "/" + caseReviseRejectRemark,
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
							   swal("Revise Case Form  is Rejected successfully !", {
								   icon: "success",
							   });
							   setTimeout(function() {
								   window.location.href = context+'/pension/reviseCaseFormList'; 
							   }, 1000);
							   
							   
							   
						   }
					   },
				   });
			   }
		   })
	   }
}
	   