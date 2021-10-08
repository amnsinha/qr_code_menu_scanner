import {type} from '../utils/type';
import {ActionWithPayload} from '../utils/action-with-payload';

export const TYPE_IDENTIFIER = '[Authentication]';
export const ActionTypes = {
  CLEAR: type(TYPE_IDENTIFIER + ' clear Authentication'),
  SET_AUTHENTICATION: type(TYPE_IDENTIFIER + 'set Authentication'),
};

export class ClearAuthentication implements ActionWithPayload {
  type = ActionTypes.CLEAR;
}

export class SetAuthentication implements ActionWithPayload {
  type = ActionTypes.SET_AUTHENTICATION;

  constructor(public payload: string) {
  }
}


export type Actions
  = ClearAuthentication
  | SetAuthentication;


