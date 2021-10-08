import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {BaseService} from './base-service';

const BASE = '/api/hoboai';
const ADMIN = '/chef';
const LOGIN = BASE + ADMIN + '/login';

@Injectable()
export class AuthenticationService extends BaseService {
  constructor(private http: HttpClient) {
    super();
  }


  public login(username: string, password: string): Observable<any> {
    const params = new HttpParams().set('username', username).set('password', password);
    const url: string = this.getServiceUrl(LOGIN);
    return this.http.get<any>(url, {headers: this.getHttpHeaders(), params});
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
