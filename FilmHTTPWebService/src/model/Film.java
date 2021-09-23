package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *  This class uses Hibernate annotations, as well as JAXB annotations. The hibernate annotations enables hibernate
 *  to map this object to database, allowing us to use all CRUD operations on it.
 *  
 *  JAXB annotations helps JAXB formulate an XML response with a marshaller from an object of Film
 *  
 *  ToString has been overridden with hashtag delimiter to help with returning text format for the user 
 *  (useful if they are using spreadsheets or other such things..)
 * @author Nick
 *
 */
@Entity
@Table(name="films")
@XmlRootElement(name = "film")
@XmlType(propOrder = { "id", "title", "year", "director", "stars", "review" })
public class Film {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="year")
	private int year;
	
	@Column(name="director")
	private String director;
	
	@Column(name="stars")
	private String stars;
	
	@Column(name="review")
	private String review;
	
	public Film() {
	}

	public Film(int id, String title, int year, String director, String stars, String review) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}
	
	public Film(String title, int year, String director, String stars, String review) {
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return id + "#" + title + "#" + year + "#" + director + "#" + stars + "#" + review;
	}
}
