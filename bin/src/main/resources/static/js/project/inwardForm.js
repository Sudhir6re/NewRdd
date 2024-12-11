jQuery(document).ready(function() {
	$("#searchDiv").hide();
	
	
	
	if($("#message").val()!=''){
		swal($("#message").val());
	}
	
	
	
	$('input[type=radio][name=haveSevaarthId]').change(function() {
		
	var ischecked=$('input[type=radio][name=haveSevaarthId]:checked').val();	
		
	    if (ischecked == 'Yes') {
	    //	$("#sevaarthIdBlock").show();
	    	$("#txtSevaarthId").attr("readonly", false); 
	    }
	    else if (ischecked == 'No') {
	    	//$("#sevaarthIdBlock").hide();
	   	 $("#procceed").attr("disabled", false); 
	 	$("#txtSevaarthId").attr("readonly", true); 
	    }
	});
	
	
	$('body').on('click', '.empdata', function() {
		 $("#procceed").attr("disabled", false); 
		var sevaarthId=$(this).attr("empsevaathid");  
		$("#txtSevaarthId").val(sevaarthId);
		var txtEmployeeName=$(this).attr("empname");  
		$("#txtEmployeeName").val(txtEmployeeName);
		document.getElementById("searchDiv").innerHTML = "";
		$("#searchDiv").hide();
		getPensSevaarthIdAlreadyExist(sevaarthId);
	});

	var empFound=0;
	
	$("#txtSevaarthId").blur(function(){
		
	/*	if(empFound==0){
			$("#txtSevaarthId").val("");
			swal("Please enter valid sevaarth id");
		}*/
		
		
		
		setTimeout(function () {
		$("#searchDiv").hide();
	}, 200);
		 
	});


	$(".getSevaarthId").click(function(){  $("#txtSevaarthId").val($(this).text());});
	
	
	
	
	$("#txtSevaarthId").keyup(function(){
		 var txtSevaarthId=$("#txtSevaarthId").val();
		 
		 var classOfPension=$("#cmbClassOfPnsn").val();
			 
		if(txtSevaarthId!='' && classOfPension!="0"){
			 // $("#loaderMainNew").show();
				$.ajax({
					type : "GET",
					url : "../pension/findEmployeeBySevaarthId/"
							+ txtSevaarthId+"/"+classOfPension,
						
					async : true,
					contentType : 'application/json',
					error : function(data) {
						 console.log(data);
					// alert("error");
						 $("#loaderMainNew").hide();
					},
					success : function(data) {
						 console.log(data);
						 $("#loaderMainNew").hide();
						var len = data.length;
					
						empFound=data.length;
						
						console.log(data);
							$("#loaderMainNew").hide();
							document
									.getElementById("searchDiv").innerHTML = "";
							for (var i = 0; i < data.length; i++) {
								
								$("#searchDiv").show();
								$("#searchDiv")
										.append(
												"<p class='empdata' empid='"+data[i].sevaarthId+"' empname='"+data[i].txtEmployeeName+"' empsevaathid='"+data[i].sevaarthId+"'   empdesgn='"+data[i].txtEmployeeName+"'>"
														+ data[i].txtEmployeeName
														+ "-"
														+ data[i].sevaarthId
														+ "</p>");
								
							//	$("#sevaarthIdCopy").val(data[i].sevaarthId);
								
								$("#searchDiv")
										.css(
												"border:1px solid #A5ACB2;");
							}
							
							if(data.length==0){
								$("#txtSevaarthId").val("");
								swal("Please enter valid sevaarth id");
							}
						 //end succcess
					}
				});
		}else if(classOfPension=="0"){
			 swal("Please select Class of pension !!!", {
	    	      icon: "warning",
	    	  });
		}
	  });
	
});


function getPensSevaarthIdAlreadyExist(sevaarthId){
	$.ajax({
		type : "GET",
		url : "../pension/getPensSevaarthIdAlreadyExist/"
				+ sevaarthId,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			 console.log(data);
		},
		success : function(data) {
			 console.log(data);
			var len = data;
			if (len != 0) { 
		    	  swal("SevaarthId Already Present In System!", {
		    	      icon: "warning",
		    	  });
		    	  $("#txtSevaarthId").val("");
		    	  $("#procceed").attr("disabled", true); 
		    	/*  setTimeout(function() {
					    location.reload(true);
					}, 3000);*/
		   }
		}
	});
}

$("#procceed").click(function(e){
	
	
	var ischecked=$('input[type=radio][name=haveSevaarthId]:checked').val();	
		
	if($("#txtSevaarthId").val()=='' && ischecked=="Yes"){
		 swal("Please enter SevaarthId id!", {
   	      icon: "warning",
   	   
   	  });
		 $("#procceed").attr("disabled", true); 
		 e.preventDefault();
	}else if($("#cmbClassOfPnsn").val()=='0'){
		 swal("Please select pension type!", {
	   	      icon: "warning",
	   	  });
		   e.preventDefault();
	}
	else{
		if($("#txtSevaarthId").val()!='' && $('input[type=radio][name=haveSevaarthId]').val()=="Yes"){
		getPensSevaarthIdAlreadyExist($("#txtSevaarthId").val());
		}
		 $("#procceed").attr("disabled", false); 
	}
});
