$("#cmbDist").change(function(){
	var contextPath = $("#appRootPath").val();
var districtId=$("#cmbDist").val();
    $.ajax({
    url : contextPath+ "/ddoast/findDataByDistrict/"+districtId,
        type: 'GET',
    async : true,
    beforeSend : function(){
		$( "#loaderMainNew").show();
		},
	complete : function(data){
		$( "#loaderMainNew").hide();
	},	
contentType : 'application/json',
        success: function(response) {
        if(response!=''){
        var dropdown = $('#cmbTaluka');
                 dropdown.empty();
                 dropdown.append($('<option  value="-1"></option>').text("Please Select")); // Adjust the value index as needed
                 $.each(response.talukaList, function(index, value) {
                    $("#cmbTaluka").val(value[1]);
                    dropdown.append($('<option  value="'+value[0]+'"></option>').text(value[1])); // Adjust the value index as needed
                   
                 });
                 
                /* var dropdown1 = $('#cmbcity');
                 dropdown1.empty();
                 dropdown1.append($('<option  value="-1"></option>').text("Please Select"));
                 $.each(response.cityList, function(index, value) {
                     dropdown1.append($('<option value="'+value[0]+'"></option>').text(value[1])); // Adjust the value index as needed
                 });
				 */
        }else{
        alert("No Data Found");
        }
        },
        error: function(error) {
           alert('Error fetching DDO data:', error);
        }
    });
});

$("#btnedit").click(function(){
	var contextPath = $("#appRootPath").val();
	$('form *').prop('readonly', false); 
	$("#btnSave").removeClass("hide");
	$('select').removeClass('readonly')
	$("DDOInfo").attr("action",contextPath+"/ddoast/updateddoOfficeDetails");
});


$("#btnSave").click(function (e) {
    const errors = {
        adminDept: "Please enter administrative department !!!",
        hodDept: "Please enter HOD department !!!",
        name: "Please enter name !!!",
        designation: "Please select designation",
        withEffectFrmDate: "Please select date !!!",
        bankName: "Please select bank name !!!",
        branch: "Please select branch name !!!",
        ifscCode: "Please enter IFSC code !!!",
        bankAccNo: "Please enter bank account number !!!",
        nameoforginstt: "Please enter name of institution !!!",
        date: "Please enter date !!!",
        state: "Please select state name !!!",
        district: "Please select district name !!!",
        taluka: "Please select taluka name !!!",
        address: "Please enter Address !!!",
        pinCode: "Please enter pin code !!!",
        cityclass: "Please enter institution city class !!!",
    };

    const fieldsToValidate = [
        { id: "#txtAdminDept", error: errors.adminDept },
        { id: "#txtFieldDept", error: errors.hodDept },
        { id: "#txtDDOName", error: errors.name },
        { id: "#cmbDesignation", error: errors.designation, invalidValues: ["", "0"] },
        { id: "#txtWEFDate", error: errors.withEffectFrmDate },
        { id: "#cmbBankName", error: errors.bankName, invalidValues: ["", "0"] },
        { id: "#cmbBranchName", error: errors.branch, invalidValues: ["", "0"] },
        { id: "#txtIFSCCode", error: errors.ifscCode },
        { id: "#txtAccountNo", error: errors.bankAccNo },
        { id: "#txtNameOfOffice", error: errors.nameoforginstt },
        { id: "#cmbState", error: errors.state, invalidValues: ["", "0"] },
        { id: "#cmbDist", error: errors.district, invalidValues: ["", "0"] },
        { id: "#txtAddress1", error: errors.address },
        { id: "#txtPin", error: errors.pinCode },
        { id: "#cmbOfficeCityClass", error: errors.cityclass }
    ];

    let hasError = false;
    for (const field of fieldsToValidate) {
        const element = $(field.id);
        const value = element.val();

        if (!value || (field.invalidValues && field.invalidValues.includes(value))) {
            showError(element, field.error); 
			console.log(element);
            hasError = true;
        } else {
            hideError(element); 
        }
    }

    if (hasError) {
		e.preventDefault(); 
		return;
	}

    $("#loaderMainNew").show();
});





