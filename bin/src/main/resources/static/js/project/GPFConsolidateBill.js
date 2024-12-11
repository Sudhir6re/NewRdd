$(document).ready(function() {
			$(".hiddinTB").hide();
			$("#noOfBan").blur(function() {
				$(".hiddinTB").show();
				$("#noOfBan").prop("readOnly",true)
				//$('#empTable').dataTable().fnClearTable();
				$('#empTable').show();
				$('#empTable tbody').empty();
				for(var i=0;i<parseInt($("#noOfBan").val());i++){
				addRow(i);
				}
			});
		});
		
		function addRow(index){
			counter=parseInt(index)+1;
			var commonObj="gpfOpeningBalEntryModelList["+index;   // id="gpfOpeningBalEntryModelList0.dateRegPay" name="gpfOpeningBalEntryModelList[0].dateRegPay"
			var month=$("#monthId option:selected").text();
			var year=$("#yearId option:selected").text();
			var col1=counter;                                                 
			var col2='<input    name="gpfOpeningBalEntryModelList['+index+'].ddoCode"  class="form-control ddoCode">';
			var col3='<input  name="gpfOpeningBalEntryModelList['+index+'].sevaarthId" class="form-control sevaarthid">';
			var col4='<input  name="gpfOpeningBalEntryModelList['+index+'].empname" class="form-control empName">';
			var col5='<input  name="gpfOpeningBalEntryModelList['+index+'].appNo" class="form-control appId">';
			var col6='<input   name="gpfOpeningBalEntryModelList['+index+'].sancAmt" class="form-control sancAmount">';
			var col7='<input   name="gpfOpeningBalEntryModelList['+index+'].monthId"  value='+month+' class="form-control month">';
			var col8='<input   name="gpfOpeningBalEntryModelList['+index+'].yearId"   value='+year+'  class="form-control year">';
			
			$('#empTable').append("<tr><td>"+col1+"</td><td>"+col2+"</td><td>"+col3+"</td><td>"+col4+"</td><td>"+col5+"</td><td>"+col6+"</td><td>"+col7+"</td><td>"+col8+"</td></tr>");
			
			
		}	
		$("#empTable").on("blur", ".sevaarthid", function() {
		  var row = $(this).closest("tr");
		  var sevaarthId = row.find(".sevaarthid").val();
	var	empName=searchEmp(sevaarthId);
		  row.find(".empName").val(empName);
		});

		
		
		function searchEmp(sevaarthid){
			var empName="null";
$.ajax({
			type : "GET",
			url : "../master/searchEmpDtlsForGPF/"+sevaarthid,      
			async : false,
			contentType: "application/json",
	        dataType: "json",
			error : function(data) {
			 console.log(data);
			// alert("error");
			 
			 swal("Searched Employee is not GPF");
			},
			success : function(data) {
				 console.log(data);
				 empName=data[0];
			}
	    });
return empName;
}
		  $("form[name='GPFConsolidateBill']").validate({
		        // Specify validation rules
		        rules: {
		        	gpfAmt: "required",
		        	noOfbenif: "required",
		        	authNo: "required",
		        	remark: "required",
		        	voucherNo: "required",
		        	voucherDate: "required",
		        	applicationType:
		        		{
		        		required:true,
		        		min:1
		        		},
		        		schemeCode:
		        		{
		        			required:true,
		        			min:1
		        		},
		        		monthId:
		        		{
		        			required:true,
		        			min:1
		        		},
		        		yearId:
		        		{
		        			required:true,
		        			min:1
		        		},
		      
		        },
		        // Specify validation error messages
		        messages: {
		        	gpfAmt: "Please Enter GPF Amount",
		        	noOfbenif: "Please Enter No Of Beneficiary",
		        	authNo: "Please Enter Authorization No",
		        	remark: "Please Enter Remark",
		        	voucherNo: "Please Enter Voucher No",
		        	voucherDate: "Please Enter Voucher Date",
		        	applicationType: "Please Select Application Type",
		        	schemeCode: "Please Select Scheme Code",
		        	monthId: "Please Select Month",
		        	yearId: "Please Select Year",
		        },
		        submitHandler: function(form,e) {
		        	e.preventDefault();
		          form.submit();
		        }
		      });		
		
		
		