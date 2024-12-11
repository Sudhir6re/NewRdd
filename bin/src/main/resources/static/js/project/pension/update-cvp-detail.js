function newPensionCalculation(row) {

	if ($("#txtDateOfBirth").val() == ''
			|| $("#txtDateOfBirth").val() == undefined) {
		swal("Please select Date of Birth  !!!");
		return false;
	}
	if ($("#txtDateOfRetiremt").val() == ''
			|| $("#txtDateOfRetiremt").val() == undefined) {
		swal("Please select Date of Retirement  !!!");
		return false;
	}

	var DOR = $("#txtDateOfRetiremt").val(); //date--month--year
	var DOB = $("#txtDateOfBirth").val();

	var commutPer = $("#txtDoWantCommute").val();
	var DORArr = DOR.split("-");
	var DOBArr = DOB.split("-");
	var PensionAmount = "";
	var cvpAmount = "";
	var commutedPension = "";
	
	
	var payCom=$("#payCommissionCode").val();
	
	
	var maxbasicPension = ($("#totalPensionAmt").val()+nanTozero($("#revisePension").val()));
	
	
	commutedPension = Number(maxbasicPension) * Number(commutPer) / 100;

	document.getElementById("txtMonthAmt"+row).value = Math.ceil(commutedPension);

	var age = ageFromDOBAndDOR(DOB, DOR);

	$("#age").val(age);

	if (Number(age) > 81) {
		age = 81;
	}
	getCVPRateFromAge(age, payCom,row);

	cvpRate = document.getElementById("hdnCvpRate").value;

	document.getElementById("txtCVPAmt"+row).value = Math
			.ceil(Number(commutedPension) * 12 * Number(cvpRate));

}

function ageFromDOBAndDOR(birthDate, retirementDate) {
	var dateofNextBirthDay = $("#dateofNextBirthDay").val();

	if (dateofNextBirthDay == '') {
		var birthArrDate = birthDate.split("-");
		var bday = birthArrDate[2];
		var bmo = birthArrDate[1];
		var byr = parseInt(birthArrDate[0]);
		var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text();
		var age;

		var retirementArrDate = retirementDate.split("-");
		var rday = retirementArrDate[2];
		var rmo = retirementArrDate[1];
		var ryr = parseInt(retirementArrDate[0]);

		if (ClassOfPnsn == "Superannuation Pension") {
			if ((bday == 01 && bmo == 01)) {
				age = byr - 1;
			} else {
				age = byr;
			}
		} else {
			if (bmo < rmo || (bmo == rmo && bday < rday)) {
				age = byr;
			} else {
				age = byr + 1;
			}
		}

		ryr = Number(ryr) + 1;
		if (isNaN(ryr - age) == true) {
			return 0;
		} else if ((ryr - age) > 150 || (ryr - age) <= -1) {
			return "N.A.";
		} else {
			return (Number(ryr - age));
		}

	} else {

		var birthArrDate = birthDate.split("-");
		var bday = birthArrDate[2];
		var bmo = birthArrDate[1];
		var byr = parseInt(birthArrDate[0]);
		var ClassOfPnsn = $("#cmbClassOfPnsn option:selected").text();
		var age;

		var dateofNextBirthDay = $("#dateofNextBirthDay").val();

		retirementDate = dateofNextBirthDay;

		var retirementArrDate = retirementDate.split("-");
		var rday = retirementArrDate[2];
		var rmo = retirementArrDate[1];
		var ryr = parseInt(retirementArrDate[0]);

		if (ClassOfPnsn == "Superannuation Pension") {
			if ((bday == 01 && bmo == 01)) {
				age = byr - 1;
			} else {
				age = byr;
			}
		} else {
			if (bmo < rmo || (bmo == rmo && bday < rday)) {
				age = byr;
			} else {
				age = byr + 1;
			}
		}
		ryr = Number(ryr);
		if (isNaN(ryr - age) == true) {
			return 0;
		} else if ((ryr - age) > 150 || (ryr - age) <= -1) {
			return "N.A.";
		} else {
			return (Number(ryr - age));
		}
	}
}

function getCVPRateFromAge(Age, PayCommission,row) {
	var payCommsionCode = $("#payCommissionCode").val();
	var context = $("#context").val();
	var age = Age;
	$.ajax({
		type : "POST",
		url : context+"/pension/getCVPRateByPayCommsionCode/" + payCommsionCode + "/"
				+ age,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			console.log(data);
		},
		success : function(data) {
			console.log(data);
			document.getElementById("hdnCvpRate").value = data;
			document.getElementById("txtCvpRate"+row).value = data;
		}
	});
}
