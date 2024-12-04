$(document).ready(function(){
	var ppoNo;
	var dateofDeath;
	$("#searchDiv").hide();
	$("#btnModal").hide();

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
						url : "../pension/getPensBrokenReportEmp/" + ppoNo,
						async : false,
						contentType : 'application/json',
						error : function(data) {
							console.log(data);
							$("#loaderMainNew").hide();
						},
						success : function(data) {
							console.log(data);
							$("#loaderMainNew").hide();
							document.getElementById("searchDiv").innerHTML = "";
							for (var i = 0; i < data.length; i++) {
								$("#searchDiv").append(
										"<p class='empdata' empname='"
												+ data[i].empName
												+ "' ppoNo='"
												+ data[i].ppoNo
												+ "' dateofDeath='"
												+ data[i].dateofDeath + "'>"
												+ data[i].empName + "-"
												+ data[i].ppoNo + "</p>");

								$("#ppoNo").val(data[i].ppoNo);
								$("#dateofDeath").val(dateToYMD(data[i].dateofDeath));
								$("#penEmpName").text(data[i].empName);

								$("#searchDiv").css("border:1px solid #A5ACB2;");

								$("#searchDiv").show();
							}

							if (data.length == 0) {
								
								swal("No Employee Found");
								$(".Hiddenstretb").hide();
								$("#searchDiv").hide();
							}
						}
					});
				}

			});



	 calcTotal();
	
	var status = false;
	function SearchEmployee(e) {
		
		$('#action').val('SEARCH_EMP');
		
		var searchName = document.getElementById("txtSearchName").value;
		
		if(searchName == "")
		{
			swal('Please enter Sevaarth ID');
			return false;
		}
		var flag=true;
			if(flag){
				$(".arrTblBody").empty();
				$("#PensBrokenPeriod").submit();   
				 $(".BrokenPeriodHiddenData").show();
				 $("#vo").dataTable();
				return true;
			}
		
	}

	


	$(".DeleteRowBrokenPrd").click(function(){
//		var pensDaArrPayMstEntityId=$("#pensDaArrPayMstEntityId").val();
		var pensBrokenId=$(this).attr("data");
		var context=$("#context").val();
		
		swal({
			  title: "Are you sure?",
			  text: "to delete this row",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  
					$.ajax({
						type : "GET",
						url : context+"/pension/deleteDueDrwanEntry/"
									+pensBrokenId,
						async : true,
						contentType : 'application/json',
						error : function(data) {
							 console.log(data);
						},
						success : function(data) {
							 console.log(data);
								swal("Pension Arrear entry deleted successfully");
					        }				
						   });
				  $(this).closest("tr").remove();
				  calcTotal();
				
			  } else {
			  }
			});

		});
	
	
	
	$("#daArrearTable").on("blur", ".daRate, .basicPay, .commutation, .drawnDaRate, .drawnbasicPay, .drawnCommutation", function() {
	    var row = $(this).closest("tr");
	    
	    var daRate = parseFloat(row.find(".daRate").val()) || 0;
	    var basicPay = parseFloat(row.find(".basicPay").val()) || 0;
	    var commutation = parseFloat(row.find(".commutation").val()) || 0;
	    
	    var drawnDaRate = parseFloat(row.find(".drawnDaRate").val()) || 0;
	    var drawnbasicPay = parseFloat(row.find(".drawnbasicPay").val()) || 0;
	    var drawnCommutation = parseFloat(row.find(".drawnCommutation").val()) || 0;
	    
	    var fromDate = row.find(".fromDate").val();
	    var toDate = row.find(".toDate").val();

	    var frDate = new Date(fromDate);
	    var fromDay = frDate.getDate();
	    var tDate = new Date(toDate);
	    var toDay = tDate.getDate();
	    var noOfDays1 = Number(toDay) - Number(fromDay) + 1;
	    var totalNoOfDays = new Date(frDate.getFullYear(), frDate.getMonth() + 1, 0);

	    var daAmt = ((basicPay / 100) * daRate);
	    var total = (basicPay + daAmt) - commutation;

	    var drawnDaAmt = ((drawnbasicPay / 100) * drawnDaRate);
	    var drawnTotal = (drawnbasicPay + drawnDaAmt) - drawnCommutation;

	    row.find('.da').val(Math.round(daAmt));
	    row.find('.total').val(Math.round(total));
	    
	    row.find('.drawnDa').val(Math.round(drawnDaAmt));
	    row.find('.drawTotal').val(Math.round(drawnTotal));
	    
	    row.find('.diffTotal').val(Math.round(total)-Math.round(drawnTotal));

	    calcTotal();
	});



	
	function calcTotal() {
	    var totalDays = 0;
	    var totalBasic = 0;
	    var totalDa = 0;
	    var totalArr = 0;
	    var totalDrawnDaRate = 0;
	    var totalDrawnbasicPay = 0;
	    var totalDrawnDa = 0;
	    var totalDrawnCommutation = 0;
	    var totalDrawTotal = 0;
	    var totalCommutation = 0;
	    var totaldiffTotal = 0;

	    $(".noOfdays").each(function() {
	        totalDays += parseFloat($(this).val()) || 0;
	    });

	    $(".basicPay").each(function() {
	        totalBasic += parseFloat($(this).val()) || 0;
	    });

	    $(".da").each(function() {
	        totalDa += parseFloat($(this).val()) || 0;
	    });
	    
	    
	    $(".commutation").each(function() {
	    	totalCommutation += parseFloat($(this).val()) || 0;
	    });

	    $(".total").each(function() {
	        totalArr += parseFloat($(this).val()) || 0;
	    });

	    $(".drawnDaRate").each(function() {
	        totalDrawnDaRate += parseFloat($(this).val()) || 0;
	    });

	    $(".drawnbasicPay").each(function() {
	        totalDrawnbasicPay += parseFloat($(this).val()) || 0;
	    });

	    $(".drawnDa").each(function() {
	        totalDrawnDa += parseFloat($(this).val()) || 0;
	    });

	    $(".drawnCommutation").each(function() {
	        totalDrawnCommutation += parseFloat($(this).val()) || 0;
	    });

	    $(".drawTotal").each(function() {
	        totalDrawTotal += parseFloat($(this).val()) || 0;
	    });
	    
	    
	    $(".diffTotal").each(function() {
	        totaldiffTotal += parseFloat($(this).val()) || 0;
	    });

	    $('#totalDays').text(Math.round(totalDays));
	    $('#totalBasic').text(Math.round(totalBasic));
	    $('#totalDa').text(Math.round(totalDa));
	    $('#totalCommutation').text(Math.round(totalCommutation));
	    $('#otherArr').text(Math.round(totalArr));

	    $('#totalDrawnDaRate').text(Math.round(totalDrawnDaRate));
	    $('#totalDrawnbasicPay').text(Math.round(totalDrawnbasicPay));
	    $('#totalDrawnDa').text(Math.round(totalDrawnDa));
	    $('#totalDrawnCommutation').text(Math.round(totalDrawnCommutation));
	    $('#totalDrawTotal').text(Math.round(totalDrawTotal));
	    
	    $('#diffTotalDrawTotal').text(totaldiffTotal);
	}
	
	
	
	$("#btnAddRow1").click(function() {
	    var index = $('.arrTblBody > tr').length;
	    var srNo = index + 1;

	    var pensBrokenId = '<input type="hidden" class="form-control pensBrokenId" id="pensBrokenId' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].pensBrokenId" />';

	    var fromDate = '<input type="date" class="form-control fromDate removeErrorFromDate" id="fromDate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].fromDate" onBlur="checkDateForThisMonth(this,' + index + ')" />';
	    var toDate = '<input type="date" class="form-control toDate removeErrorFromDate" id="toDate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].toDate" onBlur="checkDateForThisMonth(this,' + index + ');checkSameMonth(this,' + index + ')" />';
	    var noOfdays = '<input type="text" class="form-control noOfdays removeErrorFromInput" id="noOfdays' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].noOfdays" />';
	    var daRate = '<input type="text" class="form-control daRate removeErrorFromInput" id="daRate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].daRate" />';
	    var basicPay = '<input type="text" class="form-control basicPay removeErrorFromInput" id="basicPay' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].basicPay" />';
	    var da = '<input type="text" class="form-control da removeErrorFromInput" id="da' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].da" />';
	    var commutation = '<input type="text" class="form-control commutation removeErrorFromInput" id="commutation' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].commutation" />';
	    var total = '<input type="text" class="form-control total removeErrorFromInput" id="total' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].total" />';
	    var drawnDaRate = '<input type="text" class="form-control drawnDaRate removeErrorFromInput" id="drawnDaRate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnDaRate" />';
	    var drawnbasicPay = '<input type="text" class="form-control drawnbasicPay removeErrorFromInput" id="drawnbasicPay' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnbasicPay" />';
	    var drawnDa = '<input type="text" class="form-control drawnDa removeErrorFromInput" id="drawnDa' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnDa" />';
	    var drawnCommutation = '<input type="text" class="form-control drawnCommutation removeErrorFromInput" id="drawnCommutation' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnCommutation" />';
	    var drawTotal = '<input type="text" class="form-control drawTotal removeErrorFromInput" id="drawTotal' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawTotal" />';
	    var diffTotal = '<input type="text" class="form-control diffTotal removeErrorFromInput" id="diffTotal' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].diffTotal" />';
	    var deleteRow = '<div align="center" style="vertical-align: middle;" data-className="daArrearTable" class="delete daArrearTable"><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl DeleteRowBrokenPrd" id="DeleteRowBrokenPrd' + index + '" data-className="BasicPnsnDtl"></span></a></div>';

	    var newRow = '<tr class="datatableheader">' +
	        '<td>' + fromDate+pensBrokenId + '</td>' +
	        '<td>' + toDate + '</td>' +
	        '<td>' + noOfdays + '</td>' +
	        '<td>' + daRate + '</td>' +
	        '<td>' + basicPay + '</td>' +
	        '<td>' + da + '</td>' +
	        '<td>' + commutation + '</td>' +
	        '<td>' + total + '</td>' +
	        '<td>' + drawnDaRate + '</td>' +
	        '<td>' + drawnbasicPay + '</td>' +
	        '<td>' + drawnDa + '</td>' +
	        '<td>' + drawnCommutation + '</td>' +
	        '<td>' + drawTotal + '</td>' +
	        '<td>' + diffTotal + '</td>' +
	        '<td>' + deleteRow + '</td>' +
	        '</tr>';

	    $(".arrTblBody").append(newRow);
	});


	
	$("#daArrearTable").on('blur',".fromDate,.toDate",function(){
		var row = $(this).closest("tr");
		var fromDate = row.find('.fromDate').val();
		var toDate = row.find('.toDate').val();
		
		var calculateArerarOn ="0";
		
		var date1=new Date(fromDate);
		var date2=new Date(toDate);
		
		
		if(fromDate != "" && toDate != ""  &&  Date.parse(date1) &&  Date.parse(date2))
		{   
			
			if (date1.getMonth() != date2.getMonth() || date1.getFullYear() != date2.getFullYear()){
			swal("Please Select same month in a row");
		}else if(fromDate>toDate){
				swal('Select date greater than From Date');
				row.find('.fromDate').val("");
				row.find('.toDate').val("");
				row.find('.noOfDays').val("");
		}else{
			var frDate = new Date(fromDate);
			var fromDay = frDate.getDate();
			var tDate = new Date(toDate);
			var toDay = tDate.getDate();
			var daysDiff = Number(toDay) - Number(fromDay) + 1 ;
		
			row.find('.noOfdays').val(daysDiff);
			
			
			
			var noOfDays=daysDiff;
			var ppoNo=$("#PpoNo").val();
			var context=$("#context").val();
			
			 fromDate = row.find('.fromDate').val();
			 toDate = row.find('.toDate').val();
			
			 
			 //var remark=dateToDMY(fromDate)+" To "+dateToDMY(toDate)+" "+noOfDays+" Days";
			 
			 
			// row.find('.remarks').val(remark);
			 
				$.ajax({
					type : "GET",
					url : context+"/pension/calculateDueDrawanForOneMonth",
					async : true,
					data:{fromDate:fromDate,toDate:toDate,ppoNo:ppoNo,noOfDays:noOfDays},
					contentType : 'application/json',
					error : function(data) {
						 console.log(data);
					},
					success : function(data) {
						 console.log(data);
						var len = data.length;
						if (len != 0) { 
		                for(var i=0;i<len;i++){
		        			row.find('.drawnbasicPay').val(data[i].drawnbasicPay);
		        			row.find('.drawnDa').val(data[i].drawnDa);
		        			row.find('.drawnDaRate').val(data[i].drwanDaRate);
		        			row.find('.drawTotal').val(data[i].drawTotal);
		        			row.find('.drawnCommutation').val(data[i].drawnCommutation);
		        			row.find('.diffTotal').val(data[i].diffTotal);
		        			row.find('.noOfDays').val(data[i].noOfdays);
		        			
		        			row.find('.drawnDaRate').val(data[i].drawnDaRate);
		        			row.find('.daRate').val(data[i].daRate);
		                }				
					   }
						setTimeout(calcTotal, 2000);
					}
				});
		}
		}
	});
		
		
		$("#searchByRange").click(function(){
		var fromDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		
		
		var calculateArerarOn = "";
		
		var date1=new Date(fromDate);
		var date2=new Date(toDate);
		
		
		if(fromDate != "" && toDate != ""  &&  Date.parse(date1) &&  Date.parse(date2))
		{   
			
	        if(fromDate>toDate){
				swal('Select date greater than From Date');
			}else{
				var frDate = new Date(fromDate);
				var fromDay = frDate.getDate();
				var tDate = new Date(toDate);
				var toDay = tDate.getDate();
				var daysDiff = Number(toDay) - Number(fromDay) + 1 ;
				
				//row.find('.noOfDays').val(daysDiff);
				
				
				
				var noOfDays=daysDiff;
				var ppoNo=$("#PpoNo").val();
				var context=$("#context").val();
				
				$.ajax({
					type : "GET",
					url : context+"/pension/calculateDueDrwanArrearByRange/"
					+fromDate+"/"+toDate+"/"+ppoNo,
					async : true,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
					},
					success : function(data) {
						console.log(data);
						var len = data.length;
						if (len != 0) { 
							 $(".arrTblBody").empty();
							for(var i=0;i<len;i++){
								
								
								var index=i;
								var daArrearTable = $(".arrTblBody>tr").length;
								
								var fromDateVal=data[i].fromDate;
								var toDateVal=data[i].toDate;
								var noOfDaysVal=data[i].noOfdays;
								var pensionBasicVal = data[i].drawnbasicPay ?? 0;
								var daRateVal = data[i].drawnDaRate  ?? 0;
								
								
								var daRate = data[i].daRate ?? 0;
								
								
								
								var daVal = data[i].drawnDa ?? 0;
								var totalVal = data[i].drawTotal ?? 0;
								var drawnCommutation = data[i].drawnCommutation ?? 0;

								
								//var remark=dateToDMY(fromDateVal)+" To "+dateToDMY(toDateVal)+" "+noOfDaysVal+" Days";
								
							    var srNo = index + 1;

							    var pensBrokenId = '<input type="hidden"   class="form-control pensBrokenId" id="pensBrokenId' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].pensBrokenId" />';

							    var fromDate = '<input type="date"  value="'+dateToYMD(fromDateVal)+'"   class="form-control fromDate removeErrorFromDate" id="fromDate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].fromDate" onBlur="checkDateForThisMonth(this,' + index + ')" />';
							    var toDate = '<input type="date"  value="'+dateToYMD(toDateVal)+'"   class="form-control toDate removeErrorFromDate" id="toDate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].toDate" onBlur="checkDateForThisMonth(this,' + index + ');checkSameMonth(this,' + index + ')" />';
							    var noOfdays = '<input type="text"   value="'+noOfDaysVal+'"  class="form-control noOfdays removeErrorFromInput" id="noOfdays' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].noOfdays" />';
							    var daRate = '<input type="text"   value="'+daRate+'"    class="form-control daRate removeErrorFromInput" id="daRate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].daRate" />';
							    var basicPay = '<input type="text" class="form-control basicPay removeErrorFromInput" id="basicPay' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].basicPay" />';
							    var da = '<input type="text" class="form-control da removeErrorFromInput" id="da' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].da" />';
							    var commutation = '<input type="text" class="form-control commutation removeErrorFromInput" id="commutation' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].commutation" />';
							    var total = '<input type="text" class="form-control total removeErrorFromInput" id="total' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].total" />';
							
							    var drawnDaRate = '<input type="text"  value="'+daRateVal+'"  class="form-control drawnDaRate removeErrorFromInput" id="drawnDaRate' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnDaRate" />';
							    var drawnbasicPay = '<input type="text" value="'+pensionBasicVal+'"   class="form-control drawnbasicPay removeErrorFromInput" id="drawnbasicPay' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnbasicPay" />';
							    var drawnDa = '<input type="text" value="'+daVal+'"  class="form-control drawnDa removeErrorFromInput" id="drawnDa' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnDa" />';
							    var drawnCommutation = '<input type="text"  value="'+drawnCommutation+'"   class="form-control drawnCommutation removeErrorFromInput" id="drawnCommutation' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawnCommutation" />';
							    var drawTotal = '<input type="text" value="'+totalVal+'"  class="form-control drawTotal removeErrorFromInput" id="drawTotal' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].drawTotal" />';
							  
							    var diffTotal = '<input type="text"    class="form-control diffTotal removeErrorFromInput" id="diffTotal' + index + '" name="lstBrokenPeriodFamPensModel[' + index + '].diffTotal" />';
							    var deleteRow = '<div align="center" style="vertical-align: middle;" data-className="daArrearTable" class="delete daArrearTable"><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl DeleteRowBrokenPrd" id="DeleteRowBrokenPrd' + index + '" data-className="BasicPnsnDtl"></span></a></div>';

							    var newRow = '<tr class="datatableheader">' +
							        '<td>' + fromDate+pensBrokenId + '</td>' +
							        '<td>' + toDate + '</td>' +
							        '<td>' + noOfdays + '</td>' +
							        '<td>' + daRate + '</td>' +
							        '<td>' + basicPay + '</td>' +
							        '<td>' + da + '</td>' +
							        '<td>' + commutation + '</td>' +
							        '<td>' + total + '</td>' +
							        '<td>' + drawnDaRate + '</td>' +
							        '<td>' + drawnbasicPay + '</td>' +
							        '<td>' + drawnDa + '</td>' +
							        '<td>' + drawnCommutation + '</td>' +
							        '<td>' + drawTotal + '</td>' +
							        '<td>' + diffTotal + '</td>' +
							        '<td>' + deleteRow + '</td>' +
							        '</tr>';

							    $(".arrTblBody").append(newRow);
							
								/*
								var pensDaArrPayMstEntityId='<input type="hidden"  id="pensDaArrPayMstEntityId'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].pensDaArrPayMstEntityId"       />';
								var fromDatecol='<input type="date" value="'+dateToYMD(fromDateVal)+'"   class="form-control removeErrorFromDate fromDate"    id="fromDate'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].fromDate"       />';
								var toDateCol='<input type="date"  value="'+dateToYMD(toDateVal)+'"  class="form-control removeErrorFromDate toDate"    id="toDate'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].toDate"       />';
								var noOfDays='<input type="text" value="'+noOfDaysVal+'" class="form-control float removeErrorFromInput noOfdays" id="noOfdays'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].noOfdays"       />';
								var pensionBasic='<input type="text"  value="'+pensionBasicVal+'"   name="lstPensDaArrearPayMstModel[' + index + '].pensionBasic"       class="form-control  pensionBasic removeErrorFromInput"/>';
								var daRate='<input type="text" value="'+daRateVal+'"  class="form-control removeErrorFromInput daRate" id="daRate' + index + '"  name="lstPensDaArrearPayMstModel[' + index + '].daRate"       />';
								var da='<input type="text"   value="'+daVal+'"  class="form-control da removeErrorFromInput" id="da' + index + '"   name="lstPensDaArrearPayMstModel[' + index + '].da"  />';
								var total='<input type="text"    value="'+totalVal+'"   class="form-control total removeErrorFromInput"  id="total' + index + '"  name="lstPensDaArrearPayMstModel[' + index + '].total"    />';
								var remarks='<input type="text"   value="'+remark+'"   class="form-control remarks removeErrorFromInput"    id="remarks' + index + '"    name="lstPensDaArrearPayMstModel[' + index +'].remarks"   />';
								var deleteRow='<a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl"   onClick="$(this).closest(tr).remove(); calcTotal();"  	data-className="BasicPnsnDtl"   data-totalRow="'+index+'"></span></a>';
								
								
								$(".arrTblBody").append("<tr>" + 
										"<td class='text-center'>"+fromDatecol+pensDaArrPayMstEntityId+"</td>" +
								        "<td>"+toDateCol+"</td>" +
								        "<td>"+noOfDays+"</td>" +
								        "<td>"+pensionBasic+"</td>" +
								        "<td>"+daRate+"</td>" +
								        "<td class='text-center '>"+da+"</td>" +
								        "<td class='text-center'>"+total+"</td>" +
								        "<td class='text-center'>"+remarks+"</td>" +
								        "<td>"+deleteRow+"</td>" +
								        "</tr>");*/
							
							}				
						}
						setTimeout(calcTotal, 2000);
					}
				});
				
				
			}
		}
	});
		
		

		$("#drawnbasicPay0").keyup(function(){
			$(".drawnbasicPay").val($("#drawnbasicPay0").val());
		});

		$("#drawnDaRate0").keyup(function(){
			$("drawnDaRate").val($("#drawnDaRate0").val());
		});


		$("#drawnCommutation0").keyup(function(){
			$(".drawnCommutation").val($("#drawnCommutation0").val());
		});

		$("#basicPay0").keyup(function(){
			$(".basicPay").val($("#basicPay0").val());
		});


		$("#daRate").keyup(function(){
			$(".daRate").val($("#daRate").val());
		});
		$("#commutation0").keyup(function(){
			$(".commutation").val($("#commutation0").val());
		});



		$("#btnReject").click(function(){
			var context=$("#context").val();
			  $("#PensBrokenPeriod").attr("action", context+"/pension/rejectPensDaArrCalc"); 
		});

		function checkSameMonth(toDateObj,counter){

			var fromDate=document.getElementById("fromDate"+counter).value;
			var toDate=toDateObj.value;
			var fromdateArray=fromDate.split("-");
			
			var todateArray=toDate.split("-");
			toDateMonth = toDate.substring(4,5);
			var d = new Date();
			var currMonth = d.getMonth();
			var currYear = d.getFullYear();
			// alert('currDate 2 is---'+d);
			// alert('toDateMonth is---'+toDateMonth);

			
			
			if(fromdateArray[1]!=todateArray[1]){
				swal("Please Select same month in a row");
				toDateObj.value="";
				return false;
			}
				
			if(fromdateArray[0]!=todateArray[0]){
				swal("Please Select same year in a row");
				toDateObj.value="";
				return false;
			}
		return true;
			
		}
		function checkDateForThisMonth(dateField,counter)
		{
			
		// alert("checkDateForThisMonth date method Executed");
			// alert('Inside checkDateForThisMonth');
			var retFlag=checkFutureDate(dateField,counter);
			
			// return retFlag;
			var dateValue = dateField.value.trim() ;
		// var dateValue = dateField.value ;
			if(dateValue == "")
			{
				return;
			}
			//var year = document.getElementById('paybillYear').options[document.getElementById('paybillYear').selectedIndex].text.trim().substring(0,4);
			//var nextYear = Number(year) + 1;
			//var month = Number(document.getElementById("paybillMonth").value.trim());
			// alert('month'+month);
			
			var yearFromDate =  dateValue.substring(6,10);
			var monthFromDate = Number(dateValue.substring(3,5));
			
			// Below code checks for overlapping period
			var one_day = 1000*60*60*24;
			str3 = document.getElementById("fromDate"+counter).value.trim(); 
			str4 = document.getElementById("toDate"+counter).value.trim(); 
			
			
			if(str3 != "" && str4 != "")
			{
				var dt3  = parseInt(str3.substring(0,2),10); 
			    var mon3 = parseInt(str3.substring(3,5),10); 
			    var yr3  = parseInt(str3.substring(6,10),10); 
			    var dt4  = parseInt(str4.substring(0,2),10); 
			    var mon4 = parseInt(str4.substring(3,5),10); 
			    var yr4  = parseInt(str4.substring(6,10),10); 
			    
			    var date3 = new Date(yr3, mon3-1, dt3);
			    var date4 = new Date(yr4, mon4-1, dt4);
				
				var totalRows = document.getElementById("hidTotalRows").value;
				
				for(var i=1;i<=totalRows;i++){
					
				    var str1 = document.getElementById("fromDate"+i).value; 
				    var str2 = document.getElementById("toDate"+i).value; 
			
				    var dt1  = parseInt(str1.substring(0,2),10); 
				    var mon1 = parseInt(str1.substring(3,5),10); 
				    var yr1  = parseInt(str1.substring(6,10),10); 
				    var dt2  = parseInt(str2.substring(0,2),10); 
				    var mon2 = parseInt(str2.substring(3,5),10); 
				    var yr2  = parseInt(str2.substring(6,10),10); 
			
				    var date1 = new Date(yr1, mon1-1, dt1); 
				    var date2 = new Date(yr2, mon2-1, dt2);
			
				    var Diff1 = Math.floor((date3.getTime() - date1.getTime())/(one_day));
				    var Diff2 = Math.floor((date2.getTime() - date3.getTime())/(one_day));
				    var Diff3 = Math.floor((date4.getTime() - date1.getTime())/(one_day));
				    var Diff4 = Math.floor((date2.getTime() - date4.getTime())/(one_day));
			
				  
				}
			}
			
			return true;
		}

		function digitFormat(formfield)
		{	var val;
			if(window.event.keyCode>47 && window.event.keyCode<58)
			{
				val=formfield.value;
				if(val[1]!=null)
				{
					if(val[1].length>1)
					{
						window.event.keyCode=0;
					}
				}
			}
			else
			{
				window.event.keyCode=0;
			}
		}

		function dateFormat(field)
		{
			var value=new String(field.value);	if(value.length==2)
			{
				field.value=value+'/'
			}
			if(value.length==5)
			{
			field.value=value+'/'
			}
		}


		$("#PensBrokenPeriod").submit(function(e){
			var flag = 0;
			var daArrearTable = $(".arrTblBody>tr").length;
			
			
			if($("#PensBrokenPeriod").attr("action")!="loadDueDrawnPage"){
				 $(".arrTblBody > tr").each(function(index) {
				        var $row = $(this);
				        var fromDate = $row.find('.fromDate').val();
				        var toDate = $row.find('.toDate').val();
				        var noOfDays = $row.find('.noOfdays').val();
				        var daRate = $row.find('.daRate').val();
				        var da = $row.find('.da').val();
				        var pensionBasic = $row.find('.basicPay').val();
				        var commutation = $row.find('.commutation').val();
				        var total = $row.find('.total').val();
				        var drawnDaRate = $row.find('.drawnDaRate').val();
				        var drawnbasicPay = $row.find('.drawnbasicPay').val();
				        var drawnDa = $row.find('.drawnDa').val();
				        var drawnCommutation = $row.find('.drawnCommutation').val();
				        var drawTotal = $row.find('.drawTotal').val();
				        var diffTotal = $row.find('.diffTotal').val();
				        var remarks = $row.find('.remarks').val();

				        // Perform validation checks
				        if (fromDate === '') {
				            addErrorClass($row.find('.fromDate'), "Please Enter From Date");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.fromDate'));
				        }
				        if (toDate === '') {
				            addErrorClass($row.find('.toDate'), "Please enter To Date");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.toDate'));
				        }
				        if (noOfDays === '' || noOfDays === '0') {
				            addErrorClass($row.find('.noOfdays'), "Please enter No of days");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.noOfdays'));
				        }
				        if (daRate === '') {
				            addErrorClass($row.find('.daRate'), "Please enter DA Rate");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.daRate'));
				        }
				        if (da === '') {
				            addErrorClass($row.find('.da'), "Please enter DA Amount");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.da'));
				        }
				        if (pensionBasic === '') {
				            addErrorClass($row.find('.basicPay'), "Please enter Basic Pension Amount");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.basicPay'));
				        }
				        if (commutation === '') {
				            addErrorClass($row.find('.commutation'), "Please enter Commutation Amount");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.commutation'));
				        }
				        if (total === '') {
				            addErrorClass($row.find('.total'), "Please enter Total Amount");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.total'));
				        }
				        if (drawnDaRate === '') {
				            addErrorClass($row.find('.drawnDaRate'), "Please enter Drawn DA Rate");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.drawnDaRate'));
				        }
				        if (drawnbasicPay === '') {
				            addErrorClass($row.find('.drawnbasicPay'), "Please enter Drawn Basic Pay");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.drawnbasicPay'));
				        }
				        if (drawnDa === '') {
				            addErrorClass($row.find('.drawnDa'), "Please enter Drawn DA");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.drawnDa'));
				        }
				        if (drawnCommutation === '') {
				            addErrorClass($row.find('.drawnCommutation'), "Please enter Drawn Commutation");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.drawnCommutation'));
				        }
				        if (drawTotal === '') {
				            addErrorClass($row.find('.drawTotal'), "Please enter Draw Total");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.drawTotal'));
				        }
				        if (diffTotal === '') {
				            addErrorClass($row.find('.diffTotal'), "Please enter Diff Total");
				            flag = true;
				        } else {
				            removeErrorClass($row.find('.diffTotal'));
				        }
				       
				    });
			}
			
			if(flag==true){
				e.preventDefault();
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
							$.ajax({
								type : "POST",
								url : "../pension/fetchManualEntryData/" + ppoNo,
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
									var totalbasic  = 0;
									var totalda  = 0;
									var totalcomm  = 0;
									var totaldue  = 0;
									
									
									var totalDrawbasic  = 0;
									var totalDrawda  = 0;
									var totalDrawcomm  = 0;
									var totalDraw  = 0;
									
									var diffTotal1=0;
									
									
									
									for (var i = 0; i < data.length; i++) {
										var fromdate =  dateToDMY(data[i].fromDate);
										var todate =  dateToDMY(data[i].toDate);
										var daamt = data[i].da;
										var basic = data[i].basicPay;
										var commutation = data[i].commutation;
										var total = data[i].total;
										
										totalbasic =data[i].basicPay  +  totalbasic ;
										totalda =data[i].da  +  totalda ;
										totalcomm =data[i].commutation  +  totalcomm ;
										totaldue =data[i].total  +  totaldue ;
										
										
										
										var drawBasic=data[i].drawnbasicPay;
										var drawnDaRate=data[i].drawnDaRate;
										var daRate=data[i].daRate;
										var drawnDa=data[i].drawnDa;
										var drawnCommutation=data[i].drawnCommutation;
										var drawTotal=data[i].drawTotal;
										var diffTotal=total-drawTotal;
										
									
										 totalDrawbasic =Number(drawBasic)  +  Number(totalDrawbasic) ;
									      totalDrawda =Number(data[i].drawnDa)  +  Number(totalDrawda) ;
										 totalDrawcomm =Number(data[i].drawnCommutation)  +  Number(totalDrawcomm) ;
										 totalDraw = Number(data[i].drawTotal) +  Number(totalDraw) ;
										
										 diffTotal1=diffTotal1+diffTotal ;
										 
										$(".due").append("<tr>" + 
											       // "<td>"+srNo+"</td>" +
										        "<td>"+1+"</td>" +
										        "<td>"+fromdate+" to "+todate+"</td>" +
										        "<td>"+daRate+"</td>" +
										        "<td>"+nanTozero(basic)+"</td>" +
										        "<td>"+nanTozero(daamt)+"</td>" +
										        "<td>"+nanTozero(commutation)+"</td>" +
										        "<td>"+nanTozero(total)+"</td>" +
										        "<td>"+nanTozero(drawnDaRate)+"</td>" +
										        "<td>"+nanTozero(drawBasic)+"</td>" +
										        "<td>"+nanTozero(drawnDa)+"</td>" +
										        "<td>"+nanTozero(drawnCommutation)+"</td>" +
										        "<td>"+nanTozero(drawTotal)+"</td>" +
										        "<td>"+nanTozero((Number(total)-Number(drawTotal)))+"</td>" +
										        "</tr>"); 
									}
									
									
									  var fromDate = new Date(data[0].fromDate);
									    var toDate = new Date(data[data.length - 1].toDate);

									    var options = { day: 'numeric', month: 'long', year: 'numeric' };
									    var sd = new Intl.DateTimeFormat('en-US', options);

									    var status = sd.format(fromDate) + "  to  \t" + sd.format(toDate);
									
									    $("#heading").show();
									    
									    $("#duration").val("Statement Showing the Payment during "+status)
									    
									
									
									$(".totalDiv").append(
											"<th>Total</th>" +
											"<th></th>" +
											"<th></th>" +
											"<th>"+nanTozero(totalbasic)+"</th>" +
											"<th>"+nanTozero(totalda)+"</th>" +
											"<th>"+nanTozero(totalcomm)+"</th>" +
											"<th>"+nanTozero(totaldue)+"</th>" +
											"<th></th>" +
											"<th>"+nanTozero(totalDrawbasic)+"</th>" +
											"<th>"+nanTozero(totalDrawda)+"</th>" +
											"<th>"+nanTozero(totalDrawcomm)+"</th>" +
											"<th>"+nanTozero(totalDraw)+"</th>" +
											"<th>"+(Number(totaldue)-Number(totalDraw))+"</th>"
									);

									if (data.length == 0) {
										swal("No Employee Found");
										$("#tblshowdiffdue").hide();
									}
								}
							});

								$('.stretbmain, .table-responsive').show();
								
								
								$('#txtEmployeeName').val(empNam);
								$('#txtPPOno').val(ppoNo);

								document.getElementById("searchDiv").innerHTML = "";
								$("#searchDiv").hide();
						});


	
	
});
