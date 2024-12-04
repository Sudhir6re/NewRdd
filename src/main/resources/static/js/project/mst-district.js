$("#district" ).keypress(function(e) {
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
$("#districtCode1" ).keypress(function(e) {
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
							  console.log(data);
							},
							success : function(data) {
								 console.log(data);
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


$("form[name='mstDistrict']").validate({
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
    	districtNameEn: "required",
    	districtNameMr: "required",
  
    },
    // Specify validation error messages
    messages: {
    	countryCode: "Please Select Country",
    	stateCode: "Please Select State",
    	districtNameEn: "Please enter District Name in English",
    	districtNameMr: "Please enter District Name in Marathi",
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

function validateDistrictName1() {
	// alert('inside validateUIDUniqe');
	var districtname = document.getElementById("districtNameEn").value;
	
	
	
		if (districtname != '') {
			$
					.ajax({
						type : "GET",
						url : "validateDistrictName/"+districtname,
						async : false,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#districtNameEn').val(districtname);
								status = true;
							} else if (checkFlag > 0) {

								swal(districtname + ' Already Present in the system, Please enter the Different name !!!');

								document.getElementById("districtNameEn").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}



