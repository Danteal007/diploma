package com.example.dante.diploma.Step;

import com.example.dante.diploma.Comment;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    private String text;

    private ArrayList<Comment> comments;
    private int likes;  // Вместо числа потом поставить список пользователей которые поставили лайк, клол-во лайков = кол-во лайкнувших пользователей
    private int dislikes;

    public Step(){

    }

    public Step(String text){
        this.text = text;

        comments = new ArrayList<Comment>();

        likes = 0;
        dislikes = 0;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
