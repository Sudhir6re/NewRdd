var status = false;
function SearchEmployee(e) {
	// alert('inside validateUIDUniqe');
	$('#action').val('SEARCH_EMP');
	//document.getElementById("BrokenPeriod").submit();
	
	var searchName = document.getElementById("txtSearchName").value;
	var yearId = document.getElementById("yearName").value;
	var monthId = document.getElementById("monthName").value;
	
	if(searchName == "")
	{
		swal('Please enter Sevaarth ID');
		return false;
	}
	if(yearId == 0)
	{
		swal('Please enter pay year');
		return false;
	}
	if(monthId == 0)
	{
		swal('Please enter pay month');
		return false;
	}
	var flag=true;
		$.ajax({
			type : "GET",
			url : "../master/isEmpMappedWithBillGroup/"+searchName,
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
			 console.log(data);
			// alert("erro");
			},
			success : function(data) {
//				alert("A="+data);
				if(data=="0"){
					flag=false;
					 swal("Employee not mapped with  Bill Broup");
					 return false;
//					 $("#brokenSearchbtn").prop("disabled",true);
				}
//				dataFromServer=data;
			}
	    });
		var sevaarthid=searchName;
		$.ajax({
			type : "GET",
			url : "../master/getSevaarthIdMappedWithPaybill/"+sevaarthid+"/"+monthId+"/"+yearId,
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
			 console.log(data);
			},
			success : function(data) {
				/*if(data==1){
					flag=false;
					 swal("Paybill is already generated for this sevaarth id");
					 return false;
//					 $("#brokenSearchbtn").prop("disabled",true);
				}
				else*/ 
				if(data==2){
					flag=false;
					swal("Paybill is inprogress for this sevaarth id");
					 return false;	
				}
				
			}
	    });
	
		if(flag){
			$("#BrokenPeriod").submit();   
			
			 $(".BrokenPeriodHiddenData").show();
			
			 
			 $("#vo").dataTable();
			 
		//	e.preventDefault();
			return true;
		}
	
}



function setNoOfDays(counter)
{
	var fromDate = document.getElementById("txtFromDate"+counter).value;
	var toDate = document.getElementById("txtToDate"+counter).value;
	
	if(fromDate != "" && toDate != "")
	{
		if(fromDate>toDate)
		{
			swal('Select date greater than From Date');
			document.getElementById("txtFromDate"+counter).value="";
			document.getElementById("txtToDate"+counter).value="";
			document.getElementById("txtNoOfDays"+counter).value="";
		}
	//	var fromDay = fromDate.substring(0,2);
		var frDate = new Date(fromDate);
		var fromDay = frDate.getDate();
	//	var toDay = toDate.substring(0,2);
		var tDate = new Date(toDate);
		var toDay = tDate.getDate();
		var daysDiff = Number(toDay) - Number(fromDay) + 1 ;
		document.getElementById("txtNoOfDays"+counter).value = daysDiff ;
	}
}

