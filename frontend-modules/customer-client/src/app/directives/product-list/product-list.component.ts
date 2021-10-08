import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {StorageService} from '../../services/storage.service';
import {CartService} from '../../services/cart.service';
import {MenuList} from '../../model/menu/menu-list';
import {Store} from '@ngrx/store';
import * as fromRoot from '../../store/reducers/index';
import * as picklistActions from '../../store/actions/picklist';
import {CartItemSandbox} from '../../menu-page/cart-items/cart-item.sandbox';
import {Subscription} from 'rxjs';
import {Item} from '../../model/menu/item';

@Component({
  selector: 'app-product-list-dir',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit, OnChanges {

  @Input('menuCategorieClicked') menuCategorieClicked;
  @Input('allProductList') allprdts: MenuList = {};
  @Input('searchedText') searchedProduct = '';
  @Input('sortingBy') sortByOption = '';
  @Output() refresh: EventEmitter<string> = new EventEmitter();
  public cartItems: Item[];
  private subscriptions: Array<Subscription> = [];

  constructor(
    protected store: Store<fromRoot.State>,
    public storage: StorageService,
    public cart: CartService,
    protected sandbox: CartItemSandbox
  ) {
  }

  ngOnInit() {
    this.sortByOption = 'product_name';
    this.subscriptions.push(this.sandbox.picklists$.subscribe(
      (response: any) => {
        this.cartItems = response;
      }));
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes && changes['menuCategorieClicked']) {
      let divId: any = changes['menuCategorieClicked'];
      //document.getElementById(divId.currentValue).scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});
    }
  }

  oneMinus(index) {
    if (this.cartItems[index].qty > 0) {
      this.cartItems[index].qty = this.cartItems[index].qty - 1;
    }
  }

  onePlus(index) {
    if (this.cartItems[index].qty >= 0) {
      this.cartItems[index].qty = this.cartItems[index].qty + 1;
    }
  }

  updateCartItems(item: Item) {
    this.store.dispatch(new picklistActions.UpdatePicklistItemAction(item));
  }

  addToCart(product, index = 0) {
    if (!product.qty) {
      product.qty = 1;
    }
    if (product.selectedQuantity) {
      product.price = product.selectedQuantity[0].price;
    } else {
      product.price = product.minPrice;
    }
    this.store.dispatch(new picklistActions.AddPicklistItemAction(product));
  }


}
