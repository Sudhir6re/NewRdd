$(document).ready(function(){
	
     var context=$("#context").val();
	//alert(context);
	if($("#message").val()=="1") {
		Swal.fire("Data Saved Successfully !!!");
	}
	
	
	
	
	
	$("#btnSave").hide();
	$("#btnForward").hide();
	
	$("#chkValidate").change(function(){
		var checked=$(this).prop('checked');
	if(checked==true && $("#isSave").val()=="true"){
		$("#btnSave").show();
	}else{
		$("#btnForward").show();
	}
	});
	
	
	$(".nextPage").click(function(e){
		var sevaarthid=$("#sevaarthid").val();
		
		var text=$(this).attr("href");
		$(this).attr("href",text+sevaarthid);
	
	});
	
	
	
	
	
	
	
	
	//var selectedDocIds=$("#selectedDoc").val();
	
   	if($("#selectedDoc").val()!='undefined'){
   var selectedDocIdsArr=$("#selectedDoc").val().split(",");
    for(var i=0;i<selectedDocIdsArr.length;i++){
    	$('.cbDocCheckListCA'+selectedDocIdsArr[i]).prop('checked', true);
    }
    
    if(selectedDocIdsArr.length==8){
    	$("#selectAllCheckListsComp").prop('checked', true);
    }
    
   	}

	
	
	//for seting current date on html date picker
	
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	$('#txtSysDate').val(today);
	
	
  
  
  
  
  
  

  
  $("#cmbSchemeCode").change(function(){
	  var cmbSchemeCode=$("#cmbSchemeCode").val();
		$.ajax({
			type : "GET",
			url : "../level1/fetchFestivalSehemeDetials/"
					+ cmbSchemeCode,
				
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			// alert("error");
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0 ) { 
					if(data[0].txtEmployeeName!="0"){
						$("#txtMajorHead").val("");
						$("#txtMajorHead").val(data[0].majorHead);
						$("#txtDemandNo").val(data[0].demandCode);
						$("#txtSchemeName").val(data[0].schemeName);
						$("#txtSubMajorHead").val(data[0].subMajorHead);
						$("#txtMinorHead").val(data[0].minorHead);
						$("#txtGroupHead").val(data[0].charged);
						$("#txtSubHead").val(data[0].subMinorHead);
					}else{
						$("#txtMajorHead").val("");
						$("#txtDemandNo").val("");
						$("#txtSchemeName").val("");
						$("#txtSubMajorHead").val("");
						$("#txtMinorHead").val("");
						$("#txtGroupHead").val("");
						$("#txtSubHead").val("");
					   }
			   }
			}
		});
     });
  

  
  $("#txtReqAmountCA").keyup(function(){
	  var reqAmount=$(this).val();
	  $("#txtSancAmountCA").val(reqAmount);
	  $("#txtSancInstallmentsCA").val("");
	  $("#txtInstallmentAmountCA").val("");
  });
  
  $("#txtSancInstallmentsCA").keyup(function(){
	  if($(this).val()!=""){
	  var noOfInstallments=parseInt($(this).val());
	 var sanctionAmt=parseInt($("#txtSancAmountCA").val());
	  var perMonthInstallmentAmount=((sanctionAmt/noOfInstallments));
	 // $("#txtInstallmentAmountCA").val(perMonthInstallmentAmount.toFixed(2));
	  var interestRate=$("#txtInterestRateHBA").val();
	  
	  var sancAmt=$("#txtSanctionedAmountHBA").val();
	  
	  var perMonthPercentage=((parseInt(interestRate)/12)/100);
	  
	  var emiPerMonth=(((sancAmt*perMonthPercentage)*Math.pow((1+perMonthPercentage),parseInt(noOfInstallments)))/Math.pow((1+perMonthPercentage),(parseInt(noOfInstallments)-1)));
	  
	  
	 // Then, EMI = [60,00,000 x 0.75% x (1+0.75%)^240]/[(1+0.75%)^ (240-1)]
	  
	  
	//  $("#txtInstallmentAmountCA").val(Math.round(perMonthInstallmentAmount));
	  $("#txtInstallmentAmountCA").val(Math.round(emiPerMonth));
	  }else{
		  $("#txtInstallmentAmountCA").val("");
	  }
  });
  

});


//for select all

$(document).ready(function() {
    $('#selectAllCheckListsComp').click(function() {
        var checked = this.checked;
        $('input[type="checkbox"]').each(function() {
        this.checked = checked;
    });
    })
});
///////////////////////////


$("#cmbHBAType").change(function(){
	var cityClass=$("#cityClass").val();
	var reqSubType=$("#cmbHBAType").val();
 	if((cityClass!=''  || cityClass!=undefined ) && (reqSubType!=''  || reqSubType!=undefined)){
		switch(reqSubType){
		case "2":          //cost of construction 
			hideAllComponent();
			$("#costofconstruc").show();
			$("#costofconstruc2").show();
			$("#txtcostofconstruc").show();
			
		break;
		case "3":         //Ready Build Flat/House Purchase
			hideAllComponent();
			$("#costofhouse").show();
			$("#costofhouse2").show();
			$("#txtcostofhouse").show();
			break;
		case "4":           //Old Flat/House
			hideAllComponent();
			$("#oldhousevalue").show();
			$("#oldhousevalue2").show();
			$("#txtoldhousevalue").show();
			break;
		case "5":          // Bank Loan Repayment
			hideAllComponent();
			$("#loanvalue").show();
			$("#txtloanvalue").show();
			$("#loanvalue2").show();
			break;
		case "6":        //Plot Purchase and Later Construction
			hideAllComponent();
			$("#costofland").show();
			$("#costofland2").show();
			$("#txtcostofland").show();
			break;
		case "7":       //Special Repairs
			hideAllComponent();
			$("#costofrepair").show();
			$("#costofrepair2").show();
			$("#txtcostofrepair").show();
			break;
		case "8":
			hideAllComponent();
			break;
		}
		
		$(".loaderMainNew").show();
		 $.ajax({
		      type: "GET",
		      url: "../level1/getHBAInterestRate/"+cityClass+"/"+reqSubType,
		      async: false,
		      dataType : 'json',
		      contentType:'application/json',
		      error: function(data){
		    	  $(".loaderMainNew").hide();
		    	  console.log(data);
		      },
		      success: function(data){
		    	  console.log(data);
		    	  if(!isEmpty(data)){
		    		  $("#txtInterestRateHBA").val(data.interestRate);
		    		  $("#maxAmount").val(data.maxAmount);
		    		  $("#noInstallment").val(data.noInstallment);
		    		  $("#txtInterestRateHBA").val(data.interestRate);
		    	  }
		    	  $(".loaderMainNew").hide();
		    }
		 });
 	}else{
 		alert("Please select city class or req sub type");
 	}
});


