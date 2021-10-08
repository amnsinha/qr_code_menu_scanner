import {Component, OnInit} from '@angular/core';
import {BillingFormModel} from '../../model/billingformfields.model';

@Component({
  selector: 'app-billing-page',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss']
})

export class BillingComponent implements OnInit {
  public cartflag = false;

  constructor(
    public billing: BillingFormModel
  ) {

  }

  ngOnInit() {
    this.ref();
  }

  ref() {
    this.cartflag = false;
    setTimeout(() => {
      this.cartflag = true;
    }, 10);
  }

}
