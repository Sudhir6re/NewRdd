$(document).ready(function(){
	
     var context=$("#context").val();
	//alert(context);
	if($("#message").val()!='') {
		swal("Data Saved Successfully !!!");
	}
});

$(document).ready(function() {
	$(".penupHide").hide();
	$("#ppoip").click(function() {
		$(".penupHide").show();
	});

});


function validateppo(e) {
	// alert('inside validateUIDUniqe');
	var ppo = document.getElementById("ppo").value;
	//var employeeId = document.getElementById("employeeId").value;
	/*if (ppo == "" || ppo == null) {
		employeeId='0';
	}*/
	
		if (ppo != '') {
			$
					.ajax({
						type : "GET",
						url : "../pension/validateppo/" + ppo,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 1) {
								$('#ppo').val(ppo);
								//swal('flag 0 nashil');
								status = true;
							} else if (checkFlag == 0) {

								swal('Entered PPO number: '
										+ ppo
										+ ' does not exist. Please enter correct PPO number.');

								document.getElementById("ppo").value = "";
								status = false;
							}
							e.preventDefault();
							return status;
						}
					});
		}
	}



$("#btnSave").click(function(e){
	
 	 var revisedbasicpension = $('#rebasepension').val();
 	 var revisedpensiondate = $('#revisedwithEffDateBasicPen').val(); 
 	
 	 
 	removeErrorClass($('#rebasepension'));
 	removeErrorClass($('#revisedwithEffDateBasicPen'));
 	 //alert("month and year  ="+month+''+year);
 	 
 	 if(parseFloat(revisedbasicpension)<=0){
 		addErrorClass($('#rebasepension'),"Please enter revised pension !!!");
 		e.preventDefault();
 	 }
 	 
 	 
 	 if(revisedpensiondate==''){
 		addErrorClass($('#revisedwithEffDateBasicPen'),"Please enter Effective Date !!!");
 		e.preventDefault();
 	 }
 	 
 	 
 	 
 	 if(billGroup=="0"){
 		addErrorClass($('#billGroup'),"Please select Bill Group  !!!");
 		e.preventDefault();
 	 }
 	 
});