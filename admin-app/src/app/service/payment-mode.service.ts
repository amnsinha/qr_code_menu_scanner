import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';
import {ChefDetails} from '../model/staff/ChefDetails';
import {PaymentFiles} from '../model/customer-app-settings/PaymentFiles';


const BASE = '/api/hoboai';
const ADMIN = '/admin';
const UPLOAD_PAYMENT_DETAILS = BASE + ADMIN + '/upload-payment-mode';
const GET_PAYMENT_DETAILS = BASE + ADMIN + '/get-payment-details';
const REMOVE_PAYMENT_DETAILS = BASE + ADMIN + '/delete-payment-details';

@Injectable()
export class PaymentModeService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public uploadPayment(paymentFiles: any): Observable<any> {
    const url: string = this.getServiceUrl(UPLOAD_PAYMENT_DETAILS);
    return this.httpClient.post<any>(url, paymentFiles);
  }

  public getPaymentFiles(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_PAYMENT_DETAILS)
      .concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }

  public removePayment(hotelId: string, fileId: string): Observable<any> {
    const url: string = this.getServiceUrl(REMOVE_PAYMENT_DETAILS)
      .concat('/').concat(hotelId)
      .concat('/').concat(fileId);
    return this.httpClient.get<boolean>(url);
  }
}
