$(document).ready(function(){
	
	var varMessage = $("#message").val();
	if (varMessage != "" && varMessage != undefined) {
		swal('' + varMessage, {
			icon : "success",
		});
	}
	
	 $('.attchDeattachTable').DataTable();
	var selecteditems  = [];
	
	
   /*  
     $("#btnSave").click(function(e){
    	
   	 	 var input = $('#pensSchemebillGroupId').val();
   	 	 var sevaarthId = $('#sevaarthId').val(); 
   	 	// alert("sevaarthId ="+sevaarthId);
   	 	var checkPaybillInProcess=isPaybillIsInProcessForAttach(sevaarthId);
		
		if(checkPaybillInProcess=="0"){
    		 $.ajax({
			      type: "GET",
			      url: "../level1/attachEmployee/"+sevaarthId+"/"+input,
			      async: true,
			      contentType:'application/json',
			      error: function(data){
			    	  console.log(data);
			    	  alert(data);
			      },
			      success: function(data){
			    	 location.reload(true);
			    	 swal("Employee Attach Successfuly");
			    }
			 });	
		}
		else{
			swal("Paybill is in process !!!");
			$("#btnSave").prop("disabled",true);
			 e.preventDefault();
		}
    	  });*/
   
  /*
     $(".delete").click(function(){
    	   var sevaarthId=$(this).attr('value');
    	   var checkPaybillInProcess=isPaybillIsInProcessForAttach(sevaarthId);
      		
      		if(checkPaybillInProcess=="0"){
    	   swal({
    		   title: "Are you sure?",
    		   text: " to Dettach Employee!",
    		   icon: "warning",
    		   buttons: true,
    		   dangerMode: true,
    		 })
    		 .then((willDelete) => {
    		   if (willDelete) {
    			   
    			   
    			     $.ajax({
    				      type: "GET",
    				      url: "../level1/dettachBillGroupId/"+sevaarthId,
    				      async: true,
    				      error: function(data){
    				    	  console.log(data);
    				    	  alert("eror is"+data);
    				      },
    				      success: function(data){
    				    	   swal("Dettach Successfully....!", {
    				    	       icon: "success",
    				    	     });
    				    	   setTimeout(function() {
    							    location.reload(true);
    							}, 3000);
    				      }
    				 });
    		   }
    		 });
      		}else{
    			swal("Paybill is in process !!!");
    			$("#btnSave").hide();
    			
    		}
    	   
    	  });*/
     

 });


function findAttachDettachEmp(){
	var billgroupid = document.getElementById("pensSchemebillGroupId");
	if (billgroupid.value === "0") {
		swal('Please select Bill Description');
		return false;
	}
	document.getElementById("status").value="SEARCH";
	$("#myForm").submit();
}

