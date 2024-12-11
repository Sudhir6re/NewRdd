jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
	//$(".mstposttbl").DataTable();
});
$(".numbers" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});
	  

$(document)
		.ready(
				function() {
					$("#departmentCode")
							.change(
									function() {
										var departmentId = $("#departmentCode")
												.val();
										// alert("DDO CODE is "+departmentId);
										if (departmentCode != '') {
											$
													.ajax({
														type : "GET",
														url : "mstSubDept/"
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
																		'#subdepartmentCode')
																		.empty();
																$(
																		'#subdepartmentCode')
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
																							'#subdepartmentCode')
																							.append(
																									"<option value="
																											+ value[0]
																											+ ">"
																											+ value[2]
																											+ "</option>");
																				});
															} else {
																$(
																		'#subdepartmentCode')
																		.empty();
																$(
																		'#subdepartmentCode')
																		.append(
																				"<option value='0'>Please Select</option>");
																swal("Record not found !!!");
															}
														}
													});
										}

									});

					$("#subdepartmentCode")
							.change(
									function() {
										var subDepartmentId = $(
												"#subdepartmentCode").val();
									//	 alert("DDO CODE is "+subDepartmentId);
										if (subDepartmentId != '') {
											$
													.ajax({
														type : "GET",
														url : "../master/allSubDeptDDO/"
																+ subDepartmentId,
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
																
																$('#districtId')
																.empty();
																
																
/*//																/*
//																 * $('#ddoCode')
//																 * .empty();
//																 * $('#ddoCode')
//																 * .append( "<option
//																 * value='0'>Please
//																 * Select</option>");
//																 */
																$('#districtId').append("<option value='0'>Please Select</option>");
																
															var temp = data;
																$
																		.each(
																				temp,
																				function(
																						index,
																						value) {
																					console.log("date is"+value[2]);
																					/*
																					 * $(
																					 * '#ddoCode')
																					 * .append( "<option
																					 * value=" +
																					 * value[0] + "
																					 * data_id
																					 * ='" +
																					 * value[3] + "' >" +
																					 * value[1] + "</option>");
																					 */
																				$(
																					'#districtId')
																					.append(
																							"<option value="
																									+ value[1]
																									+ " >"
																									+ value[2]
																									+ "</option>");
																					$(
																					'#grOrder')
																					.append(
																							"<option value="
																									+ value[3]
																									+ " data-id="
																									+ value[2]
																									+ ">"
																									+ value[1]
																									+ "</option>");
																					
																				});
																$
																.ajax({
																	type : "GET",
																	url : "../master/allGrOrderbyDDO1/"
																			+ subDepartmentId,
																	async : true,
																	contentType : 'application/json',
																	error : function(data) {
																		 console.log(data);
																	},
																	 /*
																		 * beforeSend:
																		 * function() {
																		 * alert("ajax
																		 * before
																		 * request"); },
																		 */
																	success : function(data) {
																		
																		var len = data.length;
																		if (len != 0) {
																			//console.log("date is"+value[2]);
																			$('#grOrderId')
																					.empty();
																			$('#grOrderId')
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
																										'#grOrderId')
																										.append(
																												"<option value="
																														+ value[3]
																														+ " data-id="
																														+ value[2]
																														+ ">"
																														+ value[1]
																														+ "</option>");
																							});
																		} else {
																			$('#grOrderId')
																					.empty();
																			$('#grOrderId')
																					.append(
																							"<option value='0'>Please Select</option>");
																			swal("Gr order data not found !!!");
																		}
																	}
																	});
																
																
																
																
																
																
															} else {
																/*
																 * $('#ddoCode')
																 * .empty();
																 * $('#ddoCode')
																 * .append( "<option
																 * value='0'>Please
																 * Select</option>");
																 */
																$('#grOrderId')
																.empty();
																
																$('#districtId')
																.empty();
																
														$('#districtId')
																.append(
																		"<option value='0'>Please Select</option>");
																
																swal("District Record not found !!!");
															}
														}
													});
										}

									});
					
					/* DistrictWise DDO */
					
