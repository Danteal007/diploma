package com.example.dante.diploma.Course;

import com.example.dante.diploma.Topic.Topic;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    private int coursePos;
    private String name;
    private ArrayList<Topic> topics;

    public Course(){

    }

    public Course(String name){
        this.name = name;
        topics = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public int getCoursePos() {
        return coursePos;
    }

    public void setCoursePos(int coursePos) {
        this.coursePos = coursePos;
    }
}
