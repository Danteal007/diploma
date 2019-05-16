package com.example.dante.diploma.Step;

import com.example.dante.diploma.Comment;
import com.example.dante.diploma.Step.Quiz.QuizItem;
import com.example.dante.diploma.StepType;
import com.example.dante.diploma.ViewHolders.Article;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    private int coursePos;
    private int topicPos;
    private int stepPos;
    private StepType stepType;
    private ArrayList<Article> text;
    private String codeBlank;
    private String sampleOutput;
    private ArrayList<QuizItem> quizItems;

    private ArrayList<Comment> comments;

    public Step(){

    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
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

    public String getCodeBlank() {
        return codeBlank;
    }

    public ArrayList<QuizItem> getQuizItems() {
        return quizItems;
    }

    public void setCodeBlank(String codeBlank) {
        this.codeBlank = codeBlank;
    }

    public void setQuizItems(ArrayList<QuizItem> quizItems) {
        this.quizItems = quizItems;
    }

    public StepType getStepType() {
        return stepType;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public int getCoursePos() {
        return coursePos;
    }

    public void setCoursePos(int coursePos) {
        this.coursePos = coursePos;
    }

    public int getTopicPos() {
        return topicPos;
    }

    public void setTopicPos(int topicPos) {
        this.topicPos = topicPos;
    }

    public int getStepPos() {
        return stepPos;
    }

    public void setStepPos(int stepPos) {
        this.stepPos = stepPos;
    }
}
