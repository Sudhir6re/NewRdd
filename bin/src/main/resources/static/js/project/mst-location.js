$(document).ready(function(){
	 
	     $("#department_id").change(function() 
	  			{
	    	
	  				  var departmentId = $("#department_id").val();
	  				 // alert("DDO CODE is "+departmentId);
	  			    	 if (department_id != '') 
	  			    	 {
	  			    		 $.ajax({
	  						      type: "GET",
	  						      url: "../mstSubDept/"+departmentId,
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
	 						    		//  console.log(data);
	 						    		 $('#subdepartment_id').empty();
	 						    		$('#subdepartment_id').append("<option value='0'>Please Select</option>");
	 							    	 var temp = data;
	 							   		  $.each( temp, function( index, value ){
	 								    		console.log( value[2] ); 
	 								    		 $('#subdepartment_id').append("<option value="+value[0]+">" + value[2] + "</option>");
	 							    		});
	 						    		  }
	 						    	  else
	 						    		  {
	 						    		 $('#subdepartment_id').empty();
	 						    		 $('#subdepartment_id').append("<option value='0'>Please Select</option>");
	 						    		  swal("Record not found !!!");
	 						    		  }
	  						    	}
	  						 });	
	  			    	 }
	  			    		
	  			});
	     
	     
	     $("#subdepartment_id").change(function() 
		  			{
		  				  var subDepartmentId = $("#subdepartment_id").val();
		  				 // alert("DDO CODE is "+departmentId);
		  			    	 if (department_id != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "../allSubDeptDDO/"+subDepartmentId,
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
		 						    		console.log(data);
		 						    		 $('#ddoCode').empty();
		 						    		$('#ddoCode').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#ddoCode').append("<option value="+value[0]+">" + value[1] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#ddoCode').empty();
		 						    		 $('#ddoCode').append("<option value='0'>Please Select</option>");
		 						    		  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }
		  			    		
		  			});
	     //for fetching states associates with country
	     $("#countryId").change(function() 
		  			{
	    	// alert("hello");
		  				  var countryId = $("#countryId").val();
		  				 // alert("DDO CODE is "+departmentId);
		  			    	 if (countryId != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "../master/fetchStates/"+countryId,
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
		 						    		console.log(data);
		 						    		 $('#state').empty();
		 						    		$('#state').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#state').append("<option value="+value[1]+">" + value[2] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#state').empty();
		 						    		 $('#state').append("<option value='0'>Please Select</option>");
		 						    		  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }
		  			    		
		  			});
	     
	     
	     //for fetching city  associates with states
	     $("#state").change(function() 
		  			{
		  				  var stateId = $("#state").val();
		  				 // alert("DDO CODE is "+departmentId);
		  			    	 if (stateId != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "../master/fetchDistricts/"+stateId,
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
		 						    		console.log(data);
		 						    		 $('#district').empty();
		 						    		$('#district').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#district').append("<option value="+value[3]+">" + value[4] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#district').empty();
		 						    		 $('#district').append("<option value='0'>Please Select</option>");
		 						    		  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }
		  			    		
		  			});
	     
	     
	     
	   //for fetching tauka associates with state
	     $("#district").change(function() 
		  			{
	    	// alert("hello");
		  				  var districtCode = $("#district").val();
		  				 // alert("DDO CODE is "+departmentId);
		  				var stateId = $("#state").val();
		  			    	 if (countryId != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "../master/fetchTaluka/"+districtCode+"/"+stateId,
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
		 						    		console.log(data);
		 						    		 $('#taluka').empty();
		 						    		$('#taluka').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#taluka').append("<option value="+value[6]+">" + value[5] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#taluka').empty();
		 						    		 $('#taluka').append("<option value='0'>Please Select</option>");
		 						    		  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }
		  			    		
		  			});
	     //end tahsil
	     
	     $("#btnSave").click(function() 
		  			{
	    	// alert("hello");
	    	 
	    	
	     var data = $("#formData").serialize();
	     console.log(data);
	     /*  $.ajax({
	         data: data,
	         type: "post",
	         url: "adapter.php",
	         success: function(data){
	         alert("Data: " + data);
	         }
	     });
	     */
	     
		  		     });
	     
	     
	     $(".abc").keyup(function(){
	    	 
	    	   // $(this).css("background-color", "pink");
	    	 
	    	 
	    	 
	    	  var countryId = $("#countryId").val();
	    	  var countryText = $("#countryId option:selected").text();
	    	  var countryTextShort =countryText.slice(0, 3);
	    	  
	    
	    	    
	    	 var districtId=$("#district").val();
	    	 var districtText = $("#district option:selected").text();
	    	 var districtTextShort =districtText.slice(0, 3);
	    	 
	    	 
	    	 var stateId = $("#state").val();
	    	 var stateText = $("#state option:selected").text();
	    	 var stateTextShort =stateText.slice(0, 3);
	    	 
	    	
	    	
	     	   
	     	 
	    	  var villageName = $("#villageName").val();
	    	  var villageTextShort =villageName.slice(0, 3);
	     	 
	     	 
	    	  var pinCode = $("#pinCode").val();
	     	// alert("pinCode="+pinCode);
	     	 
	     	 
	     	 
	    	//  var taluka = $("#taluka").val();
	    	  
	    	  
	    	  var taluka = $("#taluka option:selected").text();
	    	  var talukaTextShort =taluka.slice(0, 3);
	    	  
	    	  $("#locationId").val('');
	    	  $("#locationId").val(countryTextShort+stateTextShort+districtTextShort+talukaTextShort+villageTextShort);
	    	  
	    	  
	    	  });
	     
	 
		  			    		
	     
	  });
