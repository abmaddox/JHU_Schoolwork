<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@page import="edu.jhuep.maddox.constants.Constants.Parameters"%>
   <%@page import="edu.jhuep.maddox.constants.Constants.ColumnOrder"%>
   <%@page import="edu.jhuep.maddox.bean.Bean"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<title>BHC Date Search</title>
	<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
	<script src='js/scripts.js'></script>
	<jsp:useBean id="bean"
	class="edu.jhuep.maddox.bean.Bean" scope="session"/>
</head>

<body>
	<div>
		<h1>Welcome to the BHC Booking Finder</h1>
	</div>
	<div>
		<h3>Enter a start date to find booking information for hikes on or after the selected date</h3>
	</div>
	<div>
	<form action='https://web3.jhuep.com/maddox_a_hw11/home' method=POST onSubmit='return validateSelections()'>
		<div class='error' id='dateError' hidden='hidden'>A date must be selected.<br></div>
				<label>Starting when? <input id='dateSelection' type='date' required
					name=<%="'" + Parameters.DATE + "'"%>
					value=<%="'" + bean.getsInputDate() + "'"%> onBlur='validateDate()'>
				</label>	
		<input type='submit' value='Find Bookings' >
	</form>
	</div>
	
	<div>
		<hr class = "divider">
	</div>
	<div>
		<table class = center>
		<tr>
			<%
			for(ColumnOrder co: ColumnOrder.values()){
				out.println("<th>" + ColumnOrder.getName(co) + "</th>");
			}
			%>
		</tr>
		<%
		if(bean.getBeans() == null || bean.getBeans().size() == 0){
			
			out.println("<tr><td colspan = '" + ColumnOrder.values().length + "'>No Results</td></tr>");
		}
		else{
				//populate table
				for(Bean b: bean.getBeans()){
					out.println("<tr>");
					out.println("<td>" + b.getStartDay() + "</td>");
					out.println("<td>" + b.getDuration() + "</td>");
					out.println("<td>" + b.getLocation() + "</td>");
					out.println("<td>" + b.getgFName() + "</td>");
					out.println("<td>" + b.getgLName() + "</td>");
					out.println("<td>" + b.gethFName() + "</td>");
					out.println("<td>" + b.gethLName() + "</td>");
					out.println("</tr>");
				}
				
			}
		%>
		</table>
		
	</div>
	
	<div class = "error">
		<%
		//setting dynamic content for error messages
		//checks if the errors are null or empty and prints if they aren't
		if(bean.getErrors() != null && bean.getErrors().size() > 0){
			for (String s: bean.getErrors()){
				out.println("<p>" + s + "</p>");
			}
		}
		%>
	</div>
	
	
	
</body>
</html>