//$("#displayData").hide();

var contextPath = "";

jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
	
	var noDataFoundMsg = $("#noDataFoundMsg").val();
	if (noDataFoundMsg != "" && noDataFoundMsg != undefined) {
		swal('' + noDataFoundMsg, {
			icon : "warning",
		});
	}
	
	
	
	contextPath = $("#appRootPath").val();
	if(!contextPath){
		contextPath=window.origin;
	}
	
	$("#dcpsVerifyTb").dataTable();
});


function getEmp() {
		$("#action").val("search");
		if($("#txtSevaarthId").val()==""){
			 showError("#txtSevaarthId", "Sevarth Id/Employee Name/DCPS ID is required.");
		}else{
			$("#dcpsLegacyEntryForm").submit();
		}
}






$("#btnSave").click(function(e){
	e.preventDefault();
	$("#dcpsLegacyEntryForm").attr('action',$("#appRootPath").val()+'/ddo/saveDcpsLegacyData');
	if(validateForm()==true){
		if(validatePeriod()==true ){
			swal("Data Exist for given period");
		}else{
			$("#dcpsLegacyEntryForm").submit();
		}
	}
});



$("#period0").change(function(e){
	if(validatePeriod()==true ){
		swal("Data Exist for given period");
	}
});


$("#contriStartDt0").blur(function(e){
	compareDate("contriStartDt0","doj0","<","Contribution Start Date cannot be less than Employee Date of joining");
	compareDate("contriStartDt0","empServiceEndDate0",">","Contribution Start Date cannot be greater than Employee Service End Date");
	compareDate("contriStartDt0","contriEndDt0",">","Contribution Start Date cannot greater than  End Date");
});


$("#contriEndDt0").blur(function(e){
	
	
	
	
	compareDate("contriEndDt0","doj0","<","Contribution Start Date cannot be less than Employee Date of joining","");
	compareDate("contriEndDt0","empServiceEndDate0",">","Contribution End Date cannot be greater than Employee Service End Date","");
	compareDate("contriEndDt0","empServiceEndDate0",">","Contribution End Date cannot be greater than Employee Service End Date","");
	compareDate("contriEndDt0","contriStartDt0","=","Contribution Start Date and End Date cannot be same","");
	compareDate("contriEndDt0","contriStartDt0","<","Contribution End Date cannot less than  End Date","");
	contriEndDt();
	
});



function contriEndDt() {
    var period1 = $("#period0").val();
    var contriEndDt = $("#contriEndDt0").val(); 
    if (contriEndDt) {
        var toDate, alrtStr;

        switch (period1) {
            case '10001198258':
                toDate = '2021-03-31';
                alrtStr = "Contribution End Date should not be greater than 31/03/2021";
                break;
            case '10001198259':
                toDate = '2022-03-31';
                alrtStr = "Contribution End Date should not be greater than 31/03/2022";
                break;
            case '10001198260':
                toDate = '2023-03-31';
                alrtStr = "Contribution End Date should not be greater than 31/03/2023";
                break;
            case '10001198261':
                toDate = '2024-03-31';
                alrtStr = "Contribution End Date should not be greater than 31/03/2024";
                break;
            default:
                return; 
        }
        compareDate("contriEndDt0", "", ">", alrtStr, toDate);
    }
}

	
function compareDate(dateField1,dateField2,relationOperator,errorMsg,toDate){
	 var date1 = $('#'+dateField1).val();
     var date2 ="";
    	 
    	 if(toDate==""){
    		 date2 = $('#'+dateField2).val();
    	 }else if(!toDate==""){
    		 date2=toDate;
    	 }
     
     if(date1 && date2){
    	 var d1 = new Date(date1);
         var d2 = new Date(date2);
         if (d1 < d2 && relationOperator==="<") {
        	    swal(errorMsg);
        	    $("#"+dateField1).val("");
         } else if (d1 > d2 && relationOperator===">") {
        	    swal(errorMsg);
        	    $("#"+dateField1).val("");
         }else if(relationOperator==="="  && d1==d2){
        	$('#'+dateField1).val("");
           $('#'+dateField2).val("");
           swal(errorMsg);
         } 
     }
}

function validatePeriod(){
	var flag=false;
	var sevaarthId=$("#sevaarthId0").val();
	var period=$("#period0").val();
	var params = {
		sevaarthId : sevaarthId,
		period : period
 	};
	
	$.ajax({
		type : "POST",
		url : contextPath + '/ddo/checkDataExistsForPeriod',
		data : JSON.stringify(params),
		contentType : 'application/json',
		 async: false,
		error : function(xhr, status, error) {
			console.error('Error occurred:', error);
			$("#loaderMainNew").hide();
		},
		success : function(data) {
			$("#loaderMainNew").hide();
			console.log(data);
			if (parseInt(data)>0) {
				flag=true;
				$("#period0").val("0");
			}
		}
	});
	
	return flag;
}


$("#empInterest0").blur(function(){
	setEmployerInt();
	sum();
	validatezeros();
	validateAmount();
});

