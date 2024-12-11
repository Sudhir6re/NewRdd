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
                 
                 var dropdown1 = $('#cmbcity');
                 dropdown1.empty();
                 dropdown1.append($('<option  value="-1"></option>').text("Please Select"));
                 $.each(response.cityList, function(index, value) {
                     dropdown1.append($('<option value="'+value[0]+'"></option>').text(value[1])); // Adjust the value index as needed
                 });
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

