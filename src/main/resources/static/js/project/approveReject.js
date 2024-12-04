	var contextPath =
$(document).ready(function() {
	 contextPath = $("#appRootPath").val();
});

$('#btnApprove')
							.click(
									function() {
										var zpDdoCode = $('#zpDdoCode').val();
										var flag=1;
										 if (zpDdoCode != '') {
											 $( "#loaderMainNew").show();
											$
													.ajax({
														type : "GET",
														url : contextPath+"/ddo/updateApproveStatus/"
																+ zpDdoCode +"/"+flag,
														async : true,
														contentType : 'application/json',
														error : function(data) {
															console.log(data);
															$( "#loaderMainNew").hide();
														},
														success : function(data) {
															console.log(data);
															$( "#loaderMainNew").hide();
															// alert(data);
																swal(
																		"Approved Successfully",
																		{
																			icon : "success",
																		});
																
																
																window.location.href = contextPath+"/ddo/approveDdoOfficeDataList";
															

														}
													});
										}
									});
$('#btnReject')
.click(
		function() {
			var zpDdoCode = $('#zpDdoCode').val();
			var flag=2;
			if (zpDdoCode != '') {
				$( "#loaderMainNew").show();
				$
				.ajax({
					type : "GET",
					url : contextPath+"/ddo/updateApproveStatus/"
						+ zpDdoCode +"/"+flag,
						async : true,
						contentType : 'application/json',
						error : function(data) {
							$( "#loaderMainNew").hide();
							console.log(data);
						},
						success : function(data) {
							$( "#loaderMainNew").hide();
							console.log(data);
							// alert(data);
							swal(
									"Rejected Successfully",
									{
										icon : "success",
									});
							
							window.location.href = contextPath+"/ddo/approveDdoOfficeDataList";
						}
				});
			}
		});