import {type} from '../utils/type';
import {ActionWithPayload} from '../utils/action-with-payload';
import {Item} from '../../model/menu/item';

export const TYPE_IDENTIFIER = '[Picklist]';
export const ActionTypes = {
  CLEAR: type(TYPE_IDENTIFIER + ' clear picklist'),
  UPDATE_PICKLIST: type(TYPE_IDENTIFIER + ' update picklist'),
  SET_ITEMS: type(TYPE_IDENTIFIER + 'set picklist items'),
  ADD_ITEM: type(TYPE_IDENTIFIER + 'add picklist item'),
  UPDATE_ITEM: type(TYPE_IDENTIFIER + 'update a picklist item'),
  REMOVE_ITEM: type(TYPE_IDENTIFIER + 'remove picklist item'),
};

export class ClearPicklistAction implements ActionWithPayload {
  type = ActionTypes.CLEAR;
}

export class UpdatePicklistAction implements ActionWithPayload {
  type = ActionTypes.UPDATE_PICKLIST;

  constructor(public payload: Item) {
  }
}

export class SetPicklistItemsAction implements ActionWithPayload {
  type = ActionTypes.SET_ITEMS;

  constructor(public payload: Item[]) {
  }
}

export class AddPicklistItemAction implements ActionWithPayload {
  type = ActionTypes.ADD_ITEM;

  constructor(public payload: Item) {
  }
}


export class UpdatePicklistItemAction implements ActionWithPayload {
  type = ActionTypes.UPDATE_ITEM;

  constructor(public payload: Item) {
  }
}


export type Actions
  = ClearPicklistAction
  | UpdatePicklistAction
  | SetPicklistItemsAction
  | AddPicklistItemAction
  | UpdatePicklistItemAction;


