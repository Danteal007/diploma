package com.example.dante.diploma.UserInfo;

import java.io.Serializable;

public class StepUserInfo implements Serializable {
    private int stepNumber;
    private Boolean correct;

    public StepUserInfo(){

    }

    public StepUserInfo(int stepNumber, Boolean correct){
        this.stepNumber = stepNumber;
        this.correct = correct;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
