$(document).ready(function() {
	
	
	   if ($('#cmbDistrict').length) {
	        $('#cmbDistrict').select2();
	    }
	   
	   if ($('#cmbTaluka').length) {
	        $('#cmbTaluka').select2();
	    }
	
	var contextPath = $("#appRootPath").val();
	var DDOCode =$("#ddoCode").val(); 
	var instituteId = $("#uniqeInstituteId").val();
	if (DDOCode != ''  && $("#uniqeInstituteId").val()!=null  && $("#uniqeInstituteId").val()!=undefined ) {
		swal('For DDO Code: '
				+ DDOCode
				+ ', system generated unique institute Id: '
				+ instituteId
				+ '. Please note the details for future use. Additionally, the default password is: ifms123.');
	}
	

	//var dataTable= $("#ddoMapTable").dataTable();
	
	var dataTable= $('#ddoMapTable').dataTable({
	    "columnDefs": [
	        { "targets": [4], "visible": false } 
	    ],
	    "order": [[4, 'desc']] 
	});
	
	
$("#cmbDistrict").change(function(){
	var context = $("#appRootPath").val();
	var districtId = $("#cmbDistrict").val();
	
	$( "#loaderMainNew").show();
	$.ajax({
	      type: "POST",
	      url: context+"/mdc/getAllTalukaByDistrictId/"+districtId,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    		 var len = data.length;
	    		 $('#cmbTaluka').empty();
	    		 $( "#loaderMainNew").hide();
	    		 $('#cmbTaluka').append($('<option  value="-1"></option>').text("Please Select")); 
					if (len != 0) {
							   for(var i=0;i<len;i++){
								   $('#cmbTaluka').append('<option value="' + data[i].talukaId + '">' + data[i].talukaName + '</option>');
				                }		
				}
					
	     }
	 });
});


$("#btnFilter").click(function(){
	var context = $("#appRootPath").val();
	var districtId = $("#cmbDistrict").val();
	var talukaId = $("#cmbTaluka").val();
	var cmbAdminType = $("#cmbAdminType").val();
	
	$( "#loaderMainNew").show();
	$.ajax({
	      type: "POST",
	      url: context+"/mdc/findZpRltDtls",
        contentType: 'application/json',
        data: JSON.stringify({ districtId: districtId, talukaId: talukaId,cmbAdminType:cmbAdminType }),
        dataType: 'json',
	      async: false,
	      contentType:'application/json',
	  	beforeSend : function(){
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  $( "#loaderMainNew").hide();
	    		 var len = data.length;
					if (len != 0) {
						populateTable(data);	
				}else{
					swal("No data found");
				}
					
	     }
	 });
});


function populateTable(data) {
    $.each(data, function(index, row) {
    	dataTable.fnClearTable();
    	   var row1 = '<span id="' + row[0] + '"><a href="#"  data-ddocode="'+ row[0]+'"  class="ddoCode"    data-srno="'+index+'"  >' + row[0] + '</a></span>';
           var row2 = '<span id="' + row[1] + '"><a href="#"  data-ddocode="'+ row[1]+'" class="ddoCode"    data-srno="'+index+'" >' + row[1] + '</a></span>';
    	var row3=null;
    	   if(row[5]==0){
    		   row3= '<span class="btn btn-warning" >Pending</span>';
    	   }else if(row[5]==1){
    		   row3= '<span  class="btn btn-succes" >Approved</span>';
    	   }else{
    		   row3= '<span  class="btn btn-danger >Rejected</span>';
    	   }
    	   
    	   var createdDate = new Date(row[7]); 
		    var formattedDate = dateToDMY(createdDate);
        dataTable.fnAddData(
				[
					row1,
					row2,row[4],
					createdDate,row[6],row3
						]);
    });
}

$("#txtDDODsgn").keyup(function(){
	 var txtDDODsgn=$("#txtDDODsgn").val();
	 var context = $("#appRootPath").val();
	 
	if(txtDDODsgn!='' && txtDDODsgn!="0"){
		  $("#loaderMainNew").show();
			$.ajax({
				type : "GET",
			    url: context+"/mdc/findDesignation",
				async : true,
				   data: { txtDDODsgn: txtDDODsgn },
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
				
					empFound=data.length;
					
					console.log(data);
						$("#loaderMainNew").hide();
						document
								.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {
							
							$("#searchDiv").show();
							$("#searchDiv")
									.append(
											"<p><a class='empdata'    desginationId='"+data[i].desginationId+"'    empdesgn='"+data[i].desgination+"'>"+ data[i].desgination+ "</a></p>");
							$("#searchDiv")
									.css(
											"border:1px solid #A5ACB2;");
						}
						
						if(data.length==0){
							swal("Please enter valid post");
						}
				}
			});
	}
 });



$('body').on('click', '.empdata', function() {
	 $("#procceed").attr("disabled", false); 
	var empdesgn=$(this).attr("empdesgn");  
	var desginationId=$(this).attr("desginationId");  
	$("#txtDDODsgn").val(empdesgn);
	$("#desginationId").val(desginationId);
	$("#searchDiv").hide();
});


