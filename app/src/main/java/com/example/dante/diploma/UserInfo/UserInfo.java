package com.example.dante.diploma.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable {
    private String email;
    private String name;
    private ArrayList<UserStepInfoItem> courseResults;

    public UserInfo(){

    }

    public UserInfo(String email){
        this.email = email;
        courseResults = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public ArrayList<UserStepInfoItem> getCourseResults() {
        return courseResults;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseResults(ArrayList<UserStepInfoItem> courseResults) {
        this.courseResults = courseResults;
    }
}
