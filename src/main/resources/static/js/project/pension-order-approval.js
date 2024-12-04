/*function checktheEntryPensionOrderAppr(fromDate,toDate,ppoNo)
	{
		flag=0;
		 $.ajax({
		      type: "GET",
		      url: "../pension/checktheEntryPensionOrderAppr/"+fromDate+"/"+toDate +"/"+ppoNo,
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
	*/
	$("#GenerateReport").click(function(e){
		/* var ppoNo = $('#ppoNo').val();
 	 	 var fromDate = $('#fromDate').val();
 	 	 var toDate = $('#toDate').val(); 
 	 	*/
 	 	 
		var ppoNo = document.getElementById('ppoNo').value;
	 	 var fromDate = document.getElementById('fromDate').value;
	 	 var toDate = document.getElementById('toDate').value; 
	 	 
 	 	removeErrorClass($('#ppoNo'));
 	 	removeErrorClass($('#fromDate'));
 	 	removeErrorClass($('#toDate'));
 	 	
 	 	// alert("month and year  ="+month+''+year);
 	 	//alert("ppoNo"+ppoNo);
 	 	 
 	 	if(ppoNo == "")
 	 		{
 	 	 		addErrorClass($('#ppoNo'),"Please enter Ppo No !!!");
 	 	 		e.preventDefault();
 	 		}
 	 	else{
 	 		removeErrorClass($('#ppoNo'));
 	 	}
 	 	
 	 	 if(fromDate== ""){
 	 		addErrorClass($('#fromDate'),"Please select Date !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	 
 	 	 if(toDate== ""){
 	 		addErrorClass($('#toDate'),"Please select Date !!!");
 	 		e.preventDefault();
 	 	 }
 	 	 
 	 	/* if(fromDate!="" && toDate!="" && ppoNo != ""){
  	 		var isdatapresent = checktheEntryPensionOrderAppr(fromDate,toDate,ppoNo);
  	 		if(isdatapresent == 0)
  	 			{
  	 			swal("Record not found!");
  	 			e.preventDefault();
  	 			}
  	 	 }*/
	});
	
	
 	 	