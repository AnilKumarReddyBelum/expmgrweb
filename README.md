
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

///^[^@]+@[a-zA-Z0-9._-]+\\.+[a-z._-]+$/;
///^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
///^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]{6,}$/;

function validateForm() {
	
	
    var inputEmailId = document.getElementById("emailId").value;
	
	if(inputEmailId !=null && inputEmailId.trim().length > 0 ){
		var lastOccurenceIndex = 0;
		for (var i = 0; i < inputEmailId.length; i++) {
			if(inputEmailId.charAt(i) === '@'){
				lastOccurenceIndex = i;
			}
		}
	alert('lastOccurenceIndex->'+lastOccurenceIndex);
	
	var email1 = inputEmailId.substring(0,lastOccurenceIndex);
	var email2 = inputEmailId.substring(lastOccurenceIndex+1);
	
	
	alert('Part1->'+email1);
    alert('Part2->'+email2);
	
		if((email1!=null && email1.trim().length > 0) && (email2!=null && email2.trim().length > 0) ){
			alert('Apply the REGEX and validate both');
			var email2Flag = validateSecondPartEmail(email2);
			alert('email2flag->'+email2Flag);
			var email1Flag = validateSecondPartEmail(email1);
			alert('email1flag->'+email1Flag);

			var emailIdValidationFlag = (email2Flag && email1Flag) ? true : false;

				if(emailIdValidationFlag)
				alert("GIVEN EMAIL ID IS VALID EMAIL ID ---> "+ inputEmailId);
				else
				alert("GIVEN EMAIL ID IS NOT A VALID EMAIL ID ---> "+ inputEmailId);

		}else{
			alert('This is not a valid email Id please check your data');
		}
	
	}
		


}

/**

Here we are allowing the a-z A-Z and . char

and '.' is not a first char.


**/
function validateSecondPartEmail(email2){

	var validateSecondPartEmailFlag = true;

	for(var i = 0; i < email2.length; i++){
        
		//First char should not be a DOT
		if(i==0 && email2.charCodeAt(i) == 46){
			validateSecondPartEmailFlag = false;
			break;
		}else  if(email2.charCodeAt(i) >= 33  && email2.charCodeAt(i) <= 47){
			continue;
		}else if(email2.charCodeAt(i) >= 58  && email2.charCodeAt(i) <= 63){
			continue;
		}else if(email2.charCodeAt(i) >= 65  && email2.charCodeAt(i) <= 126){
			continue;
		}else{
			validateSecondPartEmailFlag = false;
			break;
		}

	}

return validateSecondPartEmailFlag;

}

function validateFirstPartOfEmail(email1){

	var validateFirstPartOfEmailFlag = true;

	for(var i = 0; i < email1.length; i++){
        
		//a-z
		//A-Z
		//0-9
		//All specil chars expect the white space,tab.
		//32- white space

		if(email1.charCodeAt(i) >= 33  && email1.charCodeAt(i) <= 126){
			continue;
		}else{
			validateFirstPartOfEmailFlag = false;
			break;
		}
		

	}

return validateFirstPartOfEmailFlag;

}


/**
***************************
http://emailregex.com/
***************************

**/


</script>
</head>

<body align= "center">
<form name="myForm">
Email: <input type="text" name="email" id="emailId">
<input type="button" value="Submit" onClick="validateForm();">
</form>
</body>

</html>



