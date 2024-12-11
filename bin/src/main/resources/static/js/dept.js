$(document).ready(function() {

	$('#back-button').click(function() {
		parent.history.back();
		return false;
	});

	/*$("#logo-slider").owlCarousel({
		autoplayHoverPause : true,
		autoPlay : 3000, // Set AutoPlay to 3 seconds
		loop : true,
		items : 6,
		itemsDesktop : [ 1199, 6 ],
		itemsDesktopSmall : [ 979, 3 ]
	});
*/
	/*$('.faq-mm').hover(function() {
		$(".panel").hide();
		$(this).find(".panel").slideToggle();
	});*/
	$('.faq-mm').hover(function() {
		/*$(".panel").hide();*/
		$(this).find(".panel").toggleClass("showpannel");
	});
});
