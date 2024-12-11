
$("#bankId1")
.change(
		function() {
			var bankId1 = $("#bankId1").val();
			// alert("DDO CODE is "+departmentId);
			//  alert("payScale CODE is "+payScale);

			if (bankId1 != '') {
				$
						.ajax({
							type : "GET",
							url : "../fetchbankbrancbybankid/" + bankId1,
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
									$('#bankBranchId1').empty();
									$('#bankBranchId1')
											.append(
													"<option value='0'>Please Select</option>");
									var temp = data;
									$
											.each(
													temp,
													function(index,
															value) {
														console
																.log(value[1]);
														$(
																'#bankBranchId1')
																.append(
																		"<option value="
																				+ value[0]
																				+ ">"
																				+ value[1]
																				+ "</option>");
													});
								} else {
									$('#bankBranchId1').empty();
									$('#bankBranchId1')
											.append(
													"<option value='0'>Please Select</option>");
									swal("Record not found !!!");
								}
							}
						});
			}

		});

$("#bankBranchId1").change(function(){
	var branchId=$(this).val();
		$.ajax({
			type : "GET",
			url : "../getIfscCodeByBranch/"
					+ branchId,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
					$("#ifscCode").val(data[0].ifscCode);
			   }
			}
		});
     });

function bankaccountvalidenumber(inputtxt) {
	var compare = /[0-9]{9,16}/;
	if (inputtxt.value.match(compare)) {
		return true;
	} else {
		inputtxt.value = "";
		swal("Value must Contain 9 to 16 number!");
		return false;
	}
}

function validateBankAccNumUniqe(acc) {
	// alert('inside validateUIDUniqe');
	var accountNum = acc.value;
	//var employeeId = document.getElementById("employeeId").value;
	
	var context = $("#context").val();
	
		if (accountNum != '') {
			$
					.ajax({
						type : "GET",
						url :context+ "/master/validateAccountNum/" + accountNum,
						async : true,
						contentType : 'application/json',
						error : function(data) {
						},
						success : function(data) {
							var len = data.length;
							var checkFlag = data;
							if (checkFlag == 0) {
								$('#accNo').val(accountNum);
								status = true;
							} else if (checkFlag > 0) {

								swal('Entered Account number: '
										+ accountNum
										+ ' is already present in system. Please enter correct Account number.');

								document.getElementById("accNo").value = "";
								status = false;
							}
							return status;
						}
					});
		}
	}

$("form[name='editnomineeBankDtl']").validate(
		{

			ignore : ".ignore",

			// Specify validation rules
			rules : {
				
				accNo : "required",

				bankId : {
					required : true,
					min : 1
				},
				bankBranchId : {
					required : true,
					min : 1
				},
				

			},

			messages : {

				accNo : " Please Enter Account number",
				bankId : " Please Select Bank Name",
				bankBranchId : " Please Select Bank Branch Name",
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

$("select").select2("disable");
