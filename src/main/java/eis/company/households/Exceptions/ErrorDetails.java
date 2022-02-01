package eis.company.households.Exceptions;

import java.util.Date;


public class ErrorDetails {
	//private int statusCode;
	private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(/*int statusCode,*/ Date timestamp, String message, String details) {
        super();
        //this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    

   /*
    public int getStatusCode() {
		return statusCode;
	}
	*/

	public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
   }

    public String getDetails() {
       return details;
   }

}
