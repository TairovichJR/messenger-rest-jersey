package org.tairovich.exception;

import org.tairovich.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), 500,
                "http://api.tairovich.com/docs/internalerrors");
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}