function loadSalaryFromRuleEngine(rownumber) {
	var url = "";
	var toDate=document.getElementById("txtToDate"+rownumber).value;
	var fromDate=document.getElementById("txtFromDate"+rownumber).value;
	var paybillYear=  document.getElementById("yearName").value;
	var paybillMonth=  document.getElementById("monthName").value;
	if(toDate!="" && fromDate!=""){
		var toDateArray = new Date(toDate);
		var month = toDateArray.getMonth();
		var year=toDateArray.getFullYear();
		var noOfDays=document.getElementById("txtNoOfDays"+rownumber).value;
		var empId = document.getElementById("txtEmployeeId").value;
		var sevaarthid = document.getElementById("txtSevarthId").value;
		$
		.ajax({
			type : "GET",
			url : "calculateEmployeeSalary/" + sevaarthid + "/"+empId+"/"+month+"/"+year+"/"+noOfDays+"/"+paybillMonth+"/"+paybillYear+"/"+fromDate+"/"+toDate,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				swal("Salary not calculated from Rule engine becuase of some error");
			},
			success : function(data) {
				if(data[3].data == 0){
			    document.getElementById("txtBasicPay"+String(rownumber)).value=data[2].data;
			    var totalLengthOfAllowances =data[0].data.length;
				var totalLengthOfDeductions =data[1].data.length;
				 document.getElementById("hidTotalAllowances").value=totalLengthOfAllowances;
				 document.getElementById("hidTotalDeductions").value=totalLengthOfDeductions;
				if (data[0].status == 'allowRuleList') {

					$.each(data[0].data,
							function(i, allowRuleList) {
						document.getElementById("allowances"+(Number(i)+Number(1))+String(rownumber)).value=Math.round(allowRuleList.deptalldetValue);
						document.getElementById("allowancescode"+(Number(i)+Number(1))+String(rownumber)).value=Math.round(allowRuleList.deptallowdeducid);
							});

				}
				
				if (data[1].status == 'dedRuleList') {

					$.each(data[1].data,
							function(i, dedRuleList) {
							document.getElementById("dedections"+(Number(i)+Number(1))+String(rownumber)).value=Math.round(dedRuleList.deptalldetValue);
							document.getElementById("dedectionscode"+(Number(i)+Number(1))+String(rownumber)).value=Math.round(dedRuleList.deptallowdeducid);
							});

				}
				
				calcGrossAmount(rownumber);
				calcTotalDeduction(rownumber);
				calcNetPay(rownumber);
				
				}else{
					document.getElementById("txtNoOfDays"+rownumber).value="";
					swal("Selected Month already Exist");
					document.getElementById("txtFromDate"+rownumber).value="";
					document.getElementById("txtToDate"+rownumber).value="";
				}
				
			}
			});
	}
	}

//Net Pay
function calcNetPay(counter)
{
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	var basicPay = document.getElementById("txtBasicPay"+counter).value;
	var totalValueOfAllowances = 0;
	var totalValueOfDeductions = 0;
	
	for(var lInt=1;lInt<=totalAllowances;lInt++)
	{
			if(document.getElementById("allowances"+lInt+counter).value != "")
			{
				totalValueOfAllowances = Number(totalValueOfAllowances) + Number(document.getElementById("allowances"+lInt+counter).value.trim());
			}
	}
	
	for(lInt=1;lInt<=totalDeductions;lInt++)
	{
			if(document.getElementById("dedections"+lInt+counter).value != "")
			{
				totalValueOfDeductions = Number(totalValueOfDeductions) + Number(document.getElementById("dedections"+lInt+counter).value.trim());
			}
	}
	var netPay = Number(basicPay) + Number(totalValueOfAllowances) - Number(totalValueOfDeductions);
	document.getElementById("txtNetPay"+counter).value = netPay;
}

