package com.example.dante.diploma;

import com.example.dante.diploma.Step.Step;

import java.io.Serializable;
import java.util.ArrayList;

public class Topic implements Serializable {

    private int topicNumber;
    private String name;
    private ArrayList<Step> steps;

    public Topic(){

    }

    public Topic(int topicNumber, String name){
        this.topicNumber = topicNumber;
        this.name = name;
        steps = new ArrayList<>();
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void addStep(Step step) {
        this.steps.add(step);
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
