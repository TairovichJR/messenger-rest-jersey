package org.tairovich.resources;

import org.tairovich.model.Message;
import org.tairovich.resources.beans.MessageFilterBean;
import org.tairovich.service.MessageService;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService service = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {

        if(filterBean.getYear() > 0){
            return  service.getMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getStart() > 0 && filterBean.getSize() > 0){
            return service.getMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return service.getMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage( @PathParam("messageId") long id){
        return service.getMessage(id);
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {

        Message addedMessage = service.addMessage(message);
        String newId = String.valueOf(addedMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
       return Response
               .created(uri)
               .entity(service.addMessage(message))
               .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message){
        message.setId(id);
        return service.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id){
        service.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }
}
