import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';


const BASE = '/api/hoboai';
const ADMIN = '/admin';
const GET_QR_TO_PAYMENT_IMAGE = BASE + ADMIN + '/get-payment-details';


@Injectable()
export class PaymentService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public getPaymentQRCode(token: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_QR_TO_PAYMENT_IMAGE)
      .concat('/').concat(token);
    return this.httpClient.get(url);
  }


}
