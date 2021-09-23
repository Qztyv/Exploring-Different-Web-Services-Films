package utilities;

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
	public static Response generateFilmResponse(int result, String keyword) {
		
		Response responseToUser = null;
		
		if(result == 1)
		{
			System.out.println("Successful " + keyword + ", check db: " + result);
			responseToUser = new Response("200 : ok", "Film has successfully been " + keyword);
		}
		else
		{
			System.out.println("Unsuccessful " + keyword + ", check error at dao level: " + result);
			responseToUser = new Response("error", "Film was not " + keyword);
		}
		
		return responseToUser;
	}
}
