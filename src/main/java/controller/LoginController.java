package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		String customerId = null;
		String password = null;
		RequestDispatcher requestDispatcher = null;

		try {
			customerId = request.getParameter("customerId");
			password = request.getParameter("password");

			if (StringUtils.isNotEmpty(customerId)
					&& StringUtils.isNotEmpty(password)) {
				System.out.println("not");
				request.setAttribute("customerId", customerId);
				requestDispatcher = request
						.getRequestDispatcher("generateusagereport.jsp");
			} else {
				request.setAttribute("errorMsg", "Incorrect Credentials");
				requestDispatcher = request.getRequestDispatcher("login.jsp");
			}
			requestDispatcher.forward(request, response);
		} catch (Exception e) {

		}

	}
}
