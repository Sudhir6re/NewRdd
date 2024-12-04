$('.stretbmain,.table-responsive').hide();

$("#txtSearchName").blur(function() {
	setTimeout(function() {
		$("#searchDiv").hide();
	}, 200);
});
var ppoNo;
var frstInstAmt;
var grossTotalArr;
var dateofDeath;
$("#searchDiv").hide();
$("#btnModal").hide();
// $("#btnProceed").hide();





$("#txtSearchName").keyup(
		function() {
			// alert("test");
			ppoNo = $("#txtSearchName").val();
			if (ppoNo.length == 0) {
				document.getElementById("searchDiv").innerHTML = "";
				document.getElementById("searchDiv").style.border = "0px";
				return;
			}
			if (ppoNo != '' && ppoNo.length != 0) {
				$("#loaderMainNew").hide();
				$.ajax({
					type : "POST",
					url : "../pension/getFamPensEmpDtls/" + ppoNo,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					// headers:{"Access-Control-Allow-Origin"},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						document.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {
							// $("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							// $("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv").append(
									"<p class='empdata' empname='"
											+ data[i].empName
											+ "' ppoNo='"
											+ data[i].ppoNo
											+ "' dor='"
											+ data[i].dor
											+ "' dateofDeath='"
											+ data[i].dateofDeath + "'>"
											+ data[i].empName + "-"
											+ data[i].ppoNo + "</p>");

							$("#dateofDeath").val(data[i].dateofDeath);
							$("#sevaarthIdCopy").val(data[i].sevaarthId);

							$("#searchDiv").css("border:1px solid #A5ACB2;");

							$("#searchDiv").show();
						}

						if (data.length == 0) {
							swal("No Employee Found");
						}
					}
				});
			}

		});









$('body')
		.on(
				'click',
				'.empdata',
				function() {
					$("#txtSearchName").val($(this).attr("empname"));

					empNam = $(this).attr("empname");
					ppoNo = $(this).attr("ppoNo");
					dateofDeath = $(this).attr("dateofDeath");
					var dor = $(this).attr("dor");
						$("#sevaarthIdCopy")
								.attr(
										"href",
										"../pension/getFamPensEmpDtls/"
												+ ppoNo);

						$('.stretbmain, .table-responsive').show();
						
						
						$('#txtEmployeeName').val(empNam);
						$('#txtPPOno').val(ppoNo);

						document.getElementById("searchDiv").innerHTML = "";
						$("#searchDiv").hide();
				});


















$("#vo").on('blur',".txtToDate",function(){
	setNoOfDays(this);
	loadPensSalaryFromRuleEngine(this);
});


//calculate No of Days
function setNoOfDays(row)
{
	var row1 = $(row).closest("tr");
	var fromDate = row1.find('.txtFromDate').val();
	var toDate = row1.find('.txtToDate').val();
	
	if(fromDate != "" && toDate != "")
	{
		if(fromDate>toDate)
		{
			swal('Select date greater than From Date');
			 row1.find('.txtFromDate').val();
			 row1.find('.txtNoOfDays').val();
		}
	//	var fromDay = fromDate.substring(0,2);
		var frDate = new Date(fromDate);
		var fromDay = frDate.getDate();
	//	var toDay = toDate.substring(0,2);
		var tDate = new Date(toDate);
		var toDay = tDate.getDate();
		var daysDiff = Number(toDay) - Number(fromDay) + 1 ;
		 row1.find('.txtNoOfDays').val(daysDiff);
	}
}

