$(document).ready(function(){
	
	 $("input[type=text]").keyup(function () {  
         $(this).val($(this).val().toUpperCase());  
     });   
	 //added for validation by komal
	 $(function() {
		 // Initialize form validation on the registration form.
		 // It has the name attribute "registration"
		 $("form[name='Religion']").validate({
		   // Specify validation rules
		   rules: {
		     // The key name on the left side is the name attribute
		     // of an input field. Validation rules are defined
		     // on the right side
		     religionNameEnglish: "required",
		     religionNameMarathi: "required",
		    
		   },
		   // Specify validation error messages
		   messages: {
			   religionNameEnglish: "Please enter religion name in English",
			   religionNameMarathi: "Please enter religion name in Marathi",
		     
		   },
		   // Make sure the form is submitted to the destination defined
		   // in the "action" attribute of the form when valid
		   submitHandler: function(form) {
		     form.submit();
		   }
		 });
		});
	 
//for fetching tauka associates with state
$("#bankId").change(function() {
	  var bankId = $("#bankId").val();
//alert("bank  is "+bankId);
    	 if (bankId != '') 
    	 {
    		 $.ajax({
			      type: "GET",
			      url: "../mstReligion/"+bankId,
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
function ConfirmDeleteRecord(religionId,e) {
	$.ajax({
	      type: "GET",
	      url: "../master/deleteReligion/"+religionId,
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
/*
$('#btnSave').click(function(e) {
	 var bankname=$('#bankName1').val();
	 var bankshortname=$('#bankShortName').val();
	
	 if(bankname==""){
		 Swal.fire({
				title: warning,
				text: banknameerror,
				icon: "warning",
				timer: 4000
			});
			
		 e.preventDefault();
	 }else if(bankshortname=="" ){
		 Swal.fire({
				title: warning,
				text: bankshortnameerror,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		 
	 }else {
			$(".LockOn").css("display"," block");
			Swal.fire("Record Save Successfully.");
			$("#mstDepartmentForm").submit();
		} 
	 
});*/

$('#btnSave1').click(function(e) {
	 var bankname=$('#bankName1').val();
	 var bankshortname=$('#bankShortName').val();
	
	 if(bankname==""){
		 Swal.fire({
				title: warning,
				text: banknameerror,
				icon: "warning",
				timer: 4000
			});
			
		 e.preventDefault();
	 }else if(bankshortname=="" ){
		 Swal.fire({
				title: warning,
				text: bankshortnameerror,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		 
	 }else {
			$(".LockOn").css("display"," block");
			Swal.fire("Record Save Successfully.");
			$("#mstDepartmentForm").submit();
		} 
	 
});

function validateRegligon() {
	// alert('inside validateUIDUniqe');
	var religion = document.getElementById("religionNameEnglish").value.trim();
	
	
	
	
		if (religion != '') {
			$
					.ajax({
						type : "GET",
						url : "validateRegligon/"+religion,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#religionNameEnglish').val(religion);
								status = true;
							} else if (checkFlag > 0) {

								swal(religion + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("religionNameEnglish").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}