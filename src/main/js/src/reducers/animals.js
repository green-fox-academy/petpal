import { LIST_ANIMALS_SUCCEEDED, LIST_NEXT_ANIMAL } from '../actions/types';

const initState = {
  animals: [{
    type: 'cat',
    name: 'Rozi',
    birth: '2019.01.09',
    gender: 'male',
    vaccinated: false,
    spayed: true,
    photo: 'cat.jpg',
  },
  {
    type: 'dog',
    name: 'Puki',
    gender: 'female',
    birth: '2019.01.01',
    vaccinated: true,
    spayed: false,
    photo: 'dog.jpg',
  },
  {
    type: 'pinguin',
    name: 'Pingu',
    gender: 'female',
    birth: '2019.02.01',
    vaccinated: false,
    spayed: false,
    photo: 'pinguin.jpg',
  },
  {
    type: 'hamster',
    name: 'Hami',
    gender: 'female',
    birth: '2019.01.18',
    vaccinated: true,
    spayed: true,
    photo: 'hamster.jpg',
  }
  ],
  queuedAnimal: [],
};

export default (state = initState, action) => {
  switch (action.type) {
    // case LIST_ANIMALS_SUCCEEDED: return { ...state, animals: action.payload, queuedAnimal: action.payload[0] };
    case LIST_ANIMALS_SUCCEEDED: return { ...state, animals: state.animals, queuedAnimal: state.animals[0] };
    case LIST_NEXT_ANIMAL: return { ...state, queuedAnimal: state.animals[action.payload] };
    default: return state;
  }
};
