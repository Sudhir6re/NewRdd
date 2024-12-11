
function calActualServiceDays()
{
	if(document.getElementById("txtDateOfRetiremt").value != "" && document.getElementById("txtDateOfStartingService").value != "" )
	{
		var brkFrom = document.getElementById("txtDateOfStartingService").value; // Date of starting service
		var brkTo = document.getElementById("txtDateOfRetiremt").value; // Date of Retirement
		var dayDiff = 0;
		var brkFromDateArr = brkFrom.split("/");
		var brkFromDateDay = brkFromDateArr[0];
		var brkFromDateMonth = brkFromDateArr[1];
		var brkFromDateYear = brkFromDateArr[2];
		
		var statLeapfrmYr = "false";
		if(Math.floor(Number(brkFromDateYear%4 == 0 ))){
			statLeapfrmYr = "true"
		} else if(Math.floor(Number(brkFromDateYear%4 != 0 ))){
			statLeapfrmYr = "false"
		}
		
		var statLeaptoYr = "false";
		if(Math.floor(Number(brkFromDateYear%4 == 0 ))){
			statLeaptoYr = "true"
		} else if(Math.floor(Number(brkFromDateYear%4 != 0 ))){
			statLeaptoYr = "false"
		}
		
		var brkToDateArr = brkTo.split("/");
		var brkToDate = new Date(brkToDateArr[1] +"/"+brkToDateArr[0]+"/"+ brkToDateArr[2]);
		
		
		
		if(brkToDateArr[1] == 2 && brkToDateArr[0] == 28){
			brkToDate.setDate(brkToDate.getDate());
			}
			else if(brkToDateArr[1] == 2 && brkToDateArr[0] == 29 && (brkToDateArr[2]%4==0) ){
				brkToDate.setDate(brkToDate.getDate());
		
		var brkToDateDay = brkToDate.getDate();
		var brkToDateMonth = brkToDate.getMonth()+1;
		var brkToDateYear = brkToDate.getFullYear();
		if(Number(brkFromDateYear) > Number(brkToDateYear))
		{
			alert('Date of Retirement should be greater than Date of Starting Service.');
			document.getElementById("txtDateOfRetiremt").value ="";
			document.getElementById("txtDateOfRetiremt").focus();
		}
		else
		{
			if(Number(brkFromDateYear) == Number(brkToDateYear)) 
			{
				if(Number(brkFromDateMonth) > Number(brkToDateMonth))
				{
					alert('Date of Retirement should be greater than Date of Starting Service.');
					document.getElementById("txtDateOfRetiremt").value ="";
					document.getElementById("txtDateOfRetiremt").focus();
				}
				else
				{
					if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
					{
						if(Number(brkFromDateDay) > Number(brkToDateDay))
						{
							alert('Date of Retirement should be greater than Date of Starting Service.');
							document.getElementById("txtDateOfRetiremt").value ="";
							document.getElementById("txtDateOfRetiremt").focus();
						}
						else
						{
							if(Number(brkFromDateDay) ==  Number(brkToDateDay))
							{
								dayDiff = 0;
								monthDiff = 0;
								yearDiff = 0;
							}
							else
							{
								dayDiff = Number(brkToDateDay) - Number(brkFromDateDay);
								monthDiff = 0;
								yearDiff = 0;
							}
						}
					}
					else 
					{
						monthDiff = Number(brkToDateMonth) - Number(brkFromDateMonth);
						yearDiff = 0;
						if(Number(brkToDateDay) == Number(brkFromDateDay))
						{
							dayDiff = 30 * monthDiff;
							alert('brkFromDateMonth is less than brkToDateMonth')
						}
						else
						{
							dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay));
							
						}
					}
				}
			}
			else 
			{
				yearDiff = Number(brkToDateYear) - Number(brkFromDateYear);
				if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
				{
					monthDiff = 12 * yearDiff;
					if(Number(brkFromDateDay) ==  Number(brkToDateDay))
					{
						dayDiff =  (yearDiff * 360) + 1;
					}
					else
					{
						{
							dayDiff = (Number(brkToDateDay)- Number(brkFromDateDay) + 1) + (360 * (yearDiff));
						}
						{
							dayDiff = (360 * (yearDiff)) - (Number(brkFromDateDay)-Number(brkToDateDay)-1);
						}
					}
				}
				{
					
					if(Number(brkToDateMonth) > Number(brkFromDateMonth))
					{
						
						monthDiff = (12 * yearDiff) +  (Number(brkToDateMonth) - Number(brkFromDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)) + 1);//commented by Kinjal
						}
						else
						{
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								if(brkToDateMonth==2 && statLeaptoYr == "true" && brkToDateDay == 29){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
								}else if(brkToDateMonth==2 && statLeaptoYr){
								
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
								}else if(brkToDateMonth==2 && brkToDateDay == 28){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
								}else if(brkToDateMonth==2){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)-1) ;
								
								}else{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay) + 1) ;//commented by Kinjal
								}
							}
							else {
								if(brkToDateMonth==2 &&  brkToDateDay == 29){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkFromDateDay) - Number(brkToDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
									//alert('dayDiffdayDiff test 201:'+dayDiff);
									
								}else if(brkToDateMonth==2){
								
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkFromDateDay) - Number(brkToDateDay)); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
									//alert('dayDiffdayDiff test 202:'+dayDiff);
													
								}else if(brkToDateMonth==2 && brkToDateDay == 28){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  Number(brkFromDateDay) -(Number(brkToDateDay)+1) ;
						
								//dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
															
								}else if(brkToDateMonth==2){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkFromDateDay) -(Number(brkToDateDay))-1) ;
								
								}else if(brkFromDateMonth ==2 && statLeapfrmYr == "true" && brkFromDateDay == 29) {
									dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay) ) ;//commented by Kinjal
								
							}else if(brkFromDateMonth ==2 && statLeapfrmYr == "true" ) {
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)  ) ;//commented by Kinjal
							}else if(brkFromDateMonth ==2 && statLeapfrmYr == "false") {
									dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay) +1) ;//commented by Kinjal
								}else{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay) + 1) ;//commented by Kinjal
								}
								}
						}		
						}
						
					
					{
						monthDiff = (12 * yearDiff) -  (Number(brkFromDateMonth) - Number(brkToDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)));//commented by Kinjal
						}
						{	
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								if(brkToDateMonth==2 && statLeaptoYr == "true" && brkToDateDay == 29){
								
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
								
								
								}else if(brkToDateMonth==2 && statLeaptoYr == "true"){
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth) + 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
								}else if(brkToDateMonth==2 && brkToDateDay == 28){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
								}else if(brkToDateMonth==2 && brkToDateDay != 28){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)-1) ;
								}
								else{
									dayDiff = ((yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkToDateDay) -  Number(brkFromDateDay) + 1) ;//commented by Kinjal
								}
							}
							else	
							{
								
								if(Number(brkToDateMonth) == 2 && statLeaptoYr == "true" && brkToDateDay == 29){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
									
								}else if(brkToDateMonth==2 && statLeaptoYr == "true"){
								
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkFromDateDay) - Number(brkToDateDay) - 2); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
								}else if(brkToDateMonth==2 && brkToDateDay == 28){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
								}else if(brkToDateMonth==2){
									
									dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)-1) ;
								
								}else{
								
									dayDiff = (( yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay) + 1) ;
								}
								}
						}
					}
				}
			}
		}
		document.getElementById("txtActualSer").value = dayDiff;
		document.getElementById("txtQualifyingServ").value = dayDiff;
		
		var TotalYear = Math.floor(Number(dayDiff/360));
		var TotalMonth = Math.floor(Number((dayDiff%360)/30));
		
		if(brkToDateMonth==2 && statLeaptoYr == "true"){
		var TotalDays = (dayDiff%360)%30;
		}
		else if(brkToDateMonth==2){
			
			var TotalDays = (dayDiff%360)%30;
			}
		else
		{
			 TotalDays = (dayDiff%360)%30;
		}
		document.getElementById("hidTotalyear").value=TotalYear;
		document.getElementById("hidTotalMonth").value=TotalMonth;
		document.getElementById("hidTotalDays").value=TotalDays;
		
		document.getElementById("txtActualSerYear").value=TotalYear;
		document.getElementById("txtActualSerMonth").value=TotalMonth;
		document.getElementById("txtActualSerDay").value=TotalDays;
		
		if(document.getElementById("txtQualiServYear").value=="0" && document.getElementById("txtQualiServMonth").value=="0" && document.getElementById("txtQualiServDay").value=="0"){
			document.getElementById("txtQualifyingServYear").value=TotalYear;
		document.getElementById("txtQualifyingServMonth").value=TotalMonth;
		document.getElementById("txtQualifyingServDay").value=TotalDays;
		}

		else if(document.getElementById("txtQualiServYear").value=="" && document.getElementById("txtQualiServMonth").value=="" && document.getElementById("txtQualiServDay").value==""){
			document.getElementById("txtQualifyingServYear").value=TotalYear;
		document.getElementById("txtQualifyingServMonth").value=TotalMonth;
		document.getElementById("txtQualifyingServDay").value=TotalDays;
		}
	}
	else
	{
		if(document.getElementById("txtDateOfRetiremt").value == "" )
		{
			return;
		}
		if(document.getElementById("txtDateOfStartingService").value == "")
		{
			return;
		}
	}
}
	
	

	function calTotalSrvcBrk(count)
	{
		try{
		var brkFrom = document.getElementById("txtDateOfBrkFrom"+count).value;
		var brkTo = document.getElementById("txtDateOfBrkTo"+count).value;
		var dayDiff = 0;
		if(brkFrom == "" || brkTo == "")
		{
		return 0;
		}
		var brkFromDateArr = brkFrom.split("/");
		var brkFromDateDay = brkFromDateArr[0];
		var brkFromDateMonth = brkFromDateArr[1];
		
		
		var brkFromDateYear = brkFromDateArr[2];
		
		var brkToDateArr = brkTo.split("/");
		var brkToDate = new Date(brkToDateArr[1] +"/"+brkToDateArr[0]+"/"+ brkToDateArr[2]);
		brkToDate.setDate(brkToDate.getDate()+1);
		
		
		
		var brkToDateDay = brkToDateArr[0];
		var brkToDateMonth = brkToDateArr[1];
		var brkToDateYear = brkToDateArr[2];
		if(Number(brkFromDateYear) > Number(brkToDateYear))
		{
			alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
			document.getElementById("txtDateOfBrkTo"+count).value ="";
			document.getElementById("txtDateOfBrkTo"+count).focus();
		}
		else
			{
			if(Number(brkFromDateYear) == Number(brkToDateYear)) 
				{
				if(Number(brkFromDateMonth) > Number(brkToDateMonth))
				{
				alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
				document.getElementById("txtDateOfBrkTo"+count).value ="";
				document.getElementById("txtDateOfBrkTo"+count).focus();
				}
				else if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
				{
				if(Number(brkFromDateDay) > Number(brkToDateDay))
				{
				alert('Non qualifying service to date should be greater than or equal to Non qualifying service from date.');
				document.getElementById("txtDateOfBrkTo"+count).value ="";
				document.getElementById("txtDateOfBrkTo"+count).focus();
				}
				else if(Number(brkFromDateDay) ==  Number(brkToDateDay))
				{
					dayDiff = 1;
					monthDiff = 0;
					yearDiff = 0;
				}
			    else
				{
				dayDiff = Number(brkToDateDay) - Number(brkFromDateDay) + 1;
				monthDiff = 0;
				yearDiff = 0;
				}
				
				}
				{
				monthDiff = Number(brkToDateMonth) - Number(brkFromDateMonth);
				yearDiff = 0;
				if(Number(brkToDateDay) == Number(brkFromDateDay))
				{
				dayDiff = 30 * monthDiff;
				}
				else if(brkToDateMonth ==2 && brkToDateDay ==28)
				{ 
				dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay)+3);
				}
				else
				{ 
				dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay)+1);
				}
				}
			}
			{
			yearDiff = Number(brkToDateYear) - Number(brkFromDateYear);
			if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
			{
			monthDiff = 12 * yearDiff;
			if(Number(brkFromDateDay) ==  Number(brkToDateDay))
			{
			dayDiff =  (yearDiff * 360);
			}
			else
			{
			if(Number(brkToDateDay) > Number(brkFromDateDay))
			{
			dayDiff = (Number(brkToDateDay)-Number(brkFromDateDay)) + (360 * (yearDiff))   ;
			}
			else
			{
			
			dayDiff = (360 * (yearDiff)) - (Number(brkFromDateDay)-Number(brkToDateDay));
			}
			}
			}
			else 
			{ 
			if(Number(brkToDateMonth) > Number(brkFromDateMonth))
			{
			monthDiff = (12 * yearDiff) +  (Number(brkToDateMonth) - Number(brkFromDateMonth));
			if(Number(brkFromDateDay) ==  Number(brkToDateDay))
			{
			dayDiff =  (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)));
			}
			else
			{
			if(Number(brkToDateDay) > Number(brkFromDateDay))
			{
			dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
			}
			else
			{
			dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
			}
			}
			
			}
			{
			if(Number(brkFromDateDay) ==  Number(brkToDateDay))
			{
			dayDiff =  (yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)) - 1);
			}
			else
			{	
			if(Number(brkToDateDay) > Number(brkFromDateDay))
			{
			if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 ))) && brkToDateDay == 29){
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
			}else if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 )))){
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth) + 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
			}//TODO
			else if(brkFromDateMonth==2 && brkToDateMonth==2 && brkToDateDay == 28){
				dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+2) ;
				}
			else if(brkToDateMonth==2 && brkToDateDay == 28){
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
			}else if(brkToDateMonth==2 && brkToDateDay != 28){
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth) )))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)-1) ; //TODO
			}
			else{
			dayDiff = ((yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkToDateDay) -  Number(brkFromDateDay) ) ;
			}
			}
			
			if(Number(brkToDateMonth) == 2 && (Math.floor(Number(brkToDateYear%4 == 0 ))) && brkToDateDay == 29){
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkToDateDay) - Number(brkFromDateDay)+1); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
			}else if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 )))){
			
			dayDiff = ((yearDiff * 360) + (30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 29) + (Number(brkFromDateDay) - Number(brkToDateDay) - 2); //(Number(brkToDateDay) - Number(brkFromDateDay)) ;
			}else if( brkToDateMonth == 2 && brkFromDateMonth==2 && brkToDateDay == 28){
			dayDiff = ((yearDiff * 360) + (Number(brkToDateDay) - Number(brkFromDateDay)+1)) ; //(30 * ((Number(brkToDateMonth)+ 1 - Number(brkFromDateMonth))))- 28)  +  (Number(brkToDateDay) - Number(brkFromDateDay)+1) ;
			}else if(brkToDateMonth==2 && brkFromDateMonth==2){
			dayDiff = ((yearDiff * 360) +   (Number(brkToDateDay) - Number(brkFromDateDay)-1)) ;
			}else{
			
			dayDiff = (( yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay) + 1) ;
			}
			}
			
			
			}
			}
			}
			}
			}
		document.getElementById("hidDays"+count).value = dayDiff
		}catch(e){
		
		}
		
	}



