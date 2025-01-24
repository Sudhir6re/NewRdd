var contextPath = $("#appRootPath").val();
var context ="";
var ddo ="";

jQuery(document).ready(function() {
	
	
	       $("#subCorporationId").prop("disabled", true);
			$("#isChangeParentDepartment1").prop("disabled", true);
			$("#reasonForChngParentFieldDept").prop("disabled", true);
				var pph = new PramukhPhoneticHandler();
		pph.convertToIndicIME("fullNameInDevnagri", document
				.getElementById('txtNameInDevanagari'), 'devanagari');

	
	context = $("#appRootPath").val();
		ddo=getUserUrl();
	
	   if (enableTyping != undefined) {
           enableTyping(new Array('fName','mName','lName'),//Input fiel Name
                    new Array('fNamemr','mNamemr','lNamemr'), 'NAME', 'mr_in'); //Output field Name
           
          /* var fNamemr = $('#fNamemr').val();
           var mNamemr = $('#mNamemr').val();
           var lNamemr = $('#lNamemr').val();
           if (fNamemr && mNamemr && lNamemr) {
               $('#fullNameInDevnagri').val(fNamemr + ' ' + mNamemr+''+lNamemr)
           } */   
           
       }
	contextPath = $("#appRootPath").val();
	$("#adminDepartmentId").val("51");
	$("#adminDepartmentId").select2({"disabled":'readonly'});
	$("#qid").select2();
	$("#secqualification").select2();
	$("#cadre").select2();
	$("#designationId").select2();
	$("#districtCode").select2();
	$("#payscalelevel").select2();
	$("#svnthpaybasic").select2();
	$("#payScaleSeven").select2();
	$("#bankId").select2();
	$("#bankBranchId").select2();
	$("#pfseries").select2();
var paycomm =$("#payCommision").val();
if(paycomm != '' && paycomm != undefined){
	if(paycomm =='700005'){ //7 pc
		
		$('#payscalelevel').attr("readonly", false); 
		$('#svnthpaybasic').attr("readonly", false);
		$('#svnthpaybasic').attr("readonly", false);
		
	//	$('#basicPay').empty();
			$('#sevenPcBasic').val($('#basicPay').val());
		
		
		
		$('#payScaleSeven').addClass("readonlydropdown");
		$("#payScaleSeven").attr("readonly", true);
		$("#payInPayBand").attr("readonly", true);
		$('#payInPayBand').empty();
		$('#gradePay').empty();
	}else if(paycomm =='700016'){  //six pc 
		
		$('#payscalelevel').addClass("ignore");
		$('#svnthpaybasic').addClass("ignore");
		
		$("#payscalelevel").select2({"disabled":'readonly'});
		$("#svnthpaybasic").select2({"disabled":'readonly'});
		
		
		
		$('#select2-payscalelevel-container').addClass("readonlydropdown");
		$('#select2-svnthpaybasic-container').addClass("readonlydropdown");
		$('#select2-payscalelevel-container').attr("readonly", true); 
		$('#select2-svnthpaybasic-container').attr("readonly", true); 
		$('#payscalelevel').attr("readonly", true); 
		$('#svnthpaybasic').attr("readonly", true); 
		$('#payScaleSeven').attr("readonly", false); 
		$('#payscalelevel').empty(); 
		$('#svnthpaybasic').empty(); 
	//	$('#basicPay').empty(); 
		
		
	}
}
	
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
	$(".sevaarthIdBlock").show();
	var isGisApplicable=true;
	
	$("#gisapplicable").change(function(){
		$(".gisOtherDiv").hide();
	    if($(this).val()=="1"){
	    	$(".gisOtherDiv").hide();
	    	isGisApplicable=false;
	    	$("#gisgroup").prop("disabled",true);
	    	$("#membership_date").prop("disabled",true);
	    }
	    else if($(this).val()=="9"){
	    	$(".gisOtherDiv").show();
	    	$("#gisgroup").prop("disabled",false);
	    	$("#membership_date").prop("disabled",false);
	    }
	    else{
	     	$(".gisOtherDiv").hide();
	    	$("#gisgroup").prop("disabled",false);
	    	$("#membership_date").prop("disabled",false);
	    	isGisApplicable=true;
	    }
	});
	

				var accmainby = $("#accountmaintainby").val();
				if(accmainby== 3)
				{
					$('#pfseries').empty();
					
					
					$('#pfseries').append("<option value='1'>A</option>");
					$('#pfseries').append("<option value='2'>B</option>");
					$('#pfseries').append("<option value='3'>C</option>");
					$('#pfseries').append("<option value='4'>D</option>");
					$('#pfseries').append("<option value='5'>E</option>");
					$('#pfseries').append("<option value='6'>F</option>");
					$('#pfseries').append("<option value='7'>G</option>");
					$('#pfseries').append("<option value='8'>H</option>");
					$('#pfseries').append("<option value='9'>I</option>");
					$('#pfseries').append("<option value='10'>J</option>");
					$('#pfseries').append("<option value='11'>K</option>");
					$('#pfseries').append("<option value='12'>L</option>");
					$('#pfseries').append("<option value='13'>M</option>");
					$('#pfseries').append("<option value='14'>N</option>");
					$('#pfseries').append("<option value='15'>O</option>");
					$('#pfseries').append("<option value='16'>P</option>");
					$('#pfseries').append("<option value='17'>Q</option>");
					$('#pfseries').append("<option value='18'>R</option>");
					$('#pfseries').append("<option value='19'>S</option>");
					$('#pfseries').append("<option value='20'>T</option>");
					$('#pfseries').append("<option value='21'>U</option>");
					$('#pfseries').append("<option value='22'>V</option>");
					$('#pfseries').append("<option value='23'>W</option>");
					$('#pfseries').append("<option value='24'>X</option>");
					$('#pfseries').append("<option value='25'>Y</option>");
					$('#pfseries').append("<option value='26'>Z</option>");
					$('#pfseries').append("<option value='27'>PHW/NK</option>");
					$('#pfseries').append("<option value='28'>SNR</option>");
					$('#pfseries').append("<option value='29'>NK</option>");
					$('#pfseries').append("<option value='30'>NRTC</option>");
		   	}
	
	
	$("#accountmaintainby").change(function(){
		
		
		$('#pfseries').empty();
		
	    if($(this).val()=="5"){
	    	$(".accMaintainedByOtherDiv").show();
	    //   	$(".pfSeriesDive").hide();
	    }else{
	    	$(".accMaintainedByOtherDiv").hide();
	     //	$(".pfSeriesDive").show();
	    }
	    
	    
	    
	    if($(this).val()=="3"){
	    	
	    	$('#pfseries').append("<option value='1'>A</option>");
			$('#pfseries').append("<option value='2'>B</option>");
			$('#pfseries').append("<option value='3'>C</option>");
			$('#pfseries').append("<option value='4'>D</option>");
			$('#pfseries').append("<option value='5'>E</option>");
			$('#pfseries').append("<option value='6'>F</option>");
			$('#pfseries').append("<option value='7'>G</option>");
			$('#pfseries').append("<option value='8'>H</option>");
			$('#pfseries').append("<option value='9'>I</option>");
			$('#pfseries').append("<option value='10'>J</option>");
			$('#pfseries').append("<option value='11'>K</option>");
			$('#pfseries').append("<option value='12'>L</option>");
			$('#pfseries').append("<option value='13'>M</option>");
			$('#pfseries').append("<option value='14'>N</option>");
			$('#pfseries').append("<option value='15'>O</option>");
			$('#pfseries').append("<option value='16'>P</option>");
			$('#pfseries').append("<option value='17'>Q</option>");
			$('#pfseries').append("<option value='18'>R</option>");
			$('#pfseries').append("<option value='19'>S</option>");
			$('#pfseries').append("<option value='20'>T</option>");
			$('#pfseries').append("<option value='21'>U</option>");
			$('#pfseries').append("<option value='22'>V</option>");
			$('#pfseries').append("<option value='23'>W</option>");
			$('#pfseries').append("<option value='24'>X</option>");
			$('#pfseries').append("<option value='25'>Y</option>");
			$('#pfseries').append("<option value='26'>Z</option>");
	    }
	    });
		
	
	
	/*//added for text box//
	function createNewElement() {
	    // First create a DIV element.
		var txtNewInputBox = document.createElement('div');
		var txtInputBox = document.createElement('div');

	    // Then add the content (a new input box) of the element.
		txtNewInputBox.innerHTML = "<input type='text' id='newInputBox'>";
		txtInputBox.innerHTML = "<input type='text' id='InputBox'>";

	    // Finally put it where it is supposed to appear.
		document.getElementById("newElementId").appendChild(txtNewInputBox);
		document.getElementById("newAccMainByOthr").appendChild(txtInputBox);
	}
	
	//end
*/	
	
	$("#uid3").blur(function(){
		var UID1 = document.getElementById("uid1").value;
		var UID2 = document.getElementById("uid2").value;
		var UID3 = document.getElementById("uid3").value;
		$("#uidNo").val(UID1+UID2+UID3);
		$("#eidNo").prop("disabled",true);
	});
	
	if ($("#uid1").val() && $("#uid2").val() && $("#uid3").val()) {
		var UID1 = document.getElementById("uid1").value;
		var UID2 = document.getElementById("uid2").value;
		var UID3 = document.getElementById("uid3").value;
		$("#uidNo").val(UID1+UID2+UID3);
		$("#eidNo").prop("disabled",true);
	}
	
	$("#payInPayBand").keyup(function(){
		var payInPayBand=$("#payInPayBand").val();
		var gpay=$("#payScaleSeven option:selected").text().split("-");
		
		var minGpay=gpay[0];
		var maxGpay=gpay[1];
		
		if(Number(payInPayBand)<=Number(minGpay)){
			 hideError($("#payInPayBand"));
			 showError($("#payInPayBand"),"Pay In Pay Band  should be greater then "+minGpay);
		}else{
			 hideError($( "#payInPayBand" ));
		}
		
		
		
		
		if(Number(payInPayBand)>=Number(maxGpay)){
			 hideError($("#payInPayBand"));
			 showError($("#payInPayBand"),"Pay In Pay Band  should be less then "+minGpay);
		}else{
			 hideError($( "#payInPayBand" ));
		}
		
		
		
	});
	
	
	
	$("#eidNo").blur(function(){
		$("#uid1").prop("disabled",true);
		$("#uid2").prop("disabled",true);
		$("#uid3").prop("disabled",true);
	});
	$("#uid1,#uid2,#uid3").blur(function(){
		$("#eidNo").prop("disabled",true);
	});
	
	
	
	
	
	//sevaarthIdAlreadyExists
	$("#sevaarthId").blur(function(){
		 hideError($( "#sevaarthId" ));
		var sevaarthId=$("#sevaarthId").val();
			$.ajax({
				type : "GET",
				url : "../level1/sevaarthIdAlreadyExists/"
						+ sevaarthId,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				},
				success : function(data) {
					 console.log(data);
					//var len = data.length;
					if (parseInt(data) != 0) { 
						//swal("Sevaarth Id Already Exists !!!");
						 hideError($( "#sevaarthId" ));
						 showError($( "#sevaarthId" ),"Sevaarth Id Already Exists");
						
						$("#forward").prop("disabled",true);
				   }else{
					   $("#forward").prop("disabled",false);
					   hideError($("#sevaarthId"));
				   }
				}
			});
	     });
	
	
	
	
	
	//validate age
	jQuery.validator.addMethod("validateAge", function(value, element){
		var validAge=false;
		var dateString = document.getElementById("dob").value;
		if (dateString != "") {
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
				validAge=true;
				//swal("Age " + age + " is restrict");
				
				document.getElementById("dob").value = "";
			} 
		} else {
			swal("please provide your date of birth");
		}
		
		return validAge;
	}, "Please specify the correct domain for your documents");
	
	
	
	
	
	
	$(".sevaarthIdExists").change(function(){
	    if($(this).val()=="Y"){
	    	$(".sevaarthIdBlock").show();
	    }
	    else{
	    	$(".sevaarthIdBlock").hide();
	    }
	});
	
	
	
	$("#bankBranchId").change(function(){
		var branchId=$(this).val();
			$.ajax({
				type : "GET",
				url : contextPath+"/ddoast/getIfscCodeByBranchIdForEmp/"
						+ branchId,
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
					var len = data.length;
					if (len != 0) { 
						$("#ifscCode").val(data[0].ifscCode);
				   }
				}
			});
	     });

	
	
	$("#imagePath").show();
	$("#imagePathSign").show();
	
	var lvl=$('#isActive').val();
//	alert("lvl="+lvl);
	
	if(lvl!=''&&lvl!=null)
		{
		var fname=$("#fName").val();
		 $("#photodesc").val(fname.toUpperCase()+"/PHOTO");
		 $("#signaturedesc").val(fname.toUpperCase()+"/SIGNATURE");
		}
	if(lvl != 3 &&lvl != 5){
	//09 dec 2020 uid and eid validation start
	var uidnumbercheck=$('#uidNo').val();
	var eidnumbercheck=$('#eidNo').val();
//	alert("uidnumbercheck="+uidnumbercheck);
	if(eidnumbercheck!=""||uidnumbercheck!=""){
	if ($('#uidNo').val()) {
		document.getElementById('eidNo').readOnly = true;
	}else{
		const fields = ["uid1", "uid2", "uid3"];
		fields.forEach(field => {
		    const element = document.getElementById(field);
		    if (element) {
		        element.readOnly = true;
		    } else {
		    }
		});
	}
	}
	
	//09 dec 2020 uid and eid validation end
	
	//09 dec 2020 dcps validation start
	
	var dcpsarr = document.myForm.dcps;

	var dcpsValue;
	for (var i = 0; i < dcpsarr.length; i++) {
		if (dcpsarr[i].checked == true) {
			dcpsValue = dcpsarr[i].value;
		}
	}

	// alert("dcpsValue="+dcpsValue);

	if (dcpsValue == 'Y') {
		// alert("true method");
		document.getElementById('dcpsaccountmaintainby').disabled = '';
		document.getElementById('accountmaintainby').disabled = 'true';
		document.getElementById('pfseries').disabled = 'true';
		document.getElementById('pfacno').disabled = 'true';
		document.getElementById('pfdescription').disabled = 'true';
		document.getElementById("accountmaintainby").value = "0";
		document.getElementById("pfseries").value = "0";
		///document.getElementById("pfdescription").value = "";
		document.getElementById("nomineename").disabled = '';
		document.getElementById("nomineeaddress").disabled = '';
		document.getElementById("rdob").disabled = '';
		document.getElementById("relation").disabled = '';
		document.getElementById("percent_share").disabled = '';
		document.getElementById("pranNo").disabled = '';

	} 
	if (dcpsValue == 'N'){
		document.getElementById('dcpsaccountmaintainby').disabled = 'true';
		document.getElementById('accountmaintainby').disabled = '';
		document.getElementById('pfseries').disabled = '';
		document.getElementById('pfacno').disabled = '';
		///document.getElementById('pfdescription').disabled = 'true';
		document.getElementById("dcpsaccountmaintainby").value = "0";
		///document.getElementById("pfdescription").value = "";
		document.getElementById("nomineename").disabled = 'true';
		document.getElementById("nomineeaddress").disabled = 'true';
		document.getElementById("rdob").disabled = 'true';
		document.getElementById("relation").disabled = 'true';
		document.getElementById("percent_share").disabled = 'true';
		document.getElementById("pranNo").disabled = 'true';

	}
	
	
	//09 dec 2020 dcps validation end
	
	//09 dec 2020 Marrid validation start
	
	var index = document.getElementById("gender").selectedIndex;
	var genderValue = $("#gender").val();
	var marrageArr = document.myForm.married;
	var marrageValue;
	for (var i = 0; i < marrageArr.length; i++) {
		if (marrageArr[i].checked == true) {
			marrageValue = marrageArr[i].value;
		}
	}

	if (genderValue == 'Male' || genderValue == 'Transgender') {
		if (marrageValue == 'Y') {
			document.getElementById('fatherName').readOnly = true;
		} else {
			document.getElementById('fatherName').readOnly = false;
		}
	}
	if (genderValue == 'Female') {
		if (marrageValue == 'N') {
			document.getElementById('fatherName').readOnly = true;
		} else {
			document.getElementById('fatherName').readOnly = false;
		}
	}
	//09 dec 2020 Marrid validation end
	
	$('body').on('keyup', '#uid1',function() {
		 if (this.value.length === parseInt(this.attributes["maxlength"].value))
		 { 
			 $('#uid2').focus(); 
		 } 
	});
	
	$('body').on('keyup', '#uid2',function() {
		 if (this.value.length === parseInt(this.attributes["maxlength"].value))
		 { 
			 $('#uid3').focus(); 
		 } 
	});
	
	}
	
	var pfseries1=$("#pfSeries1").val();
	
	$('#pfseries').val(pfseries1);
	
	var pfseries = $('#pfseries option:selected').text();
	
	var pfacno=$("#pfacno").val();