function isEmpty(object) {
	  for (const property in object) {
	    return false;
	  }
	  return true;
	}


$("#txtReqAmountHBA").blur(function(){
	var reqAmount=$("#txtReqAmountHBA").val();
	var maxAmount=$("#maxAmount").val();
	if(Number(reqAmount)>Number(maxAmount) ){
		var reqtype=$("#cmbHBAType option:selected").text();
		$("#txtReqAmountHBA").val("");
		alert('For '+reqtype+' Advance Amount should be less than or Equals to '+Number(maxAmount));
	}
});

$("#txtSanctionedAmountHBA").blur(function(){
	var reqAmount=$("#txtReqAmountHBA").val();
	var sanctionAmt=$("#txtSanctionedAmountHBA").val();
	var maxAmount=$("#maxAmount").val();
	if(Number(sanctionAmt)>Number(reqAmount)){
		alert("Sanction amount cannot be greater than Request amount");
		$("#txtSanctionedAmountHBA").val("");
	}
});



/*$("#txtSanctionedAmountHBA").blur(function(){
	var reqAmount=$("#txtReqAmountHBA").val();
	var sanctionAmt=$("#txtSanctionedAmountHBA").val();
	var maxAmount=$("#maxAmount").val();
	if(Number(sanctionAmt)>Number(reqAmount)){
		alert("Sanction amount cannot be greater than Request amount");
		$("#txtSanctionedAmountHBA").val("");
	}
});
*/






$("#txtSancPrinInstallHBA").blur(function(){
	var sancInstallments=$("#txtSancPrinInstallHBA").val();
	var noInstallment=$("#noInstallment").val();
	if(Number(sancInstallments)>Number(noInstallment)){
		alert("Sanctioned No. of Principal Installments  should be less than or Equals to "+noInstallment);
		$("#txtSanctionedAmountHBA").val("");
	}else{
		var DOS = document.getElementById("dateSuperAnnuation").value;
		var lArrDate = DOS.split("/");
		var dateofSuperAnnu = new Date(lArrDate[1] + "/" + lArrDate[0] + "/"
				+ lArrDate[2]);
		var currDate = new Date();
		var futureDate = new Date(currDate);
		/*futureDate.setMonth(futureDate.getMonth()
				+ (Number(sancPrinInst.value) + Number(sancInterInst.value)));

		var maxInstallment = dateDiffInMonth(currDate, dateofSuperAnnu);

		var totalinstallments = Number(sancPrinInst.value)+ Number(sancInterInst.value);*/
		
	/*	var sancAmount=$("#txtSanctionedAmountHBA").val();
		var noOfInstallment=$("#txtSancPrinInstallHBA").val();
		var inst = Math.floor(sancAmount / noOfInstallment);
		var oddValue = sancAmount - (inst * (noOfInstallment - 1));*/
		
		
		
		var noOfInstallments=parseInt($(this).val());
		 var sanctionAmt=parseInt($("#txtSanctionedAmountHBA").val());
		  var perMonthInstallmentAmount=((sanctionAmt/noOfInstallments));
		 // $("#txtInstallmentAmountCA").val(perMonthInstallmentAmount.toFixed(2));
		  var interestRate=$("#txtInterestRateHBA").val();
		  
		  var sancAmt=$("#txtSanctionedAmountHBA").val();
		  
		  var perMonthPercentage=((parseInt(interestRate)/12)/100);
		  
		  
		  var totalPowval=Math.pow((1+perMonthPercentage),parseInt(noOfInstallments));
		  var totalPowMin1=totalPowval-1;
		  
		  var emiPerMonth=((sancAmt*perMonthPercentage*totalPowval)/totalPowMin1);
		  
		  
		 // Then, EMI = [60,00,000 x 0.75% x (1+0.75%)^240]/[((1+0.75%)^240-1]
		  
		  
		//  $("#txtInstallmentAmountCA").val(Math.round(perMonthInstallmentAmount));
		  
		  //var sum=Math.round(emiPerMonth)+Math.round(perMonthInstallmentAmount);
		  
		  $("#txtInstallmentAmountCA").val(Math.round(emiPerMonth));
		
		
		
		
		/*$("#txtInstallmentAmountCA").val(inst);
		$("#txtOddPrincipalInstallmentAmtHBA").val(oddValue);*/
		
	}
});

