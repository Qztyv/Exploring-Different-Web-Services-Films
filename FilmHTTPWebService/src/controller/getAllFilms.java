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

@WebServlet("/getAllFilms")
public class getAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * This servlet method handles get requests, returning ALL films.
	 * 
	 * It essentially turns cache off, uses the DAO to grab all films, parse the films to the chosen format specified by the user
	 * and then dispatch it to a jsp page for the user to see / grab via ajax.
	 */
	// http://localhost:8080/FilmHTTPWebService/getAllFilms
	// http://localhost:8080/FilmHTTPWebService/getAllFilms?format=xml
	// http://localhost:8080/FilmHTTPWebService/getAllFilms?format=json
	// http://localhost:8080/FilmHTTPWebService/getAllFilms?format=text
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get All Films request retrieved!");
		// Prevent cache from being stored
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    
	    // Using factory design pattern to determine Hibernate or Traditional, which then calls a singleton DAO.
	    IFilmDAO filmDAO = FactoryDAO.getFilmDAO("Hibernate");
	    String format = request.getParameter("format");
	     
	    // Set the default format to json
	    if(format == null)
	    	format = "json";
	    
	    System.out.println("Chosen format type for all films is: " + format);
	    
		ArrayList<Film> films = filmDAO.getAllFilms();
		
		// Determine what format was specified, then set content-type and parse films to chosen format accordingly
		String formattedFilms = FilmUtils.formatFilms(response, format, films);
		
		System.out.println("Films have been formatted");
		
		String outputPage = "/WEB-INF/view/films.jsp";
		
		request.setAttribute("formattedFilms", formattedFilms);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
	    dispatcher.include(request, response);
	}
}
