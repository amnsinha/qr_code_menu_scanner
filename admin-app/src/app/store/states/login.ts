
export interface Login {
  currentLoginState: LoginState;
}

export enum LoginState {
  LOGIN,
  LOGOUT,
  RESET_PASSWORD,
  TEMP_PASSWORD,
  CHANGE_PASSWORD,
  TOO_MANY_ATTEMPTS,
  MULTIPLE_LICENSES,
  BLANK
}
