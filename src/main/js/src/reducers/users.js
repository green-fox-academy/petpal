import { LOGIN_SUCCEDED, LOGOUT_SUCCEDED, GOOGLE_LOGIN_SUCCEDED } from '../actions/types';

const initState = {
  isAuthenticated: false,
};

export default (state = initState, action) => {
  switch (action.type) {
    case LOGIN_SUCCEDED:
      return { ...state, isAuthenticated: true };
    case LOGOUT_SUCCEDED:
      return { ...state, isAuthenticated: false };
    case GOOGLE_LOGIN_SUCCEDED:
      return { ...state, isAuthenticated: true };
    default:
      return state;
  }
};
