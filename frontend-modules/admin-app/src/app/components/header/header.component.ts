import {Component, ElementRef, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {ROUTES} from '../sidebar/sidebar.component';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public focus;
  public isCollapsed = true;
  public listTitles: any[];
  public location: Location;


  constructor(location: Location, private element: ElementRef, private router: Router) {
    this.location = location;
    this.isCollapsed = true;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
  }

  logout() {
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }

  getTitle() {
    let titlee = this.location.prepareExternalUrl(this.location.path());
    if (titlee.charAt(0) === '#') {
      titlee = titlee.slice(1);
    }

    for (let item = 0; item < this.listTitles.length; item++) {
      if (this.listTitles[item].path === titlee) {
        return this.listTitles[item].title;
      }
    }
    return 'Dashboard';
  }

}
