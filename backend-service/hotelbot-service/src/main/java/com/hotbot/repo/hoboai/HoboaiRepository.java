package com.hotbot.repo.hoboai;


import com.hotbot.model.hoboai.HotelEnquiryData;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HoboaiRepository extends MongoRepository<HotelEnquiryData, String> {

}
