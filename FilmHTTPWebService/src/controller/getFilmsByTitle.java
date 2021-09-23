package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.FactoryDAO;
import dao.IFilmDAO;
import model.Film;
import utilities.FilmUtils;

@WebServlet("/getFilmsByTitle")
public class getFilmsByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * This servlet method handles get requests for specific films by title.
	 * 
	 * It essentially turns off cache, grabs the format type and title to search by from the user and then
	 * uses the data accessor object to select these films. The film object is formatted accordingly, and is then
	 * dispatched out to a jsp page for the user to see / grab by ajax.
	 */
	// http://localhost:8080/FilmHTTPWebService/getFilm?filmname=alien
	
	// http://localhost:8080/FilmHTTPWebService/getFilm?filmname=alien&format=xml
	// http://localhost:8080/FilmHTTPWebService/getFilm?filmname=alien&format=json
	// http://localhost:8080/FilmHTTPWebService/getFilm?filmname=alien&format=text
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received request for retrieving all films by title!");
		// Prevent cache from being stored
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    
	    // use factory design pattern to select the dao I want, and that will grab a singleton
	    IFilmDAO filmDAO = FactoryDAO.getFilmDAO("Hibernate");
	    
	    String format = request.getParameter("format");
		String searchFilmName = request.getParameter("filmname");
		
	    if(format == null)
	    	format = "json";
	    
	    System.out.println("Format type requested is: " + format + " and the search name is: " + searchFilmName);
	    
		ArrayList<Film> films = filmDAO.getAllFilmsByTitle(searchFilmName);
		
		// Determine what format was specified, then set content-type and parse films to chosen format accordingly
		String formattedFilms = FilmUtils.formatFilms(response, format, films);
		
		System.out.println("Films have been formatted");
		String outputPage = "/WEB-INF/view/films.jsp";
		
		request.setAttribute("formattedFilms", formattedFilms);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	    dispatcher.include(request, response);
	}
}
