var User="";
var Use="";
var Type="";
var deletedContriArray = new Array() ;
var deletedContriIdPKsArray = new Array() ; 


function DeleteRowContri(counter)
{
	deletedContriArray[deletedContriArray.length] = counter ;
	deletedContriIdPKsArray[deletedContriIdPKsArray.length] = document.getElementById("checkbox"+counter).value.trim();
	
	var current = window.event.srcElement;
    while ( (current = current.parentElement)  && current.tagName !="TR");
         current.parentElement.removeChild(current);
    //alert(deletedContriArray.length) ;
    //alert(counter);
    
    
    document.getElementById("txtTotalRecords").value=Number(document.getElementById("txtTotalRecords").value)-1;
    //Don't decrement hdnCounter
    // document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value)-1;
}

function window_open(val,x,y,afterDateSelect,dateInputParams){
    var newWindow;

    if(afterDateSelect == undefined) {
		afterDateSelect = '';
	}
	glbAfterDateSelect = afterDateSelect;

    var urlstring = "common/calendarDppf.jsp?requestSent=" +val;

    dateChkInputs = dateInputParams;
    var X = window.event.screenX  - window.event.offsetX;
    var Y = window.event.screenY  + (20 - window.event.offsetY);	    

    var urlstyle = 'height=200,width=270,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;

	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
}

function AddNewRowContri(counter)
{
		var color1 = "#F5F5F5";
		var color2 = "#7B68EE";
	
		var selectedRow=counter ;
		var table=document.getElementById("vo");

		var cnt=table.rows.length;
		var nextRow=Number(document.getElementById("hdnCounter").value) + 1 ;
		
		var newRow = table.insertRow(-1);

		newRow.style.backgroundColor = color1;
		newRow.style.borderColor = color2;
		
		var Cell1 = newRow.insertCell(-1);
		Cell1.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell2 = newRow.insertCell(-1);
   		Cell2.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell3 = newRow.insertCell(-1);
   		Cell3.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell4 = newRow.insertCell(-1);
   		Cell4.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell5 = newRow.insertCell(-1); 
   		Cell5.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell6 = newRow.insertCell(-1); 
   		Cell6.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell7 = newRow.insertCell(-1); 
   		Cell7.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell8 = newRow.insertCell(-1); 
   		Cell8.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell9 = newRow.insertCell(-1); 
   		Cell9.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell10 = newRow.insertCell(-1); 
   		Cell10.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell11 = newRow.insertCell(-1); 
   		Cell11.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell12 = newRow.insertCell(-1); 
   		Cell12.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell13 = newRow.insertCell(-1); 
   		Cell13.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell14 = newRow.insertCell(-1); 
   		Cell14.style.border ="1px solid rgb(255, 218, 178)";
   		var Cell15 = newRow.insertCell(-1); 
   		Cell15.style.border ="1px solid rgb(255, 218, 178)";
   		
   		Cell1.width="12%";
   		Cell1.className="oddcentre";
   		Cell2.width="11%";
   		Cell2.className="oddcentre";
   		Cell3.width="14%";
   		Cell3.className="oddcentre";
   		Cell4.width="14%";
   		Cell4.className="oddcentre";
   		Cell5.width="10%";
   		Cell5.className="oddcentre";
   		Cell6.width="13%";
   		Cell6.className="oddcentre";
   		Cell7.width="10%";
   		Cell7.className="oddcentre";
   		Cell8.width="8%";
   		Cell8.className="oddcentre";
   		Cell9.width="8%";
   		Cell9.className="oddcentre";
   		Cell10.width="9%";
   		Cell10.className="oddcentre";
   		Cell11.width="6%";
   		Cell11.className="oddcentre";
   		Cell12.width="6%";
   		Cell12.className="oddcentre";
   		 
   		
	   	Cell1.align="left";
	   	Cell2.align="left";
	   	Cell3.align="left";
	   	Cell4.align="left";
		Cell5.align="left";
	   	Cell6.align="left";
		Cell7.align="left";
	   	Cell8.align="left";
		Cell9.align="left";
		Cell10.align="left";
		Cell11.align="center";
	   	Cell12.align="center";
	   	Cell13.align="center";
	   	Cell14.align="center";
	   	Cell15.align="center";
	   	
	   	Cell1.innerHTML = '<b><label id="name'+nextRow+'">'+document.getElementById("name"+selectedRow).innerHTML+'</label></b>' ;
	   	Cell2.innerHTML = '<label id="dcpsId'+nextRow+'" ><small>'+document.getElementById("dcpsId"+selectedRow).innerHTML+'</small></label>' ;   	
		Cell3.innerHTML = '<input type="text" style="width: 70%;" name="txtStartDate'+nextRow+'" id="txtStartDate'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
						+ '	onBlur="validateDate(this);compareDates('+nextRow+');changeDAForSelectedPrd('+nextRow+');changeContriForSelectedPrd('+nextRow+');" value="" width="10" />'   
						+ '&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtStartDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>'  ;
		Cell4.innerHTML = '<input type="text" style="width: 70%;" name="txtEndDate'+nextRow+'" id="txtEndDate'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
						+ '	onBlur="validateDate(this);compareDates('+nextRow+');changeDAForSelectedPrd('+nextRow+');changeContriForSelectedPrd('+nextRow+');" value="" width="10" />'   
						+ '&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtEndDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');" style="cursor: pointer;"/>' 
						+ '<input type="hidden" id="contriDoneForThisPrd'+nextRow+'" name="contriDoneForThisPrd'+nextRow+'" value="NO"/>' ;
		
		Cell5.innerHTML = '<select name="cmbPayCommission'+nextRow+'" id="cmbPayCommission'+nextRow+'" onChange="changeDPEditability('+nextRow+');changeDAForSelectedPrd('+nextRow+');" style="width: 95%;" >' 
										+ ' <option value="-1">--Select--</option>' +LISTPAYCOMMISSIONS +'</select>';
		Cell6.innerHTML = '<select name="cmbTypeOfPayment'+nextRow+'" id="cmbTypeOfPayment'+nextRow+'"  style="width: 95%;"  onchange="changeEditabilityOfFields('+nextRow+');compareDates('+nextRow+');"  >'
										+ ' <option value="-1">--Select--</option>' +LISTTYPESOFPAYMENT +'</select>';
		Cell7.innerHTML = '<input type="text" style="width: 95%;"  id="basic'+nextRow+'" name="basic'+nextRow+'" value="'+document.getElementById("basic"+selectedRow).value+'" onchange="changeDpDaAndContri('+nextRow+');" />' ;
		Cell8.innerHTML = '<input type="text" style="width: 95%;"  id="DP'+nextRow+'" name="DP'+nextRow+'"  value="'+document.getElementById("DP"+selectedRow).value+'" onchange="changeAndContri('+nextRow+');"/>' ;
		Cell9.innerHTML = '<input type="text" style="width: 95%;"  id="DA'+nextRow+'" name="DA'+nextRow+'"  value="'+document.getElementById("DA"+selectedRow).value+'" onchange="changeAndContri('+nextRow+');" />' ;
		Cell10.innerHTML = '<input type="text" style="width: 95%;"  id="contribution'+nextRow+'" name="contribution'+nextRow+'"  value="'+document.getElementById("contribution"+selectedRow).value+'" onchange="changeAndContri('+nextRow+');"/>' ;
		Cell11.innerHTML = '<input type="text" style="width: 95%;"  id="contributionNps'+nextRow+'" name="contributionNps'+nextRow+'"  value="'+document.getElementById("contributionNps"+selectedRow).value+'" readOnly="readOnly"/>' ;
						
		Cell12.innerHTML = '<a href=# onclick="AddNewRowContri('+nextRow+');">Add</a> ';
		Cell13.innerHTML = '<a href=# onclick="DeleteRowContri('+nextRow+');">Delete</a> ';
		
		Cell14.innerHTML = '<label id="status'+nextRow+'" style="color: black;"><center>Not Saved</center></label>';
		
		Cell15.innerHTML =  '<input type="checkbox" id="checkbox'+nextRow+'" name="checkbox'+nextRow+'" value="'+0+'" checked="checked" style="display:none"/>' 
		+  '<input type="hidden" name="dcpsempId'+ nextRow+'" id="dcpsempId'+ nextRow+'" value="'+  document.getElementById("dcpsempId"+selectedRow).value+'" />' 
		+  '<input type="hidden" name="daRate" id="daRate'+nextRow+'" value="' + document.getElementById("daRate"+selectedRow).value+'" />' ;
		
		Cell15.innerHTML = 	Cell15.innerHTML  
			+  '<input type="hidden" name="hidBasic" id="hidBasic'+nextRow+'" value="' + document.getElementById("hidBasic"+selectedRow).value+'" />' 
			+  '<input type="hidden" name="hidDP" id="hidDP'+nextRow+'" value="' + document.getElementById("hidDP"+selectedRow).value+'" />' 
			+  '<input type="hidden" name="hidDA" id="hidDA'+nextRow+'" value="' + document.getElementById("hidDA"+selectedRow).value+'" />' 
			+  '<input type="hidden" name="hidContribution" id="hidContribution'+nextRow+'" value="' + document.getElementById("hidContribution"+selectedRow).value+'" />'
			+  '<input type="hidden" name="hidContributionNps" id="hidContributionNps'+nextRow+'" value="' + document.getElementById("hidContributionNps"+selectedRow).value+'" />'
		;
		
		Cell14.innerHTML = 	Cell14.innerHTML 
			+ '<input type="hidden" name="hidEmpStartDate" id="hidEmpStartDate'+nextRow+'" value="' + document.getElementById("hidEmpStartDate"+selectedRow).value+'" />'
			+ '<input type="hidden" name="hidEmpEndDate" id="hidEmpEndDate'+nextRow+'" value="' + document.getElementById("hidEmpEndDate"+selectedRow).value+'" />'
			+ '<input type="hidden" name="hidJoiningDate" id="hidJoiningDate'+nextRow+'" value="' + document.getElementById("hidJoiningDate"+selectedRow).value+'" />'
		;
		
		document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value)+1;
		document.getElementById("txtTotalRecords").value=Number(document.getElementById("txtTotalRecords").value)+1;

		var lObjectcmbPayCommission = document.getElementById("cmbPayCommission"+nextRow);
		var lValuecmbPayCommission = document.getElementById("cmbPayCommission"+selectedRow).value;
		setSelectedIndex(lObjectcmbPayCommission,lValuecmbPayCommission) ;
		
		document.getElementById("cmbTypeOfPayment"+nextRow).value = '700047';
}

function setSelectedIndex(s, v) {
    for ( var i = 0; i < s.options.length; i++ ) {
        if ( s.options[i].value == v ) {
            s.options[i].selected = true;
            return;
        }
    }
}

function checkUncheckAll(theElement)
{
	 var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}

