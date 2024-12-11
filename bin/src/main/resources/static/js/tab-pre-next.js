jQuery(document).ready(function() {
	$(".tabClass").click(function() {
		var prev = $(this).attr("data-prev");
		var next = $(this).attr("data-next");
		if (prev != "0") {
			$("#btnPrevious").show();
		} else {
			$("#btnPrevious").hide();
		}

		if (next != "0") {
			$("#btnNext").show();
		} else {
			$("#btnNext").hide();
		}

	});

	updateButtonVisibility();
	function updateButtonVisibility() {
		var $tabs = $('#tabContainer .nav-tabs > li');
		var $activeTab = $('#tabContainer .nav-tabs > .active');
		var $prevTab = $activeTab.prev('li');
		var $nextTab = $activeTab.next('li');

		if ($prevTab.length > 0) {
			$('#btnPrevious').show();
		} else {
			$('#btnPrevious').hide();
		}

		if ($nextTab.length > 0) {
			$('#btnNext').show();
		} else {
			$('#btnNext').hide();
		}
	}

	$('#btnPrevious').click(function() {
		var $activeTab = $('#tabContainer .nav-tabs > .active');
		var $prevTab = $activeTab.prev('li');

		if ($prevTab.length > 0) {
			$prevTab.find('a').tab('show');
			updateButtonVisibility();
		}
	});

	$('#btnNext').click(function() {
		var $activeTab = $('#tabContainer .nav-tabs > .active');
		var $nextTab = $activeTab.next('li');

		if ($nextTab.length > 0) {
			$nextTab.find('a').tab('show');
			updateButtonVisibility();
		}
	});
});
