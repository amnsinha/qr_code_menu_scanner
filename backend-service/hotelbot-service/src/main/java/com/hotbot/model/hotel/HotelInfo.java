package com.hotbot.model.hotel;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotel_info")
public class HotelInfo extends AbstractDocument {
    String hotelName;
    String hotelUniqueId;
    String hotelContactNo;
    String hotelAddress;
    String hotelPlanActivated;
    String hotelAdmin;
    String hotelAdminEmailAddress;
    String hotelManagerNumber;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }

    public String getHotelContactNo() {
        return hotelContactNo;
    }

    public void setHotelContactNo(String hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPlanActivated() {
        return hotelPlanActivated;
    }

    public void setHotelPlanActivated(String hotelPlanActivated) {
        this.hotelPlanActivated = hotelPlanActivated;
    }

    public String getHotelAdmin() {
        return hotelAdmin;
    }

    public void setHotelAdmin(String hotelAdmin) {
        this.hotelAdmin = hotelAdmin;
    }

    public String getHotelAdminEmailAddress() {
        return hotelAdminEmailAddress;
    }

    public void setHotelAdminEmailAddress(String hotelAdminEmailAddress) {
        this.hotelAdminEmailAddress = hotelAdminEmailAddress;
    }

    public String getHotelManagerNumber() {
        return hotelManagerNumber;
    }

    public void setHotelManagerNumber(String hotelManagerNumber) {
        this.hotelManagerNumber = hotelManagerNumber;
    }
}
