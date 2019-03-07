import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import users from './users';
import errors from './errors';
import toggles from './toggles';
import settings from './settings';

export default combineReducers({
  users,
  errors,
  toggles,
  settings,
  routing: routerReducer,
});
