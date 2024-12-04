
$(document).ready(function(){
	
	
	
	var successFlag = $("#message").val();

	if (successFlag == "success") {
        $("#result").show();
		alert("Data is saved successfully. Please Navigate to NPS > DCPS Legacy > Verify DCPS Legacy Data");
		//url = "ifms.htm?actionFlag=LegacyDataEntry";
		//self.location.href = url;
	}
});


$("#txtSevaarthId")
				.keyup(
						function() {
							var sevaarthId = $("#txtSevaarthId").val();
							var empName = $("#txtEmployeeName").val();
							var dcpsId = $("#txtDcpsId").val();
							var empId = $("#empId").val();
							/*if (sevaarthId.length == 0) {
								document.getElementById("searchDiv").innerHTML = "";
								document.getElementById("searchDiv").style.border = "0px";
								return;
							}*/
							
							
							
							if (sevaarthId != ''  || empName!='' || dcpsId!=''  /*&& sevaarthId.length != 0 */    ) {
								
								

								if(sevaarthId!=''){
									sevaarthId=sevaarthId;
									
								}else{
									sevaarthId=1;
								}
								
								if(empName!=''){
									empName=empName;
									empId=empId;
									
								}else{
									empName=2;
								}
								
								
								if(dcpsId!=''){
									dcpsId=dcpsId;
								}else{
									dcpsId=3;
								}
								
								
								
								$("#loaderMainNew").show();
								$.ajax({
											type : "POST",
											url : "../master/getEmpInfoBySevaarthId/"
													+ sevaarthId+"/"+empName+"/"+dcpsId,
											async : false,
											contentType : 'application/json',
											error : function(data) {
												console.log(data);
												$("#loaderMainNew").hide();
											},
											success : function(data) {
												console.log(data);
												$("#loaderMainNew").hide();
												document
														.getElementById("searchDiv").innerHTML = "";
												for (var i = 0; i < data.length; i++) {
													//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
													//$("#searchDiv").css("border:1px solid #A5ACB2;");
													var empId=1;
													
													if(sevaarthId!='' && sevaarthId!='1'){
														sevaarthId=data[i].sevaarthId;
																												
													}else{
														sevaarthId=1;
														$("#txtSevaarthId").prop("disabled",true);
														

													}
													
													if(empName!='' &&  empName!='2'){
														empName=data[i].employeeName;
														empId=data[i].employeeid;
													}else{
														empName=2;
														$("#txtEmployeeName").prop("disabled",true);
													}
													
													
													if(dcpsId!='' && dcpsId!='3'){
														dcpsId=data[i].dcpsId;
													}else{
														dcpsId=3;
														$("#txtDcpsId").prop("disabled",true);
													}
													
													
													$("#searchDiv").append("<li class='empdata' empid='"+empId+"' empname='"+empName+"' empsevaathid='"+sevaarthId+"'   empsetDcpsId='"+dcpsId+"'>"
																			+ data[i].employeeName
																			+ "-"
																			+ data[i].sevaarthId
																			+ "</li>");
													$("#searchDiv")
															.css(
																	"border:1px solid #A5ACB2;");
												}
											}
										});
							}else{
								swal("Please enter any one parameter ");
							}
						});


$('body').on('click', '.empdata', function() {
	$("#sevaarthId").val($(this).attr("empname"));
	var sevaarthId=$(this).attr("empsevaathid");  
	var empname=$(this).attr("empname");  
	var empsetDcpsId=$(this).attr("empsetDcpsId");  
	var empid=$(this).attr("empid");  
	
	
	
	
	if(sevaarthId!='' && empname=="2" && empsetDcpsId=="3" ){
		$("#txtSevaarthId").val(sevaarthId);
	}
	
	if(empname!='' && sevaarthId=="1" && empsetDcpsId=="3" ){
		$("#txtEmployeeName").val(empname);
		$("#empId").val(empid);
	}
	
	
	if(empsetDcpsId!='' && empname=="2" && sevaarthId=="1" ){
		$("#txtDcpsId").val(empsetDcpsId);
	}
	
	
	document.getElementById("searchDiv").innerHTML = "";
});


