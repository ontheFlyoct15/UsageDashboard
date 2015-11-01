package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerUsageReport;

import org.apache.commons.lang.StringUtils;

import com.verizon.datausageengine.DataUsageNotifier;

/**
 * Servlet implementation class GenerateUsageReportController
 */

public class GenerateUsageReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateUsageReportController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter outWriter = response.getWriter();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm");

		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("MMM dd, yy");

		String customerId;
		String fromDate;
		String toDate;
		String fromTime;
		String toTime;
		String trend;

		RequestDispatcher requestDispatcher = null;
		try {
			customerId = request.getParameter("customerId");
			fromDate = request.getParameter("fromdate");
			toDate = request.getParameter("todate");
			fromTime = request.getParameter("fromtime");
			toTime = request.getParameter("totime");
			trend = request.getParameter("trend");

			if (StringUtils.isEmpty(customerId)
					|| StringUtils.isEmpty(fromDate)
					|| StringUtils.isEmpty(toDate)
					|| StringUtils.isEmpty(fromTime)
					|| StringUtils.isEmpty(toTime)
					|| (StringUtils.isEmpty(trend))) {

				request.setAttribute("errorMsg", "Enter valid data");
				requestDispatcher = request
						.getRequestDispatcher("generateusagereport.jsp");
			} else if (StringUtils.isNotEmpty(trend)
					&& trend.equalsIgnoreCase("calendarbased")) {
				StringUtils.trim(fromDate);
				StringUtils.trim(toDate);
				StringUtils.trim(fromTime);
				StringUtils.trim(toTime);
				System.out.println(fromDate + " " + fromTime);
				System.out.println(toDate + " " + toTime);
				Date from = simpleDateFormat.parse(fromDate + " " + fromTime);
				Date to = simpleDateFormat.parse(toDate + " " + toTime);

				String cassandraFrom = simpleDateFormat2.format(from);
				String cassandraTo = simpleDateFormat2.format(to);
				System.out.println(cassandraFrom + "/n" + cassandraTo);

				DataUsageNotifier dataUsageNotifier = new DataUsageNotifier();
				CustomerUsageReport customerUsageReport = dataUsageNotifier
						.getCutomerUsage(customerId, cassandraFrom, cassandraTo);
				if (customerUsageReport == null
						|| customerUsageReport.getCustomDataUsed() < 1) {
					request.setAttribute("errorMsg", "No records Found");
					request.setAttribute("customerId", customerId);
					requestDispatcher = request
							.getRequestDispatcher("generateusagereport.jsp");
				} else {
					customerUsageReport.setFromDate(simpleDateFormat3
							.format(from));
					customerUsageReport.setToDate(simpleDateFormat3.format(to));

					request.setAttribute("flag", "calendarbased");
					request.setAttribute("customerUsageReport",
							customerUsageReport);
					requestDispatcher = request
							.getRequestDispatcher("adminusagedashboard.jsp");
				}
			} else {
				DataUsageNotifier dataUsageNotifier = new DataUsageNotifier();
				CustomerUsageReport customerUsageReport = dataUsageNotifier
						.getUsageTrend(customerId);

				if (null != customerUsageReport) {
					System.out.println("ser "
							+ customerUsageReport.getLast7DaysUsage());

					request.setAttribute("flag", "trendbased");
					request.setAttribute("customerUsageReport",
							customerUsageReport);
					requestDispatcher = request
							.getRequestDispatcher("adminusagedashboard.jsp");
				} else {
					request.setAttribute("errorMsg", "No records Found");
					request.setAttribute("customerId", customerId);
					requestDispatcher = request
							.getRequestDispatcher("generateusagereport.jsp");
				}

			}
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println(e);
			// outWriter.print("Exception " + e);
			request.setAttribute("errorMsg", "Fatal Error Occrured");
			requestDispatcher = request.getRequestDispatcher("error.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
