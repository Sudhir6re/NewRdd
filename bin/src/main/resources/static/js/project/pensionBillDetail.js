$(document).ready(function(){

	
	
	$("#txtDcrgSanctionedAmt").blur(function (){
		$("#txtDcrgNetAmt").val($("#txtDcrgSanctionedAmt").val());
	});
	
	
	
	
	$("#txtPPONO").blur(function (){
		var ppoNo=$("#txtPPONO").val();
		var context=$("#context").val();
		  $.ajax({
			  type : "POST",
			  url : context+"/master/searchEmpByPPoNo/"+ppoNo,
			  async : true,
			  contentType : 'application/json',
			  error : function(data) {
				  console.log(data);
			  },
			  success : function(data) {
				  console.log(data);
				  if(data.empName!=null){
					    $("#txtName").val(data.empName);
					     $("#txtPPONO").val(data.ppoNo);
					     $("#bankBranchAddr").val(data.bankBranchAddr);
					     $("#accNo").val(data.accNo);
					     $("#ifscCode").val(data.ifscCode);
					     $('#bankId').val(data.bankId);
					     $('#totalPensionAmt').val(data.totalPensionAmt);
					     $('#bankId').select2().trigger('change'); 
					     $('#totalPensionAmt').val(data.totalPensionAmt);
					     $('#txtTIPercentAmt').val(data.txtTIPercentAmt);
				  }else{
					  swal("ppo number not found");
				  }
			  }
		  });
	});
	
	
	function calcTotal(){
		 var totalDays = 0;
		 var totalBasic = 0;
		var totalDa = 0;
		
		  $(".actualDays").each(function(){
			  totalDays += parseFloat($(this).val());
			  });
		  
		  
		  $(".txtOldBasic").each(function(){
			  totalBasic += parseFloat($(this).val());
			  });
		  
		  $(".daDueAmt").each(function(){
			  totalDa += parseFloat($(this).val());
			  });
		  
		  
		  $('#totalDays').text(totalDays);
		  $('#totalBasic').text(totalBasic);
		  $('#totalDa').text(totalDa);
		  
		  
		  
		  $("#otherArr").text(setTwoDecimalDigit(Number(totalBasic)+Number(totalDa))); 
		  
		  
		 var basicPension=Number($("#totalPensionAmt").val());  //basic
		 
		 var da=Number($("#txtTIPercentAmt").val());  //da
		  
		  
		  
		  $("#txtPensionBillAmt").val(setTwoDecimalDigit(Number(totalBasic)+Number(totalDa)+Number(basicPension)+Number(da)));  //gross
		  
		  
		  
		  $("#txtOthArrearAmt").val(setTwoDecimalDigit(totalBasic+totalDa));  //otherArr
		  
		  
		  $("#txtNetPensionAmt").val(setTwoDecimalDigit(totalBasic+totalDa+basicPension+da));  //net
		  
		  $("#txtNetAmtInword").text(price_in_words(setTwoDecimalDigit(Number($("#txtNetPensionAmt").val()))));
		}
	
	
$("#addArrDtl").click(function(){
		var index=$('#arrTbl >tbody >tr').length;
		var srNo=$('#arrTbl >tbody >tr').length+1;
		
		var actualDays='<input type="text" class="form-control removeErrorFromInput actualDays number" id="actualDays'+index+'"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].actualDays"     style="text-align: right"   />';
		var txtOldBasic1='<input type="text" class="form-control removeErrorFromInput txtOldBasic float" id="txtOldBasic'+index+'"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldBasic"    style="text-align: right"     />';
		var daDueAmt1='<input type="text" class="form-control removeErrorFromInput daDueAmt float" id="daDueAmt'+index+'"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].daDueAmt"    style="text-align: right"   />';
		var txtOldBasicEffFrom1='<input type="date" class="form-control removeErrorFromInput txtOldBasicEffFrom" id="txtOldBasicEffFrom'+index+'"   name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldBasicEffFrom"       />';
		var txtOldBasicEffTo1='<input type="date" class="form-control removeErrorFromInput txtOldBasicEffTo"  id="txtOldBasicEffTo'+index +'"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldBasicEffTo"     />';
	
		var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="tblNomineeDtls" class="delete tblNomineeDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl"    onClick="$(this).closest(\'tr\').remove();"  data-className="BasicPnsnDtl"  data-totalRow="'+index+'"></span></a>';
		
		$("#arrTbl>.cAtb2Tbody").append("<tr class='datatableheader'>" + 
		       // "<td>"+srNo+"</td>" +
		        "<td>"+txtOldBasicEffFrom1+"</td>" +
		        "<td>"+txtOldBasicEffTo1+"</td>" +
		        "<td>"+actualDays+"</td>" +
		        "<td>"+txtOldBasic1+"</td>" +
		        "<td>"+daDueAmt1+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");
		
		
		//$(".arrTbl").attr("data-totalRow",index);
	});
	
	
	
	
	
	
	$("#basicPnsnAddrow").click(function(){
		
		var index=$('#BasicPnsnDtl  >tbody >tr').length;
		var srNo=$('#BasicPnsnDtl  >tbody >tr').length+1;
		
		var cmbRevisionType='<select  id="lstPensReviseBasicDtlsTrnModel' + index + 'cmbRevisionType"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].cmbRevisionType"    class="form-control cmbRevisionType"><option value="-1">--Select--</option><option value="Old To Old">4PC - 4PC</option><option value="Old To Old">4PC - 4PC</option><option value="Old To New">4PC - 5PC</option><option value="New To New">5PC - 5PC</option><option value="New To 2006">5PC - 6PC</option><option value="2006 To 2006">6PC - 6PC</option><option value="2006 To 2016">6PC - 7PC</option><option value="2016 To 2016">7PC - 7PC</option><option value="2016 To 2026">7PC - 8PC</option><option value="2026 To 2026">8PC - 8PC</option><option value="2026 To 2036">8PC - 9PC</option><option value="2036 To 2036">9PC - 9PC</option><option value="2036 To 2046">9PC - 10PC</option><option value="2046 To 2046">10PC - 10PC</option></select>';
		
		
		var txtOldBasic1='<input type="text" class="form-control removeErrorFromInput txtOldBasic float" id="lstPensReviseBasicDtlsTrnModel' + index + 'txtOldBasic"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldBasic"       />';
		var txtNewBasic1='<input type="text" class="form-control removeErrorFromInput txtNewBasic float" id="lstPensReviseBasicDtlsTrnModel' + index + 'txtNewBasic"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtNewBasic"       />';
		var txtOldBasicEffFrom1='<input type="date" class="form-control removeErrorFromInput txtOldBasicEffFrom" id="lstPensReviseBasicDtlsTrnModel' + index + 'txtOldBasicEffFrom"   name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldBasicEffFrom"       />';
		var txtOldBasicEffTo1='<input type="date" class="form-control removeErrorFromInput txtOldBasicEffTo"  id="lstPensReviseBasicDtlsTrnModel' + index + 'txtOldBasicEffTo"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldBasicEffTo"     />';
	
		
		
		var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="tblNomineeDtls" class="delete tblNomineeDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl"    onClick="$(this).closest(\'tr\').remove();"  data-className="BasicPnsnDtl"  data-totalRow="'+index+'"></span></a>';
		
		$("#BasicPnsnDtl tbody").append("<tr>" + 
		        "<td>"+cmbRevisionType+"</td>" +
		        "<td>"+txtOldBasic1+"</td>" +
		        "<td>"+txtNewBasic1+"</td>" +
		        "<td>"+txtOldBasicEffFrom1+"</td>" +
		        "<td>"+txtOldBasicEffTo1+"</td>" +
		        "<td>"+deleteRow+"</td>" +
		        "</tr>");
		$(".BasicPnsnDtl").attr("data-totalRow",index);
	});
	
	
	
	$("#CVPAddrow").click(function(){
		
		var index=$('#tblCVPDtls  >tbody >tr').length;
		var srNo=$('#tblCVPDtls  >tbody >tr').length+1;
		
		var txtOldCVP='<input type="text" class="form-control removeErrorFromInput txtOldCVP float" id="lstPensCvpDtlTrnModel' + index + 'txtOldCVP"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldCVP"       />';
		var txtNewCVP='<input type="text" class="form-control removeErrorFromInput txtNewCVP float" id="lstPensCvpDtlTrnModel' + index + 'txtNewCVP"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtNewCVP"       />';
		var txtOldCVPEffFrom='<input type="date" class="form-control float removeErrorFromInput txtOldCVPEffFrom" id="lstPensCvpDtlTrnModel' + index + 'txtOldCVPEffFrom"   name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldCVPEffFrom"         />';
		var txtOldBasicEffTo='<input type="date" class="form-control removeErrorFromInput txtOldCVPEffTo"  id="lstPensCvpDtlTrnModel' + index + 'txtOldBasicEffTo"  name="lstPensReviseBasicDtlsTrnModel[' + index + '].txtOldCVPEffTo"     />';
		
		
		
		var deleteRow='<div  align="center" style="vertical-align: middle;"   data-className="tblNomineeDtls" class="delete tblNomineeDtls"  data-totalRow="'+index+'"   ><a><span class="glyphicon glyphicon-trash delete BasicPnsnDtl"   onClick="$(this).closest(\'tr\').remove();"   data-className="BasicPnsnDtl"  data-totalRow="'+index+'"></span></a>';
		
		$("#tblCVPDtls tbody").append("<tr  class='datatableheader'>" + 
				"<td>"+txtOldCVP+"</td>" +
				"<td>"+txtNewCVP+"</td>" +
				"<td>"+txtOldCVPEffFrom+"</td>" +
				"<td>"+txtOldBasicEffTo+"</td>" +
				"<td>"+deleteRow+"</td>" +
		"</tr>");
		$("#tblCVPDtls").attr("data-totalRow",index);
	});
	
	
	
	
	$("#arrTbl").on('blur',".txtOldBasicEffTo",function(){
		var row = $(this).closest("tr");
		var fromDate = row.find('.txtOldBasicEffFrom').val();
		var toDate = row.find('.txtOldBasicEffTo').val();
		
		var date1=new Date(fromDate);
		var date2=new Date(toDate);
		
		
		if(fromDate != "" && toDate != ""  &&  Date.parse(date1) &&  Date.parse(date2))
		{   
			
			if (date1.getMonth() != date2.getMonth() || date1.getFullYear() != date2.getFullYear()){
			swal("Please Select same month in a row");
		}else if(fromDate>toDate){
				swal('Select date greater than From Date');
				row.find('.txtOldBasicEffFrom').val("");
				row.find('.txtOldBasicEffTo').val("");
				row.find('.actualDays').val("");
		}else{
			var frDate = new Date(fromDate);
			var fromDay = frDate.getDate();
			var tDate = new Date(toDate);
			var toDay = tDate.getDate();
			var daysDiff = Number(toDay) - Number(fromDay) + 1 ;
		
			row.find('.actualDays').val(daysDiff);
			
			var ppoNo=$("#txtPPONO").val();
			var context=$("#context").val();
			
			 fromDate = row.find('.txtOldBasicEffFrom').val();
			 toDate = row.find('.txtOldBasicEffTo').val();
			
				$.ajax({
					type : "GET",
					url : context+"/pension/calculateSuppBillComponentValue/"
								+ ppoNo+"/"+fromDate+"/"+toDate,
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
		        			row.find('.txtOldBasic').val(data[i].txtOldBasic);
		        			row.find('.daDueAmt').val(data[i].daDueAmt);
		        			row.find('.actualDays').val(data[i].actualDays);
		                }				
					   }
					}
				});
				
			  setTimeout(calcTotal, 1000);
		}
		}
	});

	
	
/*	$("#tblCVPDtls").on('change',".txtOldCVPEffFrom,.txtOldCVPEffTo",function(){
		var row = $(this).closest("tr");
		var txtOldCVP=parseInt(row.find('.txtOldCVP').val());
		var txtNewCVP=parseInt(row.find('.txtNewCVP').val());
		var txtOldCVPEffFrom=parseInt(row.find('.txtOldCVPEffFrom').val());
		var txtOldBasicEffTo=parseInt(row.find('.txtOldBasicEffTo').text());
		row.find(".revisedSal").val(result);
	});*/


	
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

