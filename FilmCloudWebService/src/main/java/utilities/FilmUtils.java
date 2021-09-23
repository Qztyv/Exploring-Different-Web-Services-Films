package utilities;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Film;
import model.FilmStore;

public class FilmUtils {
	
	/**
	 * This method takes in an ArrayList of films, and parses it into XML format using JAXB, and returns it as a string
	 * @param films (ArrayList of films)
	 * @return (An XML formatted version of the ArrayList of films)
	 */
	public static String generateXMLFromFilmObject(ArrayList<Film> films) {
		FilmStore filmStore = new FilmStore();
		filmStore.setFilmList(films);
		String result = "";
		try {
			JAXBContext context = JAXBContext.newInstance(FilmStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			m.marshal(filmStore, sw);
			result = sw.toString();
		} catch (JAXBException e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
	/**
	 * This method takes in an object Film, and parses it into XML format using JAXB, and returns it as a string
	 * @param film (object of film)
	 * @return (An XML formatted version of the ArrayList of films)
	 */
	public static String generateXMLFromFilmObject(Film film) {
		String result = "";
		try {
			JAXBContext context = JAXBContext.newInstance(Film.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			m.marshal(film, sw);
			result = sw.toString();
		} catch (JAXBException e) {
	        e.printStackTrace();
	    }
		return result;
	}
	
	/**
	 * This method takes in a buffered reader which is essentially raw data of XML. it will use an unmarshaller to
	 * convert the raw data from XML to a Film object.
	 * @param br (raw data of XML)
	 * @return (An object of Film)
	 */
	public static Film generateFilmObjectFromXML(BufferedReader br) {
		Film film = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Film.class);
			Unmarshaller um = context.createUnmarshaller();
			film = (Film) um.unmarshal(br);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	/**
	 * This method takes in a content type (XML or JSON) and a buffered reader (raw data of XML or JSON).
	 * It will check the content type, and create a Film object from the raw data, using either Gson or JAXB accordingly.
	 * 
	 * @param contentType (application/json or application/xml)
	 * @param br (raw data of XML or JSON)
	 * @return (film object)
	 */
	public static Film generateFilmObjectFromXMLOrJSON(String contentType, BufferedReader br) {
		Film parsedFilm = null;
		if(br != null)
		{
			if(contentType.equals("application/json"))
			{
				parsedFilm = new Gson().fromJson(br, Film.class);
			}
			if(contentType.equals("application/xml"))
			{
				parsedFilm = FilmUtils.generateFilmObjectFromXML(br);
			}
			System.out.println(parsedFilm.toString());
		}
		return parsedFilm;
	}
	
	/**
	 * This method checks what format is requested (XML, JSON or TEXT) and will set the content type and generate the chosen
	 * format from the collection of Films accordingly, into a string of either XML, JSON or TEXT.
	 * @param response (the HttpServletResponse from the servlet, so we can set content-type)
	 * @param format (the format requested)
	 * @param films (the collection of films that is to be parsed into XML / JSON / TEXT)
	 * @return (JSON / XML / TEXT as a string)
	 */
	public static String formatFilms(HttpServletResponse response, String format, ArrayList<Film> films) {
		String formattedFilms = "";
		
		if(format.equals("xml"))
		{
			response.setContentType("application/xml");
			formattedFilms = FilmUtils.generateXMLFromFilmObject(films);
		}
		else if(format.equals("text"))
		{
			response.setContentType("text/plain");
			for(Film film : films)
			{
				formattedFilms += film.toString() + "\n";
			}
		}
		else
		{
			response.setContentType("application/json");
			formattedFilms = new GsonBuilder().setPrettyPrinting().create().toJson(films);
		}
		return formattedFilms;
	}
	
	/**
	 * This method checks what format is requested (XML, JSON or TEXT) and will set the content type and generate the chosen
	 * format from the object Film accordingly, into a string of either XML, JSON or TEXT.
	 * @param response (the HttpServletResponse from the servlet, so we can set content-type)
	 * @param format (the format requested)
	 * @param films (the object Film that is to be parsed into XML / JSON / TEXT)
	 * @return (JSON / XML / TEXT as a string)
	 */
	public static String formatFilm(HttpServletResponse response, String format, Film film) {
		String formattedFilm = "";
		if(format.equals("xml"))
		{
			response.setContentType("application/xml");
			formattedFilm = FilmUtils.generateXMLFromFilmObject(film);
		}
		else if(format.equals("text"))
		{
			response.setContentType("text/plain");
			formattedFilm = film.toString();
		}
		else
		{
			response.setContentType("application/json");
			formattedFilm = new GsonBuilder().setPrettyPrinting().create().toJson(film);
		}
		return formattedFilm;
	}
}
