import {Component, Input, OnInit} from '@angular/core';
import {SelectItem} from 'primeng';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @Input('headerData') headerData: any;
  cities1: SelectItem[];

  generaloptions: SelectItem[];
  authToken: string;
  selectedOption: any;

  constructor(protected router: Router,) {
    console.log(this.headerData);
    this.authToken = localStorage.getItem('restaurantToken');
    this.generaloptions = [
      {label: 'Actions', value: null},
      {label: 'Call Waiters', value: {callwaiter: true}}
    ];
  }

  navToService() {
    this.router.navigateByUrl('menu/start/' + this.authToken + '/service');
  }

  navToContact() {
    this.router.navigateByUrl('menu/start/' + this.authToken + '/contact');
  }

  navToAbout() {
    this.router.navigateByUrl('menu/start/' + this.authToken + '/about');
  }

  navToOrders() {
    this.router.navigateByUrl('menu/start/' + this.authToken + '/processing');
  }

  navToHome() {
    this.router.navigateByUrl('menu/start/' + this.authToken);
  }


  ngOnInit() {

  }

}
