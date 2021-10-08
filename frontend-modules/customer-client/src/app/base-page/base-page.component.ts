import {Component, OnInit} from '@angular/core';
import {CustomerApplicationSettingsService} from '../services/customer-application-settings.service';
import {HotelSettingsCustomerApplication} from '../model/common-settings/HotelSettingsCustomerApplication';
import {HeaderSettings} from '../model/common-settings/HeaderSettings';
import {PageSettings} from '../model/common-settings/PageSettings';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {MenuSandbox} from '../menu-page/menu.sandbox';

@Component({
  selector: 'app-base-page',
  templateUrl: './base-page.component.html',
  styleUrls: ['./base-page.component.scss']
})
export class BasePageComponent implements OnInit {
  public hotelSettingsCustomerApplication: HotelSettingsCustomerApplication;
  public headerSettings: HeaderSettings;
  public pageSettings: PageSettings;
  public authToken: string;
  private subscriptions: Array<Subscription> = [];
  private routeSubscription: Subscription;

  constructor(private service: CustomerApplicationSettingsService,
              private menuSandbox: MenuSandbox,
              private route: ActivatedRoute) {
    this.hotelSettingsCustomerApplication = new HotelSettingsCustomerApplication();
    this.headerSettings = new HeaderSettings();
    this.pageSettings = new PageSettings();
  }

  getAllSettings(hotelId: string) {
    this.service.getAllSettings(hotelId).subscribe(data => {
      if (data) {
        this.headerSettings = data.headerSettings;
        this.pageSettings = data.pageSettings;
      }

    });
  }

  ngOnInit(): void {
    this.subscribeToActivatedRoute();
  }

  private subscribeToActivatedRoute() {
    this.routeSubscription = this.route.params.subscribe(value => {
      const token = value['token'];
      if (token) {
        const raw = atob(token);
        const wtoken = raw.split(':');
        console.log(wtoken);
        localStorage.setItem('hotelId', wtoken[0]);
        localStorage.setItem('tableId', wtoken[1]);
        localStorage.setItem('restaurantToken', token);
        this.authToken = wtoken[0];
        this.getAllSettings(this.authToken);
      }
    });
  }

}
