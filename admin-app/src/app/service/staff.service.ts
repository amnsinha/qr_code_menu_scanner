import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {BaseService} from './base-service';
import {ChefDetails} from '../model/staff/ChefDetails';
import {ChefSettings} from '../model/staff/ChefSettings';
import {RoleDetails} from '../model/staff/RoleDetails';


const BASE = '/api/hoboai';
const ADMIN = '/admin';
const ADD_CHEF = BASE + ADMIN + '/add-chef';
const ADD_ROLE = BASE + ADMIN + '/add-roles';
const GET_ALL_CHEF = BASE + ADMIN + '/get-chef-details';
const GET_ALL_ROLES = BASE + ADMIN + '/get-roles';
const GET_ALL_CHEF_SETTINGS = BASE + ADMIN + '/get-chef-settings';
const REMOVE_CHEF = BASE + ADMIN + '/delete-chef-details';
const SAVE_UPDATE_CHEF_SETTINGS = BASE + ADMIN + '/save-update-chef-settings';

@Injectable()
export class StaffService extends BaseService {

  constructor(private httpClient: HttpClient) {
    super();
  }

  public addChef(chefDetails: ChefDetails): Observable<any> {
    const url: string = this.getServiceUrl(ADD_CHEF);
    return this.httpClient.post<ChefDetails>(url, chefDetails, this.getPostRequestOptions('application/json'));
  }

  public saveChefSettings(chefSettings: ChefSettings): Observable<any> {
    const url: string = this.getServiceUrl(SAVE_UPDATE_CHEF_SETTINGS);
    return this.httpClient.post<ChefDetails>(url, chefSettings, this.getPostRequestOptions('application/json'));
  }

  public saveRoles(roleDetails: RoleDetails): Observable<any> {
    const url: string = this.getServiceUrl(ADD_ROLE);
    return this.httpClient.post<ChefDetails>(url, roleDetails, this.getPostRequestOptions('application/json'));
  }

  public getAllRoles(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_ROLES)
      .concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }


  public getAllChef(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_CHEF)
      .concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }

  public getAllChefSettingsDetails(hotelId: string): Observable<any> {
    const url: string = this.getServiceUrl(GET_ALL_CHEF_SETTINGS)
      .concat('/').concat(hotelId);
    return this.httpClient.get<any>(url);
  }

  public removeChef(chefId: string): Observable<any> {
    const url: string = this.getServiceUrl(REMOVE_CHEF)
      .concat('/').concat(chefId);
    return this.httpClient.get<boolean>(url);
  }
}
