var contextPath ="";
jQuery(document).ready(function($) {
	 contextPath = $("#appRootPath").val();
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
	  var ddoCodeLevel1=$('#logUser').text();
	  
	  var parts = ddoCodeLevel1.split('_'); 
	  var ddoCode = parts[0]; 
	  
	  
	  var sevaarthId=$("#searchSevaarthId").val();
	  //var deptallowcode=$("#deptallowcode").val();
	  //var deptcode=$("#deptcode").val();
	 // alert("sevaarthid 1********"+sevaarthId);
	//alert("deptcode********"+deptcode);
	  var res = componentVal.split(")");    //split two data from single string
	  var allowDeducComponentId=res[1];
	  $.ajax({
		  url: contextPath+"/ddoast/getEmployeeAgainstId/"+allowDeducComponentId+'/'+ddoCode+'/'+sevaarthId,
		  cache: false,
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
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
				 var empName1="<input type='hidden'  name='empName'  id='empName' style='border:none;width:100%;background-color:white;' value='"+value[0]+"'  th:field = '*{empName[__${"+index+"}__]}'/>";
				  var empName=value[0];
				 // alert("empName = "+empName);
				  var sevaarthId1="<input type='hidden' name='sevaarthId'  style='border:none;width:100%;background-color:white;text-align:center;'  size='35' value='"+value[1]+"'  th:field = '*{sevaarthId[__${"+index+"}__]}' />";
				  var sevaarthId=value[1];
				 // alert("Sevaarth Id = "+sevaarthId);
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
				  difference=difference+empName1+sevaarthId1;
				  var basic=value[3];
				  var deptallowcode="<input type='text' style='display:none'  name='deptallowcode' id='deptallowcode' value='"+value[5]+"'  th:field = '*{deptallowcode[__${"+index+"}__]}' />";
				  var deptcode="<input type='text' style='display:none' name='deptcode' id='deptcode'  size='35' value='"+value[6]+"'  th:field = '*{deptcode[__${"+index+"}__]}' />";
				  $('#tblNonGovDuesDeduct').dataTable().fnAddData([empName,sevaarthId,existingAmount,NetAmount,difference,basic,employeeId,deptallowcode,deptcode]);
					  });

		  }
		});
  });
   
  
  
});

var checknumber = $('#textbox_id').val();




$("#componentId").change(function(e){
	e.preventDefault();
  var componentVal=$("#componentId").val();
//  var ddoCode=$('#logUser').text();
  var ddoCodeLevel1=$('#logUser').text();
  
  var parts = ddoCodeLevel1.split('_'); 
  var ddoCode = parts[0]; 
  
  var sevaarthId=$("#searchSevaarthId").val();
 // var deptallowcode=$("#deptallowcode").val();
    //var deptcode=$("#deptcode").val();
  if(sevaarthId== "" || sevaarthId =="undefined")
	  {
	  sevaarthId="1";
	  }
 // alert("deptcode********"+deptcode);
  var res = componentVal.split(")");    //split two data from single string
  var allowDeducComponentId=res[1];
  $.ajax({
	  url:  contextPath+"/ddoast/getEmployeeAgainstId/"+allowDeducComponentId+'/'+ddoCode+'/'+sevaarthId,
	  cache: false,
	  	beforeSend : function(){
			$( "#loaderMainNew").show();
			},
		complete : function(data){
			$( "#loaderMainNew").hide();
		},	
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
			  var empName1="<input type='hidden'  name='empName'  id='empName' style='border:none;width:100%;background-color:white;' value='"+value[0]+"'  th:field = '*{empName[__${"+index+"}__]}'/>";
			  var empName=value[0];
			 // alert("empName = "+empName);
			  var sevaarthId1="<input type='hidden' name='sevaarthId'  style='border:none;width:100%;background-color:white;text-align:center;'  size='35' value='"+value[1]+"'  th:field = '*{sevaarthId[__${"+index+"}__]}' />";
			  var sevaarthId=value[1];
			 // alert("Sevaarth Id = "+sevaarthId);
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
			  difference=difference+empName1+sevaarthId1;
			  var deptcode="<input type='text' style='display:none' name='deptcode' id='deptcode'  size='35' value='"+value[6]+"'  th:field = '*{deptcode[__${"+index+"}__]}' />";
			  $('#tblNonGovDuesDeduct').dataTable().fnAddData([empName,sevaarthId,existingAmount,NetAmount,difference,basic,employeeId,deptallowcode,deptcode]);
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