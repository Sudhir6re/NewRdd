jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});





$("input[name='isApplicableLocation']").on("change", function(){
				if (this.value == 'Yes') {
					 $("#district").show();
				} else {
					 $("#district").hide();
				}

			});


$("input[name='isApplicableForEndDt']").on("change", function()
		{
				if (this.value == 'Yes') {
					$("#isEndDate").show();
				} else {
					$("#isEndDate").hide();
				}

			});




			/*var departmentCode = $("#departmentCode")
					.val();
			//alert("DDO CODE is "+departmentId);
			if (departmentCode != '') {
				$
						.ajax({
							type : "GET",
									url : "../mstSubDept/"
										+ departmentCode,
							async : true,
							contentType : 'application/json',
							error : function(data) {
								 //console.log(data);
							},
							success : function(data) {
								 console.log(data);
								// alert(data);
								
								var chk="";
								var len = data.length;
								if (len != 0) {
									// console.log(data);
									$(
											'#subDepartmentId')
											.empty();
								
									var temp = data;
									$
											.each(
													temp,
													function(
															index,
															value) {
														
														if($('#subDepartmentId').val()==value[0])
															{
															chk="selected=selected";
															}
														else{
															chk="";
														}
													
														$(
																'#subDepartmentCode')
																.append(
																		"<option   "+chk+" value="
																				+ value[0]
																				+ ">"
																				+ value[2]
																				+ "</option>");
													});
								} else {
									$(
											'#subDepartmentCode')
											.empty();
									$(
											'#subDepartmentCode')
											.append(
													"<option value='1000'>          ----------- ALL SELECT-----------          </option>");
									swal("Record not found !!!");
								}
							}
						});
			}
*/
		



var districtCode=[];
function getMessage()
					{
						 var checkBox = document.getElementById("selectAllDistrict");
						  if (checkBox.checked == true){
							  $("div#checkboxes .checkBoxClass").prop('checked',true);
						  } else {
							  $("div#checkboxes .checkBoxClass").prop('checked',false);
								 $('#checkboxes1').empty();
								 districtCode=[];
						  }
					}