$("#empInterest0, #employerInterest0").blur(function(){
	sum();
	validatezeros();
	validateAmount();
});

$("#empContri0").blur(function(){
	setEmployerContri();
	sum();
	validatezeros();
	validateAmount();
});

$("#employerContri0").blur(function(){
	sum();
	validatezeros();
	validateAmount();
});


function validatezeros() {
	var emp = parseInt($("#empInterest0").val());
	var employer = parseInt($("#employerInterest0").val());
	if (emp < 0.1 || employer < 0.1) {
		swal("Please Enter Correct value");
		$("#empInterest0").val("");
		$("#employerInterest0").val("")
	}
}


function validateAmount(){

	 var period = document.getElementById("period0").value;
	 var contri = parseInt($("#empContri0").val());
	 var interest = parseInt($("#empInterest0").val());
		 if(contri>6000000 || interest>6000000 )
		 {
			 swal("Maximum acceptable value is 60 lakhs only");
			makeAllEmpty();
		}
	 if(period==10001198212)
	 {
		 if(contri>6000000 || interest>6000000 )
		 {
			 swal("Maximum acceptable value is 60 lakhs only");
			 makeAllEmpty();
		}
	 }
	}


function validateInterest() {
		var contri = parseInt($("#empContri0").value);
		var interest = parseInt(document.getElementById("#empInterest0").value);
	if (interest > contri) {
			 swal("Interest should not be greater than contribution");
			 makeAllEmpty();
		}  
	}

	function setEmployerContri() {

		var empContri = $("#empContri0").val();
		var period1 =$("#period0").val();
		if (period1 == '10001198277') {
			var contri = (Number(empContri * 40)/100).toFixed(2);
			var contriemplr = (Number(empContri) + Number(contri));
			$("#employerContri0").val(contriemplr);
		}
		else if(period1 =='10001198258')
			{
			$("#employerContri0").val(empContri);
			}
	}

	function setEmployerInt() {
		if($("#empContri0").val() == '' || $("#empContri0").val()== null)
		{	
			$("#empInterest0").val("")
		}
		var empContri = $("#empInterest0").val();
		$("#employerInterest0").val(empContri)
	}

function validTotal() {
	var total = $("#total0").val();
	if (total > 6000000) {
		swal("Total amount should not be greater than 60 Lacs");
		$("#empContri0").val("");
		$("#employerContri0").val("");
		$("#total0").val("");
	}

}

function sum() {
	var empContri = Number($("#empContri0").val());
	var employerContri = Number($("#employerContri0").val());
	var empInterest = Number($("#empInterest0").val());
	var employerInterest = Number($("#employerInterest0").val());

	var total = empContri + employerContri + empInterest + employerInterest;
	if (isNaN(total)) {
		total = "";
	}
	$("#total0").val(total);
	validTotal();
}


function makeAllEmpty(){
	 $("#empContri0").val("");
	 $("#employerContri0").val("");
	 $("#empInterest0").val("");
	 $("#employerInterest0").val("");
	 $("#total0").val("");
}


function validateForm(){
	
	  var isValid = true; 
  var contriStartDt0 = $("#contriStartDt0").val();
  if (!contriStartDt0 || contriStartDt0.trim() === "") {
      showError("#contriStartDt0", "Contribution Start Date is required.");
      isValid = false;
  } else {
      hideError("#contriStartDt0");
  }
  
  var contriEndDt0 = $("#contriEndDt0").val();
  if (!contriEndDt0 || contriEndDt0.trim() === "") {
	  showError("#contriEndDt0", "Contribution End Date is required.");
	  isValid = false;
  } else {
	  hideError("#contriEndDt0");
  }

 

  var empContri = $("#empContri0").val();
  if (!empContri || empContri.trim() === "") {
      showError("#empContri0", "Employee Contribution is required.");
      isValid = false;
  } else {
      hideError("#empContri0");
  }

  var employerContri = $("#employerContri0").val();
  if (!employerContri || employerContri.trim() === "") {
      showError("#employerContri0", "Employer Contribution is required.");
      isValid = false;
  } else {
      hideError("#employerContri0");
  }

  var empInterest = $("#empInterest0").val();
  if (!empInterest || empInterest.trim() === "") {
      showError("#empInterest0", "Employee Interest is required.");
      isValid = false;
  } else {
      hideError("#empInterest0");
  }

  var employerInterest = $("#employerInterest0").val();
  if (!employerInterest || employerInterest.trim() === "") {
      showError("#employerInterest0", "Employer Interest is required.");
      isValid = false;
  } else {
      hideError("#employerInterest0");
  }


  var remark = $("#remark0").val();
  if (!remark || remark.trim() === "") {
      showError("#remark0", "Remark is required.");
      isValid = false;
  } else {
      hideError("#remark0");
  }

  var period = $("#period0").val();
  if (period == "0" || period == "") {
      showError("#period0", "Period is required.");
      isValid = false;
  } else {
      hideError("#period0");
  }
  
  return isValid;
}


  

