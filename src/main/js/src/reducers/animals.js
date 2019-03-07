import { LIST_ANIMALS_SUCCEEDED } from '../actions/types';

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
    }
  ],
};

export default (state = initState, action) => {
  switch (action.type) {
    case LIST_ANIMALS_SUCCEEDED: return { ...state, animals: state.animals };
    default: return state;
  }
};
