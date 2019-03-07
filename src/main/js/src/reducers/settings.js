import { SET_SETTINGS_MESSAGE, SET_CURRENT_DISTANCE } from '../actions/types';

const initState = {
  distanceMessage: '',
  currentDistance: 10,
};

export default (state = initState, action) => {
  switch (action.type) {
    case SET_SETTINGS_MESSAGE: return { ...state, distanceMessage: action.message };
    case SET_CURRENT_DISTANCE: return { ...state, currentDistance: action.distance, distanceMessage: `You are now searching between 0 and ${action.distance}km!` };
    default: return state;
  }
};
