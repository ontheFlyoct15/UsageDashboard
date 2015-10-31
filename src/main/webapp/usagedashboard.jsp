<%@page import="model.CustomerUsageReport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<html>
    <head>
        <meta charset="utf-8">
        <title>Data Usage Dashboard</title>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript" src="js/excanvas.js"></script>
        <script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>

        <link rel="stylesheet" type="text/css" href="css/dashboardstyle.css" media="screen">
        <link rel="stylesheet" type="text/css" href="css/jquery.easy-pie-chart.css" media="screen">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script type="text/javascript">
            var initPieChart = function() {
                $('.percentage').easyPieChart({
                    animate: 1000
                });
                $('.percentage-light').easyPieChart({
                    barColor: function(percent) {
                        if(percent<=40)
                        {
                        return "rgb(77,179,1)";
                        }
                        if(percent>40 && percent<=70)
                        {
                        return "rgb(255,248,1)";
                        }
                        if(percent>70)
                        {
                        return "rgb(222,27,1)";
                        }
                        //return "rgb(" + Math.round(255 * (1-percent)) + ", " + Math.round(255 * percent) + ", 0)";
                    },
                    trackColor: '#666',
                    scaleColor: false,
                    lineCap: 'butt',
                    lineWidth: 15,
                    animate: 1000
                });
                $('.percentage-available').easyPieChart({
                    barColor: function(percent) {
                        if(percent<=40)
                        {
                        	return "rgb(222,27,1)";
                        }
                        if(percent>40 && percent<=70)
                        {
                        return "rgb(255,248,1)";
                        }
                        if(percent>70)
                        {
                        	 return "rgb(77,179,1)";
                        }
                        //return "rgb(" + Math.round(255 * (1-percent)) + ", " + Math.round(255 * percent) + ", 0)";
                    },
                    trackColor: '#666',
                    scaleColor: false,
                    lineCap: 'butt',
                    lineWidth: 15,
                    animate: 1000
                });

                $('.updateEasyPieChart').on('click', function(e) {
                  e.preventDefault();
                  $('.percentage, .percentage-light').each(function() {
                    var newValue = Math.round(100*Math.random());
                    $(this).data('easyPieChart').update(newValue);
                    $('span', this).text(newValue);
                  });
                });
            };
        </script>
    </head>
    <body onload="initPieChart();">
    
    <%

    CustomerUsageReport customerUsageReport=null;
    
  
   if(null!= request.getAttribute("customerUsageReport"))
   {
	   customerUsageReport=(CustomerUsageReport) request.getAttribute("customerUsageReport");
   }
    
    int usagePercentage=customerUsageReport.getUsagePercentage();
    int usedData=customerUsageReport.getDataUsed();//in GB
    int availableData=customerUsageReport.getAvailableData();
    int usedDataPercentage=customerUsageReport.getDataUsedPercentage();
    int availableDataPercentage=customerUsageReport.getAvailableDataPercentage();
    
    int customUsagePercentage=customerUsageReport.getCustomUsagePercentage();
	int customDataUsed=customerUsageReport.getCustomDataUsed();
	
	String fromDate=customerUsageReport.getFromDate();
	String toDate=customerUsageReport.getToDate();
   // System.out.println("hi "+usedDataPercentage);
    %>
        <div class="container">
            <h1>Data Usage Dashboard</h1>
            <div class="chart">
                <div class="percentage" data-percent="<%=usagePercentage%>"><span><%=usagePercentage%></span>%</div>
                <div class="label">Data Usage</div>
            </div>
            <div class="chart">
                <div class="percentage" data-percent="<%=usedDataPercentage%>"><span><%=usedData%></span>GB</div>
                <div class="label">Used Data</div>
            </div>
            <div class="chart">
                <div class="percentage" data-percent="<%=availableDataPercentage%>"><span><%=availableData%></span>GB</div>
                <div class="label">Available Data</div>
            </div>
            <div style="clear:both;"></div>
            <div class="dark">
                <div class="chart">
                    <div class="percentage-light" data-percent="<%=customUsagePercentage%>"><span><%=customUsagePercentage%></span>%</div>
                    <div class="label">Data Usage</div>
                </div>
                <div class="chart">
                    <div class="percentage-light" data-percent="<%=customUsagePercentage%>"><span><%=customDataUsed%></span>GB</div>
                    <div class="label">Used Data</div>
                </div>
                <div class="chart">
                    <div class="label">From Date : <%=fromDate %></div>
                    <div class="label">To Date : <%=toDate %></div>
                </div>
                <label>hi</label>
                <div style="clear:both;"></div>
            </div>

            <p><a href="#" class="button updateEasyPieChart">Update Usage</a></p>

           </div>
    </body>
</html>
