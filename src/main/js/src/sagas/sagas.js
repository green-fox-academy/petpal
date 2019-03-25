import { takeEvery, takeLatest } from 'redux-saga/effects';
import * as actions from '../actions/types';
import * as userSaga from './user';
import * as animalSaga from './animal';
import * as chatSaga from './chats';
import settingsSaga from './settings';

export default function* rootSaga() {
  yield takeLatest(actions.LOGIN_REQUESTED, userSaga.loginRequest);
  yield takeEvery(actions.LOGOUT_REQUESTED, userSaga.logoutRequest);
  yield takeLatest(actions.REGISTER_REQUESTED, userSaga.registerRequest);
  yield takeEvery(actions.ADD_ANIMAL_REQUEST, animalSaga.addAnimalRequest);
  yield takeEvery(actions.LIST_LIKED_ANIMALS_REQUEST, animalSaga.listLikedAnimalsRequest);
  yield takeEvery(actions.LIST_OWNED_ANIMALS_REQUEST, animalSaga.listOwnedAnimalsRequest);
  yield takeEvery(actions.LIST_ADOPTED_ANIMALS_REQUEST, animalSaga.listAdoptedAnimalsRequest);
  yield takeEvery(actions.LIST_ANIMALS_REQUEST, animalSaga.listAnimalsRequest);
  yield takeLatest(actions.SET_GEO_DISTANCE_REQUESTED, settingsSaga);
  yield takeLatest(actions.GOOGLE_SIGN_IN, userSaga.googleSignIn);
  yield takeEvery(actions.LIST_USER_CHATS_REQUEST, chatSaga.listChatsOfUserRequest);
}
