package com.example.dante.diploma.UserInfo;

import com.example.dante.diploma.Step.Step;

import java.io.Serializable;

public class UserStepInfoItem implements Serializable {
    private Step step;
    private boolean correct;

    public UserStepInfoItem(){

    }

    public UserStepInfoItem(Step step){
        this.step = step;
        this.correct = false;
    }

    public Step getStep() {
        return step;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void makeCorrect() {
        this.correct = true;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
