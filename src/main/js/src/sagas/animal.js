import { call, put } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as API from '../services/api';

export function* addAnimalRequest(action) {
  const token = localStorage.getItem('token');
  try {
    const response = yield call(API.addAnimal, { info: action.payload, token });
    if (response.name) {
      yield put({
        type: actions.ADD_ANIMAL_SUCCEEDED,
      });
    } else {
      yield put({
        type: actions.ADD_ANIMAL_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.ADD_ANIMAL_FAILED,
    });
  }
}

export function* listAnimalsRequest() {
  const token = localStorage.getItem('token');
  try {
    const response = yield call(API.listAnimals, token);
    if (response.animals) {
      yield put({
        type: actions.LIST_ANIMALS_SUCCEEDED,
        // payload: response.animals,
      });
    } else {
      yield put({
        type: actions.LIST_ANIMALS_FAILED,
      });
    }
  } catch (error) {
    yield put({
      type: actions.LIST_ANIMALS_FAILED,
    });
  }
}
