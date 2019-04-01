package com.example.dante.diploma.Step;

import com.example.dante.diploma.Comment;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    private String text;

    private ArrayList<Comment> comments;

    public Step(){

    }

    public Step(String text){
        this.text = text;

        comments = new ArrayList<Comment>();
    }

    public String getText() {
        return text;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
