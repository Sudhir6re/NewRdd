function SaveRequest()
{ 
	var requestValue = document.getElementById("hidRequestType").value;
	//ADDED BY VIVEK SHARMA 08/03/2017
	var getHieraracy = document.getElementById("getHieraracy").value;
	var lStrRequestType = document.getElementById("lStrRequestType").value;
	if(getHieraracy == 'NotGot'){
		var ans = confirm('Please Click on button before Saving Loan Request');
		if(ans){
			var url = "ifms.htm?actionFlag=updateHierActivateFlagService&lStrRequestType="+lStrRequestType;
			var myAjax = new Ajax.Request(url,
					{
				method: 'post',
				asynchronous: false,
				parameters:url,
				onSuccess: function(myAjax) {
				getsaveRequestMsg(myAjax);
			},
			onFailure: function(){ alert('Something went wrong...');} 
					} );
		}
		else if(!ans){

			return false;
		}

	}
	else{
		if(requestValue == '800028'){
			saveComputerAdvance();
		}else if(requestValue == '800029'){
			//alert("insaverequestTataTataTata"+requestValue);
			saveHouseAdvance();		
		}else if(requestValue == '800030'){
			saveMotorAdvance();
		}
		else if(requestValue == '10001198175'){
			saveHealthInsurance();
		}
	}
}

function getsaveRequestMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lblForFlag == "true"){
		var requestValue = document.getElementById("hidRequestType").value;
		if(requestValue == '800028'){
			saveComputerAdvance();
		}else if(requestValue == '800029'){
			saveHouseAdvance();		
		}else if(requestValue == '800030'){
			saveMotorAdvance();
		}
	}
}


function saveComputerAdvance(){

	var schemecode = document.getElementById("cmbSchemeCode").value;


	if(schemecode == -1){
		alert('Scheme Code cannot be left empty.');
		return false;
	}


	var documentCheckList = document.LNARequestProcessForm.cbDocCheckListCA;
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateComputerAdvance())
	{
		return false ;
	}
	var uri="ifms.htm?actionFlag=saveComputerAdvance";
	var url = "&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+runForm(0);
	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getSaveRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function saveHouseAdvance(){

	var schemecode = document.getElementById("cmbSchemeCode").value;
	var hidlStrDisburse = document.getElementById("hidlStrDisburse").value;
	var disbursement2 = document.getElementById("Disburse23").value;
	if(schemecode == -1){
		alert('Scheme Code cannot be left empty.');
		return false;
	}
	var documentCheckList;
	if(document.getElementById('ChecklistHBAForPP').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAPP;
		//alert("in documentCheckList 1 " + documentCheckList);
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBACF;
		//alert("in documentCheckList 2 " + documentCheckList);
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBARB;
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBABL;
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBASR;
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAER;
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAOF;
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBALC;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBA;
	}
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateHouseAdvance())
	{		
		return false ;
	}

	var uri="ifms.htm?actionFlag=saveHouseAdvance&hidlStrDisburse="+hidlStrDisburse+"&disbursement2="+disbursement2;
	var url = "&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+runForm(0);
	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getSaveRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function saveMotorAdvance(){

	var schemecode = document.getElementById("cmbSchemeCode").value;


	if(schemecode == -1){
		alert('Scheme Code cannot be left empty.');
		return false;
	}

	var documentCheckList;
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCAHC;
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCA;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCANew;
	}	
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);


	if(!validateMotorcarAdvance())
	{		
		return false;
	}

	var uri="ifms.htm?actionFlag=saveMotorCarAdvance";

	var url = "&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+runForm(0);

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,

		onSuccess: function(myAjax) {

		getSaveRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}

function getSaveRequestMsg(myAjax)
{

	try{

		var XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

		/* var loanAlert = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		if(loanAlert=="no")
		{*/


		var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		var lSevaarthID = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		var lUserType = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
		var lReqType = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;




		if(lblSaveFlag=="true") {

			alert('Request saved successfully');		
			//self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&txtSevaarthId="+lSevaarthID+"&criteria=1&requestType="+lReqType+"&userType="+lUserType+"&elementId=800025";
			self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&elementId=800011&userType=DEO";
		}

		/*else if(loanAlert=="yes")
		{
			alert('This employee has already taken two-wheeler loan advance.\n He/She can take only Motor Car Advance');

		}
		else if(loanAlert=="car")
		{
			alert('This employee has already taken four-wheeler loan advance.\n He/She can only take two-wheeler Advance');
		}*/

	}catch(error){
		alert("somethimg went wrong...."+ error);	
	}


}



function fwdComputerAdvance(flag){
	//alert("in fwdComputerAdvance..");
	//alert("flag is.." + flag);
	//alert("fwd"+document.getElementById("txtSancAmountCA").value);
	if(flag == 1){
		var check = document.getElementById("chkValidate").checked;


		if(!check){
			alert('Kindly select checkbox if employee has not taken loan previously.');
			return false;
		}
	}


	var documentCheckList = document.LNARequestProcessForm.cbDocCheckListCA;
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateComputerAdvance())
	{

		return false;
	}

	if(flag == 1){
		var schemecode = document.getElementById("cmbSchemeCode").value;


		if(schemecode == -1){
			alert('Scheme Code cannot be left empty.');
			return false;
		}
	}


	var uri="";	
	var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck;

	if(flag == 1){
		//alert("forwardCAToDEOVerifier");
		uri="ifms.htm?actionFlag=forwardCAToDEOVerifier";				
	}else if(flag == 2){
		//alert("forwardCAToHO");
		uri="ifms.htm?actionFlag=forwardCAToHO";
	}else if(flag == 3){
		//alert("forwardCAToAsstHOD");
		if ( !chkEmpty(document.getElementById("txtSanctionedDateCA"), "Sanctioned Date")){
			return false;
		}
		uri="ifms.htm?actionFlag=forwardCAToAsstHOD";

	}else if(flag == 4){
		//alert("forwardCAToHOD");
		uri="ifms.htm?actionFlag=forwardCAToHOD";
	}else if(flag == 5){
		//alert("forwardOfflineEntryCAToHOD");
		uri="ifms.htm?actionFlag=forwardOfflineEntryCAToHOD";
	}else if(flag == 9){
		//alert("forwardHOtoRHOAsst");
		uri="ifms.htm?actionFlag=forwardHOtoRHOAsst";
	}else if(flag == 8){
		//alert("forwardHODAsstToHOD");
		uri="ifms.htm?actionFlag=forwardHODAsstToHOD";
	}

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		if(flag == 1){
			getForwardAdvanceRequestMsg(myAjax);
		}else if(flag == 2){
			getForwardAdvanceRequestMsgDEO(myAjax);
		}else if(flag == 3){
			getForwardAdvanceRequestMsgHO(myAjax);
		}else if(flag == 4){
			getForwardAdvanceRequestMsgHOD(myAjax);
		}else if(flag == 5 ){
			getFwdAdvanceReqMsgHODAsst(myAjax);
		}else if(flag == 9 ){
			getFwdAdvanceReqMsgRHOAsst(myAjax);
		}else if(flag == 8 ){
			getFwdAdvanceReqMsgTOHOD(myAjax);
		}
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );

}


