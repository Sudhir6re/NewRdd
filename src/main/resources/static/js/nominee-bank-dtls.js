

$("form").on("change", ".bankId", function() {
		  var bankid = $(this).val(); 
		  var index =  $('option:selected', this).attr('data');
		  //alert(index);
		  getBranchByBankId(bankid,index);
		});
	

function getBranchByBankId(bankid,index1){
	
			if (bankid != '') {
				$
						.ajax({
							type : "GET",
							url : "../fetchbankbranch/" + bankid,
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
									$('#bankBranchId'+index1).empty();
									$('#bankBranchId'+index1)
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
																'#bankBranchId'+index1)
																.append(
																		"<option data="+index1+"     value="
																				+ value[0]
																				+ ">"
																				+ value[1]
																				+ "</option>");
													});
								} else {
									$('#bankBranchId'+index1).empty();
									$('#bankBranchId'+index1)
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}
}


function bankaccountvalidenumber(inputtxt) {
	var compare = /[0-9]{9,16}/;
	if (inputtxt.value.match(compare)) {
		return true;
	} else {
		inputtxt.value = "";
		swal("Value must Contain 9 to 16 number!");
		return false;
	}
}

function validateNomineeBankAccNumUniqe(acc) {
	// alert('inside validateUIDUniqe');
	var accountNum = acc.value;
	//var employeeId = document.getElementById("employeeId").value;
	
	var context = $("#context").val();
	
		if (accountNum != '') {
			$
					.ajax({
						type : "GET",
						url :context+ "/MJP/master/validateNomineeAccountNum/" + accountNum,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#accNo').val(accountNum);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered Account number: '
										+ accountNum
										+ ' is already present in system. Please enter correct Account number.');

								document.getElementById("accNo").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}





$(document).on('change','.bankBranchId', function(event){	
	var branchId=$(this).val();
	  var index =  $('option:selected', this).attr('data');
		$.ajax({
			type : "GET",
			url : "../getIfscCodeByBranchId/"
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
					$("#ifscCode"+index).val(data[0].ifscCode);
			   }
			}
		});
     });


