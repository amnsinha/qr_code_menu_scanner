import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from './header/header.component';
import {FormsModule} from '@angular/forms';
import {DropdownModule, OverlayPanelModule, TableModule} from 'primeng';


@NgModule({
  declarations: [HeaderComponent],
  exports: [HeaderComponent],
  imports: [
    CommonModule,
    TableModule,
    OverlayPanelModule,
    DropdownModule,
    FormsModule
  ]
})
export class SharedModule {
}
