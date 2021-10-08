package com.hotbot.service.hoboai;

import com.hotbot.model.hoboai.DemoRequest;
import com.hotbot.model.hoboai.HotelEnquiryData;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import org.springframework.stereotype.Service;

@Service
public interface HoboaiService {
    public boolean saveEquire(HotelEnquiryData hotelUniqueId);
    public boolean demoRequest(DemoRequest demoRequest);
}
