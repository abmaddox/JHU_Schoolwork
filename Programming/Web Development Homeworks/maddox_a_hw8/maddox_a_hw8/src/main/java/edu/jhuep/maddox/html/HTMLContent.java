/**
 * @author Andrew Maddox
 * @since Mar 30, 2024
 */
package edu.jhuep.maddox.html;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.jhu.en605681.HikeType;
import edu.jhuep.maddox.bhc.parameterNames.ParameterNames;

/**
 * This class contains the HTML used to load the BHC web pages. No validation is completed here. This simply processes the information passed to it into HTML.
 */
public class HTMLContent {

	private static final String STATIC_TOP_CONTENT = "<!DOCTYPE html>"
			+ "<html lang='en-US'>"
			+ "<head>"
			+ "<meta charset='utf-8'>"
			+ "<title>Home Page for Bryce Canyon Hiking Company</title>"
			+ "<link href='./stylesheets/maddox_a_hw8.css' rel='stylesheet' type='text/css'>"
			+ "<script src='./scripts/maddox_a_hw8.js'></script>"
			+ "</head>"
			+ "<body>"
			+ "<hr id='topheaderline'>"
			+ "<h1>Welcome to Bryce Canyon Hiking Company!</h1>"
			+ "<hr id='bottomheaderline'>"
			+ "<img src='./images/MossyCave.jpg' class='display' title='Mossy Cave'"
			+ " alt='A small waterfall along the Mossy Cave trail.'>"
			+ "<img src='./images/NavajoLoop.jpg' class='display' title='Navajo Loop'"
			+ " alt='Rock formations in Navajo Loop.'>"
			+ "<img src='./images/RimTrail.jpg' class='display' title='Rim trail'"
			+ " alt='An overhead view of the rock formations in Rim Trail.'>"
			+ "<div class='center-parent'>"
			+ "<br>"
			+ "<h3>"
			+ "Design your tour below to get a quote"
			+ "</h3>"
			+ "<form action='https://web3.jhuep.com/maddox_a_hw8/ServletBHC' method=POST onSubmit='return validateSelections()'>"
			+ "<div class='left-child'>";
	
	private final String dynamicContent;
	
	private static final String STATIC_BOTTOM_CONTENT = "</div>"
			+ "<hr class='fullscreen'>"
			+ "<h3>"
			+ "<a href='https://www.nps.gov/brca/index.htm'> Click this link for more information on Bryce Canyon National Park </a> "
			+ "</h3>"
			+ "</body>"
			+ "</html>";
	
	private final String hikeName;
	private final String startDate;
	private final String duration;
	private final String hikers;
	private final double quote;
	private final List<String> errors;
	private static final DecimalFormat DF = new DecimalFormat("#.00");
	
	//This constructor is used to generate the default HTML page when the user first visits the 
	public HTMLContent() {
		this.hikeName = "";
		this.startDate = "";
		this.duration = "";
		this.hikers = "";
		this.quote = -0.01;
		this.errors = new ArrayList<String>();
		dynamicContent = generateDynamicContent();
	}
	
	public HTMLContent(String hikeName, String startDate, String duration, String hikers, double quote, List<String> errors) {
		this.hikeName = hikeName;
		this.startDate = startDate;
		this.duration = duration;
		this.hikers = hikers;
		this.quote = quote;
		this.errors = errors;
		dynamicContent = generateDynamicContent();
	}
	
	private String generateDynamicContent() {
		//used to generate the hike dropdown list section
		String topStaticHikeDropdownHTML = "<div class='error' id='hikeError' hidden='hidden'>"
				+ "A hike must be selected."
				+ "<br>"
				+ "</div>"
				+ "<label>Which hike? "
				+ "<select id='hikeSelection' name='" + ParameterNames.HIKE + "' onBlur='validateHike()'>";
		String dynamicHikeDropdownHTML = "<option value='None'>None</option>";	//HikeDropdownHTML follows pattern <option value="someHike">someHike</option>, one option may be marked selected, first option will be None
		String bottomStaticHikeDropdownHTML = "</select></label><br>";
		
		//used to generate the date selection section
		String topStaticDateHTML = "<div class='error' id='dateError' hidden='hidden'>"
				+ "A date must be selected."
				+ "<br>"
				+ "</div>"
				+ "<label>Starting when? ";
		String dynamicDateHTML = "<input id='dateSelection' type='date' name='" + ParameterNames.DATE + "' value='" + startDate + "' onBlur='validateDate()'>";														//DateHTML follows pattern <input id="dateSelection" type="date" value=someDate onBlur="validateDate()">
		String bottomStaticDateHTML = "</label><br>";
		
		//used to generate the duration selection section
		String topStaticDurationHTML = "<div class='error' id='durationError' hidden='hidden'>"
				+ "A duration must be entered."
				+ "<br>"
				+ "</div>"
				+ "<label>For how long? ";
		String dynamicDurationHTML = "<input id='durationSelection' type='number' name='" + ParameterNames.DURATION + "' min='1' max='100' value='" + duration + "' onBlur='validateDuration()'>";
		String bottomStaticDurationHTML = " days</label><br>";
		
		String topStaticHikersHTML = "<div class='error' id='hikersError' hidden='hidden'>"
				+ "A number of hikers must be entered."
				+ "<br>"
				+ "</div>"
				+ "<label>How many hikers? ";
		String dynamicHikersHTML = "<input id='hikersSelection' type='number' name='" + ParameterNames.HIKERS + "' min='1' max='100' value='" + hikers +"' onBlur='validateHikers()'>";
		String bottomStaticHikersHTML = " hikers</label><br></div>";
		
		String topStaticQuoteHTML = "<div>"
				+ "<br>"
				+ "<input type='submit' value='Get Quote'>"
				+ "<br>";
		String dynamicQuoteHTML = "<div>Quote: $" + (quote > 0 ? DF.format(quote) : "") + "</div>";
		String bottomStaticQuoteHTML = "</div></form>";
		
		String topStaticErrorHTML = "<div class = 'error'>";
		String dynamicErrorHTML = "";						// ErrorHTML follows pattern <p>someError</p>
		String bottomStaticErrorHTML = "</div>";
		
		
		//hikeName and errors require some processing to generate the dynamic content
		
		//setting dynamic content for hike dropdown
		for (String hike : HikeType.getHikeNames()) {
			dynamicHikeDropdownHTML += "<option value='"+ hike +"' " + ((hikeName==hike)?"selected":"") + ">"+ hike +"</option>";
		}
		
		//setting dynamic content for error messages
		if (quote <= 0) {
			for (String error : errors) {
				dynamicErrorHTML += "<p>" + error + "</p>";
			}
		}
		
		
		return topStaticHikeDropdownHTML
				+ dynamicHikeDropdownHTML
				+ bottomStaticHikeDropdownHTML
				
				+ topStaticDateHTML
				+ dynamicDateHTML
				+ bottomStaticDateHTML
				
				+ topStaticDurationHTML
				+ dynamicDurationHTML
				+ bottomStaticDurationHTML
				
				+ topStaticHikersHTML
				+ dynamicHikersHTML
				+ bottomStaticHikersHTML
				
				+ topStaticQuoteHTML
				+ dynamicQuoteHTML
				+ bottomStaticQuoteHTML
				
				+ topStaticErrorHTML
				+ dynamicErrorHTML
				+ bottomStaticErrorHTML;
	}
	
	public String getHTML() {
		return STATIC_TOP_CONTENT + dynamicContent + STATIC_BOTTOM_CONTENT;
	}

}