function hideAllComponent(){
	$("#costofconstruc").hide();
	$("#costofconstruc2").hide();
	$("#txtcostofconstruc").hide();
	
	
	$("#costofhouse").hide();
	$("#costofhouse2").hide();
	$("#txtcostofhouse").hide();
	$("#oldhousevalue").hide();
	$("#oldhousevalue2").hide();
	$("#txtoldhousevalue").hide();
	$("#loanvalue").hide();
	$("#txtloanvalue").hide();
	$("#loanvalue2").hide();
	$("#costofland").hide();
	$("#costofland2").hide();
	$("#txtcostofland").hide();
	$("#costofrepair").hide();
	$("#costofrepair2").hide();
	$("#txtcostofrepair").hide();
}




$("#txtcostofconstruc").blur(function(){
	var txtcostofconstruc=$("#txtcostofconstruc").val();
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<5000000)) ){
			addErrorClass($("#txtcostofconstruc"),"For 	Cost of Construction Advance Amount should be less than or Equals to 50,00,000");
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofconstruc"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<3000000)) ){
			addErrorClass($("#txtcostofconstruc"),"For 	Cost of Construction Advance Amount should be less than or Equals to 30,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofconstruc"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<2000000)) ){
			addErrorClass($("#txtcostofconstruc"),"For 	Cost of Construction Advance Amount should be less than or Equals to 20,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofconstruc"));
		}
		break;
	}
});



$("#costofhouse").blur(function(){
	$("#btnSave").prop("disabled",false);
	$("#btnForward").prop("disabled",false);
	var txtcostofconstruc=$("#costofhouse").val();
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<5000000)) ){
			addErrorClass($("#txtcostofconstruc"),"For 	Cost of House Advance Amount should be less than or Equals to 50,00,000");
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofconstruc"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<3000000)) ){
			addErrorClass($("#txtcostofconstruc"),"For Cost of House Advance Amount should be less than or Equals to 30,00,000");	
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofconstruc"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<2000000)) ){
			addErrorClass($("#txtcostofconstruc"),"For Cost of House Advance Amount should be less than or Equals to 20,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofconstruc"));
		}
		break;
	}
});



$("#costofhouse").blur(function(){
	var txtcostofconstruc=$("#costofhouse").val();
	$("#btnSave").prop("disabled",false);
	$("#btnForward").prop("disabled",false);
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<5000000)) ){
			addErrorClass($("#costofhouse"),"For 	Cost of House Advance Amount should be less than or Equals to 50,00,000");
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#costofhouse"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<3000000)) ){
			addErrorClass($("#costofhouse"),"For Cost of House Advance Amount should be less than or Equals to 30,00,000");	
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#costofhouse"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<2000000)) ){
			addErrorClass($("#costofhouse"),"For Cost of House Advance Amount should be less than or Equals to 20,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#costofhouse"));
		}
		break;
	}
});



$("#txtoldhousevalue").blur(function(){
	var txtcostofconstruc=$("#costofhouse").val();
	$("#btnSave").prop("disabled",false);
	$("#btnForward").prop("disabled",false);
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<5000000)) ){
			addErrorClass($("#txtoldhousevalue"),"For Cost of old House Advance Amount should be less than or Equals to 50,00,000");
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtoldhousevalue"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<3000000)) ){
			addErrorClass($("#txtoldhousevalue"),"For Cost of old House Advance Amount should be less than or Equals to 30,00,000");	
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtoldhousevalue"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<2000000)) ){
			addErrorClass($("#txtoldhousevalue"),"For Cost of old House Advance Amount should be less than or Equals to 20,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtoldhousevalue"));
		}
		break;
	}
});




$("#txtloanvalue").blur(function(){
	$("#btnSave").prop("disabled",false);
	$("#btnForward").prop("disabled",false);
	var txtcostofconstruc=$("#costofhouse").val();
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<5000000)) ){
			addErrorClass($("#txtoldhousevalue"),"For Cost of old loan Advance Amount should be less than or Equals to 50,00,000");
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtoldhousevalue"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<3000000)) ){
			addErrorClass($("#txtoldhousevalue"),"For Cost of loan Advance Advance Amount should be less than or Equals to 30,00,000");	
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtoldhousevalue"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<2000000)) ){
			addErrorClass($("#txtoldhousevalue"),"For Cost of loan Advance Amount should be less than or Equals to 20,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtoldhousevalue"));
		}
		break;
	}
});




$("#txtcostofland").blur(function(){
	$("#btnSave").prop("disabled",false);
	$("#btnForward").prop("disabled",false);
	var txtcostofconstruc=$("#txtcostofland").val();
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<1500000)) ){
			addErrorClass($("#txtcostofland"),"For Cost of plot purchase should be less than or Equals to 50,00,000");
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofland"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<9000000)) ){
			addErrorClass($("#txtcostofland"),"For Cost of plot purchase Advance Amount should be less than or Equals to 30,00,000");	
			  $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofland"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<6000000)) ){
			addErrorClass($("#txtcostofland"),"For Cost of plot purchase Amount should be less than or Equals to 20,00,000");	
			 $("#btnSave").prop("disabled",true);
			  $("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofland"));
		}
		break;
	}
});

$("#txtcostofrepair").blur(function(){
	var txtcostofconstruc=$("#txtcostofrepair").val();
	
	$("#btnSave").prop("disabled",false);
	$("#btnForward").prop("disabled",false);
	
	var basic;
	var maxAmount;
	if($("#paycommsioncode").val().trim()=="7th Pay Commission")
		basic=$("#sevenpcbasic").val();
	else
		basic=$("#basic").val();
	var cityCls=$("#cityClass").val();
	switch(cityCls){
	case "1":  //x city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<1500000)) ){
			addErrorClass($("#txtcostofrepair"),"For Cost of Special repaire should be less than or Equals to 10,00,000");
			$("#btnSave").prop("disabled",true);
			$("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofrepair"));
		}
		break;
	case "2":  //y city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<9000000)) ){
			addErrorClass($("#txtcostofrepair"),"For Cost of Special repaire Amount should be less than or Equals to 90,00,000");	
			$("#btnSave").prop("disabled",true);
			$("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofrepair"));
		}
		break;
	case "3":  //z city class
		maxAmount=(Number(basic)*200);
		if((!(Number(txtcostofconstruc)<maxAmount)) || (!(Number(txtcostofconstruc)<6000000)) ){
			addErrorClass($("#txtcostofrepair"),"For Cost of Special repaire should be less than or Equals to 60,00,000");	
			$("#btnSave").prop("disabled",true);
			$("#btnForward").prop("disabled",true);
		}else{
			removeErrorClass($("#txtcostofrepair"));
		}
		break;
	}
});



