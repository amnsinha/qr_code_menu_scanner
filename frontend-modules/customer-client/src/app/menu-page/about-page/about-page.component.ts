import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {HeaderSettings} from '../../model/common-settings/HeaderSettings';
import {PageSettings} from '../../model/common-settings/PageSettings';
import {CustomerApplicationSettingsService} from '../../services/customer-application-settings.service';

declare let Razorpay: any;

@Component({
  selector: 'app-about-page',
  templateUrl: './about-page.component.html',
  styleUrls: ['./about-page.component.scss']
})
export class AboutPageComponent implements OnInit, OnDestroy {
  public authToken;
  public interval;
  private subscriptions: Array<Subscription> = [];


  public headerSettings: HeaderSettings;
  public pageSettings: PageSettings;


  constructor(
    protected router: Router,
    protected restaurantInfoService: CustomerApplicationSettingsService,
  ) {
    this.authToken = localStorage.getItem('restaurantToken');
  }


  ngOnDestroy(): void {
    clearInterval(this.interval);
  }

  navigateToOrderPage() {
    clearInterval(this.interval);
    this.router.navigateByUrl('menu/start/' + localStorage.getItem('restaurantToken'));
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

  ngOnInit() {
    this.getAllContactSettings();
  }
}
