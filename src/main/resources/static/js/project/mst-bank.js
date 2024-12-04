$(document).ready(function(){
	
	 $("input[type=text]").keyup(function () {  
         $(this).val($(this).val().toUpperCase());  
     });   
	 
//for fetching tauka associates with state
$("#bankId").change(function() {
	  var bankId = $("#bankId").val();
//alert("bank  is "+bankId);
    	 if (bankId != '') 
    	 {
    		 $.ajax({
			      type: "GET",
			      url: "../mstBank/"+bankId,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	 //console.log(data);
			      },
			      success: function(data){
			    	 //console.log(data);
			    	  //alert(data);
			    	 var len=data.length;
			    	  if(len!=0)
			    		  {
			    		console.log(data);
			    		 $('#branches').empty();
			    		$('#branches').append("<option value='0'>Please Select</option>");
				    	 var temp = data;
				   		  $.each( temp, function( index, value ){
					    		console.log( value[3] ); 
					    		 $('#branches').append("<option data-id="+value[6]+" value="+value[0]+">" + value[3] + "</option>");
				    		});
			    		  }
			    	  else
			    		  {
			    		 $('#branches').empty();
			    		 $('#branches').append("<option value='0'>Please Select</option>");
			    		//  swal("Record not found !!!");
			    		  }
			    	}
			 });	
    	 }
    		
});



$("#branches").change(function(){
	    var ifsc=$('option:selected', this).attr('data-id');
	    $('#ifscCode').val(ifsc);
		 });



$(".delete").click(function(e) {
	 var bankId=$('option:selected', this).attr('data');
	 swal({
		  title: "Are you sure?",
		  text: "to delete this Bank !!!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  ConfirmDeleteRecord(bankId,e)
		  } else {
		    swal("Bank is safe !!!");
		  }
		});
});
function ConfirmDeleteRecord(bankId,e) {
	$.ajax({
	      type: "GET",
	      url: "../master/deleteBank/"+bankId,
	      async: true,
	      success: function(data){
	    	  swal("Bank Deleted successfully !", {
	    	      icon: "success",
	    	  });
	    	  setTimeout(function() {
				    location.reload(true);
				}, 3000);
	      }
	 });
}
});



$('#btnCancel').click(
	    function(){
	   	 var bankName1=$('#bankName1').val('');
	   	 var bankShortName=$('#bankShortName').val('');
	   	
	    });

var warningError;
if($('#language').val()=="en"){
	banknameerror="Please enter Bnak Name !!!";
	bankshortnameerror="Please enter bank short name !!!";
	warning="warning !";
}else{
	banknameerror="कृपया बँकेचे नाव प्रविष्ट करा  !!!";
	bankshortnameerror="कृपया बँकेचे  लहान नाव प्रविष्ट करा !!!";
	warning="चेतावणी !";
}

$("form[name='mstBank']").validate({
    // Specify validation rules
    rules: {
    	bankName: "required",
    	bankShortName: "required",
  
    },
    // Specify validation error messages
    messages: {
    	bankName: "Please Enter Bank Name",
    	bankShortName: "Please Enter Bank Short Name",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });



function validateBankName1() {
	// alert('inside validateUIDUniqe');
	var bankname = document.getElementById("bankName1").value;
		if (bankname != '') {
			$.ajax({
						type : "GET",
						url : "validateBankName/"+bankname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#bankName1').val(bankname);
								status = true;
							} else if (checkFlag > 0) {

								swal(bankname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("bankName1").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}


