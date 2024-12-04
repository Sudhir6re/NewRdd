 const aesUtil = new AESUtil();
	//$("#submit").click(function(e){
	
 
 $("form").submit(function (e) {
     $("input").attr("maxlength",255);
     
        if($("#password").val()==""){
            e.preventDefault();
            addErrorClass($("#password"),"Please enter password !!!");
        }else if($("#username").val()==""){
            e.preventDefault();
            addErrorClass($("#username"),"Please enter username !!!");
        }else if($("#UserCaptchaCode").val()==""){
            addErrorClass($("#UserCaptchaCode"),"Please enter captcha !!!");
            e.preventDefault();
        }else if($("#password").val()!="" && $("#username").val()!="" && $("#UserCaptchaCode").val()!=""){
            $("#username").val($("#username").val().trim());
            if($("#password").val().length!=120){
            var plaintText = $("#password").val().trim();
         //   $("#password").val(aesUtil.encrypt(aesmain,plaintText));
            $("#password").val(aesUtil.encrypt("MESSAGE",plaintText));
            }
            $("#enCaptcha").val("");
        }else if($("#UserCaptchaCode").val()!=$("#enCaptcha").val()){
            addErrorClass($("#UserCaptchaCode"),"Captcha Mismatched !!!");
            e.preventDefault();
        }
        
        
        if($("#password").val().length!=120){
            e.preventDefault();
        }
        
    });
 
		



	//fetchNoof employee mapped with ddo
	function getCipherText(plaintText){
		$("#loaderMainNew").show();
		
		
		var token = $('#_csrf').attr('content');
		var header = $('#_csrf_header').attr('content');
		
	 	var cipherText="";		 
	 	$.ajax({
			type : "POST",
			url : "../master/convertPlaitoCipherText/"+plaintText,
			async : false,
			contentType : 'application/json',
			   beforeSend: function(xhr) {
	               xhr.setRequestHeader(header, token);
	           },
			error : function(data) {
				console.log(data);
				$("#loaderMainNew").hide();
			},
			success : function(data) {
				$("#loaderMainNew").hide();
				 console.log(data);
				 cipherText=data;
				 $('#password').val(data);
			}
		});	
		 return cipherText;
	}



	/*$("#logout").click(function(e){
		alert("hello");
	});*/


