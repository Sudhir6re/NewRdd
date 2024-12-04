$("#cmbSearchBy").change(function(){
		var block=$("#cmbSearchBy").val();
		$("#sevaarthIdBlock").addClass("hide");
		$("#employeeNameBlock").addClass("hide");
		$("#"+block).removeClass("hide");
	});


$("#btnSearch").click(function(){
	
	var textEmpName=$("#textEmpName").val();
	var textSevaarthId=$("#textSevaarthId").val();
	
	if(textEmpName!='' || textSevaarthId!='' ){
	}
});