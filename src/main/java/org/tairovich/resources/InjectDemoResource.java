package org.tairovich.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(
                                            @MatrixParam("param") String param,
                                            @HeaderParam("myheader") String header,
                                            @CookieParam("cookieName") String cookie){
        return "Matrix: " + param +  " Header Param: " + header + " Cookie: " + cookie;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
        String absPath = uriInfo.getAbsolutePath().toString();
        String headerInfo = headers.getCookies().toString();
        return "Path: " + absPath + " Cookies: " + headerInfo;
    }

}
