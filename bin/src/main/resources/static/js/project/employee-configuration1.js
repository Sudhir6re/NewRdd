$(document).ready(function(){
	
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
	$(".sevaarthIdBlock").show();
	var isGisApplicable=true;
	
	$("#gisapplicable").change(function(){
	    if($(this).val()=="1"){
	    	isGisApplicable=false;
	    	$("#gisgroup").prop("disabled",true);
	    	$("#membership_date").prop("disabled",true);
	    }
	    else{
	    	$("#gisgroup").prop("disabled",false);
	    	$("#membership_date").prop("disabled",false);
	    	isGisApplicable=true;
	    }
	});
	
	
	

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
						$("#ifscCode").val(data[0].ifscCode);
				   }
				}
			});
	     });

	
	
	
	$("#imagePath").hide();
	$("#imagePathSign").hide();
	
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
	if (uidnumbercheck != "") {
		document.getElementById('eidNo').readOnly = true;
	}else{
//		document.getElementById('eidNo').readOnly = true;
		document.getElementById("uid1").readOnly = true;
		document.getElementById("uid2").readOnly = true;
		document.getElementById("uid3").readOnly = true;
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
		document.getElementById('pfacno').disabled = '';
		document.getElementById('pfdescription').disabled = 'true';
		document.getElementById("accountmaintainby").value = "0";
		document.getElementById("pfseries").value = "0";
		document.getElementById("pfdescription").value = "";

	} 
	if (dcpsValue == 'N'){
		document.getElementById('dcpsaccountmaintainby').disabled = 'true';
		document.getElementById('accountmaintainby').disabled = '';
		document.getElementById('pfseries').disabled = '';
		document.getElementById('pfacno').disabled = '';
		document.getElementById('pfdescription').disabled = 'true';
		document.getElementById("dcpsaccountmaintainby").value = "0";
		document.getElementById("pfdescription").value = "";

	}
	
	//09 dec 2020 dcps validation end
	
	//09 dec 2020 Marrid validation start
	
	var index = document.myForm.gender.selectedIndex;
	var genderValue = document.myForm.gender[index].text;
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
	
	var pfseries1=$("#pfseries").val();
	var pfseries = $('#pfseries option:selected').text();
	var pfacno=$("#pfacno").val();
