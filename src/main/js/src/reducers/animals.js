import {
  LIST_ANIMALS_SUCCEEDED,
  LIST_NEXT_ANIMAL,
  LIST_LIKED_ANIMALS_SUCCEEDED,
  LIST_OWNED_ANIMALS_SUCCEEDED,
  LIST_ADOPTED_ANIMALS_SUCCEEDED,
} from '../actions/types';

const initState = {
  animals: [],
  queuedAnimal: [],
  likedAnimals: [],
  adoptableAnimals: [],
  ownedAnimals: [],
};

export default (state = initState, action) => {
  switch (action.type) {
    case LIST_ANIMALS_SUCCEEDED:
      return { ...state, animals: action.payload, queuedAnimal: action.payload[0] };
    case LIST_NEXT_ANIMAL:
      return { ...state, queuedAnimal: state.animals[action.payload] };
    case LIST_ADOPTED_ANIMALS_SUCCEEDED:
      return { ...state, adoptableAnimals: action.payload };
    case LIST_OWNED_ANIMALS_SUCCEEDED:
      return { ...state, ownedAnimals: action.payload };
    case LIST_LIKED_ANIMALS_SUCCEEDED:
      return { ...state, likedAnimals: action.payload };
    default:
      return state;
  }
};