/*					$("#districtId")
					.change(
							function() {
								var districtId = $("#districtId").val();
							// alert("DDO CODE is "+districtId);
								if (districtId != '') {
									$
											.ajax({
												type : "GET",
												url : "../master/allDistrictWiseDDO/"
														+ districtId,
												async : true,
												contentType : 'application/json',
												error : function(data) {
													 console.log(data); 
												},
												success : function(data) {
													 console.log(data);
													var len = data.length;
													if (len != 0) {
														
														
														$('#ddoId')
																.empty();
														$('#talukaId').empty();
														$('#ddoCodeLevel').empty();
														$('#ddoId')
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
																					'#ddoId')
																					.append(
																							"<option    data-level="+value[9]+"       value="
																									+ value[0]
																									+ " data_id ='"
																									+ value[3]
																									+  "' data_code ='"
																									+ value[1]+
																									"' >"
																									+ value[1]+"_"+value[6]+"_"+value[5]
																									+ "</option>");
																			$(
																			'#talukaId')
																			.append(
																					"<option value="
																							+ value[0]
																							+ " >"
																							+ value[0]
																							+ "</option>");
																		});
													} else {
														$('#talukaId').empty();
														$('#ddoCodeLevel').empty();
														$('#ddoId').empty();
														
														$('#ddoId').append(
																		"<option value='0'>Please Select</option>");
														
														swal("Record not found !!!");
													}
												}
											});
								}

							});*/
					
					$("#ddoId")
							.change(
									function() {
										var subDepartmentId = $(
												"#subdepartmentCode").val();
										var ddoId = $("#ddoId").val();
										
										var departmentId = $(
										"#departmentCode").val();

										var ddoId = $(
												"#ddoId option:selected")
												.text();
										
										
										$("#ddoCode").val($('option:selected', this).attr('data_code'));
										
										
										$("#ddoForDB").val(ddoId);

										// alert(" ddoCode "+ddoCode);
										if (departmentCode != '') {
											$
													.ajax({
														type : "GET",
														url : "../master/allGrOrderbyDDO/"
																+ ddoId
																+ "/" + subDepartmentId + "/"+departmentId,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
														},
														success : function(data) {
														console.log(data);
														}
													});
										}
									});
					$("#grOrderId").change(function() {

						var date = $('option:selected', this).attr('data-id');
						$('#grOrderDate').val(date);
						
						 var d1 = new Date(parseInt(date)); 
			                console.log("mi"+d1.toString());
			                
			                var dt=d1.getDate();
			               
			                
			                var mn=d1.getMonth();
			                mn++;
			                var yy=d1.getFullYear();
			               // document.getElementById("grOrderDate").value=dt+"/"+mn+"/"+yy;
			                
			            // var date = new Date(parseInt(time));
			                
			                /* var today = yy+"-"+(mn)+"-"+(dt) ; */
			                
			                if(dt<10){
			                	dt='0'+dt;
			                }
			                if(mn<10){
			                	mn='0'+mn;
			                }
			                
			                var today = yy +"-"+mn+"-"+ dt ;
			                console.log("gr order date="+today);

			                $('#grOrderDate').val(today);
			                
					});
					
					$("#ddoId").change(
							function() {

								var officeName = $('option:selected', this)
										.attr('data_id');
								
								var level = $('option:selected', this)
								.attr('data-level');
								
								
								// alert(officeName);
								$('#officeName').val(officeName)
								
								
							
								$( "#ddoCodeLevel" ).val(level);
								$( "#ddoCodeLevel" ).prop( "disabled", true );
							
							});
					
					
					
					$.ajax({url: "mstPostList", success: function(result){
						   console.log(result);
						 // $('#oldData').empty();
						    var len = result.length;
						    var i=0;
							if (len != 0) 
							{
								 var temp = result; 
								$.each( temp,
										  function( index, value ){
									
									  var status='';
									  if(value[10]==1)
										  {
										  status="<span class='label label-success text-center'>Active</span>";
										  }
									  else
										  {
										  status="<span class='label label-danger text-center'>Inactive</span>";
										  }
									      
									  var d1 = new Date(parseInt(value[5])); 
						                console.log("mi"+d1.toString());
						                
						                var dt=d1.getDate();
						                
						                
						                
						                var mn=d1.getMonth();
						                mn++;
						                var yy=d1.getFullYear();
						               // document.getElementById("grOrderDate").value=dt+"/"+mn+"/"+yy;
						                
						            // var date = new Date(parseInt(time));
						                
						                if(dt<10){
						                	dt='0'+dt;
						                }
						                if(mn<10){
						                	mn='0'+mn;
						                }
						                
						                var today = dt+"-"+(mn)+"-"+(yy) ;
									  
								// $('#oldData').DataTable().fnAddData(1,2,3,4,5,6,7,8,9,10,11,12,13);
										
									  
									  $('#oldData').append("<tbody class='panel' ><tr class='btnSelect'  data-parent='#oldData' data-toggle='collapse' data-target=#demo"+i+" ><td><center>"+value[8]+"</center></td><td><center>"+value[9]+"</center></td><td><center>"+value[0]+"</center></td><td><center>"+value[1]+"</center></td><td  class='example'><center>"+value[2]+"</td><td><center>"+value[4]+"</center></td><td><center>"+value[11]+"</center></td><td><center>"+today+"</center><td><center></center></td><td><center>"+value[3]+"</center></td></td><td><center>"+value[7]+"</center></td><td ><center>"+status+"</center></td><td>" +
										  		"<div class='form-group'><div class='col-sm-offset-1 col-sm-1'>  <a  href='../master/editMstPost/"+value[6]+"' ><span class='glyphicon glyphicon-edit' id='edit' style='position: relative; right: 5px;'></span></a></div>       <a  onclick='ConfirmDeleteRecord("+value[6]+","+value[10]+");'> <span class='glyphicon glyphicon-trash' id='delete' style='width: 0px;'></span> </a></div></td> <td style='display:none;'>"+value[6]+"</td>   <td class="+i+" styl" +
										  				"e='display:none;'>"+i+"</td></tr>    <tr id=demo"+i+" class='collapse'><td class="+i+"  colspan='11'></td></tr>  </tbody> ");
										  
											i++;
										
										  });
								
								} 
							
							/*$(".mstposttbl").DataTable();*/
							}
							});
					
					
					 $("#oldData").on('click', '.btnSelect', function() 
							    {
							      // get the current row
							      var currentRow = $(this).closest("tr");
							      
							      var classid = currentRow.find("td:eq(14)").text(); 
							  
							
							      var postId = currentRow.find("td:eq(13)").text(); // get
																					// current
																					// row
																					// 2nd
																					// table
																					// cell
																					// TD
																					// value
							   
									$.ajax({url: "../master/mstPostDetailsList/"+postId,error : function(data) {
										 // console.log("error");
											// console.log(data);
										
									}, success: function(result){
										// console.log("latest controller
										// data");
										// console.log(result);
										 // $('#oldData').empty();
										    var len = result.length;
										    var i=0;
										    
											if (len != 0) 
											{
												 var temp = result; 
												$.each( temp,
														  function( index, value ){
													i++;
													    
															   $('.'+classid).append('<ol >'+i+'. '+ value[1]+'</ol>');
															   
														// $('#'+res[1]).append(''+
														// value[1]);
															   
															
														  });
											} 
											}
											});
							    });

				});



