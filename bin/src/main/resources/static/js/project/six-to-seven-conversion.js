$(document).ready(function() {
	$("#sixtosevenPCtable").DataTable();
	$(".PCoptions").hide();
	$(".sixtosevenPCtablemain").hide();
	$(".aucMain").hide();
	
	$("#search").click(function() {
		$(".PCoptions").show();
	});
	
	$(".pctype").change(function() {
		$(".sixtosevenPCtablemain").show();
	});
	
	$(".promobtn").click(function() {
		$(".aucMain").show();
	});

});