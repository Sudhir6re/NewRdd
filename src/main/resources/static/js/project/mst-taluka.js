$("#taluka" ).keypress(function(e) {
    var key = e.keyCode;
    if (key >= 48 && key <= 57 ) {
        e.preventDefault();
    }
});
$(".alphacharspace").keyup(function(e) {
	var str = $(this).val();
	var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');
	$(this).val(replacestr);
	});
	  
	  /*$('#countryCode').bind('keyup paste', function(){
	        this.value = this.value.replace(/[^0-9]/g, '');
	  });*/
$("#talukaCode" ).keypress(function(e) {
    var key = e.keyCode;
    if (event.keyCode < 48 || event.keyCode > 57 && event.keyCode > 36 || event.keyCode < 46) {
        event.preventDefault(); 
    }
});
$("#countryId")
.change(
		function(e) {
			
			var countryId= $("#countryId").val();
			//alert("countryId"+countryId);
			if (countryId != '') {
				//alert("Inside if countryId"+countryId);
				$
						.ajax({
							type : "GET",
							url : "../master/fetchStateByCountry/"
									+ countryId,
							async : true,
							contentType : 'application/json',
							 dataType: 'json',
							error : function(data) {
								// console.log(data);
							},
							success : function(data) {
								// console.log(data);
								// alert(data);
								var len = data.length;
								//alert("len"+len);
								if (len != 0) {
									 for(var i=0;i<data.length;i++)
										 {
										 $('#state').append("<option  value="+ data[i].stateCode+  ">"+ data[i].stateNameEn+"</option>");
										 }
									var temp = data;
								} else {
									$(
											'#state')
											.empty();
									$(
											'#state')
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}
				});



var context=$("#context").val();
$("#state").change(function() 
			{
				  var stateId = $("#state").val();
			    	 if (stateId != '') 
			    	 {
			    		 $.ajax({
						      type: "GET",
						      url: context+"/master/fetchDistricts/"+stateId,
						      async: true,
						      contentType:'application/json',
						      error: function(data){
						    	console.log(data);
						      },
						      success: function(data){
						    	 console.log(data);
						    	  //alert(data);
						    	 var len=data.length;
						    	  if(len!=0)
						    		  {
						    		console.log(data);
						    		 $('#district').empty();
						    		$('#district').append("<option value='0'>Please Select</option>");
							    	 var temp = data;
							   		  $.each( temp, function( index, value ){
								    		console.log( value[2] ); 
								    		 $('#district').append("<option value="+value[3]+">" + value[4] + "</option>");
							    		});
						    		  }
						    	  else
						    		  {
						    		 $('#district').empty();
						    		 $('#district').append("<option value='0'>Please Select</option>");
						    		  swal("Record not found !!!");
						    		  }
						    	}
						 });	
			    	 }
			    		
			});

function validateTalukaName1() {
	// alert('inside validateUIDUniqe');
	var talukaname = document.getElementById("talukaNameEn").value;
	var districtcode = document.getElementById("district").value;
	
	
	
		if (talukaname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateTalukaName/"+talukaname+"/"+districtcode,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#talukaNameEn').val(talukaname);
								status = true;
							} else if (checkFlag > 0) {

								swal(talukaname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("talukaNameEn").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}


$("form[name='mstTaluka']").validate({
    // Specify validation rules
    rules: {
    	countryCode:{
    		required: true,
    		min:1
    	},
    	stateCode:{
    		required: true,
    		min:1
    	},
    	districtCode:{
    		required: true,
    		min:1
    	},
    	talukaNameEn: "required",
    	talukaNameMr: "required",
  
    },
    // Specify validation error messages
    messages: {
    	countryCode: "Please Select Country",
    	stateCode: "Please Select State",
    	districtCode: "Please Select District",
    	talukaNameEn: "Please enter Taluka Name in English",
    	talukaNameMr: "Please enter Taluka Name in Marathi",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