function getSchemeforBillGroup()
{
	showProgressbar();
	var billGroupId = document.getElementById("cmbBillGroup").value.trim();
	Use = document.getElementById("Use").value.trim() ;
	User = document.getElementById("User").value.trim();
	
	if(billGroupId == -1)
		{
			hideProgressbar();
			document.getElementById("txtSchemeName").value = "";
			document.getElementById("schemeCode").value = "";
		}
	
	else
		{
				var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value.trim();
				
				// Code for --- If It is contribution through challan, then payment type combo is hidden else shown. 
				
				if(User != 'TO' && (!(User == 'DDO' && (Use == 'ViewApproved' || Use == 'ViewReverted' || Use == 'ViewForwarded'))) && (!((User == 'DDOAsst' || User == 'ATO') &&  Use == 'ViewRejected')))
					{
						if(billGroupId == hidBGIdForContriThruChallan)
							{
							if(document.getElementById("paymentMasterDivDisplay") != null)
								{
								if(document.getElementById("paymentMasterDivDisplay").style.display != 'none')
									{
										document.getElementById("paymentMasterDivDisplay").style.display = 'none';
									}
								}
							}
						else
							{
							if(document.getElementById("paymentMasterDivDisplay") != null)
							{
								if(document.getElementById("paymentMasterDivDisplay").style.display == 'none')
								{
									document.getElementById("paymentMasterDivDisplay").style.display = 'inline';
								}
							}
							}
					}
				// Ends code for --- If It is contribution through challan, then payment type combo is hidden else shown. 
				
				if(billGroupId == hidBGIdForContriThruChallan)
				{
					hideProgressbar();
					document.getElementById("txtSchemeName").value="Contribution Through Challan";
					document.getElementById("schemeCode").value=hidBGIdForContriThruChallan;
				}
				else
				{
					 var uri = 'ifms.htm?actionFlag=getSchemeforBillGroup';
					 var url = 'billGroupId='+ billGroupId;
					 var myAjax = new Ajax.Request(uri,
						       {
						        method: 'post',
						        asynchronous: false,
						        parameters:url,
						        onSuccess: function(myAjax) {
									dataStateChangedForGettingSchemeForBillGroup(myAjax);
								},
						        onFailure: function(){ alert('Something went wrong...');} 
						          } );
					  	
				}
		}
}

function dataStateChangedForGettingSchemeForBillGroup(myAjax)
{
	hideProgressbar();
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var schemeName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var schemeCode = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var subSchemeName = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var subSchemeCode = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	document.getElementById("txtSchemeName").value=schemeName;
	document.getElementById("schemeCode").value=schemeCode;
	document.getElementById("txtSubSchemeName").value=subSchemeName;
	document.getElementById("subSchemeCode").value=subSchemeCode;
}

function changeDpDaAndContri(counter)
{
	var nthElement = counter ; 
	var basic = document.getElementById("basic"+nthElement).value ;
//	alert('basic-->'+basic);
	var DP;
	var payCommission = document.getElementById("cmbPayCommission"+nthElement).value.trim();
	if(payCommission == '700015' || payCommission == '700345')
	{
		DP = Number(basic/2);
	}
	else
	{
		DP = 0;
	}
	var DARate = Number (document.getElementById("daRate"+nthElement).value);
	var DA = (Number(Number(basic)+Number(DP)) * DARate).toFixed(2); //alert(basic+" : "+DP+ " : "+DA);
	var tempcontribution = Number(basic) + Number(Math.round(DA)) + Number(DP); //alert(DA+" >> "+tempcontribution );

	var contribution = (Number( tempcontribution * 0.10)).toFixed(2);
	var contributionNps = (Number( tempcontribution * 0.14)).toFixed(2);
	document.getElementById("DP" + nthElement).value = Math.round(DP) ;
	document.getElementById("DA" + nthElement).value = Math.round(DA) ; //alert(DA+" >> "+contributionNps);
	document.getElementById("contribution" + nthElement).value = Math.ceil(contribution) ;
	document.getElementById("contributionNps" + nthElement).value = Math.ceil(contributionNps) ;
}

function changeAndContri(counter)
{
	var nthElement = counter ; 
	var basic = document.getElementById("basic"+nthElement).value ;
	var DP;
	var DA ;
	var payCommission = document.getElementById("cmbPayCommission"+nthElement).value.trim();
	if(basic!=0 && basic!='NaN'){
		if(payCommission == '700015' || payCommission == '700345')
		{
			DP = Number(basic/2);
		}
		else
		{
			DP = 0;
		}
	
		var DARate = Number (document.getElementById("daRate"+nthElement).value);
		if(Number(document.getElementById("DA"+nthElement).value)>0){
			
			DA=Number(document.getElementById("DA"+nthElement).value)
		}else {
			  DA = (Number(Number(basic)+Number(DP)) * DARate).toFixed(2); //alert(basic+" : "+DP+ " : "+DA);
		}
		
		
		var tempcontribution = Number(basic) + Number(Math.round(DA)) + Number(DP)  ;
		var contribution = (Number( tempcontribution * 0.10)).toFixed(2);
		var contributionNps = (Number( tempcontribution * 0.14)).toFixed(2);
	}else {
		DP = 0;
		var DA =document.getElementById("DA"+nthElement).value.trim(); 
		var tempcontribution = Number(DA) + Number(DP)  ;
		var contribution = (Number( tempcontribution * 0.10)).toFixed(2);
		var contributionNps = (Number( tempcontribution * 0.14)).toFixed(2);
		
	} 
	document.getElementById("DP" + nthElement).value = Math.round(DP) ;
	document.getElementById("DA" + nthElement).value = Math.round(DA) ; //alert(contribution);
	document.getElementById("contribution" + nthElement).value = Math.ceil(contribution) ;
	document.getElementById("contributionNps" + nthElement).value = Math.ceil(contributionNps) ;
}



function changeEditabilityOfFields(counter)
{
	var nthElement = counter ; 
	var paymentType = document.getElementById("cmbTypeOfPayment"+nthElement).value;
	
	basicForEditedEmployee = document.getElementById("basic"+nthElement).value;
	DPForEditedEmployee = document.getElementById("DP"+nthElement).value;
	DAForEditedEmployee = document.getElementById("DA"+nthElement).value;
	contributionForEditedEmployee = document.getElementById("contribution"+nthElement).value;
	
	if(paymentType == 700048) // DA Arrears
	{
		document.getElementById("basic"+nthElement).value = "";
		document.getElementById("DP"+nthElement).value = "";
		document.getElementById("basic"+nthElement).readOnly = "";
		document.getElementById("DP"+nthElement).readOnly = true;
		document.getElementById("DA"+nthElement).readOnly = false;
	}
	if(paymentType == 700049) // Pay Arrears
	{
		document.getElementById("basic"+nthElement).value = "";
		document.getElementById("DP"+nthElement).value = "";
		document.getElementById("DA"+nthElement).value = "";
		document.getElementById("basic"+nthElement).readOnly = "";
		document.getElementById("DP"+nthElement).readOnly = true;
		document.getElementById("DA"+nthElement).readOnly = false;
	}
	if(paymentType == 700046) // Regular  
	{
		document.getElementById("basic"+nthElement).readOnly = false;
		document.getElementById("DA"+nthElement).readOnly = false;
		document.getElementById("contribution"+nthElement).readOnly = true;
		document.getElementById("contributionNps"+nthElement).readOnly = true;
		//document.getElementById("txtStartDate"+nthElement).readOnly = true;
		//document.getElementById("txtEndDate"+nthElement).readOnly = true;
		
		document.getElementById("basic"+nthElement).value = document.getElementById("hidBasic"+nthElement).value;
		document.getElementById("DP"+nthElement).value = document.getElementById("hidDP"+nthElement).value;
		document.getElementById("DA"+nthElement).value = document.getElementById("hidDA"+nthElement).value;
		document.getElementById("contribution"+nthElement).value = document.getElementById("hidContribution"+nthElement).value;
		
		var paycommission = document.getElementById("cmbPayCommission"+counter).value.trim();
		if(paycommission == '700016')
			{
				document.getElementById("DP"+counter).value = "0";
				document.getElementById("DP"+nthElement).readOnly = true;
			}
		else
			{
				document.getElementById("DP"+nthElement).readOnly = false;
			}
	}
	if(paymentType == 700047) // Delayed
	{
		document.getElementById("basic"+nthElement).readOnly = false;
		document.getElementById("DA"+nthElement).readOnly = false;
		document.getElementById("contribution"+nthElement).readOnly = true;
		document.getElementById("contributionNps"+nthElement).readOnly = true;
		//document.getElementById("txtStartDate"+nthElement).readOnly = true;
		//document.getElementById("txtEndDate"+nthElement).readOnly = true;
		
		document.getElementById("basic"+nthElement).value = document.getElementById("hidBasic"+nthElement).value;
		document.getElementById("DP"+nthElement).value = document.getElementById("hidDP"+nthElement).value;
		document.getElementById("DA"+nthElement).value = document.getElementById("hidDA"+nthElement).value;
		document.getElementById("contribution"+nthElement).value = document.getElementById("hidContribution"+nthElement).value;
		
		var paycommission = document.getElementById("cmbPayCommission"+counter).value.trim();
		if(paycommission == '700016')
			{
				document.getElementById("DP"+counter).value = "0";
				document.getElementById("DP"+nthElement).readOnly = true;
			}
		else
			{
				document.getElementById("DP"+nthElement).readOnly = false;
			}
	}
}

function getEmpListAfterValidation(){
	
	if(chkEmpty(document.getElementById('cmbTreasuryCode'),'Treasury Name') && 
			chkEmpty(document.getElementById('cmbDDOCode'),'DDO Name') &&
			chkEmpty(document.getElementById('cmbBillGroup'),'Bill Group') &&
			chkEmpty(document.getElementById('txtSchemeName'),'Scheme') &&
			chkEmpty(document.getElementById('txtSubSchemeName'),'Scheme') &&
			chkEmpty(document.getElementById('cmbMonth'),'Pay Month') &&
			chkEmpty(document.getElementById('cmbYear'),'Pay Year')){
		
		getEmpListforContri();

	}
}

function getEmpListforContri()
{
	Use = document.getElementById("Use").value.trim() ;
	User = document.getElementById("User").value.trim();
	Type = document.getElementById("Type").value.trim() ;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	
	var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value.trim();
	var contriThruChallanOrNot = "" ;
	
	if(cmbBillGroup == hidBGIdForContriThruChallan)
	{
		contriThruChallanOrNot = "Yes";
	}	
	
	var txtSchemeName = document.getElementById("txtSchemeName").value;
	txtSchemeName = txtSchemeName.replace(/--/g, '%20');
	
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value.trim();
	var cmbYear = document.getElementById("cmbYear").value.trim();
	var elementId = document.getElementById("hidElementId").value.trim();
	var typeOfPaymentMaster = "";
	var cmbDelayedMonth = "";
	var cmbDelayedYear = "";
	
	if(User != "TO" && (!(User == 'DDO' && (Use == 'ViewApproved' || Use == 'ViewForwarded'))) && cmbBillGroup != hidBGIdForContriThruChallan && (!((User == 'DDOAsst' || User == 'ATO') && Use == 'ViewRejected')))
		{
			if(document.getElementById("cmbTypeOfPaymentMaster") != null)
				{
					typeOfPaymentMaster = document.getElementById("cmbTypeOfPaymentMaster").value.trim();
				}
			if((User == 'DDOAsst' || User == 'ATO') && (Use == 'ViewAll'))
				{
					if(typeOfPaymentMaster == '700047')
						{
							cmbDelayedMonth = document.getElementById("cmbDelayedMonth").value.trim();
							cmbDelayedYear = document.getElementById("cmbDelayedYear").value.trim();
							
							if(cmbDelayedMonth == -1)
								{
									alert('Please select the delayed month');
									return false;
								}
							if(cmbDelayedYear == -1)
								{
									alert('Please select the delayed year');
									return false;
								}
							
							var currentMonthAndYearDate = new Date();
							var yearComboText = document.getElementById("cmbYear").options[document.getElementById("cmbYear").selectedIndex].text.trim();
							var yearCode = yearComboText.substr(0,4) ;
							if(cmbMonth == 1 || cmbMonth == 2 || cmbMonth == 3)
								{
									yearCode = Number(yearCode) + 1;
								}
							currentMonthAndYearDate.setFullYear(yearCode,cmbMonth,1);
							currentMonthAndYearDate.setMonth(currentMonthAndYearDate.getMonth()-1);
							
							var delayedMonthAndYearDate = new Date();
							var delayedYearComboText = document.getElementById("cmbDelayedYear").options[document.getElementById("cmbDelayedYear").selectedIndex].text.trim();
							var DelayedYearCode = delayedYearComboText.substr(0,4);
							if(cmbDelayedMonth == 1 || cmbDelayedMonth == 2 || cmbDelayedMonth ==3)
								{
									DelayedYearCode = Number(DelayedYearCode) + 1;
								}
							delayedMonthAndYearDate.setFullYear(DelayedYearCode,cmbDelayedMonth,1);
							delayedMonthAndYearDate.setMonth(delayedMonthAndYearDate.getMonth()-1);
							
							if(delayedMonthAndYearDate.getTime() >= currentMonthAndYearDate.getTime())
								{
									alert('Delayed date must not be a future date. Please select past month and year.');
									return false;
								}
						}
				}
		}
	
	var url =  "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&Type="+Type
	+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode
	+"&User="+User+"&Use="+Use+"&contriThruChallanOrNot="+contriThruChallanOrNot
	+"&typeOfPaymentMaster="+typeOfPaymentMaster+"&cmbDelayedMonth="+cmbDelayedMonth+"&cmbDelayedYear="+cmbDelayedYear ;
	
	//alert('url-->'+url);
	self.location.href = url ;
	
}

