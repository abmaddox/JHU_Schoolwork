/**
 * @author Andrew Maddox
 * @since Apr 20, 2024
 */
package edu.jhuep.maddox.endpoint;

import java.text.DecimalFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.jhuep.maddox.constants.Constants;
import edu.jhuep.maddox.validation.Validator;

/**
 * The REST endpoint for assingment 12. It uses form parameters to extract the information in the POST request and returns a simple HTML page.
 */
@Path("rates")
public class Endpoint {
	
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getHTML(@FormParam(Constants.HIKE) String hike, @FormParam(Constants.DATE) String date, @FormParam(Constants.DURATION) String duration, @FormParam(Constants.HIKERS) String hikers) {
		String returnVal = "";
		Validator v = new Validator(hike, date, duration, hikers);
		
		//if rate < 0 then there was an error, so display errors
		if(v.getRate() < 0) {
			returnVal = "<p>Errors:</p>";
			for (String error : v.getErrors()) {
				returnVal = returnVal + "<p>" + error + "</p>";
			}
		}
		//rate > 0 so there were no errors
		else {
			DecimalFormat df = new DecimalFormat("#.00");
			returnVal = "Rate: $" + df.format(v.getRate());
		}
		
		//simple HTML return
		return "<html lang=\"en\">"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>Basic BHC Form</title>"
				+ "</head>\r\n"
				+ "<body>"
				+ returnVal
				+ "</body>"
				+ "</html>";
	}
}