function ConfirmDeleteRecord(editId,isActive) {
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
					      url: "../master/deleteMstPost/"+editId,
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






/*$('#btnCancel').click(
	    function(){
	   	 var ddoCodeLevel=$('#ddoCodeLevel').val('');
	   	 var numberOfPost=$('#numberOfPost').val('');
	   	 var numberOfPost = $('#grOrderId').val(''); 
	   	 var numberOfPost = $('#remarks').val(''); 
	   	 var numberOfPost = $('#designationCode').val(''); 
	    });
*/
var countryNameError;
var stateNameError;
var districtNameError;
var talukaNameError;
var villageNameError;
var addressNameError;
var pinCodeError;
var locationNameError;
var DDONameError;
var GROrderNameError;
var DesignationNameError;
var TypeofPostNameError;
var numberOfPostNameError;

var warningError;
if($('#language').val()=="en"){
	deptCodeError="Please enter department code !!!";
	deptShortNameError="Please enter Sub department name !!!";
	deptNameEnglishError="Please enter department name in english !!!";
	deptNameMarathiError="Please enter department name in marathi !!!";
	districtNameError="Please enter District name !!!";
	DDONameError="Please enter DDO Level 1 code !!!";
	GROrderNameError="Please enter GR Order No !!!";
	DesignationNameError="Please enter Designation !!!";
	TypeofPostNameError="Please enter Type of Post !!!";
	numberOfPostNameError="Please enter Number of Post !!!";
	warning="warning !";
}else{
	deptCodeError="कृपया विभाग कोड प्रविष्ट करा  !!!";
	deptShortNameError="कृपया विभाग लहान नाव प्रविष्ट करा !!!";
	deptNameEnglishError="कृपया विभागाचे नाव इंग्रजीमध्ये प्रविष्ट करा !!!";
	deptNameMarathiError="कृपया मराठी मध्ये विभाग नाव प्रविष्ट करा !!!";
	warning="चेतावणी !";
}

$("#next")
.click(
		function(e) {
			e.preventDefault();
			var next = $(this).attr("data-id");
			
			
			
			//alert("next"+next);
			$.ajax({
				
				type : "GET",
				url : "../master/fetchDataByRange/"
						+ next,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				// alert("error");
				},
				success : function(data) {
				//	alert(data);
					// alert("success");
					var len = data.length;
					if (len != 0) {
					next=parseInt(next)+parseInt(len);
					
					$('#prev').attr('data-id',next);
					
				
					
					
					$('#next').attr('data-id',next);
					
					 next = $(this).attr("data-id");
                console.log(next);
						
						$("#oldData tbody").empty();
						for(var i=0;i<len;i++){
							
							
							
							
							var status="";
							var edit="";
							var del="";
							
							if(data[i].isActive=='1'){
								status="<span class='label label-success'>Acive</span>";
							}else{
								status="<span class='label label-danger'>In Acive</span>";
							}
							
						
									
							edit="<a class='edit'   data-val="+data[i].isActive+"  data="+data[i].postId+" data-toggle='tooltipEdit' title='Edit' href='../master/editMstPost/"+data[i].postId+"' ><span class='glyphicon glyphicon-edit'></span></a>";
							del="<a class='delete' data-val="+data[i].isActive+"  data="+data[i].postId+" data-toggle='tooltipDelete' title='Edit' href='../master/editMstPost/"+data[i].postId+"' ><span class='glyphicon glyphicon-trash'></span></a>";
							
						
							
							//th:data="${varDept.postId}" class="PostId"
							
							$("#oldData tbody").append("<tr><td class='glyphicon glyphicon-plus PostId'  data='"+data[i].postId+"'></td>"
									          +"<td>"+data[i].departmentNameEn+"</td>"
									          +"<td  class='PostId' data='"+data[i].postId+"'>"+data[i].subDepartmentname+"</td>"
									          + "<td class='PostId' data='"+data[i].postId+"'>"+data[i].districtName+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].ddoCode+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].postId+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].designationName+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].santionOrederNumber+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].officeName+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].orderDate+"</td>" 
									          +"<td class='PostId' data='"+data[i].postId+"'>"+status+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+edit+'  '+del+"</td></tr>");
							
						}
						
					
					}
				}
			});
	
						
			
		});