function selectAllTaluka1()
{
	 var checkBox = document.getElementById("selectAllTaluka");
  if (checkBox.checked == true){
		  $("div#checkboxes1 .checkBoxClass1").prop('checked',true);
	  } else {
		  $("div#checkboxes1 .checkBoxClass1").prop('checked',false);
	  }
	
	
}
$(document)
		.ready(
				function() {
					
					//$('#isApplicablelocation').prop('clicked','clicked');
					
					// $("#isApplicablelocation").prop("checked", true);
					  
						var string=$('#previousDistrict').val();
						
						var array = JSON.parse("[" + string + "]");
						
						
						
						var string1=$('#previousTaluka').val();
						
						var array1 = JSON.parse("[" + string1 + "]");
						
					
						 var orderDate = $('#orderDate1').val();
						  var res = orderDate.substring(0, 10);
						
						  $('#orderDate').val(res);
					    
						
						if(array.length>0){
						$.ajax({
							type : "GET",
							url:"../fetchTalukaList/"+array,
							async : true,
							contentType : 'application/json',
							error : function(data) {
								// console.log(data);
								 
								 
							},
							success : function(data) {
							//	console.log(data);
								//talukaId
								
								
								
								var len = data.length;
								if (len != 0) {
				
									
									$('#checkboxes1')
											.empty();
						$('#checkboxes1')
						.append(
								"<label><input type='checkbox' id='selectAllTaluka'  onchange='selectAllTaluka1();' value='1000'>---Select All---</label>");
									var temp = data;
									$
											.each(
													temp,
													function(
															index,
															value) {
														
														var chk='';
														
													//	console.log(array);
														
														for(var j=0;j<array1.length;j++)
															{
															//console.log("array 1 index"+j+"value"+array1[j]);
															   if(array1[j]==value[0])
																   {
																   chk="checked='checked'";
																   }
															   
															}
														
														
														
														
														
											$('#checkboxes1')
											.append(
													"<input type='checkbox' class='checkBoxClass1'  "+chk+" th:field=*{talukaConcatCode["+index+"]} name = 'talukaConcatCode' value="
															+ value[0]
															+ " />" 
															+ value[2]+" ("+value[3]+")" +
																	"<br>");
														
											
														
													});
								} else {
									$(
									'#checkboxes1')
									.empty();
							$(
									'#checkboxes1')
									.append(
											"<option value='1000'>          ----------- ALL SELECT-----------          </option>");
									//swal("Record not found !!!");
								}
								
								
								
			
							}
						});
						
						
						}
						
						
						/*
						$("#departmentCode")
						.change(
								function() {
									var departmentId = $("#departmentCode")
											.val();
									// alert("DDO CODE is "+departmentId);
									if (department_id != '') {
										$
												.ajax({
													type : "GET",
													url : "../mstSubDept/"
														+ departmentId,
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
															$(
																	'#subDepartmentId')
																	.empty();
															$(
																	'#subDepartmentId')
																	.append("<option value='1000'>          ----------- ALL SELECT-----------          </option>");
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
																			"<option value='1000'>          ----------- ALL SELECT-----------          </option>");
															swal("Record not found !!!");
														}
													}
												});
									}

								});
						
						*/
					
					var a=0;
								$.ajax({
									type : "GET",
									//url : "getDistrict",
									url:"../getDistrict",
									async : true,
									contentType : 'application/json',
									error : function(data) {
									//	console.log(data);
									},
									success : function(data) {
										// console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
										//	console.log(data);
											
											$(
													'#checkboxes')
													.empty();
											
											

											var chk1='';
											
										//	console.log(array);
											
											for(var j=0;j<array.length;j++)
												{
												   if(array[j]==1000)
													   {
													   chk1="checked='checked'";
													   }
												}
											
											
											
											$(
											'#checkboxes')
											.append(
													"<label><input type='checkbox' id='selectAllDistrict' "+chk1+" onchange='getMessage();'  name='districtConcatCode' th:field=*{districtCode["+a+"]} value='1000'>---Select All---</label>");
									
										
											var temp = data;
											$
													.each(
															temp,
															function(
																	index,
																	value) {
																
																

																var chk='';
																
															//	console.log(array);
																
																for(var j=0;j<array.length;j++)
																	{
																	   if(array[j]==value[0])
																		   {
																		   chk="checked='checked'";
																		   }
																	}
																
															
																
																$(
																'#checkboxes')
																.append(
																		"<input type='checkbox' class='checkBoxClass'  "+chk+" name='districtConcatCode' th:field=*{districtConcatCode["+index+"]} value="
																				+ value[0]
																				+ " />"
																				+ value[1]
																				+ "</label></br>");
															});
										} else {
											$(
													'#checkboxes')
													.empty();
											$(
													'#checkboxes')
													.append(
															"<option value='1000'>          ----------- ALL SELECT-----------          </option>");
											//swal("Record not found !!!");
										}
									}
								});
					
					
								
								  
								
								  
								  
					$("#checkboxes")
					.change(
							function() {
							 $("#taluka").show(); 
							
							 $('div#checkboxes input:checked').each(function() {
								
								
									    districtCode.push($(this).val());
										if (districtCode.length != '0') {
											$
													.ajax({
														type : "GET",
														url:"../fetchTalukaList/"+districtCode,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															 console.log(data);
															 
															 
														},
														success : function(data) {
															console.log(data);
															//talukaId
															
															
															
															var len = data.length;
															if (len != 0) {
											
																
																$('#checkboxes1')
																		.empty();
													$('#checkboxes1')
													.append(
															"<label><input type='checkbox' id='selectAllTaluka'  onchange='selectAllTaluka1();' value='1000'>---Select All---</label>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					
																					var chk='';
																					
																				//	console.log(array);
																					
																					for(var j=0;j<array1.length;j++)
																						{
																						   if(array1[j]==value[0])
																							   {
																							   chk="checked='checked'";
																							   console.log("checked");
																							   }
																						}
																					
																					
																					
																					
																					
																		$('#checkboxes1')
																		.append(
																				"<input type='checkbox' class='checkBoxClass1'  "+chk+" th:field=*{talukaConcatCode["+index+"]} name = 'talukaConcatCode' value="
																						+ value[0]
																						+ " />" 
																						+ value[2]+" ("+value[3]+")" +
																								"<br>");
																					
																		
																					
																				});
															} else {
																$(
																'#checkboxes1')
																.empty();
														$(
																'#checkboxes1')
																.append(
																		"<option value='1000'>          ----------- ALL SELECT-----------          </option>");
																//swal("Record not found !!!");
															}
															
															
															
										
														}
													});
										}
								    
									});
						     });
					$('input[type="checkbox"]').click(function(){
			            if($(this).prop("checked") == true){
			           //     alert("Checkbox is checked.");
			                
			                $("#district").show();
			                
			                
			            }
			            else if($(this).prop("checked") == false){
			                //alert("Checkbox is unchecked.");
			                $("#district").hide();
			                $("#taluka").hide();
			            }
			        });
					
					
					
					$("#districtId")
					.change(
							function() {
								  $("#taluka").show();
								
						     });
					
					
					

					$("#departmentCode")
							.change(
									function() {
										var departmentId = $("#departmentCode")
												.val();
										// alert("DDO CODE is "+departmentId);
										if (department_id != '') {
											$
													.ajax({
														type : "GET",
														url : "../mstSubDept/"
																+ departmentId,
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
																// console.log(data);
																$(
																		'#subDepartmentCode')
																		.empty();
																$(
																		'#subDepartmentCode')
																		.append("<option value='1000'>          ----------- ALL SELECT-----------          </option>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																				
																					$(
																							'#subDepartmentCode')
																							.append(
																									"<option value="
																											+ value[0]
																											+ ">"
																											+ value[2]
																											+ "</option>");
																				});
															} else {
																$(
																		'#subDepartmentCode')
																		.empty();
																$(
																		'#subDepartmentCode')
																		.append(
																				"<option value='1000'>          ----------- ALL SELECT-----------          </option>");
																swal("Record not found !!!");
															}
														}
													});
										}

									});

					$("#ddoCode").change(function() {

						var ddoSplite=$("#ddoCode option:selected").text();;
						var  values=ddoSplite.split('(');
						var one=values[0];
						var two=values[1];
						var  values=two.split(')');

						var one=values[0];
						var ddoId = $('option:selected', this).attr('data-id');
						$('#ddoId').val(ddoId);
						
						$('#ddoIdCode').val(one);
					});

					$("#department_id")
							.change(
									function() {
										var subDepartmentId = $(
												"#subDepartmentId").val();
										// alert("DDO CODE is "+departmentId);
										
									
										var departmentId = $("#departmentCode")
										.val();
										if (department_id != '') {
											$
													.ajax({
														type : "GET",
														url : "../allSubDeptDDO1/"
																+ subDepartmentId+"/"+departmentId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															 console.log(data);
														},
														success : function(data) {
															
														// console.log(data);
															// alert(data);
															var len = data.length;
															if (len != 0) {
																
																$('#ddoCode')
																		.empty();
																$('#ddoCode')
																		.append(
																				"<option value='0'>Please Select</option>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																			
																					$(
																							'#ddoCode')
																							.append(
																									"<option   data-id="
																											+ value[0]
																											+ " value="
																											+ value[1]
																											+ ">"+ value[3]+" ("
																											+ value[1]+"_"+value[7]+"_"+value[5]
																											+ ")</option>");
																					
																					
																					
																					
																				});
															} else {
																$('#ddoCode')
																		.empty();
																$('#ddoCode')
																		.append(
																				"<option value='0'>Please Select</option>");
																swal("Record not found !!!");
															}
														}
													});
										}

									});

					$("#addAttachment")
							.click(
									function() {

										var departmentId = $("#departmentCode")
												.val();

										var subDepartmentId = $(
												"#subDepartmentCode").val();

										// alert(subDepartmentId);

										var ddoCode = $("#ddoCode").val();
										var sanctionOrderNo = $(
												"#sanctionOrderNo").val();
										var sanctionDate = $("#sanctionDate")
												.val();
										// alert("ddo code is"+ddoCode);

										var orderType = $("#orderType").val();

										if (departmentId == ''
												|| departmentId == '0') {
											// $("#sanctionOrderNo").css("border-color","red");
											swal("Please select department name");
										} else if (subDepartmentId == ''
												|| subDepartmentId == '0') {
											swal("Please select subdepartment name");
										} else if (ddoCode == ''
												|| ddoCode == '0') {
											swal("Please select ddo code");
										} else if (sanctionOrderNo == '') {
											swal("Please enter sanction order no");
										} else if (sanctionDate == '') {
											swal("Please enter select sanction  date");
										} else if (orderType == ''
												|| orderType == '0') {
											swal("Please enter order type");
										} else {
											var rowCount = $('#fileData tr').length;

											// alert("number of rows"+rowCount);

											if (rowCount < 2) {
												var fdesc = $(
														"#fileDescription")
														.val();
												var imgVal = $('#fileUploads')
														.val();
												if (fdesc == '') {
													$("#fileDescription").css(
															"border-color",
															"red");
													alert("Please enter file description");

												} else if (imgVal == '') {
													// $("#fileUploads").css("border-color",
													// "red");
													swal("Please select file");
												} else {
													$("#fileData")
															.append(
																	'<tr ><td><center>'
																			+ fdesc
																			+ '</center></td><td><center>'
																			+ imgVal
																			+ '</center></td><td class=""><center><span class="btnDelete" style="color:blue;">Remove</span></center></td></tr>');
												}
											} else {
												swal("Only one file is allowed to upload");
											}

										}

									});

					$("#fileData").on('click', '.btnDelete', function() {
						$(this).closest('tr').remove();
					});

					$("#btnSave")
							.click(
									function(event) {
										// alert("jhehh");
										// event.preventDefault();
										var departmentId = $("#departmentCode")
												.val();

										var subDepartmentId = $(
												"#subDepartmentCode").val();
										
								var checkforDistrict = $("#checkboxes").val();
								var checkboxesforTaluka =  $("#checkboxes1").val();
										var ddoCode = $("#ddoCode").val();
										var sanctionOrderNo = $(
												"#sanctionOrderNo").val();
										var sanctionDate = $("#sanctionDate")
												.val();
										// alert("ddo code is"+ddoCode);

										var orderType = $("#orderType").val();

										if (departmentId == ''
												|| departmentId == '0') {
											// $("#sanctionOrderNo").css("border-color","red");
											swal("Please select department name");
											event.preventDefault();
										} else if (subDepartmentId == ''
												|| subDepartmentId == '0') {
											swal("Please select subdepartment name");
											event.preventDefault();
										}
										else if (checkforDistrict == '')
										{
											swal("Please select district !");
											event.preventDefault();
										}
											else if (checkboxesforTaluka == '')
											{
												swal("Please select Taluka !");
												event.preventDefault();
											}
											else if (ddoCode == ''
												|| ddoCode == '0') {
											swal("Please select ddo code");
											event.preventDefault();
										} else if (sanctionOrderNo == '') {
											swal("Please enter sanction order no");
											event.preventDefault();
										} else if (sanctionDate == '') {
											swal("Please enter select sanction  date");
											event.preventDefault();
										} else if (orderType == ''
												|| orderType == '0') {
											swal("Please enter order type");
											event.preventDefault();
										}

									});

					// binds to onchange event of your input field
					$('#fileUploads').bind('change', function() {
						// this.files[0].size gets the size of your file.
						var fsize = this.files[0].size;
						// alert(fsize);
						if (fsize > 2097152) {
							swal("Please select 1 mb less size file");
						}

					});

					$("input[name='applicable']").on("change", function(){
						if (this.value == 'Yes') {
							$("#isEndDate").show();
						} else {
							$("#isEndDate").hide();
						}
					});
					
					
		//start fetch old data
					
