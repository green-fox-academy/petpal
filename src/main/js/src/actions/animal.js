import {
  ADD_ANIMAL_REQUEST,
  LIST_ANIMALS_REQUEST,
  LIST_NEXT_ANIMAL,
  LIST_ADOPTED_ANIMALS_REQUEST,
  LIST_OWNED_ANIMALS_REQUEST,
  LIST_LIKED_ANIMALS_REQUEST,
} from './types';

export const addAnimalRequest = payload => ({
  type: ADD_ANIMAL_REQUEST,
  payload,
});

export const listAnimalsRequest = () => ({
  type: LIST_ANIMALS_REQUEST,
});

export const listLikedAnimalsRequest = () => ({
  type: LIST_LIKED_ANIMALS_REQUEST,
});

export const listAdoptedAnimalsRequest = () => ({
  type: LIST_ADOPTED_ANIMALS_REQUEST,
});

export const listOwnedAnimalsRequest = () => ({
  type: LIST_OWNED_ANIMALS_REQUEST,
});

export const listNextAnimalFromRedux = payload => ({
  type: LIST_NEXT_ANIMAL,
  payload,
});
