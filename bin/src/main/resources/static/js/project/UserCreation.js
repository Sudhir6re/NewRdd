	$("form[name='userCreation']").validate({
		rules : {
			userName : "Required",
			fullName : "Required",
		},
		messages : {
			userName : "Enter UserName",
			fullName : "Enter Full Name",
		},
	});
	