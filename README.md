# expmgrweb
Manage Expenses Over Here


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

///^[^@]+@[a-zA-Z0-9._-]+\\.+[a-z._-]+$/;
///^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
///^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]{6,}$/;

function validateForm() {
    var x = document.getElementById("emailId").value;
	alert('coming inside-->'+x);

	var mailformat = /^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]{6,}$/;
	if(x.match(mailformat)){
		alert('Valid Email');
	}else{
		alert('In Valid Email');
	}	
	
	
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
