package com.hotbot.controller;

import com.hotbot.exception.CureFullException;
import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.service.HotelBotService;
import com.hotbot.utils.ResponseBuilder;
import com.hotbot.utils.ResponseSuccessData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/hotelbot")
public class HotelbotController {

    final static Logger LOGGER = LoggerFactory.getLogger(HotelbotController.class);

    @Autowired
    public HotelBotService hotelBotService;

    @GetMapping(value = "/getAllCategories/", consumes = "application/json")
    public ResponseEntity<ResponseSuccessData> getAllCategories(HttpServletRequest request) throws CureFullException {
        List<DishCategories> battleDetailsDocumentResult = hotelBotService.getAllCategories();
        return ResponseBuilder.getSuccessResponse(battleDetailsDocumentResult);
    }

    @RequestMapping(value = "/addCategoryToHotel", method = RequestMethod.POST)
    public ResponseEntity<ResponseSuccessData> addCategoryToHotel(HttpServletRequest request,
                                                                  @RequestParam String hotelId,
                                                                  @RequestBody DishCategories dishCategories) throws CureFullException {
        boolean isCategoryAdded = hotelBotService.addCategoryToHotel(hotelId, dishCategories);
        return ResponseBuilder.getSuccessResponse(isCategoryAdded);
    }

    @RequestMapping(value = "/getAllHotelCategories", method = RequestMethod.GET)
    public ResponseEntity<ResponseSuccessData> getAllHotelCategories(HttpServletRequest request, @RequestParam String hotelId) throws CureFullException {
        List<DishCategories> hotelDishCategories = hotelBotService.getAllHotelCategories(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

    @RequestMapping(value = "/updateOneDishCategory", method = RequestMethod.POST)
    public ResponseEntity<ResponseSuccessData> updateOneDishCategory(HttpServletRequest request,
                                                                     @RequestBody DishCategories dishCategories)
            throws CureFullException {
        boolean hotelDishCategories = hotelBotService.updateOneDishCategory(dishCategories);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

    @RequestMapping(value = "/deleteDishCategory", method = RequestMethod.POST)
    public ResponseEntity<ResponseSuccessData> deleteDishCategory(HttpServletRequest request,
                                                                  @RequestParam Integer categoryId)
            throws CureFullException {
        boolean hotelDishCategories = hotelBotService.deleteDishCategory(categoryId);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

}
