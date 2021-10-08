package com.hotbot.model.customerapplication;

import com.hotbot.model.AbstractDocument;

import java.util.List;

public class HotelMenu extends AbstractDocument {

   private String hotelId;
   private String outletId;
   private List<Menu> menuList;

   public String getOutletId() {
      return outletId;
   }

   public void setOutletId(String outletId) {
      this.outletId = outletId;
   }

   public String getHotelId() {
      return hotelId;
   }

   public void setHotelId(String hotelId) {
      this.hotelId = hotelId;
   }

   public List<Menu> getMenuList() {
      return menuList;
   }

   public void setMenuList(List<Menu> menuList) {
      this.menuList = menuList;
   }
}
