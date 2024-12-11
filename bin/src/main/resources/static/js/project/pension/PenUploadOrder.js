$("#btnSave").click(function(e) {
	var filesize = $("#fileUploads").val();
	if(filesize==''){
		swal("Please attach Pension Order");
		e.preventDefault();
	}else{
	    var fileName = document.querySelector('#fileUploads').value;
        var extension = fileName.split('.').pop(); console.log(extension);
        
        if(extension != "pdf")
        	{
        	swal("Please select valid PDF File");
        	e.preventDefault();
        	}
        
	}
});


const uploads = []
const fileSelector = document.getElementById('fileUploads')
fileSelector.addEventListener('change', (event) => {
    const file = event.target.files[0];
    var fileName = $('#fileUploads').val().replace('C:\\fakepath\\', '');
	 var ext = fileName.split('.').pop();
    const filereader = new FileReader();
    filereader.onloadend = function(evt) {
        if (evt.target.readyState === FileReader.DONE) {
            const uint = new Uint8Array(evt.target.result)
            let bytes = []
            uint.forEach((byte) => {
                bytes.push(byte.toString(16))
            })
            const hex = bytes.join('').toUpperCase()
            
            if(getMimetype(hex)!='application/pdf'){
            	swal("please select valid pdf file !!!");
            	$('#fileUploads').val('');
            }else{
            	 if(file.size>1048576){ 
			        swal("File size must be less than 1MB");
			        $('#fileUploads').val('');
            	 }
            }
        }
    }
    const blob = file.slice(0, 4);
    filereader.readAsArrayBuffer(blob);
});



const getMimetype = (signature) => {
    switch (signature) {
        case '89504E47':
            return 'image/png'
        case '47494638':
            return 'image/gif'
        case '25504446':
            return 'application/pdf'
        case 'FFD8FFDB':
        case 'FFD8FFE0':
            return 'image/jpeg'
        case '504B0304':
            return 'application/zip'
        default:
            return 'Unknown filetype'
    }
}