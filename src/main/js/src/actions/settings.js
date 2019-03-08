import { SET_GEO_DISTANCE_REQUESTED, SET_GEO_DISTANCE_REDUX } from './types';

export const setDistance = distance => ({
  type: SET_GEO_DISTANCE_REQUESTED,
  distance,
});

export const setDistanceREDUX = distance => ({
  type: SET_GEO_DISTANCE_REDUX,
  distance,
});
