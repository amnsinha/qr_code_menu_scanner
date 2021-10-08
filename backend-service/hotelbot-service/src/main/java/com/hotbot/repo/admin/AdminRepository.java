package com.hotbot.repo.admin;


import com.hotbot.domain.ChefDetails;
import com.hotbot.domain.ChefSettings;
import com.hotbot.domain.OutletDetails;
import com.hotbot.domain.RoleDetails;
import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.adminModel.HotelDiscounts;
import com.hotbot.model.adminModel.HotelInfo;
import com.hotbot.model.adminModel.HotelTables;
import com.hotbot.model.adminModel.PaymentFiles;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface AdminRepository {
    public boolean removeTable(String getHotelUniqueId, String getHotelTableNo);

    boolean addTable(HotelTables hotelTables);

    List<OrderDetails> getAllCompletedOrders(String token);

    List<HotelTables> getAllTables(String token);

    HotelInfo login(String username, String password);

    boolean addChefDetails(ChefDetails chefDetails);

    boolean deleteDetails(String chefId);

    List<ChefDetails> getChefDetails(String hotelId);

    Boolean saveUpdateHotelSettings(String hotelId, HotelSettingsCustomerApplication hs);

    HotelSettingsCustomerApplication getHotelSettings(String hotelId) throws MongoDBDocumentNotFoundException;

    Boolean uploadPaymentDetails(PaymentFiles paymentFiles, String link);

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

    boolean removeDiscounts(String s, String s1);

    public HotelDiscounts checkDiscountValidity(HotelDiscounts hotelDiscounts) throws MongoDBDocumentNotFoundException;
}
