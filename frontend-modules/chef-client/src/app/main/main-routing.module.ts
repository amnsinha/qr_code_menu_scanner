import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonComponent} from './common/common.component';
import {TablesOverviewComponent} from '../pages/tables-overview/tables-overview.component';
import {OrdersComponent} from '../pages/orders/orders.component';
import {OrdersHistorySandbox} from '../pages/orders-history/orders-history.sandbox';
import {OrdersHistoryComponent} from '../pages/orders-history/orders-history.component';


const routes: Routes = [
  {
    path: '',
    component: CommonComponent,
    children: [
      {path: 'table-overview', pathMatch: 'full', component: TablesOverviewComponent},
      {path: 'orders', pathMatch: 'full', component: OrdersComponent},
      {path: 'order-history', pathMatch: 'full', component: OrdersHistoryComponent}
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule {
}