//	alert("pfseries="+pfseries);
//	alert("pfacno="+pfacno);
//	alert("pfseries1="+pfseries1);
	if(pfseries!=''&&pfseries!=null&&pfacno!=''&&pfacno!=null&&pfseries!='0'){
		 $("#pfdescription").val(pfseries+"-"+pfacno);
	}
	
	
	/* $('body').on('keyup', '#uid1',function(){ 
		 if (this.value.length === parseInt(this.attributes["maxlength"].value))
		 { $('#uid2').focus(); } }
	 ) 
		 $('body').on('keyup','#uid2', function(){
			 if (this.value.length === parseInt(this.attributes["maxlength"].value)) 
			 { $('#uid3').focus(); } }
		 )*/
	
	//09-12-2020:Advance Validation start
//	$("#myForm").validate({
//		 rules: {
//			 pinCode:{
//				 required: true,
//			        digits: true,
//			        minlength: 6,
//			        maxlength: 6, 
//			 }
//		 },
//		 messages: {
//			 pinCode:{
//			 minlength: "Pincode field accept only 6 digits",
//		      maxlength: "Pincode number field accept only 6 digits", 
//			 }
//		 },
//		 
//	});
	//09-12-2020:Advance Validation end


	
	
	
	
	

	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='myForm']").validate({
		
		//ignore:".ignore,:hidden",
		ignore:".ignore",
		
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			/*sevaarthId : {
				required :true,
				minlength : 11,
			//	pattern: /^([A-Z]){7}([0-9]){4}?$/,
			},*/
			employeeFNameEn : "required",
			uidNo1 : {
				required : function() {
					//returns true if eidNo is empty
					return !$("#eidNo").val();
				},
				digits : true,
				minlength : 3,
			},
			uidNo2 : {
				required : function() {
					//returns true if eidNo is empty
					return !$("#eidNo").val();
				},
				digits : true,
				minlength : 3,
			},
			uidNo3 : {
				required : function() {
					//returns true if eidNo is empty
					return !$("#eidNo").val();
				},
				digits : true,
				minlength : 3,
			},
			eidNo : {
				required : function() {
					//returns true if uid3 is empty
					return !$("#uid3").val();
				},
				digits : true,
			},
			emailId : {
				required : true,
				// Specify that email should be validated
				// by the built-in "email" rule
				email : true
			},
			salutation : {
				required : true,
				min : 1
			},
//			gender : {
//				required : true,
//				min : 1
//			},
			
			gender : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="gender"]:checked').val() == '' || $('input[name="gender"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			
			stateCode : {
				required : true,
				min : 1
			},
			districtCode : {
				required : true,
				min : 1
			},
			giscatagory : {
				required : true,
				min : 1
			},
			begisCatg : {
				required : true,
				//minlength : 2,
			},
			maritalStatus : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="maritalStatus"]:checked').val() == '' || $('input[name="maritalStatus"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			////employeeMNameEn : "required",
			employeeLNameEn : "required",
			employeeFullNameEn : "required",
			employeeFullNameMr : "required",
			///employeeMNameMr : "required",
			dob : "required",
			doj : "required",
			address1 : "required",
			address2 : "required",
			//address3 : "required",
			//locality : "required",
			villageName : "required",
			pinCode:{
				    required: true,
			        digits: true,
			        minlength: 6,
			        maxlength: 6, 
			 },
			physicallyHandicapped : {
					required : function() {
						//returns true if uid3 is empty
						return ($('input[name="physicallyHandicapped"]:checked').val() == '' || $('input[name="physicallyHandicapped"]:checked').val()==undefined);
					},
					minlength : 1,
			},
			mobileNo1:{
					 required: true,
					 digits: true,
					 pattern:/^\d{10}$/,
					 minlength: 10,
					 maxlength: 10, 
		    },
		    panNo:{
		    	required: true,
		    	//digits: false,
		    	pattern: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,
		    	//regex: /^[+-]{1}[0-9]{1,3}\-[0-9]{10}$/,
		    	minlength: 10,
		    	maxlength: 10, 
		    },
		    parentAdminDepartmentId : {   //department
				required : true,
				min : 1
			},
			parentFieldDepartmentId : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
			/*subCorporationId : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},*/
			cadreId : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
			empClass : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
			superannuationage : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
			superAnnDate : "required",
			payCommissionCode : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
			designationId : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
			payscalelevelId : {
				required : function() {
					//returns true if eidNo is empty
					return !parseInt($("#payCommision").val())!=700016;
				},
				//digits : true,
				min : 1,
			},
			svnthpaybasic : {
				required : function() {
					//returns true if eidNo is empty
					return parseInt($("#payCommision").val())!=700016;
				},
				min : 1,
				//minlength : 1,
			},
			basicPay : {
				required : true,
			//	minlength : 1,
			},
			
			payScaleCode : {
				required : function() {
					//returns true if eidNo is empty
					return parseInt($("#payCommision").val())==700016;
				},
				min : parseInt($("#payCommision").val())==700016?1:0,
				//minlength : 1,
			},
			payInPayBand : {
				required : function() {
					//returns true if eidNo is empty
					return parseInt($("#payCommision").val())==700016;
				},
			},
			postdetailid : { //current post
				required : true,
				min : 1,
			},
			adminDepartmentId : {    //current department
				required : true,
				min : 1,
			},
		/*	departmentNameEn : {    //designation at first appointment
				required : true,
			//	min : 1,
				//digit:false,
			},*/
			/*dtInitialAppointmentParentInst : {    //date initial joinng
				required : true,
			},*/
			instName : {    //Institution Address 
				required : true,
			//	minlength : 8,
			//	digit:false,
			},
			/*mobileNo2:{
				 required: true,
				 digits: true,
				 pattern:/^\d{10}$/,
				 minlength: 10,
				 maxlength: 10, 
	          },*/
	          /*instemail : {
					required : true,
					email : true,
				},*/
				/*remark : {
					required : true,
				},*/
				/*approvalByDdoDate : {
					required : true,
				},*/
				/*indiApproveOrderNo : {
					required : true,
				},*/
				bankId : {
					required : true,
					min:1,
				},
				bankBranchId : {
					required : true,
					min:1,
				},
				bankAcntNo : {
					required : true,
					min:8,
					//digit:true,
				},
				/*ifscCode : {
					required : true,
					min:11,
					pattern:/^[a-zA-Z]{4}[0-9]{7}?$/               //^[a-zA-Z0-9,.!? ]*$/
				},*/
				dcpsgpfflag : {
					required : function() {
						//returns true if uid3 is empty
						return ($('input[name="dcpsgpfflag"]:checked').val() == '' || $('input[name="dcpsgpfflag"]:checked').val()==undefined);
					},
					minlength : 1,
				},
				dcpsaccountmaintainby : {
					required : function() {
						//returns true if uid3 is empty
						return ($('input[name="dcpsgpfflag"]:checked').val() == 'Y');
					},
					min : 1,
				},
				accountmaintainby : {
					required : function() {
						//returns true if uid3 is empty
						return !($('input[name="dcpsgpfflag"]:checked').val() == 'Y');
					},
					min : 1,
				},
				pfseries : {
					required : function() {
						//returns true if uid3 is empty
						return ((!($('input[name="dcpsgpfflag"]:checked').val() == 'Y') && $("#accountmaintainby").val()!="5"));
					},
					min : 1,
				},
				pfacno : {
					required : function() {
						//returns true if uid3 is empty
						return !($('#accountmaintainby').val() == '4');
					},
					//min : 1,
				},
				pfdescription : {
					required : function() {
						//returns true if uid3 is empty
						return ((!($('input[name="dcpsgpfflag"]:checked').val() == 'Y') && $("#accountmaintainby").val()!="5"));
					},
					//min : 1,
				},
				gisapplicable : {
					required :true,
					min : 1,
				},
				gisgroup : {
					required : function() {
						//returns true if uid3 is empty
						return (parseInt($('#gisapplicable').val()) ==0 || parseInt($('#gisapplicable').val())!=1);
					},
					min :parseInt($('#gisapplicable').val())==1?0:1,
				},
				membership_date : {
					required : function() {
						return (parseInt($('#gisapplicable').val()) ==0 || parseInt($('#gisapplicable').val())!=1);
					},
//					min : parseInt($('#gisapplicable').val())==1?0:1,
				},
//				cityClass : {
//						required :true,
//						pattern: /^([A-Z]){1}?$/,
//					},
				religionCode:{
					required :true,
					min:1,
					pattern: /^([0-9])?$/,
				},
		},
		 errorPlacement: function(label, element) {
			    if (element.hasClass('web-select2')) {
			      label.insertAfter(element.next('.select2-container')).addClass('mt-2 text-danger');
			      select2label = label
			    } else {
			      label.addClass('mt-2 text-danger');
			      label.insertAfter(element);
			    }
			  },
			  highlight: function(element) {
			    $(element).parent().addClass('is-invalid')
			    $(element).addClass('form-control-danger')
			  },
			  success: function(label, element) {
			    $(element).parent().removeClass('is-invalid')
			    $(element).removeClass('form-control-danger')
			    label.remove();
			  },
		 /*errorPlacement: function (error, element) {
	          //  console.log('dd', element.attr("name"))
	            if (element.attr("name") == "client_city") {
	                error.appendTo("#messageBox");
	            } else {
	                error.insertAfter(element)
	            }
	        },*/
		// Specify validation error messages
		messages : {
		//	sevaarthId : " Please enter SevaarthId",
			/*sevaarthId : {
				required :" Please enter SevaarthId",
				minlength : "SevaarthId should be atleast 11 characters long.",
				//pattern:"Invalid Sevaarth id i.e It should contain 7 Uppercase character followed by 4 digit number",
			},*/
			pfdescription:"Please enter Pf Description",
			employeeFNameEn : "Please enter Employee First Name",
			eidNo : " Please enter EID No \n",
			uidNo1 : {
				required : "Please enter UID No 1",
				minlength : "UID No 1 should be atleast 4 characters long.",
				digit:"Enter number only",
			},
			uidNo2 : {
				required : "Please enter UID No 2",
				minlength : "UID No 2 should be atleast 4 characters long.",
				digit:"Enter number only",
			},
			uidNo3 : {
				required : "Please enter UID No 3",
				minlength : "UID No 3 should be atleast 4 characters long.",
				digit:"Enter number only",
			},
			salutation : " Please select salutation",
			gender : " Please select Gender",
			maritalStatus : " Please select Marital Status",
//			emailId : " Please enter a valid email address",
			///employeeMNameEn : " Please enter Employee Middle Name",
			employeeLNameEn : " Please enter Employee Last Name",
			employeeFullNameEn : " Please enter Employee Full Name",
			employeeFullNameMr : " Please enter FullName In MARATHI",
		///	employeeMNameMr : " Please enter Father/Husband Name",
			dob : " Please select DATE OF BIRTH",
			doj : " Please select DATE OF JOINING",
			address1 : " Please enter Address Building",
			address2 : " Please enter Address Street",
			//address3 : " Please enter Landmark",
			stateCode : " Please Select State",
			districtCode : " Please Select District",
			begisCatg : " Please Select begisCatg",
			giscatagory : " Please Select giscatagory",
			//locality : " Please Enter Locality",
			villageName : "Please Enter Village Name",
			pinCode:{
				required : "Please enter Pin Code",
				minlength : "Pin Code should be atleast 6 Digit long.",
				digit:"Enter Pin Code in number only",
			 },
			physicallyHandicapped :" Please select physicallyHandicapped",
			mobileNo1:{
				required : "Please enter mobile No",
				minlength : "Mobile No should be atleast 10 Digit long.",
				digit:"Enter Mobile No in number only",
				pattern:"Only 10 Digit mobile number allowed",
		    },
		    panNo:{
		    	required : "Please enter Pan No",
				minlength : "Pan No should be atleast 10 Digit long.",
				pattern : "Please Enter Valid Pan No ",
		    },
		    parentAdminDepartmentId : "Please Select Department",
		    parentFieldDepartmentId : "Please Select Corporation",
		//    subCorporationId : "Please Select Sub Corporation",
		    cadreId : "Please Select Cadre",
		    empClass : "Please Select Cadre Group",
		    superannuationage : "Please enter Super Annunation Age",
		    superAnnDate : "Please enter Super Annunation Date",
		    payCommissionCode : "Please Select paycommision",
		    designationId : "Please Select Designation",
		    payscalelevelId : "Please Select Payscale Level",
		    svnthpaybasic : "Please Select Seventh Pay Basic",
		    basicPay : "Please enter Basic pay",
		    postdetailid : "Please enter current Post",
			adminDepartmentId :"Please enter current Department",
			//departmentNameEn : "Please enter Designation at first appointment",
			//dtInitialAppointmentParentInst : "Please Select Initial Joining Date",
			instName:"Please enter Institution Address",
			/*mobileNo2:{
				required : "Please enter mobile No",
				minlength : "Mobile No should be atleast 10 Digit long.",
				digit:"Enter Mobile No in number only",
				pattern:"Only 10 Digit mobile number allowed",
		    },*/
//		    instemail : " Please enter a valid email address",
		    //remark : " Please enter remark",
		//    indiApproveOrderNo : "Individual Approval Order No",
		    //approvalByDdoDate : " Please select Individual Approval Date",
		    bankId : "Please Select Bank",
			bankBranchId :"Please select Bank Branch",
			bankAcntNo :"Please enter Account Number",
//			ifscCode : {
//				required :"Please Enter IFSC Code",
//				pattern:"IFSC should be Alpha Numeric",
//			},
			dcpsgpfflag :"Please Select DCPS Yes Or No",
			dcpsaccountmaintainby :"Please Select DCPS Account Maintained By",
			accountmaintainby :"Please Select Account Maintained By ",
			pfseries :"Please Select PF Series ",
			pfacno :"Please enter PF Account No",
			gisapplicable :"Please Select GIS Applicable",
			gisgroup : "Please Select Gis Group",
			membership_date :"Please select Membership Date",
			//cityClass :"Please select City Class",
			religionCode:"please select religion code",
			payScaleCode :"please select Pay Scale",
			payInPayBand :"Please enter Pay In Pay Band ",
			
		},
		invalidHandler: function(event, validator) {
            var firstError = validator.errorList[0].element;
            var $errorTab = $(firstError).closest('.tab-pane');
            var index = $('.tab-pane').index($errorTab);
            $('.nav-tabs a').eq(index).tab('show');
            
            

			$('html, body')
					.animate(
							{
								scrollTop : $(
										"#tabContainer")
										.offset().top
							},
							2000);
			
			
        },
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form,event) {
			  var dcpsflag=$('input[name="dcpsgpfflag"]:checked').val();
			  
			  var formSubmit=true;
			  if(dcpsflag=='Y'){
				var nmnname=document.getElementsByName("txtNameValue");
//				alert("nmnname="+nmnname);
//				alert("nmnname[0]="+nmnname[0]);
				if(nmnname[0]==undefined)
					{
					swal("Nominee Details  cannot be empty");
				     event.preventDefault();
				     formSubmit=false;
					}
				var nomineename = $("#txtNameValue").val();
				if (nomineename == "") {
					swal("Nominee name  cannot be empty");
				      $('#nomineename').style.borderColor = "red";
				        $('#nomineename').after("<span class='txtNameValueErr' style='color:red;'>Nominee Name is required.</span>");
				        formSubmit=false;
					 event.preventDefault();
				}
				var nomineeaddress = $("#txtNomAddr1").val();
				if (nomineeaddress == "") {
					swal("Nominee Address  cannot be empty");
					 event.preventDefault();
					 formSubmit=false;
				}
				var rdob = $("#txtDateOfBirthValue").val();
				if (rdob == "") {
					swal("Nominee Date of Birth  cannot be empty");
					 event.preventDefault();
					 formSubmit=false;
				}
				var relation = $("#txtRelationshipValue").val();
				if (relation == "0") {
					swal("Relation  cannot be empty");
					 event.preventDefault();
					 formSubmit=false;
				}
				var percent_share = $("#txtPercentShareValue").val();
				if (percent_share == "") {
					swal("Percent Share  cannot be empty");
					 event.preventDefault();
					 formSubmit=false;
				}
				/*if ($('#imagePath').attr('src') == '/MJP/images/null') {
					swal('Please Upload Photo');
					return false;
				}
				if ($('#imagePathSign').attr('src') == '/MJP/images/null') {
					swal('Please Upload Signature');
					return false;
				}*/
				// var photoAttachmentId = $($("#files")[0]).val();
				// // alert("photoAttachmentId="+photoAttachmentId);
				// if (photoAttachmentId == "") {
				// swal("Photo Attachment cannot be empty");
				// return false;
				//																	
				// }
				//																			
				// var signatureAttachmentId = $($("#files")[1]).val();
				// if (signatureAttachmentId == "") {
				// swal("Signature Attachment cannot be empty");
				// return false;
				//																	
				// }
				
				// Nominee code starte Nov 27 2020
				var lNomineeName = "";
				var lAddress1 = "";
				var lAddress2 = "";
				var lDob = "";
				var lPercentShare = "";
				var lRelationship = "";

				// alert("txtNomineeSerialNoValue="+document.getElementById("txtNomineeSerialNoValue").value);
				// alert("txtNameValue="+document.getElementById("txtNameValue").value);
				// alert("txtNomAddr1="+document.getElementById("txtNomAddr1").value);
				// alert("txtDateOfBirthValue="+document.getElementById("txtDateOfBirthValue").value);
				// alert("txtPercentShareValue="+document.getElementById("txtPercentShareValue").value);
				// alert("txtRelationshipValue="+document.getElementById("txtRelationshipValue").value);
				// var input = document.getElementsByName('txtNameValue');

				// Name
				var lListNomineeNames = document.getElementsByName("txtNameValue");
				
				var lListNomineeNamesLength = lListNomineeNames.length;
				for (i = 0; i < lListNomineeNamesLength; i++) {
					lNomineeName = lNomineeName + lListNomineeNames[i].value + "~";
				}

				if (lNomineeName != "") {
//					alert("lNomineeName=" + lNomineeName);
					$('#strArrNomineeName').val(lNomineeName);
				}

				// Address 1
				var lListAddress1 = document.getElementsByName("txtNomAddr1");
				var lListAddress1Length = lListAddress1.length;
				for (k = 0; k < lListAddress1Length; k++) {
					lAddress1 = lAddress1 + lListAddress1[k].value + "~";
				}

				if (lAddress1 != "") {
//					alert("lAddress1=" + lAddress1);
					$('#strArrAddress').val(lAddress1);
				}
				// DOB
				var lListDOB = document.getElementsByName("txtDateOfBirthValue");
				var lListDOBLength = lListDOB.length;
				for (k = 0; k < lListDOBLength; k++) {
					lDob = lDob + lListDOB[k].value + "~";
				}
				if (lDob != "") {
					$('#strArrDob').val(lDob);
				}
				// Percent Share
				var lListPercentShare = document.getElementsByName("txtPercentShareValue");
				var lListPercentShareLength = lListPercentShare.length;
				for (j = 0; j < lListPercentShareLength; j++) {
					lPercentShare = lPercentShare + lListPercentShare[j].value + "~";
				}
				if (lPercentShare != "") {
					$('#strArrPercentShare').val(lPercentShare);
				}

				// Relationship
				var lListRelationship = document.getElementsByName("txtRelationshipValue");
				var lListRelationshipLength = lListRelationship.length;
				for (l = 0; l < lListRelationshipLength; l++) {
					lRelationship = lRelationship + lListRelationship[l].value + "~";
				}
				if (lRelationship != "") {
					$('#strArrRelationship').val(lRelationship);
				}

			  }
				// Nominee code ended Nov 27 2020

			  if(formSubmit){
				  var roleId=$("#levelRoleVal").val();
				  var msg="";
				  
				  
				  if($("#action").val()=="saveAsDraft"){
					  msg="for Save AS Draft ???";
				  }else{
					  if(parseInt(roleId)==3){
						  msg="forward to DDO ???";
					  }else if(parseInt(roleId)){
						  msg="Approve Details ???";
					  }
				  }
				
				  ConfirmBeforeSubRecord(msg,form);
			  }
		}	
