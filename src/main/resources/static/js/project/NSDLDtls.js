/*var errorSeen = false;

function addErrorClass(element,msg){
	  var elementId=$(element).attr('id');
  var errorMessageVisible = $("#"+elementId+"-error").is(":visible");
  if (errorMessageVisible === false) {
	  element.after("<br><label id='"+elementId+"-error'  class='error' >"+msg+".</label>");
	  element.css("border-color", "red");
      console.log("can't find error");
    }
}

function removeErrorClass(element){
	var elementId=$(element).attr('id');
	 element.css("border-color", "");
	     var errorMessageVisible =  $("#"+elementId+"-error").is(":visible");
	     if (errorMessageVisible){
	        $("#"+elementId+"-error").remove();
	        console.log("can't find error");
	     }
}
*/

$("#Search").click(function(e){
    	
   	 	 var month = $('#monthId').val();
   	 	 var year = $('#yearId').val(); 
   	 	// alert("sevaarthId ="+sevaarthId);
   	 	 
   	 	 
   	 	 
   		removeErrorClass($('#monthId'));
  	 	removeErrorClass($('#yearId'));
  	 	 //alert("month and year  ="+month+''+year);
  	 	 
  	 	 if(month=="0"){
  	 		addErrorClass($('#monthId'),"Please select Month !!!");
  	 	 }
  	 	 
  	 	 
  	 	 if(year=="0"){
  	 		addErrorClass($('#yearId'),"Please select Year !!!");
  	 	 }
  	 	 
  	 	 
  	 	 
  	 	 if(cmbTr=="0"){
  	 		addErrorClass($('#cmbTr'),"Please select Treasury  !!!");
  	 	 }
  	 	 
   	 	 
   	 	 
		
		if(month!=""&&year!=""){
			
			$("#loaderMainNew").show();
    		 $.ajax({
			      type: "GET",
			      url: "../master/getNSDLEmpDtls/"+month+"/"+year,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    		$("#loaderMainNew").hide();
			    	  //alert(data);
			      },
			      success: function(data){
						//alert("inside success");
			    		$("#loaderMainNew").hide();
						$('#tblDataTable').show();
						$('#tblDataTable_wrapper').show();
						$('#tblDataTable tbody').html('');
						if (parseInt(data.length) > 0) {
							console.log("code updated");
							//alert(data.length);
							console.log(data);
					 $("#tblDataTable").dataTable().fnClearTable();
							var chk, fileID,empAmt, emprAmt, transcId, fileStatus;
							$
									.each(
											data,
											function(i, result) {
												var filename=result[0];
												////select file_name,bh_emp_amount,bh_emplr_amount,transaction_id,file_status from nsdl_bh
												
												if(result[3]!=null ){
													chk="<input type='radio' name='chk' disabled data-bhId='"+result[5]+"'  data-nsdlStatusCode='"+result[6]+"'    class='chk' data-pid='"
													+ result[0]
													+ "'value='"
													+ result[0]
													+ "'>";
													
													fileID="<a  target='_blank' href='../master/downloadFileChallan/"+result[3]+"/"+result[0]+"' >"+filename+"</a>";
													
												}else{
													chk="<input type='radio' name='chk'  data-bhId='"+result[5]+"' data-nsdlStatusCode='"+result[6]+"'   class='chk' data-pid='"
													+ result[0]
													+ "'value='"
													+ result[0]
													+ "'>";
													
													fileID="<p data="+result[0]+" class='filename'>"+filename+"</p>";
												}
												
												
												
												
												
												
												
												empAmt = result[1];
												emprAmt = result[2];
												transcId = result[3];
												fileStatus=result[4];
                                            //  console.log(status);
                                              
												$(
														"#tblDataTable")
														.dataTable()
														.fnAddData(
																[
																	chk,
																	fileID,
																		empAmt,
																		emprAmt,
																		transcId,
																		fileStatus,
																		]);
											});
						}
						else{
							swal("No Records Found");
						}
					}
			 });	
		}
		/*else{
			swal("Paybill is in process !!!");
			$("#btnSave").prop("disabled",true);
			 e.preventDefault();
		}*/
    	  });