function getEmpListforContriDDO()
{
	var w = document.getElementById('cmbDDOCode').selectedIndex;
	var selected_text = document.getElementById('cmbDDOCode').options[w].text;
	var DDOName = selected_text;
	w = document.getElementById('cmbTreasuryCode').selectedIndex;
	selected_text = document.getElementById('cmbTreasuryCode').options[w].text;
	var TreasuryName = selected_text;
	
	
	w = document.getElementById('cmbBillGroup').selectedIndex;
	selected_text = document.getElementById('cmbBillGroup').options[w].text;
	var BillGroup = selected_text;
	
	Use = document.getElementById("Use").value ;
	User = document.getElementById("User").value;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var txtSchemeName = document.getElementById("txtSchemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	
	url = "ifms.htm?actionFlag=createZip&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
	+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&txtSchemeName="+txtSchemeName+"&treasuryCode="+treasuryCode
	+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use+"&DDOName="+DDOName+"&TreasuryName="+encodeURIComponent(TreasuryName)+"&BillGroup="+BillGroup;
	//alert(url);
	self.location.href = url;
}

function rejectContributionData()
{
	document.getElementById("btnReject").disabled = true;
	showProgressbar();
	
	var remarksForRejection = document.getElementById("txtRemarksForRejection").value;
	var voucherContriId = document.getElementById("hidContriVoucherId").value;
	
	if(remarksForRejection == "")
	{
		alert('Please enter remarks for rejection');
		document.getElementById("btnReject").disabled = false;
		hideProgressbar();
		return;
	}
	   
	Use = document.getElementById("Use").value.trim() ;
	User = document.getElementById("User").value.trim();
	
	var testCounter=1;
	var totalselectedEmployees=0;
	var dcpsContributionIds="" ;
	var totalEmployees = document.getElementById("hdnCounter").value ;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  	
			totalselectedEmployees++ ;
		}
	}

	if(totalselectedEmployees == 0)
	{
		alert('Please select at least one contribution');
		hideProgressbar();
		return false ;
	}
	
	
	if(User == 'TO' && Use == 'ViewApproved')
		{
		var approveStatus ;
			for(var i=1;i<=totalEmployees;i++)
			{
				approveStatus = document.getElementById("approveStatus"+i).value.trim();
				
				if(document.getElementById("checkbox"+i).checked)
				{
					if(testCounter==totalselectedEmployees)
					{
						if(approveStatus != 1)
							{
								dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
							}
						testCounter++ ;
					}
					else
					{
						if(approveStatus != 1)
						{
							dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
						}
						testCounter++ ;
					}
				}
			}	
		}
	else
		{
			for(var i=1;i<=totalEmployees;i++)
			{
				if(document.getElementById("checkbox"+i).checked)
				{
					if(testCounter==totalselectedEmployees)
					{
						dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
						testCounter++ ;
					}
					else
					{
						dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
						testCounter++ ;
					}
				}
			}	
		}
	
	var uri = "ifms.htm?actionFlag=RjtContriToDDOAsst";
	var url = "dcpsContributionIds="+dcpsContributionIds+"&remarksForRejection="+remarksForRejection+"&voucherContriId="+voucherContriId;
	// Actually this function rejects contributions to DDO Assistant as well as ATO. So don't go by name of action flag.
	
    var myAjax = new Ajax.Request(uri,
	       {
	        method: 'post',
	        asynchronous: false,
	        parameters:url,
	        onSuccess: function(myAjax) {
				StChngdForRejectContris(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...');} 
	          } );

}

function StChngdForRejectContris(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var success_flag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var regStatus =  XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	
		if (success_flag) {
			
			if(regStatus==4)
			{
				alert('Contributions are successfully sent back to ATO.');
			}
			else
			{
				alert('Contributions are successfully sent back to DDO Assistant.');
			}
			
			var treasuryCode = document.getElementById("cmbTreasuryCode").value;
			var txtSchemeName = document.getElementById("txtSchemeName").value;
			var cmbMonth = document.getElementById("cmbMonth").value;
			var cmbYear = document.getElementById("cmbYear").value;
			var cmbDDOCode = document.getElementById("cmbDDOCode").value;
			var cmbBillGroup = document.getElementById("cmbBillGroup").value.trim();
			var schemeCode=document.getElementById("schemeCode").value;
			var Use = document.getElementById("Use").value.trim() ;
			var User = document.getElementById("User").value.trim();
			var elementId = document.getElementById("hidElementId").value.trim();
			
			hideProgressbar();
			var url = "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
			+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use;
			
			var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value.trim();
			
			if(User == 'DDO' && cmbBillGroup != hidBGIdForContriThruChallan )
				{
					if(document.getElementById("cmbTypeOfPaymentMaster") != null)
						{
							var typeOfPaymentMaster = document.getElementById("cmbTypeOfPaymentMaster").value.trim();
							url = url + "&typeOfPaymentMaster="+typeOfPaymentMaster ;
						}
				}
			self.location.href = url ;
			
		}
}
function approveContributionData()
{
	Use = document.getElementById("Use").value.trim() ;
	User = document.getElementById("User").value.trim();
	if(User == 'DDO')
		{
			document.getElementById("btnForward").disabled = true;
		}
	if(User == 'TO')
		{
			var voucherNo = document.getElementById("txtVoucherNo").value.trim();
			var voucherDate = document.getElementById("txtVoucherDate").value.trim();
			
			if(voucherNo == "")
				{
					alert('Voucher No cannot be blank');
					return false;
				}
			if(voucherDate == "")
				{
					alert('Voucher Date cannot be blank');
					return false;
				}
			
			document.getElementById("btnApprove").disabled = true;
		}
	showProgressbar();
	
	Type = document.getElementById("Type").value.trim();
	var approvedFlag = "Approved";
	
	var voucherNo = document.getElementById("txtVoucherNo").value;
	var voucherDate = document.getElementById("txtVoucherDate").value;
	
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var txtSchemeName = document.getElementById("txtSchemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var testCounter=1;
	var totalselectedEmployees=0;
	var dcpsContributionIds="" ;
	var ForwardToPost =  document.getElementById("ForwardToPost").value;
	
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  	
			totalselectedEmployees++ ;
		}
	}
	
	if(totalselectedEmployees == 0)
	{
		alert('Please select at least one contribution');
		hideProgressbar();
		return false ;
	}
	
	if(User == 'TO' && Use == 'ViewApproved')
		{
			var approveStatus ;
				for(var i=1;i<=totalEmployees;i++)
				{
					approveStatus = document.getElementById("approveStatus"+i).value.trim();
					
					if(document.getElementById("checkbox"+i).checked)
					{
						if(testCounter==totalselectedEmployees)
						{
							if(approveStatus != 1)
								{
									dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
								}
							testCounter++ ;
						}
						else
						{
							if(approveStatus != 1)
							{
								dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
							}
							testCounter++ ;
						}
					}
				}	
		}
	else
		{
			for(var i=1;i<=totalEmployees;i++)
			{
				if(document.getElementById("checkbox"+i).checked)
				{
					if(testCounter==totalselectedEmployees)
					{
						dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
						testCounter++ ;
					}
					else
					{
						dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
						testCounter++ ;
					}
				}
			}	
		}
	   
	   var url="";
	   var uri="";
	   if(Type == "")
	   {
		   if(User=='TO')
		   {
			   uri = "ifms.htm?actionFlag=dcpsAprForContriReq";
			   url = "dcpsContributionIds="+dcpsContributionIds+"&voucherNo="+voucherNo+"&voucherDate="+voucherDate
			   		+"&cmbTreasuryCode="+treasuryCode+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&txtSchemeName="+txtSchemeName+"&schemeCode="+schemeCode+
			   		"&monthId="+cmbMonth + "&yearId="+cmbYear + "&User=" +User + "&approvedFlag="+approvedFlag;
			   url = url + "&cmbYear="+cmbYear + "&cmbMonth=" + cmbMonth + "&txtVoucherNo=" + voucherNo + "&txtVoucherDate="+voucherDate;
		   }
	   }
	   if(Type == 'Online')
	   {
		   if(User=='TO')
		   {
			   uri = "ifms.htm?actionFlag=dcpsAprForContriReq";
			   url = "dcpsContributionIds="+dcpsContributionIds+"&voucherNo="+voucherNo+"&voucherDate="+voucherDate
			   		+ "&cmbTreasuryCode="+treasuryCode+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&txtSchemeName="+txtSchemeName+"&schemeCode="+schemeCode+
			   		  "&monthId="+cmbMonth+"&yearId="+cmbYear + "&User=" +User+ "&approvedFlag="+approvedFlag;
			   url = url + "&cmbYear="+cmbYear + "&cmbMonth=" + cmbMonth + "&txtVoucherNo=" + voucherNo + "&txtVoucherDate="+voucherDate;
		   }
		   if(User=='DDO')
		   {
			   uri = "ifms.htm?actionFlag=FwdContriToTO";
			   url = "dcpsContributionIds="+dcpsContributionIds+"&ForwardToPost="+ForwardToPost +"&voucherNo="+voucherNo+"&voucherDate="+voucherDate
			   		+ "&cmbTreasuryCode="+treasuryCode+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&txtSchemeName="+txtSchemeName+"&schemeCode="+schemeCode+
				  "&monthId="+cmbMonth+"&yearId="+cmbYear + "&User=" +User;
			   url = url + "&cmbYear="+cmbYear + "&cmbMonth=" + cmbMonth + "&txtVoucherNo=" + voucherNo + "&txtVoucherDate="+voucherDate;
		   }
	   }
	   
	   var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					StChngdForApproveContris(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	   
}

function StChngdForApproveContris(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var success_flag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	Use = document.getElementById("Use").value.trim() ;
	User = document.getElementById("User").value.trim();
	Type = document.getElementById("Type").value.trim();
	
	if (success_flag) {
		
			var treasuryCode = document.getElementById("cmbTreasuryCode").value.trim();
			var txtSchemeName = document.getElementById("txtSchemeName").value.trim();
			var cmbMonth = document.getElementById("cmbMonth").value.trim();
			var cmbYear = document.getElementById("cmbYear").value.trim();
			var cmbDDOCode = document.getElementById("cmbDDOCode").value.trim();
			var cmbBillGroup = document.getElementById("cmbBillGroup").value.trim();
			var schemeCode=document.getElementById("schemeCode").value.trim();
			var Use = document.getElementById("Use").value.trim() ;
			var User = document.getElementById("User").value.trim();
			var elementId = document.getElementById("hidElementId").value.trim();
			
			var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value.trim();
			
			hideProgressbar();
			var typeOfPaymentMaster = "";
			var url = "";
			
		if(User=='TO')
		{
			alert('Contributions are successfully approved and deposited to accounts.');
			url = "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&User="+User+"&Use="+Use;
			self.location.href = url;
		}
	    if(User=='DDO')
	    {
	    	alert('Contributions are successfully forwarded.');
	    	
	    	url = "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
				+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode
				+"&User="+User+"&Use="+Use+"&Type="+Type;
	    	
	    	var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value.trim();
	    	if(cmbBillGroup != hidBGIdForContriThruChallan && document.getElementById("cmbTypeOfPaymentMaster") != null)
	    		{
	    			typeOfPaymentMaster = document.getElementById("cmbTypeOfPaymentMaster").value.trim();
	    			url = url + "&typeOfPaymentMaster="+typeOfPaymentMaster;
	    		}
	    	
	    	self.location.href = url;
	    }
	}
}

function saveContributionData(flag){
	
	showProgressbar();
	
	Use = document.getElementById("Use").value.trim() ;
	User = document.getElementById("User").value.trim();
	
	if(flag == 1)
		{
			document.getElementById("btnSaveData").disabled = true;
		}
	
	var returnFromOuterFunction = false;
	
	// Below validation of Delayed fin year Id and delayed month added.
	var typeOfPaymentMaster = "";
	
	if(document.getElementById("cmbTypeOfPaymentMaster") != null)
		{
			typeOfPaymentMaster = document.getElementById("cmbTypeOfPaymentMaster").value.trim();
			if(typeOfPaymentMaster == '700047')
				{
					var cmbDelayedMonth = document.getElementById("cmbDelayedMonth").value.trim();
					var cmbDelayedYear = document.getElementById("cmbDelayedYear").value.trim();
					if(cmbDelayedYear == '' || cmbDelayedYear == -1)
					{
						alert('Please select delayed year');
						document.getElementById("btnSaveData").disabled = false;
						hideProgressbar();
						return;
					}
					if(cmbDelayedMonth == '' || cmbDelayedMonth == -1)
					{
						alert('Please select delayed month');
						document.getElementById("btnSaveData").disabled = false;
						hideProgressbar();
						return;
					}
				}
		}
	
	if(Use == 'ViewReverted')
		{
			var answer = confirm("Deleted or changed contibutions will not be retrieved back. Are you sure you want to save these contributions ? Click cancel and go back if you do not want to change. Click OK if you want to save.");
			if(!answer)
				{
					hideProgressbar();
					return;
				}
		}
	
	var saveOrForwardFlag=flag;
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value.trim();
	var cmbDDOCode = document.getElementById("cmbDDOCode").value.trim();
	var ForwardToPost =  document.getElementById("ForwardToPost").value.trim();
	
	// Validations for dates.
	var totalRows = document.getElementById("hdnCounter").value.trim();
	var continueFlag = 0;
	var finalSelectedEmployee = 0;;
	var txtStartDate = "";
	var txtEndDate = "";
	var name = "";
	var cmbBillGroup = document.getElementById("cmbBillGroup").value.trim();
	var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value.trim();
	
	// Validation Starts
	
	for(var k=1;k<=totalRows;k++)
	{
		//function to skip those checkboxes which are deleted
		continueFlag = 0;
		
		for(var lInt=0;lInt<deletedContriArray.length;lInt++)
		{
			if(deletedContriArray[lInt] == k)
			{
				continueFlag = 1 ;
			}
		}
		
		if(continueFlag == 1)
		{
			continue ;
		}
		
		//alert(k +'th'+ document.getElementById("checkbox"+k));
			if(document.getElementById("checkbox"+k).checked)
			{  
				//alert("hiii i m roshan");
				//alert(document.getElementById("contribution"+k).value.trim());
				//alert(Number(document.getElementById("contribution"+k).value.trim()));
				
				if(!chkEmpty(document.getElementById("name"+k),'Name') || 
						!chkEmpty(document.getElementById("dcpsId"+k),'DCPS ID') ||
						!chkEmpty(document.getElementById("txtStartDate"+k),'Contribution Start Date') ||
						!chkEmpty(document.getElementById("txtEndDate"+k),'Contribution End Date') ||
						!chkEmpty(document.getElementById("cmbPayCommission"+k),'Pay Commission') ||
						!chkEmpty(document.getElementById("cmbTypeOfPayment"+k),'Payment Type') ||
						!chkEmpty(document.getElementById("contribution"+k),'Contribution') ||
						!chkEmpty(document.getElementById("contributionNps"+k),'Contribution'))
				{
					//alert(k);
					hideProgressbar();
					document.getElementById("btnForward").disabled = false;
					if(flag == 1)
					{
						document.getElementById("btnSaveData").disabled = false;
					}
					return false;
				}
				
				else if(document.getElementById("contribution"+k).value.trim() == 0)
				{
					//alert('comes here');
					name = document.getElementById("name"+k).innerHTML;
					alert('Contribution of '+ name + ' cannot be zero' );
					hideProgressbar();
					document.getElementById("btnForward").disabled = false;
					document.getElementById("contribution"+k).value="";
					document.getElementById("contribution"+k).focus();
					return false;
				}
				
				else if(document.getElementById("cmbTypeOfPayment"+k).value != '700048' && document.getElementById("cmbTypeOfPayment"+k).value != '700049' && cmbBillGroup!=hidBGIdForContriThruChallan )
				{
					if(
						!chkEmpty(document.getElementById("basic"+k),'Basic') ||
						!chkEmpty(document.getElementById("DP"+k),'DP') ||
						!chkEmpty(document.getElementById("DA"+k),'DA')
						){
						//alert(k);
						//alert(document.getElementById("cmbTypeOfPayment"+k));
						hideProgressbar();
						document.getElementById("btnForward").disabled = false;
						if(flag == 1)
						{
							document.getElementById("btnSaveData").disabled = false;
						}
						return false;
					}
					
					// Basic zero and contribution validations removed.
					
					/*
					if(document.getElementById("basic"+k).value.trim() == 0)
					{
						name = document.getElementById("name"+k).innerHTML;
						alert('Basic pay of '+ name + ' cannot be zero' );
						hideProgressbar();
						document.getElementById("btnForward").disabled = false;
						document.getElementById("basic"+k).value="";
						document.getElementById("basic"+k).focus();
						return false;
					}

					if(document.getElementById("contribution"+k).value.trim() == 0)
					{
						alert('comes here');
						name = document.getElementById("name"+k).innerHTML;
						alert('Contribution of '+ name + ' cannot be zero' );
						hideProgressbar();
						document.getElementById("btnForward").disabled = false;
						document.getElementById("contribution"+k).value="";
						document.getElementById("contribution"+k).focus();
						return false;
					}
					*/
					
				}
				else if(document.getElementById("cmbTypeOfPayment"+k).value == '700048')
				{
					if(
						!chkEmpty(document.getElementById("DA"+k),'DA')
						){
						hideProgressbar();
						document.getElementById("btnForward").disabled = false;
						if(flag == 1)
						{
							document.getElementById("btnSaveData").disabled = false;
						}
						return false;
					}
				}
				
				finalSelectedEmployee = k ;	
			}
	}

	// Below code commented for to allow to delete a single employee's contribution.
	
	/*
	if(finalSelectedEmployee == 0)
	{
		if(Use != 'ViewRejected')
			{
				alert('Please select at least one contribution');
				hideProgressbar();
				document.getElementById("btnForward").disabled = false;
				if(flag == 1)
				{
					document.getElementById("btnSaveData").disabled = false;
				}
				return false;
			}
	}
	
	*/
	
	if(cmbBillGroup == hidBGIdForContriThruChallan)
	{
		if(!checkEmptyChallanForContrithruChallan())
		{
			hideProgressbar();
			document.getElementById("btnForward").disabled = false;
			if(flag == 1)
			{
				document.getElementById("btnSaveData").disabled = false;
			}
			return false;
		}
	}
	
	// Validation Ends
	
	for(var i=1;i<=totalRows;i++)
		{
			continueFlag = 0 ;
		
			for(var lInt=0;lInt<deletedContriArray.length;lInt++)
			{
				if(deletedContriArray[lInt] == i)
					{
						continueFlag = 1 ;
					}
			}
			
			if(continueFlag == 1)
				{
					continue ;
				}
			
			if(cmbBillGroup != hidBGIdForContriThruChallan)  // Doesn't check for contribution through Challan.
				{
					if( document.getElementById("cmbTypeOfPaymentMaster") != null)
						{
							typeOfPaymentMaster = document.getElementById("cmbTypeOfPaymentMaster").value.trim();
							if(typeOfPaymentMaster == '700047')
								{
									if(!compareDates(i))
										{
											hideProgressbar();
											document.getElementById("btnForward").disabled = false;
											if(flag == 1)
											{
												document.getElementById("btnSaveData").disabled = false;
											}
											return false; 
										}
								}
						}
				}
			
			/*
			txtStartDate =  document.getElementById("txtStartDate"+i).value.trim() ;
			txtEndDate =  document.getElementById("txtEndDate"+i).value.trim() ;
			
			if(txtStartDate == "")
				{
					name = document.getElementById("name"+i).innerHTML ;
					alert('Please enter contribution start date for ' + name);
					hideProgressbar();
					return false;
				}
			if(txtEndDate == "")
				{
					name = document.getElementById("name"+i).innerHTML ;
					alert('Please enter contribution end date for ' + name);
					hideProgressbar();
					return false;
				}
			*/
		}
	
	var deletedContributionIndexes = "";
	var deletedContributionIdPks = "";
	for(var lInt=0;lInt<deletedContriArray.length;lInt++)
	{
		deletedContributionIndexes = deletedContributionIndexes + deletedContriArray[lInt] + "~";
		deletedContributionIdPks = deletedContributionIdPks + deletedContriIdPKsArray[lInt] + "~";
	}

	var url = 'ifms.htm?actionFlag=saveContributions&cmbTreasuryCode='+cmbTreasuryCode+'&cmbDDOCode='+cmbDDOCode+'&saveOrForwardFlag='+saveOrForwardFlag+
			'&ForwardToPost='+ForwardToPost+'&deletedContributionIndexes='+deletedContributionIndexes+'&deletedContributionIdPks='+deletedContributionIdPks ;
	document.forms[0].action = url;
	document.forms[0].method = 'Post';
	document.forms[0].submit();
}

/*function saveContributionData(flag)
{	
	showProgressbar();
	Use = document.getElementById("Use").value ;
	User = document.getElementById("User").value;
	Type = document.getElementById("Type").value;
	var saveOrForwardFlag=flag;
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var dcpsEmpIds = "" ;
	var cmbPayCommission = "" ;
	var cmbTypeOfPayment = "" ;
	var basic = "" ;
	var DP = "" ;
	var DA = "" ;
	var contribution = "" ;
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var schemeCode=document.getElementById("schemeCode").value;
	var dcpsContributionIds="";
	var txtStartDate="";
	var txtEndDate="";
	var voucherNo = document.getElementById("txtVoucherNo").value;
	var voucherDate = document.getElementById("txtVoucherDate").value;
	var hidBGIdForContriThruChallan = document.getElementById("hidBGIdForContriThruChallan").value;
	
	var continueFlag ;
	
   try
   {
	var finalSelectedEmployee=0;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		//function to skip those checkboxes which are deleted
		continueFlag = 0;
		
		for(var lInt=0;lInt<deletedContriArray.length;lInt++)
		{
			if(deletedContriArray[lInt] == k)
			{
				continueFlag = 1 ;
			}
		}
		
		if(continueFlag == 1)
		{
			continue ;
		}
		
		//alert(k +'th'+ document.getElementById("checkbox"+k));
		
			if(document.getElementById("checkbox"+k).checked)
			{  
				if(!chkEmpty(document.getElementById("name"+k),'Name') || 
						!chkEmpty(document.getElementById("dcpsId"+k),'DCPS ID') ||
						!chkEmpty(document.getElementById("txtStartDate"+k),'Contribution Start Date') ||
						!chkEmpty(document.getElementById("txtEndDate"+k),'Contribution End Date') ||
						!chkEmpty(document.getElementById("cmbPayCommission"+k),'Pay Commission') ||
						!chkEmpty(document.getElementById("cmbTypeOfPayment"+k),'Payment Type') ||
						!chkEmpty(document.getElementById("contribution"+k),'Contribution'))
				{
					//alert(k);
					hideProgressbar();
					return false;
				}
				else if(document.getElementById("cmbTypeOfPayment"+k).value != '700048' && document.getElementById("cmbTypeOfPayment"+k).value != '700049' && cmbBillGroup!=hidBGIdForContriThruChallan )
				{
					if(
						!chkEmpty(document.getElementById("basic"+k),'Basic') ||
						!chkEmpty(document.getElementById("DP"+k),'DP') ||
						!chkEmpty(document.getElementById("DA"+k),'DA')
						){
						//alert(k);
						//alert(document.getElementById("cmbTypeOfPayment"+k));
						hideProgressbar();
						return false;
					}
				}
				else if(document.getElementById("cmbTypeOfPayment"+k).value == '700048')
				{
					if(
						!chkEmpty(document.getElementById("DA"+k),'DA')
						){
						hideProgressbar();
						return false;
					}
				}
				finalSelectedEmployee = k ;	
			}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one contribution');
		hideProgressbar();
		return false; 
	}
	
	
	if(cmbBillGroup == hidBGIdForContriThruChallan)
	{
		if(!checkEmptyChallanForContrithruChallan())
		{
			hideProgressbar();
			return false;
		}
	}

	for(var i=1;i<=totalEmployees;i++)
	{
		
		continueFlag = 0;
		
		for(lInt=0;lInt<deletedContriArray.length;lInt++)
		{
			if(deletedContriArray[lInt] == i)
			{
				continueFlag = 1;
			}
		}
		
		//alert(k +'th'+ document.getElementById("checkbox"+k));
		
		if(continueFlag == 1)
		{
			continue ;
		}
		
				if(document.getElementById("checkbox"+i).checked)
				{
					if(i==finalSelectedEmployee)
					{
						dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value;
						cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommission"+i).value;
						cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPayment"+i).value;
						
						if(document.getElementById("basic"+i).value.trim() == "")
						{
							document.getElementById("basic"+i).value = 0;
						}
						basic = basic + document.getElementById("basic"+i).value;
						if(document.getElementById("DP"+i).value.trim() == "")
						{
							document.getElementById("DP"+i).value = 0;
						}
						DP = DP + document.getElementById("DP"+i).value;
						
						if(document.getElementById("DA"+i).value.trim() == "")
						{
							document.getElementById("DA"+i).value = 0;
						}
						DA = DA + document.getElementById("DA"+i).value;
						
						contribution = contribution + document.getElementById("contribution"+i).value;
						dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
						txtStartDate = txtStartDate +  document.getElementById("txtStartDate"+i).value ;
						txtEndDate = txtEndDate +  document.getElementById("txtEndDate"+i).value ;
					}
					else
					{
						dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value + "~";
						cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommission"+i).value + "~";
						cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPayment"+i).value + "~";
						if(document.getElementById("basic"+i).value.trim() == "")
						{
							document.getElementById("basic"+i).value = 0;
						}
						basic = basic + document.getElementById("basic"+i).value + "~";
						
						if(document.getElementById("DP"+i).value.trim() == "")
						{
							document.getElementById("DP"+i).value = 0;
						}
						DP = DP + document.getElementById("DP"+i).value + "~";
						
						if(document.getElementById("DA"+i).value.trim() == "")
						{
							document.getElementById("DA"+i).value = 0;
						}
						DA = DA + document.getElementById("DA"+i).value + "~";
						contribution = contribution + document.getElementById("contribution"+i).value + "~";
						dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
						txtStartDate = txtStartDate +  document.getElementById("txtStartDate"+i).value + "~" ;
						txtEndDate = txtEndDate +  document.getElementById("txtEndDate"+i).value + "~" ;
					}
				}
		
	}	
   }
   catch(e)
   {
   }
	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=saveContributions&dcpsEmpIds='+dcpsEmpIds+'&dcpsContributionIds='+dcpsContributionIds+'&txtStartDate='+txtStartDate+'&txtEndDate='+txtEndDate
	+'&cmbPayCommission='+cmbPayCommission+'&cmbTypeOfPayment='+cmbTypeOfPayment+
	'&basic='+basic+'&DP='+DP+'&DA='+DA+'&contribution='+contribution+'&monthId='+monthId+'&yearId='+yearId+'&cmbTreasuryCode='+cmbTreasuryCode+
	'&cmbDDOCode='+cmbDDOCode+'&cmbBillGroup='+cmbBillGroup+'&schemeCode='+schemeCode+'&voucherNo='+voucherNo+'&voucherDate='+voucherDate+'&User='+User;
	
	//alert('urlSave-->'+url);

	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
						
						if(saveOrForwardFlag==1)
						{
							alert('Records Saved Successfully.');
							var treasuryCode = document.getElementById("cmbTreasuryCode").value;
							var txtSchemeName = document.getElementById("txtSchemeName").value;
							var cmbMonth = document.getElementById("cmbMonth").value;
							var cmbYear = document.getElementById("cmbYear").value;
							
							if(Use == 'ViewReverted')
							{
								hideProgressbar();
								self.location.href="ifms.htm?actionFlag=revertAcceptedContri";
							}
							else
							{
								hideProgressbar();
								var elementId = document.getElementById("hidElementId").value.trim();
								self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
									+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use+"&Type="+Type ;
							}
						}
						if(saveOrForwardFlag==2)
						{
							var totalContributionsForFwd = XmlHiddenValues[0].childNodes[1].text ;
							var contriIdsForFwd = "";

							for(var lInt1=0;lInt1<totalContributionsForFwd;lInt1++)
							{
								if(lInt1==totalContributionsForFwd-1)
								{
									contriIdsForFwd = contriIdsForFwd + XmlHiddenValues[0].childNodes[lInt1+2].text ;
								}
								else
								{
									contriIdsForFwd = contriIdsForFwd + XmlHiddenValues[0].childNodes[lInt1+2].text + "~" ;
								}
							}
							
							var voucherContriId = XmlHiddenValues[0].childNodes[lInt1+2].text;
							//var voucherContriId = XMLDoc.getElementsByTagName('voucherContriId');
							//alert('voucherContriId-->'+voucherContriId);
							
							var ForwardToPost =  document.getElementById("ForwardToPost").value;
							var uriForward ="" ;
							if(Type=="")
							{
								//alert('Inside Manual Contribution');
								if(User=='ATO')
								{
									uriForward = "ifms.htm?actionFlag=dcpsFwdForContriReq&dcpsContributionIds="+contriIdsForFwd+"&ForwardToPost="+ForwardToPost+"&voucherContriId="+voucherContriId;
								}
							}
							//alert('Type-->'+Type);
							//alert('User-->'+User);
							if(Type=='Online')
							{
								//alert('Inside Online Contribution');
								if(User=='DDOAsst')
								{
									uriForward = "ifms.htm?actionFlag=FwdContriToDDO&dcpsContributionIds="+contriIdsForFwd+"&ForwardToPost="+ForwardToPost+"&voucherContriId="+voucherContriId;
								}
								
							}
							
							xmlHttpNew=GetXmlHttpObject();
							
							if (xmlHttpNew==null)
							{
								alert ("Your browser does not support AJAX!");
								return;
							} 
							xmlHttpNew.onreadystatechange= function()
							{
								if (xmlHttpNew.readyState == 4) {
									if (xmlHttpNew.status == 200) {
										
										XMLDocNew = xmlHttpNew.responseXML.documentElement;
										
										var XmlHiddenValuesNew = XMLDocNew.getElementsByTagName('XMLDOC');
										var success_flag = XmlHiddenValuesNew[0].childNodes[0].text;
										
										if(success_flag=='true')
										{
											
										alert("Contributions are forwarded successfully");
										var treasuryCodeFwd = document.getElementById("cmbTreasuryCode").value;
										var cmbDDOCodeFwd = document.getElementById("cmbDDOCode").value;
										var cmbBillGroupFwd = document.getElementById("cmbBillGroup").value;
										var txtSchemeNameFwd = document.getElementById("txtSchemeName").value;
										var schemeCodeFwd =  document.getElementById("schemeCode").value;
										var cmbMonthFwd = document.getElementById("cmbMonth").value;
										var cmbYearFwd = document.getElementById("cmbYear").value;
										
										hideProgressbar();
										var elementId = document.getElementById("hidElementId").value.trim();
										self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&cmbDDOCode="+cmbDDOCodeFwd+"&cmbBillGroup="+cmbBillGroupFwd
												+"&cmbMonth="+cmbMonthFwd+"&cmbYear="+cmbYearFwd+"&schemeName="+txtSchemeNameFwd+"&treasuryCode="+treasuryCodeFwd+"&schemeCode="+schemeCodeFwd+"&User="+User+"&Use="+Use+"&Type="+Type ;
										}
									  }
									}
							};
							xmlHttpNew.open("POST",uriForward,false);
							xmlHttpNew.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
							xmlHttpNew.send(uriForward);
						}
					}
				}
			}
	} ;
xmlHttp.open("POST",url,true);
xmlHttp.send(url);

}*/

function deleteContributionData()
{
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var dcpsEmpIds = "" ;
	var finalSelectedEmployee=0;
	var dcpsContributionIds="";
	var User = document.getElementById("User").value;
	var Use = document.getElementById("Use").value ;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{ 
			finalSelectedEmployee = k ;
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one contribution');
		return false; 
	}
	execScript('n = msgbox("Do you really want to delete this contribution ?","4132")',"vbscript","Kapil");
	if(n==7)
	{
		return false; 
	}
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
			}
			else
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
			}
		}
	}	

	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=deleteContributions&dcpsContributionIds='+dcpsContributionIds ;

	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Contributions Deleted Successfully');
							var treasuryCode = document.getElementById("cmbTreasuryCode").value;
							var cmbDDOCode = document.getElementById("cmbDDOCode").value;
							var cmbBillGroup = document.getElementById("cmbBillGroup").value;
							var txtSchemeName = document.getElementById("txtSchemeName").value;
							var schemeCode =  document.getElementById("schemeCode").value;
							var cmbMonth = document.getElementById("cmbMonth").value;
							var cmbYear = document.getElementById("cmbYear").value;
							//alert("ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use)
							self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
									+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use ;
					}
				}
			}
	} ;

	xmlHttp.open("POST",url,true);
	xmlHttp.send(url);
}

