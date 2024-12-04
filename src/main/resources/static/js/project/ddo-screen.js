/*;$("#ddoName" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});*/
$(".alphacharspace").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');
	$(this).val(replacestr);
	});



$(document)
		.ready(
				function() {
										
					var varMessage = $("#message").val();
					var varUsernamePassword = $("#UsernamePassword").val();

					var varFinal = varMessage + varUsernamePassword;

					if (varMessage != "" && varUsernamePassword != ""
							&& varMessage != undefined
							&& varUsernamePassword != undefined) {
						swal('' + varFinal, {
							icon : "success",
						});
					}
					
					loadsubdept();
					
					function loadsubdept(){

						var departmentId = 60;
						// alert("DDO CODE is "+departmentId);
						if (departmentId != '') {
							$
									.ajax({
										type : "GET",
										url : "../master/mstSubDept/"
												+ departmentId,
										async : true,
										contentType : 'application/json',
										error : function(data) {
											 console.log(data);
										},
										success : function(data) {
											 console.log(data);
											// alert(data);
											var len = data.length;
											if (len != 0) {
												// console.log(data);
												$(
														'#subDepartmentId')
														.empty();
												$(
														'#subDepartmentId')
														.append(
																"<option value='0'>Please Select</option>");
												var temp = data;
												$
														.each(
																temp,
																function(
																		index,
																		value) {
																	console
																			.log(value[2]);
																	$(
																			'#subDepartmentId')
																			.append(
																					"<option value="
																							+ value[0]
																							+ ">"
																							+ value[2]
																							+ "</option>");
																});
											} else {
												$(
														'#subDepartmentId')
														.empty();
												$(
														'#subDepartmentId')
														.append(
																"<option value='0'>Please Select</option>");
												swal("Record not found !!!");
											}
										}
									});
						}

					
						
					}

					$("#departmentId")
							.change(
									function() {
										var departmentId = 60;
										// alert("DDO CODE is "+departmentId);
										if (departmentId != '') {
											$
													.ajax({
														type : "GET",
														url : "../master/mstSubDept/"
																+ departmentId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															 console.log(data);
														},
														success : function(data) {
															 console.log(data);
															// alert(data);
															var len = data.length;
															if (len != 0) {
																// console.log(data);
																$(
																		'#subDepartmentId')
																		.empty();
																$(
																		'#subDepartmentId')
																		.append(
																				"<option value='0'>Please Select</option>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					console
																							.log(value[2]);
																					$(
																							'#subDepartmentId')
																							.append(
																									"<option value="
																											+ value[0]
																											+ ">"
																											+ value[2]
																											+ "</option>");
																				});
															} else {
																$(
																		'#subDepartmentId')
																		.empty();
																$(
																		'#subDepartmentId')
																		.append(
																				"<option value='0'>Please Select</option>");
																swal("Record not found !!!");
															}
														}
													});
										}

									});

					// start for taluka

					$("#districtId")
							.change(
									function() {
										// alert("hello");
										var districtCode = $("#districtId")
												.val();
										// alert("DDO CODE is "+departmentId);
										var stateId = 27;
										if (stateId != '') {
											$
													.ajax({
														type : "GET",
														url : "../master/fetchTaluka/"
																+ districtCode
																+ "/" + stateId,
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
																console
																		.log(data);
																$('#taluka')
																		.empty();
																/*$('#taluka')
																		.append(
																				"<option value='0'>Please Select</option>");*/
																$('#taluka').append("<option value='0'>Please Select</option>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					/*console
																							.log(value[2]);*/
																					
																					$(
																							'#taluka')
																							.append(
																									"<option value="
																											+ value[6]
																											+ ">"
																											+ value[5]
																											+ "</option>");

																				});
															} else {
																$('#taluka')
																		.empty();
																$('#taluka')
																		.append(
																				"<option value='0'>Please Select</option>");
																swal("Record not found !!!");
															}
														}
													});
										}

									});

					$("#responseDistrictId")
							.change(
									function() {
										// alert("hello");
										var districtCode = $(
												"#responseDistrictId").val();
										// alert("DDO CODE is "+departmentId);
										var stateId = 27;
										if (stateId != '') {
											$
													.ajax({
														type : "GET",
														url : "../master/fetchTaluka/"
																+ districtCode
																+ "/" + stateId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															// console.log(data);
														},
														success : function(data) {
															 console.log(data);
															// alert(data);
															var len = data.length;
															if (len != 0) {
																console
																		.log(data);
																$('#taluka1')
																		.empty();
																$('#checkboxes')
																		.empty();
																$('#taluka1')
																		.append(
																				"<option value='0'>Please Select</option>");
																var temp = data;

																// checkboxes
																/*$('#checkboxes')
																		.append(
																				"<input type='checkbox' class='checkBoxClass'  value='0'/>Select All</label></br>");*/
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					
																					// $('#taluka1').append("<option
																					// value="+value[6]+">"+
																					// value[5]
																					// +
																					// "</option>");

																					$(
																							'#checkboxes')
																							.append(
																									"<input type='checkbox' class='checkBoxClass' name='talukaConcatId' th:field=*{talukaConcatId["+index+"]} value="
																											+ value[6]
																											+ " />"
																											+ value[5]
																											+ "</label></br>");

																					// $('#taluka1').append('<option>'+"<input
																					// type='checkbox'>"+'</option>');
																					// $('#taluka1').append("<option
																					// value="+value[6]+"><input
																					// type='checkbox'></input>"+
																					// value[5]
																					// +
																					// "</option>");

																				});
															} else {
																// $('#taluka1').empty();

																$('#checkboxes')
																		.empty();

																// $('#taluka1').append("<option
																// value='0'>Please
																// Select</option>");
																$('#checkboxes')
																		.append(
																				"<option value='0'>Please Select</option>");
																swal("Record not found !!!");
															}
														}
													});
										}

									});

					$("#taluka1")
							.change(
									function() {
										/*
										 * alert("schemebillGroupId
										 * "+$('#schemebillGroupId').val());
										 */
										var talukaId = $('#taluka1').val();
										// var ddoCode = $('#ddoCode').val();
										$("#customFields").empty();
										if ($('#taluka1').val() != '') {

											$
													.ajax({
														type : "GET",
														url : "../lstOfTalukaLocation/"
																+ talukaId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
															// alert(window.location+ur);
														},
														success : function(data) {
															// console.log(data);
															/*
															 * alert(" >>> : "+
															 * data);
															 */

															var temp = data;
															var tem = [];
															$("#customFields")
																	.append(
																			'<tr><th th width = "10%" style="border:none;"></th><th width = "30%" style="border:none;"> Location Code </th><th width = "30%" style="border:none;"> Location Name </th><th width = "30%" style="border:none;">Village Name</th></tr>');
															$
																	.each(
																			temp,
																			function(
																					index,
																					value) {
																				console
																						.log(value[2]); // 15
																				$(
																						"#customFields")
																						.append(
																								'<tr ><td style="border:none;" width = "50"><input type="checkbox" id="chckbox" class="btnSelect"></input></td><td style="border:none;">'
																										+ value[1]
																										+ '</td>&nbsp;<td style="border:none;">'
																										+ value[2]
																										+ '</td><td style="border:none;">'
																										+ value[10]
																										+ '</td></tr>');
																				$(
																						"#customFields")
																						.css(
																								"border-color",
																								"white");

																			});
														}
													});
										}

									});

					/*$("#checkboxes").click(function() {*/

										/*console.log($(this));*/
										$('div#checkboxes input[type=checkbox]').click(function() {
											
											
															if ($(this).prop("checked") == true) {
																
																if ($(this).val() == 0) {
																	$('.checkBoxClass').prop('checked',true);
																}
																
																
															} else if ($(this).prop("checked") == false) {
																if ($(this).val() == 0) {
																	$('.checkBoxClass').prop('checked',false);
																}
																
																
															}
															
															
															
													
												});
									/*});*/
				});



