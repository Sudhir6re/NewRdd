document.addEventListener('DOMContentLoaded', function () {
	 $('#searchTable').hide();
	 
	  setTimeout(hidePieChartDiv, 300);
      
	  var chartData;
	  
	  
	  var roleDetails = {
			  12: "pensionInward",
			  15: "pensionast",
			  13: "pensionClk",
			  29: "pensFrstClerk",
			  30: "pensionAao",
			  31: "pensionAo",
			  32: "pensionSao",
			  33: "pensionDyCao1",
			  34: "pensionCashier"
			};

      var levelRoleVal=$("#levelRoleVal").val();
      
      var contextPath = $("#appRootPath").val();
		
		
		if(window.location.host=='mjpsevaarth.in'){
			contextPath="https://mjpsevaarth.in";
		}else if(window.location.host=='stag.mjpsevaarth.in'){
			contextPath="https://stag.mjpsevaarth.in";
		}
      
		
		
	//	alert(contextPath);
		
    
    $.ajax({
        type: "GET",
        url: contextPath+"/"+roleDetails[levelRoleVal]+"/getAllDepartmentCaseCount",
        contentType: "application/json",
        beforeSend : function(){
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
     //   data: "deptCode=" + deptCode,
        dataType: 'json',
        async: false,
        success: function(data1) {
        	chartData=data1;
        }
    });
    
    // Create the pie chart
    var chart = Highcharts.chart('pieChartContainer', {
        chart: {
            type: 'pie'
        },
        title: {
            text: 'Division Wise Pension  Case Count'
        },
        series: [{
            name: 'Pension Case Count',
            data: chartData
        }],
        plotOptions: {
            pie: {
                point: {
                    events: {
                        click: function() {
                            var districtId = this.options.id;
                            //alert('District ID: ' + districtId + '\nDistrict: ' + this.name + '\nCases: ' + this.y);
                            var divisionCode = districtId;
                            var vartableHeadingTextAuditTrail =  this.name;

                            $.ajax({
                                type: "GET",
                                url: "fetchPensionerListByTypeDivisionCode/" + divisionCode,
                                async: true, // Set to true for asynchronous request
                                contentType : 'application/json',
                                beforeSend : function(){
                					// Show image container
                					$( "#loaderMainNew").show();
                					},
                				complete : function(data){
                					$( "#loaderMainNew").hide();
                				},	
                                success: function (data) {
                                	$('#paybillsearchTable').hide();
                                	   $('#searchTable').show();
                                    if (data.length > 0) {
                                        $('#tblHeading').text(vartableHeadingTextAuditTrail);
                                        $("#tblDataTable").dataTable().fnClearTable();
                                        for (var i = 0; i < data.length; i++) {
                                            var rowData = data[i];
                                            var sevaarthId = rowData.sevaarthId;
                                            var empName = rowData.empName;
                                            var typeOfPensName = rowData.typeOfPensName;
                                            var retireAdminDeptName = rowData.retireAdminDeptName;
                                            var dojGovSrvc = rowData.dojGovSrvc;
                                            var dor = rowData.dor;
                                            var isActive = rowData.isActive;
                                            var statusLabel = getStatusLabel(isActive);
                                            
                                            var doj= rowData.dojGovSrvc;
                                            
                                            var dateOfJoining = new Date(doj);
                        					  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                        					  
                        					  var dojmon=months[dateOfJoining.getMonth()];
                        					  
                        					  var dojday=dateOfJoining.getDate();
                        					  var dojyear= dateOfJoining.getFullYear();
                        					  
                        					  var dojyearnewd=dojday+' '+dojmon+' '+dojyear;
                                            
                                            
                                            
                                            
                                            var dor = rowData.dor;
                                            var dateOfRetire = new Date(dor);
                        					  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                        					  
                        					  var retiremon=months[dateOfRetire.getMonth()];
                        					  
                        					  var retireday=dateOfRetire.getDate();
                        					  var retireyear= dateOfRetire.getFullYear();
                        					  
                        					  var retirenewd=retireday+' '+retiremon+' '+retireyear;
                                            
                                            
                                            
                                            $("#tblDataTable").dataTable().fnAddData([
                                                i + 1,
                                                sevaarthId,
                                                empName,
                                                typeOfPensName,
                                                retireAdminDeptName,
                                                dojyearnewd,
                                                retirenewd,
                                                statusLabel
                                            ]);
                                        }
                                    }
                                }
                            });

                            $('#searchTable').show();
                            $('html, body').animate({
                                scrollTop: $("#tblDataTable").offset().top
                            }, 1500);
                        }
                    }
                },
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                },
                showInLegend: true,
                ignoreHiddenPoint: false 
            }
        }
    });
    
    
  
    
    
});


function hidePieChartDiv(){
//	alert("method call");
	$("#piechartmain").css("display","none");
	var roleId = parseInt($("#levelRoleVal").val());
	if(roleId=='12' || roleId=='15' || roleId=='30' || roleId=='31' || roleId=='32' || roleId=='33' || roleId=='34'){
		$("#piechartmain").css("display","block");
	}else{
		$("#piechartmain").css("display","none");
	}
}


