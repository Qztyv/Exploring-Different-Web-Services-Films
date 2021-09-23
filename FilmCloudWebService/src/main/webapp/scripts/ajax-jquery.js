$(document).ready(function() {
	// Get all films
	$("#get-all-films-form").submit(function(e) {
		e.preventDefault(); // Avoid to execute the actual submit of the form.
		
		// Store the areas where we will display data relating to films and response from server
		var resultRegion = $("#films-table");
		var loadingMessage = $("#message-div");
		
		// Grab the selected data type requested by the user
		var formatType = $("#data_type").val();
		console.log(formatType);
		
		// Place the format type in a key:value / hashmap to send to the server.
		var dataObject = { format: formatType };
		
		// Display a loading message
		$("#alert-div").show();
		loadingMessage.html("<h2> Searching For All Films (May take a while depending on server used) </h2>");
		
		// Get the targetURL for the server from the forms action attribute
		var targetURL = $(this).attr("action");
		
		// Determine the correct way to parse this data (XML, JSON, TEXT to Film)
		var responseHandler = findHandler(formatType);
		ajax("GET", targetURL, dataObject, null, formatType,
						function(data) {
							console.log(data);
							responseHandler(data, resultRegion);
							loadingMessage.html("<h2> Search For All Films Finished Successfully (Retrieved: " + formatType + ") </h2>");
						});
	});
	
	// Get films by title
	$("#get-film-form").submit(function(e) {
		e.preventDefault(); // avoid to execute the actual submit of the form.
		
		// Store the areas where we will display data relating to films and response from server
		var resultRegion = $("#films-table");
		var loadingMessage = $("#message-div");
		
		// Grab the selected data type requested by the user
		var formatType= $("#data_type").val();
		console.log(formatType);
		
		// // Grab input field and place the format type and input in a key:value / hashmap to send to the server.
		var inputField = $("#input_search_title").val();
		var dataObject = { format: formatType, filmname: inputField };
		
		// Display a loading message
		$("#alert-div").show();
		loadingMessage.html("<h2> Searching For Films With Title: " + inputField + "</h2>");
		
		// Get the targetURL for the server from the forms action attribute
		var targetURL = $(this).attr("action");
		
		// Determine the correct way to parse this data (XML, JSON, TEXT to Film)
		var responseHandler = findHandler(formatType);
		ajax("GET", targetURL, dataObject, null, formatType,
						function(data) {
							console.log(data);
							responseHandler(data, resultRegion);
							loadingMessage.html("<h2> Search For '" + inputField + "' Has Finished Successfully (Retrieved: " + formatType + ") </h2>");
						});
	});
	
	// Insert a film into db
	$("#insert-form").submit(function(e) {
	 	e.preventDefault(); // avoid to execute the actual submit of the form.
		
		// Store the areas where we will display a response from server
		var resultRegion = $("#message-div");
		
		// Grab the URL from the form attribute 'action'
		var targetURL = $(this).attr("action");
		// Serialize all of the inputs within the form, so we can easily parse a film into XML / JSON later (automatically URL encodes too)
		var filmForm = $(this).serializeArray();
		
		// Grab what option the user selected to send the film as (XML or JSON)
		var filmFormat = $("input[name='formatInsert']:checked").val();
		console.log(filmFormat);
		
		// parses the film javascript array 'filmForm' into either XML or JSON depending on 'filmFormat',
		// and then sends off to the server with jQuery ajax
		handleFilmPostPut("POST", filmFormat, filmForm, targetURL, resultRegion);
		
		// Display the response box
		$("#alert-div").show();
		
		// Hide the insert pop-up / modal
		$("#insert-film-modal").modal("toggle");
	});
	
	// When update button is pressed on table, populate the modal inputs
	 $("#films-table").on("click", ".update",function(){ 
		console.log("edit pressed");
		
		// Find the row of the selected Film
		var tr = $(this).closest("tr");
		
		// Grab all of the existing values of chosen Film
		var id = tr.find(".id").text();
		var title = tr.find(".title").text();
		var year = tr.find(".year").text();
		var director = tr.find(".director").text();
		var stars = tr.find(".stars").text();
		var review = tr.find(".review").text();
		
		// Set the input boxes on the update pop-up / modal, so user knows what values exist
		$("#idUpdate").val(id);
		$("#titleUpdate").val(title);
		$("#yearUpdate").val(year);
		$("#directorUpdate").val(director);
		$("#starsUpdate").val(stars);
		$("#reviewUpdate").val(review);
	});
	
	// Update the film in the database
	$("#update-form").submit(function(e) {
	 	e.preventDefault(); // avoid to execute the actual submit of the form.
		
		// Grab the div where we will display response from server
		var resultRegion = $("#message-div");
		
		// Get target url
		var targetURL = $(this).attr("action");
		// Serialize all form inputs into an array, making it easier to parse into xml / json (automatically url-encodes)
		var filmForm = $(this).serializeArray();
		
		// Grab the selected format to send to the server ( XML / JSON )
		var filmFormat = $("input[name='formatUpdate']:checked").val();
		console.log(filmFormat);
		
		// parses the film javascript array 'filmForm' into either XML or JSON depending on 'filmFormat',
		// and then sends off to the server with jQuery ajax
		handleFilmPostPut("PUT", filmFormat, filmForm, targetURL, resultRegion);
		
		// Display response from the server
		$("#alert-div").show();
		$("#update-film-modal").modal("toggle");
	});
	
	// When the delete button on the table row is pressed, set the button on the modal pop-up to the id
	$("#films-table").on("click", ".delete",function(){ 
		console.log("Delete Pressed");
		// Grab the film ID thats appended to the delete-id attribute on the delete button
		var filmIDToDelete = $(this).attr("data-id");
		
		// Set the delete button on the form pop-up to the chosen film ID.
		$("#delete-film-button").attr("data-id", filmIDToDelete);
	});
	
	// When the delete button on the modal pop-up is pressed, delete film from the database
	$("#delete-form").submit(function(e) {
		e.preventDefault(); // avoid to execute the actual submit of the form.
		var resultRegion = $("#message-div");
		
		// Grab the Film ID that the user wants to delete from server
		var filmID = $("#delete-film-button").attr("data-id");
		console.log("Deleting FilmID: " + filmID);
		
		// Grab targetURL for server
		var targetURL = $(this).attr("action") + "?filmID=" + filmID;
		
		// Send delete ajax request to server (expecting json as response)
		ajax("DELETE", targetURL, null, null, "json", function(data) {
			console.log(data);
			// Set response from server 
			resultRegion.html("<h2>" + data.response + "</h2>" + "<p>" + data.message + "</p>");
			
			// Update the table, removing the film
			var table = $("#films-table").DataTable();
			table.row("#" + filmID).remove().draw();
		});
		
		// Display response from server
		$("#alert-div").show();
		$("#delete-film-modal").modal("toggle");
	});
	
	// Closes the response div we use to display messages from server.
	$("#close-alert").on("click", function() {
		$("#alert-div").hide();
	});
});	