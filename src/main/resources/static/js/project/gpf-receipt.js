function nextPage()
{
	//alert("hello");
	var financialYear = document.getElementById("yearId").value;
	var sevaarthId = document.getElementById("sevaarthId").value;
	window.location.href = "viewGPFLedgerBroadsheetNextPage?sevaarthId"+sevaarthId+"&finYear="+financialYear; 

}


function generateExcel()
{
	var financialYear = document.getElementById("yearId").value;
	var sevaarthId = document.getElementById("sevaarthId").value;
	var status = "2";
	//alert(ddoCode);
	$.ajax({
		url : '/gfp/report/getFinancialYearWiseBroadSheetExcel',
		contentType : 'application/json',
		dataType : 'json',
		async : false,
		data : {
			sevaarthId : sevaarthId,
			finYear : financialYear,
			randomNum : Math.random(),
			statusCode : status

		},	success : function(data) { 
			//alert(data.length);	
			console.log(JSON.stringify(data));
  			  response.setContentType("application/vnd.ms-excel");
             response.setHeader("Content-Disposition", "attachment; filename="+ "GPFBroadsheetReport.xls");
 			
 			$('#dynamic-table tbody').remove();
			if (data.length == 0) {
				$('#dynamic-table').append('<tbody></tbody>');
			} else {
				$('#dynamic-table').append('<tbody>');
				var last_id=data.length-1;
				for (var i = 0; i < data.length; i++) {

					var dynaTableData = '<tr>';
					var newid = i + 1;
					 	dynaTableData+='<td>'+newid+'</td>'; 
					/* dynaTableData+='<td>'+data[i].id+'</td>'; */
					
					dynaTableData += '<td>' + data[i].GPF_ACC_NO + '</td>';
					dynaTableData += '<td>' + data[i].EMP_NAME + '</td>';
					dynaTableData += '<td></td>';
					dynaTableData += '<td>' + data[i].OPENING_BALANCE + '</td>';
					dynaTableData += '<td>' + data[i].APR_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].MAY_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].JUNE_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].JULY_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].AUG_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].SEP_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].OCT_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].NOV_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].DEC_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].JAN_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].FEB_CR_REGULAR + '</td>';
					dynaTableData += '<td>' + data[i].MARCH_CR_REGULAR + '</td>';
					dynaTableData += '</tr>';

					$('#dynamic-table').append(dynaTableData);
				}
			
				var dynaTableData = '<tr>';
					
				dynaTableData += '<td colspan="4"><b>Total<b></td>';
				dynaTableData += '<td>' + data[last_id].opening_balance_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_april_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_may_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_june_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_july_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_aug_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_sep_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_oct_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_nov_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_dec_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_jan_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_feb_total + '</td>';
				dynaTableData += '<td>' + data[last_id].cr_march_total + '</td>';
				dynaTableData += '</tr>';

				$('#dynamic-table').append(dynaTableData);

				$('#dynamic-table').append('</tbody>');

			}
			$(".loaderMainNew").hide();


		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert("Error !" + xhr.status);
		}
	});
}




function printReport1()
{
    var mywindow = window.open('', 'PRINT', 'height=700,width=800,border=1px solid black');

    mywindow.document.write('<html><head><title>Print Form</title>');
    mywindow.document.write('</head><body >');
    mywindow.document.write('<h1></h1>');
    mywindow.document.write(document.getElementById("maindiv").innerHTML);
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10*/

    mywindow.print();
    mywindow.close();

    return true;
}

	addReport();
	function addReport() {
		$(".loaderMainNew").show();
		
		var financialYear = document.getElementById("yearId").value;
		var sevaarthId = document.getElementById("sevaarthId").value;
		var status = "1";
		//alert(ddoCode);
		$.ajax({
			url : '../master/getFinancialYearWiseBroadSheet',
			contentType : 'application/json',
			dataType : 'json',
			async : false,
			data : {
				sevaarthId : sevaarthId,
				finYear : financialYear,
				randomNum : Math.random(),
				statusCode : status
			},
			success : function(data) { //alert(data.length);	
				console.log(JSON.stringify(data));
							console.log(JSON.stringify(data));
				$('#dynamic-table tbody').remove();
				if (data.length == 0) {
					$('#dynamic-table').append('<tbody></tbody>');
				} else {
					$('#dynamic-table').append('<tbody>');
					var last_id=data.length-1;
					for (var i = 0; i < data.length; i++) {

						var dynaTableData = '<tr>';
						var newid = i + 1;
						 	dynaTableData+='<td>'+newid+'</td>'; 
						/* dynaTableData+='<td>'+data[i].id+'</td>'; */
						dynaTableData += '<td>' + data[i].GPF_ACC_NO + '</td>';
						dynaTableData += '<td>' + data[i].EMP_NAME + '</td>';
						dynaTableData += '<td></td>';
						dynaTableData += '<td>' + data[i].OPENING_BALANCE + '</td>';
						dynaTableData += '<td>' + data[i].APR_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].MAY_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].JUNE_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].JULY_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].AUG_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].SEP_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].OCT_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].NOV_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].DEC_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].JAN_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].FEB_CR_REGULAR + '</td>';
						dynaTableData += '<td>' + data[i].MARCH_CR_REGULAR + '</td>';
						dynaTableData += '</tr>';

						$('#dynamic-table').append(dynaTableData);
					}
				
					var dynaTableData = '<tr>';
						
					dynaTableData += '<td colspan="4"><b>Total<b></td>';
					dynaTableData += '<td>' + data[last_id].opening_balance_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_april_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_may_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_june_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_july_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_aug_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_sep_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_oct_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_nov_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_dec_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_jan_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_feb_total + '</td>';
					dynaTableData += '<td>' + data[last_id].cr_march_total + '</td>';
					dynaTableData += '</tr>';

					$('#dynamic-table').append(dynaTableData);

					$('#dynamic-table').append('</tbody>');

				}
				$(".loaderMainNew").show();

			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("Error !" + xhr.status);
			}
		});
	}