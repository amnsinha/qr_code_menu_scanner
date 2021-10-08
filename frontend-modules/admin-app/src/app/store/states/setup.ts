
export interface Setup {
  currentViewState: SetupViewState;
}

export enum SetupViewState {
  NONE,
  EULA,
  SETTINGS,
  DEALER_INFO
}
