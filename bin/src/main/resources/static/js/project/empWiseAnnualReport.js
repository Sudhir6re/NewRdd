$("#search").click(function(e) {
var form=$("#empWiseAnnualReport");
	var flag=0;
	var sevaarthId= $("#sevaarthId").val();
	var yearId= $("#yearId").val();

	if(sevaarthId==""||sevaarthId==undefined){
		 e.preventDefault();
		swal("Please enter SevaarthId..!!");
		flag=1;
	}else if(yearId==""||yearId=='0'||yearId==undefined){
		 e.preventDefault();
		swal("Please select Financial Year..!!");
		flag=1;
	}
	if(flag==0){
		form.submit();
		
	}
	
	
	
});
