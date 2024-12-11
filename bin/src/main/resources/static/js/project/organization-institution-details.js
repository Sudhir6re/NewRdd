$("#district")
	.change(
			function(e) {
				
				var district= $("#district").val();
				
				
				if (district != '') {
					$
							.ajax({
								type : "GET",
								url : "../level1/fetchTalukaByDistrict/"
										+ district,
							  
								async : true,
								contentType : 'application/json',
								error : function(data) {
									// console.log(data);
								},
								success : function(data) {
									// console.log(data);
									// alert(data);
									var len = data.length;
									if (len != 0) {
										// console.log(data);
										$(
												'#taluka')
												.empty();
										$(
												'#taluka')
												.append(
														"<option value='0'>Select</option>");
										var temp = data;
										var chk="";
										$
												.each(
														temp,
														function(
																index,
																value) {
															
															$(
																	'#taluka')
																	.append(
																			"<option  value="
																					+ value[0]
																					+  ">"
																					+ value[2]
																					+ "</option>");
														});
									} else {
										$(
												'#taluka')
												.empty();
										$(
												'#taluka')
												.append(
														"<option value='0'>Please Select</option>");
										swal("Record not found !!!");
									}
								}
							});
				}
				
				
				
			});
$("#state")
.change(
		function(e) {
			
			var district= $("#state").val();
			
			
			if (district != '') {
				$
						.ajax({
							type : "GET",
							url : "../level1/fetchDistrictByState/"
									+ district,
						  
							async : true,
							contentType : 'application/json',
							error : function(data) {
								// console.log(data);
							},
							success : function(data) {
								// console.log(data);
								// alert(data);
								var len = data.length;
								if (len != 0) {
									// console.log(data);
									$(
											'#district')
											.empty();
									$(
											'#district')
											.append(
													"<option value='0'>Select</option>");
									var temp = data;
									var chk="";
									$
											.each(
													temp,
													function(
															index,
															value) {
														
														$(
																'#district')
																.append(
																		"<option  value="
																				+ value[0]
																				+  ">"
																				+ value[2]
																				+ "</option>");
													});
								} else {
									$(
											'#district')
											.empty();
									$(
											'#district')
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}
			
			
			
		});





$("#btnSave").click(function(e){
	    var nameoforginsttError;
		var govtsanorderError;
		var dateError;
		var stateError;
		var districtError;
		var talukaError;
		var townError;
		var villageError;
		var addressError;
		var pinCodeError;
		var cityclassError;
		var institutionnumberError;
		var telno1Error;
		var telno2Error;
		var emailIdError;
		var faxnoError;
		var warning;
		if($('#language').val()=='en')
		{
		nameoforginsttError="Please enter name of institution !!!";
		govtsanorderError="Please enter government sanction order !!!";
		dateError="Please enter date !!!";
		stateError="Please select state name !!!";
		districtError="Please select district name !!!";
		talukaError="Please select taluka name !!!";
		townError="Please enter town/village !!!";
		villageError="Please enter village name !!!";
		addressError="Please enter address!!!";
		pinCodeError="Please enter pin code!!!";
		cityclassError="Please enter institution city class !!!";
        institutionnumberError="Please enter institution number !!!";
		telno1Error="please enter telphone number 1";
		telno2Error="please enter telphone number 2";
		emailIdError="Please enter email id !!!";
		faxnoError="Please enter faxno !!!";
		warning="warning !!!";
		}
	else
		{
		nameoforginsttError="कृपया संस्थेचे नाव प्रविष्ट करा !!!";
		govtsanorderError="कृपया शासकीय मान्यता आदेश द्या !!!";
		dateError="कृपया तारीख प्रविष्ट करा !!!";
		stateError="कृपया राज्याचे नाव निवडा !!!";
		districtError="कृपया जिल्ह्याचे नाव निवडा !!!";
		talukaError="कृपया तालुक्याचे नाव निवडा  !!!";
		townError="कृपया शहर / गाव प्रविष्ट करा !!!";
		villageError="कृपया गावचे नाव प्रविष्ट करा !!!";
		addressError="कृपया पत्ता प्रविष्ट करा !!!";
		pinCodeError="कृपया पिन कोड प्रविष्ट करा  !!!";
		cityclassError="कृपया संस्था शहर वर्ग प्रविष्ट करा !!!";
        institutionnumberError="कृपया संस्था क्रमांक प्रविष्ट करा !!!";
		telno1Error="कृपया दूरध्वनी क्रमांक 1 प्रविष्ट करा !!!";
		telno2Error="कृपया दूरध्वनी क्रमांक 2 प्रविष्ट करा";
		emailIdError="कृपया ईमेल आयडी प्रविष्ट करा !!!";
		faxnoError="कृपया फॅक्सनो प्रविष्ट करा !!!";
	    warning="चेतावणी !!!";
		}
		if($("#nameoforginstt").val() =="") {
			swal({
				title: warning,
				text: nameoforginsttError,
				icon: "warning",
				timer: 4000
			});
			e.preventDefault();
		}else if($("#govtsanorder").val() =="") {
				swal({
					title: warning,
					text: govtsanorderError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#date").val() =="") {
				swal({
					title: warning,
					text: dateError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#state").val() =="" || $("#state").val() =="0") {
				swal({
					title: warning,
					text: stateError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#district").val() ==""   || $("#district").val() =="0") {
				swal({
					title: warning,
					text: districtError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#taluka").val() =="" || $("#taluka").val() =="0" ) {
				swal({
					title: warning,
					text: talukaError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#town").val() =="") {
				swal({
					title: warning,
					text: townError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			/*}else if($("#village").val() =="") {
				swal({
					title: warning,
					text: villageError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();*/
			}else if($("#pinCode").val() =="") {
				swal({
					title: warning,
					text: pinCodeError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#cityclass").val() =="") {
				swal({
					title: warning,
					text: cityclassError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#institutionnumber").val() =="") {
				swal({
					title: warning,
					text: institutionnumberError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#telno1").val() =="") {
				swal({
					title: warning,
					text: telno1Error,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#emailId").val() =="") {
				swal({
					title: warning,
					text: emailIdError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}else if($("#faxno").val() =="") {
				swal({
					title: warning,
					text: faxnoError,
					icon: "warning",
					timer: 4000
				});
				e.preventDefault();
			}
			else {
				var OrganizationName= $("#nameoforginstt").val();
				var GovtSancOrder = $("#govtsanorder").val();
				var date = $("#date").val();
				var TelNo1 = $("#telno1").val();
				var TelNo2 = $("#telno2").val();
				var FaxNo = $("#faxno").val();
				var EmailId = $("#emailId").val();
				var CityClass = $("#cityclass").val();
				var Pincode = $("#pinCode").val();
				var institutionnumber = $("#institutionnumber").val();
				var Town = $("#town").val();
				var address = $("#address").val();
				var taluka = $("#taluka").val();
				
			
				
				//alert("checking data"+OrganizationName + " " + GovtSancOrder + " " +date+ " " +TelNo1+ " " +TelNo2+ " " +FaxNo+ " " +EmailId+ " "+CityClass+ " "+Pincode+ " "+Town )
				$.ajax({
					
					type : "GET",
					url : "../level1/saveOfficeMaster/"+OrganizationName+"/"+TelNo1+"/"+TelNo2+"/"+FaxNo+"/"+EmailId+"/"+GovtSancOrder+"/"+date+"/"+CityClass+"/"+Pincode+"/"+Town+"/"+address+"/"+institutionnumber+"/"+taluka,
							/*+OrganizationName+"/"+GovtSancOrder+"/"+Date+"/"+TelNo1+"/"+TelNo2+"/"+FaxNo+"/"+EmailId,*/
							
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    	 // alert(data);
			      },
			      success: function(data){
			    	  
			    	  console.log(data);
			    	  
			    	  
			    	  swal("Saved Successfuly !", {
			    	      icon: "success",
			    	  });
			    	  
			    	  $("#btnSave").prop("disabled","true");
			    	  //location.reload();
			    	  
			    	  window.location.href="../level1/organizationDetails";
			    	  
			    	
			    }
			 });
			
		} 
  });





$.ajax({
	type : "POST",
	contentType : "application/json",
	url : "../level1/getDdoRegMst",
	dataType : 'json',		
	error : function(data) {
		console.log(data);
	},
	success : function(data) {
		// Code to display the response.
		console.log(data);
		if(data.length>0)
			{
			 $("#taluka").val(data[0].taluka);
			 $("#taluka").prop("disabled",true);
			 $("#village").val(data[0].villageName);
			 $("#village").prop("disabled",true);
			 $("#pinCode").val(data[0].pincode);
			 $("#pinCode").prop("disabled",true);
			}
	}
});

	


	






$.ajax({
	type : "POST",
	contentType : "application/json",
	url : "../level1/getLstOfficeData",
//	data : JSON.stringify(developerData),
	dataType : 'json',				
	success : function(data) {
		// Code to display the response.
		console.log(data);
		if(data.length>0)
			{
			 $("#nameoforginstt").val(data[0].orginstname);
			 $("#nameoforginstt").prop("disabled",true);
	
			 $("#govtsanorder").val(data[0].sanctionorderno);
			 $("#govtsanorder").prop("disabled",true);
			 
			 
			 
			 var date = new Date(data[0].sanctionorderdate);
				var month=date.getMonth();
				var year=date.getFullYear();
				var day=date.getDate();
				if (month < 10){
					month = "0" + month;
				}
				if (day < 10)
				{
					day = "0" + 1;
				}
				
				var today1 = year + "-" + month + "-" + day;    
				
				$("#date").val(today1);
				 $("#date").prop("disabled",true);
			 
			 
			 
			 
		     $("#villageName").val(data[0].villageName);
			 $("#villageName").prop("disabled",true);

			 
			 
			 $("#pinCode").val(data[0].pincode);
			 $("#pinCode").prop("disabled",true);

			 
			 $("#telno1").val(data[0].telno1);
			 $("#telno1").prop("disabled",true);

			 
			 
			 $("#telno2").val(data[0].telno2);
			 $("#telno2").prop("disabled",true);

			 
			 $("#taluka").val(data[0].taluka);
			 $("#taluka").prop("disabled",true);
			 
			 
			 
			 $("#faxno").val(data[0].faxnumber);
			 $("#faxno").prop("disabled",true);

			 
			 $("#emailId").val(data[0].emailid);
			 $("#emailId").prop("disabled",true);
			 
			 
			 $("#town").val(data[0].town);
			 $("#town").prop("disabled",true);
			 
			 $("#address").val(data[0].address);
			 $("#address").prop("disabled",true);
			 
			 $("#institutionnumber").val(data[0].institutionNumber);
			 $("#institutionnumber").prop("disabled",true);
			 
			$("#btnSave").prop("disabled",true);
			
				
			}else{
			}
	}
});

	


	