package com.example.dante.diploma;

import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Serializable {
    private String user;
    private String message;
    private ArrayList<Comment> replyes;

    public Comment(){

    }

    public Comment(String user, String message){
        this.user = user;
        this.message = message;
        replyes = new ArrayList<>();
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Comment> getReplyes() {
        return replyes;
    }

    public void addReply(Comment comment){
        replyes.add(comment);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReplyes(ArrayList<Comment> replyes) {
        this.replyes = replyes;
    }
}