$(document).on('click', '.dashboardcCount', function (event) {
    var type = $(this).data("type");
    var vartableHeadingTextAuditTrail = $(this).data("val");

    $.ajax({
        type: "GET",
        url: "fetchPensionerListByType/" + type,
        async: true, // Set to true for asynchronous request
        contentType : 'application/json',
        beforeSend : function(){
			// Show image container
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
        success: function (data) {
        	//   $('#payrollsearchTable').show();
        	   $('#paybillsearchTable').hide();
            if (data.length > 0) {
                $('#tblHeading').text(vartableHeadingTextAuditTrail);
                $("#tblDataTable").dataTable().fnClearTable();
                for (var i = 0; i < data.length; i++) {
                    var rowData = data[i];
                    var sevaarthId = rowData.sevaarthId;
                    var empName = rowData.empName;
                    var typeOfPensName = rowData.typeOfPensName;
                    var retireAdminDeptName = rowData.retireAdminDeptName;
                    var dojGovSrvc = rowData.dojGovSrvc;
                    var dor = rowData.dor;
                    var isActive = rowData.isActive;
                    var statusLabel = getStatusLabel(isActive);
                    
                    
                    var doj= rowData.dojGovSrvc;
                    
                    var dateOfJoining = new Date(doj);
					  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
					  
					  var dojmon=months[dateOfJoining.getMonth()];
					  
					  var dojday=dateOfJoining.getDate();
					  var dojyear= dateOfJoining.getFullYear();
					  
					  var dojyearnewd=dojday+' '+dojmon+' '+dojyear;
                    
                    
                    
                    
                    var dor = rowData.dor;
                    var dateOfRetire = new Date(dor);
					  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
					  
					  var retiremon=months[dateOfRetire.getMonth()];
					  var retireday=dateOfRetire.getDate();
					  var retireyear= dateOfRetire.getFullYear();
					  
					  var retirenewd=retireday+' '+retiremon+' '+retireyear;
                    
                    $("#tblDataTable").dataTable().fnAddData([
                        i + 1,
                        sevaarthId,
                        empName,
                        typeOfPensName,
                        retireAdminDeptName,
                        dojyearnewd,
                        retirenewd,
                        statusLabel
                    ]);
                }
            }
        }
    });

    $('#searchTable').show();
    $('html, body').animate({
        scrollTop: $("#tblDataTable").offset().top
    }, 1500);
});

function getStatusLabel(isActive) {
    switch (isActive) {
        case 0:
            return '<span class="label label-success text-center">Approved By DyCao1</span>';
        case 1:
            return '<span class="label label-success text-center">Approved By DyCao1</span>';
        default:
            return '<span class="label label-success text-center">Approved By DyCao1</span>';
    }
}
$('#payrollsearchTable').hide();
$('#paybillsearchTable').hide();


$(document).on('click', '.dasboardEmpCount', function (event) {
    var type = $(this).data("type");
    var vartableHeadingTextAuditTrail = $(this).data("val");

    $.ajax({
        type: "GET",
        url: "fetchPayrollEmpListByType/" + type,
        async: true, // Set to true for asynchronous request
        contentType : 'application/json',
        beforeSend : function(){
			// Show image container
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
        success: function (data) {
        	$('#paybillsearchTable').hide();
        	   $('#payrollsearchTable').show();
            if (data.length > 0) {
                $('#payrolltblHeading').text(vartableHeadingTextAuditTrail);
                $("#payrolltblDataTable").dataTable().fnClearTable();
                for (var i = 0; i < data.length; i++) {
                    var rowData = data[i];
                    var sevaarthId = rowData.sevaarthId;
                    var empName = rowData.employeeFullName;
                    var desg = rowData.designationName;
                    var dojGovSrvc = rowData.doj;
                    
                    var date = new Date(dojGovSrvc);
					  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
					  
					  var mon=months[date.getMonth()];
					  
					  var day=date.getDate();
					  var year= date.getFullYear();
					  
					  var newd=day+' '+mon+' '+year;
					  var status='';
                    var dor = rowData.superAnnDate;
                    var dateOfRetire = new Date(dor);
					  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
					  
					  var retiremon=months[dateOfRetire.getMonth()];
					  
					  var retireday=dateOfRetire.getDate();
					  var retireyear= dateOfRetire.getFullYear();
					  
					  var retirenewd=retireday+' '+retiremon+' '+retireyear;
                    $("#payrolltblDataTable").dataTable().fnAddData([
                        i + 1,
                        sevaarthId,
                        empName,
                        desg,
                        newd,
                        retirenewd
                    ]);
                }
            }
        }
    });

    $('#payrollsearchTable').show();
    $('html, body').animate({
        scrollTop: $("#payrolltblDataTable").offset().top
    }, 1500);
});


$(document).on('click', '.dasboardPaybillEmpCount', function (event) {
    var type = $(this).data("type");
    var vartableHeadingTextAuditTrail = $(this).data("val");

    $.ajax({
        type: "GET",
        url: "fetchPayrollEmpListByType/" + type,
        async: true, // Set to true for asynchronous request
        contentType : 'application/json',
        beforeSend : function(){
			// Show image container
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
        success: function (data) {
        	$('#payrollsearchTable').hide();
        	   $('#paybillsearchTable').show();
        	   $("#paybilltblDataTable").dataTable().fnClearTable();
            if (data.length > 0) {
                $('#paybilltblHeading').text(vartableHeadingTextAuditTrail);
                $("#paybilltblDataTable").dataTable().fnClearTable();
                for (var i = 0; i < data.length; i++) {
                    var rowData = data[i];
                    var sevaarthId = rowData.sevaarthId;
                    var empName = rowData.employeeFullName;
                    var desg = rowData.designationName;
                    var totalgross=rowData.totalGrossAmt;
                    var totalnet=rowData.totalNetAmt;
                    var totalded=rowData.totalDedAmt;
                    $("#paybilltblDataTable").dataTable().fnAddData([
                        i + 1,
                        sevaarthId,
                        empName,
                        desg,
                        totalgross,
                        totalnet,
                        totalded
                    ]);
                }
            }
        }
    });

    $('#paybillsearchTable').show();
    $('html, body').animate({
        scrollTop: $("#paybilltblDataTable").offset().top
    }, 1500);
});