import uniFetch from './unifetch';

export const loginRequest = data => uniFetch('/login/user', 'POST', null, { username: data.username, password: data.password });

export const registerRequest = data => uniFetch('/register/user', 'POST', null, { username: data.username, password: data.password });

export const addAnimal = data => uniFetch('/pets', 'POST', data.token, data.info);

// export const setGeoLocation = data => uniFetch('/pets', 'POST', data.token, data.info);

export const setGeoLocation = data => ({ distance: data.distance });

export const listAnimals = token => uniFetch('/home/pets', 'GET', token);
