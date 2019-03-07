import { call, put } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as API from '../services/api';
import { history } from '../store/configureStore';

export function* loginRequest(action) {
  try {
    const response = yield call(API.loginRequest, action.payload);
    if (response.status === 200) {
      yield put({
        type: actions.LOGIN_SUCCEDED,
      });
      history.push('/home/find');
    } else {
      yield put({
        type: actions.LOGIN_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.LOGIN_FAILED,
    });
  }
}

export function* registerRequest(action) {
  try {
    const response = yield call(API.registerRequest, action.payload);
    if (response.username) {
      yield put({
        type: actions.REGISTER_SUCCEDED,
      });
    } else {
      yield put({
        type: actions.REGISTER_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.REGISTER_FAILED,
    });
  }
}

export function* logoutRequest() {
  try {
    const token = localStorage.removeItem('token');
    if (!token) {
      yield put({
        type: actions.LOGOUT_SUCCEDED,
      });
      history.push('/');
    } else {
      yield put({
        type: actions.LOGOUT_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.LOGOUT_FAILED,
    });
  }
}