/*$(".PostId").hover(function(e) {
	var postId= $(this).attr("data");
	
	$.ajax({
		type : "GET",
		url : "../master/fetchPostnameOnPostId/"
				+ postId,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			 console.log(data);
		// alert("error");
		},
		success : function(data) {
			 console.log(data);
			 
			 $("#postNameModel").modal('show');
			 $("#postname").append();
			 
			 
			
				var len=data.length;
				
				
				
				
				
				if (len != 0) 
				{
					 var temp = data; 
					$.each( temp,
							  function( index, value ){
					console.log("firstloop"+value[0]);
						
					//$("#tblPostName tbody").append("<tr>"+value[0]+"<tr>");
								$("#test").text(value[0]);   
								
							  });
				} 
			 
			 
		}
	});
});*/

$('#oldData').on('click', '.glyphicon-minus', function () {
	$(".showTable").hide();
	$(this).removeClass("glyphicon-minus");
	$(this).addClass("glyphicon-plus");
	$(this).addClass("PostId");
});

$('#oldData').on('click', '.PostId', function () {
	$(".loaderMainNew").show();
	
	$(this).removeClass("glyphicon-plus");
	$(this).removeClass("PostId");
	$(".showTable").hide();
	$(this).removeClass("showTable");

	$(this).addClass("glyphicon-minus");
	
	 var child = $(this).closest('td');  
	 
	// child.after("<tr><td>ggg</td></tr>");
	 
	 var postId= $(this).attr("data");
		
		$.ajax({
			type : "GET",
			url : "../master/fetchPostnameOnPostId/"
					+ postId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			// alert("error");
			},
			success : function(data) {
				 console.log(data);
				 
				 
			/*	 $("#postNameModel").modal('show');*/
				 $("#postname").append();
				 
				 
				
					var len=data.length;
					
					if (len != 0) 
					{
						$('.showTable tbody').empty();
						 var temp = data; 
						$.each( temp,
								  function( index, value ){
						console.log("firstloop"+value[0]);
							
						//$("#tblPostName tbody").append("<tr>"+value[0]+"<tr>");
									$("#test").text(value[0]);   
									
								
									child.after('<table cellpadding="5" class="showTable" cellspacing="0" border="0" style="padding-left:50px;">'+
							        '<tr>'+
							            '<td>'+value[0]+'</td>'+
							        '</tr>'+
							    '</table>');
									
									
									
								  });
						$(".loaderMainNew").hide();
						$(".showTable").show();
					} 
				 
				 
			}
		});
});







	
/*$(document).on('click', '.PostId', function(){ 
	alert($(this).attr("data"));
	
var postId= $(this).attr("data");
	
	$.ajax({
		type : "GET",
		url : "../master/fetchPostnameOnPostId/"
				+ postId,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			 console.log(data);
		// alert("error");
		},
		success : function(data) {
			 console.log(data);
			 
			 $("#postNameModel").modal('show');
			 $("#postname").append();
			 
			 
			
				var len=data.length;
				
				if (len != 0) 
				{
					 var temp = data; 
					$.each( temp,
							  function( index, value ){
					console.log("firstloop"+value[0]);
						
					//$("#tblPostName tbody").append("<tr>"+value[0]+"<tr>");
								$("#test").text(value[0]);   
								
							  });
				} 
			 
			 
		}
	});
	
});*/