function forwardContributionData()
{
	document.getElementById("btnForward").disabled = true;
	saveContributionData(2);
}

function saveVoucherDetails()
{
	Use = document.getElementById("Use").value ;
	User = document.getElementById("User").value;
	var voucherNo = document.getElementById("txtVoucherNo").value;
	var voucherDate = document.getElementById("txtVoucherDate").value;
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var txtSchemeName = document.getElementById("txtSchemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	var approvedFlag="";
	
	if(User=='DDO' && Use=='ViewApproved' )
	{
		approvedFlag = "Approved";
	}
	
	if(voucherNo == "")
	{
		alert('Please enter Voucher No');
		return false;
	}
	
	if(voucherDate == "")
	{
		alert('Please enter Voucher Date');
		return false;
	}
	
	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 
	
	url = 'ifms.htm?actionFlag=saveVoucherDtlsForContri&voucherNo='+voucherNo+'&voucherDate='+voucherDate+'&treasuryCode='+cmbTreasuryCode+
	   '&cmbDDOCode='+cmbDDOCode+'&cmbBillGroup='+cmbBillGroup+'&txtSchemeName='+txtSchemeName+'&schemeCode='+schemeCode+
	   '&monthId='+cmbMonth + '&yearId='+cmbYear +'&User='+User +'&approvedFlag='+approvedFlag+'&cmbTreasuryCode='+cmbTreasuryCode+
	   '&Use='+Use+'&cmbMonth='+cmbMonth+'&cmbYear='+cmbYear+'&txtVoucherNo='+voucherNo+'&txtVoucherDate='+voucherDate;
	
	//alert(url);
	
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Voucher details for contribution are saved.');
							
							//alert("ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use)
							var elementId = document.getElementById("hidElementId").value.trim();
							self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&elementId="+elementId+"&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
									+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&cmbTreasuryCode="+cmbTreasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use+"&treasuryCode="+cmbTreasuryCode ;
									//treasury code added twice purposefully....don't delete
					}
				}
			}
	} ;

	xmlHttp.open("POST",url,true);
	xmlHttp.send(url);
}

