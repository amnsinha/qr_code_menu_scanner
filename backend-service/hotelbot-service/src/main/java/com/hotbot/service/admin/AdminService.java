package com.hotbot.service.admin;

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
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@Service
public interface AdminService {
    boolean removeTable(String token);

    List<OrderDetails> getAllCompletedOrders(String token);

    boolean addTable(HotelTables hotelTables);

    List<HotelTables> getAllTables(String token);

    HotelInfo login(String username, String password);

    //-------------------//
    boolean addChefDetails(ChefDetails chefDetails);

    boolean deleteDetails(String chefId);

    List<ChefDetails> getChefDetails(String hotelId);

    Boolean saveUpdateHotelSettings(String hotelId, HotelSettingsCustomerApplication hs);

    HotelSettingsCustomerApplication getHotelSettings(String hotelId) throws MongoDBDocumentNotFoundException;

    Boolean uploadPaymentDetails(PaymentFiles paymentFiles) throws IOException, NotFoundException;

    PaymentFiles getPaymentDetails(String hotelId);

    Boolean deletePaymentDetails(String hotelId, Integer fileIndex);

    Boolean saveUpdateChefSettings(ChefSettings chefSettings);

    ChefSettings getChefSettings(String hotelId);

    ModelMap addRoleDetails(RoleDetails roleDetails);

    List<RoleDetails> getRoleDetails(String hotelId);

    List<OutletDetails> getOutletDetails(String hotelId);

    ModelMap addOutletDetails(OutletDetails outletDetails);

    List<HotelDiscounts> getAlldiscounts(String hotelId);

    boolean addDiscounts(HotelDiscounts hotelDiscounts);

    boolean removeDiscounts(String token);

    HotelDiscounts checkDiscount(HotelDiscounts hotelDiscounts) throws MongoDBDocumentNotFoundException;


}
