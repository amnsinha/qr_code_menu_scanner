import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class LoginService {
  constructor(router: Router) {
  }


  checkCustomerValidation(): boolean {
    if (localStorage.getItem('currentUser')) {
      // logged in so return true
      return true;
    }
  }
}
