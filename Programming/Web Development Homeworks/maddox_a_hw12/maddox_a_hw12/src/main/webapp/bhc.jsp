<%@page import="edu.jhu.en605681.HikeType"%>
<%@page import="edu.jhuep.maddox.constants.Constants"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Basic BHC Form</title>
</head>
<body>
	<div class='center-parent'>
		<br>
		<h3>Basic BHC Form</h3>
		<form action='https://web3.jhuep.com/maddox_a_hw12/bhc/rates' method=POST>
			<div>

				<label>Which hike? <select id='hikeSelection'
				name=<%="'" + Constants.HIKE + "'"%> required>
					<option id='None selected'>None selected</option>
						<%
						for (HikeType hike : HikeType.values()) {
						out.println("<option id='" + hike + "'>" + hike + "</option>");
						}
						%>

				</select></label><br>
				
				<label>Starting when? <input id='dateSelection' type='date'
					name=<%="'" + Constants.DATE + "'"%>
					value='' required>
				</label> <br>

				<label>For how long? <input id='durationSelection'
					type='number' name=<%="'" + Constants.DURATION + "'"%> min='1'
					max='100' value=''
					required> days
				</label> <br>
				
				<label>How many hikers? <input id='hikersSelection'
					type='number' name=<%="'" + Constants.HIKERS + "'"%> min='1'
					max='100' value=''
					required> hikers
				</label><br>
			</div>
			
			<div>
				<br> <input type='submit' value='Get Quote'><br>

		</div>
		</form>
	</div>

</body>
</html>