$("#prev").click(
		function(e) {
			e.preventDefault();
			var next = $(this).attr("data-id");
			
			next=parseInt(next)-10;
			
			
			if(parseInt(next)>=0){
			
			$('#prev').attr('data-id',next);
			
			
			
			//alert("next"+next);
			$.ajax({
				
				type : "GET",
				url : "../master/fetchDataByRange/"
						+ next,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				// alert("error");
				},
				success : function(data) {
				//	alert(data);
					// alert("success");
					var len = data.length;
					if (len != 0) {
					//next=parseInt(next)+parseInt(len);
					
				
					
					
				//	$('#next').attr('data-id',next);
					
					// next = $(this).attr("data-id");
                //console.log(next);
						
						$("#oldData tbody").empty();
						for(var i=0;i<len;i++){
							
							
							
							
							var status="";
							var edit="";
							var del="";
							
							if(data[i].isActive=='1'){
								status="<span class='label label-success'>Acive</span>";
							}else{
								status="<span class='label label-danger'>In Acive</span>";
							}
							
						
									
							edit="<a class='edit'   data-val="+data[i].isActive+"  data="+data[i].postId+" data-toggle='tooltipEdit' title='Edit' href='../master/editMstPost/"+data[i].postId+"' ><span class='glyphicon glyphicon-edit'></span></a>";
							del="<a class='delete' data-val="+data[i].isActive+"  data="+data[i].postId+" data-toggle='tooltipDelete' title='Delete' href='../master/editMstPost/"+data[i].postId+"' ><span class='glyphicon glyphicon-trash'></span></a>";
							
						
							
							
							
							$("#oldData tbody").append("<tr><td class='glyphicon glyphicon-plus PostId'  data='"+data[i].postId+"'></td>"
									          +"<td>"+data[i].departmentNameEn+"</td>"
									          +"<td>"+data[i].subDepartmentname+"</td>"
									          + "<td>"+data[i].districtName+"</td>"
									          +"<td>"+data[i].ddoCode+"</td>"
									          +"<td>"+data[i].postId+"</td>"
									          +"<td>"+data[i].designationName+"</td>"
									          +"<td>"+data[i].santionOrederNumber+"</td>"
									          +"<td>"+data[i].officeName+"</td>"
									          +"<td>"+data[i].orderDate+"</td>" 
									          +"<td>"+status+"</td>"
									          +"<td>"+edit+' '+del+"</td></tr>");
							
						}
						
					
					}
				}
			});
			
			
			//ajax end
		}
	
						
			
		});






