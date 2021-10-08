import {Injectable} from '@angular/core';
import {StorageService} from './storage.service';
import {Store} from '@ngrx/store';
import * as fromRoot from '../store/reducers/index';

@Injectable()

export class CartService {
  public allItems: any = {};
  public cartData: any = [];
  public cartItemsList: any = {};
  public cartTotal: any = 0;
  public cartItemsStorageName = 'mycart';

  constructor(
    public storage: StorageService,
    protected store: Store<fromRoot.State>,
  ) {
    this.loadCart();
  }

  loadCart() {
    const temp = this.storage.get('mycart');
    if (temp === undefined || temp === '' || temp === null) {
      this.cartData = {};
    } else {
      this.cartData = temp;
    }
  }

  addToCart(pid, qty, replace) {

    if (this.cartData[pid] === undefined) {
      this.cartData[pid] = 0;
    }
    if (replace === '') {
      this.cartData[pid] = this.cartData[pid] + qty;
    } else {
      this.cartData[pid] = parseInt(qty);
    }

    if (this.cartData[pid] === 0) {
      delete this.cartData[pid];
    }
    this.storeItems();
  }

  storeItems() {
    this.storage.set({
      mycart: this.cartData
    });
    this.listCartItems();
  }

  listCartItems() {
    const tempCart = [];
    const getActualItems = Object.keys(this.cartData);
    const cartDataItems = this.cartData;
    let tempTotal = 0;

    // tslint:disable-next-line:only-arrow-functions
    const onlyChoosenItems = (this.allItems).filter(function(item) {
      if (getActualItems.indexOf(item.id) !== -1) {
        tempCart.push({
          pid: item.id,
          name: item.product_name,
          qty: cartDataItems[item.id],
          price: item.product_price * cartDataItems[item.id],
        });
        tempTotal += item.product_price * cartDataItems[item.id];
      }
    });


    this.cartItemsList = tempCart;
    this.cartTotal = tempTotal;

  }

  loadCheckoutInfo(storageKey: string) {
    return this.storage.get(storageKey);
  }

  emptyCart() {
    this.storage.set({
      mycart: {}
    });
  }


}