/*					 $.ajax({url: "../mstGrOrder1", success: function(result){
					   console.log(result);
					    
					    var len = result.length;
						if (len != 0) 
						{
							 var temp = result; 
							$.each( temp,
									  function( index, value ){
								      //console.log( value[2] );
									  
									  var date = new Date(value[5]);
									  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
									  
									  var mon=months[date.getMonth()];
									  
									  var day=date.getDate();
									  var year= date.getFullYear();
									  
									  var newd=day+' '+mon+' '+year;
									  $('#oldData').append("<tbody><tr><td><center>"+value[1]+"</center></td><td><center>"+value[2]+"</center></td><td><center>"+value[4]+"</td><td><center>"+newd+"</center></td><td>" +
									  		"<div class='form-group'><div class='col-sm-offset-1 col-sm-1'><a href='../master/editMstGrOrder'>  <span class='glyphicon glyphicon-edit' id='edit'></span></a>  </div>  <a href='/master/deleteMstGrOrder'> <span class='glyphicon glyphicon-trash' id='delete' style='width:0px;'></span> </a></div></td></tr></tbody>");
									  
									  
									
									  });
							} 
							
						}
						});*/
						


				});



$("form[name='editGrOrderForm']").validate({
    // Specify validation rules
    rules: {
    	departmentId:{
    		required:true,
    		min:1
    		},
    		subDepartmentId:{
    			required:true,
    			min:1
    		},
    		orderType:{
    			required:true,
    			min:1
    		},
    		isApplicableLocation:{
    			required:function(){
    				if($("input[name='applicable']:checked").val()=='Yes' || $("input[name='applicable']:checked").val()=='No'){
				    return false;
				    }else{
			       	return true;
				   }
    			},
    		},
    		orderDate: "required",
    		sanctionOrderNo: "required",
    		documentPath: {
            	required: true,
            	extension: "pdf",
            	filesize: 1024,
            },
            districtConcatCode:{
            	required:function(){
            		return ($("input[name='isApplicableLocation']:checked").val()=="Yes" && $("#districtConcatCode").val()=="0");
    			},
            },
    		endDate:{
    			required:function(){
    				if($("input[name='isApplicableForEndDt']:checked").val()=='Yes' ){
    					   return true;
				    }else{
			       	return false;
				   }
    			},
    		},
    		isApplicableForEndDt:{
    			required:function(){
    				if($("input[name='isApplicableForEndDt']:checked").val()=='Yes' || $("input[name='isApplicableForEndDt']:checked").val()=='No'){
    					return false;
    				}else{
    					return true;
    				}
    			},
    		},
    		grDescription: "required",
  
    },
    // Specify validation error messages
    messages: {
    	departmentId: "Please Select Department Name",
    	subDepartmentId: "Please Enter Corporation Name",
    	orderType: "Please Select GR Order Type",
    	orderDate: "Please Select Sanction order Date",
    	sanctionOrderNo: "Please Enter Sanction Order No",
    	isApplicableLocation: "Please Select Applicable For End Date",
    	documentPath:{
    		required: "Please select support document",
        	extension: "Only .pdf files are allowed",
        	filesize: "Document size should be less then 1 MB",
    	},
    	districtConcatCode: "Please Select District",
		orderDate: "Please Select End Date",
		grDescription: "Please enter Gr description",
		endDate:"Please Select End Date",
		isApplicableForEndDt:"Please Select Applicable For End Date ",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      if($("input[name='isApplicableLocation']:checked").val()=="Yes" && $("#districtConcatCode").val()=="0"){
    	  addErrorClass($("#districtConcatCode"),"Please Select District");
      }else{
    	  $("#loaderMainNew").show();
    	  form.submit();
      }	
    	
      
     
    }
  });

