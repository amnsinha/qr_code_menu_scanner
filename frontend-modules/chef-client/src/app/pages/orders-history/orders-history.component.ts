import {Component, OnInit} from '@angular/core';
import {ConfirmationService} from 'primeng';
import {OrderService} from '../../service/order.service';
import {OrderStatusConstants} from '../../shared/constants/order-status.constants';
import {Subscription} from 'rxjs';
import {OrdersHistorySandbox} from './orders-history.sandbox';
import {OrderItems} from '../../model/order-items/order-items';

@Component({
  selector: 'app-orders-history',
  templateUrl: './orders-history.component.html',
  styleUrls: ['./orders-history.component.scss']
})
export class OrdersHistoryComponent implements OnInit {

  private subscriptions: Array<Subscription> = [];
  private authToken: string;
  public completedOrders: OrderItems[];

  constructor(private confirmationService: ConfirmationService,
              protected sandbox: OrdersHistorySandbox,
              private orderService: OrderService) {
  }

  completeAll(data) {
    console.log(data);
  }

  deleteAll(data) {
    console.log(data);
  }

  ngOnInit() {
    this.subscriptions.push(this.sandbox.authState$.subscribe(
      (response: any) => {
        console.log(response);
        this.authToken = response.authentication;
        this.getCustomerOrders(this.authToken);
      }));
  }

  getCustomerOrders(token) {
    this.subscriptions.push(this.orderService.getCompletedOrders(token).subscribe(
      (response: any) => {
        this.completedOrders = response;
      }));
  }

}
