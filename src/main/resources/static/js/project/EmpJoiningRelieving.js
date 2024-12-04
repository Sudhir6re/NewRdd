$(document)
		.ready(
				function() {
var orderNoError;
var orderDateError;
var relievedateError;
var postNameError;
var reasonError;

orderNoError = "Please Enter Order Number!";
orderDateError = "Please Enter Order Date!";
relievedateError = "Please Enter Relieve Date";
postNameError = "Please Enter Post Name";
reasonError = "Please Enter Reason For Relieving";


$('#btnSave1').click(function(e) {
var form=$("#EmpReleiving");
	e.preventDefault();
	var flag=0;
	var i=0;
	if($(".checkbox:checked").length==0){
		swal("Please Select atleast 1 Record");
		flag=1;
	}else{
		$(".checkbox").each(function(){
			if(this.checked){

				var dateOfRelieve=$("#dateofrelieving"+i).val();
				var reason=$("#reason"+i).val();
				var orderNo=$("#orderNo"+i).val();
				var orderDate=$("#orderDate"+i).val();
				 
				 if(dateOfRelieve=="" ||dateOfRelieve==undefined){
			    	   swal("Please Enter Date of Relieving!");
			    		 e.preventDefault();
			    		 flag=1;
			    	 }else if(reason=="" ||reason==undefined ||reason=='0'){
			    		 swal("Please Select Reason of Relieving!");
			    			e.preventDefault();
			    			flag=1;
			    	 }
			    	
			    	 else if(orderNo =="" ||orderNo==undefined){
			    		 swal("Please Enter Order No!");
			    			e.preventDefault();
			    			flag=1;
			    	 }
			    	 else if(orderDate=="" ||orderDate==undefined){
			    		 swal("Please Enter Order Date!");
			    		 e.preventDefault();
			    		 flag=1;
			    	 }

		}
			i++;
	});
	
	
}
if(flag==0)
	ConfirmBeforeSubRecord("Want to Relieve !",form);
	
});
	
});