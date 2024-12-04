function popupSancDetailsHI(){
		
	var loanstdt = document.getElementById("txtLoanStDate").value;
	
	var lArrDate = loanstdt.split("/");	
	
	lArrDate[2] = lArrDate[2] + 1;
			
	var temp = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
		
	var tempmonth = temp.getMonth()+1;
	
	
	var loanenddt = "";

	if(tempmonth < 10){
	loanenddt = temp.getDate() + '/' + "0" + (temp.getMonth()+1) + '/' + (temp.getFullYear()-18135);
	}
	
	if(tempmonth > 10){
	loanenddt = temp.getDate() + '/' + (temp.getMonth()+1) + '/' + (temp.getFullYear()-18135);
	}	
	
	
	if(loanstdt != ""){
	document.getElementById("txtLoanEndDate").value=loanenddt;
	}
	

}





	


	








