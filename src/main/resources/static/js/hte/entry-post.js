	var context ="";
$(document).ready(function() {
	context = $("#appRootPath").val();
	
	
	
	var message =$("#message").val(); 
	if (message != ''  && $("#message").val()!=null  && $("#message").val()!=undefined ) {
		swal('Post Created Successfully !!!');
	}
	
	
	var dataTable= $("#postDetails").DataTable();
	
	    if ($('#cmbAsstDDO').length) {
	        $('#cmbAsstDDO').select2();
	    }
	    if ($('#ddoCode').length) {
	        $('#ddoCode').select2();
	    }
	    
	    
	    if ($('#designationCmb').length) {
	        $('#designationCmb').select2();
	    }
	    
	    if ($('#billCmb').length) {
	    	$('#billCmb').select2();
	    }
	    
	    
	    if ($('#subjectCmb').length) {
	    	$('#subjectCmb').select2();
	    }
	    
	    if ($('#cmbTaluka').length) {
	    	$('#cmbTaluka').select2();
	    }
	    
	    if ($('#oldGrOrderId').length) {
	    	$('#oldGrOrderId').select2();
	    }
	    
	    if ($('#cmbNewOrder').length) {
	    	$('#cmbNewOrder').select2();
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	
	function checkRenewalDataBeforeSubmit()
	{
		var counterPost = document.getElementById("counterPost").value ;
		var renewalStartDate = document.getElementById("renewalStartDate").value;
		var renewalEndDate = document.getElementById("renewalEndDate").value;
		var renewalPostStartDate = document.getElementById("renewalPostStartDate").value;
		
		
		for(var i=1;i<=counterPost;i++)
		{
			if(document.getElementById("GroupCheck"+i).checked)
			{
				document.getElementById("postIdsToBeAttached").value = document.getElementById("postIdsToBeAttached").value +  document.getElementById("GroupCheck"+i).value + "~" ;
			}
		}
	}


$("#orderCmb").change(function(){
	var context = $("#appRootPath").val();
	var grOrderId = $("#orderCmb").val();
	$.ajax({
	      type: "POST",
	      url: context+"/ddo/findGrOrderByGrOrderId/"+grOrderId,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    		 var len = data.length;
	    		 if(len>0){
	    			  var orderDate = new Date(data[0].orderDate); // Convert string to Date object
	    			    var formattedDate = orderDate.toISOString().split('T')[0]; // Convert Date to yyyy-MM-dd format
	    			    $("#OrderDate").val(formattedDate);
	    		 }
				}
	 });
});






$("#postTypeCmbBox").change(function(){
	var context = $("#appRootPath").val();
	var typePost = $("#postTypeCmbBox").val();
	
	if(typePost=="10001198130"){
		$(".sanDateDiv").show();
	}else{
		$(".sanDateDiv").hide();
	}
});

$("#cmbNewOrder").change(function(){
	var context = $("#appRootPath").val();
	var grOrderId = $("#cmbNewOrder").val();
	$.ajax({
	      type: "POST",
	      url: context+"/ddo/findGrOrderByGrOrderId/"+grOrderId,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    		 var len = data.length;
	    		 if(len>0){
	    			  var orderDate = new Date(data[0].orderDate); // Convert string to Date object
	    			    var formattedDate = orderDate.toISOString().split('T')[0]; // Convert Date to yyyy-MM-dd format
	    			    $("#renewalStartDate").val(formattedDate);
	    			    
	    		 }
				}
	 });
});




$("#oldGrOrderId").change(function(){
	var context = $("#appRootPath").val();
	var grOrderId = $("#oldGrOrderId").val();
	$.ajax({
	      type: "POST",
	      url: context+"/ddo/findGrOrderByGrOrderId/"+grOrderId,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    		 var len = data.length;
	    		 if(len>0){
	    			  var orderDate = new Date(data[0].orderDate); 
	    			    var formattedDate = orderDate.toISOString().split('T')[0]; 
	    			    $("#oldGrOrderDate").val(formattedDate);
	    		 }
				}
	 });
});





$("#btnFilter").click(function(){
	 var ddoCode1=$("#cmbAsstDDO").val();
	 var Dsgn=$("#designationCmb").val();
	 var BillNo=$("#billCmb").val();
	 var context = $("#appRootPath").val();
	 
	 
	if(ddoCode1!='' && ddoCode1!="0"){
		 // $("#loaderMainNew").show();
			$.ajax({
				type : "GET",
			    url: context+"/ddo/searchPostDetails",
				async : true,
				   data: { ddoCode1: ddoCode1 ,BillNo:BillNo,Dsgn:Dsgn,lPostName:""},
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
				},
				beforeSend : function(){
					$( "#loaderMainNew").show();
					},
				complete : function(data){
					$( "#loaderMainNew").hide();
				},	
				success : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
					var len = data.length;
						$("#loaderMainNew").hide();
						j=1;
						if(len>0){
							 dataTable.fnClearTable();
							for (var i = 0; i < data.length; i++) {
								 dataTable.fnAddData([j,data[i].empFullName,data[i].postname,data[i].postType,data[i].dsgnname,data[i].ddoCode,data[i].billNo]);
								 j++;
							}
						}
						if(data.length==0){
							tablePost.fnClearTable();
							swal("No data found");
						}
				}
			});
	}
 });


