import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';


const BASE = '/api/hoboai';
const CUSTOMER = '/customer';
const ADMIN = '/admin';
const CUSTOMER_VALIDATE = BASE + CUSTOMER + '/validate';
const RESTAURANT_MENU = BASE + CUSTOMER + '/get-restaurant-menu';
const RESTAURANT_MENU_BY_OUTLET_ID = BASE + CUSTOMER + '/get-restaurant-menu-by-outlet-id';
const GET_ALL_OUTLET = BASE + ADMIN + '/get-hotel-outlets';


@Injectable()
export class MenuService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public validateCustomer(token: string): Observable<any> {
    const url: string = this.getServiceUrl(CUSTOMER_VALIDATE)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public getRestaurantMenu(token: string): Observable<any> {
    const url: string = this.getServiceUrl(RESTAURANT_MENU)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public getRestaurantMenuByOutletId(token: string, outletId: string): Observable<any> {
    const url: string = this.getServiceUrl(RESTAURANT_MENU_BY_OUTLET_ID)
      .concat('/').concat(token)
      .concat('/').concat(outletId);
    return this.httpClient.get<any>(url);
  }

  getRestaurantOutLet(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_OUTLET)
      .concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }

}
