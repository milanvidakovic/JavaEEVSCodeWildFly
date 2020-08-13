package services.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MyJPAException extends WebApplicationException {

    private static final long serialVersionUID = 6181918907887589203L;

    public MyJPAException(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(new ErrorMessage(message)).type(MediaType.APPLICATION_JSON).build());
    }
}