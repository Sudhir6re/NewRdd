//$("input[type='date']").attr("data-date-format","DD MMMM YYYY");
$.ajaxSetup({
  xhrFields: {
    withCredentials: true  
  }
});


var contextPath = window.location.pathname.split('/')[1] || ''; 
var fullUrl = window.origin + '/' + contextPath;
console.log("Full URL with Context Path: " + fullUrl);


var errorSeen = false;
function setTwoDecimalDigit(number){
	return number.toFixed(2);
}

function addErrorClass(element, msg) {
    var elementId = $(element).attr('id');
    var errorLabel = $("label#" + elementId + "-error");
    
    if (errorLabel.length === 0) { 
        $(element).after("<br class='errorbr'><label id='" + elementId + "-error' class='error " + elementId + "-error'>" + msg + "</label>");
        $(element).css("border-color", "red");
    }
}

function removeErrorClass(element) {
    var elementId = $(element).attr('id');
    $(element).css("border-color", "");
    
    var errorLabel = $("label#" + elementId + "-error");
    if (errorLabel.length > 0) {
        errorLabel.remove();
        $(".errorbr").remove();
    }
}

function showError(element, msg) {
    addErrorClass(element, msg);
}

function hideError(element) {
    removeErrorClass(element);
}

	

$(document).on('change','.removeErrorFromDropdown', function(event){
	if($(this).val()!="0"){
		removeErrorClass($(this));		
	}
  });
 
  
  $(document).on('blur change', '.preventFutureDate', function(event) {
      if ($(this).val() != "") {
          var inputValue = $(this).val();
          if (new Date(inputValue) > new Date()) {
              $(this).val("");  // Reset to today's date
              swal('You cannot select a future date!');
          }
      }
  });

	

	$(document).on('blur','.validateMobileNo', function(event){
		if($(this).val()!=""){
	var mobileNo = $(this).val();
			var regex = /^[0-9]*$/;
			if (!regex.test(mobileNo)){
				$(this).val("");
			}
			if (mobileNo.length != 10) {
				swal('Please enter 10 digit mobile No.');
				$(this).val("");
			}
			if (!(mobileNo.charAt(0) == 7 || mobileNo.charAt(0) == 8 || mobileNo
					.charAt(0) == 9)) {
				swal('Please enter valid mobile No.');
				$(this).val("");
			}

	         }
    });
	
	
	

  
  
  
$(document).on('blur','.removeErrorFromInput', function(event){
	if($(this).val()!='' || $(this).val()!=undefined){
		removeErrorClass($(this));		
	}
  });


$(document).on('blur','.removeErrorFromDate', function(event){
	if($(this).val()!='' || $(this).val()!=undefined){
		removeErrorClass($(this));		
	}
});





$(document).ready(function(){ 
	
	
	
	$(".loader").hide();
/* $('select').select2(); */
/* $('select').chosen(); */
	
	/*
	 * $(".number").keyup(function (event) { if (event.which != 8 && event.which !=
	 * 0 && event.which < 48 || event.which > 57) { this.value =
	 * this.value.replace(/\D/g, ""); } });
	 */
	
	
	$(document).on("keyup", ".number", function(event) {
		 if (event.which != 8 && event.which != 0 && event.which < 48 || event.which > 57) {
		        this.value = this.value.replace(/\D/g, "");
		      }
	});
	
	
	$(document).on("keyup", ".percentage", function(event) {
		 if (event.which != 8 && event.which != 0 && event.which < 48 || event.which > 57) {
		        this.value = this.value.replace(/\D/g, "");
		      }
	});
	
	
	
	
	 
	
	
/*
 * $(".float").keyup(function (event) { if (event.which != 8 && event.which != 0 &&
 * event.which < 48 || event.which > 57) { this.value =
 * this.value.replace(/^[-+]?[0-9]+\.[0-9]+$/, ""); } });
 */
	
	
	$(document).on('keypress','.float', function(event){
		  if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
		    event.preventDefault();
		  }
		});
	
	
	
	
	$(".amount").blur(function(e) {

		var valueToCheck = $(this).val();

		var filter = /^[0-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;

		if (!filter.test(valueToCheck)) {

			swal({

				title : 'Please provide a valid Amount',

				styling : 'bootstrap3',

				type : 'error'

			});

			$(this).val('');

			$(this).focus;

			return false;

		}

	});

	
	$(".alphacharspace ").keyup(function (event) {
		this.value = this.value.replace(/[^a-zA-z\s]/gi, "");
	});
	
	
	

	
	
	
	startTime();
	function startTime() {
		var today = new Date();

		var curr_day = today.getDate();
		var curr_month = today.getMonth();
		var curr_year = today.getFullYear();

		var days = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday',
				'Thursday', 'Friday', 'Saturday' ];

		const monthNames = [ "January", "February", "March" +
				"", "April",
				"May", "June", "July", "August", "September",
				"October", "November", "December" ];

		var monthName = monthNames[today.getMonth()];

		var dayName = days[today.getDay()];

		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();

		var ampm = h >= 12 ? 'PM' : 'AM';
		hours = h % 12;
		hours = hours ? hours : 12; // the hour '0' should be '12'

		m = checkTime(m);
		s = checkTime(s);

		document.getElementById('txt').innerHTML = dayName + ", "
				+ monthName + " " + curr_day + ", " + curr_year + " | "
				+ hours + ":" + m + ":" + s + " " + ampm;
		var t = setTimeout(startTime, 500);
		
		
		
		
	
		
/*
 * console.log("hello"); console.log(dayName + ", " + monthName + " " + curr_day + ", " +
 * curr_year + " | " + hours + ":" + m + ":" + s + " " + ampm);
 */
	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i
		}
		; // add zero in front of numbers < 10
		return i;
	}
	
	var screen2 = $(window).width();
   /* console.log(screen2); */
    if (screen2 < 768) {
    	// console.log("ala re");
    	$(".dropdown-toggle").click(function(){
    		/* console.log("ala re"); */
    		/* $(".dropdown-menu").hide(); */
    		/* $(".dropdown-toggle").siblings().find("dropdown-menu").toggle(); */
    		/*
			 * var ps =
			 * $(".dropdown-toggle").siblings().find("dropdown-menu").hide();
			 * console.log(ps);
			 */
    		$(this).next().toggle();
    		/* $(this).closest("ul").toggle(); */
    	})
    }
    var favicon = document.getElementById("favicon").src ;
    /* alert(favicon); */
   $('head').append('<link rel="icon" href="'+favicon+'" type="image/png"  sizes="16x16" data="scrtp.js">');
    /*
	 * $('head').append('<link rel="icon" href="../images/logo-v16.png"
	 * sizes="16x16" data="scrtp.js">');
	 */
    $("form").attr("autocomplete", "off");	
    
    /* Adding Search in Select Tag */
   /*
	 * $('#schemName').select2(); $('#departmentId').select2('destroy');
	 * $('#bankId').select2(); $('#branches').select2();
	 * $('#bankCode').select2(); $('#department_id').select2('destroy');
	 * $('#subdepartment_id').select2(); $('#departmentCode').select2();
	 * $('.ipdepartmentCode').select2('destroy'); for mst-department
	 * $('#subdepartmentCode').select2(); $('#districtId').select2('destroy');
	 * $('#talukaId').select2(); // $('#designationCode').select2();
	 * $('.ipdesignationCode').select2('destroy'); for mst-designation
	 * $('#level1').select2(); $('#level2').select2();
	 * 
	 * $('#responseDistrictId').select2('destroy'); $('#bankName').select2();
	 * $('#stateCode').select2(); $('#districtCode').select2();
	 * $('#cadre').select2(); $('#designationId').select2();
	 * $('#payCommision').select2(); $('#payscalelevel').select2();
	 * $('#svnthpaybasic').select2(); $('#payScale').select2();
	 * $('#postdetailid').select2(); $('#adminDepartmentId').select2();
	 * $('#bankBranchId').select2(); //$('#schemeCode').select2(); //
	 * $('#designation').select2(); $('#ddoBulk').select2();
	 * $('.designationnamecl').select2('destroy'); for mst-designation
	 * 
	 * $('select#sevaarthId').select2(); //$('#ddoCode').select2();
	 * $('select#sevaarthId').select2(); $('#ddo_code').select2();
	 * $('select#billNumber').select2();
	 */
	
	/*
	 * var loader = document.getElementById("loaderImg").src ; alert(loader);
	 * $(".loader").css("background-image", "url(" + loader + ")");
	 */
	
	
	
	$("#yearId").val(Number(new Date().getFullYear().toString().slice(-2))+1);

	$("#year").val(Number(new Date().getFullYear().toString().slice(-2))+1);
	
	
	if($("#yearName").val()=='' ){
		$("#yearName").val(Number(new Date().getFullYear().toString().slice(-2))+1);
	}
	
	
	if($("#monthName").val()=='' ){
		$("#monthName").val(new Date().getMonth()+1);
	}
	

	
	
	
	$("#month").val(new Date().getMonth()+1);
	$("#monthId").val(new Date().getMonth()+1);
	
	
});
$(document).ready(function(){ 
	setTimeout(function(){ 
		$('.Menu-item-li:empty').hide();
	}, 100);
	
	
	$("#loaderMainNew").hide();
	
	  document.onreadystatechange = function() {
          if (document.readyState !== "complete") {
              document.querySelector(
                "body").style.visibility = "hidden";
              document.querySelector(
                "#loaderMainNew").style.visibility = "visible";
          } else {
              document.querySelector(
                "#loaderMainNew").style.display = "none";
              document.querySelector(
                "body").style.visibility = "visible";
          }
      };
	
	
	
});


function dateDiffInMonth(startDate,endDate){
	var d1Y = startDate.getFullYear();
	var d2Y = endDate.getFullYear();
	var d1M = startDate.getMonth();
	var d2M = endDate.getMonth();
	var totalMonth=(d2M+12*d2Y)-(d1M+12*d1Y);
	return totalMonth;
}

function ConfirmBeforeSubRecord(msg,form) {
	swal({
		  title: "Are you sure?",
		  text: msg,
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		}).then((willDelete) => {
		    if (willDelete) {
		    	 form.submit();  
		     }
	})
}

$(document).ready(function(){ 
	
	decimalToInteger();
function decimalToInteger(){
	 $('.decimalToInteger').each(function(i, obj) {
		 var num=$(this).text();
		  var numWithoutDecimal=(''+ +num).replace(/(-?)(\d*)\.?(\d*)e([+-]\d+)/,
			        function(a,b,c,d,e) {
			          return e < 0
			            ? b + '0.' + Array(1-e-c.length).join(0) + c + d
			            : b + c + d + Array(e-d.length+1).join(0);
			        });
		  
		  
		  var result = numWithoutDecimal

		  if (isNaN(result)) {
			  $(this).text("0");
		  } else {
			  $(this).text(parseInt(numWithoutDecimal));
		  }
		});
 }
});