function ConfirmDeleteRecord(ddoRegID,isActive) {
	if(isActive==1){
		swal({
			  title: "Are you sure?",
			  text: "Status of this record will be InActive !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			}).then((willDelete) => {
			    if (willDelete) {   
					$.ajax({
					      type: "GET",
					      url: "../user/deleteDDOScreen/"+ddoRegID,
					      async: true,
					      error: function(data){
					    	  swal("Deleted successfully !", {
					    	      icon: "success",
					    	  });
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      },
					      success: function(data){
					    	  swal("Deleted successfully !", {
					    	      icon: "success",
					    	  });
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      }
					 });
			     }
		})
	} else if(isActive==0) {
		 swal({
	    	  title: 'Not allowed !',
	    	  text: 'This record is already deleted',
			  icon: "warning",
	    });
	}
}


$('#btnCancel').click(
	    function(){
	   	 var ddoName=$('#ddoName').val('');
	   	 var subDepartmentId=$('#subDepartmentId').val('');
	   	 var ddoCode=$('#ddoCode').val('');
	   	var villageName=$('#villageName').val('');
	   	 var officeName=$('#officeName').val('');
	   	
	    });

var ddoNameError;
var subDepartmentIdError;
var ddoCodeError;
var villageNameError;
var officeNameError;
var warningError;
var departmentNameError;
var districtError;
var ddoExistsError;
var responseDistrictIdError;
var ddoLevelError;
var talukaError;
var responseTalukaIdError;
if($('#language').val()=="en"){
	ddoNameError="Please enter DDO name  !!!";
	subDepartmentIdError="Please select the subdepartment name !!!";
	ddoCodeError="Please enter DDO code !!!";
	villageNameError="Please enter village Name !!!";
	officeNameError="Please enter office Name !!!";
	departmentNameError="Please select department Name !!!";
	districtError="Please select district Name !!!";
	ddoExistsError="Please select  DDO Exists !!!";
	ddoLevelError="Please select  DDO Level  !!!";
	talukaError="Please select Taluka  !!!";
	responseDistrictIdError="Please select Resposibilty Location-District field !!!";
	responseTalukaIdError="Please select Resposibilty Location-Taluka field !!!";
	warning="warning !";
}else{
	ddoNameError="कृपया विभाग कोड प्रविष्ट करा  !!!";
	subDepartmentIdError="कृपया विभाग लहान नाव प्रविष्ट करा  !!!";
	ddoCodeError="कृपया विभागाचे नाव इंग्रजीमध्ये प्रविष्ट करा !!!";
	villageNameError="कृपया मराठी मध्ये विभाग नाव प्रविष्ट करा !!!";
	officeNameError="कृपया मराठी मध्ये विभाग नाव प्रविष्ट करा !!!";
	warning="चेतावणी !";
}

