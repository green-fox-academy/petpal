import { SET_LOGIN_ERROR, SET_REGISTER_ERROR, SET_ANIMAL_ERROR } from './types';

export const setRegisterError = message => ({
  type: SET_REGISTER_ERROR,
  message,
});

export const setLoginError = message => ({
  type: SET_LOGIN_ERROR,
  message,
});

export const setAddAnimalError = message => ({
  type: SET_ANIMAL_ERROR,
  message,
});
