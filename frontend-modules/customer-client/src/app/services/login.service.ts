import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class LoginService {
  constructor(router: Router) {
  }


  checkCustomerValidation(): boolean {
    return true;
  }
}
