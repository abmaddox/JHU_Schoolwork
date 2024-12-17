package edu.jhuep.maddox.bhc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhuep.maddox.bhc.parameterNames.ParameterNames;
import edu.jhuep.maddox.bhc.validation.Validator;
import edu.jhuep.maddox.html.HTMLContent;

/**
 * Servlet implementation class ServletBHC. This servlet returns the HTML for the filled out form with any error messages that were created.
 */
@WebServlet("/ServletBHC")
public class ServletBHC extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBHC() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hike = request.getParameter(ParameterNames.HIKE.toString());
		String date = request.getParameter(ParameterNames.DATE.toString());
		String duration = request.getParameter(ParameterNames.DURATION.toString());
		String hikers = request.getParameter(ParameterNames.HIKERS.toString());
		
		Validator v = new Validator(hike, date, duration, hikers);
		response.setContentType("text/html");
		
		//gets the HTML page with the form filled out to return with any errors
		String content = new HTMLContent(v.getHike(), v.getDate(), v.getDuration(), v.getHikers(), v.getRate(), v.getErrors()).getHTML();
		response.getWriter().println(content);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
