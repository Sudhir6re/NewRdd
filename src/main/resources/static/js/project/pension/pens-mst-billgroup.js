jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
	$(document).on('click','.delete', function(event){
	   /* alert("The paragraph was clicked.");*/
	    var schemeId = $(this).attr("id");
	    var isActive = $(this).attr("data");
	    console.log(schemeId + isActive);
	    ConfirmDeleteRecord(schemeId,isActive);
	  });
	
 	 var subBgOrNot=$('#txtsubBgOrNot').val();
 	 
 	 if(subBgOrNot!= null){
 	var arrayValues = subBgOrNot.split(',');
 	$.each(arrayValues, function(i, val){

 	   $("input[value='" + val + "']").prop('checked', true);

 	});
 	 }

 	 
	
});


function ConfirmDeleteRecord(pensBillgroupid,isActive) {
	if(isActive==1){
		swal({
			  title: "Are you sure?",
			  text: "Status of this record will be InActive !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			}).then((willDelete) => {
			    if (willDelete) {   
			    	  $("#loaderMainNew").show();
					$.ajax({
					      type: "GET",
					      url: "../pensionCashier/deletePensBillGroup/"+pensBillgroupid,
					      async: true,
					      error : function(data) {
							  $("#loaderMainNew").hide();
						  },
					      success: function(data){
					    	  $("#loaderMainNew").hide();
					    	  swal("Deleted successfully !", {
					    	      icon: "success",
					    	  });
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      }
					 });
			     }
		})
	} else if(isActive==0) {
		 swal({
	    	  title: 'Not allowed !',
	    	  text: 'This record is already deleted',
			  icon: "warning",
	    });
	}
}



$('#btnCacel').click(
	    function(){
	   	 var billGroupName=$('#billGroupName').val('');
	   	 var billDescription=$('#billDescription').val('');
	   	 var typeOfPost=$('#typeOfPost').val('');
	   	 var subBgOrNot=$('#subBgOrNot').val('');
	   	
	    });


var warningError;
if($('#language').val()=="en"){
	billGroupNameerr="Please Enter the Bill Name !!"
	billDescriptionerr="Please Enter Bill Discription !!";
	typeOfPosterr="Please Select the Post Type !!";
	subBgOrNoterr="Please Select Group Name  !!";
	warning="warning !";
}else{
	billGroupName="कृपया बिल नाव प्रविष्ट करा  !!";
	billDescription="कृपया बिल वर्णन प्रविष्ट करा!!";
	typeOfPost="कृपया पोस्ट प्रकार निवडा  !!";
	subBgOrNot="कृपया गटाचे नाव निवडा  !!";
	warning="चेतावणी !";
}

$("form[name='pensMstBillGroupEntity']").validate({
    // Specify validation rules
    rules: {
    	billGroupName:{
    		required:true,
    		},
    		billDescription:{
    			required:true,
    		//	min:1
    		},
    		subBgOrNot:{
    			required:function(){
    				if ($("input[name='subBgOrNot']:checked").length==0){
    				return true;
    				}else{
    					return false;
    				}
    			
    			},
    		},
    		
    		typeOfPost : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="typeOfPost"]:checked').val() == '' || $('input[name="typeOfPost"]:checked').val()==undefined);
				},
				//minlength : 1,
			},
  
    },
    // Specify validation error messages
    messages: {
    	billGroupName: "Please Enter Bill Group Name",
    	billDescription: "Please Enter Bill Discription",
    	subBgOrNot: "Please Select Group",
    	typeOfPost: "Please Select Post Type",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
      
      $("#loaderMainNew").show();
    }
  });

function validateBillGroupName() {
	// alert('inside validateUIDUniqe');
	var billgrp = document.getElementById("billGroupName").value;
	
		if (billgrp != '') {
			  $("#loaderMainNew").show();
			$
					.ajax({
						type : "GET",
						url : "validateBillGroupName/"+billgrp,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							  $("#loaderMainNew").hide();
						},
						success : function(data) {
							  $("#loaderMainNew").hide();
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#billGroupName').val(billgrp);
								status = true;
							} else if (checkFlag > 0) {

								swal(billgrp + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("billGroupName").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}





