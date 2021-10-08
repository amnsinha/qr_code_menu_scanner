import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {OutletDetails} from '../../model/menu/OutletDetails';
import {MenuItem} from 'primeng';
import {MenuService} from '../../services/menu.service';

@Component({
  selector: 'app-products-page',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})

export class ProductsComponent implements OnInit {
  public cartflag = false;
  public sortBy = '';
  public searchText = '';
  public sortOption = 'product_name|asc';
  public outletDetailsData: OutletDetails[];
  public productsData: any;
  @Output() onlyVeg: EventEmitter<boolean> = new EventEmitter();
  public menuCategorieClicked: any;
  public menuItems: MenuItem[];
  public selectedOutlet: MenuItem;
  public outletId: string;
  public items: MenuItem[] = [];

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.ref();
    this.createOutletMenu();
  }

  public mapMenutoSideBar() {
    this.items = [];
    this.productsData.menuList.forEach(menuList => {
      console.log(menuList);
      const item = {} as MenuItem;
      item.label = menuList.name;
      item.command = (event) => {
        // this.handleMenuItemClick(item.label);
      };
      if (menuList.categories.length > 1) {
        item.items = [];
        menuList.categories.forEach(category => {
          const newCategory = {} as any;
          newCategory.label = category.name;
          item.items.push(newCategory);
        });
      }
      this.items.push(item);
    });
  }

  createOutletMenu() {

    this.menuService.getRestaurantOutLet(localStorage.getItem('hotelId')).subscribe((response: any) => {
      if (response.payload) {
        this.outletDetailsData = response.payload;
        if (this.outletDetailsData && this.outletDetailsData.length > 0) {
          this.getRestaurantOutletMenu(this.outletDetailsData[0].outletUniqueId);
          this.menuItems = [];
          for (let i = 0; i < this.outletDetailsData.length; i++) {
            this.menuItems.push(
              {
                label: this.outletDetailsData[i].outletName,
                id: this.outletDetailsData[i].outletUniqueId,
                command: (event) => {
                  this.getRestaurantOutletMenu(event.item.id);
                }
              }
            );
          }
          this.selectedOutlet = this.menuItems[0];
        }
      }
    });
  }

  menuCategorieClickedNavtoItem(event) {
    this.menuCategorieClicked = event;
  }

  showOnlyVeg() {
    if (this.onlyVeg) {
      this.onlyVeg.emit(false);
    } else {
      this.onlyVeg.emit(true);
    }
  }

  showMenu(data) {
    console.log(data);
  }

  ref() {
    this.cartflag = false;
    setTimeout(() => {
      this.cartflag = true;
    }, 10);
  }


  private getRestaurantOutletMenu(outletId: any) {
    this.outletId = outletId;
    this.menuService.getRestaurantMenuByOutletId(localStorage.getItem('restaurantToken'), outletId).subscribe((response: any) => {
      if (response) {
        this.productsData = response;
        this.mapMenutoSideBar();
      }
    });
  }
}
