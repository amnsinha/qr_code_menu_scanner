import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  {path: '/dashboard', title: 'Dashboard', icon: 'fa-th-large', class: ''},
  {path: '/business-analytics', title: 'Analytics', icon: 'fas fa-chart-bar', class: ''},
  // { path: '/icons', title: 'Icons',  icon:'ni-planet', class: '' },
  // {path: '/maps', title: 'Maps', icon: 'fa-map', class: ''},
  {path: '/customer-site', title: 'Edit Customer Site', icon: 'fa-users', class: ''},
  {path: '/menu-panel', title: 'Manage Menu', icon: 'fa-bars', class: ''},
  {path: '/tables', title: 'Manage Tables', icon: 'fa-table', class: ''},
  {path: '/staff-panel', title: 'Staff Panel', icon: 'fa-user', class: ''},
  {path: '/discount', title: 'Manage Discount', icon: 'fa-percent', class: ''},
  {path: '/payment-modes', title: 'Manage Payments', icon: 'fa-credit-card', class: ''},
  {path: '/accounts', title: 'Manage Accounts', icon: 'fa-calculator', class: ''},

  /*    { path: '/login', title: 'Login',  icon:'ni-key-25', class: '' },
      { path: '/register', title: 'Register',  icon:'ni-circle-08', class: '' }*/
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      // this.isCollapsed = true;
    });
  }
}
