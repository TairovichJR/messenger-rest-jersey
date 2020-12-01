package org.tairovich.service;

import org.tairovich.database.DatabaseClass;
import org.tairovich.model.Message;
import org.tairovich.model.Profile;

import java.util.*;

public class MessageService {

    //acting like a repository
    private static Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService(){
   //     messages.put(1L, new Message(1L,"Hi there","Omonjon"));
    }


    public List<Message> getMessages(){
        return new ArrayList<>(messages.values());
    }

    public Message getMessage(long id){
        return messages.get(id);
    }

    public List<Message> getMessagesForYear(int year){
        List<Message> newMessages = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()){
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year){
                newMessages.add(message);
            }
        }
        return newMessages;
    }

    public List<Message> getMessagesPaginated(int start, int size){
        List<Message> newMessages = new ArrayList<>(messages.values());
        if (start+size > newMessages.size() ) return new ArrayList<Message>();
        return newMessages.subList(size, start + size);
    }


    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        message.setCreated(new Date());
        messages.put(message.getId(),message);
        return message;
    }

    public Message updateMessage(Message message){
        if(message.getId() <= 0){
            return null;
        }
        message.setCreated(new Date());
        messages.put(message.getId(),message);
        return message;
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }

}
