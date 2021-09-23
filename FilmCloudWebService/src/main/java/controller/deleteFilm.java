package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmHibernateDAO;
import dao.IFilmDAO;
import utilities.ResponseUtils;

@WebServlet("/deleteFilm")
public class deleteFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * This servlet handles delete requests with the doDelete method. 
	 * 
	 * It essentially grabs the id sent by the client, uses the data accessor object (dao) and performs 
	 * a delete on the chosen film.
	 * After the dao performs its delete, the we check for success (1) or failure (else / 0). Depending on what
	 * the result was, a response object is generated and sent to the user in JSON format 
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete request retrieved!");
		
		int filmID = Integer.parseInt(request.getParameter("filmID"));
		
		System.out.println("Film requested to be deleted: " + filmID);
		
		// Using singleton hibernate dao
		IFilmDAO filmDAO = FilmHibernateDAO.getDAOSingleton();
		
		int result = filmDAO.deleteFilm(filmID);
		
		// Determine response to send to user (we are only returning JSON response for delete)
		String responseToUser = ResponseUtils.generateFilmDeleteResponseJSON(filmID, result);
		
		System.out.println(responseToUser);
		response.getWriter().println(responseToUser);
	}



}
