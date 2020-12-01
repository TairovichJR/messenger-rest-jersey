package org.tairovich.resources;

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
    @Path("/{id}")
    public Message getMessage( @PathParam("id") long id){
        return service.getMessage(id);
    }

    @POST
    public Message addMessage(Message message){
        return service.addMessage(message);
    }


    @PUT
    @Path("/{id}")
    public Message updateMessage(@PathParam("id") long id, Message message){
        message.setId(id);
        return service.updateMessage(message);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMessage(@PathParam("id") long id){
        service.removeMessage(id);
    }
}
