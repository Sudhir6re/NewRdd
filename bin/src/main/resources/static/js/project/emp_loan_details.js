$(document).ready(function(){
	
	
	/*var selecteditems  = [];
	
	
	     
     $("#btnSave").click(function(){
    	 
   	 	 var ddoCode = $('#ddo_code').val();
   	 	 var sevaarthId = $('#sevaarthId').val(); 
   	
    	 
    		 $.ajax({
			      type: "GET",
			      url: "../mapping/mapEmployee/"+sevaarthId+"/"+ddoCode,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    	  alert(data);
			      },
			      success: function(data){
			   
			    	 location.reload(true);
			    	 swal("Employee Attach Successfuly to DDO");
			    	
			    	 
			    }
			 });	
    		 
    	 
    	  });
   
     
     
     $(".delete").click(function(){
    	   var sevaarthId=$(this).attr('value');
    	   alert("hi" + sevaarthId);
    	   swal({
    		   title: "Are you sure?",
    		   text: " to Dettach Employee!",
    		   icon: "warning",
    		   buttons: true,
    		   dangerMode: true,
    		 })
    		 .then((willDelete) => {
    		   if (willDelete) {
    			   
    			   
    			     $.ajax({
    				      type: "GET",
    				      url: "../mapping/dettachEmployee/"+sevaarthId,
    				      async: true,
    				      error: function(data){
    				    	  console.log(data);
    				    	  alert("eror is"+data);
    				      },
    				      success: function(data){
    				    	   swal("Dettach Successfully....!", {
    				    	       icon: "success",
    				    	     });
    				    	   setTimeout(function() {
    							    location.reload(true);
    							}, 3000);
    				      }
    				 });
    		   } else {
    		     swal("Dettach Cancelled...!");
    		   }
    		 });
    	   
    	   
    	  });
*/
 });


function SearchEmployee(e) {
	
	var sevaarthid = document.getElementById("sevaarth").value;
	//var ddocode = document.getElementById("ddocode").value;
	
		
		$.ajax({
			type : "GET",
			url : "../master/searchEmpDtls/"+sevaarthid,      //+"/"+ddocode
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
			 console.log(data);
			// alert("error");
			 
			 swal("Searched Employee is not GPF");
			},
			success : function(data) {
				 console.log(data);
				///alert("success");
				//$("#SevaarthId").val(data[0][0]);
				document.getElementById("sevaarthId").value=data[0];
				document.getElementById("orginstnm").value=data[3];
				$('#designation').empty();
				$('#designation')
						.append(
																					"<option selected value="
																							+ data[1]
																							+ ">"
																							+ data[1]
																							+ "</option>");
				$('#empName')
				.append(
						"<option selected value="
						+ data[1]
						+ ">"
						+ data[5]
						+ "</option>");
				document.getElementById("designation").value=data[1];
				document.getElementById("gpfNo").value=data[5];
				//alert(document.getElementById("empName").value=data[5]);
				//document.getElementById("empName").value=data[5];
				
			
				//document.getElementById("employeeid").value=data[5];
				
				
				$('#payscalelevel').empty();
				$('#payscalelevel')
						.append(
								"<option value='0'>Please Select</option>");
				var temp = data[4];
				$
						.each(
								temp,
								function(index,
										value) {
								console.log(data);
									
								
								$(
											'#loanName')
											.append(
													"<option value="
															+ value[0]
															+ ">"
															+ value[0]
															+ "</option>");
								$(
								'#principalAmt')
								.append(
										"<option value="
										+ value[0]
										+ ">"
										+ value[1]
										+ "</option>");
								$(
								'#interestAmt')
								.append(
										"<option value="
										+ value[0]
										+ ">"
										+ value[2]
										+ "</option>");
								$(
								'#EMIAmt')
								.append(
										"<option value="
										+ value[0]
										+ ">"
										+ value[3]
										+ "</option>");
								
								});
				
			}
	    });
	
		
		
		$('#sevaarthnew').val(sevaarthid);
	
			 
		
		$('#btnverify')
		.click(
				function() {
					
//					//swal("Please enter the Remarks");
//					document.getElementById("sevaarthid ").readOnly =false;
					
					
					
					var sevaarthid = $('#sevaarthnew').val();


					if (sevaarthid == "") {
						swal("Paybill is already Rejected");
					}  else if (sevaarthid != '') {
						$
								.ajax({
									type : "GET",
									url : "../master/addEmployeeLoanDetails/"
											+ sevaarthid,
									async : true,
									contentType : 'application/json',
									error : function(data) {
										console.log(data);
									},
									success : function(data) {
										console.log(data);

										if ($("#is_changed").val() == 1) {
											 window.location.href="../master/addEmployeeLoanDetails/"+ sevaarthid;

										} else {
											 window.location.href="../master/addEmployeeLoanDetails/"+ sevaarthid;										}

									}
								});
					}
				});
		
		
		
		
		
//			e.preventDefault();
//			return false;
	
}