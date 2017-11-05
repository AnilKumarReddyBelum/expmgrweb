<head>
</head>
<h2>Email validation Using Regex</h2>
<body>
Email : <input type ="text" id= "emailId"/><br><br>
<input type="submit" onclick="emailValidation(event)"/> 
</body>
</html>


<script type="text/javascript">
///^[^@]+@[a-zA-Z0-9.-]+\.+[a-z.-]+$/; ///^\w+([.-]?\w+)@\w+([.-]?\w+)(.\w{2,3})+$/; ///^[a-zA-Z0-9!@#$%^&*)(+=._-]{6,}$/;


function emailValidation(ev) {

var inputEmailId = document.getElementById("emailId").value;

if(inputEmailId !=null && inputEmailId.trim().length > 0 ){
	var lastOccurenceIndex = 0;
	for (var i = 0; i < inputEmailId.length; i++) {
		if(inputEmailId.charAt(i) === '@'){
			lastOccurenceIndex = i;
		}
	}
//alert('lastOccurenceIndex->'+lastOccurenceIndex);

var email1 = inputEmailId.substring(0,lastOccurenceIndex);
var email2 = inputEmailId.substring(lastOccurenceIndex+1);


alert('Part1->'+email1);
alert('Part2->'+email2);

	if((email1!=null && email1.trim().length > 0) && (email2!=null && email2.trim().length > 0) ){
		
		alert('Apply the REGEX and validate both');


		var email1Flag = validateFirstPartOfEmail(email1);
		alert('email1flag->'+email1Flag);
		
		var email2Flag = validateSecondPartEmail(email2);
		alert('email2flag->'+email2Flag);


		

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

function validateFirstPartOfEmail(email1){ 
var validateFirstPartOfEmailFlag = true; 
for(var i = 0; i < email1.length; i++){ 
if(email1.charCodeAt(i) >= 33 && email1.charCodeAt(i) <= 126){ 
continue; 
}
else{ 
validateFirstPartOfEmailFlag = false;
 break; 
} 
} 
return validateFirstPartOfEmailFlag; 
}

function validateSecondPartEmail(email2){ 
var count=0;
for(var i=0;i<email2.length;i++){
if(email2.charCodeAt(i) == 46){
count++;
}

}

if(count>1)
return false;
else if(count ==0)
	return false;
alert('length >> ' + email2.charCodeAt(email2.length-1));
if(!((email2.charCodeAt(email2.length-1)>= 65 && email2.charCodeAt(email2.length-1)<= 90 )|| (email2.charCodeAt(email2.length-1)>= 97 && email2.charCodeAt(email2.length-1)>= 122))){
	alert('coming inside');
	return false;
	
}
var validateSecondPartEmailFlag = true; 
for(var i = 0; i < email2.length; i++){
 if(i == 0 && email2.charCodeAt(i) == 46){ 
 validateSecondPartEmailFlag = false;
 break; 
 }
 else if((email2.charCodeAt(i) >= 65 && email2.charCodeAt(i) == 46 && email2.charCodeAt(i) == 46 <= 90) || email2.charCodeAt(i) == 46)
 { continue;
 }
 else if((email2.charCodeAt(i) >= 97 && email2.charCodeAt(i) <= 122) || email2.charCodeAt(i) == 46) 
 { 
 continue; 
 }
 else{ 
 validateSecondPartEmailFlag = false; 
 break; 
 } 
 } return validateSecondPartEmailFlag;

}
</script> 

