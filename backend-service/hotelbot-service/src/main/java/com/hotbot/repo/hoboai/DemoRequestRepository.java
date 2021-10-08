package com.hotbot.repo.hoboai;


import com.hotbot.model.hoboai.DemoRequest;
import com.hotbot.model.hoboai.HotelEnquiryData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoRequestRepository extends MongoRepository<DemoRequest, String> {

}