//	alert("pfseries="+pfseries);
//	alert("pfacno="+pfacno);
//	alert("pfseries1="+pfseries1);
	if(pfseries!=''&&pfseries!=null&&pfacno!=''&&pfacno!=null&&pfseries1!='0'){
		 $("#pfdescription").val(pfseries+"/"+pfacno);
	}
	
	
	

	var today = new Date(); var dd = today.getDate(); 
	var mm = today.getMonth()+1; //January is 0! 
	var yyyy = today.getFullYear(); 
	if(dd<10){ dd='0'+dd } 
	if(mm<10){ mm='0'+mm } 
	today = yyyy+'-'+mm+'-'+dd; 
	document.getElementById("dtInitialAppointmentParentInst").setAttribute("max", today);
	document.getElementById("dtJoinCurrentPost").setAttribute("max", today);
	document.getElementById("indiApproDt").setAttribute("max", today);
	
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='myForm']").validate({
		
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			sevaarthId : "required",
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
			gender : {
				required : true,
				min : 1
			},
			stateCode : {
				required : true,
				min : 1
			},
			districtCode : {
				required : true,
				min : 1
			},
			maritalStatus : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="maritalStatus"]:checked').val() == '' || $('input[name="maritalStatus"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			employeeMNameEn : "required",
			employeeLNameEn : "required",
			employeeFullNameEn : "required",
			employeeFullNameMr : "required",
			employeeMNameMr : "required",
			dob : "required",
			doj : "required",
			address1 : "required",
			address2 : "required",
			address3 : "required",
			locality : "required",
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
			subCorporationId : {   //parentFieldDepartmentId corporation
				required : true,
				min : 1
			},
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
					return !parseInt($("#payCommision").val())==2;
				},
				digits : true,
				minlength : 1,
			},
			svnthpaybasic : {
				required : function() {
					//returns true if eidNo is empty
					return !parseInt($("#payCommision").val())==2;
				},
				digits : true,
				minlength : 1,
			},
		    
		    
		    //var regpan = /^[a-zA-Z0-9,.!? ]*$/; alphanumeric regular expression
			 
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
			sevaarthId : " Please enter SevaarthId",
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
			emailId : " Please enter a valid email address",
			employeeMNameEn : " Please enter Employee Middle Name",
			employeeLNameEn : " Please enter Employee Last Name",
			employeeFullNameEn : " Please enter Employee Full Name",
			employeeFullNameMr : " Please enter FullName In MARATHI",
			employeeMNameMr : " Please enter Father/Husband Name",
			dob : " Please select DATE OF BIRTH",
			doj : " Please select DATE OF JOINING",
			address1 : " Please enter Address Building",
			address2 : " Please enter Address Street",
			address3 : " Please enter Landmark",
			stateCode : " Please Select State",
			districtCode : " Please Select District",
			locality : " Please Enter Locality",
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
		    },
		    panNo:{
		    	required : "Please enter Pan No",
				minlength : "Pan No should be atleast 10 Digit long.",
				pattern : "Please Enter Valid Pan No ",
		    },
		    parentAdminDepartmentId : "Please Select Department",
		    parentFieldDepartmentId : "Please Select Corporation",
		    subCorporationId : "Please Select Sub Corporation",
		    cadreId : "Please Select Cadre",
		    empClass : "Please Select Cadre Group",
		    superannuationage : "Please enter Super Annunation Age",
		    superAnnDate : "Please enter Super Annunation Date",
		    payCommissionCode : "Please Select paycommision",
		    designationId : "Please Select Designation",
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	
	
	
	$('body').on('blur', '#uid3', function() {
		validateUIDUniqe();
	});
	$('body').on('blur', '#panNo', function() {
		ValidatePancard();
	});
	$('body').on('blur', '#eidNo', function() {
		if($(this).val()!=''){
		$("#uid1-error").hide();
		$("#uid2-error").hide();
		$("#uid3-error").hide();
		$("#uid1").removeClass( "error");
		$("#uid2").removeClass( "error");
		$("#uid3").removeClass( "error");
		}
	});
	
	
	function ValidatePancard() {
		var panVal = $('#panNo').val();
		var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;

		if (regpan.test(panVal)) {
			// valid pan card number
			validatePanUniqe();
			return (true)
		} else {
			// invalid pan card number
			//document.getElementById("panNo").value = "";
			//swal("You have entered an invalid pan card number!")
			return (false)

		}
	}
	
	
	
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
				//swal('Please enter complete UID number.');
				
				$("#eidNo-error").hide();
				$("#eidNo").removeClass( "error");
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
									$("#eidNo-error").hide();
									$("#eidNo").removeClass( "error");
									
									$("#forward").prop("disabled",false);
									// alert('all ok');
								} else if (checkFlag > 0) {

									/*swal('Entered UID number: '
											+ UID
											+ ' is already present in system. Please enter correct UID number.');*/
									
									$("#uid3-error").text('Entered UID number: '
											+ UID
											+ ' is already present in system. Please enter correct UID number.');
									
									$("#forward").prop("disabled",true);
									
									$("#uid3-error").show();
									$("#uid2-error").show();
									$("#uid3-error").show();
									$("#uid1").addClass( "error");
									$("#uid2").addClass( "error");
									$("#uid3").addClass( "error");
									

									/*document.getElementById("uid1").value = "";
									document.getElementById("uid2").value = "";
									document.getElementById("uid3").value = "";*/
									status = false;
								}
								return status;
							}
						});
			}
		}
	}
	
	

	//for fetching Sub Corporation associates with Corporation
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

					});
});
	