function setTotalDaysOfSrvcBrk(FieldName1,FieldName2)
{
	if(document.getElementById('tblServBrkDtls').rows.length == 1)
	{
	document.getElementById("txtTotSerBreak").value = Number("0.00");
	document.getElementById("txtNonQualifyingServ").value = Number("0.00");
	}
	var rowCount=Number(document.getElementById("tblServBrkDtls").rows.length);
	var total=0;

	if(rowCount >1){
	for(var cnt=0;cnt<Number(rowCount+1);cnt++)
	{
	try{
	if(document.getElementById(FieldName1+cnt)!=null && document.getElementById(FieldName1+cnt).value!='' && document.getElementById(FieldName2+cnt)!=null && document.getElementById(FieldName2+cnt).value!='')
	{
		//alert("hey");
	calTotalSrvcBrk(cnt);
	//alert("Test hdDay:::: "+Number(document.getElementById("hidDays"+cnt).value));
	total=total+Number(document.getElementById("hidDays"+cnt).value);
	//alert("Total break ::"+total);
	document.getElementById("txtTotSerBreak").value=total;
	document.getElementById("txtNonQualifyingServ").value=total;
	//alert("total : "+total);
	//calQualifyingSrvc();
	//alert("Bug:::::::");
	
	var TotalBrkYear = Math.floor(Number((total/360)));
	var TotalBrkMonth = Math.floor(Number((total%360)/30));
	var TotalBrkDays = (total%360)%30;
	//alert("TotalBrkYear:::::::"+TotalBrkYear);
	//alert("TotalBrkMonth:::::::"+TotalBrkMonth);
	//alert("TotalBrkDays:::::::"+TotalBrkDays);
	
	document.getElementById("hidTotalBrkyear").value=TotalBrkYear;
	document.getElementById("hidTotalBrkMonth").value=TotalBrkMonth;
	document.getElementById("hidTotalBrkDays").value=TotalBrkDays;
	
	document.getElementById("txtTotSerBreakYear").value=TotalBrkYear;
	document.getElementById("txtTotSerBreakMonth").value=TotalBrkMonth;
	document.getElementById("txtTotSerBreakDay").value=TotalBrkDays;
	
	document.getElementById("txtNonQualifyingServYear").value=TotalBrkYear;
	document.getElementById("txtNonQualifyingServMonth").value=TotalBrkMonth;
	document.getElementById("txtNonQualifyingServDay").value=TotalBrkDays;
	
	
	}
	}catch(e){
	}
	}
	}else{
	document.getElementById("hidTotalBrkyear").value="0";
	document.getElementById("hidTotalBrkMonth").value="0";
	document.getElementById("hidTotalBrkDays").value="0";
	
	document.getElementById("txtTotSerBreakYear").value=0;
	document.getElementById("txtTotSerBreakMonth").value=0;
	document.getElementById("txtTotSerBreakDay").value=0;
	
	document.getElementById("txtNonQualifyingServYear").value=0;
	document.getElementById("txtNonQualifyingServMonth").value=0;
	document.getElementById("txtNonQualifyingServDay").value=0;
	}
	setQualifyingService();
}

