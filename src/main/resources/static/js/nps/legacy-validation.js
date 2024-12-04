$(document).ready(function () {
  //  $("#displayData").hide();

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

                            chk = (transcId != null)
                                ? `<input type='radio' name='chk' disabled data-bhId='${filename}' data-nsdlStatusCode='${filename}' class='chk' data-pid='${filename}' value='${filename}'>`
                                : `<input type='radio' name='chk' data-bhId='${bhId}' data-nsdlStatusCode='${status}' class='chk' data-pid='${filename}' value='${filename}'>`;

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
});
