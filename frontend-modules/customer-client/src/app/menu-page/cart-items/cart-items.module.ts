import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CartItemsComponent} from './cart-items.component';
import {CartItemSandbox} from './cart-item.sandbox';
import {FormsModule} from '@angular/forms';
import {ConfirmationService, ConfirmDialogModule,DialogModule} from 'primeng';


@NgModule({
  declarations: [CartItemsComponent],
  exports: [CartItemsComponent],
  imports: [
    CommonModule,
    DialogModule,FormsModule,
    ConfirmDialogModule,
    FormsModule
  ],
  providers: [
    CartItemSandbox, ConfirmationService
  ]
})
export class CartItemsModule {
}
