/**
 * This file contains functions used in assignment 11 of enterprise web development. Written by Andrew Maddox.
 * 
 */

//A boolean flag which is set to true if there was a selection made in the field
 let dateValid = false;


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


//Checks if all fields are filled out. If they are not then the submission will not work
 function validateSelections(){
	 validateDate()
	 return dateValid; 
 }