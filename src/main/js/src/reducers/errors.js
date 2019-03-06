import { SET_LOGIN_ERROR, SET_REGISTER_ERROR } from '../actions/types';

const initState = {
  loginErrorMsg: '',
  registerErrorMsg: '',
};

export default (state = initState, action) => {
  switch (action.type) {
    case SET_LOGIN_ERROR: return { ...state, loginErrorMsg: action.message };
    case SET_REGISTER_ERROR: return { ...state, registerErrorMsg: action.message };
    default: return state;
  }
};
