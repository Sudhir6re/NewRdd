$(document).ready(function(){


	var status = false;
	function SearchEmployee(e) {
		
		$('#action').val('SEARCH_EMP');
		
		var searchName = document.getElementById("txtSearchName").value;
		var yearId = document.getElementById("paybillYear").value;
		var monthId = document.getElementById("paybillMonth").value;
		
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
			if(flag){
				$(".arrTblBody").empty();
				$("#PensBrokenPeriod").submit();   
				 $(".BrokenPeriodHiddenData").show();
				 $("#vo").dataTable();
				return true;
			}
		
	}

	
	$("#txtDcrgSanctionedAmt").blur(function (){
		$("#txtDcrgNetAmt").val($("#txtDcrgSanctionedAmt").val());
	});
	
	
	
	$("#daArrearTable").on("change", ".daRate", function() {
		  var row = $(this).closest("tr");
		  var daRate =row.find(".daRate").val();
		  var pensionBasic =row.find(".pensionBasic").val();
		  var fromDate =row.find(".fromDate").val();
		  var toDate =row.find(".toDate").val();
		  
			var frDate = new Date(fromDate);
			var fromDay = frDate.getDate();
			var tDate = new Date(toDate);
			var toDay = tDate.getDate();
			var noOfDays1 = Number(toDay) - Number(fromDay) + 1 ;
			var totalNoOfDays =  new Date(frDate.getFullYear(), frDate.getMonth() + 1, 0);
		  
			var daAmt=((Number(pensionBasic)/100)*Number(daRate));
			
			var total=Number(pensionBasic)+Number(daAmt);
			
			row.find('.da').val(Math.round(daAmt));
			row.find('.total').val(Math.round(total));
		  
			calcTotal();
		});
	
	
	
	$("#daArrearTable").on("change", ".pensionBasic", function() {
		  var row = $(this).closest("tr");
		
		  var daRate =row.find(".daRate").val();
		  var pensionBasic =row.find(".pensionBasic").val();
		  var fromDate =row.find(".fromDate").val();
		  var toDate =row.find(".toDate").val();
		  
			var frDate = new Date(fromDate);
			var fromDay = frDate.getDate();
			var tDate = new Date(toDate);
			var toDay = tDate.getDate();
			var noOfDays1 = Number(toDay) - Number(fromDay) + 1 ;
			var totalNoOfDays =  new Date(frDate.getFullYear(), frDate.getMonth() + 1, 0);
		  
			var daAmt=((Number(pensionBasic)/100)*Number(daRate));
			
			var total=Number(pensionBasic)+Number(daAmt);
			
			row.find('.da').val(Math.round(daAmt));
			row.find('.total').val(Math.round(total));
		  
			calcTotal();
		});
	
	
	
	
	
	
	function calcTotal(){
		 var totalDays = 0;
		 var totalBasic = 0;
		var totalDa = 0;
		var totalArr = 0;
	
		
		
		  $(".noOfDays").each(function(){
			  totalDays += parseFloat($(this).val());
			  });
		  
		  
		  $(".pensionBasic").each(function(){
			  totalBasic += parseFloat($(this).val());
			  });
		  
		  $(".da").each(function(){
			  totalDa += parseFloat($(this).val());
			  });
		  
		  
		  $(".total").each(function(){
			  totalArr += parseFloat($(this).val());
		  });
		  
		  
		/*
		 * if(isNaN(totalDays) || isNaN(totalBasic) || isNaN(totalDa) ||
		 * isNaN(totalArr) ){ calcTotal(); }
		 */
		  
		  
		  $('#totalDays').text(Math.round(totalDays));
		  $('#totalBasic').text(Math.round(totalBasic));
	      $('#totalDa').text(Math.round(totalDa));		  
		  $("#otherArr").text(Number(Math.round(totalArr))); 
	
		}
	
	
	
	
$("#btnAddRow1").click(function(){
		var index=$('.arrTblBody >tr').length;
		var srNo=$('arrTblBody>tr').length+1;

		
		
		var fromDate='<input type="date" class="form-control removeErrorFromDate fromDate" id="fromDate'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].fromDate"   onBlur="checkDateForThisMonth(this,'+index+')"     />';
		var toDate='<input type="date" class="form-control removeErrorFromDate toDate" id="toDate'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].toDate"   onBlur="checkDateForThisMonth(this,'+index+');checkSameMonth(this,'+index+')"     />';
		var noOfDays='<input type="text" class="form-control removeErrorFromInput noOfDays" id="noOfDays'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].noOfDays"       />';
		var pensionBasic='<input type="text" class="form-control removeErrorFromInput pensionBasic" id="pensionBasic'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].pensionBasic"       />';
		var daRate='<input type="text" class="form-control removeErrorFromInput daRate" id="daRate'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].daRate"       />';
		var da='<input type="text" class="form-control removeErrorFromInput da" id="da'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].da"        />';
		var total='<input type="text" class="form-control removeErrorFromInput total" id="total'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].total"     />';
		var remarks='<input type="text" class="form-control removeErrorFromInput remarks" id="remarks'+index+'"  name="lstPensDaArrearPayMstModel[' + index + '].remarks"       />';
		
		var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="daArrearTable" class="delete daArrearTable"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl"   data-className="BasicPnsnDtl"  data-totalRow="'+index+'"></span></a>';
		
		$(".arrTblBody").append("<tr class='datatableheader'>" + 
		       // "<td>"+srNo+"</td>" +
		        "<td>"+fromDate+"</td>" +
		        "<td>"+toDate+"</td>" +
		        "<td>"+noOfDays+"</td>" +
		        "<td>"+pensionBasic+"</td>" +
		        "<td>"+daRate+"</td>" +
		        "<td>"+da+"</td>" +
		        "<td>"+total+"</td>" +
		        "<td>"+remarks+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");
	});
	
