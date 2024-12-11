$(document).ready(function() {

	$("#payCommissionCode").change(function(){
	  var payCommision =$("#payCommissionCode").val();

	  
	/// var context=$("#context").val(); 
	  
	 if (payCommision != '') {
			$
					.ajax({
						type : "GET",
						//url : "fetchbankbranch/" + bankid,
					    url: "../master/getDArates/"+payCommision,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							// console.log(data);
						},
						success : function(data) { 
							var len = data.length;
							if (len != 0) {
								// console.log(data);
						
								var i=1;
								var temp = data;
								$
										.each(
												temp,
												function(index,
														value) {
													console
															.log(value[0]);
													if(i==1)
													{
														$("#oldDa").append("<option value="+value[0]+">" + value[0] + "</option>");
														$("#oldDa").val(value[0]);
													}else{
														$("#newDa").append("<option   value="+value[0]+">" + value[0] + "</option>");
														$("#newDa").val(value[0]);	
													}
													i++;
												});
						}
						}
					});
		} else {
			$("#oldDa").empty();
			$("#newDa").empty();
			$("#oldDa").append(
							"<option value='0'>Please Select</option>");
			$("#newDa").append(
			"<option value='0'>Please Select</option>");
			swal("Record not found !!!");
		}
	});

});

jQuery(document).ready(function($) {
	//alert("qwe");
	var varMessage = $("#message").val();
	//alert("varMessage in if----"+varMessage);

	if (varMessage != "" && varMessage != undefined) {
		swal(varMessage);
	}
});