$("#btnSearch").click(function() {
			var sevaarthId = $("#txtSevaarthId").val();
			var empName = $("#txtEmployeeName").val();
			var dcpsId = $("#txtDcpsId").val();
			
			
			var period='<select name="period" id="period" class="form-control"> <option value="0">-------Selected--------</option>';
			var reasuryList=findAllDCPSLegacyTreasury();
			
			 /* var period='<select name="period" id="period" class="form-control"> '+
				'<option value="">-------Selected--------</option> '+
				'<option value="10001198212">Before 31-03-2016</option> '+
				'<option value="10001198216">01-04-2016 to 28-02-2018</option> '+
		        '</select>'; */
			 
			for(var i=0;i<reasuryList.length;i++){  //legacyTreasuryId  //treasuryDesc
				period+='<option value="'+reasuryList[i].legacyTreasuryId+'">'+reasuryList[i].treasuryDesc+'</option>';
			}
			period+='</select>'; 
			
			
			
			console.log(reasuryList+period);
			
			if (sevaarthId != ''  || empName!='' || dcpsId!=''  /*&& sevaarthId.length != 0 */    ) {

				if(sevaarthId!=''){
					sevaarthId=sevaarthId;
				}else{
					sevaarthId=1;
				}
				
				if(empName!=''){
					empName=empName;
				}else{
					empName=2;
				}
				
				
				if(dcpsId!=''){
					dcpsId=dcpsId;
				}else{
					dcpsId=3;
				}
				
				
				$("#loaderMainNew").show();
				$
						.ajax({
							type : "POST",
							url : "../master/getEmpInfoBySevaarthId/"
									+ sevaarthId+"/"+empName+"/"+dcpsId,
							async : false,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
								$("#loaderMainNew").hide();
							},
							success : function(data) {
								console.log(data);
								 $('#dcpsLagacyTable').dataTable().fnClearTable();
								  $('#dcpsLagacyTable').show();
								$("#loaderMainNew").hide();
								document
										.getElementById("searchDiv").innerHTML = "";
								for (var i = 0; i < data.length; i++) {
									
									
									  
									  var employeeName=data[i].employeeName;
									  var dcpsId=data[i].dcpsId;
									  var sevaarthId=data[i].sevaarthId;
									  
									  $("#sevaarthId").val(data[i].sevaarthId);
									  
									  var employeeId=data[i].employeeid;
									
									  var doj=new Date(data[i].doj).toLocaleDateString();
									  var serviceEndDate=new Date(data[i].serviceEndDate).toLocaleDateString();
									  
									  var dcpsNo=data[i].dcpsNo;
									  
									  
									 
										
										var hiddenData='<input type="hidden" name="dcpsId" value="'+dcpsId+'" />'+
										'<input type="hidden" name="dcpsEmpId" value="'+employeeId+'" />';
											
										
										
									
									/*  var period='<select name="period" id="period" class="form-control"> '+
											'<option value="">-------Selected--------</option> '+
											'<option value="10001198212">Before 31-03-2016</option> '+
											'<option value="10001198216">01-04-2016 to 28-02-2018</option> '+
									        '</select>'; */
									
									  var contriStartDt='<input type="date" name="contriStartDate" id="contriStartDate" '+
											'value="" maxlength="10" class="form-control">';
									  
									  var contriEndDt='<input type="date" onchange="validateDates();" name="contriEndDate" id="contriEndDate" '+
											'value="" maxlength="10" class="form-control">';
									  
                                      var empContri='<input type="text" id="empContri" name="empContri" '+
											'class="form-control float">';
                                      
                                      var employerContri='<input type="text" id="employerContri" name="employerContri" '+
											'class="form-control float">';
                                      
                                      var empInterest='<input type="text" id="empInt" name="empInt" '+
											'class="form-control float">';
											
                                      var employerInterest='<input type="text" id="employerInt" '+
											'name="employerInt" class="form-control float">';
                                      
                                      
                                      var total='<input type="text" id="total" '+
										'name="total" class="form-control total float" value="0">'+hiddenData;
                                      
									  $('#dcpsLagacyTable').dataTable().fnAddData([employeeName,dcpsId,sevaarthId,doj,serviceEndDate,period,contriStartDt,contriEndDt,empContri,employerContri,empInterest,employerInterest,total]);
										
									    $("#txtSevaarthId").val("");
										 $("#txtEmployeeName").val("");
										 $("#txtDcpsId").val("");
									
									
									$("#searchDiv").css("border:1px solid #A5ACB2;");
								}
							}
						});
			}else{
				swal("Please enter any one parameter ");
			}
		});







