import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import * as authenticationAction from '../../store/actions/authentication';
import * as fromRoot from '../../store/reducers';

@Component({
  selector: 'app-common',
  templateUrl: './common.component.html',
  styleUrls: ['./common.component.scss']
})
export class CommonComponent implements OnInit {

  constructor(protected store: Store<fromRoot.State>) {
    const token = 'U0c5MFpXd2dUR1ZsYkdFNk1DNDNNemt4TmpVeU5qVXdOalF4TVRRMk9qRTFPVEExTVRnNE1UVXhNakk2TUM0eU16WXdNekF3TURjd05UTTVNRGcwTWc9PQXSAD4DXSAD4D';
    this.store.dispatch(new authenticationAction.SetAuthentication(token));
  }

  ngOnInit() {
  }

}
