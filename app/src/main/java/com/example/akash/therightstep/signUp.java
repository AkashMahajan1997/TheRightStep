package com.example.akash.therightstep;

public class signUp {
    String user,password,email,gender,qualification;

    public signUp(String user, String password, String email, String gender, String qualification) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.qualification = qualification;
    }

    public signUp() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