//		forwardToDDo();
	});
	
	
	
  $(".tabClass").click(function(){
		var prev=$(this).attr("data-prev");
		var next=$(this).attr("data-next");
		if(prev!="0"){
			$("#btnPrevious").show();
		}else{
			$("#btnPrevious").hide();
		}
		
		if(next!="0"){
			$("#btnNext").show();
		}else{
			$("#btnNext").hide();
		}
		
	});
	
	 updateButtonVisibility();
	 function updateButtonVisibility() {
	        var $tabs = $('#tabContainer .nav-tabs > li');
	        var $activeTab = $('#tabContainer .nav-tabs > .active');
	        var $prevTab = $activeTab.prev('li');
	        var $nextTab = $activeTab.next('li');
	        
	        if ($prevTab.length > 0) {
	            $('#btnPrevious').show();
	        } else {
	            $('#btnPrevious').hide();
	        }
	        
	        if ($nextTab.length > 0) {
	            $('#btnNext').show();
	        } else {
	            $('#btnNext').hide();
	        }
	    }
	
	
	 $('#btnPrevious').click(function() {
	        var $activeTab = $('#tabContainer .nav-tabs > .active');
	        var $prevTab = $activeTab.prev('li');

	        if ($prevTab.length > 0) {
	            $prevTab.find('a').tab('show');
	            updateButtonVisibility();
	        }
	    });

	    $('#btnNext').click(function() {
	        var $activeTab = $('#tabContainer .nav-tabs > .active');
	        var $nextTab = $activeTab.next('li');

	        if ($nextTab.length > 0) {
	            $nextTab.find('a').tab('show');
	            updateButtonVisibility();
	        }
	    });
	    
	    
	    
	    $('.btnSaveAsDraft').click(function(e) {
	        var uidNo1 = $('#uid1').val().trim();
	        var uidNo2 = $('#uid2').val().trim();
	        var uidNo3 = $('#uid3').val().trim();
	        var eidNo = $('#eidNo').val().trim();
	        var dob = $('#dob').val().trim();
	        var gisApplicable = $('#gisapplicable').val().trim();
	        var pfDescription = $('#pfdescription').val().trim();
	        var accountMaintainBy = $('#accountmaintainby').val().trim();
	        var dcpsGpfFlag = $('input[name="dcpsgpfflag"]:checked').val();

	        if (accountMaintainBy === "700094" && pfDescription === "") {
	            swal("Please enter PF description.");
	        } else if ((uidNo1 === "" || uidNo2 === "" || uidNo3 === "") && eidNo === "") {
	            swal("Please enter UID or EID.");
	        } else if (uidNo1 && uidNo2 && uidNo3 && (uidNo1.length !== 4 || uidNo2.length !== 4 || uidNo3.length !== 4)) {
	            swal("Please enter a valid UID.");
	        }else if ($("#salutation").val().trim() === "0") {
	            swal("Please select salutation.");
	        }  else if ($("#fullName").val().trim() === '') {
	            swal("Please enter employee name.");
	        } else if ($("#gender").val().trim() === "") {
	            swal("Please select gender.");
	        } else if (dob === "") {
	            swal("Please select date of birth.");
	        } else if ((gisApplicable === "0" || gisApplicable !== "1") && !checkMembershipDate()) {
	            swal("Please select membership date.");
	        } else if ($("#mobileNo2").val().length>10  || $("#mobileNo2").val().length>10) {
	            swal("Please enter valid mobile number.");
	        }  else {
	        	 $('input[type=text], input[type=email], input[type=date], textarea, input[type=checkbox], input[type=radio], select').addClass('ignore');
                 $("#action").val("saveAsDraft"); 
                 $("#myForm").submit();
	            /*$('input, textarea, select').removeClass('ignore');

	            $('input[type=text], input[type=email], input[type=date], textarea').each(function() {
	                if ($(this).val().trim() === "") {
	                    $(this).addClass('ignore');
	                }
	            });

	            $('input[type=checkbox], input[type=radio]').addClass('ignore');
	            $('select').each(function() {
	                if ($(this).val() === null || $(this).val() === "" || $(this).val() === "0") {
	                    $(this).addClass('ignore');
	                }
	            });
	        	
	        	
	            if ($("form[name='myForm']").valid()) {
	                swal({
	                    title: "Are you sure?",
	                    text: "Do you want to save this as a draft?",
	                    icon: "warning",
	                    buttons: true,
	                    dangerMode: true,
	                }).then((willSave) => {
	                    if (willSave) {
	                        $("#action").val("saveAsDraft");
	                        $("form[name='myForm']").submit(); // Submit the form
	                    }
	                });
	            }*/
	            /*swal({
	                title: "Are you sure?",
	                text: "Do you want to save this as a draft?",
	                icon: "warning",
	                buttons: true,
	                dangerMode: true,
	            }).then((willSave) => {
	                if (willSave) {
	                  $('input[type=text], input[type=email], input[type=date], textarea, input[type=checkbox], input[type=radio], select').addClass('ignore');
	                    $("#action").val("saveAsDraft"); 
	                    $("#myForm").submit();
	                }
	            });*/
	        }
	    }); 

	    
	 
	    //const maxSize = 2 * 1024 * 1024; // 5 MB in bytes
	    const maxSize = 500 * 1024; 
		//const uploads = []
		$("#importFilePhoto").change(function(event){
		 const file = event.target.files[0];
		 var fileSize = event.target.files[0].size; // in bytes
		     var fileName = $(this).val().replace('C:\\fakepath\\', '');
		var ext = fileName.split('.').pop();
		     const filereader = new FileReader();
		     filereader.onloadend = function(evt) {
		         if (evt.target.readyState === FileReader.DONE) {
		             const uint = new Uint8Array(evt.target.result)
		             let bytes = []
		             uint.forEach((byte) => {
		                 bytes.push(byte.toString(16))
		             })
		             const hex = bytes.join('').toUpperCase()
		              const mimeType = getMimetype(hex);
                    if (mimeType !== 'image/jpeg' && mimeType !== 'image/png') {
		              swal("please select valid jpeg file !!!");
		              $('#PensionClassTypeReportController').val('');
		             }else if(fileSize > maxSize){
		             swal('File size exceeds 500 KB limit.');
		             }
		         }
		     }
		     const blob = file.slice(0, 4);
		     filereader.readAsArrayBuffer(blob);
		});



		const uploads1 = []
		$("#importFileSign").change(function(event){
		const file = event.target.files[0];
		 var fileSize = event.target.files[0].size; // in bytes
		var fileName = $(this).val().replace('C:\\fakepath\\', '');
		var ext = fileName.split('.').pop();
		const filereader = new FileReader();
		filereader.onloadend = function(evt) {
		if (evt.target.readyState === FileReader.DONE) {
		const uint = new Uint8Array(evt.target.result)
		let bytes = []
		uint.forEach((byte) => {
		bytes.push(byte.toString(16))
		})
		const hex = bytes.join('').toUpperCase()
		 const mimeType = getMimetype(hex);
            if (mimeType !== 'image/jpeg' && mimeType !== 'image/png') {
		swal("please select valid jpeg file !!!");
		$('#sign').val('');
		}else if(fileSize > maxSize){
		             swal('File size exceeds 500 KB limit.');
		            $('#imagePathSign').val('');
		             }
		}
		}
		const blob = file.slice(0, 4);
		filereader.readAsArrayBuffer(blob);
		});
		

		   const getMimetype = (signature) => {
		       switch (signature) {
		           case '89504E47':
		               return 'image/png'
		           case '47494638':
		               return 'image/gif'
		           case '25504446':
		               return 'application/pdf'
		           case 'FFD8FFDB':
		           case 'FFD8FFE0':
		               return 'image/jpeg'
		           case '504B0304':
		               return 'application/zip'
		           default:
		               return 'Unknown filetype'
		       }
		   }

		
	 
		   

	   
	    
	    
});

var today = new Date(); var dd = today.getDate(); 
var mm = today.getMonth()+1; //January is 0! 
var yyyy = today.getFullYear(); 
if(dd<10){ dd='0'+dd } 
if(mm<10){ mm='0'+mm } 
today = yyyy+'-'+mm+'-'+dd; 

/*
document.getElementById("dtInitialAppointmentParentInst").setAttribute("max", today);
document.getElementById("dtJoinCurrentPost").setAttribute("max", today);
document.getElementById("indiApproDt").setAttribute("max", today);
*/

const fields = ["dtInitialAppointmentParentInst", "dtJoinCurrentPost", "indiApproDt"];

fields.forEach(field => {
    const element = document.getElementById(field);
    if (element) {
        element.setAttribute("max", today);
     //   console.log(`Set max attribute for ${field}.`);
    } else {
       // console.warn(`Element with ID ${field} not found.`);
    }
});




// for fetching payscale associates with paycommission
$("#payCommision")
		.change(
				function() {

					var payCommisionId = $("#payCommision").val();
					// alert("DDO CODE is "+departmentId);
					//   alert("payCommisionId CODE is "+payCommisionId);
					
					$("#payScaleSeven").val("0");
					$("#payInPayBand").val("");
					$("#gradePay").val("");
					$("#basicPay").val("");
					
					
					
					if (payCommisionId != '' && payCommisionId != '0') {
						if (payCommisionId == '700005') {
//							$('#payScaleSeven').attr("disabled", true); 
//							$('#basicPay').attr("disabled", true);
							
							$('#payscalelevel').attr("disabled", false); 
							$('#svnthpaybasic').attr("disabled", false);
							$('#payscalelevel').removeClass("ignore");
							$('#svnthpaybasic').removeClass("ignore");
							$('#basicPay').empty();
							$('#payScaleSeven').addClass("readonlydropdown");
							$("#payScaleSeven").attr("readonly", true);
							$('#payScaleSeven').empty();
							$('#payScaleSeven').addClass("ignore");
							$('#payScaleSeven').removeClass("error");
							$('#payInPayBand').removeClass("error");
							$('#payInPayBand').addClass("ignore");
							$('#payInPayBand').empty();
							$('#gradePay').empty();
							
							$
									.ajax({
										type : "GET",
										url : contextPath+"/ddoast/fetchPayscale/" + payCommisionId,
										async : true,
										contentType : 'application/json',
										error : function(data) {
											// console.log(data);
										},
										beforeSend : function(){
											$( "#loaderMainNew").show();
											},
										complete : function(data){
											$( "#loaderMainNew").hide();
										},	
										success : function(data) {
											 console.log(data);
											// alert(data);
											var len = data.length;
											if (len != 0) {
												// document.getElementById('payScale').readOnly
												// = true;
												document.getElementById('payInPayBand').readOnly = true;
												document.getElementById('basicPay').readOnly = true;
												
												
												$('#payInPayBand').addClass("ignore");
												$('#basicPay').addClass("ignore");
												

												// console.log(data);
												$('#payscalelevel').empty();
												$('#payscalelevel')
														.append(
																"<option value='0'>Please Select</option>");
												var temp = data;
												$
														.each(
																temp,
																function(index,
																		value) {
																	
																	$('#gradePay').val(value[2]);
																	console
																			.log(value[2]);
																	$(
																			'#payscalelevel')
																			.append(
																					"<option value="
																							+ value[4]
																							+ ">"
																							+ value[3]
																							+ "</option>");
																});
											} else {
												$('#payscalelevel').empty();
												$('#svnthpaybasic').empty();
												
												$('#payscalelevel')
														.append(
																"<option value='0'>Please Select</option>");
												$('#svnthpaybasic')
												.append(
														"<option value='0'>Please Select</option>");
												
												swal("Record not found !!!");
											}
										}
									});
						} 
						else if (payCommisionId == '700016') {
							$('#payscalelevel').attr("disabled", true); 
							$('#svnthpaybasic').attr("disabled", true); 
							$('#payScaleSeven').attr("disabled", false); 
							$('#payScaleSeven').attr("readonly", false); 
							$('#payscalelevel').addClass("ignore");
							$('#svnthpaybasic').addClass("ignore");
							$('#payscalelevel').removeClass("error");
							$('#svnthpaybasic').removeClass("error");
							$("#payScaleSeven").removeClass("ignore");
							$("#payInPayBand").removeClass("ignore");
							$("#payScaleSeven").removeClass("readonlydropdown");
							$("#payInPayBand").attr("readonly", false); 
							$('#payscalelevel').empty(); 
							$('#svnthpaybasic').empty(); 
							$('#basicPay').empty(); 
							$.ajax({
										type : "GET",
										url : contextPath+"/ddoast/fetchPayscale/" + payCommisionId,
										async : true,
										contentType : 'application/json',
										error : function(data) {
											console.log("error in 6 pc "+data);
										},
										beforeSend : function(){
											$( "#loaderMainNew").show();
											},
										complete : function(data){
											$( "#loaderMainNew").hide();
										},	
										success : function(data) {
											console.log("success in 6 pc "+data);
											var len = data.length;
											if (len != 0) {
												document.getElementById('payInPayBand').readOnly = false;
												$('#payScaleSeven').empty();
												$('#payScaleSeven')
														.append("<option value='0'>Please Select</option>");
												var temp = data;
												$.each(temp,function(index,value) {
																	console.log(value[2]);
																	
																	$('#payScaleSeven').append("<option value="+ value[4]+ ">"	+ value[3]+ "</option>");
																	});
											} else {
												$('#payScaleSeven').empty();
												$('#payScaleSeven')
														.append(
																"<option value='0'>Please Select</option>");
												swal("Record not found !!!");
											}
										}
									});
						}
						else {
							$('#payScaleSeven').attr("disabled", false); 
							$('#payscalelevel').empty();
							$('#svnthpaybasic').empty();
							$('#payscalelevel').append("<option value='0'>Please Select</option>");
							$('#svnthpaybasic').append(
							"<option value='0'>Please Select</option>");
							swal("Record not found !!!");
						}
					}

				});



