$("#btnExcel").click(function() {
	$(".table-excel").table2excel({
		filename : "report.xls"
	});
});