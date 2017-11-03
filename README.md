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
			
		}else{
			
			alert('This is not a valid email Id please check your data');
			
		}
	
	}
		

	/* var mailformat = /^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]{6,}$/;
	if(x.match(mailformat)){
		alert('Valid Email');
	}else{
		alert('In Valid Email');
	}	
	 */
	
}



</script>
</head>

<body>
<form name="myForm">
Email: <input type="text" name="email" id="emailId">
<input type="button" value="Submit" onClick="validateForm();">
</form>
</body>

</html>


***************************
http://emailregex.com/
***************************
