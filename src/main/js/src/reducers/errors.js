import { SET_LOGIN_ERROR, SET_REGISTER_ERROR, SET_ANIMAL_ERROR } from '../actions/types';

const initState = {
  loginErrorMsg: '',
  animalErrorMsg: '',
  registerErrorMsg: '',
};

export default (state = initState, action) => {
  switch (action.type) {
    case SET_ANIMAL_ERROR:
      return { ...state, animalErrorMsg: action.message };
    case SET_LOGIN_ERROR:
      return { ...state, loginErrorMsg: action.message };
    case SET_REGISTER_ERROR:
      return { ...state, registerErrorMsg: action.message };
    default:
      return state;
  }
};
