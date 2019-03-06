import { takeEvery } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as userSaga from './user';

export default function* rootSaga() {
  yield takeEvery(actions.LOGIN_REQUESTED, userSaga.loginRequest);
  yield takeEvery(actions.LOGOUT_REQUESTED, userSaga.logoutRequest);
  yield takeEvery(actions.REGISTER_REQUESTED, userSaga.registerRequest);
}
