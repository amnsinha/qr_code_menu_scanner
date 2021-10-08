import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';
import {HotelTables} from '../model/customer-app-settings/HotelTables';
import {HotelDiscounts} from '../model/customer-app-settings/HotelDiscounts';


const BASE = '/api/hoboai';
const ADMIN = '/admin';
const ADD_DISCOUNT = BASE + ADMIN + '/add-discounts';
const ADD_TABLE = BASE + ADMIN + '/add-table';
const GET_ALL_HOTEL_DISCOUNTS = BASE + ADMIN + '/get-all-discounts';
const GET_ALL_TABLE = BASE + ADMIN + '/get-all-tables';
const REMOVE_TABLE = BASE + ADMIN + '/remove-table';
const REMOVE_DISCOUNT = BASE + ADMIN + '/remove-discounts';

@Injectable()
export class HotelService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public addTable(hotelTables: HotelTables): Observable<any> {
    const url: string = this.getServiceUrl(ADD_TABLE);
    return this.httpClient.post<HotelTables>(url, hotelTables, this.getPostRequestOptions('application/json'));
  }

  public getAllTables(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_TABLE)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public removeTable(token: string): Observable<any> {
    const url: string = this.getServiceUrl(REMOVE_TABLE)
      .concat('/').concat(token);
    return this.httpClient.get<boolean>(url);
  }

  public getAllDiscounts(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_HOTEL_DISCOUNTS)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public addDiscount(hotelDiscounts: HotelDiscounts): Observable<any> {
    const url: string = this.getServiceUrl(ADD_DISCOUNT);
    return this.httpClient.post<HotelTables>(url, hotelDiscounts,
      this.getPostRequestOptions('application/json'));
  }


  public removeDiscount(token: string): Observable<any> {
    const url: string = this.getServiceUrl(REMOVE_DISCOUNT)
      .concat('/').concat(token);
    return this.httpClient.get<boolean>(url);
  }
}
