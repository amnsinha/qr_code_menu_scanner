import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable, Subject} from 'rxjs';
import {LoginService} from './login.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  public isloggedIn: Subject<boolean> = new Subject();

  constructor(private loginService: LoginService, private router: Router) {
  }

  public isloggedIn$(): Observable<boolean> {
    return this.isloggedIn.asObservable();
  }

  public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.loginService.checkCustomerValidation()) {
      this.isloggedIn.next(true);
      return true;
    }
    this.isloggedIn.next(false);
    this.router.navigate(['/login']);
    return false;
  }
}
