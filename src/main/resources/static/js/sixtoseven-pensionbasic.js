$("form[name='saveSixToSeven']").validate({
	
    rules: {
  
    	sevenPcPensionBasic: "required",
    	svnPcBasicwefdate: "required",
    	
    		},
    messages: {
    	sevenPcPensionBasic: "Please enter Seven Pc Basic",
    	svnPcBasicwefdate: "Please enter Seven Pc With Effect Date",
    	
    },
  
    submitHandler: function(form) {
      form.submit();
      $("#loaderMainNew").show();
    }
  });



var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text();
var gratuityAmt=0;
var lastGradePay=0;
var lastNPA=0;
var totalSms = smsCalculation();
var lastbasic = $("#payInPayBand").val();
lastEmolument = Number(lastbasic) + Number(lastGradePay)
+ Number(lastNPA);

if (ClassOfPnsn == 'Family Pension' && Number(totalSms)>0) {
	
	if (Number(totalSms) < 1) {
		gratuityAmt = 2 * Number(lastEmolument);
	}
	else if (Number(totalSms) >= 1 && Number(totalSms) < 5) {
		gratuityAmt = 6 * Number(lastEmolument);
	}
	else if (Number(totalSms) >= 5 && Number(totalSms) < 20) {
		gratuityAmt = 12 * Number(lastEmolument);

	} else if (Number(totalSms) >= 20) {
		 gratuityAmt=((Number(lastEmolument)*totalSms)/2);
	}
	 //gratuityAmt=((Number(lastEmolument)*totalSms)/2);
}else{
	gratuityAmt=((Number(lastEmolument)*totalSms)/4);
}



if(Number($("#paycommId").val())==2){
	if (Number(gratuityAmt) > 700000){
		gratuityAmt = 700000;
  	}
}else if(Number($("#paycommId").val())==8){
	if (Number(gratuityAmt) > 1400000){
		gratuityAmt = 1400000;
  	}    	
}else{
	if (Number(gratuityAmt) > 1400000){
		gratuityAmt = 1400000;
  	}
}


$("#SvnPCgratuityAmt").val(gratuityAmt);

var commutedPension = 0;
var commutPer = 40;

var avgEmolument = document.getElementById("avgPay").value;

var pensionWorkOut=(Number(avgEmolument)/2);// (A)
var basicPensionOnLastPay= (Number(lastEmolument)/2);
var basicPension=Math.max(pensionWorkOut,basicPensionOnLastPay);// 3
var maxbasicPension =basicPension;
commutedPension = Number(maxbasicPension)* Number(commutPer) / 100;

$("#SvnPCcommValueOfPension").val(commutedPension);





function smsCalculation() {

	var smsYear = $("#qualSrvcYear").val();
	var smsMonth = $("#qualSrvcMonth").val();
	var smsDays = $("#qualSrvcDay").val();
	
	
	/*$("#txtQualifyingServYear").val(smsYear);
	$("#txtActualSerMonth").val(smsMonth);
	$("#txtActualSerDay").val(smsDays);*/
	
	var noOfSms;
	
	var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text();
	if (ClassOfPnsn == 'Family Pension' || ClassOfPnsn == 'Voluntary Pension'
			|| ClassOfPnsn == 'Voluntary Pension') {
		if (Number(smsYear) >= 20) {
			smsYear = Number(smsYear);
		}
		if (Number(smsYear) >= 33) {
			noOfSms = 66;
		}
	}

	if (Number(smsYear) > 0) {
		noOfSms = 2 * Number(smsYear);
		if (Number(noOfSms) > 66) {
			noOfSms = 66;
		} else {
			if (Number(noOfSms) == 66) {
				noOfSms = noOfSms;
			} else 
			{
				if (Number(smsMonth) >= 3 && Number(smsMonth) < 9) {
					noOfSms = noOfSms + 1;
				} else if (Number(smsMonth) >= 9) {
					noOfSms = noOfSms + 2;
				} else 
				{
					noOfSms = noOfSms;
				}
			}
		}
	} else if (Number(smsYear) < 1)

	{
		noOfSms = 2 * Number(smsYear);
		if (Number(noOfSms) > 66) {
			noOfSms = 66;
		} else {
			if (Number(noOfSms) == 66) {
				noOfSms = noOfSms;
			} else // no. of SMS are less than 66
			{
				if (Number(smsMonth) >= 3 && Number(smsMonth) < 9) {
					noOfSms = noOfSms + 1;
				} else if (Number(smsMonth) >= 9) {
					noOfSms = noOfSms + 2;
				} else // smsMonth is less than 3
				{
					noOfSms = noOfSms;
				}
			}
		}
	}
	return noOfSms;
}