// paycommission dropdown ended
// payscale dropdown start
$("#payscalelevel")
		.change(
				function() {
					var payScale = $("#payscalelevel").val();
					// alert("DDO CODE is "+departmentId);
					//  alert("payScale CODE is "+payScale);
					
					$('#svnthpaybasic').attr("disabled", false);
					$('#svnthpaybasic').removeClass("ignore");
					$('#basicPay').empty();
					$('#payScaleSeven').addClass("readonlydropdown");
					$("#payScaleSeven").attr("readonly", true);
					$('#payScaleSeven').empty();
					$('#payScaleSeven').addClass("ignore");
					$('#payScaleSeven').removeClass("error");
					$('#payInPayBand').removeClass("error");
					$('#payInPayBand').addClass("ignore");
					$('#payInPayBand').empty();
					$('#gradePay').empty();

					if (payScale != ''  && payScale != '0') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/fetchsvnpaybasic/" + payScale,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									beforeSend : function(){
										$( "#loaderMainNew").show();
										},
									complete : function(data){
										$( "#loaderMainNew").hide();
									},	
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											var payScale = $("#payScaleSeven").val();
											document.getElementById('payScaleSeven').readOnly = true;
											
											$('#svnthpaybasic').empty();
											$('#svnthpaybasic')
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
																		'#svnthpaybasic')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#svnthpaybasic').empty();
											$('#svnthpaybasic')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
						}
$("#payscalelevel").change(function() {
						var payScaleSeven = $("#payscalelevel").val();
						if (payScaleSeven != '') {
						
								$.ajax({
											type : "GET",
											url : contextPath+"/ddoast/fetchpayScaleSeven/" + payScaleSeven,
											async : true,
											contentType : 'application/json',
											error : function(data) {
											},
											beforeSend : function(){
												$( "#loaderMainNew").show();
												},
											complete : function(data){
												$( "#loaderMainNew").hide();
											},	
											success : function(data) {
												var len = data.length;
												if (len != 0) {
												document.getElementById('payInPayBand').readOnly = true;
													
													var temp = data;
													$.each(temp,function(index,value) {
																		console.log(value[2]);
																		console.log(value[1]);
																		// $("#payInPayBand").val(value[1]);
																		 $("#gradePay").val(value[2]);
//																		$('#payInPayBand').append("<option value="+ value[4]+ ">"	+ value[1]+ "</option>");
//																		$('#gradePay').append("<option value="+ value[4]+ ">"	+ value[2]+ "</option>");
																	});
												} else {
													//$('#payScaleSeven').empty();
													$('#payscalelevel')
															.append(
																	"<option value='0'>Please Select</option>");
													swal("Record not found !!!");
												}
											}
										});
							} 
						

					});

				});
// payscale dropdown end

$("#svnthpaybasic").change(function() {

	var basicpay = $('#svnthpaybasic option:selected').text();

	$("#basicPay").val(basicpay);
	$("#sevenPcBasic").val(basicpay);
	
	
	$("#payInPayBand").val("");

});

