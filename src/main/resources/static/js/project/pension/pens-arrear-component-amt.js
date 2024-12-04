/*$("#txtSearchName").blur(function() {
 setTimeout(function() {
 $("#searchDiv").hide();
 }, 200);
 });*/
var ppoNo;
$("#searchDiv").hide();
$("#empData").hide();

$("#txtSearchName").blur(
		function() {
			// alert("test");
			ppoNo = $("#txtSearchName").val();
			if (ppoNo.length == 0) {
				document.getElementById("searchDiv").innerHTML = "";
				document.getElementById("searchDiv").style.border = "0px";
				return;
			}
			if (ppoNo != '' && ppoNo.length != 0) {
				$("#loaderMainNew").hide();
				$.ajax({
					type : "POST",
					url : "../pension/getPensEmpArrearDtls/" + ppoNo,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
					// headers:{"Access-Control-Allow-Origin"},
					success : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
						document.getElementById("searchDiv").innerHTML = "";
						$("#empData").show();
						removeErrorClass($('#txtSearchName'));
					 	removeErrorClass($('#paybillYear'));
					 	removeErrorClass($('#paybillMonth'));
					 	removeErrorClass($('#netAmt'));
					 	removeErrorClass($('#fromDate'));
					 	removeErrorClass($('#toDate'));
					 	removeErrorClass($('#remark'));
						
						for (var i = 0; i < data.length; i++) {
							var empName=data[i].empName;
							$("#empName").val(empName);
									/*"<p class='empdata' empName='"+ data[i].empName+"'>"
									+ data[i].empName +
											 "</p>");*/


							$("#searchDiv").css("border:1px solid #A5ACB2;");

						}

						if (data.length == 0) {

							swal("No Employee Found");
							$(".Hiddenstretb").hide();
							$("#searchDiv").hide();
							$("#empData").hide();
						}
					}
				});
			}

		});

$("#paybillMonth")
		.change(
				function() {
					// alert("test");
					ppoNo = $("#txtSearchName").val();
					yearId = $("#paybillYear").val();
					monthId = $("#paybillMonth").val();
					fromDate = $("#fromDate").val();
					toDate = $("#toDate").val();
					remark = $("#remark").val();
					 $("#btnSave").removeAttr("disabled");

					if (ppoNo != '' && yearId != 0 && monthId != 0) {
						$("#loaderMainNew").hide();
						$
								.ajax({
									type : "POST",
									url : "../pension/fetchPensEmpArrDtls/"
											+ ppoNo + "/" + yearId + "/"
											+ monthId,
									async : false,
									contentType : 'application/json',
									error : function(data) {
										console.log(data);
										$("#loaderMainNew").hide();
									},
									// headers:{"Access-Control-Allow-Origin"},
									success : function(data) {
										console.log(data);
										$("#loaderMainNew").hide();
										document.getElementById("searchDiv").innerHTML = "";
										if (data.length != 0) {
											
											$("#btnSave").attr("disabled","disabled");

											swal(ppoNo+" Data Already Exists For Given Month and Year");
										}
									}
								});
					}

				});




$("#paybillYear")
.change(
		function() {
			// alert("test");
			ppoNo = $("#txtSearchName").val();
			yearId = $("#paybillYear").val();
			monthId = $("#paybillMonth").val();
			fromDate = $("#fromDate").val();
			toDate = $("#toDate").val();
			remark = $("#remark").val();
			 $("#btnSave").removeAttr("disabled");
			 
			 
			if (ppoNo != '' && yearId != 0 && monthId != 0) {
				$("#loaderMainNew").hide();
				$
						.ajax({
							type : "POST",
							url : "../pension/fetchPensEmpArrDtls/"
									+ ppoNo + "/" + yearId + "/"
									+ monthId,
							async : false,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
								$("#loaderMainNew").hide();
							},
							// headers:{"Access-Control-Allow-Origin"},
							success : function(data) {
								console.log(data);
								$("#loaderMainNew").hide();
								document.getElementById("searchDiv").innerHTML = "";
								
								
								if (data.length != 0) {
									
									$("#btnSave").attr("disabled","disabled");

									swal(ppoNo+" Data Already Exists For Given Month and Year");
									
								}
							}
						});
			}

		});
/*
$("#btnSave").click(function(e){
	
	
	txtSearchName = $("#txtSearchName").val();
	paybillYear = $("#paybillYear").val();
	paybillMonth = $("#paybillMonth").val();
	netAmt = $("#netAmt").val();
	 	 
	var ppoNo = document.getElementById('ppoNo').value;
 	 var fromDate = document.getElementById('fromDate').value;
 	 var toDate = document.getElementById('toDate').value; 
 	 
	 	removeErrorClass($('#txtSearchName'));
	 	removeErrorClass($('#paybillYear'));
	 	removeErrorClass($('#paybillMonth'));
	 	removeErrorClass($('#netAmt'));
	 	
	 	// alert("month and year  ="+month+''+year);
	 	//alert("ppoNo"+ppoNo);
	 	 
	 	if(txtSearchName == "")
	 		{
	 	 		addErrorClass($('#ppoNo'),"Please enter Ppo No !!!");
	 	 		e.preventDefault();
	 		}
	 	else{
	 		removeErrorClass($('#ppoNo'));
	 	}
	 	
	 	 if(paybillYear== 0){
	 		addErrorClass($('#paybillYear'),"Please select Year !!!");
	 		e.preventDefault();
	 	 }
	 	 
	 	 
	 	 if(paybillMonth== 0){
	 		addErrorClass($('#paybillMonth'),"Please select Month !!!");
	 		e.preventDefault();
	 	 }
	 	 if(netAmt== 0){
	 		 addErrorClass($('#netAmt'),"Please Enter Amount !!!");
	 		 e.preventDefault();
	 	 }
	 	 
	 	
});*/


$("form[name='PensBrokenPeriod']").validate({
	ignore: "",
    rules: {
    	ppoNo : {
			required : true
		},
		yearId : {
			required : true,
			min : 1
		},
		monthId : {
			required : true,
			min : 1
		},
    	
		netAmt : {
			required : true,
			min : 1
		},
		remark : {
			required : true
			
		},
		fromDate : {
			required : true
			
		},
		toDate : {
			required : true
			
		}
		
		
		
		
		
    },
    messages: {
    	ppoNo : {
			required : "Please Enter PPO Number"
		},
		yearId : {
			required : "Please select Year !!!",
			min : "Please select Year !!!",
		  		
    	},
    	monthId : {
			required : "Please select Month !!!",
			min : "Please select Month !!!",
			
		},
		netAmt : {
			required : "Please Enter Amount !!!",
			min : "Please Enter Amount !!!",
			
		},
		remark : {
			required : "Please Enter Remark !!!",
			
		},
		fromDate : {
			required : "Please Select From Date !!!",
			
		},
		toDate : {
			required : "Please Select To Date !!!",
			
		}
    }
  });


