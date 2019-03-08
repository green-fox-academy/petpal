import { LIST_ANIMALS_SUCCEEDED, LIST_NEXT_ANIMAL } from '../actions/types';

const initState = {
  animals: [],
  queuedAnimal: [],
};

export default (state = initState, action) => {
  switch (action.type) {
    case LIST_ANIMALS_SUCCEEDED: return { ...state, animals: action.payload, queuedAnimal: action.payload[0] };
    case LIST_NEXT_ANIMAL: return { ...state, queuedAnimal: state.animals[1] };
    default: return state;
  }
};
