package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import model.Film;

/**
 * This DAO implements the IFilmDAO interface contains all CRUD operations using the traditional way (loading jdbc etc..).
 * Access this DAO by using the getDAOSingleton() method.
 * @author Nick
 *
 */
public class FilmTraditionalDAO implements IFilmDAO {
	private static FilmTraditionalDAO dao;
	
	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "cowlingn";
	String password = "Phinglen6";
	// Note none default port used, 6306 not 3306
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;

	private FilmTraditionalDAO() {
	}
	
	// Using singleton. Synchronized helps with multi-threading issues
	public static synchronized FilmTraditionalDAO getDAOSingleton() {
		if(dao == null) {
			dao = new FilmTraditionalDAO();
		}
		return dao;
	}
	
	// Prevent clone feature that would otherwise break the singleton design pattern if used.
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilms;
	}

	public Film getFilmByID(Integer id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}

	public ArrayList<Film> getAllFilmsByTitle(String searchName) {

		ArrayList<Film> allFilmsByTitle = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where title LIKE '%" + searchName + "%'";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilmsByTitle.add(oneFilm);
			}
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilmsByTitle;
	}
	
	public Integer insertFilm(Film film)
	{
		int result = 0;
		openConnection();
		
		try {
			// Removed ID (meaning the statement is bigger, as I am now specifying the columns),
			// This is beneficial however, as it will mean that the primary key ID will auto-increment every insert
			// It is good practice to specify columns incase the columns are adjusted in the future also.
			
			// Could use prepared statements easily if required (prevents SQL injection)
			String insertSQL = "INSERT INTO films (title, year, director, stars, review) " +
						"VALUES ('" + film.getTitle() + "'," + film.getYear() + ",'" + film.getDirector() + "','" + film.getStars() + "','" + film.getReview() + "');";
			
			result = stmt.executeUpdate(insertSQL);
			
			stmt.close();
			closeConnection();
			} catch(SQLException se) {
			System.out.println(se);
		} catch(NullPointerException se) {
			System.out.println(se);
		}
		
		return result;
	}
	
	public Integer updateFilm(Film film)
	{
		int result = 0;
		
		openConnection();
		
		try {
			String updateSQL = "UPDATE films SET title = '" + film.getTitle() + "', year = " + film.getYear()
							+ ", director = '" + film.getDirector() + "', stars = '" + film.getStars() + "', review = '" + film.getReview()
							+ "' WHERE id = " + film.getId() + ";";
			result = stmt.executeUpdate(updateSQL);
			
			stmt.close();
			closeConnection();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		return result;
	}
	
	public Integer deleteFilm(Integer filmID)
	{
		int result = 0;
		
		openConnection();
		
		try {
			String deleteSQL = "DELETE FROM films WHERE id = " + filmID + ";";
			result = stmt.executeUpdate(deleteSQL);
			
			stmt.close();
			closeConnection();
		} catch(SQLException se) {
			System.out.println(se);
		}
		
		return result;
	}
	
}