function setTotalDaysOfQlyServ(FieldName1)
{
	if(document.getElementById('txtTotQlyServYear1').value !=null)
	{
	document.getElementById("txtTotQlyServ").value = Number("0.00");
	document.getElementById("txtQlyService").value = Number("0.00");
	}
	var PayComsn = document.getElementById("cmbPayComsn").value;
	if(PayComsn == '7THPAYCOMSN' || PayComsn == '6THPAYCOMSN' || PayComsn == 'Reddy Commission'){
	
	document.getElementById("txtTotQlyServYear").value=document.getElementById("txtTotQlyServYear1").value;
	document.getElementById("txtTotQlyServMonth").value=document.getElementById("txtTotQlyServMonth1").value;
	document.getElementById("txtTotQlyServDay").value=document.getElementById("txtTotQlyServDay1").value;
	
	document.getElementById("txtQualiServYear").value=document.getElementById("txtTotQlyServYear1").value;
	document.getElementById("txtQualiServMonth").value=document.getElementById("txtTotQlyServMonth1").value;
	document.getElementById("txtQualiServDay").value=document.getElementById("txtTotQlyServDay1").value;
	
	var rowCount=Number(document.getElementById("tblQylServDtls").rows.length);
	var total=0;
	var totQlySer = 0;
	var actlser = document.getElementById("txtActualSer").value;
	var NonQly = document.getElementById("txtNonQualifyingServ").value;
	
	
	if(document.getElementById('txtTotQlyServYear1').value !=null)
	{
		//alert("1 = txtTotQlyServYear"+document.getElementById('txtTotQlyServYear1').value);
	document.getElementById("txtTotQlyServ").value = Number(document.getElementById("txtTotQlyServYear").value) * 360 + Number(document.getElementById("txtTotQlyServMonth").value) *30 + Number(document.getElementById("txtTotQlyServDay").value);
	document.getElementById("txtQlyService").value = Number(document.getElementById("txtTotQlyServYear").value) * 360 + Number(document.getElementById("txtTotQlyServMonth").value) *30 + Number(document.getElementById("txtTotQlyServDay").value);
	totQlySer=Number(document.getElementById("txtTotQlyServYear1").value) * 360 + Number(document.getElementById("txtTotQlyServMonth1").value) *30 + Number(document.getElementById("txtTotQlyServDay1").value);
	var newqulyser = actlser - NonQly + totQlySer ;
	//alert("Test 1 total qualifying service"+newqulyser);
	var TotalQlyYear1 = Math.floor(Number((newqulyser/360)));
	var TotalQlyMonth1 = Math.floor(Number((newqulyser%360)/30));
	var TotalQlyDays1 = (newqulyser%360)%30;
	//alert("Step 1 passed ok ..");
	document.getElementById("txtQualifyingServYear").value = TotalQlyYear1;
	document.getElementById("txtQualifyingServMonth").value = TotalQlyMonth1;
	document.getElementById("txtQualifyingServDay").value = TotalQlyDays1;
	calQualifyingSrvc();
	
	var TotalQlyYear = Math.floor(Number((totQlySer/360)));
	var TotalQlyMonth = Math.floor(Number((totQlySer%360)/30));
	var TotalQlyDays = (totQlySer%360)%30;
	
	document.getElementById("hidTotalServyear").value=TotalQlyYear;
	document.getElementById("hidTotalServMonth").value=TotalQlyMonth;
	document.getElementById("hidTotalServDays").value=TotalQlyDays;
	
	document.getElementById("txtTotQlyServYear").value=TotalQlyYear;
	document.getElementById("txtTotQlyServMonth").value=TotalQlyMonth;
	document.getElementById("txtTotQlyServDay").value=TotalQlyDays;
	
	document.getElementById("txtQualiServYear").value=TotalQlyYear;
	document.getElementById("txtQualiServMonth").value=TotalQlyMonth;
	document.getElementById("txtQualiServDay").value=TotalQlyDays;
	
	}else{
		document.getElementById("txtTotQlyServ").value = Number("0.00");
		document.getElementById("txtQlyService").value = Number("0.00");
		
		document.getElementById("hidTotalServyear").value=Number("0.00");
		document.getElementById("hidTotalServMonth").value=Number("0.00");
		document.getElementById("hidTotalServDays").value=Number("0.00");
		
		document.getElementById("txtTotQlyServYear").value=Number("0.00");
		document.getElementById("txtTotQlyServMonth").value=Number("0.00");
		document.getElementById("txtTotQlyServDay").value=Number("0.00");
		
		document.getElementById("txtQualiServYear").value=Number("0.00");
		document.getElementById("txtQualiServMonth").value=Number("0.00");
		document.getElementById("txtQualiServDay").value=Number("0.00");
	}
	
	}
	
}


