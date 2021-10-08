package com.hotbot.controller.chef;


import com.hotbot.domain.ChefDetails;
import com.hotbot.exception.CureFullException;
import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.service.HotelBotService;
import com.hotbot.service.chef.ChefService;
import com.hotbot.utils.ResponseBuilder;
import com.hotbot.utils.ResponseSuccessData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/hoboai/chef")
public class ChefController {

    final static Logger LOGGER = LoggerFactory.getLogger(ChefController.class);

    @Autowired
    public HotelBotService hotelBotService;

    @Autowired
    public ChefService chefService;

    @RequestMapping(value = "/getNewOrder", method = RequestMethod.GET)
    public ResponseEntity<ResponseSuccessData> getNewOrder(HttpServletRequest request) throws CureFullException {
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

    @GetMapping(path = "/get-new-orders/{token}/{outletId}")
    public ResponseEntity<List<OrderDetails>> OrderItems(@PathVariable("token") String token,
                                                         @PathVariable("outletId") String outletId) throws CureFullException {
        List<OrderDetails> list = chefService.getAllNewOrders(token, outletId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @GetMapping(path = "/get-processed-orders/{token}/{outletId}")
    public ResponseEntity<List<OrderDetails>> getAllInProgressOrders(@PathVariable("token") String token,
                                                                     @PathVariable("outletId") String outletId) throws CureFullException {
        List<OrderDetails> list = chefService.getAllInProgressOrders(token, outletId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @GetMapping(path = "/get-completed-orders/{token}/{outletId}")
    public ResponseEntity<List<OrderDetails>> getAllCompletedOrders(@PathVariable("token") String token, @PathVariable("outletId") String outletId) throws CureFullException {
        List<OrderDetails> list = chefService.getAllCompletedOrders(token, outletId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @GetMapping(path = "/change-orders-status/{token}")
    public ResponseEntity<ResponseSuccessData> setOrderStatus(@PathVariable("token") String token) throws CureFullException {
        boolean orderStatus = chefService.setOrderStatus(token);
        return ResponseBuilder.getSuccessResponse(orderStatus);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<ResponseSuccessData> login(@RequestParam("username") String username, @RequestParam("password") String password) throws CureFullException {
        ChefDetails chefDetails = chefService.login(username, password);
        return ResponseBuilder.getSuccessResponse(chefDetails);
    }

    @GetMapping(value = "/change-orders-item-status")
    public ResponseEntity<ResponseSuccessData> setOrderItemStatus(@RequestParam("hotelId") String hotelId,
                                                          @RequestParam("orderId") String orderId,
                                                          @RequestParam("status") String status,
                                                          @RequestParam("itemId") String itemId
    ) {
        boolean chefDetails = chefService.setOrderItemStatus(hotelId, orderId, itemId, status);
        return ResponseBuilder.getSuccessResponse(chefDetails);
    }


}