function fwdHouseAdvance(flag){

	//alert(" in fwdHouseAdvance5555555 "+flag);

	if(flag == 1){
		var check = document.getElementById("chkValidate").checked;
		var url1 = runForm(0);

		if(!check)
		{
			alert(' Kindly select checkbox if employee has not taken loan previously. ');
			return false;
		}

	}
	//alert(" hello ");

	var documentCheckList;
	if(document.getElementById("ChecklistHBAForPP").style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAPP;
		//alert(" documentCheckList 1 "+documentCheckList);
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBACF;
		//alert(" documentCheckList 2 "+documentCheckList);
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBARB;
		//alert(" documentCheckList 3 "+documentCheckList);
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		//alert(" documentCheckList 4 "+documentCheckList);
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBABL;
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		//alert(" documentCheckList 5 "+documentCheckList);
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBASR;
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		//alert(" documentCheckList 6 "+documentCheckList);
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAER;
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		//alert(" documentCheckList 7 "+documentCheckList);
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAOF;
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		//alert(" documentCheckList 8 "+documentCheckList);
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBALC;
	}else{

		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBA;
		//alert(" documentCheckList 9 "+documentCheckList);
	}
	var CheckBoxList="";
	var CheckedUncheck="";
	//alert(documentCheckList.length);
	for(var i=0; i < documentCheckList.length; i++){
		//alert("Inside for");
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			//alert("Inside if");
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
			//alert("Inside else");
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	// alert(CheckBoxList);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	//alert(CheckedUncheck);
	if(validateHouseAdvance()==false)
	{		//alert("Inside valllll");
		return false;
	}
	var schemecode = document.getElementById("cmbSchemeCode").value;
	//alert("schemecode 1213");

	var disbursement2 = document.getElementById("Disburse23").value;
	//alert("disbursement2....*** "+disbursement2);

	var hidlStrDisburse = document.getElementById("hidlStrDisburse").value;
	//alert("hidlStrDisburse....*** "+hidlStrDisburse);
	var cmbCityClass = document.getElementById("cmbCityClass").value;
	//alert("cityClass in loan And advance"+cmbCityClass);

	var isManualflag = document.getElementById("hidisManualflag").value;
	//alert("isManualflag..."+isManualflag);

	if(schemecode == -1)
	{
		alert('Scheme Code cannot be left empty.');
		return false;
	}

	//alert(" flag iN LOAN AND ADVANCE "+flag);

	if(flag == 8 || flag == 9){
		var pk = document.getElementById("lStrPKValue").value;
		var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+"&pk="+pk;
		//alert("pk is.. " + pk);
	}
	else
	{
		var uri="";	
		var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck;
	}
	//alert(" in url is "+flag);

	if(flag == 1){
		uri = "ifms.htm?actionFlag=forwardHBAToDEOVerifier&disbursement2="+disbursement2+"&hidlStrDisburse="+hidlStrDisburse+"&cmbCityClass="+cmbCityClass+"&isManualflag="+isManualflag;	
		//alert("uri "+ uri);
	}
	else if(flag == 2){
		//alert("in flag 2");
		uri="ifms.htm?actionFlag=forwardHBAToHO&isManualflag="+isManualflag;
		//alert(uri);

	}else if(flag == 3){
		var subType = document.getElementById("cmbHBAType").value;
		if (subType==800038){

		}else if (subType==800058){

		}else{
			if (!chkEmpty(document.getElementById("txtSanctionedDateHBA"), "Sanctioned Date"))
			{
				return false;
			}
		}
		uri="ifms.htm?actionFlag=forwardHBAToAsstHOD&isManualflag="+isManualflag;
	}else if(flag == 4){
		uri="ifms.htm?actionFlag=forwardHBAToHOD&isManualflag="+isManualflag;
	}else if(flag == 5){
		uri="ifms.htm?actionFlag=forwardOfflineEntryHBAToHOD&isManualflag="+isManualflag;
	}else if(flag == 9){
		uri="ifms.htm?actionFlag=forwardRHOAsstToRHOHBA&isManualflag="+isManualflag;

	}else if(flag == 8){
		uri="ifms.htm?actionFlag=forwardHODAsstToHODHBA&isManualflag="+isManualflag;
	}
	//alert('cityClass is'+); cmbCityClass
	//alert(uri);
	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		if(flag == 1){
			getForwardAdvanceRequestMsg(myAjax);
		}else if(flag == 2){
			getForwardAdvanceRequestMsgDEO(myAjax);
		}else if(flag == 3){
			getForwardAdvanceRequestMsgHO(myAjax);
		}else if(flag == 4){
			getForwardAdvanceRequestMsgHOD(myAjax);
		}else if(flag == 5 ){
			getFwdAdvanceReqMsgHODAsst(myAjax);
		}else if(flag == 9 ){
			getFwdAdvanceReqMsgToRHOHBA(myAjax);
		}else if(flag == 8 ){
			getFwdAdvanceReqMsgToHODHBA(myAjax);
		}
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );


}
function fwdMotorCarAdvance(flag){
	//alert("in line no 436..fwdMotorCarAdvance " + flag);
	if(flag == 1){
		var check = document.getElementById("chkValidate").checked;


		if(!check){
			alert('Kindly select checkbox if employee has not taken loan previously.');
			return false;
		}
	}

	var documentCheckList;
	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCAHC;
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCA;
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCANew;
	}	
	var CheckBoxList="";
	var CheckedUncheck="";
	for(var i=0; i < documentCheckList.length; i++){
		CheckBoxList= CheckBoxList + documentCheckList[i].value + ",";
		if(documentCheckList[i].checked == true){
			CheckedUncheck= CheckedUncheck + "Y"	+ ",";
		}else{
			CheckedUncheck= CheckedUncheck + "N"	+ ",";
		}
	}
	CheckBoxList = CheckBoxList.substring(0, CheckBoxList.length-1);
	CheckedUncheck = CheckedUncheck.substring(0, CheckedUncheck.length-1);
	if(!validateMotorcarAdvance())
	{

		return false;
	}

	var schemecode = document.getElementById("cmbSchemeCode").value;


	if(schemecode == -1){
		alert('Scheme Code cannot be left empty.');
		return false;
	}
	var pk = document.getElementById("lStrPKValue").value;

	var uri="";
	var url = runForm(0)+"&CheckBoxList="+CheckBoxList+"&CheckedUncheck="+CheckedUncheck+"&pk="+pk;


	//alert("pk is "+ pk);

	if(flag == 1){
		uri="ifms.htm?actionFlag=forwardMCAToDEOVerifier";				
	}else if(flag == 2){
		uri="ifms.htm?actionFlag=forwardMCAToHO";
	}else if(flag == 3){
		if ( !chkEmpty(document.getElementById("txtSanctionedDateMCA"), "Sanctioned Date")){
			return false;
		}
		uri="ifms.htm?actionFlag=forwardMCAToAsstHOD";
	}else if(flag == 4){
		uri="ifms.htm?actionFlag=forwardMCAToHOD";
	}else if(flag == 5){
		uri="ifms.htm?actionFlag=forwardOfflineEntryMCAToHOD";
	}else if(flag == 9){
		uri="ifms.htm?actionFlag=forwardRHOAsstToRHOMotor";
	}else if(flag == 8){
		uri="ifms.htm?actionFlag=forwardHODAsstToHODMotor";
	}
	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		if(flag == 1){
			getForwardAdvanceRequestMsg(myAjax);
		}else if(flag == 2){
			getForwardAdvanceRequestMsgDEO(myAjax);
		}else if(flag == 3){
			//alert("in flag 3");
			getForwardAdvanceRequestMsgHO(myAjax);
		}else if(flag == 4){
			getForwardAdvanceRequestMsgHOD(myAjax);
		}else if(flag == 5 ){
			getFwdAdvanceReqMsgHODAsst(myAjax);
		}else if(flag == 9 ){
			getFwdAdvanceReqMsgToRHOMotor(myAjax);
		}else if(flag == 8 ){
			getFwdAdvanceReqMsgToHODMotor(myAjax);
		}

	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );

}


function getForwardAdvanceRequestMsgHO(myAjax)
{


//	alert("in getForwardAdvanceRequestMsgHO ");

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {

		alert('Request has been forwarded to Regional Head Officer');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800022&userType=HO";
	}

}


function getForwardAdvanceRequestMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrTransId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrOrgEmpId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var currDate = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	if (lblForwardFlag=="true") {

		alert('Transaction ID '+lStrTransId+' has been generated successfully against the Sevaarth ID '+lStrOrgEmpId+' for the Request Dated '+currDate);		
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&elementId=800011&userType=DEO";
	}
}
function getFwdAdvanceReqMsgHODAsst(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lStrTransId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lStrOrgEmpId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var currDate = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	if (lblForwardFlag=="true") {

		alert('Transaction ID '+lStrTransId+' has been generated successfully against the Sevaarth ID '+lStrOrgEmpId+' for the Request Dated '+currDate);
		alert("Form forwarded successfully to HOD");
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&elementId=800025&userType=HODASST2";
	}
}
function getForwardAdvanceRequestMsgDEO(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {

		alert('Request has been forwarded to Head Officer');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800021&userType=DEOAPP";
	}

}

function getFwdAdvanceReqMsgRHOAsst(myAjax)
{
	//alert("getFwdAdvanceReqMsgRHOAsst");
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert("lblForwardFlag:::::::"+lblForwardFlag);
	if (lblForwardFlag=="forwarded") {

		alert('Request has been forwarded to Regional Head Officer(RHO) ');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=1900001595&userType=RHOASST2";
	}

}

function getFwdAdvanceReqMsgToRHOMotor(myAjax)
{
	//alert("getFwdAdvanceReqMsgRHOAsst");
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert("lblForwardFlag:::::::"+lblForwardFlag);
	if (lblForwardFlag=="forwarded") {

		alert('Request has been forwarded to Regional Head Officer(RHO) in motor ');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=1900001595&userType=RHOASST2";
	}

}

function getFwdAdvanceReqMsgToRHOHBA(myAjax)
{
	//alert("getFwdAdvanceReqMsgRHOAsst");
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert("lblForwardFlag:::::::"+lblForwardFlag);
	if (lblForwardFlag=="forwarded") {

		alert('Request has been forwarded to Regional Head Officer(RHO) in HBA ');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=1900001595&userType=RHOASST2";
	}

}

function getFwdAdvanceReqMsgToHODMotor(myAjax)
{
	//alert("getFwdAdvanceReqMsgTOHOD");
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if (lblForwardFlag=="forwarded") {

		alert('Request has been forwarded to Head Of Department (HOD)');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=1900001602&userType=HODASST4";
	}

}

function getFwdAdvanceReqMsgToHODHBA(myAjax)
{
	//alert("getFwdAdvanceReqMsgTOHOD");
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if (lblForwardFlag=="forwarded") {

		alert('Request has been forwarded to Head Of Department (HOD)');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=1900001602&userType=HODASST4";
	}

}


function getForwardAdvanceRequestMsgHOD(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblForwardFlag=="true") {

		alert('Request has been forwarded to Head Of Department');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800023&userType=HODASST";
	}

}


function getFwdAdvanceReqMsgTOHOD(myAjax)
{
	//alert("getFwdAdvanceReqMsgTOHOD");
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if (lblForwardFlag=="forwarded") {

		alert('Request has been forwarded to Head Of Department (HOD)');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=1900001602&userType=HODASST4";
	}

}


