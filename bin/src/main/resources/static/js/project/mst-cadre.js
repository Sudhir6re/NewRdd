jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

function ConfirmDeleteRecord(cadreId,isActive) {
	if(isActive==1){
		swal({
			  title: "Are you sure?",
			  text: "Status of this record will be InActive !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			}).then((willDelete) => {
			    if (willDelete) {   
					$.ajax({
					      type: "GET",
					      url: "../master/deleteCadre/"+cadreId,
					      async: true,
					      success: function(data){
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
$(".supAnnAge" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});

$(".superAnnuationAge" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});



$('#btnCancel').click(
	    function(){
	   	 var cadreDescription=$('#cadreDescription').val('');
	   	 var cadreGroup=$('#cadreGroup').val('');
	   	 var superAnnuationAge=$('#superAnnuationAge').val('');
	   	
	    });

var cadreDescription;
var cadreGroup;
var	superAnnuationAg;
var	warning
if($('#language').val()=="en"){
	cadreDescriptionerror="Please enter Cadre Name !!!";
	cadreGrouperror="Please select the cadre Group  !!!";
	superAnnuationAgeerror="Please enter super annutation age !!!";

var	warning="warning !";
}else{
	cadreDescription="कृपया संवर्ग का नाम दर्ज करें!!!";
	cadreGroup="कृपया संवर्ग समूह का चयन करें !!!";
	superAnnuationAge="कृपया सुपर वार्षिकी आयु दर्ज करें!!!";
	warning="चेतावणी !";
}
$("form[name='mstCadre']").validate({
    // Specify validation rules
    rules: {
    	cadreDescription: "required",
    	superAnnuationAge: "required",
    	cadreGroup:
    		{
    		required:true,
    		min:1
    		},
  
    },
    // Specify validation error messages
    messages: {
    	cadreDescription: "Please Enter Cadre Description",
    	superAnnuationAge: "Please Enter Super Annuation Age",
    	cadreGroup: "Please Select Cadre Group",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });



