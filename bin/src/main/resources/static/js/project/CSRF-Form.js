
$('input[type="radio"][name="sameAdd"]').click(function() {
  //  $('#permanent_address').val($('#current_address').val());
//  $('#presentSamePerm').val($('#presentNotSamePerm').val());
  
  if($(this).val()=="YES")
	{
	  var presentAdd = $("#frdbno").val();
	  var presentLandmark = $("#landmark").val();
	  var presentBuilding = $("#pbv").val();
	  var presentLane = $("#rsl").val();
	  var presentTaluka = $("#alt").val();
	  var presentDistrict = $("#ctd").val();
	  var presentState = $("#stateut").val();
	  //var presentCountry = $("#presentAddCountry").val();
	  var presentPincode = $("#pincode").val();
	  var presentAddressType = $("#addresstype").val();

	  
	   $("#p_frdbno").val(presentAdd);
	  $("#p_Landmark").val(presentLandmark);
	 $("#p_pbv").val(presentBuilding);
	  $("#p_rsl").val(presentLane);
	 $("#p_alt").val(presentTaluka);
	  $("#p_ctd").val(presentDistrict);
	   $("#p_stateut").val(presentState);
	  //var presentCountry = $("#presentAddCountry").val();
	  $("#p_pincode").val(presentPincode);
	  $("#p_addresstype").val(presentAddressType);
	}
  else
	  {
	  $('.permanentAddress').prop("readOnly",false);
	  $("#p_frdbno").val("");
	  $("#p_Landmark").val("");
	 $("#p_pbv").val("");
	  $("#p_rsl").val("");
	 $("#p_alt").val("");
	  $("#p_ctd").val("");
	   $("#p_stateut").val("");
	  //var presentCountry = $("#presentAddCountry").val();
	  $("#p_pincode").val("");
	  $("#p_addresstype").val("");
	  
	  }
  });


//for reject change statement
$('#btnGenerate')
.click(
		function() {
			var countEmp = $('#count').val(); //MJP/master/GenerateTextFile'
			 if (countEmp != '') {
				$
						.ajax({
							type : "GET",
							xhrFields: {
						           responseType: 'blob'
						       },
							url : "../level1/GenerateTextFile/"
									+ countEmp,
							async : true,
							contentType : 'application/json',
							error : function(data) {
								console.log(data);
							},
							success : function(data) {
								console.log(data);
								// alert(data);
								
								 
								 var a = document.createElement('a');
						           var url = window.URL.createObjectURL(data);
						           a.href = url;
						           a.download = 'myfile.txt';
						           document.body.append(a);
						           a.click();
						           a.remove();
						           window.URL.revokeObjectURL(url);
								 setTimeout(
											function() {
												location
														.reload(true);
											}, 9000);
							}
						});
			}
			
			
			
		});


$("#submit").click(function(){
	
	
	if($('#employeeBankName').val() == '0'){
		$("#employeeBankName-error").show();
		$("#employeeBankName-error").text("Please select Bank Name");
	}
	
	$.validator.addMethod('filesize', function (value, element, param) {
	    return this.optional(element) || (element.files[0].size <= param * 1000)
	}, 'File size must be less than {0} MB');
	
	try{
	$("form[name='NPSForm']").validate({
		// Specify validation rules
		rules : {
			salutation : {
				required : true,
				min : 1
			},
			employeeFNameEn:"required",
			employeeMNameEn:"required",
			employeeLNameEn:"required",
			employeeFullNameEn:"required",
			employeeMotherName:"required",
			gender : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="gender"]:checked').val() == '' || $('input[name="gender"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			ppanNo:"required",
			dob : "required",
			employeeBirthPlace : "required",
			employeeFatherHubandName : "required",
			maritalStatus : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="maritalStatus"]:checked').val() == '' || $('input[name="maritalStatus"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			panNo:{
		    	required: true,
		    	//digits: false,
		    	pattern: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,
		    	//regex: /^[+-]{1}[0-9]{1,3}\-[0-9]{10}$/,
		    	minlength: 10,
		    	maxlength: 10, 
		    },
		    doj : "required",
		    superAnnDate : "required",
		    cityClass : {
				required :true,
				pattern: /^([A-Z]){1}?$/,
			},
		    bankAcntNo : "required",
			employeeBankName : {
				required :true,
				pattern: /^[A-Za-z.-\s]+$/,   ///^[A-Z][A-Z\s]*$/
				maxlength: 1000, 
			},
		    employeeBankBranchName : "required",
		    employeeBankBankAddress : "required",
		    employeeBankPinCode : "required",
		    bankstate : "required",
		    IfscCode : "required",
		    flatUnitNo : "required",
		    landmark : "required",
		    buildingName : "required",
		    rsl : "required",
		    locality : "required",
		    district : "required",
		    district : "required",
		    state : "required",
		    pinCode : "required",
		    addresstype : "required",
		    landlineNo : "required",
		    mobileNo1 : "required",
		    emailId : "required",
		    con_agree : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="con_agree"]:checked').val() == '' || $('input[name="con_agree"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			emailsubflag : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="emailsubflag"]:checked').val() == '' || $('input[name="emailsubflag"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			dobProof : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="dobProof"]:checked').val() == '' || $('input[name="dobProof"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			dips_name_flag : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="dips_name_flag"]:checked').val() == '' || $('input[name="dips_name_flag"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			eduQual : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="eduQual"]:checked').val() == '' || $('input[name="eduQual"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			incomeRange : {
				required : function() {
					//returns true if uid3 is empty
					return ($('input[name="incomeRange"]:checked').val() == '' || $('input[name="incomeRange"]:checked').val()==undefined);
				},
				minlength : 1,
			},
			signatureAttachmentIdnew: {
                required: true,
                extension: "jpg,jpeg",
                filesize: 300,
            },
            photoAttachmentIdnew: {
            	required: true,
            	extension: "jpg,jpeg",
            	filesize: 300,
            },
            sameAdd:"required",
            empPermanentFlatUnitNo:"required",
            empPermanentLocality:"required",
            empPermanentBuildingName:"required",
            p_rsl:"required",
            empPermanentLocality:"required",
            empPermanentDistrict:"required",
            empPermanentState:"required",
            empPermanentPinCode:"required",
            addresstypepermanent:"required",
		},
		messages : {
			salutation : " Please select salutation",
			employeeFNameEn : "Please enter Employee First Name",
			employeeMNameEn : "Please enter Employee Middle Name",
			employeeLNameEn : "Please enter Employee Last Name",
			employeeFullNameEn : "Please enter Employee Full Name",
			employeeMotherName : "Please enter Employee Mother Name",
			gender : " Please select Gender",
			ppanNo : " Please enter P Pan No",
			dob : " Please select DATE OF BIRTH",
			employeeBirthPlace : " Please enter employee birth place ",
			employeeFatherHubandName : " Please enter father or husband name",
			maritalStatus : "Please select Marital Status",
			panNo:{
			    	required : "Please enter Pan No",
					minlength : "Pan No should be atleast 10 Digit long.",
					pattern : "Please Enter Valid Pan No ",
			    },
			doj : " Please select DATE OF JOINING",
			superAnnDate : " Please select Date of Retirement",
			cityClass : "Please select Employee Class",
			bankAcntNo : "Please enter Account Number",
			employeeBankName : {
				required:"Please select employee Bank Name",
				pattern:"Please select employee Bank Name",
				maxlength:"more length",
			},
		    employeeBankBranchName : "Please enter Bank Branch name",
		    employeeBankBankAddress : "Please enter Bank Address",
		    employeeBankPinCode : "Please enter Bank Pin code",
		    bankstate :"Please enter Bank State",
		    IfscCode : "Please enter Bank IFSC code",
		    flatUnitNo :"Please enter Flat Unit No",
		    landmark : "Please enter landmark",
		    buildingName : "Please enter building name",
		    rsl : "Please enter Road/Street",
		    locality : "Please enter locality",
		    district :"Please enter district",
		    state : "Please enter district",
		    pinCode : "Please enter pin code",
		    addresstype : "Please select address type",
		    landlineNo : "Please enter landline no",
		    mobileNo1 : "Please enter mobile no",
		    emailId : "Please enter email Id",
		    con_agree : "Please select SMS Alert ",
			emailsubflag : "Please select Email Flag",
			dobProof :"Please select DOB proof",
			dips_name_flag :"please select display flag",
			eduQual :"please select Education Qualification",
			incomeRange :"please select Income Range",    
			signatureAttachmentIdnew: {
                required: "Please Select photo",
                extension: "only jpg,jpeg,png file type allowed",
                filesize:"photo should be 300 KB less",
            },
            photoAttachmentIdnew: {
            	 required: "Please Select signature",
            	 extension: "only jpg,jpeg,png file type allowed",
            	 filesize:"signature should be 300 KB less",
            },
            sameAdd:"Please select Present Address Same as Permanent Address",
            empPermanentFlatUnitNo:"Please enter permanent Flat Unit No",
            empPermanentLocality:"please enter permanent Landmark",
            empPermanentBuildingName:"Please enter Permanent BuildingName",
            p_rsl:"Please enter permanent Road/Street/Lane",
            empPermanentLocality:"Please enter permanent Area/Locality/Taluka",
            empPermanentDistrict:"Please enter permanent City/Town/District",
            empPermanentState:"Please enter permanent State/U.T",
            empPermanentPinCode:"Please enter PIN Code",
            addresstypepermanent:"Please enter Address type",
		},
		submitHandler : function(form,event) {
		     //event.preventDefault();
		//	form.submit(); 
			   var flag=true;
			   var noNominee=$("#numNominees").val();
			   var totalPercentage=0;
			   for(var i=0;i<parseInt(noNominee);i++){
				   totalPercentage=parseInt(totalPercentage)+parseInt($("#nomineePercentShare"+i).val());
			   }
			   
			   if(parseInt(totalPercentage)!=100 ){
				   alert("Nominee Share should be 100 % ");
				   flag=false;
				  // event.preventDefault();
				  // return false;
			   }
			   
			   for(var i=0;i<parseInt(noNominee);i++){
				   
				   if($("#nomineeName"+i).val()=='' || $("#nomineeName"+i).val()==undefined ){
					   addErrorClass($("#nomineeName"+i),"Please enter Nominee Name "+(i+1));
					   flag=false;
				   }else {
					   removeErrorClass($("#nomineeName"+i));
				   }
				   
				   if($("#nomineeRelation"+i).val()=="0" || $("#nomineeRelation"+i).val()==undefined ){
					   addErrorClass($("#nomineeRelation"+i),"Please enter Nominee Relation "+(i+1));
					   flag=false;
				   }else {
					   removeErrorClass($("#nomineeRelation"+i));
				   }
				   
				   if($("#nomineeDob"+i).val()=='' || $("#nomineeDob"+i).val()==undefined){
					   addErrorClass($("#nomineeDob"+i),"Please enter Nominee Dob "+(i+1));
					   flag=false;
				   }else {
				   dob = new Date($("#nomineeDob"+i).val());
				   var today = new Date();
				   var age = Math.floor((today-dob) / (365.25 * 24 * 60 * 60 * 1000));
				   if(parseInt(age)>18  && $('input[name="mstNomineeDetailsEntity['+i+'].majorMinor"]:checked').val()=='N'){
				     removeErrorClass($("#nomineeGuardianName"+i));
				     $("#nomineeGuardianName"+i).val("");
				   }else if(parseInt(age)<18  && ($("#nomineeGuardianName"+i).val()=="" || $("#nomineeGuardianName"+i).val()==undefined)){  //&& $('input[name="mstNomineeDetailsEntity['+i+'].majorMinor"]:checked').val()=='Y' 
				    addErrorClass($("#nomineeGuardianName"+i),"Please enter Guardian name "+(i+1));
				    flag=false;
				   }
					   removeErrorClass($("#nomineeDob"+i));
				   }
				   
				   if($("#nomineePercentShare"+i).val()=='' || $("#nomineePercentShare"+i).val()==undefined ){
					   addErrorClass($("#nomineePercentShare"+i),"Please enter Nominee Percent Share "+(i+1));
					   flag=false;
				   }else {
					   removeErrorClass($("#nomineePercentShare"+i));
				   }
				   
				   if($("#nomineeAddress"+i).val()=='' || $("#nomineeAddress"+i).val()==undefined ){
					   addErrorClass($("#nomineeAddress"+i),"Please enter Nominee Address "+(i+1));
					   flag=false;
				   }else {
					   removeErrorClass($("#nomineeAddress"+i));
				   }
				   
				/*   if(($("#nomineeGuardianName"+i).val()=='' || $("#nomineeGuardianName"+i).val()==undefined) && parseInt(age)<18){
					   addErrorClass($("#nomineeGuardianName"+i),"Please enter Guardian name "+(i+1));
					   flag=false;
				   }else {
					   $("#nomineeGuardianName"+i).val("");
					   removeErrorClass($("#nomineeGuardianName"+i));
				   }*/
				   
				 //  $("#noMineeDiv>table").length;
				   
				   
				   if($('input[name="mstNomineeDetailsEntity['+i+'].majorMinor"]:checked').val()==undefined){
					   $("#majorMinor"+i).show();
					   $("#majorMinor"+i).text("Please select Major/Minor Nominee");
					   flag=false;
				   }else {
					  $("#majorMinor"+i).hide();
					  $("#majorMinor"+i).text("Please select Major/Minor Nominee");
				   }
				   
				   
				   if($('input[name="mstNomineeDetailsEntity['+i+'].majorMinor"]:checked').val()=="N" && $("#nomineeGuardianName"+i).val()!='' ){
					   swal("Nominee is major please remove Guardian name");
					   flag=false;
				   }
				   
				   
			   }
			   if(flag==true){
			     form.submit(); 
			   }else{
				  $('html, body').animate({
				         scrollTop: ($('#nomineeName0').offset().top - 300)
				    }, 2000);
				   event.preventDefault();
			   }
		},
			});
	}catch(err){
		console.log("error is"+err);
	}
});




