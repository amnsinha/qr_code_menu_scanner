import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {ClipboardModule} from 'ngx-clipboard';

import {AdminLayoutRoutes} from './admin-layout.routing';
import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {IconsComponent} from '../../pages/icons/icons.component';
import {MapsComponent} from '../../pages/maps/maps.component';
import {TablesComponent} from '../../pages/tables/tables.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {CustomerSiteComponent} from '../../pages/customer-site/customer-site.component';
import {ColorPickerModule} from 'ngx-color-picker';
import {HotelService} from '../../service/hotel.service';
import {TablesSandbox} from '../../pages/tables/tables.sandbox';
import {AnQrcodeModule} from 'an-qrcode';
import {StaffPanelComponent} from '../../pages/staff-panel/staff-panel.component';
import {MegaMenuModule} from 'primeng/megamenu';
import {
  AccordionModule,
  CardModule,
  DropdownModule,
  EditorModule,
  FileUploadModule,
  InputSwitchModule,
  OverlayPanelModule,
  TabViewModule,
  ToolbarModule
} from 'primeng';
import {AccountsComponent} from '../../pages/accounts/accounts.component';
import {StaffService} from '../../service/staff.service';
import {CustomerApplicationSettingsService} from '../../service/customer-application-settings.service';
import {OrderService} from '../../service/order.service';
import {BuisnessAnalyticsComponent} from '../../pages/buisness-analytics/buisness-analytics.component';
import {PaymentModesComponent} from '../../pages/payment-modes/payment-modes.component';
import {PaymentModeService} from '../../service/payment-mode.service';
import {MenuPanelComponent} from '../../pages/menu-panel/menu-panel.component';
import {MenuService} from '../../service/menu.service';
import {DiscountsSandbox} from '../../pages/discounts/discounts.sandbox';
import {DiscountsComponent} from '../../pages/discounts/discounts.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    ColorPickerModule,
    MegaMenuModule,
    NgbModule,
    AnQrcodeModule,
    FileUploadModule,
    ClipboardModule,
    DropdownModule,
    ToolbarModule,
    AccordionModule,
    EditorModule,
    CardModule,
    InputSwitchModule,
    OverlayPanelModule,
    TabViewModule
  ],
  declarations: [
    DashboardComponent,
    BuisnessAnalyticsComponent,
    StaffPanelComponent,
    CustomerSiteComponent,
    MenuPanelComponent,
    AccountsComponent,
    PaymentModesComponent,
    DiscountsComponent,
    TablesComponent,
    IconsComponent,
    MapsComponent
  ],
  providers: [HotelService, DiscountsSandbox, MenuService,
    OrderService, CustomerApplicationSettingsService,
    TablesSandbox, PaymentModeService, StaffService]
})

export class AdminLayoutModule {
}