// for fetching current post associates with designation
$("#designationId")
		.change(
				function() {
					var designationId = $("#designationId").val();
					var postdetailid = $("#postdetailid").val();
				
					// alert("DDO CODE is "+designationId);
					if (designationId != '' && designationId != '0') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/employeeConfigurationGetCurrentPost/"
											+ designationId+"/"+postdetailid,
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
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											$('#postdetailid').empty();
											$('#postdetailid')
													.append(
															"<option value='0'>Please Select</option>");
											var temp = data;
											$
													.each(
															temp,
															function(index,
																	value) {
//																console
//																		.log(value[2]);
																$(
																		'#postdetailid')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#postdetailid').empty();
											$('#postdetailid')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
					}

				});
// end designation


$("#accountmaintainby1").change(function() {
	
	    var accmainby = $("#accountmaintainby").val();
	    var group = $('#txtGroup').val();
	   var accMaintainText = $('#accountmaintainby option:selected').text();
	   if ((accmainby !== '0' &&  accmainby !== '-1' ) && group !== '') {
	       if (group === 'D' && accMaintainText !== 'Department') {
	           alert('Group D accounts maintained by Department');
	       } else if (['A', 'B', 'C'].includes(group) && ![700092, 700093, 700096].includes(Number(accmainby))) {
	           alert('Group A, B, C accounts maintained by A.G.');
	       }
	   }
	   
	   if(accmainby === '700094'){
		                    document.getElementById('pfdescription').value = 'Others';	
		   					document.getElementById('pfseries').value = 'Others';	
	   }
	   
      
	   
	   

	                    document.getElementById('pfdescription').value = 'Others';	
	   					document.getElementById('pfseries').value = 'Others';	
	   
	   
	   if (accmainby === '700094') {
	       $('#pfseries').val(-1).prop('disabled', true).hide();
	       $('#txtPFSeries').val('').prop('disabled', false).show();
	       $('#pfdescription').val('').prop('readonly', false).prop('disabled', false);
	       $('#txtPfAccountNo').prop('readonly', false);
	       $('#labelForGPFSeriesDesc').show();
	       $('#labelForGPFAcNo').css('display', 'inline');
	   } else if (selectedValue === '700095') {
	       $('#cmbPFSeries').hide();
	       $('#txtPFSeries').val('NA').prop('readonly', true).show();
	       $('#txtPFSeriesDesc').val('NA').prop('readonly', true);
	       $('#txtPfAccountNo').prop('readonly', true);
	       $('#labelForGPFAcNo').hide();
	       $('#labelForGPFSeriesDesc').hide();
	   } else if (selectedValue === '700096') {
	       $('#cmbPFSeries').hide();
	       $('#txtPFSeries').val('Others').prop('readonly', true).show();
	       $('#txtPFSeriesDesc').val('Others').prop('readonly', true);
	       $('#txtPfAccountNo').prop('readonly', false);
	       $('#labelForGPFAcNo').hide();
	       $('#labelForGPFSeriesDesc').hide();
	   } else if (['700092', '700093'].includes(selectedValue)) {
	       $('#cmbPFSeries').prop('disabled', false).show();
	       $('#txtPFSeries').hide();
	       $('#txtPFSeriesDesc').val('').prop('readonly', true);
	       $('#txtPfAccountNo').prop('readonly', false);
	       $('#labelForGPFAcNo').css('display', 'inline');
	       $('#labelForGPFSeriesDesc').hide();

	       $.ajax({
	           url: "ifms.htm",
	           method: "POST",
	           data: {
	               actionFlag: "getLookupValuesForParentAG",
	               typeOfAG: selectedValue
	           },
	           success: function (response) {
	               populatePFSeriesDropdown(response);
	           },
	           error: function () {
	               alert('Something went wrong...');
	           }
	       });
	   } else {
	       var defaultOption = $('<option>', { value: -1, text: '-- Select --' });
	       $('#cmbPFSeries').empty().append(defaultOption).val(-1).prop('disabled', false).show();
	       $('#txtPFSeries').hide();
	       $('#txtPFSeriesDesc').val('').prop('readonly', true);
	       $('#txtPfAccountNo').prop('readonly', false);
	       $('#labelForGPFSeriesDesc').hide();
	   }
	
	
	
	
	
	});



// for fetching pf series associates with account maintain by
$("#accountmaintainby")
		.change(
				function() {
					var accmainby = $("#accountmaintainby").val();
					if(accmainby== '700096')
					{
						document.getElementById('pfdescription').value = 'Others';	
						document.getElementById('pfseries').value = 'Others';	
					}
					else if(accmainby== '700094')
					{
						document.getElementById('pfdescription').value = '';	
						document.getElementById('pfseries').disabled = 'true';	
						$('#pfseries').empty();
						$('#pfacno').empty();
						$('#pfdescription').empty();
					}
					else
						{
					document.getElementById('pfseries').disabled = '';	
					document.getElementById('pfacno').disabled = '';	
				///	document.getElementById('pfdescription').disabled = '';	
					if (accmainby != '' &&  accmainby != '0') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/PfSeries/" + accmainby,
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
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											$('#pfseries').empty();
											$('#pfseries')
													.append(
															"<option value='0'>Please Select</option>");
											var temp = data;
											$
													.each(
															temp,
															function(index,
																	value) {
																console
																		.log(value[2]);
																$('#pfseries')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#pfseries').empty();
											$('#pfseries')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
					}
						}
				});

// account maintain by end

// save as draft start
function saveAsDraft() {

	// alert("txtNomineeSerialNoValue="+document.getElementById("txtNomineeSerialNoValue").value);
	// alert("txtNameValue="+document.getElementById("txtNameValue").value);
	// alert("txtNomAddr1="+document.getElementById("txtNomAddr1").value);
	// alert("txtDateOfBirthValue="+document.getElementById("txtDateOfBirthValue").value);
	// alert("txtPercentShareValue="+document.getElementById("txtPercentShareValue").value);
	// alert("txtRelationshipValue="+document.getElementById("txtRelationshipValue").value);
	// var input = document.getElementsByName('txtNameValue');
	//	  
	// for (var i = 0; i < input.length; i++) {
	// var a = input[i];
	// alert("names[" + i + "].value= "
	// + a.value + " ");
	// }

	// UID Validation
	var UID1 = document.getElementById("uid1").value;
	var UID2 = document.getElementById("uid2").value;
	var UID3 = document.getElementById("uid3").value;
	var EIDNO = document.getElementById("eidNo").value;

	if (document.getElementById('uid1').value.trim() == ""
			&& document.getElementById('uid2').value.trim() == ""
			&& document.getElementById('uid3').value.trim() == ""
			&& document.getElementById('eidNo').value.trim() == "") {
		swal("Please enter UID or EID");
		return false;
	} else if (document.getElementById('uid1').value.trim() != ""
			&& document.getElementById('uid2').value.trim() != ""
			&& document.getElementById('uid3').value.trim() != ""
			&& document.getElementById('eidNo').value.trim() != "") {
		swal("Please enter UID or EID");
		return false;
	}
	if (UID1 != "" && UID2 != "" && UID3 != "") {
		if (document.getElementById('uid1').value.length != 4
				|| document.getElementById('uid1').value.length != 4
				|| document.getElementById('uid1').value.length != 4) {
			swal("Please enter a valid UID.Note Each block contain 4 number");
			return false;
		}
	} else {
		var UID = UID1 + UID2 + UID3;
		// alert('complete UID: '+UID);
		if (UID.length < 12) {
			swal('Please enter complete UID number.');
			return false;
		}

		$('#uidNo').val(UID);
	}
	var salutation = document.getElementById("salutation");
	if (salutation.value === "0") {
		swal('Please select salutation');
		return false;
	}
	var fName = $("#fName").val();
	if (fName == "") {
		swal("First Name cannot be empty");
		return false;
	}
	var mName = $("#mName").val();
	if (mName == "") {
		swal("Middle Name cannot be empty");
		return false;
	}
	var lName = $("#lName").val();
	if (lName == "") {
		swal("Last Name cannot be empty");
		return false;
	}
	var fullName = $("#fullName").val();
	if (fullName == "") {
		swal("Last Name cannot be empty");
		return false;
	}
	var fullNameInDevnagri = $("#fullNameInDevnagri").val();
	if (fullNameInDevnagri == "") {
		swal("Last Name cannot be empty");
		return false;
	}

	var genderv = document.getElementById("gender");
	if (genderv.value === "0") {
		swal('Please select gender');
		return false;
	}

	var marrageArr = document.myForm.married;
	var marrageValue = "";
	for (var i = 0; i < marrageArr.length; i++) {
		if (marrageArr[i].checked == true) {
			marrageValue = marrageArr[i].value;
		}
	}
	// var basicPay=$("#marrageValue").val();
	if (marrageValue == "") {
		swal("Please select married");
		return false;
	}
	if (marrageValue == "N") {
		var fatherName = $("#fatherName").val();
		if (fatherName == "") {
			swal("Father Name cannot be empty");
			return false;
		}
	}
	var dob = $("#dob").val();
	if (dob == "") {
		swal("Date of Birth cannot be empty");
		return false;
	}
	var serviceJod = $("#serviceJod").val();
	if (serviceJod == "") {
		swal("Service Joining Date cannot be empty");
		return false;
	}
	var addressBuilding = $("#addressBuilding").val();
	if (addressBuilding == "") {
		swal("Address Building cannot be empty");
		return false;
	}
	var addressStreet = $("#addressStreet").val();
	if (addressStreet == "") {
		swal("Address Street cannot be empty");
		return false;
	}
	/*var landmark = $("#landmark").val();
	if (landmark == "") {
		swal("Landmark  cannot be empty");
		return false;
	}*/

	var stateCode = document.getElementById("stateCode");
	if (stateCode.value === "0") {
		swal('Please select State');
		return false;
	}

	var districtCode = document.getElementById("districtCode");
	if (districtCode.value === "0") {
		swal('Please select District');
		return false;
	}
	var villageName = $("#villageName").val();
	if (villageName == "") {
		swal("Village Name  cannot be empty");
		return false;
	}
	var pinCode = $("#pinCode").val();
	if (pinCode == "") {
		swal("Pincode  cannot be empty");
		return false;
	}
	/*var landline = $("#landline").val();
	if (landline == "") {
		swal("Landline  cannot be empty");
		return false;
	}*/
	var mobileNo1 = $("#mobileNo1").val();
	if (mobileNo1 == "") {
		swal("Mobile No  cannot be empty");
		return false;
	}
	/*var email = $("#email").val();
	if (email == "") {
		swal("Email  cannot be empty");
		return false;
	}*/
	var panNo = $("#panNo").val();
	if (panNo == "") {
		swal("PAN NO  cannot be empty");
		return false;
	}

	var physicallyHandicappedArr = document.myForm.physicallyHandicapped;
	var physicallyHandicappedValue = "";
	for (var i = 0; i < physicallyHandicappedArr.length; i++) {
		if (physicallyHandicappedArr[i].checked == true) {
			physicallyHandicappedValue = physicallyHandicappedArr[i].value;
		}
	}
	// var basicPay=$("#marrageValue").val();
	if (physicallyHandicappedValue == "") {
		swal("Please select Physically Handicapped");
		return false;
	}

	// Nominee code starte Nov 27 2020
	var lNomineeName = "";
	var lAddress1 = "";
	var lAddress2 = "";
	var lDob = "";
	var lPercentShare = "";
	var lRelationship = "";

	// alert("txtNomineeSerialNoValue="+document.getElementById("txtNomineeSerialNoValue").value);
	// alert("txtNameValue="+document.getElementById("txtNameValue").value);
	// alert("txtNomAddr1="+document.getElementById("txtNomAddr1").value);
	// alert("txtDateOfBirthValue="+document.getElementById("txtDateOfBirthValue").value);
	// alert("txtPercentShareValue="+document.getElementById("txtPercentShareValue").value);
	// alert("txtRelationshipValue="+document.getElementById("txtRelationshipValue").value);
	// var input = document.getElementsByName('txtNameValue');

	// Name
	var lListNomineeNames = document.getElementsByName("txtNameValue");
	var lListNomineeNamesLength = lListNomineeNames.length;
	for (i = 0; i < lListNomineeNamesLength; i++) {
		lNomineeName = lNomineeName + lListNomineeNames[i].value + "~";
	}

	if (lNomineeName != "") {
//		alert("lNomineeName=" + lNomineeName);
		$('#strArrNomineeName').val(lNomineeName);
	}

	// Address 1
	var lListAddress1 = document.getElementsByName("txtNomAddr1");
	var lListAddress1Length = lListAddress1.length;
	for (k = 0; k < lListAddress1Length; k++) {
		lAddress1 = lAddress1 + lListAddress1[k].value + "~";
	}

	if (lAddress1 != "") {
//		alert("lAddress1=" + lAddress1);
		$('#strArrAddress').val(lAddress1);
	}
	// DOB
	var lListDOB = document.getElementsByName("txtDateOfBirthValue");
	var lListDOBLength = lListDOB.length;
	for (k = 0; k < lListDOBLength; k++) {
		lDob = lDob + lListDOB[k].value + "~";
	}
	if (lDob != "") {
		$('#strArrDob').val(lDob);
	}
	// Percent Share
	var lListPercentShare = document.getElementsByName("txtPercentShareValue");
	var lListPercentShareLength = lListPercentShare.length;
	for (j = 0; j < lListPercentShareLength; j++) {
		lPercentShare = lPercentShare + lListPercentShare[j].value + "~";
	}
	if (lPercentShare != "") {
		$('#strArrPercentShare').val(lPercentShare);
	}

	// Relationship
	var lListRelationship = document.getElementsByName("txtRelationshipValue");
	var lListRelationshipLength = lListRelationship.length;
	for (l = 0; l < lListRelationshipLength; l++) {
		lRelationship = lRelationship + lListRelationship[l].value + "~";
	}
	if (lRelationship != "") {
		$('#strArrRelationship').val(lRelationship);
	}

	// Nominee code ended Nov 27 2020

	// alert fullName

	// UID validation end
	$('#action').val('Edit');
	$('#isActive').val('2');
	// if(originalVal1==undefined){
	// $('#bankAccountNo').val('');
	// }else{
	// $('#bankAccountNo').val(originalVal1);
	// }

	// var queryString = $('#myForm').serialize();
	// alert("queryString="+queryString);
	document.getElementById("myForm").submit();
}
// save as draft end

// forward ddo level 2 start
function forwardToDDo() {
	// alert("originalVal1="+originalVal1);
	// UID Validation
	
	
	
      var isGisApplicable=true;

	    if($("#gisapplicable").val()=="1"){
	    	isGisApplicable=false;
	    	$("#gisgroup").prop("disabled",true);
	    	$("#membership_date").prop("disabled",true);
	    }
	    else{
	    	$("#gisgroup").prop("disabled",false);
	    	$("#membership_date").prop("disabled",false);
	    	isGisApplicable=true;
	    }

	
	
	//alert(isGisApplicable);
	
	
	var UID1 = document.getElementById("uid1").value;
	var UID2 = document.getElementById("uid2").value;
	var UID3 = document.getElementById("uid3").value;
	var EIDNO = document.getElementById("eidNo").value;

	if (document.getElementById('uid1').value.trim() == ""
			&& document.getElementById('uid2').value.trim() == ""
			&& document.getElementById('uid3').value.trim() == ""
			&& document.getElementById('eidNo').value.trim() == "") {
		swal("Please enter UID or EID");
		
		
		return false;
	} else if (document.getElementById('uid1').value.trim() != ""
			&& document.getElementById('uid2').value.trim() != ""
			&& document.getElementById('uid3').value.trim() != ""
			&& document.getElementById('eidNo').value.trim() != "") {
		swal("Please enter UID or EID");
		return false;
	}
	if (UID1 != "" && UID2 != "" && UID3 != "") {
		if (document.getElementById('uid1').value.length != 4
				|| document.getElementById('uid2').value.length != 4
				|| document.getElementById('uid3').value.length != 4) {
			swal("Please enter a valid UID.Note Each block contain 4 number");
			return false;
		}
	} else {
		var UID = UID1 + UID2 + UID3;
		// alert('complete UID: '+UID);
		if (UID.length < 12 && ($("#eidNo").val()=='' || $("#eidNo").val()==undefined)) {
			swal('Please enter complete UID number.');
			return false;
		}

		$('#uidNo').val(UID);
	}

	var salutation = document.getElementById("salutation");
	if (salutation.value === "0") {
		swal('Please select salutation');
		return false;
	}
	var fName = $("#fName").val();
	if (fName == "") {
		swal("First Name cannot be empty");
		return false;
	}
	var mName = $("#mName").val();
	if (mName == "") {
		swal("Middle Name cannot be empty");
		return false;
	}
	var lName = $("#lName").val();
	if (lName == "") {
		swal("Last Name cannot be empty");
		return false;
	}
	var fullName = $("#fullName").val();
	if (fullName == "") {
		swal("Full Name cannot be empty");
		return false;
	}
	var fullNameInDevnagri = $("#fullNameInDevnagri").val();
	if (fullNameInDevnagri == "") {
		swal("Full name of devangiri cannot be empty");
		return false;
	}

	var genderv = document.getElementById("gender");
	if (genderv.value == "") {
		swal('Please select gender');
		return false;
	}

	var marrageArr = document.myForm.married;
	var marrageValue = "";
	for (var i = 0; i < marrageArr.length; i++) {
		if (marrageArr[i].checked == true) {
			marrageValue = marrageArr[i].value;
		}
	}
	// var marrageValue=$("#marrageValue").val();
	if (marrageValue == "") {
		swal("Please select married");
		return false;
	}
	if (marrageValue == "N") {
		var fatherName = $("#fatherName").val();
		if (fatherName == "") {
			swal("Father Name cannot be empty");
			return false;
		}
	}

	var dob = $("#dob").val();
	if (dob == "") {
		swal("Date of Birth cannot be empty");
		return false;
	}
	var serviceJod = $("#serviceJod").val();
	if (serviceJod == "") {
		swal("Service Joining Date cannot be empty");
		return false;
	}
	var addressBuilding = $("#addressBuilding").val();
	if (addressBuilding == "") {
		swal("Address Building cannot be empty");
		return false;
	}
	var addressStreet = $("#addressStreet").val();
	if (addressStreet == "") {
		swal("Address Street cannot be empty");
		return false;
	}
	/*var landmark = $("#landmark").val();
	if (landmark == "") {
		swal("Land Mark  cannot be empty");
		return false;
	}*/

	var stateCode = document.getElementById("stateCode");
	if (stateCode.value === "0") {
		swal('Please select State');
		return false;
	}

	var districtCode = document.getElementById("districtCode");
	if (districtCode.value === "0") {
		swal('Please select District');
		return false;
	}
	var villageName = $("#villageName").val();
	if (villageName == "") {
		swal("village Name  cannot be empty");
		return false;
	}
	var pinCode = $("#pinCode").val();
	if (pinCode == "") {
		swal("Pincode  cannot be empty");
		return false;
	}
	/*var landline = $("#landline").val();
	if (landline == "") {
		swal("Landline  cannot be empty");
		return false;
	}*/
	var mobileNo1 = $("#mobileNo1").val();
	if (mobileNo1 == "") {
		swal("Mobile No should  be 10 digit and  cannot be empty");
		return false;
	}
	var email = $("#email").val();
	if (email == "") {
		swal("Email  cannot be empty");
		return false;
	}
	var panNo = $("#panNo").val();
	if (panNo == "") {
		swal("PAN No  cannot be empty");
		return false;
	}
	var physicallyHandicapped = $("#physicallyHandicapped").val();
	if (physicallyHandicapped == "") {
		swal("Physically Handicapped  cannot be empty");
		return false;
	}

	// Department Validation
	var cadre = document.getElementById("cadre");
	if (cadre.value === "0") {
		swal('Please select Cadre');
		return false;
	}

	var payCommision = document.getElementById("payCommision");
	if (payCommision.value === "0") {
		swal('Please select Pay Commision');
		return false;
	}

	var designationId = document.getElementById("designationId");
	if (designationId.value === "0") {
		swal('Please select Designation');
		return false;
	}
	
	if(payCommision.value != "700016"){
	var payscalelevel = document.getElementById("payscalelevel");
	if (payscalelevel.value === "0") {
		swal('Please select Payscale Level');
		return false;
	}
	var svnthpaybasic = document.getElementById("svnthpaybasic");
	if (svnthpaybasic.value === "0") {
		swal('Please select 7thpaybasic');
		return false;
	}
	}
	
	var basicPay = $("#basicPay").val();
	if (basicPay == "") {
		swal("Basic pay cannot be empty");
		return false;
	}
	var postdetailid = document.getElementById("postdetailid");
	if (postdetailid.value === "0") {
		swal('Please select Current post');
		return false;
	}
	var currentDept = document.getElementById("adminDepartmentId");
	if (currentDept.value === "0") {
		swal('Please select current Department');
		return false;
	}
	// var nameOfPostDesg=$("#nameOfPostDesg").val();
	// if (nameOfPostDesg == "") {
	// swal("Name Of Post Designation cannot be empty");
	// return false;
	// }
	/*var dtInitialAppointmentParentInst = $("#dtInitialAppointmentParentInst")
			.val();
	if (dtInitialAppointmentParentInst == "") {
		swal("Date of InitialAppointmentParentInst  cannot be empty");
		return false;
	}*/
	/*var dtJoinCurrentPost = $("#dtJoinCurrentPost").val();
	if (dtJoinCurrentPost == "") {
		swal("Date of Join Current Post  cannot be empty");
		return false;
	}*/
	/*var remark = $("#remark").val();
	if (remark == "") {
		swal("Remark  cannot be empty");
		return false;
	}*/
	/*var indiApproveOrderNo = $("#indiApproveOrderNo").val();
	if (indiApproveOrderNo == "") {
		swal("indiApproveOrderNo  cannot be empty");
		return false;
	}*/
	/*var indiApproDt = $("#indiApproDt").val();
	if (indiApproDt == "") {
		swal("individual approval Date  cannot be empty");
		return false;
	}*/

	// Bank GPF //DCPS GPF DCPS

	var bankId = document.getElementById("bankId");
	if (bankId.value === "0") {
		swal('Please select Bank');
		return false;
	}
	var bankBranchId = document.getElementById("bankBranchId");
	if (bankBranchId.value === "0") {
		swal('Please select Bank Branch');
		return false;
	}
	var bankAccountNo = $("#bankAccountNo").val();
	if (bankAccountNo == "") {
		swal("Banck Account No  cannot be empty");
		return false;
	}

	var dcpsArr = document.myForm.dcps;
	var dcpsValue = "";
	for (var i = 0; i < dcpsArr.length; i++) {
		if (dcpsArr[i].checked == true) {
			dcpsValue = dcpsArr[i].value;
		}
	}
	// var marrageValue=$("#marrageValue").val();
	// alert("dcpsValue="+dcpsValue);
	if (dcpsValue == "") {
		swal("Please select DCPS");
		return false;
	}

	if (dcpsValue == 'Y') {
		var dcpsaccountmaintainby = $("#dcpsaccountmaintainby").val()
		if (dcpsaccountmaintainby == "0") {
			swal("Dcpsaccountmaintainby  cannot be empty");
			return false;
		}
		/*var pfacno = $("#pfacno").val();
		if (pfacno == "") {
			swal("PF Account No  cannot be empty");
			return false;
		}
*/
	} else {

		var accountmaintainby = $("#accountmaintainby").val()
		if (accountmaintainby == "0") {
			swal("Accountmaintainby  cannot be empty");
			return false;
		}
		var pfseries = $("#pfseries").val();
		if (pfseries == "0"  && $("#accountmaintainby").val()!="5") {
			swal("PF Series  cannot be empty");
			return false;
		}

	}

    if(isGisApplicable==true){
	var gisapplicable = $("#gisapplicable").val();
	if (gisapplicable == "0") {
		swal("GIS Applicable  cannot be empty");
		return false;
	}
	var gisgroup = $("#gisgroup").val();
	if (gisgroup == "0") {
		swal("GIS Group  cannot be empty");
		return false;
	}

	var membership_date = $("#membership_date").val();
	if (membership_date == "") {
		swal("Membership Date  cannot be empty");
		return false;
	}
    }
	
    
  
  var dcpsflag=$('input[name="dcpsgpfflag"]:checked').val();
  if(dcpsflag=='Y'){
	var nmnname=document.getElementsByName("txtNameValue");
//	alert("nmnname="+nmnname);
//	alert("nmnname[0]="+nmnname[0]);
	if(nmnname[0]==undefined)
		{
		swal("Nominee Details  cannot be empty");
		return false;
		}
	var nomineename = $("#txtNameValue").val();
	if (nomineename == "") {
		swal("Nominee name  cannot be empty");
		

	      $('#nomineename').style.borderColor = "red";

	        $('#nomineename').after("<span class='txtNameValueErr' style='color:red;'>Nominee Name is required.</span>");
		return false;
	}
	var nomineeaddress = $("#txtNomAddr1").val();
	if (nomineeaddress == "") {
		swal("Nominee Address  cannot be empty");
		return false;

	}
	var rdob = $("#txtDateOfBirthValue").val();
	if (rdob == "") {
		swal("Nominee Date of Birth  cannot be empty");
		return false;

	}
	var relation = $("#txtRelationshipValue").val();
	if (relation == "0") {
		swal("Relation  cannot be empty");
		return false;

	}
	var percent_share = $("#txtPercentShareValue").val();
	if (percent_share == "") {
		swal("Percent Share  cannot be empty");
		return false;

	}
	/*if ($('#imagePath').attr('src') == '/MJP/images/null') {
		swal('Please Upload Photo');
		return false;
	}
	if ($('#imagePathSign').attr('src') == '/MJP/images/null') {
		swal('Please Upload Signature');
		return false;
	}*/
	// var photoAttachmentId = $($("#files")[0]).val();
	// // alert("photoAttachmentId="+photoAttachmentId);
	// if (photoAttachmentId == "") {
	// swal("Photo Attachment cannot be empty");
	// return false;
	//																	
	// }
	//																			
	// var signatureAttachmentId = $($("#files")[1]).val();
	// if (signatureAttachmentId == "") {
	// swal("Signature Attachment cannot be empty");
	// return false;
	//																	
	// }
	
	// Nominee code starte Nov 27 2020
	var lNomineeName = "";
	var lAddress1 = "";
	var lAddress2 = "";
	var lDob = "";
	var lPercentShare = "";
	var lRelationship = "";

	// alert("txtNomineeSerialNoValue="+document.getElementById("txtNomineeSerialNoValue").value);
	// alert("txtNameValue="+document.getElementById("txtNameValue").value);
	// alert("txtNomAddr1="+document.getElementById("txtNomAddr1").value);
	// alert("txtDateOfBirthValue="+document.getElementById("txtDateOfBirthValue").value);
	// alert("txtPercentShareValue="+document.getElementById("txtPercentShareValue").value);
	// alert("txtRelationshipValue="+document.getElementById("txtRelationshipValue").value);
	// var input = document.getElementsByName('txtNameValue');

	// Name
	var lListNomineeNames = document.getElementsByName("txtNameValue");
	
	var lListNomineeNamesLength = lListNomineeNames.length;
	for (i = 0; i < lListNomineeNamesLength; i++) {
		lNomineeName = lNomineeName + lListNomineeNames[i].value + "~";
	}

	if (lNomineeName != "") {
//		alert("lNomineeName=" + lNomineeName);
		$('#strArrNomineeName').val(lNomineeName);
	}

	// Address 1
	var lListAddress1 = document.getElementsByName("txtNomAddr1");
	var lListAddress1Length = lListAddress1.length;
	for (k = 0; k < lListAddress1Length; k++) {
		lAddress1 = lAddress1 + lListAddress1[k].value + "~";
	}

	if (lAddress1 != "") {
//		alert("lAddress1=" + lAddress1);
		$('#strArrAddress').val(lAddress1);
	}
	// DOB
	var lListDOB = document.getElementsByName("txtDateOfBirthValue");
	var lListDOBLength = lListDOB.length;
	for (k = 0; k < lListDOBLength; k++) {
		lDob = lDob + lListDOB[k].value + "~";
	}
	if (lDob != "") {
		$('#strArrDob').val(lDob);
	}
	// Percent Share
	var lListPercentShare = document.getElementsByName("txtPercentShareValue");
	var lListPercentShareLength = lListPercentShare.length;
	for (j = 0; j < lListPercentShareLength; j++) {
		lPercentShare = lPercentShare + lListPercentShare[j].value + "~";
	}
	if (lPercentShare != "") {
		$('#strArrPercentShare').val(lPercentShare);
	}

	// Relationship
	var lListRelationship = document.getElementsByName("txtRelationshipValue");
	var lListRelationshipLength = lListRelationship.length;
	for (l = 0; l < lListRelationshipLength; l++) {
		lRelationship = lRelationship + lListRelationship[l].value + "~";
	}
	if (lRelationship != "") {
		$('#strArrRelationship').val(lRelationship);
	}

  }
	// Nominee code ended Nov 27 2020

	$('#isActive').val('3');

	// if(originalVal1==undefined){
	// $('#bankAccountNo').val('');
	// }else{
	// $('#bankAccountNo').val(originalVal1);
	// }

	// var queryString1 = $('#myForm').serialize();
	// alert("queryString1="+queryString1);
	document.getElementById("myForm").submit();
	// alert("forwarded succefully");

}







// forward ddo level 2 end
function validateUIDUniqe() {
	// alert('inside validateUIDUniqe');
	
	
	
	
	
	
	var UID1 = document.getElementById("uid1").value;
	var UID2 = document.getElementById("uid2").value;
	var UID3 = document.getElementById("uid3").value;
	
	
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId == "" || employeeId == null) {
		employeeId='0';
	}
	if (UID1 == "" || UID1 == null || UID2 == "" || UID2 == null || UID3 == ""
			|| UID3 == null) {
		//swal('Please enter complete UID number.');

	} else {
		var UID = UID1 + UID2 + UID3;
		// alert('complete UID: '+UID);
		if (UID.length < 12) {
			swal('Please enter complete UID number.');
			return false;
		}

		if (UID != '') {
			$
					.ajax({
						type : "GET",
						url : "validateUIDUniqeness/" + UID +"/"+employeeId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
							// console.log(data);
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							//     console.log(data);
							//      alert(data);
							var len = data.length;
							//  alert(len);
							var checkFlag = data;

							if (checkFlag == 0) {
								$('#uidNo').val(UID);
								// document.getElementById("eidNo").style.display
								// = 'none';
								document.getElementById('eidNo').readOnly = true;
								status = true;
								// alert('all ok');
							} else if (checkFlag > 0) {

								swal('Entered UID number: '
										+ UID
										+ ' is already present in system. Please enter correct UID number.');

								document.getElementById("uid1").value = "";
								document.getElementById("uid2").value = "";
								document.getElementById("uid3").value = "";
								status = false;
							}
							return status;
							// var len=data.length;
							if (len != 0) {
								// console.log(data);
								//     $('#svnthpaybasic').empty();
								//     $('#svnthpaybasic').append("<option
								// value='0'>Please Select</option>");
								//     var temp = data;
								//    $.each( temp, function( index, value ){
								//     console.log( value[1] );
								//     $('#svnthpaybasic').append("<option
								// value="+value[0]+">" + value[1] +
								// "</option>");
								//     });
							} else {
								//     $('#svnthpaybasic').empty();
								//     $('#svnthpaybasic').append("<option
								// value='0'>Please Select</option>");
								//      swal("Record not found !!!");
							}
						}
					});
		}
	}

}



