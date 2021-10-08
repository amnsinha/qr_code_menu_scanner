import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';
import {ChefDetails} from '../model/staff/ChefDetails';
import {HotelSettingsCustomerApplication} from '../model/customer-app-settings/HotelSettingsCustomerApplication';


const BASE = '/api/hoboai';
const ADMIN = '/admin';
const SAVE_UPDATE = BASE + ADMIN + '/save-update-hotel-settings';
const GET_SETTINGS = BASE + ADMIN + '/get-customer-site-settings';

@Injectable()
export class CustomerApplicationSettingsService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public saveSettings(hotelId: string, settings: HotelSettingsCustomerApplication): Observable<any> {
    const url: string = this.getServiceUrl(SAVE_UPDATE).concat('/').concat(hotelId);
    return this.httpClient.post<HotelSettingsCustomerApplication>(url, settings);
  }

  public getAllSettings(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_SETTINGS).concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }

}
