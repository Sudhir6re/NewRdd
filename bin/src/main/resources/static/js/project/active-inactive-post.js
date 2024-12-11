
var contextPath = "";
	$(document).ready(function() {
	contextPath = $("#appRootPath").val();
$(document).on('click', '#updatePostStatus', function() {
    var postId = $(this).attr("data-postId");
    var activateFlag = $(this).attr("data-activateFlag");
    
    var msg="";
    if(activateFlag=="0"){
    	msg="for Activate This Post";
    }else{
    	msg="for In-Activate This Post";
    }
    
    
    swal({
        title: "Are you sure?",
        text: msg,
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
        	$.ajax({
        	      type: "POST",
        	      url: contextPath+"/mdc/updatePostStatus/"+postId+"/"+activateFlag,
        	      async: false,
        	      contentType:'application/json',
        		 // dataType : 'json',
        	      error: function(data){
        	    	  console.log(data);
        	      },
        	      success: function(data){
        	    	  console.log(data);
        	            swal(data);
        	    	}
        	 });
        }
    });
});
	});
