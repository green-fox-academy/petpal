import { call, put } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as API from '../services/api';
import { history } from '../store/configureStore';

export function* loginRequest(action) {
  try {
    const response = yield call(API.loginRequest, action.payload);
    const { token, refreshToken } = response;
    if (token) {
      yield put({
        type: actions.LOGIN_SUCCEDED,
      });
      localStorage.setItem('accesstoken', token);
      localStorage.setItem('refreshtoken', refreshToken);
      history.push('/home/find');
    } else {
      yield put({
        type: actions.SET_LOGIN_ERROR,
        message: 'Incorrect username or password!',
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
    if (response.name) {
      yield put({
        type: actions.SELECT_CURRENT_FORM,
        payload: 'login',
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

export function* googleSignIn() {
  try {
    const response = yield call(API.googleLoginRequest);
    if (response) {
      yield put({
        type: actions.GOOGLE_LOGIN_SUCCEDED,
      });
      history.push('/home/find');
    } else {
      yield put({
        type: actions.SET_LOGIN_ERROR,
        message: 'Google sign in failed!',
      });
    }
  } catch (error) {
    yield put({
      type: actions.GOOGLE_LOGIN_FAILED,
    });
  }
}
