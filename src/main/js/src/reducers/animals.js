import { LIST_ANIMALS_SUCCEEDED, LIST_NEXT_ANIMAL } from '../actions/types';

const initState = {
  animals: [
    {
      type: 'cat',
      name: 'cica',
      birth: '2019.01.01',
      vaccinated: true,
      spayed: false,
      photo: 'cica.jpg',
    },
    {
      type: 'dog',
      name: 'kutya',
      birth: '2019.01.01',
      vaccinated: true,
      spayed: false,
      photo: 'cica.jpg',
    },
  ],
  queuedAnimal: [],
};

export default (state = initState, action) => {
  switch (action.type) {
    case LIST_ANIMALS_SUCCEEDED: return { ...state, animals: state.animals, queuedAnimal: state.animals[0] };
    case LIST_NEXT_ANIMAL: return { ...state, queuedAnimal: state.animals[1] };
    default: return state;
  }
};
