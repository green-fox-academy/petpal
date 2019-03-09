import uniFetch from './unifetch';

// export const loginRequest = data => (
//   uniFetch('/login', 'POST', null, { username: data.username, password: data.password })
// );

export const loginRequest = () => ({ token: 200 });

// export const registerRequest = data => (
//   uniFetch('/register/user', 'POST', null, { username: data.username, password: data.password })
// );

export const registerRequest = () => ({ username: 'lol' });

export const addAnimal = data => uniFetch('/pets', 'POST', data.token, data.info);

// export const setGeoLocation = data => uniFetch('/pets', 'POST', data.token, data.info);

export const setGeoLocation = data => ({ distance: data.distance });

// export const listAnimals = token => uniFetch('/pets', 'GET', token);

export const listAnimals = () => ({
  animals: [{
    type: 'cat',
    name: 'Rozi',
    birth: '2019.01.09',
    gender: 'male',
    vaccinated: false,
    spayed: true,
  },
  {
    type: 'dog',
    name: 'Puki',
    gender: 'female',
    birth: '2019.01.01',
    vaccinated: true,
    spayed: false,
  },
  {
    type: 'pinguin',
    name: 'Pingu',
    gender: 'female',
    birth: '2019.02.01',
    vaccinated: false,
    spayed: false,
  },
  {
    type: 'hamster',
    name: 'Hami',
    gender: 'female',
    birth: '2019.01.18',
    vaccinated: true,
    spayed: true,
  }],
});