//calculate basic, DA, Commutations
function loadPensSalaryFromRuleEngine(row) {
	
	var row1 = $(row).closest("tr");
	
	var url = "";
	
	var fromDate = row1.find('.txtFromDate').val();
	var toDate = row1.find('.txtToDate').val();
	
	
	
	//var toDate=document.getElementById("txtToDate"+index).value;
	//var fromDate=document.getElementById("txtFromDate"+index).value;
	
	
	
	if(toDate!="" && fromDate!=""){
		var fromDateArray = new Date(fromDate);
		var toDateArray = new Date(toDate);
		var month = fromDateArray.getMonth();
		var year=fromDateArray.getFullYear();
		var noOfDays=row1.find('.txtNoOfDays').val();
//	var ppoNo=	ppoNo;
	//	alert("ppoNo-----"+ppoNo);
		dateofDeath=dateToYMD($("#dateofDeath").val());
		
		$
		.ajax({
			type : "GET",
			url : "../pension/calculatePensEmployeeSalary/" + ppoNo + "/"+noOfDays+"/"+fromDate+"/"+toDate+"/"+dateofDeath,
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
				console.log(data);
				swal("Salary not calculated from Rule engine becuase of some error");
			},
			success : function(data) {
				console.log(data);
				
				var basicAmt=0;
				var da=0;
					var len = data.length;
					if (len != 0) { 
						for(var i=0;i<len;i++){
							 row1.find('.txtBasicPay').val(data[i].basicPay);
							row1.find('.txtDa').val(data[i].da);
							row1.find('.txtCommutation').val(data[i].commutation);
							row1.find('.txtTotal').val(data[i].total);
					}
						}
				
				
			}
			});
	}
	}


$("#btnAddRow").click(function(){
	var index=$('#vo >tbody >tr').length;
	var srNo=$('#vo >tbody >tr').length+1;
	
	var txtFromDate='<input type="date" class="form-control removeErrorFromInput txtFromDate" id="txtFromDate'+index+'"  name="lstBrokenPeriodFamPensModel[' + index + '].fromDate"       />';
	var txtToDate='<input type="date" class="form-control removeErrorFromInput txtToDate" id="txtToDate'+index+'"  name="lstBrokenPeriodFamPensModel[' + index + '].toDate"    />';
	var noOfDays='<input type="text" class="form-control removeErrorFromInput txtNoOfDays" id="txtNoOfDays'+index+'"  name="lstBrokenPeriodFamPensModel[' + index + '].noOfdays"       />';
	var basic='<input type="text" class="form-control removeErrorFromInput txtBasicPay" id="txtBasicPay'+index+'"   name="lstBrokenPeriodFamPensModel[' + index + '].basicPay"       />';
	var da='<input type="text" class="form-control removeErrorFromInput txtDa"  id="txtDa'+index +'"  name="lstBrokenPeriodFamPensModel[' + index + '].da"     />';
	var commutation='<input type="text" class="form-control removeErrorFromInput txtCommutation"  id="txtCommutation'+index +'"  name="lstBrokenPeriodFamPensModel[' + index + '].commutation"     />';
	var total='<input type="text" class="form-control removeErrorFromInput txtTotal"  id="txtTotal'+index +'"  name="lstBrokenPeriodFamPensModel[' + index + '].total"     />';
	var ppoNo='<input type="hidden" class="form-control removeErrorFromInput ppoNo"  id="ppoNo'+index +'"  name="lstBrokenPeriodFamPensModel[' + index + '].ppoNo"   th:value='+ppoNo+'  />';

	var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="tblNomineeDtls" class="delete tblNomineeDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl"    onClick="$(this).closest(\'tr\').remove();"  data-className="BasicPnsnDtl"  data-totalRow="'+index+'"></span></a>';
	
	$("#vo>tbody").append("<tr class='datatableheader'>" + 
	        "<td>"+txtFromDate+"</td>" +
	        "<td>"+txtToDate+"</td>" +
	        "<td>"+noOfDays+"</td>" +
	        "<td>"+basic+"</td>" +
	        "<td>"+da+"</td>" +
	        "<td>"+commutation+"</td>" +
	        "<td>"+total+"</td>" +
	        "<td>"+deleteRow+"</td>" +
	        "</tr>");
});


