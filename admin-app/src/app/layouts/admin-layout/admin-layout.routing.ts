import {Routes} from '@angular/router';

import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {IconsComponent} from '../../pages/icons/icons.component';
import {MapsComponent} from '../../pages/maps/maps.component';
import {TablesComponent} from '../../pages/tables/tables.component';
import {CustomerSiteComponent} from '../../pages/customer-site/customer-site.component';
import {AccountsComponent} from '../../pages/accounts/accounts.component';
import {StaffPanelComponent} from '../../pages/staff-panel/staff-panel.component';
import {BuisnessAnalyticsComponent} from '../../pages/buisness-analytics/buisness-analytics.component';
import {PaymentModesComponent} from '../../pages/payment-modes/payment-modes.component';
import {MenuPanelComponent} from '../../pages/menu-panel/menu-panel.component';
import {DiscountsComponent} from '../../pages/discounts/discounts.component';

export const AdminLayoutRoutes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'business-analytics', component: BuisnessAnalyticsComponent},
  {path: 'customer-site', component: CustomerSiteComponent},
  {path: 'tables', component: TablesComponent},
  {path: 'icons', component: IconsComponent},
  {path: 'staff-panel', component: StaffPanelComponent},
  {path: 'menu-panel', component: MenuPanelComponent},
  {path: 'discount', component: DiscountsComponent},
  {path: 'maps', component: MapsComponent},
  {path: 'accounts', component: AccountsComponent},
  {path: 'payment-modes', component: PaymentModesComponent}
];
