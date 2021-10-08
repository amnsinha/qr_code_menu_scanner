package com.hotbot.repo.admin;

import com.hotbot.domain.ChefDetails;
import com.hotbot.domain.ChefSettings;
import com.hotbot.domain.OutletDetails;
import com.hotbot.domain.RoleDetails;
import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.adminModel.*;
import com.hotbot.model.customerapplication.HotelTableSessions;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import com.hotbot.mongoCrudHandler.CURDMongoHandler;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CURDMongoHandler cURDMongoHandler;


    public boolean isValidHotel(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("hotelUniqueId").is(hotelId));
        return mongoOperations.exists(findQuery, com.hotbot.model.hotel.HotelInfo.class, "hotel_info");
    }

    public boolean isTableAvailable(String hotelId, Integer tableNo) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelId)
                        .and("hotelTableNo").is(tableNo).and("isEngaged").is(false));
        return mongoOperations.exists(findQuery, HotelTableSessions.class, "hotel_table_sessions");
    }

    @Override
    public boolean removeTable(String getHotelUniqueId, String getHotelTableNo) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(getHotelUniqueId)
                        .and("hotelTableNo").is(getHotelTableNo));
        return cURDMongoHandler.removeDocument(HotelTables.class, findQuery, "hotel-tables");
    }

    @Override
    public boolean addTable(HotelTables hotelTables) {
        boolean addedSuccessfully = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelTables.getHotelUniqueId())
                        .and("hotelTableNo").is(hotelTables.getHotelTableNo()));
        boolean exist = cURDMongoHandler.checkExist(HotelTables.class, findQuery, "hotel-tables");
        System.out.println(exist);
        if (!exist) {
            addedSuccessfully = cURDMongoHandler.saveDocument(hotelTables, "hotel-tables");
        }
        return addedSuccessfully;
    }

    @Override
    public List<OrderDetails> getAllCompletedOrders(String token) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(token)
                        .and("createdOn").gt(new Date()));
        return cURDMongoHandler.getDocuments(OrderDetails.class, findQuery, "customer_order");
    }

    @Override
    public List<HotelTables> getAllTables(String token) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(token));
        return cURDMongoHandler.getDocuments(HotelTables.class, findQuery, "hotel-tables");
    }

    @Override
    public HotelInfo login(String username, String password) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelAdminUsername").is(username).and("hotelAdminPassword").is(password));
        return mongoOperations.findOne(findQuery, HotelInfo.class, "hotel_info");
    }

    @Override
    public boolean addChefDetails(ChefDetails chefDetails) {
        boolean addedSuccessfully = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(chefDetails.getHotelUniqueId())
                        .and("chefUserName").is(chefDetails.getChefUserName()));
        boolean exist = cURDMongoHandler.checkExist(HotelTables.class, findQuery, "hotel_chef_details");
        System.out.println(exist);
        if (!exist) {
            addedSuccessfully = cURDMongoHandler.saveDocument(chefDetails, "hotel_chef_details");
        }
        return addedSuccessfully;
    }

    @Override
    public boolean deleteDetails(String chefId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("chefUniqueId").is(chefId));
        return cURDMongoHandler.removeDocument(HotelTables.class, findQuery, "hotel_chef_details");
    }

    @Override
    public List<ChefDetails> getChefDetails(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelId));
        return cURDMongoHandler.getDocuments(ChefDetails.class, findQuery, "hotel_chef_details");
    }

    @Override
    public Boolean saveUpdateHotelSettings(String hotelId, HotelSettingsCustomerApplication hs) {
        Query findQuery = new Query();
        Boolean b = false;
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        Update update = new Update();

        if (hs.getHotelId() != null)
            update.set("hotelId", hs.getHotelId());

        if (hs.getHeaderSettings() != null) {
            if (hs.getHeaderSettings().getHeaderColor() != null)
                update.set("headerSettings.headerColor", hs.getHeaderSettings().getHeaderColor());
            if (hs.getHeaderSettings().getHeaderName() != null)
                update.set("headerSettings.headerName", hs.getHeaderSettings().getHeaderName());
            if (hs.getHeaderSettings().getHeaderIcon() != null)
                update.set("headerSettings.headerIcon", hs.getHeaderSettings().getHeaderIcon());
            if (hs.getHeaderSettings().getBrandNameColor() != null)
                update.set("headerSettings.brandNameColor", hs.getHeaderSettings().getBrandNameColor());

            update.set("headerSettings.roundIcon", hs.getHeaderSettings().isRoundIcon());
        }

        if (hs.getPageSettings() != null) {
            if (hs.getPageSettings().getAboutus() != null)
                update.set("pageSettings.aboutus", hs.getPageSettings().getAboutus());
            if (hs.getPageSettings().getContactEmailForCustomer() != null)
                update.set("pageSettings.contactEmailForCustomer", hs.getPageSettings().getContactEmailForCustomer());
            if (hs.getPageSettings().getContactNoforCustomer() != null)
                update.set("pageSettings.contactNoforCustomer", hs.getPageSettings().getContactNoforCustomer());
            if (hs.getPageSettings().getContactNoforCustomer2() != null)
                update.set("pageSettings.contactNoforCustomer2", hs.getPageSettings().getContactNoforCustomer2());
            if (hs.getPageSettings().getContactus() != null)
                update.set("pageSettings.contactus", hs.getPageSettings().getContactus());
        }

        try {
            UpdateResult updateResult = mongoOperations.upsert(findQuery, update, HotelSettingsCustomerApplication.class, "hotel_settings");
            if (updateResult != null) {
                b = true;
            }
        } catch (Exception e) {
            b = false;
        }
        return b;

    }

    @Override
    public HotelSettingsCustomerApplication getHotelSettings(String hotelId) throws MongoDBDocumentNotFoundException {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        return mongoOperations.findOne(findQuery, HotelSettingsCustomerApplication.class, "hotel_settings");
    }

    @Override
    public Boolean uploadPaymentDetails(PaymentFiles UploadedPaymentFilesDetails, String link) {

        PaymentFiles p = new PaymentFiles();
        BeanUtils.copyProperties(UploadedPaymentFilesDetails, p);
        p.setFile(null);
        FilesInfo fi = new FilesInfo();
        fi.setFileLink(link);
        List<FilesInfo> li = new ArrayList<>();
        li.add(fi);
        p.setFiles(li);
        boolean addedSuccessfully = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(UploadedPaymentFilesDetails.getHotelId()));
        boolean exist = cURDMongoHandler.checkExist(PaymentFiles.class, findQuery, "hotel_payment_details");

        if (exist) {
            PaymentFiles paymentDetails = getPaymentDetails(UploadedPaymentFilesDetails.getHotelId());
            paymentDetails.getFiles().addAll(li);

            Update update = new Update();

            if (paymentDetails.getHotelId() != null)
                update.set("hotelId", paymentDetails.getHotelId());
            if (paymentDetails.getFiles() != null) {
                update.set("files", paymentDetails.getFiles());
            }
            mongoOperations.upsert(findQuery, update, PaymentFiles.class, "hotel_payment_details");
            addedSuccessfully = true;
        } else {
            addedSuccessfully = cURDMongoHandler.saveDocument(p, "hotel_payment_details");
        }

        return addedSuccessfully;
    }

    @Override
    public PaymentFiles getPaymentDetails(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        return mongoOperations.findOne(findQuery, PaymentFiles.class, "hotel_payment_details");
    }

    @Override
    public Boolean deletePaymentDetails(String hotelId, Integer fileId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        PaymentFiles paymentFiles = getPaymentDetails(hotelId);


        List<FilesInfo> filtertedOrderItems = paymentFiles.getFiles().stream()
                .filter(line -> !fileId.equals(line.getId()))
                .collect(Collectors.toList());

        Update update = new Update();

        if (hotelId != null)
            update.set("hotelId", hotelId);
        if (filtertedOrderItems != null) {
            update.set("files", filtertedOrderItems);
        }
        mongoOperations.upsert(findQuery, update, PaymentFiles.class, "hotel_payment_details");
        return true;
    }

    @Override
    public Boolean saveUpdateChefSettings(ChefSettings hs) {
        Query findQuery = new Query();
        Boolean b = false;
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hs.getHotelUniqueId()));
        Update update = new Update();

        if (hs.getHotelUniqueId() != null)
            update.set("hotelUniqueId", hs.getHotelUniqueId());
        update.set("autoProgressOrder", hs.isAutoProgressOrder());

        try {
            UpdateResult updateResult = mongoOperations.upsert(findQuery, update, HotelSettingsCustomerApplication.class, "hotel_chef_settings");
            if (updateResult != null) {
                b = true;
            }
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    @Override
    public ChefSettings getChefSettings(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelId));
        return mongoOperations.findOne(findQuery, ChefSettings.class, "hotel_chef_settings");

    }

    @Override
    public ModelMap addRoleDetails(RoleDetails roleDetails) {
        ModelMap map = new ModelMap();
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(roleDetails.getHotelId()).and("roleName").is(roleDetails.getRoleName()));
        boolean exist = cURDMongoHandler.checkExist(PaymentFiles.class, findQuery, "hotel_role_details");

        if (exist) {
            map = new ModelMap().addAttribute("Response", "Role Already Exist");
        } else {
            cURDMongoHandler.saveDocument(roleDetails, "hotel_role_details");
            map = new ModelMap().addAttribute("Response", "Role SuccessFully Saved");
        }
        return map;
    }

    @Override
    public List<RoleDetails> getRoleDetails(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        return cURDMongoHandler.getDocuments(RoleDetails.class, findQuery, "hotel_role_details");
    }

    @Override
    public List<OutletDetails> getOutletDetails(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        return cURDMongoHandler.getDocuments(OutletDetails.class, findQuery, "hotel_outlet_details");

    }

    @Override
    public ModelMap addOutletDetails(OutletDetails outletDetails) {
        ModelMap map = new ModelMap();
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(outletDetails.getHotelId()).and("outletName").is(outletDetails.getOutletName()));
        boolean exist = cURDMongoHandler.checkExist(OutletDetails.class, findQuery, "hotel_outlet_details");

        if (exist) {
            map = new ModelMap().addAttribute("Response", "Outlet Already Exist");
        } else {
            cURDMongoHandler.saveDocument(outletDetails, "hotel_outlet_details");
            map = new ModelMap().addAttribute("Response", "Outlet SuccessFully Saved");
        }
        return map;
    }

    @Override
    public List<HotelDiscounts> getAlldiscounts(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelId));
        return cURDMongoHandler.getDocuments(HotelDiscounts.class, findQuery, "hotel_discounts");

    }

    @Override
    public boolean addDiscounts(HotelDiscounts hotelDiscounts) {
        boolean addedSuccessfully = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("discountId").is(hotelDiscounts.getDiscountId())
                        .and("hotelUniqueId").is(hotelDiscounts.getHotelUniqueId()));
        boolean exist = cURDMongoHandler.checkExist(HotelTables.class, findQuery, "hotel_discounts");
        System.out.println(exist);
        if (!exist) {
            addedSuccessfully = cURDMongoHandler.saveDocument(hotelDiscounts, "hotel_discounts");
        }
        return addedSuccessfully;

    }

    @Override
    public boolean removeDiscounts(String hotelId, String discountId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelId)
                        .and("discountId").is(discountId));
        return cURDMongoHandler.removeDocument(HotelDiscounts.class, findQuery, "hotel_discounts");

    }

    @Override
    public HotelDiscounts checkDiscountValidity(HotelDiscounts hotelDiscounts) throws MongoDBDocumentNotFoundException {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("discountCode").is(hotelDiscounts.getDiscountCode())
                        .and("hotelUniqueId").is(hotelDiscounts.getHotelUniqueId()));
        return mongoOperations.findOne(findQuery, HotelDiscounts.class, "hotel_discounts");

    }


}
