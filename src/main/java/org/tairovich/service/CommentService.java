package org.tairovich.service;

import org.tairovich.database.DatabaseClass;
import org.tairovich.model.Comment;
import org.tairovich.model.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getComments(long messageId){
        Map<Long,Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment getComment(long messageId, long commentId){
        Map<Long,Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        return  comment;
    }

    public Comment addComment(long messageId, Comment comment){
        Map<Long,Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size()+1);
        comment.setCreated(new Date());
        comments.put(comment.getId(),comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment){
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) return  null;
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId){
        Map<Long,Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }



}
