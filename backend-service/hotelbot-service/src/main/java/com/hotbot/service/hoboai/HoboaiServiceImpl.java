package com.hotbot.service.hoboai;

import com.hotbot.model.hoboai.DemoRequest;
import com.hotbot.model.hoboai.HotelEnquiryData;
import com.hotbot.repo.hoboai.DemoRequestRepository;
import com.hotbot.repo.hoboai.HoboaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HoboaiServiceImpl implements HoboaiService {

    @Autowired
    HoboaiRepository hoboaiRepository;

    @Autowired
    DemoRequestRepository demoRequestRepository;


    @Override
    public boolean saveEquire(HotelEnquiryData hotelEnquiryData) {
        boolean b = false;
        try {
            hoboaiRepository.save(hotelEnquiryData);
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    @Override
    public boolean demoRequest(DemoRequest demoRequest) {
        boolean b = false;
        try {
            demoRequestRepository.save(demoRequest);
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }
}