function dateToYMD(date) {
    var ed = new Date(date);
    var d = ed.getDate();
    var m = ed.getMonth() + 1;
    var y = ed.getFullYear();
    return '' + y + '-' + (m<=9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
}



function dateToDMY(date) {
	var today = new Date(date);
	var yyyy = today.getFullYear();
	var mm = today.getMonth() + 1; // Months start at 0!
	var dd = today.getDate();

	if (dd < 10) dd = '0' + dd;
	if (mm < 10) mm = '0' + mm;

	var formattedToday = dd + '/' + mm + '/' + yyyy;
	return formattedToday;
}





function toPlainString(num) {
    return (''+ +num).replace(/(-?)(\d*)\.?(\d*)e([+-]\d+)/,
      function(a,b,c,d,e) {
        return e < 0
          ? b + '0.' + Array(1-e-c.length).join(0) + c + d
          : b + c + d + Array(e-d.length+1).join(0);
      });
  } 



$(document).ready(
		function() {
			// $("#GPFBlock").hide();
			// $(".homemenu").hide();
			hideAllDashBoardBlock();
			
			var roleId = parseInt($("#levelRoleVal").val());
			var contextPath = $("#appRootPath").val();
			
			
			if(window.location.host=='http://10.10.84.162:8085/DCPS'){
				contextPath="http://10.10.84.162:8085/DCPS";
			}else if(window.location.host=='http://10.10.84.162:8085/DCPS/'){
				contextPath="http://10.10.84.162:8085/DCPS";
			}
			
			
			$("#context").val(contextPath);
			$("#appRootPath").val(contextPath);
			
			
			
          var homeLink="";
			
			if(contextPath==''){
			
			 homeLink = new Map([
				  [1, "../mdc/home"],
				  [2, "../ddo/home"],
				  [3, "../ddoast/home"],
				  [5, "../super/home"],
				  [4, "../user/home"]
				 
				]);
			}else{
				 homeLink = new Map([
				  [1,contextPath+"/mdc/home"],
				  [2,contextPath+"/ddo/home"],
				  [3,contextPath+"/ddoast/home"],
				  [4,contextPath+"/user/home"],
				  [5,contextPath+"/super/home"],
				]);
			}
			
			
			
			
			
			const dashBoardBlock = new Map([
				  [1, "#payrollBlock"],
				  [2, "#payrollBlock"],
				  [5, "#payrollBlock"],
				  [6, "#payrollBlock"],
				  [7, "#payrollBlock"],
				
				  
				]);
			
			
			$("#payrollLink1").attr("href",homeLink.get(roleId));
          
			
			if(roleId=='7'){
				$(".HomepageLevel").hide();
			}else{
			// $(dashBoardBlock.get(roleId)).hide();
			}
			
			
			$("#todate").val(dateToYMD(new Date()));
		});


function hideAllDashBoardBlock(){
	$("#pensionBlock").hide();
	$("#payrollBlock").hide();
	$("#GPFBlock").hide();
}

function price_in_words(price) {
	
	
	price=parseInt(price);
	
	
	  var sglDigit = ["Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"],
	    dblDigit = ["Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"],
	    tensPlace = ["", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"],
	    handle_tens = function(dgt, prevDgt) {
	      return 0 == dgt ? "" : " " + (1 == dgt ? dblDigit[prevDgt] : tensPlace[dgt])
	    },
	    handle_utlc = function(dgt, nxtDgt, denom) {
	      return (0 != dgt && 1 != nxtDgt ? " " + sglDigit[dgt] : "") + (0 != nxtDgt || dgt > 0 ? " " + denom : "")
	    };

	  var str = "",
	    digitIdx = 0,
	    digit = 0,
	    nxtDigit = 0,
	    words = [];
	  if (price += "", isNaN(parseInt(price))) str = "";
	  else if (parseInt(price) > 0 && price.length <= 10) {
	    for (digitIdx = price.length - 1; digitIdx >= 0; digitIdx--) switch (digit = price[digitIdx] - 0, nxtDigit = digitIdx > 0 ? price[digitIdx - 1] - 0 : 0, price.length - digitIdx - 1) {
	      case 0:
	        words.push(handle_utlc(digit, nxtDigit, ""));
	        break;
	      case 1:
	        words.push(handle_tens(digit, price[digitIdx + 1]));
	        break;
	      case 2:
	        words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != price[digitIdx + 1] && 0 != price[digitIdx + 2] ? " and" : "") : "");
	        break;
	      case 3:
	        words.push(handle_utlc(digit, nxtDigit, "Thousand"));
	        break;
	      case 4:
	        words.push(handle_tens(digit, price[digitIdx + 1]));
	        break;
	      case 5:
	        words.push(handle_utlc(digit, nxtDigit, "Lakh"));
	        break;
	      case 6:
	        words.push(handle_tens(digit, price[digitIdx + 1]));
	        break;
	      case 7:
	        words.push(handle_utlc(digit, nxtDigit, "Crore"));
	        break;
	      case 8:-
	        words.push(handle_tens(digit, price[digitIdx + 1]));
	        break;
	      case 9:
	        words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != price[digitIdx + 1] || 0 != price[digitIdx + 2] ? " and" : " Crore") : "")
	    }
	    str = words.reverse().join("")
	  } else str = "";
	  return str
	}


