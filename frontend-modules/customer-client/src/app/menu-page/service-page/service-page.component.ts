import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';

declare let Razorpay: any;

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.scss']
})
export class ServicePageComponent implements OnInit, OnDestroy {

  private subscriptions: Array<Subscription> = [];

  constructor(
    protected router: Router,
  ) {
    // this.authToken = localStorage.getItem('restaurantToken');
  }

  ngOnDestroy(): void {

  }


  ngOnInit() {
  }

}