$(".optradio").click(function()
		{
			if($("input[name='optradio']:checked").val()=='1')
				{
			//	alert("Inside");
				$('#btnSave').prop("disabled",true);
				}
			else{
				$('#btnSave').prop("disabled",false);
			}

		});


$(".numbers" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});



//validateDDOCode

function validateDDOCode() {
	// alert('inside validateUIDUniqe');
	var ddocode = document.getElementById("ddoCode").value;
	
	var level =$("#level").val();
	if(level==1){
		var isContain_AST=ddocode.includes("_AST");
		if(isContain_AST== false)
			{
			swal("Please add AST for Level 1 DDO");
			
			}
			return false;
		
	}
		
		if (ddoCode != '') {
			$
					.ajax({
						type : "GET",
						url : "validateDDOCode/"+ddocode+"/"+level,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#ddoCode').val(ddocode);
								status = true;
							} else if (checkFlag > 0) {

								swal(ddocode + ' Already Present in the system, Please enter the Different DDO Code !!!');

								document.getElementById("ddoCode").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

//validateDDOName

function validateDDOName() {
	// alert('inside validateUIDUniqe');
	var ddoname = document.getElementById("ddoName").value;
	
	
	if (ddoname != '') {
		$
		.ajax({
			type : "GET",
			url : "../user/validateDDOName/"+ddoname,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);
			},
			success : function(data) {
				console.log(data);
				var len = data.length;
				var checkFlag = data;
				if (checkFlag == 0) {
					$('#ddoname').val(ddoname);
					status = true;
				} else if (checkFlag > 0) {
					
					swal(ddoname + ' Already Present in the system, Please enter the Different DDO Name !!!');
					
					document.getElementById("ddoname").value = "";
					status = false;
				}
				return status;
			}
		});
	}
}

$("form[name='mstDDO']").validate({
    // Specify validation rules
    rules: {
    	departmentId:
    	{
    		required:true,
    		min:1
    	},
    	subDepartmentId:
    	{
    		required:true,
    		min:1
    	},
    	ddoName: "required",
    	
    /*	optradio:
		{
			required:function(){
				if{$("input[name='optradio']:checked").val()=='1' || $("input[name='optradio']:checked").val()=='2'){
return false;
}
				}
else
{
return true;
}
			
			},
		},
		},*/
		ddoCode: "required",
    	
			level:
    	{
    		required:true,
    		min:1
    	},
    	
    	//officeName: "required",
    	ddoCityCategory:
    	{
			required :true,
			pattern: /^([A-Z]){1}?$/,
		},
    	treasuryCode:
    	{
    		required:true,
    		min:1
    	},
    	parentDistrictCode:
    	{
    		required:true,
    		min:1
    	},
    	parentTalukaCode:
    	{
    		required:true,
    		min:1
    	},
    	cityGroup:
    	{
    		required:true,
    		pattern: /^([A-Z]){1}?$/,
    	},
    	
    	villageName: "required",
    	officeName: "required",
    	districtCode:
    		{
    		required: true,
    		min:1
   	},
    		},
    // Specify validation error messages
    messages: {
    	departmentId: "Please Select Department",
    	subDepartmentId: "Please Select Corporation",
    	ddoName: "Please Enter DDO Name",
    	optradio: "Please Select DDO Exists or Not",
    	ddoCode: "Please Enter DDO Code",
    //	officeName: "Please Select Level",
    	level: "Please Select DDO Level",
    	ddoCityCategory: "Please Select City Class",
    	treasuryCode: "Please Select Treasury Name ",
    	parentDistrictCode: "Please Select Parent District Name",
    	parentTalukaCode: "Please Select Parent Taluka Name",
    	villageName: "Please Enter The Village Name",
    	officeName: "Please Enter The Office Name",
    	cityGroup: "Please select City Group",
    	districtCode: "Please Select Responsibility District Name"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
      $("#loaderMainNew").show();
    }
  });