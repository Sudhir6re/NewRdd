function printContent(el) {
	var restorepage = $('body').html();
	var printcontent = $('#' + el).clone();
	$('body').empty().html(printcontent);
	setTimeout(function() {
		window.print();
		$('body').html(restorepage);
	}, 250);

}