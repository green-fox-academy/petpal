const host = 'https://petpalgf.herokuapp.com/';

export default (url, method, token, data) => {
  const options = {
    headers: {
      accept: 'application/json',
      mode: 'cors',
    },
    method,
  };

  if (method !== 'GET') options.headers['content-type'] = 'application/json';
  if (token) options.headers.authorization = `Bearer ${token}`;
  if (data && method !== 'GET') options.body = JSON.stringify(data);

  return fetch(`${host}${url}`, options)
    .then(data => data.json());
};