//Gross Amount
function calcGrossAmount(counter)
{
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
//	alert("totalAllowances="+totalAllowances);
	if(document.getElementById("txtBasicPay"+counter)==undefined){
		return false;
	} 
	if(document.getElementById("txtBasicPay"+counter).value==null){
		return false;
	}
	var basicPay = document.getElementById("txtBasicPay"+counter).value;
	var totalValueOfAllowances = 0;
	
	
	for(var lInt=1;lInt<=totalAllowances;lInt++)
	{
			if(document.getElementById("allowances"+lInt+counter).value != "")
			{
				totalValueOfAllowances = Number(totalValueOfAllowances) + Number(document.getElementById("allowances"+lInt+counter).value.trim());
			}
	}
	var GrossAmount = Number(basicPay) + Number(totalValueOfAllowances);
	document.getElementById("txtGrossAmt"+counter).value = GrossAmount;
}
//Total Deduction
function calcTotalDeduction(counter)
{
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	var totalValueOfDeductions = 0;
	for(lInt=1;lInt<=totalDeductions;lInt++)
	{
			if(document.getElementById("dedections"+lInt+counter).value != "")
			{
				totalValueOfDeductions = Number(totalValueOfDeductions) + Number(document.getElementById("dedections"+lInt+counter).value.trim());
			}
	}
	var TotalDeduction = Number(totalValueOfDeductions);
	document.getElementById("txtTotalDeduction"+counter).value =TotalDeduction;
}
$("#btnAddRow").on("click",function(){
	var table=document.getElementById("vo");
	var nextRow= Number (document.getElementById("hidTotalRows").value) + 1 ;
	
	var newRow = table.insertRow(-1);
	color2 = "rgb(255, 218, 178)";
	newRow.style.borderColor = color2;
	
	var Cell1 = newRow.insertCell(-1);
	Cell1.style.border ="1px solid rgb(255, 218, 178)";
	Cell1.innerHTML = '<input type="date" size="10" name="txtFromDate"  class="form-control" id="txtFromDate'+nextRow+'" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 	onBlur="checkDateForThisMonth(this,'+nextRow+');setNoOfDays('+nextRow+')" value="" />' ;
//			+ '&nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick="window_open(\'txtFromDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>' ;
	//Cell1.innerHTML = Cell1.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForFromDate'+ nextRow +'" >*</label>' ;
	
	var Cell2 = newRow.insertCell(-1);
	Cell2.style.border ="1px solid rgb(255, 218, 178)";
	Cell2.innerHTML = '<input type="date" size="10" name="txtToDate" class="form-control"  id="txtToDate'+nextRow+'" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" 	onBlur="checkDateForThisMonth(this,'+nextRow+');checkSameMonth(this,'+nextRow+');setNoOfDays('+nextRow+');loadSalaryFromRuleEngine('+nextRow+')" value="" />' ;
	//+ '&nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick="window_open(\'txtToDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>' ;
	//Cell2.innerHTML = Cell2.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForToDate'+ nextRow +'" >*</label>' ;
	
	var Cell3 = newRow.insertCell(-1);
	Cell3.style.border ="1px solid rgb(255, 218, 178)";
	Cell3.innerHTML = '<input type="text" size="8" name="txtNoOfDays" id="txtNoOfDays'+nextRow+'"  class="form-control"  value="" readonly="readonly" />';
	//Cell3.innerHTML = Cell3.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForNoOfDays'+ nextRow +'" >*</label>';
	
	var Cell4 = newRow.insertCell(-1);
	Cell4.style.border ="1px solid rgb(255, 218, 178)";
	Cell4.innerHTML = '<input type="text" size="8" name="txtBasicPay" id="txtBasicPay'+nextRow+'" class="form-control"  value="" onkeypress="digitFormat(this);" onblur="calcGrossAmount('+nextRow+');calcNetPay('+nextRow+');" readonly  />';
	//Cell4.innerHTML = Cell4.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForBasicPay'+ nextRow +'" >*</label>';
	
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	
	var ArrayAllowancesFromJSP = document.getElementsByName("allowancescode");
	var ArrayAllowancesNamesFromJSP = document.getElementsByName("hidAllowanceName");
	var nextlInt=0 ;
	
	for(var lInt=0;lInt < totalAllowances;lInt++ )
	{
		nextlInt = nextlInt + 1;
		var CellForAllowance = newRow.insertCell(-1);

		if( Number(ArrayAllowancesFromJSP[lInt].value)==Number('77')  || Number(ArrayAllowancesFromJSP[lInt].value)==Number('78') || Number(ArrayAllowancesFromJSP[lInt].value)==Number('75') || Number(ArrayAllowancesFromJSP[lInt].value)==Number('76') || Number(ArrayAllowancesFromJSP[lInt].value)==Number('36')||Number(ArrayAllowancesFromJSP[lInt].value)==Number('72')){
		CellForAllowance.style.border ="1px solid rgb(255, 218, 178)";
		CellForAllowance.innerHTML = '<input type="text" class="form-control"  id="allowances'+ nextlInt + "" + nextRow+'" size="5" onkeypress="digitFormat(this);" onblur="checkServcExpDate('+nextRow+',this);calcGrossAmount('+nextRow+');calcNetPay('+nextRow+');" readonly/>' +
									 '<input type="hidden" class="form-control"  name="allowancescode" id="allowancescode'+ nextlInt + nextRow+'" value="'+ArrayAllowancesFromJSP[lInt].value +'"/>' + 
									 '<input type="hidden" class="form-control" name="hidAllowanceName" id="hidAllowanceName'+ nextlInt + nextRow+'" value="'+ArrayAllowancesNamesFromJSP[lInt].value+'" />' ;
		}
		else
		{
			CellForAllowance.style.border ="1px solid rgb(255, 218, 178)";
			CellForAllowance.innerHTML = '<input type="text" class="form-control"  id="allowances'+ nextlInt + "" + nextRow+'" size="5" onkeypress="digitFormat(this);" onblur="calcGrossAmount('+nextRow+');calcNetPay('+nextRow+');" />' +
										 '<input type="hidden" class="form-control"  name="allowancescode" id="allowancescode'+ nextlInt + nextRow+'" value="'+ArrayAllowancesFromJSP[lInt].value +'"/>' + 
										 '<input type="hidden" class="form-control" name="hidAllowanceName" id="hidAllowanceName'+ nextlInt + nextRow+'" value="'+ArrayAllowancesNamesFromJSP[lInt].value+'" />' ;
			
		}
		//	CellForAllowance.innerHTML = CellForAllowance.innerHTML  + '&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator" id="labelForAllowance'+ nextlInt + nextRow+'" >*</label>';
		
	}
	
	var Cell5 = newRow.insertCell(-1); 
	Cell5.style.border ="1px solid rgb(255, 218, 178)";
	Cell5.innerHTML = '<input type="text" class="form-control" size="8" name="txtGrossAmt" id="txtGrossAmt'+nextRow+'" value="" readonly="readonly" />' ;
	//Cell5.innerHTML =  Cell5.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForGrossAmt'+ nextRow +'" >*</label>';
	
	var ArrayDeductionFromJSP = document.getElementsByName("dedectionscode");
	var ArrayDeductionNamesFromJSP =  document.getElementsByName("hidDeductionName");
	nextlInt=0 ;
	
	for(lInt=0;lInt < totalDeductions;lInt++ )
	{
		nextlInt = nextlInt + 1;
		var CellForDeduction = newRow.insertCell(-1);
		CellForDeduction.style.border ="1px solid rgb(255, 218, 178)";
		CellForDeduction.innerHTML = '<input type="text" class="form-control" id="dedections'+ nextlInt + "" + nextRow+'" size="5" onkeypress="digitFormat(this);" onblur="calcTotalDeduction('+nextRow+');calcNetPay('+nextRow+');" />' +
									 '<input type="hidden" name="dedectionscode" id="dedectionscode'+ nextlInt + nextRow+'" value="'+ArrayDeductionFromJSP[lInt].value +'"/>' +
									 '<input type="hidden" name="hidDeductionName" id="hidDeductionName'+ nextlInt + nextRow+'" value="'+ArrayDeductionNamesFromJSP[lInt].value+'" />' ;
		//CellForDeduction.innerHTML = CellForDeduction.innerHTML + '&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator" id="labelForDeduction'+ nextlInt + nextRow+'" >*</label>';
		
	}
	var Cell6 = newRow.insertCell(-1); 
	Cell6.style.border ="1px solid rgb(255, 218, 178)";
	Cell6.innerHTML = '<input type="text" size="8" class="form-control" name="txtTotalDeduction" id="txtTotalDeduction'+nextRow+'" value="" readonly="readonly" />' ;
	//Cell6.innerHTML =  Cell6.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForTotalDeduction'+ nextRow +'" >*</label>';
	
	var Cell7 = newRow.insertCell(-1); 
	Cell7.style.border ="1px solid rgb(255, 218, 178)";
	Cell7.innerHTML = '<input type="text" class="form-control" size="8" name="txtNetPay" id="txtNetPay'+nextRow+'" value="" readonly="readonly" />' ;
	//Cell7.innerHTML =  Cell7.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForNetPay'+ nextRow +'" >*</label>';
	
	var Cell8 = newRow.insertCell(-1); 
	Cell8.style.border ="1px solid rgb(255, 218, 178)";
	Cell8.width="190px";
	Cell8.innerHTML = '<select name="cmbReasonForBrokenPeriod" class="form-control" id="cmbReasonForBrokenPeriod'+nextRow+'"   >'
				 	+ '<option value="0">--Select--</option>' + LISTREASONSFORBROKENPERIOD +'</select>';
	//Cell8.innerHTML = Cell8.innerHTML + '&nbsp;<label class="mandatoryindicator" id="labelForReason'+nextRow+'" >*</label>';
	
	var Cell9 = newRow.insertCell(-1); 
	Cell9.style.border ="1px solid rgb(255, 218, 178)";
	Cell9.innerHTML = '<input type="text" name="txtRemarks" id="txtRemarks'+nextRow+'" class="form-control" />';
	//Cell9.innerHTML = Cell9.innerHTML  + '&nbsp;<label class="mandatoryindicator" id="labelForRemarksForOtherReason'+nextRow+'" style="display:none" >*</label>';
	
	var Cell10 = newRow.insertCell(-1);
	Cell10.style.border ="1px solid rgb(255, 218, 178)";
	var tablerow="tr";
	Cell10.innerHTML = '<a  onClick="deleteRow(this);"  style="CURSOR: pointer" ><LABEL id=DeleteRowBrokenPrd><span class="glyphicon glyphicon-trash"></span></LABEL></a>';
	//Cell10.innerHTML = '<a href=# onclick="DeleteRowBrokenPrd('+nextRow+');"> <label id="DeleteRowBrokenPrd">Delete</label></a>';

//	<A onClick="$(this).closest('tr').remove();" style="CURSOR: pointer" ><LABEL id=DeleteRowBrokenPrd><span class="glyphicon glyphicon-trash"></span></LABEL></A>
	
	document.getElementById("hidTotalRows").value = Number (document.getElementById("hidTotalRows").value) + 1 ;
});

