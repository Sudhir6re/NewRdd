
function getPensEmployeeId(pensEmployeeId){
	$("#pensEmployeeId").val(pensEmployeeId);
	pensEmployeeId=pensEmployeeId;
}
	





function getChkValue(currentChk){
    pensEmployeeId=currentChk.value;
    
$("#pensEmployeeId").val(pensEmployeeId);

var makeBtnDisabled=false;

}
   

$('#btnDelete')
.click(
		function() {
			var pensEmployeeId = $('#pensEmployeeId').val();
			swal({
				  title: "Are you sure?",
				  text: "Delete Case Form !",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				}).then((willDelete) => {
    if (willDelete) {   
			// alert(paybillGenerationTrnId);
			if (pensEmployeeId != '') {
				$
						.ajax({
							type : "GET",
							url : "../pensionClk/deleteRejectedCaseFormEntry/"
									+ pensEmployeeId,
							async : true,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								console.log(data);
								// alert(data);

								if ($("#is_changed")
										.val() == 1) {

									swal(
											"Case Form has been deleted successfully",
											{
												icon : "success",
											});
									setTimeout(
											function() {
												location
														.reload(true);
											}, 2000);

								} else {
									swal(
											"Case Form has been deleted successfully",
											{
												icon : "success",
											});
									setTimeout(
											function() {
												location
														.reload(true);
											}, 2000);
								}

							}
						});
			}
    }
				})
		});

