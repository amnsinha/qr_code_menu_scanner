import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {HotelSettingsCustomerApplication} from '../../model/common-settings/HotelSettingsCustomerApplication';
import {HeaderSettings} from '../../model/common-settings/HeaderSettings';
import {PageSettings} from '../../model/common-settings/PageSettings';
import {CustomerApplicationSettingsService} from '../../services/customer-application-settings.service';

@Component({
  selector: 'app-contact-page',
  templateUrl: './contact-page.component.html',
  styleUrls: ['./contact-page.component.scss']
})
export class ContactPageComponent implements OnInit, OnDestroy {
  private subscriptions: Array<Subscription> = [];
  public hotelSettingsCustomerApplication: HotelSettingsCustomerApplication;
  public headerSettings: HeaderSettings;
  public pageSettings: PageSettings;


  constructor(
    protected router: Router,
    protected restaurantInfoService: CustomerApplicationSettingsService,
  ) {
    // this.authToken = localStorage.getItem('restaurantToken');
  }

  ngOnInit() {
    this.getAllContactSettings();
  }

  ngOnDestroy(): void {
  }

  getAllContactSettings() {
    const hotelId = localStorage.getItem('hotelId');
    this.restaurantInfoService.getAllSettings(hotelId).subscribe(data => {
      console.log(data)
      if (data) {
        if (data.headerSettings) {
          this.headerSettings = data.headerSettings;
        }
        if (data.pageSettings) {
          this.pageSettings = data.pageSettings;
        }
      }
    });
  }
}
