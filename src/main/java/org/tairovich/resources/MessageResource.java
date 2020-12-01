package org.tairovich.resources;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.tairovich.model.Message;
import org.tairovich.resources.beans.MessageFilterBean;
import org.tairovich.service.MessageService;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.RequestWrapper;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{messageId}")
    public Message getMessage( @PathParam("messageId") long id){
        return service.getMessage(id);
    }

    @POST
    public Message addMessage(Message message){
        return service.addMessage(message);
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
