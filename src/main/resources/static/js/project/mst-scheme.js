jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
	
		$(document).on('click','.delete', function(event){
	/*    alert("The paragraph was clicked.");*/
	    var schemeId = $(this).attr("id");
	    var isActive = $(this).attr("data");
	    console.log(schemeId + isActive);
	    ConfirmDeleteRecord(schemeId,isActive);
	  });
});


$("#schemeCode").keyup(function(){
	var schemeCode=$(this).val();
	var flag=isSchemeCodeAlreadyExists(schemeCode);
	if(flag==1){
		$("#btnSave").prop("disabled",true);
		swal("Scheme code already exists please enter different one !!!");
	}else{
		$("#btnSave").prop("disabled",false);
	}
	
	
});

function isSchemeCodeAlreadyExists(schemeCode)
{
	flag=0;
	 $.ajax({
	      type: "GET",
	      url: "../master/isSchemeCodeAlreadyExists/"+schemeCode,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
	    	  if(parseInt(data) >0 ) {
	    		  flag=1;
	    	  }
	    	  else
	    		  {
	    		 flag=0;
	    		  }
	    }
	 });
	 return flag;
}



	  
function ConfirmDeleteRecord(schemeId,isactive) {
	if(isactive==1){
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
					      url: "../master/deleteScheme/"+schemeId,
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
	} else if(isactive==0) {
		swal({
	    	  title: 'Not allowed !',
			  text: 'This record is already deleted',
			  icon: "warning",
	    });
	}
}

//alphanumeric
$(".alphanumeric").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^0-9a-zA-Z]+/g, '');
	$(this).val(replacestr);
	});

$(".alphacharspace").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');
	$(this).val(replacestr);
	});




$('#btnCancel').click(
	    function(){
	   	 var majorHead=$('#majorHead').val('');
	   	 var subMajorHead=$('#subMajorHead').val('');
	   	 var minorHead=$('#minorHead').val('');
	   	 var subHead=$('#subHead').val('');
	   	 var demandCode=$('#demandCode').val('');
	   	 var subMinorHead=$('#subMinorHead').val('');
	   	 var schemecode=$('#schemeCode').val('');
	   	 var schemeName=$('#schemeName').val('');
	   	 var schemeType=$('#schemeType').val('');
	   	 var finyerError=$('#finYear').val('');
	   	 
	   	 
	    });
	   
	    
	    var majorHeadError;
	    var subMajorHeadError;
	    var minorHeadError;
	    var subHeadError;
	    var demandCodeError;
	    var subMinorHeadError;
	    var schemeNameError;
	    var schemeTypeError;
	    var subMinorHeadError;

	    var warningError;
	    if($('#language').val()=="en"){
	    	majorHeadError="Please enter major head code !!!";
	    	subMajorHeadError="Please enter sub major head code!!!";
	    	minorHeadError="Please enter minor head code !!!";
	    	subHeadError="Please enter sub head !!!";
	    	
	    	demandCodeError="Please enter the demond code!!!";
	    	subMinorHeadError="Please enter sub minor head !!!";
	    	schemecodeError="Please selectr the scheme Code !!!";
	    	schemeNameError =" Please enter the schme name "
	    	schemeTypeError="Please enter the scheme type !!!";
	    	finyerError="Please select fin year !!!";
	    	warning="warning !";
	    }else{
	    	majorHeadError="कृपया प्रमुख हेड कोड दर्ज करें!!!";
	    	subMajorHeadError="कृपया उप प्रमुख शीर्ष कोड दर्ज करें!!!";
	    	minorHeadError="कृपया एक मामूली हेड कोड दर्ज करें !!!";
	    	subHeadError="कृपया उपशीर्षक दर्ज करें !!!";
	    	
	    	demandCodeError="कृपया मांग कोड दर्ज करें    !!!";
	    	subMinorHeadError="कृपया उप लघु शीर्ष दर्ज करें   !!!";
	    	schemecodeError="कृपया योजना का नाम चुनें   !!!";
	    	schemeNameError =" कृपया योजना का नाम दर्ज करें !!! ";
	    	schemeTypeError="कृपया योजना प्रकार दर्ज करें   !!!";
	    	finyearError="कृपया वित्तीय वर्ष चुनें !!!";
	    	warning="चेतावणी !";
	    }
	    
	    $("form[name='mstScheme']").validate({
	        // Specify validation rules
	        rules: {
	        	majorHead: "required",
	        	subMajorHead: "required",
	        	minorHead: "required",
	        	subHead: "required",
	        	subMinorHead: "required",
	        	demandCode: "required",
	        	schemeCode: "required",
	        	schemeName: "required",
	        	schemeType: "required",
	        	finYear:
	        		{
	        		required:true,
	        		min:1
	        		},
	      
	        },
	        // Specify validation error messages
	        messages: {
	        	majorHead: "Please Enter Major Head",
	        	subMajorHead: "Please Enter Sub Major Head",
	        	minorHead: "Please Enter Minor Head",
	        	subHead: "Please Enter Sub Head",
	        	subMinorHead: "Please Sub minor Head",
	        	demandCode: "Please Enter Demand Code",
	        	schemeCode: "Please Enter Scheme Code",
	        	schemeName: "Please Enter Scheme Name",
	        	schemeType: "Please Enter Scheme Type",
	        	finYear: "Please Select Financial Year",
	        },
	        // Make sure the form is submitted to the destination defined
	        // in the "action" attribute of the form when valid
	        submitHandler: function(form) {
	          form.submit();
	        }
	      });
	    
	  /*  $("#everything").validate({
	        rules: {
	            select_field:{
	                required: {
	                    depends: function(element){
	                        if('Please select' == $('#select_field').val()){
	                            //Set predefined value to blank.
	                            $('#select_field').val('');
	                        }
	                        return true;
	                    }
	                }
	            }
	        }
	    });*/