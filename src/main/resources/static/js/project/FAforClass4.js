function findAttachDettachEmp() {
	// document.getElementById("myForm").submit();
	// alert("findAttachDettachEmp method executed");
	var billgroupid = document.getElementById("schemebillGroupId");
	if (billgroupid.value === "0") {
		swal('Please select Bill Description');
		return false;
	}
	document.getElementById("status").value = "SEARCH";
	$("#myForm").submit();
}

function addLoanDataToTable() {
	var totalRow = $('#employeeLst1>tbody>tr').length;

	$("#tblempLoan>tbody").empty();

	var i = 0;
	$("#employeeLst1 tbody tr")
			.each(
					function() {

						// var chk =row.find(".allowCheckBox").prop("checked");

						var row = $(this).closest("tr");

						var employeeId = row.find(".employeeId").val();

						var sevaarthId = row.find(".sevaarthId").val();

						var employeeFullName = row.find(".empName").val();

						var checkBox = '<input type="checkbox"  name="lstFestivalAdvanceModel1['
								+ i + '].isChecked"  	class="allowCheckBox"/>';

						employeeId = '<input type="hidden" class="employeeId" name="lstFestivalAdvanceModel['
								+ i + '].empid" value="' + employeeId + '" />';
						sevaarthId = '<input   type="hidden" class="sevaarthId" name="lstFestivalAdvanceModel['
								+ i
								+ '].sevaarthId" value="'
								+ sevaarthId
								+ '" />';

						var empName = '<input  type="hidden" class="empName"  value="'
								+ employeeFullName + '" />';

						var loanName = "FA";

						var loanDate = $("#loanDate").val();
						loanDate = '<input   type="date" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].loanDate" value="'
								+ loanDate
								+ '"  readonly/>';

						var loanACNo = $("#loanACNo").val();
						loanACNo = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].accountno" value="'
								+ loanACNo
								+ '"  readonly/>';

						var principalAmt = $("#principalAmt").val();
						principalAmt = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].principleAmt" value="'
								+ principalAmt
								+ '"  readonly/>';

						var principalInstNo = $("#principalInstNo").val();
						principalInstNo = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].principleInstalment" value="'
								+ principalInstNo + '"  readonly/>';

						var principleEMIAmt = $("#loanPrinEmiAmt").val();
						principleEMIAmt = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].principleEMIAmt" value="'
								+ principleEMIAmt + '"  readonly/>';

						var loanSancOrderNo = $("#loanSancOrderNo").val();
						loanSancOrderNo = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].sanctOrderNo" value="'
								+ loanSancOrderNo + '"  readonly/>';

						var loanSancOrderDate = $("#loanSancOrderDate").val();
						loanSancOrderDate = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].sanctOrderDate" value="'
								+ loanSancOrderDate + '"  readonly/>';

						var loanVoucherNo = $("#loanVoucherNo").val();
						loanVoucherNo = '<input   type="text" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].voucherNo" value="'
								+ loanVoucherNo
								+ '"  readonly/>';

						var loanVoucherDate = $("#loanVoucherDate").val();
						loanVoucherDate = '<input   type="date" class="loanACNo" name="lstFestivalAdvanceModel['
								+ i
								+ '].voucherDate" value="'
								+ loanVoucherDate + '"  readonly/>';

						var loanDate = $("#loanDate").val();

						$("#tblempLoan>tbody").append(
								"<tr>" + "<td class='empname'>"
										+ employeeFullName + "</td>" + "<td>"
										+ loanName + "</td>" + "<td>"
										+ principalAmt + "</td>" + "<td>"
										+ principalInstNo + "</td>" + "<td>"
										+ principleEMIAmt + "</td>" + "<td>"
										+ loanSancOrderNo + "</td>" + "<td>"
										+ loanDate + "</td>" + "<td>"
										+ loanSancOrderDate + "</td>" + "<td>"
										+ loanVoucherNo + "</td>" + "<td>"
										+ loanVoucherDate + employeeId
										+ sevaarthId + "</td>" + "</tr>");
						i++;

					});

}

$("#employeeLst")
		.on(
				"change",
				".allowCheckBox",
				function() {
					var row = $(this).closest("tr");

					var chk = row.find(".allowCheckBox").prop("checked");

					var tblName = "employeeLst1";

					// var dataTable= $('#employeeLst1').dataTable();
					// dataTable.fnClearTable();
					var i = parseInt($('#employeeLst1>tbody>tr').length - 1);

					if (chk == true) {
						var employeeId = row.find(".employeeId").val();

						var sevaarthId = row.find(".sevaarthId").val();

						var employeeFullName = row.find(".empName").val();

						var checkBox = '<input type="checkbox"  	class="allowCheckBox"/>';

						employeeId = '<input type="hidden" class="employeeId" value="'
								+ employeeId + '" />';
						sevaarthId = '<input   type="hidden" class="sevaarthId" value="'
								+ sevaarthId + '" />';

						var empName = '<input  type="hidden" class="empName"  value="'
								+ employeeFullName + '" />';

						checkBox = checkBox + sevaarthId + employeeId + empName;

						// dataTable.dataTable().fnAddData([checkBox,employeeFullName]);

						addRowToTbl(tblName, checkBox, employeeFullName);

						row.remove();

					}
				});