function calQualifyingSrvc()
{
	
	calActualServiceDays();
	document.getElementById("txtQualifyingServ").value = (Number(document.getElementById("txtActualSer").value) - Number(document.getElementById("txtNonQualifyingServ").value) )+ Number(document.getElementById("txtQualiServ").value);
	document.getElementById("txtQualifyingServ").value = Number(document.getElementById("txtActualSer").value) + Number(document.getElementById("txtQlyService").value); 
	var totalQualiser = Number('00');

	if(document.getElementById('txtTotQlyServYear1').value !=null)
	{
	 totalQualiser =document.getElementById("txtTotQlyServYear1").value * 360  + Number(document.getElementById("txtTotQlyServMonth1").value) * 30 + Number(document.getElementById("txtTotQlyServDay1").value); 
	}
	
	var totalQualifyingServ =Number(document.getElementById("txtQualifyingServ").value) + totalQualiser;
	var TotalBrkYear = Math.floor(Number((totalQualifyingServ/360)));
	var TotalBrkMonth = Math.floor(Number((totalQualifyingServ%360)/30));
	var TotalBrkDays = (totalQualifyingServ%360)%30;
	
	document.getElementById("txtQualifyingServYear").value=TotalBrkYear;
	document.getElementById("txtQualifyingServMonth").value=TotalBrkMonth;
	document.getElementById("txtQualifyingServDay").value=TotalBrkDays;
}



