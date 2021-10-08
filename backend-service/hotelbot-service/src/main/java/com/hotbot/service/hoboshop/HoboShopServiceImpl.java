package com.hotbot.service.hoboshop;

import com.hotbot.model.hoboshop.HoboShopAttribute;
import com.hotbot.model.hoboshop.HoboShopRegister;
import com.hotbot.repo.hoboshop.HoboShopRepository;
import com.hotbot.utils.DataHideUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HoboShopServiceImpl implements HoboShopService {

    @Autowired
    HoboShopRepository hoboShopRepository;

    @Override
    public boolean saveShopInfo(List<String> image, String token) {
        boolean b = false;
        try {
            HoboShopAttribute imageData = new HoboShopAttribute();
          /*  List<byte[]> arrays = new ArrayList<>();
            for(String imagedata : image){
                byte[] imageByte = Base64.decodeBase64(imagedata.getBytes("UTF-8"));
                arrays.add(imageByte);
            }*/

            imageData.setFiledata(image);
            imageData.setShopUniqueId(token);

            hoboShopRepository.saveShopInfo(imageData);

            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    @Override
    public HoboShopAttribute getShopInfo(String token) {
        HoboShopAttribute hoboShopAttribute = new HoboShopAttribute();

        try {
            hoboShopAttribute = hoboShopRepository.getShopInfo(token);
        } catch (Exception e) {

        }
        return hoboShopAttribute;
    }

    @Override
    public boolean editShopNameorNumber(String token, String shopname, String shopnumber) {
        boolean b = false;
        try {
            b = hoboShopRepository.editShopNameorNumber(token, shopname, shopnumber);
        } catch (Exception e) {
        }
        return b;
    }

    @Override
    public String login(String username, String password) {
        return hoboShopRepository.login(username, password);
    }

    @Override
    public boolean registerShop(HoboShopRegister hoboShopRegister) {
        boolean b = false;
        try {
            String unique = DataHideUtils.getUniqueIdEncrypt(hoboShopRegister.getShopContactNo());
            hoboShopRegister.setShopUniqueId(unique);
            hoboShopRepository.registerShop(hoboShopRegister);
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }
}