$('body').on('click', '#eeeeee', function() {
      var period=$("#period").val();
      var data=findDcpcLegacyDataByPeriod(period);
      if(data.length>0){
    	  
    	  console.log(data[i]);
    	  
    	  $('#dcpsVerifyLagacyTable').dataTable().fnClearTable();
		  $('#dcpsVerifyLagacyTable').show();
		  
		  
		  var dcpsLegacyId=data[i].dcpsLegacyId;
		  var empName=data[i].dcpsLegacyId;
		  var dcpsId=data[i].dcpsId;
		  var sevaarthId=data[i].sevaarthId;
		  var period=data[i].period;
		  var empContri=data[i].empContri;
		  var employerContri=data[i].employerContri;
		  var empInt=data[i].empInt;
		  var employerInt=data[i].employerInt;
		  var total=data[i].total;
		  var remark="<input type='text'  id='"+data[i].dcpsLegacyId+"' class='form-control' />";
		  
		  var apprBtn='<input type="button" name="apprBtn" id="apprBtn" value="Approve"  onclick="approve('+data[i].dcpsLegacyId+')" class="buttontag btn">';
		  var rejectBtn='<input type="button" name="rejectBtn" id="rejectBtn" onclick="reject('+data[i].dcpsLegacyId+')" value="Reject" class="buttontag btn">';
		  
		    
		  
		  
		  for(var i=0;i<data.length;i++){
			  $('#dcpsVerifyLagacyTable').dataTable().fnAddData([empName,dcpsId,sevaarthId,period,empContri,employerContri,empInt,employerInt,total,remark,apprBtn,rejectBtn]);
		  }
		  
    	  
      }else{
    	  swal("No record found !!!");
      }
});


function approve(id){
//  alert(id);	
  var flag=approveDcpsLegacyData(id);
  if(flag=="success" || flag=="0"  ){
		 swal("Data Approved Successfully !!!");
		 $('#dcpsVerifyLagacyTable').dataTable().fnClearTable();
	 }else{
		 swal("Something went wrong please try again");
	 }
}



function reject(id){
 // alert(id);
  $("#"+id).prop("readonly",false);
  var remark=$("#"+id).val();
  if(remark==''){
	  removeErrorClass($("#"+id));
	 addErrorClass($("#"+id),"Please enter remark")
  }else{
	 var flag=rejectDcpsLegacyData(id,remark);
	 if(flag=="success" || flag=="0"){
		 swal("Data rejected Successfully !!!");
		 $('#dcpsVerifyLagacyTable').dataTable().fnClearTable();
	 }else{
		 swal("Something went wrong please try again");
	 }
  }
}


$('body').on('keyup', '#empContri', function() {
	$("#employerContri").val($(this).val());
});





$("#searchRecords").click(function(){
	var period=$("#period").val();
	if (period != '' && period.length != 0) {
		
		var isActive='1';
		$("#loaderMainNew").show();
		$
				.ajax({
					type : "GET",
					url: "../master/findDcpcLegacyDataByPeriod/"+period+"/"+isActive,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						 $('#dcpsVerifyLagacyTable').dataTable().fnClearTable();
						  $('#dcpsVerifyLagacyTable').show();
						for (var i = 0; i < data.length; i++) {
						
							
							  var dcpsLegacyId=data[i].dcpsLegacyId;
							  var empName=data[i].dcpsLegacyId;
							  var dcpsId=data[i].dcpsId;
							  var sevaarthId=data[i].sevaarthId;
							  var period=data[i].period;
							  var empContri=data[i].empContri;
							  var employerContri=data[i].employerContri;
							  var empInt=data[i].empInt;
							  var employerInt=data[i].employerInt;
							  var total=data[i].total;
							//  var remark="<input type='text'  id='"+data[i].dcpsLegacyId+"'  readonly class='form-control' />";
							  
							 /* var apprBtn='<input type="button" name="apprBtn" id="apprBtn" value="Approve"  onclick="approve('+data[i].dcpsLegacyId+')" class="buttontag btn">';
							  var rejectBtn='<input type="button" name="rejectBtn" id="rejectBtn" onclick="reject('+data[i].dcpsLegacyId+')" value="Reject" class="buttontag btn">';*/
							  
							  $('#dcpsVerifyLagacyTable').dataTable().fnAddData([empName,dcpsId,sevaarthId,period,empContri,employerContri,empInt,employerInt,total]);
								
							  
						//	alert(data[i].dcpsLegacyId);
							
							
						}
						
						if(data.length==0){
							swal("No Record Found");
						}
					}
				});
	}
	
	
});

