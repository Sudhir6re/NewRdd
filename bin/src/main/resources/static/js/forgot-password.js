 $(document).ready(function() {
            $('#sendEmail').click(function(event) {
                event.preventDefault(); // Prevent the default form submission
                var email=$("#emailId").val();
                var appRootPath=$("#appRootPath").val();
                $('#sendEmail').prop("disabled",true);
                
                $.ajax({
                    url: appRootPath+'/sendToken',
                    method: 'POST',
                    data: { email: email }, 
                    success: function(response) {
                       if(response=="1"){
                    	   alert("Password Reset link is shared with your mail...!");
                    	   window.location.href=appRootPath+"/user/login";
                       }else{
                    	   alert("mail Not Found...!");
                    	   $('#sendEmail').prop("disabled",false);
                       }
                    },
                    error: function(xhr) {
                    	 alert("mail Not Found...!");
                  	   $('#sendEmail').prop("disabled",false);
                    }
                });
            });
            
       
        });
 