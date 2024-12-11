

$("#bankBranchCode")
.change(
		function(e) {
			var ifsc=$('option:selected', this).attr('data-id');
			$("#ifscCode").val(ifsc);
		});


$("#bankName")
.change(
		function(e) {
			
			var bankCode= $("#bankName").val();
		//	alert("Branch **"+bankCode);
			
			
			if (bankCode != '') {
				$
						.ajax({
							type : "GET",
							url : "../master/fetchBranchByBank/"
									+ bankCode,
						  
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
											'#bankBranchCode')
											.empty();
									$(
											'#bankBranchCode')
											.append(
													"<option value='0'>Select</option>");
									var temp = data;
									var chk="";
									$
											.each(
													temp,
													function(
															index,
															value) {
														
														$(
																'#bankBranchCode')
																.append(
																		"<option  value="
																				+ value[0]
																				+  ">"
																				+ value[1]
																				+ "</option>");
													});
								} else {
									$(
											'#bankBranchCode')
											.empty();
									$(
											'#bankBranchCode')
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}
			
			
			
		});





$("#bankId").change(function() 
		 {
		 	  var bankId = $("#bankId").val();
		 //alert("bank  is "+bankId);
		     	 if (bankId != '') 
		     	 {
		     		 $.ajax({
		 			      type: "GET",
		 			      url: "mstBank/"+bankId,
		 			      async: true,
		 			      contentType:'application/json',
		 			      error: function(data){
		 			    	 //console.log(data);
		 			      },
		 			      success: function(data){
		 			    	 //console.log(data);
		 			    	  //alert(data);
		 			    	 var len=data.length;
		 			    	  if(len!=0)
		 			    		  {
		 			    		//console.log(data);
		 			    		 $('#bankBranchCode').empty();
		 			    		$('#bankBranchCode').append("<option value='0'>Please Select</option>");
		 				    	 var temp = data;
		 				   		  $.each( temp, function( index, value ){
		 					    		console.log( value[3] ); 
		 					    		 $('#bankBranchCode').append("<option  data-id="+value[6]+" value="+value[0]+">" + value[3] + "</option>");
		 				    		});
		 				   		  
		 				   		  
		 			    		  }
		 			    	  else
		 			    		  {
		 			    		 $('#bankBranchCode').empty();
		 			    		 $('#bankBranchCode').append("<option value='0'>Please Select</option>");
		 			    		//  swal("Record not found !!!");
		 			    		  }
		 			    	}
		 			 });	
		     	 }
		     	 
		     	
		     		
		 });
// bank branch dropdown end

function validateNumber(e) {
	//alert("okay");
    const pattern = /^[0-9]$/;
    return pattern.test(e.key )
}

$("#btnSave").click(function(e){
	var adminDept;
	var hodDept;
	var name;
	var designation;
	var withEffectFrmDate;
	var tanNo;
	var itoWardCircle;
	var bankName;
	var ifscCode;
	var bankAccNo;
	var remarks;
	var warning;
	if($('#language').val()=='en')
		{
		adminDept="Please enter administrative department !!!";
		hodDept="Please enter hod department !!!";
		name="Please enter name !!!";
		designation="Please select designation";
		withEffectFrmDate="Please select date !!!";
		tanNo="Please enter TAN number !!!";
		itoWardCircle="Please enter ward !!!";
		bankName="Please select bank name !!!";
		branch="Please select branch name !!!";
		ifscCode="Please enter ifsc code !!!";
		bankAccNo="Please enter bank account number !!!";
		remarks="Please enter remark!!!";
		warning="warning !!!";
		}
	else
		{
		adminDept="कृपया प्रशासकीय विभाग प्रविष्ट करा !!!";
		hodDept="कृपया फील्ड /विभाग प्रमुख प्रविष्ट करा !!!";
		name="नाव प्रविष्ट करा !!!";
		designation="कृपया पदनाम निवडा";
		withEffectFrmDate="कृपया तारीख निवडा !!!";
		tanNo="कृपया टैन क्रमांक प्रविष्ट करा !!!";
		itoWardCircle="कृपया प्रभाग प्रविष्ट करा !!!";
		bankName="कृपया बँकेचे नाव निवडा !!!";
		branch="कृपया शाखेचे नाव निवडा !!!";
		ifscCode="कृपया ifsc कोड प्रविष्ट करा  !!!";
		bankAccNo="कृपया बँक खाते क्रमांक प्रविष्ट करा  !!!";
		remarks="कृपया टिप्पणी द्या !!!";
		warning="चेतावणी !!!";
		}
	
	
	if($("#adminiDept").val() =="") {
		swal({
			title: warning,
			text: adminDept,
			icon: "warning",
			timer: 4000
		});
		e.preventDefault();
	}/*else if($("#fieldHODDept").val() =="") {
			swal({
				title: warning,
				text: hodDept,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}*/else if($("#name").val() =="") {
			swal({
				title: warning,
				text: name,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#designation").val() =="" || $("#designation").val() =="0") {
			swal({
				title: warning,
				text: designation,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#withEffectFrmDate").val() =="") {
			swal({
				title: warning,
				text: withEffectFrmDate,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#tanNo").val() =="") {
			swal({
				title: warning,
				text: tanNo,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#itoWardCircle").val() =="") {
			swal({
				title: warning,
				text: itoWardCircle,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#bankName").val() =="" || $("#bankName").val() =="0") {
			swal({
				title: warning,
				text: bankName,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#branch").val() =="" || $("#branch").val() =="0") {
			swal({
				title: warning,
				text: branch,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#ifscCode").val() =="") {
			swal({
				title: warning,
				text: ifscCode,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#bankAccNo").val() =="") {
			swal({
				title: warning,
				text: bankAccNo,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#remarks").val() =="") {
			swal({
				title: warning,
				text: remarks,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else {
			var OrganizationName= $("#nameoforginstt").val();
			var GovtSancOrder = $("#govtsanorder").val();
			var date = $("#date").val();
			var TelNo1 = $("#telno1").val();
			var TelNo2 = $("#telno2").val();
			var FaxNo = $("#faxno").val();
			var EmailId = $("#emailId").val();
			
		//	alert(">>"+OrganizationName + " " + GovtSancOrder + " " +date+ " " +TelNo1+ " " +TelNo2+ " " +FaxNo+ " " +EmailId )
			$.ajax({
				
				type : "GET",
				url : "../org/saveOfficeMaster/"+OrganizationName+"/"+TelNo1+"/"+TelNo2+"/"+FaxNo+"/"+EmailId+"/"+GovtSancOrder+"/"+date,
						/*+OrganizationName+"/"+GovtSancOrder+"/"+Date+"/"+TelNo1+"/"+TelNo2+"/"+FaxNo+"/"+EmailId,*/
						
		      async: true,
		      contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		    	 // alert(data);
		      },
		      success: function(data){
		    	  
		    	  console.log(data);
		    	  
		    	  
		    	  swal("Saved Successfuly !", {
		    	      icon: "success",
		    	  });
		    	  
		    	//  $("#btnSave").prop("disabled","true");
		    	//  location.reload();
		    	  

		$(".LockOn").css("display"," block");
		$("#orgInstInfoForm").submit();
	}
			});
		}
});


$(document).ready(function()
{
	if($("#designationId").val()=='' || $("#designationId").val()== undefined)
		{
		$('#btnSave').show();
		}
	else{
		$('#btnSave').hide();
		
	}
	
	
	if($("#designationId").val()=='' || $("#designationId").val()== undefined)
	{
		$('#btnEdit').hide();
	}
	else{
		$('#btnEdit').show();
	}

});

