import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

}
declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  {path: '/orders', title: 'Orders', icon: 'fa-th-large', class: ''},
  // { path: '/icons', title: 'Icons',  icon:'ni-planet', class: '' },
  {path: '/order-history', title: 'Order History', icon: 'fa-map', class: ''}

  /*    { path: '/login', title: 'Login',  icon:'ni-key-25', class: '' },
      { path: '/register', title: 'Register',  icon:'ni-circle-08', class: '' }*/
];
