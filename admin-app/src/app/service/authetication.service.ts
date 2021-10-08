import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../model/user/User';
import {BaseService} from './base-service';

const BASE = '/api/hoboai';
const ADMIN = '/admin';
const LOGIN = BASE + ADMIN + '/login';

@Injectable({providedIn: 'root'})
export class AuthenticationService extends BaseService {
  public currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient) {
    super();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }


  public login(username: string, password: string): Observable<any> {
    const params = new HttpParams().set('username', username).set('password', password);
    const url: string = this.getServiceUrl(LOGIN);
    return this.http.get<any>(url, {headers: this.getHttpHeaders(), params});
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
