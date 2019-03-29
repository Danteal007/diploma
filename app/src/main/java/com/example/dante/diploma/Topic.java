package com.example.dante.diploma;

import com.example.dante.diploma.Step.Step;

import java.io.Serializable;
import java.util.ArrayList;

public class Topic implements Serializable {

    private double number;
    private String name;
    private ArrayList<Step> steps;

    public Topic(double number, String name){
        this.number = number;
        this.name = name;
        steps = new ArrayList<>();
    }

    public double getNumber() {
        return number;
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

    public void setNumber(double number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
