import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';


const BASE = '/api/hoboai';
const CHEF = '/chef';
const GET_ALL_ORDERS = BASE + CHEF + '/get-all-orders';
const GET_COMPLETED_ORDERS = BASE + CHEF + '/get-completed-orders';
const GET_PROCESSED_ORDERS = BASE + CHEF + '/get-processed-orders';
const SET_ORDER_ITEM_STATUS = BASE + CHEF + '/change-orders-item-status';
const SET_ORDER_STATUS = BASE + CHEF + '/change-orders-status';

@Injectable()
export class OrderService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public getCustomerOrders(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_ORDERS)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public getProcessedOrders(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_PROCESSED_ORDERS)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public setOrderItemStatus(orderId, itemUniqueId, hotelId, changedStatus): Observable<any> {
    const params = new HttpParams()
      .set('orderId', orderId)
      .set('itemId', itemUniqueId)
      .set('hotelId', hotelId)
      .set('status', changedStatus);
    const url: string = this.getServiceUrl(SET_ORDER_ITEM_STATUS);
    return this.httpClient.get<any>(url, {headers: this.getHttpHeaders(), params});
  }


  public getCompletedOrders(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_COMPLETED_ORDERS)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }

  public setOrderStatus(token: string): Observable<any> {
    const url: string = this.getServiceUrl(SET_ORDER_STATUS)
      .concat('/').concat(token);
    return this.httpClient.get<any>(url);
  }


}
