var context = "";
$(document).ready(function() {
	context = $("#appRootPath").val();
	if ($('#department_id').length) {
		$('#department_id').select2();
	}

	var varMessage = $("#message").val();

	if (varMessage != undefined) {
		swal('' + varFinal, {
			icon : "success",
		});
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