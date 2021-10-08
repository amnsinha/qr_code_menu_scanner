import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {OrderService} from '../../service/order.service';
import {OrderItems} from '../../model/order-items/order-items';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  public total: String;
  public subscriptions: Array<Subscription> = [];
  public currentOrders: Array<OrderItems> = [];

  constructor(private orderService: OrderService) {
  }

  ngOnInit(): void {
    const userc = localStorage.getItem('currentUser');
    this.getCurrentOrders(userc);
  }

  getTotal(orderItems): number {
    let total = 0;
    if (orderItems && orderItems.length > 0) {
      for (let i = 0; i < orderItems.length; i++) {
        if (orderItems[i].itemPrice) {
          total += orderItems[i].itemPrice * orderItems[i].orderQty;
        }
      }
      return total;
    }
  }

  getCurrentOrders(token) {
    this.subscriptions.push(this.orderService.getCustomerOrders(token).subscribe(
      (response: any) => {
        if(response){
          this.currentOrders = response;
        }

      }));
  }
}
