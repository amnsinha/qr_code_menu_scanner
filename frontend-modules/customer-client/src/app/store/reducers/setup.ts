/**
 * Created by nb9280 on 12/23/2016.
 */
import * as SetupActions from '../../store/actions/setup';
import {Setup, SetupViewState} from '../states/setup';
import {ActionWithPayload} from '../utils/action-with-payload';

export interface State extends Setup {
}

const initialState: State = {
  currentViewState: SetupViewState.NONE,
};


export function reducer(state: State = initialState, action: ActionWithPayload): State {

  switch (action.type) {

    case SetupActions.ActionTypes.SET_SETUP_VIEW_STATE: {
      return Object.assign({}, state, {
        currentViewState: action.payload as SetupViewState,
      });
    }

    default: {
      return state;
    }
  }

}

export const getSetupViewState = (state: State) => state.currentViewState;

