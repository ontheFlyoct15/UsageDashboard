<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login Form</title>
  <link rel="stylesheet" href="css/style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
  
  <script type="text/javascript">
	function validateForm(formId)
    {
		echo "hi";
        var custId, pass;
        var form=document.getElementById("customerId");
        echo "form";
        var custId= document.forms[formId]["customerId"].value;
        var pass=document.forms[formId]["password"].value;
            if (custId==null || pass==null)
            {
                alert("Field is empty");
                return false;
            }
            else
            	{ return true;}
           
    }
	</script>	
</head>
<body>
<%
String errorMsg = null; 
if(null != request.getAttribute("errorMsg")){
	errorMsg=(String) request.getAttribute("errorMsg");
	}
	%>

  <section class="container">
    <div class="login">
      <h1>Login</h1>
      <%if(null!=errorMsg)
    	  {%>
    	  <h3 style="color: red"><%=errorMsg %></h3>
    	 <%}%>
      
      <form method="post" action="LoginController" name="loginform" id="loginform" onsubmit="return validateForm('loginform')" >
        <p><label>Customer Id</label><input type="text" name="customerId" value="" ></p>
        <p><label>Password</label><input type="text" name="password" value=""></p>
        <br>
        <input type="submit" name="submit" value="Submit">
      </form>
    </div>
  </section>
</body>
</html>
