import {Component, OnInit} from '@angular/core';
import {HotelTables} from '../../model/customer-app-settings/HotelTables';
import {HotelService} from '../../service/hotel.service';

import {Store} from '@ngrx/store';
import * as fromRoot from '../../store/reducers';
import {Subscription} from 'rxjs';
import {DiscountsSandbox} from './discounts.sandbox';
import {environment} from '../../../environments/environment';
import {ToastMessageService} from '../../service/toast-message.service';
import {HotelDiscounts} from '../../model/customer-app-settings/HotelDiscounts';

@Component({
  selector: 'app-discounts',
  templateUrl: './discounts.component.html',
  styleUrls: ['./discounts.component.scss']
})
export class DiscountsComponent implements OnInit {
  public hotelDiscounts: HotelDiscounts[];
  public showaddtable = false;
  public authtoken: string;
  public elementType: 'url' | 'canvas' | 'img' = 'url';
  public value = 'facebook.com';
  kk;
  private subscriptions: Array<Subscription> = [];

  constructor(private hotelService: HotelService,
              private message: ToastMessageService,
              protected store: Store<fromRoot.State>,
              protected sandbox: DiscountsSandbox) {
  }

  showAddTable() {
    this.showaddtable = true;
  }

  addDiscount(discountPercent, desc, discountCoden) {
    if (!discountPercent) {
      this.message.showMessage('Please add discount', 'warn');
      return;
    }

    if ( !discountCoden) {
      this.message.showMessage('Please add discount code', 'warn');
      return;
    }
    const hotelDiscount = new HotelDiscounts();
    hotelDiscount.discount = discountPercent;
    hotelDiscount.discountCode = discountCoden;
    hotelDiscount.description = desc;
    hotelDiscount.hotelUniqueId = this.authtoken;

    this.hotelService.addDiscount(hotelDiscount).subscribe((response: any) => {
      if (response.payload) {
        this.hotelDiscounts = response;
        this.getAllDiscounts(this.authtoken);
        this.message.showMessage('Successfully Added Table', 'success');
      } else {
        this.message.showMessage('Some error occurred', 'error');
        return;
      }
    });
  }

  removeDiscount(discountId) {
    const authToken = btoa(this.authtoken + ':' + discountId);
    this.hotelService.removeDiscount(authToken).subscribe((response: any) => {
      if (response.payload) {
        this.message.showMessage('Successfully Removed', 'success');
        this.getAllDiscounts(this.authtoken);
      } else {
        this.message.showMessage('Some error occurred', 'error');
        return;
      }
    });
  }

  getAllDiscounts(token) {
    this.hotelService.getAllDiscounts(token).subscribe((response: any) => {
      this.hotelDiscounts = response.payload;
    });
  }

  ngOnInit() {
    this.authtoken = localStorage.getItem('currentUser');
    this.getAllDiscounts(this.authtoken);
    this.showaddtable = false;
  }
}
