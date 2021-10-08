import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MenuPageComponent} from './menu-page.component';
import {BasePageComponent} from '../base-page/base-page.component';
import {ProcessingOrderComponent} from './processing-order/processing-order.component';
import {AuthGuardService} from '../services/auth-guard.service';
import {AboutPageComponent} from './about-page/about-page.component';
import {ContactPageComponent} from './contact-page/contact-page.component';
import {ServicePageComponent} from './service-page/service-page.component';

const routes: Routes = [
  {
    path: '',
    component: BasePageComponent,
    children: [
      {path: '', component: MenuPageComponent, canActivate: [AuthGuardService]},
      {path: 'processing', component: ProcessingOrderComponent},
      {path: 'about', component: AboutPageComponent},
      {path: 'contact', component: ContactPageComponent},
      {path: 'service', component: ServicePageComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenuPageRoutingModule {
}
