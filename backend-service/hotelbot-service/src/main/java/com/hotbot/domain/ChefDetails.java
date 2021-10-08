package com.hotbot.domain;

import com.hotbot.model.AbstractDocument;

public class ChefDetails extends AbstractDocument {
    private String chefName;
    private String chefUserName;
    private String chefPassword;
    private String hotelUniqueId;
    private String chefUniqueId;
    private String emailAddress;
    private String chefCity;
    private String chefPhoneNumber;
    private String chefBirthday;
    private int chefExperience;
    private String chefDetails;
    private OutletDetails outletHolds;

    public String getChefPassword() {
        return chefPassword;
    }

    public void setChefPassword(String chefPassword) {
        this.chefPassword = chefPassword;
    }

    public OutletDetails getOutletHolds() {
        return outletHolds;
    }

    public void setOutletHolds(OutletDetails outletHolds) {
        this.outletHolds = outletHolds;
    }

    public String getChefBirthday() {
        return chefBirthday;
    }

    public void setChefBirthday(String chefBirthday) {
        this.chefBirthday = chefBirthday;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getChefCity() {
        return chefCity;
    }

    public void setChefCity(String chefCity) {
        this.chefCity = chefCity;
    }

    public String getChefUserName() {
        return chefUserName;
    }

    public void setChefUserName(String chefUserName) {
        this.chefUserName = chefUserName;
    }

    public String getChefPhoneNumber() {
        return chefPhoneNumber;
    }

    public void setChefPhoneNumber(String chefPhoneNumber) {
        this.chefPhoneNumber = chefPhoneNumber;
    }

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }

    public String getChefUniqueId() {
        return chefUniqueId;
    }

    public void setChefUniqueId(String chefUniqueId) {
        this.chefUniqueId = chefUniqueId;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public int getChefExperience() {
        return chefExperience;
    }

    public void setChefExperience(int chefExperience) {
        this.chefExperience = chefExperience;
    }

    public String getChefDetails() {
        return chefDetails;
    }

    public void setChefDetails(String chefDetails) {
        this.chefDetails = chefDetails;
    }
}
