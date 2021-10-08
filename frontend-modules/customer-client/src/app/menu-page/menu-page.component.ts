import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng';
import {ProductsModel} from '../model/products.model';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import * as authenticationAction from '../store/actions/authentication';
import * as fromRoot from '../store/reducers';
import {MenuService} from '../services/menu.service';
import {Store} from '@ngrx/store';
import {OutletDetails} from '../model/menu/OutletDetails';

@Component({
  selector: 'app-menu-page',
  templateUrl: './menu-page.component.html',
  styleUrls: ['./menu-page.component.scss']
})
export class MenuPageComponent implements OnInit {

  items: MenuItem[];
  public tableNo:any;

    public showMenu: boolean;
  public outletDetails: OutletDetails[];
  public restaurantDataResponse: any;
  private routeSubscription: Subscription;
  private vegOnlyProducts: ProductsModel;

  constructor(public products: ProductsModel,
              protected store: Store<fromRoot.State>,
              private route: ActivatedRoute,
              private menuService: MenuService) {
  }

  ngOnInit() {
    this.tableNo = localStorage.getItem('tableId')
    this.showMenu = false;
    this.subscribeToActivatedRoute();
  }


  filter(filterName) {

    switch (filterName) {
      case 'ONLY_VEG':
        this.products.data.menuList.forEach(menu => {
          menu.categories.forEach(categoriesItr => {
            return categoriesItr.items.filter(item => item.isVeg);
          });
        });
        console.log(this.products);
        // this.products = this.products.filter()
        break;
      case 'typeToNarrowFilter':
        break;
    }
  }

  private subscribeToActivatedRoute() {
    if (!this.routeSubscription) {
      this.routeSubscription = this.route.params.subscribe(value => {
          const token = value['token'];
          this.store.dispatch(new authenticationAction.SetAuthentication(token));
          this.menuService.validateCustomer(token).subscribe((response: any) => {
            console.log(response);
            if (response.STATUS_CODE === '200') {
              this.showMenu = true;
            }
          });

/*
        this.menuService.getRestaurantOutLet(localStorage.getItem('hotelId')).subscribe((response: any) => {
          if (response.payload) {
            const outletId = response.payload[0].outletUniqueId;
            this.menuService.getRestaurantMenuByOutletId(localStorage.getItem('restaurantToken'), outletId).subscribe((response: any) => {
              if (response) {
                console.log("aa gya")
                console.log(response)
                this.restaurantDataResponse = response;
              }
            });
          }
        });*/



        }
      );
    }

  }
}
