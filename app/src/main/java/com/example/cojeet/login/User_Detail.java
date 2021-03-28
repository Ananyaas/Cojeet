 package com.example.cojeet.login;

public class User_Detail {

     private String Ref,Username, Email ,Contact ,Age ,Gender ,Height ,Weight ,CoronaStatus ,VaccinationStatus ,Symptoms ,ContactHistory, HealthCondition,State;
     private Double lat,lon;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String ref) {
        Ref = ref;
    }

    public User_Detail() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }



    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

   public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getCoronaStatus() {
        return CoronaStatus;
    }

    public void setCoronaStatus(String coronaStatus) {
        CoronaStatus = coronaStatus;
    }

    public String getVaccinationStatus() {
        return VaccinationStatus;
    }

    public void setVaccinationStatus(String vaccinationStatus) {
        VaccinationStatus = vaccinationStatus;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public String getContactHistory() {
        return ContactHistory;
    }

    public void setContactHistory(String contactHistory) {
        ContactHistory = contactHistory;
    }

    public String getHealthCondition() {
        return HealthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        HealthCondition = healthCondition;
    }


}