function approveComputerAdvance(){
//	gokarna on 29/09/2015 for change of scheme code from ho hod

	//alert("in comp advance");
	if(!validateComputerAdvance())
	{		
		return false;
	}
	if ( !chkEmpty(document.getElementById("txtSanctionedDateCA"), "Sanctioned Date")) 
	{	

		return false;
	}


	var txtDemandNo= document.getElementById("txtDemandNo").value;
	//alert(txtDemandNo);
	var txtSchemeName= document.getElementById("txtSchemeName").value;
	//alert(txtSchemeName);
	var txtSubMajorHead= document.getElementById("txtSubMajorHead").value;	
	//alert(txtSubMajorHead);
	var txtMinorHead= document.getElementById("txtMinorHead").value;
	//alert(txtMinorHead);
	var txtGroupHead= document.getElementById("txtGroupHead").value;
	//alert(txtGroupHead);
	var txtSubHead= document.getElementById("txtSubHead").value;	
	//alert(txtSubHead);

	//alert("schenecode"+cmbSchemeCode);
	//var txtMajorHead= document.getElementById("txtMajorHead").value;
	var cmbSchemeCode = document.getElementById("cmbSchemeCode").value;
	//alert("cmbSchemeCode"+cmbSchemeCode);
	if(cmbSchemeCode==null)
	{
		//alert("null");
		cmbSchemeCode = document.getElementById("hidlStrSchemeCode").value;

	}

	var hidComAdvanceId = document.getElementById("hidComAdvanceId").value;
	//alert("hidComAdvanceId"+hidComAdvanceId);

	var txtSanctionedDateCA =document.getElementById("txtSanctionedDateCA").value;
	//alert("txtSanctionedDateCA"+txtSanctionedDateCA);

	var txtSancAmountCA =document.getElementById("txtSancAmountCA").value;
	//alert("txtSancAmountCA"+txtSancAmountCA);

	var txtSancInstallmentsCA=document.getElementById("txtSancInstallmentsCA").value;
	//alert("txtSancInstallmentsCA"+txtSancInstallmentsCA);

	var txtInstallmentAmountCA=document.getElementById("txtInstallmentAmountCA").value;
	//alert("txtInstallmentAmountCA"+txtInstallmentAmountCA);

	var txtOddInstallmentAmtCA=document.getElementById("txtOddInstallmentAmtCA").value;
	//alert("txtOddInstallmentAmtCA"+txtOddInstallmentAmtCA);


	var cmbOddInstallNoCA=document.getElementById("cmbOddInstallNoCA").value;

	//alert(txtSancAmountCA+txtSancInstallmentsCA+txtInstallmentAmountCA);

	//alert("cmbOddInstallNoCA"+cmbOddInstallNoCA);
	//var txtOrderNoCA=document.getElementById("txtOrderNoCA").value;
	//alert("txtOrderNoCA"+txtOrderNoCA);
	//var txtOrderDateCA=document.getElementById("txtOrderDateCA").value;
	//alert("txtOrderDateCA"+txtOrderDateCA);
	//var cmbOddInterestInstallNoMCA=document.getElementById("cmbOddInterestInstallNoMCA").value;

	var uri = "ifms.htm?actionFlag=approveComputerAdvance";

	//var url = runForm(0);
	//var url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidComAdvanceId='+hidComAdvanceId+'&txtSanctionedDateCA='+txtSanctionedDateCA+'&txtSancAmountCA='+txtSancInstallmentsCA+'&txtSancInstallmentsCA='+txtSancInstallmentsCA+'&txtInstallmentAmountCA='+txtInstallmentAmountCA+'&txtOddInstallmentAmtCA='+txtOddInstallmentAmtCA+'&txtOddInstallmentAmtCA='+cmbOddInstallNoCA+'&cmbOddInstallNoCA='+cmbOddInstallNoCA+'&txtOrderNoCA='+txtOrderNoCA+'&txtOrderDateCA='+txtOrderDateCA;

	var url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidComAdvanceId='+hidComAdvanceId+'&txtSanctionedDateCA='+txtSanctionedDateCA+'&txtSancAmountCA='+txtSancAmountCA+'&txtSancInstallmentsCA='+txtSancInstallmentsCA+'&txtInstallmentAmountCA='+txtInstallmentAmountCA+'&txtOddInstallmentAmtCA='+txtOddInstallmentAmtCA+'&txtOddInstallmentAmtCA='+cmbOddInstallNoCA+'&cmbOddInstallNoCA='+cmbOddInstallNoCA;

	//alert(url);

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getApprovedRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function approveHouseAdvance(){

	//alert("approveHouseAdvance");

	var txtSancPrinInstallHBA=0.0;
	//alert("ONE"+txtSancPrinInstallHBA);
	var subtype = document.getElementById("cmbHBAType").value;

	//alert("TWO"+subtype);
	if(document.getElementById("txtSanctionedDateHBA").value!="" && document.getElementById("txtSanctionedDateHBA").value!="-1")
		var date=document.getElementById("txtSanctionedDateHBA").value;
	else{
		//alert("gayathri");
		document.getElementById("txtSanctionedDateHBA").disabled=false;
		document.getElementById("txtSanctionedDateHBA").Value="";
	}

	//alert(date);
	var date1=document.getElementById("txtReleaseDate1").value;
	//alert("THREE"+date1);

	if(!validateHouseAdvance())
	{		
		return false;
	}


	if(subtype != 800038 && subtype != 800058){
		if(date=="" || date == "-1"){
			alert('Sanctioned Date cannot be empty.');
			document.getElementById("txtSanctionedDateHBA").value="";
			return false;
		}
		if(document.getElementById("txtSanctionedDateHBA").value=="" || document.getElementById("txtSanctionedDateHBA").value=="-1"){
			alert('Sanctioned Date cannot be empty.');
			document.getElementById("txtSanctionedDateHBA").value="";
			return false;
		}
	}

	if(subtype == 800038 || subtype == 800058){
		if(date1=="" || date1 == "-1"){
			alert('Sanctioned Date cannot be empty.');
			return false;
		}
	}
	var txtReqAmountHBA = document.getElementById("txtReqAmountHBA").value;  //Added by vivek 06_02_2018
	var cmbCityClass = document.getElementById("cmbCityClass").value; //Added by vivek 14_02_2018
	var txtInterestRateHBA = document.getElementById("txtInterestRateHBA").value;
	var txtcostofconstruc = document.getElementById("txtcostofconstruc").value;
	var txtSanctionedAmountHBA = document.getElementById("txtSanctionedAmountHBA").value;
	//alert("txtPrincipalInstallmentAmtHBA"+txtSanctionedAmountHBA);
	txtSancPrinInstallHBA  = document.getElementById("txtSancPrinInstallHBA").value;
	//alert("txtPrincipalInstallmentAmtHBA"+txtSancPrinInstallHBA);

	var txtPrincipalInstallmentAmtHBA  =document.getElementById("txtPrincipalInstallmentAmtHBA").value;
	//alert("txtPrincipalInstallmentAmtHBA"+txtPrincipalInstallmentAmtHBA);
	//if(document.getElementById("txtPrincipalInstallmentAmtHBA").value==0.0)
	//{
	//txtPrincipalInstallmentAmtHBA =0.0;
	//}

	/*if (!chkEmpty(document.getElementById("txtSanctionedDateHBA"), "Sanctioned Date")){
		return false;
	}*/


	/*var subType = document.getElementById("cmbHBAType").value;

	if (subType == 800038 || subType == 800058){
	var Disbursement2 = document.getElementById("txtDisbursement2").value;
	var Disbursement3 = document.getElementById("txtDisbursement3").value;
	}
	 if(subType == 800058){
	var Disbursement4 = document.getElementById("txtDisbursement4").value;
	}


	 if (subType==800038){
		if(!chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")){
			return false;
		}
		if(Disbursement2 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")){
				return false;
			}
		}
		if(Disbursement3 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date"))
			{
				return false;
			}
		} 

	}else if (subType==800058){
		if(!chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")){
			return false;
		}		

		if(Disbursement2 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")){
				return false;
			}
		}
		if(Disbursement3 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date")){
				return false;
			}
		}
		if(Disbursement4 != ""){
			if(!chkEmpty(document.getElementById("txtReleaseDate4"), "Sanction Date"))
			{
				return false;
			}
		} 

	}else{*/

	//}
	//alert(555);
	var txtOddInterestInstallmentAmtHBA=0.0;
	//alert("HELLO"+document.getElementById("cmbOddPrincipalInstallNoHBA").value);
	if(document.getElementById("cmbOddPrincipalInstallNoHBA").value==-1)
	{
		//alert("HELLO111");
		cmbOddPrincipalInstallNoHBA=0.0;
	}
	/*var cmbOddInterestInstallNoHBA=0.0;
	document.getElementById("cmbOddInterestInstallNoHBA").value=cmbOddInterestInstallNoHBA ;
	alert("HELLO122");*/
	/*if(document.getElementById("cmbOddInterestInstallNoHBA").value==0.0)
	{
		alert("HELLO133");
		cmbOddInterestInstallNoHBA =0.0;
	}*/

	var cmbSchemeCode = document.getElementById("cmbSchemeCode").value;
	//alert("cmbSchemeCode"+cmbSchemeCode);

	var disbursement2 = document.getElementById("Disburse23").value;
	//alert("disbursement2.... "+disbursement2);

	var disbursementType1 = document.getElementById("disbursementType").value;


	//alert("disbursementType1 is...### " + disbursementType1);
	var userTypeforHOD = document.getElementById("hidUserTypeee").value;
	//alert("userTypeforHOD is...### " +userTypeforHOD);
	if(userTypeforHOD == 'HOD'){
		disbursementType1 =document.getElementById("hidlStrDisburse").value;
	}

	if(cmbSchemeCode==null)
	{
		//alert("null");
		cmbSchemeCode = document.getElementById("hidlStrSchemeCode").value;

	}
	var txtDemandNo= document.getElementById("txtDemandNo").value;
	//alert(txtDemandNo);
	var txtSchemeName= document.getElementById("txtSchemeName").value;
	//alert(txtSchemeName);
	var txtSubMajorHead= document.getElementById("txtSubMajorHead").value;	
	//alert(txtSubMajorHead);
	var txtMinorHead= document.getElementById("txtMinorHead").value;
	//alert(txtMinorHead);
	var txtGroupHead= document.getElementById("txtGroupHead").value;
	//alert(txtGroupHead);
	var txtSubHead= document.getElementById("txtSubHead").value;	
	//alert(txtSubHead);

	//var txtMajorHead= document.getElementById("txtMajorHead").value;
	var hidHouseAdvanceId = document.getElementById("hidHouseAdvanceId").value;
	//alert("hidHouseAdvanceId"+hidHouseAdvanceId);


	var txtSanctionedDateHBA = document.getElementById("txtSanctionedDateHBA").value;
	//alert("txtSanctionedDateHBA"+txtSanctionedDateHBA);
	//alert("txtSancPrinInstallHBA"+txtSancPrinInstallHBA);
	//var txtInterInstallmentAmountHBA  = document.getElementById("txtInterInstallmentAmountHBA").value;
	//alert("txtInterInstallmentAmountHBA"+txtInterInstallmentAmountHBA);
	var txtOddPrincipalInstallmentAmtHBA  = document.getElementById("txtOddPrincipalInstallmentAmtHBA").value;
	//alert("txtOddPrincipalInstallmentAmtHBA"+txtOddPrincipalInstallmentAmtHBA);
	//var txtOddInterestInstallmentAmtHBA = document.getElementById("txtOddInterestInstallmentAmtHBA").value;
	//alert("txtOddInterestInstallmentAmtHBA"+txtOddInterestInstallmentAmtHBA);
	//alert("cmbOddInterestInstallNoHBA"+cmbOddInterestInstallNoHBA);
//	gokarna 05/10/2015
	var subType = document.getElementById("cmbHBAType").value;
	//alert("subType"+subType);
	if(subType==800038 || subType==800058)
	{
		//alert("inside this");
		var txtSanctionedAmountHBA2 = document.getElementById("txtSanctionedAmountHBA2").value;
		//alert("txtSanctionedAmountHBA2"+txtSanctionedAmountHBA2);
		var txtPrincipalAmountHBA = document.getElementById("txtPrincipalAmountHBA").value;
		//alert("txtPrincipalAmountHBA"+txtPrincipalAmountHBA);
		var txtInterestAmountHBA2 = document.getElementById("txtInterestAmountHBA2").value;
		//alert("txtInterestAmountHBA2"+txtInterestAmountHBA2);
		var txtDisbursement1 = document.getElementById("txtDisbursement1").value;
		//alert("txtDisbursement1"+txtDisbursement1);
		var txtDisbursement2 = document.getElementById("txtDisbursement2").value;
		//alert("txtDisbursement2"+txtDisbursement2);
		var txtDisbursement3 = document.getElementById("txtDisbursement3").value;
		//alert("txtDisbursement3"+txtDisbursement3);

		var txtReleaseDate1 = document.getElementById("txtReleaseDate1").value;
		//alert("txtReleaseDate1"+txtReleaseDate1);

		var txtSancPrinInstallHBA2 = document.getElementById("txtSancPrinInstallHBA2").value;
		//alert("txtSancPrinInstallHBA2^^^^^^^^^^"+txtSancPrinInstallHBA2);
		var txtSancInterInstallHBA2 = document.getElementById("txtSancInterInstallHBA2").value;
		//alert("txtSancInterInstallHBA2"+txtSancInterInstallHBA2);
		var txtPrinInstallmentAmountHBA = document.getElementById("txtPrinInstallmentAmountHBA").value;
		//alert("txtPrinInstallmentAmountHBA"+txtPrinInstallmentAmountHBA);
		var txtInterInstallmentAmountHBA2 = document.getElementById("txtInterInstallmentAmountHBA2").value;
		//alert("txtInterInstallmentAmountHBA2"+txtInterInstallmentAmountHBA2);
		var txtOddPrincipalInstallmentAmtHBA2 = document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value;
		//alert("txtOddPrincipalInstallmentAmtHBA2"+txtOddPrincipalInstallmentAmtHBA2);
		var txtOddInterestInstallmentAmtHBA2 = document.getElementById("txtOddInterestInstallmentAmtHBA2").value;
		//alert("txtOddInterestInstallmentAmtHBA2"+txtOddInterestInstallmentAmtHBA2);
		var cmbOddPrincipalInstallNoHBA2 = document.getElementById("cmbOddPrincipalInstallNoHBA2").value;
		//alert("cmbOddPrincipalInstallNoHBA2"+cmbOddPrincipalInstallNoHBA2);
		//alert(cmbOddPrincipalInstallNoHBA2);
		//var cmbOddInterestInstallNoHBA2 = document.getElementById("cmbOddInterestInstallNoHBA2").value;
		//alert("cmbOddInterestInstallNoHBA2"+cmbOddInterestInstallNoHBA2);
		//alert(cmbOddInterestInstallNoHBA2);
		//alert(txtSanctionedAmountHBA2);
		//alert(txtPrincipalAmountHBA);
		//alert(txtDisbursement1);
		//alert(txtDisbursement2);
		//alert(txtDisbursement3);
		//alert(txtReleaseDate1);
		//alert(txtReleaseDate2);
		//alert(txtReleaseDate3);
		//alert(txtSancPrinInstallHBA2);
		//alert(txtSancInterInstallHBA2);
		//alert(txtPrincipalAmountHBA);
		//alert(txtPrinInstallmentAmountHBA);
		//alert(txtInterInstallmentAmountHBA2);
		//alert(txtOddPrincipalInstallmentAmtHBA2);
		//alert(txtOddInterestInstallmentAmtHBA2);
		//alert(cmbOddPrincipalInstallNoHBA2);
		//alert(cmbOddInterestInstallNoHBA2);
		var uri = "ifms.htm?actionFlag=approveHouseAdvance&disbursementType1="+disbursementType1;
//		var url = runForm(0);
		//var url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidHouseAdvanceId='+hidHouseAdvanceId+'&txtSanctionedDateHBA='+txtSanctionedDateHBA+'&txtSanctionedAmountHBA='+txtSanctionedAmountHBA+'&txtInterestAmountHBA='+txtInterestAmountHBA+'&txtSancPrinInstallHBA='+txtSancPrinInstallHBA+'&txtSancInterInstallHBA='+txtSancInterInstallHBA+'&txtPrincipalInstallmentAmtHBA='+txtPrincipalInstallmentAmtHBA+'&txtInterInstallmentAmountHBA='+txtInterInstallmentAmountHBA+'&txtInterestAmountHBA='+txtInterestAmountHBA+'&txtOddPrincipalInstallmentAmtHBA='+txtOddPrincipalInstallmentAmtHBA+'&txtOddInterestInstallmentAmtHBA='+txtOddInterestInstallmentAmtHBA+'&cmbOddPrincipalInstallNoHBA='+cmbOddPrincipalInstallNoHBA+'&cmbOddInterestInstallNoHBA='+cmbOddInterestInstallNoHBA;
		var url = '&txtSanctionedAmountHBA2='+txtSanctionedAmountHBA2+'&txtPrincipalAmountHBA='+txtPrincipalAmountHBA+'&txtInterestAmountHBA2='+txtSubMajorHead+'&txtMinorHead='+txtInterestAmountHBA2+'&txtDisbursement1='+txtDisbursement1+'&txtDisbursement2='+txtDisbursement2+'&txtDisbursement3='+txtDisbursement3+'&txtReleaseDate1='+txtReleaseDate1
		+'&txtPrinInstallmentAmountHBA='+txtPrinInstallmentAmountHBA+'&txtInterInstallmentAmountHBA2='+txtInterInstallmentAmountHBA2+'&txtOddPrincipalInstallmentAmtHBA2='+txtOddPrincipalInstallmentAmtHBA2+'&txtOddInterestInstallmentAmtHBA2='+txtOddInterestInstallmentAmtHBA2+'&hidHouseAdvanceId='+hidHouseAdvanceId
		+'&cmbOddPrincipalInstallNoHBA2='+cmbOddPrincipalInstallNoHBA2+'&txtReqAmountHBA='+txtReqAmountHBA+'&txtSancPrinInstallHBA2='+txtSancPrinInstallHBA2+'&txtSancInterInstallHBA2='+txtSancInterInstallHBA2+'&cmbCityClass='+cmbCityClass+'&cmbSchemeCode='+cmbSchemeCode+'&txtInterestRateHBA='+txtInterestRateHBA+'&txtcostofconstruc='+txtcostofconstruc; //Added by vivek 06_02_2018
	}

	else
	{
		//alert("in else :::"+document.getElementById("txtOddPrincipalInstallmentAmtHBA").value)
		uri = "ifms.htm?actionFlag=approveHouseAdvance";
//		var url = runForm(0);
		//var url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidHouseAdvanceId='+hidHouseAdvanceId+'&txtSanctionedDateHBA='+txtSanctionedDateHBA+'&txtSanctionedAmountHBA='+txtSanctionedAmountHBA+'&txtInterestAmountHBA='+txtInterestAmountHBA+'&txtSancPrinInstallHBA='+txtSancPrinInstallHBA+'&txtSancInterInstallHBA='+txtSancInterInstallHBA+'&txtPrincipalInstallmentAmtHBA='+txtPrincipalInstallmentAmtHBA+'&txtInterInstallmentAmountHBA='+txtInterInstallmentAmountHBA+'&txtInterestAmountHBA='+txtInterestAmountHBA+'&txtOddPrincipalInstallmentAmtHBA='+txtOddPrincipalInstallmentAmtHBA+'&txtOddInterestInstallmentAmtHBA='+txtOddInterestInstallmentAmtHBA+'&cmbOddPrincipalInstallNoHBA='+cmbOddPrincipalInstallNoHBA+'&cmbOddInterestInstallNoHBA='+cmbOddInterestInstallNoHBA;
		//url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidHouseAdvanceId='+hidHouseAdvanceId+'&txtSanctionedDateHBA='+txtSanctionedDateHBA+'&txtSanctionedAmountHBA='+txtSanctionedAmountHBA+'&cmbOddPrincipalInstallNoHBA='+cmbOddPrincipalInstallNoHBA+'&cmbOddInterestInstallNoHBA='+cmbOddInterestInstallNoHBA;
		url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidHouseAdvanceId='+hidHouseAdvanceId+'&txtSanctionedDateHBA='+txtSanctionedDateHBA+'&txtSanctionedAmountHBA='+txtSanctionedAmountHBA+'&cmbOddPrincipalInstallNoHBA='+document.getElementById("cmbOddPrincipalInstallNoHBA").value
		+'&txtOddInterestInstallmentAmtHBA='+txtOddInterestInstallmentAmtHBA
		+'&txtSancPrinInstallHBA='+txtSancPrinInstallHBA+'&txtPrincipalInstallmentAmtHBA='+txtPrincipalInstallmentAmtHBA+'&txtOddPrincipalInstallmentAmtHBA='+txtOddPrincipalInstallmentAmtHBA+'&txtReqAmountHBA='+txtReqAmountHBA+'&txtSancPrinInstallHBA2='+txtSancPrinInstallHBA2+'&txtSancInterInstallHBA2='+txtSancInterInstallHBA2+'&cmbCityClass='+cmbCityClass+'&cmbSchemeCode='+cmbSchemeCode+'&txtInterestRateHBA='+txtInterestRateHBA+'&txtcostofconstruc='+txtcostofconstruc; //Added by vivek 06_02_2018;
	}
//	alert(url);
	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getApprovedRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function approveMotorCarAdvance(){
//	alert("in approveMotorCarAdvance ");
	if(!validateMotorcarAdvance())
	{		

		return false;
	}
	var txtDemandNo= document.getElementById("txtDemandNo").value;
	var txtSchemeName= document.getElementById("txtSchemeName").value;	
	var txtSubMajorHead= document.getElementById("txtSubMajorHead").value;	
	var txtMinorHead= document.getElementById("txtMinorHead").value;
	var txtGroupHead= document.getElementById("txtGroupHead").value;
	var txtSubHead= document.getElementById("txtSubHead").value;	

//	var txtMajorHead= document.getElementById("txtMajorHead").value;



	var cmbSchemeCode = document.getElementById("cmbSchemeCode").value;
//	alert("cmbSchemeCode"+cmbSchemeCode);
	if(cmbSchemeCode==null)
	{
		//alert("null");
		cmbSchemeCode = document.getElementById("hidlStrSchemeCode").value;

	}
	var hidMotorAdvanceId = document.getElementById("hidMotorAdvanceId").value;
	var txtSanctionedDateMCA =document.getElementById("txtSanctionedDateMCA").value;
	var txtSancAmountMCA =document.getElementById("txtSancAmountMCA").value;
	var txtInterestAmountMCA=document.getElementById("txtInterestAmountMCA").value;
	var txtSancPrincipalInstallMCA=document.getElementById("txtSancPrincipalInstallMCA").value;
	var txtSancInterInstallMCA=document.getElementById("txtSancInterInstallMCA").value;
	var txtPrinInstallmentAmountMCA=document.getElementById("txtPrinInstallmentAmountMCA").value;
	var txtInterInstallmentAmountMCA=document.getElementById("txtInterInstallmentAmountMCA").value;
	var txtOddPrincipalInstallmentAmtMCA=document.getElementById("txtOddPrincipalInstallmentAmtMCA").value;
	var txtOddInterestInstallmentAmtMCA=document.getElementById("txtOddInterestInstallmentAmtMCA").value;
	var cmbOddPrincipalInstallNoMCA=document.getElementById("cmbOddPrincipalInstallNoMCA").value;
	var cmbOddInterestInstallNoMCA=document.getElementById("cmbOddInterestInstallNoMCA").value;
//	alert(txtSancAmountMCA+txtSancPrincipalInstallMCA+txtPrinInstallmentAmountMCA);
//	alert(txtDemandNo);
//	alert(txtSchemeName);
//	alert(txtSubMajorHead);
//	alert(txtMinorHead);
//	/alert(txtSubHead);
//	alert(cmbSchemeCode);
//	alert(hidMotorAdvanceId);

	var sanctioneddate = document.getElementById("txtSanctionedDateMCA").value;

	if(sanctioneddate == "" || sanctioneddate == null){
		alert('Sanctioned Date cannot be empty.');
		return false;
	}


	/*if (!chkEmpty(document.getElementById("txtSanctionedDateMCA"), "Sanctioned Date")) 
	{		
		return false;
	}*/


	var uri = "ifms.htm?actionFlag=approveMotorCarAdvance";
	var url = '&txtDemandNo='+txtDemandNo+'&txtSchemeName='+txtSchemeName+'&txtSubMajorHead='+txtSubMajorHead+'&txtMinorHead='+txtMinorHead+'&txtGroupHead='+txtGroupHead+'&txtSubHead='+txtSubHead+'&cmbSchemeCode='+cmbSchemeCode+'&hidMotorAdvanceId='+hidMotorAdvanceId+'&txtSanctionedDateMCA='+txtSanctionedDateMCA+'&txtSancAmountMCA='+txtSancAmountMCA+'&txtInterestAmountMCA='+txtInterestAmountMCA+'&txtSancPrincipalInstallMCA='+txtSancPrincipalInstallMCA+'&txtSancInterInstallMCA='+txtSancInterInstallMCA+'&txtPrinInstallmentAmountMCA='+txtPrinInstallmentAmountMCA+'&txtInterInstallmentAmountMCA='+txtInterInstallmentAmountMCA+'&txtOddPrincipalInstallmentAmtMCA='+txtOddPrincipalInstallmentAmtMCA+'&txtOddInterestInstallmentAmtMCA='+txtOddInterestInstallmentAmtMCA+'&cmbOddPrincipalInstallNoMCA='+cmbOddPrincipalInstallNoMCA+'&cmbOddInterestInstallNoMCA='+cmbOddInterestInstallNoMCA;

	//var url = runForm(0);
	//alert(url);


	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getApprovedRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function getApprovedRequestMsg(myAjax)
{

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;


	if (lblForwardFlag=="true") {

		alert('Request has been approved');		
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800024&userType=HOD";
	}
}
function rejectRequest(flag){

	//alert("in reject "+flag);
	var requestValue = document.getElementById("hidRequestType").value;
	if(requestValue == '800028'){
		rejectComputerAdvance(flag);
	}else if(requestValue == '800029' ){
		rejectHouseAdvance(flag);
	}else if(requestValue == '800030'){
		rejectMotorCarAdvance(flag);
	}
}
function rejectComputerAdvance(flag){	

	var ComAdvanceId = document.getElementById("hidComAdvanceId").value;

	var uri = "";
	var url = "";
	if(flag == 1){
		var DEORemarks = document.getElementById("txtApproverRemarksCA").value;	
		if(DEORemarks==""){

			alert("Verifier Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByDEOApprover";
		url = "ComAdvanceId=" + ComAdvanceId + "&DEORemarks=" + DEORemarks;
	}else if(flag == 2){
		var Remark = document.getElementById("txtRemarks").value;	
		if(Remark==""){

			alert("Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByHO";
		url = "ComAdvanceId="+ComAdvanceId;
	}else if(flag == 3){
		var Rem = document.getElementById("txtRemarks").value;	
		if(Rem==""){

			alert("Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByHO";
		url = "ComAdvanceId="+ComAdvanceId;
	}else if(flag == 4){
		var Remarks = document.getElementById("txtRemarks").value;	
		if(Remarks==""){

			alert("Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectComAdvanceByHOD";
		url = "ComAdvanceId="+ComAdvanceId;
	}

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getRejectedRequestMsg(myAjax,flag);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function rejectHouseAdvance(flag){

	//alert("in rejectHouseAdvance"+flag);
	var HouseAdvanceId = document.getElementById("hidHouseAdvanceId").value;
	var uri = "";
	var url = "";
	if(flag == 1){
		//alert("flag***1"+flag);
		var DEORemarks = document.getElementById("txtApproverRemarksHBA").value;		
		if(DEORemarks==""){

			alert("Verifier Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectHBAByDEOApprover";
		url = "HouseAdvanceId=" + HouseAdvanceId + "&DEORemarks=" + DEORemarks;
	}
	else if(flag == 2){
		//alert("flag***2"+flag);
		var Remarks = document.getElementById("txtHORemarks").value;		
		//alert(Remarks);
		if(Remarks==""){

			alert("Remarks cannot be empty");
			return false;
		}

		uri = "ifms.htm?actionFlag=rejectHBAByHO";
		//alert(uri);
		url = "HouseAdvanceId="+HouseAdvanceId;
		//alert(url);
	}else if(flag == 3){
		//alert("flag***3"+flag);
		var Remark = document.getElementById("txtHORemarks").value;		
		if(Remark==""){

			alert("Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectHBAByHO";
		url = "HouseAdvanceId="+HouseAdvanceId;
	}else if(flag == 4){
		//alert("flag***4"+flag);
		var Rem = document.getElementById("txtHORemarks").value;		
		if(Rem==""){

			alert("Remarks cannot be empty");
			return false;
		}

		uri = "ifms.htm?actionFlag=rejectHBAByHOD";
		url = "HouseAdvanceId="+HouseAdvanceId;
	}

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getRejectedRequestMsg(myAjax,flag);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function rejectMotorCarAdvance(flag){	

	var MotorAdvanceId = document.getElementById("hidMotorAdvanceId").value;
	var uri = "";
	var url = "";
	if(flag == 1){

		var DEORemarks = document.getElementById("txtApproverRemarksMCA").value;
		if(DEORemarks==""){

			alert("Verifier Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectMCAByDEOApprover";
		url = "MotorAdvanceId=" + MotorAdvanceId + "&DEORemarks=" + DEORemarks;
	}
	else if(flag == 2){
		var Remark = document.getElementById("txtHORemarks").value;
		if(Remark==""){

			alert("Remarks cannot be empty");
			return false;
		}

		uri = "ifms.htm?actionFlag=rejectMCAByHO";
		url = "MotorAdvanceId="+MotorAdvanceId;
	}else if(flag == 3){
		var Rem = document.getElementById("txtHORemarks").value;
		if(Rem==""){

			alert("Remarks cannot be empty");
			return false;
		}
		uri = "ifms.htm?actionFlag=rejectMCAByHO";
		url = "MotorAdvanceId="+MotorAdvanceId;
	}else if(flag == 4){
		var Remarks = document.getElementById("txtHORemarks").value;
		if(Remarks==""){

			alert("Remarks cannot be empty");
			return false;
		}

		uri = "ifms.htm?actionFlag=rejectMCAByHOD";
		url = "MotorAdvanceId="+MotorAdvanceId;
	}

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getRejectedRequestMsg(myAjax,flag);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}
function getRejectedRequestMsg(myAjax,flag)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if (lblForwardFlag=="true") {
		alert('Request has been rejected');	
		if(flag == 1){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800021&userType=DEOAPP";
		}else if(flag == 2){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800022&userType=HO";
		}else if(flag == 3){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800023&userType=HODASST";
		}else if(flag == 4){
			self.location.href = "ifms.htm?actionFlag=loadLNARequestList&elementId=800024&userType=HOD";
		}
	}

}




function validateComputerAdvance() {	
	if (!chkEmpty(document.getElementById("cmbComputerSubType"), "Select Sub Type")
			|| !chkEmpty(document.getElementById("txtAppDateCA"), "Physical Application Received Date")
			|| !chkEmpty(document.getElementById("txtReqAmountCA"),"Requested Amount")				
			|| !chkEmpty(document.getElementById("txtActualCostCA"),"Actual Cost")
			|| !chkEmpty(document.getElementById("txtSancAmountCA"),"Sanction Amount")			
			|| !chkEmpty(document.getElementById("txtSancInstallmentsCA"), "Sanctioned No. of Installments")
			|| !chkEmpty(document.getElementById("txtInstallmentAmountCA"), "Installment Amount")) 
	{
		return false;
	}
	var documentCheckList = document.LNARequestProcessForm.cbDocCheckListCA;


	/* 
	Commented on 21 Dec 2012 - Start
	if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
			|| !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked)
	{
		alert("Please select Mandatory Checklists");
		return false;
	}
	Commented on 21 Dec 2012 - End 
	 */


	// Added by Mubeen on 21 Dec 2012 - Start 
	// Make efficieny certificate checkbox as Non-mandatory for all the groups
	// Removed !documentCheckList[1].checked || from if statement

	if(!documentCheckList[0].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
			|| !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked)
	{
		alert("Please select Mandatory Checklists");
		return false;
	}
	// Added by Mubeen on 21 Dec 2012 - End	



	if(document.getElementById("txtOddInstallmentAmtCA").value!=""){
		if (!chkEmpty(document.getElementById("cmbOddInstallNoCA"), "Odd Installment No"))
		{
			return false;
		}
	}
	/*var attachmentTable = document.getElementById("myTableProofCA");

	if(attachmentTable.rows.length < 2){
		alert("Please attach atleast one Proof");
		return false;
	}*/

	return true;
}




function validateHouseAdvance() {	
	//alert("in validateHouseAdvance");
	if(document.getElementById("cmbHBAType").value=="-1"){
		alert("Please select sub type");
		document.getElementById("cmbHBAType").focus();
		return false;
	}
	var subType = document.getElementById("cmbHBAType").value;
	//alert("subType"+subType);
	if (!chkEmpty(document.getElementById("cmbPayCommissionHBA"), "Pay Commission GR")			
			|| !chkEmpty(document.getElementById("txtAppDateHBA"), "Physical Application Received Date")
			|| !chkEmpty(document.getElementById("txtReqAmountHBA"),"Requested Amount")) 
	{
		return false;
	}

	if (subType==800038){
		//alert("in 800038");
		if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA2"), "Sanction Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement1"), "Disbursement1 Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA2"), "Sanctioned No. of Principal Installments"))
			/*|| !chkEmpty(document.getElementById("txtDisbursement2"), "Disbursement2 Amount")
			|| !chkEmpty(document.getElementById("txtDisbursement3"), "Disbursement3 Amount")				
			|| !chkEmpty(document.getElementById("txtSancInterInstallHBA2"), "Sanctioned No. of Interest Installments")
			|| !chkEmpty(document.getElementById("txtInterestAmountHBA2"), "Interest Amount")
			|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountHBA"), "Principal Installment Amount per month")
			|| !chkEmpty(document.getElementById("txtInterInstallmentAmountHBA2"), "Interest Installment Amount per month")
			|| !chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")
			|| !chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")
			|| !chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date")*/
		{
			//alert("iff hiiii");
			return false;
		}
		/*if(document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoHBA2"), "Odd Principal Installment No"))
			{
				//alert("1");
				return true;
			}
		}*/
		/*if(document.getElementById("txtOddInterestInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoHBA2"), "Odd Interest Installment No"))
			{
		//alert("2");
				////alert("Odd Interest Installment No");
				return true;
			}
		}*/
	}else if (subType==800058){
		//alert("3");
		if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA2"), "Sanction Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement1"), "Disbursement1 Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA2"), "Sanctioned No. of Principal Installments"))
			/*|| !chkEmpty(document.getElementById("txtDisbursement2"), "Disbursement2 Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement3"), "Disbursement3 Amount")
				|| !chkEmpty(document.getElementById("txtDisbursement4"), "Disbursement4 Amount")				
				|| !chkEmpty(document.getElementById("txtSancInterInstallHBA2"), "Sanctioned No. of Interest Installments")
				|| !chkEmpty(document.getElementById("txtInterestAmountHBA2"), "Interest Amount")
				|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountHBA"), "Principal Installment Amount per month")
				|| !chkEmpty(document.getElementById("txtInterInstallmentAmountHBA2"), "Interest Installment Amount per month")
				|| !chkEmpty(document.getElementById("txtReleaseDate1"), "Sanction Date")
				|| !chkEmpty(document.getElementById("txtReleaseDate2"), "Sanction Date")
				|| !chkEmpty(document.getElementById("txtReleaseDate3"), "Sanction Date")
				|| !chkEmpty(document.getElementById("txtReleaseDate4"), "Sanction Date")*/
		{
			return false;
		}
		//Commented by vivek
		/*if(document.getElementById("txtOddPrincipalInstallmentAmtHBA2").value!=""){
			if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoHBA2"), "Odd Principal Installment No"))
		{
				//alert("4");
				return false;
			}
		}*/
		/*if(document.getElementById("txtOddInterestInstallmentAmtHBA2").value!="")
		{
			if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoHBA2"), "Odd Interest Installment No"))
			{
				//alert("5");
				return false;
			}
		}*/
	}else{

		//
		/*if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA"),"Sanctioned Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA"),"Sanctioned No. of Principal Installments")
				|| !chkEmpty(document.getElementById("txtSancInterInstallHBA"), "Sanctioned No. of Interest Installments")
				|| !chkEmpty(document.getElementById("txtInterestAmountHBA"), "Interest Amount")
				|| !chkEmpty(document.getElementById("txtPrincipalInstallmentAmtHBA"),"Principal Installment Amount per month")
				|| !chkEmpty(document.getElementById("txtInterInstallmentAmountHBA"), "Interest Installment Amount per month"))			
		{
			return false;
		}*/

		//Added vivek sharma
		if(!chkEmpty(document.getElementById("txtSanctionedAmountHBA"),"Sanctioned Amount")
				|| !chkEmpty(document.getElementById("txtSancPrinInstallHBA"),"Sanctioned No. of Principal Installments")


				|| !chkEmpty(document.getElementById("txtPrincipalInstallmentAmtHBA"),"Principal Installment Amount per month")
		)			
		{

			return false;
		}

		//if(document.getElementById("txtOddPrincipalInstallmentAmtHBA").value!=""){
		//alert("8");
		//if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoHBA"), "Odd Principal Installment No"))
		//{
		//alert("6");
		//return true;
		//}
		//}
		//if(document.getElementById("txtOddInterestInstallmentAmtHBA").value!=""){
		//alert("7");
		//if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoHBA"), "Odd Interest Installment No"))
		//{
		//alert("9");
		//return true;
		//}
		//}

	}
	if(document.getElementById('ChecklistHBAForPP').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAPP;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked
				|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForCF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBACF;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForBL').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBABL;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked || !documentCheckList[13].checked
				|| !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked || !documentCheckList[17].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForSR').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBASR;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked 
				|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForOF').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAOF;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[9].checked 
				|| !documentCheckList[10].checked || !documentCheckList[11].checked || !documentCheckList[12].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForER').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBAER;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked
				|| !documentCheckList[8].checked || !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForLC').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBALC;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistHBAForRB').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBARB;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
				|| !documentCheckList[10].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
				|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked )
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else{
		//alert("in else");
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListHBA;
		var reqType=document.getElementById("cmbHBAType").value;	
		if(reqType==800037){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked
					|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800038){

			//alert("Inside reqtypee800038");
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
			{

				alert("Please select Mandatory Checklists");
				return false;
			}
			//alert("hii");
		}else if(reqType==800039){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[10].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked )
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800041){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[9].checked 
					|| !documentCheckList[10].checked || !documentCheckList[11].checked || !documentCheckList[12].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800042){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked || !documentCheckList[13].checked
					|| !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked || !documentCheckList[17].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800058){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked || !documentCheckList[16].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800059){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked 
					|| !documentCheckList[9].checked || !documentCheckList[10].checked || !documentCheckList[11].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else if(reqType==800060){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[5].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked
					|| !documentCheckList[9].checked || !documentCheckList[11].checked || !documentCheckList[12].checked
					|| !documentCheckList[13].checked || !documentCheckList[14].checked || !documentCheckList[15].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}

	}

	//alert("trueeee");
	return true;
}
function validateMotorcarAdvance() {	




	if (!chkEmpty(document.getElementById("cmbVehicleSubType"), "Vehicle Sub Type")
			|| !chkEmpty(document.getElementById("cmbPayCommissionMCA"), "Pay Commission GR")
			|| !chkEmpty(document.getElementById("txtAppDateMCA"), "Physical Application Received Date")
			|| !chkEmpty(document.getElementById("txtReqAmountMCA"),"Requested Amount")) 
	{
		return false;
	}

	if(document.getElementById("rdoNew").checked){
		if(!chkEmpty(document.getElementById("txtExShowPriceMCA"),"Exshowroom Price")){
			return false;
		}
	}
	/*if(!chkEmpty(document.getElementById("txtSancAmountMCA"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtSancPrincipalInstallMCA"),"Sanctioned No. of Principal Installments")
			|| !chkEmpty(document.getElementById("txtSancInterInstallMCA"), "Sanctioned No. of Interest Installments")
			|| !chkEmpty(document.getElementById("txtInterestAmountMCA"), "Interest Amount")
			|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountMCA"),"Principal Installment Amount per month")
			|| !chkEmpty(document.getElementById("txtInterInstallmentAmountMCA"), "Interest Installment Amount per month"))			
	{
		return false;
	}*/

	//Added by vivek sharma

	if(!chkEmpty(document.getElementById("txtSancAmountMCA"),"Sanctioned Amount")
			|| !chkEmpty(document.getElementById("txtSancPrincipalInstallMCA"),"Sanctioned No. of Principal Installments")

			/*|| !chkEmpty(document.getElementById("txtInterestAmountMCA"), "Interest Amount")*/
			|| !chkEmpty(document.getElementById("txtPrinInstallmentAmountMCA"),"Principal Installment Amount per month")
	)			
	{
		return false;
	}

	// Modified on  21 Dec 2012 by Mubeen

	if(document.getElementById('ChecklistMCAHandicap').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCAHC;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[6].checked)
			//	Commented on 21 Dec 2012 by Mubeen for making Efficiency Certificate Non-Mandatory || !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked)
		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else if(document.getElementById('ChecklistMCA').style.display == ''){
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCA;
		if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
				|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked )
			// Commented on 21 Dec 2012 by Mubeen for making Efficiency Certificate Non-Mandatory || !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked )

		{
			alert("Please select Mandatory Checklists");
			return false;
		}
	}else{
		documentCheckList = document.LNARequestProcessForm.cbDocCheckListMCANew;
		var reqType=document.getElementById("cmbVehicleSubType").value;	
		if(reqType==800035){
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[6].checked)
				// Commented on 21 Dec 2012 by Mubeen for making Efficiency Certificate Non-Mandatory || !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked)
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}else{
			if(!documentCheckList[0].checked || !documentCheckList[1].checked || !documentCheckList[2].checked  || !documentCheckList[3].checked 
					|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked )
				// Commented on 21 Dec 2012 by Mubeen for making Efficiency Certificate Non-Mandatory			|| !documentCheckList[4].checked || !documentCheckList[6].checked || !documentCheckList[7].checked || !documentCheckList[8].checked )
			{
				alert("Please select Mandatory Checklists");
				return false;
			}
		}
	}	










	if(document.getElementById("txtOddPrincipalInstallmentAmtMCA").value!=""){
		if (!chkEmpty(document.getElementById("cmbOddPrincipalInstallNoMCA"), "Odd Installment No"))
		{
			return false;
		}
	}
	if(document.getElementById("txtOddInterestInstallmentAmtMCA").value!=""){
		if (!chkEmpty(document.getElementById("cmbOddInterestInstallNoMCA"), "Odd Installment No"))
		{
			return false;
		}
	}
	/*var attachmentTable = document.getElementById("myTableProofMCA");

	if(attachmentTable.rows.length < 2){
		alert("Please attach atleast one Proof");
		return false;
	}
	 */
	return true;
}




function displayGuarantorDtls(){
	if(document.getElementById("rdoYes").checked){

		document.getElementById('trGuarantor').style.display = '';
	}
	else{
		document.getElementById('trGuarantor').style.display = 'none';
	}
}

function backButton(flag)
{
	if(flag == 1){
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&userType=DEO&elementId=800011";
	}else if(flag == 2){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=DEOAPP&elementId=800021";
	}else if(flag == 3){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=HO&elementId=800022";
	}else if(flag == 4){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=HODASST&elementId=800023";
	}else if(flag == 5){
		self.location.href = "ifms.htm?actionFlag=loadLNARequestList&userType=HOD&elementId=800024";
	}else if(flag == 6){
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&userType=HODASST2&elementId=800025";
	}else if(flag == 7){
		self.location.href = "ifms.htm?actionFlag=LNAloadDraftRequestList&userType=HODASST2&elementId=800029";
	}

}
function greaterThanCurrDateValidation(fieldname) 
{
	var	day = fieldname.value.split("/")[0];
	var month = fieldname.value.split("/")[1]; 
	var year = fieldname.value.split("/")[2];

	var dt = new Date(year, month-1, day); 
	var today = new Date();

	if(dt>today) 
	{
		alert("Physical Application Date cannot be greater than Current Date");
		fieldname.value="";
		fieldname.focus();
		return false; 
	}

	return true;
}
function lessThanCurrDateValidation(fieldname) 
{
	var	day = fieldname.value.split("/")[0];
	var month = fieldname.value.split("/")[1]; 
	var year = fieldname.value.split("/")[2];

	var dt = new Date(year, month-1, day);
	var today = new Date();
	var newDt = new Date(today.getFullYear(), today.getMonth(), today.getDate());
	var dtDiff = newDt-dt;
	if(fieldname!=""){
		if (dtDiff<0){ 
			alert("Sanction Date can not be greater than Current Date");
			fieldname.value="";
			fieldname.focus();
			return false; 
		}
	}	
	return true;
}
function showRowCell(element)
{
	var cell, row;    
	if (element.parentNode) 
	{

		do
			cell = element.parentNode;
		while (cell.tagName.toLowerCase() != 'td')
			row = cell.parentNode;
	}
	else if (element.parentElement) 
	{
		do
			cell= element.parentElement;
		while (cell.tagName.toLowerCase() != 'td')
			row = cell.parentElement;
	}
	return row.rowIndex;
}
function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
	document.getElementById("btnAddNewCheckListCA").style.display='';
	document.getElementById("btnAddNewCheckListMCA").style.display='';
	document.getElementById("btnAddNewCheckListHBA").style.display='';
	//document.getElementById("btnAddNewCheckListCA").disabled=false;
}

function dateDiffInMonth(startDate,endDate){
	var d1Y = startDate.getFullYear();
	var d2Y = endDate.getFullYear();
	var d1M = startDate.getMonth();
	var d2M = endDate.getMonth();
	var totalMonth=(d2M+12*d2Y)-(d1M+12*d1Y);
	return totalMonth;
}
function validateBasicPay(){

	var payCommission= document.getElementById("cmbPayCommissionHBA").value;
	var index = document.LNARequestProcessForm.cmbPayScaleHBA.selectedIndex ;
	var payScale = document.LNARequestProcessForm.cmbPayScaleHBA[index].text ;
	var basicPay = document.getElementById("txtBasicPay").value;
	var payArray;
	var count=0;

	if((basicPay != null && basicPay != "") && (document.getElementById("cmbPayScaleHBA").value != null && document.getElementById("cmbPayScaleHBA").value != -1) )
	{	
		if(payCommission == '800054'){
			var tempArray = payScale.split("(");
			payArray = tempArray[0].split("-");
			var payIn = basicPay - (tempArray[1].split(")"))[0];
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();

			}
			if(parseInt(payIn)< parseInt(payArray[0]) || parseInt(payIn) >parseInt(payArray[1])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				document.getElementById("txtBasicPay").focus();
				return;
			}

		}else if(payCommission == '800053'){

			payArray = payScale.split("-");

			var temp;
			for(k=0;k<payArray.length;k++){
				temp = payArray[k];
				payArray[k] = temp.trim();
			}
			for(var j=0;j<payArray.length;j++){
				if(payArray[j] == 'EB')
				{
					payArray.splice(j,1); 
				}
			}
			if(basicPay == payArray[payArray.length - 1]){
				return;
			}
			for(var i=0;i<payArray.length;i+=2){

				if(i != 0){
					if(parseInt(basicPay)>parseInt(payArray[i])){
						count = i;
						continue;
					}

					else{
						var start = payArray[i-2];
						var variance = payArray[i-1];

						if((basicPay-start)%variance != 0){
							alert("The Basic Pay is not in accordance with the Pay Scale selected");
							document.getElementById("txtBasicPay").value = '';
						}
						return;
					}
				}
				else{
					if(parseInt(basicPay)<parseInt(payArray[i])){

						alert("The Basic Pay is not in accordance with the Pay Scale selected");
						document.getElementById("txtBasicPay").value = '';
						return;
					}
				}
				count = i;
			}
			if(count!=0 && parseInt(basicPay)>parseInt(payArray[count])){
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				return;
			}
		}
	}

}

function GetScalePostfromDesg()
{
	var payScalelength=document.getElementById("cmbPayScaleHBA").length;
	for(i=1;i<payScalelength;i++)
	{
		var lgth = document.getElementById("cmbPayScaleHBA").options.length -1;				  
		document.getElementById("cmbPayScaleHBA").options[lgth] = null;
	}
	var commissionId;
	var payCommission= document.getElementById("cmbPayCommissionHBA").value;
	if(payCommission == '800053'){
		commissionId = 700015;
	}else{
		commissionId = 700016;
	}
	var url = './hrms.htm?actionFlag=GetScalefromDesignation';
	var uri = '&commissionId='+commissionId+'&ifAjax=TRUE';		  
	url = url + uri;

	var myAjax = new Ajax.Request(url,
			{
		method: 'post',
		asynchronous: false,
		onSuccess: function(myAjax) {
		GetScalesPostsfromDesg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...')} 
			} );
}
function GetScalesPostsfromDesg(myAjax){	

	var XMLDoc =  myAjax.responseXML.documentElement;	   
	var XmlHiddenValues = XMLDoc.getElementsByTagName('scale-mapping');
	var scale = document.getElementById("cmbPayScaleHBA");
	for ( var i = 0 ; i < XmlHiddenValues.length ; i++ )
	{
		var val=XmlHiddenValues[i].childNodes[0].firstChild.nodeValue;
		var text =XmlHiddenValues[i].childNodes[1].firstChild.nodeValue; 
		var theOption = new Option;
		theOption.value=val;
		theOption.text=text;			
		scale.options[i+1]=theOption;
	}
}
function validateRemarks(field,alrtString)
{
	var str=field.value;
	if (!str || trim(str)=="") { return  true; }
	re1 = /[^a-z^0-9\/\." "]/gi;
	if(str.search(re1) < 0)
	{
		return true;
	}
	else
	{
		//alert(alrtString);
		field.focus();
	}
	return (str.search(re1) < 0 ? true : false);
}

function getHRODtls(){
	var ans = confirm('The case will move to Regional Head Of office(RHO)?');
	var returnVl ;
	if(ans){
		var url = "./hrms.htm?actionFlag=getHRODtls";

		//url = url + uri;
		var fwdClicked= document.getElementById("fwdClicked").value;
		if(fwdClicked == "TRUE")
			return ;

		var myAjax = new Ajax.Request(url,
				{
			method: 'post',
			asynchronous: false,
			onSuccess: function(myAjax) {
			createHRODtlsTable(myAjax);
		},
		onFailure: function(){ alert('Something went wrong...')} 
				} );
	}
	else return false;
}

function createHRODtlsTable(myAjax){


	var XMLDoc =  myAjax.responseXML.documentElement;	   
	var FLAG = XMLDoc.getElementsByTagName('FLAG');

	if(FLAG[0].firstChild.nodeValue == "true"){
		var lEmpName = XMLDoc.getElementsByTagName('lEmpName');
		var locId = XMLDoc.getElementsByTagName('locId');
		var locName = XMLDoc.getElementsByTagName('locName');
		var postId = XMLDoc.getElementsByTagName('postId');

		var length = postId.length;
		var table=document.getElementById("hoFrwrd");
		var head = table.insertRow(-1);
		color2 = "rgb(255, 218, 178)";
		head.style.borderColor = color2;

		var radiobButton = head.insertCell(-1);
		radiobButton.style.border ="1px solid rgb(255, 218, 178)";
		radiobButton.align= "center";
		radiobButton.innerHTML = '<b>Select</b>'	;			

		var empName = head.insertCell(-1);
		empName.style.border ="1px solid rgb(255, 218, 178)";
		empName.innerHTML = '<b>Regional HO Name</b>'	;
		empName.align= "center";

		var ofcName = head.insertCell(-1);
		ofcName.style.border ="1px solid rgb(255, 218, 178)";
		ofcName.innerHTML = '<b>Office Name</b>'	;
		ofcName.align= "center";

		for(var i =0; i < length; i++){			
			var newRow = table.insertRow(-1);
			color2 = "rgb(255, 218, 178)";
			newRow.style.borderColor = color2;

			var Cell1 = newRow.insertCell(-1);
			Cell1.style.border ="1px solid rgb(255, 218, 178)";
			Cell1.innerHTML = '<input type="radio" name="rbRHO" id="rbRHO'+i+'" value="'+postId[i].firstChild.nodeValue +'"/>'	
			+'<input type="hidden" name="locID" id="locID'+i+'" value="'+locId[i].firstChild.nodeValue +'"/>';			


			var Cell2 = newRow.insertCell(-1);
			Cell2.style.border ="1px solid rgb(255, 218, 178)";
			Cell2.innerHTML = '<label>'+lEmpName[i].firstChild.nodeValue+'</label>' ;

			var Cell3 = newRow.insertCell(-1);
			Cell3.style.border ="1px solid rgb(255, 218, 178)";
			Cell3.innerHTML = '<label>'+locName[i].firstChild.nodeValue+'</label>' ;

		}

		var bottom = table.insertRow(-1);
		color2 = "rgb(255, 218, 178)";
		bottom.style.borderColor = color2;

		var Cell4 = bottom.insertCell(-1);
		var Cell5 = bottom.insertCell(-1);

		Cell5.align = "center";
		Cell5.innerHTML = '<input type="button" name="frwrdToRHO" id="frwrdToRHO" class="bigbutton" value="Forward To RHO" onclick="forwardToRHO('+length+')"/>'	 ;

		var Cell6 = bottom.insertCell(-1);
		//Cell4.style.border ="1px solid rgb(255, 218, 178)";

		document.getElementById("fwdClicked").value="TRUE";
		return ;

	}
	else {
		alert('No RHO found.');
		return ;
	}
}

function forwardToRHO(size){
	var selected = false;
	if(Number(size) != 0){
		for(var i = 0; i<size; i++){
			if(document.getElementById("rbRHO"+i).checked){
				var selectedPostId = document.getElementById("rbRHO"+i).value;
				document.getElementById("ForwardToPost").value = selectedPostId;
				document.getElementById("toLocation").value =document.getElementById("locID"+i).value;				
				selected = true;
				forwardRequest(3);				
				return;
			}

		}
		if(!selected) {
			alert('Please select RHO.');
			return ;
		}
	}


}



function forwardRequest(flag){
	var requestValue = document.getElementById("hidRequestType").value;
	//ADDED BY VIVEK SHARMA 07/03/2017
	var getHieraracy = document.getElementById("getHieraracy").value;
	var lStrRequestType = document.getElementById("lStrRequestType").value;
	if(getHieraracy == 'NotGot'){
		var ans = confirm('Please Click on OK button before forwarding Loan Request');
		if(ans){
			var url = "ifms.htm?actionFlag=updateHierActivateFlagService&lStrRequestType="+lStrRequestType;
			var myAjax = new Ajax.Request(url,
					{
				method: 'post',
				asynchronous: false,
				parameters:url,
				onSuccess: function(myAjax) {
				getforwardRequestMsg(myAjax,flag);
			},
			onFailure: function(){ alert('Something went wrong...');} 
					} );
		}
		else if(!ans){

			return false;
		}
	}
	else{
		if(requestValue == '800028'){
			fwdComputerAdvance(flag);
		}else if(requestValue == '800029'){
			//alert("inside forwardRequest");
			fwdHouseAdvance(flag);		
		}else if(requestValue == '800030'){
			fwdMotorCarAdvance(flag);
		}
	}
}

function getforwardRequestMsg(myAjax,flag){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lblForFlag == "true"){
		var requestValue = document.getElementById("hidRequestType").value;
		if(requestValue == '800028'){
			fwdComputerAdvance(flag);
		}else if(requestValue == '800029'){
			fwdHouseAdvance(flag);		
		}else if(requestValue == '800030'){
			fwdMotorCarAdvance(flag);
		}
	}
}
//ENDED BY VIVEK SHARMA 07/03/2017 
function saveHealthInsurance(){

	var uri = "ifms.htm?actionFlag=saveHealthInsurance";

	var url = runForm(0);


	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getSaveRequestMsg(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...');} 
			} );
}

function approveRequest(){

	//alert("hiii");
	var requestValue = document.getElementById("hidRequestType").value;

	if(requestValue == '800028'){
		approveComputerAdvance();
	}else if(requestValue == '800029' ){
		approveHouseAdvance();
	}else if(requestValue == '800030'){
		approveMotorCarAdvance();
	}
	else if(requestValue == '10001198175'){
		approveHealthInsurance();
	}
}

function approveHealthInsurance(){


	if(!validateHealthInsurance())
	{

		return false;
	}


	else if(validateHealthInsurance()){



		var uri = "ifms.htm?actionFlag=approveHealthInsurance";

		var url = runForm(0);


		var myAjax = new Ajax.Request(uri,
				{
			method: 'post',
			asynchronous: false,
			parameters:url,
			onSuccess: function(myAjax) {
			getApprovedRequestMsgforHI(myAjax);
		},
		onFailure: function(){ alert('Something went wrong...');} 
				} );
	}
}




function getApprovedRequestMsgforHI(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblForwardFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if (lblForwardFlag=="true") {

		alert('Request has been approved');		
		self.location.href = "ifms.htm?actionFlag=loadLNASearchForm&elementId=800011&userType=DEO";
	}
}

function validateHealthInsurance(){


	var appdate = document.getElementById("txtAppDateHI").value;
	var reqamt = document.getElementById("txtReqAmountHI").value;
	var loanstdt = document.getElementById("txtLoanStDate").value;
	var loanenddt = document.getElementById("txtLoanEndDate").value;
	var sancdt = document.getElementById("txtSanctionedDateHI").value;
	var chkbox = document.getElementById("chkValidate").checked;



	if(appdate == ""){
		alert('Mandatory field Physical application date cannot be left empty');
		return false;
	}

	if(reqamt == ""){
		alert('Mandatory field Request Amount cannot be left empty');
		return false;
	}

	if(loanstdt == ""){
		alert('Mandatory field loan start date cannot be left empty');
		return false;
	}

	if(loanenddt == ""){
		alert('Mandatory field loan end date cannot be left empty');
		return false;
	}

	if(sancdt == ""){
		alert('Mandatory field sanctioned date cannot be left empty');
		return false;
	}

	if(!chkbox){
		alert('Kindly specify if employee has taken a Health Insurance loan previously.');	
		return false;
	}

	return true;
}


function populateMinorHead(){

	var schemecode = document.getElementById("cmbSchemeCode").value; 
	var submajorhead = document.getElementById("cmbSubMajorHead").value;
	var grouphead = document.getElementById("cmbGroupHead").value;
	var subhead = document.getElementById("cmbSubHead").value;



	//ajax function to get minor head and scheme name

	var uri = "ifms.htm?actionFlag=getMinorHead";

	var url = "&schemecode="+schemecode+"&submajorhead="+submajorhead+"&grouphead="+grouphead+"&subhead="+subhead+runForm(0);


	//var url = url1 + url2 + url3 + url4 ;

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getDataStateChangedForMinorHead(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...')} 
			} );
}

function populateSchemeName(){

	var schemecode = document.getElementById("cmbSchemeCode").value; 
	var submajorhead = document.getElementById("cmbSubMajorHead").value;
	var grouphead = document.getElementById("cmbGroupHead").value;
	var subhead = document.getElementById("cmbSubHead").value;


	//ajax function to get minor head and scheme name

	var uri = "ifms.htm?actionFlag=getSchemeName";

	var url = "&schemecode="+schemecode+"&submajorhead="+submajorhead+"&grouphead="+grouphead+"&subhead="+subhead+runForm(0);


	//var url = url1 + url2 + url3 + url4 ;

	var myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function(myAjax) {
		getDataStateChangedForSchemeName(myAjax);
	},
	onFailure: function(){ alert('Something went wrong...')} 
			} );
}


function getDataStateChangedForMinorHead(myAjax)
{

	XMLDoc = myAjax.responseXML.documentElement;

	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');

	document.getElementById("cmbMinorHead").options.length = XmlHiddenValues.length;


	for(var j = 0;j<=XmlHiddenValues.length;j++)
	{

		var id =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
		var desc =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
		var optn = document.createElement("OPTION");



		optn.value = desc;
		optn.text = id;
		document.getElementById("cmbMinorHead").options.add(optn);
	}
}

function getDataStateChangedForSchemeName(myAjax)
{

	XMLDoc = myAjax.responseXML.documentElement;

	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');

	document.getElementById("cmbSchemeName").options.length = XmlHiddenValues.length;

	for(var j = 0;j<=XmlHiddenValues.length;j++)
	{

		var id =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
		var desc =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
		var optn = document.createElement("OPTION");


		optn.value = desc;
		optn.text = id;
		document.getElementById("cmbSchemeName").options.add(optn);
	}
}






