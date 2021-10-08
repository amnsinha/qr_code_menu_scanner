package com.hotbot.controller;

import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.model.customerapplication.Items;
import com.hotbot.exception.CureFullException;
import com.hotbot.service.ItemService;
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
@RequestMapping("/api/item")
public class ItemController {

    final static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemService itemService;

    @RequestMapping(value = "/getAllItem", method = RequestMethod.GET)
    public ResponseEntity<ResponseSuccessData> getAllCategories(HttpServletRequest request) throws CureFullException {
        List<Items> battleDetailsDocumentResult = itemService.getAllItem();
        return ResponseBuilder.getSuccessResponse(battleDetailsDocumentResult);
    }

    @RequestMapping(value = "/addItemToHotel", method = RequestMethod.POST)
    public ResponseEntity<ResponseSuccessData> addCategoryToHotel(HttpServletRequest request,
                                                                  @RequestParam String hotelId, @RequestBody DishCategories dishCategories) throws CureFullException {
        boolean isCategoryAdded = itemService.addItemToHotel(hotelId, dishCategories);
        return ResponseBuilder.getSuccessResponse(isCategoryAdded);
    }

    @RequestMapping(value = "/getAllHotelItems", method = RequestMethod.GET)
    public ResponseEntity<ResponseSuccessData> getAllHotelCategories(HttpServletRequest request, @RequestParam String hotelId) throws CureFullException {
        List<DishCategories> hotelDishCategories = itemService.getAllHotelItems(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

    @RequestMapping(value = "/updateOneItem", method = RequestMethod.POST)
    public ResponseEntity<ResponseSuccessData> updateOneDishCategory(HttpServletRequest request,
                                                                     @RequestBody DishCategories dishCategories)
            throws CureFullException {
        boolean hotelDishCategories = itemService.updateOneItem(dishCategories);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    public ResponseEntity<ResponseSuccessData> deleteDishCategory(HttpServletRequest request,
                                                                  @RequestParam Integer categoryId)
            throws CureFullException {
        boolean hotelDishCategories = itemService.deleteItem(categoryId);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

}
