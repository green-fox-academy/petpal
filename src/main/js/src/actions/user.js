import * as actions from './types';

export const requestLogin = data => ({
  type: actions.LOGIN_REQUESTED,
  payload: data,
});

export const requestRegister = data => ({
  type: actions.REGISTER_REQUESTED,
  payload: data,
});

export const requestLogout = () => ({
  type: actions.LOGOUT_REQUESTED,
});

export const setSelectedForm = form => ({
  type: actions.SELECT_CURRENT_FORM,
  payload: form,
});

export const toggleHamburgerIcon = bool => ({
  type: actions.TOGGLE_HAMB_ICON,
  payload: bool,
});

export const loginWithGoogle = () => ({
  type: actions.GOOGLE_SIGN_IN,
});

export const selectFavTab = tab => ({
  type: actions.SELECT_FAV_TAB,
  payload: tab,
});
