package dao;

import java.util.ArrayList;

import model.Film;

/**
 * This interface is essentially in place to help us swap the DAO if we wanted too (Hibernate or Traditional)
 * Also, if we had unit tests, having an interface would greatly help, as we could use dependency injection to swap out
 * our real DAO in the code for a "Mock DAO", which would contain in memory data.
 * 
 * Insert / Update / Delete return Integer (1 or 0), this could have been boolean, but Traditional DAO 
 * method "executeUpdate" returns an Integer, so I thought it would be best to keep it uniform.
 * @author Nick
 *
 */
public interface IFilmDAO {
	ArrayList<Film> getAllFilms();
	ArrayList<Film> getAllFilmsByTitle(String searchName);
	Film getFilmByID(Integer filmID);
	Integer insertFilm(Film film);
	Integer updateFilm(Film film);
	Integer deleteFilm(Integer filmID);
}
