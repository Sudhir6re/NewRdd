$("#searchData").hide();
$("#printTbl").hide();





$("#searchDiv").hide();



/*$("#sevaarthId").blur(function(){
	setTimeout(function () {
	$("#searchDiv").hide();
}, 200);
});
*/


$("#searchDiv").hide();
$("#sevaarthId").keyup(function(){
	var sevaarthId = $("#sevaarthId").val();
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
					url : "../master/getEmpDtlsBySevaarthId/"
							+ sevaarthId,
					async : false,
					contentType : 'application/json',
					error : function(data) {
						console.log(data);
						$("#loaderMainNew").hide();
					},
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
											"<p class='empdata' empid='"+data[i].employeeid+"' empname='"+data[i].employeeName+"' empsevaathid='"+data[i].sevaarthId+"'   empdesgn='"+data[i].designName+"'>"
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
							//swal("No Employee Found");
							
							
						}
					}
				});
	}
	
	
});


$('body').on('click', '.empdata', function() {
	$("#sevaarthId").val($(this).attr("empname"));
	var sevaarthId=$(this).attr("empsevaathid");  
	
	document.getElementById("searchDiv").innerHTML = "";
	$("#searchDiv").hide();
});








$("#sevaarthId").blur(function(e){
	var sevaarthId=$("#sevaarthId").val();
	if(sevaarthId!=null){
		$("#loaderMainNew").show();
		$.ajax({
			type : "GET",
			url : "../master/findEmpBySevaarthIdorByName/"
					+ sevaarthId,
					 async: false,
			contentType : 'application/json',
			error : function(data) {
				$("#loaderMainNew").hide();
				// console.log(data);
			},
			success : function(data) {
				$("#loaderMainNew").hide();
				 console.log(data);
				// alert(data);
				var len = data.length;
				var j=1;
				if (len != 0) {
					for(var i=0;i<len;i++){
						  $('#searchData').show();
						  var txtSevaarthId=data[i].sevaarthId;
						  var empName=data[i].employeeFullName;
						  var desgName=data[i].designationName;
						  var doj=new Date(data[i].doj);
						  var dob=new Date(data[i].dob);
						  
						  var gpfAccno=data[i].pfdescription;
						  
						  doj= doj.getDay()+"/"+(Number(doj.getMonth())+1)+"/"+doj.getFullYear();
					      dob= dob.getDay()+"/"+(Number(dob.getMonth())+1)+"/"+dob.getFullYear();	  
						  
						  var mobileNo=data[i].mobileNo1;
						  var empstapanno=data[i].panNo;
						  var payCommissionCode=data[i].payCommissionCode;
						  
						  
						  if(payCommissionCode=="8"){
							  $("#payCommisionName").text("7 th Paycommision");
						  }else{
							  $("#payCommisionName").text("6 th Paycommision");
						  }
						  
						  $("#txtSevaarthId").text(txtSevaarthId);
						  $("#empName").text(empName);
						  $("#desgName").text(desgName);
						  $("#doj").text(doj);
						  $("#dob").text(dob);
						  $("#mobileNo").text(mobileNo);
						  $("#empstapanno").text(empstapanno);
						  $("#txtSevaarthId").text(txtSevaarthId);
						  $("#gpfAccno").text(gpfAccno);
						  
					}
				} else {
						alert("Please Enter Currect Sevaarth Id");
						
						$("#searchDiv").hide();
						  $('#tblNonGovDuesDeduct').dataTable().fnClearTable();
						  
						  
						  $("#sevaarthId").val("");
						  
						$("#search").prop("disabled",true);
				}
			}
		}); 
		
		
		setTimeout(function () {
			$("#searchDiv").hide();
		}, 200);
		
	}
	
});








$("#search").click(function(e){
			var sevaarthId=$("#sevaarthId").val();
			var year=$("#year").val();
			
			if(sevaarthId =="" || sevaarthId == undefined){
				swal("Please enter the sevaarth Id");
				e.preventDefault();
			}
			
			e.preventDefault();
			$.ajax({
				type : "GET",
				url : "../master/getGpfAccStatment/"
						+ sevaarthId+"/"+year,
						 async: false,
				contentType : 'application/json',
				error : function(data) {
					$("#loaderMainNew").hide();
					// console.log(data);
				},
				success : function(data) {
					$("#loaderMainNew").hide();
					 console.log(data);
					// alert(data);
					var len = data.length;
					var j=1;
					  var dataTable=$('#gpfTrnsactionsTbl').dataTable();
					  dataTable.fnClearTable();
					  
					if (len != 0) {
						for(var i=0;i<len;i++){
							//  $('#gpfTrnsactionsTbl').show();
							  var opr='';
							  var amount='';
							  if(data[i].crAmount!=null){
								  opr="Credit"; 
								  amount=data[i].crAmount;
							  }
							  else{
								  opr="Debit"; 
								  amount=data[i].drAmount;
							  }
							  
							  const monthNames = ["dummy","January", "February", "March", "April", "May", "June",
								  "July", "August", "September", "October", "November", "December"
								];

								
							  
							  
							  var d=new Date(data[i].created_date);
							  
							  var date1="";
							     
							  /*if(data[i].month=="1" || data[i].month=="2" ||  data[i].month=="2"){
								  date1=monthNames[data[i].month]+" "+Number(parseInt(data[i].year)+2000);
							  }else{
								  date1=monthNames[data[i].month]+" "+Number(parseInt(data[i].year)+1999);
							  }*/
							
							  date1=monthNames[data[i].month]+" "+Number(parseInt(data[i].year)+1999);
							  
							  var remark=data[i].remark;
							  var balance=data[i].balance;
							  
							  
							  
							  if(data[i].billType=="4"  && data[i].month=="4" &&  data[i].remark.includes("Opening Balance")){
								  $("#balance").text("₹ "+balance);
							  }
							  
							  
							  dataTable.fnAddData([j++,date1,amount,opr,balance,remark]);
						}
						
					
						$("#printTbl").show();
					} else {
						  dataTable.fnClearTable();
							swal("No Employee Found");
					}
				}
			}); 
			
			
		});
		
		
		