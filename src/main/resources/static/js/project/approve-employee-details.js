function editempconfig(empid) {
		var employeeid = empid;
		// 	alert("employeeid="+employeeid);
		$('#employeeId').val(employeeid);
		// 	var queryString = $('#myEForm').serialize();
		// 	alert("queryString="+queryString);
		document.getElementById("myAForm").submit();
	}