	var context ="";
$(document).ready(function() {
	context = $("#appRootPath").val();
	//cmbDist
	$("#cmbDist").select2();
	$("#cmbDesignation").select2();
});

//instituteType
$("#instituteType").select2();
$("#cmbBankName").select2();
$("#cmbBranchName").select2();

$("#btnedit").click(function(){
	var contextPath = $("#appRootPath").val();
	$('form *').prop('readonly', false); 
	$("#txtIFSCCode").prop('readonly', true); 
	$("#btnSave").removeClass("hide");
	$('select').removeClass('readonly')
	$("DDOInfo").attr("action",contextPath+"/ddoast/updateddoOfficeDetails");
});
$("#edit").click(function(){
	var contextPath = $("#appRootPath").val();
	$('form *').prop('readonly', false); 
	$("#cmbOfficeCityClass").prop('readonly', true); 
	$("#btnSave").removeClass("hide");
	$('select').removeClass('readonly')
	$("DDOInfo").attr("action",contextPath+"/ddoast/updateddoOfficeDetails");
});



$("#cmbBankName").change(function() 
		 {
		 	  var bankId = $("#cmbBankName").val();
		 //alert("bank  is "+bankId);
		     	 if (bankId != '') 
		     	 {
		     		 $.ajax({
		 			      type: "GET",
		 			      url: context+"/ddoast/mstBank/"+bankId,
		 			      async: true,
		 			      contentType:'application/json',
		 			      error: function(data){
		 			    	 //console.log(data);
							 $( "#loaderMainNew").hide();
		 			      },
		 			     beforeSend : function(){
		 					$( "#loaderMainNew").show();
		 					},
		 				complete : function(data){
		 					$( "#loaderMainNew").hide();
		 				},
		 			      success: function(data){
		 			    	 //console.log(data);
		 			    	  //alert(data);
		 			    	 var len=data.length;
		 			    	  if(len!=0)
		 			    		  {
		 			    		//console.log(data);
		 			    		 $('#cmbBranchName').empty();
		 			    		$('#cmbBranchName').append("<option value='0'>Please Select</option>");
		 				    	 var temp = data;
		 				   		  $.each( temp, function( index, value ){
		 					    		console.log( value[3] ); 
		 					    		 $('#cmbBranchName').append("<option  data-id="+value[6]+" value="+value[0]+">" + value[3] + "</option>");
		 				    		});
		 				   		  
		 				   		  
		 			    		  }
		 			    	  else
		 			    		  {
		 			    		 $('#cmbBranchName').empty();
		 			    		 $('#cmbBranchName').append("<option value='0'>Please Select</option>");
		 			    		//  swal("Record not found !!!");
		 			    		  }
		 			    	}
		 			 });	
		     	 }
		     	 
		     	
		     		
		 });


$("#cmbBranchName").change(function() 
		 {
		 	  var branchId = $("#cmbBranchName").val();
		 //alert("bank  is "+bankId);
		     	 if (branchId != '') 
		     	 {
		     		 $.ajax({
		 			      type: "GET",
		 			      url: context+"/ddoast/getIfscCodeByBranchId/"+branchId,
		 			      async: true,
		 			      contentType:'application/json',
		 			      error: function(data){
		 			    	 //console.log(data);
							 $( "#loaderMainNew").hide();
		 			      },
		 			     beforeSend : function(){
		 					$( "#loaderMainNew").show();
		 					},
		 				complete : function(data){
		 					$( "#loaderMainNew").hide();
		 				},
		 			      success: function(data){
		 			    	  
		 			    	  
		 			    	  
		 			    	 //console.log(data);
		 			    	  //alert(data);
		 			    	 var len=data.length;
		 			    	  if(len!=0)
		 			    		  {
		 			    		//console.log(data);
		 				    	 var temp = data;
		 				   		  for(var i=0;i<len;i++)
		 				   			  {
		 				   			  $("#txtIFSCCode").val(data[i].ifscCode); 
		 				   			  }
		 				   		  
		 			    		  }
		 			    	  else
		 			    		  {
		 			    		 $("#txtIFSCCode").val("");
		 			    		  }
		 			    	}
		 			 });	
		     	 }
		     	 
		     	
		     		
		 });



