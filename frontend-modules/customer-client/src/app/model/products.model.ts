import {Injectable} from '@angular/core';
import {MenuList} from './menu/menu-list';

@Injectable()
export class ProductsModel {
  public data: MenuList = {
    menuList: [{
      name: 'Biryani',
      categories: [{
        name: 'Nonveg',
        items: [{
          qty: 1,
          id: 'zxcxzcw',
          name: 'Hydrabadi-Biryani',
          isVeg: false,
          minPrice: 400,
          selectedQuantity: {price: 2000, quantity: 'full'},
          price: 4100,
          itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
            '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
        },
          {
            qty: 1,
            id: 'wqe2eds',
            name: 'Bollywood-Biryani-options',
            isVeg: true,
            minPrice: 200,
            price: 1100,
            selectedQuantity: {price: 200, quantity: 'half'},
            availableQuantity: [{price: 200, quantity: 'half'}],
            itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
              '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
          }
        ]
      },
        {
          name: 'Veg',
          items: [{
            qty: 1,
            id: 'dsf2rfdwe',
            name: 'Hydrabadi-Biryani',
            isVeg: true,
            minPrice: 400,
            itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
              '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
          },
            {
              qty: 1,
              id: 'dsfsjfmds',
              name: 'Bollywood-Biryani',
              isVeg: true,
              price: 200,
              minPrice: 200,
              itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
                '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
            }
          ]
        }]
    },
      {
        name: 'Pulao',
        categories: [{
          name: 'Biryani',
          items: [{
            qty: 1,
            id: 'dsfhgsdkjf',
            name: 'Hydrabadi-Biryani',
            isVeg: true,
            minPrice: 400,
            itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
              '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
          },
            {
              qty: 1,
              id: 'shfkssdf',
              name: 'Bollywood-Biryani',
              isVeg: true,
              minPrice: 200,
              price: 200,
              itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
                '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
            },
            {
              qty: 1,
              id: 'sdoufhsdf',
              name: 'Bollywood-Biryani',
              isVeg: false,
              price: 200,
              minPrice: 200,
              itemTagImage: 'https://rukminim1.flixcart.com/image/832/832/jg6v24w0/washing-powder/' +
                '3/r/d/2-2-kg-top-load-surf-excel-original-imaf3udkm4v5mdrq.jpeg?q=70',
            }
          ]
        }]
      }]
  };
}
