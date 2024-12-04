/*$("#btnDelete").click(function(){
	$.ajax({
	      type: "POST",
	      url: "../pensionClk/deleteRejectedCaseFormEntry/"+pensEmployeeId,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
	    	  console.log("response from server"+data);
	    	  swal(
						"Case Form has been deleted successfully",
						{
							icon : "success",
						});
	     }
	 });
});*/

function getPensEmployeeId(pensEmployeeId){
	$("#pensEmployeeId").val(pensEmployeeId);
	pensEmployeeId=pensEmployeeId;
}
	/*$('body').on('change','.generatedChkId',function(){
		if (this.checked) {
			var id = $(this).val();

			$('#radioval').val(id);
			$('#radioid').val(id);

			 console.log($(this).attr("data"));
             var status=$(this).attr("data");
		}
}*/


/*$("#btnDelete").click(function(event){
	var pensEmployeeId=$('#radioval').val();
	if(pensEmployeeId==''){
		swal("Please Select Checkbox");
	}else{
		window.location.href="../pensionClk/deleteRejectedCaseFormEntry/"+pensEmployeeId;
	}
});*/


function getChkValue(currentChk){
    pensEmployeeId=currentChk.value;
    
$("#pensEmployeeId").val(pensEmployeeId);

var makeBtnDisabled=false;


	// swal("First Commutaion Bill Already Generated !!!");
}
   
/*jQuery(document)
.ready(
		function($) {

$('body').on('change','.generatedChkId',function(){
	if (this.checked) {
		var id = $(this).val();

		$('#radioval').val(id);
		$('#radioid').val(id);

		 console.log($(this).attr("data"));
	}
	});
		});*/

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

