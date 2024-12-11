$("#sevaarth").blur(function(){
	setTimeout(function () {
	$("#searchDiv").hide();
}, 200);
});

var sevaarthId;
$("#searchDiv").hide();
$("#sevaarth").keyup(function(){
	//alert("test");
	var sevaarthId = $("#sevaarth").val();
	if (sevaarthId.length == 0) {
		document.getElementById("searchDiv").innerHTML = "";
		document.getElementById("searchDiv").style.border = "0px";
		return;
	}
	if (sevaarthId != '' && sevaarthId.length != 0) {
		$("#loaderMainNew").hide();
		$
				.ajax({
					type : "POST",
					url : "../level1/getempDetailsById/"
							+ sevaarthId,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					//headers:{"Access-Control-Allow-Origin"},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						document
								.getElementById("searchDiv").innerHTML = "";
						for (var i = 0; i < data.length; i++) {
							
							
							//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							//$("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv")
									.append(
											"<p class='empdata' empname='"+data[i].employeeName+"' empsevaathid='"+data[i].sevaarthId+"' officeName='"+data[i].officeName+"' " +
													"designation='"+data[i].designation+"'>"
													+ data[i].employeeName
													+ "-"
													+ data[i].sevaarthId
													+ "</p>");
							
							$("#sevaarthIdCopy").val(data[i].sevaarthId);
							
							$("#searchDiv")
									.css(
											"border:1px solid #A5ACB2;");
							
							$("#searchDiv").show();
						}
						
						
						if(data.length==0){
							swal("No Employee Found");
						}
					}
				});
	}
	});


$('body').on('click', '.empdata', function() {
	$("#sevaarth").val($(this).attr("empname"));
	
	var empname=$(this).attr("empname");
	var sevaarthId=$(this).attr("empsevaathid");  
	var officeName=$(this).attr("officeName");  
	var designation=$(this).attr("designation");  
	var isalready = isExcessPayRecAlreadyTaken(sevaarthId);
	if(isalready != undefined)
	
	{
		swal("The Employee have already applied for Excess Pay Recovery");
		$("#btnSave").prop("disabled",true);
		e.preventDefault();
	}
	$('#empname').val(empname);
	  $('#sevaarthId').val(sevaarthId);
	  $('#officeName').val(officeName);
	  $('#designName').val(designation);
	
		
		document.getElementById("searchDiv").innerHTML = "";
		$("#searchDiv").hide();
		$("#search_EmpOtherSearch").hide();
		
		$('#GPDArrTb4, .table-responsive').show();
		$
		.ajax({
			type : "POST",
			url : "../level1/getsavedExcessPayRecovery/"
					+ sevaarthId,
			async : false,
			contentType : 'application/json',
			error : function(data) {
				console.log(data);
				$("#loaderMainNew").hide();
			},
			//headers:{"Access-Control-Allow-Origin"},
			success : function(data) {
				console.log(data);
				$("#loaderMainNew").hide();
				document
						.getElementById("searchDiv").innerHTML = "";
				for (var i = 0; i < data.length; i++) {
					//$("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
					//$("#searchDiv").css("border:1px solid #A5ACB2;");
				
					
					var recoveryMonName=data[i].recoveryMonName;  
					var recoveryYear=data[i].recoveryYear; 
					var amt=data[i].amt;
					var remarks=data[i].remarks;  
					
					
						
					
					
					$('#searchTable').append(
							"<tr><td>" + recoveryMonName + "</td>"+"<td>" + recoveryYear + "</td>"+"<td>" + amt + "</td>"+"<td>" + remarks + "</td>"
									+ "</td></tr>");
					
					
					
			}
			}
		});
});


$("#preInstNo").change(function(e){
	 sevaarthId=$("#sevaarthId").val();
	var appId = $('option:selected', this).attr('data-id');
	var preInstNo =$("#preInstNo").val();
	var PriAmount =$("#PriAmount").val();
	 $("#preEMIAmount").val(Number(PriAmount)/Number(preInstNo));
	 var preEMIAmount =$("#preEMIAmount").val();
	// preEMIAmount.setTwoDecimalDigit(0);
	var roundval =  Math.round(preEMIAmount);
	 $("#preEMIAmount").val(roundval);
});

$("form[name='ExcessPayRecovery']").validate(
		{

			ignore : ".ignore",

			// Specify validation rules
			rules : {
				
				txtSearchName : "required",

				paybillMonth : {
					required : true,
					min : 1
				},
				paybillYear : {
					required : true,
					min : 1
				},
				recoveryMonth : {
					required : true,
					min : 1
				},
				recoveryYear : {
					required : true,
					min : 1
				},
				payItem : {
					required : true,
					min : 1
				},
				
				
				amt : "required",
				remarks : "required",

			},

			messages : {

				txtSearchName : " Please enter sevaarthId to fetch the below listed details",
				paybillMonth : " Please select paybill Month",
				paybillYear : " Please select paybill Year",
				recoveryMonth : " Please select Recovery Month",
				recoveryYear : "Please select Recovery Year",
				recAsPerGR : " Please enter Recovery as per GR",
				payItem : " Please select pay Item",
				amt : " Please enter Amount",
				remarks : " Please enter Remarks",

			},
			submitHandler : function(form) {
				form.submit();
			}
		});

function isExcessPayRecAlreadyTaken(sevaarthId){
	 $.ajax({
	      type: "POST",
	      url: "../level1/ExcessPayRecAlreadyTaken/"+sevaarthId,
	      async: false,
	      dataType : 'json',
	    // contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	    	 // alert(data);
	      },
	      success: function(data){
	    	  if(parseInt(data) >0 ) {
	    		$("#btnSave").prop("disabled",true);
	    		alert("The Employee have already applied for Excess Pay Recovery");
	    		e.preventDefault();
	    	  }
	    	  else
	    		  {
	    		  $("#btnSave").prop("disabled",false);
	    		  }
	    }
	 });
}