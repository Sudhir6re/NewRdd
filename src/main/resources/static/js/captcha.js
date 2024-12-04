var cd;

/*
var test=window.location.href;
var pathArray = test.split("://");
var path1=pathArray[1];
var pathArray1 = path1.split(":");
var portNo = pathArray1[1].split("/");
var islocalHost=pathArray1[0];
*/


//document.getElementById("demo").innerHTML=portNo[0];

/*$(function(){
 //CreateCaptcha();

});
*/






function enterCaptcha(){
	$("#UserCaptchaCode").val($("#enCaptcha").val());
}





function myFunction() {
	var x = document.getElementById("UserCaptchaCode").value;
	if(x.length==6)
		{
		var result = ValidateCaptcha();
		   if(result == false) {
		     $('#WrongCaptchaError').text('Invalid Captcha! Please try again.').show();
		   //  CreateCaptcha();
		     
		     if(location.hostname=="localhost")
		     {
		     CreateCaptchaForLocalHost();
		     }
		     else{
		     CreateCaptcha();
		     }
		     $('#UserCaptchaCode').focus().val('');
		    return false;
		   }
		   
		}
	}



function CreateCaptchaForLocalHost() {
	 //$('#InvalidCapthcaError').hide();
	 var alpha = new Array('1','1','1','1','1','1');
	                   
	 var i;
	 for (i = 0; i < 6; i++) {
	   var a = alpha[Math.floor(Math.random() * alpha.length)];
	   var b = alpha[Math.floor(Math.random() * alpha.length)];
	   var c = alpha[Math.floor(Math.random() * alpha.length)];
	   var d = alpha[Math.floor(Math.random() * alpha.length)];
	   var e = alpha[Math.floor(Math.random() * alpha.length)];
	   var f = alpha[Math.floor(Math.random() * alpha.length)];
	 }
	 cd = a + ' ' + b + ' ' + c + ' ' + d +' '+e +' '+ f;
	 $('#CaptchaImageCode').empty().append('<canvas id="CapCode" class="capcode" width="300" height="80"></canvas>')
	  
	$("#enCaptcha").val(cd);
	 
	 var c =document.getElementById("CapCode"),
	     ctx=c.getContext("2d"),
	     x = c.width / 2,
	     img = new Image();
 var captchabg = document.getElementById("captchImg").src ;
 /*alert(captchabg);*/
	 img.src = captchabg ;
	 img.onload = function () {
	     var pattern = ctx.createPattern(img, "repeat");
	     ctx.fillStyle = pattern;
	     ctx.fillRect(0, 0, c.width, c.height);
	     ctx.font="46px Arial";
	     ctx.fillStyle = '#000';
	     ctx.textAlign = 'center';
	     ctx.setTransform (1, -0.12, 0, 1, 0, 15);
	     ctx.fillText(cd,x,55);
	 };
	 
	 
	 $("#UserCaptchaCode").val("111111");
	 $("#enCaptcha").val("111111");
	}



function CreateCaptcha() {
 //$('#InvalidCapthcaError').hide();
// var alpha = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
 var alpha = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
// var alpha = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
                   
 var i;
 for (i = 0; i < 6; i++) {
   var a = alpha[Math.floor(Math.random() * alpha.length)];
   var b = alpha[Math.floor(Math.random() * alpha.length)];
   var c = alpha[Math.floor(Math.random() * alpha.length)];
   var d = alpha[Math.floor(Math.random() * alpha.length)];
   var e = alpha[Math.floor(Math.random() * alpha.length)];
   var f = alpha[Math.floor(Math.random() * alpha.length)];
 }
 cd = a + ' ' + b + ' ' + c + ' ' + d+' '+e+' '+f;
 $('#CaptchaImageCode').empty().append('<canvas id="CapCode" class="capcode" width="300" height="80"></canvas>')
 
 var c =document.getElementById("CapCode"),
     ctx=c.getContext("2d"),
     x = c.width / 2,
     img = new Image();


 
	$("#enCaptcha").val(cd);
	
 var captchabg = document.getElementById("captchImg").src ;
 img.src = captchabg;
 img.onload = function () {
     var pattern = ctx.createPattern(img, "repeat");
     ctx.fillStyle = pattern;
     ctx.fillRect(0, 0, c.width, c.height);
     ctx.font="46px Arial";
     ctx.fillStyle = '#000';
     ctx.textAlign = 'center';
     ctx.setTransform (1, -0.12, 0, 1, 0, 15);
     ctx.fillText(cd,x,55);
 };
 
 
}

// Validate Captcha
function ValidateCaptcha() {
	

	
 var string1 = removeSpaces(cd);
 var string2 = removeSpaces($('#UserCaptchaCode').val());
 if (string1 == string2) {
   return true;
 }
 else {
   return false;
 }
// return false;
}

// Remove Spaces
function removeSpaces(string) {
 return string.split(' ').join('');
}

$(document).ready(function(){
// CreateCaptcha();
	
	if(location.hostname=="localhost" )
	 {
	 CreateCaptchaForLocalHost();
	 }
	 else{
	 CreateCaptcha();
	 }
	
  $("#submit").click(function(e){
// alert("hello d");
// e.preventDefault();
 var result = ValidateCaptcha();
 if( $("#UserCaptchaCode").val() == "" || $("#UserCaptchaCode").val() == null || $("#UserCaptchaCode").val() == "undefined") {
   $('#WrongCaptchaError').text('Please enter code given below in a picture.').show();
   $('#UserCaptchaCode').focus();
  return false;
 } else {
   if(result == false) {
     $('#WrongCaptchaError').text('Invalid Captcha! Please try again.').show();
   //  CreateCaptcha();
     
     if(location.hostname=="localhost")
     {
     CreateCaptchaForLocalHost();
     }
     else{
     CreateCaptcha();
     }
     $('#UserCaptchaCode').focus().val('');
    return false;
   }
   else {
     //$('#UserCaptchaCode').val('').attr('place-holder','Enter Captcha - Case Sensitive');
   //  CreateCaptcha();
     $('#WrongCaptchaError').fadeOut(100);
     $('#SuccessMessage').fadeIn(500).css('display','block').delay(5000).fadeOut(250);
     return true;
   }
 }  
  });

//alert("hello");
});
$(document).ready(function(){ 
	 var favicon = document.getElementById("favicon").src ;
	    /*alert(favicon);*/
    $('head').append('<link rel="icon" href="'+favicon+'" sizes="16x16">');
    $("form").attr("autocomplete", "off");
    });
