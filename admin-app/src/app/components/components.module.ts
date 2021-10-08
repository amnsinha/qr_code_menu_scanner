import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {HeaderComponent} from './header/header.component';
import {OverlayPanelModule} from 'primeng';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NgbModule,
    OverlayPanelModule
  ],
  declarations: [
    FooterComponent,
    HeaderComponent,
    SidebarComponent
  ],
  exports: [
    FooterComponent,
    SidebarComponent,
    HeaderComponent
  ]
})
export class ComponentsModule { }
