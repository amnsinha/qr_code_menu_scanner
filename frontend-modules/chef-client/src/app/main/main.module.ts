import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CommonComponent} from './common/common.component';
import {MainRoutingModule} from './main-routing.module';
import {BaseModule} from '../base-component/base.module';
import {PageModule} from '../pages/page.module';


@NgModule({
  declarations: [CommonComponent],
  imports: [
    MainRoutingModule,
    CommonModule,
    PageModule,
    BaseModule
  ]
})
export class MainModule {
}
