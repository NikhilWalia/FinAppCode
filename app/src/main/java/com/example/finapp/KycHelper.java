package com.example.finapp;

public class KycHelper {
    public KycHelper() {
    }

    public KycHelper(String firstName, String lastName, String adhar, String pan, String phoneNo, String dob, String email, String alterEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adhar = adhar;
        this.pan = pan;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.email = email;
        this.alterEmail = alterEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlterEmail() {
        return alterEmail;
    }

    public void setAlterEmail(String alterEmail) {
        this.alterEmail = alterEmail;
    }

    String firstName, lastName, adhar, pan, phoneNo, dob, email, alterEmail;
}
