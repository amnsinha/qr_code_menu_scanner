/**
 * combineReducers is another useful metareducer that takes a map of reducer
 * functions and creates a new reducer that stores the gathers the values
 * of each reducer and stores them using the reducer's key. Think of it
 * almost like a database, where every reducer is a table in the db.
 *
 * More: https://egghead.io/lessons/javascript-redux-implementing-combinereducers-from-scratch
 */
/**
 * The compose function is one of our most handy tools. In basic terms, you give
 * it any number of functions and it returns a function. This new function
 * takes a value and chains it through every composed function, returning
 * the output.
 *
 * More: https://drboolean.gitbooks.io/mostly-adequate-guide/content/ch5.html
 */
import {ActionReducer, ActionReducerMap, createSelector} from '@ngrx/store';
/**
 * Every reducer module's default export is the reducer function itself. In
 * addition, each module should export a type or interface that describes
 * the state of the reducer plus any selector functions. The `* as`
 * notation packages up all of the exports into a single object.
 */
import * as fromLogin from './login';
import * as fromSetup from './setup';
import * as fromPicklist from './picklist';
/**
 * special case action - to reset the store state
 */
import * as clearStoreActions from '../../store/actions/clear-store';
import * as fromAuthentication from './authentication';

/**
 * As mentioned, we treat each reducer like a table in a database. This means
 * our top level state interface is just a map of keys to inner state types.
 */
export interface State {
  loginState: fromLogin.State;
  setupState: fromSetup.State;
  authenticationState: fromAuthentication.State;
  picklistState: fromPicklist.State;
}

/**
 * Because metareducers take a reducer function and return a new reducer,
 * we can use our compose helper to chain them together. Here we are
 * using combineReducers to make our top level reducer, and then
 * wrapping that in storeLogger. Remember that compose applies
 * the result from right to left.
 */
/* IMPORTANT - these names need to match the names defined in the state object above (not sure why)*/
export const reducers: ActionReducerMap<State> = {
  loginState: fromLogin.reducer,
  setupState: fromSetup.reducer,
  authenticationState: fromAuthentication.reducer,
  picklistState: fromPicklist.reducer
};

// clear store meta-reducer
export function clearStoreReducer(reducer: ActionReducer<any>): ActionReducer<any> {
  return function newReducer(state: any, action: any) {
    if (action.type === clearStoreActions.ActionTypes.CLEAR_STORE) {
      state = undefined;
    }
    return reducer(state, action);
  };
}

export const getLoginState = (state: State) => state.loginState;
export const getSetupState = (state: State) => state.setupState;
export const getPicklistsState = (state: State) => state.picklistState;
export const getAuthenticationState = (state: State) => state.authenticationState;


/**
 * Every reducer module exports selector functions, however child reducers
 * have no knowledge of the overall state tree. To make them useable, we
 * need to make new selectors that wrap them.
 *
 * Once again our compose function comes in handy. From right to left, we
 * first select the books state then we pass the state to the book
 * reducer's getBooks selector, finally returning an observable
 * of search results.
 */

export const getAuthenticationToken = createSelector(getAuthenticationState, fromAuthentication.getAutheticationToken);
export const getPicklists = createSelector(getPicklistsState, fromPicklist.getPicklists);
export const getCurrentLoginState = createSelector(getLoginState, fromLogin.getCurrentLoginState);
export const getSetupViewState = createSelector(getSetupState, fromSetup.getSetupViewState);
