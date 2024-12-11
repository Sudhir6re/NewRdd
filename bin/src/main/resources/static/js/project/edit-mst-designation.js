$(".alphacharspace").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');
	$(this).val(replacestr);
	});


$("#cadreCode").change(function(){
	var cadreCode=$(this).val();
	if(parseInt(cadreCode)==2){
		$("#cadreGroup").show();
	}else{
		$("#cadreGroup").hide();
	}
});


jQuery(document).ready(function($) {
	$('#field_department').change(function() {
		var fieldDepartmrntID =$("#field_department").val();
		$.ajax({
			type: "GET",
			url: "../master/getCadreDesc",
			contentType : "application/json",
			data : "fieldDepartmrntID=" +fieldDepartmrntID,
			dataType : 'json',
			async: false,
			success: function (data){ 
				arr = data;
				console.log(data)
				if(parseInt(data.totalRows) > 0){
					$("#cadre").empty();
					if($('#language').val() == "en") {
						$("#cadre").append($("<option></option>").val("0").html("Please Select"));
					} else {
						$("#cadre").append($("<option></option>").val("0").html("कृपया  निवडा"));
					}
					$.each(data.resultData, function (i, result) {
						$("#cadre").append( $("<option></option>").attr("value",result.cadreDescId).text(result.cadreDescDD));
					});  
				}
			}
		});
	}); 
});

var warningError;
if($('#language').val()=="en"){
	//departmentName="Please Select the Department Name !!"
	designationNameError="Please Enter Designation Name !!";
	designationShortNameError="Please Enter Designation short name !!";
	warning="warning !";
}else{
	//departmentName="कृपया विभागाचे नाव निवडा  !!";
	subdeptShortNameError="कृपया पदनाम प्रविष्ट करा !!";
	subdeptNameError="कृपया पदनाम  लहान नाव प्रविष्ट करा  !!";
	warning="चेतावणी !";
}
$('#btnSave').click(function(e) {
	// var deptname=$('#select2-departmentCode-container').val();
	 var designationName=$('#designation').val();
	 var designationShortName=$('#designationShortName').val();
		var cadreCode=$("#cadreCode").val();
		var cadreGroup=$('input[name=cadreGroup]:checked').val();
		
 if(designationName==''|| designationName == undefined){
		 Swal.fire({
				title: warning,
				text: designationNameError,
				icon: "warning",
				timer: 4000
			});
			
		 e.preventDefault(); 
	 }else if(designationShortName=="" || designationShortName == undefined){
		 Swal.fire({
				title: warning,
				text: designationShortNameError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		 
	 } else if(cadreCode=="2" && cadreGroup===undefined  ){ 
		 Swal.fire({
				title: warning,
				text: "Please select cadre group !!!",
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
	 }else {
			$(".LockOn").css("display"," block");
			Swal.fire("Record updated Successfully.");
			$("#mstDepartmentForm").submit();
		} 
	 
});

function validateDesignationName() {
	// alert('inside validateDesignationName');
	var desgname = document.getElementById("designation").value;
	
	
	
		if (desgname != '') {
			$
					.ajax({
						type : "GET",
						url : "../validateDesignationName/"+desgname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							swal("error");
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#designation').val(desgname);
								status = true;
							} else if (checkFlag > 0) {

								swal(desgname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("designation").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}









