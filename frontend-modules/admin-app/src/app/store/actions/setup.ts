/**
 * Created by nb9280 on 12/23/2016.
 */
import {type} from '../utils/type';
import {SetupViewState} from '../states/setup';
import {ActionWithPayload} from '../utils/action-with-payload';


export const TYPE_IDENTIFIER = '[Setup]';

export const ActionTypes = {
  SET_SETUP_VIEW_STATE: type(`${TYPE_IDENTIFIER} Set Setup View State`),
};


export class SetSetupViewState implements ActionWithPayload {
  type = ActionTypes.SET_SETUP_VIEW_STATE;

  constructor(public payload: SetupViewState) {
  }
}

export type Actions = SetSetupViewState;