function validateMobileUniqe() {
	// alert('inside validateUIDUniqe');
	var mobileno = document.getElementById("mobileNo1").value;
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId == "" || employeeId == null) {
		employeeId='0';
	}
	
	
		if (mobileno != '') {
			$
					.ajax({
						type : "GET",
						url : "validateMobileno/" + mobileno+"/"+employeeId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#mobileNo1').val(mobileno);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered Mobile number: '
										+ mobileno
										+ ' is already present in system. Please enter correct mobile number.');

								document.getElementById("mobileNo1").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

function validateTelePhoneUniqe() {
	// alert('inside validateUIDUniqe');
	var telephoneno = document.getElementById("landline").value;
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId == "" || employeeId == null) {
		employeeId='0';
	}
	
		if (telephoneno != '') {
			$
					.ajax({
						type : "GET",
						url : "validateTelephone/" + telephoneno+"/"+employeeId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#landline').val(telephoneno);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered Tele Phone number: '
										+ telephoneno
										+ ' is already present in system. Please enter correct Tele Phone number.');

								document.getElementById("landline").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

function validateEmailUniqe() {
	// alert('inside validateUIDUniqe');
	var email = document.getElementById("email").value;
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId == "" || employeeId == null) {
		employeeId='0';
	}
	
		if (email != '') {
			$
					.ajax({
						type : "GET",
						url : "validateEmail/" + email+"/"+employeeId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#email').val(email);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered Email ID: '
										+ email
										+ ' is already present in system. Please enter another Email ID.');

								document.getElementById("email").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

function validatePanUniqe() {
	// alert('inside validateUIDUniqe');
	var panno = document.getElementById("panNo").value;
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId == "" || employeeId == null) {
		employeeId='0';
	}
	
		if (panno != '') {
			$
					.ajax({
						type : "GET",
						url : "validatePancard/" + panno+"/"+employeeId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#panNo').val(panno);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered PAN number: '
										+ panno
										+ ' is already present in system. Please enter correct PAN number.');

								document.getElementById("panNo").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}



function onlyAlphabets(e, t) {
	try {
		if (window.event) {
			var charCode = window.event.keyCode;
		} else if (e) {
			var charCode = e.which;
		} else {
			return true;
		}
		if ((charCode > 64 && charCode < 91)
				|| (charCode > 96 && charCode < 123))
			return true;
		else
			return false;
	} catch (err) {
		swal(err.Description);
	}
}

function checkGender() {
	var index = document.myForm.gender.selectedIndex;
	var genderValue = $("#gender").val();
	var marrageArr = document.myForm.married;
	var marrageValue;
	for (var i = 0; i < marrageArr.length; i++) {
		if (marrageArr[i].checked == true) {
			marrageValue = marrageArr[i].value;
		}
	}

	if (genderValue == 'Male' || genderValue == 'Transgender') {
		if (marrageValue == 'Y') {
			document.getElementById('fatherName').readOnly = true;
		} else {
			document.getElementById('fatherName').readOnly = false;
		}
	}
	if (genderValue == 'Female') {
		if (marrageValue == 'N') {
			document.getElementById('fatherName').readOnly = true;
		} else {
			document.getElementById('fatherName').readOnly = false;
		}
	}

	return true;
}

function getAge() {
	// alert("Method executed");
	var dateString = document.getElementById("dob").value;
	if (dateString != "") {
		
		/*$( "#dob" ).removeClass("error");
		  var errorMessageVisible = $("#dob-error").is(":visible");
		     if (errorMessageVisible)
		        $(".dob-error").remove();*/
		     
		     
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

		}else{
			hideError($("#dob"));
		} 
	} else {
		//swal("please provide your date of birth");
	//	$( "#dob" ).addClass("error");
		//$( "#dob" ).after( "<p class='dob-error error' id='dob-error'><b><br>Please provide your Date of Birth<br></b></p>" );
		  hideError($( "#dob" ));
		 showError($( "#dob" ),"Age " + age + " is restrict");
	}
}

function getDOJ() {
    const dateString = $("#dob").val();

    if (!dateString) {
        hideError($("#dob"));
        showError($("#dob"), "Please provide  date of birth");
        return;
    }

    const birthDate = new Date(dateString);
    const serviceJodString = $("#serviceJod").val();

    if (!serviceJodString) {
        hideError($("#serviceJod"));
        showError($("#serviceJod"), "Please provide  joining date");
        return;
    }

    const serviceJod = new Date(serviceJodString);

    let age = serviceJod.getFullYear() - birthDate.getFullYear();
    const monthDifference = serviceJod.getMonth() - birthDate.getMonth();
    const dayDifference = serviceJod.getDate() - birthDate.getDate();

    if (monthDifference < 0 || (monthDifference === 0 && dayDifference < 0)) {
        age--;
    }

    if (age < 18 || age > 100) {
        hideError($("#serviceJod"));
        showError($("#serviceJod"), "Please Enter valid Joining Date");
    }else{
    	if($("#serviceJod-error").length){
    		$("#serviceJod-error").hide();
    	}
    	
    	hideError($("#serviceJod"));
    } 
}

function ValidateEmail(mail) {
	// alert("Method validate Email");
	if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
			.test(myForm.email.value)) {
		validateEmailUniqe();
		return (true)
	}
	document.getElementById("email").value = "";
	swal("You have entered an invalid email address!")
	return (false)
}

function ValidatePancard() {
	var panVal = $('#panNo').val();
	var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;

	if (regpan.test(panVal)) {
		// valid pan card number
		validatePanUniqe();
		return (true)
	} else {
		// invalid pan card number
		document.getElementById("panNo").value = "";
		swal("You have entered an invalid pan card number!")
		return (false)

	}
}

function Validatealphanumaric(textBox) {
	// var panVal = $('#panNo').val();
	var panVal = textBox.value;
	// var regpan = /^[a-z0-9]+$/i;
	var regpan = /^[a-zA-Z0-9,.!? ]*$/;

	if (regpan.test(panVal)) {
		// valid pan card number
		return (true)
	} else {
		// invalid pan card number
		textBox.value = "";
		swal("You have entered an invalid value!")
		return (false)

	}
}

function phonenumber(inputtxt) {
	var phoneno = /^\d{10}$/;
	if (inputtxt.value.match(phoneno)) {
		validateMobileUniqe();
		return true;
	} else {
		inputtxt.value = "";
		//swal("Mobile number field accept only 10 digits!");
		showError(inputtxt,"Mobile number field accept only 10 digits!");
		return false;
	}
}

function phonelandlinenumber(inputtxt) {
	var phoneno = /^\d{11}$/;
	if (inputtxt.value.match(phoneno)) {
		validateTelePhoneUniqe();
		return true;
	} else {
		inputtxt.value = "";
		swal("Telephone number field accept only 11 digits!");
		return false;
	}
}

function uidvalidenumber(inputtxt) {
	var uid = /^\d{4}$/;
	if (inputtxt.value.match(uid)) {
		return true;
	} else {
		inputtxt.value = "";
		swal("UID number field accept only 12 digits!");
		return false;
	}
}

function isuidEmpty() {
	
	
	var UID1 = document.getElementById("uid1").value;
	var UID2 = document.getElementById("uid2").value;
	var UID3 = document.getElementById("uid3").value;
	if (UID1 == "" &&  UID2 == "" &&  UID3 == "") {
		document.getElementById('eidNo').readOnly = false;

	} 
}

function iseidEmpty() {
	
	
	var EIDNO = document.getElementById("eidNo").value;
	
	if (EIDNO == "") {
		document.getElementById('uid1').readOnly = false;
		document.getElementById('uid2').readOnly = false;
		document.getElementById('uid3').readOnly = false;

	} else {
		document.getElementById('uid1').readOnly = true;
		document.getElementById('uid2').readOnly = true;
		document.getElementById('uid3').readOnly = true;
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

function pincodevalide(inputtxt) {
	var uid = /^\d{6}$/;
	if (inputtxt.value.match(uid)) {
		return true;
	} else {
		inputtxt.value = "";
		swal("Pin Code number field accept only 6 digits!");
		return false;
	}
}
function isNumberKey(evt){ 
    var charCode = (evt.which) ? evt.which : event.keyCode 
    if (charCode > 31 && (charCode < 48 || charCode > 57)) 
        return false; 
    return true; 
} 


// for fetching city associates with states
$("#stateCode")
		.change(
				function() {

					var stateId = $("#stateCode").val();
				//	 alert("stateId CODE is "+stateId);
					if (stateId != '') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/fetchDistricts/" + stateId,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									beforeSend : function(){
										$( "#loaderMainNew").show();
										},
									complete : function(data){
										$( "#loaderMainNew").hide();
									},	
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											$('#districtCode').empty();
											$('#districtCode')
													.append(
															"<option value='0'>Please Select</option>");
											var temp = data;
											$
													.each(
															temp,
															function(index,
																	value) {
//																console
//																		.log(value[2]);
																$(
																		'#districtCode')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#districtCode').empty();
											$('#districtCode')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
					}

				});




/*//for fetching Sub Corporation associates with Corporation
$("#parentFieldDepartmentId")
		.change(
				function() {

					var parentFieldDepartmentId = $("#parentFieldDepartmentId").val();
					// alert("stateId CODE is "+stateId);
					if (parentFieldDepartmentId != '') {
						$
								.ajax({
									type : "GET",
									url : "fetchSubCorporation/" + parentFieldDepartmentId,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									beforeSend : function(){
										$( "#loaderMainNew").show();
										},
									complete : function(data){
										$( "#loaderMainNew").hide();
									},	
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											$('#subCorporationId').empty();
											$('#subCorporationId')
													.append(
															"<option value='0'>Please Select</option>");
											var temp = data;
											$
													.each(
															temp,
															function(index,
																	value) {
																console
																		.log(value[0]);
																$(
																		'#subCorporationId')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#subCorporationId').empty();
											$('#subCorporationId')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
					}

				});*/


function approveEmpDtls() {
	var dateString = document.getElementById("dob").value;
	var ddocodeString = document.getElementById("ddoCode").value;
	var location = document.getElementById("deptNm").value;
	var subdept = $("#parentFieldDepartmentId option:selected").text();
	var birthDate = new Date(dateString);
	var year = birthDate.getFullYear();
	year = year.toString().substr(-2);
	ddocodeString = ddocodeString.substring(0, 2);
	// var fstNm = document.getElementById("fName").value;
	var fstNm = $("#fName").val().toUpperCase();
	var mNm = $("#mName").val().toUpperCase();
	var lNm = $("#lName").val().toUpperCase();
	// var mNm = document.getElementById("mName").value;
	// var lNm = document.getElementById("lName").value;
	var name = fstNm.charAt(0) + mNm.charAt(0) + lNm.charAt(0);
	var index = document.myForm.gender.selectedIndex;
	var genderValue =$("#gender").val();
	genderValue = genderValue.substring(0, 1);
	var subDepartment =  subdept.substring(0, 3);
	var uniqid = document.getElementById("employeeId").value;
	//var sevaarthid = ddocodeString + location + name + genderValue + year;
	
	
	
	if($("#sevaarthId").val()=='' || $("#sevaarthId").val()==undefined){
		getSevaarthId();
	}
	
	var sevaarthid= document.getElementById("sevaarthId").value;
	
	
	
	/*if(sevaarthid=="0")
		sevaarthid = subDepartment + name + genderValue + year;*/
		var mob=$("#fName").val();
		var email=$("#fName").val();
		
	var dcpsgpfflg=$('input[name="dcpsgpfflag"]:checked').val();

	if (uniqid != '') {
		$.ajax({
			type : "GET",
			url : contextPath+"/level3/approveEmpDtls/" + uniqid + "/" + sevaarthid + "/" + dcpsgpfflg,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				// console.log(data);
			},
			beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
			success : function(data) {
				var sevaarthIdres = data;
				// alert(checkFlag);
				swal({ 
					 text: "Approved Successfully Sevaarthid is generated "+ sevaarthid,
					 type: "success"}).then(okay => {
					   if (okay) {
						   document.getElementById("myForm").submit();
					  }
					});
//				swal('Approved Successfully Sevaarthid is genrated '
//						+ sevaarthIdres);
//				document.getElementById("myForm").submit();
				return status;

			}
		});
	}
}



$("#forwardDcpsEmpToLvl3").click(function(){
	forwardDcpsEmpToLvl3();
});

function forwardDcpsEmpToLvl3(){
	var empid = $("#employeeId").val();
	
	if (empid != '') {
			swal({
				  title: "Are you sure?",
				  text: "Approve and Forward To Level 3",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				}).then((willDelete) => {
				    if (willDelete) {
						$.ajax({
										type : "GET",
										url : contextPath+ddo+"/forwardDcpsEmpToLvl3/" + empid,
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
											swal({ 
												 text: "Employee Forwarded to Level 3 Successfully",
												 type: "success"}).then(okay => {
												   if (okay) {
													   document.getElementById("myForm").submit();
												  }
												});
											return status;
										}
									});
				     }
			})
		}
}


function rejectEmpDtls() {
	var empid = document.getElementById("employeeId").value;
	if (empid != '') {
		$.ajax({
			type : "GET",
			url : "rejectEmpDtls/" + empid,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				// console.log(data);
			},
			beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
			success : function(data) {
				var checkFlag = data;

				if (checkFlag == 0) {

				} else if (checkFlag > 0) {
					swal('Rejected Successfully');
					swal({ 
						 text: "Rejected Successfully",
						 type: "success"}).then(okay => {
						   if (okay) {
							   document.getElementById("myForm").submit();
						  }
						});
				}
//				document.getElementById("myForm").submit();
				return status;

			}
		});
	}
}

function backEmpDtls() {
 document.getElementById("myForm").submit();
}

function backlvlEmpDtls(){
	 $('#action').val('EditBack');
	document.getElementById("myForm").submit();
}


$("#gisgroup").change(function() {
	//  swal("Record not found !!!");
});

// Bank dropdown start
$("#bankId")
		.change(
				function() {
					var bankid = $("#bankId").val();
					// alert("DDO CODE is "+departmentId);
					//  alert("payScale CODE is "+payScale);

					if (bankid != ''  && bankid != '0') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/fetchbankbranch/" + bankid,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									beforeSend : function(){
										$( "#loaderMainNew").show();
										},
									complete : function(data){
										$( "#loaderMainNew").hide();
									},	
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											// console.log(data);
											$('#bankBranchId').empty();
											$('#bankBranchId')
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
																		'#bankBranchId')
																		.append(
																				"<option value="
																						+ value[0]
																						+ ">"
																						+ value[1]
																						+ "</option>");
															});
										} else {
											$('#bankBranchId').empty();
											$('#bankBranchId')
													.append(
															"<option value='0'>Please Select</option>");
											swal("Record not found !!!");
										}
									}
								});
					}

				});
// bank branch dropdown end



//start accountmaintainby
$("#accountmaintainby").change(function(){
	if($("#accountmaintainby").val()=="3")
	 $("#pfacno").prop("disabled",false);
	if($("#accountmaintainby").val()=="5")
		{
		$("#pfacno").prop("disabled",false);
		
		}
});

//end accountmaintainby




