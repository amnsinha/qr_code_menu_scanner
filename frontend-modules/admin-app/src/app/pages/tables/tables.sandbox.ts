import {Injectable} from '@angular/core';
import * as fromRoot from '../../store/reducers';
import {select, Store} from '@ngrx/store';


@Injectable()
export class TablesSandbox {

  public authtoken$ = this.store.pipe(select(fromRoot.getAuthenticationState));
  constructor(protected store: Store<fromRoot.State>) {
  }
}
