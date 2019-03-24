import uniFetch from './unifetch';

export const loginRequest = data => uniFetch('/login/user', 'POST', null, { username: data.email, password: data.password });

export const registerRequest = data =>
  uniFetch('/register/user', 'POST', null, { name: data.name, email: data.email, password: data.password });

export const addAnimal = data => uniFetch('/pets', 'POST', data.token, data.info);

// export const setGeoLocation = data => uniFetch('/pets', 'POST', data.token, data.info);

export const setGeoLocation = data => ({ distance: data.distance });

export const listAnimals = token => uniFetch('/home/pets', 'GET', token);

export const googleLoginRequest = () => uniFetch('/oauth2/authorize/google', 'GET');

// export const listLikedAnimals= token => uniFetch('/pets/liked', 'GET', token);

// export const listOwnedAnimals= token => uniFetch('/pets/owned', 'GET', token);

// export const listAdoptedAnimals= token => uniFetch('/pets/adoptable', 'GET', token);

export const listLikedAnimals = token => ({
  likedAnimals: [
    { id: 1, name: 'pistike', photoPath: 'dog.jpg', gender: 'female', type: 'dog' },
    { id: 2, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 3, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 4, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 5, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 6, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 7, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 8, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 9, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 10, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 11, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 12, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 13, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 14, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 15, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 16, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
  ],
});

export const listOwnedAnimals = token => ({
  ownedAnimals: [
    { id: 1, name: 'pistike', photoPath: 'dog.jpg', gender: 'female', type: 'dog' },
    { id: 2, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
  ],
});

export const listAdoptedAnimals = token => ({
  adoptableAnimals: [
    { id: 1, name: 'pistike', photoPath: 'dog.jpg', gender: 'female', type: 'dog' },
    { id: 2, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 3, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
    { id: 4, name: 'sanyika', photoPath: 'cat.jpg', gender: 'male', type: 'cat' },
  ],
});