$("#Search").click(function(){
	 var ddoCode1=$("#cmbAsstDDO").val();
	 var Dsgn=$("#designationCmb").val();
	 var BillNo=$("#billCmb").val();
	 var context = $("#appRootPath").val();
	 
	 
	 if(ddoCode1=="-1"){
		 ddoCode1="";
	 }
	 
	if(Dsgn!='-1' && BillNo!="-1"){
		 // $("#loaderMainNew").show();
			$.ajax({
				type : "GET",
			    url: context+"/ddo/searchPostDetails",
				async : true,
				   data: { ddoCode1: ddoCode1 ,BillNo:BillNo,Dsgn:Dsgn,lPostName:""},
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
				},
				beforeSend : function(){
					$( "#loaderMainNew").show();
					},
				complete : function(data){
					$( "#loaderMainNew").hide();
				},	
				success : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
					var len = data.length;
						$("#loaderMainNew").hide();
						j=1;
						if(len>0){
							
							dataTable.clear();

							// Loop through the data array and add new data to the DataTable
							for (var i = 0; i < data.length; i++) {
							    dataTable.row.add([
							        j,
							        data[i].empFullName,
							        data[i].postname,
							        data[i].postType,
							        data[i].dsgnname,
							        data[i].ddoCode,
							        data[i].billNo
							    ]);
							    j++;
							}
							// Redraw the DataTable to display the updated data
							dataTable.draw();
							/*// dataTable.fnClearTable();
							 dataTable.clear();
							for (var i = 0; i < data.length; i++) {
								dataTable.fnAddData([j,data[i].empFullName,data[i].postname,data[i].postType,data[i].dsgnname,data[i].billNo]);
								 j++;
							}*/
						}
						if(data.length==0){
							tablePost.fnClearTable();
							swal("No data found");
						}
				}
			});
	}
});




$("#searchPostDetails").click(function(){
	var tablePost= $("#tablePost").dataTable();
	
	 var ddoCode1=$("#cmbAsstDDO").val();
	 var orderId=$("#oldGrOrderId").val();
	if(orderId!='-1' && orderId!=""){
		 // $("#loaderMainNew").show();
			$.ajax({
				type : "GET",
			    url: context+"/ddo/searchPostDetails/"+orderId,
				async : true,
				//   data: { oldGrOrderId: oldGrOrderId },
				contentType : 'application/json',
				error : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
				},
				beforeSend : function(){
					$( "#loaderMainNew").show();
					},
				complete : function(data){
					$( "#loaderMainNew").hide();
				},	
				success : function(data) {
					 console.log(data);
					 $("#loaderMainNew").hide();
					var len = data.length;
						$("#loaderMainNew").hide();
						j=1;
						if(len>0){
							tablePost.fnClearTable();
							for (var i = 0; i < data.length; i++) {
								var input='<input type="checkbox" name="GroupCheck" id="GroupCheck1" value="'+data[i].orgPostMst.postId+'">';
								var postname='<label id="postName'+i+'"><b>'+data[i].postName+'</b><b>T</b></label>';
								tablePost.fnAddData([input,data[i].postname]);
								 j++;
								 $("#counterPost").val(i);
							}
						}
						if(data.length==0){
							swal("No data found");
						}
				}
			});
	}
});





