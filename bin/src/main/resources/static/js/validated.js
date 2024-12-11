
jQuery(document)
		.ready(
				function($) {

					$(".checkFirstChar").keyup(function() {
						var mi = $(this).val();
						var letters =/^[a-zA-Z][\sa-zA-Z]*/;
						if (mi.match(letters)) {
							return true;
						} else {
							$(this).val("");
							return false;
						}
					});

					$(".checkFirstCharhyphn").keyup(function() {
						var mi = $(this).val();
						var letters =/^[a-zA-Z\45][\sa-zA-Z]*/;
						if (mi.match(letters)) {
							return true;
						} else {
							$(this).val("");
							return false;
						}
					});
					$(".alphanumer").keyup(function(e) {
						/*
						var str = $(this).val();

						var replacestr = str.replace(/[^0-9a-zA-Z]+/g);
						console.log(finalUrl.replace(/^\s/ , '')); 
						$(this).val(replacestr);*/
						
							  var removeDspaceVal=$(this).val();
							  $(this).val(removeDspaceVal.replace(/^\s/ , ''));
						

					});

					$(".alphachar").keyup(function(e) {

						var str = $(this).val();

						var replacestr = str.replace(/[^a-zA-Z]+/g, '');

						$(this).val(replacestr);

					});

					$(".alphacharspace").keyup(function(e) {

						var str = $(this).val();

						var replacestr = str.replace(/[^a-zA-Z\s]+/g, '');

						$(this).val(replacestr);

					});

					$(".numericdot").keyup(function(e) {

						var str = $(this).val();

						var replacestr = str.replace(/[^0-9.]+/g, '');

						$(this).val(replacestr);

					});

					$(".numeric").keyup(function(e) {

						var str = $(this).val();

						var replacestr = str.replace(/[^0-9]+/g, '');

						$(this).val(replacestr);

					});
					
					
					$(".charDeshNo").blur(function(e) {

						var valueToCheck = $(this).val();

//						var filter = /^([^A-Z]){2}([-]){1}([0-9]){2}?$/;
						var filter =  /^([A-Z]){2}([-]){1}([0-9]){2}?$/;

						if (!filter.test(valueToCheck)) {

							swal({

								title : 'Please provide a valid  Field',

								styling : 'bootstrap3',

								type : 'error'

							});

							$(this).val('');

							$(this).focus;

							return false;

						}

					});

					$(".mobile").blur(function(e) {

						var valueToCheck = $(this).val();

						var filter = /^([1-9]){1}([0-9]){9}?$/;

						if (!filter.test(valueToCheck)) {

							swal({

								title : 'Please provide a valid Mobile number',

								styling : 'bootstrap3',

								type : 'error'

							});

							$(this).val('');

							$(this).focus;

							return false;

						}

					});
					
					
					
					$(".mobilevalidation").blur(function(e) {

						var valueToCheck = $(this).val();

						var filter = /^([1-9]){1}([0-9]){9}?$/;

						if (!filter.test(valueToCheck)) {

							 showError($(this),"Please enter valid mobile no");
							 $(this).val("");
						}
						else
							{
							 hideError($(this));
							}

					});


					$(".aadhar").blur(function(e) {

						var valueToCheck = $(this).val();

						var filter = /^[2-9]{1}[0-9]{11}$/;

						if (!filter.test(valueToCheck)) {

							swal({

								title : 'Please provide a valid Aadhar number',

								styling : 'bootstrap3',

								type : 'error'

							});

							$(this).val('');

							$(this).focus;

							return false;

						}

					});

					$(".amount").blur(function(e) {

						var valueToCheck = $(this).val();

						var filter = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;

						if (!filter.test(valueToCheck)) {

							swal({

								title : 'Please provide a valid Amount',

								styling : 'bootstrap3',

								type : 'error'

							});

							$(this).val('');

							$(this).focus;

							return false;

						}

					});

					$(".email")
							.blur(
									function(e) {

										var email = $(this).val();

										var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

										if (!filter.test(email)) {

											swal(
													{

														title : 'Please provide a valid Email Address',

														styling : 'bootstrap3',

														type : 'error'

													});

											$(this).val('');

											$(this).focus;

											return false;

										}

									});

					$(".pancard")
							.blur(
									function(e) {

										var valueToCheck = $(this).val();

										var filter = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;

										if (!filter.test(valueToCheck)) {

											swal(
													{

														title : 'Please provide a valid Pancard Number',

														styling : 'bootstrap3',

														type : 'error'

													});

											$(this).val('');

											$(this).focus;

											return false;

										}

									});
					
					
					$(".tan")
					.blur(
							function(e) {
								
								var valueToCheck = $(this).val();
								
								var filter = /^([a-zA-Z]){4}([0-9]){5}([a-zA-Z]){1}?$/;
								
								if (!filter.test(valueToCheck)) {
									
									swal(
											{
												
												title : 'Please provide a valid TAN Number',
												
												styling : 'bootstrap3',
												
												type : 'error'
													
											});
									
									$(this).val('');
									
									$(this).focus;
									
									return false;
									
								}
								
							});
					
					

					/*$('#datetimepicker1,#datetimepicker2,#datetimepicker3')
							.datetimepicker({

								format : 'DD/MM/YYYY',

								locale : 'en'

							});

					$('#datetimepicker1').datetimepicker().on(
							'dp.change',
							function(e) {

								if ($('#datetimepicker2')
										.data('DateTimePicker')) {

									$('#datetimepicker2')
											.data('DateTimePicker').minDate(
													moment(new Date(e.date)));

								}

								$(this).data("DateTimePicker").hide();

							});

					$('#datetimepicker2').datetimepicker().on('dp.change',
							function(e) {

								$(this).data("DateTimePicker").hide();

							});

					$('#datetimepicker3').datetimepicker({

						format : 'dd/MM/yyyy HH:mm:ss.SS',

						locale : 'en'

					});

					$('#datetimepicker3').datetimepicker().on(
							'dp.show',
							function() {

								$('#datetimepicker3').data("DateTimePicker")
										.maxDate(moment());

							});
*/
				});
