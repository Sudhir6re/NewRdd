/* Hide and show Contents on selection of year */
	$('#paybillYear').on('change', function() {
		 /* alert( this.value ); */
		  $(".monthhide").css("visibility","visible");
		});
	$('#paybillMonth').on('change', function() {
		 /* alert( this.value ); */
		  $(".billgphide").css("visibility","visible");
		});
	$('#schemeBillGroupId').on('change', function() {
		 /* alert( this.value ); */
		  $(".disableTillyear").css("visibility","visible");
		});
	
	
	/*$('body').on('click','.viewPaySlip',function(e){	
		e.preventDefault();
	
	var username=$("#ddoCode").val();
	var year=$("#paybillYear").val();
	var month=$("#paybillMonth").val();
	var schemeBillGroupId=$("#schemeBillGroupId").val();
	  if(year=="0"){
      	swal("Please select year");
      	
      }else if(month=="0"){
      	swal("Please select month");
      }
		if(year!="0" && month!="0"){
	
	var test=window.location.href;
	var pathArray = test.split("/master");
	var urlString=pathArray[0]+"/master/viewPaySlip/"+month +"/"+year+"/"+schemeBillGroupId;
	window.open(urlString, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=0,left=500,width=1200,height=1200");
//	window.location.href=urlString;
		}
	});
*/
	
	function checkIsPaybillInProcess(billNumber,monthName,yearName) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../employee/isPaybillGenerated/"+billNumber+"/"+monthName +"/"+yearName,
		      async: false,
		      dataType : 'json',
		    // contentType:'application/json',
		      error: function(data){
		    	  console.log(data);
		    alert(data);
		      },
		      success: function(data){
		    	  console.log("response from server"+data);
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
	
	$("#GenerateReport").click(function(e){
		
 	 	 var month = $('#paybillMonth').val();
 	 	 var year = $('#paybillYear').val(); 
 	 	 var billGroup = $('#schemeBillGroupId').val(); 
 	 	 
 	 	removeErrorClass($('#paybillMonth'));
 	 	removeErrorClass($('#paybillYear'));
 	 	removeErrorClass($('#schemeBillGroupId'));
 	 	 //alert("month and year  ="+month+''+year);
 	 	 
 	 	 if(month=="0"){
 	 		addErrorClass($('#paybillMonth'),"Please select Month !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(year=="0"){
 	 		addErrorClass($('#paybillYear'),"Please select Year !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 
 	 	 if(billGroup=="0"){
 	 		addErrorClass($('#schemeBillGroupId'),"Please select Bill Group  !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 if(month!="0" && year!="0" && billGroup!="0"){
 	 		 $("#loaderMainNew").show();
 	 		var ispaybillinProcess = checkIsPaybillInProcess(billGroup,month,year);
 	 		if(ispaybillinProcess == 0)
 	 			{
 	 			swal.fire("Paybill not generated!");
 	 			e.preventDefault();
 	 			}
 	 		
 	 	 }
 	 	 $("#loaderMainNew").hide();
	});
 	 	
