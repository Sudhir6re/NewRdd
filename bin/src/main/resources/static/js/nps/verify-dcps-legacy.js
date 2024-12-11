//$("#displayData").hide();
var contextPath = "";

jQuery(document).ready(function($) {
	var noDataFoundMsg = $("#noDataFoundMsg").val();
	if (noDataFoundMsg != "" && noDataFoundMsg != undefined) {
		swal('' + noDataFoundMsg, {
			icon : "warning",
		});
	}
	contextPath = $("#appRootPath").val();
	if(!contextPath){
		contextPath=window.origin;
	}
	
	$("#dcpsVerifyTb").dataTable();
	
	

});


function getListOfEmp() {
	$("#displayData").fadeIn(200);
}

function getListOfEmp() {
	$("#action").val("search");
	if($("#period").val()=="0"){
		 showError("#period", "Period is required.");
	}else{
		$("#DCPSForwardedFormsList").submit();
	}
}


	/*$("#genTextFile").click(function(){
	$("#DCPSForwardedFormsList").attr("action",contextPath+"/ddo/genLegacyTxtFile");
	$("#extn").val("txt");
	$("#extnFlag").val("1");
	$("#DCPSForwardedFormsList").submit();
     });*/
	
	
	
          $('#genTextFile').on('click', function(e) {
              e.preventDefault(); 
              
          	$("#extn").val("txt");
        	$("#extnFlag").val("1");
        	$("#extnFlag").val("1");
              var formData = {
            		  extn: 'txt',
            		  extnFlag: '1',
            		  period:$("#period").val()
              };
              
              var fileId=getBatchId();

              $.ajax({
                  url: contextPath+"/ddo/genLegacyTxtFile", 
                  type: 'POST',
                  contentType: 'application/json', 
                  data: JSON.stringify(formData),  
                  xhrFields: {
			           responseType: 'blob'
			       },
                  success: function(response, status, xhr) {
                	  var a = document.createElement('a');
			           var url = window.URL.createObjectURL(response);
			           a.href = url;
			           var disposition = xhr.getResponseHeader('Content-Disposition');
			           var fileName = disposition && disposition.indexOf('filename=') !== -1
		                ? disposition.split('filename=')[1].split(';')[0].trim().replace(/"/g, '')
		                : fileId+".txt";
			           a.download = fileName;
			           document.body.append(a);
			           a.click();
			           a.remove();
			           window.URL.revokeObjectURL(url);
			           
			           window.location.href=contextPath+"/ddo/legacyValidation";
                  },
                  error: function(xhr, status, error) {
                      $('#responseMessage').html('Error: ' + error);
                  }
              });
          });
          
          
          function getBatchId(){
        	  
        	  var fileName="abc";
        	  var formData = {
            		  extn: 'txt',
            		  extnFlag: '1',
            		  period:$("#period").val()
              };
        	  
        	  $.ajax({
                  url: contextPath+"/ddo/getBatchId", 
                  type: 'POST',
             	 async: false,
                  contentType: 'application/json', 
                  data: JSON.stringify(formData),  
                  success: function(response) {
                	  fileName=response;
                  },
                  error: function(xhr, status, error) {
                     console.log(error);
                  }
              });
        	  
        	  return fileName;
          }
          
          
          
          
	



$("table").on("click", ".verify", function() {
	  var row = $(this).closest("tr");
	  var sevaarthId=$(this).attr("data-sevaarthId");
	  var period=$(this).attr("data-period");
	  
	  swal({
		  title: "Approve Details",
		  text: "Are you sure, you want to Approve the details?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		}).then((willDelete) => {
		    if (willDelete) {
		    	var isVerified=verifySavedData(sevaarthId,period); 
		    	if(isVerified==true){
		    		  row.remove();  
		    		swal("Data Approved Successfully !!!");
		    	}
		    	
		     }
	    })
	  
});


$("table").on("click", ".reject", function() {
	var row = $(this).closest("tr");
	 var sevaarthId=$(this).attr("data-sevaarthId");
	  var period=$(this).attr("data-period");
	  
	  var remark=row.find(".remark").val();
	  
	  swal({
		  title: "Reject Details",
		  text: "Are you sure, you want to reject the details?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		}).then((willDelete) => {
		    if (willDelete) {
		    	var isReject=rejectSavedData(sevaarthId,period,remark); 
		    	if(isReject==true){
		    		  row.remove();  
		    		swal("Data Rejected Successfully !!!");
		    	}
		     }
	    })
	 
});


function verifySavedData(sevaarthId,period){
	var flag=false;
	
	var params = {
		sevaarthId : sevaarthId,
		period : period
 	};
	
	$.ajax({
		type : "POST",
		url : contextPath + '/ddo/verifySavedData',
		data : JSON.stringify(params),
		contentType : 'application/json',
		 async: false,
		error : function(xhr, status, error) {
			console.error('Error occurred:', error);
			$("#loaderMainNew").hide();
		},
		success : function(data) {
			$("#loaderMainNew").hide();
			console.log(data);
			if (parseInt(data)>0) {
				flag=true;
			}
		}
	});
	return flag;
}


function rejectSavedData(sevaarthId,period,remark){
	var flag=false;
	
	var params = {
		sevaarthId : sevaarthId,
		period : period,
		remark : remark
 	};
	
	$.ajax({
		type : "POST",
		url : contextPath + '/ddo/rejectSavedData',
		data : JSON.stringify(params),
		 async: false,
		contentType : 'application/json',
		error : function(xhr, status, error) {
			console.error('Error occurred:', error);
			$("#loaderMainNew").hide();
		},
		success : function(data) {
			$("#loaderMainNew").hide();
			console.log(data);
			if (parseInt(data)>0) {
				flag=true;
			}
		}
	});
	
	return flag;
}