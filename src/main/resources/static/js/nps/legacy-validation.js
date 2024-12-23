var contextPath = "";
	$(document).ready(function() {
	contextPath = $("#appRootPath").val();

    function getListOfEmp() {
        $("#displayData").fadeIn(200);
    }

    $("#search").click(function (e) {
        var month = $('#month').val();
        var year = $('#year').val();

        removeErrorClass($('#month'));
        removeErrorClass($('#year'));
        if (month === "0") {
            addErrorClass($('#monthId'), "Please select Month!!!");
        }

        if (year === "0") {
            addErrorClass($('#yearId'), "Please select Year!!!");
        }

        if (month !== "0" && year !== "0") {
            var dcpsLegacyModel = { year: year, month: month };

            $("#loaderMainNew").show();

            $.ajax({
                type: "POST",
                url: "../ddo/searchLegacyDataByYearAndMonth",
                async: true,
                contentType: 'application/json',
                data: JSON.stringify(dcpsLegacyModel),
                error: function (data) {
                    console.log(data);
                    $("#loaderMainNew").hide();
                },
                success: function (data) {
                    $("#loaderMainNew").hide();

                    $('#tblDataTable').show();
                    $('#tblDataTable_wrapper').show();
                    $('#tblDataTable tbody').html('');

                    if (data.length > 0) {
                        $("#tblDataTable").dataTable().fnClearTable();

                        data.forEach(function (item) {
                            var chk, fileID, empAmt, emprAmt, transcId, fileStatus, bhId;

                            var filename = item.fileName;
                            var status = item.fileStatus;
                            var transcId = item.transactionId;
                            var empAmt = item.bhEmpAmt;
                            var emprAmt = item.bhEmplrAmt;
                            var bhId = item.bhBatchFixId;
                            var voucherDate = item.voucherDate;

                            chk = (transcId != null)
                                ? `<input type='radio' class='batchId' name='fileName' disabled data-bhId='${bhId}'  data-filestatus='${status}'  data-voucherdate='${voucherDate}'  data-pid='${filename}' value='${filename}'>`
                                : `<input type='radio'   class='batchId' name='fileName' data-bhId='${bhId}' data-filestatus='${status}'  data-voucherdate='${voucherDate}'   data-pid='${filename}' value='${filename}'>`;

                            fileID = (transcId!= null)
                                ? `<a target='_blank' href='../master/downloadFileChallan/${transcId}/${filename}'>${filename}</a>`
                                : `<p data='${filename}' class='filename'>${filename}</p>`;

                            $("#tblDataTable").dataTable().fnAddData([
                                chk,
                                fileID,
                                empAmt,
                                emprAmt,
                                transcId,
                                fileStatus
                            ]);
                        });
                    } else {
                        swal("No Records Found");
                    }
                }
            });
        }
    });
    
    
    $('#viewAndSaveLegacyFile').on('click', function(e) {
        e.preventDefault(); 
        var fileId=$('input[name="fileName"]:checked').val();
        if(fileId){
        	 $("#loaderMainNew").show();
        	 $.ajax({
                 url: contextPath+"/ddo/viewAndSaveLegacyFile/"+fileId, 
                 type: 'GET',
                 contentType: 'application/json', 
                // data: JSON.stringify(formData),  
                 xhrFields: {
     		           responseType: 'blob'
     		       },
                 success: function(response, status, xhr) {
                	 $("#loaderMainNew").hide();
               	  var a = document.createElement('a');
     		           var url = window.URL.createObjectURL(response);
     		           a.href = url;
     		           var fileName =fileId+".txt";
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
        }
    });
    
    
    $('#delete').on('click', function(e) {
        e.preventDefault(); 
        var fileId=$('input[name="fileName"]:checked').val();
        var bhId=$('input[name="fileName"]:checked').attr("data-bhId");
        
        if(fileId && bhId){
        	 $("#loaderMainNew").show();
        	 $.ajax({
                 url: contextPath+"/ddo/deleteLegacyData/"+fileId+"/"+bhId, 
                 type: 'GET',
                 contentType: 'application/json', 
                 success: function(data, status, xhr) {
                	 $("#loaderMainNew").hide();
                  swal(data.response);
                  window.location.reload();
                 },
                 error: function(xhr, status, error) {
                	 $("#loaderMainNew").hide();
                	 swal(error);
                 }
             });
        }
    });
    
    function setButtonState(buttonId, state) {
        $(`#${buttonId}`).prop('disabled', state);
    }
    
   
	$(document).on('change','.batchId', function(event){
		var batchId=$(this).val();
		var filestatus=$(this).attr("data-filestatus");
		var voucherdate=$(this).attr("data-voucherdate");
		disableEnableBtn(filestatus);
	  });

	
    
 
    function disableEnableBtn(filestatus) {
        switch (parseInt(filestatus)) {
            case 0:
                setButtonState("validate", false);
                setButtonState("sendfile", true);
                setButtonState("getFile", true);
                setButtonState("delete", false);
                setButtonState("updateVoucher", false);
                break;

            case 1:
                setButtonState("validate", true);
                setButtonState("delete", true);
                setButtonState("sendfile", false);
                setButtonState("getFile", true);
                setButtonState("updateVoucher", true);
                break;

            case 2:
                setButtonState("delete", false);
                setButtonState("validate", true);
                setButtonState("sendfile", true);
                setButtonState("getFile", true);
                setButtonState("updateVoucher", true);
                break;

            case 3:
                setButtonState("delete", false);
                setButtonState("validate", false);
                setButtonState("generate", true);
                setButtonState("Forward", true);
                setButtonState("send", true);
                setButtonState("save", false);
                setButtonState("viewBill", true);
                setButtonState("vEnrty", true);
                setButtonState("updateVoucher", true);
                break;

            case 5:
                setButtonState("validate", true);
                setButtonState("delete", true);
                setButtonState("sendfile", true);
                setButtonState("updateVoucher", true);
                setButtonState("getFile", false);
                break;

            case 11:
                setButtonState("validate", true);
                setButtonState("delete", true);
                setButtonState("getFile", true);
                setButtonState("sendfile", true);
                setButtonState("updateVoucher", false);
                break;

            case 12:
                setButtonState("validate", true);
                setButtonState("delete", true);
                setButtonState("getFile", true);
                setButtonState("sendfile", true);
                setButtonState("updateVoucher", true);
                break;

            default:
                console.warn('Unexpected FileStatus value:', filestatus);
        }
    }
    
    $('#validate').on('click', function(e) {
        e.preventDefault(); 
        var fileId=$('input[name="fileName"]:checked').val();
        var month=$("#month").val();
        var year=$("#year").val();
        if(fileId && month && year){
        	 $("#loaderMainNew").show();
        	 $.ajax({
                 url: contextPath+"/ddo/validateLegacyData/"+fileId+"/"+month+"/"+year, 
                 type: 'GET',
                 contentType: 'application/json', 
                 success: function(data, status, xhr) {
                	 $("#loaderMainNew").hide();
                  swal(data.response);
                  window.location.reload();
                 },
                 error: function(xhr, status, error) {
                	 $("#loaderMainNew").hide();
                	 swal(error);
                 }
             });
        }
    });

    
    
    
});
