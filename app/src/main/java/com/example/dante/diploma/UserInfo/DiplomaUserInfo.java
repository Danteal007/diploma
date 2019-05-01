package com.example.dante.diploma.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class DiplomaUserInfo implements Serializable {
    private String name;
    private String lastName;

    public DiplomaUserInfo(){

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
}
