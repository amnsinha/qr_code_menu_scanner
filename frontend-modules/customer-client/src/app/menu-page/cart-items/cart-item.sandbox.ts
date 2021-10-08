import {Injectable} from '@angular/core';
import * as fromRoot from '../../store/reducers';
import {select, Store} from '@ngrx/store';


@Injectable()
export class CartItemSandbox {

  public picklists$ = this.store.pipe(select(fromRoot.getPicklists));
  public authState$ = this.store.pipe(select(fromRoot.getAuthenticationState));

  constructor(protected store: Store<fromRoot.State>) {
  }
}
