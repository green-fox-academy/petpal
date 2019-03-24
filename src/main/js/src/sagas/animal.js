import { call, put } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as API from '../services/api';

export function* addAnimalRequest(action) {
  const token = localStorage.getItem('accesstoken');
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
  const token = localStorage.getItem('accesstoken');
  try {
    const response = yield call(API.listAnimals, token);
    if (Array.isArray(response) && response[0].name) {
      yield put({
        type: actions.LIST_ANIMALS_SUCCEEDED,
        payload: response,
      });
    } else {
      yield put({
        type: actions.LIST_ANIMALS_FAILED,
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actions.LIST_ANIMALS_FAILED,
    });
  }
}

export function* listLikedAnimalsRequest() {
  const token = localStorage.getItem('accesstoken');
  try {
    const response = yield call(API.listLikedAnimals, token);
    if (response.likedAnimals) {
      yield put({
        type: actions.LIST_LIKED_ANIMALS_SUCCEEDED,
        payload: response.likedAnimals,
      });
    } else {
      yield put({
        type: actions.LIST_LIKED_ANIMALS_FAILED,
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actions.LIST_LIKED_ANIMALS_FAILED,
    });
  }
}

export function* listOwnedAnimalsRequest() {
  const token = localStorage.getItem('accesstoken');
  try {
    const response = yield call(API.listOwnedAnimals, token);
    if (response.ownedAnimals) {
      yield put({
        type: actions.LIST_OWNED_ANIMALS_SUCCEEDED,
        payload: response.ownedAnimals,
      });
    } else {
      yield put({
        type: actions.LIST_OWNED_ANIMALS_FAILED,
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actions.LIST_OWNED_ANIMALS_FAILED,
    });
  }
}

export function* listAdoptedAnimalsRequest() {
  const token = localStorage.getItem('accesstoken');
  try {
    const response = yield call(API.listAdoptedAnimals, token);
    if (response.adoptableAnimals) {
      yield put({
        type: actions.LIST_ADOPTED_ANIMALS_SUCCEEDED,
        payload: response.adoptableAnimals,
      });
    } else {
      yield put({
        type: actions.LIST_ADOPTED_ANIMALS_FAILED,
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actions.LIST_ADOPTED_ANIMALS_FAILED,
    });
  }
}
