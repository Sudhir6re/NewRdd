 
/*function addErrorClass(element,msg){
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
*/


                    var varMessage = $("#message").val();
					if (varMessage != "" && varMessage != undefined) {
						swal('' + varMessage, {
							icon : "success",
						});
					}




$("#select_all").change(function(){
if($(this).prop("checked")){
						    	$(".checkbox").prop("checked",true);
						}else{
							  $(".checkbox").prop("checked",false);
						}
});

$("form[name='GPFLegacyForm']").validate({
		        // Specify validation rules
		        submitHandler: function(form,e) {
		        	e.preventDefault();
		        	var flag=0;
		        	var i=0;
		        	if($(".checkbox:checked").length==0){
		        		swal("Please Select atleast 1 Record");
		        		flag=1;
		        	}else{
		        		$(".checkbox").each(function(){
		        			if(this.checked){
		        				var gpfOpeningBal=$("#gpfOpeningBal"+i).val();
		        				var dateRegPay=$("#dateRegPay"+i).val();
		        				if(gpfOpeningBal==''){
		        					addErrorClass($("#gpfOpeningBal"+i),"Please Enter GPF Opening Balance");
		        					flag=1;
		        				}
		        				if(dateRegPay==''){
		        					addErrorClass($("#dateRegPay"+i),"Please Enter Date of Regular PayScale");
		        					flag=1;
		        				}
		        			}else{
		        				removeErrorClass($("#gpfOpeningBal"+i));
		        				removeErrorClass($("#dateRegPay"+i));
		        			}
		        			i++;
		        		});
		        	}
		        	if(flag==0)
		        	ConfirmBeforeSubRecord("Want to Forward To Next Level !",form,);
		          /// form.submit();
		        }
		      });		



$("form[name='GPFLegacyForm1']").validate({
    // Specify validation rules
    submitHandler: function(form,e) {
    	e.preventDefault();
    	var flag=0;
    	var i=0;
    	if($(".checkbox:checked").length==0){
    		swal("Please Select atleast 1 Record");
    		flag=1;
    	}else{
    		$(".checkbox").each(function(){
    			if(this.checked){
    				var gpfOpeningBal=$("#gpfOpeningBal"+i).val();
    				var dateRegPay=$("#dateRegPay"+i).val();
    				if(gpfOpeningBal==''){
    					addErrorClass($("#gpfOpeningBal"+i),"Please Enter GPF Opening Balance");
    					flag=1;
    				}
    				if(dateRegPay==''){
    					addErrorClass($("#dateRegPay"+i),"Please Enter Date of Regular PayScale");
    					flag=1;
    				}
    			}else{
    				removeErrorClass($("#gpfOpeningBal"+i));
    				removeErrorClass($("#dateRegPay"+i));
    			}
    			i++;
    		});
    	}
    	if(flag==0)
        form.submit();
    }
  });
		