$(document).ready(function(){
	
     var context=$("#context").val();
	//alert(context);
	if($("#message").val()=="1") {
		Swal.fire("Data Saved Successfully !!!");
	}
	
	
	
	
	
	$("#btnSave").hide();
	$("#btnForward").hide();
	
	$("#chkValidate").change(function(){
		var checked=$(this).prop('checked');
	if(checked==true && $("#isSave").val()=="true"){
		$("#btnSave").show();
	}else{
		$("#btnForward").show();
	}
	
	});
	
	
	$(".nextPage").click(function(e){
		var sevaarthid=$("#sevaarthid").val();
		
		var text=$(this).attr("href");
		$(this).attr("href",text+sevaarthid);
	
//		e.preventDefault();
	});
	
	
	
	
	
	
	
	
	//var selectedDocIds=$("#selectedDoc").val();
	
   	if($("#selectedDoc").val()!='undefined'){
   var selectedDocIdsArr=$("#selectedDoc").val().split(",");
    for(var i=0;i<selectedDocIdsArr.length;i++){
    	$('.cbDocCheckListCA'+selectedDocIdsArr[i]).prop('checked', true);
    }
    
    if(selectedDocIdsArr.length==8){
    	$("#selectAllCheckListsComp").prop('checked', true);
    }
    
   	}

	
	
	//for seting current date on html date picker
	
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	$('#txtSysDate').val(today);
	
	
  
  
  
  
  
  

  
  $("#cmbSchemeCode").change(function(){
	  var cmbSchemeCode=$("#cmbSchemeCode").val();
		$.ajax({
			type : "GET",
			url : "../master/fetchFestivalSehemeDetials/"
					+ cmbSchemeCode,
				
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			// alert("error");
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0 ) { 
					if(data[0].txtEmployeeName!="0"){
						$("#txtMajorHead").val("");
						$("#txtMajorHead").val(data[0].majorHead);
						$("#txtDemandNo").val(data[0].demandCode);
						$("#txtSchemeName").val(data[0].schemeName);
						$("#txtSubMajorHead").val(data[0].subMajorHead);
						$("#txtMinorHead").val(data[0].minorHead);
						$("#txtGroupHead").val(data[0].charged);
						$("#txtSubHead").val(data[0].subMinorHead);
					}else{
						$("#txtMajorHead").val("");
						$("#txtDemandNo").val("");
						$("#txtSchemeName").val("");
						$("#txtSubMajorHead").val("");
						$("#txtMinorHead").val("");
						$("#txtGroupHead").val("");
						$("#txtSubHead").val("");
					   }
			   }
			}
		});
     });
  

  
  $("#txtReqAmountCA").keyup(function(){
	  var reqAmount=$(this).val();
	  $("#txtSancAmountCA").val(reqAmount);
	  $("#txtSancInstallmentsCA").val("");
	  $("#txtInstallmentAmountCA").val("");
  });
  
  $("#txtSancInstallmentsCA").keyup(function(){
	  if($(this).val()!=""){
	  var noOfInstallments=parseInt($(this).val());
	 var sanctionAmt=parseInt($("#txtSancAmountCA").val());
	  var perMonthInstallmentAmount=((sanctionAmt/noOfInstallments));
	 // $("#txtInstallmentAmountCA").val(perMonthInstallmentAmount.toFixed(2));
	  $("#txtInstallmentAmountCA").val(Math.round(perMonthInstallmentAmount));
	  }else{
		  $("#txtInstallmentAmountCA").val("");
	  }
  });
  

});


