import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {StorageService} from '../../services/storage.service';
import {CartService} from '../../services/cart.service';

@Component({
  selector: 'billing-dir',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss']
})
export class BillingComponent implements OnInit {
  public billingForm: any = {};
  public errorsInfo: any = {};
  @Input('billingFields') billingCache: any = {};

  constructor(
    public fb: FormBuilder,
    public storage: StorageService,
    public cart: CartService,
  ) {

  }

  ngOnInit() {
    this.loadForm();
  }

  loadForm() {
    const temp = {};
    let billingInfo = this.cart.loadCheckoutInfo('customerInfo');
    if (billingInfo === undefined || billingInfo === '' || billingInfo === null) {
      billingInfo = {};
    }
    (this.billingCache).forEach((item) => {

      temp[item.uid] = [billingInfo[item.uid], item.validation];

    });

    this.billingForm = this.fb.group(temp);

  }


  send() {
    if (this.billingForm.valid) {

      this.storage.set({
        customerInfo: this.billingForm.value
      });
      document.location.href = '/checkout';
    }
  }

}
