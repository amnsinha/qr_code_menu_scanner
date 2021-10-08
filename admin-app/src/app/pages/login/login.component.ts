import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../service/authetication.service';
import {AlertService} from '../../service/alert.service';
import {Store} from '@ngrx/store';
import * as fromRoot from '../../store/reducers';
import * as authenticationAction from '../../store/actions/authentication';
import {ToastMessageService} from '../../service/toast-message.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  responseMessage: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private store: Store<fromRoot.State>,
              private toastService: ToastMessageService,
              private authenticationService: AuthenticationService,
              private alertService: AlertService) {
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

  }

  onSubmit() {
    console.log('asd');
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }
    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value).subscribe(
      data => {
        if (data.payload) {
          localStorage.setItem('currentUser', data.payload.hotelUniqueId);
          this.store.dispatch(new authenticationAction.SetAuthentication(data.payload.hotelUniqueId));
          this.router.navigate(['/dashboard']);
        } else {
          this.toastService.showMessage('Please Check Username/Password', 'error');
        }
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  ngOnDestroy(): void {
  }

}
