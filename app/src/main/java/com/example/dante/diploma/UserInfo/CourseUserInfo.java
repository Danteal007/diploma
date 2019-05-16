package com.example.dante.diploma.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseUserInfo implements Serializable {

    private int courseNumber;

    private ArrayList<TopicUserInfo> topicUserInfos;

    public CourseUserInfo(){
        this.topicUserInfos = new ArrayList<TopicUserInfo>();
    }

    public CourseUserInfo(int courseNumber){
        this.topicUserInfos = new ArrayList<TopicUserInfo>();
        this.courseNumber = courseNumber;
    }

    public ArrayList<TopicUserInfo> getTopicUserInfos() {
        return topicUserInfos;
    }

    public void setTopicUserInfos(ArrayList<TopicUserInfo> topicUserInfos) {
        this.topicUserInfos = topicUserInfos;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
}
