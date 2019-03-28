import { LOGIN_SUCCEDED, LOGOUT_SUCCEDED, GOOGLE_AUTH_OK } from '../actions/types';

const initState = {
  isAuthenticated: false,
};

export default (state = initState, action) => {
  switch (action.type) {
    case LOGIN_SUCCEDED:
      return { ...state, isAuthenticated: true };
    case LOGOUT_SUCCEDED:
      return { ...state, isAuthenticated: false };
    case GOOGLE_AUTH_OK:
      return { ...state, isAuthenticated: true };
    default:
      return state;
  }
};
