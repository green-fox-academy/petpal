import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import users from './users';
import errors from './errors';
import toggles from './toggles';
import settings from './settings';
import animals from './animals';
import chat from './chat';

export default combineReducers({
  users,
  chat,
  animals,
  errors,
  toggles,
  settings,
  routing: routerReducer,
});
