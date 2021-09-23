package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		Transaction tx = null;
		Integer success = 0;
		try {
			tx = session.beginTransaction();
			session.save(film);
			tx.commit();
			success = 1;
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return success;
	}
	
	public Integer updateFilm(Film film) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer success = 0;
		try {
			tx = session.beginTransaction();
			session.update(film);
			tx.commit();
			success = 1; 
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return success;
	}
	
	public Integer deleteFilm(Integer filmID) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer success = 0;
		try {
			tx = session.beginTransaction();
			Film film = (Film) session.get(Film.class, filmID);
			session.delete(film);
			tx.commit();
			success = 1;
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return success;
	}
	
	public ArrayList<Film> getAllFilms() {
		ArrayList<Film> allFilms = new ArrayList<Film>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> films = session.createQuery("FROM Film").list();
			for (Iterator<?> iterator = films.iterator(); iterator.hasNext();) {
				Film film = (Film) iterator.next();
				allFilms.add(film);
			}
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return allFilms;
	}
	
	public ArrayList<Film> getAllFilmsByTitle(String searchName) {
		ArrayList<Film> matchingFilms = new ArrayList<Film>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> films = session.createQuery("From Film f WHERE f.title LIKE '%" + searchName + "%'").list();
			for(Iterator<?> iterator = films.iterator(); iterator.hasNext();) {
				Film film = (Film) iterator.next();
				matchingFilms.add(film);
			}
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return matchingFilms;
	}
	
	public Film getFilmByID(Integer id) {
		Film film = new Film();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			film = (Film) session.get(Film.class, id);
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return film;
	}
}
