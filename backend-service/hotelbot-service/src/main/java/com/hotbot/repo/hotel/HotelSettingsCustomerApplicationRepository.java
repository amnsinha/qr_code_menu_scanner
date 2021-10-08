package com.hotbot.repo.hotel;


import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelSettingsCustomerApplicationRepository extends MongoRepository<HotelSettingsCustomerApplication, String> {

    HotelSettingsCustomerApplication findHotelSettingsCustomerApplicationByHotelId(String HotelId);

}
