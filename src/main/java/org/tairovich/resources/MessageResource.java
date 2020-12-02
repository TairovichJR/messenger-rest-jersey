package org.tairovich.resources;

import org.tairovich.model.Link;
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {

        if(filterBean.getYear() > 0){
            return  service.getMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getStart() > 0 && filterBean.getSize() > 0){
            return service.getMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return service.getMessages();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {

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
    public Message getMessage( @PathParam("messageId") long id,@Context UriInfo uriInfo){

        Message message = service.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message),"self");
        message.addLink(getUriForProfile(uriInfo,message),"profile");
        message.addLink(getUriForComments(uriInfo,message),"comments");
        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {

        String uri = uriInfo
                .getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId",message.getId())
                .build().toString();

        System.out.println(uri);
        return uri;
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId()))
                .build().toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message){
       return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {

        Message addedMessage = service.addMessage(message);
        String newId = String.valueOf(addedMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
       return Response
               .created(uri)
               .entity(addedMessage)
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
