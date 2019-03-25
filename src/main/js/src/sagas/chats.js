import { call, put } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as API from '../services/api';

export function* listChatsOfUserRequest() {
  const token = localStorage.getItem('accesstoken');
  try {
    const response = yield call(API.listChatsOfUser, token);
    if (response.ownChats) {
      yield put({
        type: actions.LIST_USER_CHATS_SUCCEEDED,
        payload: response.ownChats,
      });
    } else {
      yield put({
        type: actions.LIST_USER_CHATS_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.LIST_USER_CHATS_FAILED,
    });
  }
}
