import {AbstractDocument} from '../AbstractDocument';

export class OrderItems extends AbstractDocument{
  specialInstruction: string;
  orderTableNo: string;
  orderStatus: string;
  orderId: string;
  orderItem: OrderItem[];
}

export class OrderItem {
  orderItem: string;
  orderQty: number;
  isVeg: boolean;
  itemStatus: string;
  itemUniqueId: string;
  itemPrice: number;
}

