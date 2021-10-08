import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {AdminLayoutComponent} from './layouts/admin-layout/admin-layout.component';
import {AuthLayoutComponent} from './layouts/auth-layout/auth-layout.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppRoutingModule} from './app.routing';
import {ComponentsModule} from './components/components.module';
import {StoreModule} from '@ngrx/store';
import {clearStoreReducer, reducers} from './store/reducers/index';
import {LoginService} from './service/login.service';
import {AuthGuardService} from './service/auth-guard.service';
import {ToastMessageService} from './service/toast-message.service';
import {MessageService, ToastModule} from 'primeng';

@NgModule({
  imports: [

    StoreModule.forRoot(reducers, {metaReducers: [clearStoreReducer]}),
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule,
    ComponentsModule,
    NgbModule,

    AppRoutingModule,
    ToastModule,
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent
  ],
  providers: [AuthGuardService, LoginService, ToastMessageService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
