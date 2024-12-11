$(document)
		.ready(
				function() {

					var varMessage = $("#message").val();

					if (varMessage != "" && varMessage != undefined) {
						swal("Record Mapped Successfully!", {
							icon : "success",
						});
					}

					$('#labelLevel1').hide();
					$('#labelLevel2').hide();
					$('#labelLevel3').hide();
					$('#labelLevel4').hide();
					
					
					
				});
				
$("form[name='workflowCharterAssignWithDDOModel']").validate({
	// Specify validation rules
	rules : {
		department_code : {
			required : true,
			min : 1
		},
		/*no_of_level : "required",*/
		subDepartmentId : {
			required : true,
		    min:1,
		},
		no_of_level : {
			required : true,
			//min:1
		},
		pensClerkDDOCode : {
			required : true,
			minlength : 1,
		},
		pensFcDDOCode : {
			required : true,
		    minlength : 1,
		},
		pensFcDDOCode : {
			required : true,
			minlength : 1,
		},
		pensDDOCode : {
			required : true,
		    minlength : 1,
		},
		pensInwDDOCode : {
			required : true,
			  minlength : 1,
		},
		pensAstDDOCode : {
			required : true,
			minlength : 1,
		},
		pensAaoDDOCode : {
			required : true,
			minlength : 1,
		},
		pensAoDDOCode : {
			required : true,
		    minlength : 1,
		},

		pensSaoDDOCode : {
			required : true,
			minlength : 1,
		},
		pensDyCao1DDOCode : {
			required : true,
			minlength : 1,
		},
		pensCashierDDOCode : {
			required : true,
			minlength : 1,
		},
	},
	// Specify validation error messages
	messages : {
		department_code : "Please Select Department Name",
		subDepartmentId : "Please Select Division/Subdivision Name",
		no_of_level : "Please Enter No of Levels ",
		pensClerkDDOCode : "Please Select Pension Clerk ",
		pensFcDDOCode : "Please Select Pension First Clerk",
		pensDDOCode : "Please Select EE/DDO/DYCAO 2",
		pensInwDDOCode : "Please Select Pension Inward Clerk ",
		pensAstDDOCode : "Please Select Pension Assistant",
		pensAaoDDOCode : "Please Select Pension AAO",
		pensAoDDOCode : "Please Select Pension AO",
		pensSaoDDOCode : "Please Select Pension SAO",
		pensDyCao1DDOCode : "Please Select Pension DY CAO 1",
		pensCashierDDOCode : "Please Select Pension Cashier Bill",
	},
	// Make sure the form is submitted to the destination defined
	// in the "action" attribute of the form when valid
	submitHandler : function(form) {
		form.submit();
		$("#loaderMainNew").show();
	}
});

$('#btnReset').click(function() {
	$("#department_id").select2("val", "0");
});










$("#pensFcDDOCode").change(function(){  
	var officeId=($('option:selected', this).attr('data'));
	var context=$("#appRootPath").val();
	var isType=$("#isType").val();
	
	if(officeId==undefined || officeId==''){
		officeId=0;
	}
	  
		$.ajax({
			type : "GET",
			url : context+"/admin/getOfficeDddoCode/"
						+ officeId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			// alert("error");
			},
			 beforeSend: function(){
				 $("#loaderMainNew").show();
			   },
			   complete: function(){
				   $("#loaderMainNew").hide();
			   },
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
				$("#pensOfficeDDOCode").empty();
				 var newOption = $('<option value="0">Please Select</option>');
           	 $('#pensOfficeDDOCode').append(newOption);
               for(var i=0;i<len;i++){
            	   
            	   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'"   data="'+data[i].officeId+'">'+data[i].ddoCode+'</option>');
            	   
            	 /*  if(officeId=="1"){
            		   if(data[i].officeId=="2"  && data[i].levelHierarchy=="29"){
                		   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'"   data="'+data[i].officeId+'">'+data[i].ddoCode+'</option>');
                	   }
            	   }
            	   
            	   if(officeId=="2"){
            		   if(data[i].officeId=="3" && data[i].levelHierarchy=="29"){
            			   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'"  data="'+data[i].officeId+'">'+data[i].ddoCode+'</option>');
            		   }
            	   }
            	   
            	   if(officeId=="3"){
            		   if(data[i].officeId=="4" && data[i].levelHierarchy=="29"){
            			   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'"  data="'+data[i].officeId+'">'+data[i].ddoCode+'</option>');
            		   }
            	   }
            	   
            	   
            	   if(officeId=="4"){
            		   if(data[i].officeId=="4" && data[i].levelHierarchy=="29"){
            			   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'" >'+data[i].ddoCode+'</option>');
            		   }
            	   }
            	   
            	   */
               	
               	 $('#pensOfficeDDOCode').append(newOption); 
               }				
			   }
			}
		});
});



$("#pensClerkDDOCode").change(function(){  
	var officeId=($('option:selected', this).attr('data'));
	var context=$("#appRootPath").val();
	var subDepartmentId=$("#subDepartmentId").val();
	var isType=$("#isType").val();
	
	
	if(officeId==undefined || officeId==''){
		officeId=0;
	}
	  
		$.ajax({
			type : "GET",
			url : context+"/admin/getOfficeDddoCode/"
						+ officeId+"/"+subDepartmentId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			 beforeSend: function(){
				 $("#loaderMainNew").show();
			   },
			   complete: function(){
				   $("#loaderMainNew").hide();
			   },
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
				$("#pensFcDDOCode").empty();
				 var newOption = $('<option value="0">Please Select</option>');
           	 $('#pensFcDDOCode').append(newOption);
               for(var i=0;i<len;i++){
            		   if(officeId==data[i].officeId  && data[i].levelHierarchy=="29" ){
                		   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'"  data="'+data[i].officeId+'">'+data[i].ddoCode+'</option>');
                	   }
               	 $('#pensFcDDOCode').append(newOption); 
               }				
			   }
			}
		});
});





$("#subDepartmentId").change(function(){  
	var officeId=($('option:selected', this).attr('data'));
	var context=$("#appRootPath").val();
	var subDepartmentId=$("#subDepartmentId").val();
	var isType=$("#isType").val();
	
	
	if(officeId==undefined || officeId==''){
		officeId=0;
	}
	
		$.ajax({
			type : "GET",
			url : context+"/admin/getOfficeDddoCode/"
						+ officeId+"/"+subDepartmentId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			 beforeSend: function(){
				 $("#loaderMainNew").show();
			   },
			   complete: function(){
				   $("#loaderMainNew").hide();
			   },
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
				$("#pensFcDDOCode").empty();
				 var newOption = $('<option value="0">Please Select</option>');
           	 $('#pensFcDDOCode').append(newOption);
               for(var i=0;i<len;i++){
            		   if( data[i].levelHierarchy=="13" ){
                		   newOption = $('<option value="'+data[i].ddoRegId+','+data[i].ddoCode+'"  data="'+data[i].officeId+'">'+data[i].ddoCode+'</option>');
                	   }
               	 $('#pensFcDDOCode').append(newOption); 
               }				
			   }
			}
		});
});


