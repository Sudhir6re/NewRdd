$("#sanctionOrderNo" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
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


$("input[name='isApplicableLocation']").on("change", function(){
				if (this.value == 'Yes') {
					 $("#district").show();
				} else {
					 $("#district").hide();
				}

			});

$('.checkBoxClass1').change(function() {
	//alert($(this).val());
	});


$('.checkBoxClass').change(function() {
	//alert($(this).val());
});






//$('input[type="checkbox"]').click(function(){
//    if($(this).prop("checked") == true){
//        alert("Checkbox is checked.");
//        
//        $("#district").show();
//        
//        
//    }
//    else if($(this).prop("checked") == false){
//        //alert("Checkbox is unchecked.");
//        $("#district").show();
//        $("#taluka").show();
//    }
//});



var districtCode=[];
function getMessage()
					{
                 	districtCode=[];
	/*
                      	// $('#checkboxes').on('change');
						    var checkBox = document.getElementById("selectAllDistrict");
						  if (checkBox.checked == true){
							  $("div#checkboxes .checkBoxClass").prop('checked',true);
						  } else {
							  $('#taluka').hide();
							  $("div#checkboxes .checkBoxClass").prop('checked',false);
								 $('#checkboxes1').empty();
								 districtCode=[];
								 console.log("else executed");
							//	 break;
								
						  }*/
						  
	                    //    alert("i am true");
	                        $("#taluka").show(); 
	                      // $("div#checkboxes .checkBoxClass").prop('checked',true);
						   
							 $('div#checkboxes input:checked').each(function() {
							//	 console.log("on change excuted");
									    districtCode.push($(this).val());
									    console.log($(this).val());
									});
							 
							 
							 
							 
							 
								if (districtCode.length != '0') {
									$
											.ajax({
												type : "GET",
												url : "fetchTalukaList/"
														+ districtCode,
												async : true,
												contentType : 'application/json',
												error : function(data) {
													 //console.log(data);
													 
													 
												},
												success : function(data) {
													//console.log(data);
													//talukaId
													
													
													
													var len = data.length;
													if (len != 0) {
									
														
														$('#checkboxes1')
																.empty();
														$(
														'#checkboxes1')
														.append(
																"<label><input type='checkbox' id='selectAllTaluka'  onchange='selectAllTaluka1();' value='1000' data='1'>---Select All---</label>");
														var temp = data;
														$
																.each(
																		temp,
																		function(
																				index,
																				value) {
																			
																$('#checkboxes1')
																.append(
																		"<input type='checkbox' class='checkBoxClass1' th:field=*{talukaConcatCode["+index+"]} name = 'talukaConcatCode' data='0'  value="
																				+ value[0]
																				+ " />" 
																				+ value[2]+" ("+value[3]+")" +
																						"<br>");
																			
																	/*
																			$(
																					'#talukaId')
																					.append(
																							"<option   data-id="
																									+ value[0]
																									+ " value="
																									+ value[0]
																							+ " >"+value[2]+" ("+value[3]+")</option>");*/
																			
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
$(document).ready(function() {
	var contextPath = $("#appRootPath").val();
	
	 if ($('#department_id').length) {
	        $('#department_id').select2();
	    }
	    if ($('#ddoCode').length) {
	        $('#ddoCode').select2();
	    }
	    
	    
	    if ($('#designationCmb').length) {
	        $('#designationCmb').select2();
	    }
	    
	    if ($('#billCmb').length) {
	    	$('#billCmb').select2();
	    }
	
	
					
				var a=0;
								$.ajax({
									type : "GET",
									url : "getDistrict",
									async : true,
									contentType : 'application/json',
									error : function(data) {
										//console.log(data);
									},
									success : function(data) {
										// //console.log(data);
										// alert(data);
										var len = data.length;
										if (len != 0) {
											//console.log(data);
											$(
													'#checkboxes')
													.empty();
											
											$(
											'#checkboxes')
											.append(
													"<label><input type='checkbox' id='selectAllDistrict'  class='checkBoxClass'   name='districtConcatCode' th:field=*{districtCode["+a+"]} value='1000' data='1'>---Select All---</label>");
									
										
											var temp = data;
											$
													.each(
															temp,
															function(
																	index,
																	value) {
															
																
																$(
																'#checkboxes')
																.append(
																		"<input type='checkbox' class='checkBoxClass' name='districtConcatCode' th:field=*{districtConcatCode["+index+"]}   data='0' value="
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
										
										
										$(".checkBoxClass").change(function(){
											  //alert("The paragraph was clicked.");
										//	alert($(this).val());
											
											 if($(this).prop("checked") == true){
									               /* alert("Checkbox is checked.");*/
									             
									    
									                if($(this).val()==1000)
									                {
									                $("div#checkboxes .checkBoxClass").prop('checked',true);
									                getMessage();
									                }
									                else
									                {
									                	   getMessage();
									                }
									                
									             
									                
									            }
									            else if($(this).prop("checked") == false){
									              /*  alert("Checkbox is unchecked.");*/
									                if($(this).val()==1000)
									                {
									                $("div#checkboxes .checkBoxClass").prop('checked',false);
									                $('#taluka').hide();
									                $('#checkboxes1').empty();
													 districtCode=[];
									                }
									            }
											
											
											
											});
									}
								});
					
					
								
								
								
								
								
								
			/*				
								
					$("#checkboxes")
					.change(
							function() {
							 $("#taluka").show(); 
							 
							 
							 $('div#checkboxes input:checked').each(function() {
								// console.log("on change excuted");
									    districtCode.push($(this).val());
										if (districtCode.length != '0') {
											$
													.ajax({
														type : "GET",
														url : "fetchTalukaList/"
																+ districtCode,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															 //console.log(data);
															 
															 
														},
														success : function(data) {
															//console.log(data);
															//talukaId
															
															
															
															var len = data.length;
															if (len != 0) {
											
																
																$('#checkboxes1')
																		.empty();
																$(
																'#checkboxes1')
																.append(
																		"<label><input type='checkbox' id='selectAllTaluka'  onchange='selectAllTaluka1();' value='1000' data='1'>---Select All---</label>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					
																		$('#checkboxes1')
																		.append(
																				"<input type='checkbox' class='checkBoxClass1' th:field=*{talukaConcatCode["+index+"]} name = 'talukaConcatCode' data='0'  value="
																						+ value[0]
																						+ " />" 
																						+ value[2]+" ("+value[3]+")" +
																								"<br>");
																					
																			
																					$(
																							'#talukaId')
																							.append(
																									"<option   data-id="
																											+ value[0]
																											+ " value="
																											+ value[0]
																									+ " >"+value[2]+" ("+value[3]+")</option>");
																					
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
						     });*/
				
					
					
					$("#districtId")
					.change(
							function() {
								  $("#taluka").show();
								
						     });
					
					
					

					$("#department_id")
							.change(
									function() {
										var departmentId = $("#department_id")
												.val();
										// alert("DDO CODE is "+departmentId);
										if (department_id != '') {
											
											
											$("#loaderMainNew").show();
											
											$
													.ajax({
														type : "GET",
														url : "mstSubDept/"
																+ departmentId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															// //console.log(data);
															$("#loaderMainNew").hide();
														},
														success : function(data) {
															// //console.log(data);
															// alert(data);
															
															$("#loaderMainNew").hide();
															var len = data.length;
															if (len != 0) {
																// //console.log(data);
																$(
																		'#subdepartment_id')
																		.empty();
																$(
																		'#subdepartment_id')
																		.append("<option value='1000'> PLEASE SELECT </option>");
																var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																				
																					$(
																							'#subdepartment_id')
																							.append(
																									"<option value="
																											+ value[0]
																											+ ">"
																											+ value[2]
																											+ "</option>");
																				});
															} else {
																$(
																		'#subdepartment_id')
																		.empty();
																$(
																		'#subdepartment_id')
																		.append(
																				"<option value='1000'> PLEASE SELECT  </option>");
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

					$("#subdepartment_id")
							.change(
									function() {
										var subDepartmentId = $(
												"#subdepartment_id").val();
										// alert("DDO CODE is "+departmentId);
										
									
										var departmentId = $("#department_id")
										.val();
										if (department_id != '') {
											$("#loaderMainNew").show();
											
											$
													.ajax({
														type : "GET",
														url : "allSubDeptDDO1/"
																+ subDepartmentId+"/"+departmentId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															 //console.log(data);
															$("#loaderMainNew").hide();
														},
														success : function(data) {
														 //console.log(data);
															// alert(data);
															$("#loaderMainNew").hide();
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

										var departmentId = $("#department_id")
												.val();

										var subDepartmentId = $(
												"#subdepartment_id").val();

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


					// binds to onchange event of your input field
					$('#fileUploads').bind('change', function() {

						// this.files[0].size gets the size of your file.
						var fsize = this.files[0].size;
						// alert(fsize);
						if (fsize > 2097152) {
							swal("Document size should be less then 1 MB");
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
					
					
					
				
					
					
					//start fetch old data
					
					 $.ajax({url: "mstGrOrder1", success: function(result){
					   console.log("fisrt time load");
					   var table = $('#oldData').dataTable();
				       table.fnClearTable();
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
									  var status='';
									  if(value[7]==1)
										  {
										  status="<span class='label label-success text-center'>Active</span>";
										  }
									  else
										  {
										  status="<span class='label label-danger text-center'>Inactive</span>";
										  }
									 
									  var filename;
									  var documentPath=value[8];
									  var fileDiv;
									  if(documentPath!=null){
										  filename = documentPath.substring(documentPath.lastIndexOf('/')+1);
										  fileDiv='<a href="viewGrOrderDocuments/'+value[0]+'" target="_blank">'+filename+'</a>';
									  }else{
										  fileDiv="--";
									  }
										 
								
								 
								       table.fnAddData([
									  "<center>"+value[1]+"</center>",
									  "<center>"+value[2]+"</center>",
									  "<center>"+value[4]+"</center>",
									  "<center>"+newd+"</center>",
									  fileDiv,
									/*  status,*/
									  "<div class='form-group text-center flex'><a href='../master/editMstGrOrder/"+value[0]+"' class='edit' data-val="+value[0]+">   <span class='glyphicon glyphicon-edit' id='edit'></span></a>    <a   onclick='ConfirmDeleteRecord("+value[0]+','+value[7]+");'> <span class='glyphicon glyphicon-trash' id='delete' style='width:0px;'></span> </a></div>"
								   ]);
								       
								       
									  
								       
								       
									  
									  
									  
									  
									  
									 /* $('#oldData').append("<tbody><tr><td><center>"+value[1]+"</center></td><td><center>"+value[2]+"</center></td><td><center>"+value[4]+"</td><td><center>"+newd+"</center></td>  <td>"+status+"</td> <td>" +
									  		"<div class='form-group'><div class='col-sm-offset-1 col-sm-1'><a href='../master/editMstGrOrder/"+value[0]+"' class='edit' data-val="+value[0]+">   <span class='glyphicon glyphicon-edit' id='edit'></span></a>  </div>  <a   onclick='ConfirmDeleteRecord("+value[0]+','+value[7]+");'> <span class='glyphicon glyphicon-trash' id='delete' style='width:0px;'></span> </a></div></td></tr></tbody>");
									  $('#oldData').DataTable();*/
									  
									
									  
							});
							} 
							
						}
						});
				});




function ConfirmDeleteRecord(schemeId,isactive) {
	if(isactive==1){
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
					      url: "../master/deleteMstGrOrder/"+schemeId,
					      async: true,
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
	} else if(isactive==0) {
		swal({
	    	  title: 'Not allowed !',
			  text: 'This record is already deleted',
			  icon: "warning",
	    });
	}
}


$.validator.addMethod('filesize', function (value, element, param) {
    return this.optional(element) || (element.files[0].size <= param * 1000)
}, 'File size must be less than {1} MB');


$("form[name='mstGrOrder']").validate({
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
    		isApplicableForEndDt:{
    			required:function(){
    				if($("input[name='isApplicableForEndDt']:checked").val()=='Yes' || $("input[name='isApplicableForEndDt']:checked").val()=='No'){
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
    submitHandler: function(form) {
    	 if($("input[name='isApplicableLocation']:checked").val()=="Yes" && $("#districtConcatCode").val()=="0"){
       	  addErrorClass($("#districtConcatCode"),"Please Select District");
         }else{
       	  $("#loaderMainNew").show();
       	  form.submit();
         }	
    }
  });

