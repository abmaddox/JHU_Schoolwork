<%@page import="edu.jhu.en605681.HikeType"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="edu.jhuep.maddox.bhc.constants.*"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>

<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src='js/scripts.js'></script>

<jsp:useBean id="stringbean"
	class="edu.jhuep.maddox.bhc.model.bean.Fields" scope="session"/>
<meta charset="UTF-8">
<title>Home Page for Bryce Canyon Hiking Company</title>
</head>
<body>
	<hr id='topheaderline'>
	<h1>Welcome to Bryce Canyon Hiking Company!</h1>
	<hr id='bottomheaderline'>
	<img src='jpg/MossyCave.jpg' class='display' title='Mossy Cave'
		alt='A small waterfall along the Mossy Cave trail.'>
	<img src='jpg/NavajoLoop.jpg' class='display' title='Navajo Loop'
		alt='Rock formations in Navajo Loop.'>
	<img src='jpg/RimTrail.jpg' class='display' title='Rim trail'
		alt='An overhead view of the rock formations in Rim Trail.'>
	<div class='center-parent'>
		<br>
		<h3>Design your tour below to get a quote</h3>
		<form action='https://web3.jhuep.com/maddox_a_hw10/' method=POST
			onSubmit='return validateSelections()'>
			<div class='left-child'>
				<div class='error' id='hikeError' hidden='hidden'>
					A hike must be selected.<br>
				</div>
				<label>Which hike? <select id='hikeSelection'
					name=<%="'" + ParameterNames.HIKE + "'"%> onBlur='validateHike()'>

						<%
						//setting dynamic content for hike dropdown
						%>
						<option value='None' <%if (stringbean.getHike() == "None") {%>
							selected <%}%>>None</option>
						<%
						for (HikeType hike : HikeType.values()) {
						%>
						<option value=<%="'" + hike + "'"%>
							<%if (hike.equalsName(stringbean.getHike()) == true) {%> selected
							<%}%>><%=hike%></option>
						<%
						}
						%>

				</select></label><br>

				<div class='error' id='dateError' hidden='hidden'>
					A date must be selected.<br>
				</div>
				<label>Starting when? <input id='dateSelection' type='date'
					name=<%="'" + ParameterNames.DATE + "'"%>
					value=<%="'" + stringbean.getDate() + "'"%> onBlur='validateDate()'>
				</label> <br>

				<div class='error' id='durationError' hidden='hidden'>
					A duration must be entered.<br>
				</div>
				<label>For how long? <input id='durationSelection'
					type='number' name=<%="'" + ParameterNames.DURATION + "'"%> min='1'
					max='100' value=<%="'" + stringbean.getDuration() + "'"%>
					onBlur='validateDuration()'> days
				</label> <br>
				<div class='error' id='hikersError' hidden='hidden'>
					A number of hikers must be entered.<br>
				</div>
				<label>How many hikers? <input id='hikersSelection'
					type='number' name=<%="'" + ParameterNames.HIKERS + "'"%> min='1'
					max='100' value=<%="'" + stringbean.getHikers() + "'"%>
					onBlur='validateHikers()'> hikers
				</label><br>
			</div>
			<div>
				<br> <input type='submit' value='Get Quote'><br>

				<div>
					Quote: $
					<%
				if (stringbean.getRate() != Constants.ERROR_RATE && stringbean.getRate() != Constants.DEFAULT_RATE) {
				%>
					<%=Formats.RATE_FORMAT.format(stringbean.getRate())%>
				<%
				}
				%>
			</div>
		</div>
		</form>
		<div class='error'>

			<%
			//setting dynamic content for error messages
			//checks if the rate is -0.01, which indicates there are errors
			if (stringbean.getRate() == Constants.ERROR_RATE) {
				for (String error : stringbean.getErrors()) {
			%>
			<p>
				<%=error%>
			</p>
			<%
			}
			}
			%>

		</div>
	</div>
	<hr class='fullscreen'>
	<h3>
		<a href='https://www.nps.gov/brca/index.htm'> Click this link for
			more information on Bryce Canyon National Park </a>
	</h3>
</body>
</html>