$("#txtRepDDOCode").blur(function(){
	var context = $("#appRootPath").val();
	var ddoCode=$("#txtRepDDOCode").val();
	$( "#loaderMainNew").show();
    $.ajax({
    	url : context+"/mdc/getddoInfo",
        type: 'GET',
        data: { ddoCode: ddoCode },
    	async : true,
		contentType : 'application/json',
		beforeSend : function(){
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
        success: function(response) {
        	$( "#loaderMainNew").hide();
        	if(response!=''){
        		 var dropdown = $('#cmbSubTreasury');
                 dropdown.empty();
                 dropdown.append($('<option  value="-1"></option>').text("Please Select")); 
                 $.each(response.trasuryDetails, function(index, value) {
                    $("#txtTreasuryName").val(value[1]);
                    $("#txtTreasuryCode").val(value[0]);
                    dropdown.append($('<option  value="'+value[0]+'"></option>').text(value[1])); 
                 });
                 
                 $.each(response.subTreasuryList, function(index, value) {
                     dropdown.append($('<option value="'+value[0]+'"></option>').text(value[1]));
                 });
        	}else{
        		swal("No Data Found");
        	}
        },
        error: function(error) {
           //alert('Error fetching DDO data:', error);
        	swal("DDO Not Found")
        }
    });
});



$("#cmbSubTreasury").change(function(){
	var cmbSubTreasury=$("#cmbSubTreasury").val();
	var cmbAdminOffice=$("#cmbAdminOffice").val();
	$( "#loaderMainNew").show();
$.ajax({
	type : "GET",
	url : contextPath+"/mdc/generateDDOCode/" + cmbSubTreasury+"/"+cmbAdminOffice,
	async : true,
	contentType : 'application/json',
	error : function(data) {
		 console.log(data);
	},
	beforeSend : function(){
		$( "#loaderMainNew").show();
		},
	complete : function(data){
		$( "#loaderMainNew").hide();
	},	
	success : function(response) {
		$( "#loaderMainNew").hide();
		 console.log(response);
		 var ddoCode = response.ddoCode;
		 if(ddoCode!=''){
			 $("#txtDDOCode").val(ddoCode);
		 }else{
			 $("#txtDDOCode").val("");
		 }
	}
});
});






$("#cmbAdminOffice").change(function(){
	var context = $("#appRootPath").val();
	var ofcId = $("#cmbAdminOffice").val();
	
	$( "#loaderMainNew").show();
	$.ajax({
	      type: "POST",
	      url: context+"/mdc/fetchDistrictOfcByOffcId/"+ofcId,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    	  $( "#loaderMainNew").hide();
	    	  $('#cmbDistrict').empty();
	    	  $('#cmbTaluka').append($('<option  value="-1"></option>').text("Please Select")); 
	    	  $('#cmbDistrict').append($('<option  value="-1"></option>').text("Please Select")); 
	    		 var len = data.length;
					if (len != 0) {
						 $.each(data, function(index, row) {
								   $('#cmbDistrict').append('<option value="' + row[1]+ '">' + row[0] + '</option>');
						 });	
				}
					
	     }
	 });
});




$('body').on('click', '.hideDdoCode', function() {
	var field=$(this).attr("data-ddocode");  
	var srno=$(this).attr("data-srno");  
	 hideDtls(field, srno);
});




$('body').on('click', '.ddoCode', function() {
	var field=$(this).attr("data-ddocode");  
	var srno=$(this).attr("data-srno");  
	
	var ddoCode=field;

	$( "#loaderMainNew").show();
	$.ajax({
		type : "GET",
		url : contextPath+"/mdc/fetchDdoDetails/" + ddoCode,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			// console.log(data);
			$( "#loaderMainNew").hide();
		},
		success : function(data) {
			// console.log(data);
			// alert(data);
			var len = data.length;
			$( "#loaderMainNew").hide();
				var temp = data;
				$
						.each(
								temp,
								function(index,
										value) {
									console
											.log(value[1]);
									
									setDDOdtls(value[0]+","+value[1], field, srno);
								
																							});
		}
	});
});

function setDDOdtls(myAjax, field, srno) {
	var divId = srno.toString() + field.toString();
	document.getElementById(divId).innerHTML = '<a   data-ddocode="'+field+'"  data-srno="'+srno+'"   class="hideDdoCode"  >'+ field+ '<br>'+ myAjax + '</a>';
}

