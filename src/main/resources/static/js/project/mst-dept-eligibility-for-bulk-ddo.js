
	var selecteditems  = [];
	var serialid  = [];
	
	
	var selecteditems1  = [];
	var serialid1  = [];
	
	
	
	

	
	
	
	
	
	
$(document).ready(function(){

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
     
     
     
     
     
// 	$("#selectAll").change(function(){
//		 selecteditems  = [];
//		 serialid  = [];
//		$(".chk").prop("checked",true);
//		  $('.chk:checkbox:checked').each(function () {
//			    selecteditems.push($(this).val());
//			    serialid.push($(this).attr("data-serialid"));
//			});
//	});
	
 	
 	$("#selectAll").change(function(){
 		 selecteditems  = [];
		 serialid  = [];
		if($(this).prop("checked")){
			  $(".chk").prop("checked",true);
		    $('.chk:checkbox:checked').each(function () {
		        selecteditems.push($(this).val());
			    serialid.push($(this).attr("data-serialid"));
				});
		}else{
			  $(".chk").prop("checked",false);
		}
	});
 	

 	
 	
     
     
     
     
     ///for multiple ddo
     
 	var selecteditems1  = [];
	var serialid1  = [];
     $(".checkbox").click(function(){
    	 if($(this).is(":checked")){
        	 selecteditems1.push($(this).attr("value"));
        	// alert("serial id are"+selecteditems1);
        	// selecteditems.push($(this).attr("data-serialid"));
         }
         else if($(this).is(":not(:checked)")){
        	 //alert($(this).attr("value"));
        	 var removeItem1 = $(this).attr("value");

        	 selecteditems1 = jQuery.grep(selecteditems, function(value) {
        	   return value != removeItem1;
        	 });
        	 serialid1.pop($(this).attr("data-serialid"));
        	console.log("serial id are"+selecteditems1);
         }  
    	 
     });
     
     
     //end
     console.log(selecteditems);
     
     $(".btnUpdate").click(function(){
    	
    	 var action= 1;
    	// serialid.push(0);   	 
    	 //alert("checking for ddo"+selecteditems1)
   	 	  var input = $("#department_code").val();
   	 	  
   	 	  if(input=="undefined" || input==undefined){
   	 		  input=$("#departmentCode").val();
   	 	  }
   	 	  
   	 	  
   	 	selecteditems1=[];
   	 	  
   	 	 selecteditems1.push($("#checkbox").val());
   	 	 
   	 	 

   	 	       serialid = [];
   	 	       selecteditems = []; 
		    $('.chk:checkbox:checked').each(function () {
		    	selecteditems.push($(this).val());
				  serialid.push($(this).attr("data-serialid"));
				});
   	 	 
   	 	 

   	 	  
   	 	  
   	 	var effectiveDate = $("#effectiveDate").val();
	   var ddoCode = $("#checkbox").val();
	 	
   	 	if(effectiveDate=="" || effectiveDate=="undefined"){
   	 		swal("Please select effective date");
   	 	}
   	 	else if ((selecteditems.length != '') ) {
   	 		
   	 		$(".loaderMainNew").show();
   	 		
    		 $.ajax({
			      type: "GET",
			      url: "../moderator/saveDeptEligibilityAllowDeducLevel1/"+selecteditems+"/"+input +"/"+action+"/"+serialid+"/"+effectiveDate+"/"+selecteditems1,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    		$(".loaderMainNew").hide();
			      },
			      success: function(data){
			    		$(".loaderMainNew").hide();
			    	  
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
    						      url: "../moderator/deptEligibilityForAllowAndDeduct1/"+input,
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
    						    	  $("#selectAllDiv").show();
    						    	  
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
     
 	
	 $.ajax({
	      type: "GET",
	      url: "../moderator/deptEligibilityForAllowAndDeduct6",
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
	      url: "../moderator/deptEligibilityForAllowAndDeduct5",
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
		      url: "../moderator/deleteMappedEmployeee/"+input ,
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
    url: "../master/findallDDOcodebylevel2",
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
			///alert("Testing----!!!!");
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
					      url: "../moderator/deptEligibilityForAllowAndDeductDDOWise/"+input,
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
					    	  $("#selectAllDiv").show();
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
					      url: "../moderator/deptEligibilityForAllowAndDeduct1/"+input,
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
					    	  $("#selectAllDiv").show();
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
					      url: "../moderator/deptEligibilityForAllowAndDeduct1/"+input,
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
					    	  $("#selectAllDiv").show();
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









$("#checkbox").change(function(){	
	var ddoCode=$(this).val();
	$(".chk").prop("checked",false);
	
	$(".loaderMainNew").show();
	$.ajax({
				type : "GET",
				url : "../moderator/getAllowDeductComponentByDDO/"
						+ ddoCode,
				async : true,
				contentType : 'application/json',
				error : function(data) {
					console.log(data);
				},
				success : function(data) {
					$(".loaderMainNew").hide();
					$('html, body').animate({
						scrollTop : $("#tblDataTable").offset().top},2000);
					console.log(data);
					var len = data.length;
					if (len == 0) {
						$("#btnSave")
								.show();
						$("#btnUpdate")
								.hide();
					} else {
						selecteditems = [];
						serialid = [];
						$("#btnUpdate")
								.show();
						$("#btnSave")
								.hide();
						var temp = data;
						var tem = [];

						$.each(temp,function(index,value) {
											console.log(value[0]); // 15
											$('input[name="test"][value="' + value[2]+ '"]').prop("checked",true);
											 selecteditems.push(value[2]); 
								    		 $("#"+value[2]).attr("data-serialid",value[0]);
								    		 serialid.push(value[0]);
										});
					}
				}
			});

});











