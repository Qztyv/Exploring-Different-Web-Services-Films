package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class is used to generate responses to the user in either JSON or XML format. It contains 
 * a status code (Response), and a message. 
 * 
 * It uses JAXB annotations in order to use a marshaller to format this object into XML format ready to send off.
 * @author Nick
 *
 */
@XmlRootElement(name = "responseXML")
@XmlType(propOrder = { "response", "message" })
public class Response{
	private String response;
	private String message;
	
	public Response() {
		
	}
	
	public Response(String response, String message)
	{
		this.response = response;
		this.message = message;
	}
	
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
