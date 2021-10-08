import {Component, OnInit} from '@angular/core';
import {ChefDetails} from '../../model/staff/ChefDetails';
import {StaffService} from '../../service/staff.service';
import {TablesSandbox} from '../tables/tables.sandbox';
import {Subscription} from 'rxjs';
import {ToastMessageService} from '../../service/toast-message.service';
import {ChefSettings} from '../../model/staff/ChefSettings';
import {RoleDetails} from '../../model/staff/RoleDetails';
import {MenuService} from '../../service/menu.service';
import {OutletDetails} from '../../model/menu/OutletDetails';

@Component({
  selector: 'app-staff-panel',
  templateUrl: './staff-panel.component.html',
  styleUrls: ['./staff-panel.component.css']
})
export class StaffPanelComponent implements OnInit {

  public authtoken: string;
  public showChefDetailScreen: boolean;
  public showRoleAddScreen: boolean;
  public chefDetails: ChefDetails;
  public roleDetails: RoleDetails;
  public chefSettings: ChefSettings;
  public hotelChefDetails: ChefDetails[];
  public hotelRoleDetails: RoleDetails[];
  public outletDetails: OutletDetails[];
  public selectedRolesFromOption: OutletDetails;
  private subscriptions: Array<Subscription> = [];

  constructor(private chefService: StaffService,
              private menuService: MenuService,
              private message: ToastMessageService,
              private tablesSandbox: TablesSandbox) {
    this.showChefDetailScreen = false;
    this.chefDetails = new ChefDetails();
    this.chefSettings = new ChefSettings();
    this.roleDetails = new RoleDetails();
  }

  addchefScreen() {
    if (this.showChefDetailScreen) {
      this.showChefDetailScreen = false;
    } else {
      this.showChefDetailScreen = true;
    }
  }

  ngOnInit() {
    this.subscriptions.push(this.tablesSandbox.authtoken$.subscribe(
      (response: any) => {
        this.authtoken = response.authentication;
      }));
    this.getAllChefDetails();
    this.getAllChefSettingsDetails();
    this.getAllOutlet();
  }

  saveChefDetails() {
    if (!this.selectedRolesFromOption) {
      return this.message.showMessage('Please select Role', 'error');
    }
    if (!this.chefDetails.chefUserName) {
      return this.message.showMessage('Please enter username', 'error');
    }
    if (!this.chefDetails.chefPassword) {
      return this.message.showMessage('Please enter password', 'error');
    }
    this.chefDetails.hotelUniqueId = localStorage.getItem('currentUser');
    this.chefDetails.outletHolds = this.selectedRolesFromOption;
    this.chefService.addChef(this.chefDetails).subscribe(data => {
      if (data.payload) {
        this.getAllChefDetails();
        this.chefDetails = data;
        this.message.showMessage('Successfully Saved', 'success');
      } else {
        this.message.showMessage('Username already exist', 'error');
      }

    });
  }


  saveChefSettings() {
    this.chefSettings.hotelUniqueId = localStorage.getItem('currentUser');
    this.chefService.saveChefSettings(this.chefSettings).subscribe(data => {
      if (data.payload) {
        this.getAllChefSettingsDetails();
        this.chefSettings = data;
        this.message.showMessage('Successfully Saved', 'success');
      } else {
        this.message.showMessage('Username already exist', 'error');
      }

    });
  }

  getAllChefDetails() {
    this.chefService.getAllChef(localStorage.getItem('currentUser')).subscribe(data => {
      if (data.payload) {
        this.hotelChefDetails = data.payload;
      }
    });
  }

  getAllOutlet() {
    this.menuService.getAllOutlet(localStorage.getItem('currentUser')).subscribe(data => {
      if (data && data.payload) {
        this.outletDetails = data.payload;
      }
    });
  }


  saveRoleDetails() {
    this.roleDetails.hotelId = localStorage.getItem('currentUser');
    this.chefService.saveRoles(this.roleDetails).subscribe(data => {
      if (data) {
        this.getAllOutlet();
        this.message.showMessage('Successfully Saved', 'success');
      } else {
        this.message.showMessage('Username already exist', 'error');
      }

    });
  }

  getAllChefSettingsDetails() {
    this.chefService.getAllChefSettingsDetails(localStorage.getItem('currentUser')).subscribe(data => {
    if(data){
     this.chefSettings = data;
    }

    });
  }


  removeChef(chefId) {
    this.chefService.removeChef(chefId).subscribe(data => {
      console.log(data);
    });
  }

}
