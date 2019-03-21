import uniFetch from './unifetch';

export const loginRequest = data => uniFetch('/login/user', 'POST', null, { email: data.email, password: data.password });

export const registerRequest = data =>
  uniFetch('/register/user', 'POST', null, { name: data.name, email: data.email, password: data.password });

export const addAnimal = data => uniFetch('/pets', 'POST', data.token, data.info);

// export const setGeoLocation = data => uniFetch('/pets', 'POST', data.token, data.info);

export const setGeoLocation = data => ({ distance: data.distance });

export const listAnimals = token => uniFetch('/home/pets', 'GET', token);

export const googleLoginRequest = () => uniFetch('/oauth2/authorize/google', 'GET');
