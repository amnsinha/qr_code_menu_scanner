import {Action} from '@ngrx/store';

/**
 * interface created because ngrx store removed payload from Action
 * this is not type safe!
 */
export interface ActionWithPayload extends Action {
  payload?: any;
}