/*$(document).on('click', '.delete', function(){ 
	 //var rowlen=Number($(this).attr("data-totalRow"))+1;
	
	 var tableName=$(this).attr("data-className");
	 var rowlen=$('.arrTblBody >tr').length;
	 var currRow=$(this).closest("tr")[0].rowIndex;  
	 
	
	//if(Number(currRow)<Number(rowlen)){
		
		if(false){	
		swal({
			  title: "Warning!",
			  text: "Delete last row first!",
			  icon: "warning",
			});
	}else{
		swal({
			  title: "Are you sure?",
			  text: "to delete this row",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  
				  
				  $(this).closest("tr").remove();
				  calcTotal();
				
			  } else {
			  }
			});
		
	}
});*/
	
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
		
			row.find('.noOfDays').val(daysDiff);
			
			
			
			var noOfDays=daysDiff;
			var ppoNo=$("#PpoNo").val();
			var context=$("#context").val();
			
			 fromDate = row.find('.fromDate').val();
			 toDate = row.find('.toDate').val();
			
			 
			 var remark=dateToDMY(fromDate)+" To "+dateToDMY(toDate)+" "+noOfDays+" Days";
			 
			 
			 row.find('.remarks').val(remark);
			 
				$.ajax({
					type : "GET",
					url : context+"/pension/calculateDaArrear/"
								+fromDate+"/"+toDate+"/"+ppoNo+"/"+noOfDays,
					async : true,
					contentType : 'application/json',
					error : function(data) {
						 console.log(data);
					},
					success : function(data) {
						 console.log(data);
						var len = data.length;
						if (len != 0) { 
		                for(var i=0;i<len;i++){
		        			row.find('.pensionBasic').val(data[i].pensionBasic);
		        			row.find('.da').val(data[i].da);
		        			row.find('.daRate').val(data[i].daRate);
		        			row.find('.total').val(data[i].total);
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
					url : context+"/pension/calculateArrearByRange/"
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
								var noOfDaysVal=data[i].noOfDays;
								var pensionBasicVal=data[i].pensionBasic;
								var daRateVal=data[i].daRate;
								var daVal=data[i].da;
								var totalVal=data[i].total;
								
								
								
								
								
								var remark=dateToDMY(fromDateVal)+" To "+dateToDMY(toDateVal)+" "+noOfDaysVal+" Days";
									
								
								
								var pensDaArrPayMstEntityId='<input type="hidden"  id="pensDaArrPayMstEntityId'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].pensDaArrPayMstEntityId"       />';
								var fromDatecol='<input type="date" value="'+dateToYMD(fromDateVal)+'"   class="form-control removeErrorFromDate fromDate"    id="fromDate'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].fromDate"       />';
								var toDateCol='<input type="date"  value="'+dateToYMD(toDateVal)+'"  class="form-control removeErrorFromDate toDate"    id="toDate'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].toDate"       />';
								var noOfDays='<input type="text" value="'+noOfDaysVal+'" class="form-control float removeErrorFromInput noOfDays" id="noOfDays'+index+'"     name="lstPensDaArrearPayMstModel[' + index + '].noOfDays"       />';
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
								        "</tr>");
							
							}				
						}
						setTimeout(calcTotal, 2000);
					}
				});
				
				
			}
		}
	});

	$("#btnGenTextRpt").click(function(e){
		var contextPath=$("#context").val();
		$("#pensionBillForm").attr("action",contextPath+"/pension/generateTextReport");
		e.preventDefault();
	});
	
	$("#btnGenTextRpt").click(function(e){
		var contextPath=$("#context").val();
		$("#pensionBillForm").attr("action",contextPath+"/pension/generateTextReport");
		e.preventDefault();
	});
	
	
	
	
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
	var year = document.getElementById('paybillYear').options[document.getElementById('paybillYear').selectedIndex].text.trim().substring(0,4);
	var nextYear = Number(year) + 1;
	var month = Number(document.getElementById("paybillMonth").value.trim());
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
function checkFutureDate(dateField,counter){
	var fromDate=dateField.value.trim() ;
// var fromDate=dateField.value ;
	var selYear = document.getElementById('paybillYear').options[document.getElementById('paybillYear').selectedIndex].text.trim();
	var month = Number(document.getElementById("paybillMonth").value.trim());
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


$("#PensBrokenPeriod").submit(function(e){
	var flag = 0;
	var daArrearTable = $(".arrTblBody>tr").length;
	for(var i=0 ; i<= daArrearTable; i++)
		{
			var fromDate =  $("#fromDate"+i).val();
			var toDate =  $("#toDate"+i).val();
			var noOfDays =  $("#noOfDays"+i).val();
			var daRate =  $("#daRate"+i).val();
			var da =  $("#da"+i).val();
			var pensionBasic =  $("#pensionBasic"+i).val();
			var total =  $("#total"+i).val();
			var remarks =  $("#remarks"+i).val();
			
			if(fromDate==''){
				addErrorClass($("#fromDate"+i),"Please Enter From Date");
				flag=1;
			}else{
				removeErrorClass($("#fromDate"+i));
			}
			if(toDate==''){
				addErrorClass($("#toDate"+i),"Please enter To Date");
				flag=1;
			}else{
				removeErrorClass($("#toDate"+i));
			}
			if(noOfDays=='0' || noOfDays==''){
				addErrorClass($("#noOfDays"+i),"Please enter No of days");
				flag=1;
			}else{
				removeErrorClass($("#noOfDays"+i));
			}
			if(daRate==''){
				addErrorClass($("#daRate"+i),"Please enter DA Rate");
				flag=1;
			}else{
				removeErrorClass($("#daRate"+i));
			}
			if(da==''){
				addErrorClass($("#da"+i),"Please enter DA Amount");
				flag=1;
			}else{
				removeErrorClass($("#da"+i));
			}
			if(pensionBasic==''){
				addErrorClass($("#pensionBasic"+i),"Please enter Basic Pension Amount");
				flag=1;
			}else{
				removeErrorClass($("#pensionBasic"+i));
			}
			if(total==''){
				addErrorClass($("#total"+i),"Please enter Total Amount");
				flag=1;
			}else{
				removeErrorClass($("#total"+i));
			}
			if(remarks==''){
				addErrorClass($("#remarks"+i),"Please enter Remark");
				flag=1;
			}else{
				removeErrorClass($("#remarks"+i));
			}
		}
	
	if(flag==1){
		e.preventDefault();
	}
	
});





$(".DeleteRowBrokenPrd").click(function(){
//	var pensDaArrPayMstEntityId=$("#pensDaArrPayMstEntityId").val();
	var pensDaArrPayMstEntityId=$(this).attr("data");
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
					url : context+"/pension/deleteDaArrerDetail/"
								+pensDaArrPayMstEntityId,
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