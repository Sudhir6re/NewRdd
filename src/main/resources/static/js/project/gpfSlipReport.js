$(".hideGPF").hide();
$("#searchDiv").hide();
$("#txtSearchName").blur(function() {
	$(".hideGPF").show();
});
$('.gpfemprpt').hide();

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
			sevaarthId = $("#txtSearchName").val();
			if (sevaarthId.length == 0) {
				document.getElementById("searchDiv").innerHTML = "";
				document.getElementById("searchDiv").style.border = "0px";
				return;
			}
			if (sevaarthId != '' && sevaarthId.length != 0) {
				$("#loaderMainNew").hide();
				$.ajax({
					type : "POST",
					url : "../clerklvl/getgpfSlipEmp/" + sevaarthId,
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
									+ "' desgName='"
									+ data[i].desgName
									+ "' pfDesc='"
									+ data[i].pfDesc
									+ "' gpfOpenBal='"
									+ data[i].gpfOpenBal 
									+ "' sevaarthId='"
									+ data[i].sevaarthId +"'>"
									+ data[i].empName + "-"
									+ data[i].sevaarthId + "</p>");
									

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

$('body').on(
		'click',
		'.empdata',
		function() {
			$("#txtSearchName").val($(this).attr("empname"));

			empname = $(this).attr("empname");
			desgName = $(this).attr("desgName");
			pfDesc = $(this).attr("pfDesc");
			gpfOpenBal = $(this).attr("gpfOpenBal");
			sevaarthId = $(this).attr("sevaarthId");
			

			$(".hideGPF").show();
			$('.gpfemprpt').show();
			
			$(".gpfemprpt").append(
					"<tr>" +
					// "<td>"+srNo+"</td>" +
					"<td>" + 1 + "</td>" + "<td>" + empname + "</td>" + "<td>" + desgName
							+ "</td>" + "<td>" + pfDesc + "</td>"
							+ "<td>" + gpfOpenBal + "</td>"
							+  "</tr>");


			document.getElementById("searchDiv").innerHTML = "";
			$("#searchDiv").hide();
		});
