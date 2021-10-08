import {PicklistItem} from '../../model/picklist/picklist-item';
import * as picklistActions from '../actions/picklist';
import {ActionWithPayload} from '../utils/action-with-payload';
import {Item} from '../../model/menu/item';

export interface State {
  picklists: Item;
}

const initialState: State = {
  picklists: null,
};

export function reducer(state: State = initialState, action: ActionWithPayload): State {

  switch (action.type) {
    case picklistActions.ActionTypes.CLEAR: {
      return Object.assign({}, state, {
        picklists: [],
      });
    }
    case picklistActions.ActionTypes.UPDATE_PICKLIST: {
      return Object.assign({}, state, action.payload);
    }
    case picklistActions.ActionTypes.SET_ITEMS: {
      return Object.assign({}, state, {
        isDirty: true,
        picklists: [...action.payload]
      });
    }
    case picklistActions.ActionTypes.ADD_ITEM: {
      return Object.assign({}, state, {
        picklists: [...addItem(Object.assign([], state.picklists), action.payload)]
      });
    }
    case picklistActions.ActionTypes.UPDATE_ITEM: {
      return Object.assign({}, state, {
        isDirty: true,
        picklists: [...updateItem(Object.assign([], state.picklists), action.payload)]
      });
    }
    /*  case picklistActions.ActionTypes.REMOVE_ITEM: {
        return Object.assign({}, state, {
          isDirty: true,
          items: [...removeItem(Object.assign([], state), action.payload)]
        });
      }*/
    default: {
      return state;
    }
  }
}


export function addItem(savedItems: Item[], item: Item): PicklistItem[] {
  savedItems.push(item);
  return savedItems;
}

/*export function removeItem(items: PicklistItem[], item: PicklistItem): PicklistItem[] {
  /!* If rowIndex exists other than null then remove picklist item through row index and
  index is defined only on account of removing picklist through individual remove icon  *!/
  if (item.index !== null && item.index !== undefined) {
    // tslint:disable-next-line:radix
    items.splice(parseInt(item.index), 1);
  } else {
    // Flash impl modified to remove part based on part no type display
    for (let i = 0; i < items.length; i++) {
      if (item.partItemId && items[i].partItemId === item.partItemId) {
        items.splice(i, 1);
        break;
      } else if (items[i].partId === item.partId) {
        items.splice(i, 1);
        break;
      }
    }
  }
  return items;
}*/

export function updateItem(savedItems: Item[], item: Item): Item[] {

  let updatedList = savedItems;
  const findItem = savedItems.find(itemz => itemz.id === item.id);
  if (findItem) {
    if (findItem.qty === 0) {
      updatedList = savedItems.filter(itemz => itemz.id !== item.id && (item.qty === 0));
    }
  } else {
    if (item.qty > 0) {
      updatedList.push(item);
    }
  }

  /*  /!* If rowIndex exists other than null then update qty, picklistContext1,  picklistContext2 picklist item through row index   *!/
    for (let i = 0; i < items.length; i++) {
      if (items[i].id === item.id) {
        items[i] = item;
        break;
      }
    }*/
  return updatedList;
}

export const getPicklists = (state: State) => state.picklists;
