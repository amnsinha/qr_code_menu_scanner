export class OrderDetails {
  specialInstruction: string;
  orderTableNo: string;
  orderId: string;
  customerNumber: string;
  customerName: string;
  orderItem: OrderItem[];
}

export class OrderItem {
  orderItem: string;
  rated:number;
  orderQty: number;
  itemPrice: number;
  isVeg: boolean;
  itemStatus: string;
  itemUniqueId: string;
}

