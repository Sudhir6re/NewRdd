var context = "";

$(document).ready(function(){
	context = $("#appRootPath").val();
	var selecteditems  = [];
	var serialid  = [];
     $(".chk").click(function(){
    	 if($(this).is(":checked")){
        	 selecteditems.push($(this).attr("value"));
        	// selecteditems.push($(this).attr("data-serialid"));
         }
         else if($(this).is(":not(:checked)")){
        	 //alert($(this).attr("value"));
        	 var removeItem = $(this).attr("value");

        	 selecteditems = jQuery.grep(selecteditems, function(value) {
        	   return value != removeItem;
        	 });
        	 serialid.pop($(this).attr("data-serialid"));
        	console.log("serial id are"+selecteditems);
         }  
    	 
     });
     console.log(selecteditems);
     
     $(".btnUpdate").click(function(){
    	
    	 var action= 1;
    	 serialid.push(0);   	 
    	 
   	 	  var input = $("#department_code").val();
   	 	  
   	 	  if(input=="undefined" || input==undefined){
   	 		  input=$("#departmentCode").val();
   	 		  
   	 	  }
   	 	var effectiveDate = $("#effectiveDate").val();
	   var ddoCode = $("#ddo_code").val();
	 	
   	 	if(effectiveDate=="" || effectiveDate=="undefined"){
   	 		swal("Please select effective date");
   	 	}
   	 	else if ((selecteditems.length != '') ) {
    		 $.ajax({
			      type: "GET",
			     /* url: "../master/saveMpgDdoAllowDeduc/"+selecteditems+"/"+input +"/"+action+"/"+serialid+"/"+effectiveDate,*/
			      
			      url: context+"/moderator/saveDeptEligibilityAllowDeducLevel1/"+selecteditems+"/"+input +"/"+action+"/"+serialid+"/"+effectiveDate+"/"+ddoCode,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			      },
			      success: function(data){
			    	 swal("Saved Successfuly !", {
			    	      icon: "success",
			    	  });
			    	  setTimeout(function() {
						    location.reload(true);
						}, 3000);
			    	  $("#tblDataTable").show();
			    }
			 });		 
    	 }
    	 else
		 {
		 swal("Please Select Department Name");
		 }
    	  });
     
    
     
     $("#department_code").change(function() 
    			{
    				
    				 //$('input[name="test"][value="' + value[2] + '"]').prop("checked", true);
    				$('input[type=checkbox]').each(function() 
    		{ 
    		        this.checked = false; 
    		}); 
    				 
    				  var input = $("#department_code").val();
    				  
    				//  alert("DDO CODE is"+input);
    			    	 if (input != '') 
    			    	 {
    			    		 $.ajax({
    						      type: "GET",
    						      url: context+"/moderator/deptEligibilityForAllowAndDeduct1/"+input,
    						     
    						      async: true,
    						      contentType:'application/json',
    						      error: function(data){
    						    	  console.log(data);
    						      },
    						      success: function(data){
    						    	  selecteditems=[];
    						    	  serialid=[];
    						      console.log("first controller data");
    						    	  console.log(data);
    						    	  $("#tblDataTable").show();
    						    	  var len=data.length;
    						    	  if(len==0)
    						    		  {
    						    		  $("#btnSave").show();
    						    		  $("#btnUpdate").hide();
    						    		  }
    						    	  else
    						    		  {
    						    		  $("#btnUpdate").show();
    						    		  $("#btnSave").hide();		    		  
    							    	  var temp = data;
    							    	  var tem=[] ; 
    							    	 
    							   		  $.each( temp, function( index, value ){
    								    		console.log(value[2]); 
    								    		// 15
    					//alert("data exists");
    								    		
    								    		 $('input[name="test"][value="' + value[1] + '"]').prop("checked", true);
    								    		 selecteditems.push(value[1]); 
    								    		 $("#"+value[1]).attr("data-serialid",value[1]);
    								    		 serialid.push(value[1]);
    							    		});
    						    		  }
    						    }
    						 });	
    			    	 }
    			    	 else
    			    		 {
    			    		 swal("Please Select DDO Code");
    			    		 }

    			});
     
 	
	 $.ajax({
	      type: "GET",
	      url: context+"/moderator/deptEligibilityForAllowAndDeduct6",
	      async: true,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  console.log(data);
	    	 var temp = data;
	   		  $.each( temp, function( index, value ){
		    		console.log( value[2] ); 
		    		 $('#department_code').append("<option value="+value[0]+">" + value[1] + "</option>");
	    		});
	    	}
	 });
	 
	 
	 
	 $.ajax({
	      type: "GET",
	      url: context+"/moderator/deptEligibilityForAllowAndDeduct5",
	      async: true,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  console.log(data);
	    	 var temp = data;
	   		  $.each( temp, function( index, value ){
		    		console.log( value[2] ); 
		    		 $('#departmentCode').append("<option value="+value[0]+">" + value[1] + "</option>");
	    		});
	    	}
	 });
	 
	 
	 $('#reset').click(function() 
			  {
		 $('input[type=checkbox]').each(function() 
		    		{
		    		        this.checked = false; 
		    		}); 
		 
		  var input = $("#department_code").val();
		 //delete component from database
		 $.ajax({
		      type: "GET",
		      url: context+"/moderator/deleteMappedEmployeee/"+input ,
		      async: true,
		      contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		      },
		      success: function(data){
		    	 swal("Reset Successfuly !", {
		    	      icon: "success",
		    	  });
		    	  setTimeout(function() {
					    location.reload(true);
					}, 3000);
		    	  $("#tblDataTable").show();
		    }
		 });		 
		    			
	  });
 });


