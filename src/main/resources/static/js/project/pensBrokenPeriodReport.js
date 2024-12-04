
		$(".Hiddenstretb").hide();
			$("#searchDiv").hide();
			$("#txtSearchName").blur(function() {
				$(".Hiddenstretb").show();
			});
$('.stretbmain,.table-responsive').hide();

$("#txtSearchName").blur(function() {
	setTimeout(function() {
		$("#searchDiv").hide();
	}, 200);
});
var ppoNo;
var dateofDeath;
$("#searchDiv").hide();
$("#btnModal").hide();
// $("#btnProceed").hide();





$("#txtSearchName").keyup(
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
					url : "../pension/getPensBrokenReportEmp/" + ppoNo,
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
						for (var i = 0; i < data.length; i++) {
							// $("#searchDiv").append(data[i].employeeName+"-"+data[i].sevaarthId);
							// $("#searchDiv").css("border:1px solid #A5ACB2;");
							$("#searchDiv").append(
									"<p class='empdata' empname='"
											+ data[i].empName
											+ "' ppoNo='"
											+ data[i].ppoNo
											+ "' dateofDeath='"
											+ data[i].dateofDeath + "'>"
											+ data[i].empName + "-"
											+ data[i].ppoNo + "</p>");

							$("#ppoNo").val(data[i].ppoNo);
							$("#dateofDeath").val(dateToYMD(data[i].dateofDeath));
							$("#penEmpName").text(data[i].empName);

							$("#searchDiv").css("border:1px solid #A5ACB2;");

							$("#searchDiv").show();
						}

						if (data.length == 0) {
							
							swal("No Employee Found");
							$(".Hiddenstretb").hide();
							$("#searchDiv").hide();
						}
					}
				});
			}

		});









$('body')
		.on(
				'click',
				'.empdata',
				function() {
					$("#txtSearchName").val($(this).attr("empname"));

					empNam = $(this).attr("empname");
					ppoNo = $(this).attr("ppoNo");
					dateofDeath = $(this).attr("dateofDeath");
					$.ajax({
						type : "POST",
						url : "../pension/getPensBrokenReportEmpdtls/" + ppoNo,
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
							var totalbasic  = 0;
							var totalda  = 0;
							var totalcomm  = 0;
							var totaldue  = 0;
							
							
							var totalDrawbasic  = 0;
							var totalDrawda  = 0;
							var totalDrawcomm  = 0;
							var totalDraw  = 0;
							
							var diffTotal1=0;
							
							
							
							for (var i = 0; i < data.length; i++) {
								var fromdate =  dateToYMD(data[i].fromDate);
								var todate =  dateToYMD(data[i].toDate);
								var daamt = data[i].da ;
								var basic = data[i].basicPay;
								var commutation = data[i].commutation;
								var total = data[i].total;
								
								totalbasic =data[i].basicPay  +  totalbasic ;
								totalda =data[i].da  +  totalda ;
								totalcomm =data[i].commutation  +  totalcomm ;
								totaldue =data[i].total  +  totaldue ;
								
								
								
								var drawBasic=data[i].drawnbasicPay;
								var drawnDa=data[i].drawnDa;
								var drawnCommutation=data[i].drawnCommutation;
								var drawTotal=data[i].drawTotal;
								var diffTotal=data[i].drawTotal;
								
							
								 totalDrawbasic =Number(data[i].drawBasic)  +  Number(totalDrawbasic) ;
							      totalDrawda =Number(data[i].drawnDa)  +  Number(totalDrawda) ;
								 totalDrawcomm =Number(data[i].drawnCommutation)  +  Number(totalDrawcomm) ;
								 totalDraw = Number(data[i].drawTotal) +  Number(totalDraw) ;
								
								 diffTotal1=diffTotal1+diffTotal ;
								 
								$(".due").append("<tr>" + 
									       // "<td>"+srNo+"</td>" +
								        "<td>"+1+"</td>" +
								        "<td>"+fromdate+" to "+todate+"</td>" +
								        "<td>"+31+"</td>" +
								        "<td>"+nanTozero(basic)+"</td>" +
								        "<td>"+nanTozero(daamt)+"</td>" +
								        "<td>"+nanTozero(commutation)+"</td>" +
								        "<td>"+nanTozero(total)+"</td>" +
								        "<td>"+nanTozero(drawBasic)+"</td>" +
								        "<td>"+nanTozero(drawnDa)+"</td>" +
								        "<td>"+nanTozero(drawnCommutation)+"</td>" +
								        "<td>"+nanTozero(drawTotal)+"</td>" +
								        "<td>"+nanTozero((Number(total)-Number(drawTotal)))+"</td>" +
								        "</tr>"); 
							}
							
							$(".totalDiv").append(
									"<th>Total</th>" +
									"<th></th>" +
									"<th></th>" +
									"<th>"+nanTozero(totalbasic)+"</th>" +
									"<th>"+nanTozero(totalda)+"</th>" +
									"<th>"+nanTozero(totalcomm)+"</th>" +
									"<th>"+nanTozero(totaldue)+"</th>" +
									"<th>"+nanTozero(totalDrawbasic)+"</th>" +
									"<th>"+nanTozero(totalDrawda)+"</th>" +
									"<th>"+nanTozero(totalDrawcomm)+"</th>" +
									"<th>"+nanTozero(totalDraw)+"</th>" +
									"<th>"+(Number(totaldue)-Number(totalDraw))+"</th>"
							);

							if (data.length == 0) {
								swal("No Employee Found");
								$("#tblshowdiffdue").hide();
							}
						}
					});

						$('.stretbmain, .table-responsive').show();
						
						
						$('#txtEmployeeName').val(empNam);
						$('#txtPPOno').val(ppoNo);

						document.getElementById("searchDiv").innerHTML = "";
						$("#searchDiv").hide();
				});




   

