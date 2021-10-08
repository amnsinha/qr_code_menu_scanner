package com.hotbot.model.adminModel;

import java.util.List;

public class HotelInfo {
    String hotelUniqueId;
    String hotelName;
    String hotelManagerNumber;
    String hotelAdmin;
    String hotelContactNo;
    String hotelAddress;
    List<String> hotelAdminAccess;

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelManagerNumber() {
        return hotelManagerNumber;
    }

    public void setHotelManagerNumber(String hotelManagerNumber) {
        this.hotelManagerNumber = hotelManagerNumber;
    }

    public String getHotelAdmin() {
        return hotelAdmin;
    }

    public void setHotelAdmin(String hotelAdmin) {
        this.hotelAdmin = hotelAdmin;
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

    public List<String> getHotelAdminAccess() {
        return hotelAdminAccess;
    }

    public void setHotelAdminAccess(List<String> hotelAdminAccess) {
        this.hotelAdminAccess = hotelAdminAccess;
    }
}
