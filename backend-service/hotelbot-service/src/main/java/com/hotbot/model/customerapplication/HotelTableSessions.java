package com.hotbot.model.customerapplication;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotel_table_sessions")
public class HotelTableSessions extends AbstractDocument {

    String hotelUniqueId;
    Integer hotelTableNo;
    Boolean isEngaged;
    String customerName;
    String customerMobileNo;
    String customerAge;

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }

    public Integer getHotelTableNo() {
        return hotelTableNo;
    }

    public void setHotelTableNo(Integer hotelTableNo) {
        this.hotelTableNo = hotelTableNo;
    }

    public Boolean getEngaged() {
        return isEngaged;
    }

    public void setEngaged(Boolean engaged) {
        isEngaged = engaged;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }
}
