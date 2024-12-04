/*$(document).ready(function() {
	
	$("#departmentDeskLevel1").hide();
	$("#departmentDeskLevel2").hide();
	$("#departmentDeskLevel3").hide();
	
	if($("#levelRoleVal").val()==1) {
		$("#departmentDeskLevel1").hide();
		$("#departmentDeskLevel2").hide();
		$("#departmentDeskLevel3").hide();
	}
	else if($("#levelRoleVal").val()==2) {
		$("#departmentDeskLevel1").show();
		$("#departmentDeskLevel2").hide();
		$("#departmentDeskLevel3").hide();
	}
	else if($("#levelRoleVal").val()==3) {
		$("#departmentDeskLevel1").show();
		$("#departmentDeskLevel2").show();
		$("#departmentDeskLevel3").hide();
	}
	
	$("#tblPendingDDOApproval").hide();
	$("#tblApprovedDDO").hide();

	$("#tblApprovedDDO_length").hide();
	$("#tblApprovedDDO_filter").hide();
	$("#tblApprovedDDO_info").hide();
	$("#tblApprovedDDO_paginate").hide();

	$("#tblPendingDDOApproval_length").hide();
	$("#tblPendingDDOApproval_filter").hide();
	$("#tblPendingDDOApproval_info").hide();
	$("#tblPendingDDOApproval_paginate").hide();
	
	var lenAPPROVED = $("#lstApprovedDDOSize").val();
	if (lenAPPROVED == '1') {
		$("#tblApprovedDDO").show();
		$("#tblApprovedDDO_length").show();
		$("#tblApprovedDDO_filter").show();
		$("#tblApprovedDDO_info").show();
		$("#tblApprovedDDO_paginate").show();

		
	} else if(lenAPPROVED == '0') {
		swal("No Record Found!", "","warning");
	}
	var lenToBeApprove = $("#lstDDOTOBeApprovedSize").val();
	if (lenToBeApprove == '1') {
		$("#tblPendingDDOApproval").show();
		$("#tblPendingDDOApproval_length").show();
		$("#tblPendingDDOApproval_filter").show();
		$("#tblPendingDDOApproval_info").show();
		$("#tblPendingDDOApproval_paginate").show();
		
	} else if(lenToBeApprove == '0') {
		swal("No Record Found!", "", "warning");
	}
});


function ConfirmApprove(ddoRegID) {
		swal({
			  title: "Are you sure?",
			  text: "You are approving this DDO !",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			}).then((willDelete) => {
			    if (willDelete) {   
					$.ajax({
					      type: "GET",
					      url: "/home/dashboard/apprPendingDDO/"+ddoRegID,
					      async: true,
					      success: function(data){
					    	  swal("Approved successfully !", {
					    	      icon: "success",
					    	  });
					    	  setTimeout(function() {
								    location.reload(true);
								}, 3000);
					      }
					 });
			     }
		})
}*/