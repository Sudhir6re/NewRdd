$(document).ready(function(){
	var selecteditems  = [];
	fetchData();
	function fetchData()
	{
		var dataTable=$("#tblPost").dataTable();
		dataTable.fnClearTable();
	 $.ajax({
	      type: "GET",
	      url: "../level1/fetchPostDetails/",
	      async: true,
	      contentType:'application/json',
	      error: function(data){
	    	  console.log(data);
	      },
	      success: function(data){
          console.log(data);	
          var i=0;
          $.each( data, function( index, value ){
        	  var i=index+1;
	        	var action='&nbsp;<a  class="btn btn-success btnDettach"  data-id='+value[0]+'  data-empId='+value[0]+'  data-sevaarthId='+value[6]+' >Dettach</a>';
				dataTable.fnAddData([i,value[3],value[4],value[1],action]);
          });
	    }
	 });	
	}
     $("#btnSave").click(function(e){
   	 	 var postId = $('#postName').val();
   	 	 var empId = $('#sevaarthId').val(); 
   	     var designationCode = $('#designationCode').val(); 
       if(designationCode=="" || designationCode=="0"){
	 		 swal("Please select designationCode !!!");
	 		return false;
	 	 }
   	    else if(postId=="0" || postId==""){
   	 		 swal("Please select post !!!");
   	 		return false;
   	 	 }else if(empId=="" || empId=="0"){
   	 		 swal("Please select employee name !!!");
   	 		return false;
   	 	 }
   	 	 else{
   	 		 return true;
   	 	 } 
    	  });
     $("#designationCode").change(function() 
     			{
         	   var desgn=+$('#designationCode').val();
         	   
         	  $('#postDetailsId').empty();
         	 $('#postDetailsId').append("<option value='0'>Please Select</option>");
                  if (desgn!= '') {
                 	$.ajax({
     				      type: "GET",
     				      url: "../level1/getPostNames/"+desgn,
     				      async: true,
     				      contentType:'application/json',
     				      error: function(data){
     				    	  console.log(data);
     				    	 // alert(window.location+ur);
     				      },
     				      success: function(data){
     				    	 // console.log(data);
     				    	// alert(" >>> : "+ data);
     				    	  if(data.length!="0")
     				    		  {
     				    	 var temp = data;
     				   		  $.each( temp, function( index, value ){
     					    		console.log( value[2] ); 
     					    		 $('#postDetailsId').append("<option value="+value[0]+"  data-isVacant="+value[2]+">" + value[1] + "</option>");
     				    		});
     				    		  }
     				    	  else
     				    		  {
     				    		  swal("No post available");
     				    		  }
     				    }
     				 });	
                 } 
                 
     			 });  
     
     
     // check is paybill is in progress
     function isPaybillInProcess(sevaarthId)
     {
    	 var len=0;
    	 $.ajax({
		      type: "GET",
		      url: "../level1/checkPaybillInProcess/"+sevaarthId,
		      async: true,
		      error: function(data){
		    	  console.log(data);
		    	  alert("eror is"+data);
		      },
		      success: function(data){
		    	 // alert(data.length);
		    	 len=data.length;
		      }
		 });
    	 return len;
     }
     
    	 $('body').on('click','.btnDettach',function(e){
    	 e.preventDefault();
         var postId=$(this).attr('data-id'); 
         var empId=$(this).attr('data-empId'); 
         var sevaarthId1=$(this).attr('data-sevaarthId'); 
         var checkPaybillInProcess=isPaybillInProcess($(this).attr('data-sevaarthId'));
    	  // alert("hi" + postId);
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
    				      url: "../level1/deattachPostByEmpId/"+postId+"/"+empId,
    				      async: true,
    				      error: function(data){
    				    	  console.log(data);
    				    	  alert("eror is"+data);
    				      },
    				      success: function(data){
    				    	   swal("Dettach Successfully....!", {
    				    	       icon: "success",
    				    	     });
    				    	 // fetchData();
    				    	   location.reload();
    				      }
    				 });
    		   } else {
    		     swal("Dettach Cancelled...!");
    		   }
    		 });
         }
         else{
        	 swal("Dettach Cancelled due to paybill is in process!");
         } 
    	  });
 });
$('#btnReset').click(
	    function(){
	    	$("#sevaarthId").select2("val","0");
	    });
