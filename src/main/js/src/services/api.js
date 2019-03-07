import uniFetch from './unifetch';

// export const loginRequest = data => (
//   uniFetch(`/login/${data.target}`, 'POST', null, { username: data.username, password: data.password })
// );

export const loginRequest = () => ({ status: 200 });

export const registerRequest = data => (
  uniFetch(`/register/user`, 'POST', null, { username: data.username, password: data.password })
);
