import {PicklistItem} from '../../model/picklist/picklist-item';

export interface Picklist {
  id: string;
  lastModified: string;
  lastModifiedUser: string;
  name: string;
  note: string;
  items: PicklistItem[]; // array of picklist items
}