$("#btnSave1").click(function(e){
	var flag = 0;
	var totalNominee = $("#totalNoOfNaominees").val();

	var totalpercentage = 0;
	for(var i=0 ; i<totalNominee; i++)
		{
			var bankId =  $("#bankId"+i).val();
			var accNo =  $("#accNo"+i).val();
			var bankBranchId =  $("#bankBranchId"+i).val();
			var ifscCode =  $("#ifscCode"+i).val();
			
			var famPensioner=$("#nameOfFamMemb"+i).val();
			var nameOfFamMemb=$("#nameOfFamMemb"+i).val();
			var relation=$("#relation"+i).val();
			var percentage=$("#percentage"+i).val();
			var phyhandMentChal=$("#phyhandMentChal"+i).val();
			var dob=$("#dob"+i).val();
			//var salary=$("#salary"+i).val();
			var guardianName=$("#guardianName"+i).val();
			var percentage=$("#percentage"+i).val();
			var graduity=$("#graduity"+i).val();
			totalpercentage = Number(totalpercentage)+Number(percentage);
			
			if(bankId=='0'){
				addErrorClass($("#bankId"+i),"Please select the Bank Name");
				flag=1;
			}else{
				removeErrorClass($("#bankId"+i));
			}
			if(accNo==''){
				addErrorClass($("#accNo"+i),"Please enter Account No");
				flag=1;
			}else{
				removeErrorClass($("#accNo"+i));
			}
			if(bankBranchId=='0'){
				addErrorClass($("#bankBranchId"+i),"Please select the Bank Branch Name");
				flag=1;
			}else{
				removeErrorClass($("#bankBranchId"+i));
			}
			if(ifscCode==''){
				addErrorClass($("#ifscCode"+i),"Please Enter IFSC Code");
				flag=1;
			}else{
				removeErrorClass($("#ifscCode"+i));
			}
			
			

			if(famPensioner=='' || famPensioner==undefined){
				showError($("#famPensioner"+i),"Please enter name of family pensioner");
				flag=1;
			}else{
				hideError($("#famPensioner"+i));
			}
				
	
			
			if(nameOfFamMemb=='' || nameOfFamMemb==undefined ){
				showError($("#nameOfFamMemb"+i),"Please enter name of family member");
				flag=1;
			}else{
				hideError($("#nameOfFamMemb"+i));
			}
			
			if(relation==''  || relation=='0'){
				showError($("#relation"+i),"Please select relation");
				flag=1;
			}else{
				hideError($("#relation"+i));
			}
			
			 if(percentage=='' || percentage==undefined){
				showError($("#percentage"+i),"Please enter percentage");
				flag=1;
			}else{
				hideError($("#percentage"+i));
			}
			
			 if(phyhandMentChal=='' ||  phyhandMentChal=='0'){
				 showError($("#phyhandMentChal"+i),"Please select Physically Handicapped/Mentally Challanged");
				 flag=1;
			 }else{
					hideError($("#phyhandMentChal"+i));
			 }
			 
			 if(dob=='' || dob==undefined){
				showError($("#dob"+i),"Please select dob");
				flag=1;
			 }else{
					hideError($("#dob"+i));
			 }
			 
		/*	 if(salary=='' || salary==undefined){
				showError($("#salary"+i),"Please enter salary");
				flag=1;
			 }else{
					hideError($("#salary"+i));
			 }
			 */
			 
			 
			 if($("#minor"+i).val()=='Y'){
				 if(guardianName==''){
						showError($("#guardianName"+i),"Please enter Guardian Name");
						flag=1;
						
					}else{
						hideError($("#guardianName"+i));
					 }
			 }
			 
			 if(graduity=='' || graduity==undefined){
					showError($("#graduity"+i),"Please enter graduity");
					flag=1;
				}else{
					hideError($("#graduity"+i));
				}
			 
			 
			 
			  var dob = new Date($("#dob"+i).val());
			   var today = new Date();
			   var age = Math.floor((today-dob) / (365.25 * 24 * 60 * 60 * 1000));
			   $("#guardianName"+i).prop('disabled',true);
			   if(parseInt(age)<18){
				   $("#guardianName"+i).prop('disabled',false);
				   if(guardianName==''){
						showError($("#guardianName"+i),"Please enter Guardian Name");
						flag=1;
						alert("Please enter Guardian Name");
					}else{
						hideError($("#guardianName"+i));
					 }
			   }else{
				   $("#guardianName"+i).prop('disabled',true);
			   }			
			
			
		}
	
	
	if(flag==1){
		e.preventDefault();
	}
	if(checkPercentage()){
		
		showError($(".percentage"),"Please enter valid percentage");
		e.preventDefault();
	}
	
	
});


/*
$("#addNomineessBankDtlRow").click(function(e){
	 e.preventDefault();
	var noNominee=$("#totalNoOfNaominees").val();
	 var index=parseInt(noNominee);
	 addRow(index);
	if(parseInt(noNominee)<3){
		 var index=parseInt(noNominee);
		 addRow(index);
		 e.preventDefault();
	}else{
		 e.preventDefault();
		 $("#addNomineessBankDtlRow").prop("disabled",true);
	}
});
*/


$("#addNomineessBankDtlRow").click(function(e){
	 e.preventDefault();
	var noNominee=$("#totalNoOfNaominees").val();
	 var index=parseInt(noNominee);
	 addRow(index);
	/*if(parseInt(noNominee)<3){
		 var index=parseInt(noNominee);
		 addRow(index);
		 e.preventDefault();
	}else{
		 e.preventDefault();
		 $("#addNomineessBankDtlRow").prop("disabled",true);
	}*/
});




