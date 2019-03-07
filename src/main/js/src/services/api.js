import uniFetch from './unifetch';

// export const loginRequest = data => (
//   uniFetch('/login', 'POST', null, { username: data.username, password: data.password })
// );

export const loginRequest = () => ({ token: 200 });

export const registerRequest = data => (
  uniFetch('/register/user', 'POST', null, { username: data.username, password: data.password })
);

export const addAnimal = data => uniFetch('/pets', 'POST', data.token, data.info);

// export const listAnimals = token => uniFetch('/pets', 'GET', token);

export const listAnimals = () => ({ animals: 'lol' });
