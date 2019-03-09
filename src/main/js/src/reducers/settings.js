import { SET_SETTINGS_MESSAGE, SET_GEO_DISTANCE_SUCCEEDED, SET_GEO_DISTANCE_REDUX } from '../actions/types';

const initState = {
  distanceMessage: '',
  currentDistance: 10,
  inputDistance: 40,
};

export default (state = initState, action) => {
  switch (action.type) {
    case SET_SETTINGS_MESSAGE: return { ...state, distanceMessage: action.message };
    case SET_GEO_DISTANCE_REDUX: return { ...state, inputDistance: action.distance };
    case SET_GEO_DISTANCE_SUCCEEDED: return {
      ...state,
      currentDistance: action.distance,
      distanceMessage: `You are now searching between 0 and ${action.distance}km!`,
    };
    default: return state;
  }
};
