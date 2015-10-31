<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GenerateReport</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link href="css/style.css" rel="stylesheet" />

<script>
  $(function() {
    $( "#fromdate" ).datepicker();
    $( "#todate" ).datepicker();
  });
 </script>
  
</head>
<body>
<%
String customerId="1234";
String currentDate="10/06/2015";
String currentTime="11:11";
%>

<form action="GenerateUsageReportController" method="post" id="usageReport" name="usageReport">
<div style="">
<table align="center" cellpadding="1"  border="" >
<tr>
<td><label>Customer Id</label></td>
<td><input type="text" id="customerId" name="customerId" value="<%=customerId%>" readonly="readonly"></td>
</tr>
<tr>
<td><label>From Date</label></td>
<td><input type="text" id="fromdate" name="fromdate" value="<%=currentDate%>"></td>
<td><input type="text" id="fromtime" name="fromtime" value="<%=currentTime%>"></td>
</tr>
<tr>
<td><label style="font: arial">To Date</label></td>
<td><input type="text" id="todate" name="todate" value="<%=currentDate%>" style="font: arial"></td>
<td><input type="text" id="totime" name="totime" value="<%=currentTime%>"></td>
</tr>
<tr>
<td colspan="3" align="center">
<br>
<input type="submit" ></td>
</tr>
</table>
</div>
</form>


</body>
</html>