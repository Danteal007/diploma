package com.example.dante.diploma.Step.Quiz;

import com.example.dante.diploma.Step.Step;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizStep extends Step implements Serializable {
    private int winPoints;
    private ArrayList<QuizItem> quizItems;

    public QuizStep(){

    }

    public QuizStep(String text, int winPoints, ArrayList<QuizItem> quizItems){
        super(text);
        this.winPoints = winPoints;
        this.quizItems = quizItems;
        quizItems = new ArrayList<>();
    }

    public int getWinPoints() {
        return winPoints;
    }

    public ArrayList<QuizItem> getQuizItems() {
        return quizItems;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public void setQuizItems(ArrayList<QuizItem> quizItems) {
        this.quizItems = quizItems;
    }
}