function validateBeforeAttach(){
	var chkBoxArr=document.getElementsByName('GroupCheck'); 
	var chkLength=chkBoxArr.length;
	for(var i=0;i<chkLength;i++){ 
		if(chkBoxArr[i].checked)
		{
			AddRowInEmpBGTable();
			return true; 
		}
	} 
	return false;
}
function AddRowInEmpBGTable()
{
	var color1 = "#F0F0F0";
	var color2 = "#7B68EE";
	
	var counterEmp = document.getElementById("counterEmp").value ;
	var counterEmpBG = document.getElementById("counterEmpBG").value ;
	var dcpsEmpIdsToBeAddedToBGTable = new Array() ;
	var dcpsEmpNamesToBeAddedToBGTable = new Array() ;
	var dcpsPpoNoToBeAddedToBGTable = new Array() ;
	var selectedEmpIds = new Array();
	var totalSelectedEmpIds=0;
	var newRow;
	var Cell1;
	var Cell2;
	var Cell3;
	var counter = 1 ;
	var tableEmpBG = document.getElementById("tableEmpBG");
	var tableEmp =document.getElementById("tableEmp");
	for(var i=1;i<=counterEmp;i++)
	{
		if(document.getElementById("GroupCheck"+i).checked)
		{
			dcpsEmpIdsToBeAddedToBGTable[counter] = document.getElementById("GroupCheck"+i).value ;
			dcpsEmpNamesToBeAddedToBGTable[counter] = document.getElementById("empName"+i).innerHTML ;
			dcpsPpoNoToBeAddedToBGTable[counter] = document.getElementById("tableEmpPpoNo"+i).innerHTML ;
			
			totalSelectedEmpIds = totalSelectedEmpIds + 1 ; 
			selectedEmpIds[counter] = i ;
			counter++ ;
		}
	}

	
	for(i=1;i<=totalSelectedEmpIds;i++)
	{
		counterEmpBG = Number(counterEmpBG) + 1 ;
		newRow = tableEmpBG.insertRow(tableEmpBG.rows.length);
		newRow.style.backgroundColor = color1;
		newRow.style.borderColor = color2;
		
		Cell1 = newRow.insertCell(-1);
		Cell3 = newRow.insertCell(-1);
		Cell2 = newRow.insertCell(-1);
		Cell1.align="center";
	   	Cell2.align="left";
	 	Cell3.align="left";
	   	
	   	Cell1.innerHTML = '<input type="checkbox" name="GroupCheckBG" id="GroupCheckBG'+counterEmpBG+'" value="'+dcpsEmpIdsToBeAddedToBGTable[i]+'" />' ;
	    Cell2.innerHTML = '<label id="empNameBG'+counterEmpBG+'"><b>'+dcpsEmpNamesToBeAddedToBGTable[i]+'</b></label>' ;
	    Cell3.innerHTML = '<label id="tableEmpBGPpoNo'+counterEmpBG+'"><b>'+dcpsPpoNoToBeAddedToBGTable[i]+'</b></label>' ;

	    document.getElementById("counterEmpBG").value = Number(document.getElementById("counterEmpBG").value) + 1 ;
	    document.getElementById("dcpsEmpIdstoBeAttached").value = document.getElementById("dcpsEmpIdstoBeAttached").value +  dcpsEmpIdsToBeAddedToBGTable[i] + "~" ;

	    document.getElementById("dcpsEmpIdstoBeDetached").value = document.getElementById("dcpsEmpIdstoBeDetached").value.replace(dcpsEmpIdsToBeAddedToBGTable[i] + "~","") ;
	}
	

	for(i=counterEmp;i>=1;i--)
	{
		if(document.getElementById("GroupCheck"+i).checked)
		{
			tableEmp.rows[i].style.display = 'none' ;
			document.getElementById("GroupCheck"+i).checked = false ;
		}
	}
}
function validateBeforeDetach(){
	var chkBoxArr=document.getElementsByName('GroupCheckBG'); 
	var chkLength=chkBoxArr.length;
	 $('#myModal').modal('hide');
	for(var i=0;i<chkLength;i++){ 
		if(chkBoxArr[i].checked)
		{
			AddRowInEmpTable();
			return true; 
			
		}
	} 

	return false;	
}
function AddRowInEmpTable()
{

	var color1 = "#F0F0F0";
	var color2 = "#7B68EE";
	
	var counterEmp = document.getElementById("counterEmp").value ;
	var counterEmpBG = document.getElementById("counterEmpBG").value ;
	
	var dcpsEmpIdsToBeAddedToTable = new Array() ;
	var dcpsEmpNamesToBeAddedToTable = new Array() ;
	var dcpsPpoNoToBeAddedToBGTable = new Array() ;
	var selectedEmpIds = new Array();
	var totalSelectedEmpIds=0;
	var newRow;
	var Cell1;
	var Cell2;
	var Cell3;
	var counter = 1 ;
	var tableEmpBG = document.getElementById("tableEmpBG");
	var tableEmp = document.getElementById("tableEmp");
	
	for(var i=1;i<=counterEmpBG;i++)
	{
		if(document.getElementById("GroupCheckBG"+i).checked)
		{
			dcpsEmpIdsToBeAddedToTable[counter] = document.getElementById("GroupCheckBG"+i).value ;
			dcpsEmpNamesToBeAddedToTable[counter] = document.getElementById("empNameBG"+i).innerHTML ;
			dcpsPpoNoToBeAddedToBGTable[counter] = document.getElementById("tableEmpBGPpoNo"+i).innerHTML ;
			totalSelectedEmpIds = totalSelectedEmpIds + 1 ; 
			selectedEmpIds[counter] = i ;
			counter++ ;
		}
	}

	for(i=1;i<=totalSelectedEmpIds;i++)
	{
		counterEmp = Number(counterEmp) + 1 ;
		newRow = tableEmp.insertRow(tableEmp.rows.length);
		newRow.style.backgroundColor = color1;
		newRow.style.borderColor = color2;
		
		Cell1 = newRow.insertCell(-1);
		Cell3 = newRow.insertCell(-1);
		Cell2 = newRow.insertCell(-1);
		Cell1.align="center";
	   	Cell2.align="left";
	   	Cell3.align="left";
	   	
	   	Cell1.innerHTML = '<input type="checkbox" name="GroupCheck" id="GroupCheck'+counterEmp+'" value="'+dcpsEmpIdsToBeAddedToTable[i]+'" />' ;
	    Cell2.innerHTML = '<label id="empName'+counterEmp+'"><b>'+dcpsEmpNamesToBeAddedToTable[i]+'</b></label>' ;
	    Cell3.innerHTML = '<label id="tableEmpPpoNo'+counterEmp+'"><b>'+dcpsPpoNoToBeAddedToBGTable[i]+'</b></label>' ;

	    document.getElementById("counterEmp").value = Number(document.getElementById("counterEmp").value) + 1 ;
	    document.getElementById("dcpsEmpIdstoBeDetached").value = document.getElementById("dcpsEmpIdstoBeDetached").value +  dcpsEmpIdsToBeAddedToTable[i] + "~" ;

	    document.getElementById("dcpsEmpIdstoBeAttached").value = document.getElementById("dcpsEmpIdstoBeAttached").value.replace(dcpsEmpIdsToBeAddedToTable[i] + "~","") ;
	}

	for(i=counterEmpBG;i>=1;i--)
	{
		if(document.getElementById("GroupCheckBG"+i).checked)
		{
			tableEmpBG.rows[i].style.display = 'none' ;
			document.getElementById("GroupCheckBG"+i).checked = false ;
		}
	}

}


