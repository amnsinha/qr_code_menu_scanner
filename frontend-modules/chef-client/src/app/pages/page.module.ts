import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BaseModule} from '../base-component/base.module';
import {TablesOverviewComponent} from '../pages/tables-overview/tables-overview.component';
import {OrdersComponent} from './orders/orders.component';
import {AccordionModule, ButtonModule, ConfirmationService, ConfirmDialogModule, SplitButtonModule} from 'primeng';
import {OrdersSandbox} from './orders/orders.sandbox';
import {OrdersHistoryComponent} from './orders-history/orders-history.component';
import {OrdersHistorySandbox} from './orders-history/orders-history.sandbox';


@NgModule({
  declarations: [OrdersComponent, OrdersHistoryComponent, TablesOverviewComponent],
  imports: [
    CommonModule,
    AccordionModule,
    ButtonModule,
    ConfirmDialogModule,
    SplitButtonModule,
    BaseModule
  ],
  providers: [ConfirmationService, OrdersHistorySandbox],
})
export class PageModule {
}
