import * as authenticationActions from '../actions/authentication';
import {ActionWithPayload} from '../utils/action-with-payload';

export interface State {
  authentication: string;
}

const initialState: State = {
  authentication: '',
};

export function reducer(state: State = initialState, action: ActionWithPayload): State {

  switch (action.type) {
    case authenticationActions.ActionTypes.CLEAR: {
      return {
        authentication: '',
      };
    }
    case authenticationActions.ActionTypes.SET_AUTHENTICATION: {
      return {
        authentication: action.payload,
      };
    }
    default: {
      return state;
    }
  }
}

export const getAutheticationToken = (state: State) => state.authentication;