$("#search").click(
		function(e) {
			e.preventDefault();
			var ddocode = $('#postSearch').val();
			
			
			var postId= $(this).attr("data");
			//alert("next"+ddocode);
			
			$.ajax({
				
				type : "GET",
				url : "../master/fetchPostByDDO/"
						+ddocode,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
				//alert("error");
				},
				success : function(data) {
				// alert("success");
					$("#oldData tbody").empty();
					var len = data.length;
					if (len != 0) {
				
						$("#oldData tbody").empty();
						for(var i=0;i<len;i++){
							var status="";
							var edit="";
							var del="";
							
							if(data[i].isActive=='1'){
								status="<span class='label label-success'>Acive</span>";
							}else{
								status="<span class='label label-danger'>In Acive</span>";
							}
							edit="<a class='edit'   data-val="+data[i].isActive+"  data="+data[i].postId+" data-toggle='tooltipEdit' title='Edit' href='../master/editMstPost/"+data[i].postId+"' ><span class='glyphicon glyphicon-edit'></span></a>";
							del="<a class='delete' data-val="+data[i].isActive+"  data="+data[i].postId+" data-toggle='tooltipDelete' title='Delete' href='../master/editMstPost/"+data[i].postId+"' ><span class='glyphicon glyphicon-trash'></span></a>";
							$("#oldData tbody").append("<tr><td class='glyphicon glyphicon-plus PostId'  data='"+data[i].postId+"'></td>"
									          +"<td>"+data[i].departmentNameEn+"</td>"
									          +"<td>"+data[i].subDepartmentname+"</td>"
									          + "<td>"+data[i].districtName+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].ddoCode+"</td>"
									          +"<td class='PostId' data='"+data[i].postId+"'>"+data[i].postId+"</td>"
									          +"<td>"+data[i].designationName+"</td>"
									          +"<td>"+data[i].santionOrederNumber+"</td>"
									          +"<td>"+data[i].officeName+"</td>"
									          +"<td>"+new Date(data[i].orderDate).toJSON().slice(0,10)+"</td>" 
									          +"<td>"+status+"</td>"
									          +"<td>"+edit+' '+del+"</td></tr>");
							
						}
						
					
					}
					else{
						
						swal("Record Not Found..!!");
					}
				}
			
		});

});


