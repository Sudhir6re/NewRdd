function checkisdataPresent(monthName,yearName,subDepartmentId,subDepartmentIdSE,subDepartmentIdEE) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../admin/forAllCheckisdataPresent/"+monthName +"/"+yearName +"/"+ subDepartmentId +"/"+ subDepartmentIdSE +"/"+ subDepartmentIdEE,
		      async: false,
		      dataType : 'json',
		    // contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		    alert(data);
		      },
		      success: function(data){
		    	  console.log("response from server"+data);
		    		 console.log(data);
						var len = data.length;
						if (len != 0) { 
		                for(var i=0;i<len;i++){
		                	var noEmp=data[i].noEmp;
		                	if(noEmp!=''){
		                		if(Number(noEmp)>0 ){
		                			flag=1;
			                	}
		                	}else{
		                		 flag=0;
		                	}
		                }				
					   }
		    }
		 });
		 return flag;
	}	


$("#GenerateReport").click(function(e){
    	
	      var month = $('#monthId').val();
	 	 var year = $('#yearId').val(); 
	 	 var subDepartmentId = $('#subDepartmentId').val(); 
 	 	 var subDepartmentIdSE = $('#subDepartmentIdSE').val(); 
 	 	 var subDepartmentIdEE = $('#subDepartmentIdEE').val(); 
	 	 
 		removeErrorClass($('#monthId'));
 	 	removeErrorClass($('#yearId'));
 	 	removeErrorClass($('#subDepartmentId'));
 	 	removeErrorClass($('#subDepartmentIdSE'));
 	 	removeErrorClass($('#subDepartmentIdEE'));
 	 	
 	 	 
	     if(Number(month)>0 && Number(year)>0 ){
	    	     
	    	 if(Number(subDepartmentId)>0 ||  Number(subDepartmentIdSE)>0  ||  Number(subDepartmentIdEE)>0){

	    		 
	    		 if(Number(subDepartmentId)>0){
	    	 	 		var isdataPresent = checkisdataPresent(month,year,subDepartmentId,subDepartmentIdSE,subDepartmentIdEE);
	    	 	 		if(isdataPresent == 0)
	    	 	 			{
	    	 	 			swal.fire("Record not found!");
	    	 	 			e.preventDefault();
	    	 	 			}
	    	 	 	 }
	    	 	 	 else if(Number(subDepartmentIdSE)>0){
	    	 	 		 var isdataPresentSE = checkisdataPresent(month,year,subDepartmentId,subDepartmentIdSE,subDepartmentIdEE);
	    	 	 		 if(isdataPresentSE == 0)
	    	 	 		 {
	    	 	 			 swal.fire("Record not found!");
	    	 	 			 e.preventDefault();
	    	 	 		 }
	    	 	 	 }
	    	 	 	 else if(Number(subDepartmentIdEE)>0){
	    	 	 		 var isdataPresentEE = checkisdataPresent(month,year,subDepartmentId,subDepartmentIdSE,subDepartmentIdEE);
	    	 	 		 if(isdataPresentEE == 0)
	    	 	 		 {
	    	 	 			 swal.fire("Record not found!");
	    	 	 			 e.preventDefault();
	    	 	 		 }
	    	 	 	 }
	    	 
	    	 }else {
		  	 		 addErrorClass($('#subDepartmentId'),"Please select Office !!!");
		  	 		 addErrorClass($('#subDepartmentIdSE'),"Please select Office !!!");
		  	 		 addErrorClass($('#subDepartmentIdEE'),"Please select Office !!!");
		  	 		 e.preventDefault();
	    	 }
	     }else{
	    	 if(month=="0"){
	  	 		addErrorClass($('#monthId'),"Please select Month !!!");
	  	 		e.preventDefault();
	  	 	 }
	  	 	 
	  	 	 
	  	 	 if(year=="0"){
	  	 		addErrorClass($('#yearId'),"Please select Year !!!");
	  	 		e.preventDefault();
	  	 	 }
	  	 	 
	  	 	 
	  	 	 if(subDepartmentId=="0"){
	  	 		 addErrorClass($('#subDepartmentId'),"Please select Office !!!");
	  	 		 e.preventDefault();
	  	 	 }
	  	 	 
	  	 	 
	  	 	 if(subDepartmentIdSE=="0"){
	  	 		 addErrorClass($('#subDepartmentIdSE'),"Please select Office !!!");
	  	 		 e.preventDefault();
	  	 	 }
	  	 	 
	  	 	 
	  	 	 if(subDepartmentIdEE=="0"){
	  	 		 addErrorClass($('#subDepartmentIdEE'),"Please select Office !!!");
	  	 		 e.preventDefault();
	  	 	 }
	     }
	
 	 
	});

$("#subDepartmentId").change(function(e){
		
		$("#subDepartmentIdSE").val("0");	
		$("#subDepartmentIdEE").val("0");	
		removeErrorClass($('#subDepartmentIdSE'));
 	 	removeErrorClass($('#subDepartmentIdEE'));
	});
	
	$("#subDepartmentIdSE").change(function(e){
		
		$("#subDepartmentId").val("0");	
		$("#subDepartmentIdEE").val("0");	
		removeErrorClass($('#subDepartmentId'));
 	 	removeErrorClass($('#subDepartmentIdEE'));
		
	});
	
	$("#subDepartmentIdEE").change(function(e){
		
		$("#subDepartmentIdSE").val("0");	
		$("#subDepartmentId").val("0");	
		removeErrorClass($('#subDepartmentIdSE'));
 	 	removeErrorClass($('#subDepartmentId'));
		
	});