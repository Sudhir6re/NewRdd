$(document).ready(function() {
   var table= $('#tblDataTable').DataTable();
    
    $('#tblDDOScreen').DataTable( {
        "order": [[ 0, "desc" ]]
    } );

    
    
    $('#tblPendingDDOApproval').DataTable();
    $('#tblApprovedDDO').DataTable();
    
    $('#oldData').DataTable();
    $('#datatableNew').DataTable();
    
});