$("#employeeLst1")
		.on(
				"change",
				".allowCheckBox",
				function() {
					var row = $(this).closest("tr");

					var chk = row.find(".allowCheckBox").prop("checked");

					var tblName = "employeeLst";

					// var dataTable= $('#employeeLst1').dataTable();
					// dataTable.fnClearTable();
					var i = parseInt($('#employeeLst>tbody>tr').length - 1);

					if (chk == true) {
						var employeeId = row.find(".employeeId").val();

						var sevaarthId = row.find(".sevaarthId").val();

						var employeeFullName = row.find(".empName").val();

						var checkBox = '<input type="checkbox"   	class="allowCheckBox"/>';

						employeeId = '<input type="hidden" class="employeeId"  value="'
								+ employeeId + '" />';
						sevaarthId = '<input   type="hidden" class="sevaarthId"  value="'
								+ sevaarthId + '" />';

						var empName = '<input  type="hidden" class="empName"  value="'
								+ employeeFullName + '" />';

						checkBox = checkBox + sevaarthId + employeeId + empName;

						// dataTable.dataTable().fnAddData([checkBox,employeeFullName]);

						addRowToTbl(tblName, checkBox, employeeFullName);

						row.remove();

					}
				});

$(".emplst").hide();
$('#schemeBillGroupId')
		.change(
				function(e) {
					var schemeBillGrpId = $(this).val();
					var context = $("#context").val();
					$
							.ajax({
								type : "GET",
								url : context
										+ "/level1/getListEmpByBillGroup/"
										+ schemeBillGrpId,
								async : false,
								contentType : 'application/json',
								error : function(data) {
									console.log(data);
									// alert("error is"+data);
								},
								beforeSend : function() {
									$("#loaderMainNew").show();
								},
								complete : function() {
									$("#loaderMainNew").hide();
								},
								success : function(data) {
									console.log(data);
									// alert("data");
									flag = data.length;
									$("#loaderMainNew").hide();
									if (data.length > 0) {

										// dataTable.api().columns([2,3,4]).visible(false);

										for (var i = 0; i < data.length; i++) {
											var sevaarthId = data[i].sevaarthId;

											var isCheck = false;

											var checkBox = '<input type="checkbox"  	class="allowCheckBox"/>';

											var employeeFullName = data[i].employeeFullName;

											var employeeId = data[i].employeeId;
											// var employeeId="<label
											// style='display:none;'>"+data[i].employeeId+"</label>";

											// var hiddenCheckbox='<input
											// id="checkBox'+i+'"
											// value="'+isCheck+'"
											// class="checkbox" type="hidden"
											// name="lstFestivalAdvanceModel[' +
											// i + '].checkBox" '+empMapped+'
											// />';

											employeeId = '<input type="hidden" class="employeeId"  value="'
													+ employeeId + '" />';
											sevaarthId = '<input   type="hidden" class="sevaarthId"  value="'
													+ sevaarthId + '" />';

											var empName = '<input  type="hidden" class="empName"  value="'
													+ employeeFullName + '" />';

											checkBox = checkBox + sevaarthId
													+ employeeId + empName;

											var tblName = "employeeLst";

											addRowToTbl(tblName, checkBox,
													employeeFullName);
										}
									} else {
										swal("No employee found for this scheme bill group !!!");
										$("#employeeLst").hide();
									}
								}
							});
					$(".emplst").fadeIn();
				});

function addRowToTbl(tblName, col1, cole2) {
	$("#" + tblName + ">tbody").append(
			"<tr><td>" + col1 + "</td><td>" + cole2 + "</td></tr>");
}

$("form[name='frmMulitpleLoans']").validate({

	ignore : ".ignore",

	// Specify validation rules
	rules : {

		schemeBillGroupId : {
			required : true,
			min : 1,
		},
		loanDate : "required",
		principleAmt : "required",
		principalInstNo : "required",
		loanPrinEmiAmt : "required",
		loanSancOrderNo : "required",
		loanSancOrderDate : "required",
		loanVoucherNo : "required",
		loanVoucherDate : "required",

	},

	messages : {
		schemeBillGroupId : "Please select Bill No.",
		loanDate : "Please enter loan start Date",
		principleAmt : " Please enter Principal Amount",
		principalInstNo : " Please enter Principal EMI No",
		loanPrinEmiAmt : " Please enter Principal EMI Amount",
		loanSancOrderNo : " Please enter Sanctioned Order No",
		loanSancOrderDate : " Please enter Sanctioned Order Date",
		loanVoucherNo : " Please enter Voucher Number",
		loanVoucherDate : " Please enter Voucher Date",
	},

	// Make sure the form is submitted to the destination defined
	// in the "action" attribute of the form when valid
	submitHandler : function(form) {
		forwardToDDo();
		// form.submit();
	}
});
