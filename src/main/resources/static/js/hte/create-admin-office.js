$(document).ready(function() {


	if ($('#cmbDistrict').length) {
		$('#cmbDistrict').select2();
	}

	if ($('#cmbTaluka').length) {
		$('#cmbTaluka').select2();
	}

	if ($('#cmbDistOffice').length) {
		$('#cmbDistOffice').select2();
	}


	if ($('#dept').length) {
		$('#dept').select2();
	}


	var contextPath = $("#appRootPath").val();
	var DDOCode = $("#ddoCode").val();
	//var instituteId = $("#uniqeInstituteId").val();
	if (DDOCode != '' && DDOCode != undefined && DDOCode != "undefined") {
		swal('For DDO Code: '
			+ DDOCode
			+ ' Please note the details for future use. Additionally, the default password is: ifms123.');
	}


	//var dataTable= $("#ddoMapTable").dataTable();

	var dataTable = $('#ddoMapTable').dataTable({
		"columnDefs": [
			{ "targets": [4], "visible": false },
			{ "targets": [5], "visible": false }
		],
		"order": [[5, 'desc']]
	});


	$("#cmbDistrict").change(function() {
		var context = $("#appRootPath").val();
		var districtId = $("#cmbDistrict").val();

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/getAllTalukaByDistrictId/" + districtId,
			async: false,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				var len = data.length;
				$('#cmbTaluka').empty();
				$("#loaderMainNew").hide();
				$('#cmbTaluka').append($('<option  value="-1"></option>').text("Please Select"));
				if (len != 0) {
					for (var i = 0; i < len; i++) {
						$('#cmbTaluka').append('<option value="' + data[i].talukaId + '">' + data[i].talukaName + '</option>');
					}
				}

			}
		});
	});

	$("#cmbDistOffice").change(function() {
		var context = $("#appRootPath").val();
		var distOfcId = $("#cmbDistOffice").val();

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/findDeptByDistOfcCode/" + distOfcId,
			async: false,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				console.log(data);
				var len = data.length;
				$('#dept').empty();
				$("#loaderMainNew").hide();
				$('#dept').append($('<option  value="-1"></option>').text("Please Select"));
				if (len != 0) {
					$.each(data, function(index, row) {
						$('#dept').append('<option value="' + row[1] + '">' + row[0] + '</option>');
					});
				}

			}
		});
	});




	function loadLevel3DdoCode() {
		var context = $("#appRootPath").val();
		var distOfcId = $("#cmbDistOffice").val();
		var reptDdoCode = $("#txtRepDDOCode").val();

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/findLevel3DdoCode/" + distOfcId + "/" + reptDdoCode,
			async: false,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				console.log(data);
				$('#txtFinalDDOCode').val(data);
			}
		});
	}


	function isValidLevel2Ddo(ddo) {
		var context = $("#appRootPath").val();
		var distOfcId = $("#cmbDistOffice").val();
		var reptDdoCode = $("#txtRepDDOCode").val();
		var count = 0;

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/isValidLevel2Ddo/" + reptDdoCode,
			async: false,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				console.log(data);
				count = data
			}
		});
		return count;
	}


	$("#txtFinalDDOCode").blur(function() {
		var context = $("#appRootPath").val();
		var distOfcId = $("#cmbDistOffice").val();
		var finalDdoCode = $("#txtFinalDDOCode").val();
		var count = 0;

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/isValidLevel3Ddo/" + finalDdoCode,
			async: false,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				console.log(data);
				count = data
				if (count == 0) {
					swal("Enter Valid level 3 ddo code");
					$("#txtFinalDDOCode").val("");
				}
			}
		});
	});








	$("#btnFilter").click(function() {
		var context = $("#appRootPath").val();
		var districtId = $("#cmbDistrict").val();
		var talukaId = $("#cmbTaluka").val();
		var cmbAdminType = "-1";    //$("#cmbAdminType").val();

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/findZpRltDtls",
			contentType: 'application/json',
			data: JSON.stringify({ districtId: districtId, talukaId: talukaId, cmbAdminType: cmbAdminType }),
			dataType: 'json',
			async: false,
			contentType: 'application/json',
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			error: function(data) {
				console.log(data);
			},
			success: function(data) {
				$("#loaderMainNew").hide();
				var len = data.length;
				if (len != 0) {
					populateTable(data);
				} else {
					swal("No data found");
				}

			}
		});
	});


	function populateTable(data) {
		dataTable.fnClearTable();

		$.each(data, function(index, row) {

			var zpDdoCode = row[0];
			var reptdDoCode = row[1];
			var finalDdoCode = row[2];
			var spclDdoCode = row[3];
			var zpLevel = row[4];
			var status = row[5];
			var zpMapId = row[6];
			var createdDate = row[7];


			zpDdoCode = '<span id="' + zpDdoCode + '"><a href="#"  data-ddocode="' + zpDdoCode + '"  class="ddoCode"    data-srno="' + index + '"  >' + zpDdoCode + '</a></span>';
			reptdDoCode = '<span id="' + reptdDoCode + '"><a href="#"  data-ddocode="' + reptdDoCode + '"  class="ddoCode"    data-srno="' + index + '"  >' + reptdDoCode + '</a></span>';
			finalDdoCode = '<span id="' + finalDdoCode + '"><a href="#"  data-ddocode="' + finalDdoCode + '"  class="ddoCode"    data-srno="' + index + '"  >' + finalDdoCode + '</a></span>';
			spclDdoCode = '<span id="' + spclDdoCode + '"><a href="#"  data-ddocode="' + spclDdoCode + '"  class="ddoCode"    data-srno="' + index + '"  >' + spclDdoCode + '</a></span>';


			if (status == 0) {
				status = '<span class="label label-warning" >Pending</span>';
			} else if (status == 1) {
				status = '<span  class="label label-success" >Approved</span>';
			} else {
				status = '<span  class="label label-danger">Rejected</span>';
			}


			var formattedDate = dateToDMY(createdDate);

			dataTable.fnAddData(
				[
					zpDdoCode, reptdDoCode, finalDdoCode, zpLevel, formattedDate, zpMapId, status
				]);

		});
	}

	$("#txtDDODsgn").keyup(function() {
		var txtDDODsgn = $("#txtDDODsgn").val();
		var context = $("#appRootPath").val();

		if (txtDDODsgn != '' && txtDDODsgn != "0") {
			$("#loaderMainNew").show();
			$.ajax({
				type: "GET",
				url: context + "/mdc/findDesignation",
				async: true,
				data: { txtDDODsgn: txtDDODsgn },
				contentType: 'application/json',
				error: function(data) {
					console.log(data);
					// alert("error");
					$("#loaderMainNew").hide();
				},
				success: function(data) {
					console.log(data);
					$("#loaderMainNew").hide();
					var len = data.length;

					empFound = data.length;

					console.log(data);
					$("#loaderMainNew").hide();
					document
						.getElementById("searchDiv").innerHTML = "";
					for (var i = 0; i < data.length; i++) {

						$("#searchDiv").show();
						$("#searchDiv")
							.append(
								"<p><a class='empdata'    desginationId='" + data[i].desginationId + "'    empdesgn='" + data[i].desgination + "'>" + data[i].desgination + "</a></p>");
						$("#searchDiv")
							.css(
								"border:1px solid #A5ACB2;");
					}

					if (data.length == 0) {
						swal("Please enter valid post");
					}
				}
			});
		}
	});



	$('body').on('click', '.empdata', function() {
		$("#procceed").attr("disabled", false);
		var empdesgn = $(this).attr("empdesgn");
		var desginationId = $(this).attr("desginationId");
		$("#txtDDODsgn").val(empdesgn);
		$("#desginationId").val(desginationId);
		$("#searchDiv").hide();
	});


	$("#txtRepDDOCode").blur(function() {
		var context = $("#appRootPath").val();
		var ddoCode = $("#txtRepDDOCode").val();
		$("#loaderMainNew").show();

		var count = isValidLevel2Ddo(ddoCode);

		if (count == 0) {
			swal("Invalid Level2 ddo code  !!!");
			$("#txtRepDDOCode").val("");
		} else {
			$.ajax({
				url: context + "/mdc/getddoInfo",
				type: 'GET',
				data: { ddoCode: ddoCode },
				async: true,
				contentType: 'application/json',
				beforeSend: function() {
					$("#loaderMainNew").show();
				},
				complete: function(data) {
					$("#loaderMainNew").hide();
				},
				success: function(response) {
					loadLevel3DdoCode();


					$("#loaderMainNew").hide();
					if (response != '') {
						var dropdown = $('#cmbSubTreasury');
						dropdown.empty();
						dropdown.append($('<option  value="-1"></option>').text("Please Select"));
						$.each(response.trasuryDetails, function(index, value) {
							$("#txtTreasuryName").val(value[1]);
							$("#txtTreasuryCode").val(value[0]);
							dropdown.append($('<option  value="' + value[0] + '"></option>').text(value[1]));
						});

						$.each(response.subTreasuryList, function(index, value) {
							dropdown.append($('<option value="' + value[0] + '"></option>').text(value[1]));
						});
					} else {
						swal("No Data Found");
					}
				},
				error: function(error) {
					//alert('Error fetching DDO data:', error);
					swal("DDO Not Found")
				}
			});
		}

	});



	$("#cmbSubTreasury").change(function() {
		var cmbSubTreasury = $("#cmbSubTreasury").val();
		//var cmbAdminOffice=$("#cmbAdminOffice").val();
		//var cmbDistOffice=$("#cmbDistOffice").val();
		var cmbAdminOffice = $('#cmbDistOffice option:selected').attr('data');

		$("#loaderMainNew").show();
		$.ajax({
			type: "GET",
			url: contextPath + "/mdc/generateDDOCode/" + cmbSubTreasury + "/" + cmbAdminOffice,
			async: true,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(response) {
				$("#loaderMainNew").hide();
				console.log(response);
				var ddoCode = response.ddoCode;
				if (ddoCode != '') {
					$("#txtDDOCode").val(ddoCode);
				} else {
					$("#txtDDOCode").val("");
				}
			}
		});
	});






	$("#cmbAdminOffice").change(function() {
		var context = $("#appRootPath").val();
		var ofcId = $("#cmbAdminOffice").val();

		$("#loaderMainNew").show();
		$.ajax({
			type: "POST",
			url: context + "/mdc/fetchDistrictOfcByOffcId/" + ofcId,
			async: false,
			contentType: 'application/json',
			error: function(data) {
				console.log(data);
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				$("#loaderMainNew").hide();
				$('#cmbDistrict').empty();
				$('#cmbTaluka').append($('<option  value="-1"></option>').text("Please Select"));
				$('#cmbDistrict').append($('<option  value="-1"></option>').text("Please Select"));
				var len = data.length;
				if (len != 0) {
					$.each(data, function(index, row) {
						$('#cmbDistrict').append('<option value="' + row[1] + '">' + row[0] + '</option>');
					});
				}

			}
		});
	});




	$('body').on('click', '.hideDdoCode', function() {
		var field = $(this).attr("data-ddocode");
		var srno = $(this).attr("data-srno");
		hideDtls(field, srno);
	});




	$('body').on('click', '.ddoCode', function() {
		var field = $(this).attr("data-ddocode");
		var srno = $(this).attr("data-srno");

		var ddoCode = field;

		$("#loaderMainNew").show();
		$.ajax({
			type: "GET",
			url: contextPath + "/mdc/fetchDdoDetails/" + ddoCode,
			async: true,
			contentType: 'application/json',
			error: function(data) {
				// console.log(data);
				$("#loaderMainNew").hide();
			},
			success: function(data) {
				// console.log(data);
				// alert(data);
				var len = data.length;
				$("#loaderMainNew").hide();
				var temp = data;
				$
					.each(
						temp,
						function(index,
							value) {
							console
								.log(value[1]);

							setDDOdtls(value[0] + "," + value[1], field, srno);

						});
			}
		});
	});

	function setDDOdtls(myAjax, field, srno) {
		var divId = srno.toString() + field.toString();
		document.getElementById(divId).innerHTML = '<a   data-ddocode="' + field + '"  data-srno="' + srno + '"   class="hideDdoCode"  >' + field + '<br>' + myAjax + '</a>';
	}

	function hideDtls(field, srno) {

		var divId = srno.toString() + field.toString();
		document.getElementById(divId).innerHTML = '<a   data-ddocode="' + field + '"  data-srno="' + srno + '"   class="ddoCode"   >' + field + '</a>';
	}

	$("form[name='ZpDDOOffice']").validate({
		rules: {
			/*  cmbAdminOffice: {
				  required: true,
				 min: 1
			  },*/
			cmbDistOffice: {
				required: true,
				min: 1
			},
			radioFinalLevel: "required",
			txtRepDDOCode: {
				required: true,
				//maxlength: 10
			},
			txtFinalDDOCode: {
				required: true,
				//maxlength: 10
			},
			txtSpecialDDOCode: {
				required: false,
				//maxlength: 10
			},
			radioSalutation: "required",
			txtDDOName: {
				required: true,
				// maxlength: 100
			},
			radioGender: "required",
			txtTreasuryName: {
				maxlength: 50
			},
			txtTreasuryCode: {
				maxlength: 4
			},
			cmbSubTreasury: {
				required: true,
				min: 1
			},
			txtDDODsgn: {
				required: true,
				maxlength: 50
			},
			txtOfficeName: {
				required: true,
				maxlength: 500
			},
			txtDDOCode: {
				required: true,
				maxlength: 50
			},
			txtMobileNo: {
				required: true,
				maxlength: 10,
				minlength: 10
			},
			txtEmailId: {
				required: true,
				maxlength: 100,
				email: true
			}
		},
		messages: {
			/* cmbAdminOffice: {
				 required: "Please select Admin Office",
				 min: "Please select Admin Office"
			 },*/
			cmbDistOffice: {
				required: "Please select District Office",
				min: "Please select District Office"
			},
			radioFinalLevel: "Please select Final Level",
			txtRepDDOCode: {
				required: "Please enter DDO Code Level 2",
				maxlength: "DDO Code Level 2 should not exceed {0} characters"
			},
			txtFinalDDOCode: {
				required: "Please enter DDO Code Level 3",
				maxlength: "DDO Code Level 3 should not exceed {0} characters"
			},
			txtSpecialDDOCode: {
				required: "Please enter DDO Code Level 4",
				maxlength: "DDO Code Level 4 should not exceed {0} characters"
			},
			radioSalutation: "Please select DDO Name Salutation",
			txtDDOName: {
				required: "Please enter DDO Name",
				maxlength: "DDO Name should not exceed {0} characters"
			},
			radioGender: "Please select Gender",
			txtTreasuryName: {
				maxlength: "Treasury Name should not exceed {0} characters"
			},
			txtTreasuryCode: {
				maxlength: "Treasury Code should not exceed {0} characters"
			},
			cmbSubTreasury: {
				required: "Please select Sub Treasury Name",
				min: "Please select Sub Treasury Name"
			},
			txtDDODsgn: {
				required: "Please enter DDO Designation",
				maxlength: "DDO Designation should not exceed {0} characters"
			},
			txtOfficeName: {
				required: "Please enter Institute Name",
				maxlength: "Institute Name should not exceed {0} characters"
			},
			txtDDOCode: {
				required: "Please enter DDO Code",
				maxlength: "DDO Code should not exceed {0} characters"
			},
			txtMobileNo: {
				required: "Please enter Mobile Number",
				maxlength: "Mobile Number should not exceed {0} characters"
			},
			txtEmailId: {
				required: "Please enter Email Id",
				maxlength: "Email Id should not exceed {0} characters",
				email: "Please enter a valid Email Id"
			}
		},
		submitHandler: function(form) {
			form.submit();
			$("#loaderMainNew").show();
		}
	});
});





