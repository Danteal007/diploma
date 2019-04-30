package com.example.dante.diploma.Step;

import com.example.dante.diploma.Comment;
import com.example.dante.diploma.Step.Quiz.QuizItem;
import com.example.dante.diploma.ViewHolders.Article;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    private ArrayList<Article> text;

    private int winPoints;
    private String codeBlank;
    private ArrayList<QuizItem> quizItems;

    private ArrayList<Comment> comments;

    public Step(){

    }

    public ArrayList<Article> getText() {
        return text;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setText(ArrayList<Article> articles) {
        this.text = articles;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public int getWinPoints() {
        return winPoints;
    }

    public String getCodeBlank() {
        return codeBlank;
    }

    public ArrayList<QuizItem> getQuizItems() {
        return quizItems;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public void setCodeBlank(String codeBlank) {
        this.codeBlank = codeBlank;
    }

    public void setQuizItems(ArrayList<QuizItem> quizItems) {
        this.quizItems = quizItems;
    }
}
