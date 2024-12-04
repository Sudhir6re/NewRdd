function validateReqAmountHBAabc(flag) {

	//alert("in validateReqAmountHBA111 line no. 329");
	var city = document.getElementById("cmbCityClass").value;
	///alert("in validateReqAmountHBA111 line no. city"+city);
	
	var hidlStrDisburse = document.getElementById("hidlStrDisburse").value;
	//alert("in validateReqAmountHBA111 line no. hidlStrDisburse"+hidlStrDisburse);
	
	var reqPCType = document.getElementById("cmbPayCommissionHBA").value;
	
	var payCommissionGR = document.getElementById("payCommissionGR").value;

	if(city == -1){
		//alert("in validateReqAmountHBA line no. 334");
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
	
	var basic = document.getElementById("hidBasicPay").value;
	var basic7PC = document.getElementById("hidsevenPcBasic").value;
	//alert('basic7PC>>>>>>>>>>>>>>>>'+basic7PC);
	var citclas = document.getElementById("cmbCityClass").value;
	//alert('city class in HouseAdvance.js'+citclas);

	var cityclass;
	/*if(citclas == 10001198352){

		cityclass = 'X';
	}
if(citclas == 10001198353){
	cityclass = 'Y';
	}

if(citclas == 10001198354){
	cityclass = 'Z';
}*/

//	prod
	if(citclas == 10001198342){

		cityclass = 'X';
	}
	if(citclas == 10001198343){
		cityclass = 'Y';
	}

	if(citclas == 10001198344){
		cityclass = 'Z';
	}


	/*staging
if(citclas == 10001198241){

	cityclass = 'X';
}
if(citclas == 10001198242){
cityclass = 'Y';
}

if(citclas == 10001198243){
cityclass = 'Z';
}*/


	if (reqType == 800037 || reqType == 800058) {
		//alert("in validateReqAmountHBA line no. 406");
		document.getElementById("costofland").style.display = '';
		document.getElementById("costofland2").style.display = '';
		document.getElementById("txtcostofland").style.display = '';
	}
	if (reqType != 800037 && reqType != 800058) {
		//alert("in validateReqAmountHBA line no. 412");
		document.getElementById("costofland").style.display = 'none';
		document.getElementById("costofland2").style.display = 'none';
		document.getElementById("txtcostofland").style.display = 'none';
	}



	if (reqType == 800038 || reqType == 800060) {
		//alert("in validateReqAmountHBA line no. 421");
		document.getElementById("costofconstruc").style.display = '';
		document.getElementById("costofconstruc2").style.display = '';
		//alert("going out*********"+document.getElementById("txtcostofconstruc").style.display);
		document.getElementById("txtcostofconstruc").style.display = '';
		//alert("going out*********"+document.getElementById("txtcostofconstruc").style.display);
	}
	if (reqType != 800038 && reqType !== 800060) {
		document.getElementById("costofconstruc").style.display = 'none';
		document.getElementById("costofconstruc2").style.display = 'none';
		document.getElementById("txtcostofconstruc").style.display = 'none';
	}







	if (reqType == 800041) {
		document.getElementById("oldhousevalue").style.display = '';
		document.getElementById("oldhousevalue2").style.display = '';
		document.getElementById("txtoldhousevalue").style.display = '';
	}
	if (reqType != 800041) {
		document.getElementById("oldhousevalue").style.display = 'none';
		document.getElementById("oldhousevalue2").style.display = 'none';
		document.getElementById("txtoldhousevalue").style.display = 'none';
	}

	if (reqType == 800042) {
		document.getElementById("loanvalue").style.display = '';
		document.getElementById("loanvalue2").style.display = '';
		document.getElementById("txtloanvalue").style.display = '';
	}
	if (reqType != 800042) {
		document.getElementById("loanvalue").style.display = 'none';
		document.getElementById("loanvalue2").style.display = 'none';
		document.getElementById("txtloanvalue").style.display = 'none';
	}

	/*	if (reqType == 800060) {
		document.getElementById("costofrepair").style.display = '';
		document.getElementById("costofrepair2").style.display = '';
		document.getElementById("txtcostofrepair").style.display = '';
	}
	if (reqType != 800060) {
		document.getElementById("costofrepair").style.display = 'none';
		document.getElementById("costofrepair2").style.display = 'none';
		document.getElementById("txtcostofrepair").style.display = 'none';
	}*/



	if (reqType == 800059) {
		document.getElementById("costofrepair").style.display = '';
		document.getElementById("costofrepair2").style.display = '';
		document.getElementById("txtcostofrepair").style.display = '';
	}
	if (reqType != 800059) {
		document.getElementById("costofrepair").style.display = 'none';
		document.getElementById("costofrepair2").style.display = 'none';
		document.getElementById("txtcostofrepair").style.display = 'none';
	}

	if (reqType == 800039) {
		document.getElementById("costofhouse").style.display = '';
		document.getElementById("costofhouse2").style.display = '';
		document.getElementById("txtcostofhouse").style.display = '';
	}
	if (reqType != 800039) {
		document.getElementById("costofhouse").style.display = 'none';
		document.getElementById("costofhouse2").style.display = 'none';
		document.getElementById("txtcostofhouse").style.display = 'none';
	}

	var superanndt = document.getElementById("hidDateOfSuperAnnuation").value;
	//alert("in superanndt no. 563 "+superanndt);
	var lArrDate = superanndt.split("/");
	var anndate = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	//alert("in month line no. 563 "+anndate);
	//var annmonth = anndate.getMonth();
	var annmonth = anndate.getMonth()+1;
    //alert("in annmonth line no. 563 "+annmonth);
	//alert("in annmonth line no. 563 "+annmonth+1);
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
	//alert("in month line no. 563 "+month);
	//alert("in annyear line no. 563 "+annyear);
	//alert("in curryear line no. 563 "+curryear);
	//alert("in annmonth line no. 563 "+annmonth);
	retmonths = month + 12 * (annyear - 1 - curryear) + annmonth;

	var retyears = annyear - curryear;
	//alert("in retmonths line no. 563"+retmonths);
	//alert("in retyears line no. 563"+retyears);
	var loanRepayAmt;
	var regular = document.getElementById("rdoRegular").checked;
	var exserv = document.getElementById("rdoExservicemen").checked;
	//alert("in regular line no. 563"+regular);
	//alert("in exserv line no. 563"+exserv);
	//if ((retyears > 5 || retyears == 5) || (retyears < 10)) {
	
	/*if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
		//alert("in exserv line no. 563"+exserv);
		loanRepayAmt = Math.round(Number(retmonths) * Number(basic));
		document.getElementById("txtLoanRepaymentCapacity").value = Math
		.round(loanRepayAmt);
		//alert("in loanRepayAmt line no. 563"+loanRepayAmt);
	}

	//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
	if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
		//alert("in loanRepayAmt line no. 579");
		loanRepayAmt = Math.round(Number(retmonths) * Number(basic));
		document.getElementById("txtLoanRepaymentCapacity").value = Math
		.round(loanRepayAmt);
		//alert("in loanRepayAmt line no. 579");
	}

	if (retyears > 20 || retyears == 20) {
		//alert("in loanRepayAmt line no. 587");
		loanRepayAmt = Math.round(Number(retmonths) * Number(basic) * 0.85);
		document.getElementById("txtLoanRepaymentCapacity").value = Math
		.round(loanRepayAmt);
		//alert("in loanRepayAmt line no. 593"+loanRepayAmt);
	}*/
//add by kavita for 7pc implementation
	//7pc 
	if (reqPCType == 10001198434) {
		if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
		//	alert("in exserv line no. 5"+retyears);
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
			document.getElementById("txtLoanRepaymentCapacity").value = Math
			.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
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
			//alert("in loanRepayAmt line no. 20");
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
	//6 to 7 conversion 
	 if ((hidlStrDisburse == 'Want3rdCF' || hidlStrDisburse == 'Club3rd4thCF') && reqPCType == 10001198434 && payCommissionGR == 800054 ) { 
			var DisbusOne = document.getElementById("DisbusOne").value;
			var Disbustwo = document.getElementById("DisbusTwo").value;
			
			if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
			//	alert("in exserv line no. 5"+retyears);
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				loanRepayAmt = loanRepayAmt - Disbustwo;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
			}

			//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
			if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
			//	alert("in loanRepayAmt line no. 10");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				loanRepayAmt = loanRepayAmt - Disbustwo;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
			}

			if (retyears > 20 || retyears == 20) {
				//alert("in loanRepayAmt line no. 20");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				loanRepayAmt = loanRepayAmt - Disbustwo;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
			}
		}
	 if (hidlStrDisburse == 'Want4thCF' && reqPCType == 10001198434 && payCommissionGR == 800054 ) { 
		var DisbusOne = document.getElementById("DisbusOne").value;
		var Disbustwo = document.getElementById("DisbusTwo").value;
		var DisbusThree = document.getElementById("DisbusThree").value;
		var DisbusTwoThree = document.getElementById("DisbusTwoThree").value;
		if(Disbustwo !=0 && DisbusThree !=0){
		if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
		//	alert("in exserv line no. 5"+retyears);
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
			//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
			loanRepayAmt = loanRepayAmt - DisbusOne;
			loanRepayAmt = loanRepayAmt - Disbustwo;
			loanRepayAmt = loanRepayAmt - DisbusThree;
			//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
		}

		//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
		if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
		//	alert("in loanRepayAmt line no. 10");
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
			//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
			loanRepayAmt = loanRepayAmt - DisbusOne;
			loanRepayAmt = loanRepayAmt - Disbustwo;
			loanRepayAmt = loanRepayAmt - DisbusThree;
			//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
		}

		if (retyears > 20 || retyears == 20) {
			//alert("in loanRepayAmt line no. 20");
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
			//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
			loanRepayAmt = loanRepayAmt - DisbusOne;
			loanRepayAmt = loanRepayAmt - Disbustwo;
			loanRepayAmt = loanRepayAmt - DisbusThree;
			//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
		   }
		}
		if(DisbusTwoThree !=0){
			if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
				//	alert("in exserv line no. 5"+retyears);
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - DisbusTwoThree;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
				}

				//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
				if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
				//	alert("in loanRepayAmt line no. 10");
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - DisbusTwoThree;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
				}

				if (retyears > 20 || retyears == 20) {
					//alert("in loanRepayAmt line no. 20");
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - DisbusTwoThree;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
				   }
		}
	}
 if ((hidlStrDisburse == 'Want2rdCF' || hidlStrDisburse == 'Club2nd3rdCF' || hidlStrDisburse == 'Club2nd3rd4thCF') && reqPCType == 10001198434 && payCommissionGR == 800054 ) { 
		
		var DisbusOne = document.getElementById("DisbusOne").value;
		
		if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
		//	alert("in exserv line no. 5"+retyears);
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
			//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
			loanRepayAmt = loanRepayAmt - DisbusOne;
			//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
		}

		//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
		if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
		//	alert("in loanRepayAmt line no. 10");
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
			//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
			loanRepayAmt = loanRepayAmt - DisbusOne;
			//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
		}

		if (retyears > 20 || retyears == 20) {
			//alert("in loanRepayAmt line no. 20");
			loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
			//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
			loanRepayAmt = loanRepayAmt - DisbusOne;
			//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
			document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
			//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
		}
	}

    //var isManualFlag = document.getElementById("hidisManualflag").value;
	var cflag = document.getElementById("cFlag").value;
	 var cFlagManual = document.getElementById("cFlagManual").value;
	 var ManualREqAmount = document.getElementById("ManualREqAmount").value;
	 if (reqType == 800038){
		 
		 if ((hidlStrDisburse == 'Want3rdCF') && reqPCType == 10001198434 && ((cflag == 'Y') || (cFlagManual == 'Y')) ) { 
				var DisbusOne = (ManualREqAmount * 0.30).toFixed(2);
				var Disbustwo = (ManualREqAmount * 0.40).toFixed(2);
				
				if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
				
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - Disbustwo;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
				}
				if ((retyears > 10 || retyears == 10) && (retyears < 20)){
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - Disbustwo;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
				}

				if (retyears > 20 || retyears == 20) {
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - Disbustwo;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				}
			}

	 if ((hidlStrDisburse == 'Want2rdCF' || hidlStrDisburse == 'Club2nd3rdCF') && reqPCType == 10001198434 && ((cflag == 'Y') || (cFlagManual == 'Y')) ) { 
			var DisbusOne = (ManualREqAmount * 0.30).toFixed(2);
			
			
			if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
			//	alert("in exserv line no. 5"+retyears);
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
			}

			//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
			if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
			//	alert("in loanRepayAmt line no. 10");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
			}

			if (retyears > 20 || retyears == 20) {
				//alert("in loanRepayAmt line no. 20");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
			}
		}
	 }
	 if (reqType == 800058){
		 if ((hidlStrDisburse == 'Want3rdCF' || hidlStrDisburse == 'Club3rd4thCF') && reqPCType == 10001198434 && ((cflag == 'Y') || (cFlagManual == 'Y')) ) { 
				var DisbusOne = (ManualREqAmount * 0.25).toFixed(2);
				var Disbustwo = (ManualREqAmount * 0.25).toFixed(2);
				if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
				
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - Disbustwo;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
				}
				if ((retyears > 10 || retyears == 10) && (retyears < 20)){
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - Disbustwo;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
					//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
				}

				if (retyears > 20 || retyears == 20) {
					loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
					//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
					loanRepayAmt = loanRepayAmt - DisbusOne;
					loanRepayAmt = loanRepayAmt - Disbustwo;
					//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
					document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				}
			}
		 if (hidlStrDisburse == 'Want4thCF' && reqPCType == 10001198434 && ((cflag == 'Y') || (cFlagManual == 'Y')) ) {
			var DisbusOne = (ManualREqAmount * 0.25).toFixed(2);
		    var Disbustwo = (ManualREqAmount * 0.25).toFixed(2);
			var DisbusThree = (ManualREqAmount * 0.40).toFixed(2);
			
			if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
			//	alert("in exserv line no. 5"+retyears);
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				loanRepayAmt = loanRepayAmt - Disbustwo;
				loanRepayAmt = loanRepayAmt - DisbusThree;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
			}

			//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
			if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
			//	alert("in loanRepayAmt line no. 10");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				loanRepayAmt = loanRepayAmt - Disbustwo;
				loanRepayAmt = loanRepayAmt - DisbusThree;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
			}

			if (retyears > 20 || retyears == 20) {
				//alert("in loanRepayAmt line no. 20");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				loanRepayAmt = loanRepayAmt - Disbustwo;
				loanRepayAmt = loanRepayAmt - DisbusThree;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
			   }
			
		    }	
	 if ((hidlStrDisburse == 'Want2rdCF' || hidlStrDisburse == 'Club2nd3rdCF' || hidlStrDisburse == 'Club2nd3rd4thCF') && reqPCType == 10001198434 && ((cflag == 'Y') || (cFlagManual == 'Y')) ) { 
			
			var DisbusOne = (ManualREqAmount * 0.25).toFixed(2);
			
			if ((retyears > 5 || retyears == 5) && (retyears < 10)) {
			//	alert("in exserv line no. 5"+retyears);
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.80);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 5 10"+loanRepayAmt);
			}

			//if ((retyears > 10 || retyears == 10) || (retyears < 20)) {
			if ((retyears > 10 || retyears == 10) && (retyears < 20)) {
			//	alert("in loanRepayAmt line no. 10");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC) * 0.90);
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 10 20"+loanRepayAmt);
			}

			if (retyears > 20 || retyears == 20) {
				//alert("in loanRepayAmt line no. 20");
				loanRepayAmt = Math.round(Number(retmonths) * Number(basic7PC));
				//alert("loanRepayAmt>>>>>>"+loanRepayAmt);
				loanRepayAmt = loanRepayAmt - DisbusOne;
				//alert("loanRepayAmt>>>dd>>>"+loanRepayAmt);
				document.getElementById("txtLoanRepaymentCapacity").value = Math.round(loanRepayAmt);
				//alert("in loanRepayAmt line no. 20"+loanRepayAmt);
			}
		}
	 }
	 
	 