function checkDcpcGpf() {
	// alert("Method dcps");
	// var index = document.myForm.gender.selectedIndex ;
	// var genderValue = document.myForm.gender[index].text ;
	var dcpsarr = document.myForm.dcps;

	var dcpsValue;
	for (var i = 0; i < dcpsarr.length; i++) {
		if (dcpsarr[i].checked == true) {
			dcpsValue = dcpsarr[i].value;
		}
	}

	// alert("dcpsValue="+dcpsValue);

	if (dcpsValue == 'Y') {
		// alert("true method");
		document.getElementById('dcpsaccountmaintainby').disabled = '';
		document.getElementById('accountmaintainby').disabled = 'true';
		document.getElementById('pfseries').disabled = 'true';
	    document.getElementById('pfacno').disabled = 'false';
		document.getElementById('pfdescription').disabled = 'true';
		document.getElementById("accountmaintainby").value = "0";
		document.getElementById("pfseries").value = "0";
		///document.getElementById("pfdescription").value = "";
		document.getElementById("nomineename").disabled = '';
		document.getElementById("nomineeaddress").disabled = '';
		document.getElementById("rdob").disabled = '';
		document.getElementById("relation").disabled = '';
		document.getElementById("percent_share").disabled = '';
		document.getElementById("pranNo").disabled = '';
		$("#dcpsDiv").show();

	} else {
		document.getElementById('dcpsaccountmaintainby').disabled = 'true';
		document.getElementById('accountmaintainby').disabled = '';
		document.getElementById('pfseries').disabled = '';
		document.getElementById('pfacno').disabled = 'true';
	///	document.getElementById('pfdescription').disabled = 'true';
		document.getElementById("dcpsaccountmaintainby").value = "0";
		///document.getElementById("pfdescription").value = "";
		document.getElementById("nomineename").disabled = 'true';
		document.getElementById("nomineeaddress").disabled = 'true';
		document.getElementById("rdob").disabled = 'true';
		document.getElementById("relation").disabled = 'true';
		document.getElementById("percent_share").disabled = 'true';
		document.getElementById("pranNo").disabled = 'true';
		
		$("#dcpsDiv").hide();

	}

	// if(dcpsValue == 'No')
	// {
	// document.getElementById('fatherName').readOnly = true;
	// }else{
	// document.getElementById('fatherName').readOnly = false;
	// }

	return true;
}

function readURL1image(input) {
	if (input.files && input.files[0]) {
		var img = input.files[0];
		var size = img.size;
		// alert("size bytes="+img.size);
		var filekbsize = Math.round((size / 1024));

		// alert("fileroundvalue="+fileroundvalue);

		if (filekbsize >= 300) {
			input.value = '';
			swal("File too Big, please select a file less than 300kb");
			return false;
		}
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePath').attr('src', e.target.result);
			$('#imagePath').show();
		}

		reader.readAsDataURL(input.files[0]);
	}
}

// $("#photo").change(function(){
// readURL1(this);
// });

function getimage(file) {
	// alert('working')

	readURL1image(file);
}

function readURL1imagesign(input) {
	if (input.files && input.files[0]) {
		var img = input.files[0];
		var size = img.size;
		// alert("size bytes="+img.size);
		var filekbsize = Math.round((size / 1024));

		// alert("fileroundvalue="+fileroundvalue);
		if (filekbsize >= 300) {
			// document.getElementById('your_input_id').value= null;;
			// $($("#files")[1]).val("");
			input.value = '';
			// input.replaceWith( input = input.clone( true ) );
			swal("File too Big, please select a file less than 300kb");
			return false;
		}
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePathSign').attr('src', e.target.result);
			$('#imagePathSign').show();
		}

		reader.readAsDataURL(input.files[0]);
	}
}

// $("#photo").change(function(){
// readURL1(this);
// });

function getimagesign(file) {
	// alert('working')
	readURL1imagesign(file);
}

function checkGisGroup() {
	var index = document.myForm.gisgroup.selectedIndex;
	var gisgroup = document.myForm.gisgroup[index].text;
	//swal("Are you sure the GIS group is like  '" + gisgroup + "'");
}

function checkMembershipDate() {
	var dateString = document.getElementById("membership_date").value;
	if (dateString) {
		var today = new Date();
		var membershipdate = new Date(dateString);
		var date = membershipdate.getDate();
		var month = membershipdate.getMonth();
		if (date != 1 || month != 3) {
			swal("Mebmership date must be 1st of  April");
			return false;
		}
		return true;
	}
}

function ValidateNomineeShare() {
	var percentshare = document.getElementById("percent_share").value;
	if (percentshare > 100) {
		document.getElementById("percent_share").value = "";
		swal("Nominee Details should be equal to 100");
		return false;
	}
}

// Cadre dropdown start
$("#cadre")
		.change(
				function() {
					var cadreid = $("#cadre").val();
					// alert("DDO CODE is "+departmentId);
					//  alert("cadreid"+cadreid);

					var dob = $("#dob").val();
					if (dob == "") {
						$("#cadre").val('0');
						swal("Date of Birth cannot be empty");
						return false;
					}

					if (cadreid != '') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/fetchcadregroupdtls/" + cadreid,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									beforeSend : function(){
										$( "#loaderMainNew").show();
										},
									complete : function(data){
										$( "#loaderMainNew").hide();
									},	
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											var temp = data;
											$
													.each(
															temp,
															function(index,
																	value) {
															
																
																$("#empClass").val(value[0]);
																
																
																
																$(
																		"#superannuationage")
																		.val(
																				value[1]);
																var dateString = document
																		.getElementById("dob").value;
																var birthDate = new Date(
																		dateString);
																var lastDay = new Date(
																		birthDate
																				.getFullYear()
																				+ value[1],
																		birthDate
																				.getMonth() + 1,
																		0);
																var day = ("0" + lastDay
																		.getDate())
																		.slice(-2);
																var month = ("0" + (lastDay
																		.getMonth() + 1))
																		.slice(-2);
																var lastDayWithSlashes = lastDay
																		.getFullYear()
																		+ "-"
																		+ (month)
																		+ "-"
																		+ (day);
																// var
																// lastDayWithSlashes
																// =
																// (day)+"-"+(month)+"-"+lastDay.getFullYear()
																// ;
																// alert(lastDayWithSlashes);
																//$('#superAnnDate').val(lastDayWithSlashes);
															});
										} else {
											swal("Record not found !!!");
										}
									}
								});
					}
					
					if (cadreid != '') {
						$
								.ajax({
									type : "GET",
									url : contextPath+"/ddoast/fetchcadregroupdtlsDate/" + cadreid + "/" + dob,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										// console.log(data);
									},
									beforeSend : function(){
										$( "#loaderMainNew").show();
										},
									complete : function(data){
										$( "#loaderMainNew").hide();
									},	
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											var temp = data;
											var date=data[0].superAnnDate;
											$('#superAnnDate').val(dateToYMD(date));
											
										} else {
											swal("Record not found !!!");
										}
									}
								});
					}

				});
// Cadre dropdown end

/* Remark Hide show */

$(document).ready(function() {
	$(".remarkPanel").hide();
	$(".rejectbtnn").click(function() {
		$(".remarkPanel").show();
	});
});

var globalTotalShare = 0;
$("#add").click(function(e) {
	e.preventDefault();
	addRow();
	// var nomineeName = $("#nomineename").val();
	// var nomineeAddress = $("#nomineeaddress").val();
	// var rdob = $("#rdob").val();
	// var relation =$("#relation option:selected").text();
	// var percentShare = $("#percent_share").val();
	// globalTotalShare=globalTotalShare+percentShare;
	// if(nomineeName==""){
	// swal("Please enter nominee name");
	// }else if(nomineeAddress==""){
	// swal("Please enter nominee address");
	// }
	// else if(rdob==""){
	// swal("Please enter nominee date of birth");
	// }
	// else if(percentShare==""){
	// swal("Please enter percent share");
	// }
	// else if(parseInt(globalTotalShare)>100){
	// swal("Please enter less than 100 percent share");
	// }
	// else{
	// $('#tblShowPayBill').show();
	// $('#tblShowPayBill_wrapper').show();
	// $('#tblShowPayBill tbody').html('');
	// //$("#tblShowPayBill").dataTable().fnClearTable();
	// $("#tblShowPayBill").dataTable().fnAddData([nomineeName,nomineeAddress,rdob,relation,percentShare
	// ]);
	// }
});

var serialNo = 1;
var rowCount = 0;
var addedOrNotFlag = true;
var tbody;
var SaveOrUpdateNominee = 0; // 1 for SAVE Nominee and for all other
// positive values, it is UPDATE
// Nominee.
var totalNomineeShareAdded = 0;
var motherAdded = false;
var fatherAdded = false;
// var spouseAdded = false ;
var HusbandAdded = false;
var WifeAdded = false;
var SonAdded = false;
var DaughterAdded = false;
var OtherAdded = false;
var BrotherAdded = false;

function chkEmpty(ctrl, msg) {
	var str = ctrl.value;
	if (str == "" || str == "0") {
		swal(msg + " Cannot be Empty.");
		ctrl.focus();
		return false;
	} else
		return true;
}

function validateNomineeData() {
	if (!chkEmpty(document.getElementById('nomineename'), 'Nominee Name')
			|| !chkEmpty(document.getElementById('nomineeaddress'),
					'Nominee Address')
			|| !chkEmpty(document.getElementById('rdob'),
					'Nominee Date of Birth')
			|| !chkEmpty(document.getElementById('relation'),
					'Nominee Relationship')
			|| !chkEmpty(document.getElementById('percent_share'),
					'Percent Share')) {
		return false;
	}

	if (document.getElementById("percent_share").value > 100) {
		alert('Percentage Share cannot be more than 100');
		addedOrNotFlag = false;
		return false;
	}

	return true;

}

