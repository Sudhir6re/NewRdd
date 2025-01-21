/*
$("#departmentCodeAdmin").change(function() 
		{
			  var deptCode = $("#departmentCodeAdmin").val();
			  
			// alert("dptCode is"+deptCode);
		    	 if (deptCode != '') 
		    	 {
		    		 $.ajax({
		    			    type: "GET",
		    			    url: "../admin/findallDDOLevel2Ddo/"+deptCode,
		    			    async: true,
		    			    contentType:'application/json',
		    			    error: function(data){
		    			  	  console.log(data);
		    			    },
		    			    success: function(data){
		    			  	  console.log(data);
		    			  	 var temp = data;
		    			 		  $.each( temp, function( index, value ){
		    				    		console.log( value[0] ); 
		    				    		 $('#ddoCode').append("<option value="+value[0]+">" + value[1]+""+"("+ value[0]+")"+ "</option>");
		    			  		});
		    			  	}
		    			});
}
		});



*/


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

   $("#ddoCode").select2();  
     
     
     
     $(".btnUpdate").click(function(){
    	
    	 var action= 1;
    	 serialid.push(0);   	 
    	 
   	 	 ///// var input = $("#departmentCodeAdmin").val();
   	 	  
   	 	 /* if(input=="undefined" || input==undefined){
   	 		  input=$("#departmentCodeAdmin").val();
   	 		  
   	 	  }*/
   	 	var effectiveDate = $("#effectiveDate").val();
	   var ddoCode = $("#ddoCode").val();
	 	
   	 	if(effectiveDate=="" || effectiveDate=="undefined"){
   	 		swal("Please select effective date");
   	 	}
   	 	else if ((selecteditems.length != '') ) {
   	 	$( "#loaderMainNew").show();
    		 $.ajax({
			      type: "GET",
			     /* url: "../master/saveMpgDdoAllowDeduc/"+selecteditems+"/"+input +"/"+action+"/"+serialid+"/"+effectiveDate,*/
			      url: context+"/ddo/saveDeptEligibilityAllowDeducAdmin/"+selecteditems+"/"+action+"/"+serialid+"/"+effectiveDate+"/"+ddoCode,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    		$( "#loaderMainNew").hide(); 
			      },
			      success: function(data){
			    		$( "#loaderMainNew").hide();
			    	 swal("Saved Successfuly !", {
			    	      icon: "success",
			    	  });
			    	  setTimeout(function() {
						    location.reload(true);
						}, 3000);
			    	  $("#tblDataTable").show();
			    	  $("#selectAllDiv").show();
			    }
			 });		 
    	 }
    	 else
		 {
		 swal("Please Select Department Name");
		 }
    	  });
     
    
     
     $("#ddoCode").change(function() 
    			{
    				
    				 //$('input[name="test"][value="' + value[2] + '"]').prop("checked", true);
    				$('input[type=checkbox]').each(function() 
    		{ 
    		        this.checked = false; 
    		}); 
    				 
    				  var input = $("#ddoCode").val();
    				  
    				//  alert("DDO CODE is"+input);
    			    	 if (input != '') 
    			    	 {
    			    		 $.ajax({
    						      type: "GET",
    						      url: context+"/ddo/findallowDeductLevel1/"+input,
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
    								    		
    								    		 $('input[name="test"][value="' + value[1] + '"]').prop("checked", true);
    								    		 selecteditems.push(value[1]); 
    								    		 $("#"+value[1]).attr("data-serialid",value[1]);
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
});
$("form[name='myform']").validate({
    // Specify validation rules
    rules: {
    	departmentCodeAdmin:
    	{
    		required:true,
    		min:1
    	},
    		
    	ddoCode:
    		{
    			required:true,
    			min:1
    		},
    },
    // Specify validation error messages
    messages: {
    	departmentCodeAdmin: "Please Select Department Name",
    	ddoCode: "Please Select DDO Code",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
     