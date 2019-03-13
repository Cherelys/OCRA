package com.ocra.dwarbi.ocra.Entities;

public class Formulier{

    String phone, birthday,gender,birthplace,email,previouseducation,major;

    public Formulier(String phone, String birthday, String gender, String birthplace, String email, String previouseducation, String major) {
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.birthplace = birthplace;
        this.email = email;
        this.previouseducation = previouseducation;
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreviouseducation() {
        return previouseducation;
    }

    public void setPreviouseducation(String previouseducation) {
        this.previouseducation = previouseducation;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
