$(document).ready(function(){
  $("#reasonId").change(function(){
    var reasonId=$(this).val();
    if(reasonId=="12"){
    	$("#otherDiv").show();
    }else{
    	$("#otherDiv").hide();
    }  
  });
  
  
  $("#sevaarthId").keyup(function(){
		 var sevaarthId=$("#sevaarthId").val();
		if(sevaarthId!=''){
			 // $("#loaderMainNew").show();
				$.ajax({
					type : "GET",
					url : "../level1/getEmployeeDetails/"
							+ sevaarthId,
					async : true,
					contentType : 'application/json',
					error : function(data) {
						 console.log(data);
					// alert("error");
						 $("#loaderMainNew").hide();
					},
					success : function(data) {
						 console.log(data);
						 $("#loaderMainNew").hide();
						var len = data.length;
						
							$("#loaderMainNew").hide();
							document
									.getElementById("searchDiv").innerHTML = "";
							for (var i = 0; i < data.length; i++) {
								$("#searchDiv").show();
								$("#searchDiv")
										.append(
												"<p class='empdata' empid='"+data[i].employeeId+"'  dept='"+data[i].departmentNameEn+"'  dcpsGpfFlag='"+data[i].dcpsgpfflag+"'  empname='"+data[i].employeeFullName+"' empsevaathid='"+data[i].sevaarthId+"'   desgn='"+data[i].designationName+"'>"
														+ data[i].employeeFullName
														+ "-"
														+ data[i].sevaarthId
														+ "</p>");
								
								$("#searchDiv")
										.css(
												"border:1px solid #A5ACB2;");
							}
							if(data.length==0){
								swal("No Employee Found");
							}
						 //end succcess
					}
				});
		}
	  });
});


$('body').on('click', '.empdata', function() {
	var sevaarthId=$(this).attr("empsevaathid");  
	$("#txtSevaarthId").val(sevaarthId);
	var txtEmployeeName=$(this).attr("empname");  
	var empdesgn=$(this).attr("empdesgn");  
	var empId=$(this).attr("empid");  
	var dept=$(this).attr("dept");  
	var desgn=$(this).attr("desgn");  
	var dcpsGpfFlag=$(this).attr("dcpsGpfFlag");  
	$("#txtEmployeeName").val(txtEmployeeName);
	document.getElementById("searchDiv").innerHTML = "";
	$("#searchDiv").hide();
	$("#cmbLoanType").val("0");
	$("#sevaarthIdSearched").val(sevaarthId);
	$("#desgn").val(desgn);
	$("#dcpsGpfFlg").val(dcpsGpfFlag);
	  var empListTbl=$('#tblDataTable').dataTable();
	  empListTbl.fnClearTable();
	  var link="<a href='../level1/employeeServiceEndDetails/"+empId+"'>"+sevaarthId+"</a>";
	  empListTbl.fnAddData(['1',txtEmployeeName,link,dept,desgn,dcpsGpfFlag]);
	
});






$("form[name='mstServiceEndDateForm']").validate({
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
	orderLetterNo : "required",
	orderDate : "required",
	reasonId : {
		required : true,
		min : 1
	},
/*	eligibleForPension : {
		required:true
	},
	*/
/*	eligibleforGratuity:{
		required:true
		},*/
},
invalidHandler: function(form, validator) {
	//form.preventDefault();
	try{
		$(".error").closest(".collapse").addClass("in");
	}catch(err){
	}
},
// Specify validation error messages
messages : {
	orderLetterNo:"Please enter Order/Ref.Letter Number",
	reasonId : "Please Select Reason for End of Service",
	///eligibleForPension : "Please Select Eligible of Pension",
///	eligibleforGratuity : "Please Select Eligibility for Gratuity",
	orderDate : "Please enter Date",
	
	
},
// Make sure the form is submitted to the destination defined
// in the "action" attribute of the form when valid
submitHandler : function(form) {
	forwardToDDo();
//	form.submit();
}
});