function addRow(index){
	
	

	var relationList='<option value="0" >--Select--</option> <option value="Husband" > Husband</option> <option value="Wife" > Wife</option> <option value="Daughter" > Daughter</option> <option value="Son" > Son</option> <option value="Other" > Other</option> <option value="Sister" > Sister</option> <option value="Brother" > Brother</option> <option value="Father" > Father</option> <option value="Mother" > Mother</option> <option value="DG" > Defecto Guardian</option> <option value="CPDS" > Children of Predeceased Son</option> <option value="WDAU" > Widowed Daughters</option> <option value="WSIS" > Widow Sister</option> <option value="Spouse" > Spouse</option>';

	var counter=parseInt(index)+1;
	
	var frmGrpStart='<div class="form-group">';
	var frmGppEnd='</div>';
	
	var nomineeLabel='<label class="control-label col-sm-2" for="title">Nominee Name&nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var nameOfFamMemb='<div class="col-sm-3"><input type="text" class="form-control removeErrorFromInput nameOfFamMemb"    id="nameOfFamMemb'+index+'"     name="lstNominee[' + index + '].nameOfFamMemb";   /></div>';
	var penEmpFamDtlsId='<input type="hidden" class="form-control removeErrorFromInput penEmpFamDtlsId"    id="penEmpFamDtlsId'+index+'"   name="lstNominee[' + index + '].penEmpFamDtlsId"  />';

	var accNoLabel='<label class="control-label col-sm-2" for="title">Account Number &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var accNo='<div class="col-sm-3"><input type="text" class="form-control removeErrorFromInput accNo"    onblur="bankaccountvalidenumber(this);validateNomineeBankAccNumUniqe(this);" id="accNo'+index+'" minlength="9" maxlength="18"    name="lstNominee[' + index + '].accNo"; /></div>';
	
	var firstRow=frmGrpStart+nomineeLabel+nameOfFamMemb+penEmpFamDtlsId+accNoLabel+accNo+frmGppEnd;
	
	
	var bankList = $("#banksList > option").clone();
	
	
	 var newOption = $('<option value="0">Please Select</option>');
	 var newOption1 = $('<option value="0">Please Select</option>');
	 var newOption2 = $('<option value="0">Please Select</option>');
	
	$("#banksList > option").each(function() {
	    newOption=newOption+'<option data="'+index+'" value="'+this.value+'">'+this.text+'</option>';
	});
	
	
	var bankLabel='<label class="control-label col-sm-2" for="title">Bank Name &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var bankId='<div class="col-sm-3"><select   id="bankId'+index+'"     name="lstNominee[' + index + '].bankId"  class="form-control bankId removeErrorFromDropdown" >'+newOption+'</select></div>';
	
	
	
	var bankBranchLabel='<label class="control-label col-sm-2" for="title">Bank Branch  Name &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var bankBranchId='<div class="col-sm-3"><select   id="bankBranchId'+index+'"     name="lstNominee[' + index + '].bankBranchId"  class="form-control bankBranchId removeErrorFromDropdown" ></select></div>';
	
	
	var secondRow=frmGrpStart+bankLabel+bankId+bankBranchLabel+bankBranchId+frmGppEnd;
	
	var ifscCodeLabel='<label class="control-label col-sm-2" for="title">IFSC Code &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var ifscCode='<div class="col-sm-3"><input type="text" class="form-control removeErrorFromInput ifscCode" readonly="readonly"    id="ifscCode'+index+'"     name="lstNominee[' + index + '].ifscCode"; /></div>';
	
	var ThirdRow=frmGrpStart+ifscCodeLabel+ifscCode+frmGppEnd;

	$("#relation > option").each(function() {
	    newOption1=newOption1+'<option data="'+index+'" value="'+this.value+'">'+this.text+'</option>';
	});
	
	var relationLabel='<label class="control-label col-sm-2" for="title">Relationship &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var relation='<div class="col-sm-3"> <select   id="relation'+index+'"     name="lstNominee[' + index + '].relation"  class="form-control removeErrorFromDropdown" >'+newOption1+'</select></div>';
	
	var percentshareLabel='<label class="control-label col-sm-2" for="title">Percentage share (%) &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var percentage='<div class="col-sm-3"><input type="text" class="form-control percentage removeErrorFromInput"     id="percentage'+index+'"     name="lstNominee[' + index + '].percentage"; /></div>';
	
	
	var FourthRow=frmGrpStart+relationLabel+relation+percentshareLabel+percentage+frmGppEnd;
	
	$("#phyhandMentChal > option").each(function() {
	    newOption2=newOption2+'<option data="'+index+'" value="'+this.value+'">'+this.text+'</option>';
	});
	
	var phyhandicapLabel='<label class="control-label col-sm-2" for="title">Physically Handicap &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var phyhandicap='<div class="col-sm-3"> <select   id="phyhandicap'+index+'"     name="lstNominee[' + index + '].phyhandicap"  class="form-control removeErrorFromDropdown" >'+newOption2+'</select></div>';
	
	var doblabel='<label class="control-label col-sm-2" for="title">Date of Birth &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var dob='<div class="col-sm-3"><input type="date" class="form-control removeErrorFromInput"     id="dob'+index+'"     name="lstNominee[' + index + '].dob"; /></div>';
	
	
	var FifthRow=frmGrpStart+phyhandicapLabel+phyhandicap+doblabel+dob+frmGppEnd;
	
	var minorlable = '<label class="control-label col-sm-2" for="title"> Minor &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var minor = '<div class="col-sm-3"><input type="checkbox"  id="minor'+index+'" name="lstNominee[' + index + '].minor"> </div>';
	
	var marriedlable = '<label class="control-label col-sm-2" for="title"> Married &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var married = '<div class="col-sm-3"><input type="checkbox"  id="married'+index+'" name="lstNominee[' + index + '].married"> </div>';
	
	var SixthRow=frmGrpStart+minorlable+minor+marriedlable+married+frmGppEnd;
	
	var gaurdianLabel='<label class="control-label col-sm-2" for="title">Gaurdian Name &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var gaurdian='<div class="col-sm-3"><input type="text" class="form-control removeErrorFromInput"     id="guardianName'+index+'"     name="lstNominee[' + index + '].gaurdian"; /></div>';
	
	var graduityLabel='<label class="control-label col-sm-2" for="title">Graduity &nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label>';
	var graduity='<div class="col-sm-3"><input type="text" class="form-control removeErrorFromInput"     id="graduity'+index+'"     name="lstNominee[' + index + '].graduity"; /></div>';
	
	var SeventhRow=frmGrpStart+gaurdianLabel+gaurdian+graduityLabel+graduity+frmGppEnd;
	
	$("#addNomineeDiv").append(firstRow+secondRow+ThirdRow+FourthRow+FifthRow+SixthRow+SeventhRow);
	
	//$('#bankId'+index).append(bankList);
	
	var noNominee=$("#totalNoOfNaominees").val();
	$("#totalNoOfNaominees").val(parseInt(noNominee)+1);
}

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

