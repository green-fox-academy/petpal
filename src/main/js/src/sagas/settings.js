import { call, put } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as API from '../services/api';

export default function* setGeoLocation(action) {
  const token = localStorage.getItem('token');
  try {
    const response = yield call(API.setGeoLocation, { distance: action.distance, token });
    if (response.distance) {
      yield put({
        type: actions.SET_GEO_DISTANCE_SUCCEEDED,
        distance: response.distance,
      });
    } else {
      yield put({
        type: actions.SET_GEO_DISTANCE_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.SET_GEO_DISTANCE_FAILED,
    });
  }
}
