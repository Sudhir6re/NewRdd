/*$("#btnSave").click(function(){
	if($("#isSaved").val()=='V') {
		console.log("YES");
	}
});*/

jQuery(document).ready(function($) {
	$('#adminOffice').change(function() {
		var adminOfficeId =$("#adminOffice").val();
		$.ajax({
			type: "GET",
			url: "/master/getDistrictOffice",
			contentType : "application/json",
			data : "adminOfficeId=" +adminOfficeId,
			dataType : 'json',
			async: false,
			/*headers: {
				"X-CSRF-Token": $("#hctoken").val() 
			},*/
			success: function (data){ 
				arr = data;
				console.log(data)
				if(parseInt(data.totalRows) > 0){
					$("#districtOffice").empty();
//					$("#districtOffice").append('<option value= "0">'+'--'+getPropNm('msg.thymeleaf.dropdown.pleaseselect').responseText +'--'+'</option>').prop('selected', 'selected');
					if($('#language').val() == "en") {
						$("#districtOffice").append($("<option></option>").val("0").html("Please Select"));
					} else {
						$("#districtOffice").append($("<option></option>").val("0").html("कृपया  निवडा"));
					}
					$.each(data.resultData, function (i, result) {
						$("#districtOffice").append( $("<option></option>").attr("value",result.districtOfficeId).text(result.districtOfficeName));
					});  
				}
			}
		});
	}); 
});


$(document).ready(function(){
    $("#department_id").change(function() 
 			{
 				  var departmentId = $("#department_id").val();
 				 // alert("DDO CODE is "+departmentId);
 			    	 if (department_id != '') 
 			    	 {
 			    		 $.ajax({
 						      type: "GET",
 						      url: "mstSubDept/"+departmentId,
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
						    		//  console.log(data);
						    		 $('#subdepartment_id').empty();
						    		$('#subdepartment_id').append("<option value='0'>Please Select</option>");
							    	 var temp = data;
							   		  $.each( temp, function( index, value ){
								    		console.log( value[2] ); 
								    		 $('#subdepartment_id').append("<option value="+value[0]+">" + value[2] + "</option>");
							    		});
						    		  }
						    	  else
						    		  {
						    		 $('#subdepartment_id').empty();
						    		 $('#subdepartment_id').append("<option value='0'>Please Select</option>");
						    		  swal("Record not found !!!");
						    		  }
 						    	}
 						 });	
 			    	 }
 			    		
 			});
 });












