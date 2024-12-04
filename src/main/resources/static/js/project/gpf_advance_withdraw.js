jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
	/*	if(varMessage=="SUCCESS")
			{*/
			swal(varMessage);
	      /*  }
	else
		{
		swal("Something Went Wrong");
		}*/
	}
});


$("#sevaarthId").blur(function(){
	setTimeout(function () {
	$("#searchDiv").hide();
}, 200);
});



$("#searchDiv").hide();
$("#sevaarthId").keyup(function(){
	var sevaarthId = $("#sevaarthId").val();
	if (sevaarthId.length == 0) {
		document.getElementById("searchDiv").innerHTML = "";
		document.getElementById("searchDiv").style.border = "0px";
		return;
	}
	if (sevaarthId != '' && sevaarthId.length != 0) {
		$("#loaderMainNew").hide();
		$
				.ajax({
					type : "POST",
					url : "../master/getEmpDtlsBySevaarthId/"
							+ sevaarthId,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						document
								.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {
							//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							//$("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv")
									.append(
											"<p class='empdata' empid='"+data[i].employeeid+"' empname='"+data[i].employeeName+"' empsevaathid='"+data[i].sevaarthId+"'   empdesgn='"+data[i].designName+"'>"
													+ data[i].employeeName
													+ "-"
													+ data[i].sevaarthId
													+ "</p>");
							
							
							$("#sevaarthIdCopy").val(data[i].sevaarthId);
							
							$("#searchDiv")
									.css(
											"border:1px solid #A5ACB2;");
							
							$("#searchDiv").show();
						}
						
						if(data.length==0){
							swal("No Employee Found");
						}
					}
				});
	}
	
	
});


$('body').on('click', '.empdata', function() {
	$("#sevaarthId").val($(this).attr("empname"));
	var sevaarthId=$(this).attr("empsevaathid");  
	$("#sevaarthIdCopy").val(sevaarthId);
	

		$("#advance").attr("href", "../master/GPFAdvance/"+sevaarthId);
		$("#withdraw").attr("href", "../master/GPFWithdrawal/"+sevaarthId);
		$("#final").attr("href", "../master/GPFFinalWithdrawl/"+sevaarthId);
	
	document.getElementById("searchDiv").innerHTML = "";
	$("#searchDiv").hide();
});


$('body').on('click', '.alreadyApplied', function(event) {
	var typeOfAdvance=$(this).attr("data");
	var sevaarthId=$("#sevaarthIdCopy").val();  
	var isInProcess=checkAppIsInProcess(typeOfAdvance,sevaarthId);
	
	
	if(isInProcess==1){
		swal("Already Request Is In Processing...");
		if(Number(typeOfAdvance)==1){
			$("#advance").attr("href", "#");
		}else if(Number(typeOfAdvance)==2){
			$("#withdraw").attr("href", "#");
		}else if(Number(typeOfAdvance)==3){
			$("#final").attr("href", "#");
		}
		$(this).unbind('click', true);
	}else{
		if(Number(typeOfAdvance)==1){
			var isInProcess1=checkLoanEmiPending(typeOfAdvance,sevaarthId);
			if(isInProcess1==1){
				var isInProce=checkLoanEmiPendingorNot(sevaarthId);
				if(isInProce==0){
					
					$("#advance").attr("href", "#");
					swal("Advance Emi Is Not Yet Yet Paid !!!!");
				}else{
					$(this).unbind('click', false);
				}
				
			}
		}else{
			$(this).unbind('click', false);
		}
	}
});



//is advance already applied
function checkAppIsInProcess(typeOfAdvance,sevaarthId){
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/checkAppIsInProcess/"+typeOfAdvance+"/"+sevaarthId,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
	    	  console.log(data);
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



//is advance already applied
function checkLoanEmiPending(typeOfAdvance,sevaarthId){
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/checkLoanEmiPending/"+typeOfAdvance+"/"+sevaarthId,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
	    	  console.log(data);
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

//is advance already applied
function checkLoanEmiPendingorNot(sevaarthId){
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/checkLoanEmiPendingorNot/"+sevaarthId,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
	    	  console.log(data);
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






