package com.example.dante.diploma.Step.Quiz;

import java.io.Serializable;

public class QuizItem implements Serializable {
    private String answerVariant;
    private boolean isCorrect;

    public QuizItem(){

    }

    public QuizItem(String answerVariant, boolean isCorrect){
        this.answerVariant = answerVariant;
        this.isCorrect = isCorrect;
    }

    public String getAnswerVariant() {
        return answerVariant;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setAnswerVariant(String answerVariant) {
        this.answerVariant = answerVariant;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }



}
