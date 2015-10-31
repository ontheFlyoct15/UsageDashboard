<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login Form</title>
  <link rel="stylesheet" href="css/style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	
	<script>
  		$(function() {
    	$( "#fromdate" ).datepicker();
    	$( "#todate" ).datepicker();
  		});
 </script>
 
</head>
<body>
<%
String customerId=null;
String errorMessage=null;

if(null!=request.getAttribute("errorMsg"))
{
	errorMessage=(String) request.getAttribute("errorMsg");
}

if(null!=request.getAttribute("customerId"))
{
	customerId=(String) request.getAttribute("customerId");
}
String currentDate="10/06/2015";
String currentTime="11:11";
%>

  <div class="container">
    <div class="generatereport">
      <h1>Generate Usage Report</h1>
      <%if(null!=errorMessage)
    	  {%>
    	  <h3 style="color: red"><%=errorMessage %></h3>
    	 <%}%>
      <form method="post" action="GenerateUsageReportController">
		<p><label>Customer Id</label>
		<input type="text" id="customerId" name="customerId" value="<%= customerId%>" readonly="readonly"><p>

		<p><label>From Date</label>&nbsp;&nbsp;&nbsp;
		<input type="text" id="fromdate" name="fromdate" value="<%=currentDate%>">&nbsp;
		<input type="text" id="fromtime" name="fromtime" value="<%=currentTime%>" class="time" ></p>
		
		<p>
		<label style="font: arial">To Date</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" id="todate" name="todate" value="<%=currentDate%>">&nbsp;
		<input type="text" id="totime" name="totime" value="<%=currentTime%>"></p>

      <br><br>
       <input type="submit" id="submit" value="Submit">
      </form>
    </div>
  </div>
</body>
</html>
