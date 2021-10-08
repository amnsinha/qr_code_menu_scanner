/**
 * Created by nb9280 on 10/20/2016.
 */
import * as LoginActions from '../../store/actions/login';
import {Login, LoginState} from '../states/login';

export interface State extends Login {
}

const initialState: State = {
  currentLoginState: LoginState.BLANK,
};

export function reducer(state: State = initialState, action: LoginActions.Actions): State {

  switch (action.type) {
    case LoginActions.ActionTypes.SET_LOGIN_STATE: {
      return {
        currentLoginState: action.payload as LoginState,
      };
    }

    default: {
      return state;
    }
  }
}

export const getCurrentLoginState = (state: State) => state.currentLoginState;

