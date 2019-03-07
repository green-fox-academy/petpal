import { ADD_ANIMAL_REQUEST } from './types';

export const addAnimalRequest = payload => ({
  type: ADD_ANIMAL_REQUEST,
  payload,
});
