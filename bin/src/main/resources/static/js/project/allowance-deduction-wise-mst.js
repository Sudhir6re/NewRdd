$(document).ready(function(){
	
     var context=$("#context").val();
	//alert(context);
	if($("#message").val()=="1") {
		Swal.fire("Data Saved Successfully !!!");
	}
  $("#isType").change(function(){
	  var isType=$("#isType").val();
		$.ajax({
			type : "GET",
			/*url : "../admin/AllowanceDeductionWiseMaster/"
					+ isType,*/
					url : context+"/admin/AllowanceDeductionWiseMaster/"
						+ isType,
			async : true,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			// alert("error");
			},
			success : function(data) {
				 console.log(data);
				var len = data.length;
				if (len != 0) { 
				$("#departmentAllowdeducCode").empty();
				 var newOption = $('<option value="0">Please Select</option>');
            	 $('#departmentAllowdeducCode').append(newOption);
                for(var i=0;i<len;i++){
                	  newOption = $('<option value="'+data[i].departmentAllowdeducCode+'">'+data[i].departmentAllowdeducName+'</option>');
                	 $('#departmentAllowdeducCode').append(newOption);
                }				
			   }
			}
		});
     });
  
$(".delete").click(function(){
	  var id=$(this).attr("data");
	  var status=$(this).attr("data-val");
//	  alert("context"+context);
  Swal.fire({
	  title: 'Are you sure?',
	  text: "To delete this!",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Yes!'
	}).then((result) => {
	  if (result.isConfirmed) {
		  $.ajax({
			  type : "GET",
			  url : context+"/admin/deleteAllowanceDeductionWiseMaster/"
				  + id+"/"+status,
				  async : true,
				  contentType : 'application/json',
				  error : function(data) {
					  console.log(data);
				  },
				  success : function(data) {
					  console.log(data);
					    Swal.fire(
					  	      'Deleted!',
					  	      '!!!.',
					  	      'success'
					  	    )
					  location.reload(true);
				  }
		  });
	  }
	})
});



  
  
  $("#endDate").change(function(){
	  var isType=$("#isType").val();
	  var departmentAllowdeducCode=$("#departmentAllowdeducCode").val();
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  var payCommision=$("#payCommision").val();
	  
	  if(endDate=="" || endDate=="undefined"){
		  endDate=startDate;
		  statDate=endDate;
	  }else{
		  endDate=statDate;
		  startDate=endDate;
	  }
	  
		$.ajax({
			type : "POST",
			url :context+"/admin/isAllowanceDeductionWiseMasterDataPresent/"+isType+'/'+departmentAllowdeducCode+'/'+payCommision+'/'+startDate+'/'+endDate,
			async : true,
			//data: str,
			contentType : 'application/json',
			error : function(data) {
				 console.log(data);
			},
			success : function(data) {
				 console.log(data);
				 alert(data);
				var len = data.length;
				/* if (data != 0) {
					  Swal.fire("Data already present !!!");
					  $("#btnSave").prop("disabled",true);
				  }else{
					$("#btnSave").prop("disabled",false);
				  }*/
			}
		});
     });
  $("#startDate").change(function(){
	  var isType=$("#isType").val();
	  var departmentAllowdeducCode=$("#departmentAllowdeducCode").val();
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  var payCommision=$("#payCommision").val();
	  
	  if(endDate=="" || endDate=="undefined"){
		  endDate=startDate;
	  }else{
		  endDate=startDate;
	  }
	  $.ajax({
		  type : "POST",
		  url : context+"/admin/isAllowanceDeductionWiseMasterDataPresent/"+isType+'/'+departmentAllowdeducCode+'/'+payCommision+'/'+startDate+'/'+endDate,
		  async : true,
		  contentType : 'application/json',
		  error : function(data) {
			  console.log(data);
		  },
		  success : function(data) {
			  console.log(data);
			  var len = data.length;
			/*  if (data != 0) {
				  Swal.fire("Data already present !!!");
				  $("#btnSave").prop("disabled",true);
			  }else{
				$("#btnSave").prop("disabled",false);
			  }*/
		  }
	  });
  });
  
  $("#btnSave").click(function(e){
	  var isType=$("#isType").val();
	  var departmentAllowdeducCode=$("#departmentAllowdeducCode").val();
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  var payCommision=$("#payCommision").val();
	  var amount=$("#amount").val();
	  var percentage=$("#percentage").val();
	  
	  if(isType=="0" || isType=="undefined"){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please select allowance type !!!',
			  showConfirmButton: false,
			  timer: 1500
			})
	  }
	  else if(departmentAllowdeducCode=="0" || departmentAllowdeducCode=="undefined"){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please select allowance deduction name !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
	  else if(payCommision=="0" || payCommision=="undefined"){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please select pay commision !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
	  else if(amount=='' && percentage==''){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please  enter amount !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
	  else if(startDate=="" || startDate=="undefined"){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please select start date !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
	 /* else if(endDate=="" || endDate=="undefined"){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please select end date !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }*/
	  else if(percentage=='' && amount=='' ){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please  enter percentage !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
  });
  
  
  $("#amount").keyup(function(){
		  $("#percentage").val("");
		  $(this).val($(this).val().replace(/[^0-9]/g, ''));
  });
  $("#percentage").keyup(function(){
	 $(this).val($(this).val().replace(/[^0-9]/g, ''));
	  $("#amount").val("");
  });
  
  

});

/*function delete(id,status){
	  $.ajax({
		  type : "GET",
		  url : "../admin/deleteAllowanceDeductionWiseMaster/"
			  + id+"/"+status,
			  async : true,
			  contentType : 'application/json',
			  error : function(data) {
				  console.log(data);
			  },
			  success : function(data) {
				  console.log(data);
				    Swal.fire(
				  	      'Deleted!',
				  	      'Your file has been deleted.',
				  	      'success'
				  	    )
				  location.reload(true);
			  }
	  });
   }*/
//saveAllowanceDeductionWiseMaster
