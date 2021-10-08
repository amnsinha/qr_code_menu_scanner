import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {ConfirmationService, MenuItem} from 'primeng';
import {RazorPayService} from '../../services/razor-pay.service';
import {OrderItemService} from '../../services/order-item.service';
import {ProcessingOrderSandbox} from './processing-order.sandbox';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {OrderDetails, OrderItem} from '../../model/order-items/order-details';
import {PaymentService} from '../../services/payment.service';
import {FilesInfo} from '../../model/FilesInfo';
import {OrderConstantComponent} from '../../utils/order-constant.component';

import {HotelDiscounts} from '../../model/HotelDiscounts';
import {MessageService} from 'primeng/api';
import {ToastMessageService} from '../../services/toast-message.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

declare let Razorpay: any;

@Component({
  selector: 'app-processing-order',
  templateUrl: './processing-order.component.html',
  styleUrls: ['./processing-order.component.scss']
})
export class ProcessingOrderComponent implements OnInit, OnDestroy {
  public callWaiter: boolean;
  public isPaymentSuccess: boolean = false;
  public name = 'Angular';
  public response;
  public razorpayResponse;
  public showModal = false;
  public isOfferApplied = false;
  public offerInvalid = false;
  public discountPercentage: number = 0;
  public authToken;
  public interval;
  public activeIndex: number;
  public orderId;
  public totalAmount;
  public orderDetails: OrderDetails;
  public filesInfo: FilesInfo[] = [];
  items: MenuItem[];
  private subscriptions: Array<Subscription> = [];
  public form: FormGroup;

  constructor(
    private razorpayService: RazorPayService,
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private orderItemService: OrderItemService,
    private message: ToastMessageService,
    protected router: Router,
    protected processingOrderSandbox: ProcessingOrderSandbox,
    private cd: ChangeDetectorRef
  ) {
    this.offerInvalid = false;
    this.discountPercentage = 0;
    this.authToken = localStorage.getItem('restaurantToken');
    this.form = this.fb.group({
      rating1: ['', Validators.required],
      rating2: [4]
    });
  }

  ngOnInit() {
    this.getPaymentQRCode();
    this.getmyAllorder();
    this.items = [
      {label: 'Order Placed'},
      {label: 'Cooking'},
      {label: 'Completed'}
    ];

    this.interval = setInterval(() => {
      this.getmyAllorder();
    }, 5000);
  }

  ngOnDestroy(): void {
    clearInterval(this.interval);
  }

  navigateToOrderPage() {
    clearInterval(this.interval);
    this.router.navigateByUrl('menu/start/' + localStorage.getItem('restaurantToken'));
  }

  getPaymentQRCode() {
    this.paymentService.getPaymentQRCode(localStorage.getItem('hotelId')).subscribe((res: any) => {
      if (res.payload.files.length > 0) {


        this.filesInfo = res.payload.files;

        let imagedata = this.convertURIToImageData(res.payload.files[0]);

        const out = {
          data: new Uint8ClampedArray(imagedata),
        };
         


      }
    });
  }

  public convertURIToImageData(URI) {
    let img = new Image();
    return img.src = URI;
   
  }

  getTotal(orderItems): number {
    let total = 0;
    if (orderItems && orderItems.length > 0) {
      for (let i = 0; i < orderItems.length; i++) {
        if (orderItems[i].itemPrice) {
          total += orderItems[i].itemPrice * orderItems[i].orderQty;
        }
      }
      this.totalAmount = total;
        return total;

    }
  }

  discountRemoved(code) {
    this.discountPercentage = 0;
    this.offerInvalid = false;
  }

  checkDiscount(code) {
    if(!code){
      this.offerInvalid = false;
      return this.message.showMessage('Please add discount code', 'warn');
    }

    this.isOfferApplied = false;
    const hotelDiscounts = new HotelDiscounts();
    hotelDiscounts.discountCode = code;
    hotelDiscounts.hotelUniqueId = localStorage.getItem('hotelId');
    this.orderItemService.checkDiscount(hotelDiscounts).subscribe((res: any) => {
      if (res && res.payload && res.payload.discount) {
        this.isOfferApplied = true;
        this.offerInvalid = false;
        this.discountPercentage = res.payload.discount;
        this.totalAmount
      }
      if(res && res.payload === null){
        this.offerInvalid = true
      }
    });
  }

  getmyAllorder() {
    this.orderItemService.getMyPlacedOrder(localStorage.getItem('restaurantToken')).subscribe((res: any) => {
      if (res) {
        this.orderDetails = res;
        this.getAllOrderStatus(this.orderDetails);
      }

    });
  }


  getItemStatusColor(itm: OrderItem) {
    switch (itm.itemStatus) {
      case OrderConstantComponent.ORDER_COMPLETED_FOR_ITEM: {
        return 'green';
      }
      case OrderConstantComponent.ORDER_INPROGRESS_FOR_ITEM: {
        return 'orange';
      }
      case OrderConstantComponent.ORDER_CANCEL_FOR_ITEM: {
        return 'red';
      }
      case OrderConstantComponent.ORDER_PLACED_FOR_ITEM: {
        return 'grey';
      }
      default:
        return 'grey';
    }
  }

  getItemStatusText(itm: OrderItem) {
    switch (itm.itemStatus) {
      case OrderConstantComponent.ORDER_COMPLETED_FOR_ITEM: {
        return 'Completed';
      }
      case OrderConstantComponent.ORDER_INPROGRESS_FOR_ITEM: {
        return 'In progress';
      }
      case OrderConstantComponent.ORDER_CANCEL_FOR_ITEM: {
        return 'Cancelled';
      }
      case OrderConstantComponent.ORDER_PLACED_FOR_ITEM: {
        return 'Order Placed';
      }
      default:
        return 'Order Placed';
    }
  }

  private getAllOrderStatus(orderDetails: OrderDetails) {
    let isTotallyInProgress = false;
    let orderJustPlaced = false;
    orderDetails.orderItem.forEach(data => {
      if (data.itemStatus === OrderConstantComponent.ORDER_PLACED_FOR_ITEM) {
        orderJustPlaced = true;
        isTotallyInProgress = false;
      }
      if (data.itemStatus === OrderConstantComponent.ORDER_INPROGRESS_FOR_ITEM) {
        isTotallyInProgress = true;
        orderJustPlaced = false;
      }
    });
    if (orderJustPlaced) {
      this.activeIndex = 0;
    }
    if (isTotallyInProgress) {
      this.activeIndex = 1;
    }
    const orderplacedItems = orderDetails.orderItem.filter(data => data.itemStatus === OrderConstantComponent.ORDER_COMPLETED_FOR_ITEM);
    if (orderplacedItems.length === orderDetails.orderItem.length) {
      this.activeIndex = 2;
    }
  }
}
