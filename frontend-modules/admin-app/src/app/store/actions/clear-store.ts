import {type} from '../utils/type';
import {ActionWithPayload} from '../utils/action-with-payload';

export const TYPE_IDENTIFIER = '[CLEAR]';
export const ActionTypes = {
  CLEAR_STORE: type(TYPE_IDENTIFIER + ' clear store action'),
};

export class ClearStoreAction implements ActionWithPayload {
  type = ActionTypes.CLEAR_STORE;
}

export type Actions = ClearStoreAction;
