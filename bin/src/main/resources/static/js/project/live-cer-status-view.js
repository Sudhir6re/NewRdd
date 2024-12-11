

function checktheEntryForLiveCertificate(yearId) 
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../pension/checktheEntryForLiveCertificate/"+yearId,
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
	 var yearId = $('#yearId').val(); 
	 
	removeErrorClass($('#yearId'));
	 if(yearId==0){
		addErrorClass($('#yearId'),"Please select Year !!!");
		e.preventDefault();
	 }
	 
		 if(yearId!=0){
	 				var isdatapresent = checktheEntryForLiveCertificate(yearId);
	 	 	 		if(isdatapresent == 0)
	 	 	 			{
	 	 	 			swal.fire("Record not found!");
	 	 	 			e.preventDefault();
	 	 	 			}
	 				}
	 	 
	 
});