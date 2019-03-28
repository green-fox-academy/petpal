const host = 'http://localhost:8080';

export default (url, method, token, data) => {
  const options = {
    headers: {
      accept: 'application/json',
      mode: 'cors',
    },
    method,
  };

  if (method !== 'GET' && url !== '/pets') options.headers['content-type'] = 'application/json';
  if (token) options.headers.authorization = `Bearer ${token}`;
  if (data && method !== 'GET' && url !== '/pets') options.body = JSON.stringify(data);
  if (data && url === '/pets' && method === 'POST') options.body = data;

  return fetch(`${host}${url}`, options).then(data => data.json());
};
