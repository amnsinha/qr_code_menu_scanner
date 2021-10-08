package com.hotbot.controller.admin;

import com.google.zxing.NotFoundException;
import com.hotbot.domain.ChefDetails;
import com.hotbot.domain.ChefSettings;
import com.hotbot.domain.OutletDetails;
import com.hotbot.domain.RoleDetails;
import com.hotbot.exception.CureFullException;
import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.adminModel.HotelDiscounts;
import com.hotbot.model.adminModel.HotelInfo;
import com.hotbot.model.adminModel.HotelTables;
import com.hotbot.model.adminModel.PaymentFiles;
import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import com.hotbot.service.HotelBotService;
import com.hotbot.service.admin.AdminService;
import com.hotbot.utils.ResponseBuilder;
import com.hotbot.utils.ResponseSuccessData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4440/")
@RequestMapping("/api/hoboai/admin")
public class AdminController {

    @Autowired
    public HotelBotService hotelBotService;

    @Autowired
    public AdminService adminService;

    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public ResponseEntity<ResponseSuccessData> getAllCategories(HttpServletRequest request) {
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

    // Tables
    @GetMapping(value = "/get-all-tables/{token}")
    public ResponseEntity<ResponseSuccessData> getAllTables(@PathVariable String token)
            throws CureFullException {
        List<HotelTables> hotelDishCategories = adminService.getAllTables(token);
        return ResponseBuilder.getSuccessResponse(hotelDishCategories);
    }

    @MessageMapping("/add-table-web")
    @SendTo("/websocket/add-table-web")
    public ResponseEntity<ResponseSuccessData> addTableWeb(@RequestBody HotelTables hotelTables) {
        boolean addTable = adminService.addTable(hotelTables);
        return ResponseBuilder.getSuccessResponse(addTable);
    }

    @PostMapping(value = "/add-table")
    public ResponseEntity<ResponseSuccessData> addTable(@RequestBody HotelTables hotelTables) {
        boolean addTable = adminService.addTable(hotelTables);
        return ResponseBuilder.getSuccessResponse(addTable);
    }

    @GetMapping(value = "/remove-table/{token}")
    public ResponseEntity<ResponseSuccessData> removeTable(@PathVariable String token)
            throws CureFullException {
        boolean removeTable = adminService.removeTable(token);
        return ResponseBuilder.getSuccessResponse(removeTable);
    }

    @GetMapping(path = "/get-completed-orders/{token}")
    public ResponseEntity<List<OrderDetails>> getAllCompletedOrders(@PathVariable("token") String token) throws CureFullException {
        List<OrderDetails> list = adminService.getAllCompletedOrders(token);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<ResponseSuccessData> login(@RequestParam("username") String username, @RequestParam("password") String password) throws CureFullException {
        HotelInfo hotelInfo = adminService.login(username, password);
        return ResponseBuilder.getSuccessResponse(hotelInfo);
    }

    @PostMapping(value = "/add-chef")
    public ResponseEntity<ResponseSuccessData> addChef(@RequestBody ChefDetails chefDetails) throws CureFullException {
        Boolean added = adminService.addChefDetails(chefDetails);
        return ResponseBuilder.getSuccessResponse(added);
    }

    @GetMapping(value = "/get-chef-details/{hotelId}")
    public ResponseEntity<ResponseSuccessData> getChefDetails(@PathVariable String hotelId) throws CureFullException {
        List<ChefDetails> hotelInfo = adminService.getChefDetails(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelInfo);
    }

    @GetMapping(value = "/delete-chef-details/{chefId}")
    public ResponseEntity<ResponseSuccessData> deleteDetails(@PathVariable String chefId) throws CureFullException {
        Boolean detailsDeleted = adminService.deleteDetails(chefId);
        return ResponseBuilder.getSuccessResponse(detailsDeleted);
    }

    @RequestMapping(path = "/save-update-hotel-settings/{hotelId}", method = RequestMethod.POST)
    public ResponseEntity<Boolean> saveUpdateHotelSettings(@PathVariable("hotelId") String hotelId, @RequestBody HotelSettingsCustomerApplication headerSettings) throws CureFullException {
        Boolean hoteSettings = adminService.saveUpdateHotelSettings(hotelId, headerSettings);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(hoteSettings);
    }


    @RequestMapping(path = "/get-customer-site-settings/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<HotelSettingsCustomerApplication> getHotelSettings(@PathVariable("hotelId") String hotelId) throws CureFullException, MongoDBDocumentNotFoundException {
        HotelSettingsCustomerApplication hotelSettings = adminService.getHotelSettings(hotelId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(hotelSettings);
    }

    @PostMapping(value = "/upload-payment-mode")
    public ResponseEntity<ResponseSuccessData> uploadPaymentDetails(@ModelAttribute PaymentFiles paymentFiles) throws CureFullException, IOException, NotFoundException {
        Boolean added = adminService.uploadPaymentDetails(paymentFiles);
        return ResponseBuilder.getSuccessResponse(added);
    }

    @GetMapping(value = "/get-payment-details/{hotelId}")
    public ResponseEntity<ResponseSuccessData> getPaymentDetails(@PathVariable String hotelId) throws CureFullException {
        PaymentFiles hotelInfo = adminService.getPaymentDetails(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelInfo);
    }

    @GetMapping(value = "/delete-payment-details/{hotelId}/{filenumber}")
    public ResponseEntity<ResponseSuccessData> deletePaymentDetails(@PathVariable String hotelId, @PathVariable Integer filenumber) throws CureFullException {
        Boolean deleted = adminService.deletePaymentDetails(hotelId, filenumber);
        return ResponseBuilder.getSuccessResponse(deleted);
    }

    @PostMapping(value = "/save-update-chef-settings")
    public ResponseEntity<ResponseSuccessData> saveUpdateChefSettings(@RequestBody ChefSettings chefSettings) throws CureFullException {
        Boolean added = adminService.saveUpdateChefSettings(chefSettings);
        return ResponseBuilder.getSuccessResponse(added);
    }


    @RequestMapping(path = "/get-chef-settings/{hotelId}", method = RequestMethod.GET)
    public ResponseEntity<ChefSettings> getChefSettings(@PathVariable("hotelId") String hotelId) throws CureFullException, MongoDBDocumentNotFoundException {
        ChefSettings chefSettings = adminService.getChefSettings(hotelId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(chefSettings);
    }

    // Roles

    @PostMapping(value = "/add-roles")
    public ResponseEntity<ResponseSuccessData> addRoleDetails(@RequestBody RoleDetails roleDetails) throws CureFullException {
        ModelMap model = adminService.addRoleDetails(roleDetails);
        return ResponseBuilder.getSuccessResponse(model);
    }

    @GetMapping(value = "/get-roles/{hotelId}")
    public ResponseEntity<ResponseSuccessData> getRoleDetails(@PathVariable String hotelId) throws CureFullException {
        List<RoleDetails> hotelInfo = adminService.getRoleDetails(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelInfo);
    }

    //Outlets

    @PostMapping(value = "/add-outlet")
    public ResponseEntity<ResponseSuccessData> addOutletDetails(@RequestBody OutletDetails outletDetails) throws CureFullException {
        ModelMap model = adminService.addOutletDetails(outletDetails);
        return ResponseBuilder.getSuccessResponse(model);
    }

    @GetMapping(value = "/get-hotel-outlets/{hotelId}")
    public ResponseEntity<ResponseSuccessData> getOutletDetails(@PathVariable String hotelId) throws CureFullException {
        List<OutletDetails> hotelInfo = adminService.getOutletDetails(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelInfo);
    }
    //Discount
    // Tables
    @GetMapping(value = "/get-all-discounts/{hotelId}")
    public ResponseEntity<ResponseSuccessData> getAlldiscounts(@PathVariable String hotelId)
            throws CureFullException {
        List<HotelDiscounts> hotelDiscountsList = adminService.getAlldiscounts(hotelId);
        return ResponseBuilder.getSuccessResponse(hotelDiscountsList);
    }


    @PostMapping(value = "/add-discounts")
    public ResponseEntity<ResponseSuccessData> addDiscounts(@RequestBody HotelDiscounts hotelDiscounts) {
        boolean addTable = adminService.addDiscounts(hotelDiscounts);
        return ResponseBuilder.getSuccessResponse(addTable);
    }

    @GetMapping(value = "/remove-discounts/{token}")
    public ResponseEntity<ResponseSuccessData> removeDiscounts(@PathVariable String token)
            throws CureFullException {
        boolean removeDiscountss = adminService.removeDiscounts(token);
        return ResponseBuilder.getSuccessResponse(removeDiscountss);
    }

    @PostMapping(value = "/check-discounts")
    public ResponseEntity<ResponseSuccessData> checkDiscount(@RequestBody HotelDiscounts hotelDiscounts)
            throws CureFullException, MongoDBDocumentNotFoundException {
        HotelDiscounts checkExist = adminService.checkDiscount(hotelDiscounts);
        return ResponseBuilder.getSuccessResponse(checkExist);
    }




}