function nanTozero(amt){
	return amt ? amt : 0;
}


$(document).ready(function(){ 
	$(".btn").each(function(){
		 var i = $(this).text();
		 var j = $(this).val();
		 if(i=="Cancel" || j=="close"){ 
			 /* alert("cancel found"); */
			 var homelink = $(".homemenu1").attr("href");
	      $(this).attr('href', homelink);
	      $(this).parent().attr('href', homelink);
	       }  
	});
	function capitalize(label) {
		var words = label.split(' ');
		var CapitalizedWords = [];
		for (var i = 0; i < words.length; i++) {
			var word = words[i];
			var caps = word.charAt(0).toUpperCase() + word.slice(1);
			CapitalizedWords.push(caps);
		}
		return CapitalizedWords.join(' ');
	}
	$(".capitalize").each(function() {
		var text = $(this).text();
		var dataType = typeof text;
		if (dataType == "string" && text != null && text != '') {
			text = text.split('_').join(' ');
			$(this).text(capitalize(text.toLowerCase()));
		}
	});
	
	/* capitalize select element start */
	function capitalize(label) {
		var words = label.split(' ');
		var CapitalizedWords = [];
		for (var i = 0; i < words.length; i++) {
			var word = words[i];
			var caps = word.charAt(0).toUpperCase() + word.slice(1);
			CapitalizedWords.push(caps);
		}
		return CapitalizedWords.join(' ');
	}
	/*
	 * $(".select2-results li").each(function() { var text = $(this).text(); var
	 * dataType = typeof text; if (dataType == "string" && text != null && text !=
	 * '') { text = text.split('_').join(' ');
	 * $(this).text(capitalize(text.toLowerCase())); } });
	 */
	function capitalize(label) {
		var words = label.split(' ');
		var CapitalizedWords = [];
		for (var i = 0; i < words.length; i++) {
			var word = words[i];
			var caps = word.charAt(0).toUpperCase() + word.slice(1);
			CapitalizedWords.push(caps);
		}
		return CapitalizedWords.join(' ');
	}
	/*
	 * $("option").each(function() { var text = $(this).text(); var dataType =
	 * typeof text; if (dataType == "string" && text != null && text != '') {
	 * text = text.split('_').join(' ');
	 * $(this).text(capitalize(text.toLowerCase())); } });
	 */
	/* capitalize select element end */
});


function isValidEmail(email) {
	  // Regular expression to validate email addresses
	  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

	  // Check if the email matches the pattern
	  return emailRegex.test(email);
	}



function isValidDate(date){
	return date instanceof Date && !isNaN(date);
}


function getUserUrl(){
	var roleId=parseInt($("#levelRoleVal").val());
	var url="";
	switch(roleId){
	        case 1:
				url = "/mdc/";
				break;
			case 2:
				url = "/ddo/";
				break;
			case 3:
				url = "/ddoast/";
				break;
			case 4:
				url = "/user/";
				break;
			case 5:
				url = "/super/";
				break;
			case 6:
				url = "/mdp/";
				break;
			case 7:
				url = "/level3/";
				break;
			case 8:
				url = "/level4/";
				break;
			case 9:
				url = "/developer/";
				break;	
		}
		
		return url;
}



/*
 * $(document).ready(function() { setTimeout(function () {
 * $("select").select2(); }, 200);
 * 
 * });
 *//*
$(document).ready(function() {
    // Initialize Select2 on all select elements with class 'select2'
     $("select").select2();
    	/* $('select').each(function() {
         var preloadedValue = $(this).val(); 
         if (preloadedValue) {
             $(this).trigger('change');
         }
    	 }); */
    	 
    	 $('.select2').each(function() {
    	        var $select = $(this).closest('form').find('select').filter(function() {
    	            return $(this).hasClass('readonly');
    	        });
    	 });

/*
$(document).ready(function(){ 
	$('select').select2();
    	        // Check if the <select> element has the 'readonly' class
    	        if ($select.length) {
    	            // Find the nearest .select2-selection__rendered element and
					// add the 'readonly' class
    	            $(this).find('.select2-selection__rendered').addClass('readonly');
    	        }
    	    });*/