function goBackToRevertRequestList()
{
	self.location.href="ifms.htm?actionFlag=revertAcceptedContri";
}
function daysInMonth(anyDateInMonth) {    
	return new Date(anyDateInMonth.getYear(),anyDateInMonth.getMonth()+1,0).getDate();
} 
function validateDatesForRegularType(str3,str4,startDateForThisMonth,endDateForThisMonth,rowNo)
{
	if(compareDatesWithoutAlert(str3,startDateForThisMonth,'<'))
	{
		alert('Contribution start date should be within this month only for Regular payment type');
		document.getElementById("txtStartDate"+rowNo).value ="";
		document.getElementById("txtEndDate"+rowNo).value ="";
		return false;
	}
	if(compareDatesWithoutAlert(str3,endDateForThisMonth,'>'))
	{
		alert('Contribution start date should be within this month only for Regular payment type');
		document.getElementById("txtStartDate"+rowNo).value ="";
		document.getElementById("txtEndDate"+rowNo).value ="";
		return false;
	}
	if(compareDatesWithoutAlert(str4,startDateForThisMonth,'<'))
	{
		alert('Contribution end date should be within this month only for Regular payment type');
		document.getElementById("txtStartDate"+rowNo).value ="";
		document.getElementById("txtEndDate"+rowNo).value ="";
		return false;
	}
	if(compareDatesWithoutAlert(str4,endDateForThisMonth,'>'))
	{
		alert('Contribution end date should be within this month only for Regular payment type');
		document.getElementById("txtStartDate"+rowNo).value ="";
		document.getElementById("txtEndDate"+rowNo).value ="";
		return false;
	}
	
	// Below function checks overlapping period for multiple Regular Type contributions
	checkOverlapForRegular(rowNo);
}

