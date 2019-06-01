package com.example.dante.diploma.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class TopicUserInfo implements Serializable {

    private int topicNumber;
    private ArrayList<StepUserInfo> stepUserInfos;

    public TopicUserInfo(){
        this.stepUserInfos = new ArrayList<StepUserInfo>();
    }
    public TopicUserInfo(int topicNumber){
        this.stepUserInfos = new ArrayList<StepUserInfo>();
        this.topicNumber = topicNumber;
    }
    public ArrayList<StepUserInfo> getStepUserInfos() {
        return stepUserInfos;
    }

    public void setStepUserInfos(ArrayList<StepUserInfo> stepUserInfos) {
        this.stepUserInfos = stepUserInfos;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }
}
