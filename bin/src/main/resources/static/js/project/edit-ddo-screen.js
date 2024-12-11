jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

$(document)
		.ready(
				function() 
				{
					var string=$('#talukaConcatCode').val();
					
					var array = JSON.parse("[" + string + "]");
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
					
					
					
				
							/*	var districtCode = $("#districtId")
										.val();
								// alert("DDO CODE is "+departmentCode);
								var stateId = 27;
								if (stateId != '') {
									$
											.ajax({
												type : "GET",
													url:"../master/fetchTaluka/"+ districtCode
													+ "/" + stateId,
												async : true,
												contentType : 'application/json',
												error : function(data) {
													// console.log(data);
												},
												success : function(data) {
													 //console.log(data);
													// alert(data);
													var len = data.length;
													if (len != 0) {
														
														$('#parentTalukaId')
																.empty();
														$('#taluka')
																.append(
																		"<option value='0'>Please Select</option>");
														$('#parentTalukaId').append("<option value='0'>Please Select</option>");
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
																					'#parentTalukaId')
																					.append(
																							"<option value="
																									+ value[6]
																									+ ">"
																									+ value[5]
																									+ "</option>");

																		});
													} else {
														$('#parentTalukaId')
																.empty();
														$('#parentTalukaId')
																.append(
																		"<option value='0'>Please Select</option>");
														swal("Record not found !!!");
													}
												}
											});
								}
*/
						
					

			
		               var distId=$('#responseDistrictId').val();
								$.ajax({
											type : "GET",
										//	url : "lstOfTalukaLocation1",
											url:"../lstOfTalukaLocation1/"+distId,
											async : true,
											contentType : 'application/json',
											error : function(data) {
												// console.log(data);
											},
											success : function(data) {
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
													$
															.each(
																	temp,
																	function(
																			index,
																			value) {
																		
																		var chk='';
																		
																		console.log(array);
																		
																		for(var j=0;j<array.length;j++)
																			{
																			   if(array[j]==value[4])
																				   {
																				   chk="checked='checked'";
																				   }
																			}
																		$(
																				'#checkboxes')    
																				.append(
																						"<input type='checkbox' class='checkBoxClass' "+chk+"   id='talukaConcatCode'   name='talukaConcatCode' th:field=*{talukaConcatCode["+index+"]} value="
																								+ value[4]
																								+ " />"
																								+ value[5]
																								+ "</label></br>");

																	});
												}
											}
										});
					
					$("#departmentCode")
							.change(
									function() {
										var departmentCode = $("#departmentCode")
												.val();
										// alert("DDO CODE is "+departmentCode);
										if (departmentCode != '') {
											$
													.ajax({
														type : "GET",
														url : "../mstSubDept/"
																+ departmentCode,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															// console.log(data);
														},
														success : function(data) {
															 //console.log(data);
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
																				"<option value='1000'>--------------------All--------------------</option>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					
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
										 //alert("hello");
										var districtCode = $("#districtId")
												.val();
										 //alert("districtCode is "+districtCode);
										var stateId = 27;
										if (stateId != '') {
											$
													.ajax({
														type : "GET",
														url : "../lstOfTalukaLocation1/"
															+ districtCode,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															 //console.log(data);
														},
														success : function(data) {
															 console.log(data);
															 //alert(data);
															var len = data.length;
															if (len != 0) {
																
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
																											+ value[4]
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

			
										$("#responseDistrictId")
										.change(
												function() {
													var districtCode = $(
															"#responseDistrictId").val();
													// alert("DDO CODE is "+departmentCode);
													var stateId = 27;
													if (stateId != '') {
														$
																.ajax({
																	type : "GET",			
																		url:"../lstOfTalukaLocation1/"+ districtCode,
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
																								//console.log(value[2]);
																								// $('#taluka1').append("<option
																								// value="+value[6]+">"+
																								// value[5]
																								// +
																								// "</option>");

																								$(
																										'#checkboxes')
																										.append(
																												"<input type='checkbox' class='checkBoxClass' data-id='mi'   name='talukaConcatId' th:field=*{talukaConcatId["+index+"]} value="
																														+ value[4]
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
									/*});*/
										
										  $("input[value='16']").prop('checked', true);
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
	departmentNameError="Please enter department Name !!!";
	districtError="Please enter district Name !!!";
	ddoExistsError="Please select Is DDO Exists !!!";
	ddoLevelError="Please select Is DDO Level  !!!";
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
$('#btnSave').click(function(e) {
//	alert("department name"+$('#departmentId').val());
   	var departmentName=$('#departmentCode').val();
   	var subDepartmentName=$('#subDepartmentCode').val();
    var ddoName=$('#ddoName').val();
  	 var ddoCode=$('#ddoCode').val();
  	 var ddoLevel=$('#level').val();
  	 var district=$('#districtId').val();
  	 var taluka=$('#parentTalukaId').val();
  	var villageName=$('#ddoCode1').val();
  	var officeName=$('#ddoCode2').val();
  	var ddoExists=$("input[name='optradio']:checked").val();
  	var responseDistrictId=$("#responseDistrictId").val();
  	var responseTalukaId=$(".checkboxes").val();
  	var responseTaluka=$(".talukaConcatCode").val();	
   	
   	
   	 if(departmentName==0){
   		 if(departmentName=="0")
		 Swal.fire({
			 title: warning,
			 text: departmentNameError,
			 icon: "warning",
			 timer: 4000
		 });
		 e.preventDefault();
	 }
   	else if(subDepartmentName==0 ){
		 if(subDepartmentName=="0" )
		 Swal.fire({
				title: warning,
				text: subDepartmentIdError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		 
	 }
   	
   	else if(ddoName==""){
		 Swal.fire({
				title: warning,
				text: ddoNameError,
				icon: "warning",
				timer: 4000
			});
		 e.preventDefault();
		 
	 }else if(ddoCode=="" ){
		 Swal.fire({
				title: warning,
				text: ddoCodeError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
	 }
	
	 else if(ddoLevel=="0" ){
		 Swal.fire({
				title: warning,
				text: ddoLevelError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
	 }
	 else if(district=="0" ){
		 Swal.fire({
			 title: warning,
			 text: districtError,
			 icon: "warning",
			 timer: 4000
		 });
		 e.preventDefault();
	 }
	 else if(taluka=="0" ){
		 Swal.fire({
			 title: warning,
			 text: talukaError,
			 icon: "warning",
			 timer: 4000
		 });
		 e.preventDefault();
	 }
	 else if(villageName==""){
		 Swal.fire({
				title: warning,
				text: villageNameError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
	 }
	 else if(officeName==""){
		 Swal.fire({
				title: warning,
				text: officeNameError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
	 }
	 else if(ddoExists==""){
		 Swal.fire({
			 title: warning,
			 text: ddoExistsError,
			 icon: "warning",
			 timer: 4000
		 });
		 e.preventDefault();
	 }
	 else if(responseDistrictId=="0"){
		 Swal.fire({
			 title: warning,
			 text: responseDistrictIdError,
			 icon: "warning",
			 timer: 4000
		 });
		 e.preventDefault();
	 }
	else if(responseTalukaId==""||responseTalukaId=="Select an option"){
		 Swal.fire({
			 title: warning,
			 text: responseTalukaIdError,
			 icon: "warning",
			 timer: 4000
		 });
		 e.preventDefault();
	 }
  	else if(responseTaluka==0 ){
		 if(responseTaluka=="0" )
		 Swal.fire({
				title: warning,
				text: responseTalukaIdError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		 
	 }
  	
});




function validateDDOCode() {
	// alert('inside validateUIDUniqe');
	var ddocode = document.getElementById("ddoCode").value;
	
	
	
		if (ddoCode != '') {
			$
					.ajax({
						type : "GET",
						url : "../validateDDOCode/"+ddocode,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#ddoCode').val(deptname);
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

/*function validateDDOName() {
	// alert('inside validateUIDUniqe');
	var ddoname = document.getElementById("ddoName").value;
	
	
	
	if (ddoname != '') {
		$
		.ajax({
			type : "GET",
			url : "../validateDDOName/"+ddoname,
			async : false,
			contentType : 'application/json',
			error : function(data) {
			},
			success : function(data) {
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

*/

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
    }
  });