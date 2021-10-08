import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private routeSubscription: Subscription;

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.subscribeToActivatedRoute();
  }

  private subscribeToActivatedRoute() {
    if (!this.routeSubscription) {
      this.routeSubscription = this.route.params.subscribe(value => {
          const checkFamilycode = value['familiyCode'];
          //check for family code
          if (checkFamilycode) {

            let familyid = '123AB';
            //get all data from this familyid


          } else {
            //Join our system
          }
        }
      );
    }

  }
}
