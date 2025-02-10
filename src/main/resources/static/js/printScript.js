function printContent(el) {
	var restorepage = $('body').html();
	var printcontent = $('#' + el).clone();
	$('body').empty().html(printcontent);
	setTimeout(function() {
		window.print();
		$('body').html(restorepage);
	}, 250);

}


	$(".btnExcel").click(function() {
		$(".table-excel").table2excel({
			filename : "EmployeesStatastics.xls"
		});
	});
