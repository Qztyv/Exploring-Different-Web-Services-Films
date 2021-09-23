package model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class uses JAXB annotations to help create an XML response from a marshaller, for a collection of films.
 * @author Nick
 *
 */
@XmlRootElement(namespace = "model")
public class FilmStore {
	
	public FilmStore(ArrayList<Film> filmList) 
	{
		this.filmList = filmList;
	}
	
	public FilmStore() {
		
	}
	
	@XmlElementWrapper(name = "filmList")
	@XmlElement(name = "film")
	private ArrayList<Film> filmList;
	
	public ArrayList<Film> getFilmsList() {
		return filmList;
	}
	
	public void setFilmList(ArrayList<Film> filmList) {
		this.filmList = filmList;
	}
}
