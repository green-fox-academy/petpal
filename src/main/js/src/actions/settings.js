import { SET_SETTINGS_MESSAGE, SET_CURRENT_DISTANCE } from './types';

export const setDistanceMessage = message => ({
  type: SET_SETTINGS_MESSAGE,
  message,
});

export const setDistance = distance => ({
  type: SET_CURRENT_DISTANCE,
  distance,
});
