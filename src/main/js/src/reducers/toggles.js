import { SELECT_CURRENT_FORM, TOGGLE_HAMB_ICON, SELECT_FAV_TAB } from '../actions/types';

const initState = {
  selectedForm: 'login',
  isHamburgerToggled: false,
  currentFavTab: 'liked',
};

export default (state = initState, action) => {
  switch (action.type) {
    case SELECT_CURRENT_FORM:
      return { ...state, selectedForm: action.payload };
    case TOGGLE_HAMB_ICON:
      return { ...state, isHamburgerToggled: action.payload };
    case SELECT_FAV_TAB:
      return { ...state, currentFavTab: action.payload };
    default:
      return state;
  }
};
