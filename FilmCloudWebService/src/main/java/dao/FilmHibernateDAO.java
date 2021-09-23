package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


import model.Film;

/**
 * This DAO implements the IFilmDAO interface contains all CRUD operations using Hibernate.
 * Access this DAO by using the getDAOSingleton() method.
 * @author Nick
 *
 */
public class FilmHibernateDAO implements IFilmDAO {
	private static SessionFactory factory;
	private static FilmHibernateDAO dao;
	
	private FilmHibernateDAO() {
		try {
			factory = new AnnotationConfiguration()
					.configure()
					.addAnnotatedClass(Film.class)
					.buildSessionFactory();
		} catch(Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	// Using singleton. Synchronized helps with multi-threading issues
	public static synchronized FilmHibernateDAO getDAOSingleton() {
		if(dao == null) {
			dao = new FilmHibernateDAO();
		}
		return dao;
	}
	
	// Prevent clone feature that would otherwise break the singleton design pattern if used.
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public Integer insertFilm(Film film) {
		Session session = factory.openSession();
		Integer success = 0;
		try {
			// Ideally, we could use spring for Transactional annotations, so we wouldnt need this
			// Or the try catches...
			session.getTransaction().begin();
			session.save(film);
			session.getTransaction().commit();
			success = 1;
		} catch(HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return success;
	}
	
	public Integer updateFilm(Film film) {
		Session session = factory.openSession();
		Integer success = 0;
		try {
			session.getTransaction().begin();
			session.update(film);
			session.getTransaction().commit();
			success = 1; 
		} catch(HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return success;
	}
	
	public Integer deleteFilm(Integer filmID) {
		Session session = factory.openSession();
		Integer success = 0;
		try {
			session.getTransaction().begin();
			Film film = (Film) session.get(Film.class, filmID);
			session.delete(film);
			session.getTransaction().commit();
			success = 1;
		} catch(HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return success;
	}
	
	public ArrayList<Film> getAllFilms() {
		ArrayList<Film> allFilms = new ArrayList<Film>();
		Session session = factory.openSession();
		try {
			session.getTransaction().begin();
			List<?> films = session.createQuery("FROM Film").list();
			for (Iterator<?> iterator = films.iterator(); iterator.hasNext();) {
				Film film = (Film) iterator.next();
				allFilms.add(film);
			}
			session.getTransaction().commit();
		} catch(HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return allFilms;
	}
	
	public ArrayList<Film> getAllFilmsByTitle(String searchName) {
		ArrayList<Film> matchingFilms = new ArrayList<Film>();
		Session session = factory.openSession();
		try {
			session.getTransaction().begin();
			List<?> films = session.createQuery("From Film f WHERE f.title LIKE '%" + searchName + "%'").list();
			for(Iterator<?> iterator = films.iterator(); iterator.hasNext();) {
				Film film = (Film) iterator.next();
				matchingFilms.add(film);
			}
			session.getTransaction().commit();
		} catch(HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matchingFilms;
	}
	
	public Film getFilmByID(Integer id) {
		Film film = new Film();
		Session session = factory.openSession();
		try {
			session.getTransaction().begin();
			film = (Film) session.get(Film.class, id);
			session.getTransaction().commit();
		} catch(HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return film;
	}
}
