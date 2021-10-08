package com.hotbot.model.hoboai;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enquiry_leads")
public class HotelEnquiryData {
    private String hotelName;
    private String hotelAdmin;
    private String hotelAddress;
    private String hotePlanOpt;
    private int hotelContactNo;
    private String hotelEmailAddress;
    private String subscriptionduration;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAdmin() {
        return hotelAdmin;
    }

    public void setHotelAdmin(String hotelAdmin) {
        this.hotelAdmin = hotelAdmin;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotePlanOpt() {
        return hotePlanOpt;
    }

    public void setHotePlanOpt(String hotePlanOpt) {
        this.hotePlanOpt = hotePlanOpt;
    }

    public int getHotelContactNo() {
        return hotelContactNo;
    }

    public void setHotelContactNo(int hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
    }

    public String getHotelEmailAddress() {
        return hotelEmailAddress;
    }

    public void setHotelEmailAddress(String hotelEmailAddress) {
        this.hotelEmailAddress = hotelEmailAddress;
    }

    public String getSubscriptionduration() {
        return subscriptionduration;
    }

    public void setSubscriptionduration(String subscriptionduration) {
        this.subscriptionduration = subscriptionduration;
    }
}
