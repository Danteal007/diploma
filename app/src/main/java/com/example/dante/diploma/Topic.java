package com.example.dante.diploma;

import com.example.dante.diploma.Step.Step;

import java.io.Serializable;
import java.util.ArrayList;

public class Topic implements Serializable {

    private int coursePos;
    private int topicPos;
    private String name;
    private ArrayList<Step> steps;

    public Topic(){

    }

    public Topic(int topicPos, String name){
        this.topicPos = topicPos;
        this.name = name;
        steps = new ArrayList<>();
    }

    public int getTopicPos() {
        return topicPos;
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

    public void setTopicPos(int topicPos) {
        this.topicPos = topicPos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public int getCoursePos() {
        return coursePos;
    }

    public void setCoursePos(int coursePos) {
        this.coursePos = coursePos;
    }
}