$("#viewRecords").click(function(){
	var period=$("#period").val();
	if (period != '' && period.length != 0) {
		
		var isActive='2';
		$("#loaderMainNew").show();
		$
				.ajax({
					type : "GET",
					url: "../master/findDcpcLegacyDataByPeriod/"+period+"/"+isActive,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						 $('#dcpsVerifyLagacyTable').dataTable().fnClearTable();
						  $('#dcpsVerifyLagacyTable').show();
						for (var i = 0; i < data.length; i++) {
						
							
							  var dcpsLegacyId=data[i].dcpsLegacyId;
							  var empName=data[i].dcpsLegacyId;
							  var dcpsId=data[i].dcpsId;
							  var sevaarthId=data[i].sevaarthId;
							  var period=data[i].period;
							  var empContri=data[i].empContri;
							  var employerContri=data[i].employerContri;
							  var empInt=data[i].empInt;
							  var employerInt=data[i].employerInt;
							  var total=data[i].total;
							  var remark="<input type='text'  id='"+data[i].dcpsLegacyId+"'  readonly class='form-control' />";
							  
							  var apprBtn='<input type="button" name="apprBtn" id="apprBtn" value="Approve"  onclick="approve('+data[i].dcpsLegacyId+')" class="buttontag btn">';
							  var rejectBtn='<input type="button" name="rejectBtn" id="rejectBtn" onclick="reject('+data[i].dcpsLegacyId+')" value="Reject" class="buttontag btn">';
							  
							  $('#dcpsVerifyLagacyTable').dataTable().fnAddData([empName,dcpsId,sevaarthId,period,empContri,employerContri,empInt,employerInt,total,remark,apprBtn,rejectBtn]);
								
							  
						//	alert(data[i].dcpsLegacyId);
							
							
						}
						
						if(data.length==0){
							swal("No Record Found");
						}
					}
				});
	}
	
	
});




$('body').on('keyup', '#empInt', function() {
	$("#employerInt").val($(this).val());
	
	
	var empContri = Number($("#empContri").val());
	var employerContri = Number($("#employerContri").val());
	var empInterest = Number($("#empInt").val());
	var employerInterest = Number($("#employerInt").val());

	var total = empContri + employerContri + empInterest + employerInterest;

	if (isNaN(total)) {
		total = "";
	}

	$("#total").val(total);
	
	validTotal()
});


function validTotal() {
	var total = document.getElementById("total").value;
	if (total > 1500000) {
		swal("Total amount should not be greater than 15 Lacs");
		$("total").val("");
		$("empContri").val("");
		$("employerContri").val("");
	}

}

function validatezeros() {
	var emp = parseInt($("empInt").val());
	var employer = parseInt($("#employerInt").val());
	if (emp < 0.1 || employer < 0.1) {
		swal("Please Enter Correct value");
		$("empInt").val("");
		$("employerInt").value = val("");
	}
}

function validatezeros() {
	var emp = parseInt($("#empInt").val());
	var employer = parseInt($("#employerInt").val());
	if (emp < 0.1 || employer < 0.1) {
		swal("Please Enter Correct value");
		$("#empInt").val("");
		$("#employerInt").val("");
	}
}


function validateDates() {

	var txtSevarth = $("#sevaarthId").val();
	var period = $("#period").val();
	
	var contriStartDt = $("#contriStartDate").val();
	var contriEndDt = $("#contriEndDate").val();
	checkFlag=isDataPresent(txtSevarth,period,contriStartDt,contriEndDt);
	if (checkFlag =='1') {
		alert("Legacy Data is already submitted for entered Contribution start date and End Date ");
		 $("contriStartDate").val("");
		 $("contriEndDate").val("");
		$("#btnSave").prop("disabled",true);
	}else{
		$("#btnSave").prop("disabled",false);
	}

}

function isDataPresent(sevaarthId,period,contriStartDt,contriEndDt)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/isDataPresent/"+sevaarthId+"/"+period +"/"+contriStartDt+"/"+contriEndDt,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  if(parseInt(data) >0 ) {
	    		  flag=1;
	    	  }
	    	  else
	    		  {
	    		 flag=0;
	    		  }
	    }
	 });
	 return flag;
}


function findAllDCPSLegacyTreasury()
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/findAllDCPSLegacyTreasury",
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	 flag=data
	    }
	 });
	 return flag;
}




function findDcpcLegacyDataByPeriod(period)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/findDcpcLegacyDataByPeriod/"+period,
	      async: false,
	      dataType : 'json',
	       contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  console.log(data);
	    	 flag=data
	    }
	 });
	 return flag;
}



function approveDcpsLegacyData(dcpsLegacyId)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/approveDcpsLegacyData/"+dcpsLegacyId,
	      async: false,
	      dataType : 'json',
	       contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  console.log(data);
	    	 flag=data
	    }
	 });
	 return flag;
}

function rejectDcpsLegacyData(dcpsLegacyId,remark)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/rejectDcpsLegacyData/"+dcpsLegacyId+"/"+remark,
	      async: false,
	      dataType : 'json',
	       contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  console.log(data);
	    	 flag=data
	    }
	 });
	 return flag;
}








