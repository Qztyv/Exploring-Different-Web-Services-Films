<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Film Engine</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Full bootstrap is included in data tables below -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.22/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.22/datatables.min.js"></script>
<script src="https://kit.fontawesome.com/65aef22af2.js"
	crossorigin="anonymous"></script>

<script src="<c:url value="/resources/scripts/ajax-jquery-utils.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/ajax-jquery.js" />" type="text/javascript"></script>
<link href="<c:url value="/resources/css/site.css" />" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="master-container">
		<div id="alert-div" class="alert alert-primary alert-dismissable"
			role="alert">
			<a href="#" class="close" id="close-alert" aria-label="close">&times;</a>
			<div id="message-div"></div>
		</div>

		<div id="controls" class="container-fluid">
			<form action="films"
				id="get-all-films-form" method="get">
				<button class="btn btn-primary btn-md btn-block" type="submit"
					id="button_all_films">Show all films</button>
			</form>
			OR
			<form action="films/"
				id="get-film-form" method="get">
				<div class="form-group">
					<input class="form-control" id="input_search_title" type="text"
						placeholder="Search by title" />
					<button class="btn btn-primary btn-md btn-block" type="submit" id="button_films_by_title">Search</button>
				</div>
			</form>
			<label for="data_type">Return Data As:</label> <select class="custom-select"
				id="data_type">
				<option value="xml" selected="selected">XML</option>
				<option value="json">JSON</option>
				<option value="text">Plain String</option>
			</select>
			<p> </p>
			<button type="button" class="btn btn-success btn-md btn-block"
				data-toggle="modal" data-target="#insert-film-modal">Insert
				Film</button>
		</div>

		<div class="container-fluid">
			<table id="films-table" class="table table-striped table-lg"
				width='100%'></table>
		</div>

		<div class="modal fade" id="insert-film-modal" tabindex="-1"
			role="dialog">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Insert Film</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="films/film"
						id="insert-form" method="post">
						<div class="modal-body">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="formatInsert"
									id="radioJsonInsert" value="json" required> <label
									class="form-check-label" for="radioJsonInsert">Send as JSON,
									Return as JSON</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="formatInsert"
									id="radioXmlInsert" value="xml" required> <label
									class="form-check-label" for="radioXmlInsert">Send as XML,
									Return as XML</label>
							</div>
							<div class="form-group">
								<label for="title"><b>Title</b></label> <input
									class="form-control" type="text" placeholder="Enter Title"
									name="title" required>
							</div>
							<div class="form-group">
								<label for="year"><b>Year</b></label> <input
									class="form-control" type="number" placeholder="Enter Year"
									name="year" required>
							</div>
							<div class="form-group">
								<label for="director"><b>Director</b></label> <input
									class="form-control" type="text" placeholder="Enter Director"
									name="director" required>
							</div>
							<div class="form-group">
								<label for="stars"><b>Stars</b></label> <input
									class="form-control" type="text" placeholder="Enter Stars"
									name="stars" required>
							</div>
							<div class="form-group">
								<label for="review"><b>Review</b></label> <input
									class="form-control" type="text" placeholder="Enter Review"
									name="review" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Insert
								Film</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="modal fade" id="update-film-modal" tabindex="-1"
			role="dialog">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Update Film</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="films/film/"
						id="update-form" method="post">
						<div class="modal-body">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="formatUpdate"
									id="radioJsonUpdate" value="json" required> <label
									class="form-check-label" for="radioJsonUpdate">Send as JSON,
									Return as JSON</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="formatUpdate"
									id="radioXmlUpdate" value="xml" required> <label
									class="form-check-label" for="radioXmlUpdate">Send as XML,
									Return as XML</label>
							</div>
							<div class="form-group">
								<label for="id"><b>Film ID</b></label> <input
									class="form-control" type="text" name="id" id="idUpdate"
									readonly>
							</div>
							<div class="form-group">
								<label for="title"><b>Title</b></label> <input
									class="form-control" type="text" placeholder="Enter Title"
									name="title" id="titleUpdate" required>
							</div>
							<div class="form-group">
								<label for="year"><b>Year</b></label> <input
									class="form-control" type="number" placeholder="Enter Year"
									name="year" id="yearUpdate" required>
							</div>
							<div class="form-group">
								<label for="director"><b>Director</b></label> <input
									class="form-control" type="text" placeholder="Enter Director"
									name="director" id="directorUpdate" required>
							</div>
							<div class="form-group">
								<label for="stars"><b>Stars</b></label> <input
									class="form-control" type="text" placeholder="Enter Stars"
									name="stars" id="starsUpdate" required>
							</div>
							<div class="form-group">
								<label for="review"><b>Review</b></label> <input
									class="form-control" type="text" placeholder="Enter Review"
									name="review" id="reviewUpdate" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Update
								Film</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="modal fade" id="delete-film-modal" tabindex="-1"
			role="dialog">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Delete Film</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete this film?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<form action="films/film/"
							id="delete-form" method="post">
							<button type="submit" class="btn btn-danger"
								id="delete-film-button" data-id="0">Delete Film</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>