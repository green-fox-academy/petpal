import { ADD_ANIMAL_REQUEST, LIST_ANIMALS_REQUEST } from './types';

export const addAnimalRequest = payload => ({
  type: ADD_ANIMAL_REQUEST,
  payload,
});

export const listAnimalsRequest = () => ({
  type: LIST_ANIMALS_REQUEST,
});