$("#txtSanctionedAmountHBA").blur(function(){
	
	var city = document.getElementById("cityClass").value;
	var hidlStrDisburse ="Want4thCF";// document.getElementById("hidlStrDisburse").value;
	var reqPCType = document.getElementById("cmbPayCommissionHBA").value;
	var payCommissionGR = document.getElementById("payCommissionGR").value;
	if(city == "0"){
		alert('Kindly select City Class to proceed.');
		document.getElementById("cmbHBAType").value='-1';
		return false;
	}

	if (document.getElementById("rdoExservicemen").checked) {
		var joining = document.getElementById("txtJoiningdate").value;
		if (joining == "") {
			alert('Kindly enter Date of Joining for Ex-servicemen.')
			document.getElementById("cmbHBAType").value='-1';
			return false;
		}
	}

	if (!(document.getElementById("rdoExservicemen").checked)
			&& !(document.getElementById("rdoRegular").checked)) {
		alert('Please select Employee type to proceed.');
		document.getElementById("cmbHBAType").value='-1';
		return false;
	}

	var reqType = document.getElementById("cmbHBAType").value;
	
	var basic = document.getElementById("basic").value;
	var basic7PC = document.getElementById("sevenpcbasic").value;
	

	if(city == 1){

		cityclass = 'X';
	}
	if(city == 2){
		cityclass = 'Y';
	}

	if(city == 3){
		cityclass = 'Z';
	}
	
	
	var superanndt = document.getElementById("dateSuperAnnuation").value;
	var lArrDate = superanndt.split("/");
	var anndate = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	var annmonth = anndate.getMonth()+1;
	var annyear = anndate.getFullYear();

	var currDate = new Date();
	var currmonth = currDate.getMonth() + 1;
	var curryear = currDate.getFullYear();

	var retmonths;

	var month;

	if (currmonth < 10) {
		month = 12 - currmonth;
	}

	if (currmonth == 10) {

		month = 2;
	}

	if (currmonth == 11) {

		month = 1;
	}

	if (currmonth == 12) {
		month = 0;
	}
	
	retmonths = month + 12 * (annyear - 1 - curryear) + annmonth;

	var retyears = annyear - curryear;
	var loanRepayAmt;
	var regular = document.getElementById("rdoRegular").checked;
	var exserv = document.getElementById("rdoExservicemen").checked;
	
	if (reqPCType == 2) {
		if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
		}

		//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
		if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
		//	alert("in loanRepayAmt line no. 10");
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
		}

		if (retyears > 20 || retyears == 20) {
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
		}
	}	// 6 pc
	else {
		if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
			//alert("in exserv line no.555 563"+retmonths);
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic));
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 563"+loanRepayAmt);
		}

		//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
		if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
			//alert("in loanRepayAmt line no.20 10 579"+retmonths);
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic));
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 579");
		}

		if (retyears > 20 || retyears == 20) {
			//alert("in loanRepayAmt line no.20 587"+retmonths);
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic) * 0.85);
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 593"+loanRepayAmt);
		}
	}
	
	var flag=1;
	var reqAmount = 0;
	var reqAmountMCAl = document.getElementById("txtReqAmountHBA").value;
	var sancAmountHBAl = document.getElementById("txtSanctionedAmountHBA").value;
	var sancAmountHBA2 = document.getElementById("txtSanctionedAmountHBA2").value;
	if (flag == 1) {
		reqAmount = document.getElementById("txtReqAmountHBA");
	} else if (flag == 2) {
		reqAmount = document.getElementById("txtSanctionedAmountHBA");
	} else if (flag == 3) {
		reqAmount = document.getElementById("txtSanctionedAmountHBA2");
	}
	
	
	if(((Math.round(Number(sancAmountHBAl))) > (Math.round(Number(reqAmountMCAl))))){
		alert("Sanction amount cannot be greater than Request amount");
		reqAmount.value = "";
		//reqAmount.focus();
		return false;
	}
	

	// 7th pay commission
	else if(reqPCType == 3){

		var costland;
		var paylimit;
		var maxlimit;
		var limit;
		var oldhouse;
		var costconstruc;
		var costrepair;
		var costhouse;
		var loanval;

		//alert('construction of flat or house  X reqType'+reqType);

		// if applying house loan for the first time

		// a--construction of flat or house
		if (reqType == 2 && cityclass == "X"
			&& document.getElementById("rdoyes").checked) {
       //  alert('construction of flat or house  X reqAmount'+reqAmount.value);
			paylimit = Math.round(Number(basic7PC) * 100);
			//alert('paylimit--------------->'+paylimit);
			 maxlimit = 5000000;
			 var refundcap =
			 document.getElementById("txtLoanRepaymentCapacity").value;

			costhouse = document.getElementById("txtcostofconstruc").value;
			//alert("in paylimit line no. "+paylimit);
			//alert("in costhouse line no. "+costhouse);
			//alert("in loanRepayAmt line no. "+loanRepayAmt);
			limit = Math.min(paylimit, 7000000,costhouse, loanRepayAmt);
			//alert("in limit line no. 714"+limit);
			
			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Construction of Flat/House and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}


		if (reqType == 2 && cityclass == "Y"
			&& document.getElementById("rdoyes").checked) {



			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			costhouse = document.getElementById("txtcostofconstruc").value;

			limit = Math.min(paylimit, 7000000,costhouse, loanRepayAmt);



			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Construction of Flat/House and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}



		if (reqType == 2 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			costhouse = document.getElementById("txtcostofconstruc").value;
			limit = Math.min(paylimit, 4000000,costhouse, loanRepayAmt);




			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 10000000) {
				alert('Maximum request amount for Construction of Flat/House and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 10000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}


		// if applying house loan for the first time

		// a--ready built
		if (reqType == 3 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costhouse, paylimit, 7000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Ready built flat/house and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Ready built flat/house and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}
		if (reqType != 3 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		if (reqType == 3 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;


			limit = Math.min(costhouse, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Ready built Flat/House and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Ready built Flat/House and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 3 && cityclass != "Y"
			&& document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		if (reqType == 3 && cityclass == "Z"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			//alert("in ready bult for z ************");
			limit = Math.min(costhouse, paylimit, 4000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 10000000) {
				alert('Maximum request amount for Ready built Flat/House and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 10000000) {
				alert('Maximum Cost of House for Ready built Flat/House and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 3 && cityclass != "Z"
			&& document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		// bank loan repayment and first time changes 5/2/2021
		if (reqType == 5 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic7PC * 100));
			// maxlimit = 5000000;

			limit = Math.min(loanval, paylimit, 7000000, loanRepayAmt);
			//alert('limit 959' + limit );
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval < 10000000) {
				alert('Maximum Loan value for Bank Loan Repayment and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval > 10000000 ) {
				alert('Maximum Loan value should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
		}
		if (reqType != 5 && cityclass != "X"
			&& document.getElementById("rdoyes").checked) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		if (reqType == 5 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(loanval, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval < 10000000) {
				alert('Maximum loan value for Bank loan repayment and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval > 10000000) {
				alert('Maximum Loan value should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 5 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		if (reqType == 5 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			//alert('limit 959' + loanval );
			//alert('limit 959' + paylimit );
			//alert('limit 959' + loanRepayAmt );
			limit = Math.min(loanval, paylimit, 4000000, loanRepayAmt);
			//alert('limit 959' + limit );
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval > 10000000) {
				alert('Maximum Loan value should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval < 10000000) {
				alert('Maximum Loan value for Bank loan repayment and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 5 && cityclass != "Z"
			&& document.getElementById("rdoyes").checked) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		// plot purchase 1st time
		if (reqType == 1 && cityclass == "X" && document.getElementById("rdoyes").checked) {



			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;



			limit = Math.min(costland, paylimit, 2100000, loanRepayAmt);



			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			//alert("reqAmount*******"+reqAmount.Value);
			//alert("limit*******"+limit);
			//alert("costland*******"+costland);
			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value= "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 1 && reqType != 6 && cityclass != "X"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 1 && cityclass == "Y"
			&& document.getElementById("rdoyes").checked) {


			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 1500000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 1 && reqType != 6 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';

			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 1 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 1200000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland < 10000000) {
				alert('Maximum request amount for Plot Purchase and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 10000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 1 && reqType != 6 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}


		// plot purchase & later construction
		if (reqType == 6 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 7000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and later construction and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and later construction and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}
		if (reqType != 6 && reqType !=1 && cityclass != "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 6 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and later construction city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and later construction city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 6 && reqType !=1 && cityclass != "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 6 && cityclass == "Z"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 4000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 10000000) {
				alert('Maximum request amount for Plot Purchase and later construction city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 10000000) {
				alert('Maximum Cost of Land for Plot Purchase and later construction and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 6 && reqType !=1 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		// old flat/house
		if (reqType == 4 && cityclass == "X"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 7000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 20000000) {

				alert('Maximum request amount for buying Old house/Flat and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 20000000) {

				alert('Maximum Old house value for buying Old house/Flat and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}
		if (reqType != 4 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}



		if (reqType == 4 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 20000000) {
				alert('Maximum request amount for buying Old house/Flat and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 20000000) {
				alert('Maximum Old house value for buying Old house/Flat and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 4 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}

		if (reqType == 4 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 4000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 10000000) {
				alert('Maximum request amount for buying Old house/Flat and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 10000000) {
				alert('Maximum Old house value for buying Old house/Flat and city class Z is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 4 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}

		// special repairs
		if (reqType == 7 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic7PC) * 50);
			// maxlimit = 750000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 1050000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair < 10000000) {
				alert('Maximum request amount for Special Repair and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = ""; 
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair > 10000000) {
				alert('Maximum Cost of Repair should be 1 crore.');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}


		if (reqType != 7 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

		if (reqType == 7 && cityclass == "Y" && document.getElementById("rdoyes").checked) {


			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic7PC) * 50);
			// maxlimit = 450000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 750000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair < 10000000) {
				alert('Maximum request amount for Special Repairs and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair > 10000000) {
				alert('Maximum Cost of Repair should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 7 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

		if (reqType == 7 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic7PC) * 50);
			// maxlimit = 300000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 600000, loanRepayAmt);


			if (reqAmount.value > limit && costrepair != "" 	&& costrepair != 0 && costrepair < 10000000) {
				alert('Maximum request amount for Special Repairs and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair > 10000000) {
				alert('Maximum Cost of Repair should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 7 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}


		//first time for extension
		if (reqType == 8 && cityclass == "X"	&& (document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 1500000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 2100000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != ""
				&& costconstruc != 0) {
				alert('Maximum request amount for Extension of Rooms and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}
		if (reqType != 8 && reqType != 2 && cityclass != "X"	&& !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}

		if (reqType == 8 && cityclass == "Y" && (document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 1500000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != ""	&& costconstruc != 0) {
				alert('Maximum request amount for Extension of Rooms and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 8 && reqType != 2 && cityclass != "Y" && (document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}

		if (reqType == 8  && cityclass == "Z" && (document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic7PC) * 100);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 1200000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0 ) {
				alert('Maximum request amount for Extension of Rooms and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 8 && reqType != 2 && cityclass != "Z" && (document.getElementById("rdoyes").checked)) {
			//alert("inside to chjeck"); 
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
			//alert("inside to chjeck...Bye"); 
		}

		// if applying house loan for the second time  changes not done kavita
		if ((!document.getElementById("rdoyes").checked) && (reqType == 1 || reqType == 2 || reqType == 3 || reqType == 4 || reqType == 5 || reqType == 6 || reqType == 7 || reqType == 8 )) {
			alert("Not applicable for second time  ") ;
			document.getElementById("txtcostofconstruc").value = "";
			document.getElementById("txtReqAmountHBA").value = "";
			document.getElementById("txtSanctionedAmountHBA").value = "";
		    document.getElementById("txtSanctionedAmountHBA2").value = "";
			}
			
		}
	

	
	//6th PC 
	else {
		var costland;
		var paylimit;
		var maxlimit;
		var limit;
		var oldhouse;
		var costconstruc;
		var costrepair;
		var costhouse;
		var loanval;



		// if applying house loan for the first time

		// a--construction of flat or house
		if (reqType == 2 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			costhouse = document.getElementById("txtcostofconstruc").value;
			//alert("in paylimit line no. 710"+paylimit);
			//alert("in costhouse line no. 711"+costhouse);
			//alert("in loanRepayAmt line no. 712"+loanRepayAmt);
			limit = Math.min(paylimit, 5000000,costhouse, loanRepayAmt);
			//alert("in limit line no. 714"+limit);
			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Construction of Flat/House and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}


		if (reqType == 2 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			costhouse = document.getElementById("txtcostofconstruc").value;

			limit = Math.min(paylimit, 3000000,costhouse, loanRepayAmt);



			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Construction of Flat/House and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}



		if (reqType == 2 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			costhouse = document.getElementById("txtcostofconstruc").value;
			limit = Math.min(paylimit, 2000000,costhouse, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 10000000) {
				alert('Maximum request amount for Construction of Flat/House and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 10000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}


		// if applying house loan for the first time

		// a--ready built
		if (reqType == 3 && cityclass == "X"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costhouse, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Ready built flat/house and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Ready built flat/house and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}
		if (reqType != 3 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		if (reqType == 3 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;


			limit = Math.min(costhouse, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Ready built Flat/House and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Ready built Flat/House and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 3 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		if (reqType == 3 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			//alert("in ready bult for z ************");
			limit = Math.min(costhouse, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 10000000) {
				alert('Maximum request amount for Ready built Flat/House and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 10000000) {
				alert('Maximum Cost of House for Ready built Flat/House and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 3 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		// bank loan repayment and first time
		if (reqType == 5 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic * 200));
			// maxlimit = 5000000;

			limit = Math.min(loanval, paylimit, 5000000, loanRepayAmt);
			//alert('limit 959' + limit );
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval < 10000000) {
				alert('Maximum Loan value for Bank Loan Repayment and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval > 10000000 ) {
				alert('Maximum Loan value should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
		}
		if (reqType != 5 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		if (reqType == 5 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(loanval, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval < 10000000) {
				alert('Maximum loan value for Bank loan repayment and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval > 10000000) {
				alert('Maximum Loan value should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 5 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		if (reqType == 5 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			//alert('limit 959' + loanval );
			//alert('limit 959' + paylimit );
			//alert('limit 959' + loanRepayAmt );
			limit = Math.min(loanval, paylimit, 2000000, loanRepayAmt);
			//alert('limit 959' + limit );
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval > 10000000) {
				alert('Maximum Loan value should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && loanval != "" && loanval != 0 && loanval < 10000000) {
				alert('Maximum Loan value for Bank loan repayment and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 5 && cityclass != "Z"
			&& document.getElementById("rdoyes").checked) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		// plot purchase 1st time
		if (reqType == 1 && cityclass == "X" && document.getElementById("rdoyes").checked) {



			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;



			limit = Math.min(costland, paylimit, 1500000, loanRepayAmt);



			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			//alert("reqAmount*******"+reqAmount.Value);
			//alert("limit*******"+limit);
			//alert("costland*******"+costland);
			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value= "";
				reqAmount.value = "";
				reqAmount.focus();
			}
		}

		if (reqType != 1 && reqType != 6 && cityclass != "X"	&& document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 1 && cityclass == "Y" && document.getElementById("rdoyes").checked) {


			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 900000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 1 && reqType != 6 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';

			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 1 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 600000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 10000000) {
				alert('Maximum request amount for Plot Purchase and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 	&& costland > 10000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 1 && reqType != 6 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}


		// plot purchase & later construction
		if (reqType == 6 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and later construction and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and later construction and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}
		if (reqType != 6 && reqType !=1 && cityclass != "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 6 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and later construction city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and later construction city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}

		if (reqType != 6 && reqType !=1 && cityclass != "Y" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 6 && cityclass == "Z"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland < 10000000) {
				alert('Maximum request amount for Plot Purchase and later construction city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 10000000) {
				alert('Maximum Cost of Land for Plot Purchase and later construction and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
		}

		if (reqType != 6 && reqType !=1 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		// old flat/house
		if (reqType == 4 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 20000000) {

				alert('Maximum request amount for buying Old house/Flat and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 20000000) {

				alert('Maximum Old house value for buying Old house/Flat and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}
		if (reqType != 4 && cityclass != "X"
			&& document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}



		if (reqType == 4 && cityclass == "Y"
			&& document.getElementById("rdoyes").checked) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 20000000) {
				alert('Maximum request amount for buying Old house/Flat and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 20000000) {
				alert('Maximum Old house value for buying Old house/Flat and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 4 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}

		if (reqType == 4 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 10000000) {
				alert('Maximum request amount for buying Old house/Flat and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 10000000) {
				alert('Maximum Old house value for buying Old house/Flat and city class Z is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 4 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}

		// special repairs
		if (reqType == 7 && cityclass == "X" && document.getElementById("rdoyes").checked) {

			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic) * 100);
			// maxlimit = 750000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 750000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair < 10000000) {
				alert('Maximum request amount for Special Repair and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair > 10000000) {
				alert('Maximum Cost of Repair should be 1 crore.');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}


		if (reqType != 7 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

		if (reqType == 7 && cityclass == "Y" && document.getElementById("rdoyes").checked) {


			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic) * 100);
			// maxlimit = 450000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 450000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair < 10000000) {
				alert('Maximum request amount for Special Repairs and city class Y for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair > 10000000) {
				alert('Maximum Cost of Repair should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 7 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

		if (reqType == 7 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic) * 100);
			// maxlimit = 300000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 300000, loanRepayAmt);


			if (reqAmount.value > limit && costrepair != ""	&& costrepair != 0 && costrepair < 10000000) {
				alert('Maximum request amount for Special Repairs and city class Z for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}
			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 && costrepair > 10000000) {
				alert('Maximum Cost of Repair should be 1 crore');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 7 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}



		// if applying house loan for the second time

		// construction of flat or house
		if (reqType == 2 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			costhouse = document.getElementById("txtcostofconstruc").value;
			limit = Math.min(paylimit, 5000000,costhouse, loanRepayAmt);



			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Construction of Flat/House and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}


		if (reqType == 2 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			costhouse = document.getElementById("txtcostofconstruc").value;

			limit = Math.min(paylimit, 3000000,costhouse, loanRepayAmt);
			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Construction of Flat/House and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}



		if (reqType == 2 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {


			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			costhouse = document.getElementById("txtcostofconstruc").value;
			limit = Math.min(paylimit, 2000000,costhouse, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 10000000) {
				alert('Maximum request amount for Construction of Flat/House and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse > 10000000) {
				alert('Maximum Cost of House for Construction of Flat/House and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}




		// ready built flat/house
		// a--ready built
		if (reqType == 3 && cityclass == "X"
			&& !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costhouse, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Ready built flat/house and city class X for first time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Ready built flat/house and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}
		if (reqType != 3 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		if (reqType == 3 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costhouse, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != ""	&& costhouse != 0 && costhouse < 20000000) {
				alert('Maximum request amount for Ready built Flat/House and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 20000000) {
				alert('Maximum Cost of House for Ready built Flat/House and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 3 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}

		if (reqType == 3 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofhouse").style.display = '';
			document.getElementById("costofhouse2").style.display = '';
			document.getElementById("txtcostofhouse").style.display = '';

			costhouse = document.getElementById("txtcostofhouse").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;
			//alert("in ready bult for z ************");
			limit = Math.min(costhouse, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse < 10000000) {
				alert('Maximum request amount for Ready built Flat/House and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costhouse != "" && costhouse != 0 && costhouse > 10000000) {
				alert('Maximum Cost of House for Ready built Flat/House and city class Z is 1 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofhouse").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 3 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("costofhouse").style.display = 'none';
			document.getElementById("costofhouse2").style.display = 'none';
			document.getElementById("txtcostofhouse").style.display = 'none';
		}


		// plot purchase and later construction 2nd time
		if (reqType == 6 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 1500000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;


			limit = Math.min(costland, paylimit, 5000000, loanRepayAmt);


			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}
		if (reqType != 6  && reqType != 1 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 6 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value= "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value= "";
				reqAmount.value = "";
				reqAmount.focus();
			}
		}

		if (reqType != 6 && reqType != 1 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 6 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 10000000) {
				alert('Maximum request amount for Plot Purchase and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value= "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 10000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Z is 1 crore');
				document.getElementById("txtSanctionedAmountHBA2").value = "";
				document.getElementById("txtPrincipalAmountHBA").value = "";
				document.getElementById("txtDisbursement1").value = "";
				document.getElementById("txtcostofland").value= "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 6 && reqType != 1 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}



		// plot purchase
		if (reqType == 1 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 1500000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 1500000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}
		if (reqType != 1 && reqType != 6 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 1 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 900000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 1  && reqType != 6 && cityclass != "Y"	&& !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		if (reqType == 1 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = '';
			document.getElementById("costofland2").style.display = '';
			document.getElementById("txtcostofland").style.display = '';

			costland = document.getElementById("txtcostofland").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costland, paylimit, 600000, loanRepayAmt);

			if (reqAmount.value > limit && costland != "" && costland != 0	&& costland < 20000000) {
				alert('Maximum request amount for Plot Purchase and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && costland != "" && costland != 0 && costland > 20000000) {
				alert('Maximum Cost of Land for Plot Purchase and city class Z is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}

		if (reqType != 1 && reqType != 6 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofland").style.display = 'none';
			document.getElementById("costofland2").style.display = 'none';
			document.getElementById("txtcostofland").style.display = 'none';
		}

		// old flat/house
		// old flat/house
		if (reqType == 4 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 20000000) {

				alert('Maximum request amount for buying Old house/Flat and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 20000000) {

				alert('Maximum Old house value for buying Old house/Flat and city class X is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}




		}
		if (reqType != 4 && cityclass != "X" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}



		if (reqType == 4 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 20000000) {
				alert('Maximum request amount for buying Old house/Flat and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 20000000) {
				alert('Maximum Old house value for buying Old house/Flat and city class Y is 2 crores');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 4 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}

		if (reqType == 4 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("oldhousevalue").style.display = '';
			document.getElementById("oldhousevalue2").style.display = '';
			document.getElementById("txtoldhousevalue").style.display = '';

			oldhouse = document.getElementById("txtoldhousevalue").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(oldhouse, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse < 10000000) {
				alert('Maximum request amount for buying Old house/Flat and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

			if (reqAmount.value > limit && oldhouse != "" && oldhouse != 0 && oldhouse > 10000000) {
				alert('Maximum Old house value for buying Old house/Flat and city class Z is 1 crore.');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtoldhousevalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}



		}

		if (reqType != 4 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
			document.getElementById("oldhousevalue").style.display = 'none';
			document.getElementById("oldhousevalue2").style.display = 'none';
			document.getElementById("txtoldhousevalue").style.display = 'none';
		}

		// bank loan repayment
		if (reqType == 5 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 5000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(loanval, paylimit, 5000000, loanRepayAmt);

			if (reqAmount.value > limit && loanval != "" && loanval != 0) {
				alert('Maximum request amount for Bank loan Repayment and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}
		if (reqType != 5 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		if (reqType == 5 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 3000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(loanval, paylimit, 3000000, loanRepayAmt);

			if (reqAmount.value > limit && loanval != "" && loanval != 0) {
				alert('Maximum request amount for Bank loan Repayment and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 5 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		if (reqType == 5 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("loanvalue").style.display = '';
			document.getElementById("loanvalue2").style.display = '';
			document.getElementById("txtloanvalue").style.display = '';

			loanval = document.getElementById("txtloanvalue").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 2000000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(loanval, paylimit, 2000000, loanRepayAmt);

			if (reqAmount.value > limit && loanval != "" && loanval != 0) {
				alert('Maximum request amount for Bank loan Repayment and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtloanvalue").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 5 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("loanvalue").style.display = 'none';
			document.getElementById("loanvalue2").style.display = 'none';
			document.getElementById("txtloanvalue").style.display = 'none';
		}

		// extension of rooms 2nd time
		if (reqType == 8 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 1500000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 1500000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0 ) {
				alert('Maximum request amount for Extension of Rooms and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}
		if (reqType != 8 && reqType != 2 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}

		if (reqType == 8 && cityclass == "Y"	&& !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 900000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0) {
				alert('Maximum request amount for Extension of Rooms and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 8 &&  reqType != 2 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}

		if (reqType == 8 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 600000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0) {
				alert('Maximum request amount for Extension of Rooms and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 8 && reqType != 2 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}





		//first time for extension
		if (reqType == 8 && cityclass == "X" && (document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 1500000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 1500000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0) {
				alert('Maximum request amount for Extension of Rooms and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}
		if (reqType != 8 && reqType != 2 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}

		if (reqType == 8 && cityclass == "Y" && (document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 900000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 900000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0) {
				alert('Maximum request amount for Extension of Rooms and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 8 && reqType != 2 && cityclass != "Y" && (document.getElementById("rdoyes").checked)) {
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
		}

		if (reqType == 8  && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofconstruc").style.display = '';
			document.getElementById("costofconstruc2").style.display = '';
			document.getElementById("txtcostofconstruc").style.display = '';

			costconstruc = document.getElementById("txtcostofconstruc").value;
			paylimit = Math.round(Number(basic) * 200);
			// maxlimit = 600000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costconstruc, paylimit, 600000, loanRepayAmt);

			if (reqAmount.value > limit && costconstruc != "" && costconstruc != 0 ) {
				alert('Maximum request amount for Extension of Rooms and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofconstruc").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 8 && reqType != 2 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
			//alert("inside to chjeck"); 
			document.getElementById("costofconstruc").style.display = 'none';
			document.getElementById("costofconstruc2").style.display = 'none';
			document.getElementById("txtcostofconstruc").style.display = 'none';
			//alert("inside to chjeck...Bye"); 
		}

		// special repairs 2nd time
		if (reqType == 7 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 750000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 750000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 ) {
				alert('Maximum request amount for Special Repair and city class X for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}
		if (reqType != 7 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

		if (reqType == 7 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 450000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 450000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0 ) {
				alert('Maximum request amount for Special Repairs and city class Y for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}


		}

		if (reqType != 7 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

		if (reqType == 7 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

			document.getElementById("costofrepair").style.display = '';
			document.getElementById("costofrepair2").style.display = '';
			document.getElementById("txtcostofrepair").style.display = '';

			costrepair = document.getElementById("txtcostofrepair").value;
			paylimit = Math.round(Number(basic) * 150);
			// maxlimit = 300000;
			// var refundcap =
			// document.getElementById("txtLoanRepaymentCapacity").value;

			limit = Math.min(costrepair, paylimit, 300000, loanRepayAmt);

			if (reqAmount.value > limit && costrepair != "" && costrepair != 0) {
				alert('Maximum request amount for Special Repairs and city class Z for second time is ' + limit + '');
				document.getElementById("txtSanctionedAmountHBA").value = "";
				document.getElementById("txtcostofrepair").value = "";
				reqAmount.value = "";
				reqAmount.focus();
			}

		}

		if (reqType != 7 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
			document.getElementById("costofrepair").style.display = 'none';
			document.getElementById("costofrepair2").style.display = 'none';
			document.getElementById("txtcostofrepair").style.display = 'none';
		}

	}
	
	//end
	
});





var errorSeen = false;

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





