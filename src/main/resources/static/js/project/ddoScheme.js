var context = "";
$(document).ready(function() {
	
	var varMessage = $("#message").val();
		if (varMessage != undefined && varMessage != "") {
			swal('scheme added to DDO successfully', {
				icon : "success",
			});
		}
	
	context = $("#appRootPath").val();
	if ($('#department_id').length) {
		$('#department_id').select2();
	}
});



  $("#txtSchemeCode").blur(function(){
	  var schemeCode=$("#txtSchemeCode").val();
	  $.ajax({
		  type : "GET",
		  url : "../ddo/displaySchemeNameForCode/"
		  + schemeCode,
		  async : true,
		  contentType : 'application/json',
		  error : function(data) {
			  console.log(data);
			  // alert("error");
		  },
		  success : function(data) {
				// console.log(data);
				// alert(data);
				var len = data.length;
				if (len != 0) {
					// console.log(data);
					$('#cmbSchemeName').empty();
					$('#cmbSchemeName')
							.append(
									"<option value='0'>Please Select</option>");
					var temp = data;
					$
							.each(
									temp,
									function(index,
											value) {
										console
												.log(value[1]);
										$(
												'#cmbSchemeName')
												.append(
														"<option value="
																+ value[0]
																+ ">"
																+ value[1]
																+ "</option>");
									});
				} else {
					$('#cmbSchemeName').empty();
					$('#cmbSchemeName')
							.append(
									"<option value='0'>Please Select</option>");
					swal("Record not found !!!");
				}
			}
	  });
  });
  
  
  
  

  $("#cmbSchemeName").blur(function(){
    var schemeCode=$("#txtSchemeCode").val();
    var ddoCode=$("#department_id").val();
    $.ajax({
  	  type : "GET",
  	  url : "../ddo/CheckSubSchemeExist/"
  	  + schemeCode+"/"+ddoCode,
  	  async : true,
  	  contentType : 'application/json',
	  beforeSend : function(){
	    $( "#loaderMainNew").show();
	  },
	  complete : function(data){
	    $( "#loaderMainNew").hide();
	  },
  	  error : function(data) {
  		  console.log(data);
		  $( "#loaderMainNew").hide();
  	  },
  	  success : function(data) {
  			console.log(data);
  			if (parseInt(data)>0) {
  				swal("The scheme has already been attached to DDO");
				$("#cmbSchemeName").val("");
			$('#department_id').val('0').trigger('change');
				$("#txtSchemeCode").val("");
  			} 
  		}
    });
  });
  
  
  

  
  
  
  
    $("form[name='frmScheme']").validate({
          rules: {
              instDdoCode: {
                  required: true,
                 min: 1
              },
              cmbSchemeName: {
                  required: true,
                 min: 1
              },
              txtSchemeCode: "required",
          },
          messages: {
              instDdoCode: {
                  required: "Please select DDO Institution Name",
                  min:"Please select DDO Institution Name"
              },
              cmbSchemeName: {
                  required: "Please enter Scheme Name",
                  min:"Please enter Scheme Name"
              },
              txtSchemeCode: "Please enter Scheme Code",
          },
          submitHandler: function(form) {
              form.submit();
              $("#loaderMainNew").show();
          }
      });

