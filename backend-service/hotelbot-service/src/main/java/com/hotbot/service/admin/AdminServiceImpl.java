package com.hotbot.service.admin;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
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
import com.hotbot.repo.admin.AdminRepository;
import com.hotbot.repo.customer.CustomerRepository;
import com.hotbot.utils.DataHideUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdminServiceImpl implements AdminService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AdminRepository adminRepository;

    public static String readQRCode(MultipartFile filePath) throws IOException, NotFoundException, com.google.zxing.NotFoundException {
        Map hintMap = new HashMap();
        byte[] byteArr = filePath.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(inputStream))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);
        return qrCodeResult.getText();
    }

    @Override
    public boolean removeTable(String token) {
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        return adminRepository.removeTable(token_de[0], token_de[1]);
    }

    @Override
    public List<OrderDetails> getAllCompletedOrders(String token) {
        return null;
    }

    @Override
    public boolean addTable(HotelTables hotelTables) {
        hotelTables.setHotelTableUniqueId(DataHideUtils.getUniqueIdEncrypt(hotelTables.getHotelTableNo()));
        return adminRepository.addTable(hotelTables);
    }

    @Override
    public List<HotelTables> getAllTables(String token) {
        return adminRepository.getAllTables(token);
    }

    @Override
    public HotelInfo login(String username, String password) {
        return adminRepository.login(username, password);
    }

    @Override
    public boolean addChefDetails(ChefDetails chefDetails) {
        chefDetails.setChefUniqueId(DataHideUtils.getUniqueIdEncrypt(chefDetails.getChefPhoneNumber()));
        return adminRepository.addChefDetails(chefDetails);
    }

    @Override
    public boolean deleteDetails(String chefId) {
        return adminRepository.deleteDetails(chefId);
    }

    @Override
    public List<ChefDetails> getChefDetails(String hotelId) {
        return adminRepository.getChefDetails(hotelId);
    }

    @Override
    public Boolean saveUpdateHotelSettings(String hotelId, HotelSettingsCustomerApplication hs) {
        return adminRepository.saveUpdateHotelSettings(hotelId, hs);
    }

    @Override
    public HotelSettingsCustomerApplication getHotelSettings(String hotelId) throws MongoDBDocumentNotFoundException {
        return adminRepository.getHotelSettings(hotelId);
    }

    @Override
    public Boolean uploadPaymentDetails(PaymentFiles paymentFiles) throws IOException, com.google.zxing.NotFoundException {
        String link = readQRCode(paymentFiles.getFile());
        System.out.println(link);
        return adminRepository.uploadPaymentDetails(paymentFiles , link);
    }

    @Override
    public PaymentFiles getPaymentDetails(String hotelId) {
        return adminRepository.getPaymentDetails(hotelId);
    }

    @Override
    public Boolean deletePaymentDetails(String hotelId, Integer fileIndex) {
        return adminRepository.deletePaymentDetails(hotelId, fileIndex);
    }

    @Override
    public Boolean saveUpdateChefSettings(ChefSettings chefSettings) {
        return adminRepository.saveUpdateChefSettings(chefSettings);
    }

    @Override
    public ChefSettings getChefSettings(String hotelId) {
        return adminRepository.getChefSettings(hotelId);
    }

    @Override
    public ModelMap addRoleDetails(RoleDetails roleDetails) {
        return adminRepository.addRoleDetails(roleDetails);
    }

    @Override
    public List<RoleDetails> getRoleDetails(String hotelId) {
        return adminRepository.getRoleDetails(hotelId);
    }

    @Override
    public List<OutletDetails> getOutletDetails(String hotelId) {
        return adminRepository.getOutletDetails(hotelId);
    }

    @Override
    public ModelMap addOutletDetails(OutletDetails outletDetails) {
        outletDetails.setOutletUniqueId(DataHideUtils.getUniqueIdEncrypt(outletDetails.getOutletName()));
        return adminRepository.addOutletDetails(outletDetails);
    }

    @Override
    public List<HotelDiscounts> getAlldiscounts(String hotelId) {
        return adminRepository.getAlldiscounts(hotelId);
    }

    @Override
    public boolean addDiscounts(HotelDiscounts hotelDiscounts) {
        hotelDiscounts.setDiscountId(DataHideUtils.getUniqueIdEncrypt(hotelDiscounts.getDiscount()));
        return adminRepository.addDiscounts(hotelDiscounts);
    }

    @Override
    public boolean removeDiscounts(String token) {
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        return adminRepository.removeDiscounts(token_de[0], token_de[1]);
    }

    @Override
    public HotelDiscounts checkDiscount(HotelDiscounts hotelDiscounts) throws MongoDBDocumentNotFoundException {
        return adminRepository.checkDiscountValidity(hotelDiscounts);
    }


}
