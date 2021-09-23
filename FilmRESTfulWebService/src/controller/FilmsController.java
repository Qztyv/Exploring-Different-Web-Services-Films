package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.FilmHibernateDAO;
import dao.IFilmDAO;
import model.Film;
import model.FilmStore;
import utilities.ResponseUtils;

/**
 * This class is our controller that uses spring. It ultimately handles all of our CRUD operations.
 * For all GET requests, it handles JSON, XML and TEXT. 
 * For POST and PUT requests, it handles XML and JSON.
 * For DELETE requests, it simply grabs the id from the PathVariable.
 * 
 * Annotations such as 'RequestMapping' tells spring what type of request that method should handle, what it 
 * should produce or consume as a result, can extend the URL and more.
 * The default response for GET requests is JSON, as it is the first MediaType specified.
 * 
 * We return a ResponseEntity for all methods, as it is an easy and clean way to deliver a response to the user, and to set
 * the HTTPStatus.
 * 
 * There are additional methods to handle users sending GET requests with TEXT as the Accept header
 * (One method handles XML and JSON, and the other handles just text).
 * 
 * @author Nick
 *
 */
@Controller
public class FilmsController {

	// Get the singleton DAO
	IFilmDAO filmDAO = FilmHibernateDAO.getDAOSingleton();
	
	@RequestMapping("/")
	public String index() 
	{
		System.out.println("Opening Index page");
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/films", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody 
	public ResponseEntity<Object> getAllFilms(@RequestHeader(value="Accept") String requestFormat, HttpServletResponse res) 
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Received GET request for all films!");
		ArrayList<Film> films = filmDAO.getAllFilms();
		
		if(films.size() == 0)
			return new ResponseEntity<>("No Films Were Found...", HttpStatus.NO_CONTENT);
		
		if(requestFormat.contains("xml"))
		{
			FilmStore filmStore = new FilmStore(films);
			System.out.println("Returning XML format for all films!");
			return new ResponseEntity<>(filmStore, HttpStatus.OK);
		}
		else
		{
			System.out.println("Returning JSON format for all films!");
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/films", produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody 
	public ResponseEntity<String> getAllFilmsPlainText(HttpServletResponse res) 
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Received GET request for all films!");
		
		ArrayList<Film> films = filmDAO.getAllFilms();
		
		if(films.size() == 0)
			return new ResponseEntity<>("No Films Were Found...", HttpStatus.NO_CONTENT);
		
		String result = "";
		for(Film film : films)
		{
			result += film.toString() + "\n";
		}
		System.out.println("Returning Text format for all films!");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/films/{searchName}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getFilmsByTitle(@RequestHeader(value="Accept") String requestFormat, @PathVariable String searchName, HttpServletResponse res)
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Received GET request for films by title!");
		
		ArrayList<Film> films = filmDAO.getAllFilmsByTitle(searchName);
		
		if(films.size() == 0)
			return new ResponseEntity<>("No Films Were Found Matching: " + searchName, HttpStatus.NO_CONTENT);
		
		if(requestFormat.contains("xml"))
		{
			FilmStore filmStore = new FilmStore(films);
			System.out.println("Returning XML format for films by title!");
			return new ResponseEntity<>(filmStore, HttpStatus.OK);
		}
		else
		{
			System.out.println("Returning JSON format for all films by title!");
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/films/{searchName}", produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody 
	public ResponseEntity<String> getFilmsByTitlePlainText(@PathVariable String searchName, HttpServletResponse res) 
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Received GET request for films by title!");
		
		ArrayList<Film> films = filmDAO.getAllFilmsByTitle(searchName);
		
		if(films.size() == 0)
			return new ResponseEntity<>("No Films Were Found Matching: " + searchName, HttpStatus.NO_CONTENT);
		
		String result = "";
		for(Film film : films)
		{
			result += film.toString() + "\n";
		}
		System.out.println("Returning Text format for all films by title!");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/films/film/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> getFilmByID(@PathVariable Integer id, HttpServletResponse res)
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Received GET request for films by ID!");
		
		Film film = filmDAO.getFilmByID(id);
		
		if(film == null)
			return new ResponseEntity<>("No Film Was Found With ID: " + id, HttpStatus.NO_CONTENT);
		
		System.out.println("Returning formatted film with ID: " + id);
		return new ResponseEntity<>(film, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/films/film/{id}", produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody 
	public ResponseEntity<String> getFilmByIDPlainText(@PathVariable Integer id, HttpServletResponse res) 
	{
		res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Received GET request for films by ID!");
		
		Film film = filmDAO.getFilmByID(id);
		
		if(film == null)
			return new ResponseEntity<>("No Film Was Found With ID: " + id, HttpStatus.NO_CONTENT);
		
		String result = film.toString();
		System.out.println("Returning formatted film with ID: " + id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/films/film", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> insertFilm(@RequestBody Film film)
	{
		System.out.println("Received POST request for creating a film!");
		
		int result = filmDAO.insertFilm(film);
		if(result == 1)
			return new ResponseEntity<>(ResponseUtils.generateFilmResponse(result, "inserted"), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(ResponseUtils.generateFilmResponse(result, "inserted"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/films/film/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> updateFilm(@PathVariable int id, @RequestBody Film film)
	{
		System.out.println("Received PUT request for updating a film!");
		
		film.setId(id);
		int result = filmDAO.updateFilm(film);
		if(result == 1)
			return new ResponseEntity<>(ResponseUtils.generateFilmResponse(result, "updated"), HttpStatus.OK);
		else
			return new ResponseEntity<>(ResponseUtils.generateFilmResponse(result, "updated"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/films/film/{id}")
	@ResponseBody
	public ResponseEntity<Object> deleteFilm(@PathVariable int id)
	{
		System.out.println("Received DELETE request for deleting a film!");
		
		int result = -1;
		try {
			result = filmDAO.deleteFilm(id);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<>(ResponseUtils.generateFilmResponse(result, "deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<>(ResponseUtils.generateFilmResponse(result, "deleted"), HttpStatus.OK);
	}
}
