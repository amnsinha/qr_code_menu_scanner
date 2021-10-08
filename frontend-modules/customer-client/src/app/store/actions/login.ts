/**
 * Created by nb9280 on 10/20/2016.
 */
import {type} from '../utils/type';
import {LoginState} from '../states/login';
import {ActionWithPayload} from '../utils/action-with-payload';

export const TYPE_IDENTIFIER = '[Login]';

export const ActionTypes = {
  SET_LOGIN_STATE: type(`${TYPE_IDENTIFIER} Set Login State`),
};

export class SetLoginState implements ActionWithPayload {
  type = ActionTypes.SET_LOGIN_STATE;

  constructor(public payload: LoginState) {
  }
}

export type Actions = SetLoginState;
