jQuery(document)
		.ready(
				function($) {
				 $('#tblShowPayBill').hide();
				 $('#btnViewDetails').attr("disabled", true);
                 $('#btnAbstractReport').attr("disabled", true); 
                 $('#btnForwardToBeams').attr("disabled", true);  
                 $('#ApproveBill').attr("disabled", true);  
});




$('body').on('click','.gpfdetailsId',function(){
	  // alert("hello");
	   var gpfdetailsId=$(this).text();
	
		$("#loaderMainNew").show();
		 if (gpfdetailsId != '') {
			 $.ajax({
				 type : "GET",
				 url : "../master/gpfdetailsIdReport/"+gpfdetailsId,
					 async : true,
					 error : function(data) {
						 console.log(data);
					 },
					 success : function(data) {
							$("#loaderMainNew").hide();
						 console.log(data);
						 var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
						 var win = window.open("","",urlstyle);
				            win.document.write("");
				            win.document.write(data);
				            win.focus();
					}
			 });
		 }
});