function checkOverlapForRegular(counter)
{
	var one_day = 1000*60*60*24;
	str3 = document.getElementById("txtStartDate"+counter).value.trim(); 
	str4 = document.getElementById("txtEndDate"+counter).value.trim(); 
	
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
		
		var totalRows = document.getElementById("hdnCounter").value.trim();
		
		var continueFlag = 0;
		
		for(var i=1;i<=totalRows;i++){
			
			continueFlag = 0;
			
			for(var lInt=0;lInt<deletedContriArray.length;lInt++)
			{
				if(deletedContriArray[lInt] == i)
				{
					continueFlag = 1 ;
				}
			}	
			
			if(continueFlag == 1)
			{
				continue ;
			}
			
			if(document.getElementById("dcpsempId"+counter).value.trim() == document.getElementById("dcpsempId"+i).value.trim())
				{
				    var str1 = document.getElementById("txtStartDate"+i).value.trim(); 
				    var str2 = document.getElementById("txtEndDate"+i).value.trim(); 
			
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
			
					if(i!=counter && ((Diff1>=0 && Diff2>=0) || (Diff3>=0 && Diff4>=0))){
						
						alert('The dates overlap for Regular Type in the same month.');
						document.getElementById("txtStartDate"+counter).value="";
						document.getElementById("txtEndDate"+counter).value="";
						return false;
					}
				}
			}
	}
	
	return true;
}

function validateDatesForDelayedType(str3,str4,rowNo)
{
	
	 var monthForContriStartDate = parseInt(str3.substring(3,5),10);
	 var monthForContriEndDate = parseInt(str4.substring(3,5),10); 
	 if(monthForContriStartDate != monthForContriEndDate)
	 {
		 alert('Contribution Start Date and End Date must be within the month.');
		 document.getElementById("txtStartDate"+rowNo).value ="";
 		 document.getElementById("txtEndDate"+rowNo).value ="";
		 return false;
	 }
}
function validateDatesForDAArrearType(str3,str4,rowNo)
{
	
}
function validateDatesForPayArrearType(str3,str4,rowNo)
{
	
}
function compareDates(rowNo)
{
	User = document.getElementById("User").value.trim();
	Use = document.getElementById("Use").value.trim();
	if(User == 'DDO' || (User == 'TO' && Use != 'ViewReverted'))
		{
			return ;
		}
	
	
	var today= new Date();
	var dcpsID = document.getElementById("dcpsId"+rowNo).innerHTML;
	var totalRows = document.getElementById("hdnCounter").value;

	var str3 = document.getElementById("txtStartDate"+rowNo).value;
    var str4 = document.getElementById("txtEndDate"+rowNo).value;
    var joiningDate = document.getElementById("hidJoiningDate"+rowNo).value;
	var startDateForThisMonth = document.getElementById("hidEmpStartDate"+rowNo).value;
	var endDateForThisMonth = document.getElementById("hidEmpEndDate"+rowNo).value;
	
	var lArrEndDateOfMonth = endDateForThisMonth.split("/");	
	var EndDateOfMonthObject = new Date(lArrEndDateOfMonth[1] + "/" + lArrEndDateOfMonth[0] + "/" + lArrEndDateOfMonth[2]);
    
    if(str3 == '' || str4 == ''){
    	return false;
    }
    
    if(compareDatesWithoutAlert(str3,str4,'>'))
    {
    	alert('Contribution Start Date cannot be greater than Contribution End Date.');
    	
    	document.getElementById("txtStartDate"+rowNo).value = "";
    	document.getElementById("txtEndDate"+rowNo).value = "";
    	return false;
    }
    
    if(compareDatesWithoutAlert(str3,joiningDate,'<'))
    {
    	alert('Contribution Start Date cannot be before Employee joining date - '+joiningDate);
    	document.getElementById("txtStartDate"+rowNo).value = "";
    	document.getElementById("txtEndDate"+rowNo).value = "";
    	return false;
    }
    
    var dt3  = parseInt(str3.substring(0,2),10); 
    var mon3 = parseInt(str3.substring(3,5),10); 
    var yr3  = parseInt(str3.substring(6,10),10); 
    var dt4  = parseInt(str4.substring(0,2),10); 
    var mon4 = parseInt(str4.substring(3,5),10); 
    var yr4  = parseInt(str4.substring(6,10),10); 
    
    var date3 = new Date(yr3, mon3-1, dt3);
    var date4 = new Date(yr4, mon4-1, dt4);
    
    
    var paymentType = document.getElementById("cmbTypeOfPayment"+rowNo).value;
    
    if(paymentType != 700046 && paymentType != -1)
    {
    	if(compareDatesWithoutAlert(str4,startDateForThisMonth,'>'))
    	{
    		alert('For Non Regular types Contribution dates must not be from current month.');
    		document.getElementById("txtStartDate"+rowNo).value ="";
    		document.getElementById("txtEndDate"+rowNo).value ="";
    		return false;
    	}
    }
    
    if(paymentType == 700046)	// Regular Type
    {
    	validateDatesForRegularType(str3,str4,startDateForThisMonth,endDateForThisMonth,rowNo);
    }
    if(paymentType == 700047)	// Delayed Type
    {
    	validateDatesForDelayedType(str3,str4,rowNo);
    }
    if(paymentType == 700048)	// DA Arrears Type
    {
    	//validateDatesForDAArrearType(str3,str4,rowNo);
    }
    if(paymentType == 700049)	// Pay Arrears Type
    {
    	//validateDatesForPayArrearType(str3,str4,rowNo);
    }
    
    var lBlFlagForCheckContri = checkContriOfEmpForSelectedPeriod(str3,str4,rowNo);
    
    var contriDoneForThisPrd = document.getElementById("contriDoneForThisPrd"+rowNo).value.trim();
    if(contriDoneForThisPrd == 'YES')
    	{
    		return false;
    	}
    
    
    var one_day = 1000*60*60*24;
    
    if(Math.ceil((date3.getTime() - EndDateOfMonthObject.getTime())/(one_day))>0){
    	alert('Future Date is not allowed');
    	document.getElementById("txtStartDate"+rowNo).value ="";
    	return false;
    }
    if(Math.ceil((date4.getTime() - EndDateOfMonthObject.getTime())/(one_day))>0){
    	alert('Future Date is not allowed');
    	document.getElementById("txtEndDate"+rowNo).value ="";
    	return false;
    }
    
    var continueFlag = 0;
    
	for(var i=1;i<totalRows;i++){
		
		continueFlag = 0;
		
		for(var lInt=0;lInt<deletedContriArray.length;lInt++)
		{
			if(deletedContriArray[lInt] == i)
			{
				continueFlag = 1 ;
			}
		}	
		
		if(continueFlag == 1)
		{
			continue ;
		}
		
	    var str1 = document.getElementById("txtStartDate"+i).value; 
	    var str2 = document.getElementById("txtEndDate"+i).value; 

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

		if(i!=rowNo && document.getElementById("dcpsId"+i).innerHTML == dcpsID && ((Diff1>=0 && Diff2>=0) || (Diff3>=0 && Diff4>=0))){
			alert('The contribution dates overlaps for the Same employee');
			document.getElementById("txtStartDate"+rowNo).value="";
			document.getElementById("txtEndDate"+rowNo).value="";
			return false;
		}
	}
	
	return true;
}

function checkContriOfEmpForSelectedPeriod(str3,str4,rowNo)
{
	var dcpsEmpId = document.getElementById("dcpsempId"+rowNo).value;
	var typeOfPayment = document.getElementById("cmbTypeOfPayment"+rowNo).value;
	var uri="ifms.htm?actionFlag=checkContriOfEmpForSelectedPeriod";
	var url="dcpsEmpId="+dcpsEmpId+"&contriStartDate="+str3+"&contriEndDate="+str4+"&typeOfPayment="+typeOfPayment;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForCheckContri(myAjax,rowNo);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForCheckContri(myAjax,rowNo)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue.trim();
	
	var name = document.getElementById("name"+rowNo).innerHTML;
	
	if(lBlFlag == 'false')
	{
		alert('Contribution is already done for employee '+ name +' in this period.');
		document.getElementById("txtStartDate"+rowNo).value ="";
		document.getElementById("txtEndDate"+rowNo).value ="";
		document.getElementById("contriDoneForThisPrd"+rowNo).value = "YES";
		return false;
	}
	else
	{
		document.getElementById("contriDoneForThisPrd"+rowNo).value = "NO";
		return true;
	}
	return true;
}