$.ajax({
    type: "GET",
    url: context+"/level1/findallDDOcodebylevel2",
    async: true,
    contentType:'application/json',
    error: function(data){
  	  console.log(data);
    },
    success: function(data){
  	  console.log(data);
  	 var temp = data;
 		  $.each( temp, function( index, value ){
	    		console.log( value[2] ); 
	    		 $('#ddo_code').append("<option value="+value[1]+">" + value[2] + "</option>");
  		});
  	}
});






$("#ddo_code").change(function() 
		{
			
			 //$('input[name="test"][value="' + value[2] + '"]').prop("checked", true);
			$('input[type=checkbox]').each(function() 
	{ 
	        this.checked = false; 
	}); 
			 
			  var input = $("#ddo_code").val();
			  
			//  alert("DDO CODE is"+input);
		    	 if (input != '') 
		    	 {
		    		 $.ajax({
					      type: "GET",
					      url: context+"/master/deptEligibilityForAllowAndDeductDDOWise/"+input,
					      async: true,
					      contentType:'application/json',
					      error: function(data){
					    	  console.log(data);
					      },
					      success: function(data){
					    	  selecteditems=[];
					    	  serialid=[];
					      console.log("first controller data");
					    	  console.log(data);
					    	  $("#tblDataTable").show();
					    	  var len=data.length;
					    	  if(len==0)
					    		  {
					    		  $("#btnSave").show();
					    		  $("#btnUpdate").hide();
					    		  }
					    	  else
					    		  {
					    		  $("#btnUpdate").show();
					    		  $("#btnSave").hide();		    		  
						    	  var temp = data;
						    	  var tem=[] ; 
						    	 
						   		  $.each( temp, function( index, value ){
							    		console.log(value[2]); 
							    		// 15
				//alert("data exists");
							    		
							    		 $('input[name="test"][value="' + value[2] + '"]').prop("checked", true);
							    		 selecteditems.push(value[2]); 
							    		 $("#"+value[2]).attr("data-serialid",value[0]);
							    		 serialid.push(value[0]);
						    		});
					    		  }
					    }
					 });	
		    	 }
		    	 else
		    		 {
		    		 swal("Please Select DDO Code");
		    		 }

		});



//for level 2 provide admin  

