import {BrowserModule, HAMMER_GESTURE_CONFIG, HammerGestureConfig} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {AccordionModule} from 'primeng/accordion';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import * as Hammer from 'hammerjs';
import {FullCalendarModule, MessageModule, MessageService, MessagesModule, ToastModule} from 'primeng';
import {AuthenticationService} from './service/authentication.service';
import {AlertService} from './service/alert.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {BaseModule} from './base-component/base.module';
import { OrdersComponent } from './pages/orders/orders.component';
import {OrderService} from './service/order.service';
import {StoreModule} from '@ngrx/store';
import {clearStoreReducer, reducers} from './store/reducers';
import {AuthGuardService} from './service/auth-guard.service';
import {LoginService} from './service/login.service';
import {ToastMessageService} from './service/toast-message.service';
import {HomeComponent} from './home/home.component';
import {RegisterComponent} from './register/register.component';

export class MyHammerConfig extends HammerGestureConfig {
  overrides = <any> {
    swipe: {direction: Hammer.DIRECTION_ALL},
  };
}

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BaseModule,
    DragDropModule,
    AccordionModule, FullCalendarModule, StoreModule.forRoot(reducers, {metaReducers: [clearStoreReducer]}),
    MessageModule, MessagesModule, ToastModule,
    BrowserAnimationsModule
  ],
  providers: [
    MessageService,
    AuthGuardService,
    AlertService,
    OrderService,
    LoginService,
    ToastMessageService,
    AuthenticationService,
    {
      provide: HAMMER_GESTURE_CONFIG,
      useClass: MyHammerConfig,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