// CheckAll and UnCheckAll
function checkUncheckAll(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name == 'GroupCheck' )
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}	
function checkUncheckAllBG(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name == 'GroupCheckBG')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}






/*
 * 
 * $("table").on("change", ".allowCheckBox", function() { var row =
 * $(this).closest("tr"); var chk =row.find(".allowCheckBox").prop("checked");
 * 
 * 
 * row.find(".checkbox").val(chk); });
 */
	   

function AttachAndDetachEmp()
{
	document.getElementById("status").value="SAVE";
	$("#myForm").submit();
}

function detachRemark(){
	$("#remarksTableBody").empty();
	var i=0;
	var index=$('#remarksTableBody  >tbody >tr').length;
	$(".childTable>tbody>tr").each(function(){ 
		  var row = $(this);
			
		    	var empId=$(this).find(".empId").val();
		    	var empName=$(this).find(".empName").text();
		    	
		    	var empPpoNo=$(this).find(".empPpoNo").text();
		    	 var ppoNo='<input type ="hidden" class="empPpoNo" value="'+empPpoNo+'">';
		    	
		    	var chk =row.find(".empId").prop("checked");  
		    	
		    	 var dettachReasons = '<select name="lstPensBillGrpDeattachRemarkModel['+i+'].dettachReasons" class="form-control dettachReasons">';
		    	 
		    	 
		    	 var moveToSevenPay="<a class='moveToSevenPay hide' href='../pension/editEnhanceFamilyPensionDates/"+empPpoNo+"/"+empId+"' target='_blank'>Update Basic Details</a>";
		    	 
		 		
		 		$("#reasons > option").each(function() {
		 			dettachReasons=dettachReasons+'<option data="'+index+'" value="'+this.value+'">'+this.text+'</option>';
		 		});
		    	
		 		 dettachReasons = dettachReasons+'</select>';
		    	
		        if(chk==true){
		        	var detachRemark='<input type="text" size="40%" id="remark'+i+'"  name="lstPensBillGrpDeattachRemarkModel['+i+'].remark"  class="form-control remark" />'
			    	var effectiveDateRemark='<input type="date" size="40%" id="effectiveDate'+i+'"  name="lstPensBillGrpDeattachRemarkModel['+i+'].effectiveDate"  class="form-control effectiveDate" />'
			    	var employeeId='<input type="hidden" size="40%" id="pensEmployeeId'+i+'"  name="lstPensBillGrpDeattachRemarkModel['+i+'].pensEmployeeId" value="'+empId+'"  class="form-control" />'
			        $("#remarksTableBody").append("<tr><td>"+empPpoNo+ppoNo+"</td><td>"+empName+employeeId+"</td><td>"+effectiveDateRemark+"</td><td>"+detachRemark+"</td><td>"+dettachReasons+"</td><td>"+moveToSevenPay+"</td</tr>")
			    	i++; 
		        }
	});
	   $('#myModal').modal('show');
}





$("#remarksTableBody").on("change", ".dettachReasons", function() {
	  var row = $(this).closest("tr");
	  var dettachReasons = row.find(".dettachReasons").val();
	  if(dettachReasons=="1"){
		  row.find(".moveToSevenPay").removeClass("hide");
	  }
	  
	  console.log("cliekdfhhhh");
	
});



$("#remarksTableBody").on("keyup", ".remark,.effectiveDate", function() {
	  var row = $(this).closest("tr");
	  var remark = row.find(".remark").val();
	  var effectiveDate = row.find(".effectiveDate").val();
	  
	  if($("#selectAllDate").prop("checked")==true){
	  if(effectiveDate!='' && effectiveDate!=undefined){
		  $(".effectiveDate").val(effectiveDate);
	  }
	  }
	  
	  if($("#selectAllRemark").prop("checked")==true){
	  if(remark!='' && remark!=undefined){
		  $(".remark").val(remark);
	  }
	  } 
});