$(document).on('click','.delete', function(event){
	var noNominee=$("#numNominees").val();
	 $("#tbl"+noNominee).remove();
	 
	 $("#numNominees").val(Number(noNominee)-1);
	 
	 
	 noNominee=$("#numNominees").val();
	 
	 if(parseInt(noNominee)<3){
		 $("#addNomineess").prop("disabled",false);
	}
	 
	 
  });


function addRow(index){
	counter=parseInt(index)+1;
	var relationList='<option value="0">Please Select</option> <option value="Husband">Husband</option> <option value="Wife">Wife</option> <option value="Son">Son</option> <option value="Daughter">Daughter</option> <option value="Other">Other</option> <option value="Father">Father</option> <option value="Mother">Mother</option> <option value="Brother">Brother</option>';
var newNomineeRow='<br><table class="table table-bordered"  id="tbl'+counter+'"> <tbody> <tr> <td colspan="4"><legend style="margin-bottom: 0 !important;"> <span >Nominee '+counter+' Detail </span>     <span class="glyphicon glyphicon-trash delete" style="float:right;"  data="tbl'+counter+'"></span> </legend></td> </tr> <tr> <td width="25%" align="left">Nominee Name</td> <td width="25%" align="left"><input type="text" id="nomineeName'+index+'" name="mstNomineeDetailsEntity[' + index + '].nomineename" maxlength="120" class="form-control"></td> <td width="25%" align="left" >Relationship with the Nominee</td> <td width="25%" align="left"><select id="nomineeRelation'+index+'"   name="mstNomineeDetailsEntity[' + index + '].relation" class="form-control">'+relationList+'</select></td> </tr> <tr> <td width="25%" align="left" >Date of Birth</td> <td><input type="date" id="nomineeDob'+index+'"  name="mstNomineeDetailsEntity[' + index + '].dob"  class="datepicker form-control" title="dd/mm/yyyy" value="" maxlength="10"></td> <td width="25%" align="left" ><label>Nominee Percentage Share</label></td> <td width="25%" align="left"><input type="text" id="nomineePercentShare'+index+'"  name="mstNomineeDetailsEntity[' + index + '].percent_share" maxlength="99" value="" class="form-control"></td> </tr> <tr> <td width="25%" align="left">Nominees Guardian Details (in case of a minor)</td> <td width="25%" align="left"><span >Nominee Guardian Name</span> <input type="text"   id="nomineeGuardianName'+index+'"  name="mstNomineeDetailsEntity[' + index + '].guardianName"  maxlength="99" class="form-control"></td> <td width="25%" align="left"><label>Nominee Address</label></td> <td width="25%" align="left"> <span >Nominee Condition rendering</span> <input type="text" id="nomineeAddress'+index+'" name="mstNomineeDetailsEntity['+index+'].nomineeaddress" maxlength="40" value="" class="form-control"></td> </tr> <tr> <td width="25%" align="left"><label>Major/Minor</label></td> <td width="25%" align="left"><label>Major</label> <input type="radio" value="N" id="mstNomineeDetailsEntity['+index+'].majorMinor" name="mstNomineeDetailsEntity['+index+'].majorMinor" checked="checked">     <label>Minor</label> <input type="radio" name="mstNomineeDetailsEntity['+index+'].majorMinor" id="mstNomineeDetailsEntity['+index+'].majorMinor" value="Y">   <br><label id="majorMinor'+index+'""  class="error" style="display:none;" >Please select Major/Minor Nominee'+index+'</label></td> </tr> </tbody> </table>';
	$("#noMineeDiv").append(newNomineeRow);
	var noNominee=$("#numNominees").val();
	$("#numNominees").val(parseInt(noNominee)+1);
}

