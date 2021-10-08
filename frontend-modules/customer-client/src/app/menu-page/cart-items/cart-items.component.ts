import { AfterContentChecked, Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import * as fromRoot from '../../store/reducers';
import { CartItemSandbox } from './cart-item.sandbox';
import { Item } from '../../model/menu/item';
import { OrderItemService } from '../../services/order-item.service';
import { OrderDetails, OrderItem } from '../../model/order-items/order-details';
import { ConfirmationService } from 'primeng';
import { Router } from '@angular/router';
import * as picklistActions from '../../store/actions/picklist';
import { OrderConstantComponent } from '../../utils/order-constant.component';

@Component({
  selector: 'app-cart-items',
  templateUrl: './cart-items.component.html',
  styleUrls: ['./cart-items.component.scss']
})
export class CartItemsComponent implements OnInit, AfterContentChecked, OnChanges {

  public items: Item[] = [];
  public total: number;
  @Input('selectedOutletId') selectedOutletId;
  public specialInstruction: string;
  public customerNumber: string;
  public customerName: string;
  private subscriptions: Array<Subscription> = [];
  private runningValue: any;
  private authToken: string;
  public display: boolean = false;
  constructor(protected store: Store<fromRoot.State>,
    private confirmationService: ConfirmationService,
    protected router: Router,
    protected orderItemService: OrderItemService,
    protected sandbox: CartItemSandbox) {
  }


  textAreaAdjust(o) {
    o.style.height = '1px';
    o.style.height = (25 + o.scrollHeight) + 'px';
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes && changes['selectedOutletId'] && changes['selectedOutletId'].currentValue) {
      this.selectedOutletId = changes['selectedOutletId'].currentValue;
    }
  }


  ngOnInit() {
    this.subscriptions.push(this.sandbox.picklists$.subscribe(
      (response: any) => {
        this.items = response;
        this.getTotal();
      }));

    this.subscriptions.push(this.sandbox.authState$.subscribe(
      (response: any) => {
        this.authToken = response.authentication;
      }));
  }

  ngAfterContentChecked() {
  }

  oneMinus(index) {
    if (this.items[index].qty > 0) {
      this.items[index].qty = this.items[index].qty - 1;
      this.getTotal();
    }
    this.updateCartItems(this.items[index]);
  }

  updateCartItems(item: Item) {
    this.store.dispatch(new picklistActions.UpdatePicklistItemAction(item));
  }

  clearCartItems() {
    this.store.dispatch(new picklistActions.ClearPicklistAction());
  }

  onePlus(index) {
    if (this.items[index].qty >= 0) {
      this.items[index].qty = this.items[index].qty + 1;
      this.getTotal();
    }
    this.updateCartItems(this.items[index]);
  }

  orderNow() {
    const orderDetails = new OrderDetails();
    orderDetails.orderItem = [];
    orderDetails.specialInstruction = this.specialInstruction;
    orderDetails.customerName = this.customerName;
    orderDetails.customerNumber = this.customerNumber;
    this.items.forEach(item => {
      const orderItem = new OrderItem();
      orderItem.itemUniqueId = item.id;
      orderItem.isVeg = item.isVeg;
      orderItem.itemPrice = item.price;
      orderItem.orderQty = item.qty;
      orderItem.orderItem = item.name;
      orderItem.orderItem = item.name;
      orderItem.itemStatus = OrderConstantComponent.ORDER_PLACED_FOR_ITEM;
      orderDetails.orderItem.push(orderItem);
    });
   
    
        this.orderItemService.orderItem(orderDetails, this.authToken, this.selectedOutletId).subscribe((res: any) => {
          if (res.STATUS_CODE === '200') {
            this.clearCartItems();
            this.router.navigateByUrl('menu/start/' + this.authToken + '/processing');
          } else {
          }
        });
    


  }


  showDialog() {
    this.display = true;
  }


  getTotal() {
    console.log(this.items);
    let total = 0;
    if (this.items && this.items.length >= 1) {
      for (let i = 0; i < this.items.length; i++) {
        if (this.items[i].price) {
          total += this.items[i].price * this.items[i].qty;
        }
      }
      return total;
    }
  }
}
