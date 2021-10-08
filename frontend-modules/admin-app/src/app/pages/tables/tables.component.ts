import {Component, OnInit} from '@angular/core';
import {HotelTables} from '../../model/customer-app-settings/HotelTables';
import {HotelService} from '../../service/hotel.service';

import {Store} from '@ngrx/store';
import * as fromRoot from '../../store/reducers';
import {Subscription} from 'rxjs';
import {TablesSandbox} from './tables.sandbox';
import {environment} from '../../../environments/environment';
import {ToastMessageService} from '../../service/toast-message.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss']
})
export class TablesComponent implements OnInit {
  public hotelTables: HotelTables[];
  public showaddtable = false;
  public authtoken: string;
  public elementType: 'url' | 'canvas' | 'img' = 'url';
  public value = 'facebook.com';
  kk;
  private subscriptions: Array<Subscription> = [];

  constructor(private hotelService: HotelService,
              private message: ToastMessageService,
              protected store: Store<fromRoot.State>,
              protected sandbox: TablesSandbox) {
  }

  ngOnInit() {
      this.authtoken = localStorage.getItem('currentUser');
      this.getAllTables(this.authtoken);
      this.showaddtable = false;
    }

  showAddTable() {
    this.showaddtable = true;
  }

  addTable(tableno) {
    if (!tableno) {
      this.message.showMessage('Please add table no', 'warn');
      return;
    }
    const hotel = new HotelTables();
    hotel.hotelTableNo = tableno;
    hotel.hotelUniqueId = this.authtoken;
    hotel.hotelQRCodeValue = environment.qrScanUrl + btoa(this.authtoken + ':' + tableno);
    this.hotelService.addTable(hotel).subscribe((response: any) => {
      if (response.payload) {
        this.hotelTables = response;
        this.getAllTables(this.authtoken);
        this.message.showMessage('Successfully Added Discount', 'success');
      } else {
        this.message.showMessage('Some error occurred', 'error');
        return;
      }
    });
  }

  removeTable(tableno) {
    const authToken = btoa(this.authtoken + ':' + tableno);
    this.hotelService.removeTable(authToken).subscribe((response: any) => {
      if (response.payload) {
        this.message.showMessage('Successfully Removed', 'success');
        this.getAllTables(this.authtoken);
      } else {
        this.message.showMessage('Some error occurred', 'error');
        return;
      }
    });
  }

  getAllTables(token) {
    this.hotelService.getAllTables(token).subscribe((response: any) => {
      this.hotelTables = response.payload;
    });
  }



}
