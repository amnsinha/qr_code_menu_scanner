import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MenuItem} from 'primeng';
import {OrderService} from '../../service/order.service';
import {OrderItemStatusConstants, OrderStatusConstants} from '../../shared/constants/order-status.constants';
import {Subscription} from 'rxjs';
import {OrdersSandbox} from './orders.sandbox';
import {OrderItems} from '../../model/order-items/order-items';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  public items: MenuItem[];
  private subscriptions: Array<Subscription> = [];
  private authToken: string;
  public orders: OrderItems[];
  public processedOrder: OrderItems[];

  constructor(private confirmationService: ConfirmationService,
              protected sandbox: OrdersSandbox,
              private orderService: OrderService) {
  }

  completeAll(data) {
    console.log(data);
  }

  deleteAll(data) {
    console.log(data);
  }


  ngOnInit() {

    this.items = [
      {
        label: 'Update', icon: 'pi pi-refresh', command: () => {
          // this.update();
        }
      },
      {
        label: 'Delete', icon: 'pi pi-times', command: () => {
          // this.delete();
        }
      },
      {label: 'Angular.io', icon: 'pi pi-info', url: 'http://angular.io'},
      {separator: true},
      {label: 'Setup', icon: 'pi pi-cog', routerLink: ['/setup']}
    ];

    this.subscriptions.push(this.sandbox.authState$.subscribe(
      (response: any) => {
        this.authToken = response.authentication;
        this.getCustomerOrders(this.authToken);
      }));
  }

  getCustomerOrders(token) {
    this.subscriptions.push(this.orderService.getCustomerNewOrders(token, localStorage.getItem('outletId')).subscribe(
      (response: any) => {
        this.orders = response;
      }));

    this.subscriptions.push(this.orderService.getProcessedOrders(token, localStorage.getItem('outletId')).subscribe(
      (response: any) => {
        this.processedOrder = response;
      }));
  }

  getCancelItemTranslation(status): string {
    switch (status) {
      case OrderItemStatusConstants.ORDER_CANCEL_FOR_ITEM:
        return 'Cancelled';
        break;

      default:
        return 'Ordered';
        break;

    }
  }


  actionPerformedForItem(event, orderId, itemUniqueId, changedStatus) {
    event.stopPropagation();
    switch (changedStatus) {
      case OrderItemStatusConstants.ORDER_CANCEL_FOR_ITEM:
        this.orderService.setOrderItemStatus(orderId, itemUniqueId, this.authToken, changedStatus).subscribe(response => {
          if (response.payload) {
            this.getCustomerOrders(this.authToken);
          }
        });
        break;

      case OrderItemStatusConstants.REVERTFROMCANCEL: {
        this.orderService.setOrderItemStatus(orderId, itemUniqueId, this.authToken, changedStatus).subscribe(response => {
          if (response.payload) {
            this.getCustomerOrders(this.authToken);
          }
        });
        break;
      }
    }
  }

  actionPerformed(data, event, taskPerformed) {
    event.stopPropagation();
    console.log(event, taskPerformed);
    console.log(data);
    switch (taskPerformed) {
      case OrderStatusConstants.COMPLETED: {
        const token = btoa(this.authToken + ':' + data.orderId + ':' + taskPerformed);
        this.orderService.setOrderStatus(token).subscribe(response => {
          if (response.payload) {
            this.getCustomerOrders(this.authToken);
          }
        });
        break;
        break;
      }
      case OrderStatusConstants.INPROGRESS: {
        const token = btoa(this.authToken + ':' + data.orderId + ':' + taskPerformed);
        this.orderService.setOrderStatus(token).subscribe(response => {
          if (response.payload) {
            this.getCustomerOrders(this.authToken);
          }
          console.log(response);
        });
        break;
      }
      case OrderStatusConstants.COMPLETED : {
        this.confirmationService.confirm({
          message: 'Are you sure that you want to perform this action?',
          accept: () => {
            const token = btoa(this.authToken + ':' + data.orderId + ':' + taskPerformed);
            this.orderService.setOrderStatus(token).subscribe(response => {
              if (response.payload) {
                this.getCustomerOrders(this.authToken);
              }
              console.log(response);
            });
          }
        });
        break;
      }
      case OrderStatusConstants.DROP_WHOLE_ORDER : {
        this.confirmationService.confirm({
          message: 'Are you sure that you want to drop this order?',
          accept: () => {
            const token = btoa(this.authToken + ':' + data.orderId + ':' + taskPerformed);
            this.orderService.setOrderStatus(token).subscribe(response => {
              if (response.payload) {
                this.getCustomerOrders(this.authToken);
              }
              console.log(response);
            });
          }
        });
        break;
      }
    }

  }

}