function AddNewRowContriForContriThruChallan(counter)
{
	rowAddedForContrThruChallan = true;
	var color1 = "#F5F5F5";
	var color2 = "#7B68EE";

	var selectedRow=counter ;
	var table=document.getElementById("vo");

	var cnt=table.rows.length;
	var nextRow=Number(document.getElementById("hdnCounter").value) + 1 ;
	
	var newRow = table.insertRow(-1);

	newRow.style.backgroundColor = color1;
	newRow.style.borderColor = color2;
	
	var Cell1 = newRow.insertCell(-1);
	var Cell2 = newRow.insertCell(-1);
	var Cell3 = newRow.insertCell(-1);
	var Cell4 = newRow.insertCell(-1);
	var Cell5 = newRow.insertCell(-1); 
	var Cell6 = newRow.insertCell(-1); 
	var Cell7 = newRow.insertCell(-1); 
	var Cell8 = newRow.insertCell(-1); 
	var Cell9 = newRow.insertCell(-1); 
	var Cell10 = newRow.insertCell(-1); 
	var Cell11 = newRow.insertCell(-1); 
	var Cell12 = newRow.insertCell(-1); 
	var Cell13 = newRow.insertCell(-1); 
		
		Cell1.width="15%";
		Cell2.width="15%";
		Cell3.width="14%";
		Cell4.width="14%";
		Cell5.width="9%";
		Cell6.width="11%";
		Cell7.width="8%";
		Cell8.width="7%";
		Cell9.width="7%";
		Cell10.width="7%";
		Cell11.width="5%";
		Cell12.width="5%";
			
	   	Cell1.align="left";
	   	Cell2.align="left";
	   	Cell3.align="left";
	   	Cell4.align="left";
		Cell5.align="left";
	   	Cell6.align="left";
		Cell7.align="left";
	   	Cell8.align="left";
		Cell9.align="left";
		Cell10.align="left";
		Cell11.align="left";
	   	Cell12.align="center";
	   	Cell13.align="center";
	   	
	   	Cell1.innerHTML = '<input type="text" name="name'+nextRow+'" id="name'+nextRow+'" value="" style="width:95%" readOnly="readOnly" />' ;
	   	Cell2.innerHTML = '<input type="text" style="width:95%" name="dcpsId'+nextRow+'" id="dcpsId'+nextRow+'" value="" onblur="getDtlsForContriThruChallan('+nextRow+');" />' ;
	   	Cell3.innerHTML = ' <input type="text" style="width: 75%" name="txtStartDate'+nextRow+'" id="txtStartDate'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
	   			+ '	onBlur="validateDate(this);compareDates('+nextRow+');" value="" width="10" /> '   
	   			+ ' <img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtStartDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>'  ;
	   	
		Cell4.innerHTML = ' <input type="text"  style="width: 75%" name="txtEndDate'+nextRow+'" id="txtEndDate'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
				+ '	onBlur="validateDate(this);compareDates('+nextRow+');" value="" width="10" /> '   
				+ ' <img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtEndDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');" style="cursor: pointer;"/>'  
				+ ' <input type="hidden" id="contriDoneForThisPrd'+nextRow+'" name="contriDoneForThisPrd'+nextRow+'" value="NO"/>' ;
		
		Cell5.innerHTML = ' <select name="cmbPayCommission'+nextRow+'" id="cmbPayCommission'+nextRow+'"  style="width: 95%" >' 
								+ ' <option value="-1">-- Select --</option>' +LISTPAYCOMMISSIONS +'</select>';
		Cell6.innerHTML = ' <select name="cmbTypeOfPayment'+nextRow+'" id="cmbTypeOfPayment'+nextRow+'"  style="width: 95%" onchange="changeEditabilityOfFields('+nextRow+');compareDates('+nextRow+');"  >'
								+ ' <option value="-1">-- Select --</option>' +LISTTYPESOFPAYMENT +'</select>';
		
		Cell7.innerHTML = ' <input type="text" style="width: 95%"  id="basic'+nextRow+'" name="basic'+nextRow+'" value="" onchange="changeDpDaAndContri('+nextRow+');" />' ;
		Cell8.innerHTML = ' <input type="text" style="width: 95%"  id="DP'+nextRow+'" name="DP'+nextRow+'"  value=""/>' ;
		Cell9.innerHTML = ' <input type="text" style="width: 95%"  id="DA'+nextRow+'" name="DA'+nextRow+'"  value=""/>' ;
		Cell10.innerHTML = ' <input type="text" style="width: 95%"  id="contribution'+nextRow+'" name="contribution'+nextRow+'"  value=""/>' ;
		
		Cell11.innerHTML = ' <a href=# onclick="AddNewRowContriForContriThruChallan('+nextRow+');"> <label id="AddNewRowContri">Add</label></a> ';
		Cell12.innerHTML = ' <a href=# onclick="DeleteRowContri('+nextRow+');"> <label id="DeleteRowContri">Delete</label></a> ';
		
		Cell13.innerHTML =  ' <input type="checkbox" id="checkbox'+nextRow+'" name="checkbox'+nextRow+'" value="'+0+'" checked="checked" style="display:none"/>' 
		+  ' <input type="hidden" name="dcpsempId'+ nextRow+'" id="dcpsempId'+ nextRow+'" value="" />' 
		+  ' <input type="hidden" name="daRate" id="daRate'+nextRow+'" value="" />' ;
		
		Cell13.innerHTML = 	Cell13.innerHTML  
		+  ' <input type="hidden" name="hidBasic" id="hidBasic'+nextRow+'" value="0" />' 
		+  ' <input type="hidden" name="hidDP" id="hidDP'+nextRow+'" value="0" />' 
		+  ' <input type="hidden" name="hidDA" id="hidDA'+nextRow+'" value="0" />' 
		+  ' <input type="hidden" name="hidContribution" id="hidContribution'+nextRow+'" value="0" />'
		;
		
		Cell13.innerHTML = 	Cell13.innerHTML 
		+ ' <input type="hidden" name="hidEmpStartDate" id="hidEmpStartDate'+nextRow+'" value="' + document.getElementById("hidEmpStartDate"+selectedRow).value+'" />'
		+ ' <input type="hidden" name="hidEmpEndDate" id="hidEmpEndDate'+nextRow+'" value="' + document.getElementById("hidEmpEndDate"+selectedRow).value+'" />'
		+ ' <input type="hidden" name="hidJoiningDate" id="hidJoiningDate'+nextRow+'" value="" />'
		;
		
		document.getElementById("txtTotalRecords").value=Number(document.getElementById("txtTotalRecords").value)+1;
		document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value)+1;
		
}

function getDtlsForContriThruChallan(counter)
{
	var dcpsId = document.getElementById("dcpsId"+counter).value;
	if(dcpsId.length == 20)
	{
		var uri="ifms.htm?actionFlag=displayEmployeeDtls";
		var url="dcpsId="+dcpsId;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForPopupDtlsForContriThruChallan(myAjax,counter);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		alert('Please enter a complete DCPS Id');
	}
}

function getDataStateChangedForPopupDtlsForContriThruChallan(myAjax,counter)
{
	XMLDoc = myAjax.responseXML.documentElement;
	if(XMLDoc == null)
	{
		hideProgressbar();
	}
	
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var empName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var payCommission = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var basic = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var doj = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	var dcpsEmpId = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
	var daRate = XmlHiddenValues[0].childNodes[5].firstChild.nodeValue;
	alert(daRate);
	document.getElementById("name"+counter).value =  empName ;
	document.getElementById("cmbPayCommission"+counter).value =  payCommission ;
	document.getElementById("basic"+counter).value =  basic ;
	document.getElementById("hidJoiningDate"+counter).value =  doj ;
	document.getElementById("dcpsempId"+counter).value =  dcpsEmpId ;
	document.getElementById("daRate"+counter).value = Number(daRate*0.01);
	document.getElementById("cmbTypeOfPayment"+counter).value =  '700046' ;
	
	document.getElementById("name"+counter).readOnly = true;
	//document.getElementById("cmbPayCommission"+counter).readOnly = true;
}

function checkEmptyChallanForContrithruChallan()
{
	var challanNo = document.getElementById("txtVoucherNo").value;
	var challanDate = document.getElementById("txtVoucherDate").value;
	if(challanNo == "")
	{
		alert('Please enter Challan no');
		return false;
	}
	if(challanDate == "")
	{
		alert('Please enter Challan date');
		return false;
	}
	return true;
}

function changeDPEditability(counter)
{
	var paycommission = document.getElementById("cmbPayCommission"+counter).value.trim();
	if(paycommission == '700016')
	{
		document.getElementById("DP"+counter).value = "0";
		document.getElementById("DP"+counter).readOnly = true;
	}
	else 
	{
		document.getElementById("DP"+counter).value = document.getElementById("hidDP"+counter).value.trim();
		document.getElementById("DP"+counter).readOnly = false;
	}
}

function changeDAForSelectedPrd(counter)
{
	var typeOfPayment = document.getElementById("cmbTypeOfPayment"+counter).value.trim();
	var contriStartDate = document.getElementById("txtStartDate"+counter).value.trim();
	var contriEndDate = document.getElementById("txtEndDate"+counter).value.trim();
	var payCommission = document.getElementById("cmbPayCommission"+counter).value.trim();
	
	if(contriStartDate != "" && contriEndDate != "")
		{
			if(typeOfPayment == 700047 || typeOfPayment == 700048)
				
			{
				 var uri = 'ifms.htm?actionFlag=getDARateForGivenPrd';
				 var url = 'contriStartDate='+ contriStartDate+'&contriEndDate='+contriEndDate+'&payCommission='+payCommission;
				 var myAjax = new Ajax.Request(uri,
					       {
					        method: 'post',
					        asynchronous: false,
					        parameters:url,
					        onSuccess: function(myAjax) {
								StChngdForGettingDARateForSlctdPrd(myAjax,counter);
							},
					        onFailure: function(){ alert('Something went wrong...');} 
					          } );
			}
		}
}

function StChngdForGettingDARateForSlctdPrd(myAjax,counter)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var daRate =0;
	
	if(XmlHiddenValues[0].childNodes[0].firstChild != null)
		{
			daRate = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		}
	
	document.getElementById("daRate"+counter).value = Number(daRate*0.01);
	 
	var basic = document.getElementById("basic"+counter).value.trim();
	var DP = document.getElementById("DP"+counter).value.trim();
	var DA = document.getElementById("DA"+counter).value.trim(); alert(daRate);
	document.getElementById("DA"+counter).value = Math.round(Number(Number((Number(basic)+ Number(DP))) * Number(Number(daRate)*0.01))) ;
	var tempDA = document.getElementById("DA"+counter).value;  

	var tempcontribution = Number(basic) + Number(tempDA) + Number(DP)  ; //alert(tempcontribution )
	var contribution =Math.ceil((tempcontribution * 0.10).toFixed(2));  //alert(contribution )
	var npscontribution =Math.ceil((tempcontribution * 0.14).toFixed(2)); // alert(npscontribution )
	document.getElementById("contribution" + counter).value = contribution ;
	document.getElementById("contributionNps" + counter).value = npscontribution ;
	

}

function addMonthYearComboForDelayed()
{
	User = document.getElementById("User").value.trim();
	Use = document.getElementById("Use").value.trim();
	if((User == "DDOAsst" || User == "ATO") && ( Use == 'ViewAll'))
	{
		typeOfPaymentMaster = document.getElementById("cmbTypeOfPaymentMaster").value.trim();
		if(typeOfPaymentMaster == '700047') // For Delayed shows the delayed month and year combo
		{
			document.getElementById("delayedMonthAndYearCombos").style.display = '' ;
		}
		else
		{
			document.getElementById("delayedMonthAndYearCombos").style.display = 'none' ;
		}
		
		// Code added for setting DA Arrer and Pay arrear dates
		if(typeOfPaymentMaster == '700048') // For Delayed shows the delayed month and year combo
		{
			if(document.getElementById("DAArrearsDatesDivDisplay") != null)
				{
					document.getElementById("DAArrearsDatesDivDisplay").style.display = '' ;
				}
		}
		else
		{
			if(document.getElementById("DAArrearsDatesDivDisplay") != null)
				{
					document.getElementById("DAArrearsDatesDivDisplay").style.display = 'none' ;
				}
		}
		
		if(typeOfPaymentMaster == '700049') // For Delayed shows the delayed month and year combo
		{
			if(document.getElementById("PayArrearsDatesDivDisplay") != null)
				{
					document.getElementById("PayArrearsDatesDivDisplay").style.display = 'inline' ;
				}
		}
		else
		{
			if(document.getElementById("PayArrearsDatesDivDisplay") != null)
				{
					document.getElementById("PayArrearsDatesDivDisplay").style.display = 'none' ;
				}
		}
	}
}

