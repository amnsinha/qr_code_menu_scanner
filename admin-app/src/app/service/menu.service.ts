import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';
import {ChefDetails} from '../model/staff/ChefDetails';
import {ChefSettings} from '../model/staff/ChefSettings';
import {RoleDetails} from '../model/staff/RoleDetails';
import {OutletDetails} from '../model/menu/OutletDetails';


const BASE = '/api/hoboai';
const ADMIN = '/admin';
const ADD_OUTLET = BASE + ADMIN + '/add-outlet';
const GET_ALL_OUTLET = BASE + ADMIN + '/get-hotel-outlets';

@Injectable()
export class MenuService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public addOutlet(outletDetails: OutletDetails): Observable<any> {
    const url: string = this.getServiceUrl(ADD_OUTLET);
    return this.httpClient.post<ChefDetails>(url, outletDetails, this.getPostRequestOptions('application/json'));
  }

  getAllOutlet(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_OUTLET)
      .concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }
}
