$("#empname").click(function(e) {
				var empId= $("#empId").val();
				$.ajax({
					type : "GET",
					url : "../master/getEmpDetails/"+empId,
					async : false,
					contentType: "application/json",
			        dataType: "json",
					error : function(data) {
					 console.log(data);
					// alert("erro");
					},
					success : function(data) {
						 console.log("data");
						 console.log(data);
					}
			    });