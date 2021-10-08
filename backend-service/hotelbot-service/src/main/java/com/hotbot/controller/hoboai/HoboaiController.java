package com.hotbot.controller.hoboai;

import com.hotbot.exception.CureFullException;
import com.hotbot.model.hoboai.DemoRequest;
import com.hotbot.model.hoboai.HotelEnquiryData;
import com.hotbot.service.hoboai.HoboaiService;
import com.hotbot.utils.ResponseBuilder;
import com.hotbot.utils.ResponseSuccessData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hoboai/web")
public class HoboaiController {

    final static Logger LOGGER = LoggerFactory.getLogger(HoboaiController.class);

    @Autowired
    public HoboaiService hoboaiService;

    @PostMapping(value = "/save-enquire")
    public ResponseEntity<ResponseSuccessData> saveEquire(@RequestBody HotelEnquiryData hotelEnquiryData) throws CureFullException {
        boolean b = hoboaiService.saveEquire(hotelEnquiryData);
        return ResponseBuilder.getSuccessResponse(b);
    }

    @PostMapping(value = "/demo-request")
    public ResponseEntity<ResponseSuccessData> saveEquire(@RequestBody DemoRequest demoRequest) throws CureFullException {
        boolean b = hoboaiService.demoRequest(demoRequest);
        return ResponseBuilder.getSuccessResponse(b);
    }

}