//function DeleteRowBrokenPrd(counter)
//{
//	if(counter == 1)
//	{
//		alert('You cannot delete the first row.');
//		return ;
//	}
//	var answer = confirm('Are you sure you want to delete the row?');
//	if (answer)
//	{
//	var current = window.event.srcElement;
//    while ( (current = current.parentElement)  && current.tagName !="TR");
//         current.parentElement.removeChild(current);
//    
//    document.getElementById("hidTotalRows").value = Number(document.getElementById("hidTotalRows").value) - 1 ;
//	}
//}
function removeRow(r)
{
  var noOfRows = $('#vo tr').length;
  if(noOfRows==2)
	{
swal('You cannot delete the first row.');
	}
		if (noOfRows > 2) {
			  var i = r.parentNode.parentNode.rowIndex;
			  document.getElementById("vo").deleteRow(i);
			  document.getElementById("hidTotalRows").value = Number(document.getElementById("hidTotalRows").value) - 1 ;
		}
		
}

function deleteRow(r) {
	  var i = r.parentNode.parentNode.rowIndex;
	  document.getElementById("vo").deleteRow(i);
	}
	//} command by mani
//End :Add Broken  Period

//Start:Save broken period value
function saveBrokenPrdData()
{
	if(!validateSaveForBrokenPeriodPay())
	{
		return false;
	}
	
	var totalRows = document.getElementById("hidTotalRows").value;
//	var totalRows = 1;
	var paybillYear = document.getElementById("yearName").value;
	var paybillMonth = document.getElementById("monthName").value;
	var empId = document.getElementById("txtEmployeeId").value;
	var sevaarthid = document.getElementById("txtSevarthId").value;
	var fromDates = "";
	var toDates = "";
	var noOfDays = "";
	var basicPays = "";
	var netPays = "";
	var reasons = "";
	var remarks = "";
	
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	var allowancesCodes = "";
	var allowancesValues = "";
	var deductionCodes = "";
	var deductionValues = "";
	
	for(var counter=1;counter<=totalRows;counter++)
	{
		fromDates = fromDates + document.getElementById("txtFromDate"+counter).value + "~";
		toDates = toDates + document.getElementById("txtToDate"+counter).value + "~";
		noOfDays = noOfDays + document.getElementById("txtNoOfDays"+counter).value + "~";
		basicPays = basicPays + document.getElementById("txtBasicPay"+counter).value + "~";
		netPays = netPays + document.getElementById("txtNetPay"+counter).value + "~";
		reasons = reasons + document.getElementById("cmbReasonForBrokenPeriod"+counter).value + "~";
		remarks = remarks + document.getElementById("txtRemarks"+counter).value + "~";
		
		//getting allowances for the employee
		
		for(var lInt=1;lInt<=totalAllowances;lInt++)
		{
			if(lInt==totalAllowances)
			{
				allowancesCodes = allowancesCodes + document.getElementById("allowancescode"+lInt+counter).value + ":" + "~";
				allowancesValues = allowancesValues + document.getElementById("allowances"+lInt+counter).value + ":" + "~";
			}
			else
			{
				allowancesCodes = allowancesCodes + document.getElementById("allowancescode"+lInt+counter).value + ":";
				allowancesValues = allowancesValues + document.getElementById("allowances"+lInt+counter).value + ":";
			}
		}
		
		//getting deductions for the employee
		
		for(lInt=1;lInt<=totalDeductions;lInt++)
		{
			if(lInt==totalDeductions)
			{
				deductionCodes = deductionCodes + document.getElementById("dedectionscode"+lInt+counter).value + ":" + "~";
				deductionValues = deductionValues + document.getElementById("dedections"+lInt+counter).value + ":" + "~";
			}
			else
			{
				deductionCodes = deductionCodes + document.getElementById("dedectionscode"+lInt+counter).value + ":";
				deductionValues = deductionValues + document.getElementById("dedections"+lInt+counter).value + ":";
			}
		}
	}

	
	$
	.ajax({
		type : "GET",
		url : "saveBrokenPeriodPay/" + sevaarthid + "/"+empId+"/"+fromDates+"/"+toDates+"/"+noOfDays+"/"+paybillMonth+"/"+paybillYear+"/"+basicPays+"/"+allowancesCodes+"/"+allowancesValues+"/"+deductionCodes+"/"+deductionValues+"/"+netPays+"/"+reasons+"/"+remarks,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			alert("Something went wrong...");
		},
		success : function(data) {
//			var len = data.length;
		swal("Broken Period Pays are saved for the employee.");    
		 
		 location.reload();
		   
		}
		});
}


