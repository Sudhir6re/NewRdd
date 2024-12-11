$("#tblDataTable").on('change',".percentage",function(){
	
	var row = $(this).closest("tr");
	var percentage=parseInt(row.find('.percentage').val());
	var basic=parseInt(row.find('.basicSal').text());
	
	console.log("basic=="+basic);
	if(percentage > '0' && percentage <='100')
		{
	var result=(basic*percentage)/100;
		}
	else
		{
		swal("Percentage should greater than 0 and less than 100..");
		}
	
	console.log("result="+result);
	row.find(".revisedSal").val(result);
//	alert(percentage);s

});


