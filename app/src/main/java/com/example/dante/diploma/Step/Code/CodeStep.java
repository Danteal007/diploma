package com.example.dante.diploma.Step.Code;

import com.example.dante.diploma.Step.Step;

import java.io.Serializable;

public class CodeStep extends Step implements Serializable {
    private int winPoints;
    private String codeBlank;

    public CodeStep(String text, int winPoints, String codeBlank){
        super(text);
        this.codeBlank = codeBlank;
        this.winPoints = winPoints;
    }

    public int getWinPoints() {
        return winPoints;
    }

    public String getCodeBlank() {
        return codeBlank;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public void setCodeBlank(String codeBlank) {
        this.codeBlank = codeBlank;
    }
}