function adjustTotalDays(count)
{
	if(document.getElementById('tblServBrkDtls').rows.length == 1)
	{
	document.getElementById("txtTotSerBreak").value = Number("0.00");
	document.getElementById("txtNonQualifyingServ").value = Number("0.00");
	
	document.getElementById("txtTotSerBreakYear").value=0;
	document.getElementById("txtTotSerBreakMonth").value=0;
	document.getElementById("txtTotSerBreakDay").value=0;
	
	document.getElementById("txtNonQualifyingServYear").value=0;
	document.getElementById("txtNonQualifyingServMonth").value=0;
	document.getElementById("txtNonQualifyingServDay").value=0;
	
	}else if(document.getElementById("txtTotSerBreak").value != "")
	{	
	try{
	document.getElementById("txtTotSerBreak").value = Number(document.getElementById("txtTotSerBreak").value)-  Number(document.getElementById("hidDays"+count).value);
	document.getElementById("txtNonQualifyingServ").value = Number(document.getElementById("txtNonQualifyingServ").value)-  Number(document.getElementById("hidDays"+count).value);
	}catch(e){
	}
	}
}

function adjustTotalQlyDays(count)
{
	if(document.getElementById('tblQylServDtls').rows.length == 0)
	{
	document.getElementById("txtTotQlyServ").value = Number("0.00");
	document.getElementById("txtQlyService").value = Number("0.00");
	
	document.getElementById("txtQualiServYear").value=0;
	document.getElementById("txtQualiServMonth").value=0;
	document.getElementById("txtQualiServDay").value=0;
	
	
	
	}else if(document.getElementById("txtTotQlyServ").value != null)
	{	
	try{
	document.getElementById("txtQlyService").value = Number(document.getElementById("txtQlyService").value)+  Number(document.getElementById("hidQylDays").value);
	document.getElementById("txtTotQlyServ").value = Number(document.getElementById("txtTotQlyServ").value)+  Number(document.getElementById("hidQylDays").value); //+ Number(document.getElementById("txtQlyService").value)+  Number(document.getElementById("hidQylDays"+count).value); 
	
	document.getElementById("txtQualiServYear").value=document.getElementById("txtQualiServYear").value;
	document.getElementById("txtQualiServMonth").value=document.getElementById("txtQualiServMonth").value;;
	document.getElementById("txtQualiServDay").value=document.getElementById("txtQualiServDay").value;;
	
	
	}catch(e){
	}
	}
}

