	var context ="";
$(document).ready(function() {
	context = $("#appRootPath").val();
});

$("#cmbBankName").change(function() 
		 {
		 	  var bankId = $("#cmbBankName").val();
		 // alert("bank is "+bankId);
		     	 if (bankId != '') 
		     	 {
		     		 $.ajax({
		 			      type: "GET",
		 			      url: context+"/ddoast/mstBank/"+bankId,
		 			      async: true,
		 			      contentType:'application/json',
		 			      error: function(data){
		 			    	 // console.log(data);
		 			      },
		 			     beforeSend : function(){
			 					$( "#loaderMainNew").show();
			 					},
			 				complete : function(data){
			 					$( "#loaderMainNew").hide();
			 				},
		 			      success: function(data){
		 			    	 // console.log(data);
		 			    	  // alert(data);
		 			    	 var len=data.length;
		 			    	  if(len!=0)
		 			    		  {
		 			    		// console.log(data);
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
		 			    		// swal("Record not found !!!");
		 			    		  }
		 			    	}
		 			 });	
		     	 }
		     	 
		     	
		     		
		 });


$("#cmbBranchName").change(function() 
		 {
		 	  var branchId = $("#cmbBranchName").val();
		 // alert("bank is "+bankId);
		     	 if (branchId != '') 
		     	 {
		     		 $.ajax({
		 			      type: "GET",
		 			      url: context+"/ddoast/getIfscCodeByBranchId/"+branchId,
		 			      async: true,
		 			      contentType:'application/json',
		 			      error: function(data){
		 			    	 // console.log(data);
		 			      },
		 			     beforeSend : function(){
			 					$( "#loaderMainNew").show();
			 					},
			 				complete : function(data){
			 					$( "#loaderMainNew").hide();
			 				},
		 			      success: function(data){
		 			    	 // console.log(data);
		 			    	  // alert(data);
		 			    	 var len=data.length;
		 			    	  if(len!=0)
		 			    		  {
		 			    		// console.log(data);
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