$("form[name='postEntryModel']").validate({
    rules: {
       /* cmbTaluka: {
            required: true
        },*/
        ddoCode: {
            required: true,
            min:1,
        },
        postTypeCmbBox: {
            required: true,
            min:1,
        },
        cmbSubFieldDept: {
            required: true,
            min:1,
        },
        purposeCmbBox: {
            required: true,
            min:1,
        },
       /* tempPostTypeCmbBox: {
            required: true,
            min:1,
        },*/
        designationCmb: {
            required: true,
            min:1,
        },
        subjectCmb: {
            required: true,
            //minlength:3,
        },
        orderCmb: {
            required: true,
            min:1,
        },
        officeCmb: {
            required: true,
            min:1,
        },
       startDate: {
            required: true,
        },
        endDate: {
         //   required: true,
            required : function() {
				return $("#postTypeCmbBox").val()=="10001198130";
			},
        },
         postNumber: {
            required: true,
            digits: true ,
            min:1,
        }
    },
    messages: {
        cmbTaluka: {
            required: "Please select Taluka"
        },
        ddoCodeforFilter: {
            required: "Please enter DDO Code/Office Name"
        },
        postTypeCmbBox: {
            required: "Please select Type Of Post"
        },
        cmbSubFieldDept: {
            required: "Please select Sub Field Department"
        },
        purposeCmbBox: {
            required: "Please select Purpose"
        },
        tempPostTypeCmbBox: {
            required: "Please select Type of Temporary Post"
        },
        designationCmb: {
            required: "Please select Designation"
        },
        subjectCmb: {
            required: "Please select Subject"
        },
        orderCmb: {
            required: "Please select GR No"
        },
        officeCmb: {
            required: "Please select Office"
        },
        startDate: {
            required: "Please enter Sanctioned From Date"
        },
        endDate: {
            required: "Please enter Sanctioned To Date"
        },
        postNumber: {
            required: "Please enter Number of Post",
            digits: "Please enter only digits"
        }
    },
    submitHandler: function(form) {
        form.submit(); 
        $("#loaderMainNew").show();
    }
});



$("#btnGo").click(function(){
	var oldGrOrderId = $("#oldGrOrderId").val();
	var oldGrOrderDate = $("#oldGrOrderDate").val();
	$("#action").val("search");
	$("#renewPost").submit();
});


$("#btnSave").click(function(e){
	
	var attachedValues = '';
    $('.postIdsChk:checked').each(function() {
        attachedValues += $(this).val() + '~';
    });
    $('#postIdsToBeAttached').val(attachedValues.slice(0, -1));
	
	$("#renewPost").attr("action", context+"/ddo/renewPostEntry"); 

	if(attachedValues==''){
		e.preventDefault();
	}
	
	//$("#renewPost").submit();
	
	
});




$('#cmbAsstDDO').on('change', function() {
	var ddoCode=$(this).val();
	if(ddoCode=="-1"){
		dataTable.columns().search('').draw();
	}else{
		dataTable.column(5).search(ddoCode).draw(); 
	}
	
	
	if ($('#billCmb').length) {
		fetchBillGroup();
	}
	
});


$('#designationCmb').on('change', function() {
	var designation=$(this).val();
	if(designation=="-1"){
		dataTable.columns().search('').draw();
	}else{
		dataTable.column(4).search(designation).draw(); 
	}
});


$('#BillNo').on('change', function() {
	var BillNo=$(this).val();
	if(BillNo=="-1"){
		dataTable.columns().search('').draw();
	}else{
		dataTable.column(6).search(BillNo).draw();
	}
});


/*
$('#billCmb').on('change', function() {
	var BillNo=$(this).val();
	if(BillNo=="-1"){
		dataTable.columns().search('').draw();
	}else{
		dataTable.column(6).search(BillNo).draw();
	}
});*/








$('#SelectAll').on('change', function() {
    var isChecked = $(this).prop('checked');
    $('.postIdsChk').prop('checked', isChecked);
});




$(document).on('change', '#ddoCode', function() {
	var context = $("#appRootPath").val();
	var ddoCode = $("#ddoCode").val();
	$.ajax({
	      type: "POST",
	      url: context+"/ddo/findGrOrderByGrOrderByDdoCode/"+ddoCode,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    		 var len = data.length;
	    		 $('#orderCmb').empty();
	    		 $( "#loaderMainNew").hide();
	    		 $('#orderCmb').append($('<option  value="-1"></option>').text("Please Select")); 
					if (len != 0) {
							   for(var i=0;i<len;i++){
								   $('#orderCmb').append('<option value="' + data[i].orderId + '">' + data[i].orderName + '</option>');
				                }		
				}
					
	     }
	 });
});


function fetchBillGroup(){
	var context = $("#appRootPath").val();
	var ddoCode = $("#cmbAsstDDO").val();
	$.ajax({
	      type: "POST",
	      url: context+"/ddo/fetchBillGroupByDdoCode/"+ddoCode,
	      async: false,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
		  	beforeSend : function(){
				$( "#loaderMainNew").show();
				},
			complete : function(data){
				$( "#loaderMainNew").hide();
			},	
	      success: function(data){
	    		 var len = data.length;
	    		 $('#billCmb').empty();
	    		 $( "#loaderMainNew").hide();
	    		 $('#billCmb').append($('<option  value="-1"></option>').text("Please Select")); 
					if (len != 0) {
							   for(var i=0;i<len;i++){
								   $('#billCmb').append('<option value="' + data[i].dcpsDdoBillGroupId + '">' + data[i].description + '</option>');
				                }		
				}
					
	     }
	 });
}

});















