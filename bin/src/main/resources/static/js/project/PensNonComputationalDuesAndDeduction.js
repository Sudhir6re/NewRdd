jQuery(document).ready(function($) {
	var varMessage = $("#message").val();
	if(varMessage != "" && varMessage != undefined) {
		swal(''+varMessage, {
  	      icon: "success",
  	  });
	}
});

$(document).ready(function(){
	$('#tblNonGovDuesDeduct').hide();
    $("#search").click(function(e){
    	e.preventDefault();
	  var componentVal=$("#componentId").val();
	  var ddoCode=$('#logUser').text();
	  var ppoNo=$("#ppoNo").val();
	  //var deptallowcode=$("#deptallowcode").val();
	  //var deptcode=$("#deptcode").val();
	 // alert("ppoNo 1********"+ppoNo);
	//alert("deptcode********"+deptcode);
	  var res = componentVal.split(")");    //split two data from single string
	  var allowDeducComponentId=res[1];
	  $.ajax({
		  url: "../master/getPensEmployeeAgainstId/"+allowDeducComponentId+'/'+ddoCode+'/'+ppoNo,
		  cache: false,
		  success: function(data){
			  
			  
			  $('#tblNonGovDuesDeduct').dataTable().fnClearTable();
			  $('#tblNonGovDuesDeduct').show();
			//  $('#tblNonComputationDeduction').dataTable().fnAddData(data);		
			  
			 

			  //$('#tblNonGovDuesDeduct').dataTable().column(6).visible( false );
			  var s="<input type='text' disabled='disabled' class='existingAmount'>";
			  var NetAmount="<input type='text' class='netAmount number' >";
			  var difference="<input type='text' disabled='disabled' class='difference' >";
			  
			  
			  

			  $.each(data,function(index,value) 
					  { 
				  
				 console.log(value);
//				  
				 var employeeId="<input type='text'   id='employeeId'  style='display:none' value='"+value[4]+"' th:field = '*{employeeId[__${"+index+"}__]}'/>";
				  var empName="<input type='text'  name='empName'  id='empName' style='border:none;width:100%;background-color:white;'  value='"+value[0]+"'   th:field = '*{empName[__${"+index+"}__]}'/>";
				 // alert("empName = "+empName);
				  var ppoNo1="<input type='hidden' name='ppoNo'  style='border:none;width:100%;background-color:white;'  size='35' value='"+value[1]+"'  th:field = '*{ppoNo[__${"+index+"}__]}' />";
				  var ppoNo= value[1];
				 // alert("Sevaarth Id = "+ppoNo);
				  var existingAmount="<input type='text' disabled='disabled'  th:name='existingAmt' value='"+value[8]+"' class='existingAmount' />";//value='"+value[3]+"'
				  var NetAmount="<input type='text' class='netAmount number' name='netAmt' value='"+value[7]+"' th:field = '*{netAmt[__${"+index+"}__]}' />";
				//  var difference="<input type='text' class='netAmount'  />";
				  if(value[8]!=0)
					  {
				  var difference=value[7]-value[8];
					  }
				  else
					  {
					  var difference=0;
					  }
				  
				  difference="<input type='text' class='difference' value="+difference+" style='border:none;'/>";
				  
				  var basic=value[3];
				  var deptallowcode="<input type='text' style='display:none'  name='deptallowcode' id='deptallowcode' value='"+value[5]+"'  th:field = '*{deptallowcode[__${"+index+"}__]}' />";
				  var deptcode="<input type='text' style='display:none' name='deptcode' id='deptcode'  size='35' value='"+value[6]+"'  th:field = '*{deptcode[__${"+index+"}__]}' />";
				  $('#tblNonGovDuesDeduct').dataTable().fnAddData([empName,ppoNo+ppnNo1,existingAmount,NetAmount,difference,basic,employeeId,deptallowcode,deptcode]);
					  });

		  }
		});
  });
   
  
  
});

var checknumber = $('#textbox_id').val();




$("#componentId").change(function(e){
	e.preventDefault();
  var componentVal=$("#componentId").val();
  var ddoCode=$('#logUser').text();
  var ppoNo=$("#searchSevaarthId").val();
 // var deptallowcode=$("#deptallowcode").val();
    //var deptcode=$("#deptcode").val();
  if(ppoNo== "" || ppoNo =="undefined")
	  {
	  ppoNo="1";
	  }
 // alert("deptcode********"+deptcode);
  var res = componentVal.split(")");    //split two data from single string
  var allowDeducComponentId=res[1];
  $.ajax({
	  url: "../master/getPensEmployeeAgainstId/"+allowDeducComponentId+'/'+ddoCode+'/'+ppoNo,
	  cache: false,
	  success: function(data){
		  $('#tblNonGovDuesDeduct').dataTable().fnClearTable();
		  $('#tblNonGovDuesDeduct').show();
		  var s="<input type='text' disabled='disabled' class='existingAmount'>";
		  var NetAmount="<input type='text' class='netAmount' >";
		  var difference="<input type='text' disabled='disabled' class='difference' >";
		  
		  $.each(data,function(index,value) 
				  { 
			  
			 console.log(value);

			 var employeeId="<input type='text'   id='employeeId'  style='display:none' value='"+value[4]+"' th:field = '*{employeeId[__${"+index+"}__]}'/>";
			  var empName="<input type='hidden'  name='empName'  id='empName' style='border:none;width:100%;background-color:white;' value='"+value[0]+"'  th:field = '*{empName[__${"+index+"}__]}'/>";
			  var empName1=value[0];
			 // alert("empName = "+empName);
			  
			  
			  var ppoNo1="<input type='hidden' name='ppoNo'  style='border:none;width:100%;background-color:white;'  size='35' value='"+value[1]+"'  th:field = '*{ppoNo[__${"+index+"}__]}' />";
			  var ppoNo= value[1];
			 // alert("Sevaarth Id = "+ppoNo);
			  var existingAmount="<input type='text' disabled='disabled'  th:name='existingAmt' value='"+value[8]+"' class='existingAmount' />";//value='"+value[3]+"'
			  var NetAmount="<input type='text' class='netAmount number' onclick() name='netAmt' value='"+value[7]+"' th:field = '*{netAmt[__${"+index+"}__]}' value = '0'/>";
			  //var difference="<input type='text' class='netAmount'  />";
			  if(value[8]!=null)
			  {
		  var difference=value[7]-value[8];
			  }
		  else
			  {
			  var difference=0;
			  }
			  
			  difference="<input type='text' class='difference' value="+difference+" style='border:none;'/>"
			  var basic=value[3];
			  var deptallowcode="<input type='text' style='display:none'  name='deptallowcode' id='deptallowcode'  size='35' value='"+value[5]+"'  th:field = '*{deptallowcode[__${"+index+"}__]}' />";
			  var deptcode="<input type='text' style='display:none' name='deptcode' id='deptcode'  size='35' value='"+value[6]+"'  th:field = '*{deptcode[__${"+index+"}__]}' />";
			  $('#tblNonGovDuesDeduct').dataTable().fnAddData([empName1+empName,ppoNo+ppoNo1,existingAmount,NetAmount,difference,basic,employeeId,deptallowcode,deptcode]);
				  });
	  }
	});
});



$("table").on("keyup", ".netAmount", function() {
  var row = $(this).closest("tr");
  var qty = parseFloat(row.find(".netAmount").val());
  var price = parseFloat(row.find(".existingAmount").val());
  var total = qty - price;
  row.find(".difference").val(isNaN(total) ? "" : total);
});


function isNumber(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}