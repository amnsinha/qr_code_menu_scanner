import {Component, OnInit} from '@angular/core';
import {StaffService} from '../../service/staff.service';
import {TablesSandbox} from '../tables/tables.sandbox';
import {ToastMessageService} from '../../service/toast-message.service';
import {OutletDetails} from '../../model/menu/OutletDetails';
import {MenuService} from '../../service/menu.service';

@Component({
  selector: 'app-staff-panel',
  templateUrl: './menu-panel.component.html',
  styleUrls: ['./menu-panel.component.css']
})
export class MenuPanelComponent implements OnInit {

  public outletDetail: OutletDetails;
  public hotelOutletDetails: OutletDetails[];
  public showOutletAddScreen: boolean;

  constructor(private menuService: MenuService, private message: ToastMessageService,
              private tablesSandbox: TablesSandbox) {
    this.outletDetail = new OutletDetails();
  }

  ngOnInit(): void {
    this.getAllOutlet();
  }

  saveOutletDetails() {
    this.outletDetail.hotelId = localStorage.getItem('currentUser');
    this.menuService.addOutlet(this.outletDetail).subscribe(data => {
      if (data) {
        this.getAllOutlet();
        this.message.showMessage('Succesfully Saved', 'success');
      } else {
        this.message.showMessage('Username already exist', 'error');
      }

    });
  }

  getAllOutlet() {
    this.menuService.getAllOutlet(localStorage.getItem('currentUser')).subscribe(data => {
      if (data && data.payload) {
        this.hotelOutletDetails = data.payload;

      }
    });
  }

}
