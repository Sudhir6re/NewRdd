$(document).ready(function(){
		  				 // alert("DDO CODE is "+departmentId);
	            /*var countryId=1;
		  			    	 if (countryId != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "fetchStates/"+countryId,
		  						      async: true,
		  						      contentType:'application/json',
		  						      error: function(data){
		  						    	 //console.log(data);
		  						      },
		  						      success: function(data){
		  						    	 //console.log(data);
		  						    	  //alert(data);
		  						    	 var len=data.length;
		 						    	  if(len!=0)
		 						    		  {
		 						    	//	console.log(data);
		 						    		 $('#state').empty();
		 						    		$('#state').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#state').append("<option  value="+value[1]+">" + value[2] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#state').empty();
		 						    		 $('#state').append("<option value='0'>Please Select</option>");
		 						    		  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }*/
		  			    		
	     
	     
	     //for fetching city  associates with states
	     $("#state").change(function() 
		  			{
		  				  var stateId = $("#state").val();
		  				 // alert("DDO CODE is "+departmentId);
		  			    	 if (stateId != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "fetchDistricts/"+stateId,
		  						      async: true,
		  						      contentType:'application/json',
		  						      error: function(data){
		  						    	 //console.log(data);
		  						      },
		  						      success: function(data){
		  						    	 //console.log(data);
		  						    	  //alert(data);
		  						    	 var len=data.length;
		 						    	  if(len!=0)
		 						    		  {
		 						    		//console.log(data);
		 						    		 $('#district').empty();
		 						    		$('#district').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#district').append("<option value="+value[3]+">" + value[4] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#district').empty();
		 						    		 $('#district').append("<option value='0'>Please Select</option>");
		 						    		  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }
		  			    		
		  			});
	     
	     
	     
	   //for fetching tauka associates with state
	     $("#district").change(function() 
		  			{
	    	// alert("hello");
		  				  var districtCode = $("#district").val();
		  				 // alert("DDO CODE is "+departmentId);
		  				var stateId = $("#state").val();
		  			    	 if (countryId != '') 
		  			    	 {
		  			    		 $.ajax({
		  						      type: "GET",
		  						      url: "fetchTaluka/"+districtCode+"/"+stateId,
		  						      async: true,
		  						      contentType:'application/json',
		  						      error: function(data){
		  						    	 //console.log(data);
		  						      },
		  						      success: function(data){
		  						    	 //console.log(data);
		  						    	  //alert(data);
		  						    	 var len=data.length;
		 						    	  if(len!=0)
		 						    		  {
		 						    		//console.log(data);
		 						    		 $('#taluka').empty();
		 						    		$('#taluka').append("<option value='0'>Please Select</option>");
		 							    	 var temp = data;
		 							   		  $.each( temp, function( index, value ){
		 								    		console.log( value[2] ); 
		 								    		 $('#taluka').append("<option value="+value[6]+">" + value[5] + "</option>");
		 							    		});
		 						    		  }
		 						    	  else
		 						    		  {
		 						    		 $('#taluka').empty();
		 						    		 $('#taluka').append("<option value='0'>Please Select</option>");
		 						    		//  swal("Record not found !!!");
		 						    		  }
		  						    	}
		  						 });	
		  			    	 }
		  			    		
		  			});
	     
	
	    	 
	    	 $("#fName, #mName, #lName").keyup(function(){
	    	  var fname=$("#fName").val();
	    	  var mname=$("#mName").val();
	    	  var lname=$("#lName").val();
//	    	  $("#fName").val().toUpperCase()
	    	  
	    	  
	    	  
	    	  setTimeout(() => {
			    	 var fNamemr = $('#fNamemr').val();
			           var mNamemr = $('#mNamemr').val();
			           var lNamemr = $('#lNamemr').val();
			           if (fNamemr || mNamemr || lNamemr) {
			               $('#fullNameInDevnagri').val(fNamemr + ' ' + mNamemr+' '+lNamemr)
			           } 
			           $("#fullName").val(fname.toUpperCase()+" "+mname.toUpperCase()+" "+lname.toUpperCase());
			    	}, 2000); 
	    	  
	    	  
	    	  
	    	  
	    	  
	    	//  $("#fullNameInDevnagri").val(fname.toUpperCase()+" "+mname.toUpperCase()+" "+lname.toUpperCase());
	    	  });
	   
	     /*
	     $("#mName").keyup(function()	 {

	    	  var fname=$("#fName").val();
	    	  var mname=$("#mName").val();
	    	  var lname=$("#lName").val();
	    	  
	    	  $("#fatherName").val(mname.toUpperCase());
	    	  
	    	  $("#fullName").val("");
	    	  $("#fullName").val(fname.toUpperCase()+" "+mname.toUpperCase()+" "+lname.toUpperCase());
	    	
	    	   var fNamemr = $('#fNamemr').val();
	           var mNamemr = $('#mNamemr').val();
	           var lNamemr = $('#lNamemr').val();
	           if (fNamemr || mNamemr || lNamemr) {
	               $('#fullNameInDevnagri').val(fNamemr + ' ' + mNamemr+''+lNamemr)
	           } 
	    	  
	    	  //$("#fullNameInDevnagri").val(fname.toUpperCase()+" "+mname.toUpperCase()+" "+lname.toUpperCase());
	    	    
	    	    
	    	  })
	     $("#lName").keyup(function()
	    		 {

	    	  var fname=$("#fName").val();
	    	  var mname=$("#mName").val();
	    	  var lname=$("#lName").val();
	    	  $("#fullName").val("");
	    	  
	    	  $("#fullName").val(fname.toUpperCase()+" "+mname.toUpperCase()+" "+lname.toUpperCase());
	    	  
	    	   var fNamemr = $('#fNamemr').val();
	           var mNamemr = $('#mNamemr').val();
	           var lNamemr = $('#lNamemr').val();
	           if (fNamemr || mNamemr || lNamemr) {
	               $('#fullNameInDevnagri').val(fNamemr + ' ' + mNamemr+''+lNamemr)
	           } 
	    	  
	    	//  $("#fullNameInDevnagri").val(fname.toUpperCase()+" "+mname.toUpperCase()+" "+lname.toUpperCase());
	    	  });
	     
*/	     
	     
	     $("#bankId").change(function() 
	    		 {
	    		 	  var bankId = $("#bankId").val();
	    		 //alert("bank  is "+bankId);
	    		     	 if (bankId != '') 
	    		     	 {
	    		     		 $.ajax({
	    		 			      type: "GET",
	    		 			      url: "mstBank/"+bankId,
	    		 			      async: true,
	    		 			      contentType:'application/json',
	    		 			      error: function(data){
	    		 			    	 //console.log(data);
	    		 			      },
	    		 			      success: function(data){
	    		 			    	 //console.log(data);
	    		 			    	  //alert(data);
	    		 			    	 var len=data.length;
	    		 			    	  if(len!=0)
	    		 			    		  {
	    		 			    		//console.log(data);
	    		 			    		 $('#branches').empty();
	    		 			    		$('#branches').append("<option value='0'>Please Select</option>");
	    		 				    	 var temp = data;
	    		 				   		  $.each( temp, function( index, value ){
	    		 					    		console.log( value[3] ); 
	    		 					    		 $('#branches').append("<option  data-id="+value[6]+" value="+value[0]+">" + value[3] + "</option>");
	    		 				    		});
	    		 			    		  }
	    		 			    	  else
	    		 			    		  {
	    		 			    		 $('#branches').empty();
	    		 			    		 $('#branches').append("<option value='0'>Please Select</option>");
	    		 			    		//  swal("Record not found !!!");
	    		 			    		  }
	    		 			    	}
	    		 			 });	
	    		     	 }
	    		     	 
	    		     	
	    		     		
	    		 });
	     
	     $("#branches").change(function() 
	    		 {
	    	 var ifsc=$('option:selected', this).attr('data-id');
	    	    $('#ifscCode').val(ifsc);
	    		 });
	     
	     
	     
	     
	     $("#payCommision").change(function() 
	    		 {
	    	 var payCommissionId= $('#payCommision').val();
	    	 {
	     		 $.ajax({
	 			      type: "GET",
	 			      url: "employeeConfiguration1/"+payCommissionId,
	 			      async: true,
	 			      contentType:'application/json',
	 			      error: function(data){
	 			    	 //console.log(data);
	 			      },
	 			      success: function(data){
	 			    	 //console.log(data);
	 			    	  //alert(data);
	 			    	 var len=data.length;
	 			    	  if(len!=0 && (payCommissionId == 2500341 || payCommissionId == 2))
	 			    		  {
	 			    		//console.log(data);
	 			    		  
	 			    		 
	 			    		 $('#payscaleLevels').text("Pay Scale");
	 			    		 $('#payScale').empty();
	 			    		$('#payScale').append("<option value='0'>Please Select</option>");
	 				    	 var temp = data;
	 				   		  $.each( temp, function( index, value ){
	 					    		console.log( value[3] ); 
	 					    		 $('#payScale').append("<option  data-id="+value[0]+" value="+value[0]+">" + value[1] + "</option>");
	 				    		});
	 			    		  }
	 			    	  
	 			    	  else if(len!=0 && (payCommissionId == 2500347 || payCommissionId == 8))
			    		  {
			    		//console.log(data);
	 			    		 $('#payscaleLevels').text("Pay Levels");
			    		 $('#payScale').empty();
			    		$('#payScale').append("<option value='0'>Please Select</option>");
				    	 var temp = data;
				   		  $.each( temp, function( index, value ){
					    		console.log( value[3] ); 
					    		 $('#payScale').append("<option  data-id="+value[0]+" value="+value[0]+">" + value[3] + "</option>");
				    		});
			    		  }
	 			    	  
	 			    	  else
	 			    		  {
	 			    		 $('#payScale').empty();
	 			    		 $('#payScale').append("<option value='0'>Please Select</option>");
	 			    		//  swal("Record not found !!!");
	 			    		  }
	 			    	}
	 			 });	
	     	 }
	    	
	    		 });
	     
	     
	     function readURL(input)
	     {
	    	    if (input.files && input.files[0]) {
	    	        var reader = new FileReader();

	    	        reader.onload = function (e) {
	    	            $('#blah').attr('src', e.target.result);
	    	            $('#blah').show();
	    	        }

	    	        reader.readAsDataURL(input.files[0]);
	    	    }
	     }

	    	$("#signature").change(function(){
	    	    readURL(this);
	    	});
	     
	    	
	    	function readURL1(input) {
	    	    if (input.files && input.files[0]) {
	    	        var reader = new FileReader();

	    	        reader.onload = function (e) {
	    	            $('#photoView').attr('src', e.target.result);
	    	            $('#photoView').show();
	    	        }

	    	        reader.readAsDataURL(input.files[0]);
	    	    }
	    	}

	    	$("#photo").change(function(){
	    	    readURL1(this);
	    	});
	    	
	    	 //for fetching current post  associates with designation
		     $("#designation").change(function() 
			  			{
			  				  var designationId = $("#designation").val();
			  				 // alert("DDO CODE is "+departmentId);
			  			    	 if (designationId != '') 
			  			    	 {
			  			    		 $.ajax({
			  						      type: "GET",
			  						      url: "employeeConfigurationGetCurrentPost/"+designationId,
			  						      async: true,
			  						      contentType:'application/json',
			  						      error: function(data){
			  						    	 //console.log(data);
			  						      },
			  						      success: function(data){
			  						    	 //console.log(data);
			  						    	  //alert(data);
			  						    	 var len=data.length;
			 						    	  if(len!=0)
			 						    		  {
			 						    		//console.log(data);
			 						    		 $('#currentPost').empty();
			 						    		$('#currentPost').append("<option value='0'>Please Select</option>");
			 							    	 var temp = data;
			 							   		  $.each( temp, function( index, value ){
			 								    		console.log( value[2] ); 
			 								    		 $('#currentPost').append("<option value="+value[0]+">" + value[1] + "</option>");
			 							    		});
			 						    		  }
			 						    	  else
			 						    		  {
			 						    		 $('#currentPost').empty();
			 						    		 $('#currentPost').append("<option value='0'>Please Select</option>");
			 						    		  swal("Record not found !!!");
			 						    		  }
			  						    	}
			  						 });	
			  			    	 }
			  			    		
			  			});
		     //end designation
		     
		     
		     //for fetching cadre
		     
		     /*
		     $("#cadre").change(function() 
			  			{
		                 	 var cadreId= $("#cadre").val();
			  				 // alert("DDO CODE is "+departmentId);
			  			    	 if (cadreId != '') 
			  			    	 {
			  			    		 $.ajax({
			  						      type: "GET",
			  						      url: "employeeConfiguration/"+cadreId,
			  						      async: true,
			  						      contentType:'application/json',
			  						      error: function(data){
			  						    	 //console.log(data);
			  						      },
			  						      success: function(data){
			  						    	  
			  						    	 console.log(data);
			  						    	var temp=data;
			  							//	$scope.age='';
			  								//$scope.group='';
			  								 $.each(temp, function( index, value ){
			  							    		 $('#age').val(value[6]);
			  							    		 $('#group').val(value[1]);
			  							    		 
			  							    		
			  							    		
			  							    		
			  							    		var addY=value[6];
			  							    		 var d= $('#dob').val();
			  							    		 
			  							    	//	 alert("dob is"+d);
			  							    		 
			  							    		 var d1=new Date(d);
			  							    		 
			  							    		// alert(d);
			  							    		 
			  							    		  var year = d1.getFullYear();
			  							             // alert(year);
			  							              var month = d1.getMonth();
			  							              var day = d1.getDate();
			  							              var c = new Date(year + addY, month, day);
			  							             
			  							              
			  							            // alert("updated date is"+c);
			  							           
			  							           //  var nd=year + addY;
			  							             var ny=c.getFullYear();
			  							             var nm=c.getMonth();
			  							             var nd=c.getDate();
			  							              
			  							             
			  							           console.log(""+ny+"-"+nm+"-"+nd);
			  							           
			  							           var v=
			  							             
			  							           $('#retiringDate').val("2017-12-01");
			  							             
			  							              
			  							           // $('#retiringDate').val(c);
			  							              
			  							              //
			  						    		});
			  						    	}
			  						 });	
			  			    	 }
			  			    		
			  			});
		     //end cadre*/
		     
		     
		     $("#payScale").change(function() 
		    		 {
		    	 var payScale= $('#payScale').val();
		    	 if (payScale != '') 
			    	 {
			    		 $.ajax({
						      type: "GET",
						      url: "employeeConfigurationGetGradePay/"+payScale,
						      async: true,
						      contentType:'application/json',
						      error: function(data){
						    	 //console.log(data);
						      },
						      success: function(data){
						    	 var len=data.length;
						    	 console.log(data);
						    	  if(len!=0)
						    		  {
						    			 $('#gradePay').val('');
						    			 
						    			 $('#basicPay').val("");
						    		
							    	 var temp = data;
							   		  $.each( temp, function( index, value ){
								    		console.log( value[2] ); 
								    		 $('#gradePay').val(value[2]);
								    		 
								    		 var payBandVal=$('#payInPayBand').val();
								    		 
								    		 var third=parseInt(payBandVal) + parseInt(value[2]);
								    		 
								    		 $('#basicPay').val(third);
								    		 
							    		});
						    		  }
						    	  else
						    		  {
						    		 $('#gradePay').val('');
						    		 $('#basicPay').val('');
						    		  swal("Record not found !!!");
						    		  }
						    	}
						 });	
			    	 }
		    	
		    		  });
		     
		     
		     
		    
		     
		     $("#payInPayBand").keyup(function() 
		    		 {
		    	 var payBandVal=$('#payInPayBand').val();
		     	 var gradePay=$('#gradePay').val();
		  		 var third=parseInt(payBandVal) + parseInt(gradePay);
		  		$('#basicPay').val("");
		     	$('#basicPay').val(third);
	    		 
	  
		    		 });
		    		 
		     
	    	
	  });
// Load the Google Transliterate API
/*google.load("elements", "1", {
      packages: "transliteration"
    });*/

function onLoad() {
  var options = {
      sourceLanguage:
          google.elements.transliteration.LanguageCode.ENGLISH,
      destinationLanguage:
          [google.elements.transliteration.LanguageCode.HINDI],
      shortcutKey: 'ctrl+g',
      transliterationEnabled: true
  };

  // Create an instance on TransliterationControl with the required
  // options.
  var control =
      new google.elements.transliteration.TransliterationControl(options);

  // Enable transliteration in the textbox with id
  // 'transliterateTextarea'.
  control.makeTransliteratable(['fullNameInDevnagri']);
}
//google.setOnLoadCallback(onLoad);