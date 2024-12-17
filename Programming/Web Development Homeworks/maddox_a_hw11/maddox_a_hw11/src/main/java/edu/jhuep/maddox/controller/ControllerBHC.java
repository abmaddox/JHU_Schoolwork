package edu.jhuep.maddox.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhuep.maddox.bean.Bean;
import edu.jhuep.maddox.validation.Validator;
import edu.jhuep.maddox.constants.Constants;
import edu.jhuep.maddox.constants.Constants.Parameters;
import edu.jhuep.maddox.sql.connector.ConnectorBHC;
import edu.jhuep.maddox.sql.query.Query;

/**
 * Servlet implementation class ControllerBHC
 */
@WebServlet(name = "home", description = "Controls database access and overall program flow for HW 11.", urlPatterns = { "/home" })
public class ControllerBHC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ConnectorBHC connector;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerBHC() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		
		connector = new ConnectorBHC();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		connector.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//General design from https://www.jhuep.com/~spiegel/en605681/Servlets_JSP/ServletsAndJSP.html
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Bean bean = (Bean) session.getAttribute(Constants.BEAN);

		if (bean == null) {
			//instatiate a new bean object, default values are set to be functional for the base page
			bean = new Bean();
			session.setAttribute(Constants.BEAN, bean);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}
		else {
			bean.setsInputDate(request.getParameter(Parameters.DATE.toString()));

			//do validation on the date and get any errors
			Validator v = new Validator(bean);
			bean.setErrors(v.getErrors());
			
			//execute query and populate bean with results
			if (v.isValidDate() && connector.isValid()) {
				Query q = new Query(bean);
				bean = q.getResults(bean, connector.getConnection());
			}
			else {
				bean.setErrors(connector.getErrors());
			}
			
			session.setAttribute(Constants.BEAN, bean);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
