jQuery(document).ready(function($) {
	$('#tblChangeMgmt').hide();
});

$("#btnSearch").click(function(){
	
	//  var input =  $('#schemebillGroupId').val(); 
});

$(document).ready(function() {
	$("#btnSearch").click(function() {

		var monthName = $('#monthName').val();
		var yearName = $('#yearName').val();
		var ddoCode = $('#ddoCode').val();

		if (monthName == "0") {
			swal(" Please Select Month !!!");
		} else if (yearName == "0") {
			swal("Please Select Year !!!");
		} else if (ddoCode == "0") {
			swal(" Please Select DDO Code !!!");
		} else {

		}
	});
}); //ready Close