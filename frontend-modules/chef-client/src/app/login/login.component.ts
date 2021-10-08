import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from '../service/alert.service';
import {AuthenticationService} from '../service/authentication.service';
import {Store} from '@ngrx/store';
import * as fromRoot from '../store/reducers';
import * as authenticationAction from '../store/actions/authentication';
import {ToastMessageService} from '../service/toast-message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  returnUrl: string;

  constructor(
    private message: ToastMessageService,
    private route: ActivatedRoute,
    private router: Router,
    private store: Store<fromRoot.State>,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
  ) {
  }

  ngOnInit() {
    // reset login status
    this.authenticationService.logout();
  }

  login() {
    console.log("kya be");
    this.loading = true;
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        data => {
          console.log(data);
          if (data.payload) {
            console.log(data.payload);
            localStorage.setItem('currentUser', data.payload.hotelUniqueId);
            localStorage.setItem('outletId', data.payload.outletHolds.outletUniqueId);
            this.store.dispatch(new authenticationAction.SetAuthentication(data.payload.hotelUniqueId));
            this.router.navigate(['/orders']);
          } else {
            this.loading = false;
            this.message.showMessage('Check username/password', 'warn');
          }
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

}

