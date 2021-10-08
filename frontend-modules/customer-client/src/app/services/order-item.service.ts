import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';
import {OrderDetails} from '../model/order-items/order-details';
import {HotelDiscounts} from '../model/HotelDiscounts';


const BASE = '/api/hoboai';
const CUSTOMER = '/customer';
const CUSTOMER_VALIDATE = BASE + CUSTOMER + '/validate';
const ORDER_TO_CHEF = BASE + CUSTOMER + '/order';
const GET_MY_PLACED_ORDER = BASE + CUSTOMER + '/get-my-placed-orders';
const CHECK_DISCOUNT_VALIDATION = BASE + '/admin' + '/check-discounts';


@Injectable()
export class OrderItemService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public orderItem(orderItems: OrderDetails, token: string, selectedOutletId: string): Observable<any> {
    const url: string = this.getServiceUrl(ORDER_TO_CHEF)
      .concat('/').concat(token)
      .concat('/').concat(selectedOutletId);
    return this.httpClient.post<OrderDetails>(url, orderItems);
  }

  public getMyPlacedOrder(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_MY_PLACED_ORDER)
      .concat('/').concat(token);
    return this.httpClient.get(url);
  }

  public checkDiscount(hotelDiscounts: HotelDiscounts): Observable<any> {
    const url: string = this.getServiceUrl(CHECK_DISCOUNT_VALIDATION)
    return this.httpClient.post<HotelDiscounts>(url, hotelDiscounts);
  }


}
