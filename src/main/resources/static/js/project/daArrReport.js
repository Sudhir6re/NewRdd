jQuery(document).ready(function($) {
	//alert("qwe");
	var varMessage = $("#message").val();
	//alert("varMessage in if----"+varMessage);

	if (varMessage != "" && varMessage != undefined) {
		swal(varMessage);
	}
});

function checkdaarrEntry(paycomm,monthName,yearName) ///CheckPaybill/{billNumber}/{monthName}/{yearName}
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/checkdaarrEntry/"+paycomm+"/"+monthName +"/"+yearName,
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
	
 	 var month = $('#payMonth').val();
 	 var year = $('#payYear').val(); 
 	 var paycomm = $('#payCommissionCode').val(); 
 	 
 	removeErrorClass($('#payMonth'));
 	removeErrorClass($('#payYear'));
 	removeErrorClass($('#payCommissionCode'));
 	 //alert("month and year  ="+month+''+year);
 	 
 	 if(month=="0"){
 		addErrorClass($('#payMonth'),"Please select Month !!!");
 		e.preventDefault();
 	 }
 	 
 	 
 	 if(year=="0"){
 		addErrorClass($('#payYear'),"Please select Year !!!");
 		e.preventDefault();
 	 }
 	 
 	 
 	 
 	 if(paycomm=="0"){
 		addErrorClass($('#payCommissionCode'),"Please select PayCommission  !!!");
 		e.preventDefault();
 	 }
 	 
 	 if(month!="0" && year!="0" && paycomm!="0"){
 				var isdatapresent = checkdaarrEntry(paycomm,month,year);
 	 	 		if(isdatapresent == 0)
 	 	 			{
 	 	 			swal.fire("Record not found!");
 	 	 			e.preventDefault();
 	 	 			}
 				}
 	 });