
var numberCheck;

$(document).ready(function() {
	numberCheck = $("#newProdId").val(); // just initialize , not mandatory
	
	//console.log($('#newProdId').val().charAt(0));
	//At initialy if first char is zero then erase it , 
	//other wise java take it as an octal (base 8) number [eg: 067 of octal =  55 of decimal].
	if($("#newProdId").val().charAt(0) == 0){
		$('#newProdId').val($('#newProdId').val().replace('0',''));
	}
	
	$("#newProdId").ForceNumericOnly();
	
	$("#newProdId").bind('paste', function(e) {
		var data = e.originalEvent.clipboardData.getData('Text');
		var replaceData = data.replace(/e/g,""); // delete all e when copy paste to input field // 34e2 -> 342
		replaceData = replaceData.replace(/E/g,""); //  delete all E when copy paste to input field // 34E2 -> 342
		replaceData = replaceData.replace('+',''); // not ok , but not problem +473 //
		replaceData = replaceData.replace(/-/g,"");// delete all - when copy paste to input field -383 -> 383
		//console.log("Clipboard :: "+data+" replace :: "+replaceData);
		
		zeroDelete(replaceData);
		
    	setTimeout(function () { 
        	//console.log('input event handled including paste event');
    		$('#newProdId').val(numberCheck);
    	}, 0);
	});	
	

	$('#newProdId').on('keyup change', function(){
		var newVal = $('#newProdId').val();
		
		if(newVal <= 0){
			// setTimeout method use to set 1 to input
			setTimeout(function () { 
    			$('#newProdId').val(1);
    		}, 0);
		}
		
		zeroDelete(newVal);
		
		$('#newProdId').val(numberCheck);
	});
});

function zeroDelete(value){
	numberCheck = value;
	if(value == ''){
		console.log("returning...");
		return;
	}
	
	console.log("not return for loop check")
	if(value.charAt(0) == 0){
		var newVal = value.replace('0','');
		console.log("NEWVAL ::"+newVal);
		zeroDelete(newVal);
	}
	console.log("end looping....")
}

// Numeric only control handler
jQuery.fn.ForceNumericOnly =
function()
{
    return this.each(function()
    {
        $(this).keydown(function(e)
        {
            var key = e.charCode || e.keyCode || 0;
            //only 0 - 9
            return (
			key == 8 ||  // backspace
			key == 9 ||  // tab
			key == 33 ||  // page up
			key == 34 ||  // page down
			key == 35 ||  // end
            key == 46 || // delete
			(key >= 37 && key <= 40)|| // left up right down 
			(key >= 48 && key <= 57)   // 0 to 9
            );
        });
    });
};

function checkProductAlreadyExist(){
	num = $('#newProdId').val();
	csrfValue = $("input[name=_csrf]").val();
	
	url = contextPath+"prod/check_product";
	params = {id: num,_csrf: csrfValue};
	
	$.post(url, params, function(response){
		alert(response);
		if(response == "NO_PRODUCT"){
		$('#message').html("NO product").css('color', 'green');
		}else{
		$('#message').html(response.name).css('color', 'green');
		}
	})
}

