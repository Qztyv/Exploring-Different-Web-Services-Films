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

@WebServlet("/updateFilm")
public class updateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * This servlet method handles put requests for updating a film in the database. 
	 * 
	 * It essentially grabs the content type of the request that was sent to the server, uses a buffered-reader to take in
	 * the data, and then parse this raw data (that is XML or JSON) to a Film object accordingly. This film object is then
	 * passed into the DAO to update in the database. A response is generated (in XML or JSON, depending on what 
	 * content type they sent to the server), and is sent back to the user.
	 */
	// http://localhost:8080/FilmHTTPWebService/updateFilm
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received request to update film!");
		
		String contentType = request.getContentType();
		
		// We must use BufferedReader, as the data sent is not of content type "application/x-www-form-urlencoded"
		BufferedReader rawData = new BufferedReader(new InputStreamReader(request.getInputStream()));

		System.out.println("Updating a Film object from data: " + contentType);
		
		Film updatedFilm = FilmUtils.generateFilmObjectFromXMLOrJSON(contentType, rawData);
		
		IFilmDAO filmDAO = FilmHibernateDAO.getDAOSingleton();
		int result = filmDAO.updateFilm(updatedFilm);
		
		String responseToUser = ResponseUtils.generateFilmResponse(result, contentType, "updated");

		System.out.println("Request finished");
		System.out.println(responseToUser);
		response.getWriter().println(responseToUser);
	}	
}
