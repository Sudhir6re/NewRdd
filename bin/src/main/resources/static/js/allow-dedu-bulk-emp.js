
	$("#selectAll").change(function(){
						if($(this).prop("checked")){
							  $(".allowCheckBox").prop("checked",true);
							  $(".checkbox").val(true);
						}else{
							  $(".allowCheckBox").prop("checked",false);
							  $(".checkbox").val(false);
						}
					});



$("#isType").change(function(){
		
		 var context=$("#context").val();
		  var isType=$("#isType").val();
		  
		  
			$.ajax({
				type : "GET",
				url : context+"/ddoast/allowancDeductionByType/"
							+ isType,
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
					$("#departmentAllowdeducCode").empty();
					 var newOption = $('<option value="0">Please Select</option>');
	            	 $('#departmentAllowdeducCode').append(newOption);
	                for(var i=0;i<len;i++){
	                	  newOption = $('<option value="'+data[i].departmentAllowdeducCode+'">'+data[i].departmentAllowdeducName+'</option>');
	                	 $('#departmentAllowdeducCode').append(newOption); 
	                }				
				   }
				}
			});
			
			
	     });
	
	
	
	jQuery(document).ready(function($) {
	$("#employeeLst").hide();
	});
	
	$('#schemeBillGrpId').change(function(e) {
		var schemeBillGrpId=$(this).val();
		
		  var departmentAllowdeducCode=$("#departmentAllowdeducCode").val();
		
		 var context=$("#context").val();
		$.ajax({
			type : "GET",
			url :  context+"/ddoast/getListEmpBySchemBillGroup/" + schemeBillGrpId+"/"+departmentAllowdeducCode,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);
				// alert("error is"+data);
			},
			 beforeSend: function(){
				 $("#loaderMainNew").show();
			   },
			   complete: function(){
				   $("#loaderMainNew").hide();
			   },
			success : function(data) {
				console.log(data);
				// alert("data");
				flag = data.length;
				var dataTable= $('#employeeLst').dataTable();
				dataTable.fnClearTable();
				
				if(data.length>0){
					var isInProcess=checkIsPaybillInProcess(schemeBillGrpId);
					if(isInProcess!="0"){
						swal("Paybill is in process !!!");
						$("#employeeLst").hide();
					}else{
						$("#employeeLst").show();
						
						//	dataTable.api().columns([2,3,4]).visible(false);
							
							for(var i=0;i<data.length;i++){
								   var sevaarthId=data[i].sevaarthId;
								
								
								   
								   
								   var isCheck=false;
								
								  var empMapped=data[i].empMapped;
								if(parseInt(empMapped)>0){
									empMapped="checked";
									isCheck=true;
								}else{
									empMapped="";
								}
								
									
							   var checkBox='<input type="checkbox" '+empMapped+' name="lstDeptEligibilityForAllowAndDeductModel[' + i + '].isChecked"  	class="allowCheckBox"/>';	
							
							   var employeeFullName=data[i].employeeFullName;
							   
							   var employeeId=data[i].employeeId;
							  // var employeeId="<label  style='display:none;'>"+data[i].employeeId+"</label>";
							   
							   var hiddenCheckbox='<input id="checkBox'+i+'" value="'+isCheck+'" class="checkbox" type="hidden" name="lstDeptEligibilityForAllowAndDeductModel[' + i + '].checkBox" '+empMapped+' />';
								 
							   
							   employeeId='<input type="hidden" name="lstDeptEligibilityForAllowAndDeductModel[' + i + '].employeeId" value="'+employeeId+'" />';
							    sevaarthId='<input   type="hidden" name="lstDeptEligibilityForAllowAndDeductModel[' + i + '].sevaarthId" value="'+sevaarthId+'" />';
							    
							    var dedducCode='<input  type="hidden" name="lstDeptEligibilityForAllowAndDeductModel[' + i + '].departmentAllowdeducCode" value="'+departmentAllowdeducCode+'" />';
							   
							    
							    checkBox=checkBox+sevaarthId+employeeId+dedducCode+hiddenCheckbox;
							    
							   dataTable.dataTable().fnAddData([employeeFullName,checkBox]);
								
							}
					}
					
				}else{
					swal("No employee found for this scheme bill group !!!");
					$("#employeeLst").hide();
				}
				
			}
		});
	
	});
	
	
	function checkIsPaybillInProcess(schemeBillGrpId)
	{
		$("#loaderMainNew").show();
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../ddoast/PaybillInBulkProcess/"+schemeBillGrpId,
		      async: false,
		      dataType : 'json',
		    // contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		    		$("#loaderMainNew").hide();
		    	 // alert(data);
		      },
		      success: function(data){
		  		$("#loaderMainNew").hide();
		    	  if(parseInt(data) >0 ) {
		    		  flag=1;
		    	  }
		    	  else
		    		  {
		    		 flag=0;
		    		  }
		    }
		 });
		 return flag;
	}

	
	
	//allowCheckBox   //checkBox
	
	
	$("table").on("change", ".allowCheckBox", function() {
		  var row = $(this).closest("tr");
		  var chk =row.find(".allowCheckBox").prop("checked");
		  row.find(".checkbox").val(chk);
		});
	
	
	
	

	$("form[name='saveAllowDeduBulkForEmpForm']").validate({
	    // Specify validation rules
	    rules: {
	    	isType : {
				required : true,
				min : 1
			},
			departmentAllowdeducCode : {
				required : true,
				min : 1
			},
			schemeBillGrpId : {
				required : true,
				min : 1
			},
	    },
	    // Specify validation error messages
	    messages: {
	    	isType: "Please Select Type Of Component ",
	    	departmentAllowdeducCode: "Please Select Pay-Item",
	    	schemeBillGrpId: "Please Select Bill Description",
	    },
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form,event) {
	      if($("#employeeLst>tbody>tr").length>0){
	    		 $("#loaderMainNew").show();
	    	  form.submit();
	      }else{
	    	 // form.submit();
	    	  swal("no data found for update");
	    	  event.preventDefault();
	      }
	    }
	  });
	
	
	
	
	
	
	
	
	
	  