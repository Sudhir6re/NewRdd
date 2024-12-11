$("#txtSevaarthId").change(function() {
	var contextPath = $("#appRootPath").val();
	var sevaarthId = $("#txtSevaarthId").val();

	if (sevaarthId != '') {
		$.ajax({
			type : "GET",
			url : contextPath + "/mdc/getDOJ/" + sevaarthId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				// console.log(data);
			},
			beforeSend : function() {
				$("#loaderMainNew").show();
			},
			complete : function(data) {
				$("#loaderMainNew").hide();
			},
			success : function(data) {
				$.each(data, function(index, row) {
					$("#txtDOJ").val(row[0]);
				});

			}
		});
	}

});