$("#remarksTableBody").on("change", ".effectiveDate", function() {
	  var row = $(this).closest("tr");
	  var remark = row.find(".remark").val();
	  var effectiveDate = row.find(".effectiveDate").val();
	  
	  if($("#selectAllDate").prop("checked")==true){
	  if(effectiveDate!='' && effectiveDate!=undefined){
		  $(".effectiveDate").val(effectiveDate);
	  }
	  }
	  
	  if($("#selectAllRemark").prop("checked")==true){
	  if(remark!='' && remark!=undefined){
		  $(".remark").val(remark);
	  }
	  } 
});







$("#btnUpdate").click(function(e){
//	alert("Hii");
	 var isAllFieldFilled=true;
	$(".remarksTable>tbody>tr").each(function(){ 
		
	 var row = $(this);
 	 var remark = $(this).find(".remark").val();
 	 var effectiveDate =  $(this).find(".effectiveDate").val(); 
 	  var dettachReasons = $(this).find(".dettachReasons").val();
 	 var empPpoNo=$(this).find(".empPpoNo").val();
 	 
 	/* if(empPpoNo!=null || empPpoNo!="" || empPpoNo!=undefined){
 	  var checkPaybillInProcess=isPaybillIsInProcessForAttach(empPpoNo);
 	 }*/
 	
 	 
 	
 	 
 	if(effectiveDate=="" || effectiveDate==undefined || effectiveDate==null){
 		swal("Please select Date !!!");
 		isAllFieldFilled=false;
 		
 		e.preventDefault();
 	 }
 	 
 	 else  if(remark=="" || remark==undefined){
 		swal("Please Enter Remark !!!");
 		isAllFieldFilled=false;
 		e.preventDefault();
 	 }
 	
 	else if(dettachReasons=="" || dettachReasons==undefined || dettachReasons=="0"){
		 
  		swal("Please Select Dettach Reason !!!");
 		isAllFieldFilled=false;
 		e.preventDefault();
 		 
 	 }
 	
 	 else if (effectiveDate!= undefined || effectiveDate!= null || effectiveDate!=""){

 		 var date1= new Date();
 		 var yyyy = date1.getFullYear();
 		 var mm = date1.getMonth()+1;
 		 var dd = date1.getDate();
 		if (dd < 10) dd = '0' + dd;
 		if (mm < 10) mm = '0' + mm;

 		var formattedToday = yyyy + '-' + mm + '-' + dd;
 		 
 		
 	if(effectiveDate>formattedToday){
			swal("Future date not allowed !!!");	
			isAllFieldFilled=false;
			}
	

 	 }
 	
	});
	
 		/*
 		if(checkPaybillInProcess=="0"){
	   swal({
		   title: "Are you sure?",
		   text: " to Dettach Employee!",
		   icon: "warning",
		   buttons: true,
		   dangerMode: true,
		 })
		 .then((willDelete) => {
		   if (willDelete) {
			   
			   
			     $.ajax({
				      type: "GET",
				      url: "../master/isPensBillIsInProcessForAttach/"+empPpoNo,
				      async: true,
				      error: function(data){
				    	  console.log(data);
				    	  alert("eror is"+data);
				      },
				      success: function(data){
				    	   swal("Dettach Successfully....!", {
				    	       icon: "success",
				    	     });
				    	   setTimeout(function() {
							    location.reload(true);
							}, 3000);
				      }
				 });
		   }
		 });
 		}else{
			swal("Pension Bill is in process !!!");
			$("#btnSave").hide();
			
		}
	   */
 		
 		if(isAllFieldFilled==true){
 			validateBeforeDetach();
 		}
	 

});


/*
function isPaybillIsInProcessForAttach(empPpoNo) {
	var len=0;
	$.ajax({
		type : "GET",
		url : "../master/isPensBillIsInProcessForAttach/" +empPpoNo,
		async : false,
		contentType:'application/json',
		error : function(data) {
			console.log(data);
			alert(data);
		},
		success : function(data) {
			console.log(data);
			len=data.length;
		}
	});
	return len;
}
	 */


$(document).ready(function() {
	  $("#serachnameAttachList").keyup(function(e) {
	    filterTableData($("#serachnameAttachList").val().toUpperCase(), $("#tableEmpBG tbody"));
	  });

	  $("#serachnameDeaatachedList").keyup(function(e) {
	    filterTableData($("#serachnameDeaatachedList").val().toUpperCase(), $("#tableEmp tbody"));
	  });
	});

	function filterTableData(searchText, $tbody) {
	  $tbody.find("tr").hide().filter(function() {
	    return $(this).text().toUpperCase().indexOf(searchText) > -1;
	  }).show();
	}