$("#departmentCode").change(function() 
		{
			
			 //$('input[name="test"][value="' + value[2] + '"]').prop("checked", true);
			/*$('input[type=checkbox]').each(function() 
	{ 
	        this.checked = false; 
	}); */
			 
			  var input = $("#departmentCode").val();
			  
			//  alert("DDO CODE is"+input);
		    	 if (input != '') 
		    	 {
		    		 $.ajax({
					      type: "GET",
					      url: context+"/master/deptEligibilityForAllowAndDeduct1/"+input,
					      async: true,
					      contentType:'application/json',
					      error: function(data){
					    	  console.log(data);
					      },
					      success: function(data){
					    	  selecteditems=[];
					    	  serialid=[];
					      console.log("first controller data");
					    	  console.log(data);
					    	  $("#tblDataTable").show();
					    	  var len=data.length;
					    	  if(len==0)
					    		  {
					    		  $("#btnSave").show();
					    		  $("#btnUpdate").hide();
					    		  }
					    	  else
					    		  {
					    		  $("#btnUpdate").show();
					    		  $("#btnSave").hide();		    		  
						    	  var temp = data;
						    	  var tem=[] ; 
						    	 
						   		  $.each( temp, function( index, value ){
							    		console.log(value[2]); 
							    		// 15
				//alert("data exists");
							    		
							    		
							    		$("#span"+value[2]).show();
							    		
							    		/* $('input[name="test"][value="' + value[2] + '"]').prop("checked", true);*/
							    		 selecteditems.push(value[2]); 
							    		 $("#"+value[2]).attr("data-serialid",value[0]);
							    		 serialid.push(value[0]);
						    		});
					    		  }
					    }
					 });	
		    	 }
		    	 else
		    		 {
		    		 swal("Please Select DDO Code");
		    		 }

		});


/////For level 2 DDO's





//for allowances onclick level 2


$("#departmentCode").change(function() 
		{
			
			 //$('input[name="test"][value="' + value[2] + '"]').prop("checked", true);
			/*$('input[type=checkbox]').each(function() 
	{ 
	        this.checked = false; 
	}); 
			 */
			  var input = $("#departmentCode").val();
			  
			//  alert("DDO CODE is"+input);
		    	 if (input != '') 
		    	 {
		    		 $.ajax({
					      type: "GET",
					      url: context+"/master/deptEligibilityForAllowAndDeduct1/"+input,
					      async: true,
					      contentType:'application/json',
					      error: function(data){
					    	  console.log(data);
					      },
					      success: function(data){
					    	  selecteditems=[];
					    	  serialid=[];
					      console.log("first controller data");
					    	  console.log(data);
					    	  $("#tblDataTable").show();
					    	  var len=data.length;
					    	  if(len==0)
					    		  {
					    		  $("#btnSave").show();
					    		  $("#btnUpdate").hide();
					    		  }
					    	  else
					    		  {
					    		  $("#btnUpdate").show();
					    		  $("#btnSave").hide();		    		  
						    	  var temp = data;
						    	  var tem=[] ; 
						    	 
						   		  $.each( temp, function( index, value ){
							    		console.log(value[2]); 
							    		// 15
				//alert("data exists");
							    		
							    		/* $('input[name="test"][value="' + value[2] + '"]').prop("checked", true);*/
							    		 selecteditems.push(value[2]); 
							    		 $("#"+value[2]).attr("data-serialid",value[0]);
							    		 serialid.push(value[0]);
						    		});
					    		  }
					    }
					 });	
		    	 }
		    	 else
		    		 {
		    		 swal("Please Select DDO Code");
		    		 }

		});


$("form[name='myform']").validate({
    // Specify validation rules
    rules: {
    	departmentAllowdeducName:"required",
    		
    	isType:
    		{
    			required:true,
    			min:1
    		},
    },
    // Specify validation error messages
    messages: {
    	departmentAllowdeducName: "Please Enter Allowance Or Deduction Name",
    	isType: "Please Select Allownance Or Deduction type",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

function validateAllowDeduct() {
	// alert('inside validateUIDUniqe');
	var allowdeductName = document.getElementById("allowanceDeduction").value;
	var isType = document.getElementById("isType").value;
	
	
	
		if (allowdeductName != '') {
			$
					.ajax({
						type : "GET",
						url : context+"/admin/validateAllowDeduct/"+allowdeductName+"/"+isType,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							console.log(data);
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#allowanceDeduction').val(allowdeductName);
								status = true;
							} else if (checkFlag > 0) {

								swal(allowdeductName + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("allowanceDeduction").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}
