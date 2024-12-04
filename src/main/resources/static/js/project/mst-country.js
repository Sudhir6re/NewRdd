jQuery(document).ready(function($) {
	$('#tblConsolidatePayBill').hide();
});




	  
$(".alphacharspace").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');
	$(this).val(replacestr);
	});
	  /*$('#countryCode').bind('keyup paste', function(){
	        this.value = this.value.replace(/[^0-9]/g, '');
	  });*/
$("#countryCode" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});
	  
	

var countryNameError;
var countryCodeError;
var warningError;

if($('#language').val()=="en"){
	countryNameError="Please enter country name !!!";
	countryNameMr="Please enter country Name in marathi !!!";
	warning="warning !";
}else{
	countryNameError="कृपया देशाचे नाव प्रविष्ट करा !!!";
	countryNameMr="कृपया देशाचे नाव मराठी द्या  !!!";
	warning="चेतावणी !";
}
$('#btnSave').click(function(e) {
	 var countryNameEn=$('#countryNameEn').val();
	 var countryNameMr=$('#countryNameMr').val();
	 var countryCode=$('#countryCode').val();
	 
	 if (countryNameEn == "" || countryNameEn == "0") {
			e.preventDefault();
			swal("Please enter country Name");
		}
		else if (countryNameMr == "" || countryNameMr == "0") {
			e.preventDefault();
			swal("Please enter country Name in marathi");
		} 
		 else{
			 $(".LockOn").css("display"," block");
			
			 swal("Record Save successfully.");
		    // $("#mstCountryForm").submit();
		 }
	 e.preventDefault();

		});



$('#btnCancel').click(
	    function(){
	   	 var countryNameEn=$('#countryNameEn').val('');
	   	 var countryNameMr=$('#countryNameMr').val('');
	   	
	    });

function validateCountryName() {
	// alert('inside validateUIDUniqe');
	var countryname = document.getElementById("countryNameEn").value;
	
	
	
		if (countryname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateCountryName/"+countryname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#countryNameEn').val(countryname);
								status = true;
							} else if (checkFlag > 0) {

								swal(deptname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("countryNameEn").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

