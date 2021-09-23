package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.FilmHibernateDAO;
import dao.IFilmDAO;
import model.Film;
import utilities.FilmUtils;

@WebServlet("/getFilmByID")
public class getFilmByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * This servlet method handles get requests for specific films by an ID. 
	 * 
	 * It essentially turns off cache, grabs the ID sent from the user, uses the DAO to select a film by an ID, formats the film
	 * into the format specified by the user, and then send it off for the user to see / grab via ajax.
	 */
	// http://localhost:8080/FilmHTTPWebService/getFilmByID?filmID=10026&format=xml
	// http://localhost:8080/FilmHTTPWebService/getFilmByID?filmID=10026&format=json
	// http://localhost:8080/FilmHTTPWebService/getFilmByID?filmID=10026&format=text
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received request for retrieving a film by ID!");
		// Prevent cache from being stored
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    
	    // use factory design pattern to select the dao I want, and that will grab a singleton
	    IFilmDAO filmDAO = FilmHibernateDAO.getDAOSingleton();
	    
	    String format = request.getParameter("format");
	    String filmIDCheck = request.getParameter("filmID");
	    
	    Integer filmID = -1;
	    
	    // Check if anything was entered before trying to parse
	    if(filmIDCheck != null && !filmIDCheck.trim().isEmpty())
	    	filmID = Integer.parseInt(filmIDCheck);
		
	    // set default format to json
	    if(format == null)
	    	format = "json";
	    
	    System.out.println("Format type requested is: " + format + " and the film ID requested is: " + filmIDCheck );
	    
		Film film = filmDAO.getFilmByID(filmID);
		
		// Determine what format was specified, then set content-type and parse film to chosen format accordingly
		String formattedFilm = FilmUtils.formatFilm(response, format, film);
		
		System.out.println("Film request complete");
		
		String outputPage = "/WEB-INF/view/films.jsp";
		
		request.setAttribute("formattedFilms", formattedFilm);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	    dispatcher.include(request, response);
	}
}