function loadNetPay() {
	var noOfRows = $("#BrokenPeriodPayListSize").val();

	if (noOfRows > 0) {
		for ( var i = 1; i <= noOfRows; i++) {
			calcGrossAmount(i);

		}
	}
}
function loadGrossAmount() {
	var noOfRows = $("#BrokenPeriodPayListSize").val();
	if (noOfRows > 0) {
		for ( var i = 1; i <= noOfRows; i++) {
			calcTotalDeduction(i);
		}
	}
}
function loadTotalDeduction() {
	var noOfRows = $("#BrokenPeriodPayListSize").val();
	if (noOfRows > 0) {
		for ( var i = 1; i <= noOfRows; i++) {
			calcNetPay(i);
		}
	}
}

function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	if(str=="" || str == "-1" || str=="0")
	{
		swal(msg +" Cannot be Empty.");
		ctrl.focus();		
		return false;
	}		
	else
		return true;
}


function validateSaveForBrokenPeriodPay()
{
	var totalRows = document.getElementById("hidTotalRows").value;
	var totalAllowances = document.getElementById("hidTotalAllowances").value;
	var totalDeductions = document.getElementById("hidTotalDeductions").value; 
	
	for(var k=1;k<=totalRows;k++)
	{
		if(!chkEmpty(document.getElementById("txtFromDate"+k),'From Date') || 
				!chkEmpty(document.getElementById("txtToDate"+k),'To Date') ||
				!chkEmpty(document.getElementById("txtBasicPay"+k),'Basic Pay') ||
				!chkEmpty(document.getElementById("txtNetPay"+k),'Net Pay') ||
				!chkEmpty(document.getElementById("cmbReasonForBrokenPeriod"+k),'Reason'))
		{
			return false;
		}
		
		if(document.getElementById("cmbReasonForBrokenPeriod"+k).value == '700336' && document.getElementById("txtRemarks"+k).value == "")
		{
			swal('Remarks must be entered for other reason');
			return false;
		}
		//Checks for allowances not empty
		for(var lInt=1;lInt<=totalAllowances;lInt++)
		{
				if(document.getElementById("allowances"+lInt+k).value == "")
				{
					var allowanceName =  document.getElementById("hidAllowanceName"+lInt+k).value ;
					swal('Please enter value for '+ allowanceName);
					return false;
				}
		}
		//Checks for deductions not empty
		for(lInt=1;lInt<=totalDeductions;lInt++)
		{
				if(document.getElementById("dedections"+lInt+k).value == "")
				{
					var deductionName =  document.getElementById("hidDeductionName"+lInt+k).value ;
					swal('Please enter value for '+ deductionName);
					return false;
				}
		}
		
	}
	return true;
}

