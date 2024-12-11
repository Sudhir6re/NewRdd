	var expanded = false;
	function showCheckboxes(elementId) {
		var checkboxes = document.getElementById("checkboxes"+elementId);
		if (!expanded) {
			checkboxes.style.display = "block";
			expanded = true;
		} else {
			checkboxes.style.display = "none";
			expanded = false;
			
		/*	$(".lstcheckboxes").hide();
			 checkboxes = document.getElementById("checkboxes"+elementId);
		    checkboxes.style.display = "block";*/
			
			
		}
	}
	
	

	
	
	
	$(document).ready(function(){
		$(".selectedlist").each(function(){   
			var sevaarthId=$(this).attr("data");
		var selectedId=$(this).val().split(",");
		for(var i=0;i<selectedId.length;i++){
			//$('input[value="'+ selectedId[i]+ '"][class="'+sevaarthId+'"]').prop("checked",true);
			$('.'+sevaarthId+':checkbox[value="'+selectedId[i]+'"]').prop("checked",true);  
		}
	});
	});