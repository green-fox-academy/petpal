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
  },]
});