/*function showreport(billNumber) {*/
	function showreport(filename) {
		//alert("filename--"+filename);
		 var test=window.location.href;
		 var pathArray = test.split("/master");

		console.log(pathArray[0]);
		
		var path=pathArray[0]+"/master/NSDLEmpWiseReport/"+filename;
		console.log(path);

		window.location.href=pathArray[0]+"/master/NSDLEmpWiseReport/"+filename;


}
	
	
$(document).on('click', '.filename', function(e){ 
	var filename=$(this).attr("data");
	showreport(filename);
//	alert(filename);
});	


	
	
	$("#btnSearch").click(function(e){
    	
  	 	 var month = $('#monthId').val();
  	 	 var year = $('#yearId').val(); 
  	 	 var cmbTr = $('#cmbTr').val(); 
  	 	 
  	 	removeErrorClass($('#monthId'));
  	 	removeErrorClass($('#yearId'));
  	 	 //alert("month and year  ="+month+''+year);
  	 	 
  	 	 if(month=="0"){
  	 		addErrorClass($('#monthId'),"Please select Month !!!");
  	 	 }
  	 	 
  	 	 
  	 	 if(year=="0"){
  	 		addErrorClass($('#yearId'),"Please select Year !!!");
  	 	 }
  	 	 
  	 	 
  	 	 
  	 	 if(cmbTr=="0"){
  	 		addErrorClass($('#cmbTr'),"Please select Treasury  !!!");
  	 	 }
  	 	
  	 	
  	 	
  	 	 if(month!="0"&&year!="0" && cmbTr!="0"){
  	 		$("#loaderMainNew").show();
   		 $.ajax({
			      type: "GET",
			      url: "../master/getNSDLEmpDtlsForGenerate/"+month+"/"+year,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    	  //alert(data);
			       	$("#loaderMainNew").hide();
			      },
			      success: function(data){
						//alert("inside success");
			       	$("#loaderMainNew").hide();
						$('#tblDataTable').show();
						$('#tblDataTable_wrapper').show();
						$('#tblDataTable tbody').html('');
						if (parseInt(data.length) > 0) {
							console.log("code updated");
							//alert(data.length);
							console.log(data);
					 $("#tblDataTable").dataTable().fnClearTable();
							var srNo, ddoCode,grossAmt, netAmt, empAmt,ddoRegNo, emprAmt,totalAmt;
							$
									.each(
											data,
											function(i, result) {
												var total= Number (result[2])+ Number (result[3]);
												////select file_name,bh_emp_amount,bh_emplr_amount,transaction_id,file_status from nsdl_bh
												srNo=" <input type='radio' name='selchk' class='selchk' />";
												//fileID = "<a  onclick='showreport("+result[0]+");' class='fileID'>"+result[0]+"</a>"; //result[0],
												ddoCode="<a data="+result[5]+" href='../master/empContriDtls/"+result[5]+"/"+month+"/"+year+"' class='fileID'>"+result[5]+"</a>";
												grossAmt = result[0];
												netAmt = result[1];
												empAmt = result[2];
												emprAmt=result[3];
												ddoRegNo=result[4];
												totalAmt=total;
                                           //  console.log(status);
                                             
												$(
														"#tblDataTable")
														.dataTable()
														.fnAddData(
																[
																	srNo,
																	ddoCode,
																	grossAmt,
																	netAmt,
																	empAmt,
																	emprAmt,
																	ddoRegNo,
																	totalAmt,
																		]);
											});
						}
						else{
							swal("No Records Found");
						}
					}
			 });	
		}
		/*else{
			swal("Paybill is in process !!!");
			$("#btnSave").prop("disabled",true);
			 e.preventDefault();
		}*/
  	 	 
  	 	/*$(".selchk").click(function() {
 	 		 $(".fileID").click();
 	 	 });*/
					 $(".selchk").click(function() {
						$(this).closest(".fileID").click();
					});
   	  });
	
	function showreportagainstDDO(ddoCode) {
		alert("ddoCode--"+ddoCode);
		 var test=window.location.href;
		 var pathArray = test.split("/master");

		console.log(pathArray[0]);
		
		var path=pathArray[0]+"/master/NSDLDDOWiseReport/"+ddoCode;
		console.log(path);

		window.location.href=pathArray[0]+"/master/NSDLDDOWiseReport/"+ddoCode;


}
	
	$(document).on('click', '.fileID', function(e){ 
		var ddoCode=$(this).attr("data");
		$("#fileId").val(ddoCode);
		//showreportagainstDDO(ddoCode);
//		alert(filename);
	});	
	
	$(document).on('click', '.chk', function(e){ 
	var filename=$(this).val();
	$("#fileId").val(filename);
	$("#bhId").val($(this).attr("data-bhid"));
	$("#nsdlStatusCode").val($(this).attr("data-nsdlStatusCode"));
	
      });	
	
	
	
	$(document).on('click', '#save', function(e){ 
		var month=$("#monthId").val();
		var year=$("#yearId").val();
		var treasuryId=$("#cmbTr").val();
		var fileId=$("#fileId").val();
		
		
		
		
		if(fileId!="" || fileId!=undefined){
			$("#loaderMainNew").show();
			  $.ajax({
			        type: 'GET',
			        url: "../master/viewAndSaveFile/"+month+"/"+year+"/"+treasuryId+"/"+fileId,
			        xhrFields: {
				           responseType: 'blob'
				       },
					async : true,
					contentType : 'application/json',
			        success: function (data) {
			        	$("#loaderMainNew").hide();
			        	
			        	if(data){
			        		swal("File Saved Successfully  !!!");
			        	}
			        	
			        	   var a = document.createElement('a');
				           var url = window.URL.createObjectURL(data);
				           a.href = url;
				           a.download = fileId+'.txt';
				           document.body.append(a);
				           a.click();
				           a.remove();
				           window.URL.revokeObjectURL(url);
			        },
			        error:function (xhr, ajaxOptions, thrownError) {
			            console.log("in error");
			         	$("#loaderMainNew").hide();
			        } 
			    });
		}else{
			swal("Please Select Atleast one batch file !!!");
		}
		
		
	});	
	
	
	$("#validate").click(function(event){
		if($("#bhId").val()=="" || $("#bhId").val()==undefined && $("#fileId").val()=="" || $("#fileId").val()==undefined ){
			swal("Please Select Atleast one batch file !!!");
			event.preventDefault();
		}		
	});
	
	$("#send").click(function(event){
		if($("#bhId").val()=="" || $("#bhId").val()==undefined && $("#fileId").val()=="" || $("#fileId").val()==undefined ){
			swal("Please Select Atleast one batch file !!!");
			event.preventDefault();
		}else{
			$("#contributionList").attr("action", "../master/sendContriFile");
		}		
	});
	
	
	$("#getContributionStatus").click(function(event){
		if($("#bhId").val()=="" || $("#bhId").val()==undefined && $("#fileId").val()=="" || $("#fileId").val()==undefined ){
			swal("Please Select Atleast one batch file !!!");
			event.preventDefault();
		}else{
			$("#contributionList").attr("action", "../master/getNpsFileContriTranId");
		}	
	});
	
	

	
	   $(document).ready(function(){
		    if($("#message").val()!="" && $("#message").val()!=undefined ){
		    	swal($("#message").val());
		    }
	   });	
	
		
	

	
	