////for taluka

function findTalukaName() {
	
	var districtId = $("#districtId").val();
	
	if(districtId!='')
		{
					$.ajax({
					      type: "GET",
					      url : "../master/allDistrictWiseDDO/"
								+ districtId,
					      async: true,
					      error: function(data){
					    	  swal("error");
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      },
					      success: function(data){
					    	  $('#talukaId')
								.empty();
					    	  var temp = data;
								$
								.each(
										temp,
										function(
												index,
												value) {
											$(
											'#talukaId')
											.append(
													"<option value="
															+ value[0]
															+ " >"
															+ value[0]
															+ "</option>");
										});
					      }
					 });
		}
	else
		{
		swal("Record not found");
		}
			     
	} 


//foe level 1 DDO name
function findLevel1DDO() {
	//alert("testing ddo");
	var districtId = $("#districtId").val();
	//alert("districtId"+districtId);
	if(districtId!='')
		{
					$.ajax({
					      type: "GET",
					      url : "../master/allDistrictLevel1DDO/"
								+ districtId,
					      async: true,
					      error: function(data){
					    	  swal("error");
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      },
					      success: function(data){
					    		
					    	  var temp = data;
								$
								.each(
										temp,
										function(
												index,
												value) {
											$(
											'#ddoId')
											.append(
													"<option    data-level="+value[9]+"       value="
															+ value[0]
															+ " data_id ='"
															+ value[3]
															+  "' data_code ='"
															+ value[1]+
															"' >"
															+ value[1]+"_"+value[6]+"_"+value[5]
															+ "</option>");
										});
					      }
					 });
		}
	else
		{
		swal("Record not found");
		}
			     
	} 



$("form[name='mstPost']").validate({
    // Specify validation rules
    rules: {
    	departmentCode:
    	{
    		required:true,
    		min:1
    	},
    	subDepartmentCode:
    	{
    		required:true,
    		min:1
    	},
    	districtCode:
    	{
			/*required :true,
			pattern: /^[A-Za-z]+$/,   ///^[A-Z][A-Z\s]*$/
			min: 1, */
    		required:true,
    		min:1
		},
    	talukaId:
    	{
			required :true,
			pattern: /^[A-Za-z.\s]+$/,   ///^[A-Z][A-Z\s]*$/
			maxlength: 1000, 
		},
    	ddoId:
    	{
    		required:true,
    		min:1
    	},
    	
    	//officeName: "required",
    	grOrderCode:
    	{
    		required:true,
    		min:1
    	},
    	
    	grOrderDate: "required",
    	designationCode:
    	{
    		required:true,
    		min:1
    	},
    	typeOfPost:
    	{
    		required:true,
    		min:1
    	},
    	numberOfPost: "required",
    },
    // Specify validation error messages
    messages: {
    	departmentCode: "Please Select Department",
    	subDepartmentCode: "Please Select Corporation",
    	districtCode: "Please Select District",
    	talukaId: "Please Select Taluka",
    	ddoId: "Please Select DDO",
    //	officeName: "Please Select Level",
    	grOrderCode: "Please Select GR No",
    	grOrderDate: "Please Enter GR Date",
    	designationCode: "Please Select Designation",
    	typeOfPost: "Please Select Type Of Post",
    	numberOfPost: "Please Enter number of Post",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });