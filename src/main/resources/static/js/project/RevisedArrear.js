var myrules = {
		'input' : function(element)
		{
			element.onblur = function()
			{
				if(element.type=='radio')
				{
					element.className = 'pnsnradiotag';
				}
				if(element.type=='checkbox')
				{
					element.className = 'pnsnradiotag';
				}
				if(element.type=='text')
				{
					element.className = 'pnsntexttag';
				}
				if(element.name == 'txtOldBasicEffFrom' || element.name == 'txtOldBasicEffTo' || element.name == 'txtNewBasicEffFrom' || element.name == 'txtNewBasicEffTo' || element.name == 'txtOldMAEffFrom' || element.name == 'txtOldMAEffTo' || element.name == 'txtNewMAEffFrom' || element.name == 'txtNewMAEffTo' || element.name == 'txtOldDPEffFrom' || element.name == 'txtOldDPEffTo' || element.name == 'txtNewDPEffFrom' || element.name == 'txtNewDPEffFrom' || element.name == 'txtOldCVPEffFrom' || element.name == 'txtOldCVPEffTo' || element.name == 'txtNewCVPEffFrom' || element.name == 'txtNewCVPEffTo' || element.name == 'txtOldPnsnCutEffFrom' || element.name == 'txtOldPnsnCutEffTo' || element.name == 'txtNewPnsnCutEffFrom' || element.name == 'txtNewPnsnCutEffTo' || element.name == 'txtOldReEmpEffFrom' || element.name == 'txtOldReEmpEffTo' || element.name == 'txtNewReEmpEffFrom' || element.name == 'txtNewReEmpEffTo')
				{
					//validateDate(this);
					if(element.id == 'txtOldBasicEffFrom1')
					{
						document.getElementById('txtOldMAEffFrom1').value = element.value;
						document.getElementById('txtOldDPEffFrom1').value = element.value;
					}
					if(element.id == 'txtNewBasicEffFrom1')
					{
						document.getElementById('txtNewMAEffFrom1').value = element.value;
						document.getElementById('txtNewDPEffFrom1').value = element.value;
					}
					if(element.id == 'txtOldBasicEffTo1')
					{
						document.getElementById('txtOldMAEffTo1').value = element.value;
						document.getElementById('txtOldDPEffTo1').value = element.value;
					}
					if(element.id == 'txtNewBasicEffTo1')
					{
						document.getElementById('txtNewMAEffTo1').value = element.value;
						document.getElementById('txtNewDPEffTo1').value = element.value;
					}
				} 
			}
			element.onfocus = function()
			{
				if(element.type=='text')
				{
					element.className = 'pnsntextOnFocustag';
				}
			  	
			}
		},
		'select' : function(element)
		{
			element.onblur = function()
			{
				element.className = 'pnsnselecttag';
			}
			element.onfocus = function()
			{
				element.className = 'pnsnselectOnFocustag';
			}
		}
	};
	
	Behaviour.register(myrules);



function createXMLHttpRequest() 
{ 
 		var ua; 
	
	if(window.XMLHttpRequest) 
	{ 
		try 
		{ 
			ua = new XMLHttpRequest(); 
		} 
		catch(e) 
		{ 
			ua = false; 
		} 
	} 
	else if(window.ActiveXObject) 
	{ 
		try 
		{ 
			ua = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 
		catch(e) 
		{ 
			ua = false; 
		} 
	} 
	return ua; 
}

function IsEmptyFun(varStr,alrtStr)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1" || element == "0.00")
	{
		alert(alrtStr);
	//	goToFieldTab(varStr,tabNo);
		document.getElementById(varStr).focus();
		return false;
	}
	return true;
}



//VALIDATE PPO NUMBER
function validatePPONumber()
{

	if(document.getElementById("txtPPONO").value != "")
	{
		var uri;
		uri = 'ifms.htm?actionFlag=validationOfPPONo';
		validatePpoNoUsingAjax(uri);
	}
	else
	{
		 document.getElementById("txtPPONO").value="";
   	 	 document.getElementById("txtDOB").value="";
		 document.getElementById("txtDOR").value="";
	     document.getElementById("txtPensionerName").value="";
		 document.getElementById("txtOldBasic1").value="";
		 document.getElementById("txtOldBasicEffFrom1").value="";
	}
}

function validatePpoNoUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&PpoNo="+document.getElementById("txtPPONO").value,
		        onSuccess: function(myAjax) {
					validatePpoNoOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
  /* xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
      return;
   }  
           
   xmlHttp.onreadystatechange=validatePPOOnStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(uri);*/
   
}


function validatePpoNoOnStateChanged(myAjax) 
{ 
	   var XMLDoc =  myAjax.responseXML.documentElement;
	   
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	    
	     if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
	     {
	    	 getPensionerDetailsFromPPONO();
	     }
	     if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'N')
	     {
	    	 alert("Please Enter Correct PPO Number.");
	    	 document.getElementById("txtPPONO").value="";
	    	 document.getElementById("txtPPONO").focus();
	         document.getElementById("txtDOB").value="";
			 document.getElementById("txtDOR").value="";
		     document.getElementById("txtPensionerName").value="";
			 document.getElementById("txtOldBasic1").value="";
			 document.getElementById("txtOldBasicEffFrom1").value="";
	     }
	
	
/*   if (xmlHttp.readyState==complete_state)
   { 
	  var XMLDoc=xmlHttp.responseXML.documentElement;
    
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
     
     if(XmlHiddenValues[0].childNodes[1].text == 'Y')
     {
    	 	getPensionerDetailsFromPPONO();
     }
     if(XmlHiddenValues[0].childNodes[1].text == 'N')
     {
    	 alert("Please Enter Correct PPO Number.");
    	 document.getElementById("txtPPONO").value="";
    	 document.getElementById("txtPPONO").focus();
         document.getElementById("txtDOB").value="";
		 document.getElementById("txtDOR").value="";
	     document.getElementById("txtPensionerName").value="";
		 document.getElementById("txtOldBasic1").value="";
		 document.getElementById("txtOldBasicEffFrom1").value="";
	   
     }

   }*/
}
function validateBankDtls()
{
	if(IsEmptyFun("cmbBankName","Please Select Bank Name.")==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbBankBranch","Please Select Branch Name.")==false)
	{
		return false;
	}
	if(IsEmptyFun("txtAccountNo","Please Enter Account Number.")==false)
	{
		return false;
	}
	return true;
}

function getPnsnrDtlsFromBankBranch()
{
	if(validateBankDtls())
	{
		var uri;
		var bankCode=document.getElementById("cmbBankName").value;
		var branchCode=document.getElementById("cmbBankBranch").value;
		var accountNo=document.getElementById("txtAccountNo").value;
		uri = 'ifms.htm?actionFlag=getPensionerDtlsFromPPONo&bankCode='+bankCode+'&branchCode='+branchCode+'&accountNo='+accountNo;
		getPnsnrDtlsUsingAjax(uri);
	}
}

function getPensionerDetailsFromPPONO()
{
	if(document.getElementById("txtPPONO").value != '')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getPensionerDtlsFromPPONo&PpoNo='+document.getElementById("txtPPONO").value;
		getPnsnrDtlsUsingAjax(uri);
	}
	else
	{
		 document.getElementById("txtPPONO").value="";
    	 document.getElementById("hidPensnReqId").value="";
 	     document.getElementById("hidPnsnrCode").value="";
	     document.getElementById("hidppono").value="";
	     document.getElementById("hidheadcode").value=""; 
	     document.getElementById("hidFamMemDOB").value="";
	     document.getElementById("hidFamMemDOD").value="";
	     document.getElementById("hidPnsnrDOB").value="";
	     //document.getElementById("txtDOB").value="";  
	     document.getElementById("hidPnsnrDOD").value="";
	     document.getElementById("txtOldBasic1").value="";
	     document.getElementById("hidCommensionDate").value="";   
	     document.getElementById("txtOldBasicEffFrom1").value="";
	     document.getElementById("txtNewBasicEffFrom1").value="";
	     document.getElementById("hidFP1Date").value="";
	     document.getElementById("hidFP2Date").value="";
	     document.getElementById("hidNewFP1Basic").value="";
	     document.getElementById("hidNewFP2Basic").value="";
	     document.getElementById("hidEndDate").value="";
	     document.getElementById("hidlastBillMonth").value="";
	     document.getElementById("txtDOR").value="";
	     document.getElementById("hidPnsnrDOR").value="";
	     document.getElementById("txtPensionerName").value="";
	     document.getElementById("hdnPensionerName").value="";
	}
   
}
function getPnsnrDtlsUsingAjax(uri)
{
	var bankCode=document.getElementById("cmbBankName").value;
	var branchCode=document.getElementById("cmbBankBranch").value;
	var accountNo=document.getElementById("txtAccountNo").value;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
					getPnsnrDtlsCaseStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
    
/*   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
      return;
   }  
        
   xmlHttp.onreadystatechange=onCaseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(uri);*/
   
}

function getPnsnrDtlsCaseStateChanged(myAjax)
{
	var XMLDoc =  myAjax.responseXML.documentElement;
	   
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var XmlHiddenValues1 = XMLDoc.getElementsByTagName('REEMPFROMDT');  
    var XmlHiddenReEmpl = XMLDoc.getElementsByTagName('REEMPDTLS');  
    var XmlHiddenPunishCutCount =XMLDoc.getElementsByTagName('PUNISHAMT');  
    var XmlHiddenPunishCut = XMLDoc.getElementsByTagName('PUNISHCUTDTLS');  
    var i=1;
    for(var j = document.getElementById("ReEmp").rows.length; j > 2;j--)
    {
   	 document.getElementById("ReEmp").deleteRow(j -1);
    }
    for(var p = document.getElementById("tblPnshmntCut").rows.length; p > 2;p--)
    {
   	 document.getElementById("tblPnshmntCut").deleteRow(p -1);
    }
    
    if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue != 'Y')
    {       
	      if((document.getElementById('ReEmp').rows.length)==2)
	  	  {
	  		  addRowReEmp();
	  	  }
	      if((document.getElementById('tblPnshmntCut').rows.length)==1)
	  	  {
    		  punishmentCutAddRow();
	  	  }
    	  document.getElementById("hidPensnReqId").value=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	      document.getElementById("hidPnsnrCode").value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	      document.getElementById("hidppono").value=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	      document.getElementById("txtPPONO").value=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	      document.getElementById("hidheadcode").value=XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue; 
	      if(XmlHiddenValues[0].childNodes[5].childNodes[0] != undefined && XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue != 'null')
	      {
	      	document.getElementById("hidFamMemDOB").value=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[6].childNodes[0] != undefined && XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue != 'null')
	      {
	      	document.getElementById("hidFamMemDOD").value=XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue != 'null')
	      {
	    	  document.getElementById("hidPnsnrDOB").value=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	    	  document.getElementById("txtDOB").value=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[8].childNodes[0] != undefined && XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue != 'null')
	      { 
	      	  document.getElementById("hidPnsnrDOD").value=XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue;
	      }
	      document.getElementById("txtNewBasic1").value=XmlHiddenValues[0].childNodes[9].childNodes[0].nodeValue;
	      document.getElementById("hidCommensionDate").value=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue; 
	     
	      document.getElementById("txtOldBasicEffFrom1").value=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue;
	     	
	      if(XmlHiddenValues[0].childNodes[11].childNodes[0].nodeValue != 'null')
	      {
	    	  document.getElementById("hidFP1Date").value=XmlHiddenValues[0].childNodes[11].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[12].childNodes[0].nodeValue != 'null')
	      {
	    	  document.getElementById("hidFP2Date").value=XmlHiddenValues[0].childNodes[12].childNodes[0].nodeValue;
	      }
	      document.getElementById("hidNewFP1Basic").value=XmlHiddenValues[0].childNodes[13].childNodes[0].nodeValue;
	      document.getElementById("hidNewFP2Basic").value=XmlHiddenValues[0].childNodes[14].childNodes[0].nodeValue;
	      if(XmlHiddenValues[0].childNodes[16].childNodes[0] != undefined)
	      {
	      	document.getElementById("hidEndDate").value=XmlHiddenValues[0].childNodes[16].childNodes[0].nodeValue;
	      }
	      document.getElementById("hidlastBillMonth").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
//	      document.getElementById("txtOldBasicEffTo1").value=XmlHiddenValues[0].childNodes[17].text;
//	      document.getElementById("txtNewBasicEffTo1").value=XmlHiddenValues[0].childNodes[17].text;
	     
	      if(XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue != 'null')
	      {
	    	  document.getElementById("txtDOR").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
	    	  document.getElementById("hidPnsnrDOR").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
	      }
	      document.getElementById("txtPensionerName").value=XmlHiddenValues[0].childNodes[18].childNodes[0].nodeValue;
	      document.getElementById("hdnPensionerName").value=XmlHiddenValues[0].childNodes[18].childNodes[0].nodeValue;
	      document.getElementById("hidBankName").value=XmlHiddenValues[0].childNodes[19].childNodes[0].nodeValue;
	      document.getElementById("hidBranchName").value=XmlHiddenValues[0].childNodes[20].childNodes[0].nodeValue;
	      document.getElementById("hidAccountNo").value=XmlHiddenValues[0].childNodes[21].childNodes[0].nodeValue;
	      document.getElementById("hidCurrentDt").value=XmlHiddenValues[0].childNodes[22].childNodes[0].nodeValue;
          var rowCount = document.getElementById("hidReEmpDtlsGridSize").value;
          rowCount = rowCount - 1;
	      var k=0;
	    
	      for(var j=0;j<XmlHiddenValues1.length;j++)
	      {
		      document.getElementById("txtOldReEmpEffFrom"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue;
		      k++;
		      if(XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue != 'null')
		      {
		    	  document.getElementById("txtOldReEmpEffTo"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue;
		      }
		      k++;
		      document.getElementById("cmbDAInPnsnSal"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue;
		      k++;
		      rowCount++;
		      if(j!=XmlHiddenValues1.length-1)
		      {
	    	 	 addRowReEmp();
		      }
	    	 
	      }
	      var punishCnt = document.getElementById("hidPnshmntCutSize").value;
	      punishCnt = punishCnt - 1;
	      var p=0;
	      
	      for(var j=0;j<XmlHiddenPunishCutCount.length;j++)
	      {  
		      document.getElementById("txtPnshmntAmount"+punishCnt).value=XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue;
		      p++;
		      if(XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue != 'null')
		      {
		    	  document.getElementById("txtPnshmntFromDate"+punishCnt).value=XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue;
		      }
		      p++;
		      document.getElementById("txtPnshmntToDate"+punishCnt).value=XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue;
		      p++;
		      punishCnt++;
		      if(j!=XmlHiddenPunishCutCount.length-1)
		      {
		    	  punishmentCutAddRow();
		      }
	    	 
	      }
    }
    if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
    {
			alert("Please Enter Correct PPO Number or Bank Detail.");
			document.getElementById("txtPPONO").value="";
			document.getElementById("txtDOB").value="";
			document.getElementById("txtDOR").value="";
			document.getElementById("txtPensionerName").value="";
			document.getElementById("txtOldBasic1").value="";
		    document.getElementById("txtOldBasicEffFrom1").value="";
		
    }
}
/*
function onCaseStateChanged() 
{ 

   if (xmlHttp.readyState==complete_state)
   { 
	  var XMLDoc=xmlHttp.responseXML.documentElement;
    
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
      var XmlHiddenValues1 = XMLDoc.getElementsByTagName('REEMPFROMDT');  
      var XmlHiddenReEmpl = XMLDoc.getElementsByTagName('REEMPDTLS');  
        
   	  var i=1;
      if(XmlHiddenValues[0].childNodes[0].text != 'Y')
      {
          if((document.getElementById('ReEmp').rows.length)==2)
    	  {
    		  addRowReEmp();
    	  }
          document.getElementById("hidPensnReqId").value=XmlHiddenValues[0].childNodes[0].text;
  	      document.getElementById("hidPnsnrCode").value=XmlHiddenValues[0].childNodes[1].text;
	      document.getElementById("hidppono").value=XmlHiddenValues[0].childNodes[2].text;
	      document.getElementById("txtPPONO").value=XmlHiddenValues[0].childNodes[2].text;
	      document.getElementById("hidheadcode").value=XmlHiddenValues[0].childNodes[4].text; 
	      document.getElementById("hidFamMemDOB").value=XmlHiddenValues[0].childNodes[5].text;
	      document.getElementById("hidFamMemDOD").value=XmlHiddenValues[0].childNodes[6].text;
	      document.getElementById("hidPnsnrDOB").value=XmlHiddenValues[0].childNodes[7].text;
	      document.getElementById("txtDOB").value=XmlHiddenValues[0].childNodes[7].text;  
	      document.getElementById("hidPnsnrDOD").value=XmlHiddenValues[0].childNodes[8].text;
	      document.getElementById("txtOldBasic1").value=XmlHiddenValues[0].childNodes[9].text;
	      document.getElementById("hidCommensionDate").value=XmlHiddenValues[0].childNodes[10].text;   
	      document.getElementById("txtOldBasicEffFrom1").value=XmlHiddenValues[0].childNodes[10].text;
	      //document.getElementById("txtNewBasicEffFrom1").value=XmlHiddenValues[0].childNodes[10].text;
	      document.getElementById("hidFP1Date").value=XmlHiddenValues[0].childNodes[11].text;
	      document.getElementById("hidFP2Date").value=XmlHiddenValues[0].childNodes[12].text;
	      document.getElementById("hidNewFP1Basic").value=XmlHiddenValues[0].childNodes[13].text;
	      document.getElementById("hidNewFP2Basic").value=XmlHiddenValues[0].childNodes[14].text;
	      document.getElementById("hidEndDate").value=XmlHiddenValues[0].childNodes[16].text;
	      document.getElementById("hidlastBillMonth").value=XmlHiddenValues[0].childNodes[17].text;
//	      document.getElementById("txtOldBasicEffTo1").value=XmlHiddenValues[0].childNodes[17].text;
//	      document.getElementById("txtNewBasicEffTo1").value=XmlHiddenValues[0].childNodes[17].text;
	      document.getElementById("txtDOR").value=XmlHiddenValues[0].childNodes[17].text;
	      document.getElementById("txtPensionerName").value=XmlHiddenValues[0].childNodes[18].text;
	      document.getElementById("hdnPensionerName").value=XmlHiddenValues[0].childNodes[18].text;
	      document.getElementById("hidBankName").value=XmlHiddenValues[0].childNodes[19].text;
	      document.getElementById("hidBranchName").value=XmlHiddenValues[0].childNodes[20].text;
	      document.getElementById("hidAccountNo").value=XmlHiddenValues[0].childNodes[21].text;
          var rowCount = document.getElementById("hidReEmpDtlsGridSize").value;
          rowCount = rowCount - 1;
	      var k=0;
	    
	      for(var j=0;j<XmlHiddenValues1.length;j++)
	      {
		      document.getElementById("txtOldReEmpEffFrom"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].text;
		      k++;
		      document.getElementById("txtOldReEmpEffTo"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].text;
		      k++;
		      document.getElementById("cmbDAInPnsnSal"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].text;
		      k++;
		      rowCount++;
		      if(j!=XmlHiddenValues1.length-1)
		      {
	    	 	 addRowReEmp();
		      }
	    	 
	      }
	     
      }
      if(XmlHiddenValues[0].childNodes[0].text == 'Y')
      {
			alert("Please Enter Correct PPO Number or Bank Detail.");
			document.getElementById("txtPPONO").value="";
			document.getElementById("txtDOB").value="";
			document.getElementById("txtDOR").value="";
			document.getElementById("txtPensionerName").value="";
			document.getElementById("txtOldBasic1").value="";
		    document.getElementById("txtOldBasicEffFrom1").value="";
		
      }
      
   }
}*/

function calculateArrear(exportTo)
{
	var selectType = document.getElementsByTagName('select');
	for(var i = 0; i < selectType.length; i++)
	{
		if(selectType[i].name == 'cmbRevisionType' && selectType[i].value == '-1')
		{
			alert("Please select Revision Type.");
			selectType[i].focus();
			return false;
		}
//		else if(selectType[i].name == 'cmbOldDP' && selectType[i].value == '-1')
//		{
//			alert("Please select Old DP.");
//			selectType[i].focus();
//			return false;
//		}
//		else if(selectType[i].name == 'cmbNewDP' && selectType[i].value == '-1')
//		{
//			alert("Please select New DP.");
//			selectType[i].focus();
//			return false;
//		}
	}

	var inputType = document.getElementsByTagName('input');
	for(i = 0; i < inputType.length; i++)
	{
		if(inputType[i].name == 'txtPPONO' && inputType[i].value.trim() == '')
		{
			alert("Please Enter PPO Number or Bank Detail.");
			inputType[i].focus();
			return false;
		}
		if(inputType[i].name == 'txtOldBasic' && inputType[i].value.trim() == '')
		{
			alert("Please Enter Old Basic.");
			inputType[i].focus();
			return false;
		}
		else if(inputType[i].name == 'txtOldBasicEffFrom' && inputType[i].value.trim() == '')
		{
			alert("Please Enter Effective From Date.");
			inputType[i].focus();
			return false;
		}
		else if(inputType[i].name == 'txtOldBasicEffTo' && inputType[i].value.trim() == '')
		{
			alert("Please Enter Effective To Date.");
			inputType[i].focus();
			return false;
		}
		else if(inputType[i].name == 'txtNewBasic' && inputType[i].value.trim() == '')
		{
			alert("Please Enter New Basic.");
			inputType[i].focus();
			return false;
		}
	}
//	    var rowCount=document.getElementById("tblDPDtls").rows.length;
//	    var oldDp=document.getElementsByName('cmbOldDP');
//		var newDp=document.getElementsByName('cmbNewDP');
//		var dpEffFromDt=document.getElementsByName('txtOldDPEffFrom');
//		var dpEffToDt=document.getElementsByName('txtOldDPEffTo');
//	  
//	    if(rowCount>3)
//	    {
//	    	for(var k=0;k<oldDp.length;k++)
//			{
//				var flag =0;
//				if(oldDp[k].value=='Yes' || newDp[k].value=='Yes')
//				{
//					flag =1;
//				}
//				if(flag==0)
//				{
//						alert("Please select either Old DP or New DP as Yes.");
//						oldDp[k].focus();
//						return false;
//				}
//				
//				if(flag==1)
//				{
//					if(dpEffFromDt[k].value=='')
//					{
//						alert("Please Enter DP From Date.");
//						dpEffFromDt[k].focus();
//						return false;
//					}
//					if(dpEffToDt[k].value=='')
//					{
//						alert("Please Enter DP To Date.");
//						dpEffToDt[k].focus();
//						return false;
//					}
//				}
//			}
//	    }
//	    else
//	    {
//		
//			for(k=0;k<oldDp.length;k++)
//			{
//				flag =0;
//				if(oldDp[k].value=='Yes' || newDp[k].value=='Yes')
//				{
//					flag =1;
//				}
//				if(k>0)
//				{
//					if(flag==0)
//					{
//						alert("Please select either Old DP or New DP as Yes.");
//						oldDp[k].focus();
//						return false;
//					}
//				}
//				if(flag==1)
//				{
//					if(dpEffFromDt[k].value=='')
//					{
//						alert("Please Enter Effective From Date.");
//						dpEffFromDt[k].focus();
//						return false;
//					}
//					if(dpEffToDt[k].value=='')
//					{
//						alert("Please Enter Effective To Date.");
//						dpEffToDt[k].focus();
//						return false;
//					}
//				}
//			}
//	    }
		
		//Punishment cut validation
		var punishRowCount=document.getElementById("tblPnshmntCut").rows.length;
		var punishAmount=document.getElementsByName("txtPnshmntAmount");
		var punishFromDate=document.getElementsByName("txtPnshmntFromDate");
		var punishToDate=document.getElementsByName("txtPnshmntToDate");
		
		if(punishRowCount >= 2)
		{
			for(var cnt=0;cnt<punishAmount.length;cnt++)
			{
				if(punishFromDate[cnt].value != "" || punishToDate[cnt].value != "")
				{
					if(punishAmount[cnt].value=='' || punishAmount[cnt].value=='0.00')
					{
						alert("Please Enter Punishment Amount.");
						punishAmount[cnt].focus();
						return false;
					}
					if(punishFromDate[cnt].value=='')
					{
						alert("Please Enter Effective From Date.");
						punishFromDate[cnt].focus();
						return false;
					}
					if(punishToDate[cnt].value=='')
					{
						alert("Please Select Effective To Date.");
						punishToDate[cnt].focus();
						return false;
					}
				}
			}
		}
		
		//Re-Employment validation
		var reEmpRowCount=document.getElementById("ReEmp").rows.length;
		var reEmpFromDate=document.getElementsByName("txtOldReEmpEffFrom");
		var reEmpToDate=document.getElementsByName("txtOldReEmpEffTo");
		var daInSalaryPnsn=document.getElementsByName("cmbDAInPnsnSal");
		
		if(reEmpRowCount >= 3)
		{
			for(var cnt=0;cnt<reEmpFromDate.length;cnt++)
			{
				if(reEmpFromDate[cnt].value != "" || reEmpToDate[cnt].value != "")
				{
					if(reEmpFromDate[cnt].value=='')
					{
						alert("Please Enter Effective From Date.");
						reEmpFromDate[cnt].focus();
						return false;
					}
					if(reEmpToDate[cnt].value=='')
					{
						alert("Please Enter Effective To Date.");
						reEmpToDate[cnt].focus();
						return false;
					}
					if(daInSalaryPnsn[cnt].value=='-1')
					{
						alert("Please Select DA in Pension/Salary.");
						daInSalaryPnsn[cnt].focus();
						return false;
					}
				}
			}
		}
		
		//Allowance Validation
	    var allowRowCount=document.getElementById("tblAllowanceDtls").rows.length;
	    
	    var allowanceType=document.getElementsByName('cmbAllowanceType');
		var oldAllowAmt=document.getElementsByName('txtOldAllowanceAmt');
		var newAllowAmt=document.getElementsByName('txtNewAllowanceAmt');
		var allowEffFrom=document.getElementsByName('txtAllowanceEffFrom');
		var allowEffTo=document.getElementsByName('txtAllowanceEffTo');
	    if(allowRowCount >= 3)
	    {
	    	for(var cnt=0;cnt<allowanceType.length;cnt++)
			{
	    		if(allowanceType[cnt].value != '-1')
	    		{
	    			if(oldAllowAmt[cnt].value=='')
					{
						alert("Please Enter Drawn Amount.");
						oldAllowAmt[cnt].focus();
						return false;
					}
	    			if(newAllowAmt[cnt].value=='')
					{
						alert("Please Enter Due Amount.");
						newAllowAmt[cnt].focus();
						return false;
					}
	    			if(allowEffFrom[cnt].value=='')
					{
						alert("Please Enter Effective From Date.");
						allowEffFrom[cnt].focus();
						return false;
					}
	    			if(allowEffTo[cnt].value=='')
					{
						alert("Please Enter Effective To Date.");
						allowEffTo[cnt].focus();
						return false;
					}
	    			if(compareDate(allowEffFrom[cnt].value,allowEffTo[cnt].value) == false)
					{
						alert("Effective To Date should be greater than Effective From Date.");
						allowEffTo[cnt].focus()
						return false;
					}
	    		}
			}
	    }

	     var commencementDt = document.getElementById("hidCommensionDate").value;
		 var basicCntLength=document.getElementById("hidPnsnBasicGridSize").value;
		 var currentDate = document.getElementById("hidCurrentDt").value;	
		 if(document.getElementById('BasicPnsnDtl').rows.length > 1)
		 {
			for(var rowCnt=0;rowCnt<Number(basicCntLength);rowCnt++)
			{		
				try
				{				 
								
					var basicEffFromDate=document.getElementById("txtOldBasicEffFrom"+rowCnt).value;
					var basicEffToDate=document.getElementById("txtOldBasicEffTo"+rowCnt).value;
					if(commencementDt != basicEffFromDate)
					{
						if(compareDate(commencementDt,basicEffFromDate) == false)
						{
							alert("Effective From Date must be greater than Commencement Date.");
							document.getElementById("txtOldBasicEffFrom"+rowCnt).focus();
							return false;
						}
					}
					if(compareDate(basicEffFromDate,basicEffToDate) == false)
					{
						alert("Effective To Date should be greater than Effective From Date.");
						document.getElementById("txtOldBasicEffTo"+rowCnt).focus();
						return false;
					}
					if(compareDate(basicEffFromDate,currentDate) == false)
					{
						alert("Effective From Date should not go beyond the start date for which the bill is being prepared.");
						document.getElementById("txtOldBasicEffFrom"+rowCnt).focus();
						return false;
					}
					if(compareDate(basicEffToDate,currentDate) == false)
					{
						alert("Effective To Date should not go beyond the start date for which the bill is being prepared.");
						document.getElementById("txtOldBasicEffTo"+rowCnt).focus();
						return false;
					}
													 
				}
				catch(ex)
				{
					
				}
			}
		 }
		
//		 var dpCntLength=document.getElementById("hidDPGridSize").value;
//		 if(document.getElementById('tblDPDtls').rows.length > 1)
//		 {
//			for(rowCnt=0;rowCnt<Number(dpCntLength);rowCnt++)
//			{		
//				try
//				{				 
//								
//					var dpEffFromDate=document.getElementById("txtOldDPEffFrom"+rowCnt).value;
//					var dpEffToDate=document.getElementById("txtOldDPEffTo"+rowCnt).value;
//					if(commencementDt.length > 0 && dpEffFromDate.length >0 && dpEffToDate.length >0)
//					{
//						if(commencementDt != dpEffFromDate)
//						{
//							if(compareDate(commencementDt,dpEffFromDate) == false)
//							{
//								alert("Effective From Date must be greater than Commencement Date.");
//								document.getElementById("txtOldDPEffFrom"+rowCnt).focus();
//								return false;
//							}
//						}
//						if(compareDate(dpEffFromDate,dpEffToDate) == false)
//						{
//							alert("Effective To Date should be greater than Effective From Date.");
//							document.getElementById("txtOldDPEffTo"+rowCnt).focus();
//							return false;
//						}
//						if(compareDate(dpEffFromDate,currentDate) == false)
//						{
//							alert("Effective From Date should not go beyond the start date for which the bill is being prepared.");
//							document.getElementById("txtOldDPEffFrom"+rowCnt).focus();
//							return false;
//						}
//						if(compareDate(dpEffToDate,currentDate) == false)
//						{
//							alert("Effective To Date should not go beyond the start date for which the bill is being prepared.");
//							document.getElementById("txtOldDPEffTo"+rowCnt).focus();
//							return false;
//						}
//					}
//													 
//				}
//				catch(ex)
//				{
//					
//				}
//			}
//		 }
		 var cvpCntLength=document.getElementById("hidCVPGridSize").value;
		 if(document.getElementById('tblCVPDtls').rows.length > 1)
		 {
			for(rowCnt=0;rowCnt<Number(cvpCntLength);rowCnt++)
			{		
				try
				{				 
								
					var cvpEffFromDate=document.getElementById("txtOldCVPEffFrom"+rowCnt).value;
					var cvpEffToDate=document.getElementById("txtOldCVPEffTo"+rowCnt).value;
					if(commencementDt.length > 0 && cvpEffFromDate.length >0 && cvpEffToDate.length >0)
					{
						if(commencementDt != cvpEffFromDate)
						{
							if(compareDate(commencementDt,cvpEffFromDate) == false)
							{
								alert("Effective From Date must be greater than Commencement Date.");
								document.getElementById("txtOldCVPEffFrom"+rowCnt).focus();
								return false;
							}
						}
						if(compareDate(cvpEffFromDate,cvpEffToDate) == false)
						{
							alert("Effective To Date should be greater than Effective From Date.");
							document.getElementById("txtOldCVPEffTo"+rowCnt).focus();
							return false;
						}
						if(compareDate(cvpEffFromDate,currentDate) == false)
						{
							alert("Effective From Date should not go beyond the start date for which the bill is being prepared.");
							document.getElementById("txtOldCVPEffFrom"+rowCnt).focus();
							return false;
						}
						if(compareDate(cvpEffToDate,currentDate) == false)
						{
							alert("Effective To Date should not go beyond the start date for which the bill is being prepared.");
							document.getElementById("txtOldCVPEffTo"+rowCnt).focus();
							return false;
						}
					}
													 
				}
				catch(ex)
				{
					
				}
			}
		 }

		    document.forms[0].action="ifms.htm?actionFlag=calculateArrearForRevision&exportTo="+exportTo+"&billFlag="+document.getElementById("hidBillFlag").value+"&rowCnt="+document.getElementById("hidRowCnt").value;
			document.forms[0].setAttribute("target", "formresult");
		    window.open('','formresult','width=1300,height=800,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0');
		    document.forms[0].submit();
	        return true;
	    
}



function checkRevisionType(revisiontype)
{
	return false;
	document.getElementById("cmbDP").value = '-1';
	
	if(document.getElementById("cmbRevisionType").value != '-1')
	{
		document.getElementById("mandtrytype").style.display = "inline";
		document.getElementById("mandtryMA").style.display = "inline";
		document.getElementById("mandtryDP").style.display = "inline";
	}
	else if(document.getElementById("cmbRevisionType").value == '-1')
	{
		document.getElementById("mandtrytype").style.display = "none";
		document.getElementById("mandtryMA").style.display = "none";
		document.getElementById("mandtryDP").style.display = "none";
	}
	if(document.getElementById("cmbRevisionType").value == 'Old To Old' || document.getElementById("cmbRevisionType").value == '-1')
	{
		document.getElementById("cmbDP").disabled = 'true';
	}
	else
	{
		document.getElementById("cmbDP").disabled = '';
	}
}




function saveArrearByAjax()
{
	//if((document.getElementById("cmbMA").value != '-1') && (document.getElementById("txtoldbasic").value != '') && (document.getElementById("txtnewbasic").value != '') && (document.getElementById("txtefffrom").value != '') && (document.getElementById("txteffto").value != ''))
	{
		window.setTimeout("RevisedArrearDetailsAjaxSave()",1000);
	}
	
}

function winCls()
{
	try
 	{
 	 	if(window.opener.document) 
	 	{
	 		window.close();
 	 	}	
 	}
 	catch(err)
 	{
 		//enableAjaxSubmit(true);
 		window.location.href='ifms.htm?actionFlag=getHomePage';
 		//document.RevisedArrrearCalculation.action = 'ifms.htm?actionFlag=getHomePage';
 		//document.RevisedArrrearCalculation.submit();
		//setAjaxSubmit(document.forms[1]);
	}
}

function compareTwoDates(frmStr, toStr)
{
	if(frmStr.length > 0 && toStr.length > 0)
	{
		var frmdaysInMonth=DaysArray(12)
		var frmpos1=frmStr.indexOf(dtCh)
		var frmpos2=frmStr.indexOf(dtCh,frmpos1+1)
		var frmDay=frmStr.substring(0,frmpos1)
		var frmMonth=frmStr.substring(frmpos1+1,frmpos2)
		var frmYear=frmStr.substring(frmpos2+1)
	
		var todaysInMonth=DaysArray(12)
		var topos1=toStr.indexOf(dtCh)
		var topos2=toStr.indexOf(dtCh,topos1+1)
		var toDay=toStr.substring(0,topos1)
		var toMonth=toStr.substring(topos1+1,topos2)
		var toYear=toStr.substring(topos2+1)
	
		if(frmYear > toYear)
		{
			return false;
		} 
		if(frmYear == toYear) 
		{
			if(frmMonth > toMonth)
			{
				return false;
			}
		}
		if(frmYear == toYear) 
		{
			if(frmMonth == toMonth)
			{
				if(frmDay > toDay)
				{
					return false;
				}
			}
		}
	}
	return true;
}

function DaysArray(n)
{
	for (var i=1; i <= n; i++)
	{
		this[i]=31
		if (i==4 || i==6 || i==9 || i==11) {this[i]=30}
		if (i==2) {this[i]=29}
   	} 
   	return this;
}

//function dateFormat(lThis)
//{
//	if(window.event.keyCode == 47)
//	{
//		var val = lThis.value.trim();
//		var lStrArr = val.split('/');
//		if(lStrArr.length > 2)
//		{
//			window.event.keyCode = 0;
//		}
//	}
//	else if(!((window.event.keyCode > 47 && window.event.keyCode < 58 )))
//	{
//		window.event.keyCode = 0;
//	}
//}
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
	var value=new String(field.value);
	if(value.length==2)
	{
		field.value=value+'/'
	}
	if(value.length==5)
	{
		field.value=value+'/'
	}
}

//function addRowPnsnBasic(tabId, i)   	
//{
//	var noOfRows = document.getElementsByName('txtOldBasic').length;	
//	var noOfRows_incremented = noOfRows + 1;		 
//	var newRow = document.all(tabId).insertRow(noOfRows+2);
//	 
//	newRow.setAttribute("align","center");
//	var cell1 = newRow.insertCell(); 
//	var cell2 = newRow.insertCell();
//	var cell3 = newRow.insertCell();
//	var cell4 = newRow.insertCell();
//	var cell5 = newRow.insertCell();
//	var cell6 = newRow.insertCell();
//	
//	cell1.innerHTML = '<select name="cmbRevisionType"  id="cmbRevisionType'+i+noOfRows_incremented+'" style="width:100%" onchange="checkRevisionType(this)"><option value="-1">--Select--</option>'+cmbListRevisedType+'</select><label style="display: none;" class="mandatoryindicator">*</label>';		  
//	cell2.innerHTML = '<input type="text" name="txtOldBasic" id="txtOldBasic'+i+noOfRows_incremented+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>';	
//	cell3.innerHTML = '<input type="text" name="txtNewBasic" id="txtNewBasic'+i+noOfRows_incremented+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>'; 
//	cell4.innerHTML = '<input type="text" name="txtOldBasicEffFrom" id="txtOldBasicEffFrom'+i+noOfRows_incremented+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldBasicEffFrom'+i+noOfRows_incremented+'\', 375, 570, \'\', \'\', '+i+noOfRows_incremented+');" />';	
//	cell5.innerHTML = '<input type="text" name="txtOldBasicEffTo" id="txtOldBasicEffTo'+i+noOfRows_incremented+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldBasicEffTo'+i+noOfRows_incremented+'\', 375, 570, \'\', \'\', '+i+noOfRows_incremented+');" />';
//	cell6.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" id="delete'+i+noOfRows_incremented+'" onclick="deleteRow(this, '+tabId+');"/>';
//	assignTab();
//	document.getElementById('cmbRevisionType'+i+noOfRows_incremented).focus();
//	//Behaviour.apply(myrules);
//}

function addRowPnsnBasicDtls()
{
    rowId = document.getElementById("hidPnsnBasicGridSize").value;
	
	var table=document.getElementById("BasicPnsnDtl");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell(4);
   	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
	
   	
   	Cell1.innerHTML = '<select name="cmbRevisionType"  id="cmbRevisionType'+rowId+'" style="width:100%" onchange="checkRevisionType(this)"><option value="-1">--Select--</option>'+cmbListRevisedType+'</select><label style="display: none;" class="mandatoryindicator">*</label>';		  
	Cell2.innerHTML = '<input type="text" name="txtOldBasic" id="txtOldBasic'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>';	
	Cell3.innerHTML = '<input type="text" name="txtNewBasic" id="txtNewBasic'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>'; 
	Cell4.innerHTML = '<input type="text" name="txtOldBasicEffFrom" id="txtOldBasicEffFrom'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldBasicEffFrom'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';	
	Cell5.innerHTML = '<input type="text" name="txtOldBasicEffTo" id="txtOldBasicEffTo'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldBasicEffTo'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';
	Cell6.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'BasicPnsnDtl\')" /> ';
	  
	document.getElementById("hidPnsnBasicGridSize").value = Number(rowId)+1;  
}

function punishmentCutAddRow()
{
	Row_ID_Pnshmt = document.getElementById("hidPnshmntCutSize").value;	
	var table=document.getElementById("tblPnshmntCut");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount);
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";   	
	  	
   	
	Cell1.innerHTML = '<input type="hidden" name="hdnPensionCutDtlsId" id="hdnPensionCutDtlsId'+Row_ID_Pnshmt+'"/><input type="text" name="txtPnshmntAmount" id="txtPnshmntAmount'+Row_ID_Pnshmt+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell2.innerHTML = '<input type="text" name="txtPnshmntFromDate" id="txtPnshmntFromDate'+Row_ID_Pnshmt+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtPnshmntFromDate'+Row_ID_Pnshmt+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Pnshmt)+');" />';
   	Cell3.innerHTML = '<input type="text" name="txtPnshmntToDate" id="txtPnshmntToDate'+Row_ID_Pnshmt+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtPnshmntToDate'+Row_ID_Pnshmt+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Pnshmt)+');" />';
   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblPnshmntCut\')" /> ';   	
   	document.getElementById("hidPnshmntCutSize").value = Number(Row_ID_Pnshmt)+1; 
}

function addRowMA(tabId, i)   	
{
	var noOfRows = document.getElementsByName('cmbOldMA').length;	
	var noOfRows_incremented = noOfRows + 1;		 
	var newRow = document.all(tabId).insertRow(noOfRows+2);
	 
	newRow.setAttribute("align","center");
	var cell1 = newRow.insertCell(); 
	var cell2 = newRow.insertCell();
	var cell3 = newRow.insertCell();
	var cell4 = newRow.insertCell();
	var cell5 = newRow.insertCell();
	var cell6 = newRow.insertCell(); 
	
	cell1.innerHTML = '<select name="cmbOldMA"  id="cmbOldMA'+i+noOfRows_incremented+'" style="width:90%" onchange="checkRevisionType(this)">'+cmbListYesNo+'</select><label style="display: none;" class="mandatoryindicator">*</label>';		  
	cell2.innerHTML = '<input type="text" name="txtOldMAEffFrom" id="txtOldMAEffFrom'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);" onchange="populateNewFields(this.id)"   />';	
	cell3.innerHTML = '<input type="text" name="txtOldMAEffTo" id="txtOldMAEffTo'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"  onchange="populateNewFields(this.id)"    />';
	cell4.innerHTML = '<select name="cmbNewMA"  id="cmbNewMA'+i+noOfRows_incremented+'" style="width:90%" onchange="checkRevisionType(this)">'+cmbListYesNo+'</select><label style="display: none;" class="mandatoryindicator">*</label>';		  
	cell5.innerHTML = '<input type="text" name="txtNewMAEffFrom" id="txtNewMAEffFrom'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"   />';	
	cell6.innerHTML = '<input type="text" name="txtNewMAEffTo" id="txtNewMAEffTo'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"   />&nbsp;&nbsp;<img src="images/CalendarImages/DeleteIcon.gif" id="deleteMA'+i+noOfRows_incremented+'" onclick="deleteRow(this, '+tabId+');"/>';
	assignTab();
	document.getElementById('cmbOldMA'+i+noOfRows_incremented).focus();
	//Behaviour.apply(myrules);
}

//function addRowDP(tabId, i)   	
//{
//	var noOfRows = document.getElementsByName('cmbOldDP').length;	
//	var noOfRows_incremented = noOfRows + 1;		 
//	var newRow = document.all(tabId).insertRow(noOfRows+2);
//	 
//	newRow.setAttribute("align","center");
//	var cell1 = newRow.insertCell(); 
//	var cell2 = newRow.insertCell();
//	var cell3 = newRow.insertCell();
//	var cell4 = newRow.insertCell();
//	var cell5 = newRow.insertCell();
//	 
//	
//	cell1.innerHTML = '<select name="cmbOldDP"  id="cmbOldDP'+i+noOfRows_incremented+'" style="width:90%" onchange="checkRevisionType(this)"><option value="-1">--Select--</option>'+cmbListYesNo+'</select><label style="display: none;" class="mandatoryindicator">*</label>';		  
//	cell2.innerHTML = '<select name="cmbNewDP"  id="cmbNewDP'+i+noOfRows_incremented+'" style="width:90%" onchange="checkRevisionType(this)"><option value="-1">--Select--</option>'+cmbListYesNo+'</select><label style="display: none;" class="mandatoryindicator">*</label>'; 
//	cell3.innerHTML = '<input type="text" name="txtOldDPEffFrom" id="txtOldDPEffFrom'+i+noOfRows_incremented+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13" onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldDPEffFrom'+i+noOfRows_incremented+'\', 375, 570, \'\', \'\', '+i+noOfRows_incremented+');" />';
//	cell4.innerHTML = '<input type="text" name="txtOldDPEffTo" id="txtOldDPEffTo'+i+noOfRows_incremented+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13" onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldDPEffTo'+i+noOfRows_incremented+'\', 375, 570, \'\', \'\', '+i+noOfRows_incremented+');" />'
//	cell5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" id="deleteDP'+i+noOfRows_incremented+'" onclick="deleteRow(this, '+tabId+');"/>';
//	assignTab();
//	document.getElementById('cmbOldDP'+i+noOfRows_incremented).focus();
//	//Behaviour.apply(myrules);
//}

function addRowDPDtls()
{
	    rowId = document.getElementById("hidDPGridSize").value;
		
		var table=document.getElementById("tblDPDtls");
		var rowCount=table.rows.length;
		var newRow = table.insertRow(rowCount); 
		
	   	var Cell1 = newRow.insertCell(0);
	   	Cell1.className = "tds";
	   	Cell1.align="center";
	   	var Cell2 = newRow.insertCell(1);
	   	Cell2.className = "tds";
	   	Cell2.align="center";
	   	var Cell3 = newRow.insertCell(2);
	   	Cell3.className = "tds";
	   	Cell3.align="center";
	   	var Cell4 = newRow.insertCell(3);
		Cell4.className = "tds";
	   	Cell4.align="center";
	   	var Cell5 = newRow.insertCell(4);
	   	Cell5.className = "tds";
	   	Cell5.align="center";
		
	   	Cell1.innerHTML = '<select name="cmbOldDP"  id="cmbOldDP'+rowId+'" style="width:90%" onchange="checkRevisionType(this)"><option value="-1">--Select--</option>'+cmbListYesNo+'</select><label style="display: none;" class="mandatoryindicator">*</label>';		  
	   	Cell2.innerHTML = '<select name="cmbNewDP"  id="cmbNewDP'+rowId+'" style="width:90%" onchange="checkRevisionType(this)"><option value="-1">--Select--</option>'+cmbListYesNo+'</select><label style="display: none;" class="mandatoryindicator">*</label>'; 
	   	Cell3.innerHTML = '<input type="text" name="txtOldDPEffFrom" id="txtOldDPEffFrom'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13" onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldDPEffFrom'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';
	   	Cell4.innerHTML = '<input type="text" name="txtOldDPEffTo" id="txtOldDPEffTo'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13" onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldDPEffTo'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';
	   	Cell5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblDPDtls\')" /> ';
	   	
	 	document.getElementById("hidDPGridSize").value = Number(rowId)+1;  
}

//function addRowCVP(tabId, i)   	
//{
//	var noOfRows = document.getElementsByName('txtOldCVP').length;	
//	var noOfRows_incremented = noOfRows + 1;		 
//	var newRow = document.all(tabId).insertRow(noOfRows+2);
//	 
//	newRow.setAttribute("align","center");
//	var cell1 = newRow.insertCell(); 
//	var cell2 = newRow.insertCell();
//	var cell3 = newRow.insertCell();
//	var cell4 = newRow.insertCell();
//	var cell5 = newRow.insertCell();
//
//	cell1.innerHTML = '<input type="text" name="txtOldCVP"  id="txtOldCVP'+i+noOfRows_incremented+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/>';		  
//	cell2.innerHTML = '<input type="text" name="txtNewCVP"  id="txtNewCVP'+i+noOfRows_incremented+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/>'; 
//	cell3.innerHTML = '<input type="text" name="txtOldCVPEffFrom" id="txtOldCVPEffFrom'+i+noOfRows_incremented+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldCVPEffFrom'+i+noOfRows_incremented+'\', 375, 570, \'\', \'\', '+i+noOfRows_incremented+');" />';
//	cell4.innerHTML = '<input type="text" name="txtOldCVPEffTo" id="txtOldCVPEffTo'+i+noOfRows_incremented+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldCVPEffTo'+i+noOfRows_incremented+'\', 375, 570, \'\', \'\', '+i+noOfRows_incremented+');" />'
//	cell5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" id="deleteCVP'+i+noOfRows_incremented+'" onclick="deleteRow(this, '+tabId+');"/>';
//	assignTab();
//	document.getElementById('txtOldCVP'+i+noOfRows_incremented).focus();
//	//Behaviour.apply(myrules);
//}

function addRowCVPDtls()
{
	rowId = document.getElementById("hidCVPGridSize").value;
	
	var table=document.getElementById("tblCVPDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell(4);
   	Cell5.className = "tds";
   	Cell5.align="center";
   	
   	Cell1.innerHTML = '<input type="text" name="txtOldCVP"  id="txtOldCVP'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/>';		  
	Cell2.innerHTML = '<input type="text" name="txtNewCVP"  id="txtNewCVP'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/>'; 
	Cell3.innerHTML = '<input type="text" name="txtOldCVPEffFrom" id="txtOldCVPEffFrom'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldCVPEffFrom'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';
	Cell4.innerHTML = '<input type="text" name="txtOldCVPEffTo" id="txtOldCVPEffTo'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldCVPEffTo'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />'
	Cell5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblCVPDtls\')" /> ';
	document.getElementById("hidCVPGridSize").value = Number(rowId)+1;  	
}

function addRowPnsnCut(tabId, i)   	
{
	var noOfRows = document.getElementsByName('txtOldPnsnCut').length;	
	var noOfRows_incremented = noOfRows + 1;		 
	var newRow = document.all(tabId).insertRow(noOfRows+2);
	 alert(noOfRows_incremented);
	newRow.setAttribute("align","center");
	var cell1 = newRow.insertCell(); 
	var cell2 = newRow.insertCell();
	var cell3 = newRow.insertCell();
	var cell4 = newRow.insertCell();
	var cell5 = newRow.insertCell();
	var cell6 = newRow.insertCell(); 

	cell1.innerHTML = '<input type="text" name="txtOldPnsnCut"  id="txtOldPnsnCut'+i+noOfRows_incremented+'" size="16" /><label style="display: none;" class="mandatoryindicator">*</label>';		  
	cell2.innerHTML = '<input type="text" name="txtOldPnsnCutEffFrom" id="txtOldPnsnCutEffFrom'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);" onchange="populateNewFields(this.id)"   />';	
	cell3.innerHTML = '<input type="text" name="txtOldPnsnCutEffTo" id="txtOldPnsnCutEffTo'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"  onchange="populateNewFields(this.id)"    />';
	cell4.innerHTML = '<input type="text" name="txtNewPnsnCut"  id="txtNewPnsnCut'+i+noOfRows_incremented+'" size="16" /><label style="display: none;" class="mandatoryindicator">*</label>';		  
	cell5.innerHTML = '<input type="text" name="txtNewPnsnCutEffFrom" id="txtNewPnsnCutEffFrom'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"   />';	
	cell6.innerHTML = '<input type="text" name="txtNewPnsnCutEffTo" id="txtNewPnsnCutEffTo'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"     />&nbsp;&nbsp;<img src="images/CalendarImages/DeleteIcon.gif" id="deletePnsnCut'+i+noOfRows_incremented+'" onclick="deleteRow(this, '+tabId+');"/>';
	assignTab();
	document.getElementById('txtOldPnsnCut'+i+noOfRows_incremented).focus();
	Behaviour.apply(myrules);
}

function addRowReEmp()
{	
	Row_ID_ReEmp = document.getElementById("hidReEmpDtlsGridSize").value;
	
	var table=document.getElementById("ReEmp");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	//var newRow =  document.all("tblCvpRestnDtls").insertRow();	  			  		
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
	
   	Cell1.innerHTML = '<input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom'+Row_ID_ReEmp+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldReEmpEffFrom'+Row_ID_ReEmp+'\', 375, 570, \'\', \'\', '+Row_ID_ReEmp+');" />';	
	Cell2.innerHTML = '<input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo'+Row_ID_ReEmp+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldReEmpEffTo'+Row_ID_ReEmp+'\', 375, 570, \'\', \'\', '+Row_ID_ReEmp+');" />';
	
      	   
//  Cell1.innerHTML = '<input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom'+Row_ID_ReEmp+'" size="13" onKeyup="dateFormat(this);" />';	
//	Cell2.innerHTML = '<input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo'+Row_ID_ReEmp+'" size="13" onKeyup="dateFormat(this);"   />';
	Cell3.innerHTML = '<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal'+Row_ID_ReEmp+'" ><option value="-1">--Select--</option>'+LISTDAIN+'</select>';
	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'ReEmp\')" /> ';
	document.getElementById("hidReEmpDtlsGridSize").value = Number(Row_ID_ReEmp)+1;  
}

function addRowAllowanceDtls()
{
    Row_Id_Allow = document.getElementById("hidOtherAllwGridSize").value;
	
	var table=document.getElementById("tblAllowanceDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	//var newRow =  document.all("tblCvpRestnDtls").insertRow();	  			  		
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell(4);
	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
	
      	   
   	Cell1.innerHTML = '<select name="cmbAllowanceType"  id="cmbAllowanceType'+Row_Id_Allow+'" style="width:90%"><option value="-1">--Select--</option>'+LISTALLOWANCE+'</select><label class="mandatoryindicator">*</label>';	
   	Cell2.innerHTML = '<input type="text" name="txtOldAllowanceAmt" id="txtOldAllowanceAmt'+Row_Id_Allow+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>';	
	Cell3.innerHTML = '<input type="text" name="txtNewAllowanceAmt" id="txtNewAllowanceAmt'+Row_Id_Allow+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>'; 
	Cell4.innerHTML = '<input type="text" name="txtAllowanceEffFrom" id="txtAllowanceEffFrom'+Row_Id_Allow+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtAllowanceEffFrom'+Row_Id_Allow+'\', 375, 570, \'\', \'\', '+Row_Id_Allow+');" />';	
	Cell5.innerHTML = '<input type="text" name="txtAllowanceEffTo" id="txtAllowanceEffTo'+Row_Id_Allow+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtAllowanceEffTo'+Row_Id_Allow+'\', 375, 570, \'\', \'\', '+Row_Id_Allow+');" />';
	Cell6.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblAllowanceDtls\')" /> ';
	document.getElementById("hidOtherAllwGridSize").value = Number(Row_Id_Allow)+1;  
}
//function addRowReEmp(tabId, i)   	
//{
//	var noOfRows = document.getElementsByName('txtOldReEmpEffFrom').length;	
//	var noOfRows_incremented = noOfRows + 1;		 
//	var newRow = document.all(tabId).insertRow(-1);
//	 alert("i="+i);
//	 alert("noOfRows_incremented="+noOfRows_incremented);
//	newRow.setAttribute("align","center");
//	var cell1 = newRow.insertCell();
//	var cell2 = newRow.insertCell();
//	var cell3 = newRow.insertCell();
//	var cell4 = newRow.insertCell(); 
//
//	cell1.innerHTML = '<input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"  onchange="populateNewFields(this.id)"  />';	
//	cell2.innerHTML = '<input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"   onchange="populateNewFields(this.id)"  />';
//	cell3.innerHTML = '<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal'+i+noOfRows_incremented+'" ><option value="-1">--Select--</option>'+LISTDAIN+'</select>';
//	//cell3.innerHTML = '<input type="text" name="txtNewReEmpEffFrom" id="txtNewReEmpEffFrom'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"   />';	
//	//cell4.innerHTML = '<input type="text" name="txtNewReEmpEffTo" id="txtNewReEmpEffTo'+i+noOfRows_incremented+'" size="13" onKeyup="dateFormat(this);"    />&nbsp;&nbsp;
//	cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" id="deleteReEmp'+i+noOfRows_incremented+'" onclick="deleteRow(this, '+tabId+');"/>';
//	assignTab();
//	document.getElementById('txtOldReEmpEffFrom'+i+noOfRows_incremented).focus();
//	Behaviour.apply(myrules);
//}

function deleteRow(row, table)
{
	var i = row.parentNode.parentNode.rowIndex;
	table.deleteRow(i);
	assignTab();
}

function assignTab()
{
	var tab = 1;

	for(var i=0;i<document.forms[0].elements.length;i++)
	{
		if(document.forms[0].elements[i].type != 'hidden' && 
		   document.forms[0].elements[i].type != 'checkbox' && 
		   document.forms[0].elements[i].type != 'textarea' &&
		   document.forms[0].elements[i].getAttribute('readonly')!=true && 
		   document.forms[0].elements[i].getAttribute('readonly')!='readonly' &&
		   document.forms[0].elements[i].tagName != "FIELDSET")
		{
			document.forms[0].elements[i].tabIndex = tab;
			tab++;
		}
	}		
}


function populateNewFields(id)
{
	var id1 = new String(id).replace('Old', 'New');
	document.getElementById(id1).value = document.getElementById(id).value;
}
