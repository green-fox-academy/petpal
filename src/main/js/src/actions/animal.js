import { ADD_ANIMAL_REQUEST, LIST_ANIMALS_REQUEST, LIST_NEXT_ANIMAL } from './types';

export const addAnimalRequest = payload => ({
  type: ADD_ANIMAL_REQUEST,
  payload,
});

export const listAnimalsRequest = () => ({
  type: LIST_ANIMALS_REQUEST,
});

export const listNextAnimalFromRedux = payload => ({
  type: LIST_NEXT_ANIMAL,
  payload,
});
