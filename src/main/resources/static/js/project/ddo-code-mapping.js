   $(document).ready(function(){
	   $("#level2").hide();
   	$("#level3").hide();
   	$("#level4").hide();
        $("input[type='radio']").click(function(){
        	var radioValue = $("input[name='optradio']:checked").val();
            if(radioValue){
            	
            //   alert("Your are a - " + radioValue);
                if(radioValue==2)
                	{
                	$("#level2").show();
                	$("#level3").hide();
                   	$("#level4").hide();
                	}
                else if(radioValue==3)
            	{
            	$("#level2").show();
            	$("#level3").show();
              	$("#level4").hide();
            	}
                else
            	{
            	$("#level2").show();
            	$("#level3").show();
            	$("#level4").show();
            	}
                
            }
        });
    });