function addSalutationToName() {
	if (document.getElementById("radioSalutationShri").checked == true) {
		document.getElementById("txtDDOName").value = 'Shri.';
	}
	if (document.getElementById("radioSalutationSmt").checked == true) {
		document.getElementById("txtDDOName").value = 'Smt.';
	}
}
function validateDDOName() {
	var txt = document.getElementById("txtDDOName").value;
	var regex = /^[ A-Za-z.-]*$/;
	if (regex.test(txt)) {
	} else {
		//alert('Please Enter Valid DDO Name.\nOnly Characters are allowed in DDO Name.');
		document.getElementById("txtDDOName").value = '';
		if (document.getElementById("radioSalutationShri").checked == true) {
			//alert('Shree selected');
			document.getElementById("txtDDOName").value = 'Shri.';
		}
		if (document.getElementById("radioSalutationSmt").checked == true) {
			//alert('Smt Selected');
			document.getElementById("txtDDOName").value = 'Smt.';
		}
		return false;
	}
}

function validateMobileNo() {
	var mobileNo = document.getElementById("txtMobileNo").value;
	var regex = /^[0-9]*$/;
	if (regex.test(mobileNo)) {
	} else {
		//alert('Please enter only digit in Mobile No. field');
		document.getElementById("txtMobileNo").value = '';
		return false;
	}
	if (mobileNo.length != 10) {
		//alert('Please enter complete Mobile No.');
		document.getElementById("txtMobileNo").value = '';
		return false;
	}
	if (!(mobileNo.charAt(0) == 7 || mobileNo.charAt(0) == 8 || mobileNo
		.charAt(0) == 9)) {
		swal('Mobile number should not start with a digit between 0 and 6');
		document.getElementById("txtMobileNo").value = '';
		return false;
	}
}