$("#addNomineess").click(function(e){
	 e.preventDefault();
	var noNominee=$("#numNominees").val();
	if(parseInt(noNominee)<3){
		 var index=parseInt(noNominee);
		 addRow(index);
		 e.preventDefault();
	}else{
		 e.preventDefault();
		 $("#addNomineess").prop("disabled",true);
	}
});

var errorSeen = false;

function addErrorClass(element,msg){
	  var elementId=$(element).attr('id');
  var errorMessageVisible = $("#"+elementId+"-error").is(":visible");
  if (errorMessageVisible === false) {
	  element.after("<br><label id='"+elementId+"-error'  class='error' >"+msg+".</label>");
	  element.css("border-color", "red");
      console.log("can't find error");
    }
}

function removeErrorClass(element){
	var elementId=$(element).attr('id');
	 element.css("border-color", "");
	     var errorMessageVisible =  $("#"+elementId+"-error").is(":visible");
	     if (errorMessageVisible){
	        $("#"+elementId+"-error").remove();
	        console.log("can't find error");
	     }
}


$('#photoAttachmentIdnew').change(function(){
  readFile(this,1);
  });


$('#signatureAttachmentIdnew').change(function(){
	readFile(this,2);
});





function readFile(input,type){
	  const file = input.files[0];
	    if (file){
	      let reader = new FileReader();
	      reader.onload = function(event){
	        if(type==1)
	           $('#imagePath').attr('src', event.target.result);
	        else
	          $('#imagePathSign').attr('src', event.target.result);
	      }
	      reader.readAsDataURL(file);
	    }
}




/*$(".table").on("change", ".allowCheckBox", function() {
	
	
	  var row = $(this).closest("tr");
	  var chk =row.find(".allowCheckBox").prop("checked");
	
	  
	  row.find(".checkbox").val(chk);
	});
*/



