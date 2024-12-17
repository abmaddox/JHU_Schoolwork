/**
 * This file contains functions used in assignment 10 of enterprise web development. Written by Andrew Maddox.
 * 
 */

//A boolean flag which is set to true if there was a selection made in the field
 let hikeValid = false;
 let dateValid = false;
 let durationValid = false;
 let hikersValid = false;

//Checks if there is a valid selection made for hike. Displays error message if there is an invalid selection
function validateHike(){
	let element = document.getElementById("hikeSelection")
	let error = document.getElementById("hikeError")
	hikeValid = element.value != "None";
	
	if(hikeValid==true){
		error.setAttribute("hidden", "hidden");
	}
	else{
		error.removeAttribute("hidden");
	}
}

//Checks if there is a valid entry for start date. Displays error message if there is an invalid entry
function validateDate(){
	let element = document.getElementById("dateSelection");
	let error = document.getElementById("dateError");
	dateValid = element.value != "";
	
	if(dateValid==true){
		error.setAttribute("hidden", "hidden");
	}
	else{
		error.removeAttribute("hidden");
	}
}

//Checks if there is a valid entry for duration. Displays error message if there is an invalid entry
function validateDuration(){
	let element = document.getElementById("durationSelection");
	let error = document.getElementById("durationError");
	durationValid = element.value != "";
	
	if(durationValid==true){
		error.setAttribute("hidden", "hidden");
	}
	else{
		error.removeAttribute("hidden");
	}
}

//Checks if there is a valid entry for number of hikers. Displays error message if there is an invalid entry
function validateHikers(){
	let element = document.getElementById("hikersSelection");
	let error = document.getElementById("hikersError");
	hikersValid = element.value != "";
	
	if(hikersValid==true){
		error.setAttribute("hidden", "hidden");
	}
	else{
		error.removeAttribute("hidden");
	}
}

//Checks if all fields are filled out. If they are not then the submission will not work
 function validateSelections(){
	 validateHike()
	 validateDate()
	 validateDuration()
	 validateHikers()
	 return hikeValid && dateValid && durationValid && hikersValid; 
 }