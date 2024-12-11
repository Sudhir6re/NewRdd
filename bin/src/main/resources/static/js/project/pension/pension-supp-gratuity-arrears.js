var status = false;
///$("#pensupTbmain").hide();

function SearchEmployee(e) {
	// alert('inside validateUIDUniqe');
	$('#action').val('SEARCH_EMP');
	 $("#pensupTbmain2").show();
	//document.getElementById("BrokenPeriod").submit();
	var searchName = document.getElementById("sevaarthid").value;
	var yearId = document.getElementById("yearName").value;
	var monthId = document.getElementById("monthName").value;
	
	if(searchName == "")
	{
		swal('Please enter PPO Number');
		return false;
	}
	if(yearId == 0)
	{
		swal('Please Select Pay year');
		return false;
	}
	if(monthId == 0)
	{
		swal('Please Select Pay month');
		return false;
	}
	var flag=true;
		$.ajax({
			type : "GET",
			url : "../master/isEmpMappedWithBillGroup/"+searchName,
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
			 console.log(data);
			// alert("erro");
			},
			success : function(data) {
//				alert("A="+data);
				if(data=="0"){
					flag=false;
					 swal("Employee not mapped with  Bill Broup");
					 return false;
//					 $("#brokenSearchbtn").prop("disabled",true);
				}
//				dataFromServer=data;
			}
	    });
		var sevaarthid=searchName;
		$.ajax({
			type : "GET",
			url : "../master/getSevaarthIdMappedWithPaybill/"+sevaarthid+"/"+monthId+"/"+yearId,
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
			 console.log(data);
			},
			success : function(data) {
				
				 $("#pensupTbmain2").show();
				/*if(data==1){
					flag=false;
					 swal("Paybill is already generated for this sevaarth id");
					 return false;
//					 $("#brokenSearchbtn").prop("disabled",true);
				}
				else*/ 
				if(data==2){
					flag=false;
					swal("Paybill is inprogress for this sevaarth id");
					 return false;	
				}
				
			}
	    });
	
		if(flag){
			$("#BrokenPeriod").submit();   
			
			 ///$(".BrokenPeriodHiddenData").show();
			 $("#pensupTbmain2").show();
			 $("#pensupTbmain").dataTable();
			 
		//	e.preventDefault();
			return true;
		}
	
}

function saveData()
{
var ppoNo = $("#sevaarthid").val()
var year = $("#yearName").val()
var month = $("#monthName").val()
var arrears = $("#arrears").val()
var remarks = $("#remarks").val()

if(arrears ==''){
	
	swal("Please enter the arrears amount!..");
}else if(remarks==''){
	swal("Please enter the remark!..");
}	


else{

	$
	.ajax({
		type : "GET",
		url : "saveSuppGratuityArrearsData/"+ppoNo+"/"+year+"/"+month+"/"+arrears+"/"+remarks,
		async : true,
		contentType : 'application/json',
		error : function(data) {
			alert("Something went wrong...");
		},
		success : function(data) {
//			var len = data.length;
		swal("Arrears has been saved for the employee.");    
		 
		 location.reload();
		   
		}
		});
}
}

