package com.example.dante.diploma.UserInfo;

import com.example.dante.diploma.EducationPlaceInfo.EducationPlaceInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class DiplomaUserInfo implements Serializable {
    private String name;
    private String lastName;
    private int userType;
    private int educationPlace;
    private EducationPlaceInfo educationPlaceInfo;

    private ArrayList<CourseUserInfo> courseUserInfos;

    public DiplomaUserInfo(){
        courseUserInfos = new ArrayList<CourseUserInfo>();

    }

    public DiplomaUserInfo(String name, String lastName){
        this.name = name;
        this.lastName = lastName;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<CourseUserInfo> getCourseUserInfos() {
        return courseUserInfos;
    }

    public void setCourseUserInfos(ArrayList<CourseUserInfo> courseUserInfos) {
        this.courseUserInfos = courseUserInfos;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getEducationPlace() {
        return educationPlace;
    }

    public void setEducationPlace(int educationPlace) {
        this.educationPlace = educationPlace;
    }

    public EducationPlaceInfo getEducationPlaceInfo() {
        return educationPlaceInfo;
    }

    public void setEducationPlaceInfo(EducationPlaceInfo educationPlaceInfo) {
        this.educationPlaceInfo = educationPlaceInfo;
    }
}