function checkforHirechy() {
	if ($('#radioFinalLevel2').is(':checked')) {
		$('#txtFinalDDOCode').val("").prop('readonly', true);
	}
	if ($('#radioFinalLevel3').is(':checked')) {
		$('#txtFinalDDOCode').prop('readonly', false);
	}
}


$("#txtMobileNo").blur(function() //cmbOfficeCityClass
{
	/*swal(contextPath);*/
	var mobNo = $("#txtMobileNo").val();
	if (mobNo != '') {
		$.ajax({
			type: "GET",
			url:   "/mdc/validateMobNo/" + mobNo,
			async: true,
			contentType: 'application/json',
			error: function(data) {
				//console.log(data);
				$("#loaderMainNew").hide();
				
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
				
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
				
			},
			success: function(data) {
				if (data > 0) {

					swal('Entered mobile  number: '
						+ mobNo
						+ ' is already present in system. Please enter correct mobile number.');

					document.getElementById("txtMobileNo").value = "";
				}

			}
		});
	}
});




$("#txtEmailId").blur(function() //cmbOfficeCityClass
{
	var email = $("#txtEmailId").val();
	alert(email);
	if (email != '') {
		$.ajax({
			type: "GET",
			url:"/mdc/validateEmailAdd/" + email,
			async: true,
			contentType: 'application/json',
			error: function(data) {
				alert("error");
				$("#loaderMainNew").hide();
				
			},
			beforeSend: function() {
				$("#loaderMainNew").show();
				alert("beforeSend");
			},
			complete: function(data) {
				$("#loaderMainNew").hide();
				alert("complete");
			},
			success: function(data) {
				alert("success");
				if (data > 0) {
					swal('Entered Email: '
						+ email
						+ ' is already present in system. Please enter correct Email.');
					document.getElementById("txtEmail").value = "";
				}

			}
		});
	}
});






