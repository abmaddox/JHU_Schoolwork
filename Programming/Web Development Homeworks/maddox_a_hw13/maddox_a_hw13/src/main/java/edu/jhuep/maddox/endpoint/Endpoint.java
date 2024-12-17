/**
 * @author Andrew Maddox
 * @since Apr 20, 2024
 */
package edu.jhuep.maddox.endpoint;

import java.text.DecimalFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.jhuep.maddox.utils.HikeInput;
import edu.jhuep.maddox.utils.RateOutput;
import edu.jhuep.maddox.validation.Validator;

/**
 * The REST endpoint for assingment 13. It deserializes a JSON message, processes the rate, and serializes a reponse into JSON.
 */
@Path("rates")
public class Endpoint {
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RateOutput getHTML(HikeInput input) {
		Validator v = new Validator(input.getHike(), input.getDate(), input.getDuration(), input.getHikers());
		

		DecimalFormat df = new DecimalFormat("#.00");
		RateOutput ro = new RateOutput();
		if(v.getRate() < 0) {
			ro.setErrors(v.getErrors().toArray(new String[0]));
		}
		else {
			ro.setErrors(new String[0]);
		}
		ro.setRate(df.format(v.getRate()));
			
		//Return RateOutput object for serialization into JSON
		return ro;
	}
}
