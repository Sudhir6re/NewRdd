jQuery(document).ready(function($) {
	
	
	var contextPath = $("#appRootPath").val();
	$('.sed').attr("readonly", true); 
	
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
	
	
/*	$(".checkbox").click(function() {
		
		if($(this).prop("checked")){
			$(this).prop("checked", true);
		}
		else{
			$(this).prop("checked", false);
		}
		
		
	});*/
	$("#btnsubmit").click(function(event) {
		var flag=0;
		var i=0;
		
		if ($(".checkbox:checked").length==0) {
			event.preventDefault();
			swal("Please Select any Record");
		}
		else{
			$(".checkbox").each(function(){
				if(this.value=="true"){
					var SED=$("#sed"+i).val();
					if(SED==''){
						addErrorClass($("#sed"+i),"Please Enter Service End Date");
						flag=1;
					}
				}else{
					removeErrorClass($("#sed"+i));
				}
				i++;
			});
			
			
			if(flag==1)
				event.preventDefault();
			
		
				}
		});
	
	

	$("table").on("change", ".checkbox", function() {
		  var row = $(this).closest("tr");
		  var chk =row.find(".checkbox").prop("checked");
		 
		  
		  if(chk== true){
			row.find(".sed").prop("readonly",false);
		  }else{
			  row.find(".sed").prop("readonly",true);
		  }
		
		  
		});
	
	

	
	/*$("table").on("change", ".sed", function() {
		  var row = $(this).closest("tr");
		  var sed =row.find(".sed").val();
		  var cadreid = row.find(".cadreid").val();
		  var dob = row.find(".dob").val();
		  var chk =row.find(".checkbox").prop("checked");
		  var isValid=validateSED(sed,cadreid,dob)
		  if(isValid){
			  swal("Please select valid Service End Date");
			  row.find(".sed").val("");
		  }
		});
	*/

	$("table").on("blur", ".sed", function() {
		  var row = $(this).closest("tr");
		  var sed =row.find(".sed").val();
		  var cadreid = row.find(".cadreid").val();
		  var dob = row.find(".dob").val();
		  var chk =row.find(".checkbox").prop("checked");
		  var isValid=validateSED(sed,cadreid,dob);
		 /* if(isValid==true){
			  swal("Please select valid Service End Date");
			  row.find(".sed").val("");
		  }*/
		});
	function validateSED(sed,cadreid,dob){
	if (cadreid != '') {
		
		var flag=false;
		
		$
				.ajax({
					type : "GET",
					url : contextPath+"/ddoast/fetchcadregroupdtlsDate/" + cadreid + "/" + dob,
					async : true,
					contentType : 'application/json',
					error : function(data) {
						// console.log(data);
					},
					beforeSend : function(){
						$( "#loaderMainNew").show();
						},
					/*complete : function(data){
						$( "#loaderMainNew").hide();
						return flag;
					},	*/
					success : function(data) {
						 console.log(data);
						// alert(data);
						 $( "#loaderMainNew").hide();
						var len = data.length;
						if (len != 0) {
							var temp = data;
							var date=data[0].superAnnDate;
							if(new Date(sed).getTime()>new Date(date).getTime())
								{
								flag=true;
								  swal("Please select valid Service End Date");
								  row.find(".sed").val("");
								}
						} else {
							swal("Record not found !!!");
						}
					}
				});
		
		return flag;
	}
	}
	
});





/*$("form[name='updatePAN']").validate({
    // Specify validation rules
    rules: {
    	 panNo:{
		    	required: true,
		    	//digits: false,
		    	pattern: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,
		    	//regex: /^[+-]{1}[0-9]{1,3}\-[0-9]{10}$/,
		    	minlength: 10,
		    	maxlength: 10, 
		    },
  
    },
    // Specify validation error messages
    messages: {
    	 panNo:{
		    	required : "Please enter Pan No",
				minlength : "Pan No should be atleast 10 Digit long.",
				pattern : "Please Enter Valid Pan No ",
		    },
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
    	      form.submit();
    
    }
  });*/


