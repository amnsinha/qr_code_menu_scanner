import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CheckboxModule, PanelMenuModule, StepsModule, TabMenuModule,DialogModule, ToastModule} from 'primeng';
import {MenuPageComponent} from './menu-page.component';
import {SharedModule} from '../shared/shared.module';
import {MenuPageRoutingModule} from './menu-page-routing.module';
import {ProductsComponent} from './products/products.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {BillingComponent} from './billing/billing.component';
import {dirConfig} from '../directives';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FilterPipe} from '../pipes/filter.pipe';
import {SortPipe} from '../pipes/sort.pipe';
import {ProductsModel} from '../model/products.model';
import {StorageService} from '../services/storage.service';
import {CartService} from '../services/cart.service';
import {CartItemsModule} from './cart-items/cart-items.module';
import {BasePageComponent} from '../base-page/base-page.component';
import {ProcessingOrderComponent} from './processing-order/processing-order.component';
import {AuthGuardService} from '../services/auth-guard.service';
import {LoginService} from '../services/login.service';
import {MenuService} from '../services/menu.service';
import {CustomerApplicationSettingsService} from '../services/customer-application-settings.service';
import {MenuSandbox} from './menu.sandbox';
import {ProcessingOrderSandbox} from './processing-order/processing-order.sandbox';
import {AnQrcodeModule} from 'an-qrcode';
import {ServicePageComponent} from './service-page/service-page.component';
import {AboutPageComponent} from './about-page/about-page.component';
import {ContactPageComponent} from './contact-page/contact-page.component';
import {AboutPageSandbox} from './about-page/about-page.sandbox';
import {ContactPageSandbox} from './contact-page/contact-page.sandbox';
import {ServicePageSandbox} from './service-page/service-page.sandbox';
import {MessageService} from 'primeng/api';
import {ToastMessageService} from '../services/toast-message.service';
import { NgxStarRatingModule } from 'ngx-star-rating';


@NgModule({
  declarations: [MenuPageComponent, dirConfig, ProductsComponent, CheckoutComponent, BillingComponent, FilterPipe,
    SortPipe,
    BasePageComponent,
    ProcessingOrderComponent,
    ServicePageComponent,
    AboutPageComponent,
    ContactPageComponent,
  ],


  exports: [ProductsComponent],
  imports: [
    CommonModule,
    AnQrcodeModule,
    StepsModule,
    PanelMenuModule,
    NgxStarRatingModule,
    CheckboxModule,
    CartItemsModule,
    MenuPageRoutingModule,
    SharedModule,
    FormsModule,
    TabMenuModule,
    ToastModule,
    ReactiveFormsModule
  ],
  providers: [ProductsModel, CustomerApplicationSettingsService, ProcessingOrderSandbox,
    AboutPageSandbox, ContactPageSandbox, ServicePageSandbox,
    MessageService,ToastMessageService, MenuSandbox, MenuService, AuthGuardService, LoginService, StorageService, CartService],
})
export class MenuPageModule {
}