function hideDtls(field, srno) {

	var divId = srno.toString() + field.toString();
	document.getElementById(divId).innerHTML = '<a   data-ddocode="'+field+'"  data-srno="'+srno+'"   class="ddoCode"   >' + field + '</a>';
}

    $("form[name='ZpDDOOffice']").validate({
        rules: {
            cmbAdminOffice: {
                required: true,
               min: 1
            },
            cmbDistOffice: {
                required: true,
               min: 1
            },
            radioFinalLevel: "required",
            txtRepDDOCode: {
                required: true,
                //maxlength: 10
            },
            txtFinalDDOCode: {
                required: true,
                //maxlength: 10
            },
            txtSpecialDDOCode: {
                required: false,
                //maxlength: 10
            },
            radioSalutation: "required",
            txtDDOName: {
                required: true,
               // maxlength: 100
            },
            radioGender: "required",
            txtTreasuryName: {
                maxlength: 50
            },
            txtTreasuryCode: {
                maxlength: 4
            },
            cmbSubTreasury: {
                required: true,
                min: 1
            },
            txtDDODsgn: {
                required: true,
                maxlength: 50
            },
            txtOfficeName: {
                required: true,
                maxlength: 500
            },
            txtDDOCode: {
                required: true,
                maxlength: 50
            },
            txtMobileNo: {
                required: true,
                maxlength: 10,
                minlength:10
            },
            txtEmailId: {
                required: true,
                maxlength: 100,
                email: true 
            }
        },
        messages: {
            cmbAdminOffice: {
                required: "Please select Admin Office",
                min: "Please select Admin Office"
            },
            cmbDistOffice: {
                required: "Please select District Office",
                min: "Please select District Office"
            },
            radioFinalLevel: "Please select Final Level",
            txtRepDDOCode: {
                required: "Please enter DDO Code Level 2",
                maxlength: "DDO Code Level 2 should not exceed {0} characters"
            },
            txtFinalDDOCode: {
                required: "Please enter DDO Code Level 3",
                maxlength: "DDO Code Level 3 should not exceed {0} characters"
            },
            txtSpecialDDOCode: {
                required: "Please enter DDO Code Level 4",
                maxlength: "DDO Code Level 4 should not exceed {0} characters"
            },
            radioSalutation: "Please select DDO Name Salutation",
            txtDDOName: {
                required: "Please enter DDO Name",
                maxlength: "DDO Name should not exceed {0} characters"
            },
            radioGender: "Please select Gender",
            txtTreasuryName: {
                maxlength: "Treasury Name should not exceed {0} characters"
            },
            txtTreasuryCode: {
                maxlength: "Treasury Code should not exceed {0} characters"
            },
            cmbSubTreasury: {
                required: "Please select Sub Treasury Name",
                min: "Please select Sub Treasury Name"
            },
            txtDDODsgn: {
                required: "Please enter DDO Designation",
                maxlength: "DDO Designation should not exceed {0} characters"
            },
            txtOfficeName: {
                required: "Please enter Institute Name",
                maxlength: "Institute Name should not exceed {0} characters"
            },
            txtDDOCode: {
                required: "Please enter DDO Code",
                maxlength: "DDO Code should not exceed {0} characters"
            },
            txtMobileNo: {
                required: "Please enter Mobile Number",
                maxlength: "Mobile Number should not exceed {0} characters"
            },
            txtEmailId: {
                required: "Please enter Email Id",
                maxlength: "Email Id should not exceed {0} characters",
                email: "Please enter a valid Email Id"
            }
        },
        submitHandler: function(form) {
            form.submit();
            $("#loaderMainNew").show();
        }
    });
});





function addSalutationToName() {
	if (document.getElementById("radioSalutationShri").checked == true) {
		document.getElementById("txtDDOName").value = 'Shri.';
	}
	if (document.getElementById("radioSalutationSmt").checked == true) {
		document.getElementById("txtDDOName").value = 'Smt.';
	}
}
function validateDDOName() {
	var txt = document.getElementById("txtDDOName").value;
	var regex = /^[ A-Za-z.-]*$/;
	if (regex.test(txt)) {
	} else {
		//alert('Please Enter Valid DDO Name.\nOnly Characters are allowed in DDO Name.');
		document.getElementById("txtDDOName").value = '';
		if (document.getElementById("radioSalutationShri").checked == true) {
			//alert('Shree selected');
			document.getElementById("txtDDOName").value = 'Shri.';
		}
		if (document.getElementById("radioSalutationSmt").checked == true) {
			//alert('Smt Selected');
			document.getElementById("txtDDOName").value = 'Smt.';
		}
		return false;
	}
}

function validateMobileNo() {
	var mobileNo = document.getElementById("txtMobileNo").value;
	var regex = /^[0-9]*$/;
	if (regex.test(mobileNo)) {
	} else {
		//alert('Please enter only digit in Mobile No. field');
		document.getElementById("txtMobileNo").value = '';
		return false;
	}
	if (mobileNo.length != 10) {
		//alert('Please enter complete Mobile No.');
		document.getElementById("txtMobileNo").value = '';
		return false;
	}
	if (!(mobileNo.charAt(0) == 7 || mobileNo.charAt(0) == 8 || mobileNo
			.charAt(0) == 9)) {
		//alert('Please enter valid mobile No.');
		document.getElementById("txtMobileNo").value = '';
		return false;
	}
}