function getLastDateOfMonth(Year,Month){
	return(new Date((new Date(Year, Month+1,1))-1));
	}

function changeContriForSelectedPrd(counter)
{
	var str3 = document.getElementById("txtStartDate"+counter).value.trim(); // Contribution Start Date
	var str4 = document.getElementById("txtEndDate"+counter).value.trim(); // Contribution End Date
	var startDateForThisMonth = document.getElementById("hidEmpStartDate"+counter).value;
	var endDateForThisMonth = document.getElementById("hidEmpEndDate"+counter).value;
	
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
	    
	    dt3  = parseInt(startDateForThisMonth.substring(0,2),10); 
	    mon3 = parseInt(startDateForThisMonth.substring(3,5),10); 
	    yr3  = parseInt(startDateForThisMonth.substring(6,10),10); 
	    dt4  = parseInt(endDateForThisMonth.substring(0,2),10); 
	    mon4 = parseInt(endDateForThisMonth.substring(3,5),10); 
	    yr4  = parseInt(endDateForThisMonth.substring(6,10),10); 
	    
	    var date5 = new Date(yr3, mon3-1, dt3);
	    var date6 = new Date(yr4, mon4-1, dt4);
	   
	    var contributionDays = daysBetween(date3,date4);
	    var totalDaysInMonth = daysBetween(date5,date6);
	    
	    //alert('contributionDays-->'+contributionDays);
	    //alert('totalDaysInMonth-->'+totalDaysInMonth);
	    
	    var nthElement = counter ; 
		var basic = document.getElementById("hidBasic"+nthElement).value ;
		var DP;
		var payCommission = document.getElementById("cmbPayCommission"+nthElement).value.trim();
		if(payCommission == '700015' || payCommission == '700345')
		{
			DP = Number(basic/2);
		}
		else
		{
			DP = 0;
		}
		var DARate = Number (document.getElementById("daRate"+nthElement).value);
		var DA = (Number(Number(basic)+Number(DP)) * DARate).toFixed(2);
		var tempcontribution = Number(basic) + Number(Math.round(DA)) + Number(DP)  ;
		var contribution = (Number(tempcontribution * 0.10)).toFixed(2);
		/* NPS calculation BY naveen Priya Sinha*/
			/*const compareDates = (d1, d2) => {
			  let date1 = new Date(d1).getTime();
			  let date2 = new Date(d2).getTime();

			  if (date1 < date2) {
			    console.log(`${d1} is less than ${d2}`);
			  } else if (date1 > date2) {
			    console.log(`${d1} is greater than ${d2}`);
			  } else {
			    console.log(`Both dates are equal`);
			  }
			};*/
			var contributionNps = 0;
			 let date1 = new Date(str3).getTime();
			  let date2 = new Date("04/01/2019").getTime();
			//compareDates(str3, "04/01/2019");
			
			if (date1 < date2) {
			    console.log(`${date1} is less than ${date2}`);
			    contributionNps = (Number(tempcontribution * 0.10)).toFixed(2);
			  } else if (date1 > date2) {
			    console.log(`${date1} is greater than ${date2}`);
			    contributionNps = (Number(tempcontribution * 0.14)).toFixed(2);
			  }
		/* NPS calculation BY naveen Priya Sinha*/
				
		
		//alert(contributionNps);
			//alert(contribution+contributionDays+totalDaysInMonth);
		var newBasic = (Number(basic) * Number(contributionDays) / Number(totalDaysInMonth));
		var newDP = (Number(DP) * Number(contributionDays) / Number(totalDaysInMonth));
		var newDA =  (Number(DA) * Number(contributionDays) / Number(totalDaysInMonth));
		var newContri =  (Number(contribution) * Number(contributionDays) / Number(totalDaysInMonth));
		var newContriNps =  (Number(contributionNps) * Number(contributionDays) / Number(totalDaysInMonth));
		//alert( newContriNps+">>" +Math.ceil(newContriNps));
		//alert( newContri+">>" +Math.ceil(newContri));
		document.getElementById("basic" + nthElement).value = Math.round(newBasic) ;
		document.getElementById("DP" + nthElement).value = Math.round(newDP) ;
		document.getElementById("DA" + nthElement).value = Math.round(newDA) ;
		document.getElementById("contribution" + nthElement).value =  Math.ceil(newContri); //alert(newContri);
		document.getElementById("contributionNps" + nthElement).value =  Math.ceil(newContriNps) ;
		 
	    
	}
	
}

function daysBetween(first, second) { 
	// Copy date parts of the timestamps, discarding the time parts.  
	var one = new Date(first.getFullYear(), first.getMonth(), first.getDate());   
	var two = new Date(second.getFullYear(), second.getMonth(), second.getDate());   
	// Do the math.     
	var millisecondsPerDay = 1000 * 60 * 60 * 24;   
	var millisBetween = two.getTime() - one.getTime();   
	
	var days = millisBetween / millisecondsPerDay;  
	days = Number(days) + 1;
	// Round down.    
	return Math.floor(Number(days)); 
} 

function selectCorrectFinYear()
{
	User = document.getElementById("User").value.trim();
	Use = document.getElementById("Use").value.trim();
	if(User == 'DDOAsst' || User == 'ATO')
		{
			alert('Please select correct Financial Pay Year. For January, February and March - Select 2017-2018 and for April onwards - Select 2018-2019.');
		}
}

function showProgressBarWhileBillsLoad()
{
	//alert('comes in showprogressbar');
	showProgressbar();
}
function hideProgressBarAfterBillsLoad()
{
	//alert('comes in hideprogressbar');
	hideProgressbar();
}

function setDAArrearDates()
{
	showProgressbar();
	var txtDAArrearStartDate = "";
	var txtDAArrearEndDate = "";
	
	if(document.getElementById("txtDAArrearStartDate") != null)
		{
			txtDAArrearStartDate = document.getElementById("txtDAArrearStartDate").value.trim();
		}
	if(document.getElementById("txtDAArrearEndDate") != null)
		{
			txtDAArrearEndDate = document.getElementById("txtDAArrearEndDate").value.trim();
		}
	if(txtDAArrearStartDate == "")
		{
			alert('Please fill DA Arrear Start date');
			hideProgressbar();
			return false;
		}
	if(txtDAArrearEndDate == "")
		{
			alert('Please fill DA Arrear End date');
			hideProgressbar();
			return false;
		}
	
	var hdnGoPressed = "";
	if(document.getElementById("hdnGoPressed") != null)
		{
			hdnGoPressed = document.getElementById("hdnGoPressed").value.trim();
		}
	
	if(hdnGoPressed != 'Y')
		{
			alert('Please press Go Button first');
			hideProgressbar();
			document.getElementById("btnDAArrearSetDates").disabled = false;
			return ;
		}
	
	var totalRows = "";
	
	if(document.getElementById("hdnCounter") != null)
		{
			totalRows = document.getElementById("hdnCounter").value;
		}
	else
		{
			hideProgressbar();
		}
	
	if(txtDAArrearStartDate != "" && txtDAArrearEndDate != "" && document.getElementById("hdnCounter") != null)
	{
		for(var k=1;k<=totalRows;k++)
		{
			//function to skip those checkboxes which are deleted
			continueFlag = 0;
			
			for(var lInt=0;lInt<deletedContriArray.length;lInt++)
			{
				if(deletedContriArray[lInt] == k)
				{
					continueFlag = 1 ;
				}
			}
			
			if(continueFlag == 1)
			{
				continue ;
			}
			
			//alert(k +'th'+ document.getElementById("checkbox"+k));
				if(document.getElementById("checkbox"+k).checked)
				{ 
					if(document.getElementById("cmbTypeOfPayment"+k).value.trim() == '700048')
						{
							document.getElementById("txtStartDate"+k).value = txtDAArrearStartDate;
							document.getElementById("txtEndDate"+k).value = txtDAArrearEndDate;
							changeDAForSelectedPrd(k);
							/*
							if(!compareDates(k))
								{
									break;
								}
							*/
							
							// Code to be added to get DA Rate commonly
							
							//document.getElementById("hidBasic"+k).value = document.getElementById("hidBasicMstEmp"+k).value;
							//document.getElementById("basic"+k).value = document.getElementById("hidBasicMstEmp"+k).value;
							
							document.getElementById("basic"+k).readOnly = true;
							document.getElementById("basic"+k).value = 0;
							
							//changeDAForSelectedPrdMaster(k);
							
							// Code to be added to get DA Rate commonly ends
							//changeContriForSelectedPrd(k);
						}
				}
		}
	}

	hideProgressbar();
	
}

function setPayArrearDates()
{
	showProgressbar();
	var txtPayArrearStartDate = "";
	var txtPayArrearEndDate = "";
	
	if(document.getElementById("txtPayArrearStartDate") != null)
		{
			txtPayArrearStartDate = document.getElementById("txtPayArrearStartDate").value.trim();
		}
	if(document.getElementById("txtPayArrearEndDate") != null)
		{
			txtPayArrearEndDate = document.getElementById("txtPayArrearEndDate").value.trim();
		}
	if(txtPayArrearStartDate == "")
		{
			alert('Please fill Pay Arrear Start date');
			hideProgressbar();
			return false;
		}
	if(txtPayArrearEndDate == "")
		{
			alert('Please fill Pay Arrear End date');
			hideProgressbar();
			return false;
		}
	
	var hdnGoPressed = "";
	if(document.getElementById("hdnGoPressed") != null)
		{
			hdnGoPressed = document.getElementById("hdnGoPressed").value.trim();
		}
	
	if(hdnGoPressed != 'Y')
		{
			alert('Please press Go Button first');
			hideProgressbar();
			document.getElementById("btnPayArrearSetDates").disabled = false;
			hideProgressbar();
			return ;
		}
	
	var totalRows = "";
	
	if(document.getElementById("hdnCounter") != null)
		{
			totalRows = document.getElementById("hdnCounter").value;
		}
	else
		{
			hideProgressbar();
		}
	
	
	if(txtPayArrearStartDate != "" && txtPayArrearEndDate != "" && document.getElementById("hdnCounter") != null)
	{
		for(var k=1;k<=totalRows;k++)
		{
			//function to skip those checkboxes which are deleted
			continueFlag = 0;
			
			for(var lInt=0;lInt<deletedContriArray.length;lInt++)
			{
				if(deletedContriArray[lInt] == k)
				{
					continueFlag = 1 ;
				}
			}
			
			if(continueFlag == 1)
			{
				continue ;
			}
			
			//alert(k +'th'+ document.getElementById("checkbox"+k));
				if(document.getElementById("checkbox"+k).checked)
				{ 
					if(document.getElementById("cmbTypeOfPayment"+k).value.trim() == '700049')
						{
							document.getElementById("txtStartDate"+k).value = txtPayArrearStartDate;
							document.getElementById("txtEndDate"+k).value = txtPayArrearEndDate;
							
							/*
							if(!compareDates(k))
								{
									break;
								}
							*/
							
							// Code to be added to get DA Rate commonly
							
							//document.getElementById("hidBasic"+k).value = document.getElementById("hidBasicMstEmp"+k).value;
							//document.getElementById("basic"+k).value = document.getElementById("hidBasicMstEmp"+k).value;
							
							document.getElementById("basic"+k).readOnly = false;
							document.getElementById("basic"+k).value = 0;
							
							//changeDAForSelectedPrdMaster(k);
							
							// Code to be added to get DA Rate commonly ends
							//changeContriForSelectedPrd(k);
						}
				}
		}
	}

	hideProgressbar();
	
}