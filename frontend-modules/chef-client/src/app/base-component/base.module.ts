import {NgModule} from '@angular/core';
import {HeaderComponent} from './header/header.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {RouterModule} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {CommonModule} from '@angular/common';
import {OrdersSandbox} from '../pages/orders/orders.sandbox';

@NgModule({
  declarations: [HeaderComponent, SidebarComponent],
  imports: [RouterModule, CommonModule],
  providers: [OrdersSandbox],

  exports: [
    SidebarComponent,
    HeaderComponent
  ]
})
export class BaseModule {
}
