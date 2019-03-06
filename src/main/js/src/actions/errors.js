import { SET_LOGIN_ERROR, SET_REGISTER_ERROR } from './types';

export const setRegisterError = message => ({
  type: SET_REGISTER_ERROR,
  message,
});

export const setLoginError = message => ({
  type: SET_LOGIN_ERROR,
  message,
});
