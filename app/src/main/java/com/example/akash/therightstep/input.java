package com.example.akash.therightstep;

public class input
{
    String iQuestion,iRespose,x;

    public input() {
    }

    public input(String iQuestion, String iRespose, String x) {
        this.iQuestion = iQuestion;
        this.iRespose = iRespose;
        this.x = x;
    }

    public String getiQuestion() {
        return iQuestion;
    }

    public void setiQuestion(String iQuestion) {
        this.iQuestion = iQuestion;
    }

    public String getiRespose() {
        return iRespose;
    }

    public void setiRespose(String iRespose) {
        this.iRespose = iRespose;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
