package com.example.akash.therightstep;

public class get {
String question,discription,auth,uid;



    public get() {
    }

    public get(String question, String discription, String auth, String uid) {
        this.question = question;
        this.discription = discription;
        this.auth = auth;
        this.uid = uid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
