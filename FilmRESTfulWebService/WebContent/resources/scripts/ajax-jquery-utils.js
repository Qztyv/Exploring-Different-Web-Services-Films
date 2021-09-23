// jQuery, will handle all 4 different types ( GET, POST, PUT, DELETE )
// For GET and DELETE, pass null into the parameters that are not needed accordingly.
function ajax(methodType, targetURL, dataObject, contentType, dataType, callBack) {
	return $.ajax({
		method: methodType,
		url: targetURL,
		data: dataObject,
		contentType: contentType,
		dataType: dataType,
		success: function(data) {
			return callBack(data);
		},
		error: errorHandlerFunc
	});
}

// Display error message to the user if something went wrong with ajax call
function errorHandlerFunc() {
	var loadingMessage = $("#message-div");
	loadingMessage.html("Ajax call failed!");
}

// use jQuery to grab the film values and store in array, then use DataTables method
function parseJSONToFilm(data, resultRegion) {
    var rows = [];
	$.each(data, function(i, film) {
		rows[i] = [film.id, film.title, film.year, film.director, film.stars, film.review];
	});
    getFilmTable(rows, resultRegion);
}

// use jQuery foreach to iterate over the film dom and grab the values, storing in an array.
// Then use DataTables method
function parseXMLToFilm(data, resultRegion) {
	var rows = [];
	$(data).find("film").each(
		function(i,film) 
		{
			var id = $(film).find("id").text();
			var title = $(film).find("title").text();
			var year = $(film).find("year").text();
			var director = $(film).find("director").text();
			var stars = $(film).find("stars").text();
			var review = $(film).find("review").text();
			rows[i] = [id, title, year, director, stars, review];
		}
	);
	getFilmTable(rows, resultRegion);
}

// split the text received from server, according to new lines, then use foreach to iterate over
// Each line,  then split each line by hashtag, grabbing values to store in array. 
// Finally, use DataTables method.
function parseTextToFilm(data, resultRegion) {
	var rows = [];
	var films = data.split(/[\n\r]+/);
	$.each(films, function(i, film) {
		if(films[i].length > 1) // To ignore the blank lines
		{
			rows[i] = films[i].split("#");
		}
	});
    getFilmTable(rows, resultRegion);
}

// Send the film to the server in XML format by using the serialized form name & values of inputs
// To create our own valid XML format
function parseFilmToXML(filmForm) {
		var tag = "";
		$.each(filmForm, function(i, filmInput) {
			if(i != 0) {
				tag += "<" + filmInput.name + ">" + filmInput.value + "</" + filmInput.name + ">";
				}
		});
		var filmStringToSend = "<film>" + tag + "</film>";
		return filmStringToSend;
}

// Send the film to the server in JSON format, using the serialized form name & values of inputs
// Putting them into a hashmap, then use JSON.stringify.
function parseFilmToJSON(filmForm) {
	var filmJsonObject = {};
	$.each(filmForm, function(i, filmInput) {
		if(i != 0) {
			filmJsonObject[filmInput.name] = filmInput.value;
		}
	});
	var filmStringToSend = JSON.stringify(filmJsonObject);
	return filmStringToSend;
}

function handleFilmPostPut(methodType, filmFormat, filmForm, targetURL, resultRegion)
{
	// If filmFormat is XML, parse the film to XML, and send it to the server
	if(filmFormat === "xml") {
		var filmStringToSend = parseFilmToXML(filmForm);
		var contentType = "application/xml";
		console.log(filmStringToSend);
		ajax(methodType, targetURL, filmStringToSend, contentType, filmFormat, function(data) {
			console.log(data);
			// Display response from the server (receiving an XML response)
			resultRegion.html("<h2>" + $(data).find("response").text() + "</h2>" + "<p>" + $(data).find("message").text() + "</p>");
		});
	}
	// If film format is JSON, parse the film to JSON and send it to the server
	else {
		var filmStringToSend = parseFilmToJSON(filmForm);
		var contentType = "application/json";
		console.log(filmStringToSend);
		ajax(methodType, targetURL, filmStringToSend, contentType, filmFormat, function(data) {
			console.log(data);
			// Display response from the server (receiving JSON response)
			resultRegion.html("<h2>" + data.response + "</h2>" + "<p>" + data.message + "</p>");
		});
	}
}

// DataTables function, takes a javascript array and a result region to display the table.
function getFilmTable(dataSet, resultRegion) {
	// Check if the webpage has already generated a DataTable, if so we need to clear and update the rows
	// (when searching multiple times this is required)
	if($.fn.dataTable.isDataTable(resultRegion))
	{
		resultRegion.DataTable().clear();
		resultRegion.DataTable().rows.add(dataSet);
		resultRegion.DataTable().draw();
	}
	else
	{
		// create the DataTable using their method (imported via CDN link in html)
		resultRegion.DataTable( {
		data: dataSet,
		columns: [
			{ 
				title: "Film ID",
			  	className: "id"
			},
			{ 
				title: "Title",
			  	className: "title" 
			},
			{ 
				title: "Year",
			 	className: "year" 
			},
			{ 
				title: "Director",
				className: "director" 
			},
			{ 
				title: "Stars",
				className: "stars"
			},
			{ 
				title: "Review",
				className: "review"
			},
			{ 
				// Creating a dynamic column for update / delete buttons
				title: "Options",
				data: null,
			 	render:function(data, type, row) {
					// most importantly, we set the data-id to the film ID relevant to that row (making buttons dynamic)
					// We also need a data-toggle and a data-target to prompt the modal to come up when pressed.
					var updateButton = "<button class='btn btn-warning update' data-id='"+row[0]+"' data-toggle='modal' data-target='#update-film-modal'><i class='far fa-edit'></i></button>";
					var deleteButton = "<button class='btn btn-danger delete' data-id='"+row[0]+"' data-toggle='modal' data-target='#delete-film-modal'><i class='far fa-trash-alt'></i></button>";
					// In order to make the buttons display vertically, using bootstrap for div class and wrap the buttons within it
					var containerVertical = "<div class='btn-group-vertical'>" + updateButton + deleteButton + "</div>";
					return containerVertical;
				}
			}],
			// Set the row ID, this is useful for selecting rows dynamically (when user presses update, 
			// We need to know what row that was, so we can pre-set the modal with the correct film data accordingly )
			rowId: function(a) {
				return a[0];
			}
		});
	}
}

// function to help decide on what format we are parsing to a film
function findHandler(format) {
  if (format === "xml") {
    return(parseXMLToFilm);
  } else if (format === "text") {
    return(parseTextToFilm);
  } else {
    return(parseJSONToFilm);
  }
}