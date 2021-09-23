package utilities;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import model.Response;

public class ResponseUtils {
	
	/**
	 * This method generates a response to send to the user depending on the result and content-type.
	 * If result was a success and the content-type was JSON, it will return a JSON response.
	 * If result was a success and the content-type was XML,  it will return an XML response.
	 * It does this vice versa for if it was a failure.
	 * 
	 * A keyword is passed in, so it can be used technically for all CRUD operations if wanted.
	 * (provided all CRUD operations want to return in either XML or JSON.
	 * @param result (1 success or 0 failure)
	 * @param contentType (JSON or XML)
	 * @param keyword (CRUD operation such as Updated, inserted, deleted, retrieved)
	 * @return (formatted XML or JSON response string)
	 */
	public static String generateFilmResponse(int result, String contentType, String keyword) {
		String responseToUser = "";
		
		if(result == 1 && contentType.equals("application/json"))
		{
			System.out.println("Successful " + keyword + ", check db: " + result);
			responseToUser = new Gson().toJson(new Response("200 : ok", "Film has successfully been " + keyword + " (This response was sent in JSON)"));
		}
		if(result == 1 && contentType.equals("application/xml"))
		{
			System.out.println("Successful " + keyword + ", check db: " + result);
			Response resp = new Response("200 : ok", "Film has successfully been " + keyword + " (This response was sent in XML)");
			responseToUser = generateXMLFromResponseObject(resp);
		}
		if(result == 0 && contentType.equals("application/xml"))
		{
			System.out.println("Unsuccessful " + keyword + ", check error at dao level: " + result);
			Response resp = new Response("error", "Film was not " + keyword + " (This response was sent in XML)");
			responseToUser = generateXMLFromResponseObject(resp);
		}
		if(result == 0 && contentType.equals("application/json"))
		{
			System.out.println("Unsuccessful " + keyword + ", check error at dao level: " + result);
			responseToUser = new Gson().toJson(new Response("error", "Film was not " + keyword + " (This response was sent in JSON)"));
		}
		
		return responseToUser;
	}
	
	/**
	 * This method generates a response specifically for a delete operation, based on whether it was a success or failure.
	 * This is because for delete, we only return JSON responses (even this is going over what the criteria wanted for assignment)
	 * We could use the generateFilmResponse method for delete too, but front end is only expecting JSON, and we can create
	 * a more specific response here, showing the filmID (harder to achieve with creating a film)
	 * @param filmID (film that is to be deleted)
	 * @param result (was it a success or a failure)
	 * @return (response in JSON format)
	 */
	public static String generateFilmDeleteResponseJSON(int filmID, int result) {
		String responseToUser = "";
		if(result == 1)
		{
			System.out.println("Successful delete, check db: " + result);
			responseToUser = new Gson().toJson(new Response("200 : ok", "Film: " + filmID + " has successfully been deleted"));
		}
		else
		{
			System.out.println("Unsuccessful delete, check error at dao level: " + result);
			responseToUser = new Gson().toJson(new Response("error", "There was an issue deleting the Film: " + filmID + " from the database"));
		}
		return responseToUser;
	}
	
	/**
	 * Generates XML from the response object using a marshaller (JAXB), and returns it as a string.
	 * @param response (the response object containing status and message)
	 * @return (the formatted xml of a response object)
	 */
	public static String generateXMLFromResponseObject(Response response) {
		String result = "";
		try {
			JAXBContext context = JAXBContext.newInstance(Response.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			m.marshal(response, sw);
			result = sw.toString();
		} catch (JAXBException e) {
	        e.printStackTrace();
	    }
		return result;
	}
}
