package it.unipi.lam.entities;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String roomName;
    private List<User> userList;
    private List<Message> chatMessages;

    public Room(){
        this.userList = new ArrayList<>();
        this.chatMessages = new ArrayList<>();
    }
    public Room(String roomName){
        this.roomName = roomName;
        this.userList = new ArrayList<>();
        this.chatMessages = new ArrayList<>();
    }

    public List<Message> getChatMessages() {
        return chatMessages;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setChatMessages(List<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public void showUsers(){
        for (User u: userList){
            System.out.println(u.getUsername());
        }
    }

    public void join(User u){
        this.userList.add(u);
    }

    public void leave(User u){
        u.changeStatus(false);
        for(User curr: userList){
            if (curr.getUsername().equals(u.getUsername())){
                userList.remove(curr);
                break;
            }
        }
    }

    public void suspend(User u){
        u.changeStatus(false);
    }

    public void wakeup(User u){
        u.changeStatus(true);
    }

    public void sendMessage(Message m){
        System.out.format("%s: %s \n", m.getAuthor().getUsername(), m.getContent());
        this.chatMessages.add(m);
    }

}
