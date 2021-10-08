import {BrowserModule, HAMMER_GESTURE_CONFIG, HammerGestureConfig} from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {LoginComponent} from './independent-component/login/login.component';
import {RegisterComponent} from './independent-component/register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import * as Hammer from 'hammerjs';
import {SharedModule} from './shared/shared.module';
import {AppRoutingModule} from './app-routing.module';
import {StoreModule} from '@ngrx/store';
import {clearStoreReducer, reducers} from './store/reducers/index';
import {OrderItemService} from './services/order-item.service';
import {AuthGuardService} from './services/auth-guard.service';
import {LoginService} from './services/login.service';
import {ToastMessageService} from './services/toast-message.service';
import {PaymentService} from './services/payment.service';
import {MessageService, ToastModule} from 'primeng';

@Injectable()
export class MyHammerConfig extends HammerGestureConfig {
  overrides = <any> {
    swipe: {direction: Hammer.DIRECTION_ALL},
  };
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    StoreModule.forRoot(reducers, {metaReducers: [clearStoreReducer]}),
    BrowserAnimationsModule,
    AppRoutingModule,
    SharedModule
  ],
  providers: [OrderItemService, PaymentService, AuthGuardService, LoginService,
    {
      provide: HAMMER_GESTURE_CONFIG,
      useClass: MyHammerConfig,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
