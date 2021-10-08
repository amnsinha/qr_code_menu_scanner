export class OrderItems {
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
}

