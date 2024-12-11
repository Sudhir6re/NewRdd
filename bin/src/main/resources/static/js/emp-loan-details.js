
var status = $("#status").val();
if (status == "2") {
	$("#form1 input,#form1 select").attr("disabled", "disabled");
}

$("#searchDiv").hide();
$("#sevaarth").keyup(
		function() {
			var sevaarthId = $("#sevaarth").val();
			if (sevaarthId.length == 0) {
				document.getElementById("searchDiv").innerHTML = "";
				document.getElementById("searchDiv").style.border = "0px";
				return;
			}
			if (sevaarthId != '' && sevaarthId.length != 0) {
				$("#loaderMainNew").show();
				$.ajax({
					type : "POST",
					url : "../ddoast/getEmpInfoBySevaarthId/" + sevaarthId,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						document.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {

							$("#searchDiv").show();
							//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							//$("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv").append(
									"<li class='empdata' empsevaathid='"
											+ data[i].sevaarthId + "' gpfNo='"
											+ data[i].gpfNo + "'  "
											+ " empdesgn='"
											+ data[i].designName
											+ "' orgInstName='"
											+ data[i].officeName + "'>"
											+ data[i].employeeName + "-"
											+ data[i].sevaarthId + "</li>");
							$("#searchDiv").css("border:1px solid #A5ACB2;");

							///a.sevaarth_id,b.designation_name,c.ddo_office,a.pfacno	///a.sevaarth_id,b.designation_name,c.ddo_office,a.pfacno

						}

					}
				});
			}
		});

$('body').on(
		'click',
		'.empdata',
		function() {
			$("#sevaarthId").val($(this).attr("empsevaathid"));
			$("#employeeid").val($(this).attr("empid"));
			$("#designName").val($(this).attr("empdesgn"));
			$("#gpfNo").val($(this).attr("gpfNo"));
			$("#sevaarth").val($(this).attr("empname"));
			$("#orgInstName").val($(this).attr("orgInstName"));
			document.getElementById("searchDiv").innerHTML = "";

			/* for loan recovering */

			var sevaarthId = $("#sevaarthId").val();

			var length = 0
			$.ajax({
				type : "GET",
				url : "../level1/getGpfAdvAppNo/" + sevaarthId,
				async : false,
				contentType : 'application/json',
				error : function(data) {
					console.log(data);
					$("#loaderMainNew").hide();
				},
				success : function(data) {
					console.log(data);
					$("#appNo").empty();
					$("#appNo").append(
							'<option value="0">Please Select</option>');
					$("#loaderMainNew").hide();

					for (var i = 0; i < data.length; i++) {
						$("#appNo").append(
								'<option  data-id="'
										+ data[i].loanemployeeadvid
										+ '"  value="' + data[i].loanaccountno
										+ '">' + data[i].loanaccountno
										+ '</option>');
					}
				}
			});
			$("#searchDiv").hide();

		});

$("#btnSave").click(function(e) {
	var advType = $("#loanAdvName").val();
	var adString = advType.split('-');
	var advId = adString[0];
	var sevaartrhId = $("#sevaarthId").val();
	var empId = $("#employeeid").val();
	$.ajax({
		type : "POST",
		url : "../level1/checkloanAlreadyTaken/" + empId + "/" + advId,
		async : false,
		dataType : 'json',
		// contentType:'application/json',
		error : function(data) {
			console.log(data);
			// alert(data);
		},
		success : function(data) {
			if (parseInt(data) > 0) {
				$("#btnSave").prop("disabled", true);
				e.preventDefault();
			} else {
				$("#btnSave").prop("disabled", false);
			}
		}
	});
});

$("form[name='form1']").validate({
	// Specify validation rules
	rules : {
		loanAdvName : {
			"required" : true,
		},
		loanDate : {
			"required" : true,
		},
		loansancorderdate : {
			"required" : true,
		},
		loanprinamt : {
			"required" : true,
		},

	},
	// Specify validation error messages
	messages : {
		stateNameEn : "Please enter State Name in english",
		stateNameMr : "Please enter State Name in Marathi",
		loanAdvName : "Please select the Loan Name",
	},
	// Make sure the form is submitted to the destination defined
	// in the "action" attribute of the form when valid
	submitHandler : function(form) {
		var flag = true;
		if ($('#loanAdvName').val() == "0") {
			flag = false;
			addErrorClass($('#loanAdvName'), "Please select the Loan Name");
		} else {
			removeErrorClass($('#loanAdvName'));

		}

		if (flag == false) {

		} else {
			form.submit();
		}

	}
});

$("#appNo")
		.change(
				function(e) {
					$("#loaderMainNew").show();
					var sevaarthId = $("#sevaarthId").val();
					var appId = $('option:selected', this).attr('data-id');

					//	var appId=$(this).val();

					if (sevaarthId != '' && appId != '') {
						$
								.ajax({
									type : "GET",
									url : "../level1/getGpfAppTrnDtlsByAppId/"
											+ sevaarthId + "/" + appId,
									async : false,
									contentType : 'application/json',
									error : function(data) {
										console.log(data);
										$("#loaderMainNew").hide();
									},
									success : function(data) {
										console.log(data);
										$("#loaderMainNew").hide();
										for (var i = 0; i < data.length; i++) {

											// var principalAmt=data[i].id;
											var preEMIAmount = data[i].loanprinemiamt;
											var principalAmt = data[i].loanprinamt;
											var sanctionDate = new Date(
													data[i].loansancorderdate);
											var voucherDate = new Date(
													data[i].voucherdate);
											var voucherno = data[i].voucherno;
											var totalRecoveredInst = data[i].totalRecoveredInst;
											var loanemployeeadvid = data[i].loanemployeeadvid;
											var loanprininstno = data[i].loanprininstno;

											$("#loanEmpAdvId").val(
													loanemployeeadvid);

											var day = ("0" + sanctionDate
													.getDate()).slice(-2);
											var month = ("0" + (sanctionDate
													.getMonth() + 1)).slice(-2);

											var today = sanctionDate
													.getFullYear()
													+ "-"
													+ (month)
													+ "-"
													+ (day);

											var day1 = ("0" + voucherDate
													.getDate()).slice(-2);
											var month1 = ("0" + (voucherDate
													.getMonth() + 1)).slice(-2);

											var today1 = voucherDate
													.getFullYear()
													+ "-"
													+ (month1)
													+ "-"
													+ (day1);

											$("#PriAmount").val(principalAmt);
											$("#preEMIAmount")
													.val(preEMIAmount);
											$("#sectionOrderDate").val(today);
											$("#voucherNo").val(voucherno);
											$("#voucherdate").val(today1);
											$("#preInstNo").val(loanprininstno);
											$("#priRecovInsta").val(
													totalRecoveredInst);

										}
									}
								});
					}

				});

$("#preInstNo").change(function(e) {
	var sevaarthId = $("#sevaarthId").val();
	var appId = $('option:selected', this).attr('data-id');
	var preInstNo = $("#preInstNo").val();
	var PriAmount = $("#PriAmount").val();
	$("#preEMIAmount").val(Number(PriAmount) / Number(preInstNo));
	var preEMIAmount = $("#preEMIAmount").val();
	preEMIAmount.toFixed(2);
	$("#preEMIAmount").val(preEMIAmount);
	//	var appId=$(this).val();

});