$("#cmbcity").change(function() //cmbOfficeCityClass
		 {
		 	  var city = $("#cmbcity").val();
		 //alert("bank  is "+bankId);
		     	 if (city != '') 
		     	 {
		     		 $.ajax({
		 			      type: "GET",
		 			      url: context+"/ddoast/getCityClassByCity/"+city,
		 			      async: true,
		 			      contentType:'application/json',
		 			      error: function(data){
		 			    	 //console.log(data);
							 $( "#loaderMainNew").hide();
		 			      },
		 			     beforeSend : function(){
		 					$( "#loaderMainNew").show();
		 					},
		 				complete : function(data){
		 					$( "#loaderMainNew").hide();
		 				},
		 			      success: function(data){
		 			    	 $.each(data, function(index, row) {
		 			    		 $("#cmbOfficeCityClass").val(row[12]);
		 			    	 });
		 			    	
		 			    	}
		 			 });	
		     	 }
		 });
		 
		 
		 
		 $("#txtAccountNo").blur(function() //cmbOfficeCityClass
		 		 {
		 		 	  var accNo = $("#txtAccountNo").val();
		 		     	 if (city != '') 
		 		     	 {
		 		     		 $.ajax({
		 		 			      type: "GET",
		 		 			      url: context+"/ddoast/validateAccNo/"+accNo,
		 		 			      async: true,
		 		 			      contentType:'application/json',
		 		 			      error: function(data){
		 		 			    	 //console.log(data);
									 $( "#loaderMainNew").hide();
		 		 			      },
		 		 			     beforeSend : function(){
		 		 					$( "#loaderMainNew").show();
		 		 					},
		 		 				complete : function(data){
		 		 					$( "#loaderMainNew").hide();
		 		 				},
		 		 			      success: function(data){
									if (data > 0) {

																swal('Entered Account no number: '
																		+ accNo
																		+ ' is already present in system. Please enter correct Account number.');

																document.getElementById("txtAccountNo").value = "";
															}
		 		 			    	
		 		 			    	}
		 		 			 });	
		 		     	 }
		 		 });

				 
				 
				 $("#txtTelNo2").blur(function() //cmbOfficeCityClass
				 	 		 {
				 	 		 	  var telPhone = $("#txtTelNo2").val();
				 	 		     	 if (city != '') 
				 	 		     	 {
				 	 		     		 $.ajax({
				 	 		 			      type: "GET",
				 	 		 			      url: context+"/ddoast/validateTelPhone/"+telPhone,
				 	 		 			      async: true,
				 	 		 			      contentType:'application/json',
				 	 		 			      error: function(data){
				 	 		 			    	 //console.log(data);
												 $( "#loaderMainNew").hide();
				 	 		 			      },
				 	 		 			     beforeSend : function(){
				 	 		 					$( "#loaderMainNew").show();
				 	 		 					},
				 	 		 				complete : function(data){
				 	 		 					$( "#loaderMainNew").hide();
				 	 		 				},
				 	 		 			      success: function(data){
				 								if (data > 0) {

				 															swal('Entered Telephone  number: '
				 																	+ telPhone
				 																	+ ' is already present in system. Please enter correct telepphone number.');

				 															document.getElementById("txtTelNo2").value = "";
				 														}
				 	 		 			    	
				 	 		 			    	}
				 	 		 			 });	
				 	 		     	 }
				 	 		 });

							 
							 
							 
							 $("#txtEmail").blur(function() //cmbOfficeCityClass
							 	 		 {
							 	 		 	  var email = $("#txtEmail").val();
							 	 		     	 if (city != '') 
							 	 		     	 {
							 	 		     		 $.ajax({
							 	 		 			      type: "GET",
							 	 		 			      url: context+"/ddoast/validateEmailAdd/"+email,
							 	 		 			      async: true,
							 	 		 			      contentType:'application/json',
							 	 		 			      error: function(data){
							 	 		 			    	 //console.log(data);
															 $( "#loaderMainNew").hide();
							 	 		 			      },
							 	 		 			     beforeSend : function(){
							 	 		 					$( "#loaderMainNew").show();
							 	 		 					},
							 	 		 				complete : function(data){
							 	 		 					$( "#loaderMainNew").hide();
							 	 		 				},
							 	 		 			      success: function(data){
							 								if (data > 0) {

							 															swal('Entered Email: '
							 																	+ email
							 																	+ ' is already present in system. Please enter correct Email.');

							 															document.getElementById("txtEmail").value = "";
							 														}
							 	 		 			    	
							 	 		 			    	}
							 	 		 			 });	
							 	 		     	 }
							 	 		 });



$("#cmbDist").change(function(){
	var contextPath = $("#appRootPath").val();
var districtId=$("#cmbDist").val();
    $.ajax({
    url : contextPath+ "/ddoast/findDataByDistrict/"+districtId,
        type: 'GET',
    async : true,
    beforeSend : function(){
		$( "#loaderMainNew").show();
		},
	complete : function(data){
		$( "#loaderMainNew").hide();
	},	
contentType : 'application/json',
        success: function(response) {
        if(response!=''){
        var dropdown = $('#cmbTaluka');
                 dropdown.empty();
                 dropdown.append($('<option  value="-1"></option>').text("Please Select")); // Adjust the value index as needed
                 $.each(response.talukaList, function(index, value) {
                    $("#cmbTaluka").val(value[1]);
                    dropdown.append($('<option  value="'+value[0]+'"></option>').text(value[1])); // Adjust the value index as needed
                   
                 });
                 
                 var dropdown1 = $('#cmbcity');
                 dropdown1.empty();
                 dropdown1.append($('<option  value="-1"></option>').text("Please Select"));
                 $.each(response.cityList, function(index, value) {
                     dropdown1.append($('<option value="'+value[0]+'"></option>').text(value[1])); // Adjust the value index as needed
                 });
        }else{
        alert("No Data Found");
        }
        },
        error: function(error) {
           alert('Error fetching DDO data:', error);
        }
    });
});






