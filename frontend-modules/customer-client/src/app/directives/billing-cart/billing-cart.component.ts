import {Component} from '@angular/core';
import {CartService} from '../../services/cart.service';

@Component({
  selector: 'app-billing-cart',
  templateUrl: './billing-cart.component.html',
  styleUrls: ['./billing-cart.component.scss']
})
export class BillingCartComponent {

  constructor(
    public cart: CartService
  ) {

  }

}