/*function getAge() {
	// alert("Method executed");
	var dateString = document.getElementById("dob").value;
	if (dateString != "") {
		
		$( "#dob" ).removeClass("error");
		  var errorMessageVisible = $("#dob-error").is(":visible");
		     if (errorMessageVisible)
		        $(".dob-error").remove();
		     
		     
		     hideError($( "#dob" ));
		
		var today = new Date();
		var birthDate = new Date(dateString);
		var age = today.getFullYear() - birthDate.getFullYear();
		var m = today.getMonth() - birthDate.getMonth();
		var da = today.getDate() - birthDate.getDate();
		if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
			age--;
		}
		if (m < 0) {
			m += 12;
		}
		if (da < 0) {
			da += 30;
		}

		if (age < 18 || age > 100) {
		//	swal("Age " + age + " is restrict");
			//document.getElementById("dob").value = "";
			
			//$( "#dob" ).addClass("error");
			//$( "#dob" ).after( "<p class='dob-error error' id='dob-error'><b><br>Age " + age + " is restrict<br></b></p>" );
			
			 showError($( "#dob" ),"Age " + age + " is restrict");

		} 
	} else {
		//swal("please provide your date of birth");
	//	$( "#dob" ).addClass("error");
		//$( "#dob" ).after( "<p class='dob-error error' id='dob-error'><b><br>Please provide your Date of Birth<br></b></p>" );
		  hideError($( "#dob" ));
		 showError($( "#dob" ),"Age " + age + " is restrict");
	}
}*/





