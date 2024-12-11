$(document).ready(function(){
	
     var context=$("#context").val();
	if($("#message").val()=="1") {
		Swal.fire("Data Saved Successfully !!!");
	}
	
//	$("#btnSave").prop("disabled",true);
	
  $("#isType").change(function(){
	  var isType=$("#isType").val();
		$.ajax({
			type : "GET",
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
  
  $("#sevaarthId").blur(function(){
	  var sevaarthId=$("#sevaarthId").val();
	  $.ajax({
		  type : "GET",
		  url : context+"/level1/findEmpBySevaarthId/"
		  + sevaarthId,
		  async : true,
		  contentType : 'application/json',
		  error : function(data) {
			  console.log(data);
			  // alert("error");
		  },
		  success : function(data) {
			  console.log(data);
			  var len = parseInt(data);
			  if (len == 0) {
				  $("#btnSave").prop("disabled",false);
			  }else{
				  $("#btnSave").prop("disabled",true);
				  Swal.fire({
					  position: 'center',
					  icon: 'warning',
					  title: 'Sevaarth id does not exists !!!',
					  showConfirmButton: false,
					  timer: 1500
				  })
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
			  url : context+"/level1/deleteEmployeeSuspensionEntity/"
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



  
  
  
 
  
  $(".btnSave").click(function(e){
	  var sevaarthId=$("#sevaarthId").val();
	  var startDate=$("#startDate").val();
	  var endDate=$("#endDate").val();
	  var reason=$("#reason").val();
	  var percentage=$("#percentage").val();
	  
	  var isEmployeeSuspended=0;
	  $.ajax({
  	      type: "GET",
  	      url : context+"/level1/isEmployeeSuspended/"
		  + sevaarthId,
  	      async: false,
  	      dataType : 'json',
  	      contentType : 'application/json',
  	      error: function(data){
  	    	  console.log(data);
  	      },
  	      success: function(data){
  	    	isEmployeeSuspended=parseInt(data);
  	    }
  	 });
	  
	  
	  
	  if(sevaarthId==""){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please enter sevaarthId !!!',
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
	  else if(percentage==''){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please  enter percentage !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
	  else if(reason=='' && reason==''){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please reason !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }else if(parseInt(isEmployeeSuspended)!=0){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Employee Already Suspended !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
	  else if(parseInt(percentage)>90){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please  enter below percentage !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
  });
  

  $("#percentage").keyup(function(){
	  var percentage=$(this).val();
	   if(parseInt(percentage)>90){
		  e.preventDefault();
		  Swal.fire({
			  position: 'center',
			  icon: 'warning',
			  title: 'Please  enter below percentage !!!',
			  showConfirmButton: false,
			  timer: 1500
		  })
	  }
  });
  
  
// isEmployeeSuspended
  function isEmployeeSuspended(sevaarthId)
  {
  	flag=0;
  	 $.ajax({
  	      type: "GET",
  	      url : context+"/level1/isEmployeeSuspended/"
		  + sevaarthId,
  	      async: false,
  	      dataType : 'json',
  	      contentType : 'application/json',
  	      error: function(data){
  	    	  console.log(data);
  	      },
  	      success: function(data){
  	    	flag=parseInt(data);
  	    }
  	 });
  	 return flag;
  }
});

//validate for employee suspension 

function validateEmpForSusp() {
	/* alert('inside validateEmpForSusp');*/
		var sevaarthId = document.getElementById("sevaarthId").value;
		
		
		
		if (sevaarthId != '') {
			$
			.ajax({
				type : "GET",
				url : "../level1/validateEmpForSusp/"+sevaarthId,
				async : false,
				contentType : 'application/json',
				error : function(data) {
					console.log(data);
				},
				success : function(data) {
					console.log(data);
					var len = data.length;
					var checkFlag = data;
					if (checkFlag == 0) {
						$('#sevaarthId').val(sevaarthId);
						status = true;
					} else if (checkFlag > 0) {
						
						swal(sevaarthId + ' Is Already Suspended !!!');
						
						document.getElementById("sevaarthId").value = "";
						status = false;
					}
					return status;
				}
			});
		}
	}