function addRow() {
	if (document.getElementById("relation").value == 'Mother') {
		if (motherAdded) {
			swal('Nominee as Mother is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Father') {
		if (fatherAdded) {
			swal('Nominee as Father is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Husband') {
		if (HusbandAdded) {
			swal('Nominee as Husband is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Wife') {
		if (WifeAdded) {
			swal('Nominee as Wife is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Son') {
		if (SonAdded) {
			swal('Nominee as Son is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Daughter') {
		if (DaughterAdded) {
			swal('Nominee as Daughter is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Other') {
		if (OtherAdded) {
			swal('Nominee as Other is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Brother') {
		if (BrotherAdded) {
			swal('Nominee as Brother is already added.');
			return false;
		}
	}
	if (!validateNomineeData()) {
		return false;
	}

	if (totalNomineeShareAdded
			+ Number(document.getElementById("percent_share").value) > 100) {
		swal('Total Nominee share exceeds 100.Please enter correct nominee share.');
		return false;
	}

	totalNomineeShareAdded = totalNomineeShareAdded
			+ Number(document.getElementById("percent_share").value);

	tbody = document.getElementById('displayTableForNomineeDtls')
			.getElementsByTagName('tbody')[0];
	var row = document.createElement('TR');

	rowCount++;

	var className = serialNo;

	var cell1 = document.createElement('TD');
	cell1.align = "center";

	var cell2 = document.createElement('TD');

	var cell3 = document.createElement('TD');
	var cell4 = document.createElement('TD');
	var cell5 = document.createElement('TD');

	cell1.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" id=\"txtNomineeSerialNoValue\" name=\"txtNomineeSerialNoValue\" class='"
			+ className + "' value='" + serialNo + "' readonly=\"readonly\" />";
	cell2.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" id=\"txtNameValue\" name=\"txtNameValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("nomineename").value.toUpperCase()
			+ "' readonly=\"readonly\" /> <input type='hidden' id=\"txtNomAddr1\" name=\"txtNomAddr1\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("nomineeaddress").value.toUpperCase()
			+ "' />";
	cell3.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" id=\"txtDateOfBirthValue\" name=\"txtDateOfBirthValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("rdob").value
			+ "' readonly=\"readonly\"  />";
	cell4.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" id=\"txtPercentShareValue\" name=\"txtPercentShareValue\"  id=\"txtPercentShareValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("percent_share").value
			+ "' readonly=\"readonly\"  />";
	cell5.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" id=\"txtRelationshipValue\" name=\"txtRelationshipValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("relation").value
			+ "'  readonly=\"readonly\" />";
	cell1.align = "center";
	cell1.className = "tds";
	cell2.align = "center";
	cell2.className = "tds";
	cell3.align = "center";
	cell3.className = "tds";
	cell4.align = "center";
	cell4.className = "tds";
	cell5.align = "center";
	cell5.className = "tds";

	var hiddenCell0 = document.createElement('TD');
	hiddenCell0.innerHTML = "<input type='hidden' name=\"txtNomSerialNo\" class='"
			+ className + "' value='" + serialNo + "' />";
	var hiddenCell1 = document.createElement('TD');
	hiddenCell1.innerHTML = "<input type='hidden' name=\"txtNomName\" class='"
			+ className + "' value='"
			+ document.getElementById("nomineename").value.toUpperCase()
			+ "' />";
	var hiddenCell4 = document.createElement('TD');
	hiddenCell4.innerHTML = "<input type='hidden' name=\"txtNomAddr1\" class='"
			+ className + "' value='"
			+ document.getElementById("nomineeaddress").value.toUpperCase()
			+ "' />";
	var hiddenCell6 = document.createElement('TD');
	hiddenCell6.innerHTML = "<input type='hidden' name=\"txtNomDOB\" class='"
			+ className + "' value='" + document.getElementById("rdob").value
			+ "' />";
	var hiddenCell7 = document.createElement('TD');
	hiddenCell7.innerHTML = "<input type='hidden' name=\"txtNomPerShare\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("percent_share").value + "' />";
	var hiddenCell8 = document.createElement('TD');
	hiddenCell8.innerHTML = "<input type='hidden' name=\"txtNomRelationship\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("relation").value + "' />";

	var cell7 = document.createElement('TD');
	cell7.innerHTML = "<a data-toggle='tooltip'  class='glyphicon glyphicon-trash' onclick=\"deleteRow()\" />";
	cell7.align = "center";
	cell7.className = "tds";

	var cell8 = document.createElement('TD');
	cell8.innerHTML = "<input type=\"hidden\" value='" + rowCount + "'/>";

	if (document.getElementById("relation").value == 'Mother') {
		if (motherAdded) {
			swal('Nominee as Mother is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Father') {
		if (fatherAdded) {
			swal('Nominee as Father is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Husband') {
		if (HusbandAdded) {
			swal('Nominee as Husband is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Wife') {
		if (WifeAdded) {
			swal('Nominee as Wife is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Son') {
		if (SonAdded) {
			swal('Nominee as Son is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Daughter') {
		if (DaughterAdded) {
			swal('Nominee as Daughter is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Other') {
		if (OtherAdded) {
			swal('Nominee as Other is already added.');
			return true;
		}
	}
	if (document.getElementById("relation").value == 'Brother') {
		if (BrotherAdded) {
			swal('Nominee as Brother is already added.');
			return trie;
		}
	}

//	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	row.appendChild(cell7);
	// row.appendChild(cell8);

	// row.appendChild(hiddenCell0);
	// row.appendChild(hiddenCell1);
	// row.appendChild(hiddenCell4);
	// row.appendChild(hiddenCell6);
	// row.appendChild(hiddenCell7);
	// row.appendChild(hiddenCell8);

	tbody.appendChild(row);
	serialNo++;

	addedOrNotFlag = true;

	resetFields();
	return true;

}

function deleteRow() {
	
	if($("#displayTableForNomineeDtls>tbody>tr").length==2){

		var current = window.event.srcElement;
		var TD = current.parentElement;
		var TR = TD.parentElement;
		var lArrAllTds = TR.childNodes;

		while ((current = current.parentElement) && current.tagName != "TR");
		current.parentElement.removeChild(current);
	}else{
		

	serialNo = serialNo - 1;
	var current = window.event.srcElement;
	var TD = current.parentElement;
	var TR = TD.parentElement;
	var lArrAllTds = TR.childNodes;

	var currentShare = lArrAllTds[3].childNodes[0].value;
	totalNomineeShareAdded = totalNomineeShareAdded - Number(currentShare);
	while ((current = current.parentElement) && current.tagName != "TR")
		;
	current.parentElement.removeChild(current);

	var relationToBeRemoved = lArrAllTds[4].childNodes[0].value;

	if (document.getElementById("relation").value == 'Mother') {
		if (motherAdded) {
			swal('Nominee as Mother is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Father') {
		if (fatherAdded) {
			swal('Nominee as Father is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Husband') {
		if (HusbandAdded) {
			swal('Nominee as Husband is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Wife') {
		if (WifeAdded) {
			swal('Nominee as Wife is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Son') {
		if (SonAdded) {
			swal('Nominee as Son is already added.');
			
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Daughter') {
		if (DaughterAdded) {
			swal('Nominee as Daughter is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Other') {
		if (OtherAdded) {
			swal('Nominee as Other is already added.');
			return false;
		}
	}
	if (document.getElementById("relation").value == 'Brother') {
		if (BrotherAdded) {
			swal('Nominee as Brother is already added.');
			return false;
		}
	}
	}
	
	
	
}

function resetFields() {
	if (addedOrNotFlag) {
		document.getElementById("nomineename").value = "";
		document.getElementById("rdob").value = "";
		document.getElementById("percent_share").value = "";
		document.getElementById("relation").value = "0";
		document.getElementById("nomineeaddress").value = "";
	}
}
//03 Dec 2020:Dcps Creation started
function approveDcpsEmpDtls() {
	var ddocodeString = document.getElementById("ddoCode").value;
	
	//Sevaarth Id Creation start
//	getSevaarthId();
	var sevaarthid=document.getElementById("sevaarthId").value;
	//Sevaarth Id Creation End
	
	var empid = document.getElementById("employeeId").value;
//	alert("ddocodeString="+ddocodeString);
//	alert("sevaarthold="+sevaarthold);
//	var sevarthtemp='15VJNTAEKM9001';
//	alert('sevarthtemp='+sevarthtemp);
//	sevarthtemp=sevarthtemp.toString().substr(-8);
//	alert('after -8 sub='+sevarthtemp);
//	alert("sevaarthold-8="+sevaarthold);
	// var mNm = document.getElementById("mName").value;
	// var lNm = document.getElementById("lName").value;
	var dcpsnumber = ddocodeString ;
//	var dcpsgpfflg=document.getElementById("dcpsgpfflg").value;
	var dcpsgpfflg='Y';

	if (empid != '') {
		$.ajax({
			type : "GET",
			url : contextPath+"/ddo/approveDcpsEmpDtls/" + empid + "/" + dcpsnumber+"/"+sevaarthid+"/"+dcpsgpfflg,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				// console.log(data);
			},
			beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
			success : function(data) {
				var dcpsid = data;
//				 alert(dcpsid);
//				 alert(dcpsid[0]);
				
				swal({ 
					 text: "DCPS ID ("+dcpsid[0]+") and Sevaarth ID ("+dcpsid[1]+") Registered Successfully",
					 type: "success"}).then(okay => {
					   if (okay) {
						   document.getElementById("myForm").submit();
					  }
					});
//				swal('DCPS ID '+dcpsid+' Registered Successfully');
//				document.getElementById("myForm").submit();
				return status;

			}
		});
	}
}

//03 Dec 2020:Dcps Creation Ended


function getFisrtName(){
	var fname=$("#fName").val();
	 $("#photodesc").val(fname.toUpperCase()+"/PHOTO");
	 $("#signaturedesc").val(fname.toUpperCase()+"/SIGNATURE");
}
function getPfseriesPfacc(){
	var pfseries = $('#pfseries option:selected').text();
	var pfacno=$("#pfacno").val();
//	alert("pfseries="+pfseries);
//	alert("pfacno="+pfacno);
//	alert("pfseries1="+pfseries1);
	if(pfseries!=''&&pfseries!=null&&pfacno!=''&&pfacno!=null){
		 $("#pfdescription").val(pfseries+"-"+pfacno);
	}
}

function openPerformaA(){
	var urlToOpen = '/MJP/images/Proforma A.pdf';
	var prop = 'width='+screen.availWidth-100+',height='+screen.availHeight-100+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	window.open(urlToOpen, "_blank", prop);
}

function openPerformaB(){
	var urlToOpen = '/MJP/images/Proforma B.pdf';
	var prop = 'width='+screen.availWidth-100+',height='+screen.availHeight-100+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	window.open(urlToOpen,  "_blank", prop);
}

function empDtlsReport() {
//	alert("Welcome to Employee Details Report");
	var employeeId = document.getElementById("employeeId").value;
	var urlToOpen = 'empDtlsReport/'+employeeId;
	var prop = 'width='+screen.availWidth-100+',height='+screen.availHeight-100+',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	window.open(urlToOpen,  "_blank", prop);
}

function getSevaarthId(){
	
	
	if($("#sevaarthId").val()==''){
		var dateString = document.getElementById("dob").value;
		var ddocodeString = $("#logUser").text().trim(); //document.getElementById("ddoCode").value;
		var location = document.getElementById("deptNm").value;
		var birthDate = new Date(dateString);
		var year = birthDate.getFullYear();
		year = year.toString().substr(-2);
		ddocodeString = "MJP";//ddocodeString.substring(0, 2);
		// var fstNm = document.getElementById("fName").value;
		var fstNm = $("#fName").val().toUpperCase();
		var mNm = $("#mName").val().toUpperCase();
		var lNm = $("#lName").val().toUpperCase();
		// var mNm = document.getElementById("mName").value;
		// var lNm = document.getElementById("lName").value;
		var name = fstNm.charAt(0) + mNm.charAt(0) + lNm.charAt(0);
		var index ="01";
		var genderValue = $("#gender").val();
		if(genderValue=="1")
			genderValue="M";
		else if(genderValue=="2")
			genderValue="F";
		else 
			genderValue="T";
		var empid = document.getElementById("employeeId").value;
		var sevaarthid = ddocodeString + name + genderValue + year + index;
		$("#sevaarthId").val(sevaarthid);
	}
	
}




//Payscal and basic and grade pay list dropdown start
//for fetching payscale associates with paycommission
/*$("#payCommision").change(function() {
					var payCommisionId = $("#payCommision").val();
					if (payCommisionId != '') {
						if (payCommisionId == '2') {
							$('#payscalelevel').attr("disabled", true); 
							$('#svnthpaybasic').attr("disabled", true); 
							$.ajax({
										type : "GET",
										url : "fetchPayscale/" + payCommisionId,
										async : true,
										contentType : 'application/json',
										error : function(data) {
										},
										success : function(data) {
											var len = data.length;
											if (len != 0) {
												document.getElementById('payInPayBand').readOnly = true;
												$('#payScaleSeven').empty();
												$('#payScaleSeven')
														.append("<option value='0'>Please Select</option>");
												var temp = data;
												$.each(temp,function(index,value) {
																	console.log(value[2]);
																	
																	$('#payScaleSeven').append("<option value="+ value[4]+ ">"	+ value[3]+ "</option>");
																	});
											} else {
												$('#payScaleSeven').empty();
												$('#payScaleSeven')
														.append(
																"<option value='0'>Please Select</option>");
												swal("Record not found !!!");
											}
										}
									});
						} else {
							$('#payScaleSeven').empty();
							$('#payScaleSeven').append(
									"<option value='0'>Please Select</option>");
							swal("Record not found !!!");
							$('#payInPayBand').attr("disabled", true); 
							$('#basicPay').attr("disabled", false); 
						}
					}

				});*/

$("#payScaleSeven").change(function() {
	var payScaleSeven = $("#payScaleSeven").val();
	if (payScaleSeven != '') {
		
		$('#payInPayBand').attr("disabled", false); 
		$('#basicPay').attr("disabled", false); 
			$.ajax({
						type : "GET",
						url : contextPath+"/ddoast/fetchpayScaleSeven/" + payScaleSeven,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							var len = data.length;
							if (len != 0) {
								document.getElementById('payInPayBand').readOnly = false;
								document.getElementById('basicPay').readOnly = false;
								var temp = data;
								$.each(temp,function(index,value) {
													console.log(value[2]);
													console.log(value[1]);
													 //$("#payInPayBand").val(value[1]);
													 $("#gradePay").val(value[2]);
//													$('#payInPayBand').append("<option value="+ value[4]+ ">"	+ value[1]+ "</option>");
//													$('#gradePay').append("<option value="+ value[4]+ ">"	+ value[2]+ "</option>");
												});
							} else {
								//$('#payScaleSeven').empty();
								$('#payScaleSeven')
										.append(
												"<option value='0'>Please Select</option>");
								swal("Record not found !!!");
							}
						}
					});
		} 
	
	

});

// added By Aks
$("#postdetailid")
.change(
		function() {
			var postdetailid = $("#postdetailid").val();
			//alert("postdetailid"+postdetailid);
			if (postdetailid != '' && postdetailid != '0') {
				$
						.ajax({
							type : "GET",
							url : contextPath+"/ddoast/employeeConfigurationGetCurrenOffice/"
									+ postdetailid,
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
								// console.log(data);
								// alert(data);
								var len = data.length;
								if (len != 0) {
									// console.log(data);
									$('#adminDepartmentId').empty();
//									$('#adminDepartmentId')
//											.append(
//													"<option value='0'>Please Select</option>");
									var temp = data;
									$
											.each(
													temp,
													function(index,
															value) {
//														console
//																.log(value[2]);
														$(
																'#adminDepartmentId')
																.append(
																		"<option value="
																				+ value[0]
																				+ ">"
																				+ value[1]
																				+ "</option>");
														 $("#instituteAdd").val(value[2]);
														 $("#mobileNo2").val(value[3]);
														 $("#instEmailId").val(value[6]);
														 $("#cityClass").val(value[8]);
													});
								} else {
									$('#adminDepartmentId').empty();
									$('#adminDepartmentId')
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}

		});
//end designation

$("#adminDepartmentId")
.change(
		function() {
			var adminDepartmentId = $("#adminDepartmentId").val();
	//		alert("adminDepartmentId"+adminDepartmentId);
			if (adminDepartmentId != '' && adminDepartmentId!="0") {
				$
						.ajax({
							type : "GET",
							url : "employeeConfigurationGetCurrenOfficeAddress/"
									+ adminDepartmentId,
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
								// console.log(data);
								// alert(data);
								var len = data.length;
								if (len != 0) {
									// console.log(data);
									$('#adminDepartmentId').empty();
//									$('#adminDepartmentId')
//											.append(
//													"<option value='0'>Please Select</option>");
									var temp = data;
									$
											.each(
													temp,
													function(index,
															value) {
//														console
//																.log(value[2]);
														 $("#instituteAdd").val(value[0]);
														 $("#mobileNo2").val(value[1]);
														 $("#instEmailId").val(value[4])
													});
								} else {
									$('#adminDepartmentId').empty();
									$('#adminDepartmentId')
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}

		});

// ended By Aks

var today1 = new Date();
var dd1 = today1.getDate();
var mm1 = today1.getMonth() + 1; //January is 0!
var yyyy1 = today1.getFullYear();

if (dd1 < 10) {
   dd1 = '0' + dd1;
}

if (mm1 < 10) {
   mm1 = '0' + mm1;
}
   
today1 = yyyy1 + '-' + mm1 + '-' + dd1;

if (document.getElementById("serviceJod")) {
	document.getElementById("serviceJod").setAttribute("max", today1);
}



$("#dob").change(function() {

	var today1 = new Date($(this).val());
	var dd1 = today1.getDate();
	var mm1 = today1.getMonth() + 1; //January is 0!
	var yyyy1 = today1.getFullYear();

	if (dd1 < 10) {
	   dd1 = '0' + dd1;
	}

	if (mm1 < 10) {
	   mm1 = '0' + mm1;
	}
	   
	today1 = yyyy1 + '-' + mm1 + '-' + dd1;
	
	document.getElementById("serviceJod").setAttribute("min", today1);

});


$('input[type="checkbox"][name="sameOrNot"]').click(function() {
	  //  $('#permanent_address').val($('#current_address').val());
	//  $('#presentSamePerm').val($('#presentNotSamePerm').val());
	  
	  if($(this).prop("checked"))
		{
		  var addressStreet = $("#addressBuilding").val();
		   $("#addressStreet").val(addressStreet);
		   $("#addressStreet").prop("readOnly",true);
		}
	  else
		  {
		  $("#addressStreet").prop("readOnly",false);
		  $("#addressStreet").val("");
		  }
	  });


function validateBankAccNumUniqe() {
	// alert('inside validateUIDUniqe');
	var accountNum = document.getElementById("bankAccountNo").value;
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId == "" || employeeId == null) {
		employeeId='0';
	}
		if (bankAccountNo != '') {
			$
					.ajax({
						type : "GET",
						url : contextPath+"/ddoast/validateAccountNum/" + accountNum+"/"+employeeId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#bankAccountNo').val(accountNum);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered Account number: '
										+ accountNum
										+ ' is already present in system. Please enter correct Account number.');

								document.getElementById("bankAccountNo").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}


$("#myForm").on('keypress', function(e) {
    if (e.which === 13) { 
        e.preventDefault(); 
        if ($("#myForm").valid()) { 
            $(this).submit(); 
        }
    }
});




function fetchPayScale(){


	var payCommisionId = $("#payCommision").val();
	// alert("DDO CODE is "+departmentId);
	//   alert("payCommisionId CODE is "+payCommisionId);
	
	$("#payScaleSeven").val("0");
	$("#payInPayBand").val("");
	$("#gradePay").val("");
	$("#basicPay").val("");
	
	
	
	if (payCommisionId != '' && payCommisionId != '0') {
		if (payCommisionId == '700005') {
//			$('#payScaleSeven').attr("disabled", true); 
//			$('#basicPay').attr("disabled", true);
			
			$('#payscalelevel').attr("disabled", false); 
			$('#svnthpaybasic').attr("disabled", false);
			$('#payscalelevel').removeClass("ignore");
			$('#svnthpaybasic').removeClass("ignore");
			$('#basicPay').empty();
			$('#payScaleSeven').addClass("readonlydropdown");
			$("#payScaleSeven").attr("readonly", true);
			$('#payScaleSeven').empty();
			$('#payScaleSeven').addClass("ignore");
			$('#payScaleSeven').removeClass("error");
			$('#payInPayBand').removeClass("error");
			$('#payInPayBand').addClass("ignore");
			$('#payInPayBand').empty();
			$('#gradePay').empty();
			
			$
					.ajax({
						type : "GET",
						url : contextPath+"/ddoast/fetchPayscale/" + payCommisionId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
							// console.log(data);
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							 console.log(data);
							// alert(data);
							var len = data.length;
							if (len != 0) {
								// document.getElementById('payScale').readOnly
								// = true;
								document.getElementById('payInPayBand').readOnly = true;
								document.getElementById('basicPay').readOnly = true;
								
								
								$('#payInPayBand').addClass("ignore");
								$('#basicPay').addClass("ignore");
								

								// console.log(data);
								$('#payscalelevel').empty();
								$('#payscalelevel')
										.append(
												"<option value='0'>Please Select</option>");
								var temp = data;
								$
										.each(
												temp,
												function(index,
														value) {
													
													$('#gradePay').val(value[2]);
													console
															.log(value[2]);
													$(
															'#payscalelevel')
															.append(
																	"<option value="
																			+ value[4]
																			+ ">"
																			+ value[3]
																			+ "</option>");
												});
							} else {
								$('#payscalelevel').empty();
								$('#svnthpaybasic').empty();
								
								$('#payscalelevel')
										.append(
												"<option value='0'>Please Select</option>");
								$('#svnthpaybasic')
								.append(
										"<option value='0'>Please Select</option>");
								
								swal("Record not found !!!");
							}
						}
					});
		} 
		else if (payCommisionId == '700016') {
			$('#payscalelevel').attr("disabled", true); 
			$('#svnthpaybasic').attr("disabled", true); 
			$('#payScaleSeven').attr("disabled", false); 
			$('#payScaleSeven').attr("readonly", false); 
			$('#payscalelevel').addClass("ignore");
			$('#svnthpaybasic').addClass("ignore");
			$('#payscalelevel').removeClass("error");
			$('#svnthpaybasic').removeClass("error");
			$("#payScaleSeven").removeClass("ignore");
			$("#payInPayBand").removeClass("ignore");
			$("#payScaleSeven").removeClass("readonlydropdown");
			$("#payInPayBand").attr("readonly", false); 
			$('#payscalelevel').empty(); 
			$('#svnthpaybasic').empty(); 
			$('#basicPay').empty(); 
			$.ajax({
						type : "GET",
						url : contextPath+"/ddoast/fetchPayscale/" + payCommisionId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
							console.log("error in 6 pc "+data);
						},
						beforeSend : function(){
							$( "#loaderMainNew").show();
							},
						complete : function(data){
							$( "#loaderMainNew").hide();
						},	
						success : function(data) {
							console.log("success in 6 pc "+data);
							var len = data.length;
							if (len != 0) {
								document.getElementById('payInPayBand').readOnly = false;
								$('#payScaleSeven').empty();
								$('#payScaleSeven')
										.append("<option value='0'>Please Select</option>");
								var temp = data;
								$.each(temp,function(index,value) {
													console.log(value[2]);
													
													$('#payScaleSeven').append("<option value="+ value[4]+ ">"	+ value[3]+ "</option>");
													});
							} else {
								$('#payScaleSeven').empty();
								$('#payScaleSeven')
										.append(
												"<option value='0'>Please Select</option>");
								swal("Record not found !!!");
							}
						}
					});
		}
		else {
			$('#payScaleSeven').attr("disabled", false); 
			$('#payscalelevel').empty();
			$('#svnthpaybasic').empty();
			$('#payscalelevel').append("<option value='0'>Please Select</option>");
			$('#svnthpaybasic').append(
			"<option value='0'>Please Select</option>");
			swal("Record not found !!!");
		}
	}


}


$("#dcpsaccountmaintainby").change(function(){
	var dcpsAccMain=$("#dcpsaccountmaintainby").val();
	if(dcpsAccMain=="700175"){
		$("#dcpsDiv").hide();
	}else{
		$("#dcpsDiv").show();
	}
	
	
});