//	end 
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
	
	//Commented By Vivek sharma 07/02/2018
	/*alert('Houseadvance reqAmount 611 flag '+flag);
	alert('Houseadvance reqAmount 611 '+Math.round(Number(reqAmount.value)));
	alert('Houseadvance txtSanctionedAmountHBA 611 '+Math.round(Number(document.getElementById("txtSanctionedAmountHBA").value)));
	*/
	/*if (document.getElementById("txtSanctionedAmountHBA").value != "" && (Math.round(Number(reqAmount.value)) > Math.round(Number(document.getElementById("txtSanctionedAmountHBA").value)))) {
		
		//alert("Request amount cannot be greater than Sanction amount421");
		alert("Sanction amount cannot be greater than Request amount");
		reqAmount.value = "";
		reqAmount.focus();
	}

	if (document.getElementById("txtSanctionedAmountHBA2").value != ""
		&& (Math.round(Number(reqAmount.value)) > Math.round(Number(document
		.getElementById("txtSanctionedAmountHBA2").value)))) {
		//alert('Houseadvance 620');
		alert("Request amount cannot be greater than Sanction amount");
		reqAmount.value = "";
		reqAmount.focus();
	}*/
	if(((Math.round(Number(sancAmountHBAl))) > (Math.round(Number(reqAmountMCAl)))) || ((Math.round(Number(sancAmountHBA2))) > (Math.round(Number(reqAmountMCAl)))) ){
		alert("Sanction amount cannot be greater than Request amount");
		reqAmount.value = "";
		//reqAmount.focus();
		return false;
	}
	
	//End
	//alert("hii 1 reqType******"+reqType);
	if (reqAmount.value != "") {

		// 5th pay commission
		if (reqPCType == 800053) {
			if (reqType == 800037) {
				if (reqAmount.value > 180000) {
					alert('For Plot Purchase Advance Amount should be less than or Equals to 1,80,000');
					return false;
				}
			} else if (reqType == 800038) {
				if (reqAmount.value > 750000) {
					alert('For Consrtuction of Flat/House Advance Amount should be less than or Equals to 7,50,000');
					return false;
				}
			} else if (reqType == 800039) {
				if (reqAmount.value > 750000) {
					alert('For Ready Build Flat/House Purchase Advance Amount should be less than or Equals to 7,50,000');
					return false;
				}
			} else if (reqType == 800041) {
				if (reqAmount.value > 750000) {
					alert('For Old Flat/House Advance Amount should be less than or Equals to 7,50,000');
					return false;
				}
			} else if (reqType == 800042) {
				if (reqAmount.value > 750000) {
					alert('For Bank Loan Repayment Advance Amount should be less than or Equals to 7,50,000');
					return false;
				}
			} else if (reqType == 800058) {
				if (reqAmount.value > 750000) {
					alert('For Plot Purchase and Later Construction Advance Amount should be less than or Equals to 7,50,000');
					return false;
				}
			} else if (reqType == 800059) {
				if (reqAmount.value > 90000) {
					alert('For Special Repairs Advance Amount should be less than or Equals to 90,000');
					return false;
				}
			} else if (reqType == 800060) {
				if (reqAmount.value > 180000) {
					alert('For Extension of Rooms Advance Amount should be less than or Equals to 1,80,000');
					return false;
				}
			}
			if (flag != 1) {
				// var loanRepayAmt =
				// document.getElementById("txtLoanRepaymentCapacity").value
				//alert("in loanRepayAmt line no. 667"+loanRepayAmt);
				if (reqAmount.value > Number(loanRepayAmt)) {
					alert('Sanction Amount is greater than Loan Repayment Capacity of this Employee');
					reqAmount.value = "";
					reqAmount.focus();
				}
				// var basic = document.getElementById("hidBasicPay").value;
				var basicAndDP = (Number(basic) + Number(basic) * 0.5) * 50;
				if (reqAmount.value > Number(basicAndDP)) {
					alert('Maximum Sanction Amount for this Employee is ' + basicAndDP + '\n Sanction Amount is Greater than this Amount');
					reqAmount.value = "";
					reqAmount.focus();
				}
			}
		}

		// 7th pay commission
		else if(reqPCType == 10001198434){

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
			if (reqType == 800038 && cityclass == "X"
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


			if (reqType == 800038 && cityclass == "Y"
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



			if (reqType == 800038 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


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
			if (reqType == 800039 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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
			if (reqType != 800039 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			if (reqType == 800039 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800039 && cityclass != "Y"
				&& document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			if (reqType == 800039 && cityclass == "Z"
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

			if (reqType != 800039 && cityclass != "Z"
				&& document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			// bank loan repayment and first time changes 5/2/2021
			if (reqType == 800042 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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
			if (reqType != 800042 && cityclass != "X"
				&& document.getElementById("rdoyes").checked) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			if (reqType == 800042 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800042 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			if (reqType == 800042 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800042 && cityclass != "Z"
				&& document.getElementById("rdoyes").checked) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			// plot purchase 1st time
			if (reqType == 800037 && cityclass == "X" && document.getElementById("rdoyes").checked) {



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

			if (reqType != 800037 && reqType != 800058 && cityclass != "X"
				&& document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800037 && cityclass == "Y"
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

			if (reqType != 800037 && reqType != 800058 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';

				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800037 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800037 && reqType != 800058 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}


			// plot purchase & later construction
			if (reqType == 800058 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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
			if (reqType != 800058 && reqType !=800037 && cityclass != "X" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800058 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800058 && reqType !=800037 && cityclass != "Y" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800058 && cityclass == "Z"
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

			if (reqType != 800058 && reqType !=800037 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			// old flat/house
			if (reqType == 800041 && cityclass == "X"
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
			if (reqType != 800041 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}



			if (reqType == 800041 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800041 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}

			if (reqType == 800041 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800041 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}

			// special repairs
			if (reqType == 800059 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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


			if (reqType != 800059 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}

			if (reqType == 800059 && cityclass == "Y" && document.getElementById("rdoyes").checked) {


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

			if (reqType != 800059 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}

			if (reqType == 800059 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


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

			if (reqType != 800059 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}


			//first time for extension
			if (reqType == 800060 && cityclass == "X"	&& (document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800060 && reqType != 800038 && cityclass != "X"	&& !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}

			if (reqType == 800060 && cityclass == "Y" && (document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800060 && reqType != 800038 && cityclass != "Y" && (document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}

			if (reqType == 800060  && cityclass == "Z" && (document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800060 && reqType != 800038 && cityclass != "Z" && (document.getElementById("rdoyes").checked)) {
				//alert("inside to chjeck"); 
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
				//alert("inside to chjeck...Bye"); 
			}

			// if applying house loan for the second time  changes not done kavita
			if ((!document.getElementById("rdoyes").checked) && (reqType == 800037 || reqType == 800038 || reqType == 800039 || reqType == 800041 || reqType == 800042 || reqType == 800058 || reqType == 800059 || reqType == 800060 )) {
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
			if (reqType == 800038 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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


			if (reqType == 800038 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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



			if (reqType == 800038 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


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
			if (reqType == 800039 && cityclass == "X"
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
			if (reqType != 800039 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			if (reqType == 800039 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800039 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			if (reqType == 800039 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800039 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			// bank loan repayment and first time
			if (reqType == 800042 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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
			if (reqType != 800042 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			if (reqType == 800042 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800042 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			if (reqType == 800042 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800042 && cityclass != "Z"
				&& document.getElementById("rdoyes").checked) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			// plot purchase 1st time
			if (reqType == 800037 && cityclass == "X" && document.getElementById("rdoyes").checked) {



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

			if (reqType != 800037 && reqType != 800058 && cityclass != "X"	&& document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800037 && cityclass == "Y" && document.getElementById("rdoyes").checked) {


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

			if (reqType != 800037 && reqType != 800058 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';

				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800037 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800037 && reqType != 800058 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}


			// plot purchase & later construction
			if (reqType == 800058 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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
			if (reqType != 800058 && reqType !=800037 && cityclass != "X" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800058 && cityclass == "Y" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800058 && reqType !=800037 && cityclass != "Y" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800058 && cityclass == "Z"
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

			if (reqType != 800058 && reqType !=800037 && cityclass != "Z" && document.getElementById("rdoyes").checked) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			// old flat/house
			if (reqType == 800041 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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
			if (reqType != 800041 && cityclass != "X"
				&& document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}



			if (reqType == 800041 && cityclass == "Y"
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

			if (reqType != 800041 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}

			if (reqType == 800041 && cityclass == "Z" && document.getElementById("rdoyes").checked) {

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

			if (reqType != 800041 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}

			// special repairs
			if (reqType == 800059 && cityclass == "X" && document.getElementById("rdoyes").checked) {

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


			if (reqType != 800059 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}

			if (reqType == 800059 && cityclass == "Y" && document.getElementById("rdoyes").checked) {


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

			if (reqType != 800059 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}

			if (reqType == 800059 && cityclass == "Z" && document.getElementById("rdoyes").checked) {


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

			if (reqType != 800059 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}



			// if applying house loan for the second time

			// construction of flat or house
			if (reqType == 800038 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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


			if (reqType == 800038 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {
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



			if (reqType == 800038 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {


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
			if (reqType == 800039 && cityclass == "X"
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
			if (reqType != 800039 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			if (reqType == 800039 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800039 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}

			if (reqType == 800039 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800039 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("costofhouse").style.display = 'none';
				document.getElementById("costofhouse2").style.display = 'none';
				document.getElementById("txtcostofhouse").style.display = 'none';
			}


			// plot purchase and later construction 2nd time
			if (reqType == 800058 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800058  && reqType != 800037 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800058 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800058 && reqType != 800037 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800058 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800058 && reqType != 800037 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}



			// plot purchase
			if (reqType == 800037 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800037 && reqType != 800058 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800037 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800037  && reqType != 800058 && cityclass != "Y"	&& !(document.getElementById("rdoyes").checked)) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			if (reqType == 800037 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800037 && reqType != 800058 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {

				document.getElementById("costofland").style.display = 'none';
				document.getElementById("costofland2").style.display = 'none';
				document.getElementById("txtcostofland").style.display = 'none';
			}

			// old flat/house
			// old flat/house
			if (reqType == 800041 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800041 && cityclass != "X" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}



			if (reqType == 800041 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800041 && cityclass != "Y" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}

			if (reqType == 800041 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800041 && cityclass != "Z" && document.getElementById("rdoyes").checked) {
				document.getElementById("oldhousevalue").style.display = 'none';
				document.getElementById("oldhousevalue2").style.display = 'none';
				document.getElementById("txtoldhousevalue").style.display = 'none';
			}

			// bank loan repayment
			if (reqType == 800042 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800042 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			if (reqType == 800042 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800042 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			if (reqType == 800042 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800042 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("loanvalue").style.display = 'none';
				document.getElementById("loanvalue2").style.display = 'none';
				document.getElementById("txtloanvalue").style.display = 'none';
			}

			// extension of rooms 2nd time
			if (reqType == 800060 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800060 && reqType != 800038 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}

			if (reqType == 800060 && cityclass == "Y"	&& !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800060 &&  reqType != 800038 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}

			if (reqType == 800060 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800060 && reqType != 800038 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}





			//first time for extension
			if (reqType == 800060 && cityclass == "X" && (document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800060 && reqType != 800038 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}

			if (reqType == 800060 && cityclass == "Y" && (document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800060 && reqType != 800038 && cityclass != "Y" && (document.getElementById("rdoyes").checked)) {
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
			}

			if (reqType == 800060  && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800060 && reqType != 800038 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
				//alert("inside to chjeck"); 
				document.getElementById("costofconstruc").style.display = 'none';
				document.getElementById("costofconstruc2").style.display = 'none';
				document.getElementById("txtcostofconstruc").style.display = 'none';
				//alert("inside to chjeck...Bye"); 
			}

			// special repairs 2nd time
			if (reqType == 800059 && cityclass == "X" && !(document.getElementById("rdoyes").checked)) {

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
			if (reqType != 800059 && cityclass != "X" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}

			if (reqType == 800059 && cityclass == "Y" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800059 && cityclass != "Y" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}

			if (reqType == 800059 && cityclass == "Z" && !(document.getElementById("rdoyes").checked)) {

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

			if (reqType != 800059 && cityclass != "Z" && !(document.getElementById("rdoyes").checked)) {
				document.getElementById("costofrepair").style.display = 'none';
				document.getElementById("costofrepair2").style.display = 'none';
				document.getElementById("txtcostofrepair").style.display = 'none';
			}
		}
		}

}