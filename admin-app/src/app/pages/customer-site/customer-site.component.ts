import {Component, OnInit} from '@angular/core';
import {HotelSettingsCustomerApplication} from '../../model/customer-app-settings/HotelSettingsCustomerApplication';
import {HeaderSettings} from '../../model/customer-app-settings/HeaderSettings';
import {PageSettings} from '../../model/customer-app-settings/PageSettings';
import {CustomerApplicationSettingsService} from '../../service/customer-application-settings.service';
import {ToastMessageService} from '../../service/toast-message.service';

@Component({
  selector: 'app-customer-site',
  templateUrl: './customer-site.component.html',
  styleUrls: ['./customer-site.component.css']
})
export class CustomerSiteComponent implements OnInit {

  public hotelSettingsCustomerApplication: HotelSettingsCustomerApplication;
  public headerSettings: HeaderSettings;
  public pageSettings: PageSettings;
  public color: string;

  constructor(private service: CustomerApplicationSettingsService,
              private message: ToastMessageService) {
    this.hotelSettingsCustomerApplication = new HotelSettingsCustomerApplication();
    this.headerSettings = new HeaderSettings();
    this.pageSettings = new PageSettings();
  }

  saveHeaderSettings() {
    this.hotelSettingsCustomerApplication.hotelId = localStorage.getItem('currentUser');
    this.hotelSettingsCustomerApplication.headerSettings = this.headerSettings;
    this.service.saveSettings(this.hotelSettingsCustomerApplication.hotelId, this.hotelSettingsCustomerApplication).subscribe(data => {
      if (data) {
        this.message.showMessage('Succesfully Saved', 'success');
      } else {
        this.message.showMessage('Some erroe occurred Saved', 'error');
      }
    });
  }

  onUpload(file) {
    const fileReader = new FileReader();
    let filebase64 = null;
    fileReader.readAsDataURL(file[0]);
    fileReader.onload = function () {
      filebase64 = fileReader.result;
    };
    setTimeout(() => {
      this.headerSettings.headerIcon = filebase64;
    }, 500);
  }


  savePageSettings() {
    this.hotelSettingsCustomerApplication.hotelId = localStorage.getItem('currentUser');
    this.hotelSettingsCustomerApplication.pageSettings = this.pageSettings;
    this.service.saveSettings(this.hotelSettingsCustomerApplication.hotelId, this.hotelSettingsCustomerApplication).subscribe(data => {
      if (data) {
        this.message.showMessage('Succesfully Saved', 'success');
      } else {
        this.message.showMessage('Some erroe occurred Saved', 'error');
      }
    });
  }

  getAllSettings() {
    const hotelId = localStorage.getItem('currentUser');
    this.service.getAllSettings(hotelId).subscribe(data => {
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

  ngOnInit(): void {
    this.getAllSettings();
  }

}
