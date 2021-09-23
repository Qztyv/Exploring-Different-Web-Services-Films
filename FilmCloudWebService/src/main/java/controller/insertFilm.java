package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.FilmHibernateDAO;
import dao.IFilmDAO;
import model.Film;
import utilities.FilmUtils;
import utilities.ResponseUtils;

@WebServlet("/insertFilm")
public class insertFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This servlet method handles post requests for inserting a film into the database.
	 * 
	 * It essentially grabs the content type of the request that was sent to the server, uses a buffered-reader to take in
	 * the data, and then parse this raw data (that is XML or JSON) to a Film object accordingly. This film object is then
	 * passed into the DAO for insertion into the database. A response is generated (in XML or JSON depending on what 
	 * content type they sent to the server), and is sent back to the user.
	 */
	// http://localhost:8080/FilmHTTPWebService/insertFilm
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received request for inserting a film into the database!");
		String contentType = request.getContentType();
		
		// We must use BufferedReader, as the data sent is not of content type "application/x-www-form-urlencoded"
		// This will essentially grab the raw data sent in from the user
		BufferedReader rawData = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
		System.out.println("Creating a Film object from data: " + contentType);
		
		// Parse the raw data into a film object, so we can pass it into the DAO
		Film newFilm = FilmUtils.generateFilmObjectFromXMLOrJSON(contentType, rawData);
		
		// Using factory design pattern to get film dao (Hibernate or Traditional), and that will bring back a singleton
		IFilmDAO filmDAO = FilmHibernateDAO.getDAOSingleton();
		int result = filmDAO.insertFilm(newFilm);
		
		// Generate a response to send back to the user (we will send them back XML if they sent us XML, or vice versa for JSON)
		String responseToUser = ResponseUtils.generateFilmResponse(result, contentType, "inserted");
		
		System.out.println("Request finished");
		System.out.println(responseToUser);
		response.getWriter().println(responseToUser);
	}
}
