package com.example.akash.therightstep;

public class post {
String newsHeading,newsDiscription,uid;

    public post(String newsHeading, String newsDiscription, String uid) {
        this.newsHeading = newsHeading;
        this.newsDiscription = newsDiscription;
        this.uid = uid;
    }

    public post() {
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsDiscription() {
        return newsDiscription;
    }

    public void setNewsDiscription(String newsDiscription) {
        this.newsDiscription = newsDiscription;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