function checkDateForThisMonth(dateField,counter)
{
	
//	alert("checkDateForThisMonth date method Executed");
	//alert('Inside checkDateForThisMonth');
	var retFlag=checkFutureDate(dateField,counter);
	
	//return retFlag;
	var dateValue = dateField.value.trim() ;
//	var dateValue = dateField.value ;
	if(dateValue == "")
	{
		return;
	}
	var year = document.getElementById('yearName').options[document.getElementById('yearName').selectedIndex].text.trim().substring(0,4);
	var nextYear = Number(year) + 1;
	var month = Number(document.getElementById("monthName").value.trim());
	//alert('month'+month);
	
	var yearFromDate =  dateValue.substring(6,10);
	var monthFromDate = Number(dateValue.substring(3,5));
	
	// Below code checks for overlapping period
	var one_day = 1000*60*60*24;
	str3 = document.getElementById("txtFromDate"+counter).value.trim(); 
	str4 = document.getElementById("txtToDate"+counter).value.trim(); 
	
	
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
			
		    var str1 = document.getElementById("txtFromDate"+i).value; 
		    var str2 = document.getElementById("txtToDate"+i).value; 
	
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

function checkSameMonth(toDateObj,counter){

	var fromDate=document.getElementById("txtFromDate"+counter).value;
	var toDate=toDateObj.value;
	var fromdateArray=fromDate.split("-");
	
	var todateArray=toDate.split("-");
	toDateMonth = toDate.substring(4,5);
	var d = new Date();
	var currMonth = d.getMonth();
	var currYear = d.getFullYear();
	
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

function checkFutureDate(dateField,counter){
	var fromDate=dateField.value.trim() ;
//	var fromDate=dateField.value ;
	var selYear = document.getElementById('yearName').options[document.getElementById('yearName').selectedIndex].text.trim();
	var month = Number(document.getElementById("monthName").value.trim());
	var fromdateArray=fromDate.split("-");
	var selYearArray=selYear.split("-");
	var year="";
	year=selYearArray[0];
	
	
	
	if(Number(fromdateArray[0])>Number(year)){
		swal("Please Select Year Lesser than the Selected Pay Year");
		dateField.value="";
		dateField.focus();
		return false;
	}
	if(Number(fromdateArray[0])==Number(year)){
		if(fromdateArray[1]>month)
		{
			swal("Please Select Month Lesser than the Selected Pay Month");
			dateField.value="";
			dateField.focus();
			return false;
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