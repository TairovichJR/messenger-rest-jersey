package org.tairovich.resources;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.tairovich.model.Comment;
import org.tairovich.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class CommentResource {

    private CommentService service = new CommentService();

    @GET
    public List<Comment> getComments(@PathParam("messageId") long messageId){
        return service.getComments(messageId);
    }

    @GET
    @Path("{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
        return service.getComment(messageId,commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
        return service.addComment(messageId,comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long commentId,
                                 Comment comment){
        return service.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
        service.removeComment(messageId, commentId);
    }
}
