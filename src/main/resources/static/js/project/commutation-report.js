$("#GenerateReport")
				.click(
						function(e) {
							
							var fromdate = $("#fromdate").val();
							var todate = $("#todate").val();
							
							$.ajax({
								type : "GET",
								url : "../master/getCommutationReport/"+fromdate+"/"+todate,
										
								async : false,
								contentType : 'application/json',
								error : function(data) {
									console.log(data);
									$("#loaderMainNew").hide();
								},
								success : function(data) {
									console.log(data);
									var len = data.length;
									if (len != 0)
										{
										}
										
									else {
										swal("Record not found!");
										e.preventDefault();
										}
									
								}
							});
							
						});
