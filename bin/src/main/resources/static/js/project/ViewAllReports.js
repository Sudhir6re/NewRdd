$('#search').click(function(e) {
	e.preventDefault();
	//var billNumber = $("#billNumber").val();
	var yearName = $("#paybillYear").val();
	var monthName = $("#paybillMonth").val();
	if (monthName == "" || monthName == "0") {
		e.preventDefault();
		swal("Please select month");
	} 
	 else if (yearName == "" || yearName == "0") {
			e.preventDefault();
			swal("Please select year");
		}
		$
				.ajax({
					type : "GET",
					url : "../master/btnShowReport/"+ yearName + "/" + monthName,
					async : false,
					error : function(data) {
						console.log(data);
					},
					success : function(data) {
						
						$('#tblDataTable').show();
						$('#tblShowPayBill_wrapper').show();
						$('#tblDataTable tbody').html('');
						if (parseInt(data.length) > 0) {
							console.log("code updated");
							//alert(data.length);
							console.log(data);
							var a =$("#tblDataTable").dataTable();
							a.fnClearTable();
					// $("#tblDataTable").dataTable().fnClearTable();
							var subDivsion,billDescription,ddoCode,paybillId,billGroupId;
							$
									.each(
											data,
											function(i, result) {
												subDivsion = result[0],
												billDescription = result[1];
												ddoCode = result[2];
												paybillId = result[3];
												billGroupId = result[4];
											//	var action="<input type='button' class='btn' value='Edit'/>"+result[3];
												var action="<a  target='_blank' href='/MJP/paybill/outerreportsearch?month="+monthName+"&yearName="+yearName+"&billGroupId="+billGroupId+"&paybillId="+paybillId+"&ddoCode="+ddoCode+"' >"+"Show Report"+"</a>";
												//var action="<a  target='_blank' href='/MJP/paybill/btnShowReport/"+yearName+"/"+monthName+"/"+ billGroupId' >"+"Show Report"+"</a>";
                                                console.log(action);
                                              
											a.fnAddData([subDivsion,billDescription,action,ddoCode,paybillId,billGroupId]);
											});
						}
						else{
							swal("No Records Found");
						}
					}
				});
	
});






