import {ItemQuantity} from './item-quantity';

export class Item {
  id?: any;
  name?: string;
  price?: number;
  desc?: string;
  isVeg?: boolean;
  availableQuantity?: ItemQuantity[];
  selectedQuantity?: ItemQuantity;
  minPrice?: number;
  maxPrice?: number;
  displayPrice?: number;
  mrpItem?: number;
  parentMenuId?: number;
  alwayShowOnCheckout?: 0;
  updatedOn?: string;
  visible?: false;
  addedInCart?: false;
  rating?: { total_rating_text: string, value: number };
  nameSlug?: string;
  freeDishQuantity?: number;
  itemType?: string;
  itemTagImage?: string;
  qty?: number;
}
